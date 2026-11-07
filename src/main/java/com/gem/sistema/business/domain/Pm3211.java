package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM3211 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm3211.findAll", query="SELECT p FROM Pm3211 p")
public class Pm3211 extends AbstractEntity implements Serializable {
	
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

	/** The nobs. */
	private Integer nobs;

	/** The obsnobs. */
	private String obsnobs;

	/** The obstotobr. */
	private String obstotobr;

	/** The totobr. */
	private Integer totobr;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/**
	 * Instantiates a new pm 3211.
	 */
	public Pm3211() {
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
	 * Gets the nobs.
	 *
	 * @return the nobs
	 */
	public Integer getNobs() {
		return this.nobs;
	}

	/**
	 * Sets the nobs.
	 *
	 * @param nobs the new nobs
	 */
	public void setNobs(Integer nobs) {
		this.nobs = nobs;
	}

	/**
	 * Gets the obsnobs.
	 *
	 * @return the obsnobs
	 */
	public String getObsnobs() {
		return this.obsnobs;
	}

	/**
	 * Sets the obsnobs.
	 *
	 * @param obsnobs the new obsnobs
	 */
	public void setObsnobs(String obsnobs) {
		this.obsnobs = obsnobs;
	}

	/**
	 * Gets the obstotobr.
	 *
	 * @return the obstotobr
	 */
	public String getObstotobr() {
		return this.obstotobr;
	}

	/**
	 * Sets the obstotobr.
	 *
	 * @param obstotobr the new obstotobr
	 */
	public void setObstotobr(String obstotobr) {
		this.obstotobr = obstotobr;
	}

	/**
	 * Gets the totobr.
	 *
	 * @return the totobr
	 */
	public Integer getTotobr() {
		return this.totobr;
	}

	/**
	 * Sets the totobr.
	 *
	 * @param totobr the new totobr
	 */
	public void setTotobr(Integer totobr) {
		this.totobr = totobr;
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