package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Catmun;

// TODO: Auto-generated Javadoc
/**
 * The Interface CatmunRepository.
 */
@Repository
public interface CatmunRepository extends PagingAndSortingRepository<Catmun, Long>, QueryDslPredicateExecutor<Catmun> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Catmun> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Catmun> S save(S entity);

	/**
	 * Find by clvmun.
	 *
	 * @param clvmun
	 *            the clvmun
	 * @return the list
	 */
	List<Catmun> findByClvmun(Integer clvmun);

	@Query(value = "select cm.nommun || ' - ' || lpad(cm.clvmun, 3, '0') entidad from Catmun cm where cm.clvmun = :clave", nativeQuery = true)
	String findMuniClave(@Param("clave") Integer clave);
	
	@Query(value = "select cm.nommun || ' - ' || lpad(cm.clvmun, 3, '0') entidad from Catmun cm order by cm.nommun asc", nativeQuery = true)
	List<String> findMuniClave();
}
