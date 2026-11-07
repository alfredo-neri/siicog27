package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the TW_CHEQUES database table.
 * 
 * @author Alfredo Neri
 *
 */
@Entity
@Table(name = "TW_CHEQUES")
@NamedQuery(name = "TwCheque.findAll", query = "SELECT t FROM TwCheque t")
public class TwCheque extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_ELABORO")
	private Date fechaElaboro;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_POLIDE")
	private Polide polide;

	@Column(name = "NUMERO_CHEQUE")
	private Integer numeroCheque;

	@Column(name = "MONTO_CHEQUE")
	private BigDecimal montoCheque;

	private String beneficiario;

	private String observaciones;

	private String userid;

	public TwCheque() {
	}

	public Date getFechaElaboro() {
		return this.fechaElaboro;
	}

	public void setFechaElaboro(Date fechaElaboro) {
		this.fechaElaboro = fechaElaboro;
	}

	public Integer getNumeroCheque() {
		return this.numeroCheque;
	}

	public Polide getPolide() {
		return polide;
	}

	public void setPolide(Polide polide) {
		this.polide = polide;
	}

	public void setNumeroCheque(Integer numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public BigDecimal getMontoCheque() {
		return montoCheque;
	}

	public void setMontoCheque(BigDecimal montoCheque) {
		this.montoCheque = montoCheque;
	}

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}