package com.gem.sistema.business.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the COPOME database table.
 * 
 */
@Entity
public class Copome extends AbstractEntity {

	/** The anopme. */
	private Integer anopme;

	/** The cocpme. */
	private Integer cocpme;

	/** The id ref. */
	@Column(name = "ID_REF")
	private Integer idRef;

	/** The idsector. */
	private Integer idsector;

	/** The mecpme. */
	private Integer mecpme;

	/** The tpcpme. */
	private String tpcpme;

	/** The userid. */
	@Column(name = "USERID")
	private String userid;
	
	/** The cod error. */
	@Transient
	private Integer codError;
	
	/** The msg error. */
	@Transient
	private String msgError;
	
	/** The num nex. */
	@Transient
	private Integer numNex;

	/**
	 * Instantiates a new copome.
	 */
	public Copome() {
	}
	
	

	/**
	 * Gets the anopme.
	 *
	 * @return the anopme
	 */
	public Integer getAnopme() {
		return this.anopme;
	}

	/**
	 * Sets the anopme.
	 *
	 * @param anopme the new anopme
	 */
	public void setAnopme(Integer anopme) {
		this.anopme = anopme;
	}

	/**
	 * Gets the cocpme.
	 *
	 * @return the cocpme
	 */
	public Integer getCocpme() {
		return this.cocpme;
	}

	/**
	 * Sets the cocpme.
	 *
	 * @param cocpme the new cocpme
	 */
	public void setCocpme(Integer cocpme) {
		this.cocpme = cocpme;
	}

	/**
	 * Gets the id ref.
	 *
	 * @return the id ref
	 */
	public Integer getIdRef() {
		return this.idRef;
	}

	/**
	 * Sets the id ref.
	 *
	 * @param idRef the new id ref
	 */
	public void setIdRef(Integer idRef) {
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
	 * Gets the mecpme.
	 *
	 * @return the mecpme
	 */
	public Integer getMecpme() {
		return this.mecpme;
	}

	/**
	 * Sets the mecpme.
	 *
	 * @param mecpme the new mecpme
	 */
	public void setMecpme(Integer mecpme) {
		this.mecpme = mecpme;
	}

	/**
	 * Gets the tpcpme.
	 *
	 * @return the tpcpme
	 */
	public String getTpcpme() {
		return this.tpcpme;
	}

	/**
	 * Sets the tpcpme.
	 *
	 * @param tpcpme the new tpcpme
	 */
	public void setTpcpme(String tpcpme) {
		this.tpcpme = tpcpme;
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
	 * Gets the cod error.
	 *
	 * @return the cod error
	 */
	public Integer getCodError() {
		return codError;
	}



	/**
	 * Gets the msg error.
	 *
	 * @return the msg error
	 */
	public String getMsgError() {
		return msgError;
	}



	/**
	 * Gets the num nex.
	 *
	 * @return the num nex
	 */
	public Integer getNumNex() {
		return numNex;
	}



	/**
	 * Sets the cod error.
	 *
	 * @param codError the new cod error
	 */
	public void setCodError(Integer codError) {
		this.codError = codError;
	}



	/**
	 * Sets the msg error.
	 *
	 * @param msgError the new msg error
	 */
	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}



	/**
	 * Sets the num nex.
	 *
	 * @param numNex the new num nex
	 */
	public void setNumNex(Integer numNex) {
		this.numNex = numNex;
	}
	
	

}