package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcOrigenRecurso;

// TODO: Auto-generated Javadoc
/**
 * The Interface tcOrigenRecursoRepository.
 */
@Repository(value = "tcOrigenRecursoRepository")
public interface TcOrigenRecursoRepository
		extends PagingAndSortingRepository<TcOrigenRecurso, Long>, QueryDslPredicateExecutor<TcOrigenRecurso> {

	List<TcOrigenRecurso> findAll();

}
