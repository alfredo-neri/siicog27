package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.dao.ConceptoDAO;
import com.gem.sistema.business.domain.Concepto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConceptoServiceImpl implements ConceptoService {
    @Autowired
    private ConceptoDAO conceptoDAO;

    @Override
    public void save(Concepto c) {
        this.conceptoDAO.save(c);
    }
}
