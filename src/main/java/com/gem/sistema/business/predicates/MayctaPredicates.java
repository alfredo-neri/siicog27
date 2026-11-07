package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QMaycta;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class MayctaPredicates.
 */
public class MayctaPredicates {

	/**
	 * Instantiates a new maycta predicates.
	 */
	private MayctaPredicates(){}
	
	/**  Cuenta Acreedora. */
	private static final String CREDITOR_ACCOUNT = "A";
	
	/**  Cuenta deudora. */
	private static final String ACCOUNT_RECEIVABLE = "D";
	
	/**
	 * Gets the natcta is not empty.
	 *
	 * @return the natcta is not empty
	 */
	public static Predicate getNatctaIsNotEmpty() {
		return QMaycta.maycta.natcta.isNotEmpty();
    }
	
	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public static OrderSpecifier<String> getOrder() {
		return QMaycta.maycta.cuenta.asc();
	}
	
	/**
	 * Only nature expensive.
	 *
	 * @return the predicate
	 */
	public static Predicate onlyNatureExpensive() {		
		return QMaycta.maycta.natcta.eq(CREDITOR_ACCOUNT).and(QMaycta.maycta.natcta.eq(ACCOUNT_RECEIVABLE)) ;
    }
	
	/**
	 * Exist account.
	 *
	 * @param cuenta the cuenta
	 * @return the predicate
	 */
	public static Predicate existAccount(String cuenta) {		
		return QMaycta.maycta.cuenta.eq(cuenta);
    }
}

