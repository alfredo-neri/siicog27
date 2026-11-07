package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "TC_PROVEEDORES")
@NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p")
public class Proveedor extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 72598209374040475L;

	// Lista de tipos posibles
	public final static List<String> TIPOS = Arrays.asList("Proveedor", "Contratista");

	private String rfc;
	@Column(name = "NOMBRE_PROVEEDOR")
	private String nombreProveedor;
	private String domicilio;
	private String telefono;
	private String tipo;

	public Proveedor() {

	}

	public Proveedor(String rfc, String nombreProveedor, String domicilio, String telefono, String tipo) {
		this.rfc = rfc;
		this.nombreProveedor = nombreProveedor;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.tipo = tipo;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<String> isValid() {
		List<String> errors = new ArrayList<String>();
		if (this != null) {
			if (nombreProveedor == null || nombreProveedor.equals(""))
				errors.add("El nombre del proveedor requerido");
			if (rfc == null || rfc.equals(""))
				errors.add("Debe de ingresar un RFC");
			if (domicilio == null || domicilio.equals(""))
				errors.add("Debe de ingresar un domicilio");
			if (telefono == null || telefono.equals(""))
				errors.add("Debe de ingresar un telefono valido");
			if (tipo == null || tipo.equals("") || !Proveedor.TIPOS.contains(tipo))
				errors.add("Debe de seleccionar un tipo valido");
		}
		return errors;
	}
}