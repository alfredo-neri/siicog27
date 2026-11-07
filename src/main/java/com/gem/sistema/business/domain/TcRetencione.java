package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * The persistent class for the TC_RETENCIONES database table.
 * 
 */
@Entity
@Table(name = "TC_RETENCIONES")
@NamedQuery(name = "TcRetencione.findAll", query = "SELECT t FROM TcRetencione t")
public class TcRetencione extends AbstractEntity implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Column(name = "CAMPO_1")
	private String campo1 = StringUtils.EMPTY;

	@Column(name = "CAMPO_2")
	private String campo2 = StringUtils.EMPTY;

	@Column(name = "CAMPO_3")
	private BigDecimal campo3 = BigDecimal.ZERO;

	@Column(name = "CAMPO_4")
	private BigDecimal campo4 = BigDecimal.ZERO;

	@Column(name = "DATOS_BENEFICIARIO")
	private String datosBeneficiario = StringUtils.EMPTY;

	@Column(name = "ID_POLIDE")
	private Long idPolide;

	@Column(name = "ID_SECTOR")
	private Long idSector;

	@Column(name = "IMPORTE_SIN_IVA")
	private BigDecimal importeSinIva = BigDecimal.ZERO;

	@Column(name = "NUM_CONTRATO")
	private String numContrato =  StringUtils.EMPTY;

	public TcRetencione() {
	}

	public String getCampo1() {
		return this.campo1;
	}

	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}

	public String getCampo2() {
		return this.campo2;
	}

	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}

	public BigDecimal getCampo3() {
		return this.campo3;
	}

	public void setCampo3(BigDecimal campo3) {
		this.campo3 = campo3;
	}

	public BigDecimal getCampo4() {
		return this.campo4;
	}

	public void setCampo4(BigDecimal campo4) {
		this.campo4 = campo4;
	}

	public String getDatosBeneficiario() {
		return this.datosBeneficiario;
	}

	public void setDatosBeneficiario(String datosBeneficiario) {
		this.datosBeneficiario = datosBeneficiario;
	}

	public Long getIdPolide() {
		return this.idPolide;
	}

	public void setIdPolide(Long idPolide) {
		this.idPolide = idPolide;
	}

	public Long getIdSector() {
		return this.idSector;
	}

	public void setIdSector(Long idSector) {
		this.idSector = idSector;
	}

	public BigDecimal getImporteSinIva() {
		return this.importeSinIva;
	}

	public void setImporteSinIva(BigDecimal importeSinIva) {
		this.importeSinIva = importeSinIva;
	}

	public String getNumContrato() {
		return this.numContrato;
	}

	public void setNumContrato(String numContrato) {
		this.numContrato = numContrato;
	}

}