package com.gem.sistema.business.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.Pm5611DAO;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm5611DAOImpl.
 */
@Repository(value = "pm5611DAOImpl")
public class Pm5611DAOImpl implements Pm5611DAO{
	
	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;

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
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.Pm5611DAO#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer mensual) {
		jdbcTemplate.execute("delete from Pm5611 where mensual = " + mensual);
		
	}
	

}
