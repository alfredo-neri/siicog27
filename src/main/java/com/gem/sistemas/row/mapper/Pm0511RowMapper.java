package com.gem.sistemas.row.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gem.sistema.business.domain.Pm0511;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm0511RowMapper.
 */
public class Pm0511RowMapper implements RowMapper<Pm0511> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public Pm0511 mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pm0511 pm0511 = new Pm0511();
		pm0511.setId(rs.getLong("ID"));
		pm0511.setTrimestre(rs.getInt("TRIMESTRE"));
		pm0511.setConse(rs.getString("CONSE"));
		pm0511.setEmergencia(rs.getString("EMERGENCIA"));
		pm0511.setObservaciones(rs.getString("OBSERVACIONES"));
		pm0511.setCapturo(rs.getString("CAPTURO"));
		pm0511.setFeccap(rs.getDate("FECCAP"));
		pm0511.setTiempo(rs.getInt("TIEMPO"));
		pm0511.setConta(rs.getInt("CONTA"));
		pm0511.setUserid(rs.getString("USERID"));
		pm0511.setIdsector(rs.getInt("IDSECTOR"));
		pm0511.setIdRef(rs.getLong("ID_REF"));
		return pm0511;
	}

}
