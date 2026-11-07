package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM1111 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm1311.findAll", query = "SELECT p FROM Pm1311 p")
public class Pm1311 implements Serializable {
	
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
	
	/** The tco. */
	@Column(name = "TCO")
	private Integer tco;
	
	/** The pttm. */
	@Column(name = "PTTM")
	private Integer pttm;
	
	/** The obstco. */
	@Column(name = "OBSTCO")
	private String obstco;
	
	/** The obspttm. */
	@Column(name = "OBSPTTM")
	private String obspttm;
	
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
	 * Gets the tco.
	 *
	 * @return the tco
	 */
	public Integer getTco() {
		return tco;
	}

	/**
	 * Sets the tco.
	 *
	 * @param tco the new tco
	 */
	public void setTco(Integer tco) {
		this.tco = tco;
	}

	/**
	 * Gets the pttm.
	 *
	 * @return the pttm
	 */
	public Integer getPttm() {
		return pttm;
	}

	/**
	 * Sets the pttm.
	 *
	 * @param pttm the new pttm
	 */
	public void setPttm(Integer pttm) {
		this.pttm = pttm;
	}

	/**
	 * Gets the obstco.
	 *
	 * @return the obstco
	 */
	public String getObstco() {
		return obstco;
	}

	/**
	 * Sets the obstco.
	 *
	 * @param obstco the new obstco
	 */
	public void setObstco(String obstco) {
		this.obstco = obstco;
	}

	/**
	 * Gets the obspttm.
	 *
	 * @return the obspttm
	 */
	public String getObspttm() {
		return obspttm;
	}

	/**
	 * Sets the obspttm.
	 *
	 * @param obspttm the new obspttm
	 */
	public void setObspttm(String obspttm) {
		this.obspttm = obspttm;
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