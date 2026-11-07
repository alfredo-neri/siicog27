package com.gem.sistema.business.repository.catalogs;

import com.gem.sistema.business.domain.Puesto;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "puestoRepository")
public interface PuestoRepository
        extends PagingAndSortingRepository<Puesto, Long>, CrudRepository<Puesto, Long>, QuerydslPredicateExecutor<Puesto> {

    List<Puesto> findAll();
}
