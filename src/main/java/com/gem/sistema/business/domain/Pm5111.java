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
 * The persistent class for the PM5111 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm5111.findAll", query = "SELECT p FROM Pm5111 p")
public class Pm5111 implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/** The trimestre. */
	private Integer trimestre;

	/** The itsa. */
	private BigDecimal itsa;

	/** The teca. */
	private BigDecimal teca;

	/** The acuitsa. */
	private BigDecimal acuitsa;

	/** The acuteca. */
	private BigDecimal acuteca;

	/** The obsitsa. */
	private String obsitsa;

	/** The obsteca. */
	private String obsteca;

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
	 * Instantiates a new pm 5111.
	 */
	public Pm5111() {
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
	 * Gets the itsa.
	 *
	 * @return the itsa
	 */
	public BigDecimal getItsa() {
		return itsa;
	}

	/**
	 * Sets the itsa.
	 *
	 * @param itsa the new itsa
	 */
	public void setItsa(BigDecimal itsa) {
		this.itsa = itsa;
	}

	/**
	 * Gets the teca.
	 *
	 * @return the teca
	 */
	public BigDecimal getTeca() {
		return teca;
	}

	/**
	 * Sets the teca.
	 *
	 * @param teca the new teca
	 */
	public void setTeca(BigDecimal teca) {
		this.teca = teca;
	}

	/**
	 * Gets the acuitsa.
	 *
	 * @return the acuitsa
	 */
	public BigDecimal getAcuitsa() {
		return acuitsa;
	}

	/**
	 * Sets the acuitsa.
	 *
	 * @param acuitsa the new acuitsa
	 */
	public void setAcuitsa(BigDecimal acuitsa) {
		this.acuitsa = acuitsa;
	}

	/**
	 * Gets the acuteca.
	 *
	 * @return the acuteca
	 */
	public BigDecimal getAcuteca() {
		return acuteca;
	}

	/**
	 * Sets the acuteca.
	 *
	 * @param acuteca the new acuteca
	 */
	public void setAcuteca(BigDecimal acuteca) {
		this.acuteca = acuteca;
	}

	/**
	 * Gets the obsitsa.
	 *
	 * @return the obsitsa
	 */
	public String getObsitsa() {
		return obsitsa;
	}

	/**
	 * Sets the obsitsa.
	 *
	 * @param obsitsa the new obsitsa
	 */
	public void setObsitsa(String obsitsa) {
		this.obsitsa = obsitsa;
	}

	/**
	 * Gets the obsteca.
	 *
	 * @return the obsteca
	 */
	public String getObsteca() {
		return obsteca;
	}

	/**
	 * Sets the obsteca.
	 *
	 * @param obsteca the new obsteca
	 */
	public void setObsteca(String obsteca) {
		this.obsteca = obsteca;
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
		Pm5111 other = (Pm5111) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}