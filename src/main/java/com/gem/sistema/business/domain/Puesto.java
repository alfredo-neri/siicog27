package com.gem.sistema.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TC_PUESTO")
@NamedQuery(name = "Puesto.findAll", query = "SELECT t FROM Puesto t")
public class Puesto extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String rango;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "tc_tabulador_id")
    private Tabulador tabulador;
    @OneToMany(mappedBy = "puesto")
    private List<Contrato> contratos;

    public Puesto() {
    }

    public Puesto(String nombre, String rango, Tabulador tabulador) {
        this.nombre = nombre;
        this.rango = rango;
        this.tabulador = tabulador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public Tabulador getTabulador() {
        return tabulador;
    }

    public void setTabulador(Tabulador tabulador) {
        this.tabulador = tabulador;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }
}
