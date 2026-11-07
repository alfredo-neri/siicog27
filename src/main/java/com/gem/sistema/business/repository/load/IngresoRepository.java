package com.gem.sistema.business.repository.load;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.Ingreso;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Interface IngresoRepository.
 */
@Repository(value = "ingresoRepository")
public interface IngresoRepository
		extends PagingAndSortingRepository<Ingreso, Long>, QueryDslPredicateExecutor<Ingreso> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Ingreso> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.querydsl.QueryDslPredicateExecutor#findAll()
	 */
	List<Ingreso> findAll(Predicate prdct);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Ingreso> S save(S entity);

	/**
	 * Gets the cuentas 8110.
	 *
	 * @return the cuentas 8110
	 */
	@Query(value = "SELECT C.NOMCTA, I.CUENTA, I.CVETXT, I.SCTA, I.SSCTA, I.SSSCTA, I.SSSSCTA, I.CAMPO1, I.CLVFUEN, I.AUTOI_1, "
			+ "I.AUTOI_2, I.AUTOI_3, I.AUTOI_4, I.AUTOI_5, I.AUTOI_6, I.AUTOI_7, I.AUTOI_8, I.AUTOI_9, I.AUTOI_10, I.AUTOI_11, "
			+ "I.AUTOI_12, I.AUTOI_13, COALESCE(F.CLVFTE,'')CLVFTE "
			+ "FROM CUENTA C INNER JOIN INGRESO I ON I.CUENTA=C.CUENTA AND I.SCTA=C.SCTA AND I.SSCTA=C.SSCTA AND I.SSSCTA=C.SSSCTA "
			+ "AND I.SSSSCTA=C.SSSSCTA AND I.CVETXT='1' LEFT JOIN FUENTEF F ON I.CLVFUEN=F.LIGA AND F.IDSECTOR=1", nativeQuery = true)
	List<Object> getCuentas8110();

	/**
	 * Find first by cuenta and scta and sscta and ssscta and sssscta and idsector.
	 *
	 * @param cuenta   the cuenta
	 * @param scta     the scta
	 * @param sscta    the sscta
	 * @param ssscta   the ssscta
	 * @param sssscta  the sssscta
	 * @param idsector the idsector
	 * @return the ingreso
	 */
	Ingreso findFirstByCuentaAndSctaAndSsctaAndSssctaAndSsssctaAndIdsector(String cuenta, String scta, String sscta,
			String ssscta, String sssscta, Long idsector);

	/**
	 * Execute acumula ingreso.
	 *
	 * @param idSector the id sector
	 * @param userName the user name
	 */
	@Procedure(name = "acumula_ingreso")
	void executeAcumulaIngreso(@Param("ID_SECTOR") Integer idSector, @Param("USER_ID") String userName);

	@Query("select i from Ingreso i where idsector =:idSector order by cuenta, scta, sscta, ssscta, sssscta asc")
	List<Ingreso> getIngresosByIdsector(@Param("idSector") Long idSector);

	@Query("select i from Ingreso i where idsector =:idSector and cuenta =:cuenta and scta =:scta and sscta =:sscta and ssscta =:ssscta and sssscta =:sssscta")
	Ingreso findIngreso(@Param("idSector") Long idSector, @Param("cuenta") String cuenta, @Param("scta") String scta,
			@Param("sscta") String sscta, @Param("ssscta") String ssscta, @Param("sssscta") String sssscta);

}
