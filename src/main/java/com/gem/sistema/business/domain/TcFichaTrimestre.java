package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * The persistent class for the TC_FICHA_TRIMESTRES database table.
 * 
 * @author Alfredo Neri
 *
 */
@Entity
@Table(name = "TC_FICHA_TRIMESTRES")
@NamedQuery(name = "TcFichaTrimestre.findAll", query = "SELECT t FROM TcFichaTrimestre t")
public class TcFichaTrimestre extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal alcanzada;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "ID_PERIODO", nullable = false)
	private TcPeriodo periodo;

	@Column(name = "META_ANUAL")
	private BigDecimal metaAnual;

	private BigDecimal porcentaje;

	@Column(name = "PORCENTAJE_ALCANZADA")
	private BigDecimal porcentajeAlcanzada;

	private BigDecimal programada;

	private String semaforo;

	@Column(name = "SEMAFORO_ACUMULADO")
	private String semaforoAcumulado;

	public TcFichaTrimestre() {
		this.alcanzada = BigDecimal.ZERO;
		this.metaAnual = BigDecimal.ZERO;
		this.programada = BigDecimal.ZERO;
		this.porcentaje = BigDecimal.ZERO;
		this.semaforo = StringUtils.EMPTY;
		this.semaforoAcumulado = StringUtils.EMPTY;
		this.porcentajeAlcanzada = BigDecimal.ZERO;
	}

	public TcFichaTrimestre(TcPeriodo periodo) {
		this.periodo = periodo;
		this.alcanzada = BigDecimal.ZERO;
		this.metaAnual = BigDecimal.ZERO;
		this.programada = BigDecimal.ZERO;
		this.porcentaje = BigDecimal.ZERO;
		this.semaforo = StringUtils.EMPTY;
		this.semaforoAcumulado = StringUtils.EMPTY;
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

	public BigDecimal getMetaAnual() {
		return this.metaAnual;
	}

	public void setMetaAnual(BigDecimal metaAnual) {
		this.metaAnual = metaAnual;
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

	public String getSemaforo() {
		return this.semaforo;
	}

	public void setSemaforo(String semaforo) {
		this.semaforo = semaforo;
	}

	public String getSemaforoAcumulado() {
		return this.semaforoAcumulado;
	}

	public void setSemaforoAcumulado(String semaforoAcumulado) {
		this.semaforoAcumulado = semaforoAcumulado;
	}

}