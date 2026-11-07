package com.gem.sistema.business.predicates;

import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.QCuenta;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

// TODO: Auto-generated Javadoc
/**
 * The Class CuentaPredicates.
 *
 * @author Juan Carlos Pedraza Alcala
 */
public class CuentaPredicates {

	/**
	 * Constructor por default.
	 */
	private CuentaPredicates() {
	}

	/**
	 * Exists five levels account.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param scuenta
	 *            the scuenta
	 * @param sscuenta
	 *            the sscuenta
	 * @param ssscuenta
	 *            the ssscuenta
	 * @param sssscuenta
	 *            the sssscuenta
	 * @param idsector
	 *            the idsector
	 * @return the predicate
	 */
	public static Predicate existsFiveLevelsAccount(final String cuenta, final String scuenta, final String sscuenta,
			final String ssscuenta, final String sssscuenta, Long idsector) {
		return QCuenta.cuenta1.cuenta.equalsIgnoreCase(cuenta).and(QCuenta.cuenta1.scuenta.equalsIgnoreCase(scuenta))
				.and(QCuenta.cuenta1.sscuenta.equalsIgnoreCase(sscuenta))
				.and(QCuenta.cuenta1.ssscuenta.equalsIgnoreCase(ssscuenta))
				.and(QCuenta.cuenta1.sssscuenta.equalsIgnoreCase(sssscuenta))
				.and(QCuenta.cuenta1.idsector.eq(idsector));
	}

	/**
	 * Exists five levels account.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the predicate
	 */
	public static Predicate existsFiveLevelsAccount(Cuenta cuenta) {
		return QCuenta.cuenta1.cuenta.equalsIgnoreCase(cuenta.getCuenta())
				.and(QCuenta.cuenta1.scuenta.equalsIgnoreCase(cuenta.getScuenta()))
				.and(QCuenta.cuenta1.sscuenta.equalsIgnoreCase(cuenta.getSscuenta()))
				.and(QCuenta.cuenta1.ssscuenta.equalsIgnoreCase(cuenta.getSsscuenta()))
				.and(QCuenta.cuenta1.sssscuenta.equalsIgnoreCase(cuenta.getSssscuenta()))
				.and(QCuenta.cuenta1.idsector.eq(cuenta.getIdsector()));
	}

	/**
	 * Exists four levels account.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param scuenta
	 *            the scuenta
	 * @param sscuenta
	 *            the sscuenta
	 * @param ssscuenta
	 *            the ssscuenta
	 * @param idsector
	 *            the idsector
	 * @return the predicate
	 */
	public static Predicate existsFourLevelsAccount(final String cuenta, final String scuenta, final String sscuenta,
			final String ssscuenta, Long idsector) {
		return QCuenta.cuenta1.cuenta.equalsIgnoreCase(cuenta).and(QCuenta.cuenta1.scuenta.equalsIgnoreCase(scuenta))
				.and(QCuenta.cuenta1.sscuenta.equalsIgnoreCase(sscuenta))
				.and(QCuenta.cuenta1.ssscuenta.equalsIgnoreCase(ssscuenta)).and(QCuenta.cuenta1.idsector.eq(idsector));
	}

	/**
	 * Exists three levels account.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param scuenta
	 *            the scuenta
	 * @param sscuenta
	 *            the sscuenta
	 * @param idsector
	 *            the idsector
	 * @return the predicate
	 */
	public static Predicate existsThreeLevelsAccount(final String cuenta, final String scuenta, final String sscuenta,
			Long idsector) {
		return QCuenta.cuenta1.cuenta.equalsIgnoreCase(cuenta).and(QCuenta.cuenta1.scuenta.equalsIgnoreCase(scuenta))
				.and(QCuenta.cuenta1.sscuenta.equalsIgnoreCase(sscuenta)).and(QCuenta.cuenta1.idsector.eq(idsector));
	}

	/**
	 * Exists two levels account.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param scuenta
	 *            the scuenta
	 * @param idsector
	 *            the idsector
	 * @return the predicate
	 */
	public static Predicate existsTwoLevelsAccount(final String cuenta, final String scuenta, Long idsector) {
		return QCuenta.cuenta1.cuenta.equalsIgnoreCase(cuenta).and(QCuenta.cuenta1.scuenta.equalsIgnoreCase(scuenta))
				.and(QCuenta.cuenta1.idsector.eq(idsector));
	}

