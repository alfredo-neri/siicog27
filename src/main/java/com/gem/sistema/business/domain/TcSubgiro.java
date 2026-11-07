package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the TC_SUB_GIROS database table.
 * 
 */
@Entity
@Table(name = "TC_SUBGIROS")
@NamedQuery(name = "TcSubgiro.findAll", query = "SELECT t FROM TcSubgiro t")
public class TcSubgiro extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 4869257909089489109L;
	private String clave;
	private String descripcion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_NATGAS", nullable = true)
	private Natgas giro;

	public TcSubgiro() {
	}

	public List<String> isValid() {
		List<String> errors = new ArrayList<String>();
		if (clave == null || clave.equals("")) {
			errors.add("La clave es requerida");
		}
		if (descripcion == null || descripcion.equals("")) {
			errors.add("La descripción es requerida");
		}
		if (giro == null) {
			errors.add("Deve de seleccionar un giro comercial");
		}
		return errors;
	}

	public String busquedaSimple() {
		return "" + clave + descripcion + giro.getNomgas() + giro.getClvgas();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((clave == null) ? 0 : clave.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((giro == null) ? 0 : giro.hashCode());
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
		TcSubgiro other = (TcSubgiro) obj;
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
		if (giro == null) {
			if (other.giro != null)
				return false;
		} else if (!giro.equals(other.giro))
			return false;
		return true;
	}

	public Natgas getGiro() {
		return giro;
	}

	public void setGiro(Natgas giro) {
		this.giro = giro;
	}

	public String getClave() {
		return clave;
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
}