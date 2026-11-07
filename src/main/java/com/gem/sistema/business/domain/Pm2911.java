package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM2911 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm2911.findAll", query="SELECT p FROM Pm2911 p")
public class Pm2911 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	

	/** The acumgas. */
	private BigDecimal acumgas;

	/** The acumtot. */
	private BigDecimal acumtot;

	/** The capturo. */
	private String capturo;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The gastot. */
	private BigDecimal gastot;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private Integer idsector;

	/** The obsgas. */
	private String obsgas;

	/** The obstot. */
	private String obstot;

	/** The semes. */
	private Integer semes;

	/** The totton. */
	private BigDecimal totton;

	/** The userid. */
	@Column(name="USERID")
	private String userid;
	
	/** The b guardar. */
	@Transient
	private boolean bGuardar;

	/**
	 * Instantiates a new pm 2911.
	 */
	public Pm2911() {
	}

	

	/**
	 * Gets the acumgas.
	 *
	 * @return the acumgas
	 */
	public BigDecimal getAcumgas() {
		return this.acumgas;
	}

	/**
	 * Sets the acumgas.
	 *
	 * @param acumgas the new acumgas
	 */
	public void setAcumgas(BigDecimal acumgas) {
		this.acumgas = acumgas;
	}

	/**
	 * Gets the acumtot.
	 *
	 * @return the acumtot
	 */
	public BigDecimal getAcumtot() {
		return this.acumtot;
	}

	/**
	 * Sets the acumtot.
	 *
	 * @param acumtot the new acumtot
	 */
	public void setAcumtot(BigDecimal acumtot) {
		this.acumtot = acumtot;
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
	 * Gets the gastot.
	 *
	 * @return the gastot
	 */
	public BigDecimal getGastot() {
		return this.gastot;
	}

	/**
	 * Sets the gastot.
	 *
	 * @param gastot the new gastot
	 */
	public void setGastot(BigDecimal gastot) {
		this.gastot = gastot;
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
	 * Gets the obsgas.
	 *
	 * @return the obsgas
	 */
	public String getObsgas() {
		return this.obsgas;
	}

	/**
	 * Sets the obsgas.
	 *
	 * @param obsgas the new obsgas
	 */
	public void setObsgas(String obsgas) {
		this.obsgas = obsgas;
	}

	/**
	 * Gets the obstot.
	 *
	 * @return the obstot
	 */
	public String getObstot() {
		return this.obstot;
	}

	/**
	 * Sets the obstot.
	 *
	 * @param obstot the new obstot
	 */
	public void setObstot(String obstot) {
		this.obstot = obstot;
	}

	/**
	 * Gets the semes.
	 *
	 * @return the semes
	 */
	public Integer getSemes() {
		return this.semes;
	}

	/**
	 * Sets the semes.
	 *
	 * @param semes the new semes
	 */
	public void setSemes(Integer semes) {
		this.semes = semes;
	}

	/**
	 * Gets the totton.
	 *
	 * @return the totton
	 */
	public BigDecimal getTotton() {
		return this.totton;
	}

	/**
	 * Sets the totton.
	 *
	 * @param totton the new totton
	 */
	public void setTotton(BigDecimal totton) {
		this.totton = totton;
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