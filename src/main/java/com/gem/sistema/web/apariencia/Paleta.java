package com.gem.sistema.web.apariencia;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Paleta del tema personalizado: deriva de un color principal y uno de acento las mismas variables
 * que definen los temas fijos en siicog.css (--marino-50 ... --marino-800, --acero, --niebla, --cian-*).
 *
 * Valida el contraste para que la barra superior, los botones y los enlaces sigan siendo legibles
 * (WCAG: 4.5:1 para texto).
 */
public final class Paleta {

	/** Solo #RRGGBB: el valor termina dentro de un bloque CSS, no se acepta otra cosa. */
	private static final Pattern HEX = Pattern.compile("#[0-9a-fA-F]{6}");

	private static final int[] BLANCO = { 255, 255, 255 };
	private static final int[] NEGRO = { 0, 0, 0 };
	private static final int[] PIZARRA = { 112, 128, 144 };

	private Paleta() {
	}

	/**
	 * @param valor texto capturado (con o sin #)
	 * @return #RRGGBB en mayusculas, o null si no es un color valido
	 */
	public static String normalizar(String valor) {
		if (valor == null) {
			return null;
		}
		String v = valor.trim();
		if (!v.startsWith("#")) {
			v = "#" + v;
		}
		return HEX.matcher(v).matches() ? v.toUpperCase(Locale.ROOT) : null;
	}

	/**
	 * Revisa que los dos colores sirvan como tema.
	 *
	 * @return el motivo por el que no sirven, o null si son validos
	 */
	public static String validar(String principal, String acento) {
		String p = normalizar(principal);
		String a = normalizar(acento);
		if (p == null || a == null) {
			return "Capture los colores en formato #RRGGBB.";
		}
		if (contraste(rgb(p), BLANCO) < 4.5) {
			return "El color principal es muy claro: el texto blanco de la barra superior y de los botones no se leería. "
					+ "Elija un tono más oscuro.";
		}
		if (contraste(rgb(p), rgb(a)) < 3) {
			return "El color de acento se parece demasiado al principal: los indicadores del menú y el foco no se distinguirían. "
					+ "Elija un acento más claro o más intenso.";
		}
		return null;
	}

	/**
	 * Variables CSS del tema personalizado. Supone colores ya validados con {@link #validar}.
	 *
	 * @return nombre de la variable (sin --) y valor #RRGGBB, en el orden de siicog.css
	 */
	public static Map<String, String> variables(String principal, String acento) {
		int[] p = rgb(normalizar(principal));
		int[] a = rgb(normalizar(acento));
		Map<String, String> v = new LinkedHashMap<>();
		v.put("marino", hex(p));
		v.put("marino-800", hex(mezclar(p, NEGRO, .18)));
		v.put("marino-700", hex(mezclar(p, BLANCO, .07)));
		v.put("marino-600", hex(mezclar(p, BLANCO, .15)));
		v.put("marino-400", hex(mezclar(p, BLANCO, .35)));
		v.put("marino-100", hex(mezclar(p, BLANCO, .78)));
		v.put("marino-50", hex(mezclar(p, BLANCO, .92)));
		// acero: el principal apagado hacia gris; se oscurece hasta que el texto blanco del avatar se lea
		int[] acero = oscurecerHasta(mezclar(p, PIZARRA, .6), BLANCO, 4.5);
		v.put("acero", hex(acero));
		v.put("acero-600", hex(mezclar(acero, NEGRO, .12)));
		v.put("acero-700", hex(mezclar(acero, NEGRO, .24)));
		v.put("acero-50", hex(mezclar(acero, BLANCO, .91)));
		// niebla: texto secundario sobre la barra superior
		v.put("niebla", hex(aclararHasta(mezclar(p, BLANCO, .5), p, 4.5)));
		v.put("cian", hex(a));
		// cian-700: enlaces y chips sobre blanco y sobre cian-50
		int[] cian50 = mezclar(a, BLANCO, .9);
		v.put("cian-700", hex(oscurecerHasta(mezclar(a, NEGRO, .2), cian50, 4.5)));
		v.put("cian-400", hex(mezclar(a, BLANCO, .15)));
		v.put("cian-50", hex(cian50));
		return v;
	}

	/**
	 * Bloque CSS del tema personalizado, para el &lt;style&gt; del layout y del login.
	 *
	 * @return reglas CSS (sin comillas ni &lt; &gt; &amp;: se escriben tal cual en la pagina)
	 */
	public static String css(String principal, String acento) {
		return "[data-sg-tema=personalizado]{" + declaraciones(principal, acento) + "}";
	}

	/**
	 * @return las variables como declaraciones CSS (--marino:#...;...), para un atributo style
	 */
	public static String declaraciones(String principal, String acento) {
		StringBuilder sb = new StringBuilder();
		variables(principal, acento).forEach((k, val) -> sb.append("--").append(k).append(':').append(val).append(';'));
		return sb.toString();
	}

	static int[] rgb(String hex) {
		return new int[] { Integer.parseInt(hex.substring(1, 3), 16), Integer.parseInt(hex.substring(3, 5), 16),
				Integer.parseInt(hex.substring(5, 7), 16) };
	}

	static String hex(int[] c) {
		return String.format(Locale.ROOT, "#%02X%02X%02X", c[0], c[1], c[2]);
	}

	/** Mezcla {@code c} con {@code hacia} en la proporcion {@code t} (0 = c, 1 = hacia). */
	static int[] mezclar(int[] c, int[] hacia, double t) {
		int[] r = new int[3];
		for (int i = 0; i < 3; i++) {
			r[i] = (int) Math.round(c[i] + (hacia[i] - c[i]) * t);
		}
		return r;
	}

	private static int[] oscurecerHasta(int[] c, int[] fondo, double minimo) {
		int[] r = c;
		for (int i = 0; i < 20 && contraste(r, fondo) < minimo; i++) {
			r = mezclar(r, NEGRO, .08);
		}
		return r;
	}

	private static int[] aclararHasta(int[] c, int[] fondo, double minimo) {
		int[] r = c;
		for (int i = 0; i < 20 && contraste(r, fondo) < minimo; i++) {
			r = mezclar(r, BLANCO, .15);
		}
		return r;
	}

	static double contraste(int[] a, int[] b) {
		double la = luminancia(a);
		double lb = luminancia(b);
		return (Math.max(la, lb) + .05) / (Math.min(la, lb) + .05);
	}

	private static double luminancia(int[] c) {
		double[] k = new double[3];
		for (int i = 0; i < 3; i++) {
			double x = c[i] / 255.0;
			k[i] = x <= .03928 ? x / 12.92 : Math.pow((x + .055) / 1.055, 2.4);
		}
		return .2126 * k[0] + .7152 * k[1] + .0722 * k[2];
	}
}
