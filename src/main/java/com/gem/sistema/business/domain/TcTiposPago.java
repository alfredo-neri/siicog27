package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TC_TIPOS_PAGO database table.
 * 
 */
@Entity
@Table(name = "TC_TIPOS_PAGO")
@NamedQuery(name = "TcTiposPago.findAll", query = "SELECT t FROM TcTiposPago t")
public class TcTiposPago extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 3235834022527373996L;
	private String nombre;

	public TcTiposPago() {
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TcTiposPago other = (TcTiposPago) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

}