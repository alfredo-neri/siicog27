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

import com.gem.sistema.business.dao.DefensorDAO;

import com.gem.sistema.business.domain.TcValores;
import com.gem.sistema.business.dto.DefensorDTO;
import com.gem.sistema.business.repository.catalogs.TcValoresRepository;
import com.gem.sistema.business.repository.catalogs.TrEtqTablasRepository;
import com.gem.sistema.business.utils.GetValuesClassUtils;

@Repository
public class DefensorDAOImpl implements DefensorDAO {

	private static final Log LOGGER = LogFactory.getLog(Pm4911DAOImpl.class);

	private static final String TABLE_NAME = "DEFENSOR";

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
	public List<DefensorDTO> save(DefensorDTO DefensorDTO) {
		String sSql;
		tcValores = new TcValores();
		max = this.tcValoresRepository.findByidTable(TABLE_NAME);

		if (null != max && max > 0L) {
			max = max + 1;
		} else {
			max = 1;
		}

		try {
			map = GetValuesClassUtils.getFieldNamesAndValues(DefensorDTO, false);
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
	public List<DefensorDTO> findByTableName(String tableName) {
		String sSql = "SELECT TV.ID_ROW,\n" + "       MAX(DECODE(TE.NOMBRE, 'SEMESTRE', TV.VALOR, NULL))SEMESTRE,\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'FECHAING', TV.VALOR, NULL))FECHAING,\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'TITULO', TV.VALOR, NULL))TITULO,\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'ESTUDIOS', TV.VALOR, NULL))ESTUDIOS,\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'CAPTURO', TV.VALOR, NULL))CAPTURO,\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'IDSECTOR', TV.VALOR, NULL))IDSECTOR\n"
				+ "     FROM GEMUSER.TC_VALORES TV    ,\n" + "          GEMUSER.TR_ETIQ_TABLAS TR,\n"
				+ "          GEMUSER.TC_ETIQUETAS TE  ,\n" + "          GEMUSER.TC_TABLAS TC\n"
				+ "WHERE TV.ID_ETIQ_TABLA = TR.ID\n" + "  AND TR.ID_ETIQUETA   = TE.ID\n"
				+ "  AND TR.ID_TABLA      = TC.ID\n" + "  AND TE.STATUS        = 1 AND TC.NOMBRE        = '" + tableName
				+ "'  GROUP BY TV.ID_ROW";
		List<DefensorDTO> listValores = this.jdbcTemplate.query(sSql, new RowMapper<DefensorDTO>() {

			@Override
			public DefensorDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				DefensorDTO defensorDTO = new DefensorDTO();
				defensorDTO.setIdRow(rs.getInt("ID_ROW"));
				defensorDTO.setSemestre(rs.getInt("SEMESTRE"));
				defensorDTO.setFechaIng(rs.getString("FECHAING"));
				defensorDTO.setTitulo(rs.getString("TITULO"));
				defensorDTO.setEstudios(rs.getString("ESTUDIOS"));
				defensorDTO.setCapturo(rs.getString("CAPTURO"));

				defensorDTO.setIdSector(rs.getInt("IDSECTOR"));
				return defensorDTO;
			}

		});
		return listValores;
	}

	@Override
	public List<DefensorDTO> modify(DefensorDTO DefensorDTO) {
		tcValores = new TcValores();
		String sSql;
		Integer idRow = trEtqTablasRepository.getIdRow(DefensorDTO.getSemestre().toString(), TABLE_NAME);
		try {
			map = GetValuesClassUtils.getFieldNamesAndValues(DefensorDTO, false);
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
	public List<DefensorDTO> delete(DefensorDTO defensorDTO) {
		String sSql;
		tcValores = new TcValores();
		Integer idRow = trEtqTablasRepository.getIdRow(defensorDTO.getSemestre().toString(), TABLE_NAME);
		try {
			map = GetValuesClassUtils.getFieldNamesAndValues(defensorDTO, false);
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
