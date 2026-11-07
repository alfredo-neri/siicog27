package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcModulosOperacione;

@Repository
public interface TcModulosOperacioneRepository
		extends PagingAndSortingRepository<TcModulosOperacione, Long>, QueryDslPredicateExecutor<TcModulosOperacione> {

	List<TcModulosOperacione> findAllByIdSector(Integer idSector);
	
	

}
