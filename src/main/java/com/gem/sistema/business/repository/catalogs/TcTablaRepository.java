package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcTabla;

@Repository(value="tcTablaRepository")
public interface TcTablaRepository extends PagingAndSortingRepository<TcTabla, Long>, QueryDslPredicateExecutor<TcTabla>{

}
