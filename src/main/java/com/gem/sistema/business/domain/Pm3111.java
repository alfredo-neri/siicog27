package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM3111 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm3111.findAll", query="SELECT p FROM Pm3111 p")
public class Pm3111 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	/** The anual. */
	private Integer anual;

	/** The capturo. */
	private String capturo;

	/** The cpci. */
	private Integer cpci;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private int idsector;

	/** The ntloc. */
	private Integer ntloc;

	/** The obscpci. */
	private String obscpci;

	/** The obsntloc. */
	private String obsntloc;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/**
	 * Instantiates a new pm 3111.
	 */
	public Pm3111() {
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
	 * Gets the cpci.
	 *
	 * @return the cpci
	 */
	public Integer getCpci() {
		return this.cpci;
	}

	/**
	 * Sets the cpci.
	 *
	 * @param cpci the new cpci
	 */
	public void setCpci(Integer cpci) {
		this.cpci = cpci;
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
	 * Gets the ntloc.
	 *
	 * @return the ntloc
	 */
	public Integer getNtloc() {
		return this.ntloc;
	}

	/**
	 * Sets the ntloc.
	 *
	 * @param ntloc the new ntloc
	 */
	public void setNtloc(Integer ntloc) {
		this.ntloc = ntloc;
	}

	/**
	 * Gets the obscpci.
	 *
	 * @return the obscpci
	 */
	public String getObscpci() {
		return this.obscpci;
	}

	/**
	 * Sets the obscpci.
	 *
	 * @param obscpci the new obscpci
	 */
	public void setObscpci(String obscpci) {
		this.obscpci = obscpci;
	}

	/**
	 * Gets the obsntloc.
	 *
	 * @return the obsntloc
	 */
	public String getObsntloc() {
		return this.obsntloc;
	}

	/**
	 * Sets the obsntloc.
	 *
	 * @param obsntloc the new obsntloc
	 */
	public void setObsntloc(String obsntloc) {
		this.obsntloc = obsntloc;
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