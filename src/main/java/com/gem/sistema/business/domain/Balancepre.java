package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.gem.sistema.annotation.IgnoredQuery;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BALANCEPRE database table.
 * 
 */
@Entity
@NamedQuery(name="Balancepre.findAll", query="SELECT b FROM Balancepre b")
public class Balancepre extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String capturo;

	private String concepto;

	private Long consecutivo;

	private BigDecimal devengado;

	private BigDecimal ea;

	@Temporal(TemporalType.DATE)
	private Date feccap;

	@Column(name="ID_ANIO")
	private Long idAnio;

	@Column(name="ID_REF")
	private Long idRef;

	@Column(name="ID_SECTOR")
	private Integer idSector;

	private BigDecimal rp;

	private Integer trimestre;

	@Transient
	@IgnoredQuery
	private boolean bGuardar;
	
	public Balancepre() {
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

	public Long getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public BigDecimal getDevengado() {
		return this.devengado;
	}

	public void setDevengado(BigDecimal devengado) {
		this.devengado = devengado;
	}

	public BigDecimal getEa() {
		return this.ea;
	}

	public void setEa(BigDecimal ea) {
		this.ea = ea;
	}

	public Date getFeccap() {
		return this.feccap;
	}

	public void setFeccap(Date feccap) {
		this.feccap = feccap;
	}

	public Long getIdAnio() {
		return this.idAnio;
	}

	public void setIdAnio(Long idAnio) {
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

	public BigDecimal getRp() {
		return this.rp;
	}

	public void setRp(BigDecimal rp) {
		this.rp = rp;
	}

	public Integer getTrimestre() {
		return this.trimestre;
	}

	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
	}

	public boolean isbGuardar() {
		return bGuardar;
	}

	public void setbGuardar(boolean bGuardar) {
		this.bGuardar = bGuardar;
	}

}