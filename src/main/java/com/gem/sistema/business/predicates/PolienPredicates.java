package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.domain.QPolien;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class PolienPredicates.
 */
public class PolienPredicates {

	/**
	 * Instantiates a new polien predicates.
	 */
	private PolienPredicates() {
	}

	/**
	 * Elegible to programs.
	 *
	 * @param tipopol
	 *            the tipopol
	 * @param mespol
	 *            the mespol
	 * @param minConpol
	 *            the min conpol
	 * @param maxConpol
	 *            the max conpol
	 * @return the predicate
	 */
	public static Predicate elegibleToPrograms(String tipopol, Integer mespol, Integer minConpol, Integer maxConpol) {
		return QPolien.polien.tippol.eq(tipopol).and(QPolien.polien.mespol.eq(mespol))
				.and(QPolien.polien.conpol.goe(minConpol)).and(QPolien.polien.conpol.loe(maxConpol));
	}

	/**
	 * Elegible to programs.
	 *
	 * @param tipopol
	 *            the tipopol
	 * @param mespol
	 *            the mespol
	 * @param minConpol
	 *            the min conpol
	 * @param maxConpol
	 *            the max conpol
	 * @param idSector
	 *            the id sector
	 * @return the predicate
	 */
	public static Predicate elegibleToPrograms(String tipopol, Integer mespol, Integer minConpol, Integer maxConpol,
			Integer idSector) {
		return QPolien.polien.tippol.eq(tipopol).and(QPolien.polien.mespol.eq(mespol))
				.and(QPolien.polien.conpol.goe(minConpol)).and(QPolien.polien.conpol.loe(maxConpol))
				.and(QPolien.polien.idsector.eq(idSector));
	}

	/**
	 * Find by tipo mes.
	 *
	 * @param tipopol
	 *            the tipopol
	 * @param mespol
	 *            the mespol
	 * @param idSector
	 *            the id sector
	 * @return the predicate
	 */
	public static Predicate findByTipoMes(String tipopol, Integer mespol, Integer idSector) {
		return QPolien.polien.tippol.eq(tipopol).and(QPolien.polien.mespol.eq(mespol))
				.and(QPolien.polien.idsector.eq(idSector));

	}

	/**
	 * Next.
	 *
	 * @param tipo
	 *            the tipo
	 * @param mes
	 *            the mes
	 * @param numero
	 *            the numero
	 * @param idSector
	 *            the id sector
	 * @return the predicate
	 */
	public static Predicate next(String tipo, Integer mes, Integer numero, Integer idSector) {
		return QPolien.polien.tippol.equalsIgnoreCase(tipo).and(QPolien.polien.mespol.eq(mes))
				.and(QPolien.polien.conpol.eq(numero)).and(QPolien.polien.idsector.eq(idSector));
	}

	/**
	 * By tipo mes.
	 *
	 * @param tipo
	 *            the tipo
	 * @param mes
	 *            the mes
	 * @param idSector
	 *            the id sector
	 * @return the predicate
	 */
	public static Predicate byTipoMes(String tipo, Integer mes, Integer idSector) {
		return QPolien.polien.tippol.eq(tipo).and(QPolien.polien.mespol.eq(mes))
				.and(QPolien.polien.idsector.eq(idSector));
	}

	/**
	 * By mes afectado.
	 *
	 * @param mes
	 *            the mes
	 * @param idSector
	 *            the id sector
	 * @param afectedState
	 *            the afected state
	 * @return the predicate
	 */
	public static Predicate byMesAfectado(Integer mes, Integer idSector, String afectedState) {
		return QPolien.polien.mespol.eq(mes).and(QPolien.polien.idsector.eq(idSector))
				.and(QPolien.polien.staafe.equalsIgnoreCase(afectedState));
	}

	/**
	 * Checks if is afect.
	 *
	 * @param month
	 *            the month
	 * @param staafe
	 *            the staafe
	 * @param idSector
	 *            the id sector
	 * @return the predicate
	 */
	public static Predicate isAfect(Integer month, String staafe, Integer idSector) {
		return QPolien.polien.mespol.eq(month).and(QPolien.polien.staafe.eq(staafe))
				.and(QPolien.polien.idsector.eq(idSector));
	}

	/**
	 * Month afect.
	 *
	 * @param mes
	 *            the mes
	 * @param idSector
	 *            the id sector
	 * @param staafe
	 *            the staafe
	 * @return the predicate
	 */
	public static Predicate monthAfect(Integer mes, Integer idSector, String staafe) {
		return QPolien.polien.mespol.eq(mes).and(QPolien.polien.idsector.eq(idSector));
	}

	/**
	 * Find by filters.
	 *
	 * @param polien
	 *            the polien
	 * @return the predicate
	 */
	public static Predicate findByFilters(Polien polien) {
		return QPolien.polien.anopol.eq(polien.getAnopol()).and(QPolien.polien.mespol.eq(polien.getMespol()))
				.and(QPolien.polien.tippol.eq(polien.getTippol())).and(QPolien.polien.conpol.eq(polien.getConpol()))
				.and(QPolien.polien.idsector.eq(polien.getIdsector()));
	}

	/**
	 * Delete byid user.
	 *
	 * @param user
	 *            the user
	 * @return the predicate
	 */
	public static Predicate deleteByidUser(String user) {
		return QPolien.polien.userid.eq(user);
	}

	/**
	 * @param idSector
	 * @param month
	 * @return
	 */
	public static Predicate findByidSectorAndMonth(Integer idSector, Integer month) {
		return QPolien.polien.idsector.eq(idSector).and(QPolien.polien.mespol.eq(month));
	}

}
