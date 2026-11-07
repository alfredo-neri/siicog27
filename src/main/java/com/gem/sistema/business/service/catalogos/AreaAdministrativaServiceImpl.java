package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.dao.AreaAdministrativaDAO;
import com.gem.sistema.business.domain.AreaAdministrativa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "areaAdministrativaService")
public class AreaAdministrativaServiceImpl implements AreaAdministrativaService {
    @Autowired
    private AreaAdministrativaDAO areaAdministrativaDAO;

    @Override
    public void save(AreaAdministrativa areaAdministrativa) {
        this.areaAdministrativaDAO.save(areaAdministrativa);
    }
}
