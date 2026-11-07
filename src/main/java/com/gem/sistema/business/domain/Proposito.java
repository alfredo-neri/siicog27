package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the PROPOSITO database table.
 * 
 */
@Entity
@NamedQuery(name = "Proposito.findAll", query = "SELECT p FROM Proposito p")
public class Proposito extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String clvdepg;

	private String cvefin;

	private String cveind;

	private Long cvepro;

	private String cveprog;

	private String cvetemas;

	@Temporal(TemporalType.DATE)
	private Date feccap;

	private String formula;

	private String frecuencia;

	@Column(name = "ID_REF")
	private Long idRef;

	private String medio;

	private String nombre;

	private String objetivo;

	private int sectorid;

	private String supuesto;

	@Column(name = "\"USERID\"")
	private String userid;

	private String usuario;

	public Proposito() {
	}

	public String getClvdepg() {
		return this.clvdepg;
	}

	public void setClvdepg(String clvdepg) {
		this.clvdepg = clvdepg;
	}

	public String getCvefin() {
		return this.cvefin;
	}

	public void setCvefin(String cvefin) {
		this.cvefin = cvefin;
	}

	public String getCveind() {
		return this.cveind;
	}

	public void setCveind(String cveind) {
		this.cveind = cveind;
	}

	public Long getCvepro() {
		return this.cvepro;
	}

	public void setCvepro(Long cvepro) {
		this.cvepro = cvepro;
	}

	public String getCveprog() {
		return this.cveprog;
	}

	public void setCveprog(String cveprog) {
		this.cveprog = cveprog;
	}

	public String getCvetemas() {
		return this.cvetemas;
	}

	public void setCvetemas(String cvetemas) {
		this.cvetemas = cvetemas;
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

	public String getMedio() {
		return this.medio;
	}

	public void setMedio(String medio) {
		this.medio = medio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public int getSectorid() {
		return this.sectorid;
	}

	public void setSectorid(int sectorid) {
		this.sectorid = sectorid;
	}

	public String getSupuesto() {
		return this.supuesto;
	}

	public void setSupuesto(String supuesto) {
		this.supuesto = supuesto;
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

}