	/**
	 * Exists one levels account.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param idsector
	 *            the idsector
	 * @return the predicate
	 */
	public static Predicate existsOneLevelsAccount(final String cuenta, final Long idsector) {
		return QCuenta.cuenta1.cuenta.equalsIgnoreCase(cuenta).and(QCuenta.cuenta1.idsector.eq(idsector));
	}

	/**
	 * Find by accoun composite.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the predicate
	 */
	public static Predicate findByAccounComposite(final Cuenta cuenta) {
		BooleanExpression p = null;
		if (!StringUtils.isEmpty(cuenta.getCuenta())) {
			p = QCuenta.cuenta1.cuenta.equalsIgnoreCase(cuenta.getCuenta());
		}
		if (!StringUtils.isEmpty(cuenta.getScuenta())) {
			if (p == null) {
				p = QCuenta.cuenta1.scuenta.equalsIgnoreCase(cuenta.getScuenta());
			} else {
				p = p.and(QCuenta.cuenta1.scuenta.equalsIgnoreCase(cuenta.getScuenta()));
			}
		}
		if (!StringUtils.isEmpty(cuenta.getSscuenta())) {
			if (p == null) {
				p = QCuenta.cuenta1.sscuenta.equalsIgnoreCase(cuenta.getSscuenta());
			} else {
				p = p.and(QCuenta.cuenta1.sscuenta.equalsIgnoreCase(cuenta.getSscuenta()));
			}
		}
		if (!StringUtils.isEmpty(cuenta.getSsscuenta())) {
			if (p == null) {
				p = QCuenta.cuenta1.ssscuenta.equalsIgnoreCase(cuenta.getSsscuenta());
			} else {
				p = p.and(QCuenta.cuenta1.ssscuenta.equalsIgnoreCase(cuenta.getSsscuenta()));
			}
		}
		if (!StringUtils.isEmpty(cuenta.getSssscuenta())) {
			if (p == null) {
				p = QCuenta.cuenta1.sssscuenta.equalsIgnoreCase(cuenta.getSssscuenta());
			} else {
				p = p.and(QCuenta.cuenta1.sssscuenta.equalsIgnoreCase(cuenta.getSssscuenta()));
			}
		}
		if (p == null) {
			p = QCuenta.cuenta1.idsector.eq(cuenta.getIdsector());
		} else {
			p = p.and(QCuenta.cuenta1.idsector.eq(cuenta.getIdsector()));
		}
		return p;
	}

	/**
	 * Find by accoun composite inlcude empty.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the predicate
	 */
	public static Predicate findByAccounCompositeInlcudeEmpty(final Cuenta cuenta) {
		BooleanExpression p = null;
		if (!StringUtils.isEmpty(cuenta.getCuenta())) {
			p = QCuenta.cuenta1.cuenta.equalsIgnoreCase(cuenta.getCuenta());
		}
		if (!StringUtils.isEmpty(cuenta.getScuenta())) {
			p = p.and(QCuenta.cuenta1.scuenta.equalsIgnoreCase(cuenta.getScuenta()));
		} else {
			p = p.and(QCuenta.cuenta1.scuenta.equalsIgnoreCase(StringUtils.EMPTY));
		}
		if (!StringUtils.isEmpty(cuenta.getSscuenta())) {
			p = p.and(QCuenta.cuenta1.sscuenta.equalsIgnoreCase(cuenta.getSscuenta()));

		} else {
			p = p.and(QCuenta.cuenta1.sscuenta.equalsIgnoreCase(StringUtils.EMPTY));
		}
		if (!StringUtils.isEmpty(cuenta.getSsscuenta())) {
			p = p.and(QCuenta.cuenta1.ssscuenta.equalsIgnoreCase(cuenta.getSsscuenta()));
		} else {
			p = p.and(QCuenta.cuenta1.ssscuenta.equalsIgnoreCase(StringUtils.EMPTY));
		}
		if (!StringUtils.isEmpty(cuenta.getSssscuenta())) {
			p = p.and(QCuenta.cuenta1.sssscuenta.equalsIgnoreCase(cuenta.getSssscuenta()));
		} else {
			p = p.and(QCuenta.cuenta1.sssscuenta.equalsIgnoreCase(StringUtils.EMPTY));
		}

		p = p.and(QCuenta.cuenta1.idsector.eq(cuenta.getIdsector()));

		return p;
	}

