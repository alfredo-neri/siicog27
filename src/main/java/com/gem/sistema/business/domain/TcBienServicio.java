package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TC_BIENES_SERVICIOS database table.
 * 
 */
@Entity
@Table(name = "TC_BIENES_SERVICIOS")
@NamedQuery(name = "TcBienServicio.findAll", query = "SELECT t FROM TcBienServicio t")
public class TcBienServicio extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4660469611732192798L;
	private String nombre;

	public TcBienServicio() {
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
		TcBienServicio other = (TcBienServicio) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

}