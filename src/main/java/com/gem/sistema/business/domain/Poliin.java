package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the POLIIN database table.
 * 
 */
@Entity
@NamedQuery(name="Poliin.findAll", query="SELECT p FROM Poliin p")
public class Poliin extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	

	/** The anopol. */
	private int anopol;

	/** The clave. */
	private int clave;

	/** The clvben. */
	private BigDecimal clvben;

	/** The conpol. */
	private int conpol;

	/** The id ref. */
	@Column(name="ID_REF")
	private long idRef;

	/** The idsector. */
	private int idsector;

	/** The mespol. */
	private int mespol;

	/** The moncor. */
	private BigDecimal moncor;

	/** The monlar. */
	private BigDecimal monlar;

	/** The renpol. */
	private Integer renpol;

	/** The tippol. */
	private String tippol;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/**
	 * Instantiates a new poliin.
	 */
	public Poliin() {
	}

	

	/**
	 * Gets the anopol.
	 *
	 * @return the anopol
	 */
	public int getAnopol() {
		return this.anopol;
	}

	/**
	 * Sets the anopol.
	 *
	 * @param anopol the new anopol
	 */
	public void setAnopol(int anopol) {
		this.anopol = anopol;
	}

	/**
	 * Gets the clave.
	 *
	 * @return the clave
	 */
	public int getClave() {
		return this.clave;
	}

	/**
	 * Sets the clave.
	 *
	 * @param clave the new clave
	 */
	public void setClave(int clave) {
		this.clave = clave;
	}

	/**
	 * Gets the clvben.
	 *
	 * @return the clvben
	 */
	public BigDecimal getClvben() {
		return this.clvben;
	}

	/**
	 * Sets the clvben.
	 *
	 * @param clvben the new clvben
	 */
	public void setClvben(BigDecimal clvben) {
		this.clvben = clvben;
	}

	/**
	 * Gets the conpol.
	 *
	 * @return the conpol
	 */
	public int getConpol() {
		return this.conpol;
	}

	/**
	 * Sets the conpol.
	 *
	 * @param conpol the new conpol
	 */
	public void setConpol(int conpol) {
		this.conpol = conpol;
	}

	/**
	 * Gets the id ref.
	 *
	 * @return the id ref
	 */
	public long getIdRef() {
		return this.idRef;
	}

	/**
	 * Sets the id ref.
	 *
	 * @param idRef the new id ref
	 */
	public void setIdRef(long idRef) {
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
	 * Gets the mespol.
	 *
	 * @return the mespol
	 */
	public int getMespol() {
		return this.mespol;
	}

	/**
	 * Sets the mespol.
	 *
	 * @param mespol the new mespol
	 */
	public void setMespol(int mespol) {
		this.mespol = mespol;
	}

	/**
	 * Gets the moncor.
	 *
	 * @return the moncor
	 */
	public BigDecimal getMoncor() {
		return this.moncor;
	}

	/**
	 * Sets the moncor.
	 *
	 * @param moncor the new moncor
	 */
	public void setMoncor(BigDecimal moncor) {
		this.moncor = moncor;
	}

	/**
	 * Gets the monlar.
	 *
	 * @return the monlar
	 */
	public BigDecimal getMonlar() {
		return this.monlar;
	}

	/**
	 * Sets the monlar.
	 *
	 * @param monlar the new monlar
	 */
	public void setMonlar(BigDecimal monlar) {
		this.monlar = monlar;
	}

	/**
	 * Gets the renpol.
	 *
	 * @return the renpol
	 */
	public Integer getRenpol() {
		return this.renpol;
	}

	/**
	 * Sets the renpol.
	 *
	 * @param renpol the new renpol
	 */
	public void setRenpol(Integer renpol) {
		this.renpol = renpol;
	}

	/**
	 * Gets the tippol.
	 *
	 * @return the tippol
	 */
	public String getTippol() {
		return this.tippol;
	}

	/**
	 * Sets the tippol.
	 *
	 * @param tippol the new tippol
	 */
	public void setTippol(String tippol) {
		this.tippol = tippol;
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