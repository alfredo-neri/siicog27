package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the EAID database table.
 * 
 */
@Entity
@NamedQuery(name = "Eaid.findAll", query = "SELECT e FROM Eaid e")
public class Eaid extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "AMPLIACION_REDUCCION")
	private BigDecimal ampliacionReduccion = BigDecimal.ZERO;

	private String capturo;

	private String concepto = StringUtils.EMPTY;

	private Integer consecutivo;

	private BigDecimal devengado = BigDecimal.ZERO;

	private BigDecimal diferencia = BigDecimal.ZERO;

	private BigDecimal estimado = BigDecimal.ZERO;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_CPTURA")
	private Date fechaCptura;

	@Column(name = "ID_ANIO")
	private long idAnio;

	@Column(name = "ID_REF")
	private Long idRef;

	@Column(name = "ID_SECTOR")
	private Integer idSector;

	private BigDecimal modificado = BigDecimal.ZERO;

	private BigDecimal recaudado = BigDecimal.ZERO;

	private Integer trimestre;

	public Eaid() {
	}

	public BigDecimal getAmpliacionReduccion() {
		return this.ampliacionReduccion;
	}

	public void setAmpliacionReduccion(BigDecimal ampliacionReduccion) {
		this.ampliacionReduccion = ampliacionReduccion;
	}

	public String getCapturo() {
		return this.capturo;
	}

	public void setCapturo(String capturo) {
		this.capturo = capturo;
	}

	public String getConcepto() {
		return this.concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Integer getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}

	public BigDecimal getDevengado() {
		return this.devengado;
	}

	public void setDevengado(BigDecimal devengado) {
		this.devengado = devengado;
	}

	public BigDecimal getDiferencia() {
		return this.diferencia;
	}

	public void setDiferencia(BigDecimal diferencia) {
		this.diferencia = diferencia;
	}

	public BigDecimal getEstimado() {
		return this.estimado;
	}

	public void setEstimado(BigDecimal estimado) {
		this.estimado = estimado;
	}

	public Date getFechaCptura() {
		return this.fechaCptura;
	}

	public void setFechaCptura(Date fechaCptura) {
		this.fechaCptura = fechaCptura;
	}

	public long getIdAnio() {
		return this.idAnio;
	}

	public void setIdAnio(long idAnio) {
		this.idAnio = idAnio;
	}

	public Long getIdRef() {
		return this.idRef;
	}

	public void setIdRef(Long idRef) {
		this.idRef = idRef;
	}

	public Integer getIdSector() {
		return this.idSector;
	}

	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

	public BigDecimal getModificado() {
		return this.modificado;
	}

	public void setModificado(BigDecimal modificado) {
		this.modificado = modificado;
	}

	public BigDecimal getRecaudado() {
		return this.recaudado;
	}

	public void setRecaudado(BigDecimal recaudado) {
		this.recaudado = recaudado;
	}

	public Integer getTrimestre() {
		return this.trimestre;
	}

	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
	}

}