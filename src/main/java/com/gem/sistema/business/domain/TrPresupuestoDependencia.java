package com.gem.sistema.business.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the TR_PRESUPUESTO_DEPENDENCIA database table.
 * 
 */
@Entity
@Table(name = "TR_PRESUPUESTO_DEPENDENCIA")
@NamedQuery(name = "TrPresupuestoDependencia.findAll", query = "SELECT t FROM TrPresupuestoDependencia t")
public class TrPresupuestoDependencia extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DEPENDENCIA")
	private Catdep dependencia;

	@Column(name = "PRESUPUESTO_ASIGNADO")
	private BigDecimal presupuestoAsignado;

	@OneToMany(mappedBy = "presupuestoDependencia", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TwObra> obras = new ArrayList<>();

	public TrPresupuestoDependencia() {
		this.dependencia = new Catdep();
	}

	public Catdep getDependencia() {
		return dependencia;
	}

	public void setDependencia(Catdep dependencia) {
		this.dependencia = dependencia;
	}

	public BigDecimal getPresupuestoAsignado() {
		return presupuestoAsignado;
	}

	public void setPresupuestoAsignado(BigDecimal presupuestoAsignado) {
		this.presupuestoAsignado = presupuestoAsignado;
	}

	public List<TwObra> getObras() {
		return obras;
	}

	public void setObras(List<TwObra> obras) {
		this.obras = obras;
	}
}