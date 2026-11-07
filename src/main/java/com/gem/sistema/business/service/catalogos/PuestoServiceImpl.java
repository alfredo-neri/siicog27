package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.dao.PuestoDAO;
import com.gem.sistema.business.domain.Puesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "puestoService")
public class PuestoServiceImpl implements PuestoService {
    @Autowired
    private PuestoDAO puestoDAO;

    @Override
    public void save(Puesto p) {
        this.puestoDAO.save(p);
    }
}