	/**
	 * Find by polide composite last level.
	 *
	 * @param polide
	 *            the polide
	 * @param idSector
	 *            the id sector
	 * @return the predicate
	 */
	public static Predicate findByPolideCompositeLastLevel(final Polide polide, Integer idSector) {
		BooleanExpression p = null;
		if (!StringUtils.isEmpty(polide.getCuenta())) {
			p = QCuenta.cuenta1.cuenta.equalsIgnoreCase(polide.getCuenta());
		}
		if (!StringUtils.isEmpty(polide.getScta())) {
			if (p == null) {
				p = QCuenta.cuenta1.scuenta.equalsIgnoreCase(polide.getScta());
			} else {
				p = p.and(QCuenta.cuenta1.scuenta.equalsIgnoreCase(polide.getScta()));
			}
		}
		if (!StringUtils.isEmpty(polide.getSscta())) {
			if (p == null) {
				p = QCuenta.cuenta1.sscuenta.equalsIgnoreCase(polide.getSscta());
			} else {
				p = p.and(QCuenta.cuenta1.sscuenta.equalsIgnoreCase(polide.getSscta()));
			}
		}
		if (!StringUtils.isEmpty(polide.getSsscta())) {
			if (p == null) {
				p = QCuenta.cuenta1.ssscuenta.equalsIgnoreCase(polide.getSsscta());
			} else {
				p = p.and(QCuenta.cuenta1.ssscuenta.equalsIgnoreCase(polide.getSsscta()));
			}
		}
		if (!StringUtils.isEmpty(polide.getSssscta())) {
			if (p == null) {
				p = QCuenta.cuenta1.sssscuenta.equalsIgnoreCase(polide.getSssscta());
			} else {
				p = p.and(QCuenta.cuenta1.sssscuenta.equalsIgnoreCase(polide.getSssscta()));
			}
		}
		if (p == null) {
			p = QCuenta.cuenta1.idsector.eq(idSector.longValue());
		} else {
			p = p.and(QCuenta.cuenta1.idsector.eq(idSector.longValue()));
		}
		p = p.and(QCuenta.cuenta1.nivcta.equalsIgnoreCase("S"));
		return p;
	}

	/**
	 * Find by polide composite last level inlcude empty.
	 *
	 * @param polide
	 *            the polide
	 * @param idSector
	 *            the id sector
	 * @return the predicate
	 */
	public static Predicate findByPolideCompositeLastLevelInlcudeEmpty(final Polide polide, Integer idSector) {
		BooleanExpression p = null;
		if (!StringUtils.isEmpty(polide.getCuenta())) {
			p = QCuenta.cuenta1.cuenta.equalsIgnoreCase(polide.getCuenta());
		}
		if (!StringUtils.isEmpty(polide.getScta())) {
			p = p.and(QCuenta.cuenta1.scuenta.equalsIgnoreCase(polide.getScta()));
		} else {
			p = p.and(QCuenta.cuenta1.scuenta.equalsIgnoreCase(StringUtils.EMPTY));
		}
		if (!StringUtils.isEmpty(polide.getSscta())) {
			p = p.and(QCuenta.cuenta1.sscuenta.equalsIgnoreCase(polide.getSscta()));

		} else {
			p = p.and(QCuenta.cuenta1.sscuenta.equalsIgnoreCase(StringUtils.EMPTY));
		}
		if (!StringUtils.isEmpty(polide.getSsscta())) {
			p = p.and(QCuenta.cuenta1.ssscuenta.equalsIgnoreCase(polide.getSsscta()));
		} else {
			p = p.and(QCuenta.cuenta1.ssscuenta.equalsIgnoreCase(StringUtils.EMPTY));
		}
		if (!StringUtils.isEmpty(polide.getSssscta())) {
			p = p.and(QCuenta.cuenta1.sssscuenta.equalsIgnoreCase(polide.getSssscta()));
		} else {
			p = p.and(QCuenta.cuenta1.sssscuenta.equalsIgnoreCase(StringUtils.EMPTY));
		}

		p = p.and(QCuenta.cuenta1.idsector.eq(idSector.longValue()));
		p = p.and(QCuenta.cuenta1.nivcta.equalsIgnoreCase("S"));
		return p;
	}

