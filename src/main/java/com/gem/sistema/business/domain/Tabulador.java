package com.gem.sistema.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "TC_TABULADOR")
@NamedQuery(name = "Tabulador.findAll", query = "SELECT t FROM Tabulador t")
public class Tabulador extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long rango;
    @Column(name = "SALDO_BASE")
    private BigDecimal saldoBase;
    @Column(name = "GRATIFICACION_MENSUAL")
    private BigDecimal gratificacionMensual;
    @Column(name = "DESPENSA_MENSUAL")
    private BigDecimal despensaMensual;
    @Column(name = "SUELDO_MENSUAL_BRUTO")
    private BigDecimal sueldoMensualBruto;
    @Column(name = "SUELDO_NETO_MENSUAL")
    private BigDecimal sueldoNetoMensual;
    @Column(name = "SUELDO_NETO_QUINCENAL")
    private BigDecimal sueldoNetoQuincenal;
    @OneToMany(mappedBy = "tabulador")
    private List<Puesto> puestos;

    public Tabulador() {
    }

    public Tabulador(Long rango, BigDecimal saldoBase, BigDecimal gratificacionMensual, BigDecimal despensaMensual, BigDecimal sueldoMensualBruto, BigDecimal sueldoNetoMensual, BigDecimal sueldoNetoQuincenal) {
        this.rango = rango;
        this.saldoBase = saldoBase;
        this.gratificacionMensual = gratificacionMensual;
        this.despensaMensual = despensaMensual;
        this.sueldoMensualBruto = sueldoMensualBruto;
        this.sueldoNetoMensual = sueldoNetoMensual;
        this.sueldoNetoQuincenal = sueldoNetoQuincenal;
    }

    public Long getRango() {
        return rango;
    }

    public void setRango(Long rango) {
        this.rango = rango;
    }

    public BigDecimal getSaldoBase() {
        return saldoBase;
    }

    public void setSaldoBase(BigDecimal saldoBase) {
        this.saldoBase = saldoBase;
    }

    public BigDecimal getGratificacionMensual() {
        return gratificacionMensual;
    }

    public void setGratificacionMensual(BigDecimal gratificacionMensual) {
        this.gratificacionMensual = gratificacionMensual;
    }

    public BigDecimal getDespensaMensual() {
        return despensaMensual;
    }

    public void setDespensaMensual(BigDecimal despensaMensual) {
        this.despensaMensual = despensaMensual;
    }

    public BigDecimal getSueldoMensualBruto() {
        return sueldoMensualBruto;
    }

    public void setSueldoMensualBruto(BigDecimal sueldoMensualBruto) {
        this.sueldoMensualBruto = sueldoMensualBruto;
    }

    public BigDecimal getSueldoNetoMensual() {
        return sueldoNetoMensual;
    }

    public void setSueldoNetoMensual(BigDecimal sueldoNetoMensual) {
        this.sueldoNetoMensual = sueldoNetoMensual;
    }

    public BigDecimal getSueldoNetoQuincenal() {
        return sueldoNetoQuincenal;
    }

    public void setSueldoNetoQuincenal(BigDecimal sueldoNetoQuincenal) {
        this.sueldoNetoQuincenal = sueldoNetoQuincenal;
    }

    public List<Puesto> getPuestos() {
        return puestos;
    }

    public void setPuestos(List<Puesto> puestos) {
        this.puestos = puestos;
    }
}
