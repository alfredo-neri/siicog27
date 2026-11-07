package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QTcUsuario;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class TcUsuarioPredicates.
 */
public class TcUsuarioPredicates {

	/**
	 * Instantiates a new tc usuario predicates.
	 */
	private TcUsuarioPredicates() {
	}

	/**
	 * Find by usuario.
	 *
	 * @param user the user
	 * @return the predicate
	 */
	public static Predicate findByUsuario(String user) {
		// return QMaycta.maycta.cuenta.eq(cuenta);
		return QTcUsuario.tcUsuario.usuario.eq(user);
	}

	/**
	 * Find by id sector.
	 *
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate findByIdSector(Long idSector) {
		// return QMaycta.maycta.cuenta.eq(cuenta);
		return QTcUsuario.tcUsuario.tipoUsuario.id.eq(idSector);
	}
}
