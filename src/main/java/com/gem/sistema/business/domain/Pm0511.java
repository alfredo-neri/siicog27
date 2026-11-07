package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.gem.sistema.annotation.IgnoredQuery;

import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PM0511 database table.
 * 
 */
@Entity
@NamedQuery(name="Pm0511.findAll", query="SELECT p FROM Pm0511 p")
public class Pm0511 extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The capturo. */
	private String capturo;

	/** The conse. */
	private String conse;

	/** The conta. */
	private Integer conta;

	/** The emergencia. */
	private String emergencia;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private int idsector;

	/** The observaciones. */
	private String observaciones;

	/** The tiempo. */
	private Integer tiempo;

	/** The trimestre. */
	private Integer trimestre;

	/** The userid. */
	@Column(name="USERID")
	private String userid;
	

	
	/** The b guardar. */
	@Transient
	@IgnoredQuery
	private boolean bGuardar;

	/**
	 * Instantiates a new pm 0511.
	 */
	public Pm0511() {
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
	 * Gets the conse.
	 *
	 * @return the conse
	 */
	public String getConse() {
		return this.conse;
	}

	/**
	 * Sets the conse.
	 *
	 * @param conse the new conse
	 */
	public void setConse(String conse) {
		this.conse = conse;
	}

	/**
	 * Gets the conta.
	 *
	 * @return the conta
	 */
	public Integer getConta() {
		return this.conta;
	}

	/**
	 * Sets the conta.
	 *
	 * @param conta the new conta
	 */
	public void setConta(Integer conta) {
		this.conta = conta;
	}

	/**
	 * Gets the emergencia.
	 *
	 * @return the emergencia
	 */
	public String getEmergencia() {
		return this.emergencia;
	}

	/**
	 * Sets the emergencia.
	 *
	 * @param emergencia the new emergencia
	 */
	public void setEmergencia(String emergencia) {
		this.emergencia = emergencia;
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
	 * Gets the observaciones.
	 *
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return this.observaciones;
	}

	/**
	 * Sets the observaciones.
	 *
	 * @param observaciones the new observaciones
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Gets the tiempo.
	 *
	 * @return the tiempo
	 */
	public Integer getTiempo() {
		return this.tiempo;
	}

	/**
	 * Sets the tiempo.
	 *
	 * @param tiempo the new tiempo
	 */
	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
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