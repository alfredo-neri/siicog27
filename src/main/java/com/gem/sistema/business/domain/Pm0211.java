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
 * The persistent class for the PM0211 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm0211.findAll", query = "SELECT p FROM Pm0211 p")
public class Pm0211 implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

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
	private Integer mensual = 0;

	/** The np. */
	private Integer np;

	/** The pt. */
	private Integer pt;

	/** The obspt. */
	private String obspt;

	/** The obsnp. */
	private String obsnp;

	/** The userid. */
	@Column(name = "USERID")
	private String userid;

	/**
	 * Instantiates a new pm 0211.
	 */
	public Pm0211() {
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
	 * Gets the mensual.
	 *
	 * @return the mensual
	 */
	public Integer getMensual() {
		return mensual;
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
	 * Gets the np.
	 *
	 * @return the np
	 */
	public Integer getNp() {
		return np;
	}

	/**
	 * Sets the np.
	 *
	 * @param np the new np
	 */
	public void setNp(Integer np) {
		this.np = np;
	}

	/**
	 * Gets the pt.
	 *
	 * @return the pt
	 */
	public Integer getPt() {
		return pt;
	}

	/**
	 * Sets the pt.
	 *
	 * @param pt the new pt
	 */
	public void setPt(Integer pt) {
		this.pt = pt;
	}

	/**
	 * Gets the obspt.
	 *
	 * @return the obspt
	 */
	public String getObspt() {
		return obspt;
	}

	/**
	 * Sets the obspt.
	 *
	 * @param obspt the new obspt
	 */
	public void setObspt(String obspt) {
		this.obspt = obspt;
	}

	/**
	 * Gets the obsnp.
	 *
	 * @return the obsnp
	 */
	public String getObsnp() {
		return obsnp;
	}

	/**
	 * Sets the obsnp.
	 *
	 * @param obsnp the new obsnp
	 */
	public void setObsnp(String obsnp) {
		this.obsnp = obsnp;
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
		Pm0211 other = (Pm0211) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}