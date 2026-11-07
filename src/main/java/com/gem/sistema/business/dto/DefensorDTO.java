package com.gem.sistema.business.dto;

import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;

public class DefensorDTO {

	private Integer idRow;
	private String fechaIng = StringUtils.EMPTY;
	private String titulo = BigInteger.ZERO.toString();
	private String estudios = BigInteger.ZERO.toString();
	private Integer semestre = BigInteger.ZERO.intValue();
	private String capturo = StringUtils.EMPTY;
	private Integer idSector = BigInteger.ZERO.intValue();

	public Integer getIdRow() {
		return idRow;
	}

	public void setIdRow(Integer idRow) {
		this.idRow = idRow;
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

	public String getEstudios() {
		return estudios;
	}

	public void setEstudios(String estudios) {
		this.estudios = estudios;
	}
	
	

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public String getCapturo() {
		return capturo;
	}

	public void setCapturo(String capturo) {
		this.capturo = capturo;
	}

	public Integer getIdSector() {
		return idSector;
	}

	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

	public DefensorDTO(Integer idRow, String fechaIng, String titulo, String estudios) {
		super();
		this.idRow = idRow;
		this.fechaIng = fechaIng;
		this.titulo = titulo;
		this.estudios = estudios;
	}

	@Override
	public String toString() {
		return "DefensorDTO [idRow=" + idRow + ", fechaIng=" + fechaIng + ", titulo=" + titulo + ", estudios="
				+ estudios + "]";
	}

	public DefensorDTO() {

	}

}
