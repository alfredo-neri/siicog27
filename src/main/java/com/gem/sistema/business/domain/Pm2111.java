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
 * The persistent class for the PM2111 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm2111.findAll", query = "SELECT p FROM Pm2111 p")
public class Pm2111 implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/** The trimestre. */
	private Integer trimestre = 0;

	/** The atendidas. */
	private Integer atendidas;

	/** The programadas. */
	private Integer programadas;

	/** The obsaten. */
	private String obsaten;

	/** The obsprog. */
	private String obsprog;

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
	 * Instantiates a new pm 2111.
	 */
	public Pm2111() {
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
	 * Gets the atendidas.
	 *
	 * @return the atendidas
	 */
	public Integer getAtendidas() {
		return atendidas;
	}

	/**
	 * Sets the atendidas.
	 *
	 * @param atendidas the new atendidas
	 */
	public void setAtendidas(Integer atendidas) {
		this.atendidas = atendidas;
	}

	/**
	 * Gets the programadas.
	 *
	 * @return the programadas
	 */
	public Integer getProgramadas() {
		return programadas;
	}

	/**
	 * Sets the programadas.
	 *
	 * @param programadas the new programadas
	 */
	public void setProgramadas(Integer programadas) {
		this.programadas = programadas;
	}

	/**
	 * Gets the obsaten.
	 *
	 * @return the obsaten
	 */
	public String getObsaten() {
		return obsaten;
	}

	/**
	 * Sets the obsaten.
	 *
	 * @param obsaten the new obsaten
	 */
	public void setObsaten(String obsaten) {
		this.obsaten = obsaten;
	}

	/**
	 * Gets the obsprog.
	 *
	 * @return the obsprog
	 */
	public String getObsprog() {
		return obsprog;
	}

	/**
	 * Sets the obsprog.
	 *
	 * @param obsprog the new obsprog
	 */
	public void setObsprog(String obsprog) {
		this.obsprog = obsprog;
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
		Pm2111 other = (Pm2111) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}