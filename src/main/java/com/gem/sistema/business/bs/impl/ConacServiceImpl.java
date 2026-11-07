package com.gem.sistema.business.bs.impl;

import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.ConacService;
@Service(value = "conacService")
public class ConacServiceImpl implements ConacService{

	@Override
	public  String getQueryCncClasificacionObjetoGasto(Integer mes) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT ").append("  	* ").append("  FROM ").append("  	( ").append("  SELECT ")
				.append("  	GRUP, ").append("  	CLVGAS, ").append("  	NOMGAS, ").append("  	NVL(APROBADO, ")
				.append("  	0)APROBADO, ").append("  	NVL(AMPL_REDU, ").append("  	0)AMPL_REDU, ")
				.append("  	NVL(MODIFICADO, ").append("  	0)MODIFICADO, ").append("  	NVL(DEVENGADO, ")
				.append("  	0) DEVENGADO, ").append("  	NVL(PAGADO, ").append("  	0)PAGADO, ")
				.append("  	NVL(SUBEJERCICIO, ").append("  	0)SUBEJERCICIO, ")
				.append("  	ROW_NUMBER() OVER(PARTITION BY SUBSTR(CLVGAS, ").append("  	1, ").append("  	1), ")
				.append("  	GRUP) ROW_OF_TOTAL ").append("  FROM ").append("  	( ").append("  SELECT ")
				.append("  	GRUP, ").append("  	CLVGAS, ").append("  	NOMGAS, ").append("  	DECODE(GRUP, ")
				.append("  	1, ").append("  	DECODE(CLVGAS, ").append("  	'6000', ")
				.append("  	FN_GET_PAD_FTE3(1), ").append("  	APROBADO), ").append("  	APROBADO)APROBADO, ")
				.append("  	AMPL_REDU, ").append("  	DECODE(GRUP, ").append("  	1, ")
				.append("  	DECODE(CLVGAS, ").append("  	'6000', ").append("  	FN_GET_PAD_FTE3(1) , ")
				.append("  	MODIFICADO), ").append("  	MODIFICADO)MODIFICADO, ").append("  	DEVENGADO, ")
				.append("  	PAGADO, ").append("  	DECODE(GRUP, ").append("  	1, ").append("  	DECODE(CLVGAS, ")
				.append("  	'6000', ").append("  	FN_GET_PAD_FTE3(1), ").append("  	SUBEJERCICIO), ")
				.append("  	SUBEJERCICIO)SUBEJERCICIO ").append("  FROM ").append("  	( ").append("  SELECT ")
				.append("  	GRUP, ").append("  	CLVGAS, ").append("  	NOMGAS, ").append("  	DECODE(GRUP, ")
				.append("  	1, ").append("  	DECODE(CLVGAS, ").append("  	'6100', ")
				.append("  	FN_GET_PAD_FTE3(1), ").append("  	APROBADO), ").append("  	APROBADO)APROBADO, ")
				.append("  	AMPL_REDU, ").append("  	DECODE(GRUP, ").append("  	1, ")
				.append("  	DECODE(CLVGAS, ").append("  	'6100', ").append("  	FN_GET_PAD_FTE3(1) , ")
				.append("  	MODIFICADO), ").append("  	MODIFICADO)MODIFICADO, ").append("  	DEVENGADO, ")
				.append("  	PAGADO, ").append("  	DECODE(GRUP, ").append("  	1, ").append("  	DECODE(CLVGAS, ")
				.append("  	'6100', ").append("  	FN_GET_PAD_FTE3(1), ").append("  	SUBEJERCICIO), ")
				.append("  	SUBEJERCICIO)SUBEJERCICIO ").append("  FROM ").append("  	( ").append("  SELECT ")
				.append("  	GRUP, ").append("  	CLVGAS, ").append("  	NOMGAS, ").append("  	DECODE(GRUP, ")
				.append("  	2, ").append("  	DECODE(SUBSTR(CLVGAS, ").append("  	1, ").append("  	1), ")
				.append("  	'6', ").append("  	0, ").append("  	APROBADO), ").append("  	APROBADO)APROBADO, ")
				.append("  	DECODE(GRUP, ").append("  	2, ").append("  	DECODE(SUBSTR(CLVGAS, ").append("  	1, ")
				.append("  	1), ").append("  	'6', ").append("  	0, ").append("  	AMPL_REDU), ")
				.append("  	AMPL_REDU)AMPL_REDU, ").append("  	DECODE(GRUP, ").append("  	2, ")
				.append("  	DECODE(SUBSTR(CLVGAS, ").append("  	1, ").append("  	1), ").append("  	'6', ")
				.append("  	0, ").append("  	MODIFICADO), ").append("  	MODIFICADO)MODIFICADO, ")
				.append("  	DEVENGADO, ").append("  	PAGADO, ").append("  	DECODE(GRUP, ").append("  	2, ")
				.append("  	DECODE(SUBSTR(CLVGAS, ").append("  	1, ").append("  	1), ").append("  	'6', ")
				.append("  	0, ").append("  	SUBEJERCICIO), ").append("  	SUBEJERCICIO)SUBEJERCICIO ")
				.append("  FROM ").append("  	( ").append("  SELECT ").append("  	GRUP, ").append("  	CLVGAS, ")
				.append("  	NOMGAS, ").append("  	DECODE(GRUP, ").append("  	2, ")
				.append("  	DECODE(SUBSTR(CLVGAS, ").append("  	1, ").append("  	1), ").append("  	'4', ")
				.append("  	AMPL_REDU, ").append("  	APROBADO), ").append("  	APROBADO)APROBADO, ")
				.append("  	DECODE(GRUP, ").append("  	2, ").append("  	DECODE(SUBSTR(CLVGAS, ").append("  	1, ")
				.append("  	1), ").append("  	'4', ").append("  	APROBADO, ").append("  	AMPL_REDU), ")
				.append("  	AMPL_REDU)AMPL_REDU, ").append("  	MODIFICADO, ").append("  	DEVENGADO, ")
				.append("  	PAGADO, ").append("  	SUBEJERCICIO ").append("  FROM ").append("  	( ")
				.append("  SELECT ").append("  	GRUP, ").append("  	CLVGAS, ").append("  	NOMGAS, ")
				.append("  	APROBADO, ").append("  	AMPLIACION -REDUCCIONES AMPL_REDU, ")
				.append("  	(APROBADO + AMPLIACION -REDUCCIONES) MODIFICADO, ").append("  	DEVENGADO, ")
				.append("  	PAGADO, ").append("  	(APROBADO + AMPLIACION -REDUCCIONES) - DEVENGADO SUBEJERCICIO ")
				.append("  FROM ").append("  	(( ").append("  SELECT ").append("  	2 GRUP, ")
				.append("  	NAT.CLVGAS, ").append("  	NAT.NOMGAS, ")
				.append("  	SUM( PA.AUTO1_1 + PA.AUTO1_2 + PA.AUTO1_3 + PA.AUTO1_4 + PA.AUTO1_5 +  ")
				.append("  	PA.AUTO1_6 + PA.AUTO1_7 + PA.AUTO1_8 + PA.AUTO1_9 + PA.AUTO1_10 + PA.AUTO1_11 + ")
				.append("  	PA.AUTO1_12 ) APROBADO, ").append("  	SUM( PA.EJPA1_" + mes + ") PAGADO, ")
				.append("  	SUM( PA.REDU1_" + mes + " ) REDUCCIONES, ")
				.append("  	SUM( PA.TOEJE1_" + mes + " ) DEVENGADO, ")
				.append("  	SUM( PA.AMPLI1_" + mes + " ) AMPLIACION ").append("  FROM ").append("  	NATGAS NAT ")
				.append("  		LEFT JOIN ").append("  		PASO PA ").append("  		ON ")
				.append("  		NAT.CLVGAS = PA.PARTIDA AND ").append("  	NAT.IDSECTOR = PA.IDSECTOR AND ")
				.append("  	PA.IDSECTOR = 2 AND ").append("  	(SUBSTR(PA.PROGRAMA, ").append("  	15, ")
				.append("  	1)>='3' AND ").append("  	SUBSTR(PA.PROGRAMA, ").append("  	15, ")
				.append("  	1)<='5') AND ").append("  	PA.PROGRAMA<>'040401010101265' ").append("  WHERE ")
				.append("  	SUBSTR(NAT.CLVGAS, ").append("  	3, ").append("  	2) = '00' ").append("  GROUP BY ")
				.append("  	NAT.CLVGAS, ").append("  	NAT.NOMGAS ").append("  ORDER BY ")
				.append("  	NAT.CLVGAS ASC) ").append("  UNION ").append("  	ALL ( ").append("  SELECT ")
				.append("  	1 GRUP, ").append("  	NAT.CLVGAS, ").append("  	NAT.NOMGAS, ")
				.append("  	SUM( PA.AUTO1_1 + PA.AUTO1_2 + PA.AUTO1_3 + PA.AUTO1_4 + PA.AUTO1_5 +  ")
				.append("  	PA.AUTO1_6 + PA.AUTO1_7 + PA.AUTO1_8 + PA.AUTO1_9 + PA.AUTO1_10 + PA.AUTO1_11 +")
				.append("  	PA.AUTO1_12 ) APROBADO, ").append("  	SUM( PA.EJPA1_" + mes + ") PAGADO, ")
				.append("  	SUM( PA.REDU1_" + mes + " ) REDUCCIONES, ")
				.append("  	SUM( PA.TOEJE1_" + mes + " ) DEVENGADO, ")
				.append("  	SUM( PA.AMPLI1_" + mes + " ) AMPLIACION ").append("  FROM ").append("  	NATGAS NAT ")
				.append("  		LEFT JOIN ").append("  		PASO PA ").append("  		ON ")
				.append("  		NAT.CLVGAS = PA.PARTIDA AND ").append("  	NAT.IDSECTOR = PA.IDSECTOR AND ")
				.append("  	PA.IDSECTOR = 2 AND ").append("  	SUBSTR(PA.PROGRAMA, ").append("  	15, ")
				.append("  	1) IN ('1', ").append("  	'2', ").append("  	'5') ").append("  WHERE ")
				.append("  	SUBSTR(NAT.CLVGAS, ").append("  	3, ").append("  	2) = '00' ").append("  GROUP BY ")
				.append("  	NAT.CLVGAS, ").append("  	NAT.NOMGAS ").append("  ORDER BY ")
				.append("  	NAT.CLVGAS ASC) ) ) ").append("  ORDER BY ").append("  	GRUP ASC))))) ").append("  ");
		
		System.out.println(sSql.toString());
		return sSql.toString();
	}
	
	}


