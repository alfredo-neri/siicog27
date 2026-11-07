package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM0611 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm0611.findAll", query = "SELECT p FROM Pm0611 p")
public class Pm0611 implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/** The semestral. */
	private Integer semestral = 0;

	/** The parve. */
	private Integer parve;

	/** The patru. */
	private Integer patru;

	/** The habi. */
	private Integer habi;

	/** The obsparve. */
	private String obsparve;

	/** The obspatru. */
	private String obspatru;

	/** The obshabi. */
	private String obshabi;

	/** The capturo. */
	private String capturo;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The userid. */
	@Column(name = "USERID")
	private String userid;

	/** The idsector. */
	private int idsector;

	/** The id ref. */
	@Column(name = "ID_REF")
	private Long idRef;

	/**
	 * Instantiates a new pm 0611.
	 */
	public Pm0611() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the semestral.
	 *
	 * @return the semestral
	 */
	public Integer getSemestral() {
		return semestral;
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
	 * Gets the parve.
	 *
	 * @return the parve
	 */
	public Integer getParve() {
		return parve;
	}

	/**
	 * Sets the parve.
	 *
	 * @param parve the new parve
	 */
	public void setParve(Integer parve) {
		this.parve = parve;
	}

	/**
	 * Gets the patru.
	 *
	 * @return the patru
	 */
	public Integer getPatru() {
		return patru;
	}

	/**
	 * Sets the patru.
	 *
	 * @param patru the new patru
	 */
	public void setPatru(Integer patru) {
		this.patru = patru;
	}

	/**
	 * Gets the habi.
	 *
	 * @return the habi
	 */
	public Integer getHabi() {
		return habi;
	}

	/**
	 * Sets the habi.
	 *
	 * @param habi the new habi
	 */
	public void setHabi(Integer habi) {
		this.habi = habi;
	}

	/**
	 * Gets the obsparve.
	 *
	 * @return the obsparve
	 */
	public String getObsparve() {
		return obsparve;
	}

	/**
	 * Sets the obsparve.
	 *
	 * @param obsparve the new obsparve
	 */
	public void setObsparve(String obsparve) {
		this.obsparve = obsparve;
	}

	/**
	 * Gets the obspatru.
	 *
	 * @return the obspatru
	 */
	public String getObspatru() {
		return obspatru;
	}

	/**
	 * Sets the obspatru.
	 *
	 * @param obspatru the new obspatru
	 */
	public void setObspatru(String obspatru) {
		this.obspatru = obspatru;
	}

	/**
	 * Gets the obshabi.
	 *
	 * @return the obshabi
	 */
	public String getObshabi() {
		return obshabi;
	}

	/**
	 * Sets the obshabi.
	 *
	 * @param obshabi the new obshabi
	 */
	public void setObshabi(String obshabi) {
		this.obshabi = obshabi;
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
	 * Gets the userid.
	 *
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
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
	 * Gets the idsector.
	 *
	 * @return the idsector
	 */
	public int getIdsector() {
		return idsector;
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
	 * Gets the id ref.
	 *
	 * @return the id ref
	 */
	public Long getIdRef() {
		return idRef;
	}

	/**
	 * Sets the id ref.
	 *
	 * @param idRef the new id ref
	 */
	public void setIdRef(Long idRef) {
		this.idRef = idRef;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pm0611 other = (Pm0611) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}