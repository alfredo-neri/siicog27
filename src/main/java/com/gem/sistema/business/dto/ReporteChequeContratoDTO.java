package com.gem.sistema.business.dto;

import java.math.BigDecimal;

public class ReporteChequeContratoDTO {
	
	private Integer folio;
	private String destCheque;
	private String conceptoCheque;
	private BigDecimal montoCheque;
	private String numeroPago;
	private String tipoCheque;
	private String estatusCheque;
	private String mesCheque;
	private String numeroContrato;
	private String conceptoContrato;
	private BigDecimal montoContrato;
	public Integer getFolio() {
		return folio;
	}
	public void setFolio(Integer folio) {
		this.folio = folio;
	}
	public String getDestCheque() {
		return destCheque;
	}
	public void setDestCheque(String destCheque) {
		this.destCheque = destCheque;
	}
	public String getConceptoCheque() {
		return conceptoCheque;
	}
	public void setConceptoCheque(String conceptoCheque) {
		this.conceptoCheque = conceptoCheque;
	}
	public BigDecimal getMontoCheque() {
		return montoCheque;
	}
	public void setMontoCheque(BigDecimal montoCheque) {
		this.montoCheque = montoCheque;
	}
	public String getNumeroPago() {
		return numeroPago;
	}
	public void setNumeroPago(String numeroPago) {
		this.numeroPago = numeroPago;
	}
	public String getTipoCheque() {
		return tipoCheque;
	}
	public void setTipoCheque(String tipoCheque) {
		this.tipoCheque = tipoCheque;
	}
	public String getEstatusCheque() {
		return estatusCheque;
	}
	public void setEstatusCheque(String estatusCheque) {
		this.estatusCheque = estatusCheque;
	}
	public String getMesCheque() {
		return mesCheque;
	}
	public void setMesCheque(String mesCheque) {
		this.mesCheque = mesCheque;
	}
	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public String getConceptoContrato() {
		return conceptoContrato;
	}
	public void setConceptoContrato(String conceptoContrato) {
		this.conceptoContrato = conceptoContrato;
	}
	public BigDecimal getMontoContrato() {
		return montoContrato;
	}
	public void setMontoContrato(BigDecimal montoContrato) {
		this.montoContrato = montoContrato;
	}
	
	

}
