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

import com.gem.sistema.business.dao.ReconducPresupEgrIngrDAO;
import com.gem.sistema.business.dto.ReconducPresupEgrIngrDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class ReconducPresupEgrIngrDAOImpl.
 *
 * @author Gerardo CUERO
 */
@Repository
public class ReconducPresupEgrIngrDAOImpl implements ReconducPresupEgrIngrDAO {

	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;
	
	/** The data source. */
	private DataSource dataSource;

	/** The jdbc call reconduct. */
	private SimpleJdbcCall jdbcCallReconduct;

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
	@Autowired
	@Qualifier(value = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.jdbcCallReconduct = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_PRESUPUESTO_I_E");

	}

	/**
	 * Gets the jdbc call reconduct.
	 *
	 * @return the jdbcCallReconduct
	 */
	public SimpleJdbcCall getJdbcCallReconduct() {
		return jdbcCallReconduct;
	}

	/**
	 * Sets the jdbc call reconduct.
	 *
	 * @param jdbcCallReconduct            the jdbcCallReconduct to set
	 */
	public void setJdbcCallReconduct(SimpleJdbcCall jdbcCallReconduct) {
		this.jdbcCallReconduct = jdbcCallReconduct;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.ReconducPresupEgrIngrServiceDAO#getByMonthUser(java.lang.Integer, java.lang.String)
	 */
	@Override
	public ReconducPresupEgrIngrDTO getByMonthUser(Integer month, String user) {
		// // TODO Auto-generated method stub
		// IN i_mes INTEGER,
		// IN i_usuario VARCHAR(100),
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_mes", month).addValue("i_usuario", user);
		Map<String, Object> out = jdbcCallReconduct.execute(in);
		// OUT o_cod_error INTEGER,
		// OUT o_msg_error VARCHAR(500),
		// OUT o_ruta_file_e VARCHAR(1000),
		// OUT o_ruta_file_i VARCHAR(1000),
		// OUT o_name_file_e VARCHAR(100),
		// OUT o_name_file_i VARCHAR(100)

		return new ReconducPresupEgrIngrDTO(MapUtils.getIntValue(out, "O_COD_ERROR", 1),
				MapUtils.getString(out, "O_MSG_ERROR"), MapUtils.getString(out, "O_RUTA_FILE_I"), 
				MapUtils.getString(out, "O_NAME_FILE_I"), MapUtils.getString(out, "O_RUTA_FILE_E"),
				MapUtils.getString(out, "O_NAME_FILE_E"));
	}

}
