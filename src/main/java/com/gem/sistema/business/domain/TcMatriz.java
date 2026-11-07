package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import org.apache.commons.lang3.StringUtils;

/**
 * The persistent class for the TC_MATRIZ database table.
 * 
 * @author Alfredo Neri
 *
 */
@Entity
@Table(name = "TC_MATRIZ")
@NamedQuery(name = "TcMatriz.findAll", query = "SELECT t FROM TcMatriz t")
public class TcMatriz extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "ID_DEPENDENCIA")
	private Catdgm dependencia;

	@ManyToOne
	@Nullable
	@JoinColumn(name = "ID_DEP_AUX")
	private Catdaa dependenciaAuxiliar;

	@ManyToOne
	@JoinColumn(name = "ID_PROYECTO")
	private Muninep proyecto;

	@ManyToOne
	@JoinColumn(name = "ID_TEMA")
	private Cpd tema;

	@Column(name = "PROGRAMA")
	private String programa;

	@Column(name = "ID_SECTOR")
	private Integer sector;

	@OneToMany(mappedBy = "matriz")
	List<TrMatrizNivel> niveles;

	@Transient
	private String programaDescripcion = StringUtils.EMPTY;

	@Transient
	private Boolean existXcatpro = Boolean.FALSE;

	@Transient
	private Boolean canUpdateIndicador = Boolean.TRUE;

	@Transient
	private Boolean headerFull = Boolean.FALSE;

	@Transient
	private TrMatrizNivel finalidad;

	@Transient
	private TrMatrizNivel proposito;

	@Transient
	private List<TrMatrizNivel> componentes;

	public TcMatriz() {
	}

	public Catdgm getDependencia() {
		return dependencia;
	}

	public void setDependencia(Catdgm dependencia) {
		this.dependencia = dependencia;
	}

	public Catdaa getDependenciaAuxiliar() {
		return dependenciaAuxiliar;
	}

	public void setDependenciaAuxiliar(Catdaa dependenciaAuxiliar) {
		this.dependenciaAuxiliar = dependenciaAuxiliar;
	}

	public Muninep getProyecto() {
		return proyecto;
	}

	public void setProyecto(Muninep proyecto) {
		this.proyecto = proyecto;
	}

	public Cpd getTema() {
		return tema;
	}

	public void setTema(Cpd tema) {
		this.tema = tema;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public Integer getSector() {
		return sector;
	}

	public void setSector(Integer sector) {
		this.sector = sector;
	}

	public String getProgramaDescripcion() {
		return programaDescripcion;
	}

	public void setProgramaDescripcion(String programaDescripcion) {
		this.programaDescripcion = programaDescripcion;
	}

	public Boolean getExistXcatpro() {
		return existXcatpro;
	}

	public void setExistXcatpro(Boolean existXcatpro) {
		this.existXcatpro = existXcatpro;
	}

	public Boolean getCanUpdateIndicador() {
		return canUpdateIndicador;
	}

	public void setCanUpdateIndicador(Boolean canUpdateIndicador) {
		this.canUpdateIndicador = canUpdateIndicador;
	}

	public List<TrMatrizNivel> getNiveles() {
		return niveles;
	}

	public void setNiveles(List<TrMatrizNivel> niveles) {
		this.niveles = niveles;
	}

	public Boolean getHeaderFull() {
		return headerFull;
	}

	public void setHeaderFull(Boolean headerFull) {
		this.headerFull = headerFull;
	}

	public TrMatrizNivel getFinalidad() {
		return finalidad;
	}

	public void setFinalidad(TrMatrizNivel finalidad) {
		this.finalidad = finalidad;
	}

	public TrMatrizNivel getProposito() {
		return proposito;
	}

	public void setProposito(TrMatrizNivel proposito) {
		this.proposito = proposito;
	}

	public List<TrMatrizNivel> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<TrMatrizNivel> componentes) {
		this.componentes = componentes;
	}

}