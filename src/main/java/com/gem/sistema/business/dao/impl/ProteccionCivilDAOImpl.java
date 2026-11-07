package com.gem.sistema.business.dao.impl;

import static com.roonin.utils.UtilDate.converStringToDate;
import static com.roonin.utils.UtilDate.getFormatDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import com.gem.sistema.business.dao.ProteccionCivilDAO;
import com.gem.sistema.business.domain.TcValores;
import com.gem.sistema.business.dto.ProteccionCivilDTO;
import com.gem.sistema.business.repository.catalogs.TcValoresRepository;
import com.gem.sistema.business.repository.catalogs.TrEtqTablasRepository;
import com.gem.sistema.business.utils.GetValuesClassUtils;

@Repository
public class ProteccionCivilDAOImpl implements ProteccionCivilDAO {

	private static final Log LOGGER = LogFactory.getLog(Pm4911DAOImpl.class);

	private static final String TABLE_NAME = "PROTECCION";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private TcValoresRepository tcValoresRepository;

	@Autowired
	private TrEtqTablasRepository trEtqTablasRepository;

	TcValores tcValores;
	Integer max;
	Map<String, Object> map;

	@Override
	public List<ProteccionCivilDTO> save(ProteccionCivilDTO ProteccionCivilDTO) {
		String sSql;
		tcValores = new TcValores();
		max = this.tcValoresRepository.findByidTable(TABLE_NAME);

		if (null != max && max > 0L) {
			max = max + 1;
		} else {
			max = 1;
		}

		try {
			map = GetValuesClassUtils.getFieldNamesAndValues(ProteccionCivilDTO, false);
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
				if (!entry.getKey().equals("idRow")) {
					Long idEt = this.trEtqTablasRepository.findByEtiquetaAndTableName(entry.getKey().toUpperCase(),
							TABLE_NAME);

					tcValores.setIdRow(max.intValue());
					tcValores.setValor(entry.getValue().toString());
					tcValores.setIdEtiqTabla(idEt);
					if (entry.getKey().equals("fechaIng")) {
						tcValores.setValor(getFormatDate(entry.getValue().toString()));
					}

					sSql = "INSERT INTO TC_VALORES (VALOR, ID_ROW, ID_ETIQ_TABLA) VALUES (?, ?, ?) ";
					jdbcTemplate.update(sSql,
							new Object[] { tcValores.getValor(), tcValores.getIdRow(), tcValores.getIdEtiqTabla() });
				}

			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.findByTableName(TABLE_NAME);
	}

	@Override
	public List<ProteccionCivilDTO> findByTableName(String tableName) {
		String sSql = "SELECT TV.ID_ROW,\n" + "       MAX(DECODE(TE.NOMBRE, 'SEMESTRE', TV.VALOR, NULL))SEMESTRE,\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'FECHAING', TV.VALOR, NULL))FECHAING,\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'TITULO', TV.VALOR, NULL))TITULO,\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'EXPERIENCIA', TV.VALOR, NULL))EXPERIENCIA,\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'CERTIFICACION', TV.VALOR, NULL))CERTIFICACION,\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'CAPTURO', TV.VALOR, NULL))CAPTURO,\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'CURSO', TV.VALOR, NULL))CURSO,\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'IDSECTOR', TV.VALOR, NULL))IDSECTOR\n"
				+ "     FROM GEMUSER.TC_VALORES TV    ,\n" + "          GEMUSER.TR_ETIQ_TABLAS TR,\n"
				+ "          GEMUSER.TC_ETIQUETAS TE  ,\n" + "          GEMUSER.TC_TABLAS TC\n"
				+ "WHERE TV.ID_ETIQ_TABLA = TR.ID\n" + "  AND TR.ID_ETIQUETA   = TE.ID\n"
				+ "  AND TR.ID_TABLA      = TC.ID\n" + "  AND TE.STATUS        = 1 AND TC.NOMBRE        = '" + tableName
				+ "'  GROUP BY TV.ID_ROW";
		List<ProteccionCivilDTO> listValores = this.jdbcTemplate.query(sSql, new RowMapper<ProteccionCivilDTO>() {

			@Override
			public ProteccionCivilDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProteccionCivilDTO ProteccionCivilDTO = new ProteccionCivilDTO();
				ProteccionCivilDTO.setIdRow(rs.getInt("ID_ROW"));
				ProteccionCivilDTO.setSemestre(rs.getInt("SEMESTRE"));
				ProteccionCivilDTO.setFechaIng(rs.getString("FECHAING"));
				ProteccionCivilDTO.setTitulo(rs.getString("TITULO"));
				ProteccionCivilDTO.setExperiencia(rs.getString("EXPERIENCIA"));
				ProteccionCivilDTO.setCertificacion(rs.getString("CERTIFICACION"));
				ProteccionCivilDTO.setCapturo(rs.getString("CAPTURO"));
				ProteccionCivilDTO.setIdSector(rs.getInt("IDSECTOR"));
				ProteccionCivilDTO.setCurso(rs.getString("CURSO"));
				return ProteccionCivilDTO;
			}

		});
		return listValores;
	}

