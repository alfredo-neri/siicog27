package com.gem.sistema.business.sorts;

import org.springframework.data.domain.Sort;

// TODO: Auto-generated Javadoc
/**
 * The Class CuentaSort.
 */
public class CuentaSort {

	/**
	 * Sort by all.
	 *
	 * @return the sort
	 */
	public static Sort sortByAll(){
		return new Sort(Sort.Direction.ASC, "cuenta", "scuenta", "sscuenta", "ssscuenta", "sssscuenta");
	}
}
