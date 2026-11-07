package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * The persistent class for the TC_CONTRATOS database table.
 * 
 */
@Entity
@Table(name = "TC_CONTRATOS")
@NamedQuery(name = "TcContrato.findAll", query = "SELECT t FROM TcContrato t")
public class TcContrato extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECCHA_CREACION")
	private Date fecchaCreacion = Calendar.getInstance().getTime();

	@Column(name = "IMPORTE_ADQUISICION")
	private BigDecimal importeAdquisicion = BigDecimal.ZERO; 

	@Column(name = "NUMERO_CONTRATO")
	private String numeroContrato = StringUtils.EMPTY;

	@Column(name = "OBJETO_ADQUISICION")
	private String objetoAdquisicion = StringUtils.EMPTY;

	// bi-directional many-to-one association to TcEstadosContrato
	@ManyToOne
	@JoinColumn(name = "ID_PROCEDIMIENTO_ADQUISITIVO")
	private TcProcedimientoAdquisitivo procediemientoAdquisitivo;

	// bi-directional many-to-one association to TcEstadosContrato
	@ManyToOne
	@JoinColumn(name = "ID_ESTADO")
	private TcEstadoContrato estadosContrato;

	// bi-directional many-to-one association to TcTipoContrato
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_CONTRATO")
	private TcTipoContrato tipoContrato;

//	// bi-directional many-to-one association to TcAdquisicione
//	@ManyToOne(cascade = CascadeType.MERGE)
//	@JoinColumn(name = "ID_ADQUISICION")
//	private TcAdquisicion adquisicion;

	@ManyToOne
	@JoinColumn(name = "ID_PROVEEDOR")
	private ProveedorNuevo proveedor;

	@ManyToOne
	@JoinColumn(name = "ID_REVISOR")
	private TcUsuario revisor;

	@OneToOne(mappedBy = "contrato", optional = true, cascade = CascadeType.ALL)
	private TcConvenio convenio;

//	@Column(name = "ID_ADQUISICION")
//	private Integer idAquisicion;

//	@ManyToMany(cascade = CascadeType.DETACH)
//	@JoinTable(name = "TR_CONTRATO_SOLICITUDES", joinColumns = @JoinColumn(name = "ID_CONTRATO"), inverseJoinColumns = @JoinColumn(name = "ID_SOLICITUD"))
//	private List<TcAdquisicion> adquisicions = new ArrayList<TcAdquisicion>();

//	@OneToMany(targetEntity = TrContratoSolicitud.class, cascade = CascadeType.ALL)
//	private List<TrContratoSolicitud> contratoSolicituds;

	public TcContrato() {
//		TcAdquisicion adquisicion = new TcAdquisicion();
//		this.adquisicion = adquisicion;
	}

	@Override
	public String toString() {
		return "TcContrato [ fecchaCreacion=" + fecchaCreacion + ", importeAdquisicion=" + importeAdquisicion
				+ ", numeroContrato=" + numeroContrato + ", objetoAdquisicion=" + objetoAdquisicion
				+ ", procediemientoAdquisitivo=" + procediemientoAdquisitivo + ", estadosContrato=" + estadosContrato
				+ ", tipoContrato=" + tipoContrato + ",  proveedor=" + proveedor + "]";
	}

	@PrePersist
	public void prepresist() {
		if (StringUtils.isEmpty(this.objetoAdquisicion))
			this.objetoAdquisicion = StringUtils.EMPTY;

	}

	public Date getFecchaCreacion() {
		return this.fecchaCreacion;
	}

	public void setFecchaCreacion(Date fecchaCreacion) {
		this.fecchaCreacion = fecchaCreacion;
	}

	public BigDecimal getImporteAdquisicion() {
		return this.importeAdquisicion;
	}

	public void setImporteAdquisicion(BigDecimal importeAdquisicion) {
		this.importeAdquisicion = importeAdquisicion;
	}

	public String getNumeroContrato() {
		return this.numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getObjetoAdquisicion() {
		return this.objetoAdquisicion;
	}

	public void setObjetoAdquisicion(String objetoAdquisicion) {
		this.objetoAdquisicion = objetoAdquisicion;
	}

	public TcProcedimientoAdquisitivo getProcediemientoAdquisitivo() {
		return procediemientoAdquisitivo;
	}

	public void setProcediemientoAdquisitivo(TcProcedimientoAdquisitivo procediemientoAdquisitivo) {
		this.procediemientoAdquisitivo = procediemientoAdquisitivo;
	}

	public TcEstadoContrato getEstadosContrato() {
		return estadosContrato;
	}

	public void setEstadosContrato(TcEstadoContrato estadosContrato) {
		this.estadosContrato = estadosContrato;
	}

	public TcTipoContrato getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(TcTipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

//	public TcAdquisicion getAdquisicion() {
//		return adquisicion;
//	}
//
//	public void setAdquisicion(TcAdquisicion adquisicion) {
//		this.adquisicion = adquisicion;
//	}

	public ProveedorNuevo getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorNuevo proveedor) {
		this.proveedor = proveedor;
	}

	public TcUsuario getRevisor() {
		return revisor;
	}

	public void setRevisor(TcUsuario revisor) {
		this.revisor = revisor;
	}

	public TcConvenio getConvenio() {
		return convenio;
	}

	public void setConvenio(TcConvenio convenio) {
		this.convenio = convenio;
	}

//	public List<TrContratoSolicitud> getContratoSolicituds() {
//		return contratoSolicituds;
//	}
//
//	public void setContratoSolicituds(List<TrContratoSolicitud> contratoSolicituds) {
//		this.contratoSolicituds = contratoSolicituds;
//	}

//	public List<TcAdquisicion> getAdquisicions() {
//		return adquisicions;
//	}
//
//	public void setAdquisicions(List<TcAdquisicion> adquisicions) {
//		this.adquisicions = adquisicions;
//	}

}