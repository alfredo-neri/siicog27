package com.gem.sistema.business.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.gem.sistema.util.Constants;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TSUELDOS database table.
 * 
 */
@Entity
@Table(name = "TSUELDOS")
public class Tsueldo extends AbstractEntity {

	/** The aguinaldo. */
	private Double aguinaldo = Double.valueOf(Constants.ZERO);

	/** The aguinaldoeven. */
	private Double aguinaldoeven = Double.valueOf(Constants.ZERO);

	/** The capturo. */
	private String capturo;

	/** The compensacion. */
	private Double compensacion = Double.valueOf(Constants.ZERO);

	/** The cvepuesto. */
	private String cvepuesto;

	/** The dietas. */
	private Double dietas = Double.valueOf(Constants.ZERO);

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The gratificacion. */
	private Double gratificacion = Double.valueOf(Constants.ZERO);

	/** The nconfianza. */
	private Integer nconfianza = 0;

	/** The neventual. */
	private Integer neventual = 0;

	/** The nivel. */
	private String nivel = Constants.STRING_ZERO;

	/** The nompuesto. */
	private String nompuesto;

	/** The nsindicalizados. */
	private Integer nsindicalizados = 0;

	/** The opercepciones. */
	private Double opercepciones = Double.valueOf(Constants.ZERO);

	/** The pv. */
	private Double pv = Double.valueOf(Constants.ZERO);

	/** The sbase. */
	private Double sbase = Double.valueOf(Constants.ZERO);

	/** The sbaseeven. */
	private Double sbaseeven = Double.valueOf(Constants.ZERO);
	
	/** The id ref. */
	@Column(name = "ID_REF")
	private Long idRef;
	
	/** The id user. */
	@Column(name = "USERID")
	private String idUser;
	
	/** The sector id. */
	private Integer sectorId;
	
	/** The o cod error. */
	@Transient
	private Integer oCodError;
	
	/** The o msg error. */
	@Transient
	private String oMsgError;

	/**
	 * Instantiates a new tsueldo.
	 */
	public Tsueldo() {
	}

	/**
	 * Gets the aguinaldo.
	 *
	 * @return the aguinaldo
	 */
	public Double getAguinaldo() {
		return this.aguinaldo;
	}

	/**
	 * Sets the aguinaldo.
	 *
	 * @param aguinaldo the new aguinaldo
	 */
	public void setAguinaldo(Double aguinaldo) {
		this.aguinaldo = aguinaldo;
	}

	/**
	 * Gets the aguinaldoeven.
	 *
	 * @return the aguinaldoeven
	 */
	public Double getAguinaldoeven() {
		return this.aguinaldoeven;
	}

