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
 * The persistent class for the PM1711 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm1711.findAll", query = "SELECT p FROM Pm1711 p")
public class Pm1711 implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/** The trimestre. */
	private Integer trimestre;

	/** The cm. */
	private Integer cm;

	/** The me. */
	private Integer me;

	/** The dias. */
	private Integer dias;

	/** The acucm. */
	private Integer acucm;

	/** The acume. */
	private Integer acume;

	/** The acudias. */
	private Integer acudias;

	/** The obscm. */
	private String obscm;

	/** The obsme. */
	private String obsme;

	/** The obsdias. */
	private String obsdias;

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
	 * Instantiates a new pm 1711.
	 */
	public Pm1711() {
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
	 * Gets the cm.
	 *
	 * @return the cm
	 */
	public Integer getCm() {
		return cm;
	}

	/**
	 * Sets the cm.
	 *
	 * @param cm the new cm
	 */
	public void setCm(Integer cm) {
		this.cm = cm;
	}

	/**
	 * Gets the me.
	 *
	 * @return the me
	 */
	public Integer getMe() {
		return me;
	}

	/**
	 * Sets the me.
	 *
	 * @param me the new me
	 */
	public void setMe(Integer me) {
		this.me = me;
	}

	/**
	 * Gets the dias.
	 *
	 * @return the dias
	 */
	public Integer getDias() {
		return dias;
	}

	/**
	 * Sets the dias.
	 *
	 * @param dias the new dias
	 */
	public void setDias(Integer dias) {
		this.dias = dias;
	}

	/**
	 * Gets the acucm.
	 *
	 * @return the acucm
	 */
	public Integer getAcucm() {
		return acucm;
	}

	/**
	 * Sets the acucm.
	 *
	 * @param acucm the new acucm
	 */
	public void setAcucm(Integer acucm) {
		this.acucm = acucm;
	}

	/**
	 * Gets the acume.
	 *
	 * @return the acume
	 */
	public Integer getAcume() {
		return acume;
	}

	/**
	 * Sets the acume.
	 *
	 * @param acume the new acume
	 */
	public void setAcume(Integer acume) {
		this.acume = acume;
	}

	/**
	 * Gets the acudias.
	 *
	 * @return the acudias
	 */
	public Integer getAcudias() {
		return acudias;
	}

	/**
	 * Sets the acudias.
	 *
	 * @param acudias the new acudias
	 */
	public void setAcudias(Integer acudias) {
		this.acudias = acudias;
	}

	/**
	 * Gets the obscm.
	 *
	 * @return the obscm
	 */
	public String getObscm() {
		return obscm;
	}

	/**
	 * Sets the obscm.
	 *
	 * @param obscm the new obscm
	 */
	public void setObscm(String obscm) {
		this.obscm = obscm;
	}

	/**
	 * Gets the obsme.
	 *
	 * @return the obsme
	 */
	public String getObsme() {
		return obsme;
	}

	/**
	 * Sets the obsme.
	 *
	 * @param obsme the new obsme
	 */
	public void setObsme(String obsme) {
		this.obsme = obsme;
	}

	/**
	 * Gets the obsdias.
	 *
	 * @return the obsdias
	 */
	public String getObsdias() {
		return obsdias;
	}

	/**
	 * Sets the obsdias.
	 *
	 * @param obsdias the new obsdias
	 */
	public void setObsdias(String obsdias) {
		this.obsdias = obsdias;
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
		Pm1711 other = (Pm1711) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}