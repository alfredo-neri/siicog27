package com.gem.sistema.business.service.catalogos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.Polien;


// TODO: Auto-generated Javadoc
/**
 * The Interface PolideService.
 *
 * @author Gerardo CUERO
 */
public interface PolideService {
    
    /**
     * Find all.
     *
     * @return the list
     */
    List<Polide> findAll();
        
    /**
     * Find all.
     *
     * @param pageRequest the page request
     * @return the page
     */
    Page<Polide> findAll(Pageable pageRequest);

	/**
	 * Save.
	 *
	 * @param Polide the polide
	 * @return the polide
	 */
	Polide save(Polide Polide);

	/**
	 * Find polide by id.
	 *
	 * @param id the id
	 * @return the polide
	 */
	Polide findPolideById(Long id);
    
	/**
	 * Delete polide.
	 *
	 * @param Polide the polide
	 */
	void deletePolide(Polide Polide);
  
	/**
	 * Find by description.
	 *
	 * @param searchTerm the search term
	 * @param pageRequest the page request
	 * @return the page
	 */
	Page<Polide> findByDescription(String searchTerm, Pageable pageRequest);    
    
    /**
     * Find all by filters.
     *
     * @param filters the filters
     * @param pageRequest the page request
     * @param count the count
     * @return the page
     */
    Page<Polide> findAllByFilters(Map<String, Object> filters, Pageable pageRequest, Integer count);
    
    /**
     * Find all by filters.
     *
     * @param filters the filters
     * @param pageRequest the page request
     * @param count the count
     * @param renpol the renpol
     * @return the page
     */
    Page<Polide> findAllByFilters(Map<String, Object> filters, Pageable pageRequest, Integer count, Integer renpol );
    
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
     * Buscar cantidad referencia.
     *
     * @param cantidad the cantidad
     * @param referencia the referencia
     * @param idSector the id sector
     * @return the list
     */
    public List<Polide> buscarCantidadReferencia(BigDecimal cantidad, BigDecimal referencia, Integer idSector);
 
    /**
     * Consulta movimientos.
     *
     * @param polide the polide
     * @return the list
     */
    List<Polide> consultaMovimientos(Polide polide);
}