	/**
	 * Find by polide composite inlcude empty.
	 *
	 * @param polide
	 *            the polide
	 * @param idSector
	 *            the id sector
	 * @return the predicate
	 */
	public static Predicate findByPolideCompositeInlcudeEmpty(final Polide polide, Integer idSector) {
		BooleanExpression p = null;
		if (!StringUtils.isEmpty(polide.getCuenta())) {
			p = QCuenta.cuenta1.cuenta.equalsIgnoreCase(polide.getCuenta());
		}
		if (!StringUtils.isEmpty(polide.getScta())) {
			p = p.and(QCuenta.cuenta1.scuenta.equalsIgnoreCase(polide.getScta()));
		} else {
			p = p.and(QCuenta.cuenta1.scuenta.equalsIgnoreCase(StringUtils.EMPTY));
		}
		if (!StringUtils.isEmpty(polide.getSscta())) {
			p = p.and(QCuenta.cuenta1.sscuenta.equalsIgnoreCase(polide.getSscta()));

		} else {
			p = p.and(QCuenta.cuenta1.sscuenta.equalsIgnoreCase(StringUtils.EMPTY));
		}
		if (!StringUtils.isEmpty(polide.getSsscta())) {
			p = p.and(QCuenta.cuenta1.ssscuenta.equalsIgnoreCase(polide.getSsscta()));
		} else {
			p = p.and(QCuenta.cuenta1.ssscuenta.equalsIgnoreCase(StringUtils.EMPTY));
		}
		if (!StringUtils.isEmpty(polide.getSssscta())) {
			p = p.and(QCuenta.cuenta1.sssscuenta.equalsIgnoreCase(polide.getSssscta()));
		} else {
			p = p.and(QCuenta.cuenta1.sssscuenta.equalsIgnoreCase(StringUtils.EMPTY));
		}

		p = p.and(QCuenta.cuenta1.idsector.eq(idSector.longValue()));
		return p;
	}

	/**
	 * Find by account like composite.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the predicate
	 */
	public static Predicate findByAccountLikeComposite(final Cuenta cuenta) {
		BooleanExpression p = null;
		if (!StringUtils.isEmpty(cuenta.getCuenta())) {
			p = QCuenta.cuenta1.cuenta.likeIgnoreCase(cuenta.getCuenta());
		}
		if (!StringUtils.isEmpty(cuenta.getScuenta())) {
			if (p == null) {
				p = QCuenta.cuenta1.scuenta.likeIgnoreCase(cuenta.getScuenta());
			} else {
				p = p.and(QCuenta.cuenta1.scuenta.likeIgnoreCase(cuenta.getScuenta()));
			}
		}
		if (!StringUtils.isEmpty(cuenta.getSscuenta())) {
			if (p == null) {
				p = QCuenta.cuenta1.sscuenta.likeIgnoreCase(cuenta.getSscuenta());
			} else {
				p = p.and(QCuenta.cuenta1.sscuenta.likeIgnoreCase(cuenta.getSscuenta()));
			}
		}
		if (!StringUtils.isEmpty(cuenta.getSsscuenta())) {
			if (p == null) {
				p = QCuenta.cuenta1.ssscuenta.likeIgnoreCase(cuenta.getSsscuenta());
			} else {
				p = p.and(QCuenta.cuenta1.ssscuenta.likeIgnoreCase(cuenta.getSsscuenta()));
			}
		}
		if (!StringUtils.isEmpty(cuenta.getSssscuenta())) {
			if (p == null) {
				p = QCuenta.cuenta1.sssscuenta.likeIgnoreCase(cuenta.getSssscuenta());
			} else {
				p = p.and(QCuenta.cuenta1.sssscuenta.likeIgnoreCase(cuenta.getSssscuenta()));
			}
		}
		if (!StringUtils.isEmpty(cuenta.getNomcta())) {
			if (p == null) {
				p = QCuenta.cuenta1.nomcta.lower().likeIgnoreCase(cuenta.getNomcta().toLowerCase());
			} else {
				p = p.and(QCuenta.cuenta1.nomcta.lower().likeIgnoreCase(cuenta.getNomcta().toLowerCase()));
			}
		}
		if (p == null) {
			p = QCuenta.cuenta1.idsector.eq(cuenta.getIdsector());
		} else {
			p = p.and(QCuenta.cuenta1.idsector.eq(cuenta.getIdsector()));
		}
		return p;
	}

