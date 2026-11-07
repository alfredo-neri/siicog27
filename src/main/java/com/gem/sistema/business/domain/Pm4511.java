package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM4511 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm4511.findAll", query="SELECT p FROM Pm4511 p")
public class Pm4511 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The anual. */
	private Integer anual;

	/** The capturo. */
	private String capturo;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private Integer idsector;

	/** The nhab. */
	private Integer nhab;

	/** The obsnhab. */
	private String obsnhab;

	/** The obstotpre. */
	private String obstotpre;

	/** The totpre. */
	private BigDecimal totpre;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/**
	 * Instantiates a new pm 4511.
	 */
	public Pm4511() {
	}

	/**
	 * Gets the anual.
	 *
	 * @return the anual
	 */
	public Integer getAnual() {
		return this.anual;
	}

	/**
	 * Sets the anual.
	 *
	 * @param anual the new anual
	 */
	public void setAnual(Integer anual) {
		this.anual = anual;
	}

	/**
	 * Gets the capturo.
	 *
	 * @return the capturo
	 */
	public String getCapturo() {
		return this.capturo;
	}

	/**
	 * Sets the capturo.
	 *
	 * @param capturo the new capturo
	 */
	public void setCapturo(String capturo) {
		this.capturo = capturo;
	}

	/**
	 * Gets the feccap.
	 *
	 * @return the feccap
	 */
	public Date getFeccap() {
		return this.feccap;
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
	 * Gets the id ref.
	 *
	 * @return the id ref
	 */
	public Long getIdRef() {
		return this.idRef;
	}

	/**
	 * Sets the id ref.
	 *
	 * @param idRef the new id ref
	 */
	public void setIdRef(Long idRef) {
		this.idRef = idRef;
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
	 * Gets the nhab.
	 *
	 * @return the nhab
	 */
	public Integer getNhab() {
		return this.nhab;
	}

	/**
	 * Sets the nhab.
	 *
	 * @param nhab the new nhab
	 */
	public void setNhab(Integer nhab) {
		this.nhab = nhab;
	}

	/**
	 * Gets the obsnhab.
	 *
	 * @return the obsnhab
	 */
	public String getObsnhab() {
		return this.obsnhab;
	}

	/**
	 * Sets the obsnhab.
	 *
	 * @param obsnhab the new obsnhab
	 */
	public void setObsnhab(String obsnhab) {
		this.obsnhab = obsnhab;
	}

	/**
	 * Gets the obstotpre.
	 *
	 * @return the obstotpre
	 */
	public String getObstotpre() {
		return this.obstotpre;
	}

	/**
	 * Sets the obstotpre.
	 *
	 * @param obstotpre the new obstotpre
	 */
	public void setObstotpre(String obstotpre) {
		this.obstotpre = obstotpre;
	}

	/**
	 * Gets the totpre.
	 *
	 * @return the totpre
	 */
	public BigDecimal getTotpre() {
		return this.totpre;
	}

	/**
	 * Sets the totpre.
	 *
	 * @param totpre the new totpre
	 */
	public void setTotpre(BigDecimal totpre) {
		this.totpre = totpre;
	}

	/**
	 * Gets the userid.
	 *
	 * @return the userid
	 */
	public String getUserid() {
		return this.userid;
	}

	/**
	 * Sets the userid.
	 *
	 * @param userid the new userid
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

}