package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the TC_ADQUISICIONES database table.
 * 
 */
@Entity
@Table(name = "TC_ADQUISICIONES")
@NamedQuery(name = "TcAdquisicion.findAll", query = "SELECT t FROM TcAdquisicion t")
public class TcAdquisicion extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "MARCA_ESPESIFICA")
	private Boolean marcaEspesifica;

	@Column(name = "CARGO_RECEPTOR")
	private String cargoReceptor;

	@Column(name = "ESTADO_CAPTURA")
	private Integer estadoCaptura;
	private BigDecimal anticipo;

	@Column(name = "DICTAMEN_UNICO")
	private String dictamenUnico;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_ELABORACION")
	private Date fechaElaboracion;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_SUMINISTRO")
	private Date fechaSuministro;

	@Column(name = "HORARIO_A")
	private String horarioA;

	@Column(name = "HORARIO_DE")
	private String horarioDe;

	@ManyToOne
	@JoinColumn(name = "ID_BIEN_SERVICIO", nullable = false)
	private TcBienServicio bienServicio;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_PAGO", nullable = false)
	private TcTiposPago formaPago;

	@ManyToOne
	@JoinColumn(name = "ID_ORIGEN_RECURSOS", nullable = false)
	private TcOrigenRecurso origenRecurso;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_GASTO", nullable = false)
	private TcTipoGasto tipoGasto;

	@ManyToOne
	@JoinColumn(name = "ID_TRAMITACION", nullable = false)
	private TcTramite tramite;

	@ManyToOne
	@JoinColumn(name = "ID_PERSONAL_ADMINISTRATIVO", nullable = false)
	private TcPersonalAdministrativo administrativo;

	private String usuario;
	private String revisor;
	@Column(name = "LUGAR_ENTREGA")
	private String lugarEntrega;

	@Column(name = "MONTO_COMPROMETIDO")
	private BigDecimal montoComprometido;

	@Column(name = "NO_OFICIO_AUTORIZACION")
	private Integer noOficioAutorizacion;

	@Column(name = "NOMBRE_RECEPTOR")
	private String nombreReceptor;

	@Column(name = "NUMERO_CONTROL")
	private Integer numeroControl;

	private String obs;

	@Column(name = "OFICIO_CERTIFICACION")
	private String oficioCertificacion;

	@Column(name = "OFICIO_JUSTIFICACION")
	private String oficioJustificacion;

	@Column(name = "PROGRAMA_GOBIERNO")
	private String programaGobierno;

	private String programado;

	@OneToOne
	@JoinColumn(name = "ID_ESTADO", nullable = false)
	private TcEstadoContrato estado;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "adquisicion")
	private List<TcFirmaSolicitudDetalle> firmas = new ArrayList<TcFirmaSolicitudDetalle>();

	@ManyToOne
	@JoinColumn(name = "ID_NATGAS", nullable = false)
	private Natgas giro;

	@Column(name = "OBS_REVISOR")
	private String obsRevisor;

	@OneToMany(mappedBy = "adquisicion", fetch = FetchType.LAZY)
	private List<TcAdquisicionGiroSubgiro> giroSubgirosList;

	@OneToMany(mappedBy = "adquisicion", fetch = FetchType.LAZY)
	private List<TcAdquisicionPrograma> programasList;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((administrativo == null) ? 0 : administrativo.hashCode());
		result = prime * result + ((anticipo == null) ? 0 : anticipo.hashCode());
		result = prime * result + ((bienServicio == null) ? 0 : bienServicio.hashCode());
		result = prime * result + ((cargoReceptor == null) ? 0 : cargoReceptor.hashCode());
		result = prime * result + ((dictamenUnico == null) ? 0 : dictamenUnico.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((estadoCaptura == null) ? 0 : estadoCaptura.hashCode());
		result = prime * result + ((fechaElaboracion == null) ? 0 : fechaElaboracion.hashCode());
		result = prime * result + ((fechaSuministro == null) ? 0 : fechaSuministro.hashCode());
		result = prime * result + ((firmas == null) ? 0 : firmas.hashCode());
		result = prime * result + ((formaPago == null) ? 0 : formaPago.hashCode());
		result = prime * result + ((giro == null) ? 0 : giro.hashCode());
		result = prime * result + ((horarioA == null) ? 0 : horarioA.hashCode());
		result = prime * result + ((horarioDe == null) ? 0 : horarioDe.hashCode());
		result = prime * result + ((lugarEntrega == null) ? 0 : lugarEntrega.hashCode());
		result = prime * result + ((marcaEspesifica == null) ? 0 : marcaEspesifica.hashCode());
		result = prime * result + ((montoComprometido == null) ? 0 : montoComprometido.hashCode());
		result = prime * result + ((noOficioAutorizacion == null) ? 0 : noOficioAutorizacion.hashCode());
		result = prime * result + ((nombreReceptor == null) ? 0 : nombreReceptor.hashCode());
		result = prime * result + ((numeroControl == null) ? 0 : numeroControl.hashCode());
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result + ((oficioCertificacion == null) ? 0 : oficioCertificacion.hashCode());
		result = prime * result + ((oficioJustificacion == null) ? 0 : oficioJustificacion.hashCode());
		result = prime * result + ((origenRecurso == null) ? 0 : origenRecurso.hashCode());
		result = prime * result + ((programaGobierno == null) ? 0 : programaGobierno.hashCode());
		result = prime * result + ((programado == null) ? 0 : programado.hashCode());
		result = prime * result + ((revisor == null) ? 0 : revisor.hashCode());
		result = prime * result + ((tipoGasto == null) ? 0 : tipoGasto.hashCode());
		result = prime * result + ((tramite == null) ? 0 : tramite.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	public TcAdquisicion() {
		this.administrativo = new TcPersonalAdministrativo();
		this.administrativo = new TcPersonalAdministrativo();
		this.origenRecurso = new TcOrigenRecurso();
		this.bienServicio = new TcBienServicio();
		this.tipoGasto = new TcTipoGasto();
		this.formaPago = new TcTiposPago();
		this.tramite = new TcTramite();
		this.estado = new TcEstadoContrato();
		this.giro = new Natgas();
	}

	public BigDecimal getAnticipo() {
		return anticipo;
	}

	public void setAnticipo(BigDecimal anticipo) {
		this.anticipo = anticipo;
	}

	public String getCargoReceptor() {
		return this.cargoReceptor;
	}

	public void setCargoReceptor(String cargoReceptor) {
		this.cargoReceptor = cargoReceptor;
	}

	public String getDictamenUnico() {
		return this.dictamenUnico;
	}

	public void setDictamenUnico(String dictamenUnico) {
		this.dictamenUnico = dictamenUnico;
	}

	public Date getFechaElaboracion() {
		return this.fechaElaboracion;
	}

	public void setFechaElaboracion(Date fechaElaboracion) {
		this.fechaElaboracion = fechaElaboracion;
	}

	public Date getFechaSuministro() {
		return this.fechaSuministro;
	}

	public void setFechaSuministro(Date fechaSuministro) {
		this.fechaSuministro = fechaSuministro;
	}

	public String getHorarioA() {
		return horarioA;
	}

	public void setHorarioA(String horarioA) {
		this.horarioA = horarioA;
	}

	public String getHorarioDe() {
		return horarioDe;
	}

	public void setHorarioDe(String horarioDe) {
		this.horarioDe = horarioDe;
	}

	public TcBienServicio getBienServicio() {
		return bienServicio;
	}

	public void setBienServicio(TcBienServicio bienServicio) {
		this.bienServicio = bienServicio;
	}

	public TcTiposPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(TcTiposPago formaPago) {
		this.formaPago = formaPago;
	}

	public TcOrigenRecurso getOrigenRecurso() {
		return origenRecurso;
	}

	public void setOrigenRecurso(TcOrigenRecurso origenRecurso) {
		this.origenRecurso = origenRecurso;
	}

	public TcTipoGasto getTipoGasto() {
		return tipoGasto;
	}

	public void setTipoGasto(TcTipoGasto tipoGasto) {
		this.tipoGasto = tipoGasto;
	}

	public TcTramite getTramite() {
		return tramite;
	}

	public void setTramite(TcTramite tramite) {
		this.tramite = tramite;
	}

	public TcPersonalAdministrativo getAdministrativo() {
		return administrativo;
	}

	public void setAdministrativo(TcPersonalAdministrativo administrativo) {
		this.administrativo = administrativo;
	}

	public String getLugarEntrega() {
		return this.lugarEntrega;
	}

	public void setLugarEntrega(String lugarEntrega) {
		this.lugarEntrega = lugarEntrega;
	}

	public BigDecimal getMontoComprometido() {
		return montoComprometido;
	}

	public void setMontoComprometido(BigDecimal montoComprometido) {
		this.montoComprometido = montoComprometido;
	}

	public Integer getNoOficioAutorizacion() {
		return this.noOficioAutorizacion;
	}

	public void setNoOficioAutorizacion(Integer noOficioAutorizacion) {
		this.noOficioAutorizacion = noOficioAutorizacion;
	}

	public String getNombreReceptor() {
		return this.nombreReceptor;
	}

	public void setNombreReceptor(String nombreReceptor) {
		this.nombreReceptor = nombreReceptor;
	}

	public Integer getNumeroControl() {
		return this.numeroControl;
	}

	public void setNumeroControl(Integer numeroControl) {
		this.numeroControl = numeroControl;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getOficioCertificacion() {
		return this.oficioCertificacion;
	}

	public void setOficioCertificacion(String oficioCertificacion) {
		this.oficioCertificacion = oficioCertificacion;
	}

	public String getOficioJustificacion() {
		return this.oficioJustificacion;
	}

	public void setOficioJustificacion(String oficioJustificacion) {
		this.oficioJustificacion = oficioJustificacion;
	}

	public String getProgramaGobierno() {
		return this.programaGobierno;
	}

	public void setProgramaGobierno(String programaGobierno) {
		this.programaGobierno = programaGobierno;
	}

	public String getProgramado() {
		return this.programado;
	}

	public void setProgramado(String programado) {
		this.programado = programado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<TcFirmaSolicitudDetalle> getFirmas() {
		return firmas;
	}

	public void setFirmas(List<TcFirmaSolicitudDetalle> firmas) {
		this.firmas = firmas;
	}

	public String getRevisor() {
		return revisor;
	}

	public void setRevisor(String revisor) {
		this.revisor = revisor;
	}

	public TcEstadoContrato getEstado() {
		return estado;
	}

	public void setEstado(TcEstadoContrato estado) {
		this.estado = estado;
	}

	public Integer getEstadoCaptura() {
		return estadoCaptura;
	}

	public void setEstadoCaptura(Integer estadoCaptura) {
		this.estadoCaptura = estadoCaptura;
	}

	public Boolean getMarcaEspesifica() {
		return marcaEspesifica;
	}

	public void setMarcaEspesifica(Boolean marcaEspesifica) {
		this.marcaEspesifica = marcaEspesifica;
	}

//	public List<TcContrato> getContratos() {
//		return contratos;
//	}
//
//	public void setContratos(List<TcContrato> contratos) {
//		this.contratos = contratos;
//	}

	public String busquedaSimple() {
		return "" + numeroControl + montoComprometido + (estado == null ? "" : estado.getEstado())
				+ (tramite == null ? "" : tramite.getTramite());
	}

	public Natgas getGiro() {
		return giro;
	}

	public void setGiro(Natgas giro) {
		this.giro = giro;
	}

	public String getObsRevisor() {
		return obsRevisor;
	}

	public void setObsRevisor(String obsRevisor) {
		this.obsRevisor = obsRevisor;
	}

	public List<TcAdquisicionGiroSubgiro> getGiroSubgirosList() {
		return giroSubgirosList;
	}

	public void setGiroSubgirosList(List<TcAdquisicionGiroSubgiro> giroSubgirosList) {
		this.giroSubgirosList = giroSubgirosList;
	}

	public List<TcAdquisicionPrograma> getProgramasList() {
		return programasList;
	}

	public void setProgramasList(List<TcAdquisicionPrograma> programasList) {
		this.programasList = programasList;
	}

	@Override
	public String toString() {
		return "TcAdquisicion [marcaEspesifica=" + marcaEspesifica + ", cargoReceptor=" + cargoReceptor
				+ ", estadoCaptura=" + estadoCaptura + ", anticipo=" + anticipo + ", dictamenUnico=" + dictamenUnico
				+ ", fechaElaboracion=" + fechaElaboracion + ", fechaSuministro=" + fechaSuministro + ", horarioA="
				+ horarioA + ", horarioDe=" + horarioDe + ", bienServicio=" + bienServicio + ", formaPago=" + formaPago
				+ ", origenRecurso=" + origenRecurso + ", tipoGasto=" + tipoGasto + ", tramite=" + tramite
				+ ", administrativo=" + administrativo + ", usuario=" + usuario + ", revisor=" + revisor
				+ ", lugarEntrega=" + lugarEntrega + ", montoComprometido=" + montoComprometido
				+ ", noOficioAutorizacion=" + noOficioAutorizacion + ", nombreReceptor=" + nombreReceptor
				+ ", numeroControl=" + numeroControl + ", obs=" + obs + ", oficioCertificacion=" + oficioCertificacion
				+ ", oficioJustificacion=" + oficioJustificacion + ", programaGobierno=" + programaGobierno
				+ ", programado=" + programado + ", estado=" + estado + ", firmas=" + firmas + ", giro=" + giro
				+ ", obsRevisor=" + obsRevisor + "]";
	}

}