	/**
	 * Find by accounts like last level composite.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the predicate
	 */
	public static Predicate findByAccountsLikeLastLevelComposite(final Cuenta cuenta) {
		BooleanExpression p = null;
		if (!StringUtils.isEmpty(cuenta.getCuenta())) {
			p = QCuenta.cuenta1.cuenta.likeIgnoreCase(cuenta.getCuenta());
		}
		if (!StringUtils.isEmpty(cuenta.getScuenta())) {
			if (p == null) {
				p = QCuenta.cuenta1.scuenta.likeIgnoreCase(cuenta.getScuenta());
			} else {
				p = p.and(QCuenta.cuenta1.scuenta.likeIgnoreCase(cuenta.getScuenta()));
			}
		}
		if (!StringUtils.isEmpty(cuenta.getSscuenta())) {
			if (p == null) {
				p = QCuenta.cuenta1.sscuenta.likeIgnoreCase(cuenta.getSscuenta());
			} else {
				p = p.and(QCuenta.cuenta1.sscuenta.likeIgnoreCase(cuenta.getSscuenta()));
			}
		}
		if (!StringUtils.isEmpty(cuenta.getSsscuenta())) {
			if (p == null) {
				p = QCuenta.cuenta1.ssscuenta.likeIgnoreCase(cuenta.getSsscuenta());
			} else {
				p = p.and(QCuenta.cuenta1.ssscuenta.likeIgnoreCase(cuenta.getSsscuenta()));
			}
		}
		if (!StringUtils.isEmpty(cuenta.getSssscuenta())) {
			if (p == null) {
				p = QCuenta.cuenta1.sssscuenta.likeIgnoreCase(cuenta.getSssscuenta());
			} else {
				p = p.and(QCuenta.cuenta1.sssscuenta.likeIgnoreCase(cuenta.getSssscuenta()));
			}
		}
		if (!StringUtils.isEmpty(cuenta.getNomcta())) {
			if (p == null) {
				p = QCuenta.cuenta1.nomcta.lower().likeIgnoreCase(cuenta.getNomcta().toLowerCase());
			} else {
				p = p.and(QCuenta.cuenta1.nomcta.lower().likeIgnoreCase(cuenta.getNomcta().toLowerCase()));
			}
		}
		if (p == null) {
			p = QCuenta.cuenta1.idsector.eq(cuenta.getIdsector());
		} else {
			p = p.and(QCuenta.cuenta1.idsector.eq(cuenta.getIdsector()));
		}
		p = p.and(QCuenta.cuenta1.nivcta.equalsIgnoreCase("S"));
		return p;
	}

	/**
	 * Find poliza by cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param sCta
	 *            the s cta
	 * @param ssCta
	 *            the ss cta
	 * @param sssCta
	 *            the sss cta
	 * @param ssssCta
	 *            the ssss cta
	 * @return the predicate
	 */
	public static Predicate findPolizaByCuenta(final String cuenta, final String sCta, final String ssCta,
			final String sssCta, final String ssssCta) {
		BooleanExpression expresion = null;
		if (!cuenta.equals(null) && sCta.equals(null) && ssCta.equals(null) && sssCta.equals(null)
				&& ssssCta.equals(null)) {
			expresion = QCuenta.cuenta1.cuenta.equalsIgnoreCase(cuenta);
		} else if (!cuenta.equals(null) && !sCta.equals(null) && ssCta.equals(null) && sssCta.equals(null)
				&& ssssCta.equals(null)) {
			expresion = QCuenta.cuenta1.cuenta.equalsIgnoreCase(cuenta)
					.and(QCuenta.cuenta1.scuenta.equalsIgnoreCase(sCta));
		} else if (!cuenta.equals(null) && !sCta.equals(null) && !ssCta.equals(null) && sssCta.equals(null)
				&& ssssCta.equals(null)) {
			expresion = QCuenta.cuenta1.cuenta.equalsIgnoreCase(cuenta)
					.and(QCuenta.cuenta1.scuenta.equalsIgnoreCase(sCta))
					.and(QCuenta.cuenta1.sscuenta.equalsIgnoreCase(ssCta));
		} else if (!cuenta.equals(null) && !sCta.equals(null) && !ssCta.equals(null) && !sssCta.equals(null)
				&& ssssCta.equals(null)) {
			expresion = QCuenta.cuenta1.cuenta.equalsIgnoreCase(cuenta)
					.and(QCuenta.cuenta1.scuenta.equalsIgnoreCase(sCta))
					.and(QCuenta.cuenta1.sscuenta.equalsIgnoreCase(ssCta))
					.and(QCuenta.cuenta1.ssscuenta.equalsIgnoreCase(sssCta));
		} else if (!cuenta.equals(null) && !sCta.equals(null) && !ssCta.equals(null) && !sssCta.equals(null)
				&& !ssssCta.equals(null)) {
			expresion = QCuenta.cuenta1.cuenta.equalsIgnoreCase(cuenta)
					.and(QCuenta.cuenta1.scuenta.equalsIgnoreCase(sCta))
					.and(QCuenta.cuenta1.sscuenta.equalsIgnoreCase(ssCta))
					.and(QCuenta.cuenta1.ssscuenta.equalsIgnoreCase(sssCta))
					.and(QCuenta.cuenta1.sssscuenta.equalsIgnoreCase(ssssCta));
		}
		return expresion;

	}

