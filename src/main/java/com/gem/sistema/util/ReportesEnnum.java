package com.gem.sistema.util;

/**
 * @author Alfredo Neri
 * @Date 31/05/2021
 * @Changes Se crea Ennum para la administracion del catalogo de reportes
 */
public enum ReportesEnnum {

	REPORTE_022(
			"SELECT '\"'||EA.CONCEPTO||'\"|'||'\"'||TRIM(TO_CHAR(EA.ESTIMADO,'999,999,999,999,990.99'))||'\"|'||'\"'||TRIM(TO_CHAR(EA.AMPLIACION_REDUCCION, '999,999,999,999,990.99'))||'\"|'||'\"'||TRIM(TO_CHAR(EA.MODIFICADO, '999,999,999,999,990.99'))||'\"|'||'\"'||TRIM(TO_CHAR(EA.DEVENGADO, '999,999,999,999,990.99'))||'\"|'||'\"'||TRIM(TO_CHAR(EA.RECAUDADO, '999,999,999,999,990.99'))||'\"|'||'\"'||TRIM(TO_CHAR(EA.DIFERENCIA, '999,999,999,999,990.99'))||'\"' FROM GEMUSER.EAID EA WHERE EA.ID_SECTOR  =%s   AND EA.TRIMESTRE  =%s"),
	REPORTE_023(
			"SELECT '\"' || CONCEPTO || '\"|' ||TRIM(TO_CHAR(EA,'999999999999999990.99'))||'|'||TRIM(TO_CHAR(DEVENGADO,'999999999999999990.99'))||'|'||TRIM(TO_CHAR(RP,'999999999999999990.99')) FROM BALANCEPRE WHERE ID_SECTOR = %s AND TRIMESTRE = %s ORDER BY CONSECUTIVO");

	private String value;

	private ReportesEnnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
