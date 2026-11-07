package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TC_PERIODO database table.
 * 
 */
@Entity
@Table(name = "TC_PERIODO")
@NamedQuery(name = "TcPeriodo.findAll", query = "SELECT t FROM TcPeriodo t")
public class TcPeriodo extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String descripcion;

	private Integer periodo;

	@Column(name = "TIPO_PERIODO")
	private Integer tipoPeriodo;

	public TcPeriodo() {
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Integer getTipoPeriodo() {
		return this.tipoPeriodo;
	}

	public void setTipoPeriodo(Integer tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}

}