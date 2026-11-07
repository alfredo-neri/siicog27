package com.gem.sistema.business.dto;

import java.math.BigDecimal;

public class TotalIngresoEgreso {

    private BigDecimal autotizado;
    private BigDecimal ampliacion;
    private BigDecimal reduccion;
    private BigDecimal aotorizadoFinal;
    private BigDecimal variacionInicial;
    private BigDecimal modifcado;
    private BigDecimal ejercido;

    public BigDecimal getAutotizado() {
        return autotizado;
    }

    public void setAutotizado(BigDecimal autotizado) {
        this.autotizado = autotizado;
    }

    public BigDecimal getAmpliacion() {
        return ampliacion;
    }

    public void setAmpliacion(BigDecimal ampliacion) {
        this.ampliacion = ampliacion;
    }

    public BigDecimal getReduccion() {
        return reduccion;
    }

    public void setReduccion(BigDecimal reduccion) {
        this.reduccion = reduccion;
    }

    public BigDecimal getAotorizadoFinal() {
        return aotorizadoFinal;
    }

    public void setAotorizadoFinal(BigDecimal aotorizadoFinal) {
        this.aotorizadoFinal = aotorizadoFinal;
    }

    public BigDecimal getVariacionInicial() {
        return variacionInicial;
    }

    public void setVariacionInicial(BigDecimal variacionInicial) {
        this.variacionInicial = variacionInicial;
    }

    public BigDecimal getModifcado() {
        return modifcado;
    }

    public void setModifcado(BigDecimal modifcado) {
        this.modifcado = modifcado;
    }

    public BigDecimal getEjercido() {
        return ejercido;
    }

    public void setEjercido(BigDecimal ejercido) {
        this.ejercido = ejercido;
    }
}
