package com.gem.sistema.business.predicates;

import com.gem.sistema.business.bs.PM0511BS;
import com.gem.sistema.business.domain.Pm0511;
import com.gem.sistema.business.domain.QPm0511;
import com.gem.sistema.business.domain.QPm5011;
import com.gem.sistema.ennum.constans.LogicalConstansEnum;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm5011Predicate.
 */
public class Pm5011Predicate {

	/**
	 * Find allby id sector and order by.
	 *
	 * @param idsector the idsector
	 * @return the order specifier
	 */
	public static OrderSpecifier<Boolean> findAllbyIdSectorAndOrderBy(Integer idsector) {
		OrderSpecifier<Boolean> p = null;
		p = QPm5011.pm5011.idsector.eq(idsector).asc();
		return p;
	}

	/**
	 * Validate trimonth and cosec.
	 *
	 * @param trimestre the trimestre
	 * @param cosec the cosec
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate validateTrimonthAndCosec(Integer trimestre, String cosec, Integer idSector) {
		return QPm0511.pm0511.trimestre.eq(trimestre)
				.and(QPm0511.pm0511.conse.eq(cosec).and(QPm0511.pm0511.idsector.eq(idSector)));
	}

	/**
	 * Generate query filters.
	 *
	 * @param parmeters the parmeters
	 * @param filter1 the filter 1
	 * @param filter2 the filter 2
	 * @return the predicate
	 */
	public static Predicate generateQueryFilters(Pm0511 parmeters, String filter1, String filter2) {

		Predicate p = null;
		if (null != parmeters.getTrimestre()) {

		}
		return null;
	}
    
	/**
	 * Find by id sector.
	 *
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate findByIdSector(Integer idSector){
		return QPm0511.pm0511.idsector.eq(idSector);
	}
}
