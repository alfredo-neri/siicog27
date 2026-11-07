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
 * The persistent class for the PM0311 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm0311.findAll", query = "SELECT p FROM Pm0311 p")
public class Pm0311 implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/** The trimestre. */
	private Integer trimestre;

	/** The denuncias. */
	private Integer denuncias;

	/** The poblacion. */
	private Integer poblacion;

	/** The acuden. */
	private Integer acuden;

	/** The obsden. */
	private String obsden;

	/** The obspob. */
	private String obspob;

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
	 * Instantiates a new pm 0311.
	 */
	public Pm0311() {
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
	 * Gets the denuncias.
	 *
	 * @return the denuncias
	 */
	public Integer getDenuncias() {
		return denuncias;
	}

	/**
	 * Sets the denuncias.
	 *
	 * @param denuncias the new denuncias
	 */
	public void setDenuncias(Integer denuncias) {
		this.denuncias = denuncias;
	}

	/**
	 * Gets the poblacion.
	 *
	 * @return the poblacion
	 */
	public Integer getPoblacion() {
		return poblacion;
	}

	/**
	 * Sets the poblacion.
	 *
	 * @param poblacion the new poblacion
	 */
	public void setPoblacion(Integer poblacion) {
		this.poblacion = poblacion;
	}

	/**
	 * Gets the acuden.
	 *
	 * @return the acuden
	 */
	public Integer getAcuden() {
		return acuden;
	}

	/**
	 * Sets the acuden.
	 *
	 * @param acuden the new acuden
	 */
	public void setAcuden(Integer acuden) {
		this.acuden = acuden;
	}

	/**
	 * Gets the obsden.
	 *
	 * @return the obsden
	 */
	public String getObsden() {
		return obsden;
	}

	/**
	 * Sets the obsden.
	 *
	 * @param obsden the new obsden
	 */
	public void setObsden(String obsden) {
		this.obsden = obsden;
	}

	/**
	 * Gets the obspob.
	 *
	 * @return the obspob
	 */
	public String getObspob() {
		return obspob;
	}

	/**
	 * Sets the obspob.
	 *
	 * @param obspob the new obspob
	 */
	public void setObspob(String obspob) {
		this.obspob = obspob;
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
		Pm0311 other = (Pm0311) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}