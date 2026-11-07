package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;

/**
 * The persistent class for the TC_ARTICULOS_CONTRATOS database table.
 * 
 */
@Entity
@Table(name = "TC_ARTICULOS_CONTRATOS")
@NamedQuery(name = "TcArticulosContrato.findAll", query = "SELECT t FROM TcArticulosContrato t")
public class TcArticulosContrato extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ARTICULO")
	private TcArticulosSA articulo;

	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CONTRATO")
	private TcContrato contrato;

	private String observaciones;

	private BigDecimal precio;

	private BigDecimal iva;

	private BigDecimal total;

	public TcArticulosContrato() {
		this.iva = new BigDecimal(16);
		this.total = BigDecimal.ZERO;
		this.precio = BigDecimal.ZERO;
	}

	@PrePersist
	@PreUpdate
	public void persist() {
		if (StringUtils.isEmpty(this.observaciones))
			this.observaciones = StringUtils.EMPTY;

		if (this.precio == null)
			this.precio = BigDecimal.ZERO;

		if (this.total == null)
			this.total = BigDecimal.ZERO;

		if (this.iva == null)
			this.iva = BigDecimal.ZERO;

	}

	public TcArticulosSA getArticulo() {
		return articulo;
	}

	public void setArticulo(TcArticulosSA articulo) {
		this.articulo = articulo;
	}

	public TcContrato getContrato() {
		return contrato;
	}

	public void setContrato(TcContrato contrato) {
		this.contrato = contrato;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}