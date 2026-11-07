package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;

/**
 * The persistent class for the TC_FICHA database table.
 * 
 * @author Alfredo Neri
 *
 */
@Entity
@Table(name = "TC_FICHA")
@NamedQuery(name = "TcFicha.findAll", query = "SELECT t FROM TcFicha t")
public class TcFicha extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MATRIZ_NIVEL")
	private TrMatrizNivel matrizNivel;

	private String interpretacion;
	private String dimension;
	private String cobertura;
	private Integer ambito;
	private BigDecimal factor;

	@Column(name = "DESCRIPCION_FACTOR")
	private String descripcionFactor;

	@Column(name = "DESCRIPCION_RESULTADOS")
	private String descripcionResultados;

	@Column(name = "DESCRIPCION_META_ANUAL")
	private String descripcionMetaAnual;

	@Column(name = "EVALUACION_INDICADOR")
	private String evaluacionIndicador;

	@Column(name = "LINEA_BASE")
	private String lineaBase;

	@Column(name = "METAS_ACCION")
	private String metasAccion;

	@OneToMany(targetEntity = TcFichaTrimestre.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "ID_FICHA", referencedColumnName = "id")
	private List<TcFichaTrimestre> trimestres = new ArrayList<>();

	@OneToMany(targetEntity = TcFichaVariable.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "ID_FICHA", referencedColumnName = "id")
	private List<TcFichaVariable> variables = new ArrayList<>();

	public TcFicha() {
		this.factor = BigDecimal.ZERO;
		this.lineaBase = StringUtils.EMPTY;
		this.dimension = StringUtils.EMPTY;
		this.cobertura = StringUtils.EMPTY;
		this.metasAccion = StringUtils.EMPTY;
		this.interpretacion = StringUtils.EMPTY;
		this.ambito = BigInteger.ZERO.intValue();
		this.descripcionFactor = StringUtils.EMPTY;
		this.evaluacionIndicador = StringUtils.EMPTY;
		this.evaluacionIndicador = StringUtils.EMPTY;
		this.descripcionMetaAnual = StringUtils.EMPTY;
		this.descripcionResultados = StringUtils.EMPTY;
	}

	public String getInterpretacion() {
		return interpretacion;
	}

	public void setInterpretacion(String interpretacion) {
		this.interpretacion = interpretacion;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getCobertura() {
		return cobertura;
	}

	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
	}

	public Integer getAmbito() {
		return ambito;
	}

	public void setAmbito(Integer ambito) {
		this.ambito = ambito;
	}

	public BigDecimal getFactor() {
		return factor;
	}

	public void setFactor(BigDecimal factor) {
		this.factor = factor;
	}

	public String getDescripcionFactor() {
		return descripcionFactor;
	}

	public void setDescripcionFactor(String descripcionFactor) {
		this.descripcionFactor = descripcionFactor;
	}

	public String getDescripcionResultados() {
		return descripcionResultados;
	}

	public void setDescripcionResultados(String descripcionResultados) {
		this.descripcionResultados = descripcionResultados;
	}

	public String getDescripcionMetaAnual() {
		return descripcionMetaAnual;
	}

	public void setDescripcionMetaAnual(String descripcionMetaAnual) {
		this.descripcionMetaAnual = descripcionMetaAnual;
	}

	public String getEvaluacionIndicador() {
		return evaluacionIndicador;
	}

	public void setEvaluacionIndicador(String evaluacionIndicador) {
		this.evaluacionIndicador = evaluacionIndicador;
	}

	public String getLineaBase() {
		return lineaBase;
	}

	public void setLineaBase(String lineaBase) {
		this.lineaBase = lineaBase;
	}

	public String getMetasAccion() {
		return metasAccion;
	}

	public void setMetasAccion(String metasAccion) {
		this.metasAccion = metasAccion;
	}

	public List<TcFichaTrimestre> getTrimestres() {
		return trimestres;
	}

	public void setTrimestres(List<TcFichaTrimestre> trimestres) {
		this.trimestres = trimestres;
	}

	public List<TcFichaVariable> getVariables() {
		return variables;
	}

	public void setVariables(List<TcFichaVariable> variables) {
		this.variables = variables;
	}

	public TrMatrizNivel getMatrizNivel() {
		return matrizNivel;
	}

	public void setMatrizNivel(TrMatrizNivel matrizNivel) {
		this.matrizNivel = matrizNivel;
	}

}