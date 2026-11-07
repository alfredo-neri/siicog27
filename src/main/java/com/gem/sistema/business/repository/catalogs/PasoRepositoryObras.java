package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.Paso;

// TODO: Auto-generated Javadoc
/**
 * The Interface PasoRepositoryObras.
 *
 * @author gauss.
 */
@Repository
public interface PasoRepositoryObras extends PagingAndSortingRepository<Paso, Long>, QueryDslPredicateExecutor<Paso> {
	
	/**
	 * Find all by programa.
	 *
	 * @param programa the programa
	 * @return Lista con los programas
	 */
	List<Paso> findAllByPrograma(String programa);
	
	/**
	 * Find all by order by partida.
	 *
	 * @return the list
	 */
	List<Paso> findAllByOrderByPartida();
	
	List<Paso> findByCatsector_IdsectorAndProgramaAndPartida(Integer idsector, String programa, String partida);
}
