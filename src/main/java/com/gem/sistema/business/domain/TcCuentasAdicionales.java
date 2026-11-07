package com.gem.sistema.business.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TC_CUENTAS_ADICIONALES database table.
 * 
 */
@Entity
@Table(name = "TC_CUENTAS_ADICIONALES")
public class TcCuentasAdicionales extends AbstractEntity {

	/** The adicional. */
	private String adicional;

	/** The cta adicional. */
	@Column(name = "CTA_ADICIONAL")
	private String ctaAdicional;

	/** The cuenta. */
	private String cuenta;

	/** The idsector. */
	private Integer idsector;

	/** The invertir. */
	private Short invertir;

	/**
	 * Instantiates a new tc cuentas adicionales.
	 */
	public TcCuentasAdicionales() {
	}

	/**
	 * Gets the adicional.
	 *
	 * @return the adicional
	 */
	public String getAdicional() {
		return this.adicional;
	}

	/**
	 * Sets the adicional.
	 *
	 * @param adicional the new adicional
	 */
	public void setAdicional(String adicional) {
		this.adicional = adicional;
	}

	/**
	 * Gets the cta adicional.
	 *
	 * @return the cta adicional
	 */
	public String getCtaAdicional() {
		return this.ctaAdicional;
	}

	/**
	 * Sets the cta adicional.
	 *
	 * @param ctaAdicional the new cta adicional
	 */
	public void setCtaAdicional(String ctaAdicional) {
		this.ctaAdicional = ctaAdicional;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return this.cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta the new cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the idsector.
	 *
	 * @return the idsector
	 */
	public Integer getIdsector() {
		return this.idsector;
	}

	/**
	 * Sets the idsector.
	 *
	 * @param idsector the new idsector
	 */
	public void setIdsector(Integer idsector) {
		this.idsector = idsector;
	}

	/**
	 * Gets the invertir.
	 *
	 * @return the invertir
	 */
	public Short getInvertir() {
		return this.invertir;
	}

	/**
	 * Sets the invertir.
	 *
	 * @param invertir the new invertir
	 */
	public void setInvertir(Short invertir) {
		this.invertir = invertir;
	}

}