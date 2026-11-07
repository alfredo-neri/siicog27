package com.gem.sistema.business.repository.catalogs;

import com.gem.sistema.business.domain.Contrato;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "contratoRepository")
public interface ContratoRepository
        extends PagingAndSortingRepository<Contrato, Long>, QueryDslPredicateExecutor<Contrato> {

    List<Contrato> findAll();
}
