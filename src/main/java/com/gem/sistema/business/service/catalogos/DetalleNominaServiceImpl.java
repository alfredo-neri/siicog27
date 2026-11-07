package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.dao.DetalleNominaDAO;
import com.gem.sistema.business.domain.DetalleNomina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "detalleNominaService")
public class DetalleNominaServiceImpl implements DetalleNominaService {
    @Autowired
    private DetalleNominaDAO detalleNominaDAO;

    @Override
    public void save(DetalleNomina detalleNomina) {
        this.detalleNominaDAO.save(detalleNomina);
    }
}
