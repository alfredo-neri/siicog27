package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM4711 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm4711.findAll", query="SELECT p FROM Pm4711 p")
public class Pm4711 extends AbstractEntity implements Serializable {
	
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

	/** The ntinsfin. */
	private Integer ntinsfin;

	/** The ntinsini. */
	private Integer ntinsini;

	/** The obsntinsfin. */
	private String obsntinsfin;

	/** The obsntinsini. */
	private String obsntinsini;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/**
	 * Instantiates a new pm 4711.
	 */
	public Pm4711() {
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
	 * Gets the ntinsfin.
	 *
	 * @return the ntinsfin
	 */
	public Integer getNtinsfin() {
		return this.ntinsfin;
	}

	/**
	 * Sets the ntinsfin.
	 *
	 * @param ntinsfin the new ntinsfin
	 */
	public void setNtinsfin(Integer ntinsfin) {
		this.ntinsfin = ntinsfin;
	}

	/**
	 * Gets the ntinsini.
	 *
	 * @return the ntinsini
	 */
	public Integer getNtinsini() {
		return this.ntinsini;
	}

	/**
	 * Sets the ntinsini.
	 *
	 * @param ntinsini the new ntinsini
	 */
	public void setNtinsini(Integer ntinsini) {
		this.ntinsini = ntinsini;
	}

	/**
	 * Gets the obsntinsfin.
	 *
	 * @return the obsntinsfin
	 */
	public String getObsntinsfin() {
		return this.obsntinsfin;
	}

	/**
	 * Sets the obsntinsfin.
	 *
	 * @param obsntinsfin the new obsntinsfin
	 */
	public void setObsntinsfin(String obsntinsfin) {
		this.obsntinsfin = obsntinsfin;
	}

	/**
	 * Gets the obsntinsini.
	 *
	 * @return the obsntinsini
	 */
	public String getObsntinsini() {
		return this.obsntinsini;
	}

	/**
	 * Sets the obsntinsini.
	 *
	 * @param obsntinsini the new obsntinsini
	 */
	public void setObsntinsini(String obsntinsini) {
		this.obsntinsini = obsntinsini;
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