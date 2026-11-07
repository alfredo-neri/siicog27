package com.gem.sistema.business.predicates;

import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.QCuenta;
import com.gem.sistema.business.domain.QPoliza;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

// TODO: Auto-generated Javadoc
/**
 * The Class PolizaPredicates.
 *
 * @author Juan Carlos Pedraza Alcala
 */
public class PolizaPredicates {

	/**
	 * Constructor por default.
	 */
	private PolizaPredicates() {
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
		return QPoliza.poliza.cuenta.eq(cuenta).and(QPoliza.poliza.scuenta.eq(scuenta))
				.and(QPoliza.poliza.sscuenta.eq(sscuenta)).and(QPoliza.poliza.ssscuenta.eq(ssscuenta))
				.and(QPoliza.poliza.sssscuenta.eq(sssscuenta));
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
		return QPoliza.poliza.cuenta.eq(cuenta).and(QPoliza.poliza.scuenta.eq(scuenta))
				.and(QPoliza.poliza.sscuenta.eq(sscuenta)).and(QPoliza.poliza.ssscuenta.eq(ssscuenta));
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
		return QPoliza.poliza.cuenta.eq(cuenta).and(QPoliza.poliza.scuenta.eq(scuenta))
				.and(QPoliza.poliza.sscuenta.eq(sscuenta));
	}

	/**
	 * Exists two levels account.
	 *
	 * @param cuenta the cuenta
	 * @param scuenta the scuenta
	 * @return the predicate
	 */
	public static Predicate existsTwoLevelsAccount(final String cuenta, final String scuenta) {
		return QPoliza.poliza.cuenta.eq(cuenta).and(QPoliza.poliza.scuenta.eq(scuenta));
	}

	/**
	 * Exists one levels account.
	 *
	 * @param cuenta the cuenta
	 * @return the predicate
	 */
	public static Predicate existsOneLevelsAccount(final String cuenta) {
		return QPoliza.poliza.cuenta.eq(cuenta);
	}

	/**
	 * Find by accoun composite.
	 *
	 * @param cuenta the cuenta
	 * @return the predicate
	 */
	public static Predicate findByAccounComposite(final Cuenta cuenta) {
		BooleanExpression p = null;
		if (!StringUtils.isEmpty(cuenta.getCuenta())) {
			p = QPoliza.poliza.cuenta.eq(cuenta.getCuenta());
		}
		if (!StringUtils.isEmpty(cuenta.getScuenta())) {
			if (p == null) {
				p = QPoliza.poliza.scuenta.eq(cuenta.getScuenta());
			} else {
				p = p.and(QPoliza.poliza.scuenta.eq(cuenta.getScuenta()));
			}
		}
		if (!StringUtils.isEmpty(cuenta.getSscuenta())) {
			if (p == null) {
				p = QPoliza.poliza.sscuenta.eq(cuenta.getSscuenta());
			} else {
				p = p.and(QPoliza.poliza.sscuenta.eq(cuenta.getSscuenta()));
			}
		}
		if (!StringUtils.isEmpty(cuenta.getSsscuenta())) {
			if (p == null) {
				p = QPoliza.poliza.ssscuenta.eq(cuenta.getSsscuenta());
			} else {
				p = p.and(QPoliza.poliza.ssscuenta.eq(cuenta.getSsscuenta()));
			}
		}
		if (!StringUtils.isEmpty(cuenta.getSssscuenta())) {
			if (p == null) {
				p = QPoliza.poliza.sssscuenta.eq(cuenta.getSssscuenta());
			} else {
				p = p.and(QPoliza.poliza.sssscuenta.eq(cuenta.getSssscuenta()));
			}
		}
		return p;
	}

}
