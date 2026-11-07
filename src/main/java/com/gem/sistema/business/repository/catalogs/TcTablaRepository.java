package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcTabla;

@Repository(value="tcTablaRepository")
public interface TcTablaRepository extends PagingAndSortingRepository<TcTabla, Long>, CrudRepository<TcTabla, Long>, QuerydslPredicateExecutor<TcTabla>{

}
