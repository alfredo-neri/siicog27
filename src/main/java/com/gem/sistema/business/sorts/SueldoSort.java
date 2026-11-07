package com.gem.sistema.business.sorts;

import org.springframework.data.domain.Sort;

// TODO: Auto-generated Javadoc
/**
 * The Class SueldoSort.
 */
public class SueldoSort {

	/**
	 * Ordenamiento de la busqueda de sueldos.
	 *
	 * @return the sort
	 */
	public static Sort sortByAll() {
		return new Sort(Sort.Direction.ASC, "cvepuesto", "nompuesto");
	}
}
