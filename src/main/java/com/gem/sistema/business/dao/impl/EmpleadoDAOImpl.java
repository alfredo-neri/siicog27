package com.gem.sistema.business.dao.impl;

import com.gem.sistema.business.dao.EmpleadoDAO;
import com.gem.sistema.business.domain.Empleado;
import com.gem.sistema.business.repository.catalogs.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpleadoDAOImpl implements EmpleadoDAO {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public void save(Empleado emp) {
        this.empleadoRepository.save(emp);
    }
}
