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

import com.gem.sistema.business.domain.TcUsuario;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcUsuarioRepository.
 *
 * @author gauss
 */
@Repository
public interface TcUsuarioRepository
		extends PagingAndSortingRepository<TcUsuario, Long>, QueryDslPredicateExecutor<TcUsuario> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcUsuario> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcUsuario> S save(S entity);

	/**
	 * Find by usuario.
	 *
	 * @param <S>
	 *            the generic type
	 * @param usuario
	 *            the usuario
	 * @return the s
	 */
	<S extends TcUsuario> S findByUsuario(String usuario);

	/**
	 * Sets the account non locked for.
	 *
	 * @param accountnonlocked
	 *            the accountnonlocked
	 * @param id
	 *            the id
	 * @return the int
	 */
	@Transactional(timeout = 10)
	@Modifying(clearAutomatically = true)
	@Query("update #{#entityName} u set u.accountnonlocked = :accountnonlocked where u.id = :id")
	int setAccountNonLockedFor(@Param("accountnonlocked") Boolean accountnonlocked, @Param("id") Long id);

	/**
	 * Sets the contrasenia for.
	 *
	 * @param accountnonlocked
	 *            the accountnonlocked
	 * @param id
	 *            the id
	 * @return the int
	 */
	@Transactional(timeout = 10)
	@Modifying(clearAutomatically = true)
	@Query("update #{#entityName} u set u.contrasenia = :contrasenia where u.id = :id")
	int setContraseniaFor(@Param("contrasenia") String accountnonlocked, @Param("id") Long id);

	@Transactional(timeout = 10)
	@Modifying(clearAutomatically = true)
	@Query("update #{#entityName} u set u.idLocalidad = :idLocalidad, u.idEntidadAdmini = :idEntidadAdmini where u.usuario = :usuario")
	int updateLocalidad(@Param("idLocalidad") Long idLocalidad, @Param("idEntidadAdmini") Long idEntidadAdmini,
			@Param("usuario") String usuario);

	/**
	 * Gets the nom archivo ing caledarizado.
	 *
	 * @param user
	 *            the user
	 * @return the nom archivo ing caledarizado
	 */
	@Query("select ent.clave, loc.clave from TcUsuario u,TcLocalidadesUsuario loc,TcEntidadAdministrativa ent where u.idLocalidad=loc.id and u.idEntidadAdmini=ent.id and u.usuario=:user")
	Object getNomArchivoIngCaledarizado(@Param("user") String user);
}
