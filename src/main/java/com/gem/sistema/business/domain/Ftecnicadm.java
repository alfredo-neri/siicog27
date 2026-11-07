package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the FTECNICADM database table.
 * 
 */
@Entity
@NamedQuery(name = "Ftecnicadm.findAll", query = "SELECT f FROM Ftecnicadm f")
public class Ftecnicadm extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String clvdep;

	private String clvfin;

	private String clvfun;

	private String cveind;

	private String cvetemas;

	private String descanual;

	private String descfac;

	private String dimension;

	private String elaboro;

	private BigDecimal factor;

	@Temporal(TemporalType.DATE)
	private Date feccap;

	private String formula;

	private String frecuencia;

	@Column(name = "ID_REF")
	private Long idRef;

	// private int idsector;

	private String interpretacion;

	private String medios;

	private BigDecimal metanuale;

	private String metasact;

	private String nomind;

	private String nope;

	private String objetivo;

	private String pe;

	private String tema;

	private String tipo;

	private BigDecimal trim1e;

	private BigDecimal trim2e;

	private BigDecimal trim3e;

	private BigDecimal trim4e;

	@Column(name = "USERID")
	private String userid;

	private String usuario;

	private String valido;

	@Column(name = "IDSECTOR")
	private Integer idSector;

	@Column(name = "LINEA_BASE")
	private String lineaBase;

	public Ftecnicadm() {
	}

	public String getClvdep() {
		return this.clvdep;
	}

	public void setClvdep(String clvdep) {
		this.clvdep = clvdep;
	}

	public String getClvfin() {
		return this.clvfin;
	}

	public void setClvfin(String clvfin) {
		this.clvfin = clvfin;
	}

	public String getClvfun() {
		return this.clvfun;
	}

	public void setClvfun(String clvfun) {
		this.clvfun = clvfun;
	}

	public String getCveind() {
		return this.cveind;
	}

	public void setCveind(String cveind) {
		this.cveind = cveind;
	}

	public String getCvetemas() {
		return this.cvetemas;
	}

	public void setCvetemas(String cvetemas) {
		this.cvetemas = cvetemas;
	}

	public String getDescanual() {
		return this.descanual;
	}

	public void setDescanual(String descanual) {
		this.descanual = descanual;
	}

	public String getDescfac() {
		return this.descfac;
	}

	public void setDescfac(String descfac) {
		this.descfac = descfac;
	}

	public String getDimension() {
		return this.dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getElaboro() {
		return this.elaboro;
	}

	public void setElaboro(String elaboro) {
		this.elaboro = elaboro;
	}

	public BigDecimal getFactor() {
		return this.factor;
	}

	public void setFactor(BigDecimal factor) {
		this.factor = factor;
	}

	public Date getFeccap() {
		return this.feccap;
	}

	public void setFeccap(Date feccap) {
		this.feccap = feccap;
	}

	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getFrecuencia() {
		return this.frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	public Long getIdRef() {
		return this.idRef;
	}

	public void setIdRef(Long idRef) {
		this.idRef = idRef;
	}

	/*
	 * public int getIdsector() { return this.idsector; }
	 * 
	 * public void setIdsector(int idsector) { this.idsector = idsector; }
	 */
	public String getInterpretacion() {
		return this.interpretacion;
	}

	public void setInterpretacion(String interpretacion) {
		this.interpretacion = interpretacion;
	}

	public String getMedios() {
		return this.medios;
	}

	public void setMedios(String medios) {
		this.medios = medios;
	}

	public BigDecimal getMetanuale() {
		return this.metanuale;
	}

	public void setMetanuale(BigDecimal metanuale) {
		this.metanuale = metanuale;
	}

	public String getMetasact() {
		return this.metasact;
	}

	public void setMetasact(String metasact) {
		this.metasact = metasact;
	}

	public String getNomind() {
		return this.nomind;
	}

	public void setNomind(String nomind) {
		this.nomind = nomind;
	}

	public String getNope() {
		return this.nope;
	}

	public void setNope(String nope) {
		this.nope = nope;
	}

	public String getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getPe() {
		return this.pe;
	}

	public void setPe(String pe) {
		this.pe = pe;
	}

	public String getTema() {
		return this.tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getTrim1e() {
		return this.trim1e;
	}

	public void setTrim1e(BigDecimal trim1e) {
		this.trim1e = trim1e;
	}

	public BigDecimal getTrim2e() {
		return this.trim2e;
	}

	public void setTrim2e(BigDecimal trim2e) {
		this.trim2e = trim2e;
	}

	public BigDecimal getTrim3e() {
		return this.trim3e;
	}

	public void setTrim3e(BigDecimal trim3e) {
		this.trim3e = trim3e;
	}

	public BigDecimal getTrim4e() {
		return this.trim4e;
	}

	public void setTrim4e(BigDecimal trim4e) {
		this.trim4e = trim4e;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getValido() {
		return this.valido;
	}

	public void setValido(String valido) {
		this.valido = valido;
	}

	public Integer getIdSector() {
		return idSector;
	}

	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

	public String getLineaBase() {
		return lineaBase;
	}

	public void setLineaBase(String lineaBase) {
		this.lineaBase = lineaBase;
	}

}