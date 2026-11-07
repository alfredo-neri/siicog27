package com.gem.sistema.business.predicates;

import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.business.domain.Mir;
import com.gem.sistema.business.domain.QMir;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

// TODO: Auto-generated Javadoc
/**
 * The Class MirPredicates.
 *
 * @author Gerardo CUERO
 */
public class MirPredicates {

	/**
	 * By composite.
	 *
	 * @param mir the mir
	 * @return the predicate
	 */
	public static Predicate byComposite(Mir mir) {
		BooleanExpression p = null;
		if (StringUtils.isNotEmpty(mir.getNomind())) {
			p = QMir.mir.nomind.likeIgnoreCase(mir.getNomind());
		}
		if (StringUtils.isNotEmpty(mir.getNivel())) {
			if (p == null) {
				p = QMir.mir.nivel.likeIgnoreCase(mir.getNivel());
			} else {
				p = QMir.mir.nivel.likeIgnoreCase(mir.getNivel());
			}
		}
		
		if(StringUtils.isNotEmpty(mir.getPrograma())) {
			p = QMir.mir.programa.like(mir.getPrograma());
		}
		
		return p;
	}

	/**
	 * Find bycve ind.
	 *
	 * @param cveInd the cve ind
	 * @return the predicate
	 */
	public static Predicate findBycveInd(String cveInd) {
		return QMir.mir.codigo.eq(cveInd);
	}

	/**
	 * Find by nivel and cve prog.
	 *
	 * @param nivel the nivel
	 * @param cveProg the cve prog
	 * @return the predicate
	 */
	public static Predicate findByNivelAndCveProg(String nivel, String cveProg) {
		return QMir.mir.nivel.eq(nivel).and(QMir.mir.programa.eq(cveProg));
	}

}
