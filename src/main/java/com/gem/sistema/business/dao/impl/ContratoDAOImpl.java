package com.gem.sistema.business.dao.impl;

import com.gem.sistema.business.dao.ContratoDAO;
import com.gem.sistema.business.domain.Contrato;
import com.gem.sistema.business.repository.catalogs.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContratoDAOImpl implements ContratoDAO {
    @Autowired
    private ContratoRepository contratoRepository;

    @Override
    public void save(Contrato contrato) {
        this.contratoRepository.save(contrato);
    }
}
