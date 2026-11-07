package com.gem.sistema.business.repository.catalogs;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcIngresosPropio;

@Repository(value = "tcIngresosPropioRepository")
public interface TcIngresoPropioRepository
		extends PagingAndSortingRepository<TcIngresosPropio, Long>, QueryDslPredicateExecutor<TcIngresosPropio> {

	List<TcIngresosPropio> findAll();

	List<TcIngresosPropio> findAllByOrderByClave();;

	List<TcIngresosPropio> findAllByClave(Integer clave);
	@Query("select max(i.clave) + 1  from TcIngresosPropio i")
	Integer getNewClave();
	
	@Query(value = "SELECT SUM(TW.MONTO) FROM TW_INGRESO_PROPIOS_DETALLE TW, TC_INGRESOS_PROPIOS TC "
			+ "WHERE TW.MESPOL BETWEEN  :mesInicial AND :mesFinal AND TC.CLAVE = :clave ", nativeQuery = true)
	BigDecimal getTotalIngresos(@Param("mesInicial") Integer mesInicial,@Param("mesFinal") Integer mesFinal, @Param("clave") Integer clave);
}
