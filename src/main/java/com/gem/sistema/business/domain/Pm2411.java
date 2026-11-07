package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM2411 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm2411.findAll", query="SELECT p FROM Pm2411 p")
public class Pm2411 extends AbstractEntity implements Serializable {
	
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

	/** The npu. */
	private Integer npu;

	/** The obsnpu. */
	private String obsnpu;

	/** The obstpu. */
	private String obstpu;

	/** The tpu. */
	private Integer tpu;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/**
	 * Instantiates a new pm 2411.
	 */
	public Pm2411() {
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
	 * Gets the npu.
	 *
	 * @return the npu
	 */
	public Integer getNpu() {
		return this.npu;
	}

	/**
	 * Sets the npu.
	 *
	 * @param npu the new npu
	 */
	public void setNpu(Integer npu) {
		this.npu = npu;
	}

	/**
	 * Gets the obsnpu.
	 *
	 * @return the obsnpu
	 */
	public String getObsnpu() {
		return this.obsnpu;
	}

	/**
	 * Sets the obsnpu.
	 *
	 * @param obsnpu the new obsnpu
	 */
	public void setObsnpu(String obsnpu) {
		this.obsnpu = obsnpu;
	}

	/**
	 * Gets the obstpu.
	 *
	 * @return the obstpu
	 */
	public String getObstpu() {
		return this.obstpu;
	}

	/**
	 * Sets the obstpu.
	 *
	 * @param obstpu the new obstpu
	 */
	public void setObstpu(String obstpu) {
		this.obstpu = obstpu;
	}

	/**
	 * Gets the tpu.
	 *
	 * @return the tpu
	 */
	public Integer getTpu() {
		return this.tpu;
	}

	/**
	 * Sets the tpu.
	 *
	 * @param tpu the new tpu
	 */
	public void setTpu(Integer tpu) {
		this.tpu = tpu;
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

}