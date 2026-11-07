package com.gem.sistema.business.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class IngresosPropiosDetalleDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String clave;
	private String nombre;
	private BigDecimal monto;

	public IngresosPropiosDetalleDTO() {

	}

	public IngresosPropiosDetalleDTO(String clave, String nombre, BigDecimal monto) {
		this.clave = clave;
		this.nombre = nombre;
		this.monto = monto;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

}
