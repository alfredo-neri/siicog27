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
 * The persistent class for the PM2811 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm2811.findAll", query = "SELECT p FROM Pm2811 p")
public class Pm2811 implements Serializable {
	
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
	
	/** The vdst. */
	@Column(name = "VDST")
	private BigDecimal vdst;
	
	/** The vdsg. */
	@Column(name = "VDSG")
	private BigDecimal vdsg;
	
	/** The obsvdst. */
	@Column(name = "OBSVDST")
	private String obsvdst;
	
	/** The obsvdsg. */
	@Column(name = "OBSVDSG")
	private String obsvdsg;
	
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
	 * Gets the vdst.
	 *
	 * @return the vdst
	 */
	public BigDecimal getVdst() {
		return vdst;
	}

	/**
	 * Sets the vdst.
	 *
	 * @param vdst the new vdst
	 */
	public void setVdst(BigDecimal vdst) {
		this.vdst = vdst;
	}

	/**
	 * Gets the vdsg.
	 *
	 * @return the vdsg
	 */
	public BigDecimal getVdsg() {
		return vdsg;
	}

	/**
	 * Sets the vdsg.
	 *
	 * @param vdsg the new vdsg
	 */
	public void setVdsg(BigDecimal vdsg) {
		this.vdsg = vdsg;
	}

	/**
	 * Gets the obsvdst.
	 *
	 * @return the obsvdst
	 */
	public String getObsvdst() {
		return obsvdst;
	}

	/**
	 * Sets the obsvdst.
	 *
	 * @param obsvdst the new obsvdst
	 */
	public void setObsvdst(String obsvdst) {
		this.obsvdst = obsvdst;
	}

	/**
	 * Gets the obsvdsg.
	 *
	 * @return the obsvdsg
	 */
	public String getObsvdsg() {
		return obsvdsg;
	}

	/**
	 * Sets the obsvdsg.
	 *
	 * @param obsvdsg the new obsvdsg
	 */
	public void setObsvdsg(String obsvdsg) {
		this.obsvdsg = obsvdsg;
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