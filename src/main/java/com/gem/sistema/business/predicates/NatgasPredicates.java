package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QNatgas;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class NatgasPredicates.
 */
public class NatgasPredicates {
	
	/**
	 * Instantiates a new natgas predicates.
	 */
	private NatgasPredicates(){}
	
	/**
	 * Exist natgas.
	 *
	 * @param natgasVo the natgas vo
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate existNatgas(String natgasVo, Integer idSector) {
		return QNatgas.natgas.clvgas.eq(natgasVo).and(QNatgas.natgas.idsector.eq(idSector));
    }

	/**
	 * Exist natgas.
	 *
	 * @param natgasVo the natgas vo
	 * @return the predicate
	 */
	public static Predicate existNatgas(String natgasVo) {
		return QNatgas.natgas.clvgas.eq(natgasVo);
    }
	
	/**
	 * Find by clvgas.
	 *
	 * @param clvgas the clvgas
	 * @return the predicate
	 */
	public static Predicate findByClvgas(String clvgas) {
		return QNatgas.natgas.clvgas.eq(clvgas);
    }
	
	/**
	 * Supongo que es el Filtro es por ID Sector.
	 *
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate findByIdSector(Integer idSector) {
		return QNatgas.natgas.idsector.eq(idSector);
    }
}
