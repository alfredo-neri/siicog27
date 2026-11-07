package com.gem.sistema.business.repository.catalogs;

import com.gem.sistema.business.domain.Empleado;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "empleadoRepository")
public interface EmpleadoRepository
        extends PagingAndSortingRepository<Empleado, Long>, CrudRepository<Empleado, Long>, QuerydslPredicateExecutor<Empleado> {

    List<Empleado> findAll();
}
