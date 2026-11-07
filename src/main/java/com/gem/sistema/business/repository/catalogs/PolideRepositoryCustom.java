package com.gem.sistema.business.repository.catalogs;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.Polien;

// TODO: Auto-generated Javadoc
/**
 * Interface para acceso de datos implementados manualmente.
 * 
 * @author Luis Sosa
 */

public interface PolideRepositoryCustom {

	/**
	 * Find all by filters.
	 *
	 * @param filters the filters
	 * @param pageable the pageable
	 * @param count the count
	 * @return the page
	 */
	Page<Polide> findAllByFilters(Map<String, Object> filters, Pageable pageable, Integer count);
	
	/**
	 * Find all by filters.
	 *
	 * @param filters the filters
	 * @param pageable the pageable
	 * @param count the count
	 * @param renpol the renpol
	 * @return the page
	 */
	Page<Polide> findAllByFilters(Map<String, Object> filters, Pageable pageable, Integer count, Integer renpol );

	/**
	 * Count.
	 *
	 * @param filters the filters
	 * @return the integer
	 */
	Integer count(Map<String, Object> filters);
	
	/**
	 * Act cargo abono.
	 *
	 * @param polien the polien
	 * @param idSector the id sector
	 */
	void actCargoAbono(Polien polien, Integer idSector);

	/**
	 * Gets the last row.
	 *
	 * @param anopol the anopol
	 * @param tippol the tippol
	 * @param conpol the conpol
	 * @param idsector the idsector
	 * @param mespol the mespol
	 * @return the last row
	 */
	Polide getLastRow(Integer anopol, String tippol, Integer conpol, Integer idsector, Integer mespol);
	
	/**
	 * Consulta movimientos.
	 *
	 * @param polide the polide
	 * @return the list
	 */
	List<Polide> consultaMovimientos(Polide polide);
}