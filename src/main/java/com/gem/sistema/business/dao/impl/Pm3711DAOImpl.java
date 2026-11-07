package com.gem.sistema.business.dao.impl;

import static com.gem.sistema.business.utils.GetValuesClassUtils.getFieldNamesAndValues;
import static com.roonin.utils.UtilDate.formatDate;
import static com.roonin.utils.UtilDate.getFormatDate;
import static com.roonin.utils.UtilDate.converStringToDate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.Pm3711DAO;
import com.gem.sistema.business.domain.TcValores;
import com.gem.sistema.business.domain.TrEtiqTabla;
import com.gem.sistema.business.dto.Pm3711DTO;
import com.gem.sistema.business.dto.TcValoresDTO;
import com.gem.sistema.business.predicates.TcValoresPredicate;
import com.gem.sistema.business.predicates.TrEtiTablaPredicate;
import com.gem.sistema.business.repository.catalogs.TcValoresRepository;
import com.gem.sistema.business.repository.catalogs.TrEtqTablasRepository;
import com.gem.sistema.business.utils.GetValuesClassUtils;

@Repository(value = "pm3711DAO")
public class Pm3711DAOImpl implements Pm3711DAO {
	private static final String NAME_PROCEDURE = "SP_INSERTA_VALORES";

	TcValores tcValores = null;
	List<TcValores> liValo = null;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private TcValoresRepository tcValoresRepository;

	@Autowired
	private TrEtqTablasRepository trEtqTablasRepository;

