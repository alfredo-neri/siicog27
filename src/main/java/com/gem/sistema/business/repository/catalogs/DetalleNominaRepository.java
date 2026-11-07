package com.gem.sistema.business.repository.catalogs;

import com.gem.sistema.business.domain.DetalleNomina;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "detalleNominaRepository")
public interface DetalleNominaRepository
        extends PagingAndSortingRepository<DetalleNomina, Long>, CrudRepository<DetalleNomina, Long>, QuerydslPredicateExecutor<DetalleNomina> {

    List<DetalleNomina> findAll();
}
