package com.gem.sistema.config;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Conserva en los repositorios dos comportamientos de Spring Data 1.9 / Hibernate 4 (difem26) de los que
 * depende el codigo heredado.
 *
 * 1. Argumentos null. Spring Data 1.9 aceptaba {@code findAll(null)}, {@code count(null)},
 * {@code findAll(pred, (Sort) null)} o un {@code Pageable} null y los interpretaba como "sin filtro",
 * "sin orden" y "sin paginar". Spring Data 4 lanza IllegalArgumentException. Se traducen:
 * <ul>
 * <li>Predicate null: {@code new BooleanBuilder()} (sin condicion).</li>
 * <li>Sort null: {@link Sort#unsorted()}.</li>
 * <li>Pageable null: {@link Pageable#unpaged()}.</li>
 * </ul>
 * Solo se tocan parametros declarados con esos tipos; el resto de argumentos null se pasa igual.
 *
 * 2. Altas con id 0. El codigo crea registros nuevos con {@code setId(0L)} (p. ej. los renglones de
 * poliza). Con un id distinto de null, {@code save} hace {@code merge}: Hibernate 4 insertaba el registro
 * al no encontrar la fila 0, pero Hibernate 7 lanza StaleObjectStateException. En {@code save*}, si la
 * entidad tiene id generado con valor 0, se deja en null para que se inserte como nueva. Ninguna tabla
 * con id generado tiene registros con id 0, asi que no se confunde con una actualizacion.
 */
public class CompatibilidadRepositoriosFactoryBean<T extends Repository<S, ID>, S, ID>
		extends JpaRepositoryFactoryBean<T, S, ID> {

	/** Campo @Id con @GeneratedValue de cada entidad (Optional.empty si no tiene). */
	private static final Map<Class<?>, Optional<Field>> ID_GENERADO = new ConcurrentHashMap<>();

	public CompatibilidadRepositoriosFactoryBean(Class<? extends T> repositoryInterface) {
		super(repositoryInterface);
	}

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		RepositoryFactorySupport factory = super.createRepositoryFactory(entityManager);
		factory.addRepositoryProxyPostProcessor(
				(proxyFactory, repositoryInformation) -> proxyFactory.addAdvice(compatibilidadSpringData19()));
		return factory;
	}

	private static MethodInterceptor compatibilidadSpringData19() {
		return invocation -> {
			Object[] args = invocation.getArguments();
			Class<?>[] types = invocation.getMethod().getParameterTypes();
			boolean guardar = invocation.getMethod().getName().startsWith("save");
			for (int i = 0; i < args.length && i < types.length; i++) {
				if (args[i] == null) {
					if (types[i] == Predicate.class) {
						args[i] = new BooleanBuilder();
					} else if (types[i] == Sort.class) {
						args[i] = Sort.unsorted();
					} else if (types[i] == Pageable.class) {
						args[i] = Pageable.unpaged();
					}
				} else if (guardar) {
					if (args[i] instanceof Iterable<?> entidades) {
						entidades.forEach(CompatibilidadRepositoriosFactoryBean::idCeroComoNuevo);
					} else {
						idCeroComoNuevo(args[i]);
					}
				}
			}
			return invocation.proceed();
		};
	}

	private static void idCeroComoNuevo(Object entidad) {
		if (entidad == null) {
			return;
		}
		ID_GENERADO.computeIfAbsent(entidad.getClass(), CompatibilidadRepositoriosFactoryBean::buscarIdGenerado)
				.ifPresent(campo -> {
					try {
						if (esCero(campo.get(entidad))) {
							campo.set(entidad, null);
						}
					} catch (IllegalAccessException e) {
						throw new IllegalStateException("No se pudo leer el id de " + entidad.getClass(), e);
					}
				});
	}

	private static Optional<Field> buscarIdGenerado(Class<?> clase) {
		for (Class<?> c = clase; c != null && c != Object.class; c = c.getSuperclass()) {
			for (Field campo : c.getDeclaredFields()) {
				if (campo.isAnnotationPresent(Id.class) && campo.isAnnotationPresent(GeneratedValue.class)
						&& !campo.getType().isPrimitive() && Number.class.isAssignableFrom(campo.getType())) {
					campo.setAccessible(true);
					return Optional.of(campo);
				}
			}
		}
		return Optional.empty();
	}

	private static boolean esCero(Object valor) {
		if (valor instanceof BigDecimal decimal) {
			return decimal.signum() == 0;
		}
		if (valor instanceof BigInteger entero) {
			return entero.signum() == 0;
		}
		return valor instanceof Number numero && numero.longValue() == 0L;
	}
}
