package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the TC_ETIQUETAS database table.
 * 
 */
@Entity
@Table(name = "TC_ETIQUETAS")
@NamedQuery(name = "TcEtiqueta.findAll", query = "SELECT t FROM TcEtiqueta t")
public class TcEtiqueta extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nombre;

	@Column(name = "STATUS")
	private Integer status;

	public TcEtiqueta() {
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}