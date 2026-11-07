package com.gem.sistema.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.GeneraTxtBS;
import com.gem.sistema.business.dao.QueryDinamicoDAO;
import com.gem.sistema.util.Constants;

@Repository
public class QueryDinamicoDAOImpl implements QueryDinamicoDAO {

	private static final String PATH_NAME = "/gem/reportes/";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private GeneraTxtBS generaTxt;

	@Override
	public String getData(Integer mes, String fileName, Integer programa) {

		List<String> list = this.jdbcTemplate.query(getQuery(mes, programa), new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String data = "" + rs.getString("PROGRAMA") + ", \"" + rs.getString("CAMPO6") + "\", "
						+ rs.getBigDecimal("P_1000") + ", " + rs.getBigDecimal("P_2000") + ", "
						+ rs.getBigDecimal("P_3000") + ", " + rs.getBigDecimal("P_4000") + ", "
						+ rs.getBigDecimal("P_5000") + ", " + rs.getBigDecimal("P_6000") + ", "
						+ rs.getBigDecimal("P_7000") + ", " + rs.getBigDecimal("P_8000") + ", "
						+ rs.getBigDecimal("P_9000");
				return data;
			}
		});
		list.add(0, Constants.TITLES_EJERCIDO);

		return this.generaTxt.generaArchivo(list, PATH_NAME + fileName);
	}

	private String getQuery(int mes, Integer programa) {
		StringBuilder query = new StringBuilder();
		String toEje = "";
		for (int i = 0; i < mes; i++) {
			toEje = toEje + "PA.TOEJE1_" + (i + 1) + " + ";
		}
		toEje = toEje.substring(0, toEje.length() - 2);
		query.append("SELECT T1.PROGRAMA, MN.CAMPO6,\n ").append("        MN.CAMPO6  ,\n")
				.append("        T1.P_1000  ,\n").append("        T1.P_2000  ,\n").append("        T1.P_3000  ,\n")
				.append("        T1.P_4000  ,\n").append("        T1.P_5000  ,\n").append("        T1.P_6000  ,\n")
				.append("        T1.P_7000  ,\n").append("        T1.P_8000  ,\n").append("        T1.P_9000  \n")
				.append("      FROM ( ").append("SELECT SUBSTR(PA.PROGRAMA,1," + programa + ") PROGRAMA,\n")
				.append("        SUM(DECODE(SUBSTR(PA.PARTIDA,1,1), '1'," + toEje + ", 0 )) P_1000,\n")
				.append("        SUM(DECODE(SUBSTR(PA.PARTIDA,1,1), '2', " + toEje + ", 0 )) P_2000,\n")
				.append("        SUM(DECODE(SUBSTR(PA.PARTIDA,1,1), '3', " + toEje + ", 0 )) P_3000,\n")
				.append("        SUM(DECODE(SUBSTR(PA.PARTIDA,1,1), '4', " + toEje + ", 0 )) P_4000,\n")
				.append("        SUM(DECODE(SUBSTR(PA.PARTIDA,1,1), '5', " + toEje + ", 0 )) P_5000,\n")
				.append("        SUM(DECODE(SUBSTR(PA.PARTIDA,1,1), '6', " + toEje + ", 0 )) P_6000,\n")
				.append("        SUM(DECODE(SUBSTR(PA.PARTIDA,1,1), '7', " + toEje + ", 0 )) P_7000,\n")
				.append("        SUM(DECODE(SUBSTR(PA.PARTIDA,1,1), '8', " + toEje + ", 0 )) P_8000,\n")
				.append("        SUM(DECODE(SUBSTR(PA.PARTIDA,1,1), '9', " + toEje + ", 0 )) P_9000\n")
				.append("     FROM PASO PA\n").append(" WHERE PA.IDSECTOR = 2\n")
				.append("  AND SUBSTR(PA.PARTIDA,4,1) <>  '0'\n")
				.append("GROUP BY SUBSTR(PA.PROGRAMA,1," + programa + ")\n")
				.append("ORDER BY SUBSTR(PA.PROGRAMA,1," + programa + ")")
				.append(") T1, MUNINEP MN\n" + "WHERE T1.PROGRAMA =  MN.CAMPO7");
		return query.toString();

	}

	@Override
	public String generaTxtFichaDisenio(Integer sector, String fileName) {
		final String SEPARATOR = "\"|\"";

		List<String> listMaster = this.jdbcTemplate.query(this.generateQueryForFichaDisenioMaster(sector),
				new RowMapper<String>() {

					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {

						StringBuilder dataDatils = new StringBuilder();

						List<String> listDetails = jdbcTemplate.query(
								generateQueryForFichaDisenioDetail(sector, rs.getString("CLVFUN"),
										rs.getString("CVEIND"), rs.getString("CLVFIN"), rs.getString("CLVDEP")),
								new RowMapper<String>() {

									@Override
									public String mapRow(ResultSet rs, int rowNum) throws SQLException {

										String details = "\"" + rs.getString("CVEIND") + SEPARATOR
												+ rs.getString("NOMIND") + SEPARATOR + rs.getString("DEP_AUX")
												+ SEPARATOR + rs.getString("INTERPRETACION") + SEPARATOR
												+ rs.getString("DIMENSION") + SEPARATOR + rs.getBigDecimal("FACTOR")
												+ SEPARATOR + rs.getString("DESCFAC") + SEPARATOR + rs.getString("TIPO")
												+ SEPARATOR + rs.getString("CVEVAR") + SEPARATOR
												+ rs.getString("VARIABLES") + SEPARATOR + rs.getString("UNIMED")
												+ SEPARATOR + rs.getString("TIPOPER") + SEPARATOR
												+ rs.getBigDecimal("TRIM1E") + SEPARATOR + rs.getBigDecimal("TRIM2E")
												+ SEPARATOR + rs.getBigDecimal("TRIM3E") + SEPARATOR
												+ rs.getBigDecimal("TRIM4E") + SEPARATOR + rs.getBigDecimal("METANUAL")
												+ "\"";
										return details;
									}

								});

						for (String c : listDetails) {
							dataDatils.append(c).append("\r");
						}

						String masters = dataDatils + "\"" + rs.getString("CVEIND") + SEPARATOR + rs.getString("NOMIND")
								+ SEPARATOR + rs.getString("CLVDEP").substring(0, 3) + SEPARATOR
								+ rs.getString("INTERPRETACION") + SEPARATOR + rs.getString("DIMENSION") + SEPARATOR
								+ rs.getBigDecimal("FACTOR") + SEPARATOR + rs.getString("DESCFAC") + SEPARATOR
								+ rs.getString("TIPO") + SEPARATOR + rs.getBigDecimal("TRIM1E") + SEPARATOR
								+ rs.getBigDecimal("TRIM2E") + SEPARATOR + rs.getBigDecimal("TRIM3E") + SEPARATOR
								+ rs.getBigDecimal("TRIM4E") + SEPARATOR + rs.getBigDecimal("METANUALE") + SEPARATOR
								+ rs.getString("DESCANUAL") + SEPARATOR + rs.getString("MEDIOS") + SEPARATOR
								+ rs.getString("METASACT") + "\"";
						return masters;
					}

				});

		return this.generaTxt.generaArchivo(listMaster, PATH_NAME + fileName);
	}

	private String generateQueryForFichaDisenioMaster(Integer sector) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT CLVDEP, 		CLVFUN, 		CLVFIN, ")
				.append(" 		DM.CVEIND, 		MIR.NOMIND, 	DM.INTERPRETACION, ")
				.append("		DM.DIMENSION, 	FN_GET_FORMAT_NUMBER(NVL(DM.FACTOR,0)) FACTOR, ")
				.append("		DM.DESCFAC, 	'M' TIPO, 		FN_GET_FORMAT_NUMBER(DM.TRIM1E) TRIM1E, ")
				.append("		FN_GET_FORMAT_NUMBER(DM.TRIM2E) TRIM2E, ")
				.append("		FN_GET_FORMAT_NUMBER(DM.TRIM3E) TRIM3E, ")
				.append("		FN_GET_FORMAT_NUMBER(DM.TRIM4E) TRIM4E, ")
				.append("		FN_GET_FORMAT_NUMBER(DM.METANUALE) METANUALE, ")
				.append("		DM.DESCANUAL, 	DM.MEDIOS, 		DM.METASACT ").append("	FROM FTECNICADM DM, ")
				.append("		 MIR MIR ").append(" WHERE MIR.CODIGO  = DM.CVEIND ").append("	AND DM.IDSECTOR = ")
				.append(sector);

		return sql.toString();
	}

	private String generateQueryForFichaDisenioDetail(Integer sector, String clvfun, String clvind, String clvfin,
			String clvdep) {

		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT DD.CVEIND,  					MIR.NOMIND, ")
				.append(" 		SUBSTR(DD.CLVDEP,4,3) DEP_AUX, 	DM.INTERPRETACION, ")
				.append(" 		DM.DIMENSION, 					FN_GET_FORMAT_NUMBER(NVL(DM.FACTOR,0)) FACTOR, ")
				.append(" 		DM.DESCFAC, 					'V' TIPO, ")
				.append(" 		DD.CVEVAR,  					DD.VARIABLES, ")
				.append(" 		DD.UNIMED,  					DD.TIPOPER, ")
				.append(" 		FN_GET_FORMAT_NUMBER(DD.TRIM1) TRIM1E, FN_GET_FORMAT_NUMBER(DD.TRIM2) TRIM2E, ")
				.append(" 		FN_GET_FORMAT_NUMBER(DD.TRIM3) TRIM3E, FN_GET_FORMAT_NUMBER(DD.TRIM4) TRIM4E, ")
				.append(" 		FN_GET_FORMAT_NUMBER(DD.METANUAL) METANUAL ").append("	FROM GEMUSER.FTECNICADD DD ")
				.append(" 		INNER JOIN GEMUSER.MIR MIR ON MIR.CODIGO        = DD.CVEIND ")
				.append(" 		LEFT JOIN GEMUSER.FTECNICADM DM ON DD.CLVDEP    = DM.CLVDEP ")
				.append(" 										AND DD.CLVFUN   = DM.CLVFUN ")
				.append(" 										AND DD.CVEIND   = DM.CVEIND ")
				.append(" 										AND DD.CVETEMAS = DM.CVETEMAS ")
				.append(" 										AND DD.IDSECTOR = DM.IDSECTOR ")
				.append(" WHERE DD.IDSECTOR = ").append(sector).append("	AND	DD.CLVDEP   = '").append(clvdep)
				.append("'	AND	DD.CLVFUN   = '").append(clvfun).append("'	AND	DD.CLVFIN   = '").append(clvfin)
				.append("'	AND	DD.CVEIND   = '").append(clvind).append("'");

		return sql.toString();
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public GeneraTxtBS getGeneraTxt() {
		return generaTxt;
	}

	public void setGeneraTxt(GeneraTxtBS generaTxt) {
		this.generaTxt = generaTxt;
	}

}
