package com.gem.sistema.web.bean;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Datos generales de SIICOG que muestran el login y el marco: version y tiempo de sesion.
 * Es un bean de aplicacion (singleton) para no crear beans de sesion en paginas publicas.
 */
@Component("siicog")
public class SiicogInfo {

	/** Version del pom.xml (siicog27.version en application.yml). */
	@Value("${siicog27.version:}")
	private String version;

	/** server.servlet.session.timeout. */
	@Value("${server.servlet.session.timeout:30m}")
	private Duration timeoutSesion;

	/** Marca del arranque: cambia en cada despliegue aunque la version del pom sea la misma. */
	private final String arranque = Long.toString(System.currentTimeMillis(), 36);

	public String getVersion() {
		return version;
	}

	/**
	 * Clave para las URL de siicog.css, siicog.js y gem.js ({@code ?v=}). Con solo la version, el
	 * navegador seguia usando los archivos de una compilacion anterior.
	 *
	 * @return version y marca del arranque
	 */
	public String getRecursos() {
		return version + "-" + arranque;
	}

	/**
	 * Minutos de inactividad tras los que se cierra la sesion.
	 *
	 * @return minutos
	 */
	public long getMinutosSesion() {
		return timeoutSesion.toMinutes();
	}
}
