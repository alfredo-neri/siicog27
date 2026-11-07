package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcAdquisicion;
import com.gem.sistema.business.domain.TcAdquisicionGiroSubgiro;
import com.gem.sistema.business.domain.TcSubgiro;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcAdquisicionGiroSubgiroRepository.
 */
@Repository(value = "tcAdquisicionGiroSubgiroRepository")
public interface TcAdquisicionGiroSubgiroRepository extends PagingAndSortingRepository<TcAdquisicionGiroSubgiro, Long>,
		QueryDslPredicateExecutor<TcAdquisicionGiroSubgiro> {

	List<TcAdquisicionGiroSubgiro> findAll();

	Long countBySubgiro(TcSubgiro subgiro);

	Long countByAdquisicion(TcAdquisicion adquisicion);

	@Query("Select ga,a,s from TcAdquisicionGiroSubgiro ga right join fetch ga.adquisicion a join fetch ga.subgiro s where ga.adquisicion=:adquisicion")
	List<TcAdquisicionGiroSubgiro> findAllByAdquisicion(@Param("adquisicion") TcAdquisicion adquisicion);

	TcAdquisicionGiroSubgiro findFirstBySubgiroAndAdquisicion(TcSubgiro giro, TcAdquisicion adquisicion);

	List<TcAdquisicionGiroSubgiro> findAllByAdquisicionOrderBySubgiroClaveAsc(TcAdquisicion adquisicion);
}
