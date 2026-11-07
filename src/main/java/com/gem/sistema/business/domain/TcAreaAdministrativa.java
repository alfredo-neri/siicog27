package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.gem.sistema.annotation.IgnoredQuery;

/**
 * The persistent class for the TC_AREAS_ADMINISTRATIVAS database table.
 * 
 */
@Entity
@Table(name = "TC_AREAS_ADMINISTRATIVAS")
@NamedQuery(name = "TcAreaAdministrativa.findAll", query = "SELECT t FROM TcAreaAdministrativa t")
public class TcAreaAdministrativa extends AbstractEntity implements Serializable {
	@IgnoredQuery
	private static final long serialVersionUID = -6788956852783051553L;

	private String descripcion;
	private String clave;

	public TcAreaAdministrativa() {
	}

	public List<String> isValid() {
		List<String> errors = new ArrayList<String>();
		if (descripcion == null || descripcion.equals("")) {
			errors.add("La descripcion es requerida");
		}
		if (clave == null || clave.equals("")) {
			errors.add("La clave es requerida");
		}
		return errors;
	}

	public String busquedaSimple() {
		return "" + descripcion + clave;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((clave == null) ? 0 : clave.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
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
		TcAreaAdministrativa other = (TcAreaAdministrativa) obj;
		if (clave == null) {
			if (other.clave != null)
				return false;
		} else if (!clave.equals(other.clave))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		return true;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
}