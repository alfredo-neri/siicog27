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
 * The persistent class for the PM3011 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm3011.findAll", query = "SELECT p FROM Pm3011 p")
public class Pm3011 implements Serializable {
	
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
	
	/** The lkpav. */
	@Column(name = "LKPAV")
	private BigDecimal lkpav;
	
	/** The lknopav. */
	@Column(name = "LKNOPAV")
	private BigDecimal lknopav;
	
	/** The obslkpav. */
	@Column(name = "OBSLKPAV")
	private String obslkpav;
	
	/** The obslknopav. */
	@Column(name = "OBSLKNOPAV")
	private String obslknopav;
	
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
	 * Gets the lkpav.
	 *
	 * @return the lkpav
	 */
	public BigDecimal getLkpav() {
		return lkpav;
	}

	/**
	 * Sets the lkpav.
	 *
	 * @param lkpav the new lkpav
	 */
	public void setLkpav(BigDecimal lkpav) {
		this.lkpav = lkpav;
	}

	/**
	 * Gets the lknopav.
	 *
	 * @return the lknopav
	 */
	public BigDecimal getLknopav() {
		return lknopav;
	}

	/**
	 * Sets the lknopav.
	 *
	 * @param lknopav the new lknopav
	 */
	public void setLknopav(BigDecimal lknopav) {
		this.lknopav = lknopav;
	}

	/**
	 * Gets the obslkpav.
	 *
	 * @return the obslkpav
	 */
	public String getObslkpav() {
		return obslkpav;
	}

	/**
	 * Sets the obslkpav.
	 *
	 * @param obslkpav the new obslkpav
	 */
	public void setObslkpav(String obslkpav) {
		this.obslkpav = obslkpav;
	}

	/**
	 * Gets the obslknopav.
	 *
	 * @return the obslknopav
	 */
	public String getObslknopav() {
		return obslknopav;
	}

	/**
	 * Sets the obslknopav.
	 *
	 * @param obslknopav the new obslknopav
	 */
	public void setObslknopav(String obslknopav) {
		this.obslknopav = obslknopav;
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