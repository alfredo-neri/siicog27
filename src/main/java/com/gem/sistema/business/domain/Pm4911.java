package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM4911 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm4911.findAll", query = "SELECT p FROM Pm4911 p")
public class Pm4911 implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/** The acuncpip. */
	private Integer acuncpip;

	/** The acutcrpip. */
	private Integer acutcrpip;

	/** The capturo. */
	private String capturo;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The id ref. */
	@Column(name = "ID_REF")
	private Long idRef;

	/** The idsector. */
	private int idsector;

	/** The mensual. */
	private Integer mensual;

	/** The ncpip. */
	private Integer ncpip;

	/** The obsncpip. */
	private String obsncpip;

	/** The obstcrpip. */
	private String obstcrpip;

	/** The tcrpip. */
	private Integer tcrpip;

	/** The userid. */
	@Column(name = "USERID")
	private String userid;

	/**
	 * Instantiates a new pm 4911.
	 */
	public Pm4911() {
	}

	/**
	 * Gets the acuncpip.
	 *
	 * @return the acuncpip
	 */
	public Integer getAcuncpip() {
		return this.acuncpip;
	}

	/**
	 * Sets the acuncpip.
	 *
	 * @param acuncpip the new acuncpip
	 */
	public void setAcuncpip(Integer acuncpip) {
		this.acuncpip = acuncpip;
	}

	/**
	 * Gets the acutcrpip.
	 *
	 * @return the acutcrpip
	 */
	public Integer getAcutcrpip() {
		return this.acutcrpip;
	}

	/**
	 * Sets the acutcrpip.
	 *
	 * @param acutcrpip the new acutcrpip
	 */
	public void setAcutcrpip(Integer acutcrpip) {
		this.acutcrpip = acutcrpip;
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
	public int getIdsector() {
		return this.idsector;
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
	 * Gets the mensual.
	 *
	 * @return the mensual
	 */
	public Integer getMensual() {
		return this.mensual;
	}

	/**
	 * Sets the mensual.
	 *
	 * @param mensual the new mensual
	 */
	public void setMensual(Integer mensual) {
		this.mensual = mensual;
	}

	/**
	 * Gets the ncpip.
	 *
	 * @return the ncpip
	 */
	public Integer getNcpip() {
		return this.ncpip;
	}

	/**
	 * Sets the ncpip.
	 *
	 * @param ncpip the new ncpip
	 */
	public void setNcpip(Integer ncpip) {
		this.ncpip = ncpip;
	}

	/**
	 * Gets the obsncpip.
	 *
	 * @return the obsncpip
	 */
	public String getObsncpip() {
		return this.obsncpip;
	}

	/**
	 * Sets the obsncpip.
	 *
	 * @param obsncpip the new obsncpip
	 */
	public void setObsncpip(String obsncpip) {
		this.obsncpip = obsncpip;
	}

	/**
	 * Gets the obstcrpip.
	 *
	 * @return the obstcrpip
	 */
	public String getObstcrpip() {
		return this.obstcrpip;
	}

	/**
	 * Sets the obstcrpip.
	 *
	 * @param obstcrpip the new obstcrpip
	 */
	public void setObstcrpip(String obstcrpip) {
		this.obstcrpip = obstcrpip;
	}

	/**
	 * Gets the tcrpip.
	 *
	 * @return the tcrpip
	 */
	public Integer getTcrpip() {
		return this.tcrpip;
	}

	/**
	 * Sets the tcrpip.
	 *
	 * @param tcrpip the new tcrpip
	 */
	public void setTcrpip(Integer tcrpip) {
		this.tcrpip = tcrpip;
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
		Pm4911 other = (Pm4911) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}