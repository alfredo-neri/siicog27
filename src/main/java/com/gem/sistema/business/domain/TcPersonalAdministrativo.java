package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.gem.sistema.util.Regex;

/**
 * The persistent class for the TC_PERSONAL_ADMINISTRATIVO database table.
 * 
 */
@Entity
@Table(name = "TC_PERSONAL_ADMINISTRATIVO")
@NamedQuery(name = "TcPersonalAdministrativo.findAll", query = "SELECT t FROM TcPersonalAdministrativo t")
public class TcPersonalAdministrativo extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -3578970637296380778L;
	@ManyToOne
	@JoinColumn(name = "ID_UNIDAD_ADMINISTRATIVA", nullable = false)
	private TcAreaAdministrativa administrativa;

	private String nombre;
	private String domicilio;
	private String cargo;
	private String telefono;
	private String telfax;
	private String email;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((administrativa == null) ? 0 : administrativa.hashCode());
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((domicilio == null) ? 0 : domicilio.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + ((telfax == null) ? 0 : telfax.hashCode());
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
		TcPersonalAdministrativo other = (TcPersonalAdministrativo) obj;
		if (administrativa == null) {
			if (other.administrativa != null)
				return false;
		} else if (!administrativa.equals(other.administrativa))
			return false;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		if (domicilio == null) {
			if (other.domicilio != null)
				return false;
		} else if (!domicilio.equals(other.domicilio))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (telfax == null) {
			if (other.telfax != null)
				return false;
		} else if (!telfax.equals(other.telfax))
			return false;
		return true;
	}

	public TcPersonalAdministrativo() {
		TcAreaAdministrativa administrativa = new TcAreaAdministrativa();
		this.administrativa = administrativa;
	}
	
	public TcAreaAdministrativa getAdministrativa() {
		return administrativa;
	}

	public void setAdministrativa(TcAreaAdministrativa administrativa) {
		this.administrativa = administrativa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTelfax() {
		return telfax;
	}

	public void setTelfax(String telfax) {
		this.telfax = telfax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String busquedaSimple() {
		return nombre + cargo;
	}

	public List<String> isValid() {
		List<String> errors = new ArrayList<String>();
		if (nombre == null || nombre.equals("")) {
			errors.add("El nombre  es requerido");
		}
		if (cargo == null || cargo.equals("")) {
			errors.add("El cargo es requerido");
		}
		if (domicilio == null || domicilio.equals("")) {
			errors.add("El domicilio  es requerido");
		}
		if (telefono == null || telefono.equals("")) {
			errors.add("El telefono  es requerido");
		}
		if (email == null || email.equals("")) {
			errors.add("El email es requerido");
		} else if (!Regex.isValid(Regex.EMAIL, email)) {
			errors.add("El email no tiene un formato valido");
		}
		return errors;
	}
}