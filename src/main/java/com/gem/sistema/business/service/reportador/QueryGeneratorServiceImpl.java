package com.gem.sistema.business.service.reportador;

import org.springframework.stereotype.Service;

@Service(value = "queryGeneratorService")
public class QueryGeneratorServiceImpl implements QueryGeneratorService {

	/**
	 * Generador de Query para el Reporte
	 * Avance Presupuestal de Egresos por Secretaría
	 * 
	 * Integer idSector
	 * Integer mes
	 * String secretaria
	 */
	@Override
	public String queryAvancePresupuestalEgresosSecretaria(Integer idSector, Integer mes, String secretaria) {
		
		StringBuilder sql = new StringBuilder();
		StringBuilder redu = new StringBuilder();
		StringBuilder auto = new StringBuilder();
		StringBuilder ejpa = new StringBuilder();
		StringBuilder ejxpa = new StringBuilder();
		StringBuilder toeje = new StringBuilder();
		StringBuilder ampli = new StringBuilder();
		StringBuilder compro = new StringBuilder();
		int secretariaLength = secretaria.length();

		for (int i = 1; i <= Integer.valueOf(mes); i++) {
			redu.append("REDU1_" + i + " + ");
			auto.append("AUTO1_" + i + " + ");
			ejpa.append("EJPA1_" + i + " + ");
			ejxpa.append("EJXPA1_" + i + " + ");
			toeje.append("TOEJE1_" + i + " + ");
			ampli.append("AMPLI1_" + i + " + ");
			compro.append("COMPRO1_" + i + " + ");
		}

		sql.append("SELECT T1.PARTIDA, T1.NOMGAS, AUTORIZADO, AMPLIACION, REDUCCION, ")
				.append(" (AUTORIZADO + AMPLIACION - REDUCCION) MODIFICADO, COMPROMETIDO, DEVENGADO, PAGADO, EJERCIDO ")
				.append(" FROM( SELECT P.PARTIDA, N.NOMGAS,").append(" SUM(")
				.append(redu.substring(0, redu.length() - 2)).append(") REDUCCION, ").append(" SUM(")
				.append(auto.substring(0, auto.length() - 2)).append(") AUTORIZADO,").append(" SUM(")
				.append(ejpa.substring(0, ejpa.length() - 2)).append(") PAGADO, ").append(" SUM(")
				.append(ejxpa.substring(0, ejxpa.length() - 2)).append(") DEVENGADO, ").append(" SUM(")
				.append(toeje.substring(0, toeje.length() - 2)).append(") EJERCIDO, ").append(" SUM(")
				.append(ampli.substring(0, ampli.length() - 2)).append(") AMPLIACION, ").append(" SUM(")
				.append(compro.substring(0, compro.length() - 2)).append(") COMPROMETIDO ")
				.append("FROM PASO P INNER JOIN NATGAS N ON P.PARTIDA=N.CLVGAS ").append("WHERE SUBSTR(CLAVE,1,")
				.append(secretariaLength).append(") = '").append(secretaria)
				.append("' AND P.IDSECTOR=N.IDSECTOR AND P.IDSECTOR= ").append(idSector)
				.append(" GROUP BY P.PARTIDA,N.NOMGAS ) T1 ORDER BY T1.PARTIDA");

		return sql.toString();
	}

}
