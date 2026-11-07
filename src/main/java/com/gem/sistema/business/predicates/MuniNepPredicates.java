package com.gem.sistema.business.predicates;

import static com.gem.sistema.util.Constants.MIN_LENGTH_FIELD_CAMPO8_INDICATORS;
import static com.gem.sistema.util.Constants.MIN_LENGTH_FIELD_CAMPO8_MATRIX_INDICATORS;

import com.gem.sistema.business.domain.QMuninep;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class MuniNepPredicates.
 */
public class MuniNepPredicates {

	/**
	 * Instantiates a new muni nep predicates.
	 */
	private MuniNepPredicates() {
	}

	/** The Constant VALUE_FIELD_CAMPO00_00. */
	private static final String VALUE_FIELD_CAMPO00_00 = "00";

	/**
	 * Checks if is muni nep not empty.
	 *
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate isMuniNepNotEmpty(Integer idSector) {
		return QMuninep.muninep.campo0.ne(VALUE_FIELD_CAMPO00_00).and(QMuninep.muninep.campo0.isNotEmpty())
				.and(QMuninep.muninep.idsector.eq(idSector));
	}

	/**
	 * Gets the predicate to indicators.
	 *
	 * @return the predicate to indicators
	 */
	public static Predicate getPredicateToIndicators() {
		return (QMuninep.muninep.campo0.isNotEmpty()).and(QMuninep.muninep.campo0.isNotNull())
				.and(QMuninep.muninep.campo0.ne(VALUE_FIELD_CAMPO00_00)).and(QMuninep.muninep.campo1.isNotEmpty())
				.and(QMuninep.muninep.campo1.isNotNull()).and(QMuninep.muninep.campo1.ne(VALUE_FIELD_CAMPO00_00))
				.and(QMuninep.muninep.campo2.isNotEmpty()).and(QMuninep.muninep.campo2.isNotNull())
				.and(QMuninep.muninep.campo2.ne(VALUE_FIELD_CAMPO00_00)).and(QMuninep.muninep.campo3.isNotEmpty())
				.and(QMuninep.muninep.campo3.isNotNull()).and(QMuninep.muninep.campo3.ne(VALUE_FIELD_CAMPO00_00))
				.and(QMuninep.muninep.campo4.isNotEmpty()).and(QMuninep.muninep.campo4.isNotNull())
				.and(QMuninep.muninep.campo4.ne(VALUE_FIELD_CAMPO00_00)).and(QMuninep.muninep.campo8.isNotEmpty())
				.and(QMuninep.muninep.campo8.isNotNull())
				.and(QMuninep.muninep.campo8.length().eq(MIN_LENGTH_FIELD_CAMPO8_INDICATORS));
	}

	/**
	 * Gets the predicate to indicators 2.
	 *
	 * @return the predicate to indicators 2
	 */
	public static Predicate getPredicateToIndicators2() {
		return (QMuninep.muninep.campo0.isNotEmpty()).and(QMuninep.muninep.campo0.isNotNull())
				.and(QMuninep.muninep.campo0.ne(VALUE_FIELD_CAMPO00_00)).and(QMuninep.muninep.campo1.isNotEmpty())
				.and(QMuninep.muninep.campo1.isNotNull()).and(QMuninep.muninep.campo1.ne(VALUE_FIELD_CAMPO00_00))
				.and(QMuninep.muninep.campo2.isNotEmpty()).and(QMuninep.muninep.campo2.isNotNull())
				.and(QMuninep.muninep.campo2.ne(VALUE_FIELD_CAMPO00_00)).and(QMuninep.muninep.campo3.isNotEmpty())
				.and(QMuninep.muninep.campo3.isNotNull()).and(QMuninep.muninep.campo3.ne(VALUE_FIELD_CAMPO00_00))
				.and(QMuninep.muninep.campo4.isNotEmpty()).and(QMuninep.muninep.campo4.isNotNull())
				.and(QMuninep.muninep.campo4.ne(VALUE_FIELD_CAMPO00_00)).and(QMuninep.muninep.campo7.isNotEmpty())
				.and(QMuninep.muninep.campo7.isNotNull())
				.and(QMuninep.muninep.campo7.length().eq(MIN_LENGTH_FIELD_CAMPO8_INDICATORS));
	}

