package com.gem.sistema.business.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TC_AREA_ADMINISTRATIVA")
@NamedQuery(name = "AreaAdministrativa.findAll", query = "SELECT t FROM AreaAdministrativa t")
public class AreaAdministrativa extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    @OneToMany(mappedBy = "areaAdministrativa")
    private List<Contrato> contratos;

    public AreaAdministrativa() {
    }

    public AreaAdministrativa(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }
}
