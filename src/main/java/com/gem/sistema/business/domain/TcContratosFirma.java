package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the TC_CONTRATOS_FIRMAS database table.
 * 
 */
@Entity
@Table(name = "TC_CONTRATOS_FIRMAS")
@NamedQuery(name = "TcContratosFirma.findAll", query = "SELECT t FROM TcContratosFirma t")
public class TcContratosFirma extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String[] ESTADO_FIRMAS = { "Sin firmar", "Firmado" };

	@Column(name = "FIRMADO_EL")
	private Date firmadoEl;

	private String estado;

	private Integer posicion;

	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CONTRATO")
	private TcContrato contrato;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "ID_FIRMA", nullable = true)
	private TcFirmaDigital firma;

	public TcContratosFirma() {
	}

	public TcContratosFirma(String estado, Integer posicion, TcFirmaDigital firma) {
		this.estado = estado;
		this.posicion = posicion;
		this.firma = firma;
	}

	public TcContratosFirma(Integer posicion, TcFirmaDigital firma, TcContrato contrato) {
		this.estado = TcFirmaSolicitudDetalle.ESTADO_FIRMAS[0];
		this.posicion = posicion;
		this.contrato = contrato;
		this.firma = firma;
	}

	public void setEstadoFirmado() {
		estado = TcFirmaSolicitudDetalle.ESTADO_FIRMAS[1];
	}

	public void setEstadoSinFirmar() {
		estado = TcFirmaSolicitudDetalle.ESTADO_FIRMAS[0];
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public TcContrato getContrato() {
		return contrato;
	}

	public void setContrato(TcContrato contrato) {
		this.contrato = contrato;
	}

	public TcFirmaDigital getFirma() {
		return firma;
	}

	public void setFirma(TcFirmaDigital firma) {
		this.firma = firma;
	}

	public Integer getPosicion() {
		return this.posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public Date getFirmadoEl() {
		return firmadoEl;
	}

	public void setFirmadoEl(Date firmadoEl) {
		this.firmadoEl = firmadoEl;
	}

}