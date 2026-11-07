package com.gem.sistema.business.dto;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

/**
 * @author mateo
 *
 */
public class EaepecaldfDTO {
	private String clave = StringUtils.EMPTY;
	private String titulo;
	private BigDecimal aprobado = BigDecimal.ZERO;
	private BigDecimal pagado = BigDecimal.ZERO;
	private BigDecimal devengado = BigDecimal.ZERO;
	private BigDecimal ampliacion = BigDecimal.ZERO;
	private BigDecimal reduccion = BigDecimal.ZERO;
	private BigDecimal porEjercer = BigDecimal.ZERO;
	private BigDecimal ampreduccion = BigDecimal.ZERO;;
	private BigDecimal modificado = BigDecimal.ZERO;;

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public BigDecimal getAprobado() {
		return aprobado;
	}

	public void setAprobado(BigDecimal aprobado) {
		this.aprobado = aprobado;
	}

	public BigDecimal getPagado() {
		return pagado;
	}

	public void setPagado(BigDecimal pagado) {
		this.pagado = pagado;
	}

	public BigDecimal getDevengado() {
		return devengado;
	}

	public void setDevengado(BigDecimal devengado) {
		this.devengado = devengado;
	}

	public BigDecimal getAmpliacion() {
		return ampliacion;
	}

	public void setAmpliacion(BigDecimal ampliacion) {
		this.ampliacion = ampliacion;
	}

	public BigDecimal getReduccion() {
		return reduccion;
	}

	public void setReduccion(BigDecimal reduccion) {
		this.reduccion = reduccion;
	}

	public BigDecimal getPorEjercer() {
		return porEjercer;
	}

	public void setPorEjercer(BigDecimal porEjercer) {
		this.porEjercer = porEjercer;
	}

	public BigDecimal getAmpreduccion() {
		return ampreduccion;
	}

	public void setAmpreduccion(BigDecimal ampreduccion) {
		this.ampreduccion = ampreduccion;
	}

	public BigDecimal getModificado() {
		return modificado;
	}

	public void setModificado(BigDecimal modificado) {
		this.modificado = modificado;
	}

}
