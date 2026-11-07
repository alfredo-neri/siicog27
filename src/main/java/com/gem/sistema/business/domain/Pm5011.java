package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM5011 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm5011.findAll", query="SELECT p FROM Pm5011 p")
public class Pm5011 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The acumtcc. */
	private Integer acumtcc;

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

	/** The mensual. */
	private Integer mensual = 0;

	/** The obstcc. */
	private String obstcc;

	/** The obstcr. */
	private String obstcr;

	/** The tcc. */
	private Integer tcc;

	/** The tcr. */
	private Integer tcr;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/**
	 * Instantiates a new pm 5011.
	 */
	public Pm5011() {
	}

	/**
	 * Gets the acumtcc.
	 *
	 * @return the acumtcc
	 */
	public Integer getAcumtcc() {
		return this.acumtcc;
	}

	/**
	 * Sets the acumtcc.
	 *
	 * @param acumtcc the new acumtcc
	 */
	public void setAcumtcc(Integer acumtcc) {
		this.acumtcc = acumtcc;
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
	 * Gets the mensual.
	 *
	 * @return the mensual
	 */
	public Integer getMensual() {
		return this.mensual;
	}
	

	/**
	 * Sets the mensual.
	 *
	 * @param mensual the new mensual
	 */
	public void setMensual(Integer mensual) {
		this.mensual = mensual;
	}

	/**
	 * Gets the obstcc.
	 *
	 * @return the obstcc
	 */
	public String getObstcc() {
		return this.obstcc;
	}

	/**
	 * Sets the obstcc.
	 *
	 * @param obstcc the new obstcc
	 */
	public void setObstcc(String obstcc) {
		this.obstcc = obstcc;
	}

	/**
	 * Gets the obstcr.
	 *
	 * @return the obstcr
	 */
	public String getObstcr() {
		return this.obstcr;
	}

	/**
	 * Sets the obstcr.
	 *
	 * @param obstcr the new obstcr
	 */
	public void setObstcr(String obstcr) {
		this.obstcr = obstcr;
	}

	/**
	 * Gets the tcc.
	 *
	 * @return the tcc
	 */
	public Integer getTcc() {
		return this.tcc;
	}

	/**
	 * Sets the tcc.
	 *
	 * @param tcc the new tcc
	 */
	public void setTcc(Integer tcc) {
		this.tcc = tcc;
	}

	/**
	 * Gets the tcr.
	 *
	 * @return the tcr
	 */
	public Integer getTcr() {
		return this.tcr;
	}

	/**
	 * Sets the tcr.
	 *
	 * @param tcr the new tcr
	 */
	public void setTcr(Integer tcr) {
		this.tcr = tcr;
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