	/**
	 * Exist by sscuenta and cuenta eq 5500 or 82.
	 *
	 * @param sCta
	 *            the s cta
	 * @return the predicate
	 */
	public static Predicate existBySscuentaAndCuentaEq5500Or82(final String sCta) {
		return QCuenta.cuenta1.sscuenta.likeIgnoreCase(sCta.toLowerCase() + "%")
				.and(QCuenta.cuenta1.cuenta.eq("5500").or(QCuenta.cuenta1.cuenta.like("82%")));
	}

	/**
	 * Validate exist account.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the predicate
	 */
	public static Predicate validateExistAccount(Cuenta cuenta) {
		Predicate p = null;
		if (null != cuenta.getCuenta() && null == cuenta.getScuenta() && null == cuenta.getSscuenta()
				&& null == cuenta.getSsscuenta() && null == cuenta.getSssscuenta()) {
			p = QCuenta.cuenta1.cuenta.eq(cuenta.getCuenta()).and(QCuenta.cuenta1.scuenta.eq(""))
					.and(QCuenta.cuenta1.sscuenta.eq("")).and(QCuenta.cuenta1.ssscuenta.eq(""))
					.and(QCuenta.cuenta1.sssscuenta.eq("")).and(QCuenta.cuenta1.idsector.eq(cuenta.getIdsector()));
		} else if (null != cuenta.getCuenta() && null != cuenta.getScuenta() && null == cuenta.getSscuenta()
				&& null == cuenta.getSsscuenta() && null == cuenta.getSssscuenta()) {
			p = QCuenta.cuenta1.cuenta.eq(cuenta.getCuenta()).and(QCuenta.cuenta1.scuenta.eq(cuenta.getScuenta()))
					.and(QCuenta.cuenta1.sscuenta.eq("")).and(QCuenta.cuenta1.ssscuenta.eq(""))
					.and(QCuenta.cuenta1.sssscuenta.eq("")).and(QCuenta.cuenta1.idsector.eq(cuenta.getIdsector()));
		} else if (null != cuenta.getCuenta() && null != cuenta.getScuenta() && null != cuenta.getSscuenta()
				&& null == cuenta.getSsscuenta() && null == cuenta.getSssscuenta()) {
			p = QCuenta.cuenta1.cuenta.eq(cuenta.getCuenta()).and(QCuenta.cuenta1.scuenta.eq(cuenta.getScuenta()))
					.and(QCuenta.cuenta1.sscuenta.eq(cuenta.getSscuenta())).and(QCuenta.cuenta1.ssscuenta.eq(""))
					.and(QCuenta.cuenta1.sssscuenta.eq("")).and(QCuenta.cuenta1.idsector.eq(cuenta.getIdsector()));
		} else if (null != cuenta.getCuenta() && null != cuenta.getScuenta() && null != cuenta.getSscuenta()
				&& null != cuenta.getSsscuenta() && null == cuenta.getSssscuenta()) {
			p = QCuenta.cuenta1.cuenta.eq(cuenta.getCuenta()).and(QCuenta.cuenta1.scuenta.eq(cuenta.getScuenta()))
					.and(QCuenta.cuenta1.sscuenta.eq(cuenta.getSscuenta()))
					.and(QCuenta.cuenta1.ssscuenta.eq(cuenta.getSsscuenta())).and(QCuenta.cuenta1.sssscuenta.eq(""))
					.and(QCuenta.cuenta1.idsector.eq(cuenta.getIdsector()));
		} else if (null != cuenta.getCuenta() && null != cuenta.getScuenta() && null != cuenta.getSscuenta()
				&& null != cuenta.getSsscuenta() && null != cuenta.getSssscuenta()) {
			p = QCuenta.cuenta1.cuenta.eq(cuenta.getCuenta()).and(QCuenta.cuenta1.scuenta.eq(cuenta.getScuenta()))
					.and(QCuenta.cuenta1.sscuenta.eq(cuenta.getSscuenta()))
					.and(QCuenta.cuenta1.ssscuenta.eq(cuenta.getSsscuenta()))
					.and(QCuenta.cuenta1.sssscuenta.eq(cuenta.getSssscuenta()))
					.and(QCuenta.cuenta1.idsector.eq(cuenta.getIdsector()));
		}

		return p;
	}

