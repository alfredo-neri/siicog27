package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Cuenta;

// TODO: Auto-generated Javadoc
/**
 * The Interface CuentaRepository.
 *
 * @author s3159962
 */
@Repository("cuentaRepository")
public interface CuentaRepository
		extends PagingAndSortingRepository<Cuenta, Long>, CrudRepository<Cuenta, Long>, QuerydslPredicateExecutor<Cuenta>, CuentaRepositoryCustom {
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Cuenta> findAll();

	/**
	 * Find all by order by cuenta.
	 *
	 * @return the list
	 */
	List<Cuenta> findAllByOrderByCuenta();

	/**
	 * Find all by idsector.
	 *
	 * @param idsector the idsector
	 * @param sort     the sort
	 * @return the list
	 */
	List<Cuenta> findAllByIdsector(Long idsector, Sort sort);

	/**
	 * Find all by idsector and cuenta.
	 *
	 * @param idsector the idsector
	 * @param cuenta   the cuenta
	 * @return the list
	 */
	List<Cuenta> findAllByIdsectorAndCuenta(Long idsector, String cuenta);

	@Query(value = "select c.* from Cuenta c where c.idsector = :idSector and c.cuenta = :cuenta order by c.cuenta, c.scta, c.sscta, c.ssscta, c.sssscta", nativeQuery = true)
	List<Cuenta> findAllByIdsectorAndCuentaOrdered(@Param("idSector") Long idsector, @Param("cuenta") String cuenta);

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Cuenta> S save(S entity);

	/**
	 * Find bysscuenta.
	 *
	 * @param sscuenta the sscuenta
	 * @return the list
	 */
	List<Cuenta> findBysscuenta(String sscuenta);

	/**
	 * Find first by sscuenta and cuenta eq 5500 or 82.
	 *
	 * @param sscta  the sscta
	 * @param ssscta the ssscta
	 * @param scta   the scta
	 * @return the cuenta
	 */
	@Query(value = "SELECT * FROM Cuenta WHERE sscta like ?1||'%' and ssscta like ?2||'%'   and scta like ?3||'%' and (cuenta = '5500' OR  cuenta like '82%') fetch first 1 rows only ", nativeQuery = true)
	Cuenta findFirstBySscuentaAndCuentaEq5500Or82(final String sscta, final String ssscta, final String scta);

	/**
	 * Find first by sscuenta and cuenta.
	 *
	 * @param sscta    the sscta
	 * @param scta     the scta
	 * @param idSector the id sector
	 * @return the cuenta
	 */
	@Query(value = "SELECT * FROM Cuenta WHERE sscta =:sscta and scta = :scta and  idsector = :idSector and (cuenta = '5500' OR  cuenta like '82%') fetch first 1 rows only ", nativeQuery = true)
	Cuenta findFirstBySscuentaAndCuenta(@Param("sscta") String sscta, @Param("scta") String scta,
			@Param("idSector") Integer idSector);

	/**
	 * Reset accounts.
	 *
	 * @param idSector the id sector
	 */
	@Modifying
	@Transactional
	@Query("update Cuenta set salini = 0 where cuenta not in ('4000', '4100', '4200', '4300', '4110', '4111', '4112', '4113', '4114', '4115', '4116', '4117', '4119', '4120', '4121', '4122', '4123', '4124', '4129', '4130', '4131', '4132', '4140', '4141', '4142', '4143', '4144', '4150', '4151', '4152', '4159', '4160', '4161', '4162', '4163', '4164', '4165', '4166', '4167', '4168', '4169', '4170', '4171', '4172', '4173', '4174', '4190', '4191', '4192', '4210', '4211', '4212', '4213', '4220', '4221', '4222', '4223', '4224', '4225', '4310', '4311', '4319', '4320', '4321', '4322', '4323', '4324', '4325', '4330', '4331', '4340', '4341', '4390', '4391', '4392', '4393', '4394', '4395', '4396', '4399', '8000', '8100', '8110', '8120', '8140', '8150', '5000', '5100', '5200', '5300', '5400', '5500', '5600', '5700', '5800', '8200', '8210', '8211', '8212', '8213', '8214', '8215', '8216', '8217', '8220', '8221', '8222', '8223', '8224', '8225', '8226', '8227', '8240', '8241', '8242', '8243', '8244', '8245', '8246', '8247', '8250', '8251', '8252', '8253', '8254', '8255', '8256', '8257', '8270', '8271', '8272', '8273', '8274', '8275', '8276', '8277') and idsector = :idSector")
	void resetAccounts(@Param("idSector") Long idSector);

	/**
	 * Find one by cuenta and scuenta and sscuenta and ssscuenta and sssscuenta and
	 * idsector.
	 *
	 * @param cuenta     the cuenta
	 * @param scuenta    the scuenta
	 * @param sscuenta   the sscuenta
	 * @param ssscuenta  the ssscuenta
	 * @param sssscuenta the sssscuenta
	 * @param idSector   the id sector
	 * @return the list
	 */
	List<Cuenta> findOneByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(String cuenta, String scuenta,
			String sscuenta, String ssscuenta, String sssscuenta, Long idSector);

	/**
	 * Find accounts to backup.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query("select c from Cuenta c  where c.cuenta not in  ('4000','4100','4200','4300','4110','4111','4112','4113','4114','4115','4116','4117','4119','4120','4121','4122','4123','4124','4129','4130','4131','4132','4140','4141','4142','4143','4144','4150','4151','4152','4159','4160','4161','4162','4163','4164','4165','4166','4167','4168','4169','4170','4171','4172','4173','4174','4190','4191','4192','4210','4211','4212','4213','4220','4221','4222','4223','4224','4225','4310','4311','4319','4320','4321','4322','4323','4324','4325','4330','4331','4340','4341','4390','4391','4392','4393','4394','4395','4396','4399','8000','8100','8110','8120','8140','8150','5000','5100','5200','5300','5400','5500','5600','5700','5800','8200','8210','8211','8212','8213','8214','8215','8216','8217','8220','8221','8222','8223','8224','8225','8226','8227','8240','8241','8242','8243','8244','8245','8246','8247','8250','8251','8252','8253','8254','8255','8256','8257','8270','8271','8272','8273','8274','8275','8276','8277') and c.idsector = :idSector order by c.cuenta")
	List<Cuenta> findAccountsToBackup(@Param("idSector") Long idSector);

	/**
	 * Find first by cuenta and scuenta and sscuenta and ssscuenta and sssscuenta
	 * and idsector.
	 *
	 * @param cuenta     the cuenta
	 * @param scuenta    the scuenta
	 * @param sscuenta   the sscuenta
	 * @param ssscuenta  the ssscuenta
	 * @param sssscuenta the sssscuenta
	 * @param idSector   the id sector
	 * @return the cuenta
	 */
	Cuenta findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(String cuenta, String scuenta,
			String sscuenta, String ssscuenta, String sssscuenta, Long idSector);

	/**
	 * Find first by cuenta and scuenta and idsector.
	 *
	 * @param cuenta   the cuenta
	 * @param scuenta  the scuenta
	 * @param idSector the id sector
	 * @return the cuenta
	 */
	Cuenta findFirstByCuentaAndScuentaAndIdsector(String cuenta, String scuenta, Long idSector);

	/**
	 * Find first by cuenta and scuenta and sscuenta and idsector.
	 *
	 * @param cuenta   the cuenta
	 * @param scuenta  the scuenta
	 * @param sscuenta the sscuenta
	 * @param idSector the id sector
	 * @return the cuenta
	 */
	Cuenta findFirstByCuentaAndScuentaAndSscuentaAndIdsector(String cuenta, String scuenta, String sscuenta,
			Long idSector);

	/**
	 * Find first by cuenta and scuenta and sscuenta and ssscuenta and idsector.
	 *
	 * @param cuenta    the cuenta
	 * @param scuenta   the scuenta
	 * @param sscuenta  the sscuenta
	 * @param ssscuenta the ssscuenta
	 * @param idSector  the id sector
	 * @return the cuenta
	 */
	Cuenta findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndIdsector(String cuenta, String scuenta, String sscuenta,
			String ssscuenta, Long idSector);

	/**
	 * Gets the sum salini.
	 *
	 * @param idSector the id sector
	 * @param minCta   the min cta
	 * @param maxCta   the max cta
	 * @param scta     the scta
	 * @return the sum salini
	 */
	@Query(value = "select COALESCE(sum(COALESCE(salini,0)),0) from cuenta where cuenta >=:minCta AND cuenta <= :maxCta and scta = :scta and idsector=:idSector", nativeQuery = true)
	Object[] getSumSalini(@Param("idSector") Long idSector, @Param("minCta") String minCta,
			@Param("maxCta") String maxCta, @Param("scta") String scta);

	/**
	 * Gets the sum 3 digits account.
	 *
	 * @param idSector the id sector
	 * @param minCta   the min cta
	 * @param notcta   the notcta
	 * @return the sum 3 digits account
	 */
	@Query(value = "select substr(mc.cuenta,1,3) , sum (c.salini) from maycta mc inner join cuenta c on mc.cuenta = c.cuenta  where c.cuenta < :minCta and c.notcta= :notcta  and idsector=:idSector and c.scta='' and c.sscta='' and c.ssscta='' and c.sssscta='' group by  substr(mc.cuenta,1,3) ", nativeQuery = true)
	List<Object[]> getSum3DigitsAccount(@Param("idSector") Long idSector, @Param("minCta") String minCta,
			@Param("notcta") Integer notcta);

	/**
	 * Gets the sum 2 digits account.
	 *
	 * @param idSector the id sector
	 * @param notcta   the notcta
	 * @return the sum 2 digits account
	 */
	@Query(value = "select substr(c.cuenta,1,2) , sum (c.salini) from cuenta c where c.notcta= :notcta and idsector=:idSector group by  substr(c.cuenta,1,2)  ", nativeQuery = true)
	List<Object[]> getSum2DigitsAccount(@Param("idSector") Long idSector, @Param("notcta") Integer notcta);

	/**
	 * Gets the sum 1 digits account.
	 *
	 * @param idSector the id sector
	 * @param notcta   the notcta
	 * @return the sum 1 digits account
	 */
	@Query(value = "select substr(c.cuenta,1,1) , sum (c.salini) from cuenta c where c.notcta= :notcta and  idsector=:idSector and c.scta ='' and c.sscta ='' and c.ssscta='' group by  substr(c.cuenta,1,1) ", nativeQuery = true)
	List<Object[]> getSum1DigitsAccount(@Param("idSector") Long idSector, @Param("notcta") Integer notcta);

	/**
	 * Find first by idsector and cuenta.
	 *
	 * @param idSector the id sector
	 * @param cuenta   the cuenta
	 * @return the cuenta
	 */
	Cuenta findFirstByIdsectorAndCuenta(Long idSector, String cuenta);

	/**
	 * Execute acumulacion saldos.
	 *
	 * @param idSector the id sector
	 */
	@Procedure(name = "sp_acumulacion_saldos")
	void executeAcumulacionSaldos(@Param("ID_SECTOR") Integer idSector);

	/**
	 * Find all by cuenta and nomcta and idsector.
	 *
	 * @param cuenta   the cuenta
	 * @param scta     the scta
	 * @param sscta    the sscta
	 * @param ssscta   the ssscta
	 * @param sssscta  the sssscta
	 * @param nomcta   the nomcta
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query(value = "  select c.* " + " from Cuenta c "
			+ " where cuenta =  :cuenta " + " and c.idsector = :idSector "
			+ " and c.scta = :scta "
			+ " and c.sscta = :sscta "
			+ " and c.ssscta = :ssscta "
			+ " and c.sssscta = :sssscta"
			+ " order by c.cuenta, c.scta, c.sscta, c.ssscta, c.sssscta ", nativeQuery = true)
	List<Cuenta> findAllByCuentaAndNomctaAndIdsector(@Param("cuenta") String cuenta, @Param("scta") String scta,
			@Param("sscta") String sscta, @Param("ssscta") String ssscta, @Param("sssscta") String sssscta,
			@Param("idSector") Long idSector);

	/**
	 * Find where cta start with and ssscta not empty and id sector.
	 *
	 * @param cta      the cta
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query("Select c from Cuenta c where c.cuenta like :cta || '%' and c.ssscuenta <> '' and c.idsector = :idSector")
	List<Cuenta> findWhereCtaStartWithAndSssctaNotEmptyAndIdSector(@Param("cta") String cta,
			@Param("idSector") Long idSector);

	/**
	 * Find where cta and ssscta not empty and id sector.
	 *
	 * @param cta      the cta
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query("Select c from Cuenta c where c.cuenta = :cta and c.ssscuenta <> '' and c.idsector = :idSector")
	List<Cuenta> findWhereCtaAndSssctaNotEmptyAndIdSector(@Param("cta") String cta, @Param("idSector") Long idSector);

	/**
	 * Find where cta and sssscta not empty and id sector.
	 *
	 * @param cta      the cta
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query("Select c from Cuenta c where c.cuenta = :cta and c.sssscuenta <> '' and c.idsector = :idSector")
	List<Cuenta> findWhereCtaAndSsssctaNotEmptyAndIdSector(@Param("cta") String cta, @Param("idSector") Long idSector);

	/**
	 * Find where cta and ssscta not empty and ssssscta not empty and id sector.
	 *
	 * @param cta      the cta
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query("Select c from Cuenta c where c.cuenta = :cta and c.ssscuenta <> '' and c.sssscuenta = '' and c.idsector = :idSector")

	List<Cuenta> findWhereCtaAndSssctaNotEmptyAndSssssctaNotEmptyAndIdSector(@Param("cta") String cta,
			@Param("idSector") Long idSector);

	/**
	 * Find where cta between and ssscta not empty and id sector.
	 *
	 * @param startCta the start cta
	 * @param endCta   the end cta
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query("Select c from Cuenta c where c.cuenta >= :startCta and c.cuenta <= :endCta and c.ssscuenta <> '' and c.idsector = :idSector")
	List<Cuenta> findWhereCtaBetweenAndSssctaNotEmptyAndIdSector(@Param("startCta") String startCta,
			@Param("endCta") String endCta, @Param("idSector") Long idSector);

	/**
	 * Find acount by acount.
	 *
	 * @param partida  the partida
	 * @param idSector the id sector
	 * @return the cuenta
	 */
	@Query("select c from Cuenta c where c.idsector = :idSector and c.cuenta = :cuenta and c.scuenta = '' and c.sscuenta = '' and c.ssscuenta = '' and c.sssscuenta = ''")
	Cuenta findAcountByAcount(@Param("cuenta") String partida, @Param("idSector") Integer idSector);

	/**
	 * Find all where cuenta begins and scta begins and id sector.
	 *
	 * @param cuenta   the cuenta
	 * @param scta     the scta
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query("select c from Cuenta c where c.cuenta like :cta || '%' and c.scuenta like :scta || '%' and c.nivcta='S' and c.idsector = :idsector")
	List<Cuenta> findAllWhereCuentaBeginsAndSctaBeginsAndIdSector(@Param("cta") String cuenta,
			@Param("scta") String scta, @Param("idsector") Long idSector);

	/**
	 * Find first by cuenta and idsector order by id asc.
	 *
	 * @param cuenta   the cuenta
	 * @param idsector the idsector
	 * @return the cuenta
	 */
	@Query(value = "select c.* from Cuenta c where c.cuenta like :cta || '%' and c.nivcta = 'S' and c.idsector = :idsector order by id ASC fetch first 1 rows only", nativeQuery = true)
	Cuenta findFirstByCuentaAndIdsectorOrderByIdAsc(@Param("cta") String cuenta, @Param("idsector") Long idsector);

	/**
	 * Update name by cuenta and scta and idsector.
	 *
	 * @param nomcta   the nomcta
	 * @param idsector the idsector
	 * @param cuenta   the cuenta
	 * @param scta     the scta
	 * @return the int
	 */
	@Modifying
	@Transactional
	@Query("UPDATE Cuenta  SET nomcta = :nomcta WHERE idsector = :idsector and cuenta= :cuenta and scuenta= :scta")
	int updateNameByCuentaAndSctaAndIdsector(@Param("nomcta") String nomcta, @Param("idsector") Long idsector,
			@Param("cuenta") String cuenta, @Param("scta") String scta);

	@Query(value = "select c from Cuenta c where c.scuenta='' and c.idsector=:sector")
	List<Cuenta> findFirstLevelAccountsBySector(@Param("sector") Long sector);
	
	@Query("Select c from Cuenta c where c.cuenta like :cta || '%' and c.ssscuenta <> '' and c.idsector = :idSector order by c.cuenta ,c.scuenta,c.sscuenta,c.ssscuenta")
	List<Cuenta> findWhereCtaStartWithAndSssctaNotEmptyAndIdSectorOrdered(@Param("cta") String cta,
			@Param("idSector") Long idSector);

	@Query("Select c from Cuenta c where c.scuenta =:cta  and c.sscuenta =:scta and c.ssscuenta =:sscta and c.sssscuenta =:ssscta  "
			+ " and c.idsector = :idSector and c.cuenta in('8120','8140','8150') order by c.cuenta ,c.scuenta,c.sscuenta,c.ssscuenta")
	List<Cuenta> find8120And8140And8150By4xxx(@Param("cta") String cta, @Param("scta") String scta,
			@Param("sscta") String sscta, @Param("ssscta") String ssscta, @Param("idSector") Long idSector);

}
