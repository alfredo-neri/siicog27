package com.gem.sistema.config;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.error.ErrorPage;
import org.springframework.boot.web.error.ErrorPageRegistrar;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.server.servlet.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import com.gem.sistema.Siicog27Application;
import com.gem.sistema.web.apariencia.AparienciaService;
import com.gem.sistema.web.apariencia.AparienciaServlet;

import jakarta.faces.application.ViewExpiredException;

/**
 * Lo que difem26 declaraba en web.xml y applicationContext.xml y que Spring Boot no lee por si solo.
 *
 * El web.xml se conserva reducido: Tomcat externo lo usa, y OmniFaces lee de ahi las paginas de
 * error para FullAjaxExceptionHandler. En Tomcat embebido se registran aqui; en WildFly y Tomcat externo
 * se toman del web.xml.
 */
@Configuration
@PropertySource(value = "classpath:conf-properties.xml", encoding = "UTF-8")
@ImportResource("classpath:validators/file-validators.xml")
public class WebConfig {

	@Bean
	public ErrorPageRegistrar errorPages() {
		return registry -> registry.addErrorPages(
				new ErrorPage(ViewExpiredException.class, "/expiredSession.xhtml"),
				new ErrorPage(NullPointerException.class, "/errorPage.xhtml"),
				new ErrorPage(Exception.class, "/errorPage.xhtml"),
				new ErrorPage(Throwable.class, "/errorPage.xhtml"));
	}

	/** GET /apariencia: tema vigente para los navegadores abiertos (siicog.js). */
	@Bean
	public ServletRegistrationBean<AparienciaServlet> aparienciaServlet(AparienciaService apariencia) {
		return new ServletRegistrationBean<>(new AparienciaServlet(apariencia), "/apariencia");
	}

	/**
	 * {@code <welcome-file>login.xhtml</welcome-file>} en Tomcat embebido. Solo se carga si Tomcat esta
	 * presente: en WildFly no existe {@code org.apache.catalina} y ahi lo toma del web.xml.
	 */
	@Configuration(proxyBeanMethods = false)
	@ConditionalOnClass(name = "org.apache.catalina.Context")
	static class TomcatEmbebido {

		@Bean
		public TomcatContextCustomizer welcomeFile() {
			return context -> context.addWelcomeFile("login.xhtml");
		}

		/**
		 * Con {@code java -jar} la raiz web es una carpeta con el contenido del WAR expandido, para que
		 * {@code getRealPath} funcione (reportes y temporales). Ver {@link ContenidoWebExpandido}.
		 */
		@Bean
		public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> contenidoWebExpandido(
				@Value("${siicog27.contenido-web}") String carpeta) {
			return factory -> {
				File fuente = new ApplicationHome(Siicog27Application.class).getSource();
				if (fuente == null || !fuente.isFile() || !fuente.getName().endsWith(".war")) {
					return;
				}
				try {
					factory.setDocumentRoot(ContenidoWebExpandido.expandir(fuente, Path.of(carpeta)));
				} catch (IOException e) {
					throw new UncheckedIOException("No se pudo expandir el contenido web en " + carpeta, e);
				}
			};
		}
	}
}
