package com.gem.sistema.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.EstadoCambiosSFBCMBDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadoCambiosSFBCMBDAOImpl.
 *
 * @author Dalia Tovar
 */
@Repository
public class EstadoCambiosSFBCMBDAOImpl implements EstadoCambiosSFBCMBDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.EstadoCambiosSFBCMBDAO#generaQueryActivo(java.
	 * lang.Integer, java.lang.Integer)
	 */
	@Override
	public String generaQueryActivo(Integer idsector, Integer mesInicial, Integer mesFinal,
			Boolean mesAnteriorAcumulado) {
		StringBuilder sSqlActivo = new StringBuilder();
		StringBuilder cargosAnt = new StringBuilder();
		StringBuilder abonosAnt = new StringBuilder();
		StringBuilder cargosAct = new StringBuilder();
		StringBuilder abonosAct = new StringBuilder();
		if (mesAnteriorAcumulado) {
			cargosAnt.append(" 0.00 ");
			abonosAnt.append(" 0.00 ");
		} else {
			if (mesFinal == 1) {
				cargosAnt.append(" 0.00 ");
				abonosAnt.append(" 0.00 ");
			} else {

				for (int i = mesInicial; i < mesFinal; i++) {
					cargosAnt.append(" CARGOS_" + i + " +");
					abonosAnt.append(" ABONOS_" + i + " +");

				}
			}
		}
		for (int i = mesInicial; i <= mesFinal; i++) {
			cargosAct.append(" CARGOS_" + i + " +");
			abonosAct.append(" ABONOS_" + i + " +");

		}

		sSqlActivo.append("SELECT CUENTA, NOMCTA, \r\n"
				+ "		CASE WHEN SALDO > 0 THEN SALDO ELSE 0.0 END ORIGEN,\r\n"
				+ "		CASE WHEN SALDO < 0 THEN ABS(SALDO) ELSE 0.0 END APLICACION\r\n"
				+ "		FROM(\r\n"
				+ "SELECT CUENTA, NOMCTA, NOVIEMBRE - DICIEMBRE SALDO FROM (\r\n"
				+ "SELECT 	CUENTA, NOMCTA, \r\n"
				+ "		(SALINI + CARGOS_ANT - ABONOS_ANT) NOVIEMBRE, \r\n"
				+ "		(SALINI + CARGOS - ABONOS) DICIEMBRE \r\n"
				+ "	FROM (\r\n"
				+ "		SELECT 	CUENTA, NOMCTA, SALINI,\r\n"
				+ "				(" + abonosAnt.substring(0, abonosAnt.length() -1) + " ) ABONOS_ANT, \r\n"
				+ "				(" + cargosAnt.substring(0, cargosAnt.length() -1) + " ) CARGOS_ANT, \r\n"
				+ "				(" + abonosAct.substring(0, abonosAct.length() -1) + " ) ABONOS, \r\n"
				+ "				(" + cargosAct.substring(0, cargosAct.length() -1) + " ) CARGOS  \r\n"
				+ "			FROM CUENTA\r\n"
				+ "		WHERE SUBSTR(CUENTA,1,1) = '1' AND  SUBSTR(CUENTA,4,1) = '0' AND SCTA = '' \r\n"
				+ "	)T1\r\n"
				+ ")T2\r\n"
				+ ")T3\r\n"
				+ "WHERE SUBSTR(CUENTA,3,2)!= '00'\r\n"
				+ "ORDER BY CUENTA");
		System.out.println("Activo-> " + sSqlActivo.toString());

		return sSqlActivo.toString();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.EstadoCambiosSFBCMBDAO#generaQueryPasivo(java.
	 * lang.Integer, java.lang.Integer)
	 */
	@Override
	public String generaQueryPasivo(Integer idsector, Integer mesInicial, Integer mesFinal,
			Boolean mesAnteriorAcumulado) {

		StringBuilder sSqlPasivo = new StringBuilder();
		StringBuilder cargosAnt = new StringBuilder();
		StringBuilder abonosAnt = new StringBuilder();
		StringBuilder cargosAct = new StringBuilder();
		StringBuilder abonosAct = new StringBuilder();
		if (mesAnteriorAcumulado) {
			cargosAnt.append(" 0.00 ");
			abonosAnt.append(" 0.00 ");
		} else {
			if (mesFinal == 1) {
				cargosAnt.append(" 0.00 ");
				abonosAnt.append(" 0.00 ");
			} else {

				for (int i = mesInicial; i < mesFinal; i++) {
					cargosAnt.append(" CARGOS_" + i + " +");
					abonosAnt.append(" ABONOS_" + i + " +");

				}
			}
		}
		for (int i = mesInicial; i <= mesFinal; i++) {
			cargosAct.append(" CARGOS_" + i + " +");
			abonosAct.append(" ABONOS_" + i + " +");

		}

		sSqlPasivo.append("SELECT CUENTA, NOMCTA, \r\n"
				+ "		CASE WHEN SALDO < 0 THEN ABS(SALDO) ELSE 0.0 END ORIGEN,\r\n"
				+ "		CASE WHEN SALDO > 0 THEN SALDO ELSE 0.0 END APLICACION\r\n"
				+ "		FROM(\r\n"
				+ "SELECT CUENTA, NOMCTA, NOVIEMBRE - DICIEMBRE SALDO FROM (\r\n"
				+ "SELECT 	CUENTA, NOMCTA, \r\n"
				+ "		(SALINI + CARGOS_ANT - ABONOS_ANT) NOVIEMBRE, \r\n"
				+ "		(SALINI + CARGOS - ABONOS) DICIEMBRE \r\n"
				+ "	FROM (\r\n"
				+ "		SELECT 	CUENTA, NOMCTA, SALINI,\r\n"
				+ "				(" + abonosAnt.substring(0, abonosAnt.length() -1) + " ) ABONOS_ANT, \r\n"
				+ "				(" + cargosAnt.substring(0, cargosAnt.length() -1) + " ) CARGOS_ANT, \r\n"
				+ "				(" + abonosAct.substring(0, abonosAct.length() -1) + " ) ABONOS, \r\n"
				+ "				(" + cargosAct.substring(0, cargosAct.length() -1) + " ) CARGOS  \r\n"
				+ "			FROM CUENTA\r\n"
				+ "		WHERE SUBSTR(CUENTA,1,1) = '2' AND  SUBSTR(CUENTA,4,1) = '0' AND SCTA = ''\r\n"
				+ "	)T1\r\n"
				+ ")T2\r\n"
				+ ")T3\r\n"
				+ "WHERE SUBSTR(CUENTA,3,2)!= '00'\r\n"
				+ "ORDER BY CUENTA");

		System.out.println("pasivo ->" + sSqlPasivo.toString());
		return sSqlPasivo.toString();
	}

	@Override
	public String generaQueryHacienda(Integer idsector, Integer mesInicial, Integer mesFinal,
			Boolean mesAnteriorAcumulado) {
		StringBuilder sqlHacienda = new StringBuilder();
		StringBuilder cargosAnt = new StringBuilder();
		StringBuilder abonosAnt = new StringBuilder();
		StringBuilder cargosAct = new StringBuilder();
		StringBuilder abonosAct = new StringBuilder();
		if (mesAnteriorAcumulado) {
			cargosAnt.append(" 0.00 ");
			abonosAnt.append(" 0.00 ");
		} else {
			if (mesFinal == 1) {
				cargosAnt.append(" 0.00 ");
				abonosAnt.append(" 0.00 ");
			} else {

				for (int i = mesInicial; i < mesFinal; i++) {
					cargosAnt.append(" CARGOS_" + i + " +");
					abonosAnt.append(" ABONOS_" + i + " +");

				}
			}
		}
		for (int i = mesInicial; i <= mesFinal; i++) {
			cargosAct.append(" CARGOS_" + i + " +");
			abonosAct.append(" ABONOS_" + i + " +");

		}

		sqlHacienda.append("SELECT CUENTA, NOMCTA, \r\n"
				+ "		CASE WHEN SALDO > 0 THEN SALDO ELSE 0.0 END ORIGEN,\r\n"
				+ "		CASE WHEN SALDO < 0 THEN ABS(SALDO) ELSE 0.0 END APLICACION\r\n"
				+ "		FROM(\r\n"
				+ "SELECT CUENTA, NOMCTA, NOVIEMBRE - DICIEMBRE SALDO FROM (\r\n"
				+ "SELECT 	CUENTA, NOMCTA, \r\n"
				+ "		(SALINI + CARGOS_ANT - ABONOS_ANT) NOVIEMBRE, \r\n"
				+ "		(SALINI + CARGOS - ABONOS) DICIEMBRE \r\n"
				+ "	FROM (\r\n"
				+ "		SELECT 	CUENTA, NOMCTA, SALINI,\r\n"
				+ "				(" + abonosAnt.substring(0, abonosAnt.length() -1) + " ) ABONOS_ANT, \r\n"
				+ "				(" + cargosAnt.substring(0, cargosAnt.length() -1) + " ) CARGOS_ANT, \r\n"
				+ "				(" + abonosAct.substring(0, abonosAct.length() -1) + " ) ABONOS, \r\n"
				+ "				(" + cargosAct.substring(0, cargosAct.length() -1) + " ) CARGOS  \r\n"
				+ "			FROM CUENTA\r\n"
				+ "		WHERE SUBSTR(CUENTA,1,1) = '3' AND  SUBSTR(CUENTA,4,1) = '0' AND SCTA = '' \r\n"
				+ "	)T1\r\n"
				+ ")T2\r\n"
				+ ")T3\r\n"
				+ "WHERE SUBSTR(CUENTA,3,2)!= '00'\r\n"
				+ "ORDER BY CUENTA");

		System.out.println("hacienda ->" + sqlHacienda.toString());
		return sqlHacienda.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.EstadoCambiosSFBCMBDAO#generaQuery3211(java.lang
	 * .Integer, java.lang.Integer)
	 */
	@Override
	public String generaQuery3211(Integer idsector, Integer mesInicial, Integer mesFinal,
			Boolean mesAnteriorAcumulado) {
		StringBuilder cargonant = new StringBuilder();
		StringBuilder abonosant = new StringBuilder();
		StringBuilder cargosact = new StringBuilder();
		StringBuilder abonosact = new StringBuilder();
		StringBuilder sSql = new StringBuilder();
		if (mesAnteriorAcumulado) {
			cargonant.append(" 0.00");
			abonosant.append(" 0.00");
		} else {
			if (mesFinal == 1) {
				cargonant.append(" 0.00");
				abonosant.append(" 0.00");
			} else {

				for (int i = mesInicial; i < mesFinal; i++) {
					cargonant.append(" C.CARGOS_" + i + "+");
					abonosant.append(" C.ABONOS_" + i + "+");
				}
			}
		}
		for (int i = mesInicial; i <= mesFinal; i++) {
			cargosact.append(" C.CARGOS_" + i + "+");
			abonosact.append(" C.ABONOS_" + i + "+");
		}

		sSql.append("SELECT (SUM(T3.VAN4)-SUM(T3.VAN5)) TOTAL_ANT,").append(" (SUM(T3.VACT4)-SUM(T3.VACT5)) TOTAL_ACT")
				.append(" FROM (SELECT ").append(" CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMANT) ELSE 0.00 END VAN4,")
				.append(" CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMANT) ELSE 0.00 END VAN5,")
				.append(" CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMACT) ELSE 0.00 END VACT4,")
				.append(" CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMACT)ELSE 0.00 END VACT5")
				.append(" FROM(SELECT  T1.GRUPO, ").append(" T1.SALINI,")
				.append(" CASE WHEN T1.GRUPO  ='4' THEN (T1.SALINI+ABONOS_ANT-CARGOS_ANT)")
				.append(" ELSE (T1.SALINI-ABONOS_ANT+CARGOS_ANT)").append(" END VMANT,")
				.append(" CASE WHEN T1.GRUPO  ='4' THEN (T1.SALINI+ABONOS_ACT-CARGOS_ACT)")
				.append(" ELSE (T1.SALINI-ABONOS_ACT+CARGOS_ACT)").append(" END VMACT")
				.append(" FROM (SELECT SUBSTR(C.CUENTA,1,1) GRUPO, ").append(" C.CUENTA CUENTA ,")
				.append(" C.SALINI SALINI,").append(" SUM(").append(cargonant.substring(1, cargonant.length() - 1))
				.append(") CARGOS_ANT,").append(" SUM(").append(abonosant.substring(1, abonosant.length() - 1))
				.append(") ABONOS_ANT,").append(" SUM(").append(cargosact.substring(1, cargosact.length() - 1))
				.append(") CARGOS_ACT,").append(" SUM(").append(abonosact.substring(1, abonosact.length() - 1))
				.append(") ABONOS_ACT").append(" FROM CUENTA C").append(" WHERE C.IDSECTOR=" + idsector)
				.append(" AND (((C.CUENTA >='4100' AND C.CUENTA<='4399' AND SUBSTR(C.CUENTA,4,1)<>'0')")
				.append(" OR (C.CUENTA IN ('5100','5200','5300','5400','5600','5700')))").append(" AND C.SCTA ='')")
				.append(" OR (C.CUENTA ='5500' AND C.SCTA<>'' AND C.SSCTA ='' AND C.NOTCTA=0)")
				.append(" GROUP BY C.CUENTA,").append(" C.SALINI").append(" ORDER BY C.CUENTA)T1)T2")
				.append(" GROUP BY T2.GRUPO )T3");

		return sSql.toString();

	}

}
