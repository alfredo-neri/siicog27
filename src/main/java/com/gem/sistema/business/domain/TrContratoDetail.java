package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;

/**
 * The persistent class for the TR_CONTRATO_DETAIL database table.
 * 
 */
@Entity
@Table(name = "TR_CONTRATO_DETAIL")
@NamedQuery(name = "TrContratoDetail.findAll", query = "SELECT t FROM TrContratoDetail t")
public class TrContratoDetail extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String anticipos;

	@Column(name = "ANTICIPOS_INTERESES")
	private String anticiposIntereses;

	@Column(name = "AJUSTE_PRECIOS")
	private String ajustePrecios;

	private String calidad;

	@Column(name = "CONDICIONES_ENTREGA")
	private String condicionesEntrega;

	@Column(name = "CONDICIONES_PAGO")
	private String condicionesPago;

	@Column(name = "DEVOLUCION_GARANTIAS")
	private String devolucionGarantias;

	@Column(name = "DICTAMEN_AJUDICACION")
	private String dictamenAjudicacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_FALLO")
	private Date fechaFallo;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_PRESENTACION")
	private Date fechaPresentacion;

	private String garantia;

	@Column(name = "GARANTIA_CONTRA_DEFECTOS")
	private String garantiaContraDefectos;

	@Column(name = "GARANTIA_CUMPLIMIENTO_CONTRATO")
	private String garantiaCumplimientoContrato;

	@Column(name = "LEGISLACION_APLICADA")
	private String legislacionAplicada;

	@Column(name = "LUGAR_ENTREGA")
	private String lugarEntrega;

	@Column(name = "LUGAR_PRESTACION")
	private String lugarPrestacion;

	@Column(name = "NOMBRE_PROPIETARIO")
	private String nombrePropietario;

	private String observaciones;

	@Column(name = "PENAS_SANCIONES")
	private String penasSanciones;

	@Column(name = "PLAZO_ENTREGA")
	private String plazoEntrega;

	@Column(name = "PRESENTACION_DEVOLUCION_GARANTIAS")
	private String presentacionDevolucionGarantias;

	@Column(name = "SELECCION_PROPUESTAS")
	private String seleccionPropuestas;

	@Column(name = "VIGENCIA_CONTRATO")
	private String vigenciaContrato;

	@Column(name = "VIGENCIA_PRECIOS")
	private String vigenciaPrecios;

	@Column(name = "VIGENCIA_PRESENTACION")
	private String vigenciaPresentacion;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_CONTRATO", nullable = false)
	private TcContrato contrato;

	@PreUpdate
	@PrePersist
	public void prePersist() {
		if (StringUtils.isEmpty(this.vigenciaPresentacion))
			this.vigenciaPresentacion = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.vigenciaPrecios))
			this.vigenciaPrecios = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.vigenciaContrato))
			this.vigenciaContrato = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.nombrePropietario))
			this.nombrePropietario = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.legislacionAplicada))
			this.legislacionAplicada = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.dictamenAjudicacion))
			this.dictamenAjudicacion = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.plazoEntrega))
			this.plazoEntrega = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.lugarEntrega))
			this.lugarEntrega = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.condicionesEntrega))
			this.condicionesEntrega = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.calidad))
			this.calidad = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.condicionesPago))
			this.condicionesPago = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.anticipos))
			this.anticipos = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.ajustePrecios))
			this.ajustePrecios = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.garantia))
			this.garantia = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.garantiaCumplimientoContrato))
			this.garantiaCumplimientoContrato = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.garantiaContraDefectos))
			this.garantiaContraDefectos = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.devolucionGarantias))
			this.devolucionGarantias = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.penasSanciones))
			this.penasSanciones = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.observaciones))
			this.observaciones = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.lugarPrestacion))
			this.lugarPrestacion = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.seleccionPropuestas))
			this.seleccionPropuestas = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.vigenciaContrato))
			this.vigenciaContrato = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.anticiposIntereses))
			this.anticiposIntereses = StringUtils.EMPTY;

		if (StringUtils.isEmpty(this.presentacionDevolucionGarantias))
			this.presentacionDevolucionGarantias = StringUtils.EMPTY;

	}

	public TrContratoDetail() {
	}

	public String getAnticipos() {
		return this.anticipos;
	}

	public void setAnticipos(String anticipos) {
		this.anticipos = anticipos;
	}

	public String getAnticiposIntereses() {
		return this.anticiposIntereses;
	}

	public void setAnticiposIntereses(String anticiposIntereses) {
		this.anticiposIntereses = anticiposIntereses;
	}

	public String getAjustePrecios() {
		return ajustePrecios;
	}

	public void setAjustePrecios(String ajustePrecios) {
		this.ajustePrecios = ajustePrecios;
	}

	public String getCalidad() {
		return this.calidad;
	}

	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}

	public String getCondicionesEntrega() {
		return this.condicionesEntrega;
	}

	public void setCondicionesEntrega(String condicionesEntrega) {
		this.condicionesEntrega = condicionesEntrega;
	}

	public String getCondicionesPago() {
		return this.condicionesPago;
	}

	public void setCondicionesPago(String condicionesPago) {
		this.condicionesPago = condicionesPago;
	}

	public String getDevolucionGarantias() {
		return this.devolucionGarantias;
	}

	public void setDevolucionGarantias(String devolucionGarantias) {
		this.devolucionGarantias = devolucionGarantias;
	}

	public String getDictamenAjudicacion() {
		return this.dictamenAjudicacion;
	}

	public void setDictamenAjudicacion(String dictamenAjudicacion) {
		this.dictamenAjudicacion = dictamenAjudicacion;
	}

	public Date getFechaFallo() {
		return fechaFallo;
	}

	public void setFechaFallo(Date fechaFallo) {
		this.fechaFallo = fechaFallo;
	}

	public Date getFechaPresentacion() {
		return fechaPresentacion;
	}

	public void setFechaPresentacion(Date fechaPresentacion) {
		this.fechaPresentacion = fechaPresentacion;
	}

	public String getGarantia() {
		return this.garantia;
	}

	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}

	public String getGarantiaContraDefectos() {
		return this.garantiaContraDefectos;
	}

	public void setGarantiaContraDefectos(String garantiaContraDefectos) {
		this.garantiaContraDefectos = garantiaContraDefectos;
	}

	public String getGarantiaCumplimientoContrato() {
		return this.garantiaCumplimientoContrato;
	}

	public void setGarantiaCumplimientoContrato(String garantiaCumplimientoContrato) {
		this.garantiaCumplimientoContrato = garantiaCumplimientoContrato;
	}

	public String getLegislacionAplicada() {
		return this.legislacionAplicada;
	}

	public void setLegislacionAplicada(String legislacionAplicada) {
		this.legislacionAplicada = legislacionAplicada;
	}

	public String getLugarEntrega() {
		return this.lugarEntrega;
	}

	public void setLugarEntrega(String lugarEntrega) {
		this.lugarEntrega = lugarEntrega;
	}

	public String getLugarPrestacion() {
		return this.lugarPrestacion;
	}

	public void setLugarPrestacion(String lugarPrestacion) {
		this.lugarPrestacion = lugarPrestacion;
	}

	public String getNombrePropietario() {
		return this.nombrePropietario;
	}

	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getPenasSanciones() {
		return this.penasSanciones;
	}

	public void setPenasSanciones(String penasSanciones) {
		this.penasSanciones = penasSanciones;
	}

	public String getPlazoEntrega() {
		return this.plazoEntrega;
	}

	public void setPlazoEntrega(String plazoEntrega) {
		this.plazoEntrega = plazoEntrega;
	}

	public String getPresentacionDevolucionGarantias() {
		return this.presentacionDevolucionGarantias;
	}

	public void setPresentacionDevolucionGarantias(String presentacionDevolucionGarantias) {
		this.presentacionDevolucionGarantias = presentacionDevolucionGarantias;
	}

	public String getSeleccionPropuestas() {
		return this.seleccionPropuestas;
	}

	public void setSeleccionPropuestas(String seleccionPropuestas) {
		this.seleccionPropuestas = seleccionPropuestas;
	}

	public String getVigenciaContrato() {
		return this.vigenciaContrato;
	}

	public void setVigenciaContrato(String vigenciaContrato) {
		this.vigenciaContrato = vigenciaContrato;
	}

	public String getVigenciaPrecios() {
		return this.vigenciaPrecios;
	}

	public void setVigenciaPrecios(String vigenciaPrecios) {
		this.vigenciaPrecios = vigenciaPrecios;
	}

	public String getVigenciaPresentacion() {
		return this.vigenciaPresentacion;
	}

	public void setVigenciaPresentacion(String vigenciaPresentacion) {
		this.vigenciaPresentacion = vigenciaPresentacion;
	}

	public TcContrato getContrato() {
		return contrato;
	}

	public void setContrato(TcContrato contrato) {
		this.contrato = contrato;
	}

}