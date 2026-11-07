package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM5611 database table.
 * 
 */
@Entity
@NamedQuery(name = "Pm5611.findAll", query = "SELECT p FROM Pm5611 p")
public class Pm5611 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The capturo. */
	private String capturo;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The fecdep. */
	@Temporal(TemporalType.DATE)
	private Date fecdep;

	/** The fecdepfor. */
	@Temporal(TemporalType.DATE)
	private Date fecdepfor;

	/** The id ref. */
	@Column(name = "ID_REF")
	private Long idRef;

	/** The idsector. */
	private int idsector;

	/** The mensual. */
	private Integer mensual;

	/** The mes. */
	private String mes;

	/** The mminfism. */
	private BigDecimal mminfism = BigDecimal.ZERO;

	/** The mminfor. */
	private BigDecimal mminfor = BigDecimal.ZERO;

	/** The ncbfism. */
	private Integer ncbfism = 0;

	/** The ncbfor. */
	private Integer ncbfor = 0;

	/** The obsfism. */
	private String obsfism;

	/** The obsfor. */
	private String obsfor;

	/** The userid. */
	@Column(name = "USERID")
	private String userid;

	/**
	 * Instantiates a new pm 5611.
	 */
	public Pm5611() {
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
	 * Gets the fecdep.
	 *
	 * @return the fecdep
	 */
	public Date getFecdep() {
		return this.fecdep;
	}

	/**
	 * Sets the fecdep.
	 *
	 * @param fecdep the new fecdep
	 */
	public void setFecdep(Date fecdep) {
		this.fecdep = fecdep;
	}

	/**
	 * Gets the fecdepfor.
	 *
	 * @return the fecdepfor
	 */
	public Date getFecdepfor() {
		return this.fecdepfor;
	}

	/**
	 * Sets the fecdepfor.
	 *
	 * @param fecdepfor the new fecdepfor
	 */
	public void setFecdepfor(Date fecdepfor) {
		this.fecdepfor = fecdepfor;
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
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return this.mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the mminfism.
	 *
	 * @return the mminfism
	 */
	public BigDecimal getMminfism() {
		return this.mminfism;
	}

	/**
	 * Sets the mminfism.
	 *
	 * @param mminfism the new mminfism
	 */
	public void setMminfism(BigDecimal mminfism) {
		this.mminfism = mminfism;
	}

	/**
	 * Gets the mminfor.
	 *
	 * @return the mminfor
	 */
	public BigDecimal getMminfor() {
		return this.mminfor;
	}

	/**
	 * Sets the mminfor.
	 *
	 * @param mminfor the new mminfor
	 */
	public void setMminfor(BigDecimal mminfor) {
		this.mminfor = mminfor;
	}

	/**
	 * Gets the ncbfism.
	 *
	 * @return the ncbfism
	 */
	public Integer getNcbfism() {
		return this.ncbfism;
	}

	/**
	 * Sets the ncbfism.
	 *
	 * @param ncbfism the new ncbfism
	 */
	public void setNcbfism(Integer ncbfism) {
		this.ncbfism = ncbfism;
	}

	/**
	 * Gets the ncbfor.
	 *
	 * @return the ncbfor
	 */
	public Integer getNcbfor() {
		return this.ncbfor;
	}

	/**
	 * Sets the ncbfor.
	 *
	 * @param ncbfor the new ncbfor
	 */
	public void setNcbfor(Integer ncbfor) {
		this.ncbfor = ncbfor;
	}

	/**
	 * Gets the obsfism.
	 *
	 * @return the obsfism
	 */
	public String getObsfism() {
		return this.obsfism;
	}

	/**
	 * Sets the obsfism.
	 *
	 * @param obsfism the new obsfism
	 */
	public void setObsfism(String obsfism) {
		this.obsfism = obsfism;
	}

	/**
	 * Gets the obsfor.
	 *
	 * @return the obsfor
	 */
	public String getObsfor() {
		return this.obsfor;
	}

	/**
	 * Sets the obsfor.
	 *
	 * @param obsfor the new obsfor
	 */
	public void setObsfor(String obsfor) {
		this.obsfor = obsfor;
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