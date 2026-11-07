package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

import org.apache.commons.lang3.StringUtils;

/**
 * The persistent class for the TC_CLAVES database table.
 * 
 */
@Entity
@Table(name = "TC_CLAVES")
@NamedQuery(name = "TcClave.findAll", query = "SELECT t FROM TcClave t")
public class TcClave extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String clave = StringUtils.EMPTY;
	private String descripcion = StringUtils.EMPTY;

	@OneToMany(mappedBy = "clave")
	private List<TcPuesto> puestos;

	public TcClave() {
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<TcPuesto> getPuestos() {
		return puestos;
	}

	public void setPuestos(List<TcPuesto> puestos) {
		this.puestos = puestos;
	}

}