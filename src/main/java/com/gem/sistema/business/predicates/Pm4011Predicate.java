package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QPm4011;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm4011Predicate.
 *
 * @author Mateo
 */
public class Pm4011Predicate {
	
	/**
	 * Find by clave and id sector.
	 *
	 * @param clave the clave
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate findByClaveAndIdSector(Integer clave, Integer idSector) {
		return QPm4011.pm4011.clvreq.eq(clave).and(QPm4011.pm4011.idsector.eq(idSector));
	}

}
