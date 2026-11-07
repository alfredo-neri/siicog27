package com.gem.sistemas.row.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gem.sistema.business.vo.CompetenciaLaboralVO;

public class CompetenciasRowMapper implements RowMapper<CompetenciaLaboralVO> {

	@Override
	public CompetenciaLaboralVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		CompetenciaLaboralVO competenciaLaboralVO = new CompetenciaLaboralVO();
		
		competenciaLaboralVO.setIdRow(rs.getInt("ID_ROW"));
		competenciaLaboralVO.setIdEtiqueta(rs.getInt("ID_ETIQUETA"));
		competenciaLaboralVO.setNombre(rs.getString("NOMBRE"));
		competenciaLaboralVO.setValor(rs.getString("VALOR"));
		
		return competenciaLaboralVO;
	}

}
