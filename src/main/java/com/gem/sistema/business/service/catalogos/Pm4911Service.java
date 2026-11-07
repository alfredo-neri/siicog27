package com.gem.sistema.business.service.catalogos;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Temporal;

import com.gem.sistema.business.domain.Pm4911;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm4911Service.
 */
public interface Pm4911Service {
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
   List<Pm4911> findAll();
   
   /**
    * Find all by sector.
    *
    * @param idSector the id sector
    * @return the list
    */
   List<Pm4911> findAllBySector(Integer idSector);
       
   /**
    * Find all.
    *
    * @param pageRequest the page request
    * @return the page
    */
   Page<Pm4911> findAll(Pageable pageRequest);

	/**
	 * Save.
	 *
	 * @param entity the entity
	 * @return the pm 4911
	 */
	Pm4911 save(Pm4911 entity);

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the pm 4911
	 */
	Pm4911 findById(Long id);
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	void delete(Long id);
	
}
