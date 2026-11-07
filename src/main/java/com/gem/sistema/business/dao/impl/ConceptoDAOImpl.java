package com.gem.sistema.business.dao.impl;

import com.gem.sistema.business.dao.ConceptoDAO;
import com.gem.sistema.business.domain.Concepto;
import com.gem.sistema.business.repository.catalogs.ConceptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConceptoDAOImpl implements ConceptoDAO {
    @Autowired
    private ConceptoRepository conceptoRepository;

    @Override
    public void save(Concepto concepto) {
        this.conceptoRepository.save(concepto);
    }
}
