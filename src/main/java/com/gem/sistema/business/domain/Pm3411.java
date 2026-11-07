package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM3411 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm3411.findAll", query="SELECT p FROM Pm3411 p")
public class Pm3411 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The acuspcam. */
	private Integer acuspcam;

	/** The capturo. */
	private String capturo;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The id ref. */
	@Column(name="ID_REF")
	private long idRef;

	/** The idsector. */
	private int idsector;

	/** The obsspcam. */
	private String obsspcam;

	/** The obstspaem. */
	private String obstspaem;

	/** The semestral. */
	private Integer semestral = 0;

	/** The spcam. */
	private Integer spcam;

	/** The tspaem. */
	private Integer tspaem;

	/** The userid. */
	@Column(name="USERID")
	private String userid;
	
	/** The b guardar. */
	@Transient
	private boolean bGuardar;
	
	/**
	 * Instantiates a new pm 3411.
	 */
	public Pm3411() {
	}


	/**
	 * Gets the acuspcam.
	 *
	 * @return the acuspcam
	 */
	public Integer getAcuspcam() {
		return this.acuspcam;
	}

	/**
	 * Sets the acuspcam.
	 *
	 * @param acuspcam the new acuspcam
	 */
	public void setAcuspcam(Integer acuspcam) {
		this.acuspcam = acuspcam;
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
	 * Gets the obsspcam.
	 *
	 * @return the obsspcam
	 */
	public String getObsspcam() {
		return this.obsspcam;
	}

	/**
	 * Sets the obsspcam.
	 *
	 * @param obsspcam the new obsspcam
	 */
	public void setObsspcam(String obsspcam) {
		this.obsspcam = obsspcam;
	}

	/**
	 * Gets the obstspaem.
	 *
	 * @return the obstspaem
	 */
	public String getObstspaem() {
		return this.obstspaem;
	}

	/**
	 * Sets the obstspaem.
	 *
	 * @param obstspaem the new obstspaem
	 */
	public void setObstspaem(String obstspaem) {
		this.obstspaem = obstspaem;
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
	 * Gets the spcam.
	 *
	 * @return the spcam
	 */
	public Integer getSpcam() {
		return this.spcam;
	}

	/**
	 * Sets the spcam.
	 *
	 * @param spcam the new spcam
	 */
	public void setSpcam(Integer spcam) {
		this.spcam = spcam;
	}

	/**
	 * Gets the tspaem.
	 *
	 * @return the tspaem
	 */
	public Integer getTspaem() {
		return this.tspaem;
	}

	/**
	 * Sets the tspaem.
	 *
	 * @param tspaem the new tspaem
	 */
	public void setTspaem(Integer tspaem) {
		this.tspaem = tspaem;
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