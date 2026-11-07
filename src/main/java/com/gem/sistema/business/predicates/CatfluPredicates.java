package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QCatflu;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class CatfluPredicates.
 */
public class CatfluPredicates {
	
	/**
	 * Instantiates a new catflu predicates.
	 */
	private CatfluPredicates(){}
	
	/**
	 * Checks if is localidad unique.
	 *
	 * @param clvfluVo the clvflu vo
	 * @return the predicate
	 */
	public static Predicate isLocalidadUnique(Double clvfluVo) {
		return QCatflu.catflu.clvflu.eq(clvfluVo) ;
    }
	
	/**
	 * Checks if is localidad unique.
	 *
	 * @param clvfluVo the clvflu vo
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate isLocalidadUnique(Double clvfluVo, Integer idSector) {
		return QCatflu.catflu.clvflu.eq(clvfluVo).and(QCatflu.catflu.idsector.eq(idSector)) ;
    }
	
	/**
	 * By id sector.
	 *
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate byIdSector(Integer idSector) {
		return QCatflu.catflu.idsector.eq(idSector) ;
    }

}
