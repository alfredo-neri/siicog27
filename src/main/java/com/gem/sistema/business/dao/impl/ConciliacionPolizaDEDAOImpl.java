package com.gem.sistema.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.ConciliacionPolizaDEDAO;
import com.gem.sistema.business.vo.ConciliacionPolizaDEVO;

/**
 * Implementacion de la conciliacion de polizas D contra E por referencia.
 *
 * La consulta trae los movimientos del mes solicitado y del inmediato anterior,
 * pero el conjunto de referencias lo fija el mes solicitado (CTE REFS): el mes
 * previo solo sirve para completar la poliza que falta. La prioridad se resuelve
 * con ROW_NUMBER ordenando por mes descendente, de modo que el mes consultado
 * siempre gana y el anterior entra unicamente cuando ese tipo de poliza no
 * existe en el mes.
 *
 * Los contadores NUM_D y NUM_E cuentan las polizas del mismo tipo dentro del mes
 * que quedo seleccionado, y sirven para marcar referencias repetidas en las que
 * la consulta muestra una sola poliza.
 *
 * CUENTAS_D y CUENTAS_E concentran las cuentas de orden 82xx de los movimientos
 * de cada poliza. Se toman de la poliza que realmente quedo seleccionada en
 * POLSEL, empatando mes y consecutivo, por lo que reflejan sus propios
 * movimientos incluso cuando la poliza proviene del mes anterior.
 *
 * @author Alfredo Neri
 */
@Repository
public class ConciliacionPolizaDEDAOImpl implements ConciliacionPolizaDEDAO {

	/** Prefijo de las cuentas de orden presupuestarias consideradas. */
	private static final String PREFIJO_CUENTA_ORDEN = "82%";

	/** Longitud minima que debe tener la referencia para ser considerada. */
	private static final int LONGITUD_MINIMA_REFPOL = 0;

