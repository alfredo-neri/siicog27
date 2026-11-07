package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * The persistent class for the TC_MATRIZ_NIVEL database table.
 * 
 * @author Alfredo Neri
 */
@Entity
@Table(name = "TC_NIVEL")
@NamedQuery(name = "TcNivel.findAll", query = "SELECT t FROM TcNivel t ")
public class TcNivel extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "ID_NIVEL")
	private TcTipoNivel tipoNivel;

	private String formula;

	private String frecuencia;

	@Column(name = "MEDIOS_VERIFICACION")
	private String mediosVerificacion;

	private String nombre;

	private String objetivo;

	private String supuesto;

	private String tipo;

	@Column(name = "CODIGO_INDICADOR")
	private String codigoIndicador;

	@Transient
	@ManyToOne()
	private Mir indicador;

	@Column(name = "ID_PADRE")
	private Long padre;

	@OneToMany(mappedBy = "nivel", cascade = CascadeType.ALL)
	private List<TrMatrizNivel> niveles;

	@Transient
	private List<TrMatrizNivel> actividades;

	@Transient
	private Boolean hasFicha = Boolean.FALSE;

	public TcTipoNivel getTipoNivel() {
		return tipoNivel;
	}

	public void setTipoNivel(TcTipoNivel tipoNivel) {
		this.tipoNivel = tipoNivel;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	public String getMediosVerificacion() {
		return mediosVerificacion;
	}

	public void setMediosVerificacion(String mediosVerificacion) {
		this.mediosVerificacion = mediosVerificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getSupuesto() {
		return supuesto;
	}

	public void setSupuesto(String supuesto) {
		this.supuesto = supuesto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getPadre() {
		return padre;
	}

	public void setPadre(Long padre) {
		this.padre = padre;
	}

	public List<TrMatrizNivel> getNiveles() {
		return niveles;
	}

	public void setNiveles(List<TrMatrizNivel> niveles) {
		this.niveles = niveles;
	}

	public String getCodigoIndicador() {
		return codigoIndicador;
	}

	public void setCodigoIndicador(String codigoIndicador) {
		this.codigoIndicador = codigoIndicador;
	}

	public Boolean getHasFicha() {
		return hasFicha;
	}

	public Mir getIndicador() {
		return indicador;
	}

	public void setIndicador(Mir indicador) {
		this.indicador = indicador;
	}

	public void setHasFicha(Boolean hasFicha) {
		this.hasFicha = hasFicha;
	}

	public List<TrMatrizNivel> getActividades() {
		return actividades;
	}

	public void setActividades(List<TrMatrizNivel> actividades) {
		this.actividades = actividades;
	}

}
