package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM1511 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm1511.findAll", query="SELECT p FROM Pm1511 p")
public class Pm1511 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The acunccm. */
	private Integer acunccm;

	/** The acunccms. */
	private Integer acunccms;

	/** The acutcpd. */
	private Integer acutcpd;

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

	/** The nccm. */
	private Integer nccm = 0;

	/** The nccms. */
	private Integer nccms = 0;

	/** The obsnccm. */
	private String obsnccm;

	/** The obsnccms. */
	private String obsnccms;

	/** The obstcpd. */
	private String obstcpd;

	/** The semestral. */
	private Integer semestral;

	/** The tcpd. */
	private Integer tcpd = 0;

	/** The userid. */
	@Column(name="USERID")
	private String userid;
	
	/** The b guardar. */
	@Transient
	private boolean bGuardar;

	/**
	 * Instantiates a new pm 1511.
	 */
	public Pm1511() {
	}

	/**
	 * Gets the acunccm.
	 *
	 * @return the acunccm
	 */
	public Integer getAcunccm() {
		return this.acunccm;
	}

	/**
	 * Sets the acunccm.
	 *
	 * @param acunccm the new acunccm
	 */
	public void setAcunccm(Integer acunccm) {
		this.acunccm = acunccm;
	}

	/**
	 * Gets the acunccms.
	 *
	 * @return the acunccms
	 */
	public Integer getAcunccms() {
		return this.acunccms;
	}

	/**
	 * Sets the acunccms.
	 *
	 * @param acunccms the new acunccms
	 */
	public void setAcunccms(Integer acunccms) {
		this.acunccms = acunccms;
	}

	/**
	 * Gets the acutcpd.
	 *
	 * @return the acutcpd
	 */
	public Integer getAcutcpd() {
		return this.acutcpd;
	}

	/**
	 * Sets the acutcpd.
	 *
	 * @param acutcpd the new acutcpd
	 */
	public void setAcutcpd(Integer acutcpd) {
		this.acutcpd = acutcpd;
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
	 * Gets the nccm.
	 *
	 * @return the nccm
	 */
	public Integer getNccm() {
		return this.nccm;
	}

	/**
	 * Sets the nccm.
	 *
	 * @param nccm the new nccm
	 */
	public void setNccm(Integer nccm) {
		this.nccm = nccm;
	}

	/**
	 * Gets the nccms.
	 *
	 * @return the nccms
	 */
	public Integer getNccms() {
		return this.nccms;
	}

	/**
	 * Sets the nccms.
	 *
	 * @param nccms the new nccms
	 */
	public void setNccms(Integer nccms) {
		this.nccms = nccms;
	}

	/**
	 * Gets the obsnccm.
	 *
	 * @return the obsnccm
	 */
	public String getObsnccm() {
		return this.obsnccm;
	}

	/**
	 * Sets the obsnccm.
	 *
	 * @param obsnccm the new obsnccm
	 */
	public void setObsnccm(String obsnccm) {
		this.obsnccm = obsnccm;
	}

	/**
	 * Gets the obsnccms.
	 *
	 * @return the obsnccms
	 */
	public String getObsnccms() {
		return this.obsnccms;
	}

	/**
	 * Sets the obsnccms.
	 *
	 * @param obsnccms the new obsnccms
	 */
	public void setObsnccms(String obsnccms) {
		this.obsnccms = obsnccms;
	}

	/**
	 * Gets the obstcpd.
	 *
	 * @return the obstcpd
	 */
	public String getObstcpd() {
		return this.obstcpd;
	}

	/**
	 * Sets the obstcpd.
	 *
	 * @param obstcpd the new obstcpd
	 */
	public void setObstcpd(String obstcpd) {
		this.obstcpd = obstcpd;
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
	 * Gets the tcpd.
	 *
	 * @return the tcpd
	 */
	public Integer getTcpd() {
		return this.tcpd;
	}

	/**
	 * Sets the tcpd.
	 *
	 * @param tcpd the new tcpd
	 */
	public void setTcpd(Integer tcpd) {
		this.tcpd = tcpd;
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