package com.gem.sistema.business.repository.catalogs;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcAdquisicion;
import com.gem.sistema.business.domain.TcFirmaSolicitudDetalle;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcFirmaSolicitudDetalleRepository.
 */
@Repository(value = "tcFirmaSolicitudDetalleRepository")
public interface TcFirmaSolicitudDetalleRepository extends PagingAndSortingRepository<TcFirmaSolicitudDetalle, Long>,
		QueryDslPredicateExecutor<TcFirmaSolicitudDetalle> {

	@Query("select d,f,u from TcFirmaSolicitudDetalle d "
			+ "left join fetch d.firma f join d.adquisicion a left join fetch f.usuario u "
			+ "where a.id=:id and d.posicion<5 order by d.posicion")
	List<TcFirmaSolicitudDetalle> getFirmasRevisionByAdquisicionId(@Param("id") Long id);

	@Query("select d,f,u from TcFirmaSolicitudDetalle d "
			+ "left join fetch d.firma f join d.adquisicion a left join fetch f.usuario u "
			+ "where a.id=:id and d.posicion>4 order by d.posicion")
	List<TcFirmaSolicitudDetalle> getFirmasRecepcionByAdquisicionId(@Param("id") Long id);

	@Query("select d,f from TcFirmaSolicitudDetalle d " + "left join fetch d.firma f join d.adquisicion a "
			+ "where a.id=:id and d.estado='Firmado' order by d.posicion")
	List<TcFirmaSolicitudDetalle> getFirmasByAdquisicionId(@Param("id") Long id);

	@Query("select d,f from TcFirmaSolicitudDetalle d "
			+ "left join fetch d.firma f join f.usuario u where u.usuario=:usuario")
	List<TcFirmaSolicitudDetalle> getDetalleByUsername(@Param("usuario") String usuario);

	@Query("select d,f from TcFirmaSolicitudDetalle d " + "left join fetch d.firma f join d.adquisicion a "
			+ "where a=:adquisicion")
	List<TcFirmaSolicitudDetalle> getAllByAdquisicion(@Param("adquisicion") TcAdquisicion adquisicion);

	List<TcFirmaSolicitudDetalle> findAll();

}
