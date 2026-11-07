package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

@Entity
@Table(name = "TC_CONTRA_RECIBOS")
@NamedQuery(name = "ContraRecibo.findAll", query = "SELECT p FROM ContraRecibo p")
public class ContraRecibo extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -7123848995427030815L;

	private String concepto;
	private String cancelado;

	@Column(name = "CONTRATO_PEDIDO")
	private String contratoPedido;

	private String elaboro;
	@ManyToOne
	@JoinColumn(name = "ID_FUENTE_FINANCIEMIENTO", nullable = false)
	private Fuentef fuentef;

	@Column(name = "FECHA_EMICION")
	@Temporal(TemporalType.DATE)
	private Date fechaEmicion;

	@Column(name = "FECHA_PAGO")
	private Date fechaPago;

	@ManyToOne
	@JoinColumn(name = "ID_PROVEEDOR", nullable = false)
	private ProveedorNuevo proveedor;

	private Integer folio;

	@ManyToOne
	@JoinColumn(name = "ID_FOLIO_IDENTIFICACION", nullable = false)
	private FolioContrarecibo folioIdentificacion;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contraRecibo")
	private List<TcFacturaContraRecibo> facturas = new ArrayList<>();

	private String obs;

	public ContraRecibo() {

	}

	public void deleteFactura(TcFacturaContraRecibo factura) {
		if (facturas.contains(factura)) {
			facturas.remove(factura);
		}
	}

	public void addFactura(TcFacturaContraRecibo facturaContraRecibo) {
		facturaContraRecibo.setContraRecibo(this);
		facturas.add(facturaContraRecibo);
	}

	public void updateFactura(TcFacturaContraRecibo factura) {
		facturas = facturas.stream().map(a -> a.getId() == factura.getId() ? factura : a).collect(Collectors.toList());
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getElaboro() {
		return elaboro;
	}

	public void setElaboro(String elaboro) {
		this.elaboro = elaboro;
	}

	public Fuentef getFuentef() {
		return fuentef;
	}

	public void setFuentef(Fuentef fuentef) {
		this.fuentef = fuentef;
	}

	public Date getFechaEmicion() {
		return fechaEmicion;
	}

	public void setFechaEmicion(Date fechaEmicion) {
		this.fechaEmicion = fechaEmicion;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public ProveedorNuevo getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorNuevo proveedor) {
		this.proveedor = proveedor;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public FolioContrarecibo getFolioIdentificacion() {
		return folioIdentificacion;
	}

	public void setFolioIdentificacion(FolioContrarecibo folioIdentificacion) {
		this.folioIdentificacion = folioIdentificacion;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getContratoPedido() {
		return contratoPedido;
	}

	public void setContratoPedido(String contratoPedido) {
		this.contratoPedido = contratoPedido;
	}

	public List<TcFacturaContraRecibo> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<TcFacturaContraRecibo> facturas) {
		this.facturas = facturas;
	}

	public List<String> isValid() {
		List<String> errors = new ArrayList<String>();
		if (this != null) {
			if (concepto == null || concepto.equals(""))
				errors.add("El concetpto no puedo ser vacio");
			if (elaboro == null || elaboro.equals(""))
				errors.add("El campo elaboro es nesesario");
			if (fuentef == null)
				errors.add("Debe de serleccionar un fuente");
			if (fechaEmicion == null)
				errors.add("Debe de seleccionar una fecha de emicion");
			if (fechaPago == null)
				errors.add("Debe de seleccionar una fecha de emicion");
			if (proveedor == null)
				errors.add("Debe de seleccionar un proveedor");
			if (contratoPedido == null || contratoPedido.equals(""))
				errors.add("Dede be ingresar el contrato o pedido");
			if (folio == null || folio == 0)
				errors.add("Debes de capturar el folio antes de capturar un contra recibo");
		}
		return errors;
	}

	public String getCancelado() {
		return cancelado;
	}

	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}

	public String busquedaSimple() {
		return "" + concepto + folio + fechaPago + fechaEmicion + contratoPedido;
	}
}