package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QFtecnicadd;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class FtecnicaDdPredicate.
 *
 * @author Mateo
 */
public class FtecnicaDdPredicate {

	/**
	 * Find by clv dep and clv fun and clv ind.
	 *
	 * @param clvDep the clv dep
	 * @param clvFun the clv fun
	 * @param clvInd the clv ind
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate findByClvDepAndClvFunAndClvInd(String clvDep, String clvFun, String clvInd,
			Integer idSector) {
		return QFtecnicadd.ftecnicadd.clvdep.eq(clvDep).and(QFtecnicadd.ftecnicadd.clvfun.eq(clvFun))
				.and(QFtecnicadd.ftecnicadd.cveind.eq(clvInd)).and(QFtecnicadd.ftecnicadd.idsector.eq(idSector));
	}

	/**
	 * Find by clv dep and clv fun and clv ind and codigo.
	 *
	 * @param clvDep the clv dep
	 * @param clvFun the clv fun
	 * @param clvInd the clv ind
	 * @param codigo the codigo
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate findByClvDepAndClvFunAndClvIndAndCodigo(String clvDep, String clvFun, String clvInd,
			Integer codigo, Integer idSector) {
		return QFtecnicadd.ftecnicadd.clvdep.eq(clvDep).and(QFtecnicadd.ftecnicadd.clvfun.eq(clvFun))
				.and(QFtecnicadd.ftecnicadd.cveind.eq(clvInd)).and(QFtecnicadd.ftecnicadd.codigo.eq(codigo))
				.and(QFtecnicadd.ftecnicadd.idsector.eq(idSector));
	}

}
