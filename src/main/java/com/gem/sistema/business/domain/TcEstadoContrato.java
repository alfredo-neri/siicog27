package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TC_ESTADOS_CONTRATO database table.
 * 
 */
@Entity
@Table(name = "TC_ESTADOS_CONTRATO")
@NamedQuery(name = "TcEstadoContrato.findAll", query = "SELECT t FROM TcEstadoContrato t")
public class TcEstadoContrato extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String REVISANDO = "REVISANDO";
	public static final String REVISADO = "REVISADO";
	public static final String FIRMANDO = "FIRMANDO";
	public static final String FIRMADO = "FIRMADO";
	private String estado;

	public TcEstadoContrato() {
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}