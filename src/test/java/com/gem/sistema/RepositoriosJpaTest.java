package com.gem.sistema;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryComposition.RepositoryFragments;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * Valida, sin base de datos, que Hibernate 7 acepta el mapeo de todas las entidades y que Spring Data
 * puede construir cada repositorio: consultas derivadas del nombre del metodo y cada {@code @Query}
 * JPQL. Es la comprobacion que hace el arranque de la aplicacion, pero informa de todos los fallos a
 * la vez en lugar de detenerse en el primero.
 */
class RepositoriosJpaTest {

	private static LocalContainerEntityManagerFactoryBean factoryBean;
	private static EntityManagerFactory emf;

	@BeforeAll
	static void iniciarHibernate() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:db2://127.0.0.1:1/SINBD");
		dataSource.setDriverClassName("com.ibm.db2.jcc.DB2Driver");

		Properties jpa = new Properties();
		// mismos parametros que application.yml, sin acceso a metadatos JDBC
		jpa.put("hibernate.dialect", "org.hibernate.dialect.DB2Dialect");
		jpa.put("hibernate.boot.allow_jdbc_metadata_access", "false");
		jpa.put("hibernate.physical_naming_strategy",
				"org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
		jpa.put("hibernate.implicit_naming_strategy",
				"org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl");
		jpa.put("hibernate.enable_lazy_load_no_trans", "true");

		factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factoryBean.setPackagesToScan("com.gem.sistema.business.domain");
		factoryBean.setJpaProperties(jpa);
		factoryBean.afterPropertiesSet();
		emf = factoryBean.getObject();
	}

	@AfterAll
	static void cerrar() {
		if (factoryBean != null) {
			factoryBean.destroy();
		}
	}

	@Test
	void todosLosRepositoriosSeConstruyen() throws Exception {
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false) {
			@Override
			protected boolean isCandidateComponent(
					org.springframework.beans.factory.annotation.AnnotatedBeanDefinition definition) {
				return definition.getMetadata().isInterface();
			}
		};
		scanner.addIncludeFilter(new AssignableTypeFilter(Repository.class));

		EntityManager em = emf.createEntityManager();
		JpaRepositoryFactory factory = new JpaRepositoryFactory(em);
		List<String> fallos = new ArrayList<>();
		int total = 0;
		for (BeanDefinition bd : scanner.findCandidateComponents("com.gem.sistema.business.repository")) {
			Class<?> repo = Class.forName(bd.getBeanClassName());
			total++;
			try {
				factory.getRepository(repo, fragmentosPersonalizados(repo));
			} catch (RuntimeException e) {
				fallos.add(repo.getSimpleName() + " :: " + causaRaiz(e));
			}
		}
		em.close();

		fallos.forEach(f -> System.out.println("FALLO " + f));
		System.out.printf("Repositorios: %d, con fallos: %d%n", total, fallos.size());
		assertTrue(fallos.isEmpty(), fallos.size() + " repositorios no se pueden construir; ver salida");
	}

	/**
	 * Los metodos de las interfaces *Custom los implementan clases *Impl con dependencias de Spring;
	 * aqui basta con un sustituto para que Spring Data no intente derivarlos como consultas.
	 */
	private static RepositoryFragments fragmentosPersonalizados(Class<?> repo) {
		List<Object> fragments = new ArrayList<>();
		for (Class<?> parent : repo.getInterfaces()) {
			if (!Repository.class.isAssignableFrom(parent) && parent.getName().startsWith("com.gem.")) {
				fragments.add(Proxy.newProxyInstance(repo.getClassLoader(), new Class<?>[] { parent },
						(proxy, method, args) -> switch (method.getName()) {
							case "equals" -> proxy == args[0];
							case "hashCode" -> System.identityHashCode(proxy);
							case "toString" -> "fragmento " + parent.getSimpleName();
							default -> throw new UnsupportedOperationException(method.getName());
						}));
			}
		}
		return RepositoryFragments.just(fragments.toArray());
	}

	private static String causaRaiz(Throwable e) {
		String detalle = e.getMessage();
		Throwable t = e;
		while (t.getCause() != null && t.getCause() != t) {
			t = t.getCause();
			if (t.getMessage() != null) {
				detalle = t.getMessage();
			}
		}
		String metodo = "";
		for (Throwable c = e; c != null; c = c.getCause()) {
			if (c.getMessage() != null && c.getMessage().contains("for method")) {
				metodo = c.getMessage().replaceAll("(?s).*for method \\[?([^\\];]*).*", "$1") + " :: ";
				break;
			}
		}
		return metodo + (detalle == null ? e.toString() : detalle.replaceAll("\\s+", " "));
	}
}
