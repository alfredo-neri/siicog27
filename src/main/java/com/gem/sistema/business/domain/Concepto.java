package com.gem.sistema.business.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CONCEPTO")
@NamedQuery(name = "Concepto.findAll", query = "SELECT t FROM Concepto t")
public class Concepto extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "cve_concepto")
    private String cveConcepto;
    private String concepto;
    @OneToMany(mappedBy = "concepto")
    private List<DetalleNomina> detalleNominas;

    public Concepto() {
    }

    public Concepto(String cveConcepto, String concepto) {
        this.cveConcepto = cveConcepto;
        this.concepto = concepto;
    }

    public String getCveConcepto() {
        return cveConcepto;
    }

    public void setCveConcepto(String cveConcepto) {
        this.cveConcepto = cveConcepto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public List<DetalleNomina> getDetalleNominas() {
        return detalleNominas;
    }

    public void setDetalleNominas(List<DetalleNomina> detalleNominas) {
        this.detalleNominas = detalleNominas;
    }
}
