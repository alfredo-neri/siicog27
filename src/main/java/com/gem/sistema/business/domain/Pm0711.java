package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM0711 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm0711.findAll", query="SELECT p FROM Pm0711 p")
public class Pm0711 extends com.gem.sistema.business.domain.AbstractEntity implements Serializable {
	
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
	private int idsector;

	/** The nespa. */
	private Integer nespa;

	/** The obsnespa. */
	private String obsnespa;

	/** The obstesp. */
	private String obstesp;

	/** The tesp. */
	private Integer tesp;

	/** The userid. */
	@Column(name="USERID")
	private String userid;
	

	/**
	 * Instantiates a new pm 0711.
	 */
	public Pm0711() {
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
	 * Gets the nespa.
	 *
	 * @return the nespa
	 */
	public Integer getNespa() {
		return this.nespa;
	}

	/**
	 * Sets the nespa.
	 *
	 * @param nespa the new nespa
	 */
	public void setNespa(Integer nespa) {
		this.nespa = nespa;
	}

	/**
	 * Gets the obsnespa.
	 *
	 * @return the obsnespa
	 */
	public String getObsnespa() {
		return this.obsnespa;
	}

	/**
	 * Sets the obsnespa.
	 *
	 * @param obsnespa the new obsnespa
	 */
	public void setObsnespa(String obsnespa) {
		this.obsnespa = obsnespa;
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

}