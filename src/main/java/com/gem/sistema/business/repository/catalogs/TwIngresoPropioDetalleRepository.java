package com.gem.sistema.business.repository.catalogs;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TwIngresoPropiosDetalle;

@Repository(value = "twIngresosPropioDetalleRepository")
public interface TwIngresoPropioDetalleRepository extends PagingAndSortingRepository<TwIngresoPropiosDetalle, Long>,
		QueryDslPredicateExecutor<TwIngresoPropiosDetalle> {

	List<TwIngresoPropiosDetalle> findAll();

	@Query(value = "SELECT i ,p FROM  TwIngresoPropiosDetalle i join fetch i.polide p WHERE p.mespol = :mespol AND  "
			+ "p.tippol = :tippol AND p.conpol = :conpol AND p.renpol = :renpol")
	List<TwIngresoPropiosDetalle> getAllByMesAndTipoAndConpolAndRenpol(@Param("mespol") Integer mespol,
			@Param("tippol") String tipol, @Param("conpol") Integer conpol, @Param("renpol") BigDecimal renpol);

	@Query(value = "SELECT D.* FROM TW_INGRESO_PROPIOS_DETALLE D , TC_INGRESOS_PROPIOS I WHERE D.ID_INGRESO=I.ID "
			+ "AND I.CLAVE= :clave", nativeQuery = true)
	List<TwIngresoPropiosDetalle> getAllByClaveIngreso(@Param("clave") Integer clave);

	@Query(value = "SELECT SUM(D.MONTO) FROM TW_INGRESO_PROPIOS_DETALLE D  WHERE MESPOL=:mespol GROUP BY MESPOL ", nativeQuery = true)
	Double getTotalByMes(@Param("mespol") Integer mespol);
}
