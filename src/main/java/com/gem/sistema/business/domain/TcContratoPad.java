package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the TC_CONTRATO_PAD database table.
 * 
 */
@Entity
@Table(name = "TC_CONTRATO_PAD")
@NamedQuery(name = "TcContratoPad.findAll", query = "SELECT t FROM TcContratoPad t")
public class TcContratoPad extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "CLAVE_PRESUPUESTAL")
	private String clavePresupuestal;

	private String convenios;

	private String domicilio;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_CONTRATO")
	private Date fechaContrato;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_INICIO_OBRA")
	private Date fechaInicioObra;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_TERMINO_OBRA")
	private Date fechaTerminoObra;

	private BigDecimal importe;

	private String localidad;

	private String municipio;

	@Column(name = "NOMBRE_OBRA")
	private String nombreObra;

	@Column(name = "NUMERO_CONTRATO")
	private String numeroContrato;

	private String proveedor;

	@Column(name = "REPRESENTANTE_LEGAL")
	private String representanteLegal;

	private String rfc;

	public TcContratoPad() {
	}

	public String getClavePresupuestal() {
		return this.clavePresupuestal;
	}

	public void setClavePresupuestal(String clavePresupuestal) {
		this.clavePresupuestal = clavePresupuestal;
	}

	public String getConvenios() {
		return this.convenios;
	}

	public void setConvenios(String convenios) {
		this.convenios = convenios;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Date getFechaContrato() {
		return this.fechaContrato;
	}

	public void setFechaContrato(Date fechaContrato) {
		this.fechaContrato = fechaContrato;
	}

	public Date getFechaInicioObra() {
		return this.fechaInicioObra;
	}

	public void setFechaInicioObra(Date fechaInicioObra) {
		this.fechaInicioObra = fechaInicioObra;
	}

	public Date getFechaTerminoObra() {
		return this.fechaTerminoObra;
	}

	public void setFechaTerminoObra(Date fechaTerminoObra) {
		this.fechaTerminoObra = fechaTerminoObra;
	}

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNombreObra() {
		return this.nombreObra;
	}

	public void setNombreObra(String nombreObra) {
		this.nombreObra = nombreObra;
	}

	public String getNumeroContrato() {
		return this.numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getRepresentanteLegal() {
		return this.representanteLegal;
	}

	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	public String getRfc() {
		return this.rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

}