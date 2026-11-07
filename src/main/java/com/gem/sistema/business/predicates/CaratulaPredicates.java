package com.gem.sistema.business.predicates;

import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.business.domain.Caratula;
import com.gem.sistema.business.domain.QCaratula;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

// TODO: Auto-generated Javadoc
/**
 * The Class CaratulaPredicates.
 *
 * @author gauss.
 */
public class CaratulaPredicates {

	/**
	 * Constructor por default.
	 */
	private CaratulaPredicates() {
	}

	/**
	 * Exists five levels account.
	 *
	 * @param cuenta the cuenta
	 * @param scuenta the scuenta
	 * @param sscuenta the sscuenta
	 * @param ssscuenta the ssscuenta
	 * @param sssscuenta the sssscuenta
	 * @return the predicate
	 */
	public static Predicate existsFiveLevelsAccount(final String cuenta, final String scuenta, final String sscuenta,
			final String ssscuenta, final String sssscuenta) {
		return QCaratula.caratula.cuenta.eq(cuenta).and(QCaratula.caratula.scta.eq(scuenta))
				.and(QCaratula.caratula.sscta.eq(sscuenta)).and(QCaratula.caratula.ssscta.eq(ssscuenta))
				.and(QCaratula.caratula.sssscta.eq(sssscuenta));
	}

	/**
	 * Exists four levels account.
	 *
	 * @param cuenta the cuenta
	 * @param scuenta the scuenta
	 * @param sscuenta the sscuenta
	 * @param ssscuenta the ssscuenta
	 * @return the predicate
	 */
	public static Predicate existsFourLevelsAccount(final String cuenta, final String scuenta, final String sscuenta,
			final String ssscuenta) {
		return QCaratula.caratula.cuenta.eq(cuenta).and(QCaratula.caratula.scta.eq(scuenta))
				.and(QCaratula.caratula.sscta.eq(sscuenta)).and(QCaratula.caratula.ssscta.eq(ssscuenta));
	}

	/**
	 * Exists three levels account.
	 *
	 * @param cuenta the cuenta
	 * @param scuenta the scuenta
	 * @param sscuenta the sscuenta
	 * @return the predicate
	 */
	public static Predicate existsThreeLevelsAccount(final String cuenta, final String scuenta, final String sscuenta) {
		return QCaratula.caratula.cuenta.eq(cuenta).and(QCaratula.caratula.scta.eq(scuenta))
				.and(QCaratula.caratula.sscta.eq(sscuenta));
	}

	/**
	 * Exists two levels account.
	 *
	 * @param cuenta the cuenta
	 * @param scuenta the scuenta
	 * @return the predicate
	 */
	public static Predicate existsTwoLevelsAccount(final String cuenta, final String scuenta) {
		return QCaratula.caratula.cuenta.eq(cuenta).and(QCaratula.caratula.scta.eq(scuenta));
	}

	/**
	 * Exists one levels account.
	 *
	 * @param cuenta the cuenta
	 * @return the predicate
	 */
	public static Predicate existsOneLevelsAccount(final String cuenta) {
		return QCaratula.caratula.cuenta.eq(cuenta);
	}
	
	public static Predicate existsAccountAndScta(final String cuenta, final String scta) {
		Predicate p = null;
		if(StringUtils.isEmpty(scta)) {
			return QCaratula.caratula.cuenta.eq(cuenta).and(QCaratula.caratula.scta.eq(StringUtils.EMPTY));
		}else {
			return QCaratula.caratula.cuenta.eq(cuenta).and(QCaratula.caratula.scta.eq(scta));
		}
	}
	
	/**
	 * Find by accoun composite.
	 *
	 * @param caratula the caratula
	 * @return the predicate
	 */
	public static Predicate findByAccounComposite(final Caratula caratula) {
		BooleanExpression p = null;
		if (!StringUtils.isEmpty(caratula.getCuenta())) {
			p = QCaratula.caratula.cuenta.eq(caratula.getCuenta());
		}
		if (!StringUtils.isEmpty(caratula.getScta())) {
			if (p == null) {
				p = QCaratula.caratula.scta.eq(caratula.getScta());
			} else {
				p = p.and(QCaratula.caratula.scta.eq(caratula.getScta()));
			}
		}
		if (!StringUtils.isEmpty(caratula.getSscta())) {
			if (p == null) {
				p = QCaratula.caratula.sscta.eq(caratula.getSscta());
			} else {
				p = p.and(QCaratula.caratula.sscta.eq(caratula.getSscta()));
			}
		}
		if (!StringUtils.isEmpty(caratula.getSsscta())) {
			if (p == null) {
				p = QCaratula.caratula.ssscta.eq(caratula.getSsscta());
			} else {
				p = p.and(QCaratula.caratula.ssscta.eq(caratula.getSsscta()));
			}
		}
		if (!StringUtils.isEmpty(caratula.getSssscta())) {
			if (p == null) {
				p = QCaratula.caratula.sssscta.eq(caratula.getSssscta());
			} else {
				p = p.and(QCaratula.caratula.sssscta.eq(caratula.getSssscta()));
			}
		}
		return p;
	}

}
