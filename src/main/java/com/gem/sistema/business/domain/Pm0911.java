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
 * The persistent class for the PM0911 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm0911.findAll", query = "SELECT p FROM Pm0911 p")
public class Pm0911 implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/** The semes. */
	private Integer semes;

	/** The zonas. */
	private Integer zonas;

	/** The totzonas. */
	private Integer totzonas;

	/** The obszonas. */
	private String obszonas;

	/** The obstotzon. */
	private String obstotzon;

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
	 * Instantiates a new pm 0911.
	 */
	public Pm0911() {
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
	 * Gets the semes.
	 *
	 * @return the semes
	 */
	public Integer getSemes() {
		return semes;
	}

	/**
	 * Sets the semes.
	 *
	 * @param semes the new semes
	 */
	public void setSemes(Integer semes) {
		this.semes = semes;
	}

	/**
	 * Gets the zonas.
	 *
	 * @return the zonas
	 */
	public Integer getZonas() {
		return zonas;
	}

	/**
	 * Sets the zonas.
	 *
	 * @param zonas the new zonas
	 */
	public void setZonas(Integer zonas) {
		this.zonas = zonas;
	}

	/**
	 * Gets the totzonas.
	 *
	 * @return the totzonas
	 */
	public Integer getTotzonas() {
		return totzonas;
	}

	/**
	 * Sets the totzonas.
	 *
	 * @param totzonas the new totzonas
	 */
	public void setTotzonas(Integer totzonas) {
		this.totzonas = totzonas;
	}

	/**
	 * Gets the obszonas.
	 *
	 * @return the obszonas
	 */
	public String getObszonas() {
		return obszonas;
	}

	/**
	 * Sets the obszonas.
	 *
	 * @param obszonas the new obszonas
	 */
	public void setObszonas(String obszonas) {
		this.obszonas = obszonas;
	}

	/**
	 * Gets the obstotzon.
	 *
	 * @return the obstotzon
	 */
	public String getObstotzon() {
		return obstotzon;
	}

	/**
	 * Sets the obstotzon.
	 *
	 * @param obstotzon the new obstotzon
	 */
	public void setObstotzon(String obstotzon) {
		this.obstotzon = obstotzon;
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
		Pm0911 other = (Pm0911) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pm0911 [id=" + id + ", semes=" + semes + ", zonas=" + zonas + ", totzonas=" + totzonas + ", obszonas="
				+ obszonas + ", obstotzon=" + obstotzon + ", capturo=" + capturo + ", feccap=" + feccap + ", userid="
				+ userid + ", idsector=" + idsector + ", idRef=" + idRef + "]";
	}

	
}