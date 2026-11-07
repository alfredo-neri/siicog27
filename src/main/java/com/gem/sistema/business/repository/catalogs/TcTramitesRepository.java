package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcTramite;

// TODO: Auto-generated Javadoc
/**
 * The Interface tcTramitesRepository.
 */
@Repository(value = "tcTramitesRepository")
public interface TcTramitesRepository
		extends PagingAndSortingRepository<TcTramite, Long>, CrudRepository<TcTramite, Long>, QuerydslPredicateExecutor<TcTramite> {

	List<TcTramite> findAll();

}
