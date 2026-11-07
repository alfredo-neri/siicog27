package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

/**
 * The persistent class for the TC_CONTROL_PRESUPUESTARIO database table.
 * 
 */
@Entity
@Table(name = "TC_CONTROL_PRESUPUESTARIO")
@NamedQuery(name = "TcControlPresupuestario.findAll", query = "SELECT t FROM TcControlPresupuestario t")
public class TcControlPresupuestario extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "AUTORIZADO_ANUAL")
	private BigDecimal autorizadoAnual;

	private String clave;

	private String concepto;
	
	@Transient
	private BigDecimal disponible;

	public TcControlPresupuestario() {
	}

	public BigDecimal getAutorizadoAnual() {
		return this.autorizadoAnual;
	}

	public void setAutorizadoAnual(BigDecimal autorizadoAnual) {
		this.autorizadoAnual = autorizadoAnual;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getConcepto() {
		return this.concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public BigDecimal getDisponible() {
		return disponible;
	}

	public void setDisponible(BigDecimal disponible) {
		this.disponible = disponible;
	}

}