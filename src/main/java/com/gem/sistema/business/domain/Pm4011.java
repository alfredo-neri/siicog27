package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.gem.sistema.annotation.IgnoredQuery;

import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM4011 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm4011.findAll", query="SELECT p FROM Pm4011 p")
public class Pm4011 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The anual. */
	private Integer anual;

	/** The capturo. */
	private String capturo;

	/** The clvreq. */
	private Integer clvreq = 0;

	/** The correo. */
	private String correo;

	/** The cumple. */
	private String cumple = "1";

	/** The evidencia. */
	private String evidencia;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private int idsector;

	/** The obs. */
	private String obs;

	/** The requer. */
	private String requer;

	/** The userid. */
	@Column(name="USERID")
	private String userid;
	
	/** The b guardar. */
	@Transient
	@IgnoredQuery
	private Boolean bGuardar;

	/**
	 * Instantiates a new pm 4011.
	 */
	public Pm4011() {
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
	 * Gets the clvreq.
	 *
	 * @return the clvreq
	 */
	public Integer getClvreq() {
		return this.clvreq;
	}

	/**
	 * Sets the clvreq.
	 *
	 * @param clvreq the new clvreq
	 */
	public void setClvreq(Integer clvreq) {
		this.clvreq = clvreq;
	}

	/**
	 * Gets the correo.
	 *
	 * @return the correo
	 */
	public String getCorreo() {
		return this.correo;
	}

	/**
	 * Sets the correo.
	 *
	 * @param correo the new correo
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Gets the cumple.
	 *
	 * @return the cumple
	 */
	public String getCumple() {
		return this.cumple;
	}

	/**
	 * Sets the cumple.
	 *
	 * @param cumple the new cumple
	 */
	public void setCumple(String cumple) {
		this.cumple = cumple;
	}

	/**
	 * Gets the evidencia.
	 *
	 * @return the evidencia
	 */
	public String getEvidencia() {
		return this.evidencia;
	}

	/**
	 * Sets the evidencia.
	 *
	 * @param evidencia the new evidencia
	 */
	public void setEvidencia(String evidencia) {
		this.evidencia = evidencia;
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
	 * Gets the obs.
	 *
	 * @return the obs
	 */
	public String getObs() {
		return this.obs;
	}

	/**
	 * Sets the obs.
	 *
	 * @param obs the new obs
	 */
	public void setObs(String obs) {
		this.obs = obs;
	}

	/**
	 * Gets the requer.
	 *
	 * @return the requer
	 */
	public String getRequer() {
		return this.requer;
	}

	/**
	 * Sets the requer.
	 *
	 * @param requer the new requer
	 */
	public void setRequer(String requer) {
		this.requer = requer;
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
	 * Gets the b guardar.
	 *
	 * @return the b guardar
	 */
	public Boolean getbGuardar() {
		return bGuardar;
	}

	/**
	 * Sets the b guardar.
	 *
	 * @param bGuardar the new b guardar
	 */
	public void setbGuardar(Boolean bGuardar) {
		this.bGuardar = bGuardar;
	}
	
	

}