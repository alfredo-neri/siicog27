package com.gem.sistema.business.predicates;

import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.business.domain.QTsueldo;
import com.gem.sistema.business.domain.Tsueldo;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

// TODO: Auto-generated Javadoc
/**
 * The Class SueldosPredicate.
 */
public class SueldosPredicate {

	/**
	 * Find by all.
	 *
	 * @param capturo the capturo
	 * @return the predicate
	 */
	public static Predicate findByAll(String capturo) {
		return QTsueldo.tsueldo.capturo.eq(capturo);
	}

	/**
	 * Predicado de objeto compuesto con acercamiento de like.
	 * 
	 * @param sueldo2find
	 *            Objeto compuesto de busqueda.
	 * @return Predicado con la busqueda por acercamiento.
	 */
	public static Predicate findByLikeComposite(Tsueldo sueldo2find) {

		BooleanExpression p = null;
		if (!StringUtils.isEmpty(sueldo2find.getCvepuesto())) {
			p = QTsueldo.tsueldo.cvepuesto.likeIgnoreCase(sueldo2find.getCvepuesto());
		}

		if (!StringUtils.isEmpty(sueldo2find.getNompuesto())) {
			if (p == null) {
				p = QTsueldo.tsueldo.nompuesto.likeIgnoreCase(sueldo2find.getNompuesto());
			} else {
				p = p.and(QTsueldo.tsueldo.nompuesto.likeIgnoreCase(sueldo2find.getNompuesto()));
			}

		}

		return p;
	}

	/**
	 * Predicado de objeto compuesto con acercamiento de like.
	 *
	 * @param cvepuesto the cvepuesto
	 * @return Predicado con la busqueda por acercamiento.
	 */
	public static Predicate findByCvepuesto(String cvepuesto) {
		return QTsueldo.tsueldo.cvepuesto.equalsIgnoreCase(cvepuesto);
	}

}
