package com.gem.sistema.business.domain;

import static com.gem.sistema.business.common.PreCondition.isTrue;
import static com.gem.sistema.business.common.PreCondition.notNull;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gem.sistema.annotation.ColumnFile;
import com.gem.sistema.annotation.IgnoredQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class Cpd.
 */
@Entity
@Table(name = "cpd")
public class Cpd extends AbstractEntity {

	/** The Constant MAX_LENGTH_pe. */
	@IgnoredQuery
	static final int MAX_LENGTH_pe = 4;

	/** The Constant MAX_LENGTH_nope. */
	@IgnoredQuery
	static final int MAX_LENGTH_nope = 4;

	/** The Constant MAX_LENGTH_tema. */
	@IgnoredQuery
	static final int MAX_LENGTH_tema = 45;

	/** The Constant MAX_LENGTH_descripcion. */
	@IgnoredQuery
	static final int MAX_LENGTH_descripcion = 255;

	/** The Constant MAX_LENGTH_usuario. */
	@IgnoredQuery
	static final int MAX_LENGTH_usuario = 45;

	/** The Constant MAX_LENGTH_cvetemas. */
	@IgnoredQuery
	static final int MAX_LENGTH_cvetemas = 255;

	@IgnoredQuery
	static final int MAX_LENGTH_SUB_TEMA = 2;

	/** The pe. */
	@Column(name = "pe", nullable = false, length = MAX_LENGTH_pe)
	private String pe;

	/** The nope. */
	@Column(name = "nope", nullable = false, length = MAX_LENGTH_nope)
	private String nope;

	/** The tema. */
	@Column(name = "tema", nullable = false, length = MAX_LENGTH_tema)
	private String tema;

	/** The descripcion. */
	@Column(name = "descripcion", nullable = false, length = MAX_LENGTH_descripcion)
	private String descripcion;

	/** The usuario. */
	@Column(name = "usuario", length = MAX_LENGTH_usuario)
	private String usuario;

	/** The feccap. */
	@Column(name = "feccap")
	private Date feccap;

	/** The cvetemas. */
	@Column(name = "cvetemas", nullable = false, length = MAX_LENGTH_cvetemas)
	private String cvetemas;

	@Column(name = "SUB_TEMA", nullable = false, length = MAX_LENGTH_SUB_TEMA)
	private String subTema;

	/**
	 * Require valid pe.
	 *
	 * @param pe the pe
	 */
	public void requireValidPe(String pe) {
		notNull(pe, "pe no puede ser nulo.");
		isTrue(pe.length() <= MAX_LENGTH_pe, "La máxima longitud de pe es de <%d> caracteres.", MAX_LENGTH_pe);
	}

	/**
	 * Require valid nope.
	 *
	 * @param nope the nope
	 */
	public void requireValidNope(String nope) {
		notNull(nope, "nope no puede ser nulo.");
		isTrue(nope.length() <= MAX_LENGTH_nope, "La máxima longitud de nope es de <%d> caracteres.", MAX_LENGTH_nope);
	}

	/**
	 * Require valid tema.
	 *
	 * @param tema the tema
	 */
	public void requireValidTema(String tema) {
		notNull(tema, "tema no puede ser nulo.");
		isTrue(tema.length() <= MAX_LENGTH_tema, "La máxima longitud de tema es de <%d> caracteres.", MAX_LENGTH_tema);
	}

	/**
	 * Require valid descripcion.
	 *
	 * @param descripcion the descripcion
	 */
	public void requireValidDescripcion(String descripcion) {
		isTrue(descripcion.length() <= MAX_LENGTH_descripcion,
				"La máxima longitud de descripcion es de <%d> caracteres.", MAX_LENGTH_descripcion);
	}

	/**
	 * Require validcvetemas.
	 *
	 * @param cvetemas the cvetemas
	 */
	public void requireValidcvetemas(String cvetemas) {
		isTrue(cvetemas.length() <= MAX_LENGTH_cvetemas, "La máxima longitud de cvetemas es de <%d> caracteres.",
				MAX_LENGTH_cvetemas);
	}

	/**
	 * Instantiates a new cpd.
	 *
	 * @param pe          the pe
	 * @param nope        the nope
	 * @param tema        the tema
	 * @param descripcion the descripcion
	 * @param usuario     the usuario
	 * @param feccap      the feccap
	 * @param cvetemas    the cvetemas
	 */
	public Cpd(String pe, String nope, String tema, String descripcion, String usuario, Date feccap, String cvetemas) {
		this.pe = pe;
		this.nope = nope;
		this.tema = tema;
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.feccap = feccap;
		this.cvetemas = cvetemas;
	}

	/**
	 * Instantiates a new cpd.
	 */
	public Cpd() {
	}

	/**
	 * Gets the pe.
	 *
	 * @return the pe
	 */
	@ColumnFile(indexColumn = 3)
	public String getPe() {
		return pe;
	}

	/**
	 * Sets the pe.
	 *
	 * @param pe the new pe
	 */
	public void setPe(String pe) {
		this.pe = pe;
	}

	/**
	 * Gets the nope.
	 *
	 * @return the nope
	 */
	@ColumnFile(indexColumn = 4)
	public String getNope() {
		return nope;
	}

	/**
	 * Sets the nope.
	 *
	 * @param nope the new nope
	 */
	public void setNope(String nope) {
		this.nope = nope;
	}

	/**
	 * Gets the tema.
	 *
	 * @return the tema
	 */
	@ColumnFile(indexColumn = 5)
	public String getTema() {
		return tema;
	}

	/**
	 * Sets the tema.
	 *
	 * @param tema the new tema
	 */
	public void setTema(String tema) {
		this.tema = tema;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	@ColumnFile(indexColumn = 2)
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the feccap.
	 *
	 * @return the feccap
	 */
	public Date getFeccap() {
		return feccap;
	}

	/**
	 * Sets the feccap.
	 *
	 * @param feccap the new feccap
	 */
	public void setFeccap(Date feccap) {
		this.feccap = feccap;
	}

	/**
	 * Gets the cvetemas.
	 *
	 * @return the cvetemas
	 */
	@ColumnFile(indexColumn = 1)
	public String getCvetemas() {
		return cvetemas;
	}

	public String getSubTema() {
		return subTema;
	}

	public void setSubTema(String subTema) {
		this.subTema = subTema;
	}

	/**
	 * Sets the cvetemas.
	 *
	 * @param cvetemas the new cvetemas
	 */
	public void setCvetemas(String cvetemas) {
		this.cvetemas = cvetemas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("pe", this.pe).append("nope", this.nope).append("tema", this.tema)
				.append("descripcion", this.descripcion).append("usuario", this.usuario)
				.append("cvetemas", this.cvetemas).toString();
	}

}
