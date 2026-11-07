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
 * The persistent class for the PM1611 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm1611.findAll", query = "SELECT p FROM Pm1611 p")
public class Pm1611 implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/** The trimestre. */
	private Integer trimestre;

	/** The conca. */
	private Integer conca;

	/** The ajp. */
	private Integer ajp;

	/** The acuconca. */
	private Integer acuconca;

	/** The acuajp. */
	private Integer acuajp;

	/** The obsconca. */
	private String obsconca;

	/** The obsajp. */
	private String obsajp;

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
	 * Instantiates a new pm 1611.
	 */
	public Pm1611() {
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
	 * Gets the trimestre.
	 *
	 * @return the trimestre
	 */
	public Integer getTrimestre() {
		return trimestre;
	}

	/**
	 * Sets the trimestre.
	 *
	 * @param trimestre the new trimestre
	 */
	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
	}

	/**
	 * Gets the conca.
	 *
	 * @return the conca
	 */
	public Integer getConca() {
		return conca;
	}

	/**
	 * Sets the conca.
	 *
	 * @param conca the new conca
	 */
	public void setConca(Integer conca) {
		this.conca = conca;
	}

	/**
	 * Gets the ajp.
	 *
	 * @return the ajp
	 */
	public Integer getAjp() {
		return ajp;
	}

	/**
	 * Sets the ajp.
	 *
	 * @param ajp the new ajp
	 */
	public void setAjp(Integer ajp) {
		this.ajp = ajp;
	}

	/**
	 * Gets the acuconca.
	 *
	 * @return the acuconca
	 */
	public Integer getAcuconca() {
		return acuconca;
	}

	/**
	 * Sets the acuconca.
	 *
	 * @param acuconca the new acuconca
	 */
	public void setAcuconca(Integer acuconca) {
		this.acuconca = acuconca;
	}

	/**
	 * Gets the acuajp.
	 *
	 * @return the acuajp
	 */
	public Integer getAcuajp() {
		return acuajp;
	}

	/**
	 * Sets the acuajp.
	 *
	 * @param acuajp the new acuajp
	 */
	public void setAcuajp(Integer acuajp) {
		this.acuajp = acuajp;
	}

	/**
	 * Gets the obsconca.
	 *
	 * @return the obsconca
	 */
	public String getObsconca() {
		return obsconca;
	}

	/**
	 * Sets the obsconca.
	 *
	 * @param obsconca the new obsconca
	 */
	public void setObsconca(String obsconca) {
		this.obsconca = obsconca;
	}

	/**
	 * Gets the obsajp.
	 *
	 * @return the obsajp
	 */
	public String getObsajp() {
		return obsajp;
	}

	/**
	 * Sets the obsajp.
	 *
	 * @param obsajp the new obsajp
	 */
	public void setObsajp(String obsajp) {
		this.obsajp = obsajp;
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
		Pm1611 other = (Pm1611) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}