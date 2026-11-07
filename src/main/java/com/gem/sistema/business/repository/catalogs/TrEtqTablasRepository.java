package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.gem.sistema.business.domain.TrEtiqTabla;

public interface TrEtqTablasRepository
		extends PagingAndSortingRepository<TrEtiqTabla, Long>, QueryDslPredicateExecutor<TrEtiqTabla> {
	@Query(nativeQuery = true, value = "SELECT TR.ID" + "     FROM GEMUSER.TR_ETIQ_TABLAS TR,\n"
			+ "          GEMUSER.TC_ETIQUETAS TE,\n" + "          GEMUSER.TC_TABLAS TC\n"
			+ "WHERE TR.ID_ETIQUETA = TE.ID\n" + "  AND TR.ID_TABLA    = TC.ID\n" + "  AND TC.ID          = :id\n"
			+ "  AND TE.STATUS      = 1\n" + "  \n" + "  AND TE.NOMBRE      = :etiqueta")
	Long findByEtiqueta(@Param("etiqueta") String etiqueta, @Param("id") Integer id);
	
	@Query(nativeQuery = true, value = "SELECT TR.ID" + "     FROM GEMUSER.TR_ETIQ_TABLAS TR,\n"
			+ "          GEMUSER.TC_ETIQUETAS TE,\n" + "          GEMUSER.TC_TABLAS TC\n"
			+ "WHERE TR.ID_ETIQUETA = TE.ID\n" + "  AND TR.ID_TABLA    = TC.ID\n" + "  AND TC.NOMBRE          = :tableName \n"
			+ "  AND TE.STATUS      = 1\n" + "  \n" + "  AND UPPER(TE.NOMBRE) = UPPER(:etiqueta)")
	Long findByEtiquetaAndTableName(@Param("etiqueta") String etiqueta, @Param("tableName") String tableName);

	@Query(nativeQuery = true, value = "SELECT COUNT(1)\n" + "       FROM GEMUSER.TC_VALORES TV,\n"
			+ "            GEMUSER.TR_ETIQ_TABLAS TR,\n" + "            GEMUSER.TC_ETIQUETAS TE,\n"
			+ "            GEMUSER.TC_TABLAS TT\n" + "  WHERE TV.ID_ETIQ_TABLA = TR.ID\n"
			+ "    AND TR.ID_ETIQUETA   = TE.ID\n" + "    AND TR.ID_TABLA      = TT.ID\n"
			+ "    AND TR.STATUS        = 1\n" + "    AND TT.ID            = 1\n"
			+ "    AND TE.NOMBRE        = 'SEMESTRE'\n" + "    AND TV.VALOR         = :semestre")
	Integer validaSemestre(@Param("semestre") String semestre);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(1)\n" + "       FROM GEMUSER.TC_VALORES TV,\n"
			+ "            GEMUSER.TR_ETIQ_TABLAS TR,\n" + "            GEMUSER.TC_ETIQUETAS TE,\n"
			+ "            GEMUSER.TC_TABLAS TT\n" + "  WHERE TV.ID_ETIQ_TABLA = TR.ID\n"
			+ "    AND TR.ID_ETIQUETA   = TE.ID\n" + "    AND TR.ID_TABLA      = TT.ID\n"
			+ "    AND TR.STATUS        = 1\n" + "    AND TT.NOMBRE            = :tableName\n"
			+ "    AND TE.NOMBRE        = 'SEMESTRE'\n" + "    AND TV.VALOR         = :semestre")
	Integer validaSemestre(@Param("semestre") String semestre, @Param("tableName")String tableName);
	
	
	@Query(nativeQuery = true, value = "SELECT  TV.ID_ROW\n" + "       FROM GEMUSER.TC_VALORES TV,\n"
			+ "            GEMUSER.TR_ETIQ_TABLAS TR,\n" + "            GEMUSER.TC_ETIQUETAS TE,\n"
			+ "            GEMUSER.TC_TABLAS TT\n" + "  WHERE TV.ID_ETIQ_TABLA = TR.ID\n"
			+ "    AND TR.ID_ETIQUETA   = TE.ID\n" + "    AND TR.ID_TABLA      = TT.ID\n"
			+ "    AND TR.STATUS        = 1\n" + "    AND TT.ID            = 1\n"
			+ "    AND TE.NOMBRE        = 'SEMESTRE'\n" + "    AND TV.VALOR         = :semestre")
	Integer getIdRow(@Param("semestre")String semestre);
	
	
	@Query(nativeQuery = true, value = "SELECT  TV.ID_ROW\n" + "       FROM GEMUSER.TC_VALORES TV,\n"
			+ "            GEMUSER.TR_ETIQ_TABLAS TR,\n" + "            GEMUSER.TC_ETIQUETAS TE,\n"
			+ "            GEMUSER.TC_TABLAS TT\n" + "  WHERE TV.ID_ETIQ_TABLA = TR.ID\n"
			+ "    AND TR.ID_ETIQUETA   = TE.ID\n" + "    AND TR.ID_TABLA      = TT.ID\n"
			+ "    AND TR.STATUS        = 1\n" + "    AND TT.NOMBRE            = :tableName\n"
			+ "    AND TE.NOMBRE        = 'SEMESTRE'\n" + "    AND TV.VALOR         = :semestre")
	Integer getIdRow(@Param("semestre")String semestre, @Param("tableName")String tableName);
	

}
