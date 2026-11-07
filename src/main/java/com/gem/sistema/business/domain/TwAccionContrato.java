package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the TW_ACCION_CONTRATO database table.
 * 
 */
@Entity
@Table(name = "TW_ACCION_CONTRATO")
@NamedQuery(name = "TwAccionContrato.findAll", query = "SELECT t FROM TwAccionContrato t")
public class TwAccionContrato extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_AT")
	private Date createAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_AREA_ACCION")
	private TrAreaAccion areaAccion;

	@OneToOne()
	@JoinColumn(name = "ID_CONTRATO")
	private TcContrato contrato;

	@JsonIgnore
	@OneToMany(mappedBy = "accionContrato", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TwContratoFactura> facturas = new ArrayList<>();

	public TwAccionContrato() {
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public TrAreaAccion getAreaAccion() {
		return areaAccion;
	}

	public void setAreaAccion(TrAreaAccion areaAccion) {
		this.areaAccion = areaAccion;
	}

	public TcContrato getContrato() {
		return contrato;
	}

	public void setContrato(TcContrato contrato) {
		this.contrato = contrato;
	}

	public List<TwContratoFactura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<TwContratoFactura> facturas) {
		this.facturas = facturas;
	}

}