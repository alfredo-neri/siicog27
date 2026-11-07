package com.gem.sistema.business.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.EdoSF3211DAO;
import com.gem.sistema.business.dto.EdoSF3211DTO;

// TODO: Auto-generated Javadoc
/**
 * The Class EdoSF3211DAOImpl.
 *
 * @author Dalia Tovar
 */
@Repository
public class EdoSF3211DAOImpl implements EdoSF3211DAO {

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Gets the jdbc template.
	 *
	 * @return the jdbc template
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * Sets the jdbc template.
	 *
	 * @param jdbcTemplate the new jdbc template
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.dao.EdoSF3211DAO#executeQueryCuenta(java.lang.
	 * Integer, java.lang.Integer)
	 */
	@Override
	public String createQueryCuenta(Integer idsector, Integer mesInicial, Integer mesFinal,
			Boolean mesAnteriorAcumulado) {

		StringBuilder cargonant = new StringBuilder();
		StringBuilder abonosant = new StringBuilder();
		StringBuilder cargosact = new StringBuilder();
		StringBuilder abonosact = new StringBuilder();
		StringBuilder sSql = new StringBuilder();
		if (mesAnteriorAcumulado) {
			if (mesFinal == 1) {
				cargonant.append(" 0.00");
				abonosant.append(" 0.00");
			} else {

				for (int i = mesInicial; i < mesFinal; i++) {
					cargonant.append(" C.CARGOS_" + i + "+");
					abonosant.append(" C.ABONOS_" + i + "+");
				}
			}
		} else {
			cargonant.append(" 0.00");
			abonosant.append(" 0.00");
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
				.append(" AND (	(	(SUBSTR(C.CUENTA,1,1) =4 AND\n" + "			 SUBSTR(C.CUENTA,4,1)<>'0')\n"
						+ "	 	OR\n" + "			(SUBSTR(C.CUENTA,1,1) = 5 AND \n"
						+ "			 SUBSTR(C.CUENTA,2,1) > 0)\n" + "		) AND\n" + "	C.SCTA ='')")
				.append(" GROUP BY C.CUENTA,").append(" C.SALINI").append(" ORDER BY C.CUENTA)T1)T2")
				.append(" GROUP BY T2.GRUPO )T3");
		System.out.println(sSql.toString());
		return sSql.toString();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.EdoSF3211DAO#executeQuery(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public EdoSF3211DTO executeQuery(Integer idsector, Integer mesInicial, Integer mesFinal,
			Boolean mesAnteriorAcumulado) {

		return jdbcTemplate.queryForObject(this.createQueryCuenta(idsector, mesInicial, mesFinal, mesAnteriorAcumulado),
				new EdoSF3211RowMapper());
	}

	/**
	 * Obtiene los saldos de la cuenta 3211 para el año actual y el año anterior
	 * (CUENTA_ANTERIOR) si existe un error retorno 0
	 */
	@Override
	public EdoSF3211DTO getTotalActualAndAnteriorByAnio(Integer idsector, Integer mesFinal) {
		try {

			StringBuilder query = new StringBuilder();
			String cargosact = "";
			String abonosact = "";
			for (int i = 1; i <= mesFinal; i++) {
				cargosact += " NVL(CARGOS_" + i + ",0)+";
				abonosact += " NVL(ABONOS_" + i + ",0)+";
			}
			cargosact = cargosact.substring(0, cargosact.length() - 1);
			abonosact = abonosact.substring(0, abonosact.length() - 1);
			query.append(
					"SELECT  I.ANTERIOR-E.ANTERIOR TOTAL_ANT,I.ACTUAL-E.ACTUAL TOTAL_ACT FROM										")
					.append("(SELECT N.CUENTA,N.ACTUAL,B.ANTERIOR                          ")
					.append("			FROM(SELECT                                                                                  ")
					.append("					3211 CUENTA,                                                                                ")
					.append("					SUM(( " + abonosact + " ) -                               ")
					.append("						(" + cargosact + ") +                                 ")
					.append("						NVL(SALINI,0)                                                                           ")
					.append("					) ACTUAL                                                                                    ")
					.append("				FROM CUENTA CU                                                                                  ")
					.append("				WHERE                                                                                           ")
					.append("					(CU.CUENTA >= '4151' AND CU.CUENTA <='4399') AND                                            ")
					.append("					CU.SSSCTA <> '' AND                                                                         ")
					.append("					CU.IDSECTOR= 2                                                                              ")
					.append("				GROUP BY 1                                                                                      ")
					.append("			) N INNER JOIN (                                                                                    ")
					.append("				SELECT                                                                                          ")
					.append("					3211 CUENTA,                                                                                ")
					.append("					SUM(( NVL(ABONOS_1,0) + NVL(ABONOS_2,0) + NVL(ABONOS_3,0) + NVL(ABONOS_4,0) +               ")
					.append("							NVL(ABONOS_5,0) + NVL(ABONOS_6,0)+ NVL(ABONOS_7,0) + NVL(ABONOS_8,0) +              ")
					.append("							NVL(ABONOS_9,0) + NVL(ABONOS_10,0) + NVL(ABONOS_11,0) + NVL(ABONOS_12,0)) -         ")
					.append("						(NVL(CARGOS_1,0) + NVL(CARGOS_2,0) + NVL(CARGOS_3,0)+NVL(CARGOS_4,0) +                  ")
					.append("							NVL(CARGOS_5,0) + NVL(CARGOS_6,0)+ NVL(CARGOS_7,0) + NVL(CARGOS_8,0) +              ")
					.append("							NVL(CARGOS_9,0)+NVL(CARGOS_10,0) + NVL(CARGOS_11,0) + NVL(CARGOS_12,0)) +           ")
					.append("						NVL(SALINI,0)                                                                           ")
					.append("					) ANTERIOR                                                                                  ")
					.append("				FROM CUENTA_ANTERIOR CU                                                                         ")
					.append("				WHERE                                                                                           ")
					.append("					(CU.CUENTA >= '4151' AND CU.CUENTA <='4399') AND                                            ")
					.append("					CU.SCTA = '' AND                                                                            ")
					.append("					CU.IDSECTOR= 2                                                                              ")
					.append("				GROUP BY 1                                                                                      ")
					.append("			)B ON N.CUENTA=B.CUENTA                                                                             ")
					.append("		) I	                                                                                                    ")
					.append("INNER JOIN                                                                                                     ")
					.append("			(SELECT N.CUENTA ,N.ACTUAL ,B.ANTERIOR                      ")
					.append("			FROM(SELECT 3211 CUENTA,                                                                            ")
					.append("					SUM(( " + cargosact + " ) -                       ")
					.append("						( " + abonosact + " ) +                       ")
					.append("						NVL(C.SALINI,0)                                                                         ")
					.append("					) ACTUAL                                                                                    ")
					.append("				FROM	CUENTA C                                                                                ")
					.append("				WHERE C.IDSECTOR=2 AND                                                                          ")
					.append("					((C.CUENTA IN ('5100', '5200', '5300', '5400','5600','5700')                                ")
					.append("					AND SSSCTA<>'')                                                                             ")
					.append("					OR (C.CUENTA IN ('5500') AND C.SCTA='' ))                                                   ")
					.append("				GROUP BY 1                                                                                      ")
					.append("			) N FULL JOIN (                                                                                     ")
					.append("				SELECT 	3211 CUENTA,                                                                            ")
					.append("					SUM(( NVL(C.CARGOS_1,0) + NVL(C.CARGOS_2,0) + 	NVL(C.CARGOS_3,0)+NVL(C.CARGOS_4,0) +       ")
					.append("						NVL(C.CARGOS_5,0) + 	NVL(C.CARGOS_6,0)+ NVL(C.CARGOS_7,0) + NVL(C.CARGOS_8,0) + 	    ")
					.append("						NVL(C.CARGOS_9,0)+NVL(C.CARGOS_10,0) + NVL(C.CARGOS_11,0) + 	NVL(C.CARGOS_12,0)) -   ")
					.append("						( NVL(C.ABONOS_1,0) + NVL(C.ABONOS_2,0) +	NVL(C.ABONOS_3,0) +NVL(C.ABONOS_4,0) +      ")
					.append("							NVL(C.ABONOS_5,0) +	NVL(C.ABONOS_6,0)+  NVL(C.ABONOS_7,0) + NVL(C.ABONOS_8,0) +	    ")
					.append("							NVL(C.ABONOS_9,0) +NVL(C.ABONOS_10,0) + NVL(C.ABONOS_11,0) +	NVL(C.ABONOS_12,0)  ")
					.append("						) + NVL(C.SALINI,0)                                                                     ")
					.append("					) ANTERIOR                                                                                  ")
					.append("				FROM	CUENTA_ANTERIOR C                                                                       ")
					.append("				WHERE C.IDSECTOR=2 AND                                                                          ")
					.append("					(                                                                                           ")
					.append("						(C.CUENTA IN ('5100', '5200', '5300', '5400','5600','5700')                             ")
					.append("						AND SSSCTA<>'')                                                                         ")
					.append("						OR (C.CUENTA IN ('5500') AND C.SCTA='' )                                                ")
					.append("					)                                                                                           ")
					.append("					GROUP BY 1	                                                                                ")
					.append("				)B ON N.CUENTA=B.CUENTA                                                                         ")
					.append("			)E ON I.CUENTA=E.CUENTA                                                                             ");
			return jdbcTemplate.queryForObject(query.toString(), new EdoSF3211RowMapper());
		} catch (Exception e) {
			e.printStackTrace();
			return new EdoSF3211DTO();
		}
	}

	@Override
	public BigDecimal getTotalEjerciciosAnteriores(Integer idSector) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ").append("SUM(C.SALINI - ")
				.append(" (C.CARGOS_1+ C.CARGOS_2+ C.CARGOS_3+ C.CARGOS_4+ C.CARGOS_5+ C.CARGOS_6+  ")
				.append(" C.CARGOS_7+ C.CARGOS_8+ C.CARGOS_9+ C.CARGOS_10+ C.CARGOS_11+ C.CARGOS_12 ")
				.append(" )+(C.ABONOS_1+ C.ABONOS_2+ C.ABONOS_3+ C.ABONOS_4+ C.ABONOS_5+ C.ABONOS_6+ ")
				.append(" C.ABONOS_7+ C.ABONOS_8+ C.ABONOS_9+ C.ABONOS_10+ C.ABONOS_11+ C.ABONOS_12 ")
				.append(" ) )SALDO ").append(" FROM CUENTA_ANTERIOR C  ")
				.append(" WHERE SUBSTR(CUENTA,1,2)='32' AND C.SCTA='' AND C.NOTCTA=0 AND C.IDSECTOR=2 ")
				.append(" GROUP BY SUBSTR(CUENTA,1,2) ");

		return jdbcTemplate.queryForObject(query.toString(), BigDecimal.class);
	}

	@Override
	public EdoSF3211DTO getTotalForEfectivoByAnio(Integer mesFinalActual, Integer mesFinalAnterior) {
		StringBuilder query = new StringBuilder();
		String cargosact = "";
		String abonosact = "";
		String cargosant = "";
		String abonosant = "";
		for (int i = 1; i < mesFinalActual; i++) {
			cargosact += " NVL(CARGOS_" + i + ",0)+";
			abonosact += " NVL(ABONOS_" + i + ",0)+";
		}
		for (int i = 1; i < mesFinalAnterior; i++) {
			cargosant += " NVL(CARGOS_" + i + ",0)+";
			abonosant += " NVL(ABONOS_" + i + ",0)+";
		}
		if (cargosact.equals(""))
			cargosact = " 0 ";
		if (cargosant.equals(""))
			cargosant = " 0 ";
		if (abonosact.equals(""))
			abonosact = " 0 ";
		if (abonosant.equals(""))
			abonosant = " 0 ";

		cargosact = cargosact.substring(0, cargosact.length() - 1);
		abonosact = abonosact.substring(0, abonosact.length() - 1);
		cargosant = cargosant.substring(0, cargosant.length() - 1);
		abonosant = abonosant.substring(0, abonosant.length() - 1);

		query.append("	SELECT CN.TOTAL_ACT,CA.TOTAL_ANT					")
				.append("	FROM(SELECT SUBSTR(CUENTA,1,3) CUENTA,          ")
				.append("			SUM(SALINI)                             ")
				.append("				+ SUM(" + cargosact + ")            ")
				.append("				-SUM(" + abonosact + ") TOTAL_ACT   ")
				.append("		FROM CUENTA                                 ")
				.append("		WHERE SUBSTR(CUENTA,1,3)='111' AND SCTA=''  ")
				.append("		GROUP BY SUBSTR(CUENTA,1,3)                 ")
				.append("	)CN INNER JOIN (                                ")
				.append("		SELECT SUBSTR(CUENTA,1,3) CUENTA,           ")
				.append("			SUM(NVL(C.SALINI,0.00) +                ")
				.append("				(" + cargosant + ")-                ")
				.append("				(" + abonosant + ")                	")
				.append("			)TOTAL_ANT                              ")
				.append("		FROM CUENTA_ANTERIOR C                      ")
				.append("		WHERE SUBSTR(CUENTA,1,3)='111' AND SCTA=''  ")
				.append("		GROUP BY SUBSTR(CUENTA,1,3)                 ")
				.append("	) CA ON CN.CUENTA=CA.CUENTA                     ");
		return jdbcTemplate.queryForObject(query.toString(), new EdoSF3211RowMapper());
	}

	@Override
	public EdoSF3211DTO getAnteriorByAnio(Integer mesFinalActual, Integer mesFinalAnterior) {
		StringBuilder query = new StringBuilder();
		String cargosact = "";
		String abonosact = "";
		String cargosant = "";
		String abonosant = "";
		for (int i = 1; i < mesFinalActual; i++) {
			cargosact += " NVL(CARGOS_" + i + ",0)+";
			abonosact += " NVL(ABONOS_" + i + ",0)+";
		}
		for (int i = 1; i < mesFinalAnterior; i++) {
			cargosant += " NVL(CARGOS_" + i + ",0)+";
			abonosant += " NVL(ABONOS_" + i + ",0)+";
		}

		if (cargosact.equals(""))
			cargosact = " 0 ";
		if (cargosant.equals(""))
			cargosant = " 0 ";
		if (abonosact.equals(""))
			abonosact = " 0 ";
		if (abonosant.equals(""))
			abonosant = " 0 ";
		
		cargosact = cargosact.substring(0, cargosact.length() - 1);
		abonosact = abonosact.substring(0, abonosact.length() - 1);
		cargosant = cargosant.substring(0, cargosant.length() - 1);
		abonosant = abonosant.substring(0, abonosant.length() - 1);

		query.append(
				"	SELECT (SUM(T3.VAN4)-SUM(T3.VAN5)) TOTAL_ANT,													")
				.append("		(SUM(T3.VACT4)-SUM(T3.VACT5)) TOTAL_ACT                                                     ")
				.append("	FROM (SELECT CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMANT) ELSE 0.00 END VAN4,                      ")
				.append("			CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMANT) ELSE 0.00 END VAN5,                           ")
				.append("			CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMACT) ELSE 0.00 END VACT4,                          ")
				.append("			CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMACT)ELSE 0.00 END VACT5                            ")
				.append("		FROM(SELECT T1.GRUPO,                                                                       ")
				.append("				CASE WHEN T1.GRUPO ='4' THEN (T1.SALINI+ABONOS_ANT-CARGOS_ANT) ELSE                 ")
				.append("				(T1.SALINI-ABONOS_ANT+CARGOS_ANT) END VMANT,                                        ")
				.append("				CASE WHEN T1.GRUPO ='4' THEN (T1.SALINIA+ABONOS_ACT-CARGOS_ACT) ELSE                ")
				.append("				(T1.SALINIA-ABONOS_ACT+CARGOS_ACT) END VMACT                                        ")
				.append("			FROM (SELECT CA.GRUPO,CA.SALINI,CA.CARGOS_ANT,CA.ABONOS_ANT,                            ")
				.append("					CN.SALINI SALINIA,CN.CARGOS_ACT,CN.ABONOS_ACT                                   ")
				.append("				FROM(                                                                               ")
				.append("					SELECT SUBSTR(C.CUENTA,1,1) GRUPO,                                              ")
				.append("						SUM(C.SALINI) SALINI,                                                       ")
				.append("	SUM( " + cargosant + " ) CARGOS_ANT,     ")
				.append("	SUM( " + abonosant + " ) ABONOS_ANT      ")
				.append("					FROM CUENTA_ANTERIOR C                                                          ")
				.append("					WHERE C.IDSECTOR=2 AND	( ( (SUBSTR(C.CUENTA,1,	1) =4 AND                       ")
				.append("						SUBSTR(C.CUENTA,4,1)<>'0') OR (                                             ")
				.append("							SUBSTR(C.CUENTA,	1,1) = 5 AND	SUBSTR(C.CUENTA,2,1) > 0)           ")
				.append("						) AND C.SCTA ='')                                                           ")
				.append("					GROUP BY SUBSTR(C.CUENTA,1,1)                                                   ")
				.append("				) CA INNER JOIN(                                                                    ")
				.append("					SELECT SUBSTR(C.CUENTA,1,1) GRUPO,                                              ")
				.append("						SUM(C.SALINI) SALINI,                                                       ")
				.append("	SUM( " + cargosact + " ) CARGOS_ACT,     ")
				.append("	SUM( " + abonosact + " ) ABONOS_ACT      ")
				.append("					FROM CUENTA C                                                                   ")
				.append("					WHERE C.IDSECTOR=2 AND	( ( (SUBSTR(C.CUENTA,1,	1) =4 AND                       ")
				.append("						SUBSTR(C.CUENTA,4,1)<>'0') OR (                                             ")
				.append("							SUBSTR(C.CUENTA,	1,1) = 5 AND	SUBSTR(C.CUENTA,2,1) > 0)           ")
				.append("						) AND C.SCTA ='')                                                           ")
				.append("					GROUP BY SUBSTR(C.CUENTA,1,1)                                                   ")
				.append("				)CN ON CA.GRUPO=CN.GRUPO                                                            ")
				.append("			)T1                                                                                     ")
				.append("		)T2                                                                                         ")
				.append("		GROUP BY T2.GRUPO                                                                           ")
				.append("	)T3                                                                                             ");

		return jdbcTemplate.queryForObject(query.toString(), new EdoSF3211RowMapper());
	}
}

class EdoSF3211RowMapper implements RowMapper<EdoSF3211DTO> {

	@Override
	public EdoSF3211DTO mapRow(ResultSet rs, int arg1) throws SQLException {
		EdoSF3211DTO edoSF3211DTO = new EdoSF3211DTO();

		edoSF3211DTO.setTotalAct(rs.getBigDecimal("TOTAL_ACT"));
		edoSF3211DTO.setTotalAnt(rs.getBigDecimal("TOTAL_ANT"));
		return edoSF3211DTO;
	}

}
