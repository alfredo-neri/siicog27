package com.gem.sistema.business.domain;

import java.io.Serializable;
import jakarta.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * The persistent class for the TW_REGISTRO_PAGO_CHEQUES database table.
 * 
 */
@Entity
@Table(name="TW_REGISTRO_PAGO_CHEQUES")
@NamedQuery(name="TwRegistroPagoCheque.findAll", query="SELECT t FROM TwRegistroPagoCheque t")
public class TwRegistroPagoCheque extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String concepto;

	private String destinario;

	@DateTimeFormat(pattern = "dd/MMMM/YYYY")
	@Column(name="FECHA_IMPRESION")
	private Timestamp fechaImpresion = Timestamp.valueOf(LocalDateTime.now());
	
	private Integer folio;

	private BigDecimal monto;

	@Column(name="MONTO_LETRAS")
	private String montoLetras;

	@Column(name="NUMERO_PAGO")
	private String numeroPago;

	//bi-directional many-to-one association to TcTipoCheque
	@ManyToOne
	@JoinColumn(name="ID_TIPO_CHEQUE")
	private TcTipoCheque tcTipoCheque;

	//bi-directional many-to-one association to TcEstatusCheque
	@ManyToOne
	@JoinColumn(name="ID_ESTATUS")
	private TcEstatusCheque tcEstatusCheque;
	
	@Column(name = "CUENTA_BANCARIA")
	private String cuentaBancaria;
	
	@Column(name = "NOTAS_CHEQUES")
	private String notascheues;

	public TwRegistroPagoCheque() {
	}

	@ManyToOne
	@JoinColumn(name = "ID_MES")
	private TcPeriodo tcPeriodo;
	
	public String getConcepto() {
		return this.concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getDestinario() {
		return this.destinario;
	}

	public void setDestinario(String destinario) {
		this.destinario = destinario;
	}

	public Timestamp getFechaImpresion() {
		return this.fechaImpresion;
	}

	public void setFechaImpresion(Timestamp fechaImpresion) {
		this.fechaImpresion = fechaImpresion;
	}

	

	public BigDecimal getMonto() {
		return this.monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getMontoLetras() {
		return this.montoLetras;
	}

	public void setMontoLetras(String montoLetras) {
		this.montoLetras = montoLetras;
	}

	public String getNumeroPago() {
		return this.numeroPago;
	}

	public void setNumeroPago(String numeroPago) {
		this.numeroPago = numeroPago;
	}

	public TcTipoCheque getTcTipoCheque() {
		return this.tcTipoCheque;
	}

	public void setTcTipoCheque(TcTipoCheque tcTipoCheque) {
		this.tcTipoCheque = tcTipoCheque;
	}

	public TcEstatusCheque getTcEstatusCheque() {
		return this.tcEstatusCheque;
	}

	public void setTcEstatusCheque(TcEstatusCheque tcEstatusCheque) {
		this.tcEstatusCheque = tcEstatusCheque;
	}

	public TcPeriodo getTcPeriodo() {
		return tcPeriodo;
	}

	public void setTcPeriodo(TcPeriodo tcPeriodo) {
		this.tcPeriodo = tcPeriodo;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public String getNotascheues() {
		return notascheues;
	}

	public void setNotascheues(String notascheues) {
		this.notascheues = notascheues;
	}
	
	

}