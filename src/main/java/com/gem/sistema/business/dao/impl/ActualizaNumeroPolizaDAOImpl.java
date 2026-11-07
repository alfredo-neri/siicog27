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

import com.gem.sistema.business.dao.ActualizaNumeroPolizaDAO;
import com.gem.sistema.business.domain.Copome;
import com.gem.sistema.business.domain.Polien;

// TODO: Auto-generated Javadoc
/**
 * The Class ActualizaNumeroPolizaDAOImpl.
 */
@Repository("actualizaNumeroPolizaDAO")
public class ActualizaNumeroPolizaDAOImpl implements ActualizaNumeroPolizaDAO {
	
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
		this.jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_ACTUALIZA_NUM_POLIZA");
		this.jdbcCall2 = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SP_GET_NUM_POLIZA");
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
	 * @see com.gem.sistema.business.dao.ActualizaNumeroPolizaDAO#actualizaNumero(com.gem.sistema.business.domain.Polien, java.lang.Integer, java.lang.String)
	 */
	@Override
	public Copome actualizaNumero(Polien polien, Integer idSector, String user) {

		// IN i_mes INTEGER ,
		// IN i_tipo CHAR ,
		// IN i_numero INTEGER ,
		// IN i_id_sector INTEGER ,
		// IN i_user VARCHAR(200),
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_mes", polien.getMespol())
				.addValue("i_tipo", polien.getTippol()).addValue("i_numero", polien.getConpol())
				.addValue("i_id_sector", idSector).addValue("i_user", user);

		Map<String, Object> out = jdbcCall.execute(in);
		Copome copome = new Copome();
		copome.setCodError(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
		copome.setMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		copome.setNumNex(MapUtils.getIntValue(out, "O_NUM_NEXT"));
		return copome;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.ActualizaNumeroPolizaDAO#actualizaNumero2(com.gem.sistema.business.domain.Polien, java.lang.Integer, java.lang.String)
	 */
	@Override
	public Copome actualizaNumero2(Polien polien, Integer idSector, String user) {
		// IN i_mes INTEGER ,
		// IN i_tipo CHAR ,
		// IN i_numero INTEGER ,
		// IN i_id_sector INTEGER ,
		// IN i_user VARCHAR(200),
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_mes", polien.getMespol())
				.addValue("i_tipo", polien.getTippol()).addValue("i_numero", polien.getConpol())
				.addValue("i_id_sector", idSector).addValue("i_user", user);

		Map<String, Object> out = jdbcCall2.execute(in);
		Copome copome = new Copome();
		copome.setCodError(MapUtils.getIntValue(out, "O_COD_ERROR", 1));
		copome.setMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		copome.setNumNex(MapUtils.getIntValue(out, "O_NUM_NEXT"));
		return copome;
	}

}
