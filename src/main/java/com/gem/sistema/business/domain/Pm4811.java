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

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM4811 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm4811.findAll", query = "SELECT p FROM Pm4811 p")
public class Pm4811 implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	/** The anual. */
	@Column(name = "ANUAL")
	private Integer anual; 
	
	/** The rta. */
	@Column(name = "RTA")
	private BigDecimal rta;
	
	/** The rtaa. */
	@Column(name = "rtaa")
	private BigDecimal rtaa;
	
	/** The obsrta. */
	@Column(name = "OBSRTA")
	private String obsrta;
	
	/** The obsrtaa. */
	@Column(name = "OBSRTAA")
	private String obsrtaa;
	
	/** The capturo. */
	@Column(name = "CAPTURO")
	private String capturo;
	
	/** The feccap. */
	@Column(name = "FECCAP")
	private Date feccap;
	
	/** The user id. */
	@Column(name = "USERID")
	private String userId;
	
	/** The id sector. */
	@Column(name = "IDSECTOR")
	private Integer idSector;
	
	/** The id ref. */
	@Column(name = "ID_REF")
	private Integer idRef;

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
	 * Gets the anual.
	 *
	 * @return the anual
	 */
	public Integer getAnual() {
		return anual;
	}

	/**
	 * Sets the anual.
	 *
	 * @param anual the new anual
	 */
	public void setAnual(Integer anual) {
		this.anual = anual;
	}

	/**
	 * Gets the rta.
	 *
	 * @return the rta
	 */
	public BigDecimal getRta() {
		return rta;
	}

	/**
	 * Sets the rta.
	 *
	 * @param rta the new rta
	 */
	public void setRta(BigDecimal rta) {
		this.rta = rta;
	}

	/**
	 * Gets the rtaa.
	 *
	 * @return the rtaa
	 */
	public BigDecimal getRtaa() {
		return rtaa;
	}

	/**
	 * Sets the rtaa.
	 *
	 * @param rtaa the new rtaa
	 */
	public void setRtaa(BigDecimal rtaa) {
		this.rtaa = rtaa;
	}

	/**
	 * Gets the obsrta.
	 *
	 * @return the obsrta
	 */
	public String getObsrta() {
		return obsrta;
	}

	/**
	 * Sets the obsrta.
	 *
	 * @param obsrta the new obsrta
	 */
	public void setObsrta(String obsrta) {
		this.obsrta = obsrta;
	}

	/**
	 * Gets the obsrtaa.
	 *
	 * @return the obsrtaa
	 */
	public String getObsrtaa() {
		return obsrtaa;
	}

	/**
	 * Sets the obsrtaa.
	 *
	 * @param obsrtaa the new obsrtaa
	 */
	public void setObsrtaa(String obsrtaa) {
		this.obsrtaa = obsrtaa;
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
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the id sector.
	 *
	 * @return the id sector
	 */
	public Integer getIdSector() {
		return idSector;
	}

	/**
	 * Sets the id sector.
	 *
	 * @param idSector the new id sector
	 */
	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

	/**
	 * Gets the id ref.
	 *
	 * @return the id ref
	 */
	public Integer getIdRef() {
		return idRef;
	}

	/**
	 * Sets the id ref.
	 *
	 * @param idRef the new id ref
	 */
	public void setIdRef(Integer idRef) {
		this.idRef = idRef;
	}

	
}