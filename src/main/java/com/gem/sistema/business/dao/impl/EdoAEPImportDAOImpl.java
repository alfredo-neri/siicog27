/**
 * 
 */
package com.gem.sistema.business.dao.impl;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.EdoAEPImportDAO;
import com.gem.sistema.business.dto.EdoAEPImportDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class EdoAEPImportDAOImpl.
 *
 * @author Gerardo CUERO
 */
@Repository("edoAEPImportDAO")
public class EdoAEPImportDAOImpl implements EdoAEPImportDAO {

	/** The Constant SP_REPORTE_RF005103. */
	private static final String SP_REPORTE_RF005103 = "SP_REPORTE_RF005103";

	/** The Constant SP_IMPORTAR_INTEGRADOI_RF005_103. */
	private static final String SP_IMPORTAR_INTEGRADOI_RF005_103 = "SP_IMPORTAR_INTEGRADOI_RF005_103";

	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;
	
	/** The data source. */
	private DataSource dataSource;
	
	/** The jdbc call. */
	private SimpleJdbcCall jdbcCall;
	
	/** The jdbc call 2. */
	private SimpleJdbcCall jdbcCall2;

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
	@Qualifier(value = "dataSource")
	public void setDataSourceCobis(final DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(SP_REPORTE_RF005103);
		this.jdbcCall2 = new SimpleJdbcCall(jdbcTemplate).withProcedureName(SP_IMPORTAR_INTEGRADOI_RF005_103);
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

	/**
	 * Instantiates a new edo AEP import DAO impl.
	 */
	public EdoAEPImportDAOImpl() {
		// No args
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.EdoAEPImportDAO#doImport(com.gem.sistema.
	 * business.dto.EdoAEPImportDTO)
	 */
	@Override
	public EdoAEPImportDTO doImport(EdoAEPImportDTO edoAEPImportDTO) {
		// IN i_mes INTEGER ,
		// IN i_organismo INTEGER ,
		// IN i_id_sector INTEGER ,
		// IN i_user_id VARCHAR(20) ,
		// IN i_file_name VARCHAR(50) ,
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_mes", edoAEPImportDTO.getMes())
				.addValue("i_organismo", edoAEPImportDTO.getOrganismo())
				.addValue("i_id_sector", edoAEPImportDTO.getIdSector())
				.addValue("i_user_id", edoAEPImportDTO.getUserid())
				.addValue("i_file_name", edoAEPImportDTO.getFileName());

		Map<String, Object> out = jdbcCall.execute(in);

		edoAEPImportDTO.setCodError(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
		edoAEPImportDTO.setMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		edoAEPImportDTO.setFullFile(MapUtils.getString(out, "O_FULL_FILE"));
		return edoAEPImportDTO;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.EdoAEPImportDAO#doImportIngreso(com.gem.sistema.business.dto.EdoAEPImportDTO)
	 */
	@Override
	public EdoAEPImportDTO doImportIngreso(EdoAEPImportDTO edoAEPImportDTO) {
		// IN i_organismo INTEGER,
		// IN i_id_sector INTEGER,
		// IN imes INTEGER,
		// IN i_id_user VARCHAR(50),
		// IN i_file_name VARCHAR(50),
		SqlParameterSource in = new MapSqlParameterSource().addValue("imes", edoAEPImportDTO.getMes())
				.addValue("i_organismo", edoAEPImportDTO.getOrganismo())
				.addValue("i_id_sector", edoAEPImportDTO.getIdSector())
				.addValue("i_id_user", edoAEPImportDTO.getUserid())
				.addValue("i_file_name", edoAEPImportDTO.getFileName());

		Map<String, Object> out = jdbcCall2.execute(in);

		edoAEPImportDTO.setCodError(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
		edoAEPImportDTO.setMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		edoAEPImportDTO.setFullFile(MapUtils.getString(out, "O_FULL_FILE"));
		return edoAEPImportDTO;
	}

}
