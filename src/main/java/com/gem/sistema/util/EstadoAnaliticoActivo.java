package com.gem.sistema.util;

import static com.gem.sistema.util.EstadoAnaliticoActivo.getActivos;
import static com.gem.sistema.util.FlujoEfectivoUtil.getMapCounts;

import java.util.Map;

public class EstadoAnaliticoActivo {

	private static String UNION_ALL = " UNION ALL ";

	private static String activoCirculante(char tipo, Integer value) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();

		Integer maxMonth = tipo == 't' ? (getMonth(value) - 3) : (value - 1);
		System.out.println(":::::" + maxMonth);

		Map<String, String> map = FlujoEfectivoUtil.getMapCounts(tipo, maxMonth);
		cargos.append(map.get("cargos"));
		abonos.append(map.get("abonos"));
		sSql.append("SELECT 1 GRUPO, ").append("        T2.CUENTA, ").append("        T2.NOMCTA, ")
				.append("        T2.SALDO, ").append("        T2.CARGOS_MES, ").append("        T2.CARGOS_MES, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) SALDO_FINAL, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) - T2.SALDO VARIACION ")
				.append("      FROM( ").append(" SELECT SUBSTR(T1.CUENTA,1,2) CUENTA, ")
				.append("        'Efectivo y Equivalentes' NOMCTA, ")
				.append("        SUM(T1.SALINI + CARGOS - ABONOS)  SALDO, ")
				.append("        SUM(T1.CARGOS_MES) CARGOS_MES, ").append("        SUM(T1.ABONOS_MES) ABONOS_MES ")
				.append("      FROM ( ").append(" SELECT ").append(" 	CU.CUENTA, ").append(" 	CU.NOMCTA, ")
				.append(" 	CU.STACTA, ").append(" 	CU. SALINI, ").append(" 	" + cargos + " CARGOS, ")
				.append(" 	" + abonos + " ABONOS, ").append(" 	CU.CARGOS_" + (maxMonth + 1) + " CARGOS_MES, ")
				.append(" 	CU.ABONOS_" + (maxMonth + 1) + " ABONOS_MES ").append("  ").append(" FROM ")
				.append(" 	CUENTA CU ").append(" WHERE CU.CUENTA  ").append("   AND CU.CUENTA IN ('1111', '1112') ")
				.append("   AND CU.SCTA = '' ").append(" ) T1 ").append(" GROUP BY SUBSTR(T1.CUENTA,1,2) ")
				.append(" ) T2");
		sSql.append(UNION_ALL);
		sSql.append("SELECT 1 GRUPO, ").append("        T2.CUENTA, ").append("        T2.NOMCTA, ")
				.append("        T2.SALDO, ").append("        T2.CARGOS_MES, ").append("        T2.CARGOS_MES, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) SALDO_FINAL, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) - T2.SALDO VARIACION ")
				.append("      FROM( ").append(" SELECT SUBSTR(T1.CUENTA,1,2) CUENTA, ")
				.append("        'Derechos a Recibir Efectivo o Equivalentes' NOMCTA, ")
				.append("        SUM(T1.SALINI + CARGOS - ABONOS)  SALDO, ")
				.append("        SUM(T1.CARGOS_MES) CARGOS_MES, ").append("        SUM(T1.ABONOS_MES) ABONOS_MES ")
				.append("      FROM ( ").append(" SELECT ").append(" 	CU.CUENTA, ").append(" 	CU.NOMCTA, ")
				.append(" 	CU.STACTA, ").append(" 	CU. SALINI, ").append(" 	" + cargos + " CARGOS, ")
				.append(" 	" + abonos + " ABONOS, ").append(" 	CU.CARGOS_" + (maxMonth + 1) + " CARGOS_MES, ")
				.append(" 	CU.ABONOS_" + (maxMonth + 1) + " ABONOS_MES ").append("  ").append(" FROM ")
				.append(" 	CUENTA CU ").append(" WHERE CU.CUENTA  ").append("   AND CU.CUENTA = '1123' ")
				.append("   AND CU.SCTA = '' ").append(" ) T1 ").append(" GROUP BY SUBSTR(T1.CUENTA,1,2) ")
				.append(" ) T2");
		sSql.append(UNION_ALL);
		sSql.append("SELECT 1 GRUPO, ").append("        CU.CUENTA, ").append("        CU.NOMCTA, ")
				.append("         0 SALDO, ").append("         0 CARGOS_MES, ").append("         0 ABONOS_MES, ")
				.append("         0 SALDO_FINAL, ").append("         0 VARIACION ").append("       FROM CUENTA CU ")
				.append(" WHERE CU.CUENTA >= '1130' ").append("   AND CU.CUENTA <= '1160' ")
				.append("   AND SUBSTR(CU.CUENTA,4,1) = '0'");
		sSql.append(UNION_ALL);
		sSql.append("SELECT 1 GRUPO, ").append("        T2.CUENTA, ").append("        T2.NOMCTA, ")
				.append("        T2.SALDO, ").append("        T2.CARGOS_MES, ").append("        T2.CARGOS_MES, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) SALDO_FINAL, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) - T2.SALDO VARIACION ")
				.append("      FROM( ").append(" SELECT SUBSTR(T1.CUENTA,1,2)||'70' CUENTA, ")
				.append("        'Otros Activos  Circulantes' NOMCTA, ")
				.append("        SUM(T1.SALINI + CARGOS - ABONOS)  SALDO, ")
				.append("        SUM(T1.CARGOS_MES) CARGOS_MES, ").append("        SUM(T1.ABONOS_MES) ABONOS_MES ")
				.append("      FROM ( ").append(" SELECT ").append(" 	CU.CUENTA, ").append(" 	CU.NOMCTA, ")
				.append(" 	CU.STACTA, ").append(" 	CU. SALINI, ").append(" 	" + cargos + " CARGOS, ")
				.append(" 	" + abonos + " ABONOS, ").append(" 	CU.CARGOS_" + (maxMonth + 1) + " CARGOS_MES, ")
				.append(" 	CU.ABONOS_" + (maxMonth + 1) + " ABONOS_MES ").append("  ").append(" FROM ")
				.append(" 	CUENTA CU ").append(" WHERE CU.CUENTA  ").append("   AND CU.CUENTA = '1191' ")
				.append("   AND CU.SCTA = '' ").append(" ) T1 ").append(" GROUP BY SUBSTR(T1.CUENTA,1,2) ")
				.append(" ) T2");
		return sSql.toString();
	}

	private static String activoCirculanteTrimestre(Integer trimestre) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder cargosAnterior = new StringBuilder();
		StringBuilder abonosAnterior = new StringBuilder();
		StringBuilder cargosActual = new StringBuilder();
		StringBuilder abonosActual = new StringBuilder();

		int monthMaximo = getMonth(trimestre);
		int monthAnterior = monthMaximo - 3;

		System.out.println(":::::" + monthMaximo);
		System.out.println(":::::" + monthAnterior);
		
		if(trimestre == 1) {
			cargosAnterior.append(" 0 ");
			abonosAnterior.append(" 0 ");
		}

		for (int x = 1; x <= monthMaximo; x++) {

			if (x <= monthAnterior) {
				abonosAnterior.append("CU.ABONOS_").append(x);
				cargosAnterior.append("CU.CARGOS_").append(x);
				if (x < monthAnterior) {
					cargosAnterior.append(" + ");
					abonosAnterior.append(" + ");
				}

			}

			if (x > monthAnterior && x <= monthMaximo) {
				abonosActual.append("CU.ABONOS_").append(x);
				cargosActual.append("CU.CARGOS_").append(x);
				if (x < monthMaximo) {
					cargosActual.append(" + ");
					abonosActual.append(" + ");
				}
			}
		}

		sSql.append("SELECT 1 GRUPO, ").append("        T2.CUENTA, ").append("        T2.NOMCTA, ")
				.append("        T2.SALDO, ").append("        T2.CARGOS_MES, ").append("        T2.CARGOS_MES, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) SALDO_FINAL, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) - T2.SALDO VARIACION ")
				.append("      FROM( ").append(" SELECT SUBSTR(T1.CUENTA,1,2) CUENTA, ")
				.append("        'Efectivo y Equivalentes' NOMCTA, ")
				.append("        SUM(T1.SALINI + CARGOS - ABONOS)  SALDO, ")
				.append("        SUM(T1.CARGOS_MES) CARGOS_MES, ").append("        SUM(T1.ABONOS_MES) ABONOS_MES ")
				.append("      FROM ( ").append(" SELECT ").append(" 	CU.CUENTA, ").append(" 	CU.NOMCTA, ")
				.append(" 	CU.STACTA, ").append(" 	CU. SALINI, ").append(" 	" + cargosAnterior + " CARGOS, ")
				.append(" 	" + abonosAnterior + " ABONOS, ").append(cargosActual).append( " CARGOS_MES, ")
				.append( abonosActual ).append( " ABONOS_MES ").append(" FROM ")
				.append(" 	CUENTA CU ").append(" WHERE CU.CUENTA  ").append("   AND CU.CUENTA IN ('1111', '1112') ")
				.append("   AND CU.SCTA = '' ").append(" ) T1 ").append(" GROUP BY SUBSTR(T1.CUENTA,1,2) ")
				.append(" ) T2");
		sSql.append(UNION_ALL);
		sSql.append("SELECT 1 GRUPO, ").append("        T2.CUENTA, ").append("        T2.NOMCTA, ")
				.append("        T2.SALDO, ").append("        T2.CARGOS_MES, ").append("        T2.CARGOS_MES, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) SALDO_FINAL, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) - T2.SALDO VARIACION ")
				.append("      FROM( ").append(" SELECT SUBSTR(T1.CUENTA,1,2) CUENTA, ")
				.append("        'Derechos a Recibir Efectivo o Equivalentes' NOMCTA, ")
				.append("        SUM(T1.SALINI + CARGOS - ABONOS)  SALDO, ")
				.append("        SUM(T1.CARGOS_MES) CARGOS_MES, ").append("        SUM(T1.ABONOS_MES) ABONOS_MES ")
				.append("      FROM ( ").append(" SELECT ").append(" 	CU.CUENTA, ").append(" 	CU.NOMCTA, ")
				.append(" 	CU.STACTA, ").append(" 	CU. SALINI, ").append(" 	" + cargosAnterior + " CARGOS, ")
				.append(" 	" + abonosAnterior + " ABONOS, ").append( cargosActual ).append( " CARGOS_MES, ")
				.append( abonosActual ).append( " ABONOS_MES ").append(" FROM ")
				.append(" 	CUENTA CU ").append(" WHERE CU.CUENTA  ").append("   AND CU.CUENTA = '1123' ")
				.append("   AND CU.SCTA = '' ").append(" ) T1 ").append(" GROUP BY SUBSTR(T1.CUENTA,1,2) ")
				.append(" ) T2");
		sSql.append(UNION_ALL);
		sSql.append("SELECT 1 GRUPO, ").append("        CU.CUENTA, ").append("        CU.NOMCTA, ")
				.append("         0 SALDO, ").append("         0 CARGOS_MES, ").append("         0 ABONOS_MES, ")
				.append("         0 SALDO_FINAL, ").append("         0 VARIACION ").append("       FROM CUENTA CU ")
				.append(" WHERE CU.CUENTA >= '1130' ").append("   AND CU.CUENTA <= '1160' ")
				.append("   AND SUBSTR(CU.CUENTA,4,1) = '0'");
		sSql.append(UNION_ALL);
		sSql.append("SELECT 1 GRUPO, ").append("        T2.CUENTA, ").append("        T2.NOMCTA, ")
				.append("        T2.SALDO, ").append("        T2.CARGOS_MES, ").append("        T2.CARGOS_MES, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) SALDO_FINAL, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) - T2.SALDO VARIACION ")
				.append("      FROM( ").append(" SELECT SUBSTR(T1.CUENTA,1,2)||'70' CUENTA, ")
				.append("        'Otros Activos  Circulantes' NOMCTA, ")
				.append("        SUM(T1.SALINI + CARGOS - ABONOS)  SALDO, ")
				.append("        SUM(T1.CARGOS_MES) CARGOS_MES, ").append("        SUM(T1.ABONOS_MES) ABONOS_MES ")
				.append("      FROM ( ").append(" SELECT ").append(" 	CU.CUENTA, ").append(" 	CU.NOMCTA, ")
				.append(" 	CU.STACTA, ").append(" 	CU. SALINI, ").append(cargosAnterior).append( " CARGOS, ")
				.append( abonosAnterior ).append( " ABONOS, ").append( cargosActual ).append( " CARGOS_MES, ")
				.append( abonosActual ).append( " ABONOS_MES ").append(" FROM ")
				.append(" 	CUENTA CU ").append(" WHERE CU.CUENTA  ").append("   AND CU.CUENTA = '1191' ")
				.append("   AND CU.SCTA = '' ").append(" ) T1 ").append(" GROUP BY SUBSTR(T1.CUENTA,1,2) ")
				.append(" ) T2");
		return sSql.toString();
	}

	private static String generaActivNoCirculante(char tipo, Integer value) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();

		Integer maxMonth = tipo == 't' ? getMonth(value) : value;

		Map<String, String> map = FlujoEfectivoUtil.getMapCounts(maxMonth - 1);
		cargos.append(map.get("cargos"));
		abonos.append(map.get("abonos"));

		sSql.append("SELECT 2 GRUPO, ").append("        CU.CUENTA, ").append("        CU.NOMCTA, ")
				.append("         0 SALDO, ").append("         0 CARGOS_MES, ").append("         0 ABONOS_MES, ")
				.append("         0 SALDO_FINAL, ").append("         0 VARIACION ").append("       FROM CUENTA CU ")
				.append(" WHERE CU.CUENTA >= '1210' ").append("   AND CU.CUENTA <= '1230' ")
				.append("   AND SUBSTR(CU.CUENTA,4,1) = '0'  ").append(" UNION ALL ").append(" SELECT 2 GRUPO, ")
				.append("        T2.CUENTA, ").append("        T2.NOMCTA, ").append("        T2.SALDO, ")
				.append("        T2.CARGOS_MES, ").append("        T2.CARGOS_MES, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) SALDO_FINAL, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) - T2.SALDO VARIACION ")
				.append("      FROM( ").append(" SELECT SUBSTR(T1.CUENTA,1,2)||'40' CUENTA, ")
				.append("        'Bienes Muebles' NOMCTA, ").append("        SUM(T1.SALINI + CARGOS - ABONOS)  SALDO, ")
				.append("        SUM(T1.CARGOS_MES) CARGOS_MES, ").append("        SUM(T1.ABONOS_MES) ABONOS_MES ")
				.append("      FROM ( ").append(" SELECT ").append(" 	CU.CUENTA, ").append(" 	CU.NOMCTA, ")
				.append(" 	CU.STACTA, ").append(" 	CU. SALINI, ").append(" 	" + cargos + " CARGOS, ")
				.append(" 	" + abonos + " ABONOS, ").append(" 	CU.CARGOS_" + maxMonth + " CARGOS_MES, ")
				.append(" 	CU.ABONOS_" + maxMonth + " ABONOS_MES ").append("  ").append(" FROM ")
				.append(" 	CUENTA CU ").append(" WHERE CU.CUENTA  ")
				.append("   AND CU.CUENTA IN ('1242','1244','1246', '1241') ").append("   AND CU.SCTA = '' ")
				.append(" ) T1 ").append("        ").append(" GROUP BY SUBSTR(T1.CUENTA,1,2) ").append(" ) T2");
		sSql.append(UNION_ALL);
		sSql.append("SELECT 2 GRUPO, ").append("        CU.CUENTA, ").append("        CU.NOMCTA, ")
				.append("         0 SALDO, ").append("         0 CARGOS_MES, ").append("         0 ABONOS_MES, ")
				.append("         0 SALDO_FINAL, ").append("         0 VARIACION ").append("       FROM CUENTA CU ")
				.append(" WHERE CU.CUENTA = '1250' ").append("   AND SUBSTR(CU.CUENTA,4,1) = '0'");
		sSql.append(UNION_ALL);
		sSql.append("SELECT 2 GRUPO, ").append("        T2.CUENTA, ").append("        T2.NOMCTA, ")
				.append("        T2.SALDO, ").append("        T2.CARGOS_MES, ").append("        T2.CARGOS_MES, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) SALDO_FINAL, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) - T2.SALDO VARIACION ")
				.append("      FROM( ").append(" SELECT T1.CUENTA CUENTA, ")
				.append("        'Depreciación, Deterioro y Amortización Acumulada de Bienes' NOMCTA, ")
				.append("        SUM(T1.SALINI + CARGOS - ABONOS)  SALDO, ")
				.append("        SUM(T1.CARGOS_MES) CARGOS_MES, ").append("        SUM(T1.ABONOS_MES) ABONOS_MES ")
				.append("      FROM ( ").append(" SELECT ").append(" 	CU.CUENTA, ").append(" 	CU.NOMCTA, ")
				.append(" 	CU.STACTA, ").append(" 	(CU. SALINI*-1) SALINI, ").append(" 	" + cargos + " CARGOS, ")
				.append(" 	" + abonos + " ABONOS, ").append(" 	CU.CARGOS_" + maxMonth + " CARGOS_MES, ")
				.append(" 	CU.ABONOS_" + maxMonth + " ABONOS_MES ").append("  ").append(" FROM ")
				.append(" 	CUENTA CU ").append(" WHERE CU.CUENTA  ").append("   AND CU.CUENTA ='1263' ")
				.append("   AND CU.SCTA = '' ").append(" ) T1 ").append(" GROUP BY T1.CUENTA ").append(" ) T2");
		sSql.append(UNION_ALL);
		sSql.append("SELECT 2 GRUPO, ").append("        CU.CUENTA, ").append("        CU.NOMCTA, ")
				.append("         SALINI SALDO, ").append("         CARGOS_6 CARGOS_MES, ")
				.append("         ABONOS_6 ABONOS_MES, ")
				.append("         SALINI + CARGOS_" + value + " - ABONOS_" + value + " SALDO_FINAL, ")
				.append("         (SALINI + CARGOS_" + value + " - ABONOS_" + value + ") - SALINI VARIACION ")
				.append("       FROM CUENTA CU ").append(" WHERE CU.CUENTA IN ('1270', '1280', '1290')  ")
				.append("   AND SUBSTR(CU.CUENTA,4,1) = '0' ").append(" ORDER BY 1, 2 ASC");
		return sSql.toString();
	}

	private static String generaActivNoCirculanteTrimestre(Integer trimestre) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder cargosAnterior = new StringBuilder();
		StringBuilder abonosAnterior = new StringBuilder();
		StringBuilder cargosActual = new StringBuilder();
		StringBuilder abonosActual = new StringBuilder();

		int monthMaximo = getMonth(trimestre);
		int monthAnterior = monthMaximo - 3;
		
		if(trimestre == 1) {
			cargosAnterior.append(" 0 ");
			abonosAnterior.append(" 0 ");
		}

		for (int x = 1; x <= monthMaximo; x++) {

			if (x <= monthAnterior) {
				abonosAnterior.append("CU.ABONOS_").append(x);
				cargosAnterior.append("CU.CARGOS_").append(x);
				if (x < monthAnterior) {
					cargosAnterior.append(" + ");
					abonosAnterior.append(" + ");
				}

			}

			if (x > monthAnterior && x <= monthMaximo) {
				abonosActual.append("CU.ABONOS_").append(x);
				cargosActual.append("CU.CARGOS_").append(x);
				if (x < monthMaximo) {
					cargosActual.append(" + ");
					abonosActual.append(" + ");
				}
			}
		}

		sSql.append("SELECT 2 GRUPO, ").append("        CU.CUENTA, ").append("        CU.NOMCTA, ")
				.append("         0 SALDO, ").append("         0 CARGOS_MES, ").append("         0 ABONOS_MES, ")
				.append("         0 SALDO_FINAL, ").append("         0 VARIACION ").append("       FROM CUENTA CU ")
				.append(" WHERE CU.CUENTA >= '1210' ").append("   AND CU.CUENTA <= '1230' ")
				.append("   AND SUBSTR(CU.CUENTA,4,1) = '0'  ").append(" UNION ALL ").append(" SELECT 2 GRUPO, ")
				.append("        T2.CUENTA, ").append("        T2.NOMCTA, ").append("        T2.SALDO, ")
				.append("        T2.CARGOS_MES, ").append("        T2.CARGOS_MES, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) SALDO_FINAL, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) - T2.SALDO VARIACION ")
				.append("      FROM( ").append(" SELECT SUBSTR(T1.CUENTA,1,2)||'40' CUENTA, ")
				.append("        'Bienes Muebles' NOMCTA, ").append("        SUM(T1.SALINI + CARGOS - ABONOS)  SALDO, ")
				.append("        SUM(T1.CARGOS_MES) CARGOS_MES, ").append("        SUM(T1.ABONOS_MES) ABONOS_MES ")
				.append("      FROM ( ").append(" SELECT ").append(" 	CU.CUENTA, ").append(" 	CU.NOMCTA, ")
				.append(" 	CU.STACTA, ").append(" 	CU. SALINI, ").append( cargosAnterior).append( " CARGOS, ")
				.append(" 	" + abonosAnterior + " ABONOS, ").append(cargosActual).append(" CARGOS_MES, ")
				.append(abonosActual).append(" ABONOS_MES ").append("  ").append(" FROM ")
				.append(" 	CUENTA CU ").append(" WHERE CU.CUENTA  ")
				.append("   AND CU.CUENTA IN ('1242','1244','1246', '1241') ").append("   AND CU.SCTA = '' ")
				.append(" ) T1 ").append("        ").append(" GROUP BY SUBSTR(T1.CUENTA,1,2) ").append(" ) T2");
		sSql.append(UNION_ALL);
		sSql.append("SELECT 2 GRUPO, ").append("        CU.CUENTA, ").append("        CU.NOMCTA, ")
				.append("         0 SALDO, ").append("         0 CARGOS_MES, ").append("         0 ABONOS_MES, ")
				.append("         0 SALDO_FINAL, ").append("         0 VARIACION ").append("       FROM CUENTA CU ")
				.append(" WHERE CU.CUENTA = '1250' ").append("   AND SUBSTR(CU.CUENTA,4,1) = '0'");
		sSql.append(UNION_ALL);
		sSql.append("SELECT 2 GRUPO, ").append("        T2.CUENTA, ").append("        T2.NOMCTA, ")
				.append("        T2.SALDO, ").append("        T2.CARGOS_MES, ").append("        T2.CARGOS_MES, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) SALDO_FINAL, ")
				.append("        (T2.SALDO + T2.CARGOS_MES - T2.ABONOS_MES) - T2.SALDO VARIACION ")
				.append("      FROM( ").append(" SELECT T1.CUENTA CUENTA, ")
				.append("        'Depreciación, Deterioro y Amortización Acumulada de Bienes' NOMCTA, ")
				.append("        SUM(T1.SALINI + CARGOS - ABONOS)  SALDO, ")
				.append("        SUM(T1.CARGOS_MES) CARGOS_MES, ").append("        SUM(T1.ABONOS_MES) ABONOS_MES ")
				.append("      FROM ( ").append(" SELECT ").append(" 	CU.CUENTA, ").append(" 	CU.NOMCTA, ")
				.append(" 	CU.STACTA, ").append(" 	(CU. SALINI*-1) SALINI, ").append(" 	" + cargosAnterior + " CARGOS, ")
				.append(" 	" + abonosAnterior + " ABONOS, ").append(cargosActual).append(" CARGOS_MES, ")
				.append( abonosActual).append(" ABONOS_MES ").append("  ").append(" FROM ")
				.append(" 	CUENTA CU ").append(" WHERE CU.CUENTA  ").append("   AND CU.CUENTA ='1263' ")
				.append("   AND CU.SCTA = '' ").append(" ) T1 ").append(" GROUP BY T1.CUENTA ").append(" ) T2");
		sSql.append(UNION_ALL);
		sSql.append("SELECT 2 GRUPO, ").append("        CU.CUENTA, ").append("        CU.NOMCTA, ")
				.append("         SALINI SALDO, ").append(cargosActual).append(" CARGOS_MES, ")
				.append(abonosActual).append(" ABONOS_MES, ")
				.append("         SALINI + " + cargosActual + " - " + abonosActual + " SALDO_FINAL, ")
				.append("         (SALINI + " + cargosActual + " - " + abonosActual + ") - SALINI VARIACION ")
				.append("       FROM CUENTA CU ").append(" WHERE CU.CUENTA IN ('1270', '1280', '1290')  ")
				.append("   AND SUBSTR(CU.CUENTA,4,1) = '0' ").append(" ORDER BY 1, 2 ASC");
		return sSql.toString();
	}

	public static String getActivos(char tipo, Integer value) {
		StringBuilder sSql = new StringBuilder();
		
		if(tipo=='t') 
			sSql.append(activoCirculanteTrimestre(value)).append(UNION_ALL).append(generaActivNoCirculanteTrimestre(value));
		else
			sSql.append(activoCirculante(tipo, value)).append(UNION_ALL).append(generaActivNoCirculante(tipo, value));
		
		return sSql.toString();
	}

	private static int getMonth(Integer trimestre) {
		int month = 0;
		if (trimestre == 1)
			month = 3;
		else if (trimestre == 2)
			month = 6;
		else if (trimestre == 3)
			month = 9;
		else if (trimestre == 4)
			month = 12;
		else if (trimestre <= 0)
			month = 1;
		return month;
	}

}
