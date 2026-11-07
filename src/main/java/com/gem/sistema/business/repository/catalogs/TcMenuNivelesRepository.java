/**
 * 
 */
package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TcMenuNiveles;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcMenuNivelesRepository.
 *
 * @author lancer
 */
@Repository
public interface TcMenuNivelesRepository
		extends PagingAndSortingRepository<TcMenuNiveles, Long>, CrudRepository<TcMenuNiveles, Long>, QuerydslPredicateExecutor<TcMenuNiveles> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcMenuNiveles> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcMenuNiveles> S save(S entity);

	/**
	 * Find menu.
	 *
	 * @param idSector  the id sector
	 * @param idUsuario the id usuario
	 * @return the list
	 */
	@Query(value = "  SELECT mn.CLAVE_MENU, mn.ID_MENU_PADRE, mn.LABEL, mn.URL, IDSECTOR "
			+ " FROM TC_MENU_NIVELES mn, TR_USUARIOS2MENUITEMS um " + "WHERE mn.CLAVE_MENU = um.ID_MENU_ITEM"
			+ "  AND um.ID_USUARIO = :idUsuario"
			+ "  AND mn.IDSECTOR = :idSector ORDER BY ID_MENU_PADRE, POSICION, CLAVE_MENU ASC", nativeQuery = true)
	List<Object> findMenu(@Param("idSector") int idSector, @Param("idUsuario") Long idUsuario);

	/**
	 * Find menu niveles.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query(value = " SELECT mn.CLAVE_MENU, mn.ID_MENU_PADRE, mn.LABEL, mn.URL, mn.IDSECTOR "
			+ " FROM TC_MENU_NIVELES mn "
			+ " WHERE mn.IDSECTOR = :idSector  ORDER BY ID_MENU_PADRE, CLAVE_MENU ASC", nativeQuery = true)
	List<Object> findMenuNiveles(@Param("idSector") int idSector);

	/**
	 * Find by id sector.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<TcMenuNiveles> findByIdSector(Integer idSector);
}
