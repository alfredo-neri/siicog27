package com.gem.sistema.business.dao.impl;

import com.gem.sistema.business.dao.PuestoDAO;
import com.gem.sistema.business.domain.Puesto;
import com.gem.sistema.business.repository.catalogs.PuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PuestoDAOImpl implements PuestoDAO {
    @Autowired
    private PuestoRepository puestoRepository;

    @Override
    public void save(Puesto p) {
        this.puestoRepository.save(p);
    }
}
