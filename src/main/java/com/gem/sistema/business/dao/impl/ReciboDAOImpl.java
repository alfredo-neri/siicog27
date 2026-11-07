package com.gem.sistema.business.dao.impl;

import com.gem.sistema.business.dao.ReciboDAO;
import com.gem.sistema.business.domain.Recibo;
import com.gem.sistema.business.repository.catalogs.ReciboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReciboDAOImpl implements ReciboDAO {
    @Autowired
    private ReciboRepository reciboRepository;

    @Override
    public void save(Recibo recibo) {
        this.reciboRepository.save(recibo);
    }
}
