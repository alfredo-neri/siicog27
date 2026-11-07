package com.gem.sistema.business.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the TW_OBRA_PAGOS database table.
 * 
 */
@Entity
@Table(name = "TW_OBRA_PAGOS")
@NamedQuery(name = "TwObraPago.findAll", query = "SELECT t FROM TwObraPago t")
public class TwObraPago extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "AUTORIZACION_PAGO")
	private Integer autorizacionPago;

	private String estimacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_PAGO")
	private Date fechaPago;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_OBRA")
	private TwObra obra;

	@Column(name = "IMPORTE_AFECTACION_PRESUPUESTAL")
	private BigDecimal importeAfectacionPresupuestal;

	@Column(name = "IMPORTE_FACTURA")
	private BigDecimal importeFactura;

	@Column(name = "NO_FACTURA")
	private String noFactura;

	private String observaciones;

	@ManyToOne
	@JoinColumn(name = "ID_STATUS_PAGO")
	private TcStatusPago statusPago;

	public TwObraPago() {
	}

	public Integer getAutorizacionPago() {
		return this.autorizacionPago;
	}

	public void setAutorizacionPago(Integer autorizacionPago) {
		this.autorizacionPago = autorizacionPago;
	}

	public String getEstimacion() {
		return this.estimacion;
	}

	public void setEstimacion(String estimacion) {
		this.estimacion = estimacion;
	}

	public Date getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public BigDecimal getImporteAfectacionPresupuestal() {
		return this.importeAfectacionPresupuestal;
	}

	public void setImporteAfectacionPresupuestal(BigDecimal importeAfectacionPresupuestal) {
		this.importeAfectacionPresupuestal = importeAfectacionPresupuestal;
	}

	public BigDecimal getImporteFactura() {
		return this.importeFactura;
	}

	public void setImporteFactura(BigDecimal importeFactura) {
		this.importeFactura = importeFactura;
	}

	public String getNoFactura() {
		return this.noFactura;
	}

	public void setNoFactura(String noFactura) {
		this.noFactura = noFactura;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public TwObra getObra() {
		return obra;
	}

	public void setObra(TwObra obra) {
		this.obra = obra;
	}

	public TcStatusPago getStatusPago() {
		return statusPago;
	}

	public void setStatusPago(TcStatusPago statusPago) {
		this.statusPago = statusPago;
	}

}