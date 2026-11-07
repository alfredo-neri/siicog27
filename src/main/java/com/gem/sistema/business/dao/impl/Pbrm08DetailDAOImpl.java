package com.gem.sistema.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.Pbrm08DetailDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class Pbrm08DetailDAOImpl.
 *
 * @author Dalia Tovar
 */
@Repository
public class Pbrm08DetailDAOImpl implements Pbrm08DetailDAO {

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
	 * @see com.gem.sistema.business.dao.Pbrm08DetailDAO#executeQueryHead(java.lang.
	 * Integer, java.lang.Integer)
	 */
	@Override
	public String executeQueryHead(Integer idsector, Integer trim) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder progracumulado = new StringBuilder();
		StringBuilder alcanacum = new StringBuilder();

		for (int i = 1; i <= trim; i++) {
			progracumulado.append(" FT.PROGE_" + i + "+");
			alcanacum.append(" FT.ALCANE_" + i + "+");

		}

		sSql.append("SELECT (FT.CLVDEP||SUBSTR( FT.CLVFUN,1,12)|| FT.CLVFIN|| FT.CVETEMAS || FT.CVEIND) GRUPO, ")
				.append(" SUBSTR(FT.CLVFUN,1,8) CLVF,").append(" SUBSTR (FT.CVETEMAS,1,4)PILAR,")
				.append(" SUBSTR( FT.CLVFUN,11,2) PROYECTO,").append(" FT.CLVDEP,").append(" FT.CLVFUN,")
				.append(" FT.CLVFIN,").append(" FT.OBJETIVO,").append(" M.NOMIND,")
				.append(" NVL(FT.FORMULA,'')FORMULA,").append(" NVL(FT.INTERPRETACION,'')INTERPRETACION,")
				.append(" NVL(FT.DIMENSION,'')DIMENSION,").append(" NVL(FT.DESCFAC,'') DESFAC,")
				.append(" NVL(FT.AMBITO1,'') AMBITO1,").append(" FT.AMBITO2 AMBITO,")
				.append(" NVL(FT.COBERTURA,'')COBERTURA,").append(" NVL(FT.DESCRIPCION,'')DESCRIPCION,")
				.append(" NVL(FT.LINEA_BASE,'')LINEA_BASE,").append(" NVL(FT.EVALUACION,'')EVALUACION,")
				.append(" CPD1.CVETEMAS CVTEMAS,").append(" COALESCE(FT.METANUALE_" + trim + ",0.00) METAANULE,")
				.append(" (FT.SEMAFORO1_" + trim + ") SEMAFORO1,").append(" (FT.SEMAFORO2_" + trim + ") SEMAFORO2,")
				.append(" COALESCE(FT.PROGE_" + trim + ",0.00)PROGE,")
				.append(" COALESCE(FT.ALCANE_" + trim + ",0.00)ALCANE,").append(" CPD1.DESCRIPCION NOMPILAR,")
				.append(" CPD2.DESCRIPCION NOMTEMA,").append(" MN1.CAMPO6 NOMPROG,").append(" MN2.CAMPO6 NOMPOY,")
				.append(" DG.CLAVE DG,").append(" DG.NOMBRE NOMGRAL,").append(" DA.NOMBRE NOMAUX,")
				.append(" DA.CLAVE DA,").append(" M.NOMIND NOMIN,").append(" FT.FRECUENCIA,").append(" M.TIPO MTIP,")
				.append("DECODE(FT.PROGE_" + trim + ",0, 0, NULL, 0, (FT.ALCANE_" + trim + "*100.00)/ FT.PROGE_" + trim
						+ ") EF1,")
				.append(" COALESCE(").append(progracumulado.substring(1, progracumulado.length() - 1))
				.append(",0.00)PROACUMULADO,").append(" COALESCE(")
				.append(alcanacum.substring(1, alcanacum.length() - 1)).append(",0.00)ALCANACUM,").append("DECODE((")
				.append(progracumulado.substring(0, progracumulado.length() - 1)).append("),0, 0, NULL, 0, ( (")
				.append(alcanacum.substring(0, alcanacum.length() - 1)).append(") *100.00)/ (")
				.append(progracumulado.substring(0, progracumulado.length() - 1)).append(") ) EF2,")
				.append("	DECODE(LENGTH(FT.CVETEMAS) , 8, FT.CVETEMAS) SUBTEMA,\n"
						+ "	DECODE(LENGTH(FT.CVETEMAS) , 8, CPD2.DESCRIPCION) NOM_TEMA")
				.append(" FROM FTECNICASM FT").append(" JOIN CPD CPD1 ON SUBSTR(FT.CVETEMAS,1,4) = CPD1.CVETEMAS")
				.append(" JOIN CPD CPD2 ON SUBSTR(FT.CVETEMAS,1,8) = CPD2.CVETEMAS")
				.append(" JOIN MUNINEP MN1 ON MN1.IDSECTOR = FT.IDSECTOR AND SUBSTR(FT.CLVFUN,1,8) = MN1.CAMPO7")
				.append(" JOIN MUNINEP MN2 ON MN2.IDSECTOR = FT.IDSECTOR AND SUBSTR(FT.CLVFUN,1,12) = MN2.CAMPO7")
				.append(" JOIN CATDGM DG ON SUBSTR (FT.CLVDEP,1,3) = DG.CLAVE")
				.append(" JOIN CATDAA DA ON SUBSTR( FT.CLVDEP,4,3) = DA.CLAVE")
				.append(" JOIN MIR M ON FT.CVEIND = M.CODIGO ").append(" WHERE FT.IDSECTOR=" + idsector)
				.append(" ORDER BY FT.CLVDEP, FT.CLVFUN");

		System.out.println(sSql.toString());
		return sSql.toString();
	}

	/** The grupo query. */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.Pbrm08DetailDAO#executeQueryDetail(java.lang
	 * .String, java.lang.Integer, java.lang.Integer)
	 */
	String grupoQuery;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.Pbrm08DetailDAO#executeQueryDetail(java.lang.
	 * Integer, java.lang.Integer)
	 */
	@Override
	public String executeQueryDetail(Integer idsector, Integer trim) {
		StringBuilder sSql2 = new StringBuilder();
		StringBuilder pgacum = new StringBuilder();
		StringBuilder alum = new StringBuilder();
		StringBuilder porcprogacum = new StringBuilder();
		StringBuilder porcalcanacum = new StringBuilder();

		for (int i = 1; i <= trim; i++) {
			pgacum.append(" FD.PROG_" + i + "+");
			alum.append(" FD.ALCAN_" + i + "+");

			porcprogacum.append(" FD.PORCPROG_" + i + "+");
			porcalcanacum.append(" FD.PORCALCAN_" + i + "+");
		}

		// grupoQuery=this.executeQuery(idsector, trim);
		sSql2.append("SELECT SUBSTR(FD.CLVFUN,1,8) CLVFUN,").append(" FD.CLVDEP,").append(" FD.CLVFUN,")
				.append("FD.CODIGO,").append("UPPER(FD.VARIABLES),").append("FD.UNIMED,").append("FD.OPERACION,")
				.append("FD.METANUAL,").append("FD.CVETEMAS,").append("FD.CVEVAR,")
				.append("COALESCE(FD.PROG_" + trim + ",0.00) PG,").append("COALESCE(FD.ALCAN_" + trim + ",0.00)ALC,")
				.append("COALESCE((").append(pgacum.substring(1, pgacum.length() - 1)).append("),0.00) PGACUM,")
				.append("COALESCE((").append(alum.substring(1, alum.length() - 1)).append("),0.00) ALCANACUM,")
				.append("FD.CVEIND,")
				.append("(FD.CLVDEP||SUBSTR( FD.CLVFUN,1,12)|| FD.CLVFIN|| FD.CVETEMAS || FD.CVEIND) GRUPO, ")
				.append("FD.PORCPROG_" + trim + " PROG_PORCE_TRIM, FD.PORCALCAN_" + trim + " ALC_PORCE_TRIM,")
				.append("(").append(porcprogacum.substring(0, porcprogacum.length() - 1)).append(") PROG_PORCE_ACUM, (")
				.append(porcalcanacum.substring(0, porcalcanacum.length() - 1)).append(") ALC_PORCE_ACUM ")
				.append(" FROM FTECNICASD FD").append(" WHERE FD.IDSECTOR=" + idsector)
				.append(" AND FD.CLVDEP||SUBSTR( FD.CLVFUN,1,12)|| FD.CLVFIN|| FD.CVETEMAS || FD.CVEIND =");

		System.out.println(sSql2.toString());
		return sSql2.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.Pbrm08DetailDAO#executeQueryGroup(java.lang.
	 * Integer, java.lang.Integer)
	 */
	@Override
	public String executeQueryGroup(Integer idsector, Integer trim) {
		StringBuilder sSqlG = new StringBuilder();

		sSqlG.append("SELECT (FT.CLVDEP||SUBSTR( FT.CLVFUN,1,12)|| FT.CLVFIN|| FT.CVETEMAS || FT.CVEIND) GRUPO ")
				.append(" FROM FTECNICASM FT JOIN CPD CPD1 ON SUBSTR(FT.CVETEMAS,1,4) = CPD1.CVETEMAS ")
				.append(" JOIN CPD CPD2 ON SUBSTR(FT.CVETEMAS,1,6) = CPD2.CVETEMAS ")
				.append(" JOIN MUNINEP MN1 ON MN1.IDSECTOR = FT.IDSECTOR AND SUBSTR(FT.CLVFUN,1,8) = MN1.CAMPO7 ")
				.append(" JOIN MUNINEP MN2 ON MN2.IDSECTOR = FT.IDSECTOR AND SUBSTR(FT.CLVFUN,1,12) = MN2.CAMPO7 ")
				.append(" JOIN CATDGM DG ON SUBSTR (FT.CLVDEP,1,3) = DG.CLAVE JOIN CATDAA DA ON SUBSTR( FT.CLVDEP,4,3) = DA.CLAVE ")
				.append(" JOIN MIR M ON FT.CVEIND = M.CODIGO  WHERE FT.IDSECTOR=" + idsector + " ORDER BY FT.CLVDEP");

		return sSqlG.toString();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.Pbrm08DetailDAO#executeQuery(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public String executeQuery(Integer idsector, Integer trim) {
		StringBuilder input = new StringBuilder();
		List<String> listGroup = this.jdbcTemplate.query(this.executeQueryGroup(idsector, trim), new GroupRowMaper());
		for (int i = 0; i < listGroup.size(); i++) {
			input.append("'" + listGroup.get(i) + "',");
		}
		return input.substring(0, input.length() - 1).toString();
	}

}

class GroupRowMaper implements RowMapper<String> {

	@Override
	public String mapRow(ResultSet rs, int arg1) throws SQLException {
		return rs.getString("GRUPO");
	}

}
