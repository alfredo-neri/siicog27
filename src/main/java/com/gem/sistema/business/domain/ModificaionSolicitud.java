package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "TC_MODIFICACION_SOLICITUD")
@Entity
public class ModificaionSolicitud extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -7472137173967107289L;
	@ManyToOne
	@JoinColumn(name = "ID_ADQUISICION", nullable = false)
	private TcAdquisicion adquisicion;

	@Lob
	private byte[] archivo;
	@Column(name = "NOMBRE_ARCHIVO")
	private String nombreArchivo;
	@Column(name = "TIPO_ARCHIVO")
	private String tipoArchivo;

	private Date fecha;
	private String usuario;

	public ModificaionSolicitud() {

	}

	public ModificaionSolicitud(TcAdquisicion adquisicion, byte[] archivo, String nombreArchivo, String tipoArchivo,
			Date fecha, String usuario) {
		super();
		this.adquisicion = adquisicion;
		this.archivo = archivo;
		this.nombreArchivo = nombreArchivo;
		this.tipoArchivo = tipoArchivo;
		this.fecha = fecha;
		this.usuario = usuario;
	}

	public TcAdquisicion getAdquisicion() {
		return adquisicion;
	}

	public void setAdquisicion(TcAdquisicion adquisicion) {
		this.adquisicion = adquisicion;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((adquisicion == null) ? 0 : adquisicion.hashCode());
		result = prime * result + Arrays.hashCode(archivo);
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((nombreArchivo == null) ? 0 : nombreArchivo.hashCode());
		result = prime * result + ((tipoArchivo == null) ? 0 : tipoArchivo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		ModificaionSolicitud other = (ModificaionSolicitud) obj;
		if (adquisicion == null) {
			if (other.adquisicion != null)
				return false;
		} else if (!adquisicion.equals(other.adquisicion))
			return false;
		if (!Arrays.equals(archivo, other.archivo))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (nombreArchivo == null) {
			if (other.nombreArchivo != null)
				return false;
		} else if (!nombreArchivo.equals(other.nombreArchivo))
			return false;
		if (tipoArchivo == null) {
			if (other.tipoArchivo != null)
				return false;
		} else if (!tipoArchivo.equals(other.tipoArchivo))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
