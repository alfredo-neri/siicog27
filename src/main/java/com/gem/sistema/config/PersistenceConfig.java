package com.gem.sistema.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Equivalente de WEB-INF/spring/applicationContext-jpa.xml.
 *
 * El DataSource ya no se busca por JNDI ({@code jdbc/DIFEMDS_26}): lo crea Spring Boot a partir de
 * {@code spring.datasource.*} (ver application.yml). Las propiedades de Hibernate tambien viven ahi.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.gem.sistema.business.repository",
		repositoryFactoryBeanClass = CompatibilidadRepositoriosFactoryBean.class)
@EntityScan(basePackages = "com.gem.sistema.business.domain")
public class PersistenceConfig {

	/** El XML original registraba la plantilla JDBC con id {@code template}. */
	@Bean(name = { "template", "jdbcTemplate" })
	public JdbcTemplate template(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	/**
	 * Transacciones declarativas ({@code @Transactional} en servicios).
	 *
	 * En difem26 nunca estuvieron activas: el {@code <tx:annotation-driven/>} estaba comentado, asi que
	 * cada llamada a un repositorio o a JdbcTemplate confirmaba por separado. Activarlas cambia el
	 * comportamiento contable (atomicidad, rollback, procedimientos almacenados dentro de una misma
	 * transaccion), por lo que quedan apagadas hasta validarlas con pruebas:
	 * {@code siicog27.transacciones-declarativas=true}.
	 *
	 * Los repositorios de Spring Data siguen siendo transaccionales por si mismos, igual que antes.
	 */
	@Configuration
	@ConditionalOnBooleanProperty(name = "siicog27.transacciones-declarativas")
	@EnableTransactionManagement
	static class TransaccionesDeclarativas {
	}
}
