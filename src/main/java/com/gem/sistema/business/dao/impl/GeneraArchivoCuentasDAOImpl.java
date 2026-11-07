package com.gem.sistema.business.dao.impl;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.GeneraArchivoCuentasDAO;
import com.gem.sistema.business.dto.ArchivoCuentas;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneraArchivoCuentasDAOImpl.
 */
@Repository("generaArchivoCuentasDAO")
public class GeneraArchivoCuentasDAOImpl implements GeneraArchivoCuentasDAO {
	
	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;
	
	/** The data source. */
	private DataSource dataSource;
	
	/** The jdbc call. */
	private SimpleJdbcCall jdbcCall;

	/** The Constant SP_EXPORTA_CUENTAS. */
	private static final String SP_EXPORTA_CUENTAS = "SP_EXPORTA_CUENTAS";

	/** The Constant DATASOURCE. */
	private static final String DATASOURCE = "dataSource";

	/**
	 * Sets the data source cobis.
	 *
	 * @param dataSource the new data source cobis
	 */
	/*
	 * Inyecta el datasource de la aplicacion.
	 * 
	 * @param dataSource
	 */
	@Autowired
	@Qualifier(value = GeneraArchivoCuentasDAOImpl.DATASOURCE)
	public void setDataSourceCobis(final DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(SP_EXPORTA_CUENTAS);

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

	/** The Constant I_HEADER. */
	private static final String I_HEADER = "i_header";

	/** The Constant I_QUERY. */
	private static final String I_QUERY = "i_query";

	/** The Constant I_FILE_NAME. */
	private static final String I_FILE_NAME = "i_file_name";

	/** The Constant O_COD_ERROR. */
	private static final String O_COD_ERROR = "O_COD_ERROR";
	
	/** The Constant O_MSG_ERROR. */
	private static final String O_MSG_ERROR = "O_MSG_ERROR";
	
	/** The Constant O_FULL_FILE_PATH. */
	private static final String O_FULL_FILE_PATH = "O_FULL_FILE_PATH";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.GeneraArchivoCuentasDAO#generateFile(java.
	 * lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ArchivoCuentas generateFile(String header, String query, String fileName) {
		// IN i_header VARCHAR(3000),
		// IN i_query VARCHAR(3954),
		// IN i_file_name VARCHAR(300) ,
		SqlParameterSource in = new MapSqlParameterSource().addValue(I_HEADER, StringUtils.EMPTY).addValue(I_QUERY, query)
				.addValue(I_FILE_NAME, fileName);

		Map<String, Object> out = jdbcCall.execute(in);
		// OUT o_cod_error INTEGER ,
		// OUT o_msj_error VARCHAR(300) ,
		// OUT o_full_file_path VARCHAR(500)
		ArchivoCuentas archivoCuentas = new ArchivoCuentas();
		archivoCuentas.setCodError(MapUtils.getIntValue(out, O_COD_ERROR, 1));
		archivoCuentas.setMsgError(MapUtils.getString(out, O_MSG_ERROR));
		archivoCuentas.setFullFilePath(MapUtils.getString(out, O_FULL_FILE_PATH));
		return archivoCuentas;
	}

}
