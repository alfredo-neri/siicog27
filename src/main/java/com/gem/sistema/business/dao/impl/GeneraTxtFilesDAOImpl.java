package com.gem.sistema.business.dao.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.GeneraTxtBS;
import com.gem.sistema.business.dao.GeneraTxtFilesDAO;

/**
 * @author Alfredo Neri
 *
 */
@Repository
public class GeneraTxtFilesDAOImpl implements GeneraTxtFilesDAO {

	private static final String PATH_NAME = "/gem/reportes/";
	private static final String SEPARATOR = "\"|\"";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private GeneraTxtBS generaTxt;

	@Override
	public String generaTxtLDFObjGasto(String query, String fileName) {

		Integer varNoEtiquetado = 1;
		Integer varEtiquetado = 2;

		List<Integer> integers = new ArrayList<>();
		List<String> strings = new ArrayList<>();
		List<BigDecimal> aprobados = new ArrayList<>();
		List<BigDecimal> amplRedus = new ArrayList<>();
		List<BigDecimal> modificados = new ArrayList<>();
		List<BigDecimal> devengados = new ArrayList<>();
		List<BigDecimal> pagados = new ArrayList<>();
		List<BigDecimal> subejercicios = new ArrayList<>();

		List<String> rows = new ArrayList<String>();
		rows = this.jdbcTemplate.query(query, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				if (rs.getInt("GRUP") != varNoEtiquetado && integers.isEmpty()) {
					integers.add(rowNum);

					String line = addLineToList(varNoEtiquetado, getTotalOfList(aprobados), getTotalOfList(amplRedus),
							getTotalOfList(modificados), getTotalOfList(devengados), getTotalOfList(pagados),
							getTotalOfList(subejercicios));

					HashMap<Integer, String> mapa = new HashMap<>();

					mapa.put(rowNum, line);
					strings.add(0, line);

					aprobados.clear();
					amplRedus.clear();
					modificados.clear();
					devengados.clear();
					pagados.clear();
					subejercicios.clear();
				}

				if (rs.getString("CLVGAS").substring(1).equals("000")) {
					aprobados.add(rs.getBigDecimal("APROBADO"));
					amplRedus.add(rs.getBigDecimal("AMPL_REDU"));
					modificados.add(rs.getBigDecimal("MODIFICADO"));
					devengados.add(rs.getBigDecimal("DEVENGADO"));
					pagados.add(rs.getBigDecimal("PAGADO"));
					subejercicios.add(rs.getBigDecimal("SUBEJERCICIO"));
				}

				String line = "\"" + rs.getString("CLVGAS") + " " + rs.getString("NOMGAS") + SEPARATOR
						+ rs.getBigDecimal("APROBADO").setScale(2) + SEPARATOR
						+ rs.getBigDecimal("AMPL_REDU").setScale(2) + SEPARATOR
						+ rs.getBigDecimal("MODIFICADO").setScale(2) + SEPARATOR
						+ rs.getBigDecimal("DEVENGADO").setScale(2) + SEPARATOR + rs.getBigDecimal("PAGADO").setScale(2)
						+ SEPARATOR + rs.getBigDecimal("SUBEJERCICIO").setScale(2) + "\"";
				return line;
			}

		});

		if (!CollectionUtils.isEmpty(strings)) {
			rows.add(0, strings.get(0));
			rows.add(integers.get(0) + 1,
					addLineToList(varEtiquetado, getTotalOfList(aprobados), getTotalOfList(amplRedus),
							getTotalOfList(modificados), getTotalOfList(devengados), getTotalOfList(pagados),
							getTotalOfList(subejercicios)));
		} else {
			rows.add(0,
					addLineToList(varNoEtiquetado, getTotalOfList(aprobados), getTotalOfList(amplRedus),
							getTotalOfList(modificados), getTotalOfList(devengados), getTotalOfList(pagados),
							getTotalOfList(subejercicios)));
		}

		return generaTxt.generaArchivo(rows, PATH_NAME + fileName);
	}

	@Override
	public String generaTxtLDFClasifAdministrativa(String query, String fileName) {
		Integer varNoEtiquetado = 1;
		Integer varEtiquetado = 2;

		List<Integer> integers = new ArrayList<>();
		List<String> strings = new ArrayList<>();
		List<BigDecimal> aprobados = new ArrayList<>();
		List<BigDecimal> amplRedus = new ArrayList<>();
		List<BigDecimal> modificados = new ArrayList<>();
		List<BigDecimal> devengados = new ArrayList<>();
		List<BigDecimal> pagados = new ArrayList<>();
		List<BigDecimal> subejercicios = new ArrayList<>();

		List<String> rows = new ArrayList<String>();
		rows = this.jdbcTemplate.query(query, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				if (rs.getInt("GRUP") != varNoEtiquetado && integers.isEmpty()) {
					integers.add(rowNum);

					String line = addLineToList(varNoEtiquetado, getTotalOfList(aprobados), getTotalOfList(amplRedus),
							getTotalOfList(modificados), getTotalOfList(devengados), getTotalOfList(pagados),
							getTotalOfList(subejercicios));

					HashMap<Integer, String> mapa = new HashMap<>();

					mapa.put(rowNum, line);
					strings.add(0, line);

					aprobados.clear();
					amplRedus.clear();
					modificados.clear();
					devengados.clear();
					pagados.clear();
					subejercicios.clear();
				}

				aprobados.add(rs.getBigDecimal("APROBADO"));
				amplRedus.add(rs.getBigDecimal("AMPLI_REDU"));
				modificados.add(rs.getBigDecimal("MODIFICADO"));
				devengados.add(rs.getBigDecimal("DEVENGADO"));
				pagados.add(rs.getBigDecimal("PAGADO"));
				subejercicios.add(rs.getBigDecimal("SUBEJERCICIO"));

				String line = "\"" + rs.getString("CLAVE") + " " + rs.getString("NOMBRE") + SEPARATOR
						+ rs.getBigDecimal("APROBADO").setScale(2) + SEPARATOR
						+ rs.getBigDecimal("AMPLI_REDU").setScale(2) + SEPARATOR
						+ rs.getBigDecimal("MODIFICADO").setScale(2) + SEPARATOR
						+ rs.getBigDecimal("DEVENGADO").setScale(2) + SEPARATOR + rs.getBigDecimal("PAGADO").setScale(2)
						+ SEPARATOR + rs.getBigDecimal("SUBEJERCICIO").setScale(2) + "\"";
				return line;
			}

		});

		if (!CollectionUtils.isEmpty(strings)) {
			rows.add(0, strings.get(0));
			rows.add(integers.get(0) + 1,
					addLineToList(varEtiquetado, getTotalOfList(aprobados), getTotalOfList(amplRedus),
							getTotalOfList(modificados), getTotalOfList(devengados), getTotalOfList(pagados),
							getTotalOfList(subejercicios)));
		} else {
			rows.add(0,
					addLineToList(varNoEtiquetado, getTotalOfList(aprobados), getTotalOfList(amplRedus),
							getTotalOfList(modificados), getTotalOfList(devengados), getTotalOfList(pagados),
							getTotalOfList(subejercicios)));
		}

		return generaTxt.generaArchivo(rows, PATH_NAME + fileName);
	}

	@Override
	public String generaTxtLDFServiciosPersonales(String query, String fileName) {
		Integer varNoEtiquetado = 1;
		Integer varEtiquetado = 2;

		List<Integer> integers = new ArrayList<>();
		List<String> strings = new ArrayList<>();
		List<BigDecimal> aprobados = new ArrayList<>();
		List<BigDecimal> amplRedus = new ArrayList<>();
		List<BigDecimal> modificados = new ArrayList<>();
		List<BigDecimal> devengados = new ArrayList<>();
		List<BigDecimal> pagados = new ArrayList<>();
		List<BigDecimal> subejercicios = new ArrayList<>();

		List<String> rows = new ArrayList<String>();
		rows = this.jdbcTemplate.query(query, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				if (rs.getInt("GRUP") != varNoEtiquetado && integers.isEmpty()) {
					integers.add(rowNum);

					String line = addLineToList(varNoEtiquetado, getTotalOfList(aprobados), getTotalOfList(amplRedus),
							getTotalOfList(modificados), getTotalOfList(devengados), getTotalOfList(pagados),
							getTotalOfList(subejercicios));

					HashMap<Integer, String> mapa = new HashMap<>();

					mapa.put(rowNum, line);
					strings.add(0, line);

					aprobados.clear();
					amplRedus.clear();
					modificados.clear();
					devengados.clear();
					pagados.clear();
					subejercicios.clear();
				}

				if (!rs.getString("CLVGAS").substring(3).equals("0")) {
					aprobados.add(rs.getBigDecimal("APROBADO"));
					amplRedus.add(rs.getBigDecimal("AMPL_REDU"));
					modificados.add(rs.getBigDecimal("MODIFICADO"));
					devengados.add(rs.getBigDecimal("DEVENGADO"));
					pagados.add(rs.getBigDecimal("PAGADO"));
					subejercicios.add(rs.getBigDecimal("SUBEJERCICIO"));
				}

				String line = "\"" + rs.getString("CONCEPTO") + SEPARATOR
						+ rs.getBigDecimal("APROBADO").setScale(2, RoundingMode.HALF_EVEN) + SEPARATOR
						+ rs.getBigDecimal("AMPL_REDU").setScale(2, RoundingMode.HALF_EVEN) + SEPARATOR
						+ rs.getBigDecimal("MODIFICADO").setScale(2, RoundingMode.HALF_EVEN) + SEPARATOR
						+ rs.getBigDecimal("DEVENGADO").setScale(2, RoundingMode.HALF_EVEN) + SEPARATOR
						+ rs.getBigDecimal("PAGADO").setScale(2, RoundingMode.HALF_EVEN) + SEPARATOR
						+ rs.getBigDecimal("SUBEJERCICIO").setScale(2, RoundingMode.HALF_EVEN) + "\"";
				return line;
			}

		});

		if (!CollectionUtils.isEmpty(strings)) {
			rows.add(0, strings.get(0));
			rows.add(integers.get(0) + 1,
					addLineToList(varEtiquetado, getTotalOfList(aprobados), getTotalOfList(amplRedus),
							getTotalOfList(modificados), getTotalOfList(devengados), getTotalOfList(pagados),
							getTotalOfList(subejercicios)));
		} else {
			rows.add(0,
					addLineToList(varNoEtiquetado, getTotalOfList(aprobados), getTotalOfList(amplRedus),
							getTotalOfList(modificados), getTotalOfList(devengados), getTotalOfList(pagados),
							getTotalOfList(subejercicios)));
		}

		return generaTxt.generaArchivo(rows, PATH_NAME + fileName);
	}

	@Override
	public String generaTxtLDFClasifFuncional(String query, String fileName) {
		Integer varNoEtiquetado = 1;
		Integer varEtiquetado = 2;

		List<Integer> integers = new ArrayList<>();
		List<String> strings = new ArrayList<>();
		List<String> listGastos = new ArrayList<>();
		List<BigDecimal> aprobados = new ArrayList<>();
		List<BigDecimal> amplRedus = new ArrayList<>();
		List<BigDecimal> modificados = new ArrayList<>();
		List<BigDecimal> devengados = new ArrayList<>();
		List<BigDecimal> pagados = new ArrayList<>();
		List<BigDecimal> subejercicios = new ArrayList<>();

		listGastos.add("1|1|1|1");

		List<String> rows = new ArrayList<String>();
		rows = this.jdbcTemplate.query(query, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				Integer index = listGastos.size() - 1;
				if (!listGastos.get(index).isEmpty()) {
					String[] collection = listGastos.get(index).split("\\|");
					if (collection.length > 0) {
						if (!(collection[1] + collection[2] == rs.getInt("GRUP") + rs.getString("GRUPO_GASTO"))) {
							listGastos.add(listGastos.size() + "|" + rs.getInt("GRUP") + "|"
									+ rs.getString("GRUPO_GASTO") + "|" + rs.getString("NOMBRE"));
						}
					}
				}

				if (rs.getInt("GRUP") != varNoEtiquetado && integers.isEmpty()) {
					integers.add(rowNum);

					String line = addLineToList(varNoEtiquetado, getTotalOfList(aprobados), getTotalOfList(amplRedus),
							getTotalOfList(modificados), getTotalOfList(devengados), getTotalOfList(pagados),
							getTotalOfList(subejercicios));

					HashMap<Integer, String> mapa = new HashMap<>();

					mapa.put(rowNum, line);
					strings.add(0, line);

					aprobados.clear();
					amplRedus.clear();
					modificados.clear();
					devengados.clear();
					pagados.clear();
					subejercicios.clear();
				}

				aprobados.add(rs.getBigDecimal("AUTO"));
				amplRedus.add(rs.getBigDecimal("AMPLI_REDU"));
				modificados.add(rs.getBigDecimal("MODIFICADO"));
				devengados.add(rs.getBigDecimal("EJXPA"));
				pagados.add(rs.getBigDecimal("EJPA"));
				subejercicios.add(rs.getBigDecimal("SUBEJERCICIO"));

				String line = "\"" + rs.getString("GASTO") + " " + rs.getString("NAME") + SEPARATOR
						+ rs.getBigDecimal("AUTO").setScale(2) + SEPARATOR + rs.getBigDecimal("AMPLI_REDU").setScale(2)
						+ SEPARATOR + rs.getBigDecimal("MODIFICADO").setScale(2) + SEPARATOR
						+ rs.getBigDecimal("EJXPA").setScale(2) + SEPARATOR + rs.getBigDecimal("EJPA").setScale(2)
						+ SEPARATOR + rs.getBigDecimal("SUBEJERCICIO").setScale(2) + "\"";
				return line;
			}

		});

		listGastos.remove(0);
		String last = StringUtils.EMPTY;
		Integer addRowsEtiquetado = 1;
		Integer add = -1;

		for (String string : listGastos) {
			String[] data = string.split("\\|");
			if (!last.equals(data[1] + data[2])) {

				rows.add(Integer.valueOf(data[0]) + add,
						"\"" + data[3] + "\"|" + this.lineSubtotal(query, data[2], Integer.valueOf(data[1])));
				last = data[1] + data[2];
				add++;

				if (Integer.valueOf(data[1]) == varNoEtiquetado)
					addRowsEtiquetado++;
			}
		}

		if (!CollectionUtils.isEmpty(strings)) {
			rows.add(0, strings.get(0));
			rows.add(integers.get(0) + addRowsEtiquetado,
					addLineToList(varEtiquetado, getTotalOfList(aprobados), getTotalOfList(amplRedus),
							getTotalOfList(modificados), getTotalOfList(devengados), getTotalOfList(pagados),
							getTotalOfList(subejercicios)));
		} else {
			rows.add(0,
					addLineToList(varNoEtiquetado, getTotalOfList(aprobados), getTotalOfList(amplRedus),
							getTotalOfList(modificados), getTotalOfList(devengados), getTotalOfList(pagados),
							getTotalOfList(subejercicios)));
		}

		return generaTxt.generaArchivo(rows, PATH_NAME + fileName);
	}

	private String lineSubtotal(String query, String gasto, Integer grupo) {

		String fullQuery = "SELECT SUM(T3.AUTO) AUTO, SUM(T3.AMPLI) AMPLI, SUM(T3.REDU) REDU, SUM(T3.AMPLI_REDU) AMPLI_REDU, "
				+ "SUM(T3.MODIFICADO) MODIFICADO, SUM(T3.EJXPA) EJXPA, SUM(T3.EJPA) EJPA, SUM(T3.SUBEJERCICIO) SUBEJERCICIO FROM ( "
				+ query + ") T3 WHERE T3.GRUPO_GASTO = '" + gasto + "' AND T3.GRUP= " + grupo;

		List<String> rows = new ArrayList<String>();
		rows = this.jdbcTemplate.query(fullQuery, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				String line = "\"" + rs.getBigDecimal("AUTO").setScale(2) + SEPARATOR
						+ rs.getBigDecimal("AMPLI_REDU").setScale(2) + SEPARATOR
						+ rs.getBigDecimal("MODIFICADO").setScale(2) + SEPARATOR + rs.getBigDecimal("EJXPA").setScale(2)
						+ SEPARATOR + rs.getBigDecimal("EJPA").setScale(2) + SEPARATOR
						+ rs.getBigDecimal("SUBEJERCICIO").setScale(2) + "\"";
				return line;
			}

		});

		return rows.get(0);
	}

	private BigDecimal getTotalOfList(List<BigDecimal> values) {

		BigDecimal total = BigDecimal.ZERO;

		for (BigDecimal value : values) {
			total = total.add(value);
		}

		return total;
	}

	private String addLineToList(Integer type, BigDecimal aprobado, BigDecimal amplRedu, BigDecimal modificado,
			BigDecimal devengado, BigDecimal pagado, BigDecimal subejercicio) {
		String line = StringUtils.EMPTY;

		if (type == 1) {
			line = "\"Gasto No Etiquetado" + SEPARATOR + aprobado.setScale(2) + SEPARATOR + amplRedu.setScale(2)
					+ SEPARATOR + modificado.setScale(2) + SEPARATOR + devengado.setScale(2) + SEPARATOR
					+ pagado.setScale(2) + SEPARATOR + subejercicio.setScale(2) + "\"";
		} else {
			line = "\"Gasto Etiquetado" + SEPARATOR + aprobado.setScale(2) + SEPARATOR + amplRedu.setScale(2)
					+ SEPARATOR + modificado.setScale(2) + SEPARATOR + devengado.setScale(2) + SEPARATOR
					+ pagado.setScale(2) + SEPARATOR + subejercicio.setScale(2) + "\"";
		}

		return line;
	}

	@Override
	public String generaReporte1e(String fileName, Integer idSector) {

		String sql = "SELECT MU.CAMPO7, MU.CAMPO6, NVL(MU.CAMPO8, '') CAMPO8, \r\n"
				+ "	   'N'||TN.CLAVE CLAVE, N.OBJETIVO, MIR.CODIGO, \r\n"
				+ "	   N.NOMBRE, N.FORMULA, N.FRECUENCIA, N.TIPO,\r\n" + "	   N.SUPUESTO, N.MEDIOS_VERIFICACION\r\n"
				+ " FROM TC_MATRIZ M\r\n" + "	INNER JOIN TR_MATRIZ_NIVEL MN ON MN.ID_MATRIZ = M.ID\r\n"
				+ "    INNER JOIN TC_NIVEL N ON N.ID = MN.ID_NIVEL\r\n"
				+ "    INNER JOIN TC_TIPO_NIVEL TN ON TN.ID = N.ID_NIVEL\r\n"
				+ "    INNER JOIN MUNINEP MU ON MU.ID = M.ID_PROYECTO\r\n"
				+ "    INNER JOIN MIR ON MIR.CODIGO = N.CODIGO_INDICADOR\r\n" + "WHERE M.ID_SECTOR = " + idSector;

		List<String> rows = new ArrayList<String>();
		rows = this.jdbcTemplate.query(sql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				String line = "\"" + rs.getString("CAMPO7") + SEPARATOR + rs.getString("CAMPO6") + SEPARATOR
						+ rs.getString("CAMPO8") + SEPARATOR + rs.getString("CLAVE") + SEPARATOR
						+ rs.getString("OBJETIVO") + SEPARATOR + rs.getString("CODIGO") + SEPARATOR
						+ rs.getString("NOMBRE") + SEPARATOR + rs.getString("FORMULA") + SEPARATOR
						+ rs.getString("FRECUENCIA") + SEPARATOR + rs.getString("TIPO") + SEPARATOR
						+ rs.getString("SUPUESTO") + SEPARATOR + rs.getString("MEDIOS_VERIFICACION") + "\"";
				return line;
			}

		});
		return generaTxt.generaArchivo(rows, PATH_NAME + fileName);
	}

	@Override
	public String generaReporte08b(String filename, Integer idsector, Integer trimestre, String cveOrganismo) {
		String sqlMaster = "SELECT T1.CODIGO, T1.ID_PADRE, T1.ID_NIVEL, T1.CAMPO7, T1.GEN, T1.CLAVE, 'V', T1.CODIGO||'\"|\"'||T1.NOMIND||'\"|\"V\"|\"'||T1.CVEVAR||'\"|\"'||T1.NOMVAR||'\"|\"'||T1.UNIDAD_MEDIDA||'\"|\"'||T1.OPERACION||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.META_ANUAL)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.PROGRAMADA)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.PORCENTAJE)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.ALCANZADA)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.PORCENTAJE_ALCANZADA)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.PROGRAMADA_ACUM)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.PORCENTAJE_ACUM)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.ALCANZADA_ACUM)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.PORCENTAJE_ALCANZADA_ACUM) LINE\r\n"
				+ "  FROM (\r\n"
				+ "    SELECT MIR.CODIGO, DECODE(N.ID_PADRE,0, N.ID, N.ID_PADRE) ID_PADRE, N.ID_NIVEL, MIR.NOMIND, V.CVEVAR, \r\n"
				+ "			   V.NOMVAR, FV.UNIDAD_MEDIDA, FV.OPERACION, FV.META_ANUAL, MU.CAMPO7, DG.CLAVE GEN, DA.CLAVE, "
				+ "           SUM(DECODE(P.PERIODO,%1$s,VT.PROGRAMADA,0)) PROGRAMADA, \r\n"
				+ "           SUM(DECODE(P.PERIODO,%1$s,VT.PORCENTAJE,0)) PORCENTAJE,\r\n"
				+ "           SUM(DECODE(P.PERIODO,%1$s,VT.ALCANZADA, 0)) ALCANZADA,\r\n"
				+ "           SUM(DECODE(P.PERIODO,%1$s,VT.PORCENTAJE_ALCANZADA,0)) PORCENTAJE_ALCANZADA,\r\n"
				+ "           SUM(VT.PROGRAMADA) PROGRAMADA_ACUM,\r\n"
				+ "           SUM(VT.PORCENTAJE) PORCENTAJE_ACUM,\r\n"
				+ "           SUM(VT.ALCANZADA) ALCANZADA_ACUM,\r\n"
				+ "           SUM(VT.PORCENTAJE_ALCANZADA) PORCENTAJE_ALCANZADA_ACUM\r\n" + "      FROM TC_FICHA F \r\n"
				+ "     	INNER JOIN TC_FICHA_VARIABLES FV ON FV.ID_FICHA = F.ID\r\n"
				+ "         INNER JOIN VARIABLES V ON V.ID = FV.ID_VARIABLE\r\n"
				+ "         INNER JOIN TC_VARIABLES_TRIMESTRES VT ON VT.ID_FICHA_VARIABLE = FV.ID\r\n"
				+ "         INNER JOIN TC_PERIODO P ON P.ID = VT.ID_PERIODO\r\n"
				+ "         INNER JOIN TR_MATRIZ_NIVEL MN ON MN.ID = F.ID_MATRIZ_NIVEL\r\n"
				+ " 		INNER JOIN TC_NIVEL N ON MN.ID_NIVEL = N.ID\r\n"
				+ "         INNER JOIN MIR ON MIR.CODIGO = N.CODIGO_INDICADOR\r\n"
				+ "         INNER JOIN TC_MATRIZ M ON M.ID = MN.ID_MATRIZ\r\n"
				+ "         INNER JOIN MUNINEP MU ON MU.ID = M.ID_PROYECTO\r\n"
				+ "         INNER JOIN CATDAA DA ON DA.ID = M.ID_DEP_AUX " 
				+ "			INNER JOIN CATDGM DG ON DG.ID = M.ID_DEPENDENCIA " + "    WHERE P.PERIODO <= %1$s \r\n"
				+ "    GROUP BY MIR.CODIGO, DECODE(N.ID_PADRE,0, N.ID, N.ID_PADRE), N.ID_NIVEL, MIR.NOMIND, V.CVEVAR, V.NOMVAR, FV.UNIDAD_MEDIDA, "
				+ "				FV.OPERACION, FV.META_ANUAL, MU.CAMPO7, DG.CLAVE, DA.CLAVE\r\n" + "  )T1\r\n" + "  UNION ALL\r\n"
				+ "SELECT T1.CODIGO, T1.ID_PADRE, T1.ID_NIVEL, T1.CAMPO7, T1.GEN, T1.CLAVE, 'M', T1.CODIGO||'\"|\"'||T1.NOMIND||'\"|\"M\"|\"'||FN_GET_FORMAT_NUMBER(T1.META_ANUAL)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.PROGRAMADA)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.ALCANZADA)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.PORCENTAJE)||'\"|\"'||T1.SEMAFORO||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.PROGRAMADA_ACUM)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.ALCANZADA_ACUM)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.PORCENTAJE_ALCANZADA)||'\"|\"'||T1.SEMAFORO_ACUMULADO\r\n"
				+ "  FROM (\r\n"
				+ "    SELECT F.ID ID_FICHA, DECODE(N.ID_PADRE,0, N.ID, N.ID_PADRE) ID_PADRE, N.ID_NIVEL, MIR.CODIGO, MIR.NOMIND, MU.CAMPO7, DG.CLAVE GEN, DA.CLAVE, \r\n"
				+ "           SUM(DECODE(P.PERIODO,%1$s,FT.META_ANUAL, 0)) META_ANUAL, \r\n"
				+ "           SUM(DECODE(P.PERIODO,%1$s,FT.PROGRAMADA, 0)) PROGRAMADA, \r\n"
				+ "           SUM(DECODE(P.PERIODO,%1$s,FT.PORCENTAJE, 0)) PORCENTAJE,\r\n"
				+ "           SUM(DECODE(P.PERIODO,%1$s,FT.ALCANZADA, 0)) ALCANZADA, \r\n"
				+ "           MAX(DECODE(P.PERIODO,%1$s,FT.SEMAFORO,'')) SEMAFORO,\r\n"
				+ "           SUM(FT.PROGRAMADA) PROGRAMADA_ACUM,                        \r\n"
				+ "           SUM(FT.ALCANZADA) ALCANZADA_ACUM,                        \r\n"
				+ "           SUM(FT.PORCENTAJE_ALCANZADA) PORCENTAJE_ALCANZADA,              \r\n"
				+ "           MAX(DECODE(P.PERIODO,%1$s,FT.SEMAFORO_ACUMULADO,'')) SEMAFORO_ACUMULADO\r\n"
				+ "      FROM TC_FICHA F \r\n" 
				+ "        	INNER JOIN TC_FICHA_TRIMESTRES FT ON FT.ID_FICHA = F.ID\r\n"
				+ "         INNER JOIN TC_PERIODO P ON P.ID = FT.ID_PERIODO\r\n"
				+ "         INNER JOIN TR_MATRIZ_NIVEL MN ON MN.ID = F.ID_MATRIZ_NIVEL\r\n"
				+ " 		INNER JOIN TC_NIVEL N ON MN.ID_NIVEL = N.ID\r\n"
				+ "         INNER JOIN MIR ON MIR.CODIGO = N.CODIGO_INDICADOR\r\n"
				+ "         INNER JOIN TC_MATRIZ M ON M.ID = MN.ID_MATRIZ\r\n"
				+ "         INNER JOIN MUNINEP MU ON MU.ID = M.ID_PROYECTO\r\n"
				+ "         INNER JOIN CATDAA DA ON DA.ID = M.ID_DEP_AUX "  
				+ "			INNER JOIN CATDGM DG ON DG.ID = M.ID_DEPENDENCIA" + "    WHERE P.PERIODO <= %1$s \r\n"
				+ "    GROUP BY F.ID, DECODE(N.ID_PADRE,0, N.ID, N.ID_PADRE), N.ID_NIVEL, MIR.CODIGO, MIR.NOMIND, MU.CAMPO7, DG.CLAVE, DA.CLAVE \r\n"
				+ "  ) T1\r\n" + "ORDER BY GEN,CLAVE, CAMPO7, CODIGO, ID_NIVEL, ID_PADRE ASC, 7 DESC";

		System.out.println(String.format(sqlMaster, trimestre));

		List<String> rows = new ArrayList<String>();
		rows = this.jdbcTemplate.query(String.format(sqlMaster, trimestre), new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				String line = "\"" + cveOrganismo + SEPARATOR + rs.getString("LINE") + "\"";

				return line;
			}

		});
		return generaTxt.generaArchivo(rows, PATH_NAME + filename);
	}

	@Override
	public String generaReporte01d(String filename, Integer idsector, Integer trimestre) {
		String sqlMaster = "SELECT T1.CODIGO, T1.ID_PADRE, T1.ID_NIVEL, T1.CAMPO7, T1.CLAVE, 'V', T1.CODIGO||'\"|\"'||T1.NOMIND||'\"|\"'||T1.CAMPO7||'\"|\"'||T1.CLAVE||'\"|\"'||T1.INTERPRETACION||'\"|\"'||T1.DIMENSION||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.FACTOR)||'\"|\"'||T1.DESCRIPCION_FACTOR||'\"|\"V\"|\"'||T1.CVEVAR||'\"|\"'||T1.NOMVAR||'\"|\"'||T1.UNIDAD_MEDIDA||'\"|\"'||T1.OPERACION||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.PROGRAMADA_1)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.PROGRAMADA_2)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.PROGRAMADA_3)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.PROGRAMADA_4)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.META_ANUAL) LINE\r\n"
				+ "  FROM (\r\n"
				+ "    SELECT MIR.CODIGO, DECODE(N.ID_PADRE,0,N.ID,N.ID_PADRE) ID_PADRE, N.ID_NIVEL, MIR.NOMIND, MU.CAMPO7, DA.CLAVE, V.CVEVAR, V.NOMVAR, F.INTERPRETACION , F.DIMENSION, F.FACTOR, F.DESCRIPCION_FACTOR, FV.UNIDAD_MEDIDA, FV.OPERACION, FV.META_ANUAL, \r\n"
				+ "           SUM(DECODE(P.PERIODO,1,VT.PROGRAMADA,0)) PROGRAMADA_1, \r\n"
				+ "           SUM(DECODE(P.PERIODO,2,VT.PROGRAMADA,0)) PROGRAMADA_2,\r\n"
				+ "           SUM(DECODE(P.PERIODO,3,VT.PROGRAMADA, 0)) PROGRAMADA_3,\r\n"
				+ "           SUM(DECODE(P.PERIODO,4,VT.PROGRAMADA,0)) PROGRAMADA_4\r\n" + "      FROM TC_FICHA F \r\n"
				+ "         INNER JOIN TR_MATRIZ_NIVEL MN ON F.ID_MATRIZ_NIVEL = MN.ID\r\n"
				+ "         INNER JOIN TC_NIVEL N ON MN.ID_NIVEL = N.ID\r\n"
				+ "         INNER JOIN TC_MATRIZ M ON MN.ID_MATRIZ = M.ID \r\n"
				+ "			INNER JOIN CATDAA DA ON DA.ID = M.ID_DEP_AUX\r\n"
				+ "			INNER JOIN MUNINEP MU ON MU.ID = M.ID_PROYECTO\r\n"
				+ "			INNER JOIN MIR ON N.CODIGO_INDICADOR = MIR.CODIGO\r\n"
				+ "			INNER JOIN TC_FICHA_VARIABLES FV ON FV.ID_FICHA = F.ID\r\n"
				+ "			INNER JOIN VARIABLES V ON V.ID = FV.ID_VARIABLE\r\n"
				+ "			INNER JOIN TC_VARIABLES_TRIMESTRES VT ON VT.ID_FICHA_VARIABLE = FV.ID\r\n"
				+ "			INNER JOIN TC_PERIODO P ON P.ID = VT.ID_PERIODO     "
				+ "    GROUP BY MIR.CODIGO, DECODE(N.ID_PADRE,0,N.ID,N.ID_PADRE), N.ID_NIVEL, MIR.NOMIND, MU.CAMPO7, DA.CLAVE, V.CVEVAR, V.NOMVAR, F.INTERPRETACION, F.DIMENSION, F.FACTOR, F.DESCRIPCION_FACTOR, FV.UNIDAD_MEDIDA, FV.OPERACION, FV.META_ANUAL\r\n"
				+ "  )T1\r\n" + "  UNION ALL\r\n"
				+ "SELECT T1.CODIGO, T1.ID_PADRE, T1.ID_NIVEL, T1.CAMPO7, T1.CLAVE, 'M', T1.CODIGO||'\"|\"'||T1.NOMIND||'\"|\"'||T1.CAMPO7||'\"|\"'||T1.CLAVE||'\"|\"'||T1.INTERPRETACION||'\"|\"'||T1.DIMENSION||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.FACTOR)||'\"|\"'||T1.DESCRIPCION_FACTOR||'\"|\"M\"|\"'||FN_GET_FORMAT_NUMBER(T1.PROGRAMADA_1)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.PROGRAMADA_2)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.PROGRAMADA_3)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.PROGRAMADA_4)||'\"|\"'||FN_GET_FORMAT_NUMBER(T1.META_ANUAL)||'\"|\"'||T1.DESCRIPCION_META_ANUAL||'\"|\"'||T1.MEDIOS_VERIFICACION||'\"|\"'||T1.METAS_ACCION\r\n"
				+ "  FROM (\r\n"
				+ "    SELECT F.ID ID_FICHA, DECODE(N.ID_PADRE,0,N.ID, N.ID_PADRE) ID_PADRE, N.ID_NIVEL, MIR.CODIGO, MIR.NOMIND, MU.CAMPO7, DA.CLAVE, F.INTERPRETACION , F.DIMENSION, F.FACTOR, F.DESCRIPCION_FACTOR,F.DESCRIPCION_META_ANUAL, N.MEDIOS_VERIFICACION, F.METAS_ACCION,\r\n"
				+ "           SUM(DECODE(P.PERIODO,1,FT.PROGRAMADA, 0)) PROGRAMADA_1, \r\n"
				+ "           SUM(DECODE(P.PERIODO,2,FT.PROGRAMADA, 0)) PROGRAMADA_2, \r\n"
				+ "           SUM(DECODE(P.PERIODO,3,FT.PROGRAMADA, 0)) PROGRAMADA_3,\r\n"
				+ "           SUM(DECODE(P.PERIODO,4,FT.PROGRAMADA, 0)) PROGRAMADA_4, \r\n"
				+ "           SUM(DECODE(P.PERIODO,%1$s,FT.META_ANUAL, 0)) META_ANUAL\r\n"
				+ "      FROM TC_FICHA F \r\n"
				+ "       	INNER JOIN TR_MATRIZ_NIVEL MN ON MN.ID = F.ID_MATRIZ_NIVEL\r\n"
				+ "         INNER JOIN TC_NIVEL N ON MN.ID_NIVEL = N.ID\r\n"
				+ "			INNER JOIN TC_MATRIZ M ON MN.ID_MATRIZ = M.ID\r\n"
				+ "		    INNER JOIN CATDAA DA ON DA.ID = M.ID_DEP_AUX\r\n"
				+ "			INNER JOIN MUNINEP MU ON MU.ID = M.ID_PROYECTO\r\n"
				+ "			INNER JOIN MIR ON MIR.CODIGO = N.CODIGO_INDICADOR"
				+ "        	LEFT JOIN TC_FICHA_TRIMESTRES FT ON FT.ID_FICHA = F.ID\r\n"
				+ "        	LEFT JOIN TC_PERIODO P ON P.ID = FT.ID_PERIODO\r\n"
				+ "    GROUP BY F.ID, DECODE(N.ID_PADRE,0,N.ID,N.ID_PADRE), N.ID_NIVEL, MIR.CODIGO, MIR.NOMIND, MU.CAMPO7, DA.CLAVE, F.INTERPRETACION , F.DIMENSION, F.FACTOR, F.DESCRIPCION_FACTOR,F.DESCRIPCION_META_ANUAL, N.MEDIOS_VERIFICACION, F.METAS_ACCION \r\n"
				+ "  ) T1 ORDER BY CAMPO7, CLAVE, ID_PADRE, ID_NIVEL, CODIGO ASC, 6 DESC";

		System.out.println(sqlMaster.toString());
		List<String> rows = new ArrayList<String>();
		rows = this.jdbcTemplate.query(String.format(sqlMaster, trimestre), new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				String line = "\"" + rs.getString("LINE") + "\"";

				return line;
			}

		});
		return generaTxt.generaArchivo(rows, PATH_NAME + filename);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public GeneraTxtBS getGeneraTxt() {
		return generaTxt;
	}

	public void setGeneraTxt(GeneraTxtBS generaTxt) {
		this.generaTxt = generaTxt;
	}

}