	@Override
	public List<Pm3711DTO> findAll() {

		String sSql = "SELECT  TV.ID_ROW,\n"
				+ "				MAX(DECODE(TE.NOMBRE, 'SEMESTRE', TV.VALOR, 1)) SEMESTRE,\n"
				+ "				MAX(DECODE(TE.NOMBRE, 'IDREF', TV.VALOR, 0)) ID_REF,\n"
				+ "				MAX(DECODE(TE.NOMBRE, 'IDSECTOR', TV.VALOR, 1)) ID_SECTOR,\n"
				+ "				MAX(DECODE(TE.NOMBRE, 'IDANIO', TV.VALOR, 0)) ID_ANIO,\n"
				+ "				MAX(DECODE(TE.NOMBRE, 'CERTIFICACION', TV.VALOR, 0)) CERTIFICACION,\n"
				+ "				MAX(DECODE(TE.NOMBRE, 'EXPERIENCIA', TV.VALOR, 0)) EXPERIENCIA,\n"
				+ "				MAX(DECODE(TE.NOMBRE, 'TITULO', TV.VALOR,0)) TITULO,\n"
				+ "				MAX(DECODE(TE.NOMBRE, 'FECHAING', TV.VALOR,'')) FECHAING,\n"
				+ "				MAX(DECODE(TE.NOMBRE, 'CAPTURO', TV.VALOR,'')) CAPTURO"
				+ "				FROM TR_ETIQ_TABLAS AS ET \n"
				+ "					INNER JOIN TC_ETIQUETAS AS TE ON ET.ID_ETIQUETA = TE.ID\n"
				+ "					INNER JOIN TC_TABLAS AS TT ON ET.ID_TABLA = TT.ID\n"
				+ "					INNER JOIN TC_VALORES AS TV ON ET.ID = TV.ID_ETIQ_TABLA WHERE 1=1\n"
				+ "				AND ET.STATUS = 1 AND TT.ID = 1 GROUP BY TV.ID_ROW";

		List<Pm3711DTO> lista = this.jdbcTemplate.query(sSql, new RowMapper<Pm3711DTO>() {

			@Override
			public Pm3711DTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				Pm3711DTO pm3711 = new Pm3711DTO();

				pm3711.setSemestre(Integer.valueOf(rs.getString("SEMESTRE")));
				pm3711.setIdRef(Long.valueOf(rs.getString("ID_REF")));
				pm3711.setIdSector(Integer.valueOf(rs.getInt("ID_SECTOR")));
				pm3711.setIdAnio(Long.valueOf(rs.getString("ID_ANIO")));
				pm3711.setCertificacion(Integer.valueOf(rs.getString("CERTIFICACION")));
				pm3711.setExperiencia(Integer.valueOf(rs.getString("EXPERIENCIA")));
				pm3711.setTitulo(Integer.valueOf(rs.getString("TITULO")));
				pm3711.setFechaIng(rs.getString("FECHAING"));
				pm3711.setCapturo(rs.getString("CAPTURO"));

				return pm3711;
			}

		});
		return lista;
	}

	@Override
	public List<Pm3711DTO> delete(Pm3711DTO pm3711dto) {

		String sSql;
		Integer idRow = trEtqTablasRepository.getIdRow(pm3711dto.getSemestre().toString());
		try {
			map = GetValuesClassUtils.getFieldNamesAndValues(pm3711dto, false);
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());

				Long idEt = this.trEtqTablasRepository.findByEtiqueta(entry.getKey().toUpperCase(), 1);

				if (!entry.getKey().equals("idEtq")) {

					String val = entry.getValue().toString();
					if (entry.getKey().equals("fechaIng")) {

						val = entry.getValue().toString();
					}

					sSql = "DELETE FROM GEMUSER.TC_VALORES TV WHERE TV.VALOR ='" + val + "' AND TV.ID_ROW = " + idRow
							+ " AND TV.ID_ETIQ_TABLA = " + idEt;
					jdbcTemplate.update(sSql);
				}

			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.findAll();

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

	Map<String, Object> map = null;
	Integer max = 0;
	TrEtiqTabla trEtiqTabla = null;

	@Override
	public List<Pm3711DTO> save(Pm3711DTO pm3711dto) {
		tcValores = new TcValores();
		max = this.tcValoresRepository.findByidTable(1);

		if (null != max && max > 0L) {
			max = max + 1;
		} else {
			max = 1;
		}
		String sSql;
		try {
			map = GetValuesClassUtils.getFieldNamesAndValues(pm3711dto, false);
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());

				Long idEt = this.trEtqTablasRepository.findByEtiqueta(entry.getKey().toUpperCase(), 1);
				System.out.println("ID ETIQUETA : " + idEt);
				if (!entry.getKey().equals("idEtq")) {

					tcValores.setValor(entry.getValue().toString());

					if (entry.getKey().equals("fechaIng")) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.findAll();
	}

	public TrEtqTablasRepository getTrEtqTablasRepository() {
		return trEtqTablasRepository;
	}

	public void setTrEtqTablasRepository(TrEtqTablasRepository trEtqTablasRepository) {
		this.trEtqTablasRepository = trEtqTablasRepository;
	}

	@Override
	public List<Pm3711DTO> modificar(Pm3711DTO pm3711dto) {
		String sSql;
		String val;
		Integer idRow = trEtqTablasRepository.getIdRow(pm3711dto.getSemestre().toString());
		try {
			map = GetValuesClassUtils.getFieldNamesAndValues(pm3711dto, false);
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());

				Long idEt = this.trEtqTablasRepository.findByEtiqueta(entry.getKey().toUpperCase(), 1);
				System.out.println("id etiq:: " + idEt);
				System.out.println("id etiq:: " + idEt);
				if (!entry.getKey().equals("idEtq")) {
					val = entry.getValue().toString();
					if (entry.getKey().equals("fechaIng")) {
						val = getFormatDate(entry.getValue().toString());
					}
					sSql = " UPDATE TC_VALORES \n" + "      SET VALOR = '" + val + "'\n" + "    WHERE ID_ETIQ_TABLA = "
							+ idEt + "\n" + "      AND ID_ROW = " + idRow;
					jdbcTemplate.update(sSql);
				}

			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.findAll();

	}

}
