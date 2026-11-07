package com.gem.sistema.business.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.ReportsDAO;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.dto.ReporteRF0091202DTO;
import com.gem.sistema.business.dto.ReportsDTO;
import com.gem.sistema.business.dto.TotalesDTO;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.ParametrosRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.reportador.BByteArrayInputStream;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

// TODO: Auto-generated Javadoc
/**
 * The Class ReportsDAOImpl.
 */
@Repository(value = "reportsDAO")
public class ReportsDAOImpl implements ReportsDAO {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportsDAOImpl.class);

	/** The Constant REPORT_PATH_JASPER. */
	private static final String REPORT_PATH_JASPER = "reports";
	
	/** The Constant FILE_SEPARATOR. */
	private static final String FILE_SEPARATOR = "/";
	
	/** The Constant jasperReportName. */
	private static final String jasperReportName = "reporte_RF0091202";

	/**
	 * Tipo de contenido web pdf.
	 */
	public static final String CONTENT_TYPE_PDF = "application/pdf";

	/** The firmas. */
	private Firmas firmas;
	
	/** The firmas repository. */
	@Autowired
	private FirmasRepository firmasRepository;
	
	/** The parametros repository. */
	@Autowired
	private ParametrosRepository parametrosRepository;

	/** The mes repository. */
	@Autowired
	private TcMesRepository mesRepository;
	
	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;
	
	/** The data source. */
	private DataSource dataSource;

	/**
	 * Inyecta el datasource de la aplicacion.
	 *
	 * @param dataSource the new data source
	 */
	@Autowired
	@Qualifier(value = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
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
	 * Gets the jdbc call reports.
	 *
	 * @return the jdbc call reports
	 */
	public SimpleJdbcCall getJdbcCallReports() {
		return jdbcCallReports;
	}

	/**
	 * Sets the jdbc call reports.
	 *
	 * @param jdbcCallReports the new jdbc call reports
	 */
	public void setJdbcCallReports(SimpleJdbcCall jdbcCallReports) {
		this.jdbcCallReports = jdbcCallReports;
	}

	/**
	 * Gets the data source.
	 *
	 * @return the data source
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/** The jdbc call reports. */
	private SimpleJdbcCall jdbcCallReports;

	/**
	 * Inyecta el datasource de la aplicacion.
	 *
	 * @param dataSource the new data source cobis
	 */
	@Autowired
	@Qualifier(value = "dataSource")
	public void setDataSourceCobis(final DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.jdbcCallReports = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_REPORTE_RF0091202");

	}

	/**
	 * Gets the parametros repository.
	 *
	 * @return the parametros repository
	 */
	public ParametrosRepository getParametrosRepository() {
		return parametrosRepository;
	}

	/**
	 * Sets the parametros repository.
	 *
	 * @param parametrosRepository the new parametros repository
	 */
	public void setParametrosRepository(ParametrosRepository parametrosRepository) {
		this.parametrosRepository = parametrosRepository;
	}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.ReportsDAO#executeReport(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public ReportsDTO executeReport(Integer mes, Integer idSector, Integer firmas) {
		ReportsDTO reportsDTO = new ReportsDTO();
		/*
		 * IN i_mes INTEGER , IN i_id_sector INTEGER
		 */
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_mes", mes).addValue("i_firmas", firmas).addValue("i_id_sector", idSector);
		Map<String, Object> out = jdbcCallReports.execute(in);

		/**
		 * OUT o_cod_error INTEGER , OUT o_msg_error VARCHAR(9000), OUT o_file
		 * VARCHAR(500)
		 */

		reportsDTO.setoMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		reportsDTO.setoCodError(MapUtils.getInteger(out, "O_COD_ERROR"));
		reportsDTO.setoFileName(MapUtils.getString(out, "O_FILE"));
		return reportsDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.dao.ReportsDAO#getPath(java.lang.String)
	 */
	@Override
	public String getPath(String cvePath) {
		return this.parametrosRepository.getValorByCv(cvePath);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.ReportsDAO#generatePDF(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public JasperPrint generatePDF(Integer mes, Integer idSector) {
		Connection connection = null;
		JasperPrint jasperPrint = null;
		try {
			// JasperCompileManager.compileReport("reports/reporte_RF009_1_20_2.jrxml");
			try {
				connection = dataSource.getConnection();
				final FacesContext context = FacesContext.getCurrentInstance();
				final ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
				final String jasperPathReport = servletContext
						.getRealPath(REPORT_PATH_JASPER + FILE_SEPARATOR + jasperReportName + ".jasper");
				jasperPrint = JasperFillManager.fillReport(jasperPathReport, getParameters(mes, idSector), connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
				} catch (final SQLException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}

		} catch (JRException ex) {
			ex.printStackTrace();
		}
		return jasperPrint;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.ReportsDAO#generateQuery(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String generateQuery(Integer mes, Integer idSector) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT T1.PARTIDA PARTIDA, ").append(" T1.NOMGAS NOMGAS, ")
				.append(" T1.APROBADO  APROBADO,")
				.append(" T1.AMPLIACION AMPLIACION,")
				.append(" T1.REDUCCION  REDUCCION,")
				.append(" (T1.APROBADO + T1.AMPLIACION + T1.REDUCCION) MODIFICADO,")
				.append(" T1.COMPROMETIDO COMPROMETIDO,")
				.append(" T1.DEVENGADO DEVENGADO,")
				.append(" T1.PAGADO PAGADO,")
				.append(" T1.TOTAL_EJERCIDO TOTAL_EJERCIDO,")
				.append(" ((T1.APROBADO + T1.AMPLIACION + T1.REDUCCION) -  T1.TOTAL_EJERCIDO) POR_EJERCER")
				.append(" FROM (SELECT PA.PARTIDA,").append(" NG.NOMGAS,").append(" SUM(PA.AUTO1_1 + PA.AUTO1_2 +")
				.append("     PA.AUTO1_3 + PA.AUTO1_4 +").append("    PA.AUTO1_5 + PA.AUTO1_6 +")
				.append("    PA.AUTO1_7 + PA.AUTO1_8 +").append("    PA.AUTO1_9 + PA.AUTO1_10 +")
				.append("    PA.AUTO1_11 + PA.AUTO1_12 ").append(" ) APROBADO,").append(" SUM(PA.AMPLI1_" + mes + " )")
				.append(" AMPLIACION,").append(" SUM(PA.REDU1_" + mes + "  )").append(" REDUCCION,")
				.append(" SUM(PA.COMPRO1_" + mes + " )").append(" COMPROMETIDO,").append(" SUM(PA.EJXPA1_" + mes + ")")
				.append(" DEVENGADO,").append(" SUM(PA.EJPA1_" + mes + ")").append(" PAGADO,")
				.append(" SUM(PA.TOEJE1_" + mes + ")").append(" TOTAL_EJERCIDO").append(" FROM PASO PA,")
				.append(" NATGAS NG").append(" WHERE PA.IDSECTOR = NG.IDSECTOR").append("  AND PA.PARTIDA  = NG.CLVGAS")
				.append("	AND PA.IDSECTOR = " + idSector + "").append(" GROUP BY PA.PARTIDA,").append(" NG.NOMGAS")
				.append(" ) T1").append(" ORDER BY T1.PARTIDA ASC");

		return sSql.toString();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.ReportsDAO#getParameters(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Map<String, Object> getParameters(Integer mes, Integer idSector) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(idSector);

		Calendar c = Calendar.getInstance();
		int firsYear = c.get(c.YEAR);
		TcMes tcMes = mesRepository.findByMes(StringUtils.leftPad(String.valueOf(mes), 2, "0"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String dateInString = "01-" + StringUtils.leftPad(String.valueOf(mes), 2, "0") + "-" + firsYear + " 10:20:56";
		Date date;
		Calendar calendar = Calendar.getInstance();
		try {
			date = sdf.parse(dateInString);

			calendar.setTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		int lastDay = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
		TotalesDTO totales = jdbcTemplate.queryForObject(this.getQueryTotales(mes, idSector),
				new GetTotalesRowMapper());
		
		parameters.put("MesIn", 1);
		parameters.put("MesF", lastDay);
		parameters.put("Anio", firmas.getCampo3());
		parameters.put("Mes", tcMes.getDescripcion());
		parameters.put("SQL", this.generateQuery(mes, idSector));
		parameters.put("TotalAprobado", totales.getTotalAprobado());
		parameters.put("TotalAmpliacion", totales.getTotalAmpliacion());
		parameters.put("TotalReduccion", totales.getTotalReduccion());
		parameters.put("TotalModificacion", totales.getTotalModificacino());
		parameters.put("TotalComprometido", totales.getTotalComprometido());
		parameters.put("TotalDevengado", totales.getTotalDevengado());
		parameters.put("TotalPagado", totales.getPagado());
		parameters.put("TotalEjercido", totales.getTotalEjercido());
		parameters.put("TotalPorEjercer", totales.getTotalPorEjercer());
		parameters.put("firmaP1", firmas.getL4());
		parameters.put("firmaP2", firmas.getL5());
		parameters.put("firmaP3", firmas.getL27());
		parameters.put("firmaN1", firmas.getN4());
		parameters.put("firmaN2", firmas.getN5());
		parameters.put("firmaN3", firmas.getN27());
		parameters.put("entidadName", firmas.getCampo1());
		return parameters;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.ReportsDAO#createReportPdftoFs(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public void createReportPdftoFs(Integer mes, Integer idSector, String pathFileName) {

		try {
			final JasperPrint print = this.generatePDF(mes, idSector);
			JasperExportManager.exportReportToPdfFile(print, pathFileName);
			LOGGER.info(":: Termina Exportar PDF ");
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.ReportsDAO#getInternalReport(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public InputStream getInternalReport(Integer mes, Integer idSector) {
		InputStream stream = null;
		try {

			final JasperPrint print = this.generatePDF(mes, idSector);
			final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(print, arrayOutputStream);
			stream = new BByteArrayInputStream(arrayOutputStream.toByteArray());
		} catch (JRException e) {
			throw new RuntimeException(e);
		}

		return stream;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.ReportsDAO#getQueryTotales(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String getQueryTotales(Integer mes, Integer idSector) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT T1.APROBADO APROBADO, ")
				.append(" T1.AMPLIACION  AMPLIACION, ")
				.append(" T1.REDUCCION  REDUCCION,  ")
				.append(" (T1.APROBADO + T1.AMPLIACION + T1.REDUCCION)  MODIFICACION, ")
				.append(" T1.COMPROMETIDO,  COMPROMETIDO, ")
				.append(" T1.DEVENGADO DEVENGADO, ")
				.append(" T1.PAGADO PAGADO, ")
				.append(" T1.TOTAL_EJERCIDO TOTAL_EJERCIDO, ")
				.append(" ((T1.APROBADO + T1.AMPLIACION + T1.REDUCCION) -  T1.TOTAL_EJERCIDO) POR_EJERCER ")
				.append(" FROM (SELECT SUM(PA.AUTO1_1 + PA.AUTO1_2  + ").append(" PA.AUTO1_3 + PA.AUTO1_4  + ")
				.append(" PA.AUTO1_5 + PA.AUTO1_6  + ").append(" PA.AUTO1_7 + PA.AUTO1_8  + ")
				.append(" PA.AUTO1_9 + PA.AUTO1_10 + ").append(" PA.AUTO1_11 + PA.AUTO1_12   ) APROBADO,  ")
				.append(" SUM(PA.AMPLI1_%s)  AMPLIACION, ").append(" SUM(PA.REDU1_%s  ) REDUCCION, ")
				.append(" SUM(PA.COMPRO1_%s )  COMPROMETIDO, ").append(" SUM(PA.EJXPA1_%s) DEVENGADO, ")
				.append(" SUM(PA.EJPA1_%s) PAGADO, ").append(" SUM(PA.TOEJE1_%s) TOTAL_EJERCIDO ")
				.append(" FROM PASO PA  ").append(" WHERE SUBSTR(PA.PARTIDA,4) <> '0' AND PA.IDSECTOR = %s ) T1");
		String query = String.format(sSql.toString(), mes, mes, mes, mes, mes, mes, idSector);
		return query;
	}

}

class FindDataReport implements RowMapper<ReporteRF0091202DTO> {

	@Override
	public ReporteRF0091202DTO mapRow(ResultSet rs, int arg1) throws SQLException {
		ReporteRF0091202DTO report = new ReporteRF0091202DTO();
		report.setPartida(rs.getString("PARTIDA"));
		report.setDescripcion(rs.getString("NOMGAS"));
		report.setAprobado(rs.getBigDecimal("APROBADO"));
		report.setAmpliacion(rs.getBigDecimal("AMPLIACION"));
		report.setReduccion(rs.getBigDecimal("REDUCCION"));
		report.setModifcacion(rs.getBigDecimal("MODIFICADO"));
		report.setComprometido(rs.getBigDecimal("COMPROMETIDO"));
		report.setDevengado(rs.getBigDecimal("DEVENGADO"));
		report.setPagado(rs.getBigDecimal("PAGADO"));
		report.setTotalPagado(rs.getBigDecimal("TOTAL_EJERCIDO"));
		report.setPorEjercer(rs.getBigDecimal("POR_EJERCER"));
		return report;
	}

}

class GetTotalesRowMapper implements RowMapper<TotalesDTO> {

	@Override
	public TotalesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		TotalesDTO totales = new TotalesDTO();
		totales.setTotalAprobado(rs.getBigDecimal("APROBADO"));
		totales.setTotalAmpliacion(rs.getBigDecimal("AMPLIACION"));
		totales.setTotalReduccion(rs.getBigDecimal("REDUCCION"));
		totales.setTotalModificacino(rs.getBigDecimal("MODIFICACION"));
		totales.setTotalComprometido(rs.getBigDecimal("COMPROMETIDO"));
		totales.setTotalDevengado(rs.getBigDecimal("DEVENGADO"));
		totales.setPagado(rs.getBigDecimal("PAGADO"));
		totales.setTotalEjercido(rs.getBigDecimal("TOTAL_EJERCIDO"));
		totales.setTotalPorEjercer(rs.getBigDecimal("POR_EJERCER"));
		return totales;
	}

}