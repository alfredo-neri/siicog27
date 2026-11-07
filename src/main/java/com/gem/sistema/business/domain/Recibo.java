package com.gem.sistema.business.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TC_RECIBO")
@NamedQuery(name = "Recibo.findAll", query = "SELECT t FROM Recibo t")
public class Recibo extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "num_quincena")
    private Integer numQuincena;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detalle_nomina")
    private DetalleNomina detalleNomina;
    @OneToMany(mappedBy = "recibo")
    private List<Contrato> contratos;

    public Recibo() {
    }

    public Recibo(Integer numQuincena, DetalleNomina detalleNomina) {
        this.numQuincena = numQuincena;
        this.detalleNomina = detalleNomina;
    }

    public Integer getNumQuincena() {
        return numQuincena;
    }

    public void setNumQuincena(Integer numQuincena) {
        this.numQuincena = numQuincena;
    }

    public DetalleNomina getDetalleNomina() {
        return detalleNomina;
    }

    public void setDetalleNomina(DetalleNomina detalleNomina) {
        this.detalleNomina = detalleNomina;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }
}
