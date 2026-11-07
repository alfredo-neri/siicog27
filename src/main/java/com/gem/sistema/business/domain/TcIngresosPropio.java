package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.gem.sistema.annotation.IgnoredQuery;

/**
 * The persistent class for the TC_INGRESOS_PROPIOS database table.
 * 
 */
@Entity
@Table(name = "TC_INGRESOS_PROPIOS")
@NamedQuery(name = "TcIngresosPropio.findAll", query = "SELECT t FROM TcIngresosPropio t")
public class TcIngresosPropio extends AbstractEntity implements Serializable {
	@IgnoredQuery
	private static final long serialVersionUID = 1L;

	private String nombre;
	private Integer clave;

	public TcIngresosPropio() {
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getClave() {
		return clave;
	}

	public void setClave(Integer clave) {
		this.clave = clave;
	}

}