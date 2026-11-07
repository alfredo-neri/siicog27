package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the TW_ORAS database table.
 * 
 */
@Entity
@Table(name = "TW_OBRAS")
@NamedQuery(name = "TwObra.findAll", query = "SELECT t FROM TwObra t")
public class TwObra extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal asignado;

	private BigDecimal autorizado;

	@OneToOne()
	@JoinColumn(name = "ID_CONTRATO")
	private TcContratoPad contrato;

	@ManyToOne
	@JoinColumn(name = "ID_PRESUPUESTO")
	private TrPresupuestoDependencia presupuestoDependencia;

	@OneToMany(mappedBy = "obra", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TwObraPago> pagos = new ArrayList<>();

	@Column(name = "NO_ACCION")
	private Integer noAccion;

	@Column(name = "NOMBRE_OBRA")
	private String nombreObra;

	@Column(name = "OFICIO_ASIGNACION")
	private String oficioAsignacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_ASIGNACION")
	private Date fechaAsignacion;

	@Column(name = "OFICIO_AUTORIZACION")
	private String oficioAutorizacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_AUTORIZACION")
	private Date fechaAutorizacion;

	public TwObra() {

//		this.noAccion = 0;
//		this.asignado = BigDecimal.ZERO;
//		this.autorizado = BigDecimal.ZERO;
//		this.nombreObra = StringUtils.EMPTY;
//		this.contrato.setNumeroContrato(StringUtils.EMPTY);
//		this.setContrato(new TcContrato());
//		this.setPresupuestoDependencia(new TrPresupuestoDependencia());
	}

	public BigDecimal getAsignado() {
		return this.asignado;
	}

	public void setAsignado(BigDecimal asignado) {
		this.asignado = asignado;
	}

	public BigDecimal getAutorizado() {
		return this.autorizado;
	}

	public void setAutorizado(BigDecimal autorizado) {
		this.autorizado = autorizado;
	}

	public int getNoAccion() {
		return this.noAccion;
	}

	public void setNoAccion(int noAccion) {
		this.noAccion = noAccion;
	}

	public String getNombreObra() {
		return this.nombreObra;
	}

	public void setNombreObra(String nombreObra) {
		this.nombreObra = nombreObra;
	}

	public TcContratoPad getContrato() {
		return contrato;
	}

	public void setContrato(TcContratoPad contrato) {
		this.contrato = contrato;
	}

	public TrPresupuestoDependencia getPresupuestoDependencia() {
		return presupuestoDependencia;
	}

	public void setPresupuestoDependencia(TrPresupuestoDependencia presupuestoDependencia) {
		this.presupuestoDependencia = presupuestoDependencia;
	}

	public List<TwObraPago> getPagos() {
		return pagos;
	}

	public void setPagos(List<TwObraPago> pagos) {
		this.pagos = pagos;
	}

	public void setNoAccion(Integer noAccion) {
		this.noAccion = noAccion;
	}

	public String getOficioAsignacion() {
		return oficioAsignacion;
	}

	public void setOficioAsignacion(String oficioAsignacion) {
		this.oficioAsignacion = oficioAsignacion;
	}

	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public String getOficioAutorizacion() {
		return oficioAutorizacion;
	}

	public void setOficioAutorizacion(String oficioAutorizacion) {
		this.oficioAutorizacion = oficioAutorizacion;
	}

	public Date getFechaAutorizacion() {
		return fechaAutorizacion;
	}

	public void setFechaAutorizacion(Date fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}

}