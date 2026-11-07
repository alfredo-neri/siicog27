package com.gem.sistema.business.dto;

import org.apache.commons.lang3.StringUtils;

public class CompetenciaLaboralGenericDTO {
	private Integer idrow = 0;
	private Integer semestre = 0;
	private String fechaing= StringUtils.EMPTY;
	private Integer titulo = 0;
	private String experiencia = StringUtils.EMPTY;
	private Integer certificacion= 0;
	private String capturo = StringUtils.EMPTY;
	private Integer idanio= 0;
	private Integer idref= 0;
	private Integer idsector= 0;
	private Integer estudios= 0;
	private Integer cursos= 0;
	private String nombre = StringUtils.EMPTY;
	private String cargo= StringUtils.EMPTY;
	private Integer issemym= 0;
	private Integer constancia= 0;
	private Integer antecedentes= 0;
	private Integer diplomado = 0;

	public CompetenciaLaboralGenericDTO() {
	}

	public CompetenciaLaboralGenericDTO(String capturo, Integer idsector) {
		super();
		this.capturo = capturo;
		this.idsector = idsector;
	}

	public Integer getIdrow() {
		return idrow;
	}

	public void setIdrow(Integer idrow) {
		this.idrow = idrow;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public String getFechaing() {
		return fechaing;
	}

	public void setFechaing(String fechaing) {
		this.fechaing = fechaing;
	}

	public Integer getTitulo() {
		return titulo;
	}

	public void setTitulo(Integer titulo) {
		this.titulo = titulo;
	}

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
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

	public Integer getIdanio() {
		return idanio;
	}

	public void setIdanio(Integer idanio) {
		this.idanio = idanio;
	}

	public Integer getIdref() {
		return idref;
	}

	public void setIdref(Integer idref) {
		this.idref = idref;
	}

	public Integer getIdsector() {
		return idsector;
	}

	public void setIdsector(Integer idsector) {
		this.idsector = idsector;
	}

	public Integer getEstudios() {
		return estudios;
	}

	public void setEstudios(Integer estudios) {
		this.estudios = estudios;
	}

	public Integer getCursos() {
		return cursos;
	}

	public void setCursos(Integer cursos) {
		this.cursos = cursos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Integer getIssemym() {
		return issemym;
	}

	public void setIssemym(Integer issemym) {
		this.issemym = issemym;
	}

	public Integer getConstancia() {
		return constancia;
	}

	public void setConstancia(Integer constancia) {
		this.constancia = constancia;
	}

	public Integer getAntecedentes() {
		return antecedentes;
	}

	public void setAntecedentes(Integer antecedentes) {
		this.antecedentes = antecedentes;
	}

	public Integer getDiplomado() {
		return diplomado;
	}

	public void setDiplomado(Integer diplomado) {
		this.diplomado = diplomado;
	}

}