	/**
	 * Gets the predicate to matrix indicators.
	 *
	 * @return the predicate to matrix indicators
	 */
	public static Predicate getPredicateToMatrixIndicators() {
		return (QMuninep.muninep.campo0.isNotEmpty()).and(QMuninep.muninep.campo0.isNotNull())
				.and(QMuninep.muninep.campo0.ne(VALUE_FIELD_CAMPO00_00)).and(QMuninep.muninep.campo1.isNotEmpty())
				.and(QMuninep.muninep.campo1.isNotNull()).and(QMuninep.muninep.campo1.ne(VALUE_FIELD_CAMPO00_00))
				.and(QMuninep.muninep.campo2.isNotEmpty()).and(QMuninep.muninep.campo2.isNotNull())
				.and(QMuninep.muninep.campo2.ne(VALUE_FIELD_CAMPO00_00)).and(QMuninep.muninep.campo3.isNotEmpty())
				.and(QMuninep.muninep.campo3.isNotNull()).and(QMuninep.muninep.campo3.ne(VALUE_FIELD_CAMPO00_00))
				.and(QMuninep.muninep.campo8.isNotEmpty()).and(QMuninep.muninep.campo8.isNotNull())
				.and(QMuninep.muninep.campo8.length().eq(MIN_LENGTH_FIELD_CAMPO8_MATRIX_INDICATORS));
	}

	/**
	 * Gets the predicate to matrix indicators 2.
	 *
	 * @return the predicate to matrix indicators 2
	 */
	public static Predicate getPredicateToMatrixIndicators2() {
		return (QMuninep.muninep.campo0.isNotEmpty()).and(QMuninep.muninep.campo0.isNotNull())
				.and(QMuninep.muninep.campo0.ne(VALUE_FIELD_CAMPO00_00)).and(QMuninep.muninep.campo1.isNotEmpty())
				.and(QMuninep.muninep.campo1.isNotNull()).and(QMuninep.muninep.campo1.ne(VALUE_FIELD_CAMPO00_00))
				.and(QMuninep.muninep.campo2.isNotEmpty()).and(QMuninep.muninep.campo2.isNotNull())
				.and(QMuninep.muninep.campo2.ne(VALUE_FIELD_CAMPO00_00)).and(QMuninep.muninep.campo3.isNotEmpty())
				.and(QMuninep.muninep.campo3.isNotNull()).and(QMuninep.muninep.campo3.ne(VALUE_FIELD_CAMPO00_00))
				.and(QMuninep.muninep.campo7.isNotEmpty()).and(QMuninep.muninep.campo7.isNotNull())
				.and(QMuninep.muninep.campo7.length().eq(MIN_LENGTH_FIELD_CAMPO8_MATRIX_INDICATORS));
	}

	/**
	 * Gets the predicate to programs.
	 *
	 * @return the predicate to programs
	 */
	public static Predicate getPredicateToPrograms() {
		return (QMuninep.muninep.campo0.isNotEmpty()).and(QMuninep.muninep.campo0.isNotNull())
				.and(QMuninep.muninep.campo0.ne(VALUE_FIELD_CAMPO00_00)).and(QMuninep.muninep.campo1.isNotEmpty())
				.and(QMuninep.muninep.campo1.isNotNull()).and(QMuninep.muninep.campo1.ne(VALUE_FIELD_CAMPO00_00))
				.and(QMuninep.muninep.campo2.isNotEmpty()).and(QMuninep.muninep.campo2.isNotNull())
				.and(QMuninep.muninep.campo2.ne(VALUE_FIELD_CAMPO00_00)).and(QMuninep.muninep.campo3.isNotEmpty())
				.and(QMuninep.muninep.campo3.isNotNull()).and(QMuninep.muninep.campo3.ne(VALUE_FIELD_CAMPO00_00))
				.and(QMuninep.muninep.campo4.isNotEmpty()).and(QMuninep.muninep.campo4.isNotNull())
				.and(QMuninep.muninep.campo4.ne(VALUE_FIELD_CAMPO00_00));
	}

	/**
	 * Gets the predicate to programs.
	 *
	 * @param cveFin the cve fin
	 * @param idSector the id sector
	 * @return the predicate to programs
	 */
	public static Predicate getPredicateToPrograms(String cveFin, Integer idSector) {
		return QMuninep.muninep.campo7.eq(cveFin).and(QMuninep.muninep.idsector.eq(idSector));
	}

	/**
	 * Find byclvfun.
	 *
	 * @param clvFun the clv fun
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate findByclvfun(String clvFun, Integer idSector) {
		return QMuninep.muninep.campo7.eq(clvFun).and(QMuninep.muninep.idsector.eq(idSector));
	}

}
