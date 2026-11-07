package com.gem.sistema.business.domain;

import java.io.Serializable;
import jakarta.persistence.*;

import com.gem.sistema.annotation.IgnoredQuery;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TW_PROYECCION_RESULTADO database table.
 * 
 * @author Alfredo Neri
 *
 */
@Entity
@Table(name="TW_PROYECCION_RESULTADO")
@NamedQuery(name="TwProyeccionResultado.findAll", query="SELECT t FROM TwProyeccionResultado t")
public class TwProyeccionResultado extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String capturo;

	private String concepto;

	private String cuenta;

	@Column(name="CURRENT_YEAR")
	private BigDecimal currentYear;

	private String etiquetado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="ID_SECTOR")
	private Integer idSector;

	private String tipcta;

	private String tipo;

	@Column(name="YEAR_1")
	private BigDecimal year1;

	@Column(name="YEAR_2")
	private BigDecimal year2;

	@Column(name="YEAR_3")
	private BigDecimal year3;

	@Column(name="YEAR_4")
	private BigDecimal year4;

	@Column(name="YEAR_5")
	private BigDecimal year5;

	@Transient
	@IgnoredQuery
	private boolean bGuardar;
	
	public TwProyeccionResultado() {
	}

	public String getCapturo() {
		return this.capturo;
	}

	public void setCapturo(String capturo) {
		this.capturo = capturo;
	}

	public String getConcepto() {
		return this.concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public BigDecimal getCurrentYear() {
		return this.currentYear;
	}

	public void setCurrentYear(BigDecimal currentYear) {
		this.currentYear = currentYear;
	}

	public String getEtiquetado() {
		return this.etiquetado;
	}

	public void setEtiquetado(String etiquetado) {
		this.etiquetado = etiquetado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIdSector() {
		return this.idSector;
	}

	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

	public String getTipcta() {
		return this.tipcta;
	}

	public void setTipcta(String tipcta) {
		this.tipcta = tipcta;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getYear1() {
		return this.year1;
	}

	public void setYear1(BigDecimal year1) {
		this.year1 = year1;
	}

	public BigDecimal getYear2() {
		return this.year2;
	}

	public void setYear2(BigDecimal year2) {
		this.year2 = year2;
	}

	public BigDecimal getYear3() {
		return this.year3;
	}

	public void setYear3(BigDecimal year3) {
		this.year3 = year3;
	}

	public BigDecimal getYear4() {
		return this.year4;
	}

	public void setYear4(BigDecimal year4) {
		this.year4 = year4;
	}

	public BigDecimal getYear5() {
		return this.year5;
	}

	public void setYear5(BigDecimal year5) {
		this.year5 = year5;
	}

	public boolean isbGuardar() {
		return bGuardar;
	}

	public void setbGuardar(boolean bGuardar) {
		this.bGuardar = bGuardar;
	}

}