package com.gem.sistema.business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.dto.ParametersRF001027DTO;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.web.bean.AbstractMB;

import static com.roonin.utils.UtilDate.getYear;
import static com.roonin.utils.UtilDate.getDateOfSystem;
import static com.roonin.utils.UtilDate.getLastDay;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteRF001027DAOImpl.
 *
 * @author buser
 */
@Repository(value = "reporteRF001027DAO")
public class ReporteRF001027DAOImpl extends AbstractMB implements ReporteRF001027DAO {

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/** The data source. */
	@Autowired
	private DataSource dataSource;

	/** The mes repository. */
	@Autowired
	private TcMesRepository mesRepository;

	/** The firmas repository. */
	@Autowired
	private FirmasRepository firmasRepository;

	/**
	 * Gets the mes repository.
	 *
	 * @return the mes repository
	 */
	public TcMesRepository getMesRepository() {
		return mesRepository;
	}

	/**
	 * Sets the mes repository.
	 *
	 * @param mesRepository the new mes repository
	 */
	public void setMesRepository(TcMesRepository mesRepository) {
		this.mesRepository = mesRepository;
	}

	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	/**
	 * Sets the firmas repository.
	 *
	 * @param firmasRepository the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

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

	/**
	 * Gets the data source.
	 *
	 * @return the data source
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Sets the data source.
	 *
	 * @param dataSource the new data source
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.ReporteRF001027DAO#generateQuery(java.lang.
	 * Integer, java.lang.Integer)
	 */
	@Override
	public String generateQuery(Integer mes, Integer idSector) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder sumAuto = new StringBuilder();
		StringBuilder sumAmpli = new StringBuilder();
		StringBuilder sumredu = new StringBuilder();
		StringBuilder sumEjer = new StringBuilder();
		sSql.append("SELECT T2.CUENTA, ").append(" T2.CONCEPTO,").append(" T2.PRE_AUTO,").append(" T2.PRE_MES ,")
				.append(" T2.EJERCIDO,").append(" T2.PRE_ACU_ME,").append("  T2.EJERCIDO_MES,")
				.append(" T2.VARIACION_ABS,").append("  CASE").append(" WHEN T2.PRE_ACU_ME <> 0 THEN ")
				.append("  (( T2.VARIACION_ABS/ T2.PRE_ACU_ME) * 100) ")
				.append("  WHEN T2.VARIACION_ABS <> 0 THEN ").append("   0 ").append("  ELSE ").append("     0.00 ")
				.append("  END  PORCENTAJE ").append("  FROM (SELECT T1.CUENTA, ").append("   T1.CONCEPTO, ")
				.append("   T1.PRE_AUTO, ").append("  T1.PRE_MES, ")
				.append("  DECODE(" + mes
						+ ", 1,( SUM_AMPLI + SUM_REDU ), (SUM_AUTO + SUM_AMPLI + SUM_REDU ))  PRE_ACU_ME, ")
				.append("  T1.EJERCIDO, ").append("  T1.EJERCIDO_MES, ")
				.append("  ABS(DECODE(" + mes
						+ ", 1,( SUM_AMPLI + SUM_REDU ), (SUM_AUTO + SUM_AMPLI + SUM_REDU )) - T1.EJERCIDO_MES ) VARIACION_ABS ")
				.append(" FROM ").append("(SELECT PA.PARTIDA CUENTA,").append(" NA.NOMGAS CONCEPTO,")
				.append(" (SUM(PA.AUTO1_1) + SUM(PA.AUTO1_2) +").append(" SUM(PA.AUTO1_3) + SUM(PA.AUTO1_4) + ")
				.append(" SUM(PA.AUTO1_5) + SUM(PA.AUTO1_6) +").append(" SUM(PA.AUTO1_7) + SUM(PA.AUTO1_8) +")
				.append(" SUM(PA.AUTO1_9) + SUM(PA.AUTO1_10) +")
				.append(" SUM(PA.AUTO1_11) + SUM(PA.AUTO1_12) ) PRE_AUTO,")
				.append(" (SUM(PA.AUTO1_" + mes + ") + SUM(PA.AMPLI1_" + mes + ") - ")
				.append("  SUM(PA.REDU1_" + mes + ")) PRE_MES,");
		for (int i = 1; i <= mes; i++) {
			sumAuto.append(" SUM(PA.AUTO1_" + i + ") +");
			sumAmpli.append(" SUM(PA.AMPLI1_" + i + " ) +");
			sumredu.append(" SUM(PA.REDU1_" + i + ") +");
			sumEjer.append(" SUM(PA.TOEJE1_" + i + ") +");
		}
		sSql.append("(" + sumAuto.toString().substring(1, sumAuto.length() - 1) + ")SUM_AUTO, ")
				.append(" (" + sumAmpli.substring(1, sumAmpli.length() - 1) + ") SUM_AMPLI, ")
				.append(" ( " + sumredu.substring(1, sumredu.length() - 1) + " ) SUM_REDU, ")
				.append(" ( " + sumEjer.substring(1, sumEjer.length() - 1) + " ) EJERCIDO,")
				.append(" SUM(PA.TOEJE1_" + mes + ") EJERCIDO_MES ").append(" FROM PASO PA, ").append(" NATGAS NA ")
				.append("  WHERE PA.IDSECTOR = " + idSector).append("    AND PA.IDSECTOR = NA.IDSECTOR ")
				.append("   AND PA.PARTIDA  = NA.CLVGAS ").append("  GROUP BY PA.PARTIDA, ").append("   NA.NOMGAS ")
				.append(" ORDER BY PA.PARTIDA ASC ").append(" ) T1").append(" ) T2");

