package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.Localidades;
import com.gem.sistema.business.domain.QLocalidades;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

// TODO: Auto-generated Javadoc
/**
 * The Class LocalidadesPredicates.
 */
public class LocalidadesPredicates {
	
	/**
	 * Instantiates a new localidades predicates.
	 */
	private LocalidadesPredicates(){}
	
	/**
	 * Checks if is cve mun and clv loc unique.
	 *
	 * @param cveMunView the cve mun view
	 * @param cveLocView the cve loc view
	 * @return the predicate
	 */
	public static Predicate isCveMunAndClvLocUnique(Integer cveMunView, Integer cveLocView) {
		return QLocalidades.localidades.cveMun.eq(cveMunView).and(QLocalidades.localidades.cveLoc.eq(cveLocView));
    }
	
	/**
	 * By composite.
	 *
	 * @param localidades the localidades
	 * @return the predicate
	 */
	public static Predicate byComposite(Localidades localidades) {
		BooleanExpression p = null;
		if (null != localidades.getCveMun()) {
			p = QLocalidades.localidades.cveMun.eq(localidades.getCveMun());
		}
		if (null != localidades.getCveLoc()) {
			if (p == null) {
				p = QLocalidades.localidades.cveLoc.eq(localidades.getCveLoc());
			} else {
				p = p.and(QLocalidades.localidades.cveLoc.eq(localidades.getCveLoc()));
			}
		}
		return p;
    }
}