	/** Separador de la lista de cuentas concentrada por poliza. */
	private static final String SEPARADOR_CUENTAS = ", ";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<ConciliacionPolizaDEVO> findByPeriodo(final Integer anopol, final Integer mespol, Integer idSector) {

		final Integer mesAnterior = Integer.valueOf(mespol.intValue() - 1);

		StringBuilder sSql = new StringBuilder();

		sSql.append("WITH MOV AS (\n")
			.append("    SELECT EN.ANOPOL,\n")
			.append("           EN.MESPOL,\n")
			.append("           EN.TIPPOL,\n")
			.append("           EN.CONPOL,\n")
			.append("           EN.FECPOL,\n")
			.append("           RTRIM(DE.REFPOL) REFPOL,\n")
			.append("           DE.CUENTA\n")
			.append("      FROM POLIEN EN\n")
			.append("           INNER JOIN POLIDE DE ON  EN.ANOPOL   = DE.ANOPOL\n")
			.append("                                AND EN.MESPOL   = DE.MESPOL\n")
			.append("                                AND EN.TIPPOL   = DE.TIPPOL\n")
			.append("                                AND EN.CONPOL   = DE.CONPOL\n")
			.append("                                AND EN.IDSECTOR = DE.IDSECTOR\n")
			.append("     WHERE EN.TIPPOL IN ('D', 'E')\n")
			.append("       AND DE.CUENTA LIKE ?\n")
			.append("       AND EN.ANOPOL   = ?\n")
			.append("       AND EN.MESPOL  IN (?, ?)\n")
			.append("       AND EN.IDSECTOR = ?\n")
			.append("       AND LENGTH(RTRIM(DE.REFPOL)) > ?\n")
			.append("),\n")
			.append("REFS AS (\n")
			.append("    SELECT DISTINCT REFPOL\n")
			.append("      FROM MOV\n")
			.append("     WHERE MESPOL = ?\n")
			.append("),\n")
			.append("POL AS (\n")
			.append("    SELECT T.REFPOL,\n")
			.append("           T.TIPPOL,\n")
			.append("           T.MESPOL,\n")
			.append("           T.CONPOL,\n")
			.append("           T.FECPOL,\n")
			.append("           ROW_NUMBER() OVER (PARTITION BY T.REFPOL, T.TIPPOL\n")
			.append("                                  ORDER BY T.MESPOL DESC, T.CONPOL DESC) RN,\n")
			.append("           COUNT(*)     OVER (PARTITION BY T.REFPOL, T.TIPPOL, T.MESPOL) NUM\n")
			.append("      FROM (SELECT DISTINCT M.REFPOL, M.TIPPOL, M.MESPOL, M.CONPOL, M.FECPOL\n")
			.append("              FROM MOV M\n")
			.append("                   INNER JOIN REFS R ON R.REFPOL = M.REFPOL) T\n")
			.append("),\n")
			.append("POLSEL AS (\n")
			.append("    SELECT REFPOL, TIPPOL, MESPOL, CONPOL, FECPOL, NUM\n")
			.append("      FROM POL\n")
			.append("     WHERE RN = 1\n")
			.append("),\n")
			.append("CTAS AS (\n")
			.append("    SELECT C.REFPOL,\n")
			.append("           C.TIPPOL,\n")
			.append("           LISTAGG(C.CUENTA, '").append(SEPARADOR_CUENTAS)
			.append("') WITHIN GROUP (ORDER BY C.CUENTA) CUENTAS\n")
			.append("      FROM (SELECT DISTINCT S.REFPOL, S.TIPPOL, M.CUENTA\n")
			.append("              FROM POLSEL S\n")
			.append("                   INNER JOIN MOV M ON  M.REFPOL = S.REFPOL\n")
			.append("                                    AND M.TIPPOL = S.TIPPOL\n")
			.append("                                    AND M.MESPOL = S.MESPOL\n")
			.append("                                    AND M.CONPOL = S.CONPOL) C\n")
			.append("     GROUP BY C.REFPOL, C.TIPPOL\n")
			.append(")\n")
			.append("SELECT R.REFPOL,\n")
			.append("       MAX(CASE WHEN S.TIPPOL = 'D' THEN S.MESPOL END) MES_D,\n")
			.append("       MAX(CASE WHEN S.TIPPOL = 'D' THEN S.TIPPOL END) TIPO_D,\n")
			.append("       COALESCE(MAX(CASE WHEN S.TIPPOL = 'D' THEN S.CONPOL END), 0) CONPOL_D,\n")
			.append("       MAX(CASE WHEN S.TIPPOL = 'D' THEN S.FECPOL END) FECPOL_D,\n")
			.append("       MAX(CASE WHEN S.TIPPOL = 'D' THEN C.CUENTAS END) CUENTAS_D,\n")
			.append("       MAX(CASE WHEN S.TIPPOL = 'E' THEN S.MESPOL END) MES_E,\n")
			.append("       MAX(CASE WHEN S.TIPPOL = 'E' THEN S.TIPPOL END) TIPO_E,\n")
			.append("       COALESCE(MAX(CASE WHEN S.TIPPOL = 'E' THEN S.CONPOL END), 0) CONPOL_E,\n")
			.append("       MAX(CASE WHEN S.TIPPOL = 'E' THEN S.FECPOL END) FECPOL_E,\n")
			.append("       MAX(CASE WHEN S.TIPPOL = 'E' THEN C.CUENTAS END) CUENTAS_E,\n")
			.append("       COALESCE(MAX(CASE WHEN S.TIPPOL = 'D' THEN S.NUM END), 0) NUM_D,\n")
			.append("       COALESCE(MAX(CASE WHEN S.TIPPOL = 'E' THEN S.NUM END), 0) NUM_E\n")
			.append("  FROM REFS R\n")
			.append("       LEFT JOIN POLSEL S ON S.REFPOL = R.REFPOL\n")
			.append("       LEFT JOIN CTAS   C ON  C.REFPOL = S.REFPOL\n")
			.append("                          AND C.TIPPOL = S.TIPPOL\n")
			.append(" GROUP BY R.REFPOL\n")
			.append(" ORDER BY R.REFPOL");

		Object[] parametros = new Object[] { PREFIJO_CUENTA_ORDEN, anopol, mespol, mesAnterior, idSector,
				LONGITUD_MINIMA_REFPOL, mespol };

		return this.jdbcTemplate.query(sSql.toString(), parametros, new RowMapper<ConciliacionPolizaDEVO>() {

			@Override
			public ConciliacionPolizaDEVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ConciliacionPolizaDEVO conciliacion = new ConciliacionPolizaDEVO();
				conciliacion.setAnopol(anopol);
				conciliacion.setMespol(mespol);
				conciliacion.setRefpol(rs.getString("REFPOL"));
				conciliacion.setMesD(leeEntero(rs, "MES_D"));
				conciliacion.setTipoD(rs.getString("TIPO_D"));
				conciliacion.setConpolD(rs.getInt("CONPOL_D"));
				conciliacion.setFecpolD(rs.getDate("FECPOL_D"));
				conciliacion.setCuentasD(rs.getString("CUENTAS_D"));
				conciliacion.setMesE(leeEntero(rs, "MES_E"));
				conciliacion.setTipoE(rs.getString("TIPO_E"));
				conciliacion.setConpolE(rs.getInt("CONPOL_E"));
				conciliacion.setFecpolE(rs.getDate("FECPOL_E"));
				conciliacion.setCuentasE(rs.getString("CUENTAS_E"));
				conciliacion.setNumD(rs.getInt("NUM_D"));
				conciliacion.setNumE(rs.getInt("NUM_E"));
				return conciliacion;
			}

			/**
			 * Lee un entero que puede venir nulo, conservando el nulo en vez de
			 * convertirlo en cero.
			 *
			 * @param rs
			 *            resultado en curso
			 * @param columna
			 *            nombre de la columna
			 * @return el valor, o null cuando la columna es nula
			 * @throws SQLException
			 *             si falla la lectura
			 */
			private Integer leeEntero(ResultSet rs, String columna) throws SQLException {
				int valor = rs.getInt(columna);
				return rs.wasNull() ? null : Integer.valueOf(valor);
			}
		});
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
