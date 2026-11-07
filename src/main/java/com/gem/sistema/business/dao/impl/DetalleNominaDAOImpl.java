package com.gem.sistema.business.dao.impl;

import com.gem.sistema.business.dao.DetalleNominaDAO;
import com.gem.sistema.business.domain.DetalleNomina;
import com.gem.sistema.business.repository.catalogs.DetalleNominaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DetalleNominaDAOImpl implements DetalleNominaDAO {
    @Autowired
    private DetalleNominaRepository detalleNominaRepository;

    @Override
    public void save(DetalleNomina detalleNomina) {
        this.detalleNominaRepository.save(detalleNomina);
    }
}
