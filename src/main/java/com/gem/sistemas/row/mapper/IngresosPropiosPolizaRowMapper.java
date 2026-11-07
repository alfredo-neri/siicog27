package com.gem.sistemas.row.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gem.sistema.business.dto.IngresosPropiosPolizaDTO;

public class IngresosPropiosPolizaRowMapper implements RowMapper<IngresosPropiosPolizaDTO> {
	@Override
	public IngresosPropiosPolizaDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		IngresosPropiosPolizaDTO ingreso = new IngresosPropiosPolizaDTO();
		ingreso.setTippol(rs.getString("TIPPOL"));
		ingreso.setConpol(rs.getInt("CONPOL"));
		ingreso.setMespol(rs.getInt("MESPOL"));
		ingreso.setRenpol(new BigDecimal(rs.getString("RENPOL")));
		ingreso.setCuenta(rs.getString("CUENTA"));
		ingreso.setScta(rs.getString("SCTA"));
		ingreso.setSscta(rs.getString("SSCTA"));
		ingreso.setSsscta(rs.getString("SSSCTA"));
		ingreso.setCanpolh(new BigDecimal(rs.getString("CANPOLH")));
		ingreso.setIngreso(new BigDecimal(rs.getString("INGRESOS")));
		return ingreso;
	}

}
