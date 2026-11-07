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
 * The persistent class for the PM1111 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm1111.findAll", query = "SELECT p FROM Pm1111 p")
public class Pm1111 implements Serializable {
	
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
	
	/** The itev. */
	@Column(name = "ITEV")
	private BigDecimal itev;
	
	/** The natev. */
	@Column(name = "NATEV")
	private Integer natev;
	
	/** The acuitev. */
	@Column(name = "ACUITEV")
	private BigDecimal acuitev;
	
	/** The acunatev. */
	@Column(name = "ACUNATEV")
	private Integer acunatev;
	
	/** The obsitev. */
	@Column(name = "OBSITEV")
	private String obsitev;
	
	/** The obsnatev. */
	@Column(name = "OBSNATEV")
	private String obsnatev;
	
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
	 * Gets the itev.
	 *
	 * @return the itev
	 */
	public BigDecimal getItev() {
		return itev;
	}
	
	/**
	 * Sets the itev.
	 *
	 * @param itev the new itev
	 */
	public void setItev(BigDecimal itev) {
		this.itev = itev;
	}
	
	/**
	 * Gets the natev.
	 *
	 * @return the natev
	 */
	public Integer getNatev() {
		return natev;
	}
	
	/**
	 * Sets the natev.
	 *
	 * @param natev the new natev
	 */
	public void setNatev(Integer natev) {
		this.natev = natev;
	}
	
	/**
	 * Gets the acuitev.
	 *
	 * @return the acuitev
	 */
	public BigDecimal getAcuitev() {
		return acuitev;
	}
	
	/**
	 * Sets the acuitev.
	 *
	 * @param acuitev the new acuitev
	 */
	public void setAcuitev(BigDecimal acuitev) {
		this.acuitev = acuitev;
	}
	
	/**
	 * Gets the acunatev.
	 *
	 * @return the acunatev
	 */
	public Integer getAcunatev() {
		return acunatev;
	}
	
	/**
	 * Sets the acunatev.
	 *
	 * @param acunatev the new acunatev
	 */
	public void setAcunatev(Integer acunatev) {
		this.acunatev = acunatev;
	}
	
	/**
	 * Gets the obsitev.
	 *
	 * @return the obsitev
	 */
	public String getObsitev() {
		return obsitev;
	}
	
	/**
	 * Sets the obsitev.
	 *
	 * @param obsitev the new obsitev
	 */
	public void setObsitev(String obsitev) {
		this.obsitev = obsitev;
	}
	
	/**
	 * Gets the obsnatev.
	 *
	 * @return the obsnatev
	 */
	public String getObsnatev() {
		return obsnatev;
	}
	
	/**
	 * Sets the obsnatev.
	 *
	 * @param obsnatev the new obsnatev
	 */
	public void setObsnatev(String obsnatev) {
		this.obsnatev = obsnatev;
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