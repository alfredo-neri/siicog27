package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QTcMenu;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class TcMenuPredicates.
 */
public class TcMenuPredicates {

	/**
	 * Instantiates a new tc menu predicates.
	 */
	private TcMenuPredicates() {
	}

	/**
	 * Find by id sector.
	 *
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate findByIdSector(Integer idSector) {
		return QTcMenu.tcMenu.menuItems.any().idSector.eq(idSector);
	}
}
