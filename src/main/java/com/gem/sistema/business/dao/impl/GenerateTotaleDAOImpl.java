package com.gem.sistema.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.GenerateTotaleDAO;
import com.gem.sistema.business.dto.TotalMesesDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class GenerateTotaleDAOImpl.
 */
@Repository(value = "generateTotaleDAO")
public class GenerateTotaleDAOImpl implements GenerateTotaleDAO {
	
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
	 * @see com.gem.sistema.business.dao.GenerateTotaleDAO#generateTotales(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public TotalMesesDTO generateTotales(Integer idSector, String dependecia, String programa) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT ").append(" SUM(PA.AUTO1_1) AUTO1,  ").append(" SUM(PA.AUTO1_2) AUTO2, ")
				.append(" SUM(PA.AUTO1_3) AUTO3,  ").append(" SUM(PA.AUTO1_4) AUTO4, ")
				.append(" SUM(PA.AUTO1_5) AUTO5, ").append(" SUM(PA.AUTO1_6) AUTO6, ")
				.append(" SUM(PA.AUTO1_7) AUTO7, ").append(" SUM(PA.AUTO1_8) AUTO8, ")
				.append(" SUM(PA.AUTO1_9) AUTO9, ").append(" SUM(PA.AUTO1_10) AUTO10, ")
				.append(" SUM(PA.AUTO1_11) AUTO11, ").append(" SUM(PA.AUTO1_12) AUTO12, ")
				.append(" SUM(PA.AUTO1_13) AUTO13 ").append(" FROM PASO PA ")
				.append(" WHERE PA.IDSECTOR            = ? ").append(" AND PA.CLAVE               = ?").append(" AND SUBSTR(PA.PARTIDA, 2, 3)  = '000'")
				.append(" AND PA.PROGRAMA            = ?");
		return jdbcTemplate.queryForObject(sSql.toString(),
				new Object[] { idSector, dependecia, programa }, new GenerateTotales());
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.GenerateTotaleDAO#generateTotales(java.lang.Integer, java.lang.String)
	 */
	@Override
	public TotalMesesDTO generateTotales(Integer idSector, String programa) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT ").append(" SUM(PA.AUTO1_1) AUTO1,  ").append(" SUM(PA.AUTO1_2) AUTO2, ")
				.append(" SUM(PA.AUTO1_3) AUTO3,  ").append(" SUM(PA.AUTO1_4) AUTO4, ")
				.append(" SUM(PA.AUTO1_5) AUTO5, ").append(" SUM(PA.AUTO1_6) AUTO6, ")
				.append(" SUM(PA.AUTO1_7) AUTO7, ").append(" SUM(PA.AUTO1_8) AUTO8, ")
				.append(" SUM(PA.AUTO1_9) AUTO9, ").append(" SUM(PA.AUTO1_10) AUTO10, ")
				.append(" SUM(PA.AUTO1_11) AUTO11, ").append(" SUM(PA.AUTO1_12) AUTO12, ")
				.append(" SUM(PA.AUTO1_13) AUTO13 ").append(" FROM PASO PA ")
				.append(" WHERE PA.IDSECTOR            = ? ").append(" AND SUBSTR(PA.PROGRAMA, 13, 3) = ?")
				.append(" AND SUBSTR(PA.PARTIDA, 2, 3)  = '000'");
		return jdbcTemplate.queryForObject(sSql.toString(),
				new Object[] { idSector, programa }, new GenerateTotales());
	}

}
class GenerateTotales implements RowMapper<TotalMesesDTO> {

	@Override
	public TotalMesesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		TotalMesesDTO totales = new TotalMesesDTO();
		totales.setEnero(rs.getBigDecimal("AUTO1"));
		totales.setFebrero(rs.getBigDecimal("AUTO2"));
		totales.setMarzo(rs.getBigDecimal("AUTO3"));
		totales.setAbril(rs.getBigDecimal("AUTO4"));
		totales.setMayo(rs.getBigDecimal("AUTO5"));
		totales.setJunio(rs.getBigDecimal("AUTO6"));
		totales.setJulio(rs.getBigDecimal("AUTO7"));
		totales.setAgosto(rs.getBigDecimal("AUTO8"));
		totales.setSeptiembre(rs.getBigDecimal("AUTO9"));
		totales.setOctubre(rs.getBigDecimal("AUTO10"));
		totales.setNoviembre(rs.getBigDecimal("AUTO11"));
		totales.setDiciembre(rs.getBigDecimal("AUTO12"));
		totales.setTotal(rs.getBigDecimal("AUTO13"));
		return totales;
	}

}