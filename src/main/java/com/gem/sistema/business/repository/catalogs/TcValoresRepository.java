package com.gem.sistema.business.repository.catalogs;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.gem.sistema.business.domain.TcValores;

public interface TcValoresRepository
		extends PagingAndSortingRepository<TcValores, Long>, QueryDslPredicateExecutor<TcValores> {
	@Query(nativeQuery = true, value = "SELECT MAX(TV.ID_ROW) \n" + "     FROM GEMUSER.TC_VALORES TV,\n"
			+ "          GEMUSER.TR_ETIQ_TABLAS TR,\n" + "          GEMUSER.TC_ETIQUETAS TE,\n"
			+ "          GEMUSER.TC_TABLAS TT\n" + "WHERE TR.ID = TV.ID_ETIQ_TABLA\n" + "  AND TR.ID_ETIQUETA = TE.ID\n"
			+ "  AND TR.ID_TABLA    = TT.ID\n   AND TE.STATUS      = 1" + "  AND TT.ID          = :id\n" + " ")
	Integer findByidTable(@Param("id") Integer id);

	@Query(nativeQuery = true, value = "SELECT MAX(TV.ID_ROW) \n" + "     FROM GEMUSER.TC_VALORES TV,\n"
			+ "          GEMUSER.TR_ETIQ_TABLAS TR,\n" + "          GEMUSER.TC_ETIQUETAS TE,\n"
			+ "          GEMUSER.TC_TABLAS TT\n" + "WHERE TR.ID = TV.ID_ETIQ_TABLA\n" + "  AND TR.ID_ETIQUETA = TE.ID\n"
			+ "  AND TR.ID_TABLA    = TT.ID\n   AND TE.STATUS      = 1" + "  AND TT.NOMBRE         = :tableName\n"
			+ " ")
	Integer findByidTable(@Param("tableName") String tableName);


	@Query(value = "SELECT NVL(MAX(ID_ROW),0) FROM TC_VALORES", nativeQuery = true)
	Integer getMaxIdRow();
}
