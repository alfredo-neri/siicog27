package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcAreaAdministrativa;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcAreaAdministrativaRepository.
 */
@Repository(value = "tcAreaAdministrativaRepository")
public interface TcAreaAdministrativaRepository extends PagingAndSortingRepository<TcAreaAdministrativa, Long>,
		QueryDslPredicateExecutor<TcAreaAdministrativa> {

	List<TcAreaAdministrativa> findAll();

	TcAreaAdministrativa findFirstByClave(String clave);
}
