package com.gem.sistema.business.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

/**
 * The persistent class for the TC_TIPO_CHEQUE database table.
 * 
 */
@Entity
@Table(name = "TC_TIPO_CHEQUE")
@NamedQuery(name = "TcTipoCheque.findAll", query = "SELECT t FROM TcTipoCheque t")
public class TcTipoCheque extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "CLAVE_CHEQUE", unique = true)
	private Character claveCheque;

	private String nombre;

	

	public TcTipoCheque() {
	}

	public Character getClaveCheque() {
		return this.claveCheque;
	}

	public void setClaveCheque(Character claveCheque) {
		this.claveCheque = claveCheque;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

}