package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM2711 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm2711.findAll", query="SELECT p FROM Pm2711 p")
public class Pm2711 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final Long serialVersionUID = 1L;

	

	/** The aculap. */
	private Integer aculap;

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

	/** The lap. */
	private Integer lap;

	/** The obslap. */
	private String obslap;

	/** The obstloc. */
	private String obstloc;

	/** The semestral. */
	private Integer semestral;

	/** The tloc. */
	private Integer tloc;

	/** The userid. */
	@Column(name="USERID")
	private String userid;
	
	/** The b guardar. */
	@Transient
	private boolean bGuardar;

	/**
	 * Instantiates a new pm 2711.
	 */
	public Pm2711() {
	}

	/**
	 * Gets the aculap.
	 *
	 * @return the aculap
	 */
	public Integer getAculap() {
		return this.aculap;
	}

	/**
	 * Sets the aculap.
	 *
	 * @param aculap the new aculap
	 */
	public void setAculap(Integer aculap) {
		this.aculap = aculap;
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
	public Integer getIdsector() {
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
	 * Gets the lap.
	 *
	 * @return the lap
	 */
	public Integer getLap() {
		return this.lap;
	}

	/**
	 * Sets the lap.
	 *
	 * @param lap the new lap
	 */
	public void setLap(Integer lap) {
		this.lap = lap;
	}

	/**
	 * Gets the obslap.
	 *
	 * @return the obslap
	 */
	public String getObslap() {
		return this.obslap;
	}

	/**
	 * Sets the obslap.
	 *
	 * @param obslap the new obslap
	 */
	public void setObslap(String obslap) {
		this.obslap = obslap;
	}

	/**
	 * Gets the obstloc.
	 *
	 * @return the obstloc
	 */
	public String getObstloc() {
		return this.obstloc;
	}

	/**
	 * Sets the obstloc.
	 *
	 * @param obstloc the new obstloc
	 */
	public void setObstloc(String obstloc) {
		this.obstloc = obstloc;
	}

	/**
	 * Gets the semestral.
	 *
	 * @return the semestral
	 */
	public Integer getSemestral() {
		return this.semestral;
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
	 * Gets the tloc.
	 *
	 * @return the tloc
	 */
	public Integer getTloc() {
		return this.tloc;
	}

	/**
	 * Sets the tloc.
	 *
	 * @param tloc the new tloc
	 */
	public void setTloc(Integer tloc) {
		this.tloc = tloc;
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