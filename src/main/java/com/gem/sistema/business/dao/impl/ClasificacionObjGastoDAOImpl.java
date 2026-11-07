package com.gem.sistema.business.dao.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.CallSpDAO;
import com.gem.sistema.business.dao.ClasificacionObjGastoDAO;

@Repository
public class ClasificacionObjGastoDAOImpl implements ClasificacionObjGastoDAO {

	private static final String NAME_PROCEDURE = "SP_EXPORTA_CUENTAS";

	@Autowired
	private CallSpDAO callSpDAO;

	SqlParameterSource parameters;
	Map<String, Object> out;

	@Override
	public String exportTXT(Integer idSector, Integer trimestre, String nameFile) {

		StringBuilder sSql = new StringBuilder();
		int mesFinal = trimestre * 3;

		String auto = "SUM(";
		String ejpa = "SUM(";
		String redu = "SUM(";
		String ejxpa = "SUM(";
		String ampli = "SUM(";

		for (int y = 1; y <= mesFinal; y++) {
			auto = auto + " PA.AUTO1_" + y + " +";
			ejpa = ejpa + " PA.EJPA1_" + y + " +";
			redu = redu + " PA.REDU1_" + y + " +";
			ejxpa = ejxpa + " PA.EJXPA1_" + y + " +";
			ampli = ampli + " PA.AMPLI1_" + y + " +";
		}

		auto = auto.substring(0, auto.length() - 2) + " ) APROBADO, ";
		ejpa = ejpa.substring(0, ejpa.length() - 2) + " ) PAGADO ";
		redu = redu.substring(0, redu.length() - 2) + " ) REDUCCIONES, ";
		ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " ) DEVENGADO, ";
		ampli = ampli.substring(0, ampli.length() - 2) + " ) AMPLIACION, ";

		sSql.append(
				"SELECT TEXT FROM( SELECT 1 CON, '\"'||'Gasto No Etiquetado'||'\"|\"'||TRIM(TO_CHAR(SUM(T1.APROBADO),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM((T1.AMPLIACION -T1.REDUCCIONES)),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM((T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES)),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM(T1.DEVENGADO),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM(T1.PAGADO),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM((T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) - T1.DEVENGADO),'999999999999990.99'))||'\"' TEXT ")
				.append("FROM (SELECT NAT.CLVGAS,NAT.NOMGAS, ").append(auto).append(ampli).append(redu).append(ejxpa)
				.append(ejpa)
				.append("FROM PASO PA INNER JOIN NATGAS NAT ON NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR AND SUBSTR(PA.PARTIDA, 3, 2) = '00'  ")
				.append("WHERE  PA.IDSECTOR = ").append(idSector)
				//.append(" AND SUBSTR(PA.PARTIDA,4,1)<>'0' AND ")
				.append(" AND SUBSTR(PA.PROGRAMA,13,3)>='101' AND SUBSTR(PA.PROGRAMA,13,3)<='113' OR ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='201' AND SUBSTR(PA.PROGRAMA,13,3)<='202' ")
				.append("GROUP BY NAT.CLVGAS,NAT.NOMGAS ORDER BY NAT.CLVGAS ASC ) T1 UNION ALL ")
				.append("SELECT 2, '\"'||T1.CLVGAS|| ' ' ||T1.NOMGAS||'\"|\"'|| ")
				.append("TRIM( TO_CHAR(T1.APROBADO,'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR((T1.AMPLIACION -T1.REDUCCIONES),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR((T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(T1.DEVENGADO,'999999999999990.99')) ||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(T1.PAGADO,'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(((T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) - T1.DEVENGADO),'999999999999990.99')) ||'\"' ")
				.append("FROM (SELECT	NAT.CLVGAS, NAT.NOMGAS, ").append(auto).append(ampli).append(redu).append(ejxpa)
				.append(ejpa)
				.append("FROM PASO PA INNER JOIN NATGAS NAT ON NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR AND SUBSTR(PA.PARTIDA, 3, 2) = '00' ")
				.append("WHERE  PA.IDSECTOR = ").append(idSector).append(" AND SUBSTR(PA.PARTIDA,4,1)<>'0' AND ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='101' AND SUBSTR(PA.PROGRAMA,13,3)<='113' OR ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='201' AND SUBSTR(PA.PROGRAMA,13,3)<='202' ")
				.append("GROUP BY NAT.CLVGAS,NAT.NOMGAS ORDER BY NAT.CLVGAS ASC ) T1 UNION ALL ")
				.append("SELECT 3, '\"'||'Gasto Etiquetado'||'\"|\"'||TRIM(TO_CHAR(SUM(T2.APROBADO),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM((T2.AMPLIACION -T2.REDUCCIONES)),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM((T2.APROBADO + T2.AMPLIACION -T2.REDUCCIONES)),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM(T2.DEVENGADO),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM(T2.PAGADO),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM((T2.APROBADO + T2.AMPLIACION -T2.REDUCCIONES) - T2.DEVENGADO),'999999999999990.99'))||'\"' ")
				.append("FROM (SELECT NAT.CLVGAS, NAT.NOMGAS, ").append(auto).append(ampli).append(redu).append(ejxpa)
				.append(ejpa)
				.append("FROM PASO PA INNER JOIN NATGAS NAT ON NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR ")
				.append("WHERE  PA.IDSECTOR = ").append(idSector)
				//.append(" AND SUBSTR(PA.PARTIDA,4,1)<>'0' AND ")
				.append(" AND SUBSTR(PA.PROGRAMA,13,3)>='203' AND SUBSTR(PA.PROGRAMA,13,3)<='225' OR ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='114' AND SUBSTR(PA.PROGRAMA,13,3)<='115' ")
				.append("GROUP BY NAT.CLVGAS,NAT.NOMGAS ORDER BY NAT.CLVGAS ASC ) T2 UNION ALL ")
				.append("SELECT 4, '\"'||T2.CLVGAS|| ' ' ||T2.NOMGAS||'\"|\"'|| ")
				.append("TRIM( TO_CHAR(T2.APROBADO,'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR((T2.AMPLIACION -T2.REDUCCIONES),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR((T2.APROBADO + T2.AMPLIACION -T2.REDUCCIONES),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(T2.DEVENGADO,'999999999999990.99')) ||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(T2.PAGADO,'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(((T2.APROBADO + T2.AMPLIACION -T2.REDUCCIONES) - T2.DEVENGADO),'999999999999990.99')) ||'\"' ")
				.append("FROM (SELECT	NAT.CLVGAS, NAT.NOMGAS, ").append(auto).append(ampli).append(redu).append(ejxpa)
				.append(ejpa)
				.append("FROM PASO PA INNER JOIN NATGAS NAT ON NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR ")
				.append("WHERE  PA.IDSECTOR = ").append(idSector).append(" AND SUBSTR(PA.PARTIDA,4,1)<>'0' AND ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='203' AND SUBSTR(PA.PROGRAMA,13,3)<='225' OR ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='114' AND SUBSTR(PA.PROGRAMA,13,3)<='115' ")
				.append("GROUP BY NAT.CLVGAS,NAT.NOMGAS " + "ORDER BY NAT.CLVGAS ASC ) T2 ) TAB ORDER BY CON");

		parameters = new MapSqlParameterSource().addValue("i_header", StringUtils.EMPTY)
				.addValue("i_query", sSql.toString()).addValue("i_file_name", nameFile);
		out = this.callSpDAO.call(NAME_PROCEDURE, parameters);
		return out.get("O_FULL_FILE_PATH").toString();
	}

	public CallSpDAO getCallSpDAO() {
		return callSpDAO;
	}

	public void setCallSpDAO(CallSpDAO callSpDAO) {
		this.callSpDAO = callSpDAO;
	}

}
