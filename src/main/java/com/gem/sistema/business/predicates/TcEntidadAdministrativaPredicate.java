package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QTcEntidadAdministrativa;
import com.querydsl.core.types.Predicate;

/**
 * @author Mateo
 *
 */
public class TcEntidadAdministrativaPredicate {
	
	/**
	 * @param clave
	 * @return
	 */
	public static Predicate findByClaveEntidad(String clave) {
		return QTcEntidadAdministrativa.tcEntidadAdministrativa.clave.eq(clave);
	}

}
