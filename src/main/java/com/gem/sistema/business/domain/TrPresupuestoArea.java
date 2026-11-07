package com.gem.sistema.business.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the TR_PRESUPUESTO_AREA database table.
 * 
 */
@Entity
@Table(name = "TR_PRESUPUESTO_AREA")
@NamedQuery(name = "TrPresupuestoArea.findAll", query = "SELECT t FROM TrPresupuestoArea t")
public class TrPresupuestoArea extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_AREA")
	private TcArea area;

	@Column(name = "PRESUPUESTO_AUTORIZADO")
	private BigDecimal presupuestoAutorizado;
	
	@Transient
	private BigDecimal disponible;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "presupuestoArea")
	private List<TrAreaAccion> accions = new ArrayList<>();

	public TrPresupuestoArea() {
	}

	public TcArea getArea() {
		return area;
	}

	public void setArea(TcArea area) {
		this.area = area;
	}

	public BigDecimal getPresupuestoAutorizado() {
		return presupuestoAutorizado;
	}

	public void setPresupuestoAutorizado(BigDecimal presupuestoAutorizado) {
		this.presupuestoAutorizado = presupuestoAutorizado;
	}

	public List<TrAreaAccion> getAccions() {
		return accions;
	}

	public void setAccions(List<TrAreaAccion> accions) {
		this.accions = accions;
	}

	public BigDecimal getDisponible() {
		return disponible;
	}

	public void setDisponible(BigDecimal disponible) {
		this.disponible = disponible;
	}

}