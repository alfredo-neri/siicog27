package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM2511 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm2511.findAll", query="SELECT p FROM Pm2511 p")
public class Pm2511 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The acumagua. */
	private BigDecimal acumagua;

	/** The agua. */
	private BigDecimal agua;

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

	/** The obsagua. */
	private String obsagua;

	/** The obspob. */
	private String obspob;

	/** The poblacion. */
	private Integer poblacion;

	/** The trimestre. */
	private Integer trimestre;

	/** The userid. */
	@Column(name="USERID")
	private String userid;
	
	/** The b validar. */
	@Transient
	private boolean bValidar; 

	/**
	 * Instantiates a new pm 2511.
	 */
	public Pm2511() {
	}

	/**
	 * Gets the acumagua.
	 *
	 * @return the acumagua
	 */
	public BigDecimal getAcumagua() {
		return this.acumagua;
	}

	/**
	 * Sets the acumagua.
	 *
	 * @param acumagua the new acumagua
	 */
	public void setAcumagua(BigDecimal acumagua) {
		this.acumagua = acumagua;
	}

	/**
	 * Gets the agua.
	 *
	 * @return the agua
	 */
	public BigDecimal getAgua() {
		return this.agua;
	}

	/**
	 * Sets the agua.
	 *
	 * @param agua the new agua
	 */
	public void setAgua(BigDecimal agua) {
		this.agua = agua;
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
	 * Gets the obsagua.
	 *
	 * @return the obsagua
	 */
	public String getObsagua() {
		return this.obsagua;
	}

	/**
	 * Sets the obsagua.
	 *
	 * @param obsagua the new obsagua
	 */
	public void setObsagua(String obsagua) {
		this.obsagua = obsagua;
	}

	/**
	 * Gets the obspob.
	 *
	 * @return the obspob
	 */
	public String getObspob() {
		return this.obspob;
	}

	/**
	 * Sets the obspob.
	 *
	 * @param obspob the new obspob
	 */
	public void setObspob(String obspob) {
		this.obspob = obspob;
	}

	/**
	 * Gets the poblacion.
	 *
	 * @return the poblacion
	 */
	public Integer getPoblacion() {
		return this.poblacion;
	}

	/**
	 * Sets the poblacion.
	 *
	 * @param poblacion the new poblacion
	 */
	public void setPoblacion(Integer poblacion) {
		this.poblacion = poblacion;
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
	 * Checks if is b validar.
	 *
	 * @return true, if is b validar
	 */
	public boolean isbValidar() {
		return bValidar;
	}

	/**
	 * Sets the b validar.
	 *
	 * @param bValidar the new b validar
	 */
	public void setbValidar(boolean bValidar) {
		this.bValidar = bValidar;
	}
	
	

}