	public static Predicate isLastLavelAcount(Polide polide) {
		Predicate p = null;
		if (null != polide.getCuenta() && null == polide.getScta() && null == polide.getSscta()
				&& null == polide.getSsscta() && null == polide.getSsscta()) {
			p = QCuenta.cuenta1.cuenta.eq(polide.getCuenta())
					.and(QCuenta.cuenta1.idsector.eq(Long.valueOf(polide.getIdsector())))
					.and(QCuenta.cuenta1.scuenta.eq("")).and(QCuenta.cuenta1.sscuenta.eq(""))
					.and(QCuenta.cuenta1.ssscuenta.eq("")).and(QCuenta.cuenta1.sssscuenta.eq(""))
					.and(QCuenta.cuenta1.nivcta.eq("S"));
		} else if (null != polide.getCuenta() && null != polide.getScta() && null == polide.getSscta()
				&& null == polide.getSsscta() && null == polide.getSssscta()) {
			p = QCuenta.cuenta1.cuenta.eq(polide.getCuenta()).and(QCuenta.cuenta1.scuenta.eq(polide.getScta()))
					.and(QCuenta.cuenta1.sscuenta.eq("")).and(QCuenta.cuenta1.ssscuenta.eq(""))
					.and(QCuenta.cuenta1.sssscuenta.eq(""))
					.and(QCuenta.cuenta1.idsector.eq(Long.valueOf(polide.getIdsector())))
					.and(QCuenta.cuenta1.nivcta.eq("S"));
		} else if (null != polide.getCuenta() && null != polide.getScta() && null != polide.getSscta()
				&& null == polide.getSsscta() && null == polide.getSssscta()) {
			p = QCuenta.cuenta1.cuenta.eq(polide.getCuenta()).and(QCuenta.cuenta1.scuenta.eq(polide.getScta()))
					.and(QCuenta.cuenta1.sscuenta.eq(polide.getSscta())).and(QCuenta.cuenta1.ssscuenta.eq(""))
					.and(QCuenta.cuenta1.sssscuenta.eq(""))
					.and(QCuenta.cuenta1.idsector.eq(Long.valueOf(polide.getIdsector())))
					.and(QCuenta.cuenta1.nivcta.eq("S"));
		} else if (null != polide.getCuenta() && null != polide.getScta() && null != polide.getSscta()
				&& null != polide.getSsscta() && null == polide.getSssscta()) {
			p = QCuenta.cuenta1.cuenta.eq(polide.getCuenta()).and(QCuenta.cuenta1.scuenta.eq(polide.getScta()))
					.and(QCuenta.cuenta1.sscuenta.eq(polide.getSscta()))
					.and(QCuenta.cuenta1.ssscuenta.eq(polide.getSsscta())).and(QCuenta.cuenta1.sssscuenta.eq(""))
					.and(QCuenta.cuenta1.idsector.eq(Long.valueOf(polide.getIdsector())))
					.and(QCuenta.cuenta1.nivcta.eq("S"));

		} else if (null != polide.getCuenta() && null != polide.getScta() && null != polide.getSscta()
				&& null != polide.getSsscta() && null != polide.getSssscta()) {
			p = QCuenta.cuenta1.cuenta.eq(polide.getCuenta()).and(QCuenta.cuenta1.scuenta.eq(polide.getScta()))
					.and(QCuenta.cuenta1.sscuenta.eq(polide.getSscta()))
					.and(QCuenta.cuenta1.ssscuenta.eq(polide.getSsscta()))
					.and(QCuenta.cuenta1.sssscuenta.eq(polide.getSssscta()))
					.and(QCuenta.cuenta1.idsector.eq(Long.valueOf(polide.getIdsector())))
					.and(QCuenta.cuenta1.nivcta.eq("S"));
		}

		return p;
	}

}
