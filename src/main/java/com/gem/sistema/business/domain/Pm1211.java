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
 * The persistent class for the PM1211 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm1211.findAll", query = "SELECT p FROM Pm1211 p")
public class Pm1211 implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/** The semestral. */
	private Integer semestral;

	/** The transfe. */
	private BigDecimal transfe;

	/** The preegre. */
	private BigDecimal preegre;

	/** The acumtransfe. */
	private BigDecimal acumtransfe;

	/** The acumpre. */
	private BigDecimal acumpre;

	/** The obstrans. */
	private String obstrans;

	/** The obspre. */
	private String obspre;

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
	 * Instantiates a new pm 1211.
	 */
	public Pm1211() {
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
	 * Gets the semestral.
	 *
	 * @return the semestral
	 */
	public Integer getSemestral() {
		return semestral;
	}

	/**
	 * Sets the semestral.
	 *
	 * @param semestral the new semestral
	 */
	public void setSemestral(Integer semestral) {
		this.semestral = semestral;
	}

	/**
	 * Gets the transfe.
	 *
	 * @return the transfe
	 */
	public BigDecimal getTransfe() {
		return transfe;
	}

	/**
	 * Sets the transfe.
	 *
	 * @param transfe the new transfe
	 */
	public void setTransfe(BigDecimal transfe) {
		this.transfe = transfe;
	}

	/**
	 * Gets the preegre.
	 *
	 * @return the preegre
	 */
	public BigDecimal getPreegre() {
		return preegre;
	}

	/**
	 * Sets the preegre.
	 *
	 * @param preegre the new preegre
	 */
	public void setPreegre(BigDecimal preegre) {
		this.preegre = preegre;
	}

	/**
	 * Gets the acumtransfe.
	 *
	 * @return the acumtransfe
	 */
	public BigDecimal getAcumtransfe() {
		return acumtransfe;
	}

	/**
	 * Sets the acumtransfe.
	 *
	 * @param acumtransfe the new acumtransfe
	 */
	public void setAcumtransfe(BigDecimal acumtransfe) {
		this.acumtransfe = acumtransfe;
	}

	/**
	 * Gets the acumpre.
	 *
	 * @return the acumpre
	 */
	public BigDecimal getAcumpre() {
		return acumpre;
	}

	/**
	 * Sets the acumpre.
	 *
	 * @param acumpre the new acumpre
	 */
	public void setAcumpre(BigDecimal acumpre) {
		this.acumpre = acumpre;
	}

	/**
	 * Gets the obstrans.
	 *
	 * @return the obstrans
	 */
	public String getObstrans() {
		return obstrans;
	}

	/**
	 * Sets the obstrans.
	 *
	 * @param obstrans the new obstrans
	 */
	public void setObstrans(String obstrans) {
		this.obstrans = obstrans;
	}

	/**
	 * Gets the obspre.
	 *
	 * @return the obspre
	 */
	public String getObspre() {
		return obspre;
	}

	/**
	 * Sets the obspre.
	 *
	 * @param obspre the new obspre
	 */
	public void setObspre(String obspre) {
		this.obspre = obspre;
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
		Pm1211 other = (Pm1211) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}