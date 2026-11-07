package com.gem.sistema.business.bs;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Pm2511;
import com.gem.sistema.business.domain.Pm5211;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm5211BS.
 *
 * @author Mateo
 */
public interface Pm5211BS {

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
	 * Validate txt.
	 *
	 * @param pm5211 the pm 5211
	 * @return true, if successful
	 */
	boolean validateTxt(Pm5211 pm5211);

	/**
	 * Find by mensual.
	 *
	 * @param mensual the mensual
	 * @param idSector the id sector
	 * @return true, if successful
	 */
	boolean findByMensual(Integer mensual, Integer idSector);
	
	/**
	 * Order by mensual.
	 *
	 * @return the sort
	 */
	Sort orderByMensual();
	
	/**
	 * Aculation.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm5211> aculation(Integer idSector);

}
