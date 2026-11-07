package com.gem.sistema.business.dao.impl;

import java.util.Calendar;
import java.util.Map;
import javax.sql.DataSource;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import com.gem.sistema.business.dao.ReporteRF009114DAO;
import com.gem.sistema.business.dto.ReportsParamDTO;
import com.gem.sistema.business.repository.catalogs.ParametrosRepository;
import com.gem.sistema.web.security.model.GemUser;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteRF009114DAOImpl.
 */
@Repository(value = "reporteRF009114DAO")
public class ReporteRF009114DAOImpl implements ReporteRF009114DAO {

	/**
	 * Tipo de contenido web pdf.
	 */
	public static final String CONTENT_TYPE_PDF = "application/pdf";

	/** The parametros repository. */
	@Autowired
	private ParametrosRepository parametrosRepository;

	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;

	/** The jdbc call procedure. */
	private SimpleJdbcCall jdbcCallProcedure;

	/**
	 * Gets the jdbc call procedure.
	 *
	 * @return the jdbc call procedure
	 */
	public SimpleJdbcCall getJdbcCallProcedure() {
		return jdbcCallProcedure;
	}

	/**
	 * Sets the jdbc call procedure.
	 *
	 * @param jdbcCallProcedure the new jdbc call procedure
	 */
	public void setJdbcCallProcedure(SimpleJdbcCall jdbcCallProcedure) {
		this.jdbcCallProcedure = jdbcCallProcedure;
	}

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
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.jdbcCallProcedure = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_REPORTE_RF009114_PDF");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.ReporteRF009114DAO#executeProcedure(com.gem.
	 * sistema.business.dto.ReportsParamDTO)
	 */
	@Override
	public String executeQuery(Integer mes, Integer idSecor, Integer niveles) {

		/**
		 * IN i_mes INTEGER, IN i_id_sector INTEGER, IN i_tipo INTEGER, IN i_query
		 * VARCHAR(9000), IN i_qtotal VARCHAR(9000),
		 */
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_idsector", idSecor)
				.addValue("i_mes", mes).addValue("i_niveles", niveles);
		Map<String, Object> out = jdbcCallProcedure.execute(in);
		String sSql = MapUtils.getString(out, "O_NAME_FILE");

		/**
		 * OUT o_cod_error INTEGER, OUT o_msg_error VARCHAR(500), OUT o_full_file
		 * VARCHAR(1000)
		 */

		

		return  sSql  ;
	}

	@Override
	public ReportsParamDTO executeProcedure(ReportsParamDTO reportsParamDTO, Integer tipo) {
		ReportsParamDTO reports = new ReportsParamDTO();
		/**
		 * IN i_mes INTEGER, IN i_id_sector INTEGER, IN i_tipo INTEGER, IN i_query
		 * VARCHAR(9000), IN i_qtotal VARCHAR(9000),
		 */
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_idsector", reportsParamDTO.getIdSector())
			.addValue("i_tipo", tipo).addValue("i_mes", reportsParamDTO.getMes()).addValue("i_municipio", reportsParamDTO.getMunicipio());
		this.jdbcCallProcedure = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_REPORTE_RF009114_TXT_CSV");
		Map<String, Object> out = jdbcCallProcedure.execute(in);

		reports.setoCodError(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
		reports.setoMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		reports.setoFullFile(MapUtils.getString(out, "O_RUTA_FILE") + "/" + MapUtils.getString(out, "O_NAME_FILE"));
		this.jdbcCallProcedure = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_REPORTE_RF009114_PDF");
		/**
		 * OUT o_cod_error INTEGER, OUT o_msg_error VARCHAR(500), OUT o_full_file
		 * VARCHAR(1000)
		 */
		

		return reports;
	}

	/** The parameters. */
	Map<String, Object> parameters = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.dao.ReporteRF009114DAO#lastDay(java.lang.
	 * Integer, java.lang.Integer)
	 */
	@Override
	public Integer lastDay(Integer anio, Integer mes) {
		Calendar calendario = Calendar.getInstance();
		calendario.set(anio, mes - 1, 1);
		return calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Gets the user details.
	 *
	 * @return the user details
	 */
	protected GemUser getUserDetails() {
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
			return (GemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} else {
			return null;
		}
	}

}
