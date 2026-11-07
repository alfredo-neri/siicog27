package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.*;

@Entity
@Table(name = "TC_FIRMAS_DIGITALES")
@NamedQuery(name = "TcFirmaDigital.findAll", query = "SELECT t FROM TcFirmaDigital t")
public class TcFirmaDigital extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Lob
	private byte[] archivo;
	@Column(name = "NOMBRE_ARCHIVO")
	private String nombreArchivo;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO", nullable = false)
	private TcUsuario usuario;

	public TcFirmaDigital(byte[] archivo, TcUsuario usuario, String nombreArchivo) {
		this.archivo = archivo;
		this.usuario = usuario;
		this.nombreArchivo = nombreArchivo;
	}

	public TcFirmaDigital() {
	}

	@Override
	public String toString() {
		return "TcFirmaDigital [archivo=" + Arrays.toString(archivo) + ", nombreArchivo=" + nombreArchivo + "]";
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public TcUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(TcUsuario usuario) {
		this.usuario = usuario;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

}