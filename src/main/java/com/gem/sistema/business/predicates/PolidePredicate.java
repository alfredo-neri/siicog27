package com.gem.sistema.business.predicates;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.QPolide;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.expr.NumberExpression;

// TODO: Auto-generated Javadoc
/**
 * The Class PolidePredicate.
 */
public class PolidePredicate {

	/**
	 * Instantiates a new polide predicate.
	 */
	public PolidePredicate() {

	}

	/**
	 * Count poliza.
	 *
	 * @param mes
	 *            the mes
	 * @param tipo
	 *            the tipo
	 * @return the number expression
	 */
	public static NumberExpression<Long> countPoliza(Integer mes, String tipo) {
		return QPolide.polide.mespol.eq(mes).and(QPolide.polide.tippol.eq(tipo)).count();
	}

	/**
	 * Find by accoun composite.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the predicate
	 */
	public static Predicate findByAccounComposite(final Cuenta cuenta) {
		BooleanExpression p = null;
		if (!StringUtils.isEmpty(cuenta.getCuenta())) {
			p = QPolide.polide.cuenta.eq(cuenta.getCuenta());
		}
		if (!StringUtils.isEmpty(cuenta.getScuenta())) {
			if (p == null) {
				p = QPolide.polide.scta.eq(cuenta.getScuenta());
			} else {
				p = p.and(QPolide.polide.scta.eq(cuenta.getScuenta()));
			}
		}
		if (!StringUtils.isEmpty(cuenta.getSscuenta())) {
			if (p == null) {
				p = QPolide.polide.sscta.eq(cuenta.getSscuenta());
			} else {
				p = p.and(QPolide.polide.sscta.eq(cuenta.getSscuenta()));
			}
		}
		if (!StringUtils.isEmpty(cuenta.getSsscuenta())) {
			if (p == null) {
				p = QPolide.polide.ssscta.eq(cuenta.getSsscuenta());
			} else {
				p = p.and(QPolide.polide.ssscta.eq(cuenta.getSsscuenta()));
			}
		}
		if (!StringUtils.isEmpty(cuenta.getSssscuenta())) {
			if (p == null) {
				p = QPolide.polide.sssscta.eq(cuenta.getSssscuenta());
			} else {
				p = p.and(QPolide.polide.sssscta.eq(cuenta.getSssscuenta()));
			}
		}
		if (p == null) {
			p = QPolide.polide.idsector.eq(cuenta.getIdsector().intValue());
		} else {
			p = p.and(QPolide.polide.idsector.eq(cuenta.getIdsector().intValue()));
		}
		return p;
	}

	/**
	 * Find one composite.
	 *
	 * @param tippol
	 *            the tippol
	 * @param mespol
	 *            the mespol
	 * @param conpol
	 *            the conpol
	 * @param renpol
	 *            the renpol
	 * @param idSector
	 *            the id sector
	 * @return the predicate
	 */
	public static Predicate findOneComposite(String tippol, int mespol, int conpol, BigDecimal renpol, int idSector) {
		return QPolide.polide.tippol.equalsIgnoreCase(tippol).and(QPolide.polide.mespol.eq(mespol))
				.and(QPolide.polide.conpol.eq(conpol)).and(QPolide.polide.renpol.eq(renpol))
				.and(QPolide.polide.idsector.eq(idSector));
	}

	/**
	 * Count rows.
	 *
	 * @param tippol
	 *            the tippol
	 * @param mespol
	 *            the mespol
	 * @param conpol
	 *            the conpol
	 * @param anio
	 *            the anio
	 * @param idSector
	 *            the id sector
	 * @return the predicate
	 */
	public static Predicate countRows(String tippol, int mespol, int conpol, Integer anio, int idSector) {
		return QPolide.polide.tippol.equalsIgnoreCase(tippol).and(QPolide.polide.mespol.eq(mespol))
				.and(QPolide.polide.conpol.eq(conpol)).and(QPolide.polide.anopol.eq(anio))
				.and(QPolide.polide.idsector.eq(idSector));
	}

	public static Predicate existPolide(Integer aniopol, String tippol, Integer mespol, Integer conpol, BigDecimal renpol,
			Integer idSector) {
		return QPolide.polide.tippol.equalsIgnoreCase(tippol).and(QPolide.polide.mespol.eq(mespol))
				.and(QPolide.polide.conpol.eq(conpol)).and(QPolide.polide.renpol.eq(renpol))
				.and(QPolide.polide.idsector.eq(idSector)).and(QPolide.polide.anopol.eq(aniopol));
	}

}
