package com.gem.sistema.business.repository.load;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.query.Procedure;

import com.gem.sistema.business.domain.Paso;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Interface PasoRepository.
 */
@Repository(value = "pasoRepository")
public interface PasoRepository extends PagingAndSortingRepository<Paso, Long>, QueryDslPredicateExecutor<Paso> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Paso> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.querydsl.QueryDslPredicateExecutor#findAll()
	 */
	List<Paso> findAll(Predicate prdct);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Paso> S save(S entity);

	/**
	 * Validate programa.
	 *
	 * @param programa the programa
	 * @return the integer
	 */
	@Query("select count(1) from Paso p where substr(p.partida,1,1) = '6' and p.catsector = 1 and p.programa = :programa")
	Integer validatePrograma(@Param("programa") String programa);

	/**
	 * Exist budget.
	 *
	 * @return the big decimal
	 */
	@Query("select sum(p.auto113) from Paso p where substr(p.partida,1,1) = '6' and p.catsector = 1")
	BigDecimal existBudget();

	/**
	 * Find without ceros.
	 *
	 * @return the list
	 */
	@Query("select p.clave, p.programa, p.partida, sum(p.auto), sum(p.ejpa), sum(p.ejxpa), sum(p.toeje), sum(p.porpa) from Paso p where substr(p.partida, 2, 3) <> '000' and substr(p.partida, 3, 2) <> '00' and substr(p.partida, 4, 1) <> '0' group by p.clave, p.programa, p. partida")
	List<Object[]> findWithoutCeros();

	/**
	 * Find first by clave and programa and partida and catsector order by id asc.
	 *
	 * @param clave     the clave
	 * @param programa  the programa
	 * @param partida   the partida
	 * @param catsector the catsector
	 * @return the paso
	 */
	@Query(value = "select p.* from Paso p where p.clave = :clave and p.programa = :programa and p.partida = :partida and idsector = :idsector order by id ASC fetch first 1 rows only", nativeQuery = true)
	Paso findFirstByClaveAndProgramaAndPartidaAndCatsectorOrderByIdAsc(@Param("clave") String clave,
			@Param("programa") String programa, @Param("partida") String partida, @Param("idsector") Integer catsector);

	/**
	 * Find not existing by clave and programa and partida and idsector.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query(value = "select CONCAT(SUBSTR(x.clvpro,1,6),'000') cla, CONCAT(SUBSTR(clvpro,7,9),'0000') prog, n.clvgas part  from xcatpro as x, natgas as n where n.clvgas not like '%0'  and x.sectorid = :idSector and n.idSector = :idSector except  select clave cla, programa prog, partida part from paso p where p.idsector = :idSector ", nativeQuery = true)
	List<Object[]> findNotExistingByClaveAndProgramaAndPartidaAndIdsector(@Param("idSector") Integer idSector);

	/**
	 * Execute genera partidas presupuestales en cero.
	 *
	 * @param idSector the id sector
	 * @param userName the user name
	 * @return the string
	 */
	@Procedure(name = "genera_partidas_en_cero", outputParameterName = "INSERTED_ROWS")
	String executeGeneraPartidasPresupuestalesEnCero(@Param("ID_SECTOR") Integer idSector,
			@Param("USER_NAME") String userName);

	/**
	 * Gets the xdire by idsector.
	 *
	 * @param xdire    the xdire
	 * @param Idsector the idsector
	 * @return the xdire by idsector
	 */
	@Query(value = "select p.partida, count (1) from Paso p  where  p.idsector =:Idsector and substr(p.programa,1,2)=:xdire group by p.partida", nativeQuery = true)
	List<String> getXdireByIdsector(@Param("xdire") String xdire, @Param("Idsector") Integer Idsector);

	/**
	 * Execute pistov 409.
	 *
	 * @param idSector the id sector
	 * @param userName the user name
	 * @return the string
	 */
	@Procedure(name = "pistov409", outputParameterName = "NOTFOUND_ROWS")
	String executePistov409(@Param("ID_SECTOR") Integer idSector, @Param("USER_ID") String userName);

	@Procedure(name = "pistov409_M")
	String executePistov409M(@Param("I_ID_SECTOR") Integer idSector, @Param("I_USER") String userName);

	/**
	 * Find clave and programa by id sector.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query(value = "select clave, SUBSTR(programa,1,12) from paso where idsector = :idSector group by clave, SUBSTR(programa,1,12)", nativeQuery = true)
	List<Object[]> findClaveAndProgramaByIdSector(@Param("idSector") Integer idSector);

	/**
	 * Find partida primer nivel by id sector.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query(value = "select partida, sum(auto1_1) sum1, sum(auto1_2) sum2, sum(auto1_3) sum3, sum(auto1_4) sum4, sum(auto1_5) sum5 ,sum(auto1_6) sum6, sum(auto1_7) sum7, sum(auto1_8) sum8, sum(auto1_9) sum9, sum(auto1_10) sum10, sum(auto1_11) sum11, sum(auto1_12) sum12 from paso where partida like '%000' and idsector = :idSector group by partida ", nativeQuery = true)
	List<Object[]> findPartidaPrimerNivelByIdSector(@Param("idSector") Integer idSector);

	@Query(value = "SELECT DISTINCT PROGRAMA FROM PASO WHERE IDSECTOR = :idSector ORDER BY PROGRAMA ASC", nativeQuery = true)
	List<String> findProgramasByIdSector(@Param("idSector") Integer idSector);

	@Query(value = "SELECT SUBSTR(P.PROGRAMA,1,12) TIPO FROM PASO P WHERE P.IDSECTOR = :idSector GROUP BY SUBSTR(P.PROGRAMA,1,12 ) ORDER BY 1 ", nativeQuery = true)
	List<String> getProyectoByIdSector(@Param("idSector") Integer idSector);

	@Query(value = "SELECT P.* FROM PASO P " + " WHERE P.IDSECTOR=:idsector AND P.PARTIDA=:partida "
			+ " ORDER BY P.CLAVE, P.PROGRAMA " + " FETCH FIRST 100 ROWS ONLY", nativeQuery = true)
	List<Paso> findFirst100ByIdCatSectorAndPartida(@Param("idsector") Integer idSector,
			@Param("partida") String partida);
}
