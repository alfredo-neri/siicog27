package com.gem.sistema.business.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;


public class MediadorDTO {

	private Integer idRow = BigInteger.ZERO.intValue();
	private Integer semestre = 0;
	private String fechaIng = StringUtils.EMPTY;
	private String titulo = BigDecimal.ZERO.toString();
	private String certificacion = BigInteger.ZERO.toString();
	private String estudios = BigInteger.ZERO.toString();
	private String capturo = StringUtils.EMPTY;
	private Integer idAnio = BigInteger.ZERO.intValue();
	private Integer idSector = BigDecimal.ONE.intValue();

	public Integer getIdRow() {
		return idRow;
	}

	public void setIdRow(Integer idRow) {
		this.idRow = idRow;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public String getFechaIng() {
		return fechaIng;
	}

	public void setFechaIng(String fechaIng) {
		this.fechaIng = fechaIng;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCapturo() {
		return capturo;
	}

	public void setCapturo(String capturo) {
		this.capturo = capturo;
	}

	public Integer getIdAnio() {
		return idAnio;
	}

	public void setIdAnio(Integer idAnio) {
		this.idAnio = idAnio;
	}

	public Integer getIdSector() {
		return idSector;
	}

	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

	@Override
	public String toString() {
		return "MediadorDTO [idRow=" + idRow + ", semestre=" + semestre + ", fechaIng=" + fechaIng + ", titulo="
				+ titulo + ", certificacion=" + certificacion + ", estudios=" + estudios + ", capturo=" + capturo
				+ ", idAnio=" + idAnio + ", idSector=" + idSector + "]";
	}

	public MediadorDTO(Integer idRow, Integer semestre, String fechaIng, String titulo, String certificacion,
			String estudios, String capturo, Integer idAnio, Integer idSector) {
		super();
		this.idRow = idRow;
		this.semestre = semestre;
		this.fechaIng = fechaIng;
		this.titulo = titulo;
		this.certificacion = certificacion;
		this.estudios = estudios;
		this.capturo = capturo;
		this.idAnio = idAnio;
		this.idSector = idSector;
	}

	public MediadorDTO() {
	}

	public String getCertificacion() {
		return certificacion;
	}

	public void setCertificacion(String certificacion) {
		this.certificacion = certificacion;
	}

	public String getEstudios() {
		return estudios;
	}

	public void setEstudios(String estudios) {
		this.estudios = estudios;
	}

}
