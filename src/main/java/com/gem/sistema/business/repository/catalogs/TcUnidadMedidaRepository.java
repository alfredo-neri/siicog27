package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcUnidadMedida;

/**
 * The Interface TcUnidadMedidaRepository.
 */
@Repository(value = "tcUnidadMedidaRepository")
public interface TcUnidadMedidaRepository extends PagingAndSortingRepository<TcUnidadMedida, Long>, CrudRepository<TcUnidadMedida, Long>,
		QuerydslPredicateExecutor<TcUnidadMedida> {

	List<TcUnidadMedida> findAll();

	/** Catalogo completo, vigentes y dadas de baja, para la pantalla de captura */
	@Query("Select u from TcUnidadMedida u order by u.clave")
	List<TcUnidadMedida> findAllOrdenadas();

	/** Solo las vigentes, es lo que alimenta los combos de articulos */
	@Query("Select u from TcUnidadMedida u where u.activo = 1 order by u.descripcion")
	List<TcUnidadMedida> findAllVigentes();

	TcUnidadMedida findFirstByClave(String clave);

	TcUnidadMedida findFirstByDescripcion(String descripcion);

	/**
	 * Busca por clave, descripcion o abreviatura sin distinguir mayusculas. Es el
	 * empate que necesita el cargador de Excel, que recibe texto libre.
	 */
	@Query("Select u from TcUnidadMedida u where u.activo = 1 and ("
			+ " upper(trim(u.descripcion)) = upper(trim(:texto))"
			+ " or upper(trim(u.abreviatura)) = upper(trim(:texto))"
			+ " or upper(trim(u.clave)) = upper(trim(:texto)) )")
	List<TcUnidadMedida> findVigentesByTexto(@Param("texto") String texto);
}
