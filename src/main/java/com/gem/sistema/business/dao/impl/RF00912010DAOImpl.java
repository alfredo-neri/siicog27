package com.gem.sistema.business.dao.impl;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.RF00912010DAO;
import com.gem.sistema.business.dto.RF00912010DTO;
// TODO: Auto-generated Javadoc

/**
 * The Class RF00912010DAOImpl.
 */
@Repository
public class RF00912010DAOImpl implements RF00912010DAO {

	/** The data source. */
	private DataSource dataSource;

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

	/**
	 * Gets the jdbc call importar.
	 *
	 * @return the jdbc call importar
	 */
	public SimpleJdbcCall getJdbcCallImportar() {
		return jdbcCallImportar;
	}

	/**
	 * Sets the jdbc call importar.
	 *
	 * @param jdbcCallImportar the new jdbc call importar
	 */
	public void setJdbcCallImportar(SimpleJdbcCall jdbcCallImportar) {
		this.jdbcCallImportar = jdbcCallImportar;
	}

	/** The jdbc call importar. */
	private SimpleJdbcCall jdbcCallImportar;

	/**
	 * Inyecta el datasource de la aplicacion.
	 *
	 * @param dataSource the new data source cobis
	 */
	@Autowired
	@Qualifier(value = "dataSource")
	public void setDataSourceCobis(final DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcCallImportar = new SimpleJdbcCall(dataSource).withProcedureName("SP_GENERA_TXT_RF_009_1_20_10");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.dao.RF00912010DAO#executeProcedure(com.gem.sistema.
	 * business.dto.RF00912010DTO)
	 */
	@Override
	public RF00912010DTO executeProcedure(RF00912010DTO parametersDTO) {
		RF00912010DTO parametros = new RF00912010DTO();
//		IN i_idsector INTEGER,
//		IN i_mes	  INTEGER,
//		OUT o_cod_error INTEGER,
		SqlParameterSource in = new MapSqlParameterSource().addValue("i_idsector", parametersDTO.getIdSector())
				.addValue("i_mes", parametersDTO.getiMes()).addValue("i_capitulo", parametersDTO.getiCapitulo()).addValue("i_tipo_report", parametersDTO.getTipoReporte());

		Map<String, Object> out = jdbcCallImportar.execute(in);
		parametros.setoCodError(MapUtils.getInteger(out, "O_COD_ERROR"));
		parametros.setoMsgError(MapUtils.getString(out, "O_MSG_ERROR"));
		parametros.setoNameFile(MapUtils.getString(out, "O_NAME_FILE"));
		parametros.setoPathFile(MapUtils.getString(out, "O_RUTA_FILE"));
		parametros.setQuery(MapUtils.getString(out, "O_QUERY"));
//	  	OUT o_msg_error VARCHAR(9000),
//	 	OUT o_name_file VARCHAR(100),
//	 	OUT o_ruta_file VARCHAR(100)
		return parametros;
	}

}
