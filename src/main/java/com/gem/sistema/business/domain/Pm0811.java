package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM0811 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm0811.findAll", query = "SELECT p FROM Pm0811 p")
public class Pm0811 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The capturo. */
	private String capturo;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The id ref. */
	@Column(name = "ID_REF")
	private Long idRef;

	/** The idsector. */
	private int idsector;

	/** The nspc. */
	private Integer nspc = 0;

	/** The obsnspc. */
	private String obsnspc ="";

	/** The obstesp. */
	private String obstesp = "";

	/** The semestral. */
	private Integer semestral;

	/** The tesp. */
	private Integer tesp = 0;

	/** The userid. */
	@Column(name = "USERID")
	private String userid;

	/** The b guarda. */
	@Transient
	private boolean bGuarda;

	/**
	 * Instantiates a new pm 0811.
	 */
	public Pm0811() {
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
	public int getIdsector() {
		return this.idsector;
	}

	/**
	 * Sets the idsector.
	 *
	 * @param idsector the new idsector
	 */
	public void setIdsector(int idsector) {
		this.idsector = idsector;
	}

	/**
	 * Gets the nspc.
	 *
	 * @return the nspc
	 */
	public Integer getNspc() {
		return this.nspc;
	}

	/**
	 * Sets the nspc.
	 *
	 * @param nspc the new nspc
	 */
	public void setNspc(Integer nspc) {
		this.nspc = nspc;
	}

	/**
	 * Gets the obsnspc.
	 *
	 * @return the obsnspc
	 */
	public String getObsnspc() {
		return this.obsnspc;
	}

	/**
	 * Sets the obsnspc.
	 *
	 * @param obsnspc the new obsnspc
	 */
	public void setObsnspc(String obsnspc) {
		this.obsnspc = obsnspc;
	}

	/**
	 * Gets the obstesp.
	 *
	 * @return the obstesp
	 */
	public String getObstesp() {
		return this.obstesp;
	}

	/**
	 * Sets the obstesp.
	 *
	 * @param obstesp the new obstesp
	 */
	public void setObstesp(String obstesp) {
		this.obstesp = obstesp;
	}

	/**
	 * Gets the semestral.
	 *
	 * @return the semestral
	 */
	public Integer getSemestral() {
		return this.semestral;
	}

	/**
	 * Sets the semestral.
	 *
	 * @param semestral the new semestral
	 */
	public void setSemestral(Integer semestral) {
		this.semestral = semestral;
	}

	/**
	 * Gets the tesp.
	 *
	 * @return the tesp
	 */
	public Integer getTesp() {
		return this.tesp;
	}

	/**
	 * Sets the tesp.
	 *
	 * @param tesp the new tesp
	 */
	public void setTesp(Integer tesp) {
		this.tesp = tesp;
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

	/**
	 * Checks if is b guarda.
	 *
	 * @return true, if is b guarda
	 */
	public boolean isbGuarda() {
		return bGuarda;
	}

	/**
	 * Sets the b guarda.
	 *
	 * @param bGuarda the new b guarda
	 */
	public void setbGuarda(boolean bGuarda) {
		this.bGuarda = bGuarda;
	}

}