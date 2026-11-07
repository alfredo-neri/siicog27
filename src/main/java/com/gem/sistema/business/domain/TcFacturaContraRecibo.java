package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the TcFacturaContraRecibo database table.
 * 
 */
@Entity
@Table(name = "TC_FACTURAS_CONTRA_RECIBOS")
public class TcFacturaContraRecibo extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal importe;
	@Column(name = "NUMERO_FACTURA")
	private String numeroFactura;
	@ManyToOne
	@JoinColumn(name = "ID_CONTRA_RECIBO", nullable = false)
	private ContraRecibo contraRecibo;

	public List<String> isValid() {
		List<String> errors = new ArrayList<String>();
		if (importe == null || importe.equals(BigDecimal.ZERO))
			errors.add("El importe no puede ser 0");
		if (numeroFactura == null || numeroFactura.equals(""))
			errors.add("El numero de factura es requerido");
		return errors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((contraRecibo == null) ? 0 : contraRecibo.hashCode());
		result = prime * result + ((importe == null) ? 0 : importe.hashCode());
		result = prime * result + ((numeroFactura == null) ? 0 : numeroFactura.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TcFacturaContraRecibo other = (TcFacturaContraRecibo) obj;
		if (contraRecibo == null) {
			if (other.contraRecibo != null)
				return false;
		} else if (!contraRecibo.equals(other.contraRecibo))
			return false;
		if (importe == null) {
			if (other.importe != null)
				return false;
		} else if (!importe.equals(other.importe))
			return false;
		if (numeroFactura == null) {
			if (other.numeroFactura != null)
				return false;
		} else if (!numeroFactura.equals(other.numeroFactura))
			return false;
		return true;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public ContraRecibo getContraRecibo() {
		return contraRecibo;
	}

	public void setContraRecibo(ContraRecibo contraRecibo) {
		this.contraRecibo = contraRecibo;
	}

}