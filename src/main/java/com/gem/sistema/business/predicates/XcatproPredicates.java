package com.gem.sistema.business.predicates;

import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.business.domain.QCuenta;
import com.gem.sistema.business.domain.QXcatpro;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.util.UtilJPA;
import com.gem.sistema.util.UtilReport;
import com.mysema.query.jpa.impl.JPAUtil;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

// TODO: Auto-generated Javadoc
/**
 * The Class XcatproPredicates.
 */
public class XcatproPredicates {

	/**
	 * Instantiates a new xcatpro predicates.
	 */
	private XcatproPredicates() {
	}

	/**
	 * Exist clvdep related to program and fin.
	 *
	 * @param clvdep the clvdep
	 * @param clvfun the clvfun
	 * @param clvFin the clv fin
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate existClvdepRelatedToProgramAndFin(final String clvdep, final String clvfun,
			final String clvFin, final Integer idSector) {
		return QXcatpro.xcatpro.clvdep.eq(clvdep).and(QXcatpro.xcatpro.clvfun.eq(clvfun))
				.and(QXcatpro.xcatpro.clvfin.eq(clvFin)).and(QXcatpro.xcatpro.sectorid.eq(idSector));
	}

	/**
	 * Exist clv pro.
	 *
	 * @param clvpro the clvpro
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate existClvPro(final String clvpro, Integer idSector) {
		return QXcatpro.xcatpro.clvpro.eq(clvpro).and(QXcatpro.xcatpro.sectorid.eq(idSector));
	}

	/**
	 * Exist clv pro.
	 *
	 * @param clvfun the clvfun
	 * @param clvfin the clvfin
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate existClvPro(final String clvfun, final String clvfin, Integer idSector) {
		return QXcatpro.xcatpro.clvfun.eq(clvfun).and(QXcatpro.xcatpro.clvfin.eq(clvfin))
				.and(QXcatpro.xcatpro.sectorid.eq(idSector));
	}

	/**
	 * Exist clv dep.
	 *
	 * @param clvdep the clvdep
	 * @return the predicate
	 */
	public static Predicate existClvDep(final String clvdep) {
		return QXcatpro.xcatpro.clvdep.eq(clvdep);
	}

	/**
	 * Exist clv dep.
	 *
	 * @param xcatpro the xcatpro
	 * @return the predicate
	 */
	public static Predicate existClvDep(final Xcatpro xcatpro) {
		BooleanExpression p = null;
		if (!StringUtils.isEmpty(xcatpro.getClvdep())) {
			p = QXcatpro.xcatpro.clvdep.likeIgnoreCase("%" + xcatpro.getClvdep() + "%");
		}
		if (!StringUtils.isEmpty(xcatpro.getNompro())) {
			if (p == null) {
				p = QXcatpro.xcatpro.nompro.likeIgnoreCase("%" + xcatpro.getNompro() + "%");
			} else {
				p = p.and(QXcatpro.xcatpro.nompro.likeIgnoreCase("%" + xcatpro.getNompro() + "%"));
			}
		}

		if (p == null) {
			p = QXcatpro.xcatpro.sectorid.eq(xcatpro.getSectorid());
		} else {
			p = p.and(QXcatpro.xcatpro.sectorid.eq(xcatpro.getSectorid()));
		}
		return p;
	}
	
}
