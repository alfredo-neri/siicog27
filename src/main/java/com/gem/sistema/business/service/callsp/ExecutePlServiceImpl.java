package com.gem.sistema.business.service.callsp;

import java.util.Map;

import javax.sql.DataSource;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class ExecutePlServiceImpl.
 */
@Service(value = "executePlService")
public class ExecutePlServiceImpl implements ExecutePlService {

	/** The data source. */
	private DataSource dataSource;

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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.callsp.ExecutePlService#callProcedure(
	 * java.lang.String,
	 * org.springframework.jdbc.core.namedparam.SqlParameterSource)
	 */
	@Override
	public Map<String, Object> callProcedure(String procedureName, SqlParameterSource in) {
		SimpleJdbcCall jdbcCallSp = new SimpleJdbcCall(dataSource);
		jdbcCallSp.withProcedureName(procedureName);
		Map<String, Object> out = jdbcCallSp.execute(in);
		return out;
	}

	

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.callsp.ExecutePlService#getParameters(org.springframework.jdbc.core.namedparam.SqlParameterSource)
	 */
	public SqlParameterSource getParameters(SqlParameterSource parametersSql) {
		return parametersSql;
	}


}
