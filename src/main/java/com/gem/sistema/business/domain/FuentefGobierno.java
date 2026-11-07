package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "FUENTESF_GOBIERBO")
@NamedQuery(name = "FuentefGobierno.findAll", query = "SELECT a FROM FuentefGobierno a")
public class FuentefGobierno extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cuenta;
	private String scta;
	private String sscta;
	private String ssscta;
	private String nombre;
	private Boolean used;

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getScta() {
		return scta;
	}

	public void setScta(String scta) {
		this.scta = scta;
	}

	public String getSscta() {
		return sscta;
	}

	public void setSscta(String sscta) {
		this.sscta = sscta;
	}

	public String getSsscta() {
		return ssscta;
	}

	public void setSsscta(String ssscta) {
		this.ssscta = ssscta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getUsed() {
		return used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cuenta == null) ? 0 : cuenta.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((scta == null) ? 0 : scta.hashCode());
		result = prime * result + ((sscta == null) ? 0 : sscta.hashCode());
		result = prime * result + ((ssscta == null) ? 0 : ssscta.hashCode());
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
		FuentefGobierno other = (FuentefGobierno) obj;
		if (cuenta == null) {
			if (other.cuenta != null)
				return false;
		} else if (!cuenta.equals(other.cuenta))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (scta == null) {
			if (other.scta != null)
				return false;
		} else if (!scta.equals(other.scta))
			return false;
		if (sscta == null) {
			if (other.sscta != null)
				return false;
		} else if (!sscta.equals(other.sscta))
			return false;
		if (ssscta == null) {
			if (other.ssscta != null)
				return false;
		} else if (!ssscta.equals(other.ssscta))
			return false;
		return true;
	}

	public String busquedaSimple() {
		return cuenta + scta + sscta + ssscta + nombre;
	}

}