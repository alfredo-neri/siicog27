package com.gem.sistema.web.apariencia;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.TcParametro;
import com.gem.sistema.business.repository.catalogs.ParametrosRepository;

/**
 * Apariencia de toda la aplicacion (tema de colores), que solo cambia un administrador desde
 * views/administracion/apariencia.xhtml.
 *
 * Se guarda en TC_PARAMETROS (CVE_PARAMETRO = SIICOG_APARIENCIA) para que valga para todos los
 * usuarios y sobreviva a los despliegues. El layout y el login la leen en cada pagina
 * ({@code #{apariencia.tema}}, {@code #{apariencia.css}}) y los navegadores abiertos la consultan con
 * {@link AparienciaServlet} para cambiar de tema sin recargar.
 *
 * Se guarda en memoria y se relee de la base cada {@link #VIGENCIA_MS} ms, por si otra instancia
 * (WildFly y java -jar sobre la misma base) la cambio.
 */
@Component("apariencia")
public class AparienciaService {

	private static final Logger LOG = LoggerFactory.getLogger(AparienciaService.class);

	static final String PARAMETRO = "SIICOG_APARIENCIA";
	private static final String DESCRIPCION = "APARIENCIA DEL SISTEMA (PANEL DE ADMINISTRACION)";
	private static final String ROL_ADMIN = "ROLE_ADMIN";
	private static final long VIGENCIA_MS = 30_000;
	private static final DateTimeFormatter FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
	private static final DateTimeFormatter FECHA_VISTA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	/** Tema vigente. {@code usuario} y {@code fecha} son nulos mientras no se haya guardado ninguno. */
	public record Apariencia(Tema tema, String principal, String acento, String usuario, LocalDateTime fecha) {

		static final Apariencia PREDETERMINADA = new Apariencia(Tema.INSTITUCIONAL, "#001633", "#00A6C8", null, null);

		/** Bloque CSS del tema personalizado; vacio en los temas fijos (estan en siicog.css). */
		public String css() {
			return tema == Tema.PERSONALIZADO ? Paleta.css(principal, acento) : "";
		}

		/** Cambia cada vez que se guarda: los navegadores la comparan para saber si deben actualizarse. */
		public String version() {
			return Integer.toString((tema + principal + acento + fecha).hashCode() & 0x7fffffff, 36);
		}

		/** @return "itbmadminc · 01/11/2026 10:22", o null si nunca se ha cambiado */
		public String ultimoCambio() {
			return usuario == null ? null : usuario + (fecha == null ? "" : " · " + fecha.format(FECHA_VISTA));
		}
	}

	private record Cache(Apariencia apariencia, long leida) {
	}

	private final ParametrosRepository parametrosRepository;
	private volatile Cache cache;

	public AparienciaService(@Qualifier("parametrosRepository") ParametrosRepository parametrosRepository) {
		this.parametrosRepository = parametrosRepository;
	}

	/** @return el tema vigente (de memoria, o de la base si ya vencio) */
	public Apariencia getActual() {
		Cache c = cache;
		if (c == null || System.currentTimeMillis() - c.leida() > VIGENCIA_MS) {
			c = new Cache(leer(c == null ? Apariencia.PREDETERMINADA : c.apariencia()), System.currentTimeMillis());
			cache = c;
		}
		return c.apariencia();
	}

	/** Valor de data-sg-tema en &lt;html&gt;. */
	public String getTema() {
		return getActual().tema().getClave();
	}

	/** Contenido del &lt;style id="sg-tema-css"&gt; (solo el tema personalizado lo usa). */
	public String getCss() {
		return getActual().css();
	}

	public String getVersion() {
		return getActual().version();
	}

