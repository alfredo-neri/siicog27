package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TC_TIPOS_GASTO database table.
 * 
 */
@Entity
@Table(name = "TC_TIPOS_GASTO")
@NamedQuery(name = "TcTipoGasto.findAll", query = "SELECT t FROM TcTipoGasto t")
public class TcTipoGasto extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -2422868536986924822L;
	private String descripcion;

	public TcTipoGasto() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		return new Long(this.getId() + TcTipoGasto.serialVersionUID).intValue();
	}
}