package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.Firmas;

// TODO: Auto-generated Javadoc
/**
 * The Interface FirmasService.
 *
 * @author Mateo
 */
public interface FirmasService {
	
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
	 * Find by id sector.
	 *
	 * @param idSector the id sector
	 * @return the firmas
	 */
	Firmas findByIdSector(Integer idSector);
	
	/**
	 * Adds the elements.
	 *
	 * @param idSector the id sector
	 * @return the firmas
	 */
	Firmas addElements(Integer idSector);

}
