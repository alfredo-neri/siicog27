package com.gem.sistema.business.dao.impl;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.CallSpDAO;



// TODO: Auto-generated Javadoc
/**
 * The Class CallSpDAOImpl.
 */
@Repository
public class CallSpDAOImpl implements CallSpDAO {

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


	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.CallSpDAO#call(java.lang.String, org.springframework.jdbc.core.namedparam.SqlParameterSource)
	 */
	@Override
	public Map<String, Object> call(String plName,  SqlParameterSource in) {
		SimpleJdbcCall jdbcCallSp = new SimpleJdbcCall(dataSource);
		jdbcCallSp.withProcedureName(plName);
		Map<String, Object> out = jdbcCallSp.execute(in);
		return out;
	}

}
