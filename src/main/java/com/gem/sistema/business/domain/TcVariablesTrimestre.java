package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
//
//import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.CascadeType;

import java.math.BigDecimal;

/**
 * The persistent class for the TC_VARIABLES_TRIMESTRES database table.
 * 
 * @author Alfredo Neri
 *
 */
@Entity
@Table(name = "TC_VARIABLES_TRIMESTRES")
@NamedQuery(name = "TcVariablesTrimestre.findAll", query = "SELECT t FROM TcVariablesTrimestre t")
public class TcVariablesTrimestre extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal alcanzada;

	@OneToOne(targetEntity = TcPeriodo.class, fetch = FetchType.LAZY, orphanRemoval = false, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ID_PERIODO", nullable = false)
	private TcPeriodo periodo;

	private BigDecimal porcentaje;

	@Column(name = "PORCENTAJE_ALCANZADA")
	private BigDecimal porcentajeAlcanzada;

	private BigDecimal programada;

	public TcVariablesTrimestre() {
		this.alcanzada = BigDecimal.ZERO;
		this.porcentaje = BigDecimal.ZERO;
		this.programada = BigDecimal.ZERO;
		this.porcentajeAlcanzada = BigDecimal.ZERO;
	}

	public TcVariablesTrimestre(TcPeriodo periodo) {
		this.periodo = periodo;
		this.alcanzada = BigDecimal.ZERO;
		this.porcentaje = BigDecimal.ZERO;
		this.programada = BigDecimal.ZERO;
		this.porcentajeAlcanzada = BigDecimal.ZERO;
	}

	public BigDecimal getAlcanzada() {
		return this.alcanzada;
	}

	public void setAlcanzada(BigDecimal alcanzada) {
		this.alcanzada = alcanzada;
	}

	public TcPeriodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(TcPeriodo periodo) {
		this.periodo = periodo;
	}

	public BigDecimal getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	public BigDecimal getPorcentajeAlcanzada() {
		return this.porcentajeAlcanzada;
	}

	public void setPorcentajeAlcanzada(BigDecimal porcentajeAlcanzada) {
		this.porcentajeAlcanzada = porcentajeAlcanzada;
	}

	public BigDecimal getProgramada() {
		return this.programada;
	}

	public void setProgramada(BigDecimal programada) {
		this.programada = programada;
	}

}