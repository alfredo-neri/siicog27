package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "TC_FIRMAS_SOLICITUDES_DETALLE")
@NamedQuery(name = "TcFirmaSolicitudDetalle.findAll", query = "SELECT t FROM TcFirmaSolicitudDetalle t")
public class TcFirmaSolicitudDetalle extends AbstractEntity implements Serializable {

	/** tipo de firma R:revision , S:recepcion de la solicitud */
	public static final String[] TIPOS_FIRMAS = { "R", "S" };
	public static final String[] ESTADO_FIRMAS = { "Sin firmar", "Firmado" };
	private static final long serialVersionUID = 8965982909864086302L;
	@Column(name = "FIRMADO_EL")
	private Date firmadoEl;

	private String estado;
	/** Posicion de la firma en la impresion deve de estar entre 1 y 7 */
	private Integer posicion;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_FIRMA", nullable = true)
	private TcFirmaDigital firma;

	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SOLICITUD", nullable = true)
	private TcAdquisicion adquisicion;

	public TcFirmaSolicitudDetalle() {

	}

	public TcFirmaSolicitudDetalle(Integer posicion, TcFirmaDigital firma, TcAdquisicion adquisicion) {
		this.estado = TcFirmaSolicitudDetalle.ESTADO_FIRMAS[0];
		this.adquisicion = adquisicion;
		this.posicion = posicion;
		this.firma = firma;
	}

	@Override
	public String toString() {
		return "TcFirmaSolicitudDetalle [estado=" + estado + ", posicion=" + posicion + ", firma=" + firma + "]";
	}

	public void setEstadoFirmado() {
		estado = TcFirmaSolicitudDetalle.ESTADO_FIRMAS[1];
	}

	public void setEstadoSinFirmar() {
		estado = TcFirmaSolicitudDetalle.ESTADO_FIRMAS[0];
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public TcFirmaDigital getFirma() {
		return firma;
	}

	public void setFirma(TcFirmaDigital firma) {
		this.firma = firma;
	}

	public TcAdquisicion getAdquisicion() {
		return adquisicion;
	}

	public void setAdquisicion(TcAdquisicion adquisicion) {
		this.adquisicion = adquisicion;
	}

	public Date getFirmadoEl() {
		return firmadoEl;
	}

	public void setFirmadoEl(Date firmadoEl) {
		this.firmadoEl = firmadoEl;
	}

}