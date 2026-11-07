package com.gem.sistema.business.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;

public class Pm5911DTO {

	private Integer idRow = BigInteger.ZERO.intValue();
	private Integer semestre = 0;
	private String fechaIng = StringUtils.EMPTY;
	private String titulo = BigDecimal.ZERO.toString();
	private String experiencia = BigDecimal.ZERO.toString();
	private String certificacion = BigDecimal.ZERO.toString();
	private String capturo = StringUtils.EMPTY;
	private Integer idAnio = BigInteger.ZERO.intValue();
	private Integer idSector = BigDecimal.ONE.intValue();

	public Pm5911DTO() {
	}

	

	public Pm5911DTO(Integer idRow, Integer semestre, String fechaIng, String titulo, String experiencia,
			String certificacion, String capturo, Integer idAnio, Integer idSector) {
		super();
		this.idRow = idRow;
		this.semestre = semestre;
		this.fechaIng = fechaIng;
		this.titulo = titulo;
		this.experiencia = experiencia;
		this.certificacion = certificacion;
		this.capturo = capturo;
		this.idAnio = idAnio;

		this.idSector = idSector;
	}

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

	

	public String getCertificacion() {
		return certificacion;
	}

	public void setCertificacion(String certificacion) {
		this.certificacion = certificacion;
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

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
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

}
