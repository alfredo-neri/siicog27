package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TC_PROCEDIMIENTOS_ADQUISITIVOS database table.
 * 
 */
@Entity
@Table(name = "TC_PROCEDIMIENTOS_ADQUISITIVOS")
@NamedQuery(name = "TcProcedimientoAdquisitivo.findAll", query = "SELECT t FROM TcProcedimientoAdquisitivo t")
public class TcProcedimientoAdquisitivo extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String clave;

	private String descripcion;

	public TcProcedimientoAdquisitivo() {
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}