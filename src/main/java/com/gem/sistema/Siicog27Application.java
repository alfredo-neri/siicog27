package com.gem.sistema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Punto de entrada de SIICOG 27.
 *
 * Sustituye a la cadena web.xml -> ContextLoaderListener -> applicationContext.xml de difem26.
 * El escaneo arranca en {@code com.gem.sistema}, igual que el
 * {@code <context:component-scan base-package="com.gem.sistema"/>} original.
 *
 * Se puede ejecutar embebido ({@code mvn spring-boot:run}) o desplegar el WAR en un Tomcat 11.
 */
@SpringBootApplication
public class Siicog27Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Siicog27Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Siicog27Application.class);
	}
}
