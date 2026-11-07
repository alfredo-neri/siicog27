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
 * The persistent class for the PM2211 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm2211.findAll", query = "SELECT p FROM Pm2211 p")
public class Pm2211 implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/** The trimestre. */
	private Integer trimestre;

	/** The ajb. */
	private Integer ajb;

	/** The taj. */
	private Integer taj;

	/** The acuajb. */
	private Integer acuajb;

	/** The acutaj. */
	private Integer acutaj;

	/** The obsajb. */
	private String obsajb;

	/** The obstaj. */
	private String obstaj;

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
	 * Instantiates a new pm 2211.
	 */
	public Pm2211() {
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
	 * Gets the ajb.
	 *
	 * @return the ajb
	 */
	public Integer getAjb() {
		return ajb;
	}

	/**
	 * Sets the ajb.
	 *
	 * @param ajb the new ajb
	 */
	public void setAjb(Integer ajb) {
		this.ajb = ajb;
	}

	/**
	 * Gets the taj.
	 *
	 * @return the taj
	 */
	public Integer getTaj() {
		return taj;
	}

	/**
	 * Sets the taj.
	 *
	 * @param taj the new taj
	 */
	public void setTaj(Integer taj) {
		this.taj = taj;
	}

	/**
	 * Gets the acuajb.
	 *
	 * @return the acuajb
	 */
	public Integer getAcuajb() {
		return acuajb;
	}

	/**
	 * Sets the acuajb.
	 *
	 * @param acuajb the new acuajb
	 */
	public void setAcuajb(Integer acuajb) {
		this.acuajb = acuajb;
	}

	/**
	 * Gets the acutaj.
	 *
	 * @return the acutaj
	 */
	public Integer getAcutaj() {
		return acutaj;
	}

	/**
	 * Sets the acutaj.
	 *
	 * @param acutaj the new acutaj
	 */
	public void setAcutaj(Integer acutaj) {
		this.acutaj = acutaj;
	}

	/**
	 * Gets the obsajb.
	 *
	 * @return the obsajb
	 */
	public String getObsajb() {
		return obsajb;
	}

	/**
	 * Sets the obsajb.
	 *
	 * @param obsajb the new obsajb
	 */
	public void setObsajb(String obsajb) {
		this.obsajb = obsajb;
	}

	/**
	 * Gets the obstaj.
	 *
	 * @return the obstaj
	 */
	public String getObstaj() {
		return obstaj;
	}

	/**
	 * Sets the obstaj.
	 *
	 * @param obstaj the new obstaj
	 */
	public void setObstaj(String obstaj) {
		this.obstaj = obstaj;
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
		Pm2211 other = (Pm2211) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}