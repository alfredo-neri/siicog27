package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TR_CONTRATO_SOLICITUDES database table.
 * 
 */
@Entity
@Table(name = "TR_CONTRATO_SOLICITUDES")
@NamedQuery(name = "TrContratoSolicitud.findAll", query = "SELECT t FROM TrContratoSolicitud t")
public class TrContratoSolicitud extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "ID_CONTRATO", nullable = false)
	private TcContrato contrato;

	@ManyToOne
	@JoinColumn(name = "ID_SOLICITUD", nullable = false)
	private TcAdquisicion solicitud;

	public TrContratoSolicitud() {
	}

	public TcContrato getContrato() {
		return contrato;
	}

	public void setContrato(TcContrato contrato) {
		this.contrato = contrato;
	}

	public TcAdquisicion getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(TcAdquisicion solicitud) {
		this.solicitud = solicitud;
	}

}