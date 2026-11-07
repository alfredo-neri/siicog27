package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 * The persistent class for the TW_INGRESO_PROPIOS_DETALLE database table.
 * 
 */
@Entity
@Table(name = "TW_INGRESO_PROPIOS_DETALLE")
@NamedQuery(name = "TwIngresoPropiosDetalle.findAll", query = "SELECT t FROM TwIngresoPropiosDetalle t")
public class TwIngresoPropiosDetalle extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_INGRESO")
	private long idIngreso;

	// bi-directional many-to-one association to Polide
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "CONPOL", referencedColumnName = "CONPOL"),
			@JoinColumn(name = "MESPOL", referencedColumnName = "MESPOL"),
			@JoinColumn(name = "RENPOL", referencedColumnName = "RENPOL"),
			@JoinColumn(name = "TIPPOL", referencedColumnName = "TIPPOL") })
	private Polide polide;

	private BigDecimal monto;

	public TwIngresoPropiosDetalle() {
	}
	
	

	public TwIngresoPropiosDetalle(long idIngreso, Polide polide, BigDecimal monto) {
		this.idIngreso = idIngreso;
		this.polide = polide;
		this.monto = monto;
	}



	public long getIdIngreso() {
		return this.idIngreso;
	}

	public void setIdIngreso(long idIngreso) {
		this.idIngreso = idIngreso;
	}

	public Polide getPolide() {
		return this.polide;
	}

	public void setPolide(Polide polide) {
		this.polide = polide;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

}