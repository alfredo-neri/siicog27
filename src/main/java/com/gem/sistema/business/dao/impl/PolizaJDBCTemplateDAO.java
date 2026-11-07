/**
 * 
 */
package com.gem.sistema.business.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.domain.Poliza;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class PolizaJDBCTemplateDAO.
 *
 * @author Gerardo CUERO
 */
@Repository("polizaJDBCTemplateDAO")
public class PolizaJDBCTemplateDAO implements com.gem.sistema.business.dao.PolizaJDBCTemplateDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PolizaJDBCTemplateDAO.class);

	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;
	
	/** The data source. */
	private DataSource dataSource;

	/** The jdbc call. */
	private SimpleJdbcCall jdbcCall;
	
	/** The jdbc call delete. */
	private SimpleJdbcCall jdbcCallDelete;
	
	/** The jdbc call inverse. */
	private SimpleJdbcCall jdbcCallInverse;
	
	/** The jdbc call negative. */
	private SimpleJdbcCall jdbcCallNegative;
	
	/** The jdbc call afectacion. */
	private SimpleJdbcCall jdbcCallAfectacion;
	
	/** The jdbc call desafectacion. */
	private SimpleJdbcCall jdbcCallDesafectacion;
	
	/** The jdbc call desafecta poliza. */
	private SimpleJdbcCall jdbcCallDesafectaPoliza;
	
	/** The jdbc call desafectacion precierre. */
	private SimpleJdbcCall jdbcCallDesafectacionPrecierre;
	
	/** The jdbc call afectacion precierre. */
	private SimpleJdbcCall jdbcCallAfectacionPrecierre;

	/** The conctb repository. */
	@Autowired
	private ConctbRepository conctbRepository;

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Inyecta el datasource de la aplicacion.
	 *
	 * @param dataSource the new data source cobis
	 */
	@Autowired
	@Qualifier(value = "dataSource")
	public void setDataSourceCobis(final DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_COPIA_POLIZA");
		this.jdbcCallDelete = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_DELETE_POLIZA");
		this.jdbcCallInverse = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_INVERSA_POLIZA");
		this.jdbcCallNegative = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_NEGATIVA_POLIZA");
		this.jdbcCallAfectacion = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_AFECTACION");
		this.jdbcCallDesafectacion = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_DESAPLICA");
		this.jdbcCallDesafectaPoliza = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_DESAPLICA_POLIZA");
		this.jdbcCallDesafectacionPrecierre = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("SP_DESAFECTACION_PRECIERRE");
		this.jdbcCallAfectacionPrecierre = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("SP_AFECTACION_PRECIERRE");

	}

	/**
	 * Instantiates a new poliza JDBC template DAO.
	 */
	public PolizaJDBCTemplateDAO() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.PolizaJDBCTemplateDAO#copyPoliza(com.gem.
	 * sistema.business.domain.Poliza)
	 */
	@Override
	public Poliza copyPoliza(Poliza poliza, Poliza polizaDestino, String userId, int idSector) {
		// IN i_tipo VARCHAR(1),
		// IN i_mes INTEGER,
		// IN i_numero INTEGER,
		// IN i_new_tipo VARCHAR(1),
		// IN i_new_mes INTEGER,
		// IN i_numero_new INTEGER,
		// IN i_id_usuario VARCHAR(15),
		// IN i_id_sector INTEGER,
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_tipo", poliza.getTippol())
				.addValue("i_mes", poliza.getMespol()).addValue("i_numero", poliza.getConpol())
				.addValue("i_new_tipo", polizaDestino.getTippol()).addValue("i_new_mes", polizaDestino.getMespol())
				.addValue("i_numero_new", polizaDestino.getConpol()).addValue("i_id_usuario", userId)
				.addValue("i_id_sector", idSector);
		Map<String, Object> out = jdbcCall.execute(in);

		// OUT o_cod_error INTEGER,
		// OUT o_msj_error VARCHAR(1000),
		// OUT o_new_num VARCHAR(4)
		polizaDestino.setErrorCode(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
		polizaDestino.setMsgError(MapUtils.getString(out, "O_MSJ_ERROR"));
		polizaDestino.setConpol(MapUtils.getInteger(out, "O_NEW_NUM"));
		return polizaDestino;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.PolizaJDBCTemplateDAO#deletePoliza(com.gem.sistema.business.domain.Polien, int, java.lang.String)
	 */
	@Override
	public Poliza deletePoliza(Polien polien, int idSector, String idUser) {
		Calendar now = Calendar.getInstance();
		// int year = now.get(Calendar.YEAR);
		int year = conctbRepository.findByIdsector(idSector).getAnoemp();
		Date dateInit = now.getTime();
		try {
			SqlParameterSource in = new MapSqlParameterSource().addValue("i_anopol", year)
					.addValue("i_tipo", polien.getTippol()).addValue("i_mes", polien.getMespol())
					.addValue("i_numero", polien.getConpol()).addValue("i_id_sector", idSector)
					.addValue("i_id_user", idUser);
			Map<String, Object> out = jdbcCallDelete.execute(in);

			Poliza polizaDestino = new Poliza();
			polizaDestino.setErrorCode(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
			polizaDestino.setMsgError(MapUtils.getString(out, "O_MSJ_ERROR"));

			return polizaDestino;

		} catch (Exception e) {

			LOGGER.error("Error al realizar el llamado:: SP_DELETE_POLIZA", e);

		}
		Date dateEnd = now.getTime();
		LOGGER.info("");
		LOGGER.info("");
		LOGGER.info(
				"Params used : i_anopol = {}, i_tipo = {}, i_mes = {}, i_numero = {}, i_id_sector = {} , i_id_user = {},  : Time elapsed = {} ms ",
				year, polien.getTippol(), polien.getMespol(), polien.getConpol(), idSector, idUser,
				(dateEnd.getTime() - dateInit.getTime()));
		LOGGER.info("");
		LOGGER.info("");
		return null;

	}

	/**
	 * Gets the jdbc template.
	 *
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * Sets the jdbc template.
	 *
	 * @param jdbcTemplate            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Gets the data source.
	 *
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Sets the data source.
	 *
	 * @param dataSource            the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.PolizaJDBCTemplateDAO#inversePoliza(com.gem.sistema.business.domain.Poliza, com.gem.sistema.business.domain.Poliza, java.lang.String, int)
	 */
	@Override
	public Poliza inversePoliza(Poliza poliza, Poliza polizaDest, String userId, int idSector) {
		// IN i_tipo VARCHAR(1),
		// IN i_mes INTEGER,
		// IN i_numero INTEGER,
		// IN i_new_tipo VARCHAR(1),
		// IN i_new_mes INTEGER,
		// IN i_numero_new INTEGER,
		// IN i_id_usuario VARCHAR(15),
		// IN i_id_sector INTEGER,
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_tipo", poliza.getTippol())
				.addValue("i_mes", poliza.getMespol()).addValue("i_numero", poliza.getConpol())
				.addValue("i_new_tipo", polizaDest.getTippol()).addValue("i_new_mes", polizaDest.getMespol())
				.addValue("i_numero_new", polizaDest.getConpol()).addValue("i_id_usuario", userId)
				.addValue("i_id_sector", idSector);
		Map<String, Object> out = jdbcCallInverse.execute(in);

		// OUT o_cod_error INTEGER,
		// OUT o_msj_error VARCHAR(1000),
		// OUT o_new_numero INTEGER
		poliza.setErrorCode(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
		poliza.setMsgError(MapUtils.getString(out, "O_MSJ_ERROR"));
		poliza.setConpol(MapUtils.getInteger(out, "O_NEW_NUMERO"));
		return poliza;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.PolizaJDBCTemplateDAO#negativePoliza(com.gem.sistema.business.domain.Poliza, com.gem.sistema.business.domain.Poliza, java.lang.String, int)
	 */
	@Override
	public Poliza negativePoliza(Poliza poliza, Poliza polizaDest, String userId, int idSector) {
		// IN i_tipo VARCHAR(1),
		// IN i_mes INTEGER,
		// IN i_numero INTEGER,
		// IN i_new_tipo VARCHAR(1),
		// IN i_new_mes INTEGER,
		// IN i_numero_new INTEGER,
		// IN i_id_usuario VARCHAR(15),
		// IN i_id_sector INTEGER,
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_tipo", poliza.getTippol())
				.addValue("i_mes", poliza.getMespol()).addValue("i_numero", poliza.getConpol())
				.addValue("i_new_tipo", polizaDest.getTippol()).addValue("i_new_mes", polizaDest.getMespol())
				.addValue("i_numero_new", polizaDest.getConpol()).addValue("i_id_usuario", userId)
				.addValue("i_id_sector", idSector);
		Map<String, Object> out = jdbcCallNegative.execute(in);

		// OUT o_cod_error INTEGER,
		// OUT o_msj_error VARCHAR(1000),
		// OUT o_new_numero VARCHAR(4))
		poliza.setErrorCode(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
		poliza.setMsgError(MapUtils.getString(out, "O_MSJ_ERROR"));
		poliza.setConpol(MapUtils.getInteger(out, "O_NEW_NUMERO"));
		return poliza;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.PolizaJDBCTemplateDAO#cierreMensual(java.lang.Integer, java.lang.String, int)
	 */
	@Override
	public Poliza cierreMensual(Integer mes, String user, int idSector) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_mes", mes).addValue("i_id_sector", idSector)
				.addValue("i_name_user", user);
		Map<String, Object> out = jdbcCallAfectacion.execute(in);
		Poliza poliza = new Poliza();
		poliza.setErrorCode(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
		poliza.setMsgError(MapUtils.getString(out, "O_MSJ_ERROR"));
		poliza.setFileName(MapUtils.getString(out, "O_FILE_NAME"));
		poliza.setPathName(MapUtils.getString(out, "O_PATH_FILE"));

		return poliza;

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.PolizaJDBCTemplateDAO#desfaPoliza(java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Poliza desfaPoliza(Integer mes, String tipo, Integer numero, Integer idSector) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_mes", mes).addValue("i_tipo", tipo)
				.addValue("i_numero", numero).addValue("i_id_sector", idSector);

		Map<String, Object> out = jdbcCallDesafectaPoliza.execute(in);
		Poliza poliza = new Poliza();
		poliza.setErrorCode(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
		poliza.setMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		return poliza;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.PolizaJDBCTemplateDAO#desafectacion(java.lang.Integer, int)
	 */
	@Override
	public Poliza desafectacion(Integer mes, int idSector) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_mes", mes).addValue("i_id_sector", idSector);

		Map<String, Object> out = jdbcCallDesafectacion.execute(in);
		Poliza poliza = new Poliza();
		poliza.setErrorCode(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
		poliza.setMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		return poliza;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.PolizaJDBCTemplateDAO#afectacionPreCierre(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public Poliza afectacionPreCierre(Integer mes, Integer idSector, String idUser) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_mes", mes).addValue("i_id_sector", idSector)
				.addValue("i_name_user", idUser);

		Map<String, Object> out = jdbcCallAfectacionPrecierre.execute(in);
		Poliza poliza = new Poliza();
		poliza.setErrorCode(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
		poliza.setMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		return poliza;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.PolizaJDBCTemplateDAO#desafectacionPreciere(java.lang.Integer, int)
	 */
	@Override
	public Poliza desafectacionPreciere(Integer mes, int idSector) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_mes", mes).addValue("i_id_sector", idSector);

		Map<String, Object> out = jdbcCallDesafectacionPrecierre.execute(in);
		Poliza poliza = new Poliza();
		poliza.setErrorCode(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
		poliza.setMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		return poliza;
	}

}
