package com.gem.sistema.business.repository.catalogos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Variables;;

// TODO: Auto-generated Javadoc
/**
 * The Interface VariablesRepository.
 */
@Repository(value ="variablesRepository")
public interface VariablesRepository extends PagingAndSortingRepository<Variables, Long>, QueryDslPredicateExecutor<Variables>{
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Variables> findAll();
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Variables> S save(S entity);
	
	/**
	 * Find by cvevar.
	 *
	 * @param cvevar the cvevar
	 * @return the list
	 */
	List<Variables> findByCvevar(String cvevar);
	
	List<Variables> findByOrderByNumvarAsc();
	
	Variables findByNumvar(Integer numvar);
	
	@Query(value = "select max(numvar) + 1 from Variables v")
	Integer getNextNumvar();
	
	Integer countByNumvar(Integer numvar);
	
}