	/**
	 * Sets the aguinaldoeven.
	 *
	 * @param aguinaldoeven the new aguinaldoeven
	 */
	public void setAguinaldoeven(Double aguinaldoeven) {
		this.aguinaldoeven = aguinaldoeven;
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
	 * Gets the compensacion.
	 *
	 * @return the compensacion
	 */
	public Double getCompensacion() {
		return this.compensacion;
	}

	/**
	 * Sets the compensacion.
	 *
	 * @param compensacion the new compensacion
	 */
	public void setCompensacion(Double compensacion) {
		this.compensacion = compensacion;
	}

	/**
	 * Gets the cvepuesto.
	 *
	 * @return the cvepuesto
	 */
	public String getCvepuesto() {
		return this.cvepuesto;
	}

	/**
	 * Sets the cvepuesto.
	 *
	 * @param cvepuesto the new cvepuesto
	 */
	public void setCvepuesto(String cvepuesto) {
		this.cvepuesto = cvepuesto;
	}

	/**
	 * Gets the dietas.
	 *
	 * @return the dietas
	 */
	public Double getDietas() {
		return this.dietas;
	}

	/**
	 * Sets the dietas.
	 *
	 * @param dietas the new dietas
	 */
	public void setDietas(Double dietas) {
		this.dietas = dietas;
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
	 * Gets the gratificacion.
	 *
	 * @return the gratificacion
	 */
	public Double getGratificacion() {
		return this.gratificacion;
	}

	/**
	 * Sets the gratificacion.
	 *
	 * @param gratificacion the new gratificacion
	 */
	public void setGratificacion(Double gratificacion) {
		this.gratificacion = gratificacion;
	}

	/**
	 * Gets the nconfianza.
	 *
	 * @return the nconfianza
	 */
	public Integer getNconfianza() {
		return this.nconfianza;
	}

	/**
	 * Sets the nconfianza.
	 *
	 * @param nconfianza the new nconfianza
	 */
	public void setNconfianza(Integer nconfianza) {
		this.nconfianza = nconfianza;
	}

	/**
	 * Gets the neventual.
	 *
	 * @return the neventual
	 */
	public Integer getNeventual() {
		return this.neventual;
	}

	/**
	 * Sets the neventual.
	 *
	 * @param neventual the new neventual
	 */
	public void setNeventual(Integer neventual) {
		this.neventual = neventual;
	}

	/**
	 * Gets the nivel.
	 *
	 * @return the nivel
	 */
	public String getNivel() {
		return this.nivel;
	}

	/**
	 * Sets the nivel.
	 *
	 * @param nivel the new nivel
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	/**
	 * Gets the nompuesto.
	 *
	 * @return the nompuesto
	 */
	public String getNompuesto() {
		return this.nompuesto;
	}

	/**
	 * Sets the nompuesto.
	 *
	 * @param nompuesto the new nompuesto
	 */
	public void setNompuesto(String nompuesto) {
		this.nompuesto = nompuesto;
	}

	/**
	 * Gets the nsindicalizados.
	 *
	 * @return the nsindicalizados
	 */
	public Integer getNsindicalizados() {
		return this.nsindicalizados;
	}

	/**
	 * Sets the nsindicalizados.
	 *
	 * @param nsindicalizados the new nsindicalizados
	 */
	public void setNsindicalizados(Integer nsindicalizados) {
		this.nsindicalizados = nsindicalizados;
	}

	/**
	 * Gets the opercepciones.
	 *
	 * @return the opercepciones
	 */
	public Double getOpercepciones() {
		return this.opercepciones;
	}

	/**
	 * Sets the opercepciones.
	 *
	 * @param opercepciones the new opercepciones
	 */
	public void setOpercepciones(Double opercepciones) {
		this.opercepciones = opercepciones;
	}

	/**
	 * Gets the pv.
	 *
	 * @return the pv
	 */
	public Double getPv() {
		return this.pv;
	}

	/**
	 * Sets the pv.
	 *
	 * @param pv the new pv
	 */
	public void setPv(Double pv) {
		this.pv = pv;
	}

	/**
	 * Gets the sbase.
	 *
	 * @return the sbase
	 */
	public Double getSbase() {
		return this.sbase;
	}

	/**
	 * Sets the sbase.
	 *
	 * @param sbase the new sbase
	 */
	public void setSbase(Double sbase) {
		this.sbase = sbase;
	}

	/**
	 * Gets the sbaseeven.
	 *
	 * @return the sbaseeven
	 */
	public Double getSbaseeven() {
		return this.sbaseeven;
	}

	/**
	 * Sets the sbaseeven.
	 *
	 * @param sbaseeven the new sbaseeven
	 */
	public void setSbaseeven(Double sbaseeven) {
		this.sbaseeven = sbaseeven;
	}

	/**
	 * Gets the id ref.
	 *
	 * @return the id ref
	 */
	public Long getIdRef() {
		return idRef;
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
	 * Gets the id user.
	 *
	 * @return the id user
	 */
	public String getIdUser() {
		return idUser;
	}

	/**
	 * Sets the id user.
	 *
	 * @param idUser the new id user
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	/**
	 * Gets the o cod error.
	 *
	 * @return the o cod error
	 */
	public Integer getoCodError() {
		return oCodError;
	}

	/**
	 * Sets the o cod error.
	 *
	 * @param oCodError the new o cod error
	 */
	public void setoCodError(Integer oCodError) {
		this.oCodError = oCodError;
	}

	/**
	 * Gets the o msg error.
	 *
	 * @return the o msg error
	 */
	public String getoMsgError() {
		return oMsgError;
	}

	/**
	 * Sets the o msg error.
	 *
	 * @param oMsgError the new o msg error
	 */
	public void setoMsgError(String oMsgError) {
		this.oMsgError = oMsgError;
	}

	/**
	 * Gets the sector id.
	 *
	 * @return the sector id
	 */
	public Integer getSectorId() {
		return sectorId;
	}

	/**
	 * Sets the sector id.
	 *
	 * @param sectorId the new sector id
	 */
	public void setSectorId(Integer sectorId) {
		this.sectorId = sectorId;
	}
	
	
	
}