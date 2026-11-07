package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QIntegradoe;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class IntegradoePredicates.
 */
public class IntegradoePredicates {

	/**
	 * Instantiates a new integradoe predicates.
	 */
	private IntegradoePredicates(){}
	

	/**
	 * Find by partida.
	 *
	 * @param partida the partida
	 * @return the predicate
	 */
	public static Predicate findByPartida(String partida) {		

		return QIntegradoe.integradoe.partida.eq(partida);
    }
	
	
}

