package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TcRegistraArchivoDigital;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcRegistraArchivoDigitalRepository.
 */
@Repository("tcRegistraArchivoDigitalRepository")
public interface TcRegistraArchivoDigitalRepository extends PagingAndSortingRepository<TcRegistraArchivoDigital, Long>,
		QueryDslPredicateExecutor<TcRegistraArchivoDigital> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcRegistraArchivoDigital> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcRegistraArchivoDigital> S save(S entity);

	/**
	 * Find all by mes and numero and tipo and sector id.
	 *
	 * @param mes the mes
	 * @param numero the numero
	 * @param tipo the tipo
	 * @param idSector the id sector
	 * @return the list
	 */
	List<TcRegistraArchivoDigital> findAllByMesAndNumeroAndTipoAndSectorId(String mes, int numero, String tipo,
			Integer idSector);

	/**
	 * Find all by mes and numero and tipo and sector id and anio.
	 *
	 * @param mes the mes
	 * @param numero the numero
	 * @param tipo the tipo
	 * @param idSector the id sector
	 * @param anio the anio
	 * @return the tc registra archivo digital
	 */
	TcRegistraArchivoDigital findAllByMesAndNumeroAndTipoAndSectorIdAndAnio(String mes, Integer numero, String tipo,
			Integer idSector, String anio);

	/**
	 * Gets the file digital.
	 *
	 * @param mes the mes
	 * @param numero the numero
	 * @param tipo the tipo
	 * @param idSector the id sector
	 * @param anio the anio
	 * @return the file digital
	 */
	@Query("select ar from TcRegistraArchivoDigital ar where ar.mes = :mes and ar.numero = :numero and ar.tipo = :tipo and ar.sectorId = :idSector and ar.anio =:anio ")
	TcRegistraArchivoDigital getFileDigital(@Param("mes") String mes, @Param("numero") Integer numero,
			@Param("tipo") String tipo, @Param("idSector") Integer idSector, @Param("anio") String anio);

	/**
	 * Gets the count by poliza.
	 *
	 * @param mes the mes
	 * @param numero the numero
	 * @param tipo the tipo
	 * @param idSector the id sector
	 * @return the count by poliza
	 */
	@Query("select count(1) from Polien po where po.mespol = :mes and po.conpol = :numero and po.tippol = :tipo and po.idsector = :idSector ")
	Integer getCountByPoliza(@Param("mes") int mes, @Param("numero") int numero, @Param("tipo") String tipo,
			@Param("idSector") Integer idSector);

	/**
	 * Gets the poli bby.
	 *
	 * @param mes the mes
	 * @param numero the numero
	 * @param tipo the tipo
	 * @param idSector the id sector
	 * @return the poli bby
	 */
	@Query("select ar from TcRegistraArchivoDigital ar where ar.mes = :mes and ar.numero = :numero and ar.tipo = :tipo and ar.sectorId = :idSector ")
	TcRegistraArchivoDigital getPoliBby(@Param("mes") String mes, @Param("numero") int numero,
			@Param("tipo") String tipo, @Param("idSector") Integer idSector);

	/**
	 * Checks if is poli true.
	 *
	 * @param mes the mes
	 * @param numero the numero
	 * @param tipo the tipo
	 * @return the integer
	 */
	@Query("select count(ar) from TcRegistraArchivoDigital ar where ar.mes = :mes and ar.numero = :numero and ar.tipo = :tipo")
	Integer isPoliTrue(@Param("mes") String mes, @Param("numero") int numero, @Param("tipo") String tipo);

	/**
	 * Checks if is poli true.
	 *
	 * @param mes the mes
	 * @param numero the numero
	 * @param tipo the tipo
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select count(ar) from TcRegistraArchivoDigital ar where ar.mes = :mes and ar.numero = :numero and ar.tipo = :tipo and ar.sectorId =:idSector")
	Integer isPoliTrue(@Param("mes") String mes, @Param("numero") Integer numero, @Param("tipo") String tipo,
			@Param("idSector") Integer idSector);

	/**
	 * Update file policy.
	 *
	 * @param mes the mes
	 * @param numero the numero
	 * @param tipo the tipo
	 * @param fileName the file name
	 */
	@Modifying
	@Query("update TcRegistraArchivoDigital ar set nombre_archivo = :fileName  where ar.mes = :mes and ar.numero = :numero and ar.tipo = :tipo ")
	void updateFilePolicy(@Param("mes") String mes, @Param("numero") int numero, @Param("tipo") String tipo,
			@Param("fileName") String fileName);

}
