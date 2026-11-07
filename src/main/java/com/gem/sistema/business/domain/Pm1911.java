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
 * The persistent class for the PM1911 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm1911.findAll", query = "SELECT p FROM Pm1911 p")
public class Pm1911 implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/** The trimestre. */
	private Integer trimestre;

	/** The perdis. */
	private Integer perdis;

	/** The totperdis. */
	private Integer totperdis;

	/** The acumperdis. */
	private Integer acumperdis;

	/** The acumtot. */
	private Integer acumtot;

	/** The obsperdis. */
	private String obsperdis;

	/** The obstot. */
	private String obstot;

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
	 * Instantiates a new pm 1911.
	 */
	public Pm1911() {
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
	 * Gets the perdis.
	 *
	 * @return the perdis
	 */
	public Integer getPerdis() {
		return perdis;
	}

	/**
	 * Sets the perdis.
	 *
	 * @param perdis the new perdis
	 */
	public void setPerdis(Integer perdis) {
		this.perdis = perdis;
	}

	/**
	 * Gets the totperdis.
	 *
	 * @return the totperdis
	 */
	public Integer getTotperdis() {
		return totperdis;
	}

	/**
	 * Sets the totperdis.
	 *
	 * @param totperdis the new totperdis
	 */
	public void setTotperdis(Integer totperdis) {
		this.totperdis = totperdis;
	}

	/**
	 * Gets the acumperdis.
	 *
	 * @return the acumperdis
	 */
	public Integer getAcumperdis() {
		return acumperdis;
	}

	/**
	 * Sets the acumperdis.
	 *
	 * @param acumperdis the new acumperdis
	 */
	public void setAcumperdis(Integer acumperdis) {
		this.acumperdis = acumperdis;
	}

	/**
	 * Gets the acumtot.
	 *
	 * @return the acumtot
	 */
	public Integer getAcumtot() {
		return acumtot;
	}

	/**
	 * Sets the acumtot.
	 *
	 * @param acumtot the new acumtot
	 */
	public void setAcumtot(Integer acumtot) {
		this.acumtot = acumtot;
	}

	/**
	 * Gets the obsperdis.
	 *
	 * @return the obsperdis
	 */
	public String getObsperdis() {
		return obsperdis;
	}

	/**
	 * Sets the obsperdis.
	 *
	 * @param obsperdis the new obsperdis
	 */
	public void setObsperdis(String obsperdis) {
		this.obsperdis = obsperdis;
	}

	/**
	 * Gets the obstot.
	 *
	 * @return the obstot
	 */
	public String getObstot() {
		return obstot;
	}

	/**
	 * Sets the obstot.
	 *
	 * @param obstot the new obstot
	 */
	public void setObstot(String obstot) {
		this.obstot = obstot;
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
		Pm1911 other = (Pm1911) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}