package com.gem.sistema.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.FlujosIncorrectosDAO;
import com.gem.sistema.business.domain.Polifl;
import com.gem.sistema.business.repository.catalogs.PoliflRepository;
import com.gem.sistema.business.vo.FlujosIncorrectosVO;

@Repository
public class FlujosIncorrectosDAOImpl implements FlujosIncorrectosDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PoliflRepository poliflRepository;

	@Override
	public List<FlujosIncorrectosVO> findByMonth(Integer month) {

		StringBuilder sSql = new StringBuilder();

		sSql.append("SELECT PE.TIPPOL,\r\n").append("			       PE.MESPOL,\r\n")
				.append("			       PE.CONPOL,\r\n").append("			       PE.RENPOL,\r\n")
				.append("		           ABS(DECODE(PE.CANPOL, 0, PE.CANPOLH, PE.CANPOL)) CA,\r\n")
				.append("			       ABS(SUM(PF.CANFLU)) CANFLU\r\n")
				.append("			    FROM POLIDE PE,\r\n").append("			         POLIFL PF\r\n")
				.append("			WHERE PE.TIPPOL   = PF.TIPPOL\r\n")
				.append("			  AND PE.MESPOL   = PF.MESPOL\r\n")
				.append("			  AND PE.CONPOL   = PF.CONPOL\r\n")
				.append("			  AND PE.RENPOL   = PF.RENPOL\r\n")
				.append("			  AND PE.IDSECTOR = PF.IDSECTOR\r\n").append("			  AND PE.MESPOL   = ?\r\n")
				.append("             AND SUBSTR(CUENTA, 1,2) <> '11'  ")
				.append("			  AND PE.TIPPOL   IN ( 'E', 'C', 'D', 'I')\r\n")
				.append("			GROUP BY PE.TIPPOL,\r\n").append("			       	 PE.MESPOL,\r\n")
				.append("				     PE.CONPOL,\r\n").append("				     PE.RENPOL,\r\n")
				.append("				     DECODE(PE.CANPOL, 0, PE.CANPOLH, PE.CANPOL)\r\n");
		List<FlujosIncorrectosVO> listFluhos = jdbcTemplate.query(sSql.toString(), new Object[] { month },
				new RowMapper<FlujosIncorrectosVO>() {

					@Override
					public FlujosIncorrectosVO mapRow(ResultSet rs, int rowNum) throws SQLException {
						FlujosIncorrectosVO flujoWrong = new FlujosIncorrectosVO();
						flujoWrong.setTippo(rs.getString("TIPPOL"));
						flujoWrong.setMespol(rs.getInt("MESPOL"));
						flujoWrong.setConpol(rs.getInt("CONPOL"));
						flujoWrong.setRenpol(rs.getBigDecimal("RENPOL"));
						flujoWrong.setCa(rs.getBigDecimal("CA"));
						flujoWrong.setCanflu(rs.getBigDecimal("CANFLU"));
						return flujoWrong;
					}
				});
		return listFluhos;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public FlujosIncorrectosVO findByCount(FlujosIncorrectosVO flujo) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT ID, USERID, CUENTA, SCTA, SSCTA,").append(" SSSCTA, SSSSCTA, TIPPOL, MESPOL,")
				.append(" CONPOL, RENPOL, DECODE(CANPOL, 0, CANPOLH, CANPOL) CA ")
				.append(" FROM POLIDE PD WHERE PD.MESPOL = ? AND PD.TIPPOL = ? ")
				.append(" AND PD.CONPOL = ? AND PD.RENPOL = ?");
		FlujosIncorrectosVO flujosIncorrectos = this.jdbcTemplate.queryForObject(sSql.toString(),
				new Object[] { flujo.getMespol(), flujo.getTippo(), flujo.getConpol(), flujo.getRenpol() },
				new RowMapper<FlujosIncorrectosVO>() {

					@Override
					public FlujosIncorrectosVO mapRow(ResultSet rs, int rowNum) throws SQLException {
						FlujosIncorrectosVO flujo = new FlujosIncorrectosVO();
						flujo.setId(rs.getLong("ID"));
						flujo.setUsuario(rs.getString("USERID"));
						flujo.setCuenta(rs.getString("CUENTA"));
						flujo.setScta(rs.getString("SCTA"));
						flujo.setSscta(rs.getString("SSCTA"));
						flujo.setSsscta(rs.getString("SSSCTA"));
						flujo.setSssscta(rs.getString("SSSSCTA"));
						flujo.setTippo(rs.getString("TIPPOL"));
						flujo.setMespol(rs.getInt("MESPOL"));
						flujo.setConpol(rs.getInt("CONPOL"));
						flujo.setRenpol(rs.getBigDecimal("RENPOL"));
						flujo.setCa(rs.getBigDecimal("CA"));
						return flujo;
					}
				});
		return flujosIncorrectos;
	}

	@Override
	public String deleteFlujo(FlujosIncorrectosVO deleteFolujo) {
		FlujosIncorrectosVO flujo = this.findByCount(deleteFolujo);
		if (!flujo.getCuenta().substring(0, 2).equals("11")) {
			List<Polifl> listFlujo = this.poliflRepository.findByMespolAndTippolAndConpolAndRenpol(
					deleteFolujo.getMespol(), deleteFolujo.getTippo(), deleteFolujo.getConpol(),
					deleteFolujo.getRenpol());
			listFlujo.stream().forEach((Polifl polifl) -> {
				this.poliflRepository.delete(polifl);
			});
			return "EL Registro se Elimino Correctament";
		} else
			return "La Cuenta no se Puede Eliminar, porque es una cuenta de Bancsos";

	}

	public PoliflRepository getPoliflRepository() {
		return poliflRepository;
	}

	public void setPoliflRepository(PoliflRepository poliflRepository) {
		this.poliflRepository = poliflRepository;
	}

}
