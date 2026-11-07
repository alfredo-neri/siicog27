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

import com.gem.sistema.business.dao.TabuladorSueldosDAO;
import com.gem.sistema.business.domain.Tsueldo;

// TODO: Auto-generated Javadoc
/**
 * The Class TabuladorSueldosDAOImpl.
 */
@Repository("tabuladorSueldosDAO")
public class TabuladorSueldosDAOImpl implements TabuladorSueldosDAO {

	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;
	
	/** The data source. */
	private DataSource dataSource;

	/** The jdbc call tabulador. */
	private SimpleJdbcCall jdbcCallTabulador;

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
	 * @param dataSource            the dataSource to set
	 */
	@Autowired
	@Qualifier(value = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.jdbcCallTabulador = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_TABULADOR_SUELDOS");

	}

	/**
	 * Gets the jdbc call tabulador.
	 *
	 * @return the jdbc call tabulador
	 */
	public SimpleJdbcCall getJdbcCallTabulador() {
		return jdbcCallTabulador;
	}

	/**
	 * Sets the jdbc call tabulador.
	 *
	 * @param jdbcCallTabulador the new jdbc call tabulador
	 */
	public void setJdbcCallTabulador(SimpleJdbcCall jdbcCallTabulador) {
		this.jdbcCallTabulador = jdbcCallTabulador;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.TabuladorSueldosDAO#execute(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public Tsueldo execute(String fileName, String pathFile, String idUser, Integer idSectpor) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_file_name", fileName).addValue("i_path_file",
				pathFile).addValue("i_id_user", idUser).addValue("i_id_sector", idSectpor);
		Map<String, Object> out = jdbcCallTabulador.execute(in);
		Tsueldo tsueldo = new Tsueldo();
		tsueldo.setoCodError(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
		tsueldo.setoMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		return tsueldo;
	}

}
