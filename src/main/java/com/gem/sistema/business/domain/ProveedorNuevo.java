package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

import com.gem.sistema.util.Regex;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "TC_PROVEEDORES_NUEVO")
@NamedQuery(name = "ProveedorNuevo.findAll", query = "SELECT p FROM ProveedorNuevo p")
public class ProveedorNuevo extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 72598209374040475L;
	private static final String DEFAULT_VALUE = "NO APLICA";
	// Lista de tipos posibles
	public final static List<String> TIPOS = Arrays.asList("Proveedor", "Contratista");

	private String rfc;
	@Column(name = "NOMBRE_PROVEEDOR")
	private String nombreProveedor;
	private String curp;
	private String nacionalidad;
	@Column(name = "DOMICILIO_FISCAL")
	private String domicilioFiscal;
	@Column(name = "DOMICILIO_ESTADO")
	private String domicilioEstado;
	private String telefono;
	private String telefax;
	private String correo;
	private String tipo;
	@Column(name = "NOMBRE_PROPIETARIO")
	private String nombrePropietario;

	@Column(name = "INSTRUMENTO_PERSONALIDAD")
	private String instrumentoPersonalidad;
	@Lob
	@Column(name = "INSTRUMENTO_PERSONALIDAD_DOC")
	private byte[] instrumentoPersonalidadDoc;
	@Column(name = "NOMBRE_REPRESENTANTE_LEGAL")
	private String nombreRepresentanteLegal;
	@Column(name = "INSTRUMENTO_ACREDITACION")
	private String instrumentoAcreditacion;
	@Lob
	@Column(name = "INSTRUMENTO_ACREDITACION_DOC")
	private byte[] instrumentoAcreditacionDoc;

	public ProveedorNuevo() {

		this.nombreProveedor = StringUtils.EMPTY;
	}

	public List<String> isValid() {
		List<String> errors = new ArrayList<String>();
		if (this != null) {
			if (nombreProveedor == null || nombreProveedor.equals(""))
				errors.add("El nombre del proveedor requerido");
//			if (domicilioFiscal == null || domicilioFiscal.equals(""))
//				errors.add("Debe de ingresar un domicilio fiscal ");
//			if (domicilioEstado == null || domicilioEstado.equals(""))
//				errors.add("Debe de ingresar un domicilio en el estado valido");
			if (telefono == null || telefono.equals(""))
				errors.add("Debe de ingresar un telefono valido");
//			if (tipo == null || tipo.equals("") || !ProveedorNuevo.TIPOS.contains(tipo))
//				errors.add("Debe de seleccionar un tipo valido");
//			if (curp == null || curp.equals("") || !Regex.isValid(Regex.CURP, curp))
//				errors.add("Debe de ingresar una curp valida");
			if (rfc == null || rfc.equals("") || !Regex.isValid(Regex.RFC, rfc))
				errors.add("Debe de ingresar un rfc valido");
//			if (nombreRepresentanteLegal == null || nombreRepresentanteLegal.equals(""))
//				errors.add("Debe de ingresar un nombre de reprentante legal");
//			if (correo == null || correo.equals("") || !Regex.isValid(Regex.EMAIL, correo))
//				errors.add("Debe de ingresar un correo valido");
//			if (nacionalidad == null || nacionalidad.equals(""))
//				errors.add("Debe de ingresar una nacionalidad");
		}
		return errors;
	}

	@PrePersist
	private void setNoEmptyNotRequiered() {
		if (telefax == null || telefax.equals(""))
			telefax = DEFAULT_VALUE;
		if (instrumentoAcreditacion == null || instrumentoAcreditacion.equals(""))
			instrumentoAcreditacion = DEFAULT_VALUE;
		if (instrumentoPersonalidad == null || instrumentoPersonalidad.equals(""))
			instrumentoPersonalidad = DEFAULT_VALUE;
		if (nombrePropietario == null || nombrePropietario.equals(""))
			nombrePropietario = DEFAULT_VALUE;
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

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getDomicilioFiscal() {
		return domicilioFiscal;
	}

	public void setDomicilioFiscal(String domicilioFiscal) {
		this.domicilioFiscal = domicilioFiscal;
	}

	public String getDomicilioEstado() {
		return domicilioEstado;
	}

	public void setDomicilioEstado(String domicilioEstado) {
		this.domicilioEstado = domicilioEstado;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTelefax() {
		return telefax;
	}

	public void setTelefax(String telefax) {
		this.telefax = telefax;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombrePropietario() {
		return nombrePropietario;
	}

	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}

	public String getNombreRepresentanteLegal() {
		return nombreRepresentanteLegal;
	}

	public void setNombreRepresentanteLegal(String nombreRepresentanteLegal) {
		this.nombreRepresentanteLegal = nombreRepresentanteLegal;
	}

	public String getInstrumentoAcreditacion() {
		return instrumentoAcreditacion;
	}

	public void setInstrumentoAcreditacion(String instrumentoAcreditacion) {
		this.instrumentoAcreditacion = instrumentoAcreditacion;
	}

	public byte[] getInstrumentoAcreditacionDoc() {
		return instrumentoAcreditacionDoc;
	}

	public void setInstrumentoAcreditacionDoc(byte[] instrumentoAcreditacionDoc) {
		this.instrumentoAcreditacionDoc = instrumentoAcreditacionDoc;
	}

	public String getInstrumentoPersonalidad() {
		return instrumentoPersonalidad;
	}

	public void setInstrumentoPersonalidad(String instrumentoPersonalidad) {
		this.instrumentoPersonalidad = instrumentoPersonalidad;
	}

	public byte[] getInstrumentoPersonalidadDoc() {
		return instrumentoPersonalidadDoc;
	}

	public void setInstrumentoPersonalidadDoc(byte[] instrumentoPersonalidadDoc) {
		this.instrumentoPersonalidadDoc = instrumentoPersonalidadDoc;
	}

	public static List<String> getTipos() {
		return TIPOS;
	}

	@Override
	public String toString() {
		return "Proveedor [rfc=" + rfc + ", nombreProveedor=" + nombreProveedor + ", curp=" + curp + ", nacionalidad="
				+ nacionalidad + ", domicilioFiscal=" + domicilioFiscal + ", domicilioEstado=" + domicilioEstado
				+ ", telefono=" + telefono + ", telefax=" + telefax + ", correo=" + correo + ", tipo=" + tipo
				+ ", nombrePropietario=" + nombrePropietario + ", instrumentoPersonalidad=" + instrumentoPersonalidad
				+ ", instrumentoPersonalidadDoc=" + Arrays.toString(instrumentoPersonalidadDoc)
				+ ", nombreRepresentanteLegal=" + nombreRepresentanteLegal + ", instrumentoAcreditacion="
				+ instrumentoAcreditacion + ", instrumentoAcreditacionDoc="
				+ Arrays.toString(instrumentoAcreditacionDoc) + "]";
	}

	public String busquedaSimple() {
		return rfc + nombreProveedor + curp + nacionalidad + domicilioFiscal + domicilioEstado + telefono + telefax
				+ correo + tipo + nombrePropietario + instrumentoPersonalidad + nombreRepresentanteLegal
				+ instrumentoAcreditacion;
	}
}