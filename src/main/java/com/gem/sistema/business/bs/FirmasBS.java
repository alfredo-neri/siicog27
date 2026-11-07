package com.gem.sistema.business.bs;

import com.gem.sistema.business.domain.Firmas;

// TODO: Auto-generated Javadoc
/**
 * The Interface FirmasBS.
 *
 * @author Mateo
 */
public interface FirmasBS {
	
	/**
	 * Find allbyid sector.
	 *
	 * @param idSector the id sector
	 * @return the firmas
	 */
	Firmas findAllbyidSector(Integer idSector);
	
	/**
	 * Save.
	 *
	 * @param entity the entity
	 */
	void save(Firmas entity);
	
	/**
	 * Modify.
	 *
	 * @param entity the entity
	 */
	void modify(Firmas entity);
	
	/**
	 * Delete.
	 *
	 * @param entity the entity
	 */
	void delete(Firmas entity);
	
	/**
	 * Exist firma.
	 *
	 * @param entity the entity
	 * @return the boolean
	 */
	Boolean existFirma(Firmas entity);
	
	/**
	 * Adds the element.
	 *
	 * @param idSector the id sector
	 * @return the firmas
	 */
	Firmas addElement(Integer idSector);

}
