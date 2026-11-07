package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM2311 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm2311.findAll", query="SELECT p FROM Pm2311 p")
public class Pm2311 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

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

	/** The nvap. */
	private Integer nvap;

	/** The nvcp. */
	private Integer nvcp;

	/** The nvdre. */
	private Integer nvdre;

	/** The nvrb. */
	private Integer nvrb;

	/** The obsgral. */
	private String obsgral;

	/** The semestral. */
	private Integer semestral;

	/** The tvm. */
	private Integer tvm;

	/** The userid. */
	@Column(name="USERID")
	private String userid;
	
	/** The b guardar. */
	@Transient
	private boolean bGuardar;

	/**
	 * Instantiates a new pm 2311.
	 */
	public Pm2311() {
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
	 * Gets the nvap.
	 *
	 * @return the nvap
	 */
	public Integer getNvap() {
		return this.nvap;
	}

	/**
	 * Sets the nvap.
	 *
	 * @param nvap the new nvap
	 */
	public void setNvap(Integer nvap) {
		this.nvap = nvap;
	}

	/**
	 * Gets the nvcp.
	 *
	 * @return the nvcp
	 */
	public Integer getNvcp() {
		return this.nvcp;
	}

	/**
	 * Sets the nvcp.
	 *
	 * @param nvcp the new nvcp
	 */
	public void setNvcp(Integer nvcp) {
		this.nvcp = nvcp;
	}

	/**
	 * Gets the nvdre.
	 *
	 * @return the nvdre
	 */
	public Integer getNvdre() {
		return this.nvdre;
	}

	/**
	 * Sets the nvdre.
	 *
	 * @param nvdre the new nvdre
	 */
	public void setNvdre(Integer nvdre) {
		this.nvdre = nvdre;
	}

	/**
	 * Gets the nvrb.
	 *
	 * @return the nvrb
	 */
	public Integer getNvrb() {
		return this.nvrb;
	}

	/**
	 * Sets the nvrb.
	 *
	 * @param nvrb the new nvrb
	 */
	public void setNvrb(Integer nvrb) {
		this.nvrb = nvrb;
	}

	/**
	 * Gets the obsgral.
	 *
	 * @return the obsgral
	 */
	public String getObsgral() {
		return this.obsgral;
	}

	/**
	 * Sets the obsgral.
	 *
	 * @param obsgral the new obsgral
	 */
	public void setObsgral(String obsgral) {
		this.obsgral = obsgral;
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
	 * Gets the tvm.
	 *
	 * @return the tvm
	 */
	public Integer getTvm() {
		return this.tvm;
	}

	/**
	 * Sets the tvm.
	 *
	 * @param tvm the new tvm
	 */
	public void setTvm(Integer tvm) {
		this.tvm = tvm;
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