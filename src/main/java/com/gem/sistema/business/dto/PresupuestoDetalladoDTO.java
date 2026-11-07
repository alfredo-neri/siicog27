package com.gem.sistema.business.dto;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public class PresupuestoDetalladoDTO {
	@Override
	public String toString() {
		return "PresupuestoDetalladoDTO [area=" + area + ", dependencia=" + dependencia + ", proyecto=" + proyecto
				+ ", fuente=" + fuente + ", partida=" + partida + ", total=" + total + ", reduccion=" + reduccion
				+ ", ampliacion=" + ampliacion + "]";
	}

	private Integer area = 0;
	private String dependencia = StringUtils.EMPTY;
	private String proyecto = StringUtils.EMPTY;
	private String fuente = StringUtils.EMPTY;
	private String partida = StringUtils.EMPTY;
	private BigDecimal total = BigDecimal.ZERO;
	private BigDecimal reduccion = BigDecimal.ZERO;
	private BigDecimal ampliacion=BigDecimal.ZERO;

	public PresupuestoDetalladoDTO() {

	}

	public BigDecimal getReduccion() {
		return reduccion;
	}

	public void setReduccion(BigDecimal reduccion) {
		this.reduccion = reduccion;
	}

	public PresupuestoDetalladoDTO(Integer area, String dependencia, String proyecto, String fuente, String partida,
			BigDecimal total) {

		this.area = area;
		this.dependencia = dependencia;
		this.proyecto = proyecto;
		this.fuente = fuente;
		this.partida = partida;
		this.total = total;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getPartida() {
		return partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	public BigDecimal getAmpliacion() {
		return ampliacion;
	}

	public void setAmpliacion(BigDecimal ampliacion) {
		this.ampliacion = ampliacion;
	}

}

