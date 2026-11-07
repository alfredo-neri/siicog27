package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.dao.EmpleadoDAO;
import com.gem.sistema.business.domain.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "empleadoService")
public class EmpleadoServiceImpl implements EmpleadoService {
    @Autowired
    private EmpleadoDAO empleadoDAO;

    @Override
    public void save(Empleado emp) {
        this.empleadoDAO.save(emp);
    }
}
