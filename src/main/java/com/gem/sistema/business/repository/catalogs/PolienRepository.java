package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gem.sistema.business.domain.Polien;

// TODO: Auto-generated Javadoc
/**
 * The Interface PolienRepository.
 */
@Repository("polienRepository")
public interface PolienRepository extends PagingAndSortingRepository<Polien, Long>, QueryDslPredicateExecutor<Polien> {

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Polien> findAll();

	/**
	 * Find by idsector.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Polien> findByIdsector(Integer idSector);

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Polien> S save(S entity);

	// @Query("select en from Polien en where en.mespol = 2 and en.conpol = 1
	/**
	 * Gets the by first month.
	 *
	 * @param idSector the id sector
	 * @return the by first month
	 */
	// and en.tippol = 'C' and idsector = :idSector")
	@Query(value = "SELECT EN.* FROM POLIEN EN WHERE  EN.TIPPOL  = 'C' AND IDSECTOR = :idSector ORDER BY MESPOL, EN.CONPOL FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
	Polien getByFirstMonth(@Param("idSector") Integer idSector);

	/**
	 * Gets the poliza.
	 *
	 * @param mes the mes
	 * @param numero the numero
	 * @param tipo the tipo
	 * @param idSector the id sector
	 * @return the poliza
	 */
	@Query("select en from Polien en where en.mespol = :mes and en.conpol = :numero and en.tippol = :tipo and idsector = :idSector")
	Polien getPoliza(@Param("mes") int mes, @Param("numero") int numero, @Param("tipo") String tipo,
			@Param("idSector") Integer idSector);

	/**
	 * Xiste mont.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select count(1) from Polien en where en.mespol = :mes and idsector = :idSector ")
	Integer xisteMont(@Param("mes") Integer mes, @Param("idSector") Integer idSector);

	/**
	 * Checks if is month afect.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @param staafe the staafe
	 * @return the integer
	 */
	@Query("select count(1) from Polien where mespol = :mes and idsector = :idSector and staafe = :staafe ")
	Integer isMonthAfect(@Param("mes") Integer mes, @Param("idSector") Integer idSector,
			@Param("staafe") String staafe);

	/**
	 * Fin by next.
	 *
	 * @param idSector the id sector
	 * @param next the next
	 * @return the polien
	 */
	@Query("select en from Polien en where (en.mespol, en.conpol, en.tippol) in "
			+ "(select min(mespol), min(conpol)+:next, min(tippol) from Polien where idsector = :idSector )"
			+ " and en.idsector = :idSector")
	Polien finByNext(@Param("idSector") Integer idSector, @Param("next") Integer next);

	/**
	 * Find min.
	 *
	 * @param idSector the id sector
	 * @return the polien
	 */
	@Query("select en from Polien en where (en.clvpol) in "
			+ "(select min(clvpol) from Polien where idsector = :idSector )" + " and en.idsector = :idSector")
	Polien findMin(@Param("idSector") Integer idSector);

	/**
	 * Find max.
	 *
	 * @param idSector the id sector
	 * @return the polien
	 */
	@Query("select en from Polien en where (en.clvpol) in "
			+ "(select max(clvpol) from Polien where idsector = :idSector )" + " and en.idsector = :idSector")
	Polien findMax(@Param("idSector") Integer idSector);

	/**
	 * Next.
	 *
	 * @param idSector the id sector
	 * @param increment the increment
	 * @return the polien
	 */
	@Query("select en from Polien en where en.id in (select min(id)+:increment from Polien where en.idsector = :idSector) ")
	Polien next(@Param("idSector") Integer idSector, @Param("increment") Long increment);

	/**
	 * Count poliza.
	 *
	 * @param mes the mes
	 * @param tipo the tipo
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select max(de.conpol)+1 from Polien de where de.mespol = :mes and de.tippol = :tipo  and idsector = :idSector")
	Integer countPoliza(@Param("mes") Integer mes, @Param("tipo") String tipo, @Param("idSector") Integer idSector);

	/**
	 * Max polizas.
	 *
	 * @param mes the mes
	 * @param tipo the tipo
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select max(en.conpol) from Polien en where en.mespol = :mes and en.tippol = :tipo  and en.idsector = :idSector")
	Integer maxPolizas(@Param("mes") Integer mes, @Param("tipo") String tipo, @Param("idSector") Integer idSector);

	/**
	 * Find by mespol and tippol and conpol and idsector.
	 *
	 * @param mes the mes
	 * @param tipo the tipo
	 * @param numero the numero
	 * @param idSector the id sector
	 * @return the polien
	 */
	Polien findByMespolAndTippolAndConpolAndIdsector(Integer mes, String tipo, Integer numero, Integer idSector);

	/**
	 * Gets the page.
	 *
	 * @param mes the mes
	 * @param tipo the tipo
	 * @param conpol the conpol
	 * @param idSector the id sector
	 * @return the page
	 */
	@Query(value = "select x.page from( select ROW_NUMBER() OVER() as page, conpol from POLIEN "
			+ "where TIPPOL = :tipo and MESPOL = :mes and IDSECTOR = :idSector order by TIPPOL, MESPOL, CONPOL) as x "
			+ "where x.conpol = :conpol ", nativeQuery = true)
	Integer getPage(@Param("mes") Integer mes, @Param("tipo") String tipo, @Param("conpol") Integer conpol,
			@Param("idSector") Integer idSector);

	/**
	 * Count reg policies.
	 *
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query(value = "select count(*) from (select p.* from Polien p "
			+ "inner join Conctb ctb on p.anopol = ctb.anoemp and p.mespol = ctb.mesemp "
			+ "inner join Cattpo cat on p.tippol = cat.tippol "
			+ "where ctb.clvemp = '1' and ctb.idsector = :idSector)", nativeQuery = true)
	Integer countRegPolicies(@Param("idSector") Long idSector);

	/**
	 * Checks if is afected mes sector.
	 *
	 * @param idSector the id sector
	 * @param mes the mes
	 * @return the integer
	 */
	@Query(value = "select count(p.id)	from polien p inner join conctb c on p.idsector=c.idsector and p.anopol=c.ANOEMP	where c.idsector=:idSector and p.mespol=:mes and p.staafe<>'A'", nativeQuery = true)
	Integer isAfectedMesSector(@Param("idSector") Integer idSector, @Param("mes") Integer mes);
	
	/**
	 * Checks if is pol incomplet mes sector.
	 *
	 * @param idSector the id sector
	 * @param mes the mes
	 * @return the integer
	 */
	@Query(value = "select count(p.id)	from polien p inner join conctb c on p.idsector=c.idsector and p.anopol=c.ANOEMP	where c.idsector=:idSector and p.mespol=:mes and p.stapol<>'C'", nativeQuery = true)
	Integer isPolIncompletMesSector(@Param("idSector") Integer idSector, @Param("mes") Integer mes);

	/**
	 * Checks if is polizas mes sector.
	 *
	 * @param idSector the id sector
	 * @param mes the mes
	 * @return the integer
	 */
	@Query(value = "select count(p.id)	from polien p inner join conctb c on p.idsector=c.idsector and p.anopol=c.ANOEMP	where c.idsector=:idSector and p.mespol=:mes", nativeQuery = true)
	Integer isPolizasMesSector(@Param("idSector") Integer idSector, @Param("mes") Integer mes);
	
	
	/**
	 * Find first by mespol and anopol and conpol and tippol and idsector order by id.
	 *
	 * @param mespol the mespol
	 * @param anopol the anopol
	 * @param conpol the conpol
	 * @param tippol the tippol
	 * @param idsector the idsector
	 * @return the polien
	 */
	Polien findFirstByMespolAndAnopolAndConpolAndTippolAndIdsectorOrderById(Integer mespol, Integer anopol,
			Integer conpol, String tippol, Integer idsector);

  /**
   * Find first by staafe and idsector order by anopol desc mespol desc.
   *
   * @param staafe the staafe
   * @param idSector the id sector
   * @return the polien
   */
  Polien findFirstByStaafeAndIdsectorOrderByAnopolDescMespolDesc(String staafe, Integer idSector);

  /**
   * Find one by anio and mes order by id asc.
   *
   * @param anio the anio
   * @param mes the mes
   * @param idsector the idsector
   * @param pageable the pageable
   * @return the page
   */
  @Query("Select p from Polien p where p.anopol = :anio and p.mespol <= :mes and p.staafe <> 'A' and p.idsector = :idsector")
  Page<Polien> findOneByAnioAndMesOrderByIdAsc(@Param("anio") Integer anio, @Param("mes") Integer mes, @Param("idsector") Integer idsector, Pageable pageable);

  /**
   * Find first by mespol and anopol and tippol and idsector order by id desc.
   *
   * @param mespol the mespol
   * @param anopol the anopol
   * @param tippol the tippol
   * @param idSector the id sector
   * @return the polien
   */
  Polien findFirstByMespolAndAnopolAndTippolAndIdsectorOrderByIdDesc(Integer mespol, Integer anopol, String tippol, Integer idSector);
  
  /**
   * Count polienby user.
   *
   * @param idSector the id sector
   * @param user the user
   * @return the list
   */
  @Query("select p from Polien p where idsector =:idSector and cappol =:user")
  List<Polien> countPolienbyUser(@Param("idSector")Integer idSector, @Param("user")String user);
  
  @Query(value = "select p from Polien p "
			+ "where p.tippol = :tippol and p.mespol = :mespol and p.idsector = :idSector " + " order by conpol ")
	List<Polien> getByMespolAndTippolAndIdSector(@Param("mespol") Integer mespol, @Param("tippol") String tippol,
			@Param("idSector") Integer idSector);
  
  
}
