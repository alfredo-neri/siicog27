package com.gem.sistema.business.dao.impl;

import com.gem.sistema.business.dao.TabuladorDAO;
import com.gem.sistema.business.domain.Tabulador;
import com.gem.sistema.business.repository.catalogs.TabuladorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TabuladorDAOImpl implements TabuladorDAO {
    @Autowired
    private TabuladorRepository tabuladorRepository;

    @Override
    public void save(Tabulador tabulador) {
        this.tabuladorRepository.save(tabulador);
    }
}
