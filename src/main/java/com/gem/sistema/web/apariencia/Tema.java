package com.gem.sistema.web.apariencia;

/**
 * Temas de SIICOG que el administrador elige en el panel Apariencia.
 *
 * Los colores de los temas fijos estan en resources/siicog/siicog.css (bloques [data-sg-tema="..."]);
 * aqui solo se describen. El tema personalizado se calcula a partir de dos colores (ver {@link Paleta}).
 */
public enum Tema {

	INSTITUCIONAL("Institucional", "Azul marino con acento cian: la paleta original de SIICOG."),
	GUINDA("Guinda", "Guinda con acento dorado, en la línea de la imagen gubernamental."),
	BOSQUE("Bosque", "Verde profundo con acento esmeralda."),
	GRAFITO("Grafito", "Gris carbón neutro con acento azul."),
	PERSONALIZADO("Personalizado", "Color principal y de acento a elección del administrador.");

	private final String nombre;
	private final String descripcion;

	Tema(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	/** Valor de data-sg-tema y del parametro guardado. */
	public String getClave() {
		return name().toLowerCase();
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param clave valor guardado (puede ser nulo o desconocido)
	 * @return el tema, o Institucional si la clave no corresponde a ninguno
	 */
	public static Tema deClave(String clave) {
		for (Tema t : values()) {
			if (t.getClave().equalsIgnoreCase(clave == null ? "" : clave.trim())) {
				return t;
			}
		}
		return INSTITUCIONAL;
	}
}
