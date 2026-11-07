package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM0411 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm0411.findAll", query="SELECT p FROM Pm0411 p")
public class Pm0411 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final Long serialVersionUID = 1L;

	/** The acudc. */
	private Integer acudc;

	/** The acudi. */
	private Integer acudi;

	/** The capturo. */
	private String capturo;

	/** The dc. */
	private Integer dc;

	/** The di. */
	private Integer di;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private Integer idsector;

	/** The obsdc. */
	private String obsdc;

	/** The obsdi. */
	private String obsdi;

	/** The trimestre. */
	private Integer trimestre;

	/** The userid. */
	@Column(name="USERID")
	private String userid;
	
	/** The g buardar. */
	@Transient
	private boolean gBuardar;

	/**
	 * Instantiates a new pm 0411.
	 */
	public Pm0411() {
	}

	/**
	 * Gets the acudc.
	 *
	 * @return the acudc
	 */
	public Integer getAcudc() {
		return this.acudc;
	}

	/**
	 * Sets the acudc.
	 *
	 * @param acudc the new acudc
	 */
	public void setAcudc(Integer acudc) {
		this.acudc = acudc;
	}

	/**
	 * Gets the acudi.
	 *
	 * @return the acudi
	 */
	public Integer getAcudi() {
		return this.acudi;
	}

	/**
	 * Sets the acudi.
	 *
	 * @param acudi the new acudi
	 */
	public void setAcudi(Integer acudi) {
		this.acudi = acudi;
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
	 * Gets the dc.
	 *
	 * @return the dc
	 */
	public Integer getDc() {
		return this.dc;
	}

	/**
	 * Sets the dc.
	 *
	 * @param dc the new dc
	 */
	public void setDc(Integer dc) {
		this.dc = dc;
	}

	/**
	 * Gets the di.
	 *
	 * @return the di
	 */
	public Integer getDi() {
		return this.di;
	}

	/**
	 * Sets the di.
	 *
	 * @param di the new di
	 */
	public void setDi(Integer di) {
		this.di = di;
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
	 * Gets the obsdc.
	 *
	 * @return the obsdc
	 */
	public String getObsdc() {
		return this.obsdc;
	}

	/**
	 * Sets the obsdc.
	 *
	 * @param obsdc the new obsdc
	 */
	public void setObsdc(String obsdc) {
		this.obsdc = obsdc;
	}

	/**
	 * Gets the obsdi.
	 *
	 * @return the obsdi
	 */
	public String getObsdi() {
		return this.obsdi;
	}

	/**
	 * Sets the obsdi.
	 *
	 * @param obsdi the new obsdi
	 */
	public void setObsdi(String obsdi) {
		this.obsdi = obsdi;
	}

	/**
	 * Gets the trimestre.
	 *
	 * @return the trimestre
	 */
	public Integer getTrimestre() {
		return this.trimestre;
	}

	/**
	 * Sets the trimestre.
	 *
	 * @param trimestre the new trimestre
	 */
	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
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
	 * Checks if is g buardar.
	 *
	 * @return true, if is g buardar
	 */
	public boolean isgBuardar() {
		return gBuardar;
	}

	/**
	 * Sets the g buardar.
	 *
	 * @param gBuardar the new g buardar
	 */
	public void setgBuardar(boolean gBuardar) {
		this.gBuardar = gBuardar;
	}
	
	

}