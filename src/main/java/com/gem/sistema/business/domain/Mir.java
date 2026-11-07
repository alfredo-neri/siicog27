package com.gem.sistema.business.domain;

import static com.gem.sistema.business.common.PreCondition.isTrue;
import static com.gem.sistema.business.common.PreCondition.notNull;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gem.sistema.annotation.ColumnFile;
import com.gem.sistema.annotation.IgnoredQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class Mir.
 */
@Entity
@Table(name = "mir")
public class Mir extends AbstractEntity {

	/** The Constant MAX_LENGTH_codigo. */
	@IgnoredQuery
	static final int MAX_LENGTH_codigo = 26;

	/** The Constant MAX_LENGTH_programa. */
	@IgnoredQuery
	static final int MAX_LENGTH_programa = 16;

	/** The Constant MAX_LENGTH_nivel. */
	@IgnoredQuery
	static final int MAX_LENGTH_nivel = 4;

	/** The Constant MAX_LENGTH_nomind. */
	@IgnoredQuery
	static final int MAX_LENGTH_nomind = 400;

	/** The Constant MAX_LENGTH_tipo. */
	@IgnoredQuery
	static final int MAX_LENGTH_tipo = 2;

	/** The Constant MAX_LENGTH_frecuencia. */
	@IgnoredQuery
	static final int MAX_LENGTH_frecuencia = 2;

	/** The Constant MAX_LENGTH_capturo. */
	@IgnoredQuery
	static final int MAX_LENGTH_capturo = 30;

	/**/

	/** The codigo. */
	@Column(name = "codigo", nullable = false, length = MAX_LENGTH_codigo)
	private String codigo;

	/** The programa. */
	@Column(name = "programa", length = MAX_LENGTH_programa)
	private String programa;

	/** The nivel. */
	@Column(name = "nivel", length = MAX_LENGTH_nivel)
	private String nivel;

	/** The consec. */
	@Column(name = "consec")
	private Integer consec;

	/** The nomind. */
	@Column(name = "nomind", length = MAX_LENGTH_nomind)
	private String nomind;

	/** The tipo. */
	@Column(name = "tipo", length = MAX_LENGTH_tipo)
	private String tipo;

	/** The frecuencia. */
	@Column(name = "frecuencia", length = MAX_LENGTH_frecuencia)
	private String frecuencia;

	/** The capturo. */
	@Column(name = "capturo", length = MAX_LENGTH_capturo)
	private String capturo;

	/** The fecha. */
	@Column(name = "fecha")
	private Date fecha;

	private String instrumento;

	@OneToMany(mappedBy = "mir")
	private Set<Variables> variables;

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	@ColumnFile(indexColumn = 7)
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the programa.
	 *
	 * @return the programa
	 */
	@ColumnFile(indexColumn = 1)
	public String getPrograma() {
		return programa;
	}

	/**
	 * Sets the programa.
	 *
	 * @param programa the programa to set
	 */
	public void setPrograma(String programa) {
		this.programa = programa;
	}

	/**
	 * Gets the nivel.
	 *
	 * @return the nivel
	 */
	@ColumnFile(indexColumn = 2)
	public String getNivel() {
		return nivel;
	}

	/**
	 * Sets the nivel.
	 *
	 * @param nivel the nivel to set
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	/**
	 * Gets the consec.
	 *
	 * @return the consec
	 */
	@ColumnFile(indexColumn = 3)
	public Integer getConsec() {
		return consec;
	}

	/**
	 * Sets the consec.
	 *
	 * @param consec the consec to set
	 */
	public void setConsec(Integer consec) {
		this.consec = consec;
	}

	/**
	 * Gets the nomind.
	 *
	 * @return the nomind
	 */
	@ColumnFile(indexColumn = 4)
	public String getNomind() {
		return nomind;
	}

