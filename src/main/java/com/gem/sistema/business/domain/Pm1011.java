package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM1011 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm1011.findAll", query="SELECT p FROM Pm1011 p")
public class Pm1011 extends AbstractEntity implements Serializable {
	
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

	/** The ipm. */
	@Column(updatable=false)
	private Integer ipm;

	/** The obsipm. */
	private String obsipm;

	/** The obstdr. */
	private String obstdr;

	/** The tdr. */
	private Integer tdr;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/**
	 * Instantiates a new pm 1011.
	 */
	public Pm1011() {
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
	 * Gets the ipm.
	 *
	 * @return the ipm
	 */
	public Integer getIpm() {
		return this.ipm;
	}

	/**
	 * Sets the ipm.
	 *
	 * @param ipm the new ipm
	 */
	public void setIpm(Integer ipm) {
		this.ipm = ipm;
	}

	/**
	 * Gets the obsipm.
	 *
	 * @return the obsipm
	 */
	public String getObsipm() {
		return this.obsipm;
	}

	/**
	 * Sets the obsipm.
	 *
	 * @param obsipm the new obsipm
	 */
	public void setObsipm(String obsipm) {
		this.obsipm = obsipm;
	}

	/**
	 * Gets the obstdr.
	 *
	 * @return the obstdr
	 */
	public String getObstdr() {
		return this.obstdr;
	}

	/**
	 * Sets the obstdr.
	 *
	 * @param obstdr the new obstdr
	 */
	public void setObstdr(String obstdr) {
		this.obstdr = obstdr;
	}

	/**
	 * Gets the tdr.
	 *
	 * @return the tdr
	 */
	public Integer getTdr() {
		return this.tdr;
	}

	/**
	 * Sets the tdr.
	 *
	 * @param tdr the new tdr
	 */
	public void setTdr(Integer tdr) {
		this.tdr = tdr;
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