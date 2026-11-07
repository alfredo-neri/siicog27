package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcBienServicio;

// TODO: Auto-generated Javadoc
@Repository(value = "tcBienServicioRepository")
public interface TcBienServicioRepository
		extends PagingAndSortingRepository<TcBienServicio, Long>, QueryDslPredicateExecutor<TcBienServicio> {
	List<TcBienServicio> findAll();

}
