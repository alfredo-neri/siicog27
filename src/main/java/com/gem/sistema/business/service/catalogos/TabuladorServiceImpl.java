package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.dao.TabuladorDAO;
import com.gem.sistema.business.domain.Tabulador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "tabuladorService")
public class TabuladorServiceImpl implements TabuladorService {
    @Autowired
    private TabuladorDAO tabuladorDAO;

    @Override
    public void save(Tabulador tabulador) {
        this.tabuladorDAO.save(tabulador);
    }
}
