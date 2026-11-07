package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the TC_CONTRATOS_REVISON database table.
 * 
 */
@Entity
@Table(name = "TC_CONTRATOS_REVISON")
@NamedQuery(name = "TcContratosRevison.findAll", query = "SELECT t FROM TcContratosRevison t")
public class TcContratosRevison extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Boolean correcto;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CONTRATO")
	private TcContrato contrato;

	private String observaciones;

	public TcContratosRevison() {
	}

	@PrePersist
	public void prepersist() {
		this.fecha = new Date();
	}

	public Boolean getCorrecto() {
		return correcto;
	}

	public void setCorrecto(Boolean correcto) {
		this.correcto = correcto;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public TcContrato getContrato() {
		return contrato;
	}

	public void setContrato(TcContrato contrato) {
		this.contrato = contrato;
	}

}