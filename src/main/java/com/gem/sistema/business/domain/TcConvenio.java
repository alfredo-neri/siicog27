package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the TC_CONVENIOS database table.
 * 
 */
@Entity
@Table(name = "TC_CONVENIOS")
@NamedQuery(name = "TcConvenio.findAll", query = "SELECT t FROM TcConvenio t")
public class TcConvenio extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_ELABORACION")
	private Date fechaElaboracion;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CONTRATO")
	private TcContrato contrato;

	private BigDecimal importe;

	public TcConvenio() {
	}

	public Date getFechaElaboracion() {
		return this.fechaElaboracion;
	}

	public void setFechaElaboracion(Date fechaElaboracion) {
		this.fechaElaboracion = fechaElaboracion;
	}

	public TcContrato getContrato() {
		return contrato;
	}

	public void setContrato(TcContrato contrato) {
		this.contrato = contrato;
	}

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

}