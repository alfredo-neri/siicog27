package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.Natgas;
import com.gem.sistema.business.domain.TcSubgiro;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcSubgiroRepository.
 */
@Repository(value = "tcSubgiroRepository")
public interface TcSubgiroRepository
		extends PagingAndSortingRepository<TcSubgiro, Long>, QueryDslPredicateExecutor<TcSubgiro> {

	List<TcSubgiro> findAll();

	@Query("Select  s from TcSubgiro s join fetch s.giro g order by g.clvgas, s.clave")
	List<TcSubgiro> findAllWithGiroOrdered();

	List<TcSubgiro> findAllByClave(String clavw);

	List<TcSubgiro> findAllByDescripcion(String descripcion);

	List<TcSubgiro> findAllByGiro(Natgas giro);

	TcSubgiro findFirstByClaveAndGiro(String clave, Natgas giro);

}
