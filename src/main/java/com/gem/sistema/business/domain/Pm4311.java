package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * The persistent class for the PM4311 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm4311.findAll", query = "SELECT p FROM Pm4311 p")
public class Pm4311 implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/** The acumsub. */
	private BigDecimal acumsub;

	/** The acumtot. */
	private BigDecimal acumtot;

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

	/** The subsi. */
	private BigDecimal subsi;

	/** The obssub. */
	private String obssub;

	/** The obstot. */
	private String obstot;

	/** The toting. */
	private BigDecimal toting;

	/** The userid. */
	@Column(name = "USERID")
	private String userid;

	/**
	 * Instantiates a new pm 4311.
	 */
	public Pm4311() {
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
	 * Gets the acumsub.
	 *
	 * @return the acumsub
	 */
	public BigDecimal getAcumsub() {
		return acumsub;
	}

	/**
	 * Sets the acumsub.
	 *
	 * @param acumsub the new acumsub
	 */
	public void setAcumsub(BigDecimal acumsub) {
		this.acumsub = acumsub;
	}

	/**
	 * Gets the acumtot.
	 *
	 * @return the acumtot
	 */
	public BigDecimal getAcumtot() {
		return acumtot;
	}

	/**
	 * Sets the acumtot.
	 *
	 * @param acumtot the new acumtot
	 */
	public void setAcumtot(BigDecimal acumtot) {
		this.acumtot = acumtot;
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
	 * Gets the subsi.
	 *
	 * @return the subsi
	 */
	public BigDecimal getSubsi() {
		return subsi;
	}

	/**
	 * Sets the subsi.
	 *
	 * @param subsi the new subsi
	 */
	public void setSubsi(BigDecimal subsi) {
		this.subsi = subsi;
	}

	/**
	 * Gets the obssub.
	 *
	 * @return the obssub
	 */
	public String getObssub() {
		return obssub;
	}

	/**
	 * Sets the obssub.
	 *
	 * @param obssub the new obssub
	 */
	public void setObssub(String obssub) {
		this.obssub = obssub;
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
	 * Gets the toting.
	 *
	 * @return the toting
	 */
	public BigDecimal getToting() {
		return toting;
	}

	/**
	 * Sets the toting.
	 *
	 * @param toting the new toting
	 */
	public void setToting(BigDecimal toting) {
		this.toting = toting;
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
		Pm4311 other = (Pm4311) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}