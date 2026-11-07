package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.dao.ContratoDAO;
import com.gem.sistema.business.domain.Contrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "contratoService")
public class ContratoServiceImpl implements ContratoService{
    @Autowired
    private ContratoDAO contratoDAO;

    @Override
    public void save(Contrato contrato) {
        this.contratoDAO.save(contrato);
    }
}
