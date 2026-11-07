package com.gem.sistema.business.service.catalogos;

import static com.roonin.utils.UtilDate.getFormatDate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.TcValores;
import com.gem.sistema.business.dto.CompetenciaLaboralGenericDTO;
import com.gem.sistema.business.repository.catalogs.TcValoresRepository;
import com.gem.sistema.business.repository.catalogs.TrEtqTablasRepository;
import com.gem.sistema.business.utils.GetValuesClassUtils;
import com.gem.sistema.business.vo.CompetenciaLaboralVO;
import com.gem.sistema.util.CreateFileUtils;
import com.gem.sistemas.row.mapper.CompetenciasRowMapper;
import com.google.gson.Gson;

@Service(value = "competenciasLaboralesService")
public class CompetenciasLaboralesServiceImpl implements CompetenciasLaboralesService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private TcValoresRepository tcValoresRepository;

	@Autowired
	private TrEtqTablasRepository trEtqTablasRepository;

	@Override
	public List<CompetenciaLaboralGenericDTO> findAll(String table) {

		String sSql = "SELECT TV.ID_ROW, ET.ID_ETIQUETA, TE.NOMBRE,  TV.VALOR\r\n" + "	FROM TR_ETIQ_TABLAS AS ET \r\n"
				+ "		INNER JOIN TC_ETIQUETAS AS TE ON ET.ID_ETIQUETA = TE.ID\r\n"
				+ "		INNER JOIN TC_TABLAS AS TT ON ET.ID_TABLA = TT.ID\r\n"
				+ "		INNER JOIN TC_VALORES AS TV ON ET.ID = TV.ID_ETIQ_TABLA \r\n" + "WHERE TT.NOMBRE=?\r\n"
				+ "ORDER BY ID_ROW, ID_ETIQUETA";

		List<CompetenciaLaboralVO> list = jdbcTemplate.query(sSql, new Object[] { table }, new CompetenciasRowMapper());

		Gson gson = new Gson();
		StringBuilder json = new StringBuilder();
		Integer rowIndex = 0;

		for (CompetenciaLaboralVO vo : list) {

			if (rowIndex != vo.getIdRow()) {
				if (!json.toString().isEmpty()) {
					json.delete(json.length() - 2, json.length());
				}

				if (rowIndex > 0) {
					json.append("}, {");
				}

				rowIndex = vo.getIdRow();
				json.append((char) 34 + "idrow" + (char) 34 + ": " + vo.getIdRow() + ", ");
			}

			if (!vo.getValor().isEmpty()) {

				json.append((char) 34 + vo.getNombre().toLowerCase() + (char) 34 + ": " + formatObject(vo.getValor())
						+ ", ");

			}

		}

		if (!json.toString().isEmpty()) {
			json.insert(0, (char) 123).insert(0, (char) 91).delete(json.length() - 2, json.length()).append((char) 125)
					.append((char) 93);
		} else {
			json.append("[]");
		}

		List<CompetenciaLaboralGenericDTO> genericDTOs = new ArrayList<>(
				Arrays.asList(gson.fromJson(json.toString(), CompetenciaLaboralGenericDTO[].class)));
		Collections.sort(genericDTOs, new SortBySemestre());
		return genericDTOs;

	}

	@Override
	public Integer countSemestres(String table, Integer semestre) {
		String sSql = "SELECT COUNT(1) \n" + "     FROM GEMUSER.TC_VALORES TV, \n"
				+ "          GEMUSER.TR_ETIQ_TABLAS TR, \n" + "          GEMUSER.TC_ETIQUETAS TE, \n"
				+ "          GEMUSER.TC_TABLAS TT \n" + "WHERE TV.ID_ETIQ_TABLA = TR.ID \n"
				+ "  AND TR.ID_ETIQUETA   = TE.ID \n" + "  AND TR.ID_TABLA      = TT.ID \n"
				+ "  AND TT.NOMBRE        = ? \n" + "  AND TE.NOMBRE        = 'SEMESTRE' \n"
				+ "  AND TV.VALOR         = ?";

		Integer count = jdbcTemplate.queryForObject(sSql, new Object[] { table, semestre }, Integer.class);
		return count;
	}

	@Override
	public void deleteRow(String table, Integer idRow) {
		String sSql = "DELETE TC_VALORES WHERE ID_ROW = ?";

		jdbcTemplate.update(sSql, new Object[] { idRow });
	}

	@Override
	public void save(String table, CompetenciaLaboralGenericDTO dto) {
		TcValores tcValores = new TcValores();
		Integer max = this.tcValoresRepository.getMaxIdRow();

		if (null != max && max > 0L) {
			max = max + 1;
		} else {
			max = 1;
		}
		String sSql;

		try {
			Map<String, Object> map = GetValuesClassUtils.getFieldNamesAndValues(dto, false);
			for (Map.Entry<String, Object> entry : map.entrySet()) {

				Long idEt = this.trEtqTablasRepository.findByEtiquetaAndTableName(entry.getKey().toUpperCase(), table);
				if (null != idEt) {

					tcValores.setValor(entry.getValue().toString());

					if (entry.getKey().equals("fechaing")) {
						tcValores.setValor(getFormatDate(entry.getValue().toString()));
					}
					tcValores.setIdRow(max.intValue());

					tcValores.setIdEtiqTabla(idEt);
					sSql = "INSERT INTO GEMUSER.TC_VALORES  (VALOR,  ID_ROW, ID_ETIQ_TABLA )  VALUES  ('"
							+ tcValores.getValor() + "', " + tcValores.getIdRow() + ", " + tcValores.getIdEtiqTabla()
							+ ")";
					jdbcTemplate.update(sSql);
				}

			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(String table, CompetenciaLaboralGenericDTO genericDTO) {
		String sSql;
		String val;
		Integer idRow = genericDTO.getIdrow();
		try {

			Map<String, Object> map = GetValuesClassUtils.getFieldNamesAndValues(genericDTO, false);
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());

				Long idEt = this.trEtqTablasRepository.findByEtiquetaAndTableName(entry.getKey().toUpperCase(), table);
				System.out.println("id etiq:: " + idEt);
				if (null != idEt) {
					if (!entry.getKey().equals("idEtq")) {
						val = entry.getValue().toString();
						if (entry.getKey().equals("fechaing")) {
							val = getFormatDate(entry.getValue().toString());
						}
						sSql = " UPDATE TC_VALORES \n" + "      SET VALOR = '" + val + "'\n"
								+ "    WHERE ID_ETIQ_TABLA = " + idEt + "\n" + "      AND ID_ROW = " + idRow;
						
						System.out.println(sSql);
						jdbcTemplate.update(sSql);
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void generateJSON(String fileName, String jsonText) {
		CreateFileUtils.createJsonFile(fileName, jsonText);
	}

	public static Object formatObject(Object cadena) {

		Object resultado = null;

		if (cadena instanceof String) {
			resultado = (String) " \"" + cadena + "\"";
		} else if (cadena instanceof Integer) {
			resultado = (Integer) cadena;
		} else if (cadena instanceof Date) {
			resultado = "\"" + (Date) cadena + "\"";
		} else if (cadena instanceof BigDecimal) {
			resultado = new BigDecimal(cadena.toString());
		}

		return resultado;
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

class SortBySemestre implements Comparator<CompetenciaLaboralGenericDTO> {

	@Override
	public int compare(CompetenciaLaboralGenericDTO o1, CompetenciaLaboralGenericDTO o2) {

		return o1.getSemestre() - o2.getSemestre();
	}

}