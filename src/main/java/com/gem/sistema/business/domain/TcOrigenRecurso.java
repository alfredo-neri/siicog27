package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TC_ORIGEN_RECURSOS database table.
 * 
 */
@Entity
@Table(name = "TC_ORIGEN_RECURSOS")
@NamedQuery(name = "TcOrigenRecurso.findAll", query = "SELECT t FROM TcOrigenRecurso t")
public class TcOrigenRecurso extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String descripcion;

	public TcOrigenRecurso() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		return new Long(this.getId() + TcOrigenRecurso.serialVersionUID).intValue();
	}
}