package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM4611 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm4611.findAll", query="SELECT p FROM Pm4611 p")
public class Pm4611 extends AbstractEntity implements Serializable {
	
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
	private Integer idsector;

	/** The nrcifp. */
	private Integer nrcifp;

	/** The nrciip. */
	private Integer nrciip;

	/** The obsnrcifp. */
	private String obsnrcifp;

	/** The obsnrciip. */
	private String obsnrciip;

	/** The userid. */
	@Column(name="USERID")
	private String userid;
	
	/** The b guardar. */
	@Transient
	private boolean bGuardar;

	/**
	 * Instantiates a new pm 4611.
	 */
	public Pm4611() {
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
	public void setIdsector(Integer idsector) {
		this.idsector = idsector;
	}

	/**
	 * Gets the nrcifp.
	 *
	 * @return the nrcifp
	 */
	public Integer getNrcifp() {
		return this.nrcifp;
	}

	/**
	 * Sets the nrcifp.
	 *
	 * @param nrcifp the new nrcifp
	 */
	public void setNrcifp(Integer nrcifp) {
		this.nrcifp = nrcifp;
	}

	/**
	 * Gets the nrciip.
	 *
	 * @return the nrciip
	 */
	public Integer getNrciip() {
		return this.nrciip;
	}

	/**
	 * Sets the nrciip.
	 *
	 * @param nrciip the new nrciip
	 */
	public void setNrciip(Integer nrciip) {
		this.nrciip = nrciip;
	}

	/**
	 * Gets the obsnrcifp.
	 *
	 * @return the obsnrcifp
	 */
	public String getObsnrcifp() {
		return this.obsnrcifp;
	}

	/**
	 * Sets the obsnrcifp.
	 *
	 * @param obsnrcifp the new obsnrcifp
	 */
	public void setObsnrcifp(String obsnrcifp) {
		this.obsnrcifp = obsnrcifp;
	}

	/**
	 * Gets the obsnrciip.
	 *
	 * @return the obsnrciip
	 */
	public String getObsnrciip() {
		return this.obsnrciip;
	}

	/**
	 * Sets the obsnrciip.
	 *
	 * @param obsnrciip the new obsnrciip
	 */
	public void setObsnrciip(String obsnrciip) {
		this.obsnrciip = obsnrciip;
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
	 * Checks if is b guardar.
	 *
	 * @return true, if is b guardar
	 */
	public boolean isbGuardar() {
		return bGuardar;
	}

	/**
	 * Sets the b guardar.
	 *
	 * @param bGuardar the new b guardar
	 */
	public void setbGuardar(boolean bGuardar) {
		this.bGuardar = bGuardar;
	}

	
}