package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM3311 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm3311.findAll", query="SELECT p FROM Pm3311 p")
public class Pm3311 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The anual. */
	private Integer anual;

	/** The capturo. */
	private String capturo;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private int idsector;

	/** The obspttm. */
	private String obspttm;

	/** The obsspaem. */
	private String obsspaem;

	/** The pttm. */
	private Integer pttm;

	/** The spaem. */
	private Integer spaem;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/** The b guardar. */
	@Transient
	private Boolean bGuardar;
	
	/**
	 * Instantiates a new pm 3311.
	 */
	public Pm3311() {
	}

	/**
	 * Gets the anual.
	 *
	 * @return the anual
	 */
	public Integer getAnual() {
		return this.anual;
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
	 * Gets the obspttm.
	 *
	 * @return the obspttm
	 */
	public String getObspttm() {
		return this.obspttm;
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
	 * Gets the obsspaem.
	 *
	 * @return the obsspaem
	 */
	public String getObsspaem() {
		return this.obsspaem;
	}

	/**
	 * Sets the obsspaem.
	 *
	 * @param obsspaem the new obsspaem
	 */
	public void setObsspaem(String obsspaem) {
		this.obsspaem = obsspaem;
	}

	/**
	 * Gets the pttm.
	 *
	 * @return the pttm
	 */
	public Integer getPttm() {
		return this.pttm;
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
	 * Gets the spaem.
	 *
	 * @return the spaem
	 */
	public Integer getSpaem() {
		return this.spaem;
	}

	/**
	 * Sets the spaem.
	 *
	 * @param spaem the new spaem
	 */
	public void setSpaem(Integer spaem) {
		this.spaem = spaem;
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
	 * Gets the b guardar.
	 *
	 * @return the b guardar
	 */
	public Boolean getbGuardar() {
		return bGuardar;
	}

	/**
	 * Sets the b guardar.
	 *
	 * @param bGuardar the new b guardar
	 */
	public void setbGuardar(Boolean bGuardar) {
		this.bGuardar = bGuardar;
	}

}