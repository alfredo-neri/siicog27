package com.gem.sistema.business.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the TR_AREA_ACCION database table.
 * 
 */
@Entity
@Table(name = "TR_AREA_ACCION")
@NamedQuery(name = "TrAreaAccion.findAll", query = "SELECT t FROM TrAreaAccion t")
public class TrAreaAccion extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "AUTORIZADO_INICIAL")
	private BigDecimal autorizadoInicial;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_AT")
	private Date createAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ACCION")
	private TcAccion accion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PRESUPUESTO_AREA")
	private TrPresupuestoArea presupuestoArea;
	
	@OneToMany(
            mappedBy = "areaAccion",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TwAccionContrato>  listAccionesContrato = new ArrayList<>();

	
	@PrePersist
	public void persit() {
		this.createAt = new Date();
	}
	
	public TrAreaAccion() {
	}

	public BigDecimal getAutorizadoInicial() {
		return this.autorizadoInicial;
	}

	public void setAutorizadoInicial(BigDecimal autorizadoInicial) {
		this.autorizadoInicial = autorizadoInicial;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public TcAccion getAccion() {
		return accion;
	}

	public void setAccion(TcAccion accion) {
		this.accion = accion;
	}

	public TrPresupuestoArea getPresupuestoArea() {
		return presupuestoArea;
	}

	public void setPresupuestoArea(TrPresupuestoArea presupuestoArea) {
		this.presupuestoArea = presupuestoArea;
	}

	public List<TwAccionContrato> getListAccionesContrato() {
		return listAccionesContrato;
	}

	public void setListAccionesContrato(List<TwAccionContrato> listAccionesContrato) {
		this.listAccionesContrato = listAccionesContrato;
	}

	@Override
	public String toString() {
		return "TrAreaAccion [autorizadoInicial=" + autorizadoInicial + ", createAt=" + createAt + ", accion=" + accion
				+ ", presupuestoArea=" + presupuestoArea + ", listAccionesContrato=" + listAccionesContrato + "]";
	}

}