	/**
	 * Sets the nomind.
	 *
	 * @param nomind the nomind to set
	 */
	public void setNomind(String nomind) {
		this.nomind = nomind;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	@ColumnFile(indexColumn = 5)
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the frecuencia.
	 *
	 * @return the frecuencia
	 */
	@ColumnFile(indexColumn = 6)
	public String getFrecuencia() {
		return frecuencia;
	}

	/**
	 * Sets the frecuencia.
	 *
	 * @param frecuencia the frecuencia to set
	 */
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	/**
	 * Gets the capturo.
	 *
	 * @return the capturo
	 */
	public String getCapturo() {
		return capturo;
	}

	/**
	 * Sets the capturo.
	 *
	 * @param capturo the capturo to set
	 */
	public void setCapturo(String capturo) {
		this.capturo = capturo;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(String instrumento) {
		this.instrumento = instrumento;
	}

	/**
	 * Require valid codigo.
	 *
	 * @param codigo the codigo
	 */
	public void requireValidCodigo(String codigo) {
		notNull(codigo, "codigo no puede ser nulo.");
		isTrue(codigo.length() <= MAX_LENGTH_codigo, "La máxima longitud de codigo es de <%d> caracteres.",
				MAX_LENGTH_codigo);
	}

	/**
	 * Require valid nomind.
	 *
	 * @param nomind the nomind
	 */
	public void requireValidNomind(String nomind) {
		notNull(nomind, "nomind no puede ser nulo.");
		isTrue(nomind.length() <= MAX_LENGTH_nomind, "La máxima longitud de nomind es de <%d> caracteres.",
				MAX_LENGTH_nomind);
	}

	/**
	 * Require valid nivel.
	 *
	 * @param nivel the nivel
	 */
	public void requireValidNivel(String nivel) {
		isTrue(nivel.length() <= MAX_LENGTH_nivel, "La máxima longitud de nivel es de <%d> caracteres.",
				MAX_LENGTH_nivel);
	}

	/**
	 * Require valid tipo.
	 *
	 * @param tipo the tipo
	 */
	public void requireValidTipo(String tipo) {
		isTrue(tipo.length() <= MAX_LENGTH_tipo, "La máxima longitud de tipo es de <%d> caracteres.", MAX_LENGTH_tipo);
	}

	/**
	 * Require validfrecuencia.
	 *
	 * @param frecuencia the frecuencia
	 */
	public void requireValidfrecuencia(String frecuencia) {
		isTrue(frecuencia.length() <= MAX_LENGTH_frecuencia, "La máxima longitud de frecuencia es de <%d> caracteres.",
				MAX_LENGTH_frecuencia);
	}

	/**
	 * Require validcapturo.
	 *
	 * @param capturo the capturo
	 */
	public void requireValidcapturo(String capturo) {
		isTrue(capturo.length() <= MAX_LENGTH_capturo, "La máxima longitud de capturo es de <%d> caracteres.",
				MAX_LENGTH_capturo);
	}

	public Set<Variables> getVariables() {
		return variables;
	}

	public void setVariables(Set<Variables> variables) {
		this.variables = variables;
	}

	/**
	 * Instantiates a new mir.
	 *
	 * @param codigo     the codigo
	 * @param programa   the programa
	 * @param nivel      the nivel
	 * @param consec     the consec
	 * @param nomind     the nomind
	 * @param tipo       the tipo
	 * @param frecuencia the frecuencia
	 * @param capturo    the capturo
	 * @param fecha      the fecha
	 */
	public Mir(String codigo, String programa, String nivel, Integer consec, String nomind, String tipo,
			String frecuencia, String capturo, Date fecha, String instrumento) {
		this.codigo = codigo;
		this.programa = programa;
		this.nivel = nivel;
		this.consec = consec;
		this.nomind = nomind;
		this.tipo = tipo;
		this.frecuencia = frecuencia;
		this.capturo = capturo;
		this.fecha = fecha;
		this.instrumento = instrumento;
	}

	/**
	 * Instantiates a new mir.
	 */
	public Mir() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).append("index", getIndex()).append("codigo", this.codigo)
				.append("programa", this.programa).append("nivel", this.nivel).append("consec", this.consec)
				.append("nomind", this.nomind).append("tipo", this.tipo).append("frecuencia", this.frecuencia)
				.append("capturo", this.capturo).append("fecha", this.fecha).append("instrumento", this.instrumento).toString();
	}

}
