package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.Pm5211;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm5211Service.
 */
public interface Pm5211Service {
	
	/**
	 * Save.
	 *
	 * @param listPm5211 the list pm 5211
	 * @param index the index
	 * @return the list
	 */
	List<Pm5211> save(List<Pm5211> listPm5211, Integer index);

	/**
	 * Delete.
	 *
	 * @param listPm5211 the list pm 5211
	 * @param index the index
	 * @return the list
	 */
	List<Pm5211> delete(List<Pm5211> listPm5211, Integer index);

	/**
	 * Adds the.
	 *
	 * @param listPm5211 the list pm 5211
	 * @param index the index
	 * @return the list
	 */
	List<Pm5211> add(List<Pm5211> listPm5211, Integer index);

	/**
	 * Clean.
	 *
	 * @param listPm5211 the list pm 5211
	 * @param index the index
	 * @return the list
	 */
	List<Pm5211> clean(List<Pm5211> listPm5211, Integer index);

	/**
	 * Cancel.
	 *
	 * @param listPm5211 the list pm 5211
	 * @param index the index
	 * @return the list
	 */
	List<Pm5211> cancel(List<Pm5211> listPm5211, Integer index);
	
	/**
	 * Order by mendsual asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm5211> orderByMendsualAsc(Integer idSector);
	
	/**
	 * Aculation.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm5211> aculation(Integer idSector);
}
