package com.gem.sistema.business.dto;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class Pm3711DTO {

	private Integer idEtq;
	private Integer semestre = 1;
	private Integer idSector;
	private Integer titulo = 0;
	private Integer experiencia = 0;
	private Integer certificacion = 0;
	private String capturo = StringUtils.EMPTY;
	private String fechaIng;
	private Long idAnio;
	private Long idRef;

	public Integer getIdEtq() {
		return idEtq;
	}

	public void setIdEtq(Integer idEtq) {
		this.idEtq = idEtq;
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

	public Integer getTitulo() {
		return titulo;
	}

	public void setTitulo(Integer titulo) {
		this.titulo = titulo;
	}

	public Integer getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(Integer experiencia) {
		this.experiencia = experiencia;
	}

	public Integer getCertificacion() {
		return certificacion;
	}

	public void setCertificacion(Integer certificacion) {
		this.certificacion = certificacion;
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

	public Long getIdAnio() {
		return idAnio;
	}

	public void setIdAnio(Long idAnio) {
		this.idAnio = idAnio;
	}

	public Long getIdRef() {
		return idRef;
	}

	public void setIdRef(Long idRef) {
		this.idRef = idRef;
	}

}
