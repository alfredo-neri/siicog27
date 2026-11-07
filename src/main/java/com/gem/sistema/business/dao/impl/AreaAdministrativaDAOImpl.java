package com.gem.sistema.business.dao.impl;

import com.gem.sistema.business.dao.AreaAdministrativaDAO;
import com.gem.sistema.business.domain.AreaAdministrativa;
import com.gem.sistema.business.repository.catalogs.AreaAdministrativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AreaAdministrativaDAOImpl implements AreaAdministrativaDAO {
    @Autowired
    private AreaAdministrativaRepository areaAdministrativaRepository;

    @Override
    public void save(AreaAdministrativa areaAdministrativa) {
        this.areaAdministrativaRepository.save(areaAdministrativa);
    }
}
