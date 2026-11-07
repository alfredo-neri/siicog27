package com.gem.sistema.business.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TC_PARAMETROS database table.
 * 
 */
@Entity
@Table(name = "TC_PARAMETROS")
@NamedQuery(name = "TcParametro.findAll", query = "SELECT t FROM TcParametro t")
public class TcParametro implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cve parametro. */
	@Id
	@Column(name = "CVE_PARAMETRO")
	private String cveParametro;

	/** The descripcion. */
	private String descripcion;

	/** The valor. */
	private String valor;

	@Column(name = "DATA_TYPE")
	private String dataType;

	/**
	 * Instantiates a new tc parametro.
	 */
	public TcParametro() {
	}

	/**
	 * Gets the cve parametro.
	 *
	 * @return the cve parametro
	 */
	public String getCveParametro() {
		return this.cveParametro;
	}

	/**
	 * Sets the cve parametro.
	 *
	 * @param cveParametro the new cve parametro
	 */
	public void setCveParametro(String cveParametro) {
		this.cveParametro = cveParametro;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return this.descripcion;
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
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public String getValor() {
		return this.valor;
	}

	/**
	 * Sets the valor.
	 *
	 * @param valor the new valor
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

}