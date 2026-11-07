package com.gem.sistema.business.repository.catalogs;

import com.gem.sistema.business.domain.Tabulador;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "tabuladorRepository")
public interface TabuladorRepository
        extends PagingAndSortingRepository<Tabulador, Long>, QueryDslPredicateExecutor<Tabulador> {

    List<Tabulador> findAll();
}
