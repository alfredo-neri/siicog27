package com.gem.sistema.business.repository.catalogs;

import com.gem.sistema.business.domain.Contrato;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "contratoRepository")
public interface ContratoRepository
        extends PagingAndSortingRepository<Contrato, Long>, CrudRepository<Contrato, Long>, QuerydslPredicateExecutor<Contrato> {

    List<Contrato> findAll();
}
