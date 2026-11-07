package com.gem.sistema.business.repository.catalogs;

import com.gem.sistema.business.domain.Recibo;
import com.gem.sistema.business.domain.TcAdquisicion;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "reciboRepository")
public interface ReciboRepository
        extends PagingAndSortingRepository<Recibo, Long>, CrudRepository<Recibo, Long>, QuerydslPredicateExecutor<Recibo> {

    List<Recibo> findAll();
}
