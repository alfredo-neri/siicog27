package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the TW_CONTRATO_FACTURA database table.
 * 
 */
@Entity
@Table(name = "TW_CONTRATO_FACTURA")
@NamedQuery(name = "TwContratoFactura.findAll", query = "SELECT t FROM TwContratoFactura t")
public class TwContratoFactura extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_AT")
	private Date createAt;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_PAGO")
	private Date fechaPago;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_RECEPCION")
	private Date fechaRecepcion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ACCION_CONTRATO")
	private TwAccionContrato accionContrato;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_POLIZA")
	private Polien poliza;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_STATUS_PAGO")
	private TcStatusPago statusPago;

	@Column(name = "IMPORTE_FACTURA")
	private BigDecimal importeFactura;

	@Column(name = "NO_FACTURA")
	private String noFactura;

	public TwContratoFactura() {
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Date getFechaRecepcion() {
		return this.fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
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

	public TwAccionContrato getAccionContrato() {
		return accionContrato;
	}

	public void setAccionContrato(TwAccionContrato accionContrato) {
		this.accionContrato = accionContrato;
	}

	public Polien getPoliza() {
		return poliza;
	}

	public void setPoliza(Polien poliza) {
		this.poliza = poliza;
	}

	public TcStatusPago getStatusPago() {
		return statusPago;
	}

	public void setStatusPago(TcStatusPago statusPago) {
		this.statusPago = statusPago;
	}

}