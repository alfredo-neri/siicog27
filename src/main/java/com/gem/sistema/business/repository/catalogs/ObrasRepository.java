package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Obra;;

// TODO: Auto-generated Javadoc
/**
 * The Interface ObrasRepository.
 */
@Repository(value = "obrasRepository")
public interface ObrasRepository extends PagingAndSortingRepository<Obra, Long>, QueryDslPredicateExecutor<Obra> {

	/**
	 * Find all by order by id.
	 *
	 * @return the list
	 */
	List<Obra> findAllByOrderById();

	/**
	 * Find all by order by ncontrol desc.
	 *
	 * @return the list
	 */
	List<Obra> findAllByOrderByNcontrolDesc();

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Obra> S save(S entity);
	
	Integer countByCatsector_IdsectorAndFnAndFunAndSubfunAndProgAndSubprogAndProyAndFfinAndNcontrol(Integer idsector, String fn, String fun, String subfun, String prog, String subprog, String proy, String ffin, Integer ncontrol);

}