		return sSql.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.ReporteRF001027DAO#executeTotals(java.lang.
	 * Integer, java.lang.Integer)
	 */
	@Override
	public String executeTotals(Integer mes, Integer idSector) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder sumAuto = new StringBuilder();
		StringBuilder sumAmpli = new StringBuilder();
		StringBuilder sumredu = new StringBuilder();
		StringBuilder sumEjer = new StringBuilder();
		sSql.append("SELECT  T2.PRE_AUTO,").append(" T2.PRE_MES ,").append(" T2.EJERCIDO,").append(" T2.PRE_ACU_ME,")
				.append("  T2.EJERCIDO_MES,").append(" T2.VARIACION_ABS,").append("  CASE")
				.append(" WHEN T2.PRE_ACU_ME <> 0 THEN ").append("  (( T2.VARIACION_ABS/ T2.PRE_ACU_ME) * 100) ")
				.append("  WHEN T2.VARIACION_ABS <> 0 THEN ").append("   0 ").append("  ELSE ").append("     0.00 ")
				.append("  END  PORCENTAJE ").append("  FROM (SELECT ").append("   T1.PRE_AUTO, ")
				.append("  T1.PRE_MES, ")
				.append("  DECODE(" + mes
						+ ", 1,( SUM_AMPLI + SUM_REDU ), (SUM_AUTO + SUM_AMPLI + SUM_REDU ))  PRE_ACU_ME, ")
				.append("  T1.EJERCIDO, ").append("  T1.EJERCIDO_MES, ")
				.append("  ABS(DECODE(" + mes
						+ ", 1,( SUM_AMPLI + SUM_REDU ), (SUM_AUTO + SUM_AMPLI + SUM_REDU )) - T1.EJERCIDO_MES ) VARIACION_ABS ")
				.append(" FROM ").append("(SELECT ").append(" (SUM(PA.AUTO1_1) + SUM(PA.AUTO1_2) +")
				.append(" SUM(PA.AUTO1_3) + SUM(PA.AUTO1_4) + ").append(" SUM(PA.AUTO1_5) + SUM(PA.AUTO1_6) +")
				.append(" SUM(PA.AUTO1_7) + SUM(PA.AUTO1_8) +").append(" SUM(PA.AUTO1_9) + SUM(PA.AUTO1_10) +")
				.append(" SUM(PA.AUTO1_11) + SUM(PA.AUTO1_12) ) PRE_AUTO,")
				.append(" (SUM(PA.AUTO1_" + mes + ") + SUM(PA.AMPLI1_" + mes + ") - ")
				.append("  SUM(PA.REDU1_" + mes + ")) PRE_MES,");
		for (int i = 1; i <= mes; i++) {
			sumAuto.append(" SUM(PA.AUTO1_" + i + ") +");
			sumAmpli.append(" SUM(PA.AMPLI1_" + i + " ) +");
			sumredu.append(" SUM(PA.REDU1_" + i + ") +");
			sumEjer.append(" SUM(PA.TOEJE1_" + i + ") +");
		}
		sSql.append("(" + sumAuto.toString().substring(1, sumAuto.length() - 1) + ")SUM_AUTO, ")
				.append(" (" + sumAmpli.substring(1, sumAmpli.length() - 1) + ") SUM_AMPLI, ")
				.append(" ( " + sumredu.substring(1, sumredu.length() - 1) + " ) SUM_REDU, ")
				.append(" ( " + sumEjer.substring(1, sumEjer.length() - 1) + " ) EJERCIDO,")
				.append(" SUM(PA.TOEJE1_" + mes + ") EJERCIDO_MES ").append(" FROM PASO PA")
				.append("  WHERE PA.IDSECTOR = " + idSector).append("   AND SUBSTR(PA.PARTIDA,2,3)  = '000' ")
				.append(" ) T1").append(" ) T2");

		return sSql.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.dao.ReporteRF001027DAO#getTotals(java.lang.
	 * Integer, java.lang.Integer)
	 */
	@Override
	public ParametersRF001027DTO getTotals(Integer mes, Integer idSector) {
		return this.jdbcTemplate.queryForObject(this.executeTotals(mes, idSector), new ExecuteQueryTotal());
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.ReporteRF001027DAO#getParameters(java.lang.Integer)
	 */
	@Override
	public Map<String, Object> getParameters(Integer mes) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(calendar.YEAR, (mes - 1), 1);
		TcMes mesDescripcion = mesRepository.findByMes(StringUtils.leftPad(String.valueOf(mes), 2, "0"));
		
		ParametersRF001027DTO totales = getTotals(mes, this.getUserDetails().getIdSector());
		//List<Firmas> listFirmas = firmasRepository.findAllByIdSectorAndIdRef(this.getUserDetails().getIdSector(), 0);
		parameters.put("entePublico", this.getUserDetails().getMunicipio());
		parameters.put("mesFin", String.valueOf(getLastDay(mes)));
		parameters.put("mesFinalDesc", mesDescripcion.getDescripcion());
		parameters.put("year", getYear());
		parameters.put("pathImgGob", this.getUserDetails().getPathImgCab1());
		parameters.put("pathImgMuni", this.getUserDetails().getPathImgCab2());
		parameters.put("presidente", "C. IGNACIO AVILA NAVARRETE");
		parameters.put("tesorero", "LIC. ROBERTO R. GARCIA FLORES");
		parameters.put("secretario", "M. EN C. Y C.P. MIREYA MONROY MONROY");
		parameters.put("fechaEmision", getDateOfSystem());
		parameters.put("totalAutorizado", totales.getTotalAutorizado());
		parameters.put("totalModificadoMes", totales.getTotalModificadoMes());
		parameters.put("totalEjercidoMes", totales.getTotalEjercidoMes());
		parameters.put("totalAcumuladoMes", totales.getTotalAcumuladoMes());
		parameters.put("totalAcumuladoEjercido", totales.getTotalAcumuladoEjercido());
		parameters.put("totalAbs", totales.getTotalAbs());
		parameters.put("totalPociento", totales.getTotalPorcentaje());
		parameters.put("sSql", this.generateQuery(mes, this.getUserDetails().getIdSector()));

		return parameters;
	}

}

class ExecuteQueryTotal implements RowMapper<ParametersRF001027DTO> {

	@Override
	public ParametersRF001027DTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ParametersRF001027DTO reporte = new ParametersRF001027DTO();
		reporte.setTotalAutorizado(rs.getBigDecimal("PRE_AUTO"));
		reporte.setTotalModificadoMes(rs.getBigDecimal("PRE_MES"));
		reporte.setTotalEjercidoMes(rs.getBigDecimal("EJERCIDO"));
		reporte.setTotalAcumuladoMes(rs.getBigDecimal("PRE_ACU_ME"));
		reporte.setTotalAcumuladoEjercido(rs.getBigDecimal("EJERCIDO_MES"));
		reporte.setTotalAbs(rs.getBigDecimal("VARIACION_ABS"));
		reporte.setTotalPorcentaje(rs.getBigDecimal("PORCENTAJE"));
		return reporte;
	}

}
