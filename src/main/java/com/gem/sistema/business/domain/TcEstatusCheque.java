package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the TC_ESTATUS_CHEQUES database table.
 * 
 */
@Entity
@Table(name = "TC_ESTATUS_CHEQUES")
@NamedQuery(name = "TcEstatusCheque.findAll", query = "SELECT t FROM TcEstatusCheque t")
public class TcEstatusCheque extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;

	

	public TcEstatusCheque() {
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	


}