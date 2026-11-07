/**
 * 
 */
package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TrUsuarios2MenuItems;

// TODO: Auto-generated Javadoc
/**
 * The Interface TrUsuarios2MenuItemsRepository.
 *
 * @author lancer
 */
@Repository
public interface TrUsuarios2MenuItemsRepository extends PagingAndSortingRepository<TrUsuarios2MenuItems, Long>, QueryDslPredicateExecutor<TrUsuarios2MenuItems> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TrUsuarios2MenuItems> findAll();
	
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TrUsuarios2MenuItems> S save(S entity);

	/**
	 * Limpiar relacion.
	 *
	 * @param idUsuario the id usuario
	 */
	@Transactional(timeout = 60)
	@Modifying
	@Query(value = " DELETE FROM GEMUSER.TR_USUARIOS2MENUITEMS where ID_USUARIO= :idUsuario ", nativeQuery = true)
	void limpiarRelacion(@Param ("idUsuario") Integer idUsuario );
	
	/**
	 * Find all by id usuario.
	 *
	 * @param idUsuario the id usuario
	 * @return the list
	 */
	List<TrUsuarios2MenuItems> findAllByIdUsuario(@Param ("idUsuario") Integer idUsuario);
}
