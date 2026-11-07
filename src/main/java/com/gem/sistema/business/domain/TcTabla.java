package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the TC_TABLAS database table.
 * 
 */
@Entity
@Table(name = "TC_TABLAS")
@NamedQuery(name = "TcTabla.findAll", query = "SELECT t FROM TcTabla t")
public class TcTabla extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nombre;

	private String descripcion;

	public TcTabla() {
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}