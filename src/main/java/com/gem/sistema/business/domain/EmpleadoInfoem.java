package com.gem.sistema.business.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tc_empleados_infoem")
public class EmpleadoInfoem extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@Column(name = "NOMBRE_COMPLETO")
	private String nombreCompleto;

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public EmpleadoInfoem(String nombreCompleto) {
		super();
		this.nombreCompleto = nombreCompleto;
	}

	public EmpleadoInfoem() {}


	

}
