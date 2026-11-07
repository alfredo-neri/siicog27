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
 * The persistent class for the PM1811 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm1811.findAll", query = "SELECT p FROM Pm1811 p")
public class Pm1811 implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/** The semes. */
	private Integer semes;

	/** The numni. */
	private Integer numni;

	/** The numdes. */
	private Integer numdes;

	/** The acumnumni. */
	private Integer acumnumni;

	/** The obsnumni. */
	private String obsnumni;

	/** The obsnumdes. */
	private String obsnumdes;

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
	 * Instantiates a new pm 1811.
	 */
	public Pm1811() {
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
	 * Gets the numni.
	 *
	 * @return the numni
	 */
	public Integer getNumni() {
		return numni;
	}

	/**
	 * Sets the numni.
	 *
	 * @param numni the new numni
	 */
	public void setNumni(Integer numni) {
		this.numni = numni;
	}

	/**
	 * Gets the numdes.
	 *
	 * @return the numdes
	 */
	public Integer getNumdes() {
		return numdes;
	}

	/**
	 * Sets the numdes.
	 *
	 * @param numdes the new numdes
	 */
	public void setNumdes(Integer numdes) {
		this.numdes = numdes;
	}

	/**
	 * Gets the acumnumni.
	 *
	 * @return the acumnumni
	 */
	public Integer getAcumnumni() {
		return acumnumni;
	}

	/**
	 * Sets the acumnumni.
	 *
	 * @param acumnumni the new acumnumni
	 */
	public void setAcumnumni(Integer acumnumni) {
		this.acumnumni = acumnumni;
	}

	/**
	 * Gets the obsnumni.
	 *
	 * @return the obsnumni
	 */
	public String getObsnumni() {
		return obsnumni;
	}

	/**
	 * Sets the obsnumni.
	 *
	 * @param obsnumni the new obsnumni
	 */
	public void setObsnumni(String obsnumni) {
		this.obsnumni = obsnumni;
	}

	/**
	 * Gets the obsnumdes.
	 *
	 * @return the obsnumdes
	 */
	public String getObsnumdes() {
		return obsnumdes;
	}

	/**
	 * Sets the obsnumdes.
	 *
	 * @param obsnumdes the new obsnumdes
	 */
	public void setObsnumdes(String obsnumdes) {
		this.obsnumdes = obsnumdes;
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
		Pm1811 other = (Pm1811) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}