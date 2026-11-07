package com.gem.sistema.business.repository.catalogs;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcAdquisicion;
import com.gem.sistema.business.domain.TcArticulosSA;
import com.gem.sistema.business.domain.TcUnidadMedida;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcAdquisicionRepository.
 */
@Repository(value = "tcArticulosSARepository")
public interface TcArticulosSARepository
		extends PagingAndSortingRepository<TcArticulosSA, Long>, CrudRepository<TcArticulosSA, Long>, QuerydslPredicateExecutor<TcArticulosSA> {
	@Query("SELECT MAX(a.renglon)+1  FROM TcArticulosSA a join a.adquisicion s WHERE s.id=:idAdquisicion")
	Optional<Integer> getNextRenglon(@Param("idAdquisicion") Long idAdquisicion);

	List<TcArticulosSA> findAll();

	TcArticulosSA findOneByRenglon(Integer renglon);
	
	TcArticulosSA findOneByAdquisicionAndRenglon(TcAdquisicion adquisicion, Integer renglon);

	@Query("Select a,s from TcArticulosSA a right join fetch a.adquisicion s where a.adquisicion=:adquisicion order by a.renglon")
	List<TcArticulosSA> findAllByAdquisicion(@Param("adquisicion") TcAdquisicion adquisicion);

	/** Cuantos articulos usan la unidad de medida, para bloquear su borrado */
	long countByUnidad(TcUnidadMedida unidad);
}
