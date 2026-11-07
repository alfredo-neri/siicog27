package com.gem.sistema.business.repository.catalogs;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Polide;

// TODO: Auto-generated Javadoc
/**
 * The Interface PolideRepository.
 */
@Repository("polideRepository")
public interface PolideRepository
		extends PagingAndSortingRepository<Polide, Long>, CrudRepository<Polide, Long>, QuerydslPredicateExecutor<Polide>, PolideRepositoryCustom {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Polide> findAll();

	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Polide> findAllByIdsector(Integer idSector);

	/**
	 * Find by scta.
	 *
	 * @param scta the scta
	 * @return the list
	 */
	List<Polide> findByScta(String scta);

	/**
	 * Find by idsector and canpol and mespol.
	 *
	 * @param idsector the idsector
	 * @param canpol   the canpol
	 * @param mespol   the mespol
	 * @return the list
	 */
	List<Polide> findByIdsectorAndCanpolAndMespol(Integer idsector, BigDecimal canpol, Integer mespol);

	// select * from polide where polide.concep = 'Sueldo base.' order by id ASC;
	/**
	 * Find first by idsector and concep order by id asc.
	 *
	 * @param idsector the idsector
	 * @param concep   the concep
	 * @return the polide
	 */
	// findFirstByIdsectorAndClvdepOrderByIdAsc
	Polide findFirstByIdsectorAndConcepAndAnopolOrderByIdAsc(Integer idsector, String concep, Integer anopol);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Polide> S save(S entity);

	/**
	 * Gets the by all detail.
	 *
	 * @param mes      the mes
	 * @param numero   the numero
	 * @param tipo     the tipo
	 * @param idSector the id sector
	 * @return the by all detail
	 */
	@Query("select po from Polide po where po.mespol = :mes and po.conpol = :numero and po.tippol = :tipo and idsector = :idSector")
	List<Polide> getByAllDetail(@Param("mes") Integer mes, @Param("numero") Integer numero, @Param("tipo") String tipo,
			@Param("idSector") Integer idSector);

	/**
	 * Find row.
	 *
	 * @param mes      the mes
	 * @param numero   the numero
	 * @param tipo     the tipo
	 * @param renglon  the renglon
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query("select de from Polide de where de.mespol = :mes and de.conpol = :numero and de.tippol = :tipo and de.renpol = :renglon  and idsector = :idSector ")
	List<Polide> findRow(@Param("mes") Integer mes, @Param("numero") Integer numero, @Param("tipo") String tipo,
			@Param("renglon") BigDecimal renglon, @Param("idSector") Integer idSector);

	/**
	 * Next renpol.
	 *
	 * @param anopol   the anopol
	 * @param mes      the mes
	 * @param tipo     the tipo
	 * @param conpol   the conpol
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select max(de.renpol) + 1.0 from Polide de where de.anopol = :anopol and de.mespol = :mes and de.tippol = :tipo and de.conpol = :conpol and de.idsector = :idSector ")
	BigDecimal nextRenpol(@Param("anopol") Integer anopol, @Param("mes") Integer mes, @Param("tipo") String tipo,
			@Param("conpol") Integer conpol, @Param("idSector") Integer idSector);

	/**
	 * Gets the by year and month.
	 *
	 * @param anopol   the anopol
	 * @param mespol   the mespol
	 * @param idsector the idsector
	 * @param pageable the pageable
	 * @return the by year and month
	 */
	@Query("Select de from Polide de where de.anopol = :anopol and de.mespol = :mespol and de.idsector = :idsector order by de.tippol, de.conpol, de.renpol ASC")
	Page<Polide> getByYearAndMonth(@Param("anopol") Integer anopol, @Param("mespol") Integer mespol,
			@Param("idsector") Integer idsector, Pageable pageable);

	/**
	 * Gets the by month and levels.
	 *
	 * @param month    the month
	 * @param year     the year
	 * @param cta      the cta
	 * @param scta     the scta
	 * @param sscta    the sscta
	 * @param idsector the idsector
	 * @param pageable the pageable
	 * @return the by month and levels
	 */
	@Query("Select p from Polide p where p.mespol = :xmes and p.anopol = :year and p.cuenta = :cta and p.scta = :scta and p.sscta = :sscta and p.idsector = :idsector and not (p.canpol = 0 and p.canpolh = 0)")
	Page<Polide> getByMonthAndLevels(@Param("xmes") Integer month, @Param("year") Integer year,
			@Param("cta") String cta, @Param("scta") String scta, @Param("sscta") String sscta,
			@Param("idsector") Integer idsector, Pageable pageable);

	/**
	 * Gets the by mes and cantidad.
	 *
	 * @param mes      the mes
	 * @param cantidad the cantidad
	 * @param idSector the id sector
	 * @return the by mes and cantidad
	 */
	@Query("select po from Polide po where po.mespol = :mes "
			+ " and (po.canpol = :cantidad or po.canpolh = :cantidad) "
//			+ " and idRef = :idRef "
			+ " and idsector = :idSector ")
	List<Polide> getByMesAndCantidad(@Param("mes") Integer mes, @Param("cantidad") BigDecimal cantidad,
			// @Param("idRef") Long idRef,
			@Param("idSector") Integer idSector);

	/**
	 * Buscar cantidad referencia.
	 *
	 * @param cantidad   the cantidad
	 * @param referencia the referencia
	 * @param idSector   the id sector
	 * @return the list
	 */
	@Query("select de from Polide de where de.refpol = :referencia and (de.canpol=:cantidad or de.canpolh=:cantidad)  and idsector=:idSector order by mespol, fecpol")
	List<Polide> buscarCantidadReferencia(@Param("cantidad") BigDecimal cantidad,
			@Param("referencia") BigDecimal referencia, @Param("idSector") Integer idSector);

	/**
	 * Find by idsector and tippol and conpol and renpol and mespol and anopol.
	 *
	 * @param idsector the idsector
	 * @param tippol   the tippol
	 * @param conpol   the conpol
	 * @param renpol   the renpol
	 * @param mespol   the mespol
	 * @param anopol   the anopol
	 * @return the polide
	 */
	Polide findByIdsectorAndTippolAndConpolAndRenpolAndMespolAndAnopol(Integer idsector, String tippol, Integer conpol,
			BigDecimal renpol, Integer mespol, Integer anopol);

	/**
	 * Find by idsector and tippol and conpol and mespol and anopol.
	 *
	 * @param idsector the idsector
	 * @param tippol   the tippol
	 * @param conpol   the conpol
	 * @param mespol   the mespol
	 * @param anopol   the anopol
	 * @return the list
	 */
	List<Polide> findByIdsectorAndTippolAndConpolAndMespolAndAnopol(Integer idsector, String tippol, Integer conpol,
			Integer mespol, Integer anopol);

	List<Polide> findAllByIdsectorAndTippolAndConpolAndMespolAndAnopolAndCuenta(Integer idsector, String tippol,
			Integer conpol, Integer mespol, Integer anopol, String cuenta);

	@Query(value = "SELECT * FROM POLIDE P WHERE MESPOL=:mespol AND CONPOL=:conpol AND TIPPOL=:tippol AND ANOPOL =:anopol AND "
			+ "	IDSECTOR =:idsector AND CUENTA='4223' AND SCTA='000000000000001' AND	"
			+ "	SSCTA='000000000000000004'	AND SSSCTA IN('0001','0002') AND SSSSCTA='' ", nativeQuery = true)
	List<Polide> getAllByIdsectorAndTippolAndMespolAndAnopolAndConpol(@Param("idsector") Integer idsector,
			@Param("tippol") String tippol, @Param("mespol") Integer mespol, @Param("anopol") Integer anopol,
			@Param("conpol") Integer conpol);

	Polide findByIdsectorAndTippolAndConpolAndMespolAndAnopolAndCuenta(Integer idsector, String tippol, Integer conpol,
			Integer mespol, Integer anopol, String cuenta);

	Polide findById(Integer id);

	@Query("select de from Polide de where de.anopol=:anopol and de.mespol=:mespol and de.tippol=:tippol and de.conpol=:conpol and de.cuenta=:cuenta and idsector=:sector and de.canpolh <> 0 ")
	Polide findByIdsectorAndTippolAndConpolAndRenpolAndMespolAndAnopolWithAbono(@Param("sector") Integer sector,
			@Param("tippol") String tippol, @Param("anopol") Integer anopol, @Param("mespol") Integer mespol,
			@Param("conpol") Integer conpol, @Param("cuenta") String cuenta);
}
