package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.Nullable;
import javax.naming.Name;
import jakarta.persistence.*;

import org.apache.commons.lang3.StringUtils;

/**
 * The persistent class for the TC_MATRIZ_INGRESOS database table.
 *
 * @author Alfredo Neri
 *
 */
@Entity
@Table(name = "TC_MATRIZ_INGRESOS")
@NamedQuery(name = "TcMatrizIngreso.findAll", query = "SELECT t FROM TcMatrizIngreso t")
public class TcMatrizIngreso extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "TIPO_INGRESO")
	private String tipoIngreso;

	@Column(name="CONSECUTIVO")
	private Integer consecutivo;

	@Column(name = "TIPO_CRI")
	private Integer tipoCri;

	@Column(name = "CONCEPTO")
	private String concepto;

	@Column(name = "CARACTERISTICAS")
	private String caracteristicas;

	@Column(name = "CARGO")
	private String cargo;

	@Column(name = "CUENTA_CARGO")
	private String cuentaCargo;

	@Column(name = "ABONO")
	private String abono;

	@Column(name = "CUENTA_ABONO")
	private String cuentaAbono;

	@Column(name = "MEDIO_RECAUDACION")
	private String medioRecaudacion;

	public String getTipoIngreso() {
		return tipoIngreso;
	}

	public void setTipoIngreso(String tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
	}

	public Integer getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Integer getTipoCri() {
		return tipoCri;
	}

	public void setTipoCri(Integer tipoCri) {
		this.tipoCri = tipoCri;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCuentaCargo() {
		return cuentaCargo;
	}

	public void setCuentaCargo(String cuentaCargo) {
		this.cuentaCargo = cuentaCargo;
	}

	public String getAbono() {
		return abono;
	}

	public void setAbono(String abono) {
		this.abono = abono;
	}

	public String getCuentaAbono() {
		return cuentaAbono;
	}

	public void setCuentaAbono(String cuentaAbono) {
		this.cuentaAbono = cuentaAbono;
	}

	public String getMedioRecaudacion() {
		return medioRecaudacion;
	}

	public void setMedioRecaudacion(String medioRecaudacion) {
		this.medioRecaudacion = medioRecaudacion;
	}
}