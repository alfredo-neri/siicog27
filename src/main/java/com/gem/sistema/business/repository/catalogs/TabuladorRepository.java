package com.gem.sistema.business.repository.catalogs;

import com.gem.sistema.business.domain.Tabulador;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "tabuladorRepository")
public interface TabuladorRepository
        extends PagingAndSortingRepository<Tabulador, Long>, CrudRepository<Tabulador, Long>, QuerydslPredicateExecutor<Tabulador> {

    List<Tabulador> findAll();
}
