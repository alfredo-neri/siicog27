package com.gem.sistema.business.repository.catalogs;

import com.gem.sistema.business.domain.Concepto;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "conceptoRepository")
public interface ConceptoRepository
        extends PagingAndSortingRepository<Concepto, Long>, CrudRepository<Concepto, Long>, QuerydslPredicateExecutor<Concepto> {

    List<Concepto> findAll();
}
