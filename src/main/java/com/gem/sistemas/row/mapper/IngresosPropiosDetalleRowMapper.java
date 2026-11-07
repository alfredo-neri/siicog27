package com.gem.sistemas.row.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gem.sistema.business.dto.IngresosPropiosDetalleDTO;

public class IngresosPropiosDetalleRowMapper implements RowMapper<IngresosPropiosDetalleDTO> {

	@Override
	public IngresosPropiosDetalleDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		IngresosPropiosDetalleDTO ingreso = new IngresosPropiosDetalleDTO();
		ingreso.setClave(rs.getString("CLAVE"));
		ingreso.setNombre(rs.getString("NOMBRE"));
		ingreso.setMonto(new BigDecimal(rs.getString("MONTO")));
		return ingreso;
	}

}
