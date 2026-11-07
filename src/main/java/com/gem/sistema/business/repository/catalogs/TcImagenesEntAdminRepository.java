package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TcImagenesEntAdmin;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcImagenesEntAdminRepository.
 */
@Repository("tcImagenesEntAdminRepository")
public interface TcImagenesEntAdminRepository extends PagingAndSortingRepository<TcImagenesEntAdmin, Long>, QueryDslPredicateExecutor<TcImagenesEntAdmin> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcImagenesEntAdmin> findAll();
	
	
	/**
	 * Find by id entidad admin.
	 *
	 * @param idEntidadAdmin the id entidad admin
	 * @return the tc imagenes ent admin
	 */
	TcImagenesEntAdmin findByIdEntidadAdmin(/*int clvmun,*/ long idEntidadAdmin);
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcImagenesEntAdmin> S save(S entity);
	
}
