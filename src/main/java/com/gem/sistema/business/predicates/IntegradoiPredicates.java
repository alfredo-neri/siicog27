package com.gem.sistema.business.predicates;

import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.QIntegradoi;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

// TODO: Auto-generated Javadoc
/**
 * The Class IntegradoiPredicates.
 */
public class IntegradoiPredicates {

	/**
	 * Instantiates a new integradoi predicates.
	 */
	private IntegradoiPredicates() {
	}

	/**
	 * Find by accoun composite inlcude empty.
	 *
	 * @param cuenta the cuenta
	 * @return the predicate
	 */
	public static Predicate findByAccounCompositeInlcudeEmpty(final Cuenta cuenta) {
		BooleanExpression p = null;
		if (!StringUtils.isEmpty(cuenta.getCuenta())) {
			p = QIntegradoi.integradoi.cuenta.equalsIgnoreCase(cuenta.getCuenta());
		}
		if (!StringUtils.isEmpty(cuenta.getScuenta())) {
			p = p.and(QIntegradoi.integradoi.scta.equalsIgnoreCase(cuenta.getScuenta()));
		} else {
			p = p.and(QIntegradoi.integradoi.scta.equalsIgnoreCase(StringUtils.EMPTY));
		}
		if (!StringUtils.isEmpty(cuenta.getSscuenta())) {
			p = p.and(QIntegradoi.integradoi.sscta.equalsIgnoreCase(cuenta.getSscuenta()));

		} else {
			p = p.and(QIntegradoi.integradoi.sscta.equalsIgnoreCase(StringUtils.EMPTY));
		}
		if (!StringUtils.isEmpty(cuenta.getSsscuenta())) {
			p = p.and(QIntegradoi.integradoi.ssscta.equalsIgnoreCase(cuenta.getSsscuenta()));
		} else {
			p = p.and(QIntegradoi.integradoi.ssscta.equalsIgnoreCase(StringUtils.EMPTY));
		}
		if (!StringUtils.isEmpty(cuenta.getSssscuenta())) {
			p = p.and(QIntegradoi.integradoi.sssscta.equalsIgnoreCase(cuenta.getSssscuenta()));
		} else {
			p = p.and(QIntegradoi.integradoi.sssscta.equalsIgnoreCase(StringUtils.EMPTY));
		}

		p = p.and(QIntegradoi.integradoi.idsector.eq(cuenta.getIdsector().intValue()));

		return p;
	}

}