	/** @return true si el usuario de la peticion es administrador (ROLE_ADMIN) */
	public boolean isAdministrador() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth != null && auth.isAuthenticated()
				&& auth.getAuthorities().stream().anyMatch(a -> ROL_ADMIN.equals(a.getAuthority()));
	}

	/**
	 * Guarda el tema para todos los usuarios.
	 *
	 * @param principal color principal (solo tema personalizado)
	 * @param acento    color de acento (solo tema personalizado)
	 * @param usuario   quien lo cambia
	 * @return el tema guardado
	 * @throws SecurityException        si quien llama no es administrador
	 * @throws IllegalArgumentException si los colores del tema personalizado no sirven
	 */
	public Apariencia guardar(Tema tema, String principal, String acento, String usuario) {
		if (!isAdministrador()) {
			throw new SecurityException("Solo un administrador puede cambiar la apariencia.");
		}
		String p = Apariencia.PREDETERMINADA.principal();
		String a = Apariencia.PREDETERMINADA.acento();
		if (tema == Tema.PERSONALIZADO) {
			String error = Paleta.validar(principal, acento);
			if (error != null) {
				throw new IllegalArgumentException(error);
			}
			p = Paleta.normalizar(principal);
			a = Paleta.normalizar(acento);
		}
		Apariencia nueva = new Apariencia(tema, p, a, usuario, LocalDateTime.now().withSecond(0).withNano(0));
		TcParametro parametro = parametrosRepository.findById(PARAMETRO).orElseGet(TcParametro::new);
		parametro.setCveParametro(PARAMETRO);
		parametro.setDescripcion(DESCRIPCION);
		parametro.setValor(escribir(nueva));
		parametrosRepository.save(parametro);
		cache = new Cache(nueva, System.currentTimeMillis());
		LOG.info("Apariencia cambiada a {} por {}", parametro.getValor(), usuario);
		return nueva;
	}

	/** Datos que devuelve {@link AparienciaServlet}. */
	Map<String, String> paraNavegador() {
		Apariencia a = getActual();
		Map<String, String> m = new LinkedHashMap<>();
		m.put("version", a.version());
		m.put("tema", a.tema().getClave());
		m.put("css", a.css());
		return m;
	}

	/** Si la base no responde se conserva el tema que ya se tenia; se reintenta al vencer. */
	private Apariencia leer(Apariencia anterior) {
		try {
			return parametrosRepository.findById(PARAMETRO).map(t -> interpretar(t.getValor()))
					.orElse(Apariencia.PREDETERMINADA);
		} catch (RuntimeException e) {
			LOG.warn("No se pudo leer {} de TC_PARAMETROS; se conserva el tema {}", PARAMETRO, anterior.tema(), e);
			return anterior;
		}
	}

	/** VALOR: tema=guinda;principal=#611232;acento=#C19A57;usuario=itbmadminc;fecha=2026-11-01T10:22 */
	static String escribir(Apariencia a) {
		return "tema=" + a.tema().getClave() + ";principal=" + a.principal() + ";acento=" + a.acento() + ";usuario="
				+ a.usuario() + ";fecha=" + a.fecha().format(FECHA);
	}

	static Apariencia interpretar(String valor) {
		Map<String, String> m = new LinkedHashMap<>();
		for (String par : (valor == null ? "" : valor).split(";")) {
			int i = par.indexOf('=');
			if (i > 0) {
				m.put(par.substring(0, i).trim(), par.substring(i + 1).trim());
			}
		}
		Tema tema = Tema.deClave(m.get("tema"));
		String p = Paleta.normalizar(m.get("principal"));
		String a = Paleta.normalizar(m.get("acento"));
		if (tema == Tema.PERSONALIZADO && Paleta.validar(p, a) != null) {
			LOG.warn("Colores del tema personalizado no validos en {} ({}); se usa Institucional", PARAMETRO, valor);
			tema = Tema.INSTITUCIONAL;
		}
		if (p == null || a == null) {
			p = Apariencia.PREDETERMINADA.principal();
			a = Apariencia.PREDETERMINADA.acento();
		}
		LocalDateTime fecha = null;
		try {
			fecha = m.get("fecha") == null ? null : LocalDateTime.parse(m.get("fecha"), FECHA);
		} catch (DateTimeParseException e) {
			// fecha ilegible: solo se deja de mostrar
		}
		String usuario = m.get("usuario");
		return new Apariencia(tema, p, a, usuario == null || usuario.isEmpty() ? null : usuario, fecha);
	}
}