	@Override
	public List<ProteccionCivilDTO> modify(ProteccionCivilDTO ProteccionCivilDTO) {
		tcValores = new TcValores();
		String sSql;
		Integer idRow = trEtqTablasRepository.getIdRow(ProteccionCivilDTO.getSemestre().toString(), TABLE_NAME);
		try {
			map = GetValuesClassUtils.getFieldNamesAndValues(ProteccionCivilDTO, false);
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
				if (!entry.getKey().equals("idRow")) {
					Long idEt = this.trEtqTablasRepository.findByEtiquetaAndTableName(entry.getKey().toUpperCase(),
							TABLE_NAME);

					tcValores.setValor(entry.getValue().toString());
					tcValores.setIdEtiqTabla(idEt);
					if (entry.getKey().equals("fechaIng")) {
						tcValores.setValor(getFormatDate(entry.getValue().toString()));
					}

					tcValores.setIdRow(idRow);

					sSql = " UPDATE TC_VALORES \n" + "      SET VALOR = '" + tcValores.getValor() + "'\n"
							+ "    WHERE ID_ETIQ_TABLA = " + tcValores.getIdEtiqTabla() + "\n" + "      AND ID_ROW = "
							+ tcValores.getIdRow();
					jdbcTemplate.update(sSql);
				}

			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.findByTableName(TABLE_NAME);
	}

	@Override
	public List<ProteccionCivilDTO> delete(ProteccionCivilDTO proteccionCivilDTO) {
		String sSql;
		tcValores = new TcValores();
		Integer idRow = trEtqTablasRepository.getIdRow(proteccionCivilDTO.getSemestre().toString(), TABLE_NAME);
		try {
			map = GetValuesClassUtils.getFieldNamesAndValues(proteccionCivilDTO, false);
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
				if (!entry.getKey().equals("idRow")) {
					Long idEt = this.trEtqTablasRepository.findByEtiquetaAndTableName(entry.getKey().toUpperCase(),
							TABLE_NAME);
					tcValores.setValor(entry.getValue().toString());
					tcValores.setIdEtiqTabla(idEt);
					if (entry.getKey().equals("fechaIng")) {
						tcValores.setValor(entry.getValue().toString());
					}

					tcValores.setIdRow(idRow);
					sSql = "DELETE FROM GEMUSER.TC_VALORES TV WHERE TV.VALOR ='" + tcValores.getValor()
							+ "' AND TV.ID_ROW = " + tcValores.getIdRow() + " AND TV.ID_ETIQ_TABLA = "
							+ tcValores.getIdEtiqTabla();
					jdbcTemplate.update(sSql);
				}

			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.findByTableName(TABLE_NAME);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public TcValoresRepository getTcValoresRepository() {
		return tcValoresRepository;
	}

	public void setTcValoresRepository(TcValoresRepository tcValoresRepository) {
		this.tcValoresRepository = tcValoresRepository;
	}

	public TrEtqTablasRepository getTrEtqTablasRepository() {
		return trEtqTablasRepository;
	}

	public void setTrEtqTablasRepository(TrEtqTablasRepository trEtqTablasRepository) {
		this.trEtqTablasRepository = trEtqTablasRepository;
	}

}
