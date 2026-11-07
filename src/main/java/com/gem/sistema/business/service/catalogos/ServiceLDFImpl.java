package com.gem.sistema.business.service.catalogos;

import static com.gem.sistema.util.EstadoAnaliticoActivo.getActivos;
import static com.gem.sistema.util.FlujoEfectivoUtil.getFlujosEfectivo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.dto.TotalFlujoEfectivo;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;

@Service(value = "informeAnaliticoDeudaPublicaService")
public class ServiceLDFImpl implements ServiceLDF {

	@Autowired
	private ConctbRepository conctbRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public String getQuery(Integer mes) {

		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		Integer month = getMonth(mes);
		for (int i = 1; i <= month; i++) {
			abonos.append("CU.ABONOS_").append(i);
			cargos.append("CU.CARGOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
			}
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT T1.GRUPO, ").append("       T1.CUENTA, ").append("       T1.SCTA, ")
				.append("       T1.SSCTA, ").append("       T1.SSSCTA, ").append("       T1.SSSSCTA, ")
				.append("       DECODE(T1.SSSCTA, '', T1.NOMCTA, '') NOMCTA, ").append("       T1.SALINI, ")
				.append("       T1.ABONOS, ").append("       T1.CARGOS, ").append("       T1.OTROS_AJUSTES, ")
				.append("       (T1.SALINI + (T1.ABONOS - T1.CARGOS)) SALDO_FINAL_PERIODO, ")
				.append("       T1.PAGO_INTERESES, ").append("       T1.PAGO_COMISION ")
				.append("     FROM(SELECT  1 GRUPO, ").append("                 CU.CUENTA, ")
				.append("       			 CU.SCTA, ").append("			     CU.SSCTA, ")
				.append("			     CU.SSSCTA, ").append("			     CU.SSSSCTA, ")
				.append("			     CU.NOMCTA, ").append("			     CU.SALINI, ")
				.append("			     (" + abonos + ") ABONOS, ").append("			     (" + cargos + ") CARGOS, ")
				.append("			     0 OTROS_AJUSTES, ").append("			     0 PAGO_INTERESES, ")
				.append("			     0 PAGO_COMISION ").append("			    FROM CUENTA CU ")
				.append("			WHERE CU.CUENTA = '2131'  ").append("			  AND CU.SSCTA    <> '' ")
				.append("			ORDER BY  CU.CUENTA, ").append("      				  CU.SCTA, ")
				.append("					  CU.SSCTA, ").append("					  CU.SSSCTA, ")
				.append("					  CU.SSSSCTA ").append("			 ASC ").append("			) T1 ")
				.append("UNION ALL ").append("SELECT T1.GRUPO, ").append("       T1.CUENTA, ")
				.append("       T1.SCTA, ").append("       T1.SSCTA, ").append("       T1.SSSCTA, ")
				.append("       T1.SSSSCTA, ").append("       DECODE(T1.SSSCTA, '', T1.NOMCTA, '') NOMCTA, ")
				.append("       T1.SALINI, ").append("       T1.ABONOS, ").append("       T1.CARGOS, ")
				.append("       T1.OTROS_AJUSTES, ")
				.append("       (T1.SALINI + (T1.ABONOS - T1.CARGOS)) SALDO_FINAL_PERIODO, ")
				.append("       T1.PAGO_INTERESES, ").append("       T1.PAGO_COMISION ")
				.append("     FROM(SELECT 2 GRUPO, ").append("                 CU.CUENTA, ")
				.append("       			 CU.SCTA, ").append("			     CU.SSCTA, ")
				.append("			     CU.SSSCTA, ").append("			     CU.SSSSCTA, ")
				.append("			     CU.NOMCTA, ").append("			     CU.SALINI, ")
				.append("			     (" + abonos + ") ABONOS, ").append("			     (" + cargos + ") CARGOS, ")
				.append("			     0 OTROS_AJUSTES, ").append("			     0 PAGO_INTERESES, ")
				.append("			     0 PAGO_COMISION ").append("			    FROM CUENTA CU ")
				.append("			WHERE CU.CUENTA = '2233'  ").append("			  AND CU.SSCTA    <> '' ")
				.append("			ORDER BY  CU.CUENTA, ").append("      				  CU.SCTA, ")
				.append("					  CU.SSCTA, ").append("					  CU.SSSCTA, ")
				.append("					  CU.SSSSCTA ").append("			 ASC ").append("			) T1 ")
				.append("UNION ALL ").append("SELECT T1.GRUPO, ").append("       T1.CUENTA, ")
				.append("       T1.SCTA, ").append("       T1.SSCTA, ").append("       T1.SSSCTA, ")
				.append("       T1.SSSSCTA, ").append("       DECODE(T1.SSSCTA, '', T1.NOMCTA, '') NOMCTA, ")
				.append("       T1.SALINI, ").append("       T1.ABONOS, ").append("       T1.CARGOS, ")
				.append("       T1.PAGO_INTERESES, ")
				.append("       (T1.SALINI + (T1.ABONOS - T1.CARGOS)) REDUCCIONES, ")
				.append("       T1.PAGO_INTERESES, ").append("       T1.PAGO_COMISION ")
				.append("     FROM(SELECT 3 GRUPO, ").append("                 CU.CUENTA, ")
				.append("       			 CU.SCTA, ").append("			     CU.SSCTA, ")
				.append("			     CU.SSSCTA, ").append("			     CU.SSSSCTA, ")
				.append("			     CU.NOMCTA, ").append("			     CU.SALINI, ")
				.append("			     (" + abonos + ") ABONOS, ").append("			     (" + cargos + ") CARGOS, ")
				.append("			     0 OTROS_AJUSTES, ").append("			     0 PAGO_INTERESES, ")
				.append("			     0 PAGO_COMISION ").append("			    FROM CUENTA CU ")
				.append("			WHERE CU.CUENTA >= '2111'  ").append("			  AND CU.CUENTA <= '2119' ")
				.append("			  AND CU.SSCTA    <> '' ").append("			ORDER BY  CU.CUENTA, ")
				.append("      				  CU.SCTA, ").append("					  CU.SSCTA, ")
				.append("					  CU.SSSCTA, ").append("					  CU.SSSSCTA ")
				.append("			 ASC ").append("			) T1");
		System.out.println(sSql);
		return sSql.toString();
	}
	
	@Override
	public String getQueryInfoem(Integer mes) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargosAnt = new StringBuilder();
		StringBuilder abonosAnt = new StringBuilder();
		Integer month = getMonth(mes);
		Integer monthB = month == 3 ? 1 : month-3;
		for (int i = 1; i <= monthB; i++) {
			abonosAnt.append("ABONOS_").append(i);
			cargosAnt.append("CARGOS_").append(i);
			if (i < monthB) {
				cargosAnt.append(" + ");
				abonosAnt.append(" + ");
			}
		}
		
		for (int i = 1; i <= month; i++) {
			abonos.append("ABONOS_").append(i);
			cargos.append("CARGOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
			}
		}
		
		StringBuilder sSql = new StringBuilder();
		sSql.append("WITH cta AS ( ")
		.append("SELECT ")
		.append("SALINI-CARGOS_ANT+ABONOS_ANT SALINI, ")
		.append("0.00 DISPO, ")
		.append("0.00 AMORT, ")
		.append("0.00 REVALU, ")
		.append("SALINI-CARGOS_ACT+ABONOS_ACT SALFIN, ")
		.append("0.00 INTERE, ")
		.append("0.00 COMISI, ")
		.append("R.v AS ID ")
		.append("FROM ")
		.append("( ")
		.append("SELECT ")
		.append("SALINI, ")
		.append(cargosAnt+" CARGOS_ANT, ")
		.append(abonosAnt+" ABONOS_ANT, ")
		.append(abonos+" ABONOS_ACT, ")
		.append(cargos+" CARGOS_ACT ")
		.append("FROM ")
		.append("CUENTA ")
		.append("WHERE ")
		.append("CUENTA = '2100') ")
		.append("CROSS JOIN ")
		.append("( SELECT v FROM(VALUES(1), (2)) AS tbl(v)) R) ")
		.append("SELECT  ")
		.append("CASE WHEN ID = 1 THEN 10 ELSE 11 END ORDEN, 1 NIVEL, ")
		.append("CASE WHEN ID = 1 THEN '2. Otros Pasivos' ELSE '3. Total de la Deuda Pública y Otros Pasivos (3=1+2)' END NOMCTA, ")
		.append("SALINI,DISPO,AMORT,REVALU,SALFIN,INTERE,COMISI ")
		.append("FROM cta ")
		.append("UNION ALL ")
		.append("SELECT * FROM ( VALUES ")
		.append("(1, 1, '1. Deuda Pública (1=A+B)', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 2, 'A. Corto Plazo (A=a1+a2+a3)', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
		.append("(3, 3, 'a1) Instituciones de Crédito', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
		.append("(4, 3, 'a2) Títulos y Valores', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
		.append("(5, 3, 'a3) Arrendamientos Financieros', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
		.append("(6, 2, 'B. Largo Plazo (B=b1+b2+b3)', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
		.append("(7, 3, 'b1) Instituciones de Crédito', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
		.append("(8, 3, 'b2) Títulos y Valores', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
		.append("(9, 3, 'b3) Arrendamientos Financieros', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
		.append("(12, 1, '4. Deuda Contingente 1 (Informativo)', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
		.append("(13, 2, 'A. Deuda Contingente 1', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
		.append("(14, 2, 'B. Deuda Contingente 2', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
		.append("(15, 2, 'C. Deuda Contingente XX', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
		.append("(16, 1, '5. Valor de Instrumentos Bono Cupón Cero 2 (Informativo)', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
		.append("(17, 2, 'A. Instrumento Bono Cupón Cero 1', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
		.append("(18, 2, 'B. Instrumento Bono Cupón Cero 2', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
		.append("(19, 2, 'C. Instrumento Bono Cupón Cero XX', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00) ")
		.append(") AS TEMP (ORDEN, NIVEL, NOMCTA, SALINI, DISPO, AMORT, REVALU, SALFIN, INTERE, COMISI) ")
		.append("ORDER BY 1");
		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String queryIngresosDetallado(Integer mes) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		Integer month = getMonth(mes);

		for (int i = 1; i <= month; i++) {
			abonos.append("CU.ABONOS_").append(i);
			cargos.append("CU.CARGOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
			}
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT FN_GET_TOKEN(0,'|', FN_GET_DETAILS_LDF(TRIM(T4.NOMCTA))) GRUPO,")
				.append("       FN_GET_TOKEN(1,'|', FN_GET_DETAILS_LDF(TRIM(T4.NOMCTA))) NOMCTA,")
				.append("       T4.ESTIMADO,").append("       T4.AMPL_REDU,").append("       T4.MODIFICADO,")
				.append("       T4.DEVENGADO,").append("       T4.RECAUDADO").append("    FROM (")
				.append("SELECT T3.NOMCTA,").append("       T3.ESTIMADO,").append("       T3.AMPL_REDU,")
				.append("       T3.MODIFICADO,").append("       T3.DEVENGADO,").append("       T3.RECAUDADO")
				.append("  FROM (").append("SELECT T2.* FROM ").append("(SELECT T1.SCTA,").append("       T1.SSCTA,")
				.append("       T1.SSSCTA,").append("       T1.SSSSCTA,").append("       T1.NOMCTA,")
				.append("       SUM(DECODE(T1.CUENTA , '8110', T1.ESTIMADO, 0.00)) ESTIMADO,")
				.append("       SUM(DECODE(T1.CUENTA , '8110',(T1.AMPLIACIONES - T1.REDUCCIONES), 0.00 )) AMPL_REDU,")
				.append("       SUM(DECODE(T1.CUENTA , '8110',T1.ESTIMADO + (T1.AMPLIACIONES - T1.REDUCCIONES ), 0.00)) MODIFICADO,")
				.append("       SUM(DECODE(T1.CUENTA , '8140', T1.ESTIMADO,0)) DEVENGADO,")
				.append("       SUM(DECODE(T1.CUENTA , '8150', T1.ESTIMADO,0)) RECAUDADO")
				.append("    FROM (SELECT CU.CUENTA,").append("                 CU.SCTA,")
				.append("                 CU.SSCTA,").append("                 CU.SSSCTA,")
				.append("                 CU.SSSSCTA,").append("                 CU.NOMCTA,")
				.append("                 CU.SALINI ESTIMADO,").append("                " + abonos + "  AMPLIACIONES,")
				.append("                 " + cargos + "  REDUCCIONES").append("              FROM CUENTA CU")
				.append("         WHERE CU.CUENTA IN ('8110', '8140', '8150')")
				.append("          AND (SUBSTR(CU.SCTA,7,4) >= '4110'")
				.append("            AND SUBSTR(CU.SCTA,7,4) <= '4170'")
				.append("            AND SUBSTR(CU.SCTA, 10,1) = '0'   ").append("              )")
				.append("         OR (CU.CUENTA  IN ('8110', '8140', '8150')")
				.append("            AND SUBSTR(CU.SCTA,7,4) IN ('4211', '4212', '4213', '4214', '4399')")
				.append("            AND CU.SSCTA = ''").append("            ) ")
				.append("        OR (CU.CUENTA IN ('8110', '8140', '8150')")
				.append("           AND SUBSTR(CU.SCTA,7,4) = '4211'")
				.append("           AND SUBSTR(CU.SSCTA,15) IN ('1', '3')")
				.append("           AND SUBSTR(CU.SSSCTA,4)>= '1'").append("           AND SUBSTR(CU.SSSCTA,4)<= '6'")
				.append("           AND SUBSTR(CU.SSSSCTA,3) IN ('1', '6')").append("          )")
				.append("        OR (CU.CUENTA IN ('8110', '8140', '8150')")
				.append("           AND SUBSTR(CU.SCTA,7,4) = '4211'")
				.append("           AND SUBSTR(CU.SSCTA,15) = '1'").append("           AND SUBSTR(CU.SSSCTA,4)= '6'")
				.append("           AND CU.SSSSCTA = ''").append("          )").append("")
				.append("       OR(CU.CUENTA  IN ('8110', '8140', '8150')")
				.append("          AND SUBSTR(CU.SCTA,7,4) = '4214'")
				.append("          AND SUBSTR(CU.SSCTA,15) IN ('1', '3')")
				.append("          AND SUBSTR(CU.SSSCTA,4)>= '1'").append("          AND SUBSTR(CU.SSSCTA,4)<= '6'")
				.append("         AND SUBSTR(CU.SSSSCTA,3) IN ('1', '4')").append("        )").append(" ").append("")
				.append(" OR(CU.CUENTA  IN ('8110', '8140', '8150')").append("      AND SUBSTR(CU.SCTA,7,4) = '4212'")
				.append("      AND SUBSTR(CU.SSCTA,15) = '1'").append("      AND SUBSTR(CU.SSSCTA,4)>= '1'")
				.append("      AND SUBSTR(CU.SSSCTA,4)<= '8'").append("      AND CU.SSSSCTA = ''").append(")")
				.append("").append("").append("OR(CU.CUENTA IN ('8110', '8140', '8150')")
				.append("           AND SUBSTR(CU.SCTA,7,4) = '4213'")
				.append("           AND CU.SSCTA  ='000000000000001'").append("           AND SUBSTR(CU.SSSCTA,4)= '1'")
				.append("           AND CU.SSSSCTA = ''           ").append("       )")
				.append("  OR   (CU.CUENTA IN ('8110', '8140', '8150')")
				.append("           AND SUBSTR(CU.SCTA,7,4) = '4213'")
				.append("           AND CU.SSCTA  ='000000000000003'           ")
				.append("           AND CU.SSSCTA = '0001'").append("           AND CU.SSSSCTA = '001'")
				.append("       )").append("  ").append("OR(CU.CUENTA IN ('8110', '8140', '8150')")
				.append("      AND SUBSTR(CU.SCTA,7,4) = '4399'").append("      AND SUBSTR(CU.SSCTA,15) = '3'")
				.append("      AND SUBSTR(CU.SSSCTA,4)= '1'").append("      AND SUBSTR(CU.SSSSCTA,3) ='1' )").append(

						" ORDER BY CU.CUENTA, CU.SCTA, CU.SSCTA ASC")
				.append("  ) T1").append(" GROUP BY  T1.SCTA,").append("          T1.SSCTA,")
				.append("          T1.SSSCTA,").append("          T1.SSSSCTA,").append("          T1.NOMCTA")
				.append(" ORDER BY T1.SCTA ASC ) T2").append(")T3").append(" UNION ALL ").append("SELECT  ").append("")
				.append("       T2.NOMCTA,").append("       SUM(T2.ESTIMADO),")
				.append("       SUM(T2.AMPL_REDU) AMPL_REDU,").append("       SUM(T2.MODIFICADO) MODIFICADO,")
				.append("       SUM(T2.DEVENGADO) DEVENGADO,").append("       SUM(T2.RECAUDADO)RECAUDADO")
				.append("     FROM (").append("SELECT ").append("       T1.NOMCTA,")
				.append("       DECODE(T1.CUENTA, '8110', SUM(ESTIMADO), 0) ESTIMADO,")
				.append("       DECODE(T1.CUENTA, '8110', SUM(AMPLIACIONES - REDUCCIONES), 0) AMPL_REDU,")
				.append("       DECODE(T1.CUENTA, '8110', SUM(ESTIMADO + (AMPLIACIONES - REDUCCIONES)), 0) MODIFICADO,")
				.append("       DECODE(T1.CUENTA, '8140', SUM(ESTIMADO), 0) DEVENGADO,")
				.append("       DECODE(T1.CUENTA, '8150', SUM(ESTIMADO), 0) RECAUDADO").append("   FROM  (")
				.append(" SELECT CU.CUENTA,").append("       CU.SCTA,").append("       CU.SSCTA,")
				.append("       CU.SSSCTA,").append("       CU.SSSSCTA,")
				.append("       DECODE(TRIM(CU.NOMCTA),'Fiscalizacion', 'Otros Incentivos Economicos', 'Otros Incentivos', 'Otros Incentivos Economicos','Seguro Popular', 'Otros Convenios y Subsidios', 'FORTASEG','Otros Convenios y Subsidios','Colegio de Estudios Cientificos y Tecnologicos del Estado de Mexico', 'Otros Convenios y Subsidios', 'Universidad Autonoma del Estado de Mexico', 'Otros Convenios y Subsidios', 'Pasivos que se generen como Resultado de la Contratacion de Creditos, en Terminos del Titulo Octavo del Codigo Financiero del Estado de Mexico y Municipios', 'Ingresos Derivados de Financiamiento', 'Pasivos que se generen como Resultado de la Contratacion de Creditos, en Terminos del Titulo Octavo del Codigo Financiero del Estado de Mexicoy Municipios.', 'Ingresos Derivados de Financiamiento', CU.NOMCTA) NOMCTA,")
				.append("       CU.SALINI ESTIMADO,").append("       " + abonos + "  AMPLIACIONES,")
				.append("       " + cargos + "  REDUCCIONES").append("     FROM CUENTA CU")
				.append(" WHERE (CU.CUENTA  IN ('8110', '8140', '8150')")
				.append("      AND SUBSTR(CU.SCTA,7,4)  = '4214' ").append("      AND SUBSTR(CU.SSCTA,15) =  '1'")
				.append("      AND SUBSTR(CU.SSSCTA,4) IN ('3', '2')").append("      AND CU.SSSSCTA = ''")
				.append("      )").append("      OR(CU.CUENTA  IN ('8110', '8140', '8150')")
				.append("      AND SUBSTR(CU.SCTA,7,4)  =   '4223'").append("      AND CU.SSCTA  =  '000000000000001'")
				.append("      AND SUBSTR(CU.SSSCTA,4) IN ('1', '3')").append("      AND CU.SSSSCTA = '001'").append("")
				.append("").append("      )").append("     OR(CU.CUENTA IN ('8110', '8140', '8150')")
				.append("      AND SUBSTR(CU.SCTA,7,4) = '4399'").append("      AND SUBSTR(CU.SSCTA,15) = '3'")
				.append("      AND SUBSTR(CU.SSSCTA,4)= '2'").append("      AND CU.SSSSCTA =''").append(")").append("")
				.append("").append(" ORDER BY CU.CUENTA,").append("         CU.SCTA,").append("         CU.SSCTA,")
				.append("         CU.SSSCTA,").append("         CU.SSSSCTA").append("      ASC").append("      ")
				.append(") T1").append("").append("").append(" GROUP BY  T1.CUENTA,").append("          T1.NOMCTA")
				.append("          )T2").append(" GROUP BY T2.NOMCTA").append(") T4").append("")
				.append(" ORDER BY GRUPO ASC");
		System.out.println(sSql);
		return sSql.toString();
	}

	private int getMonth(Integer trimestre) {
		int month = 0;
		if (trimestre == 1)
			month = 3;
		else if (trimestre == 2)
			month = 6;
		else if (trimestre == 3)
			month = 9;
		else if (trimestre == 4)
			month = 12;
		else if( trimestre <= 0)
			month = 1;
		return month;
	}

	private int getMonthBefore(Integer trimestre) {
		int month = 0;
		if (trimestre == 1)
			month = 1;
		else if (trimestre == 2)
			month = 3;
		else if (trimestre == 3)
			month = 6;
		else if (trimestre == 4)
			month = 9;
		return month;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	@Override
	public Conctb getAllConctb(Integer idSector) {
		return this.conctbRepository.findByIdsector(idSector);
	}

	@Override
	public String getQueryActivo(Integer trimestre) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("CU.ABONOS_").append(i);
			cargos.append("CU.CARGOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
			}
		}
		sSql.append("SELECT T1.GRUPO, ").append("       T1.CUENTA, ").append("       T1.NOMCTA, ").append(
				"       DECODE(T1.STACTA, 'D', T1.SALINI + T1.CARGOS - T1.ABONOS, T1.SALINI + T1.ABONOS - T1.CARGOS) SALDO_MES, ")
				.append("       T1.SALINI ").append("     FROM ( ")
				.append("SELECT DECODE(SUBSTR(CU.CUENTA, 1,3), '111', 1, '112', 2,  '113', 3, '114', 4, '116',5, '119', 6, 7) GRUPO, ")
				.append("       CU.CUENTA, ").append("       CU.NOMCTA, ").append("       CU.STACTA, ")
				.append("       (" + cargos + ") CARGOS, ").append("       (" + abonos + " ) ABONOS, ")
				.append("       CU.SALINI ").append("     FROM CUENTA CU ")
				.append("WHERE SUBSTR(CU.CUENTA,1,2) = '11' ").append("  AND SUBSTR(CU.CUENTA,4) <> '0' ")
				.append("  AND CU.SCTA = '' ").append("  AND CU.CUENTA <> '1151' ")
				.append("  OR( SUBSTR(CU.CUENTA,1,2) = '12' ").append("  AND SUBSTR(CU.CUENTA,4) = '0' ")
				.append("  AND CUENTA <> '1200' ").append("  ) ").append("  ) T1");
		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String getQueryPasivo(Integer trimestre) {
		StringBuilder sSql = new StringBuilder();

		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("CU.ABONOS_").append(i);
			cargos.append("CU.CARGOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
			}
		}
		sSql.append("SELECT T1.GRUPO, ").append("       T1.CUENTA, ").append("       T1.NOMCTA, ").append(
				"       DECODE(T1.STACTA, 'A', ( T1.SALINI + T1.ABONOS - T1.CARGOS),(T1.SALINI + T1.CARGOS - T1.ABONOS) ) MES_SAL, ")
				.append("       T1.SALINI ").append("    FROM ( ")
				.append("SELECT DECODE(SUBSTR(CU.CUENTA, 1,3), '211', 1, '212', 2,  '215', 3, '216', 4, '216',5, '217', 6,  '219', 7, 8) GRUPO, ")
				.append("       CU.CUENTA, ").append("       CU.NOMCTA, ").append("       CU.STACTA, ")
				.append("        ( " + cargos + ") CARGOS, ").append("       (  " + abonos + " ) ABONOS, ")
				.append("       CU.SALINI ").append("     FROM CUENTA CU ")
				.append("WHERE SUBSTR(CU.CUENTA,1,2) = '21' ").append("  AND SUBSTR(CU.CUENTA,4) <> '0' ")
				.append("  AND CU.SCTA = '' ").append("OR( SUBSTR(CU.CUENTA,1,2) = '22' ")
				.append("    AND SUBSTR(CU.CUENTA,4) = '0' ").append("    AND CUENTA <> '2200' ").append("  ) ")
				.append(" ").append("UNION ALL ")
				.append("SELECT DECODE(SUBSTR(CUENTA,1,2), '31', 9,'32', 10,11) GRUPO, ").append("       CU.CUENTA, ")
				.append("       CU.NOMCTA, ").append("       CU.STACTA, ")
				.append("       (CU.CARGOS_1 + CU.CARGOS_2 + CU.CARGOS_3 + CU.CARGOS_4 + CU.CARGOS_5 +  CU.CARGOS_6) CARGOS, ")
				.append("       (CU.ABONOS_1 + CU.ABONOS_2 + CU.ABONOS_3 + CU.ABONOS_4 + CU.ABONOS_5 + CU.ABONOS_6 ) ABONOS, ")
				.append("       CU.SALINI ").append("     FROM CUENTA CU ").append("WHERE SUBSTR(CUENTA, 1,2)= '31' ")
				.append("  AND SUBSTR(CUENTA, 4) <> '0' ").append("  AND CU.SCTA = '' ")
				.append("  OR( SUBSTR(CUENTA, 1,2)= '32' ").append("      AND SUBSTR(CUENTA, 4) ='0' ")
				.append("      AND CU.CUENTA <> '3200' ").append("   ) ").append("   OR( SUBSTR(CUENTA, 1,2)= '33' ")
				.append("      AND SUBSTR(CUENTA, 4) <>'0' ").append(" ").append("   ) ").append("ORDER BY 1 ASC) T1");
		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String getQuerySituacion(Integer trimestre) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("CU.ABONOS_").append(i);
			cargos.append("CU.CARGOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
			}
		}
		sSql.append("SELECT FN_GET_TOKEN(0,'|', FN_GET_DETAILS_LDF(TRIM(T4.NOMCTA))) GRUPO, ")
				.append("       FN_GET_TOKEN(1,'|', FN_GET_DETAILS_LDF(TRIM(T4.NOMCTA))) NOMCTA, ")
				.append("       T4.ESTIMADO, ").append("       T4.AMPL_REDU, ").append("       T4.MODIFICADO, ")
				.append("       T4.DEVENGADO, ").append("       T4.RECAUDADO ").append("    FROM ( ")
				.append("SELECT T3.NOMCTA, ").append("       T3.ESTIMADO, ").append("       T3.AMPL_REDU, ")
				.append("       T3.MODIFICADO, ").append("       T3.DEVENGADO, ").append("       T3.RECAUDADO ")
				.append("  FROM ( ").append("SELECT T2.* FROM  ").append("(SELECT T1.SCTA, ")
				.append("       T1.SSCTA, ").append("       T1.SSSCTA, ").append("       T1.SSSSCTA, ")
				.append("       T1.NOMCTA, ")
				.append("       SUM(DECODE(T1.CUENTA , '8110', T1.ESTIMADO, 0)) ESTIMADO, ")
				.append("       SUM(DECODE(T1.CUENTA , '8110',(T1.AMPLIACIONES - T1.REDUCCIONES), 0 )) AMPL_REDU, ")
				.append("       SUM(DECODE(T1.CUENTA , '8110',T1.ESTIMADO + (T1.AMPLIACIONES - T1.REDUCCIONES ), 0)) MODIFICADO, ")
				.append("       SUM(DECODE(T1.CUENTA , '8140', T1.ESTIMADO,0)) DEVENGADO, ")
				.append("       SUM(DECODE(T1.CUENTA , '8150', T1.ESTIMADO,0)) RECAUDADO ")
				.append("    FROM (SELECT CU.CUENTA, ").append("                 CU.SCTA, ")
				.append("                 CU.SSCTA, ").append("                 CU.SSSCTA, ")
				.append("                 CU.SSSSCTA, ").append("                 CU.NOMCTA, ")
				.append("                 CU.SALINI ESTIMADO, ")
				.append("                 " + abonos + "  AMPLIACIONES, ")
				.append("                 " + cargos + "  REDUCCIONES ").append("              FROM CUENTA CU ")
				.append("         WHERE CU.CUENTA IN ('8110', '8140', '8150') ")
				.append("          AND (SUBSTR(CU.SCTA,7,4) >= '4110' ")
				.append("            AND SUBSTR(CU.SCTA,7,4) <= '4170' ")
				.append("            AND SUBSTR(CU.SCTA, 10,1) = '0'    ").append("              ) ")
				.append("         OR (CU.CUENTA  IN ('8110', '8140', '8150') ")
				.append("            AND SUBSTR(CU.SCTA,7,4) IN ('4211', '4212', '4213', '4214', '4399') ")
				.append("            AND CU.SSCTA = '' ").append("            )  ")
				.append("        OR (CU.CUENTA IN ('8110', '8140', '8150') ")
				.append("           AND SUBSTR(CU.SCTA,7,4) = '4211' ")
				.append("           AND SUBSTR(CU.SSCTA,15) IN ('1', '3') ")
				.append("           AND SUBSTR(CU.SSSCTA,4)>= '1' ").append("           AND SUBSTR(CU.SSSCTA,4)<= '6' ")
				.append("           AND SUBSTR(CU.SSSSCTA,3) IN ('1', '6') ").append("          ) ")
				.append("        OR (CU.CUENTA IN ('8110', '8140', '8150') ")
				.append("           AND SUBSTR(CU.SCTA,7,4) = '4211' ")
				.append("           AND SUBSTR(CU.SSCTA,15) = '1' ").append("           AND SUBSTR(CU.SSSCTA,4)= '6' ")
				.append("           AND CU.SSSSCTA = '' ").append("          ) ").append(" ")
				.append("       OR(CU.CUENTA  IN ('8110', '8140', '8150') ")
				.append("          AND SUBSTR(CU.SCTA,7,4) = '4214' ")
				.append("          AND SUBSTR(CU.SSCTA,15) IN ('1', '3') ")
				.append("          AND SUBSTR(CU.SSSCTA,4)>= '1' ").append("          AND SUBSTR(CU.SSSCTA,4)<= '6' ")
				.append("         AND SUBSTR(CU.SSSSCTA,3) IN ('1', '4') ").append("        ) ").append("  ")
				.append(" ").append("OR(CU.CUENTA  IN ('8110', '8140', '8150') ")
				.append("      AND SUBSTR(CU.SCTA,7,4) = '4212' ")
				.append("      AND SUBSTR(CU.SSCTA,15) IN ('1', '3') ").append("      AND SUBSTR(CU.SSSCTA,4)>= '1' ")
				.append("      AND SUBSTR(CU.SSSCTA,4)<= '6' ").append("      AND SUBSTR(CU.SSSSCTA,3) IN ('1', '6') ")
				.append(") ").append(" ").append(" ").append("OR(CU.CUENTA IN ('8110', '8140', '8150') ")
				.append("      AND SUBSTR(CU.SCTA,7,4) = '4213' ")
				.append("      AND SUBSTR(CU.SSCTA,15) IN ('1', '3') ").append("      AND SUBSTR(CU.SSSCTA,4)>= '1' ")
				.append("      AND SUBSTR(CU.SSSCTA,4)<= '6' ").append("      AND SUBSTR(CU.SSSSCTA,3) IN ('1', '6') ")
				.append(") ").append(" ").append("OR(CU.CUENTA IN ('8110', '8140', '8150') ")
				.append("      AND SUBSTR(CU.SCTA,7,4) = '4399' ").append("      AND SUBSTR(CU.SSCTA,15) = '3' ")
				.append("      AND SUBSTR(CU.SSSCTA,4)= '1' ").append("      AND SUBSTR(CU.SSSSCTA,3) ='1' ")
				.append(") ").append(" ").append(" ").append("ORDER BY CU.CUENTA, CU.SCTA, CU.SSCTA ASC ")
				.append("  ) T1 ").append("GROUP BY  T1.SCTA, ").append("          T1.SSCTA, ")
				.append("          T1.SSSCTA, ").append("          T1.SSSSCTA, ").append("          T1.NOMCTA ")
				.append("ORDER BY T1.SCTA ASC ) T2 ").append(")T3 ").append("UNION ALL ").append("SELECT   ")
				.append(" ").append("       T2.NOMCTA, ").append("       SUM(T2.ESTIMADO), ")
				.append("       SUM(T2.AMPL_REDU) AMPL_REDU, ").append("       SUM(T2.MODIFICADO) MODIFICADO, ")
				.append("       SUM(T2.DEVENGADO) DEVENGADO, ").append("       SUM(T2.RECAUDADO)RECAUDADO ")
				.append("     FROM ( ").append("SELECT T1.CUENTA, ").append("       T1.NOMCTA, ")
				.append("       DECODE(T1.CUENTA, '8110', SUM(ESTIMADO), 0) ESTIMADO, ")
				.append("       DECODE(T1.CUENTA, '8110', SUM(AMPLIACIONES - REDUCCIONES), 0) AMPL_REDU, ")
				.append("       DECODE(T1.CUENTA, '8110', SUM(ESTIMADO + (AMPLIACIONES - REDUCCIONES)), 0) MODIFICADO, ")
				.append("       DECODE(T1.CUENTA, '8140', SUM(ESTIMADO), 0) DEVENGADO, ")
				.append("       DECODE(T1.CUENTA, '8150', SUM(ESTIMADO), 0) RECAUDADO ").append("   FROM  ( ")
				.append("SELECT CU.CUENTA, ").append("       CU.SCTA, ").append("       CU.SSCTA, ")
				.append("       CU.SSSCTA, ").append("       CU.SSSSCTA, ")
				.append("       DECODE(TRIM(CU.NOMCTA),'Fiscalizacion', 'Otros Incentivos Economicos', 'Otros Incentivos', 'Otros Incentivos Economicos', CU.NOMCTA) NOMCTA, ")
				.append("       CU.SALINI ESTIMADO, ").append("                 " + abonos + "  AMPLIACIONES, ")
				.append("                 " + cargos + "  REDUCCIONES    FROM CUENTA CU ")
				.append("WHERE (CU.CUENTA  IN ('8110', '8140', '8150') ")
				.append("      AND SUBSTR(CU.SCTA,7,4)  = '4214'  ").append("      AND SUBSTR(CU.SSCTA,15) =  '1' ")
				.append("      AND SUBSTR(CU.SSSCTA,4) IN ('3', '2') ").append("      AND CU.SSSSCTA = '' ")
				.append("      ) ").append("    OR(CU.CUENTA  IN ('8110', '8140', '8150') ")
				.append("      AND SUBSTR(CU.SCTA,7,4)  IN ( '4213' , '4223') ")
				.append("      AND SUBSTR(CU.SSCTA,15) IN('3',  '1') ")
				.append("      AND SUBSTR(CU.SSSCTA,4) IN ('3', '3') ").append("      AND SUBSTR(CU.SSSSCTA,3) = '1' ")
				.append(" ").append(" ").append("      ) ").append("OR(CU.CUENTA IN ('8110', '8140', '8150') ")
				.append("      AND SUBSTR(CU.SCTA,7,4) = '4399' ").append("      AND SUBSTR(CU.SSCTA,15) = '3' ")
				.append("      AND SUBSTR(CU.SSSCTA,4)= '2' ").append("      AND CU.SSSSCTA ='' ").append(") ")
				.append(" ").append(" ").append("ORDER BY CU.CUENTA, ").append("         CU.SCTA, ")
				.append("         CU.SSCTA, ").append("         CU.SSSCTA, ").append("         CU.SSSSCTA ")
				.append("      ASC ").append("       ").append(") T1 ").append(" ").append(" ")
				.append("GROUP BY  T1.CUENTA, ").append("          T1.NOMCTA ").append("          )T2 ")
				.append("GROUP BY T2.NOMCTA ").append(") T4 ").append(" ").append("ORDER BY GRUPO ASC");
		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String getQueryEstadoSituacionFinancieraConac(Integer trimestre, char tipo, char tipoCuenta) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargosAnt = new StringBuilder();
		StringBuilder abonosAnt = new StringBuilder();
		Integer month = tipo == 't' ? getMonth(trimestre) : trimestre;
		Integer actual = month;
		Map<String, String> map = getMapCounts(month);
		cargos.append(map.get("cargos"));
		abonos.append(map.get("abonos"));
		month = tipo == 't' ? month - 3 : month - 1;
		map = getMapCounts(month);

		cargosAnt.append(map.get("cargos"));
		abonosAnt.append(map.get("abonos"));

		StringBuilder sSql = new StringBuilder();

		if (tipoCuenta == 'a')
			sSql.append("SELECT DECODE(SUBSTR(T1.CUENTA,1,2), '11', 1, 2) GRUPO, ");
		else if (tipoCuenta == 'p')
			sSql.append("SELECT DECODE(SUBSTR(T1.CUENTA,1,2), '21', 1, 2) GRUPO, ");
		else if (tipoCuenta == 'h')
			sSql.append("SELECT DECODE(SUBSTR(T1.CUENTA,1,2), '31', 1, '32', 2, 3) GRUPO, ");
		sSql.append("       T1.CUENTA, ").append("       MA.NOMMAY NOMCTA, ");
		if (tipoCuenta == 'a')
			sSql.append(
					"   DECODE(T1.CUENTA, '1260', FN_GET_CUENTA_1263("+month+"),T1.SALINI + T1.CARGOS_ANT - ABONOS_ANT) SALDO_ANTE, ")
					.append("  DECODE(T1.CUENTA, '1260', FN_GET_CUENTA_1263("+actual+"), T1.SALINI + CARGOS - T1.ABONOS)  SALDOACTUAL ");
		else if(tipoCuenta == 'p') {
			sSql.append("       T1.SALINI - T1.CARGOS_ANT + ABONOS_ANT SALDO_ANTE, ")
					.append("       T1.SALINI - T1.CARGOS + ABONOS SALDOACTUAL ");
		} else {
			sSql.append("  DECODE(T1.CUENTA,'3210', FN_GET_CUENTA_3211("+month+", 'a'),  T1.SALINI - T1.CARGOS_ANT + ABONOS_ANT)     SALDO_ANTE, ")
			.append("      DECODE(T1.CUENTA,'3210', FN_GET_CUENTA_3211("+actual+", 'a'), T1.SALINI - T1.CARGOS + ABONOS) SALDOACTUAL ");
		}
		sSql.append("     FROM (SELECT CU.CUENTA, ").append("   CU.SALINI, ").append(cargosAnt)
				.append("   CARGOS_ANT, ").append(abonosAnt).append("   ABONOS_ANT, ").append(cargos)
				.append("  CARGOS, ").append(abonos).append("  ABONOS ").append("               FROM CUENTA CU ")
				.append("          WHERE SUBSTR(CU.CUENTA,4,1) = '0'                 ")
				.append("            AND SUBSTR(CU.CUENTA,3,1) <> '0'                 ")
				.append("            AND CU.SCTA = ''    ");
		if (tipoCuenta == 'a')
			sSql.append("            AND SUBSTR(CU.CUENTA,1,2) IN ('11','12') ");
		else if (tipoCuenta == 'p')
			sSql.append("            AND SUBSTR(CU.CUENTA,1,2) IN ('21','22') ");
		else if (tipoCuenta == 'h')
			sSql.append("            AND SUBSTR(CU.CUENTA,1,2) IN ('31','32', '33') ");
		sSql.append("        ) T1, ").append("          MAYCTA MA ").append(" WHERE MA.CUENTA = T1.CUENTA ")
				.append(" ORDER BY 1, 2");
		System.out.println(sSql.toString());
		return sSql.toString();
	}

	private static Map<String, String> getMapCounts(Integer value) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		value = value == 0 ? 1 : value; 
		if (value < 0) {
			cargos.append("0");
			abonos.append("0");
		}
		Map<String, String> map = new HashMap<>();
		for (int i = 1; i <= value; i++) {
			abonos.append("CU.ABONOS_").append(i);
			cargos.append("CU.CARGOS_").append(i);
			if (i < value) {
				cargos.append(" + ");
				abonos.append(" + ");
			}
		}
		map.put("cargos", cargos.toString());
		map.put("abonos", abonos.toString());
		return map;
	}

	@Override
	public String getQueryActivoConac(Integer trimestre) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("CU.ABONOS_").append(i);
			cargos.append("CU.CARGOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
			}
		}
		
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT T.GRUPO, ").append("       T.CUENTA, ").append("       T.NOMCTA, ")
				.append("       DECODE(T.CUENTA, '1260',(T.SALDOACTUAL*-1),T.SALDOACTUAL) SALDOACTUAL , ")
				.append("       DECODE(T.CUENTA, '1260',(T.SALDO_ANTE*-1), T.SALDO_ANTE) AS SALDO_ANTE ")
				.append("      FROM(");
		sSql.append("SELECT DECODE(SUBSTR(T1.CUENTA,1,2), '11', 1, 2) GRUPO, T1.CUENTA, ").append("       T1.NOMCTA, ")
				.append("       DECODE(T1.NAT,'D',T1.SALINI + T1.CARGOS - T1.ABONOS, T1.SALINI - T1.CARGOS + T1.ABONOS) SALDOACTUAL,")
				.append("       T1.SALDO_ANTE  ").append("     FROM ( SELECT CU.CUENTA, ")
				.append("                   CU.NOMCTA, ").append("                   CU.SALINI, ")
				.append("                   (" + cargos + ") CARGOS,")
				.append("                   (" + abonos + ") ABONOS, ").append("                   TA.NAT, ")
				.append("                   TA.MES" + month + " SALDO_ANTE").append("               FROM CUENTA CU, ")
				.append("                    TC_CUENTAANTERIOR TA  ")
				.append("             WHERE CU.CUENTA = TA.CUENTA ")
				.append("               AND SUBSTR(CU.CUENTA,4,1) = '0' ")
				.append("               AND SUBSTR(CU.cuenta,3,1) <> '0' ").append("               AND CU.SCTA = '' ")
				.append("               AND SUBSTR(CU.CUENTA,1,2) IN ('11','12') ")
				.append("               AND SUBSTR(CU.CUENTA,1,2) = SUBSTR(TA.CUENTA,1,2) ")
				.append("           ) T1)T");
		System.out.println(sSql.toString());
		return sSql.toString();
	}

	@Override
	public String getQueryPasivoConac(Integer trimestre) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("CU.ABONOS_").append(i);
			cargos.append("CU.CARGOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
			}
		}
		StringBuilder sSql = new StringBuilder();

		sSql.append("SELECT DECODE(SUBSTR(T1.CUENTA,1,2), '21', 1,  2 ) GRUPO, T1.CUENTA, ")
				.append("       T1.NOMCTA, ")
				.append("    DECODE(T1.NAT,	'D',	T1.SALINI + T1.CARGOS - T1.ABONOS,	T1.SALINI - T1.CARGOS + T1.ABONOS)  SALDOACTUAL,")
				.append("       T1.SALDO_ANTE  ").append("     FROM ( SELECT CU.CUENTA, ")
				.append("                   CU.NOMCTA, ").append("                   CU.SALINI, ")
				.append("                   (" + cargos + ") CARGOS,")
				.append("                   (" + abonos + ") ABONOS, ").append("                   TA.NAT, ")
				.append("                   TA.MES" + month + " SALDO_ANTE").append("               FROM CUENTA CU, ")
				.append("                    TC_CUENTAANTERIOR TA  ")
				.append("             WHERE CU.CUENTA = TA.CUENTA ")
				.append("               AND SUBSTR(CU.CUENTA,4,1) = '0' ")
				.append("               AND SUBSTR(CU.cuenta,3,1) <> '0' ").append("               AND CU.SCTA = '' ")
				.append("               AND SUBSTR(CU.CUENTA,1,2) IN ('21','22') ")
				.append("               AND SUBSTR(CU.CUENTA,1,2) = SUBSTR(TA.CUENTA,1,2) ").append("           ) T1");
		System.out.println(sSql.toString());
		return sSql.toString();

	}

	@Override
	public String getQueryHaciendConac(Integer trimestre) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("CU.ABONOS_").append(i);
			cargos.append("CU.CARGOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
			}
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT T.GRUPO, ").append("       T.CUENTA, ").append("       T.NOMCTA, ")
				.append("       T.SALDOACTUAL, ").append("       DECODE(T.CUENTA, '3210', FN_GET_CINCO_CUATRO_ANTERIOR("
						+ trimestre + "), T.SALDO_ANTE) SALDO_ANTE ")
				.append("     FROM( ");
		sSql.append("SELECT DECODE(SUBSTR(T1.CUENTA,1,2),'31',	1,	'32',	2,	'33',	3 ) GRUPO, T1.CUENTA, ")
				.append("       T1.NOMCTA, ")
				.append("      DECODE(T1.CUENTA, '3210', (FN_400500(" + trimestre
						+ ")*-1),DECODE(T1.NAT,	'D',	T1.SALINI + T1.CARGOS - T1.ABONOS,	T1.SALINI - T1.CARGOS + T1.ABONOS))  SALDOACTUAL,")
				.append("       T1.SALDO_ANTE  ").append("     FROM ( SELECT CU.CUENTA, ")
				.append("                   CU.NOMCTA, ").append("                   CU.SALINI, ")
				.append("                   (" + cargos + ") CARGOS,")
				.append("                   (" + abonos + ") ABONOS, ").append("                   TA.NAT, ")
				.append("                   TA.MES" + month + " SALDO_ANTE").append("               FROM CUENTA CU, ")
				.append("                    TC_CUENTAANTERIOR TA  ")
				.append("             WHERE CU.CUENTA = TA.CUENTA ")
				.append("               AND SUBSTR(CU.CUENTA,4,1) = '0' ")
				.append("               AND SUBSTR(CU.cuenta,3,1) <> '0' ").append("               AND CU.SCTA = '' ")
				.append("               AND SUBSTR(CU.CUENTA,1,2) IN ('31','32', '33') ")
				.append("               AND SUBSTR(CU.CUENTA,1,2) = SUBSTR(TA.CUENTA,1,2) ")
				.append("           ) T1 )T");
		System.out.println(sSql.toString());
		return sSql.toString();
	}

	@Override
	public String getQueryTotalesConac(Integer trimestre) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("CU.ABONOS_").append(i);
			cargos.append("CU.CARGOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
			}
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT  SUM(T2.SALDOACTUAL) SALDOACTUAL, SUM(T2.SALDO_ANTE) SALDO_ANTE FROM ( ").append("SELECT ")
				.append("	DECODE(T1.CUENTA, '3210', FN_400500(" + trimestre
						+ "),DECODE(T1.NAT,	'D',	T1.SALINI + T1.CARGOS - T1.ABONOS,	T1.SALINI - T1.CARGOS + T1.ABONOS)) SALDOACTUAL, ")
				.append("	T1.SALDO_ANTE ").append("FROM ").append("	( ").append("SELECT ").append("	CU.CUENTA, ")
				.append("	CU.NOMCTA, ").append("	CU.SALINI, ").append("                   (" + cargos + ") CARGOS,")
				.append("                   (" + abonos + ") ABONOS, ").append("	TA.NAT, ")
				.append("	TA.MES" + month + " SALDO_ANTE ").append("FROM ").append("	CUENTA CU, ")
				.append("	TC_CUENTAANTERIOR TA ").append("WHERE ").append("	CU.CUENTA = TA.CUENTA AND ")
				.append("	SUBSTR(CU.CUENTA, ").append("	4, ").append("	1) = '0' AND ")
				.append("	SUBSTR(CU.cuenta, ").append("	3, ").append("	1) <> '0' AND ")
				.append("	CU.SCTA = '' AND ").append("	SUBSTR(CU.CUENTA, ").append("	1, ")
				.append("	2) IN ('21','22', '31', '32', '33') AND ").append("	SUBSTR(CU.CUENTA, ").append("	1, ")
				.append("	2) = SUBSTR(TA.CUENTA, ").append("	1, ").append("	2) ) T1 ").append("	 ")
				.append("	)T2");
		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String getQeryEstadoAnaliticoConac(char tipo, Integer trimestre) {
		String sSql = getActivos(tipo, trimestre);

		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String getQueryEdoCambioConac(Integer trimestre) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder cargosResta = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder abonosrResta = new StringBuilder();
		Integer month = getMonth(trimestre);

		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT tc_variacion.CUENTA cta, cu.nomcta nombre, CASE WHEN VAR" + month + " < 0 THEN ABS(VAR"
			+ month + ") ELSE 0.00 END ORIGEN, ")
			.append("               CASE WHEN VAR" + month + " > 0 THEN VAR" + month + " ELSE 0.00 END APLICACION ")
			.append("    FROM TC_VARIACION  ").append("    inner join ")
			.append("    cuenta cu on cu.cuenta = tc_variacion.CUENTA   ").append(" WHERE  ")
			.append("    tc_variacion.CUENTA IN( '1110','1120','1130','1140','1150','1160','1190') ")
			.append(" UNION ALL     ")
			.append(" select '1200' cta, 'Activo No Circulante' nombre, sum(ORIGEN) origen, ")
			.append(" SUM(APLICACION) aplicacion FROM ").append("(  ")
			.append(" SELECT tc_variacion.CUENTA cta, cu.nomcta nombre, CASE WHEN VAR" + month + " < 0 THEN ABS(VAR"
					+ month + ") ELSE 0.00 END ORIGEN, ")
			.append("               CASE WHEN VAR" + month + " > 0 THEN VAR" + month + " ELSE 0.00 END APLICACION ")
			.append("    FROM TC_VARIACION  ").append("    inner join ")
			.append("    cuenta cu on cu.cuenta = tc_variacion.CUENTA   ").append(" WHERE  ")
			.append("    tc_variacion.CUENTA IN( '1210','1220','1230','1240','1250','1260','1270','1280', '1290') ")
			.append(") ").append(" UNION ALL ")
			.append(" SELECT tc_variacion.CUENTA cta, cu.nomcta nombre, CASE WHEN VAR" + month + " < 0 THEN ABS(VAR"
					+ month + ") ELSE 0.00 END ORIGEN, ")
			.append("               CASE WHEN VAR" + month + " > 0 THEN VAR" + month + " ELSE 0.00 END APLICACION ")
			.append("    FROM TC_VARIACION  ").append("    inner join ")
			.append("    cuenta cu on cu.cuenta = tc_variacion.CUENTA   ").append(" WHERE  ")
			.append("    tc_variacion.CUENTA IN( '1210','1220','1230','1240','1250','1260','1270','1280','1290') ")
			.append(" ").append(" union all ")
			.append(" select '2000' cta, 'PASIVO' nombre, sum(ORIGEN) origen, sum(APLICACION) aplicacion ")
			.append(" from ").append("( ")
			.append(" SELECT tc_variacion.CUENTA cta, cu.nomcta nombre, CASE WHEN VAR" + month + " > 0 THEN VAR"
					+ month + " ELSE 0.00 END ORIGEN, ")
			.append("               CASE WHEN VAR" + month + " < 0 THEN ABS(VAR" + month
					+ ") ELSE 0.00 END APLICACION ")
			.append("    FROM TC_VARIACION  ").append("    inner join ")
			.append("    cuenta cu on cu.cuenta = tc_variacion.CUENTA   ").append(" WHERE  ")
			.append(" tc_variacion.CUENTA IN( '2110','2120','2130','2140','2150','2160','2170','2190', '2210',  ")
			.append(" '2220', '2230', '2240', '2250', '2260') ").append(") ").append(" UNION ALL   ")
			.append(" select '2100' cta, 'Pasivo Circulante' nombre, sum(ORIGEN) origen, ")
			.append(" SUM(APLICACION) aplicacion FROM ").append("(  ")
			.append(" SELECT tc_variacion.CUENTA cta, cu.nomcta nombre, CASE WHEN VAR" + month + " > 0 THEN VAR"
					+ month + " ELSE 0.00 END ORIGEN, ")
			.append("               CASE WHEN VAR" + month + " < 0 THEN ABS(VAR" + month
					+ ") ELSE 0.00 END APLICACION ")
			.append("    FROM TC_VARIACION  ").append("    inner join ")
			.append("    cuenta cu on cu.cuenta = tc_variacion.CUENTA   ").append(" WHERE  ")
			.append("    tc_variacion.CUENTA IN( '2110','2120','2130','2140','2150','2160','2170','2190') ")
			.append(") ").append(" UNION ALL ")
			.append(" SELECT tc_variacion.CUENTA cta, cu.nomcta nombre, CASE WHEN VAR" + month + " > 0 THEN VAR"
					+ month + " ELSE 0.00 END ORIGEN, ")
			.append("               CASE WHEN VAR" + month + " < 0 THEN ABS(VAR" + month
					+ ") ELSE 0.00 END APLICACION ")
			.append("    FROM TC_VARIACION  ").append("    inner join ")
			.append("    cuenta cu on cu.cuenta = tc_variacion.CUENTA   ").append(" WHERE  ")
			.append("    tc_variacion.CUENTA IN( '2110','2120','2130','2140','2150','2160','2170','2190') ")
			.append(" union all ")
			.append("  select '2200' cta, 'Pasivo No Circulante' nombre, sum(ORIGEN) origen, ")
			.append(" SUM(APLICACION) aplicacion FROM ").append("(  ")
			.append(" SELECT tc_variacion.CUENTA cta, cu.nomcta nombre, CASE WHEN VAR" + month + " > 0 THEN VAR"
					+ month + " ELSE 0.00 END ORIGEN, ")
			.append("               CASE WHEN VAR" + month + " < 0 THEN ABS(VAR" + month
					+ ") ELSE 0.00 END APLICACION ")
			.append("    FROM TC_VARIACION  ").append("    inner join ")
			.append("    cuenta cu on cu.cuenta = tc_variacion.CUENTA   ").append(" WHERE  ")
			.append("    tc_variacion.CUENTA IN( '2210','2220','2230','2240','2250','2260') ").append(") ")
			.append(" UNION ALL   ")
			.append(" SELECT tc_variacion.CUENTA cta, cu.nomcta nombre, CASE WHEN VAR" + month + " > 0 THEN VAR"
					+ month + " ELSE 0.00 END ORIGEN, ")
			.append("               CASE WHEN VAR" + month + " < 0 THEN ABS(VAR" + month
					+ ") ELSE 0.00 END APLICACION ")
			.append("    FROM TC_VARIACION  ").append("    inner join ")
			.append("    cuenta cu on cu.cuenta = tc_variacion.CUENTA   ").append(" WHERE  ")
			.append("    tc_variacion.CUENTA IN( '2210','2220','2230','2240','2250','2260') ").append(" UNION ALL ")
			.append(" select '3000' cta, 'HACIENDA PUBLICA/PATRIMONIO' nombre, sum(ORIGEN), sum(APLICACION) ")
			.append(" from ").append("( ")
			.append(" SELECT tc_variacion.CUENTA cta, '' nombre, CASE WHEN VAR" + month + " > 0 THEN VAR" + month
					+ " ELSE 0.00 END ORIGEN, ")
			.append("               CASE WHEN VAR" + month + " < 0 THEN abs(VAR" + month
					+ ") ELSE 0.00 END APLICACION ")
			.append("    FROM TC_VARIACION  ").append("   WHERE  ")
			.append(" tc_variacion.CUENTA IN( '3110','3120','3130','5040','3220','3230','3240','3250', '3310',  ")
			.append(" '3320') ").append(" ) ").append(" UNION ALL ")
			.append("select '3100' cta, 'Hacienda Publica/Patrimonio Contribuido' nombre, sum(ORIGEN), ")
			.append(" SUM(APLICACION) FROM ").append(" (  ")
			.append(" SELECT tc_variacion.CUENTA cta, cu.nomcta nombre, CASE WHEN VAR" + month + " > 0 THEN VAR"
					+ month + " ELSE 0.00 END ORIGEN, ")
			.append("               CASE WHEN VAR" + month + " < 0 THEN ABS(VAR" + month
					+ ") ELSE 0.00 END APLICACION ")
			.append("    FROM TC_VARIACION  ").append("    inner join ")
			.append("    cuenta cu on cu.cuenta = tc_variacion.CUENTA   ").append(" WHERE  ")
			.append("    tc_variacion.CUENTA IN( '3110','3120','3130') ").append(" ) ").append(" UNION ALL ")
			.append(" SELECT tc_variacion.CUENTA cta, cu.nomcta nombre, CASE WHEN VAR" + month + " > 0 THEN VAR"
					+ month + " ELSE 0.00 END ORIGEN, ")
			.append("               CASE WHEN VAR" + month + " < 0 THEN ABS(VAR" + month
					+ ") ELSE 0.00 END APLICACION ")
			.append("    FROM TC_VARIACION  ").append("    inner join ")
			.append("    cuenta cu on cu.cuenta = tc_variacion.CUENTA   ").append(" WHERE  ")
			.append("    tc_variacion.CUENTA IN( '3110','3120','3130') ").append(" UNION ALL  ")
			.append(" select '3200' cta , 'Hacienda Publica(Patrimonio Generado)', sum(origen), sum(aplicacion) ")
			.append(" from ( ")
			.append(" SELECT '3210' cta, 'Resultado del Ejercicio/(Ahorro/Desahorro)' nombre, CASE WHEN VAR" + month
					+ " > 0 THEN VAR" + month + " ELSE 0.00 END ORIGEN, ")
			.append("               CASE WHEN VAR" + month + " < 0 THEN ABS(VAR" + month
					+ ") ELSE 0.00 END APLICACION ")
			.append("    FROM TC_VARIACION where tc_variacion.CUENTA ='5040' ").append(" UNION ALL ")
			.append(" SELECT tc_variacion.CUENTA cta, cu.nomcta nombre, CASE WHEN VAR" + month + " > 0 THEN VAR"
					+ month + " ELSE 0.00 END ORIGEN, ")
			.append("               CASE WHEN VAR" + month + " < 0 THEN ABS(VAR" + month
					+ ") ELSE 0.00 END APLICACION ")
			.append("    FROM TC_VARIACION  ").append("    inner join ")
			.append("    cuenta cu on cu.cuenta = tc_variacion.CUENTA   ").append(" WHERE  ")
			.append("     tc_variacion.CUENTA IN( '3220','3230','3240','3250') ").append("  ) ")
			.append("  union all ")
			.append(" SELECT '3210' cta, 'Resultado del Ejercicio/(Ahorro/Desahorro)' nombre, CASE WHEN VAR" + month
					+ " > 0 THEN VAR" + month + " ELSE 0.00 END ORIGEN, ")
			.append("                CASE WHEN VAR" + month + " < 0 THEN ABS(VAR" + month
					+ ") ELSE 0.00 END APLICACION ")
			.append("     FROM TC_VARIACION where tc_variacion.CUENTA ='5040'     ").append(" UNION ALL  ")
			.append("  SELECT tc_variacion.CUENTA cta, cu.nomcta nombre, CASE WHEN VAR" + month + " > 0 THEN VAR"
					+ month + " ELSE 0.00 END ORIGEN, ")
			.append("                CASE WHEN VAR" + month + " < 0 THEN ABS(VAR" + month
					+ ") ELSE 0.00 END APLICACION ")
			.append("     FROM TC_VARIACION  ").append("     inner join ")
			.append("     cuenta cu on cu.cuenta = tc_variacion.CUENTA   ").append(" WHERE  ")
			.append("     tc_variacion.CUENTA IN( '3220','3230','3240','3250') ").append(" UNION ALL     ")
			.append(" select '3300' cta, 'Exceso o Insuficiencia en la Actualizacion de la Hacienda Publica/PatrimonioHacienda Publica/Patrimonio' nombre, sum(ORIGEN), ")
			.append("  SUM(APLICACION) FROM ").append(" (  ")
			.append(" SELECT tc_variacion.CUENTA cta, cu.nomcta nombre, CASE WHEN VAR" + month + " > 0 THEN VAR"
					+ month + " ELSE 0.00 END ORIGEN, ")
			.append("                CASE WHEN VAR" + month + " < 0 THEN ABS(VAR" + month
					+ ") ELSE 0.00 END APLICACION ")
			.append("     FROM TC_VARIACION  ").append("     inner join ")
			.append("     cuenta cu on cu.cuenta = tc_variacion.CUENTA   ").append(" WHERE  ")
			.append("     tc_variacion.CUENTA IN( '3310','3320') ").append(" ) ").append(" union all ")
			.append(" SELECT tc_variacion.CUENTA cta, cu.nomcta nombre, CASE WHEN VAR" + month + " > 0 THEN VAR"
					+ month + " ELSE 0.00 END ORIGEN, ")
			.append("                CASE WHEN VAR" + month + " < 0 THEN ABS(VAR" + month
					+ ") ELSE 0.00 END APLICACION ")
			.append("     FROM TC_VARIACION  ").append("     inner join ")
			.append("     cuenta cu on cu.cuenta = tc_variacion.CUENTA   ").append(" WHERE  ")
			.append("     tc_variacion.CUENTA IN( '3310','3320')     ").append("  ").append(" ORDER BY CTA");

		System.out.println(sSql);
		return sSql.toString();
	}

	Integer monthAux = 0;
	@Override
	public String getQueryActividadesConac(Integer trimestre, char tipo) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargosAnt = new StringBuilder();
		StringBuilder abonosAnt = new StringBuilder();
		Integer month = tipo == 't' ? getMonth(trimestre) : trimestre;

		Map<String, String> map = getMapCounts(month);
		cargos.append(map.get("cargos"));
		abonos.append(map.get("abonos"));
		monthAux = tipo == 't' ? month - 3 : trimestre - 1;
		map = getMapCounts(monthAux);

		cargosAnt.append(map.get("cargos"));
		abonosAnt.append(map.get("abonos"));

		StringBuilder sSql = new StringBuilder();
		sSql.append(" SELECT 1 PRINCIPAL, ").append("        1 GRUPO, ").append("        CU.CUENTA, ")
		.append("        FN_GET_NOMBRE_CUENTA(CU.CUENTA, CU.NOMCTA) NOMCTA, ").append("        0 SALDO_ANT, ")
		.append("        0 SALDO_ACT ").append("     FROM CUENTA CU ")
		.append(" WHERE CU.CUENTA IN ('4110', '4122', '4131', '4140', '4160', '4170') ").append(" UNION ALL ")
		.append(" SELECT 1 PRINCIPAL, ").append("        DECODE(SUBSTR(T1.CUENTA,1,2), '42', 2, 3) GRUPO, ")
		.append("        T1.CUENTA, ").append("        T1.NOMCTA, ")
		.append("        T1.SALINI + ABONOS_ANT - CARGOS_ANT SALDO_ANT, ")
		.append("        T1.SALINI + ABONOS - CARGOS SALDO_ACT ").append("      FROM ( ")
		.append(" SELECT CU.CUENTA, ").append("        FN_GET_NOMBRE_CUENTA(CU.CUENTA, CU.NOMCTA) NOMCTA, ")
		.append("        CU.SALINI, ").append("    " + cargosAnt + " CARGOS_ANT, ")
		.append("    " + abonosAnt + " ABONOS_ANT, ").append("    " + cargos + " CARGOS, ")
		.append("    " + abonos + " ABONOS ").append("     FROM CUENTA CU ")
		.append(" WHERE CU.CUENTA IN ('4210', '4220','4310', '4320', '4330', '4340', '4390') ").append(" ) T1 ")
		.append(" UNION ALL ").append(" SELECT 2 PRINCIPAL, ").append("        4 GRUPO, ")
		.append("        T1.CUENTA, ").append("        T1.NOMCTA, ")
		.append("        SUM(T1.SALINI + CARGOS_ANT - ABONOS_ANT) SALDO_ANT, ")
		.append("        SUM(T1.SALINI + CARGOS - ABONOS) SALDO_ACT ").append("      FROM ( ")
		.append(" SELECT NA.CLVGAS CUENTA, ").append("        NA.NOMGAS NOMCTA, ").append("        CU.SALINI, ")
		.append("    " + cargosAnt + " CARGOS_ANT, ").append("    " + abonosAnt + " ABONOS_ANT, ")
		.append("    " + cargos + " CARGOS, ").append("    " + abonos + " ABONOS ")
		.append("      FROM CUENTA CU ")
		.append("          LEFT JOIN NATGAS NA ON NA.CLVGAS = SUBSTR(CU.SSSCTA, 1,	1)||'000' ")
		.append(" WHERE CU.CUENTA   = '5100' ").append("  AND SUBSTR(NA.CLVGAS,2,3) = '000' ").append("  ) T1 ")
		.append("  GROUP BY 1, 2, T1.CUENTA, T1.NOMCTA ").append(" UNION ALL ").append(" SELECT 2 PRINCIPAL, ")
		.append("        5 GRUPO, ").append("        NA.CLVGAS CUENTA, ").append("        NA.NOMGAS NOMCTA, ")
		.append("        0 SALDO_ANT, ").append("        0 SALDO_ACT ").append("      FROM NATGAS NA ")
		.append(" WHERE NA.CLVGAS IN ('4100', '4200', '4300', '4500', '4600', '4700', '4800', '4900') ")
		.append(" UNION ALL        ").append(" SELECT 2 PRINCIPAL, ").append("        5 GRUPO, ")
		.append("        T1.CUENTA, ").append("        T1.NOMCTA, ")
		.append("        SUM(T1.SALINI + CARGOS_ANT - ABONOS_ANT) SALDO_ANT, ")
		.append("        SUM(T1.SALINI + CARGOS - ABONOS) SALDO_ACT ").append("      FROM ( ")
		.append(" SELECT '4400' CUENTA, ").append("        'Ayudas Sociales' NOMCTA, ")
		.append("        CU.SALINI, ").append("    " + cargosAnt + " CARGOS_ANT, ")
		.append("    " + abonosAnt + " ABONOS_ANT, ").append("    " + cargos + " CARGOS, ")
		.append("    " + abonos + " ABONOS ").append("      FROM CUENTA CU ")
		.append("          LEFT JOIN NATGAS NA ON NA.CLVGAS = SUBSTR(CU.SSSCTA, 1,	1)||'000' ")
		.append(" WHERE CU.CUENTA   = '5200' ").append("  AND SUBSTR(NA.CLVGAS,2,3) = '000' ").append("  ) T1 ")
		.append("  GROUP BY 1, 2, T1.CUENTA, T1.NOMCTA ").append(" UNION ALL ").append(" SELECT 2 PRINCIPAL, ")
		.append("        6 GRUPO, ").append("        NA.CLVGAS CUENTA, ").append("        NA.NOMGAS NOMCTA, ")
		.append("        0 SALDO_ANT, ").append("        0 SALDO_ACT ").append("      FROM NATGAS NA ")
		.append(" WHERE NA.CLVGAS IN ('8100', '8300', '8500') ").append(" UNION ALL ")
		.append(" SELECT 2 PRINCIPAL, ").append("        7 GRUPO, ").append("        NA.CLVGAS CUENTA, ")
		.append("        NA.NOMGAS NOMCTA, ").append("        0 SALDO_ANT, ").append("        0 SALDO_ACT ")
		.append("      FROM NATGAS NA ").append(" WHERE NA.CLVGAS IN ('9200', '9300', '9400', '9500', '9600') ")
		.append(" UNION ALL ").append(" SELECT 2 PRINCIPAL, ").append("        8 GRUPO, ")
		.append("        T1.CUENTA, ").append("        T1.NOMCTA, ")
		.append("        T1.SALINI + CARGOS_ANT - ABONOS_ANT SALDO_ANT, ")
		.append("        T1.SALINI + CARGOS - ABONOS SALDO_ACT ").append("      FROM ( ")
		.append(" SELECT CU.CUENTA CUENTA, ")
		.append("        'Estimaciones, Depreciaciones, Deterioros, Obsolescencia Y Amortizaciones' NOMCTA, ")
		.append("        CU.SALINI, ").append("    " + cargosAnt + " CARGOS_ANT, ")
		.append("    " + abonosAnt + " ABONOS_ANT, ").append("    " + cargos + " CARGOS, ")
		.append("    " + abonos + " ABONOS ").append("      FROM CUENTA CU ").append("           ")
		.append(" WHERE CU.CUENTA = '5500' ").append("   AND CU.SCTA   ='' ").append("   ").append("  ) T1 ")
		.append(" UNION ALL ").append(" SELECT 2 PRINCIPAL, ").append("        8 GRUPO, ")
		.append("        '5510' CUENTA, ").append("        'Provisiones' NOMCTA, ")
		.append("        0 SALDO_ANT, ").append("        0 SALDO_ACT ").append("      FROM SYSIBM.SYSDUMMY1 ")
		.append(" UNION ALL ").append(" SELECT 2 PRINCIPAL, ").append("        8 GRUPO, ")
		.append("        '5520' CUENTA, ").append("        'Disminucion de Inventarios' NOMCTA, ")
		.append("        0 SALDO_ANT, ").append("        0 SALDO_ACT ").append("      FROM SYSIBM.SYSDUMMY1 ")
		.append(" UNION ALL ").append(" SELECT 2 PRINCIPAL, ").append("        8 GRUPO, ")
		.append("        '5530' CUENTA, ").append("        'Otros Gastos' NOMCTA, ")
		.append("        0 SALDO_ANT, ").append("        0 SALDO_ACT ").append("      FROM SYSIBM.SYSDUMMY1 ")
		.append(" UNION ALL ").append(" SELECT 2 PRINCIPAL, ").append("        9 GRUPO, ")
		.append("        T1.CUENTA, ").append("        T1.NOMCTA, ")
		.append("        T1.SALINI + CARGOS_ANT - ABONOS_ANT SALDO_ANT, ")
		.append("        T1.SALINI + CARGOS - ABONOS SALDO_ACT ").append("      FROM ( ")
		.append(" SELECT CU.CUENTA CUENTA, ").append("        'Inversion Publica No Capitalizable' NOMCTA, ")
		.append("        CU.SALINI, ").append("    " + cargosAnt + " CARGOS_ANT, ")
		.append("    " + abonosAnt + " ABONOS_ANT, ").append("    " + cargos + " CARGOS, ")
		.append("    " + abonos + " ABONOS ").append("      FROM CUENTA CU ").append("           ")
		.append(" WHERE CU.CUENTA = '5700' ").append("   AND CU.SCTA   = '' ").append("   ").append("  ) T1 ")
		.append("  ORDER BY 1, 2, 3 ");

		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String getQueryVariacionHaciendaConac(Integer trimestre) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("CU.ABONOS_").append(i);
			cargos.append("CU.CARGOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
			}
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT PR.PRINCIPAL,").append(
				"	PR.GRUPO, PR.CUENTA, PR.NOMCTA,	PR.CONTRIBUIDO, PR.GENERADOANTERIOR, DECODE(PR.CUENTA, '3219', (PR.GENERADOACTUAL*-1),PR.GENERADOACTUAL) GENERADOACTUAL,")
				.append("	PR.EXCESO, ").append("	DECODE(PR.CUENTA, '3219', (PR.TOTAL*-1),PR.TOTAL)TOTAL ")
				.append("FROM ").append("	( ").append("SELECT ").append("	1 PRINCIPAL, ").append("	T5.GRUPO, ")
				.append("	T5.CUENTA, ").append("	T5.NOMCTA, ").append("	T5.CONTRIBUIDO, ")
				.append("	T5.GENERADOANTERIOR, ").append("	T5.GENERADOACTUAL, ").append("	T5.EXCESO, ")
				.append("	T5.TOTAL ").append("FROM ").append("	( ").append("SELECT ").append("	1 GRUPO, ")
				.append("	T1.CUENTA CUENTA, ").append("	T1.CONCEPTO NOMCTA, ").append("	T1.CONTRIBUIDO, ")
				.append("	T1.GENERADOANTERIOR, ").append("	T1.GENERADOACTUAL, ").append("	T1.EXCESO, ")
				.append("	(T1.CONTRIBUIDO + T1.GENERADOANTERIOR + T1.GENERADOACTUAL + T1.EXCESO) TOTAL ")
				.append("FROM ").append("	( ").append("SELECT ").append("	'3110' CUENTA, ")
				.append("	'Aportaciones' CONCEPTO, ").append("	MES12 CONTRIBUIDO, ")
				.append("	0.00 GENERADOANTERIOR, ").append("	0.00 GENERADOACTUAL, ").append("	0.00 EXCESO ")
				.append("FROM ").append("	TC_CUENTAANTERIOR ").append("WHERE ").append("	CUENTA = '3110' ")
				.append("UNION ").append("	ALL ").append("SELECT ").append("	'3111' CUENTA, ")
				.append("	'Donaciones de Capital' CONCEPTO, ").append("	0.00 CONTRIBUIDO, ")
				.append("	0.00 GENERADOANTERIOR, ").append("	0.00 GENERADOACTUAL, ").append("	0.00 EXCESO ")
				.append("FROM ").append("	SYSIBM.SYSDUMMY1 ").append("UNION ").append("	ALL ").append("SELECT ")
				.append("	'3112' CUENTA, ").append("	'Actualizacion de la Hacienda Publica/Patrimonio' CONCEPTO, ")
				.append("	0.00 CONTRIBUIDO, ").append("	0.00 GENERADOANTERIOR, ").append("	0.00 GENERADOACTUAL, ")
				.append("	0.00 EXCESO ").append("FROM ").append("	SYSIBM.SYSDUMMY1 ) T1 ").append("UNION ")
				.append("	ALL ").append("SELECT ").append("	T4.* ").append("FROM ").append("	( ")
				.append("SELECT ").append("	T3.GRUPO, ").append("	T3.CUENTA, ").append("	T3.NOMCTA, ")
				.append("	T3.CONTRIBUIDO, ").append("	T3.GENERADOANTERIOR, ").append("	T3.GENERADOACTUAL, ")
				.append("	T3.EXCESO, ").append("	T3.TOTAL ").append("FROM ").append("	( ").append("SELECT ")
				.append("	2 GRUPO, ").append("	T2.CUENTA, ").append("	T2.NOMCTA, ").append("	T2.CONTRIBUIDO, ")
				.append("	T2.GENERADOANTERIOR, ").append("	T2.GENERADOACTUAL, ").append("	T2.EXCESO, ")
				.append("	(T2.CONTRIBUIDO + T2.GENERADOANTERIOR + T2.GENERADOACTUAL + T2.EXCESO) TOTAL ")
				.append("FROM ").append("	( ").append("SELECT  2 GRUPO, ").append("        T1.CUENTA, ")
				.append("        T1.NOMCTA, ").append("        SUM(T1.CONTRIBUIDO) CONTRIBUIDO, ")
				.append("        SUM(T1.GENERADOANTERIOR) GENERADOANTERIOR, ")
				.append("        SUM((T1.CINCO - T1.CUATRO)*-1) GENERADOACTUAL, ")
				.append("        SUM(T1.EXCESO) EXCESO ").append("      FROM ( ").append("SELECT '3211' CUENTA,  ")
				.append("       'Resultado de ejercicios (Ahorro/Desahorro)' NOMCTA,  ")
				.append("       0.00 CONTRIBUIDO, ").append("       0.00 GENERADOANTERIOR, ")
				.append("       DECODE(SUBSTR(CUENTA,1,1), '4', MES12, 0) CUATRO, ")
				.append("       DECODE(SUBSTR(CUENTA,1,1), '5', MES12, 0) CINCO, ").append("       0.00 EXCESO ")
				.append("     FROM TC_CUENTAANTERIOR ").append("WHERE CUENTA IN ('4000', '5000') ").append(") T1 ")
				.append("GROUP BY T1.CUENTA, ").append("        T1.NOMCTA  ").append("UNION 	ALL ")
				.append("SELECT 2 GRUPO, ").append("       '3220' CUENTA, ")
				.append("       'Resultado de ejercicios Anteriores' CONCEPTO,  ").append("        0.00 CONTRIBUIDO,  ")
				.append("        MES12 GENERADOANTERIOR,  ").append("        0.00 GENERADOACTUAL,  ")
				.append("        0.00 EXCESO ").append("      FROM TC_CUENTAANTERIOR  ")
				.append("WHERE CUENTA ='3220'   ").append(" ").append(")T2 ").append("UNION ").append("	ALL ")
				.append("SELECT ").append("	T2.GRUPO, ").append("	T2.CUENTA, ").append("	T2.NOMCTA, ")
				.append("	T2.CONTRIBUIDO, ").append("	T2.GENERADOANTERIOR, ").append("	T2.GENERADOACTUAL, ")
				.append("	T2.EXCESO, ")
				.append("	(T2.CONTRIBUIDO + T2.GENERADOANTERIOR + T2.GENERADOACTUAL + T2.EXCESO ) TOTAL ")
				.append("FROM ").append("	( ").append("SELECT ").append("	2 GRUPO, ").append("	T1.CUENTA, ")
				.append("	T1.NOMCTA, ").append("	0.00 CONTRIBUIDO, ").append("	0.00 GENERADOANTERIOR, ")
				.append("	0.00 GENERADOACTUAL, ").append("	T1.SALDO_ANTE EXCESO ").append("FROM ").append("	( ")
				.append("SELECT ").append("	CU.CUENTA, ").append("	CU.NOMCTA, ").append("	CU.SALINI, ")
				.append(cargos + " CARGOS, ").append(abonos + " ABONOS, ").append("	TU.MES" + month + " SALDO_ANTE ")
				.append("FROM ").append("	CUENTA CU, ").append("	TC_CUENTAANTERIOR TU ").append("WHERE ")
				.append("	CU.CUENTA = TU.CUENTA AND ").append("	CU.CUENTA = '3230' ) T1 )T2 ").append("UNION ")
				.append("	ALL ").append("SELECT ").append("	2 GRUPO, ").append("	'3231' CUENTA, ")
				.append("	'Reservas' NOMCTA, ").append("	0.00 CONTRIBUIDO, ").append("	0.00 GENERADOANTERIOR, ")
				.append("	0.00 GENERADOACTUAL, ").append("	0.00 EXCESO, ").append("	0.00 TOTAL ")
				.append("FROM ").append("	SYSIBM.SYSDUMMY1 ").append("UNION ").append("	ALL ").append("SELECT ")
				.append("	2 GRUPO, ").append("	'3232' CUENTA, ")
				.append("	'Recteficaciones de Resultados de Ejercicios Anteriores' NOMCTA, ")
				.append("	0.00 CONTRIBUIDO, ").append("	0.00 GENERADOANTERIOR, ").append("	0.00 GENERADOACTUAL, ")
				.append("	0.00 EXCESO, ").append("	0.00 TOTAL ").append("FROM ").append("	SYSIBM.SYSDUMMY1 )T3 ")
				.append("ORDER BY ").append("	T3.CUENTA ) T4 ").append("UNION ").append("	ALL ").append("SELECT ")
				.append("	3 GRUPO, ").append("	'3310' CUENTA, ")
				.append("	'Resultado por Posición Monetaria' NOMCTA, ").append("	0.00 CONTRIBUIDO, ")
				.append("	0.00 GENERADOANTERIOR, ").append("	0.00 GENERADOACTUAL, ").append("	0.00 EXCESO, ")
				.append("	0.00 TOTAL ").append("FROM ").append("	SYSIBM.SYSDUMMY1 ").append("UNION ")
				.append("	ALL ").append("SELECT ").append("	3 GRUPO, ").append("	'3320' CUENTA, ")
				.append("	'Resultado por Tenencia de Activos no Monetarios' NOMCTA, ").append("	0.00 CONTRIBUIDO, ")
				.append("	0.00 GENERADOANTERIOR, ").append("	0.00 GENERADOACTUAL, ").append("	0.00 EXCESO, ")
				.append("	0.00 TOTAL ").append("FROM ").append("	SYSIBM.SYSDUMMY1 ) T5 ").append("UNION ")
				.append("	ALL ").append("SELECT ").append("	T5.* ").append("FROM ").append("	( ")
				.append("SELECT ").append("	2 PRINCIPAL, ").append("	T4.GRUPO, ").append("	T4.CUENTA, ")
				.append("	T4.NOMCTA, ").append("	T4.CONTRIBUIDO, ").append("	T4.GENERADOANTERIOR, ")
				.append("	T4.GENERADOACTUAL, ").append("	T4.EXCESO, ").append("	T4.TOTAL ").append("FROM ")
				.append("	( ").append("SELECT ").append("	4 GRUPO, ").append("	'3110' CUENTA, ")
				.append("	'Aportaciones' NOMCTA, ").append("	0.00 CONTRIBUIDO, ")
				.append("	0.00 GENERADOANTERIOR, ").append("	0.00 GENERADOACTUAL, ").append("	0.00 EXCESO, ")
				.append("	0.00 TOTAL ").append("FROM ").append("	SYSIBM.SYSDUMMY1 ").append("UNION ")
				.append("	ALL ").append("SELECT ").append("	4 GRUPO, ").append("	'3111' CUENTA, ")
				.append("	'Donaciones Capital' NOMCTA, ").append("	0.00 CONTRIBUIDO, ")
				.append("	0.00 GENERADOANTERIOR, ").append("	0.00 GENERADOACTUAL, ").append("	0.00 EXCESO, ")
				.append("	0.00 TOTAL ").append("FROM ").append("	SYSIBM.SYSDUMMY1 ").append("UNION ")
				.append("	ALL ").append("SELECT ").append("	4 GRUPO, ").append("	'3112' CUENTA, ")
				.append("	'Actualizacion de la Hacienda Pública/Patrimnio' NOMCTA, ").append("	0.00 CONTRIBUIDO, ")
				.append("	0.00 GENERADOANTERIOR, ").append("	0.00 GENERADOACTUAL, ").append("	0.00 EXCESO, ")
				.append("	0.00 TOTAL ").append("FROM ").append("	SYSIBM.SYSDUMMY1 ").append("UNION ")
				.append("	ALL ").append("SELECT ").append("	T3.* ").append("FROM ").append("	( ")
				.append("SELECT ").append("	T2.GRUPO, ").append("	T2.CUENTA, ").append("	T2.NOMCTA, ")
				.append("	T2.CONTRIBUIDO, ").append("	T2.GENERADOANTERIOR, ").append("	T2.GENERADOACTUAL, ")
				.append("	T2.EXCESO, ")
				.append("	( T2.CONTRIBUIDO + T2.GENERADOANTERIOR + T2.GENERADOACTUAL + T2.EXCESO) TOTAL ")
				.append("FROM ").append("	( ")
				.append("  SELECT 5 GRUPO, 	'3219' CUENTA, 	'Resultado del Ejercicio (Ahorro/Desahorro)' NOMCTA,	0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, fn_400500("
						+ trimestre + ") GENERADOACTUAL, " + " 0.00 EXCESO "
						+ " FROM SYSIBM.SYSDUMMY1 UNION ALL  SELECT ")
				.append("	5 GRUPO, ").append("	T1.CUENTA, ").append("	T1.NOMCTA, ").append("	0.00 contribuido, ")
				.append("	( T1.SALINI + (T1.ABONOS - T1.CARGOS)) generadoanterior, ")
				.append("	0.00 generadoactual, ").append("	0.00 exceso ").append("FROM ").append("	( ")
				.append("SELECT ").append("	CU.CUENTA, ").append("	CU.NOMCTA, ").append("	CU.SALINI, ")
				.append(cargos + " CARGOS, ").append(abonos + " ABONOS, ").append("	TU.MES" + month + " SALDO_ANTE ")
				.append("FROM ").append("	CUENTA CU, ").append("	TC_CUENTAANTERIOR TU ").append("WHERE ")
				.append("	CU.CUENTA = TU.CUENTA AND ").append("	CU.CUENTA = '3220' ) T1 ").append("UNION ")
				.append("	ALL ").append("SELECT ").append("	5 GRUPO, ").append("	'3221' CUENTA, ")
				.append("	'Revaluos' NOMCTA, ").append("	0.00 CONTRIBUIDO, ").append("	0.00 GENERADOANTERIOR, ")
				.append("	0.00 GENERADOACTUAL, ").append("	0.00 EXCESO ").append("FROM ")
				.append("	SYSIBM.SYSDUMMY1 ").append("UNION ").append("	ALL ").append("SELECT ")
				.append("	5 GRUPO, ").append("	'3222' CUENTA, ").append("	'Reservas' NOMCTA, ")
				.append("	0.00 CONTRIBUIDO, ").append("	0.00 GENERADOANTERIOR, ").append("	0.00 GENERADOACTUAL, ")
				.append("	0.00 EXCESO ").append("FROM ").append("	SYSIBM.SYSDUMMY1 ").append("UNION ")
				.append("	ALL ").append("SELECT ").append("	5 GRUPO, ").append("	'3223' CUENTA, ")
				.append("	'Recteificaciones de Resultados de Ejercicios Anteriores' NOMCTA, ")
				.append("	0.00 CONTRIBUIDO, ").append("	0.00 GENERADOANTERIOR, ").append("	0.00 GENERADOACTUAL, ")
				.append("	0.00 EXCESO ").append("FROM ").append("	SYSIBM.SYSDUMMY1 ) T2 ").append("ORDER BY ")
				.append("	T2.CUENTA ASC )T3 ").append("UNION ").append("	ALL ").append("SELECT ")
				.append("	T1.GRUPO, ").append("	T1.CUENTA, ").append("	T1.NOMCTA, ").append("	T1.CONTRIBUIDO, ")
				.append("	T1.GENERADOANTERIOR, ").append("	T1.GENERADOACTUAL, ").append("	T1.EXCESO, ")
				.append("	(T1.CONTRIBUIDO + T1.GENERADOANTERIOR + T1.GENERADOACTUAL + T1.EXCESO) TOTAL ")
				.append("FROM ").append("	( ").append("SELECT ").append("	6 GRUPO, ").append("	'3224' CUENTA, ")
				.append("	'Resultado por Posicion Monetaria' NOMCTA, ").append("	0.00 CONTRIBUIDO, ")
				.append("	0.00 GENERADOANTERIOR, ").append("	0.00 GENERADOACTUAL, ").append("	0.00 EXCESO ")
				.append("FROM ").append("	SYSIBM.SYSDUMMY1 ").append("UNION ").append("	ALL ").append("SELECT ")
				.append("	6 GRUPO, ").append("	'3225' CUENTA, ")
				.append("	'Resultado por Tenencia de Activos no Monetaria' NOMCTA, ").append("	0.00 CONTRIBUIDO, ")
				.append("	0.00 GENERADOANTERIOR, ").append("	0.00 GENERADOACTUAL, ").append("	0.00 EXCESO ")
				.append("FROM ").append("	SYSIBM.SYSDUMMY1 ) T1 ) T4 ").append("ORDER BY ")
				.append("	T4.GRUPO ASC ) T5 ) PR ").append("ORDER BY ").append("	PR.PRINCIPAL, ")
				.append("	PR.GRUPO, ").append("	PR.CUENTA ASC ").append("");

		System.out.println(sSql);
		return sSql.toString();
	}
	
	@Override
	public String getQueryVariacionHaciendaInfoem(Integer trimestre, Integer idSector, boolean mensual) {
		StringBuilder cargosAnt = new StringBuilder();
		StringBuilder abonosAnt = new StringBuilder();
		StringBuilder cargosAct = new StringBuilder();
		StringBuilder abonosAct = new StringBuilder();
		Integer month = mensual ? trimestre : getMonth(trimestre);

		if(month == 1) {
			cargosAnt.append("24098525.69");
			abonosAnt.append("0");
		} else {
			for (int i = 1; i < month; i++) {
				cargosAnt.append("C.CARGOS_" + i);
				abonosAnt.append("C.ABONOS_" + i);
				if (i < month-1) {
					cargosAnt.append(" + ");
					abonosAnt.append(" + ");
				}
			}
		}
		
		for (int i = 1; i <= month; i++) {
			abonosAct.append("C.ABONOS_").append(i);
			cargosAct.append("C.CARGOS_").append(i);
			if (i < month) {
				cargosAct.append(" + ");
				abonosAct.append(" + ");
			}
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT ")
		.append("1 PRINCIPAL, 1 GRUPO, 1 ORDEN, ")
		.append("'Aportaciones' NOMCTA, ")
		.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, 0.00 GENERADOACTUAL, ")
		.append("0.00 EXCESO, 0.00 TOTAL ")
		.append("FROM SYSIBM.SYSDUMMY1 ")
		.append("UNION ALL ")
		.append("SELECT ")
		.append("1 PRINCIPAL, 1 GRUPO, 2 ORDEN, ")
		.append("'Donaciones de Capital' NOMCTA, ")
		.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, 0.00 GENERADOACTUAL, ")
		.append("0.00 EXCESO, 0.00 TOTAL ")
		.append("FROM SYSIBM.SYSDUMMY1 ")
		.append("UNION ALL ")
		.append("SELECT ")
		.append("1 PRINCIPAL, 1 GRUPO, 3 ORDEN, ")
		.append("'Actualización de la Hacienda Pública/Patrimonio' NOMCTA, ")
		.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, 0.00 GENERADOACTUAL, ")
		.append("0.00 EXCESO, 0.00 TOTAL ")
		.append("FROM SYSIBM.SYSDUMMY1 ")
		.append("UNION ALL ")
		.append("SELECT ")
		.append("1 PRINCIPAL, 2 GRUPO, 1 ORDEN, ")
		.append("'Resultados del Ejercicio (Ahorro/Desahorro)' NOMCTA, ");
		if(month == 1) {
			sSql.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, 24098525.69 GENERADOACTUAL, ")
			.append("0.00 EXCESO, 24098525.69 TOTAL ");
		} else {
			sSql.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, TOTAL_ANT GENERADOACTUAL, ")
			.append("0.00 EXCESO, TOTAL_ANT TOTAL ");
		}
		sSql.append("FROM ( ")
		.append("SELECT ")
		.append("(SUM(T3.VAN4)-SUM(T3.VAN5)) TOTAL_ANT, ")
		.append("(SUM(T3.VACT4)-SUM(T3.VACT5)) TOTAL_ACT ")
		.append("FROM ")
		.append("( ")
		.append("SELECT ")
		.append("CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMANT) ELSE 0.00 END VAN4, ")
		.append("CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMANT) ELSE 0.00 END VAN5, ")
		.append("CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMACT) ELSE 0.00 END VACT4, ")
		.append("CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMACT)ELSE 0.00 END VACT5 ")
		.append("FROM ")
		.append("( ")
		.append("SELECT ")
		.append("T1.GRUPO, ")
		.append("T1.SALINI, ")
		.append("CASE WHEN T1.GRUPO ='4' THEN (T1.SALINI+ABONOS_ANT-CARGOS_ANT) ELSE  ")
		.append("(T1.SALINI-ABONOS_ANT+CARGOS_ANT) END VMANT, ")
		.append("CASE WHEN T1.GRUPO ='4' THEN (T1.SALINI+ABONOS_ACT-CARGOS_ACT) ELSE  ")
		.append("(T1.SALINI-ABONOS_ACT+CARGOS_ACT) END VMACT ")
		.append("FROM ")
		.append("( ")
		.append("SELECT ")
		.append("SUBSTR(C.CUENTA, ")
		.append("1, ")
		.append("1) GRUPO, ")
		.append("C.CUENTA CUENTA , ")
		.append("C.SALINI SALINI, ")
		.append("SUM("+cargosAnt+") CARGOS_ANT, ")
		.append("SUM("+abonosAnt+") ABONOS_ANT, ")
		.append("SUM("+cargosAct+") CARGOS_ACT, ")
		.append("SUM("+abonosAct+") ABONOS_ACT ")
		.append("FROM ")
		.append("CUENTA C ")
		.append("WHERE ")
		.append("C.IDSECTOR= "+idSector)
		.append(" AND ( ( (SUBSTR(C.CUENTA,1,1) =4 AND ")
		.append("SUBSTR(C.CUENTA,4,1)<>'0') OR ")
		.append("(SUBSTR(C.CUENTA,1,1) = 5 AND ")
		.append("SUBSTR(C.CUENTA,2,1) > 0) ) AND ")
		.append("C.SCTA ='') ")
		.append("GROUP BY ")
		.append("C.CUENTA, ")
		.append("C.SALINI ")
		.append("ORDER BY ")
		.append("C.CUENTA)T1)T2 ")
		.append("GROUP BY ")
		.append("T2.GRUPO )T3 ")
		.append(") ")
		.append("UNION ALL ")
		.append("SELECT ")
		.append("1 PRINCIPAL, 2 GRUPO, ")
		.append("CASE WHEN C.CUENTA = '3232' THEN 3 ELSE 2 END ORDEN, ")
		.append("CASE WHEN C.CUENTA = '3232' THEN 'Revalúos' ELSE C.NOMCTA END NOMCTA,  ");
		if(month == 1) {
			sSql.append("0.00 CONTRIBUIDO, CASE WHEN C.CUENTA = '3232' THEN 181060.00 ELSE 47742742.00 END GENERADOANTERIOR, 0.00 GENERADOACTUAL, ")
			.append("0.00 EXCESO, CASE WHEN C.CUENTA = '3232' THEN 181060.00 ELSE 47742742.00 END TOTAL ");
		}else {
			sSql.append("0.00 CONTRIBUIDO, C.SALINI GENERADOANTERIOR, 0.00 GENERADOACTUAL, ")
			.append("0.00 EXCESO, C.SALINI TOTAL ");
		}
		sSql.append("FROM   CUENTA C ")
		.append("WHERE  C.CUENTA IN ('3221','3232') ")
		.append("AND C.SCTA ='' ")
		.append("AND C.IDSECTOR = "+idSector)
		.append(" UNION ALL ")
		.append("SELECT ")
		.append("1 PRINCIPAL, 2 GRUPO, 4 ORDEN, ")
		.append("'Reservas' NOMCTA, ")
		.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, 0.00 GENERADOACTUAL, ")
		.append("0.00 EXCESO, 0.00 TOTAL ")
		.append("FROM SYSIBM.SYSDUMMY1 ")
		.append("UNION ALL ")
		.append("SELECT ")
		.append("1 PRINCIPAL, 2 GRUPO, 5 ORDEN, ")
		.append("'Rectificaciones de Resultados de Ejercicios Anteriores' NOMCTA, ")
		.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, 0.00 GENERADOACTUAL, ")
		.append("0.00 EXCESO, 0.00 TOTAL ")
		.append("FROM SYSIBM.SYSDUMMY1 ")
		.append("UNION ALL ")
		.append("SELECT ")
		.append("1 PRINCIPAL, 3 GRUPO, 1 ORDEN, ")
		.append("'Resultado por Posición Monetaria' NOMCTA, ")
		.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, 0.00 GENERADOACTUAL, ")
		.append("0.00 EXCESO, 0.00 TOTAL ")
		.append("FROM SYSIBM.SYSDUMMY1 ")
		.append("UNION ALL ")
		.append("SELECT ")
		.append("1 PRINCIPAL, 3 GRUPO, 2 ORDEN, ")
		.append("'Resultado por Tenencia de Activos no Monetarios' NOMCTA, ")
		.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, 0.00 GENERADOACTUAL, ")
		.append("0.00 EXCESO, 0.00 TOTAL ")
		.append("FROM SYSIBM.SYSDUMMY1 ")
		.append("UNION ALL ")
		.append("SELECT ")
		.append("2 PRINCIPAL, 4 GRUPO, 1 ORDEN, ")
		.append("'Aportaciones' NOMCTA, ")
		.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, 0.00 GENERADOACTUAL, ")
		.append("0.00 EXCESO, 0.00 TOTAL ")
		.append("FROM SYSIBM.SYSDUMMY1 ")
		.append("UNION ALL ")
		.append("SELECT ")
		.append("2 PRINCIPAL, 4 GRUPO, 2 ORDEN, ")
		.append("'Donaciones de Capital' NOMCTA, ")
		.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, 0.00 GENERADOACTUAL, ")
		.append("0.00 EXCESO, 0.00 TOTAL ")
		.append("FROM SYSIBM.SYSDUMMY1 ")
		.append("UNION ALL ")
		.append("SELECT ")
		.append("2 PRINCIPAL, 4 GRUPO, 3 ORDEN, ")
		.append("'Actualización de la Hacienda Pública/Patrimonio' NOMCTA, ")
		.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, 0.00 GENERADOACTUAL, ")
		.append("0.00 EXCESO, 0.00 TOTAL ")
		.append("FROM SYSIBM.SYSDUMMY1 ")
		.append("UNION ALL ")
		.append("SELECT ")
		.append("2 PRINCIPAL, 5 GRUPO, 1 ORDEN, ")
		.append("'Resultados del Ejercicio (Ahorro/Desahorro)' NOMCTA, ");
		if(month == 1) {
			sSql.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, -1395091.37 GENERADOACTUAL, ")
			.append("0.00 EXCESO, -1395091.37 TOTAL ");
		} else {
			sSql.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, TOTAL_ACT GENERADOACTUAL, ")
			.append("0.00 EXCESO, TOTAL_ACT TOTAL ");
		}
		sSql.append("FROM ( ")
		.append("SELECT ")
		.append("(SUM(T3.VAN4)-SUM(T3.VAN5)) TOTAL_ANT, ")
		.append("(SUM(T3.VACT4)-SUM(T3.VACT5)) TOTAL_ACT ")
		.append("FROM ")
		.append("( ")
		.append("SELECT ")
		.append("CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMANT) ELSE 0.00 END VAN4, ")
		.append("CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMANT) ELSE 0.00 END VAN5, ")
		.append("CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMACT) ELSE 0.00 END VACT4, ")
		.append("CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMACT)ELSE 0.00 END VACT5 ")
		.append("FROM ")
		.append("( ")
		.append("SELECT ")
		.append("T1.GRUPO, ")
		.append("T1.SALINI, ")
		.append("CASE WHEN T1.GRUPO ='4' THEN (T1.SALINI+ABONOS_ANT-CARGOS_ANT) ELSE  ")
		.append("(T1.SALINI-ABONOS_ANT+CARGOS_ANT) END VMANT, ")
		.append("CASE WHEN T1.GRUPO ='4' THEN (T1.SALINI+ABONOS_ACT-CARGOS_ACT) ELSE  ")
		.append("(T1.SALINI-ABONOS_ACT+CARGOS_ACT) END VMACT ")
		.append("FROM ")
		.append("( ")
		.append("SELECT ")
		.append("SUBSTR(C.CUENTA, ")
		.append("1, ")
		.append("1) GRUPO, ")
		.append("C.CUENTA CUENTA , ")
		.append("C.SALINI SALINI, ")
		.append("SUM("+cargosAnt+") CARGOS_ANT, ")
		.append("SUM("+abonosAnt+") ABONOS_ANT, ")
		.append("SUM("+cargosAct+") CARGOS_ACT, ")
		.append("SUM("+abonosAct+") ABONOS_ACT ")
		.append("FROM ")
		.append("CUENTA C ")
		.append("WHERE ")
		.append("C.IDSECTOR= "+idSector)
		.append(" AND ( ( (SUBSTR(C.CUENTA,1,1) =4 AND ")
		.append("SUBSTR(C.CUENTA,4,1)<>'0') OR ")
		.append("(SUBSTR(C.CUENTA,1,1) = 5 AND ")
		.append("SUBSTR(C.CUENTA,2,1) > 0) ) AND ")
		.append("C.SCTA ='') ")
		.append("GROUP BY ")
		.append("C.CUENTA, ")
		.append("C.SALINI ")
		.append("ORDER BY ")
		.append("C.CUENTA)T1)T2 ")
		.append("GROUP BY ")
		.append("T2.GRUPO )T3 ")
		.append(") ")
		.append("UNION ALL ")
		.append("SELECT ")
		.append("2 PRINCIPAL, 5 GRUPO, 2 ORDEN, ")
		.append("'Resultados de Ejercicios Anteriores' NOMCTA, ");
		if(month == 1) {
			sSql.append("0.00 CONTRIBUIDO, 24098525.69 GENERADOANTERIOR, -24098525.69 GENERADOACTUAL, ")
			.append("0.00 EXCESO, 0.00 TOTAL ");
		}else {
			sSql.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, TOTAL_ANT*-1 GENERADOACTUAL, ")
			.append("0.00 EXCESO, TOTAL_ANT*-1 TOTAL ");
		}
		sSql.append("FROM ( ")
		.append("SELECT ")
		.append("(SUM(T3.VAN4)-SUM(T3.VAN5)) TOTAL_ANT, ")
		.append("(SUM(T3.VACT4)-SUM(T3.VACT5)) TOTAL_ACT ")
		.append("FROM ")
		.append("( ")
		.append("SELECT ")
		.append("CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMANT) ELSE 0.00 END VAN4, ")
		.append("CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMANT) ELSE 0.00 END VAN5, ")
		.append("CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMACT) ELSE 0.00 END VACT4, ")
		.append("CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMACT)ELSE 0.00 END VACT5 ")
		.append("FROM ")
		.append("( ")
		.append("SELECT ")
		.append("T1.GRUPO, ")
		.append("T1.SALINI, ")
		.append("CASE WHEN T1.GRUPO ='4' THEN (T1.SALINI+ABONOS_ANT-CARGOS_ANT) ELSE  ")
		.append("(T1.SALINI-ABONOS_ANT+CARGOS_ANT) END VMANT, ")
		.append("CASE WHEN T1.GRUPO ='4' THEN (T1.SALINI+ABONOS_ACT-CARGOS_ACT) ELSE  ")
		.append("(T1.SALINI-ABONOS_ACT+CARGOS_ACT) END VMACT ")
		.append("FROM ")
		.append("( ")
		.append("SELECT ")
		.append("SUBSTR(C.CUENTA, ")
		.append("1, ")
		.append("1) GRUPO, ")
		.append("C.CUENTA CUENTA , ")
		.append("C.SALINI SALINI, ")
		.append("SUM("+cargosAnt+") CARGOS_ANT, ")
		.append("SUM("+abonosAnt+") ABONOS_ANT, ")
		.append("SUM("+cargosAct+") CARGOS_ACT, ")
		.append("SUM("+abonosAct+") ABONOS_ACT ")
		.append("FROM ")
		.append("CUENTA C ")
		.append("WHERE ")
		.append("C.IDSECTOR= "+idSector)
		.append(" AND ( ( (SUBSTR(C.CUENTA,1,1) =4 AND ")
		.append("SUBSTR(C.CUENTA,4,1)<>'0') OR ")
		.append("(SUBSTR(C.CUENTA,1,1) = 5 AND ")
		.append("SUBSTR(C.CUENTA,2,1) > 0) ) AND ")
		.append("C.SCTA ='') ")
		.append("GROUP BY ")
		.append("C.CUENTA, ")
		.append("C.SALINI ")
		.append("ORDER BY ")
		.append("C.CUENTA)T1)T2 ")
		.append("GROUP BY ")
		.append("T2.GRUPO )T3 ")
		.append(") ")
		.append("UNION ALL ")
		.append("SELECT ")
		.append("2 PRINCIPAL, 5 GRUPO, 3 ORDEN, ")
		.append("'Revalúos' NOMCTA, ")
		.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, 0.00 GENERADOACTUAL, ")
		.append("0.00 EXCESO, 0.00 TOTAL ")
		.append("FROM SYSIBM.SYSDUMMY1 ")
		.append("UNION ALL ")
		.append("SELECT ")
		.append("2 PRINCIPAL, 5 GRUPO, 4 ORDEN, ")
		.append("'Reservas' NOMCTA, ")
		.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, 0.00 GENERADOACTUAL, ")
		.append("0.00 EXCESO, 0.00 TOTAL ")
		.append("FROM SYSIBM.SYSDUMMY1 ")
		.append("UNION ALL ")
		.append("SELECT ")
		.append("2 PRINCIPAL, 5 GRUPO, 5 ORDEN, ")
		.append("'Rectificaciones de Resultados de Ejercicios Anteriores' NOMCTA, ")
		.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, 0.00 GENERADOACTUAL, ")
		.append("0.00 EXCESO, 0.00 TOTAL ")
		.append("FROM SYSIBM.SYSDUMMY1 ")
		.append("UNION ALL ")
		.append("SELECT ")
		.append("2 PRINCIPAL, 6 GRUPO, 1 ORDEN, ")
		.append("'Resultado por Posición Monetaria' NOMCTA, ")
		.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, 0.00 GENERADOACTUAL, ")
		.append("0.00 EXCESO, 0.00 TOTAL ")
		.append("FROM SYSIBM.SYSDUMMY1 ")
		.append("UNION ALL ")
		.append("SELECT ")
		.append("2 PRINCIPAL, 6 GRUPO, 2 ORDEN, ")
		.append("'Resultado por Tenencia de Activos no Monetarios' NOMCTA, ")
		.append("0.00 CONTRIBUIDO, 0.00 GENERADOANTERIOR, 0.00 GENERADOACTUAL, ")
		.append("0.00 EXCESO, 0.00 TOTAL ")
		.append("FROM SYSIBM.SYSDUMMY1 ")
		.append("ORDER BY 1,2,3");

		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String getQueryEstadoSituacionFinancieraActivosConac(Integer trimestre) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("T1.ABONOS_").append(i);
			cargos.append("T1.CARGOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
			}
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT T4.PRINCIPAL, ").append("       T4.GRUPO, ").append("       T4.LETRA, ")
				.append("       T4.CUENTA, ").append("       T4.NOMCTA, ").append("       T4.SALDO_ACTUAL, ")
				.append("       T4.SALDO_ANTERIOR ").append("      FROM( ").append("SELECT 1 PRINCIPAL, ")
				.append("       SUBSTR(T3.CUENTA,3,1) GRUPO, ")
				.append("       DECODE(SUBSTR(T3.CUENTA,3,1), 1, 'a', 2, 'b', 3, 'c', 4, 'd', 5, 'e', 6, 'f', 'g')LETRA, ")
				.append("       T3.CUENTA  , T3.NOMCTA, T3.SALDO_ACTUAL, NVL(T3.SALDO_ANTERIOR, 0.00) SALDO_ANTERIOR ")
				.append("        FROM ").append("(SELECT T2.CUENTA, T2.NOMCTA, T2.NAT,  ")
				.append("	DECODE(T2.NAT,'A',T2.SALINI + T2.ABONOS - T2.CARGOS, T2.SALINI + T2.CARGOS - T2.ABONOS) SALDO_ACTUAL, ")
				.append("    MES6 SALDO_ANTERIOR ").append("    FROM (        ")
				.append("		SELECT T1.CUENTA, T1.NOMCTA, T1.STACTA NAT, T1.SALINI , ")
				.append("		" + cargos + " CARGOS, ").append("		" + abonos + " ABONOS ")
				.append("		FROM CUENTA T1  ")
				.append("		WHERE SUBSTR(T1.CUENTA,1,2) = '11' AND T1.SCTA = '' AND STACTA <> '' )T2 LEFT JOIN TC_CUENTAANTERIOR TA ON ")
				.append("		T2.CUENTA = TA.CUENTA ) T3 ").append("UNION ALL ").append("SELECT 2 PRINCIPAL, ")
				.append("      SUBSTR(T3.CUENTA,3,2) GRUPO, ")
				.append("      DECODE( SUBSTR(T3.CUENTA,3,2), 10, 'a', 20, 'b', 30, 'c', 40, 'd', 50, 'e', 60, 'f', 70, 'g', 80, 'h', 'i') LETRA, ")
				.append("       T3.CUENTA, T3.NOMCTA, T3.SALDO_ACTUAL, T3.SALDO_ANTERIOR FROM ")
				.append("(SELECT T2.CUENTA, T2.NOMCTA, T2.NAT,  ")
				.append("	DECODE(T2.CUENTA,'1260',-(T2.SALINI + T2.ABONOS - T2.CARGOS),'1280',-(T2.SALINI + T2.ABONOS - T2.CARGOS), T2.SALINI + T2.CARGOS - T2.ABONOS) SALDO_ACTUAL, ")
				.append("    DECODE(T2.CUENTA,'1260',-MES12,'1280',-MES12,MES12) SALDO_ANTERIOR ")
				.append("    FROM (        ").append("		SELECT T1.CUENTA, T1.NOMCTA, T1.STACTA NAT, T1.SALINI , ")
				.append("		" + cargos + " CARGOS, ").append("		" + abonos + " ABONOS ")
				.append("		FROM CUENTA T1  ").append("		WHERE SUBSTR(T1.CUENTA,1,2) = '12'  ")
				.append("		AND SUBSTR(T1.CUENTA,4,1)='0'  ").append("		AND SUBSTR(T1.CUENTA,3,1)<>'0' ")
				.append("		AND T1.SCTA = ''  )T2 LEFT JOIN TC_CUENTAANTERIOR TA ON ")
				.append("		T2.CUENTA = TA.CUENTA ) T3    ").append(" ")
				.append(")T4 ORDER BY T4.PRINCIPAL, T4.GRUPO, T4.CUENTA ASC");
		System.out.println(sSql);
		return sSql.toString();
	}
	
	@Override
	public String getQueryEstadoSituacionFinancieraActivosInfoem(Integer trimestre, Integer idSector) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargosAnt = new StringBuilder();
		StringBuilder abonosAnt = new StringBuilder();
		Integer month = getMonth(trimestre);
		Integer monthB = month == 3 ? 1 : month-3;
		for (int i = 1; i <= monthB; i++) {
			abonosAnt.append("C.ABONOS_").append(i);
			cargosAnt.append("C.CARGOS_").append(i);
			if (i < monthB) {
				cargosAnt.append(" + ");
				abonosAnt.append(" + ");
			}
		}
		
		for (int i = 1; i <= month; i++) {
			abonos.append("C.ABONOS_").append(i);
			cargos.append("C.CARGOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
			}
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT ")
		.append("1 PRINCIPAL, ")
		.append("CASE ")
		.append("WHEN CUENTA = '1111' THEN 1 ")
		.append("WHEN CUENTA = '1112' THEN 1 ")
		.append("WHEN CUENTA = '1123' THEN 2 ")
		.append("WHEN CUENTA = '1191' THEN 9 ")
		.append("END GRUPO, ")
		.append("CASE ")
		.append("WHEN CUENTA = '1111' THEN 'a' ")
		.append("WHEN CUENTA = '1112' THEN 'a' ")
		.append("WHEN CUENTA = '1123' THEN 'b' ")
		.append("WHEN CUENTA = '1191' THEN 'g' ")
		.append("END LETRA, ")
		.append("CUENTA, NOMCTA, SALDO_ACTUAL, SALDO_ANTERIOR ")
		.append("FROM( ")
		.append("SELECT ")
		.append("CUENTA, NOMCTA, ")
		.append("SALINI+CARGOS_ACT-ABONOS_ACT SALDO_ACTUAL, ")
		.append("SALINI+CARGOS_ANT-ABONOS_ANT SALDO_ANTERIOR ")
		.append("FROM( ")
		.append("SELECT  ")
		.append("C.NOMCTA, C.CUENTA, C.SALINI,  ")
		.append(cargosAnt+" CARGOS_ANT, ")
		.append(abonosAnt+" ABONOS_ANT, ")
		.append(abonos+" ABONOS_ACT, ")
		.append(cargos+" CARGOS_ACT ")
		.append("FROM   MAYCTA MA ")
		.append("INNER JOIN CUENTA C ON MA.CUENTA = C.CUENTA  ")
		.append("WHERE  C.CUENTA IN ('1111','1112','1123','1191')  ")
		.append("AND C.SCTA = ''  ")
		.append("AND C.IDSECTOR = "+idSector)
		.append(" ORDER BY C.CUENTA ) ) ")
		.append("UNION ALL ")
		.append("SELECT * FROM ( VALUES ")
		.append("(1, 1, 'a', '1113', 'Bancos/Dependencias y Otros', 0.00, 0.00), ")
		.append("(1, 1, 'a', '1114', 'Inversiones Temporales (Hasta 3 meses)', 0.00, 0.00), ")
		.append("(1, 1, 'a', '1115', 'Fondos con Afectación Específica', 0.00, 0.00), ")
		.append("(1, 1, 'a', '1116', 'Depósitos de Fondos de Terceros en Garantía y/o Administración', 0.00, 0.00), ")
		.append("(1, 1, 'a', '1117', 'Otros Efectivos y Equivalentes', 0.00, 0.00), ")
		.append("(1, 2, 'b', '1121', 'Inversiones Financieras de Corto Plazo', 0.00, 0.00), ")
		.append("(1, 2, 'b', '1122', 'Cuentas por Cobrar a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 2, 'b', '1124', 'Ingresos por Recuperar a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 2, 'b', '1125', 'Deudores por Anticipos de la Tesorería a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 2, 'b', '1126', 'Préstamos Otorgados a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 2, 'b', '1127', 'Otros Derechos a Recibir Efectivo o Equivalentes a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 3, 'c', '1131', 'Anticipo a Proveedores por Adquisición de Bienes y Prestación de Servicios a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 3, 'c', '1132', 'Anticipo a Proveedores por Adquisición de Bienes Inmuebles y Muebles a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 3, 'c', '1133', 'Anticipo a Proveedores por Adquisición de Bienes Intangibles a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 3, 'c', '1134', 'Anticipo a Contratistas por Obras Públicas a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 3, 'c', '1135', 'Otros Derechos a Recibir Bienes o Servicios a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 4, 'd', '1141', 'Inventario de Mercancías para Venta', 0.00, 0.00), ")
		.append("(1, 4, 'd', '1142', 'Inventario de Mercancías Terminadas', 0.00, 0.00), ")
		.append("(1, 4, 'd', '1143', 'Inventario de Mercancías en Proceso de Elaboración', 0.00, 0.00), ")
		.append("(1, 4, 'd', '1144', 'Inventario de Materias Primas, Materiales y Suministros para Producción', 0.00, 0.00), ")
		.append("(1, 4, 'd', '1145', 'Bienes en Tránsito', 0.00, 0.00), ")
		.append("(1, 5, 'e', '1151', null, null, null), ")
		.append("(1, 6, 'f', '1161', 'Estimaciones para Cuentas Incobrables por Derechos a Recibir Efectivo o Equivalentes', 0.00, 0.00), ")
		.append("(1, 6, 'f', '1162', 'Estimación por Deterioro de Inventarios', 0.00, 0.00), ")
		.append("(1, 9, 'g', '1192', 'Bienes en Garantía (excluye depósitos de fondos)', 0.00, 0.00), ")
		.append("(1, 9, 'g', '1193', 'Bienes Derivados de Embargos, Decomisos, Aseguramientos y Dación en Pago', 0.00, 0.00), ")
		.append("(1, 9, 'g', '1194', 'Adquisición con Fondos de Terceros', 0.00, 0.00) ")
		.append(") AS TEMP (PRINCIPAL, GRUPO, LETRA, CUENTA, NOMCTA, SALDO_ACTUAL, SALDO_ANTERIOR) ")
		.append("UNION ")
		.append("SELECT ")
		.append("2 PRINCIPAL, ")
		.append("10 GRUPO, ")
		.append("CASE ")
		.append("WHEN CUENTA = '1241' THEN 'd' ")
		.append("WHEN CUENTA = '1263' THEN 'f' ")
		.append("END LETRA, ")
		.append("CUENTA, NOMCTA, SALDO_ACTUAL, SALDO_ANTERIOR ")
		.append("FROM( ")
		.append("SELECT ")
		.append("NOMCTA, CUENTA, ")
		.append("SUM(MES_ACT) SALDO_ACTUAL, ")
		.append("SUM(MES_ANT) SALDO_ANTERIOR ")
		.append("FROM( ")
		.append("SELECT ")
		.append("CASE WHEN CUENTA = '1263' THEN CUENTA ELSE '1241' END CUENTA,  ")
		.append("CASE WHEN CUENTA = '1263' THEN NOMCTA ELSE 'Bienes Muebles' END NOMCTA, ")
		.append("CASE WHEN CUENTA = '1263' THEN (SALINI-CARGOS_ACT+ABONOS_ACT)*-1 ELSE SALINI+CARGOS_ACT-ABONOS_ACT END MES_ACT, ")
		.append("CASE WHEN CUENTA = '1263' THEN (SALINI-CARGOS_ANT+ABONOS_ANT)*-1 ELSE SALINI+CARGOS_ANT-ABONOS_ANT END MES_ANT ")
		.append("FROM( ")
		.append("SELECT  ")
		.append("C.NOMCTA, C.CUENTA, C.SALINI,  ")
		.append(cargosAnt+" CARGOS_ANT, ")
		.append(abonosAnt+" ABONOS_ANT, ")
		.append(abonos+" ABONOS_ACT, ")
		.append(cargos+" CARGOS_ACT ")
		.append("FROM   MAYCTA MA ")
		.append("INNER JOIN CUENTA C ON MA.CUENTA = C.CUENTA  ")
		.append("WHERE  C.CUENTA IN ('1241','1242','1243','1244','1245','1246','1263')  ")
		.append("AND C.SCTA = ''  ")
		.append("AND C.IDSECTOR = "+idSector)
		.append(" ORDER BY C.CUENTA )) ")
		.append("GROUP BY NOMCTA, CUENTA) ")
		.append("UNION ALL ")
		.append("SELECT * FROM ( VALUES ")
		.append("(2, 10, 'a', '1210', 'Inversiones Financieras a Largo Plazo', 0.00, 0.00), ")
		.append("(2, 10, 'b', '1220', 'Derechos a Recibir Efectivo o Equivalentes a Largo Plazo', 0.00, 0.00), ")
		.append("(2, 10, 'c', '1230', 'Bienes Inmuebles, Infraestructura y Construcciones en Proceso', 0.00, 0.00), ")
		.append("(2, 10, 'e', '1250', 'Activos Intangibles', 0.00, 0.00), ")
		.append("(2, 10, 'g', '1270', 'Activos Diferidos', 0.00, 0.00), ")
		.append("(2, 10, 'h', '1280', 'Estimación por Pérdida o Deterioro de Activos no Circulantes', 0.00, 0.00), ")
		.append("(2, 10, 'i', '1290', 'Otros Activos no Circulantes', 0.00, 0.00) ")
		.append(") AS TEMP (PRINCIPAL, GRUPO, LETRA, CUENTA, NOMCTA, SALDO_ACTUAL, SALDO_ANTERIOR) ")
		.append("ORDER BY 4");
		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String getQueryEstadoSituacionFinancieraPasivosConac(Integer trimestre) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("T1.ABONOS_").append(i);
			cargos.append("T1.CARGOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
			}
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append(" ").append(" ").append(" ").append("SELECT T4.PRINCIPAL, ").append("       T4.GRUPO, ")
				.append("       T4.LETRA, ").append("       T4.CUENTA, ").append("       T4.NOMCTA, ")
				.append("       T4.SALDO_ACTUAL, ").append("       T4.SALDO_ANTERIOR ").append("     FROM ( ")
				.append("SELECT 1 PRINCIPAL, ").append("       SUBSTR(T3.CUENTA,3,1) GRUPO, ")
				.append("        DECODE(SUBSTR(T3.CUENTA,3,1), 1, 'a', 2, 'b', 3, 'c', 4, 'd', 5, 'e', 6, 'f',6, 'g','h')LETRA, ")
				.append("       T3.CUENTA, T3.NOMCTA, T3.SALDO_ACTUAL, T3.SALDO_ANTERIOR FROM ")
				.append("(SELECT T2.CUENTA, T2.NOMCTA, T2.NAT,  ")
				.append("	DECODE(T2.NAT,'A',T2.SALINI + T2.ABONOS - T2.CARGOS, T2.SALINI + T2.CARGOS - T2.ABONOS) SALDO_ACTUAL, ")
				.append("    MES6 SALDO_ANTERIOR ").append("    FROM (        ")
				.append("		SELECT T1.CUENTA, T1.NOMCTA, T1.STACTA NAT, T1.SALINI , ")
				.append("		" + cargos + " CARGOS, ").append("		" + abonos + " ABONOS ")
				.append("		FROM CUENTA T1  ")
				.append("		WHERE SUBSTR(T1.CUENTA,1,2) = '21' AND T1.SCTA = '' AND STACTA <> '' )T2 LEFT JOIN TC_CUENTAANTERIOR TA ON ")
				.append("		T2.CUENTA = TA.CUENTA ) T3  ").append("UNION ALL ").append("SELECT 2 PRINCIPAL, ")
				.append("       SUBSTR(T3.CUENTA, 3,2) GRUPO, ")
				.append("       DECODE( SUBSTR(T3.CUENTA,3,2), 10, 'a', 20, 'b', 30, 'c', 40, 'd', 50, 'e', 60, 'f') LETRA, ")
				.append("       T3.CUENTA, T3.NOMCTA, T3.SALDO_ACTUAL, T3.SALDO_ANTERIOR FROM ")
				.append("(SELECT T2.CUENTA, T2.NOMCTA, T2.NAT,  ")
				.append("	(T2.SALINI + T2.ABONOS - T2.CARGOS) SALDO_ACTUAL, ").append("    MES12 SALDO_ANTERIOR ")
				.append("    FROM (        ").append("		SELECT T1.CUENTA, T1.NOMCTA, T1.STACTA NAT, T1.SALINI , ")
				.append("		" + cargos + " CARGOS, ").append("		" + abonos + " ABONOS ")
				.append("		FROM CUENTA T1  ").append("		WHERE SUBSTR(T1.CUENTA,1,2) = '22'  ")
				.append("		AND SUBSTR(T1.CUENTA,4,1)='0'  ").append("		AND SUBSTR(T1.CUENTA,3,1)<>'0' ")
				.append("		AND T1.SCTA = ''  )T2 LEFT JOIN TC_CUENTAANTERIOR TA ON ")
				.append("		T2.CUENTA = TA.CUENTA ) T3 ").append(")T4 ")
				.append("ORDER BY T4.PRINCIPAL, T4.GRUPO, T4.CUENTA ASC  ").append("");
		System.out.println(sSql);
		return sSql.toString();
	}
	
	@Override
	public String getQueryEstadoSituacionFinancieraPasivosInfoem(Integer trimestre, Integer idSector) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargosAnt = new StringBuilder();
		StringBuilder abonosAnt = new StringBuilder();
		Integer month = getMonth(trimestre);
		Integer monthB = month == 3 ? 1 : month-3;
		for (int i = 1; i <= monthB; i++) {
			abonosAnt.append("C.ABONOS_").append(i);
			cargosAnt.append("C.CARGOS_").append(i);
			if (i < monthB) {
				cargosAnt.append(" + ");
				abonosAnt.append(" + ");
			}
		}
		
		for (int i = 1; i <= month; i++) {
			abonos.append("C.ABONOS_").append(i);
			cargos.append("C.CARGOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
			}
		}
		
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT ")
		.append("1 PRINCIPAL, 1 GRUPO,  'a' LETRA, ")
		.append("CUENTA, NOMCTA,  ")
		.append("SALINI-CARGOS_ACT+ABONOS_ACT SALDO_ACTUAL,  ")
		.append("SALINI-CARGOS_ANT+ABONOS_ANT SALDO_ANTERIOR  ")
		.append("FROM(  ")
		.append("SELECT   ")
		.append("C.NOMCTA, C.CUENTA, C.SALINI, ")
		.append(cargosAnt+" CARGOS_ANT, ")
		.append(abonosAnt+" ABONOS_ANT, ")
		.append(abonos+" ABONOS_ACT, ")
		.append(cargos+" CARGOS_ACT ")
		.append("FROM   MAYCTA MA  ")
		.append("INNER JOIN CUENTA C ON MA.CUENTA = C.CUENTA   ")
		.append("WHERE  C.CUENTA IN ('2117','2119')   ")
		.append("AND C.SCTA = ''   ")
		.append("AND C.IDSECTOR = "+idSector)
		.append(" ) UNION ALL ")
		.append("SELECT * FROM ( VALUES ")
		.append("(1, 1, 'a', '2111', 'Servicios Personales por Pagar a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 1, 'a', '2112', 'Proveedores por Pagar a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 1, 'a', '2113', 'Contratistas por Obras Públicas por Pagar a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 1, 'a', '2114', 'Participaciones y Aportaciones por Pagar a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 1, 'a', '2115', 'Transferencias Otorgadas por Pagar a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 1, 'a', '2116', 'Intereses, Comisiones y Otros Gastos de la Deuda Pública por Pagar a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 1, 'a', '2118', 'Devoluciones de la Ley de Ingresos por Pagar a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 2, 'b', '2121', 'Documentos Comerciales por Pagar a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 2, 'b', '2122', 'Documentos con Contratistas por Obras Públicas por Pagar a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 2, 'b', '2123', 'Otros Documentos por Pagar a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 3, 'c', '2131', 'Porción a Corto Plazo de la Deuda Pública', 0.00, 0.00), ")
		.append("(1, 3, 'c', '2132', 'Porción a Corto Plazo de Arrendamiento Financiero', 0.00, 0.00), ")
		.append("(1, 4, 'd', '2141', null, null, null), ")
		.append("(1, 5, 'e', '2151', 'Ingresos Cobrados por Adelantado a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 5, 'e', '2152', 'Intereses Cobrados por Adelantado a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 5, 'e', '2153', 'Otros Pasivos Diferidos a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 6, 'f', '2161', 'Fondos en Garantía a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 6, 'f', '2162', 'Fondos en Administración a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 6, 'f', '2163', 'Fondos Contingentes a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 6, 'f', '2164', 'Fondos de Fideicomisos, Mandatos y Contratos Análogos a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 6, 'f', '2165', 'Otros Fondos de Terceros en Garantía y/o Administración a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 6, 'f', '2166', 'Valores y Bienes en Garantía a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 7, 'g', '2171', 'Provisión para Demandas y Juicios a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 7, 'g', '2172', 'Provisión para Contingencias a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 7, 'g', '2173', 'Otras Provisiones a Corto Plazo', 0.00, 0.00), ")
		.append("(1, 9, 'h', '2181', 'Ingresos por Clasificar', 0.00, 0.00), ")
		.append("(1, 9, 'h', '2182', 'Recaudación por Participar', 0.00, 0.00), ")
		.append("(1, 9, 'h', '2183', 'Otros Pasivos Circulantes', 0.00, 0.00), ")
		.append("(2, 10, 'a', '2210', 'Cuentas por Pagar a Largo Plazo', 0.00, 0.00), ")
		.append("(2, 10, 'b', '2220', 'Documentos por Pagar a Largo Plazo', 0.00, 0.00), ")
		.append("(2, 10, 'c', '2230', 'Deuda Pública a Largo Plazo', 0.00, 0.00), ")
		.append("(2, 10, 'd', '2240', 'Pasivos Diferidos a Largo Plazo', 0.00, 0.00), ")
		.append("(2, 10, 'e', '2250', 'Fondos y Bienes de Terceros en Garantía y/o en Administración a Largo Plazo', 0.00, 0.00), ")
		.append("(2, 10, 'f', '2260', 'Provisiones a Largo Plazo', 0.00, 0.00) ")
		.append(") AS TEMP (PRINCIPAL, GRUPO, LETRA, CUENTA, NOMCTA, SALDO_ACTUAL, SALDO_ANTERIOR) ")
		.append("ORDER BY CUENTA");
		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String getQueryEstadoSituacionFinancieraHaciendaConac(Integer trimestre) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("T1.ABONOS_").append(i);
			cargos.append("T1.CARGOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
			}
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append("").append("SELECT T4.GRUPO, ").append("       T4.LETRA, ").append("       T4.CUENTA, ")
				.append("       T4.NOMCTA, ").append("       T4.SALDO_ACTUAL, ").append("       T4.SALDO_ANTERIOR ")
				.append("       FROM( ").append("SELECT  1  GRUPO, ")
				.append("        DECODE(T3.CUENTA, '3110', 'a', '3120', 'b', '3130', 'c')LETRA, ")
				.append("      T3.CUENTA, T3.NOMCTA, T3.SALDO_ACTUAL, T3.SALDO_ANTERIOR FROM ")
				.append("(SELECT T2.CUENTA, T2.NOMCTA, T2.NAT, ")
				.append("	(T2.SALINI + T2.ABONOS - T2.CARGOS) SALDO_ACTUAL, ").append("    MES12 SALDO_ANTERIOR ")
				.append("    FROM (       ").append("		SELECT T1.CUENTA, T1.NOMCTA, T1.STACTA NAT, T1.SALINI , ")
				.append("		" + cargos + " CARGOS, ").append("		" + abonos + " ABONOS ")
				.append("		FROM CUENTA T1 ").append("		WHERE SUBSTR(T1.CUENTA,1,2) = '31' ")
				.append("		AND SUBSTR(T1.CUENTA,4,1)='0' ").append("		AND SUBSTR(T1.CUENTA,3,1)<>'0' ")
				.append("		AND T1.SCTA = ''  )T2 LEFT JOIN TC_CUENTAANTERIOR TA ON ")
				.append("		T2.CUENTA = TA.CUENTA ) T3 ").append("UNION ALL ").append("SELECT 2 GRUPO, ")
				.append("       DECODE(T3.CUENTA, '3210', 'a', '3220', 'b', '3230', 'c', '3240', 'd', 'e')LETRA, ")
				.append("       T3.CUENTA, T3.NOMCTA, T3.SALDO_ACTUAL, T3.SALDO_ANTERIOR FROM ")
				.append("(SELECT T2.CUENTA, T2.NOMCTA, T2.NAT, ")
				.append("	DECODE(T2.CUENTA,'3210',-FN_400500(2),(T2.SALINI + T2.ABONOS - T2.CARGOS)) SALDO_ACTUAL, ")
				.append("    ")
				.append("     DECODE(T2.CUENTA,'3210',(SELECT (T6.S4000 - T6.S5000) RESULTADO FROM (SELECT SUM(T4.MES12) S4000, (SELECT SUM(T5.MES12) FROM TC_CUENTAANTERIOR T5 WHERE SUBSTR(T5.CUENTA,1,1)='5') S5000 ")
				.append("		FROM TC_CUENTAANTERIOR T4 ")
				.append("		WHERE SUBSTR(T4.CUENTA,1,1)='4') T6),MES12) SALDO_ANTERIOR ")
				.append("    FROM (       ").append("		SELECT T1.CUENTA, T1.NOMCTA, T1.STACTA NAT, T1.SALINI , ")
				.append("		" + cargos + " CARGOS, ").append("		" + abonos + " ABONOS ")
				.append("		FROM CUENTA T1 ").append("		WHERE SUBSTR(T1.CUENTA,1,2) = '32' ")
				.append("		AND SUBSTR(T1.CUENTA,4,1)='0' ").append("		AND SUBSTR(T1.CUENTA,3,1)<>'0' ")
				.append("		AND T1.SCTA = ''  )T2 LEFT JOIN TC_CUENTAANTERIOR TA ON ")
				.append("		T2.CUENTA = TA.CUENTA ) T3 ").append("UNION ALL ").append("SELECT 3 GRUPO, ")
				.append("       DECODE(T3.CUENTA, '3310', 'a', '3320', 'b', 'c')LETRA, ")
				.append("       T3.CUENTA, T3.NOMCTA, T3.SALDO_ACTUAL, T3.SALDO_ANTERIOR FROM ")
				.append("(SELECT T2.CUENTA, T2.NOMCTA, T2.NAT, ")
				.append("	(T2.SALINI + T2.ABONOS - T2.CARGOS) SALDO_ACTUAL, ").append("    MES12 SALDO_ANTERIOR ")
				.append("    FROM (       ").append("		SELECT T1.CUENTA, T1.NOMCTA, T1.STACTA NAT, T1.SALINI , ")
				.append("		" + cargos + " CARGOS, ").append("		" + abonos + " ABONOS ")
				.append("		FROM CUENTA T1 ").append("		WHERE SUBSTR(T1.CUENTA,1,2) = '33' ")
				.append("		AND SUBSTR(T1.CUENTA,4,1)='0' ").append("		AND SUBSTR(T1.CUENTA,3,1)<>'0' ")
				.append("		AND T1.SCTA = ''  )T2 LEFT JOIN TC_CUENTAANTERIOR TA ON ")
				.append("		T2.CUENTA = TA.CUENTA ) T3 ").append(") T4 ORDER BY T4.GRUPO, T4.CUENTA ASC");
		System.out.println(sSql.toString());
		return sSql.toString();
	}
	
	@Override
	public String getQueryEstadoSituacionFinancieraHaciendaInfoem(Integer trimestre, Integer idSector) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargosAnt = new StringBuilder();
		StringBuilder abonosAnt = new StringBuilder();
		Integer month = getMonth(trimestre);
		Integer monthB = month == 3 ? 1 : month-3;
		for (int i = 1; i <= monthB; i++) {
			abonosAnt.append("C.ABONOS_").append(i);
			cargosAnt.append("C.CARGOS_").append(i);
			if (i < monthB) {
				cargosAnt.append(" + ");
				abonosAnt.append(" + ");
			}
		}
		
		for (int i = 1; i <= month; i++) {
			abonos.append("C.ABONOS_").append(i);
			cargos.append("C.CARGOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
			}
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT ")
		.append("1 PRINCIPAL, 2 GRUPO,  'a' LETRA, ")
		.append("'3211' CUENTA, 'Resultados del Ejercicio (Ahorro/ Desahorro)' NOMCTA, ")
		.append("(SUM(T3.VACT4)-SUM(T3.VACT5)) SALDO_ACTUAL, ")
		.append("(SUM(T3.VAN4)-SUM(T3.VAN5)) SALDO_ANTERIOR ")
		.append("FROM (  ")
		.append("SELECT  ")
		.append("CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMANT) ELSE 0.00 END VAN4,  ")
		.append("CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMANT) ELSE 0.00 END VAN5,  ")
		.append("CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMACT) ELSE 0.00 END VACT4,  ")
		.append("CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMACT)ELSE 0.00 END VACT5  ")
		.append("FROM (  ")
		.append("SELECT T1.GRUPO, T1.SALINI,  ")
		.append("CASE WHEN T1.GRUPO ='4' THEN (T1.SALINI+ABONOS_ANT-CARGOS_ANT)  ")
		.append("ELSE (T1.SALINI-ABONOS_ANT+CARGOS_ANT) END VMANT,  ")
		.append("CASE WHEN T1.GRUPO ='4' THEN (T1.SALINI+ABONOS_ACT-CARGOS_ACT)  ")
		.append("ELSE (T1.SALINI-ABONOS_ACT+CARGOS_ACT) END VMACT  ")
		.append("FROM (  ")
		.append("SELECT  ")
		.append("SUBSTR(C.CUENTA,1,1) GRUPO,  ")
		.append("C.CUENTA, C.SALINI,  ")
		.append("SUM("+cargosAnt+") CARGOS_ANT, ")
		.append("SUM("+abonosAnt+") ABONOS_ANT, ")
		.append("SUM("+abonos+") ABONOS_ACT, ")
		.append("SUM("+cargos+") CARGOS_ACT ")
		.append("FROM  ")
		.append("CUENTA C  ")
		.append("WHERE  ")
		.append("C.IDSECTOR = "+idSector)
		.append(" AND ( ( (SUBSTR(C.CUENTA,1,1) =4 AND  ")
		.append("SUBSTR(C.CUENTA,4,1)<>'0') OR  ")
		.append("(SUBSTR(C.CUENTA,1,1) = 5 AND  ")
		.append("SUBSTR(C.CUENTA,2,1) > 0) ) AND  ")
		.append("C.SCTA ='')  ")
		.append("GROUP BY  ")
		.append("C.CUENTA,  ")
		.append("C.SALINI  ")
		.append("ORDER BY  ")
		.append("C.CUENTA)T1)T2  ")
		.append("GROUP BY  ")
		.append("T2.GRUPO )T3 ")
		.append("UNION ALL ")
		.append("SELECT  ")
		.append("1 PRINCIPAL, 2 GRUPO,   ")
		.append("CASE WHEN CUENTA = '3232' THEN 'c' ELSE 'b' END LETRA,  ")
		.append("CUENTA, ")
		.append("CASE WHEN CUENTA = '3232' THEN 'Revalúos' ELSE NOMCTA END NOMCTA,  ")
		.append("SALINI-CARGOS_ACT+ABONOS_ACT SALDO_ACTUAL,  ")
		.append("SALINI-CARGOS_ANT+ABONOS_ANT SALDO_ANTERIOR  ")
		.append("FROM(  ")
		.append("SELECT   ")
		.append("C.NOMCTA, C.CUENTA, C.SALINI,   ")
		.append(cargosAnt+" CARGOS_ANT, ")
		.append(abonosAnt+" ABONOS_ANT, ")
		.append(abonos+" ABONOS_ACT, ")
		.append(cargos+" CARGOS_ACT ")
		.append("FROM   MAYCTA MA  ")
		.append("INNER JOIN CUENTA C ON MA.CUENTA = C.CUENTA   ")
		.append("WHERE  C.CUENTA IN ('3221','3232')   ")
		.append("AND C.SCTA = ''   ")
		.append("AND C.IDSECTOR = "+idSector)
		.append(" ) UNION ALL ")
		.append("SELECT * FROM ( VALUES ")
		.append("(1, 1, 'a', '3111', 'Aportaciones', 0.00, 0.00), ")
		.append("(1, 1, 'b', '3112', 'Donaciones de Capital', 0.00, 0.00), ")
		.append("(1, 1, 'c', '3113', 'Actualización de la Hacienda Pública/Patrimonio', 0.00, 0.00), ")
		.append("(1, 2, 'd', '3233', 'Reservas', 0.00, 0.00), ")
		.append("(1, 2, 'e', '3234', 'Rectificaciones de Resultados de Ejercicios Anteriores', 0.00, 0.00), ")
		.append("(1, 3, 'a', '3311', 'Resultado por Posición Monetaria', 0.00, 0.00), ")
		.append("(1, 3, 'b', '3312', 'Resultado por Tenencia de Activos no Monetarios', 0.00, 0.00) ")
		.append(") AS TEMP (PRINCIPAL, GRUPO, LETRA, CUENTA, NOMCTA, SALDO_ACTUAL, SALDO_ANTERIOR) ")
		.append("ORDER BY CUENTA");
		System.out.println(sSql.toString());
		return sSql.toString();
	}

	@Override
	public String getQueryEstadoAnaliticoIngresoDetalladoConac(Integer trimestre) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargosRest = new StringBuilder();
		StringBuilder abonosRest = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("T1.ABONOS_").append(i);
			cargos.append("T1.CARGOS_").append(i);
			cargosRest.append("T1.CARGOS_").append(i);
			abonosRest.append("T1.ABONOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
				cargosRest.append(" - ");
				abonosRest.append(" - ");
			}
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT PR.ID, ").append("       DECODE(PR.PRINCIPAL, 4,2,1) SUPER, ")
				.append("       PR.PRINCIPAL, ").append("       PR.GRUPO, ").append("       PR.LETRA_PADRE, ")
				.append("       PR.LETRA_HIJA, ").append("       PR.SCTA, ").append("       PR.SSCTA, ")
				.append("       PR.SSSCTA, ").append("       PR.CONCEPTO, ").append("       PR.ESTIMADO, ")
				.append("       PR.AMPLIACIONES, ").append("       PR.MODIFICADO, ").append("       PR.DEVENGADO, ")
				.append("       PR.RECAUDADO, ").append("       PR.DIFERENCIA ").append("      FROM( ")
				.append("SELECT TI.ID, ").append("       1 PRINCIPAL, ").append("       1 GRUPO, ")
				.append("       TI.LETRA_PADRE, ").append("       TI.LETRA_HIJA, ").append("       T4.SCTA, ")
				.append("       T4.SSCTA, ").append("       T4.SSSCTA, ").append("       TI.CONCEPTO, ")
				.append("       NVL(T4.ESTIMADO,0.00) ESTIMADO, ")
				.append("       NVL(T4.AMPLIACIONES,0.00) AMPLIACIONES, ")
				.append("       NVL(T4.MODIFICADO,0.00) MODIFICADO, ")
				.append("       NVL(T4.DEVENGADO,0.00) DEVENGADO, ").append("       NVL(T4.RECAUDADO,0.00) RECAUDADO, ")
				.append("       NVL(T4.DIFERENCIA,0.00) DIFERENCIA ").append("     FROM ( ")
				.append("SELECT SUBSTR(T3.SCTA, 7,4) SCTA, T3.SSCTA, T3.SSSCTA, SUM(T3.ESTIMADO) ESTIMADO, SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.ESTIMADO + T3.AMPLIACIONES) -  SUM(T3.RECAUDADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ").append(" ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND SUBSTR(T1.SCTA,7,2) = '41'  ")
				.append("AND T1.SSSSCTA = '' ").append("AND T1.SSSCTA = '' ").append("AND T1.SCTA <> '' ")
				.append("AND SUBSTR(T1.SCTA,9,1) <> '0' ").append("AND SUBSTR(T1.SCTA,10,1) = '0' ")
				.append("AND T1.SCTA <= '0000004160') T3 ")
				.append("GROUP BY T3.SCTA, T3.SSCTA, T3.SSSCTA) T4 RIGHT JOIN TC_INGRESO_DETALLADO_CONAC TI  ")
				.append("      ON T4.SCTA = TI.SCTA  AND T4.SSCTA = TI.SSCTA   AND T4.SSSCTA = TI.SSSCTA  ")
				.append("WHERE TI.LETRA_PADRE IN (  'A', 'B', 'C', 'D', 'E', 'F', 'G') ")
				.append("  AND TI.LETRA_HIJA NOT IN ('a1', 'a2', 'a3', 'a4', 'a5', 'a6', 'a6', 'a7', 'a8','b1' ,'b2', 'b3', 'b4', 'c1', 'c2') ")
				.append("  AND TI.SCTA  NOT IN ( '4399', '') ").append(" ").append("UNION ALL ")
				.append("SELECT TI.ID, ").append("       1 PRINCIPAL, ").append("       2 GRUPO, ")
				.append("       TI.LETRA_PADRE, ").append("       TI.LETRA_HIJA, ").append("       T4.SCTA, ")
				.append("       T4.SSCTA, ").append("       T4.SSSCTA, ").append("       TI.CONCEPTO, ")
				.append("       NVL(T4.ESTIMADO, 0.0) ESTIMADO, ")
				.append("       NVL(T4.AMPLIACIONES, 0.0) AMPLIACIONES, ")
				.append("       NVL(T4.MODIFICADO, 0.0) MODIFICADO, ")
				.append("       NVL(T4.DEVENGADO, 0.0) DEVENGADO, ").append("       NVL(T4.RECAUDADO, 0.0) RECAUDADP, ")
				.append("       NVL(T4.DIFERENCIA, 0.0) DIFERENCIA ").append("     FROM ( ")
				.append("SELECT SUBSTR(T3.SCTA,7,4) SCTA, SUBSTR(T3.SSCTA,14,2) SSCTA, SUBSTR(T3.SSSCTA,3,2) SSSCTA, SUM(T3.ESTIMADO) ESTIMADO, SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.ESTIMADO + T3.AMPLIACIONES) -  SUM(T3.RECAUDADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ").append(" ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND SUBSTR(T1.SCTA,7,4) = '4211'  ")
				.append("AND T1.SSSSCTA <> '' ").append("AND T1.SSSCTA <= '0006' ").append(") T3 ")
				.append("GROUP BY T3.SCTA, T3.SSCTA, T3.SSSCTA) T4 RIGHT JOIN  TC_INGRESO_DETALLADO_CONAC TI ")
				.append("   ON T4.SCTA = TI.SCTA AND T4.SSCTA = TI.SSCTA AND T4.SSSCTA = TI.SSSCTA ")
				.append("WHERE TI.LETRA_PADRE = 'H'  ").append("UNION ALL ").append("SELECT TI.ID, ")
				.append("       1 PRINCIPAL, ").append("       3 GRUPO, ").append("       TI.LETRA_PADRE, ")
				.append("       TI.LETRA_HIJA, ").append("       T4.SCTA,  ").append("       T4.SSCTA, ")
				.append("       T4.SSSCTA,  ").append("       TI.CONCEPTO, ")
				.append("       NVL(T4.ESTIMADO, 0.00) ESTIMADO, ")
				.append("       NVL(T4.AMPLIACIONES, 0.0) AMPLIACIONES, ")
				.append("       NVL(T4.MODIFICADO, 0.00) MODIFICADO, ")
				.append("       NVL(T4.DEVENGADO, 0.00) DEVENGADO, ")
				.append("       NVL(T4.RECAUDADO, 0.00) RECAUDADO, ")
				.append("       NVL(T4.DIFERENCIA, 0.00) DIFERENCIA ").append("     FROM ( ").append(" ")
				.append("SELECT SUBSTR(T3.SCTA,7,4) SCTA, SUBSTR(T3.SSCTA,14,2) SSCTA, SUBSTR(T3.SSSCTA,3,2) SSSCTA, SUBSTR(T3.SSSSCTA,2,2) SSSSCTA, ")
				.append("SUM(T3.ESTIMADO) ESTIMADO,  ")
				.append("SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.ESTIMADO + T3.AMPLIACIONES) -  SUM(T3.RECAUDADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ").append(" ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND SUBSTR(T1.SCTA,7,4) = '4214'  ")
				.append("AND T1.SSSSCTA <> '' ").append("AND SUBSTR(T1.SSSCTA,4,1) <> '2' ")
				.append("AND T1.SSSCTA||T1.SSSSCTA <> '0003001' ").append("AND T1.SSSCTA||T1.SSSSCTA <> '0003003' ")
				.append("AND T1.SSSCTA||T1.SSSSCTA <> '0003005' ")
				.append("AND T1.SSCTA||T1.SSSCTA||T1.SSSSCTA <> '0000000000000020001001') T3 ")
				.append("GROUP BY T3.SCTA, T3.SSCTA, T3.SSSCTA, T3.SSSSCTA ")
				.append("ORDER BY T3.SSSCTA ) T4 RIGHT JOIN TC_INGRESO_DETALLADO_CONAC TI ON  ")
				.append("    T4.SCTA = TI.SCTA AND T4.SSCTA = TI.SSCTA AND T4.SSSCTA = TI.SSSCTA AND T4.SSSSCTA = TI.SSSSCTA ")
				.append("WHERE TI.LETRA_PADRE = 'I' AND TI.LETRA_HIJA <> 'i5' ").append("UNION ALL ").append(" ")
				.append("SELECT TI.ID, ").append("       1 PRINCIPAL, ").append("       3 GRUPO, ")
				.append("       TI.LETRA_PADRE, ").append("       TI.LETRA_HIJA, ").append("       T5.SCTA, ")
				.append("       T5.SSCTA, ").append("       T5.SSSCTA, ").append("       TI.CONCEPTO, ")
				.append("       T5.ESTIMADO, ").append("       T5.AMPLIACIONES, ").append("       T5.MODIFICADO, ")
				.append("       T5.DEVENGADO, ").append("       T5.RECAUDADO, ").append("       T5.DIFERENCIA ")
				.append("     FROM( ").append("SELECT T4.SCTA,  ").append("       '' SSCTA, ")
				.append("       '' SSSCTA, ").append("       SUM(NVL(T4.ESTIMADO, 0.00)) ESTIMADO,  ")
				.append("       SUM(NVL(T4.AMPLIACIONES, 0.00)) AMPLIACIONES, ")
				.append("       SUM(NVL(T4.MODIFICADO, 0.00))  MODIFICADO, ")
				.append("       SUM(NVL(T4.DEVENGADO, 0.00)) DEVENGADO, ")
				.append("       SUM(NVL(T4.RECAUDADO, 0.00)) RECAUDADO, ")
				.append("       SUM(NVL(T4.DIFERENCIA,0.00)) DIFERENCIA ").append("     FROM( ")
				.append("SELECT SUBSTR(T3.SCTA,7,4) SCTA, T3.SSCTA, T3.SSSCTA, T3.SSSSCTA, ")
				.append("SUM(T3.ESTIMADO) ESTIMADO,  ")
				.append("SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.ESTIMADO + T3.AMPLIACIONES) -  SUM(T3.RECAUDADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ").append(" ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND SUBSTR(T1.SCTA,7,4) = '4214'  ")
				.append("AND (T1.SSCTA||T1.SSSCTA||T1.SSSSCTA = '0000000000000010002001'  ")
				.append("  OR T1.SSCTA||T1.SSSCTA||T1.SSSSCTA = '0000000000000010003001' ")
				.append("  OR T1.SSCTA||T1.SSSCTA||T1.SSSSCTA = '0000000000000010003003' ")
				.append("  OR T1.SSCTA||T1.SSSCTA||T1.SSSSCTA = '0000000000000010003005' ").append(") ").append(" ")
				.append(") T3 ").append("GROUP BY T3.SCTA, T3.SCTA, T3.SSCTA, T3.SSSCTA, T3.SSSSCTA ").append(") T4 ")
				.append("GROUP BY T4.SCTA ").append(") T5, TC_INGRESO_DETALLADO_CONAC TI ")
				.append("WHERE T5.SCTA = TI.SCTA ").append(" AND TI.LETRA_HIJA = 'i5'  ").append(" ")
				.append("UNION ALL  ").append("SELECT TI.ID, ").append("       1 PRINCIPAL, ")
				.append("       DECODE(LETRA_PADRE, 'J', 4, 5) GRUPO, ").append("       TI.LETRA_PADRE, ")
				.append("       TI.LETRA_HIJA, ").append("       TI.SCTA, ").append("       TI.SSCTA, ")
				.append("       TI.SSSCTA, ").append("       TI.CONCEPTO, ").append("       0.00 ESTIMADO, ")
				.append("       0.00 AMPLIACIONES, ").append("       0.00 MODIFICADO, ")
				.append("       0.00 DEVENGADO, ").append("       0.00 RECAUDADO, ").append("       0.00 DIFEREMCIA ")
				.append("     FROM TC_INGRESO_DETALLADO_CONAC TI ").append("WHERE TI.LETRA_PADRE IN ('J', 'K') ")
				.append("UNION ALL ").append("SELECT TI.ID, ").append("       1 PRINCIPAL, ").append("       6 GRUPO, ")
				.append("       TI.LETRA_PADRE, ").append("       TI.LETRA_HIJA, ").append("       T4.SCTA, ")
				.append("       T4.SSCTA, ").append("       T4.SSSCTA, ").append("       TI.CONCEPTO, ")
				.append("       DECODE(TI.LETRA_HIJA , 'l2', NVL(T4.ESTIMADO, 0.00), 0.0) ESTIMADO,  ")
				.append("       DECODE(TI.LETRA_HIJA , 'l2', NVL(T4.AMPLIACIONES, 0.00), 0.0) AMPLIACIONES, ")
				.append("       DECODE(TI.LETRA_HIJA , 'l2', NVL(T4.MODIFICADO, 0.00), 0.0)  MODIFICADO, ")
				.append("       DECODE(TI.LETRA_HIJA , 'l2', NVL(T4.DEVENGADO, 0.00), 0.0) DEVENGADO, ")
				.append("       DECODE(TI.LETRA_HIJA , 'l2', NVL(T4.RECAUDADO, 0.00), 0.0) RECAUDADO, ")
				.append("       DECODE(TI.LETRA_HIJA , 'l2', NVL(T4.DIFERENCIA,0.00), 0.0) DIFERENCIA ")
				.append("    FROM( ").append("        ")
				.append("SELECT SUBSTR(T3.SCTA,7,1)||'215' SCTA, '' SSCTA, '' SSSCTA, ")
				.append("SUM(T3.ESTIMADO) ESTIMADO,  ")
				.append("SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.ESTIMADO + T3.AMPLIACIONES) -  SUM(T3.RECAUDADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ").append(" ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND  ").append("	( ")
				.append("	(SUBSTR(T1.SCTA,7,4) = '4399' AND T1.SSCTA = '000000000000003' AND T1.SSSCTA = '0001' AND T1.SSSSCTA = '001')  ")
				.append("	OR  ").append("	(SUBSTR(T1.SCTA,7,4) = '4215' AND T1.SSSSCTA <> '' ) ").append("	) ")
				.append(") T3 ").append("GROUP BY SUBSTR(T3.SCTA,7,1) ")
				.append(" ) T4 , TC_INGRESO_DETALLADO_CONAC TI  ")
				.append(" WHERE T4.SCTA = TI.SCTA AND T4.SSCTA = TI.SSCTA  AND T4.SSSCTA = TI.SSSCTA ").append(" ")
				.append("UNION ALL ").append("SELECT TI.ID, ").append("       2 PRINCIPAL, ").append("       7 GRUPO, ")
				.append("       TI.LETRA_PADRE, ").append("       TI.LETRA_HIJA, ").append("       T4.SCTA, ")
				.append("       T4.SSCTA, ").append("       T4.SSSCTA, ").append("       TI.CONCEPTO, ")
				.append("       NVL(T4.ESTIMADO, 0.00) ESTIMADO,  ")
				.append("       NVL(T4.AMPLIACIONES, 0.00) AMPLIACIONES, ")
				.append("       NVL(T4.MODIFICADO, 0.00) MODIFICADO, ")
				.append("       NVL(T4.DEVENGADO, 0.00) DEVENGADO, ")
				.append("       NVL(T4.RECAUDADO, 0.00) RECAUDADO, ")
				.append("       NVL(T4.DIFERENCIA,0.00) DIFERENCIA ").append("     FROM ( ")
				.append("SELECT SUBSTR(T3.SCTA,7,4) SCTA, SUBSTR(T3.SSCTA,14,2) SSCTA, SUBSTR(T3.SSSCTA,3,2) SSSCTA, T3.SSSSCTA, ")
				.append("SUM(T3.ESTIMADO) ESTIMADO,  ")
				.append("SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.ESTIMADO + T3.AMPLIACIONES) -  SUM(T3.RECAUDADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ").append(" ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND SUBSTR(T1.SCTA,7,4) = '4212'  ")
				.append("AND T1.SSCTA <> '' ").append("AND T1.SSSCTA <> '' ").append("AND T1.SSSSCTA = '') T3 ")
				.append("GROUP BY T3.SCTA, T3.SSCTA, T3.SSSCTA, T3.SSSSCTA ").append("ORDER BY T3.SSSCTA ")
				.append(") T4 RIGHT JOIN TC_INGRESO_DETALLADO_CONAC TI ON T4.SCTA = TI.SCTA AND T4.SSCTA = TI.SSCTA AND T4.SSSCTA = TI.SSSCTA ")
				.append("WHERE TI.LETRA_PADRE = 'A' AND TI.LETRA_HIJA NOT IN ( 'a', '') ").append("UNION ALL ")
				.append("SELECT TI.ID, ").append("       2 PRINCIPAL, ").append("       8 GRUPO, ")
				.append("       TI.LETRA_PADRE, ").append("       TI.LETRA_HIJA, ").append("       T4.SCTA,   ")
				.append("       T4.SSCTA, ").append("       T4.SSSCTA, ").append("       TI.CONCEPTO, ")
				.append("       DECODE(TI.LETRA_HIJA, 'b1', NVL(T4.ESTIMADO, 0.00), 'b4', NVL(T4.ESTIMADO, 0.00), 0.00)  ESTIMADO,  ")
				.append("       DECODE(TI.LETRA_HIJA, 'b1',NVL(T4.AMPLIACIONES, 0.00),'b4',NVL(T4.AMPLIACIONES, 0.00), 0.00) AMPLIACIONES, ")
				.append("       DECODE(TI.LETRA_HIJA, 'b1',NVL(T4.MODIFICADO, 0.00),'b4', NVL(T4.MODIFICADO, 0.00),0.00) MODIFICADO, ")
				.append("       DECODE(TI.LETRA_HIJA, 'b1',NVL(T4.DEVENGADO, 0.00),'b4',NVL(T4.DEVENGADO, 0.00), 0.00) DEVENGADO, ")
				.append("       DECODE(TI.LETRA_HIJA, 'b1',NVL(T4.RECAUDADO, 0.00),'b4',NVL(T4.RECAUDADO, 0.00), 0.00) RECAUDADO, ")
				.append("       DECODE(TI.LETRA_HIJA, 'b1',NVL(T4.DIFERENCIA,0.00),'b4',NVL(T4.DIFERENCIA,0.00) ,0.00) DIFERENCIA ")
				.append("     FROM ( ")
				.append("SELECT SUBSTR(T3.SCTA,7,4) SCTA, SUBSTR(T3.SSCTA,14,2) SSCTA, SUBSTR(T3.SSSCTA,3,2) SSSCTA, ")
				.append("SUM(T3.ESTIMADO) ESTIMADO,  ")
				.append("SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.ESTIMADO + T3.AMPLIACIONES) -  SUM(T3.RECAUDADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ").append(" ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND SUBSTR(T1.SCTA,7,4) = '4213'  ")
				.append("AND T1.SSCTA = '000000000000001' ").append("AND T1.SSSCTA = '0001' ")
				.append("AND T1.SSSSCTA = '') T3 ").append("GROUP BY T3.SCTA, T3.SSCTA, T3.SSSCTA ").append(" ")
				.append("UNION ALL ").append("SELECT SUBSTR(T3.SCTA,7,1)||'213' SCTA, ").append("       '' SSCTA, ")
				.append("       '' SSSCTA, ").append("SUM(T3.ESTIMADO) ESTIMADO,  ")
				.append("SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.ESTIMADO + T3.AMPLIACIONES) -  SUM(T3.RECAUDADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ").append(" ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND SUBSTR(T1.SCTA,7,4) IN ('4213','4223')  ")
				.append("AND T1.SSCTA <> '' ").append("AND T1.SSSCTA <> '' ").append("AND T1.SSSSCTA <> '' ")
				.append("AND T1.SCTA||T1.SSCTA <> '0000004213000000000000001') T3 ")
				.append("GROUP BY SUBSTR(T3.SCTA,7,1) ")
				.append(") T4 RIGHT JOIN TC_INGRESO_DETALLADO_CONAC TI ON T4.SCTA = TI.SCTA AND T4.SSCTA = TI.SSCTA AND T4.SSSCTA = TI.SSSCTA ")
				.append("WHERE TI.LETRA_PADRE = 'B' AND TI.LETRA_HIJA <> '' ").append("UNION ALL ")
				.append("SELECT TI.ID, ").append("       2 PRINCIPAL, ")
				.append("       DECODE(LETRA_PADRE, 'C', 9, 'D',10, 11) GRUPO, ").append("       TI.LETRA_PADRE, ")
				.append("       TI.LETRA_HIJA, ").append("       SCTA, ").append("       SSCTA, ")
				.append("       SSSCTA, ").append("       TI.CONCEPTO, ").append("        0.00 ESTIMADO, ")
				.append("       0.00 AMPLIACIONES, ").append("       0.00 MODIFICADO, ")
				.append("       0.00 DEVENGADO, ").append("       0.00 RECAUDADO, ").append("       0.00 DIFEREMCIA ")
				.append("     FROM TC_INGRESO_DETALLADO_CONAC TI ").append("WHERE TI.LETRA_PADRE IN ('C','D', 'E') ")
				.append("  AND TI.SCTA IN ('c', '') ").append("UNION ALL ").append("SELECT TI.ID, ")
				.append("       DECODE(TI.LETRA_PADRE, 'A', 3, 4) PRINCIPAL, ")
				.append("       DECODE(TI.LETRA_PADRE, 'A',12, 13) GRUPO, ").append("       TI.LETRA_PADRE, ")
				.append("       TI.LETRA_HIJA, ").append("       TI.SCTA, ").append("       TI.SSCTA, ")
				.append("       TI.SSSCTA, ").append("       TI.CONCEPTO, ")
				.append("       DECODE(TI.LETRA_PADRE, '2', 0.00, T4.ESTIMADO) ESTIMADO, ")
				.append("       DECODE(TI.LETRA_PADRE, '2', 0.00, T4.AMPLIACIONES)AMPLIACIONES, ")
				.append("       DECODE(TI.LETRA_PADRE, '2', 0.00, T4.MODIFICADO)MODIFICADO, ")
				.append("       DECODE(TI.LETRA_PADRE, '2', 0.00, T4.DEVENGADO)DEVENGADO, ")
				.append("       DECODE(TI.LETRA_PADRE, '2', 0.00, T4.RECAUDADO)RECAUDADO, ")
				.append("       DECODE(TI.LETRA_PADRE, '2', 0.00, T4.DIFERENCIA)DIFERENCIA ").append("     FROM( ")
				.append("SELECT SUBSTR(T3.SCTA,7,4) SCTA, '' SSCTA, '' SSSCTA, ").append("SUM(T3.ESTIMADO) ESTIMADO,  ")
				.append("SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.ESTIMADO + T3.AMPLIACIONES) -  SUM(T3.RECAUDADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ").append(" ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND SUBSTR(T1.SCTA,7,4) = ('4399')  ")
				.append("AND T1.SSCTA = '000000000000003' ").append("AND T1.SSSCTA = '0002' ")
				.append("AND T1.SSSSCTA = '') T3 ").append("GROUP BY T3.SCTA ) T4 , TC_INGRESO_DETALLADO_CONAC TI ")
				.append("WHERE T4.SCTA = TI.SCTA ").append(" ) PR ").append("ORDER BY PR.ID ASC");
		System.out.println(sSql.toString());
		return sSql.toString();
	}
	
	@Override
	public String getQueryEstadoAnaliticoIngresoDetalladoInfoem(Integer trimestre, Integer idSector) {
		StringBuilder dicargos = new StringBuilder();
		StringBuilder ciabonos = new StringBuilder();
		StringBuilder cicargos = new StringBuilder();
		
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			dicargos.append("DI.CARGOS_").append(i);
			ciabonos.append("CI.ABONOS_").append(i);
			cicargos.append("CI.CARGOS_").append(i);
			if (i < month) {
				dicargos.append(" + ");
				ciabonos.append(" + ");
				cicargos.append(" + ");
			}
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT ")
		.append("CASE WHEN CTA = '811000439900010119' THEN 3 ELSE 1 END PRINCIPAL, ")
		.append("CASE ")
		.append("WHEN CTA = '8110004311000101' THEN 5 ")
		.append("WHEN CTA = '8110004399' THEN 7 ")
		.append("WHEN CTA = '8110004223' THEN 26 ")
		.append("WHEN CTA = '811000439900010119' THEN 51 ")
		.append("END ORDEN, ")
		.append("CASE ")
		.append("WHEN CTA = '8110004311000101' THEN '    E. Productos' ")
		.append("WHEN CTA = '8110004399' THEN '    G. Ingresos por Ventas de Bienes y Servicios' ")
		.append("WHEN CTA = '8110004223' THEN '    J. Transferencias' ")
		.append("WHEN CTA = '811000439900010119' THEN '    A. Ingresos Derivados de Financiamientos' ")
		.append("END CONCEPTO, ")
		.append("CASE ")
		.append("WHEN CTA = '8110004311000101' THEN 0.00 ")
		.append("WHEN CTA = '8110004399' THEN ESTIMADA ")
		.append("WHEN CTA = '8110004223' THEN ESTIMADA ")
		.append("WHEN CTA = '811000439900010119' THEN 0.00 ")
		.append("END ESTIMADO, ")
		.append("CASE WHEN CTA = '811000439900010119' THEN AMPLIACION ELSE 0.00 END AMPLIACION, ")
		.append("CASE WHEN CTA = '811000439900010119' THEN 0.00 ELSE RECAUDADO END DEVENGADO, ")
		.append("CASE WHEN CTA = '811000439900010119' THEN 0.00 ELSE RECAUDADO END RECAUDADO ")
		.append("FROM ")
		.append("( ")
		.append("SELECT  ")
		.append("DI.CUENTA||SUBSTR(DI.SCTA,10)||SUBSTR(DI.SSCTA,17)|| ")
		.append("SUBSTR(DI.SSSCTA,3)||SUBSTR(DI.SSSSCTA,2) CTA, ")
		.append("DI.SALINI ESTIMADA, ")
		.append(dicargos+" AMPLIACION, ")
		.append("("+ciabonos+") ")
		.append("-("+cicargos+") RECAUDADO ")
		.append("FROM  ")
		.append("CUENTA AS DI ")
		.append("INNER JOIN CUENTA AS CI ON CI.CUENTA='8150' AND CI.SCTA=DI.SCTA AND CI.SSCTA=DI.SSCTA AND CI.SSSCTA=DI.SSSCTA AND CI.SSSSCTA=DI.SSSSCTA AND CI.IDSECTOR = DI.IDSECTOR ")
		.append("WHERE (DI.CUENTA='8110') AND DI.NOTCTA=0 AND DI.IDSECTOR= "+idSector)
		.append(" ORDER BY DI.CUENTA,DI.SCTA, DI.SSCTA, DI.SSSCTA, DI.SSSSCTA ")
		.append(") WHERE CTA IN ('8110004223','8110004311000101','8110004399','811000439900010119') ")
		.append("UNION ALL ")
		.append("SELECT * FROM ( VALUES ")
		.append("(1, 1, '    A. Impuestos', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 2, '    B. Cuotas y Aportaciones de Seguridad Social', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 3, '    C. Contribuciones de Mejoras', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 4, '    D. Derechos', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 6, '    F. Aprovechamientos', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 8, '    H. Participaciones (H=h1+h2+h3+h4+h5+h6+h7+h8+h9+h10+h11)', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 9, '        h1) Fondo General de Participaciones', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 10, '        h2) Fondo de Fomento Municipal', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 11, '        h3) Fondo de Fiscalización y Recaudación', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 12, '        h4) Fondo de Compensación', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 13, '        h5) Fondo de Extracción de Hidrocarburos', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 14, '        h6) Impuesto Especial Sobre Producción y Servicios', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 15, '        h7) 0.136% de la Recaudación Federal Participable', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 16, '        h8) 3.17% Sobre Extracción de Petróleo', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 17, '        h9) Gasolinas y Diésel', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 18, '        h10) Fondo del Impuesto Sobre la Renta', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 19, '        h11) Fondo de Estabilización de los Ingresos de las Entidades Federativas', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 20, '    I. Incentivos Derivados de la Colaboración Fiscal (I=i1+i2+i3+i4+i5)', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 21, '        i1) Tenencia o Uso de Vehículos', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 22, '        i2) Fondo de Compensación ISAN', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 23, '        i3) Impuesto Sobre Automóviles Nuevos', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 24, '        i4) Fondo de Compensación de Repecos-Intermedios', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 25, '        i5) Otros Incentivos Económicos', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 27, '    K. Convenios', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 28, '        k1) Otros Convenios y Subsidios', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 29, '    L. Otros Ingresos de Libre Disposición (L=l1+l2)', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 30, '        l1) Participaciones en Ingresos Locales', 0.00, 0.00, 0.00, 0.00), ")
		.append("(1, 31, '        l2) Otros Ingresos de Libre Disposición', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 32, '    A. Aportaciones (A=a1+a2+a3+a4+a5+a6+a7+a8)', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 33, '        a1) Fondo de Aportaciones para la Nómina Educativa y Gasto Operativo', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 34, '        a2) Fondo de Aportaciones para los Servicios de Salud', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 35, '        a3) Fondo de Aportaciones para la Infraestructura Social', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 36, '        a4) Fondo de Aportaciones para el Fortalecimiento de los Municipios y de las Demarcaciones Territoriales del Distrito Federal', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 37, '        a5) Fondo de Aportaciones Múltiples', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 38, '        a6) Fondo de Aportaciones para la Educación Tecnológica y de Adultos', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 39, '        a7) Fondo de Aportaciones para la Seguridad Pública de los Estados y del Distrito Federal', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 40, '        a8) Fondo de Aportaciones para el Fortalecimiento de las Entidades Federativas', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 41, '    B. Convenios (B=b1+b2+b3+b4)', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 42, '        b1) Convenios de Protección Social en Salud', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 43, '        b2) Convenios de Descentralización', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 44, '        b3) Convenios de Reasignación', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 45, '        b4) Otros Convenios y Subsidios', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 46, '    C. Fondos Distintos de Aportaciones (C=c1+c2)', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 47, '        c1) Fondo para Entidades Federativas y Municipios Productores de Hidrocarburos', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 48, '        c2) Fondo Minero', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 49, '    D. Transferencias, Subsidios y Subvenciones, y Pensiones y Jubilaciones', 0.00, 0.00, 0.00, 0.00), ")
		.append("(2, 50, '    E. Otras Transferencias Federales Etiquetadas', 0.00, 0.00, 0.00, 0.00) ")
		.append(") AS TEMP (PRINCIPAL, ORDEN, CONCEPTO, ESTIMADO, AMPLIACION, DEVENGADO, RECAUDADO) ")
		.append("ORDER BY ORDEN");
		System.out.println(sSql.toString());
		return sSql.toString();
	}

	@Override
	public String getQueryEstadoAnaliticoIngresoLDF1(Integer trimestre) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargosRest = new StringBuilder();
		StringBuilder abonosRest = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("T1.ABONOS_").append(i);
			cargos.append("T1.CARGOS_").append(i);
			cargosRest.append("T1.CARGOS_").append(i);
			abonosRest.append("T1.ABONOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
				cargosRest.append(" - ");
				abonosRest.append(" - ");
			}
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT 1 GRUPO, ").append("       T4.SCTA, ").append("       T4.SSCTA, ")
				.append("       T4.SSSCTA, ").append("       TI.CONCEPTO, ").append("       T4.ESTIMADO, ")
				.append("       T4.AMPLIACIONES, ").append("       T4.MODIFICADO, ").append("       T4.DEVENGADO, ")
				.append("       T4.RECAUDADO, ").append("       T4.DIFERENCIA ").append("     FROM( ")
				.append("        ")
				.append("SELECT SUBSTR(T3.SCTA,7,4) SCTA, T3.SSCTA, T3.SSSCTA, SUM(T3.ESTIMADO) ESTIMADO, SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.RECAUDADO) - SUM(T3.ESTIMADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ").append(" ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND SUBSTR(T1.SCTA,7,2) = '41'  ")
				.append("AND T1.SSSSCTA = '' ").append("AND T1.SSSCTA = '' ").append("AND T1.SCTA <> '' ")
				.append("AND SUBSTR(T1.SCTA,9,1) <> '0' ").append("AND SUBSTR(T1.SCTA,10,1) = '0' ")
				.append("AND T1.SCTA <= '0000004160') T3 ")
				.append("GROUP BY T3.SCTA, T3.SSCTA, T3.SSSCTA) T4 , TC_INGRESO_DETALLADO_CONAC TI ")
				.append("WHERE T4.SCTA = TI.SCTA ").append("  AND T4.SSCTA = TI.SSCTA ")
				.append("  AND T4.SSSCTA = TI.SSSCTA ").append("UNION ALL ").append(" ").append("SELECT 1 GRUPO, ")
				.append("       T4.SCTA, ").append("       '' SSCTA, ").append("       '' SSSCTA, ")
				.append("       'Participaciones, Aportaciones, Convenios, Incentivos Derivados de la Colaboración Fiscal y Fondos Distintos de Apotación' CONCEPTO, ")
				.append("       T4.ESTIMADO, ").append("       T4.AMPLIACIONES, ").append("       T4.MODIFICADO, ")
				.append("       T4.DEVENGADO, ").append("       T4.RECAUDADO, ").append("       T4.DIFERENCIA ")
				.append("     FROM ( ").append("SELECT SUBSTR(T3.SCTA,7,1)||'170' SCTA, ")
				.append("SUM(T3.ESTIMADO) ESTIMADO,  ")
				.append("SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.RECAUDADO) - SUM(T3.ESTIMADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ").append(" ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND  ").append("	( ").append("	 ")
				.append("	(SUBSTR(T1.SCTA,7,3) = '421' AND T1.SSSSCTA <> '' ) ").append("	) ").append(") T3 ")
				.append("GROUP BY SUBSTR(T3.SCTA,7,1)) T4, TC_INGRESO_DETALLADO_CONAC TI ")
				.append("WHERE T4.SCTA = TI.SCTA ").append("  AND '' = TI.SSCTA ").append("  AND '' = TI.SSSCTA ")
				.append("UNION ALL ").append("SELECT 1 GRUPO, ").append("       SUBSTR(T3.SCTA,7,1)||'180' SCTA, ")
				.append("       '' SSCTA, ").append("       '' SSSCTA, ")
				.append("       'Transferencias, Asignaciones, Subsudios y Subvenciones, y Pensiones y Jubilaciones' CONCEPTO, ")
				.append("SUM(T3.ESTIMADO) ESTIMADO,  ")
				.append("SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.RECAUDADO) - SUM(T3.ESTIMADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ").append(" ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND  ").append("	( ").append("	 ")
				.append("	(SUBSTR(T1.SCTA,7,3) = '422' AND T1.SSSSCTA <> '' ) ").append("	) ").append(") T3 ")
				.append("GROUP BY SUBSTR(T3.SCTA,7,1) ").append("UNION ALL ").append("SELECT 4 GRUPO, ")
				.append("       SUBSTR(T3.SCTA, 7,4) SCTA, ").append("       '' SSCTA, ").append("       '' SSSCTA, ")
				.append("       'Ingresos Derivados de Financiamientos' CONCEPTO, ")
				.append("SUM(T3.ESTIMADO) ESTIMADO,  ")
				.append("SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.RECAUDADO) - SUM(T3.ESTIMADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND SUBSTR(T1.SCTA,7,4) = ('4399')  ")
				.append("AND T1.SSCTA = '000000000000003' ").append("AND T1.SSSCTA = '0002' ")
				.append("AND T1.SSSSCTA = '') T3 ").append("GROUP BY T3.SCTA ").append("UNION ALL ")
				.append("SELECT 5 GRUPO, ").append("       SUBSTR(T3.SCTA,7,1)SCTA, ").append("       '' SSCTA, ")
				.append("       '' SSSCTA, ")
				.append("       'Generación de ADEFAS Superavit Ejercicios Anteriores' CONCEPTO, ")
				.append("SUM(T3.ESTIMADO) ESTIMADO,  ")
				.append("SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.RECAUDADO) - SUM(T3.ESTIMADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ").append(" ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND  ").append("	( ")
				.append("	(SUBSTR(T1.SCTA,7,4) = '4399' AND T1.SSCTA = '000000000000003' AND T1.SSSCTA = '0001' AND T1.SSSSCTA = '001')  ")
				.append("	OR  ").append("	(SUBSTR(T1.SCTA,7,4) = '4215' AND T1.SSSSCTA <> '' ) ").append("	) ")
				.append(") T3 ").append("GROUP BY SUBSTR(T3.SCTA,7,1)  ").append("ORDER BY GRUPO, SCTA ASC");
		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String getQueryEstadoAnaliticoIngresoLDF2(Integer trimestre) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargosRest = new StringBuilder();
		StringBuilder abonosRest = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("T1.ABONOS_").append(i);
			cargos.append("T1.CARGOS_").append(i);
			cargosRest.append("T1.CARGOS_").append(i);
			abonosRest.append("T1.ABONOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
				cargosRest.append(" - ");
				abonosRest.append(" - ");
			}
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT 1 GRUPO, ").append("       T4.SCTA, ").append("       T4.SSCTA, ")
				.append("       T4.SSSCTA, ").append("       TI.CONCEPTO, ").append("       T4.ESTIMADO, ")
				.append("       T4.AMPLIACIONES, ").append("       T4.MODIFICADO, ").append("       T4.DEVENGADO, ")
				.append("       T4.RECAUDADO, ").append("       T4.DIFERENCIA ").append("     FROM( ")
				.append("        ")
				.append("SELECT SUBSTR(T3.SCTA,7,4) SCTA, T3.SSCTA, T3.SSSCTA, SUM(T3.ESTIMADO) ESTIMADO, SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.RECAUDADO) - SUM(T3.ESTIMADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ").append(" ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND SUBSTR(T1.SCTA,7,2) = '41'  ")
				.append("AND T1.SSSSCTA = '' ").append("AND T1.SSSCTA = '' ").append("AND T1.SCTA <> '' ")
				.append("AND SUBSTR(T1.SCTA,9,1) <> '0' ").append("AND SUBSTR(T1.SCTA,10,1) = '0' ")
				.append("AND T1.SCTA <= '0000004160') T3 ")
				.append("GROUP BY T3.SCTA, T3.SSCTA, T3.SSSCTA) T4 , TC_INGRESO_DETALLADO_CONAC TI ")
				.append("WHERE T4.SCTA = TI.SCTA ").append("  AND T4.SSCTA = TI.SSCTA ")
				.append("  AND T4.SSSCTA = TI.SSSCTA ").append("UNION ALL ").append(" ").append(" ")
				.append("SELECT 1 GRUPO, ").append("       SUBSTR(T3.SCTA,7,1)||'169' SCTA, ")
				.append("       '' SSCTA, ").append("       '' SSSCTA, ")
				.append("       'Participaciones, Aportaciones, Convenios, Incentivos Derivados de la Colaboración Fiscal y Fondos Distintos de Apotación' CONCEPTO, ")
				.append("SUM(T3.ESTIMADO) ESTIMADO,  ")
				.append("SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.RECAUDADO) - SUM(T3.ESTIMADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND  ").append("	( ").append("	 ")
				.append("	(SUBSTR(T1.SCTA,7,3) = '421' AND T1.SSSSCTA <> '' ) ").append("	) ").append(") T3 ")
				.append("GROUP BY SUBSTR(T3.SCTA,7,1) ").append(" ").append("UNION ALL ").append("SELECT 1 GRUPO, ")
				.append("       SUBSTR(T3.SCTA,7,1)||'170' SCTA, ").append("       '' SSCTA, ")
				.append("       '' SSSCTA, ")
				.append("       'Transferencias, Asignaciones, Subsudios y Subvenciones, y Pensiones y Jubilaciones' CONCEPTO, ")
				.append("SUM(T3.ESTIMADO) ESTIMADO,  ")
				.append("SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.RECAUDADO) - SUM(T3.ESTIMADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ").append(" ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND  ").append("	( ").append("	 ")
				.append("	(SUBSTR(T1.SCTA,7,3) = '422' AND T1.SSSSCTA <> '' ) ").append("	) ").append(") T3 ")
				.append("GROUP BY SUBSTR(T3.SCTA,7,1) ").append("UNION ALL ").append("SELECT 2 GRUPO, ")
				.append("       '1' SCTA, ").append("       '' SSCTA, ").append("       '' SSSCTA, ")
				.append("       'Cuotas y Aportaciones de Seguridad Social' CONCEPTO, ")
				.append("       0.00 ESTIMADO, ").append("       0.00 AMPLIACIONES, ")
				.append("       0.00 MODIFICADO, ").append("       0.00 DEVENGADO, ").append("       0.00 RECAUDADO, ")
				.append("       0.00 DIFERENCIA ").append("     FROM SYSIBM.SYSDUMMY1  ").append("UNION ALL ")
				.append("SELECT 2 GRUPO, ").append("       '2' SCTA, ").append("       '' SSCTA, ")
				.append("       '' SSSCTA, ").append("       'Productos' CONCEPTO, ").append("       0.00 ESTIMADO, ")
				.append("       0.00 AMPLIACIONES, ").append("       0.00 MODIFICADO, ")
				.append("       0.00 DEVENGADO, ").append("       0.00 RECAUDADO, ").append("       0.00 DIFERENCIA ")
				.append("     FROM SYSIBM.SYSDUMMY1 ").append("UNION ALL ").append("SELECT 2 GRUPO, ")
				.append("       '3' SCTA, ").append("       '' SSCTA, ").append("       '' SSSCTA, ")
				.append("       'Ingresos por Venta de Bienes, Prestaciones de Servicios y Otros Ingresos' CONCEPTO, ")
				.append("       0.00 ESTIMADO, ").append("       0.00 AMPLIACIONES, ")
				.append("       0.00 MODIFICADO, ").append("       0.00 DEVENGADO, ").append("       0.00 RECAUDADO, ")
				.append("       0.00 DIFERENCIA ").append("     FROM SYSIBM.SYSDUMMY1 ").append("UNION ALL ")
				.append("SELECT 2 GRUPO, ").append("       '4' SCTA, ").append("       '' SSCTA, ")
				.append("       '' SSSCTA, ")
				.append("       'Transferencias, Asignaciones, Subsidios y Subvenciones, y Pensiones y Jubilaciones' CONCEPTO, ")
				.append("       0.00 ESTIMADO, ").append("       0.00 AMPLIACIONES, ")
				.append("       0.00 MODIFICADO, ").append("       0.00 DEVENGADO, ").append("       0.00 RECAUDADO, ")
				.append("       0.00 DIFERENCIA ").append("     FROM SYSIBM.SYSDUMMY1 ").append("UNION ALL ")
				.append("SELECT 3 GRUPO, ").append("       SUBSTR(T3.SCTA, 7,4) SCTA, ").append("       '' SSCTA, ")
				.append("       '' SSSCTA, ").append("       'Ingresos Derivados de Financiamientos' CONCEPTO, ")
				.append("SUM(T3.ESTIMADO) ESTIMADO,  ")
				.append("SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.RECAUDADO) - SUM(T3.ESTIMADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ").append(" ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND SUBSTR(T1.SCTA,7,4) = ('4399')  ")
				.append("AND T1.SSCTA = '000000000000003' ").append("AND T1.SSSCTA = '0002' ")
				.append("AND T1.SSSSCTA = '') T3 ").append("GROUP BY T3.SCTA ").append("UNION ALL ")
				.append("SELECT 3 GRUPO, ").append("       SUBSTR(T3.SCTA,7,1)||'444'SCTA, ")
				.append("       '' SSCTA, ").append("       '' SSSCTA, ")
				.append("       'Generación de ADEFAS Superavit Ejercicios Anteriores' CONCEPTO, ")
				.append("SUM(T3.ESTIMADO) ESTIMADO,  ")
				.append("SUM(T3.AMPLIACIONES) AMPLIACIONES, SUM(T3.ESTIMADO + T3.AMPLIACIONES) MODIFICADO,  ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO, (SUM(T3.RECAUDADO) - SUM(T3.ESTIMADO)) DIFERENCIA ")
				.append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ")
				.append(" FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND  ").append("	( ")
				.append("	(SUBSTR(T1.SCTA,7,4) = '4399' AND T1.SSCTA = '000000000000003' AND T1.SSSCTA = '0001' AND T1.SSSSCTA = '001')  ")
				.append("	OR  ").append("	(SUBSTR(T1.SCTA,7,4) = '4215' AND T1.SSSSCTA <> '' ) ").append("	) ")
				.append(") T3 ").append("GROUP BY SUBSTR(T3.SCTA,7,1)  ").append("ORDER BY GRUPO, SCTA ASC");
		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String getQueryBalancePresupuestario1(Integer trimestre) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargosRest = new StringBuilder();
		StringBuilder abonosRest = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("T1.ABONOS_").append(i);
			cargos.append("T1.CARGOS_").append(i);
			cargosRest.append("T1.CARGOS_").append(i);
			abonosRest.append("T1.ABONOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
				cargosRest.append(" - ");
				abonosRest.append(" - ");
			}
		}
		StringBuilder sSql = new StringBuilder();

		sSql.append("SELECT TI.PRINCIPAL, ").append("       TI.GRUPO, ").append("       TI.LETRA, ")
			.append("       TI.SCTA, ").append("       TI.CONCEPTO, ").append("       TI.ESTIMADO, ")
			.append("       TI.DEVENGADO, ").append("       TI.RECAUDADO ").append("    FROM( ")
			.append("SELECT 1 PRINCIPAL, ").append("       1 GRUPO, ").append("       'A' LETRA, ")
			.append("       400||SUBSTR(T3.SCTA,1,1) SCTA, 'Ingresos de Libre Disposición' CONCEPTO, SUM(T3.ESTIMADO) ESTIMADO,   ")
			.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO ").append("FROM ")
			.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
			.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
			.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
			.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ")
			.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
			.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
			.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
			.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND  ")
			.append("( ( SUBSTR(T1.SCTA,7,2)='41' AND T1.SSSSCTA = '' ").append("    AND T1.SSSCTA = '' ")
			.append("    AND T1.SCTA <> '' ").append("    AND SUBSTR(T1.SCTA,9,1) <> '0' ")
			.append("    AND SUBSTR(T1.SCTA,10,1) = '0' ").append("   )  OR ")
			.append("   ( (SUBSTR(T1.SCTA,7,4) ='4211'  ").append("     AND T1.SSCTA = '' ").append("     ) ")
			.append("     OR (SUBSTR(T1.SCTA,7,4)='4214' AND T1.SSCTA='000000000000003' AND T1.SSSCTA='0001' AND T1.SSSSCTA='001') ")
			.append("     OR (SUBSTR(T1.SCTA,7,4)='4214' AND T1.SSCTA='000000000000001' AND T1.SSSCTA='0001' AND T1.SSSSCTA='001') ")
			.append("     OR (SUBSTR(T1.SCTA,7,4)='4214' AND T1.SSCTA='000000000000001' AND T1.SSSCTA='0003' AND T1.SSSSCTA='') ")
			.append("     OR (SUBSTR(T1.SCTA,7,4)='4214' AND T1.SSCTA='000000000000001' AND T1.SSSCTA='0002' AND T1.SSSSCTA='') ")
			.append("   )  ").append("    ")
			.append("OR (SUBSTR(T1.SCTA,7,4)='4399' AND T1.SSCTA='000000000000003' AND T1.SSSCTA='0001' AND T1.SSSSCTA='001') ")
			.append(") ").append(") T3 ").append("GROUP BY SUBSTR(T3.SCTA,1,1) ").append("UNION ALL ")
			.append("SELECT 1 PRINCIPAL, ").append("       1 GRUPO, ").append("       'A' LETRA, ")
			.append("       '4'||SUBSTR(T3.SCTA,1,1)||'01' SCTA, 'Transferencias Federales Etiquetadas' CONCEPTO, ")
			.append("      SUM(T3.ESTIMADO) ESTIMADO,   ")
			.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO ").append("FROM ")
			.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
			.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
			.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
			.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ")
			.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
			.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
			.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
			.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND  ")
			.append("( ( SUBSTR(T1.SCTA,7,4) = '4212' AND T1.SSCTA = '' )   ").append("   OR ")
			.append("   ( SUBSTR(T1.SCTA,7,4) = '4213'  AND T1.SSCTA = '000000000000001' AND T1.SSSCTA = '0001' AND T1.SSSSCTA = '')  ")
			.append("   OR  ").append("(SUBSTR(T1.SCTA,7,4) IN ('4213','4223')  ").append("AND T1.SSCTA <> '' ")
			.append("AND T1.SSSCTA <> '' ").append("AND T1.SSSSCTA <> '' ")
			.append("AND T1.SCTA||T1.SSCTA <> '0000004213000000000000001') ").append(") ").append(") T3 ")
			.append("GROUP BY SUBSTR(T3.SCTA,1,1) ").append("UNION ALL ").append("SELECT 1 PRINCIPAL, ")
			.append("       1 GRUPO, ").append("       'A' LETRA, ").append("   '4002' SCTA, ")
			.append("'Financiamiento' CONCEPTO, SUM(T5.ESTIMADO), SUM(T5.DEVENGADO), SUM (T5.RECAUDADO) FROM ")
			.append("(SELECT T3.SCTA, ").append("SUM(T3.ESTIMADO) ESTIMADO,    ")
			.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO ").append("FROM ")
			.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO, ")
			.append("DECODE(T1.CUENTA,'8140',(  " + abonos + " - " + cargosRest + "),0.0) DEVENGADO, ")
			.append("DECODE(T1.CUENTA,'8150',(  " + abonos + " - " + cargosRest + "),0.0) RECAUDADO "

			).append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
			.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
			.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
			.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND SUBSTR(T1.SCTA,7,4) = ('4399')  ")
			.append("AND T1.SSCTA = '000000000000003' ").append("AND T1.SSSCTA = '0002' ")
			.append("AND T1.SSSSCTA = '') T3 ").append("GROUP BY T3.SCTA ").append("UNION  ").append("SELECT  ")
			.append("DECODE(SUBSTR(T5.CLVFTE,1,1),'1','ETIQUETADO', 'NO_ETIQUETADO') , ")
			.append("SUM(- T5.ESTIMADO) ESTIMADO, SUM(- T5.DEVENGADO) DEVENGADO, SUM(- T5.RECAUDADO) RECAUDADO FROM ")
			.append("(SELECT T4.FTE, F.CLVFTE, T4.ESTIMADO, T4.DEVENGADO, T4.RECAUDADO FROM ")
			.append("(SELECT SUBSTR(T3.SSCTA,13,3) FTE, SUM(T3.ESTIMADO) ESTIMADO ,SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO FROM ")
			.append("(SELECT T2.SCTA, T2.SSCTA, SUM(T2.ESTIMADO) ESTIMADO ,SUM(T2.DEVENGADO) DEVENGADO, SUM(T2.RECAUDADO) RECAUDADO FROM ")
			.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.NOMCTA ,T1.SALINI ESTIMADO,  ")
			.append(" DECODE(SUBSTR(T1.CUENTA,1,3),'825',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
			.append(" DECODE(SUBSTR(T1.CUENTA,1,3),'827',(" + cargos + " - " + abonosRest + "),0.0) RECAUDADO ")
			.append(" FROM CUENTA T1  ").append(" ").append(" WHERE T1.SSCTA<>'' AND T1.SSSCTA <> '' AND  ")
			.append(" SUBSTR(T1.CUENTA,1,3) IN ('821','825','827') ").append(" AND SUBSTR(T1.SSSCTA,1,2) = '91' ")
			.append(") T2 GROUP BY T2.SCTA, T2.SSCTA ) T3 GROUP BY SUBSTR(T3.SSCTA,13,3) ")
			.append(") T4 LEFT JOIN FUENTEF F ON F.LIGA=T4.FTE WHERE F.NIVFTE=4 ")
			.append(") T5 GROUP BY SUBSTR(T5.CLVFTE,1,1)) T5 ").append(" UNION ALL ")
			.append(" SELECT 1 PRINCIPAL, ").append("       2 GRUPO, ").append("       'B' LETRA, ")
			.append("       DECODE(SUBSTR(T5.CLVFTE,1,1),'1', '2', '1')SCTA,  ").append(" ")
			.append("DECODE(SUBSTR(T5.CLVFTE,1,1),'1','Gasto Etiquetado(sin Incluir Amortización de la Deuda)', 'Gasto No Etiqueta (Sin Incluir Amortización de la Deuda Pública)') CONCEPTO, ")
			.append("SUM(T5.ESTIMADO) ESTIMADO, SUM(T5.DEVENGADO) DEVENGADO, SUM(T5.RECAUDADO) RECAUDADO FROM ")
			.append("(SELECT T4.FTE, F.CLVFTE, T4.ESTIMADO, T4.DEVENGADO, T4.RECAUDADO FROM ")
			.append("(SELECT SUBSTR(T3.SSCTA,13,3) FTE, SUM(T3.ESTIMADO) ESTIMADO ,SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO FROM ")
			.append("(SELECT T2.SCTA, T2.SSCTA, SUM(T2.ESTIMADO) ESTIMADO ,SUM(T2.DEVENGADO) DEVENGADO, SUM(T2.RECAUDADO) RECAUDADO FROM ")
			.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.NOMCTA ,T1.SALINI ESTIMADO,  ")
			.append("DECODE(SUBSTR(T1.CUENTA,1,3),'825',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
			.append("DECODE(SUBSTR(T1.CUENTA,1,3),'827',(" + cargos + " - " + abonosRest + "),0.0) RECAUDADO ")
			.append("FROM CUENTA T1 WHERE T1.SSCTA<>'' AND T1.SSSCTA<>'' AND ")
			.append("SUBSTR(T1.CUENTA,1,3) IN ('821','825','827') AND SUBSTR(T1.SSSCTA,1,2)<>'91' ")
			.append(") T2 GROUP BY T2.SCTA, T2.SSCTA ) T3 GROUP BY SUBSTR(T3.SSCTA,13,3) ")
			.append(") T4 LEFT JOIN FUENTEF F ON F.LIGA=T4.FTE WHERE F.NIVFTE=4 ")
			.append(") T5 GROUP BY SUBSTR(T5.CLVFTE,1,1) ").append("UNION ALL ").append("SELECT 1 PRINCIPAL, ")
			.append("       3 GRUPO, ").append("       'C' LETRA, ").append("       '1' SCTA, ")
			.append("       'Remanente de Ingresos de LIbre Disposición aplicado en el Periodo' CONCEPTO, ")
			.append("       0.00 ESTIMADO, ").append("       0.00 DEVEGENADO, ").append("       0.00 RECAUDADO ")
			.append("     FROM SYSIBM.SYSDUMMY1 ").append("UNION ALL ").append("SELECT 1 PRINCIPAL, ")
			.append("       3 GRUPO, ").append("       'C' LETRA, ").append("       '2' SCTA, ")
			.append("       'Remanente de Transferencias Federales Etiquetadas Aplicados en el Periodo' CONCEPTO, ")
			.append("       0.00 ESTIMADO, ").append("       0.00 DEVEGENADO, ").append("       0.00 RECAUDADO ")
			.append("     FROM SYSIBM.SYSDUMMY1 ").append(" ").append("    ) TI ")
			.append("ORDER BY TI.PRINCIPAL, TI.GRUPO, TI.SCTA ASC");
		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String getQueryBalancePresupuestario1(Integer trimestre, boolean mensual) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargosR = new StringBuilder();
		StringBuilder abonosR = new StringBuilder();
		StringBuilder compro = new StringBuilder();
		StringBuilder ejxpa = new StringBuilder();
		StringBuilder ejpa = new StringBuilder();
		Integer month = mensual ? trimestre : getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			cargos.append("T1.CARGOS_").append(i);
			abonos.append("T1.ABONOS_").append(i);
			cargosR.append("T1.CARGOS_").append(i);
			abonosR.append("T1.ABONOS_").append(i);
			compro.append("P.COMPRO1_").append(i);
			ejxpa.append("P.EJXPA1_").append(i);
			ejpa.append("P.EJPA1_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" - ");
				cargosR.append(" - ");
				abonosR.append(" + ");
				compro.append(" + ");
				ejxpa.append("+");
				ejpa.append("+");
			}
		}

		StringBuilder sSql = new StringBuilder();
		
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("1 GRUPO, ");
		sSql.append("'A' LETRA, ");
		sSql.append("4000 SCTA, ");
		sSql.append("'Ingresos de Libre Disposición' CONCEPTO, ");
		sSql.append("SUM(T3.ESTIMADO) ESTIMADO, ");
		sSql.append("SUM(T3.RECAUDADO) DEVENGADO, ");
		sSql.append("SUM(T3.RECAUDADO) RECAUDADO ");
		sSql.append("FROM ");
		sSql.append("( ");
		sSql.append("SELECT ");
		sSql.append("T1.CUENTA, ");
		sSql.append("T1.SCTA, ");
		sSql.append("T1.SSCTA, ");
		sSql.append("T1.SSSCTA, ");
		sSql.append("T1.SSSSCTA, ");
		sSql.append("T1.NOMCTA , ");
		sSql.append("(T1.SALINI) ESTIMADO, ");
		sSql.append("DECODE(T1.CUENTA,'8110', ");
		sSql.append("("+cargos+" - ");
		sSql.append(abonos+"), ");
		sSql.append("0.0) AMPLIACIONES, ");
		sSql.append("DECODE(T1.CUENTA,'8140', ");
		sSql.append("("+cargos+" - ");
		sSql.append(abonos+"), ");
		sSql.append("0.0) DEVENGADO, ");
		sSql.append("DECODE(T1.CUENTA,'8150', ");
		sSql.append("("+abonosR+" - ");
		sSql.append(cargosR+"), ");
		sSql.append("0.0) RECAUDADO ");
		sSql.append("FROM CUENTA T1 ");
		sSql.append("LEFT JOIN INGRESO T2 ");
		sSql.append("ON T1.CUENTA = T2.CUENTA AND ");
		sSql.append("T1.SCTA = T2.SCTA AND ");
		sSql.append("T1.SSCTA = T2.SSCTA AND ");
		sSql.append("T1.SSSCTA = T2.SSSCTA AND ");
		sSql.append("T1.SSSSCTA = T2.SSSSCTA ");
		sSql.append("WHERE T1.CUENTA IN ('8110','8140','8150')  ");
		sSql.append("AND T1.SCTA = '' ) T3 ");
		sSql.append("GROUP BY SUBSTR(T3.SCTA,	1,	1) ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("1 GRUPO, ");
		sSql.append("'A' LETRA, ");
		sSql.append("'4001' SCTA, ");
		sSql.append("'Transferencias Federales Etiquetadas' CONCEPTO, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("1 GRUPO, ");
		sSql.append("'A' LETRA, ");
		sSql.append("'4002' SCTA, ");
		sSql.append("'Financiamiento Neto' CONCEPTO, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ALL ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("2 GRUPO, ");
		sSql.append("'B' LETRA, ");
		sSql.append("1 SCTA, ");
		sSql.append("'Gasto No Etiquetado (Sin Incluir Amortización de la Deuda Pública)' CONCEPTO, ");
		sSql.append("SUM(ESTIMADO) ESTIMADO, ");
		sSql.append("(SELECT ");
		sSql.append("SUM( ");
		sSql.append(compro+" + ");
		sSql.append(ejxpa+" + ");
		sSql.append(ejpa+" ) ");
		sSql.append("FROM PASO P ");
		sSql.append("INNER JOIN MUNINEP M ");
		sSql.append("ON M.IDSECTOR = P.IDSECTOR AND ");
		sSql.append("M.CAMPO7 = SUBSTR(P.PROGRAMA,1,4) ");
		sSql.append("WHERE P.IDSECTOR = 2 AND ");
		sSql.append("SUBSTR(P.PARTIDA,4,1) <> '0') DEVENGADO, ");
		sSql.append("SUM(RECAUDADO) RECAUDADO ");
		sSql.append("FROM( ");
		sSql.append("SELECT ");
		sSql.append("T1.CUENTA, ");
		sSql.append("T1.SCTA, ");
		sSql.append("T1.SSCTA, ");
		sSql.append("T1.SSSCTA, ");
		sSql.append("T1.NOMCTA , ");
		sSql.append("T1.SALINI ESTIMADO, ");
		sSql.append("0.0 DEVENGADO, ");
		sSql.append("DECODE(SUBSTR(T1.CUENTA,1,3),'827', ");
		sSql.append("("+cargos+" - ");
		sSql.append(abonos+"), ");
		sSql.append("0.0) RECAUDADO ");
		sSql.append("FROM CUENTA T1 ");
		sSql.append("WHERE T1.SSCTA<>'' AND ");
		sSql.append("T1.SSSCTA<>'' AND ");
		sSql.append("SUBSTR(T1.CUENTA,1,3) IN ('821','825','827') AND ");
		sSql.append("SUBSTR(T1.SSSCTA,1,2)<>'91') ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("2 GRUPO, ");
		sSql.append("'B' LETRA, ");
		sSql.append("2 SCTA, ");
		sSql.append("'Gasto Etiquetado(sin Incluir Amortización de la Deuda)' CONCEPTO, ");
		sSql.append("0.0 ESTIMADO, ");
		sSql.append("0.0 DEVENGADO, ");
		sSql.append("0.0 RECAUDADO ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ALL ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("3 GRUPO, ");
		sSql.append("'C' LETRA, ");
		sSql.append("'1' SCTA, ");
		sSql.append("'Remanentes de Ingresos de Libre Disposición Aplicados en el Período' CONCEPTO, ");
		sSql.append("0.00 ESTIMADO, ");
		sSql.append("0.00 DEVEGENADO, ");
		sSql.append("0.00 RECAUDADO ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("3 GRUPO, ");
		sSql.append("'C' LETRA, ");
		sSql.append("'2' SCTA, ");
		sSql.append("'Remanentes de Transferencias Federales Etiquetadas Aplicados en el Período'  ");
		sSql.append("CONCEPTO, ");
		sSql.append("0.00 ESTIMADO, ");
		sSql.append("0.00 DEVEGENADO, ");
		sSql.append("0.00 RECAUDADO ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("ORDER BY 1,2,3,4");
		
		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String getQueryBalancePresupuestario2(Integer trimestre) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargosRest = new StringBuilder();
		StringBuilder abonosRest = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("T1.ABONOS_").append(i);
			cargos.append("T1.CARGOS_").append(i);
			cargosRest.append("T1.CARGOS_").append(i);
			abonosRest.append("T1.ABONOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
				cargosRest.append(" - ");
				abonosRest.append(" - ");
			}
		}
		StringBuilder sSql = new StringBuilder();

		sSql.append("SELECT 2 PRINCIPAL, ").append("       4 GRUPO, ").append("       'E' LETRA, ")
				.append("       '' SCTA, ")
				.append("DECODE(SUBSTR(T5.CLVFTE,1,1),'1','Intereses, Comisiones y Gastos de la Deuda Etiquetado', 'Interes, Comisiones y Gastos de la Deuda con Gastos no Etiquetado') CONCEPTO , ")
				.append("SUM(T5.ESTIMADO) ESTIMADO, SUM(T5.DEVENGADO) DEVENGADO, SUM(T5.RECAUDADO) RECAUDADO FROM ")
				.append("(SELECT T4.FTE, F.CLVFTE, T4.ESTIMADO, T4.DEVENGADO, T4.RECAUDADO FROM ")
				.append("(SELECT SUBSTR(T3.SSCTA,13,3) FTE, SUM(T3.ESTIMADO) ESTIMADO ,SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO FROM ")
				.append("(SELECT T2.SCTA, T2.SSCTA, SUM(T2.ESTIMADO) ESTIMADO ,SUM(T2.DEVENGADO) DEVENGADO, SUM(T2.RECAUDADO) RECAUDADO FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.NOMCTA ,T1.SALINI ESTIMADO,  ")
				.append("DECODE(SUBSTR(T1.CUENTA,1,3),'825',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(SUBSTR(T1.CUENTA,1,3),'827',(" + cargos + " - " + abonosRest + "),0.0) RECAUDADO ")
				.append("FROM CUENTA T1 WHERE T1.SSCTA<>'' AND T1.SSSCTA <> '' AND  ")
				.append("SUBSTR(T1.CUENTA,1,3) IN ('821','825','827') ")
				.append("AND SUBSTR(T1.SSSCTA,1,2) IN ('92','93','94','95','96') ")
				.append(") T2 GROUP BY T2.SCTA, T2.SSCTA ) T3 GROUP BY SUBSTR(T3.SSCTA,13,3) ")
				.append(") T4 LEFT JOIN FUENTEF F ON F.LIGA=T4.FTE WHERE F.NIVFTE=4 ")
				.append(") T5 GROUP BY SUBSTR(T5.CLVFTE,1,1)");

		System.out.println(sSql);
		return sSql.toString();
	}
	
	public String getQueryBalancePresupuestario2() {
		
		StringBuilder sSql = new StringBuilder();

		sSql.append("SELECT ");
		sSql.append("2 PRINCIPAL, ");
		sSql.append("4 GRUPO, ");
		sSql.append("'E' LETRA, ");
		sSql.append("'' SCTA, ");
		sSql.append("'Intereses, Comisiones y Gastos de la Deuda con Gasto No Etiquetado' CONCEPTO, ");
		sSql.append("0.0 ESTIMADO, ");
		sSql.append("0.0 DEVENGADO, ");
		sSql.append("0.0 RECAUDADO ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("2 PRINCIPAL, ");
		sSql.append("4 GRUPO, ");
		sSql.append("'E' LETRA, ");
		sSql.append("'' SCTA, ");
		sSql.append("'Intereses, Comisiones y Gastos de la Deuda con Gasto Etiquetado' CONCEPTO, ");
		sSql.append("0.0 ESTIMADO, ");
		sSql.append("0.0 DEVENGADO, ");
		sSql.append("0.0 RECAUDADO ");
		sSql.append("FROM SYSIBM.SYSDUMMY1");

		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String getQueryBalancePresupuestario3(Integer trimestre) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargosRest = new StringBuilder();
		StringBuilder abonosRest = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("T1.ABONOS_").append(i);
			cargos.append("T1.CARGOS_").append(i);
			cargosRest.append("T1.CARGOS_").append(i);
			abonosRest.append("T1.ABONOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
				cargosRest.append(" - ");
				abonosRest.append(" - ");
			}
		}
		StringBuilder sSql = new StringBuilder();

		sSql.append("SELECT TI.PRINCIPAL, ").append("       TI.GRUPO, ").append("       TI.LETRA, ")
				.append("       TI.SCTA, ").append("       TI.CONCEPTO, ").append("       TI.ESTIMADO, ")
				.append("       TI.DEVENGADO, ").append("       TI.RECAUDADO ").append("     FROM( ").append(" ")
				.append("SELECT 1 PRINCIPAL, ").append("       1 GRUPO, ").append("       'F' LETRA, ")
				.append("       T3.SCTA, ")
				.append("       'Financiamiento con Fuente de Pago de Ingresos de Libre Disposición' CONCEPTO, ")
				.append("       SUM(T3.ESTIMADO) ESTIMADO,  ")
				.append("       SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO ").append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO, ")
				.append("DECODE(T1.CUENTA,'8140',(  " + abonos + " - " + cargosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(  " + abonos + " - " + cargosRest + "),0.0) RECAUDADO ").append(" ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND SUBSTR(T1.SCTA,7,4) = ('4399')  ")
				.append("AND T1.SSCTA = '000000000000003' ").append("AND T1.SSSCTA = '0002' ")
				.append("AND T1.SSSSCTA = '') T3 ").append("GROUP BY T3.SCTA ").append(" ").append("UNION ALL ")
				.append("SELECT 1 PRINCIPAL, ").append("       1 GRUPO, ").append("       'F' LETRA, ")
				.append("       '0000004444' SCTA, ")
				.append("       'Financiamiento con Fuente de Pago de Transferencias Federales Etiquetadas ' CONCEPTO, ")
				.append("      0.00 ESTIMADO,  ").append("      0.0 DEVENGADO,  ").append("      0.00 RECAUDADO ")
				.append("FROM SYSIBM.SYSDUMMY1 ").append(" ").append("UNION ALL ").append("SELECT 2 PRINCIPAL, ")
				.append("       2 GRUPO, ").append("       'G' LETRA, ").append("       '1' SCTA, ")
				.append("DECODE(SUBSTR(T5.CLVFTE,1,1),'1','Amortización de la Deuda Pública con Gasto No Etiquetado', 'Amortización de la Deuda Pública con Gasto Etiquetado')CONCEPTO , ")
				.append("SUM(T5.ESTIMADO) ESTIMADO, SUM(T5.DEVENGADO) DEVENGADO, SUM(T5.RECAUDADO) RECAUDADO FROM ")
				.append("(SELECT T4.FTE, F.CLVFTE, T4.ESTIMADO, T4.DEVENGADO, T4.RECAUDADO FROM ")
				.append("(SELECT SUBSTR(T3.SSCTA,13,3) FTE, SUM(T3.ESTIMADO) ESTIMADO ,SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO FROM ")
				.append("(SELECT T2.SCTA, T2.SSCTA, SUM(T2.ESTIMADO) ESTIMADO ,SUM(T2.DEVENGADO) DEVENGADO, SUM(T2.RECAUDADO) RECAUDADO FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.NOMCTA ,T1.SALINI ESTIMADO,  ")
				.append("DECODE(SUBSTR(T1.CUENTA,1,3),'825',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(SUBSTR(T1.CUENTA,1,3),'827',(" + cargos + " - " + abonosRest + "),0.0) RECAUDADO ")
				.append("FROM CUENTA T1 WHERE T1.SSCTA<>'' AND T1.SSSCTA <> '' AND  ")
				.append("SUBSTR(T1.CUENTA,1,3) IN ('821','825','827') ").append("AND SUBSTR(T1.SSSCTA,1,2) = '91' ")
				.append(") T2 GROUP BY T2.SCTA, T2.SSCTA ) T3 GROUP BY SUBSTR(T3.SSCTA,13,3) ")
				.append(") T4 LEFT JOIN FUENTEF F ON F.LIGA=T4.FTE WHERE F.NIVFTE=4 AND SUBSTR(F.CLVFTE,1,1) = '1' ")
				.append(") T5 GROUP BY SUBSTR(T5.CLVFTE,1,1) ").append("UNION ALL ").append("SELECT 2 PRINCIPAL, ")
				.append("       2 GRUPO, ").append("       'G' LETRA, ").append("       '2' SCTA, ")
				.append("       'Amortización de la Deuda Pública con Gasto Etiquetado' CONCEPTO , ")
				.append("       0.00 ESTIMADO,  ").append("       0.00 DEVENGADO,  ").append("       0.00 RECAUDADO  ")
				.append("      FROM SYSIBM.SYSDUMMY1 ").append(") TI ").append(" ")
				.append("ORDER BY TI.PRINCIPAL, TI.GRUPO, TI.SCTA ASC");

		System.out.println(sSql);
		return sSql.toString();
	}
	
	public String getQueryBalancePresupuestario3() {
		StringBuilder sSql = new StringBuilder();

		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("1 GRUPO, ");
		sSql.append("'F' LETRA, ");
		sSql.append("1 SCTA, ");
		sSql.append("'Financiamiento con Fuente de Pago de Ingresos de Libre Disposición' CONCEPTO, ");
		sSql.append("0.00 ESTIMADO, ");
		sSql.append("0.00 DEVENGADO, ");
		sSql.append("0.00 RECAUDADO ");
		sSql.append("FROM ");
		sSql.append("SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("1 GRUPO, ");
		sSql.append("'F' LETRA, ");
		sSql.append("2 SCTA, ");
		sSql.append("'Financiamiento con Fuente de Pago de Transferencias Federales Etiquetadas' CONCEPTO, ");
		sSql.append("0.00 ESTIMADO, ");
		sSql.append("0.0 DEVENGADO, ");
		sSql.append("0.00 RECAUDADO ");
		sSql.append("FROM ");
		sSql.append("SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ALL ");
		sSql.append("SELECT ");
		sSql.append("2 PRINCIPAL, ");
		sSql.append("2 GRUPO, ");
		sSql.append("'G' LETRA, ");
		sSql.append("1 SCTA, ");
		sSql.append("'Amortización de la Deuda Pública con Gasto No Etiquetado' CONCEPTO , ");
		sSql.append("0.00 ESTIMADO, ");
		sSql.append("0.00 DEVENGADO, ");
		sSql.append("0.00 RECAUDADO ");
		sSql.append("FROM ");
		sSql.append("SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("2 PRINCIPAL, ");
		sSql.append("2 GRUPO, ");
		sSql.append("'G' LETRA, ");
		sSql.append("2 SCTA, ");
		sSql.append("'Amortización de la Deuda Pública con Gasto Etiquetado' CONCEPTO , ");
		sSql.append("0.00 ESTIMADO, ");
		sSql.append("0.00 DEVENGADO, ");
		sSql.append("0.00 RECAUDADO ");
		sSql.append("FROM ");
		sSql.append("SYSIBM.SYSDUMMY1 ");
		sSql.append("ORDER BY 1,2,3,4");
		
		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String getQueryBalancePresupuestario4(Integer trimestre) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargosRest = new StringBuilder();
		StringBuilder abonosRest = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("T1.ABONOS_").append(i);
			cargos.append("T1.CARGOS_").append(i);
			cargosRest.append("T1.CARGOS_").append(i);
			abonosRest.append("T1.ABONOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
				cargosRest.append(" - ");
				abonosRest.append(" - ");
			}
		}
		StringBuilder sSql = new StringBuilder();

		sSql.append("SELECT TI.PRINCIPAL, ").append("       TI.GRUPO, ").append("       TI.SCTA, ")
				.append("       TI.LETRA, ").append("       TI.CONCEPTO, ").append("       TI.ESTIMADO, ")
				.append("       TI.DEVENGADO, ").append("       TI.RECAUDADO ").append("     FROM( ")
				.append("SELECT 1 PRINCIPAL, ").append("       0 GRUPO, ").append("       'A1' LETRA, ")
				.append("       400||SUBSTR(T3.SCTA,1,1) SCTA, ")
				.append("       'Ingreso de Libre Disposición' CONCEPTO , ")
				.append("       SUM(T3.ESTIMADO) ESTIMADO,   ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO ").append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND  ")
				.append("( ( SUBSTR(T1.SCTA,7,2)='41' AND T1.SSSSCTA = '' ").append("    AND T1.SSSCTA = '' ")
				.append("    AND T1.SCTA <> '' ").append("    AND SUBSTR(T1.SCTA,9,1) <> '0' ")
				.append("    AND SUBSTR(T1.SCTA,10,1) = '0' ").append("   )  OR ")
				.append("   ( (SUBSTR(T1.SCTA,7,4) ='4211'  ").append("     AND T1.SSCTA = '' ").append("     ) ")
				.append("     OR (SUBSTR(T1.SCTA,7,4)='4214' AND T1.SSCTA='000000000000003' AND T1.SSSCTA='0001' AND T1.SSSSCTA='001') ")
				.append("     OR (SUBSTR(T1.SCTA,7,4)='4214' AND T1.SSCTA='000000000000001' AND T1.SSSCTA='0001' AND T1.SSSSCTA='001') ")
				.append("     OR (SUBSTR(T1.SCTA,7,4)='4214' AND T1.SSCTA='000000000000001' AND T1.SSSCTA='0003' AND T1.SSSSCTA='') ")
				.append("     OR (SUBSTR(T1.SCTA,7,4)='4214' AND T1.SSCTA='000000000000001' AND T1.SSSCTA='0002' AND T1.SSSSCTA='') ")
				.append("   )  ").append("    ")
				.append("OR (SUBSTR(T1.SCTA,7,4)='4399' AND T1.SSCTA='000000000000003' AND T1.SSSCTA='0001' AND T1.SSSSCTA='001') ")
				.append(") ").append(") T3 ").append("GROUP BY SUBSTR(T3.SCTA,1,1) ").append(" ").append("UNION ALL ")
				.append("SELECT 1 PRINCIPAL, ").append("       1 GRUPO, ").append("       'F1' LETRA,  ")
				.append("       T3.SCTA, ")
				.append("       'Financiamiento con Fuente de Pago de Ingresos de Libre Disposición' CONCEPTO, ")
				.append("SUM(T3.ESTIMADO) ESTIMADO,  ").append("   ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO ").append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO, ")
				.append("DECODE(T1.CUENTA,'8140',(  " + abonos + " - " + cargosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(  " + abonos + " - " + cargosRest + "),0.0) RECAUDADO ").append(" ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND SUBSTR(T1.SCTA,7,4) = ('4399')  ")
				.append("AND T1.SSCTA = '000000000000003' ").append("AND T1.SSSCTA = '0002' ")
				.append("AND T1.SSSSCTA = '') T3 ").append("GROUP BY T3.SCTA ").append("UNION ALL ")
				.append("SELECT 1 PRINCIPAL, ").append("       1 GRUPO, ").append("       'G1' LETRA, ")
				.append("       '0000004499' SCTA, ")
				.append("DECODE(SUBSTR(T5.CLVFTE,1,1),'1','Amortización de la Deuda Pública con Gasto No Etiquetado', 'Amortización de la Deuda Pública con Gasto Etiquetado')CONCEPTO , ")
				.append("SUM(T5.ESTIMADO) ESTIMADO, SUM(T5.DEVENGADO) DEVENGADO, SUM(T5.RECAUDADO) RECAUDADO FROM ")
				.append("(SELECT T4.FTE, F.CLVFTE, T4.ESTIMADO, T4.DEVENGADO, T4.RECAUDADO FROM ")
				.append("(SELECT SUBSTR(T3.SSCTA,13,3) FTE, SUM(T3.ESTIMADO) ESTIMADO ,SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO FROM ")
				.append("(SELECT T2.SCTA, T2.SSCTA, SUM(T2.ESTIMADO) ESTIMADO ,SUM(T2.DEVENGADO) DEVENGADO, SUM(T2.RECAUDADO) RECAUDADO FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.NOMCTA ,T1.SALINI ESTIMADO,  ")
				.append("DECODE(SUBSTR(T1.CUENTA,1,3),'825',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(SUBSTR(T1.CUENTA,1,3),'827',(" + cargos + " - " + abonosRest + "),0.0) RECAUDADO ")
				.append("FROM CUENTA T1 WHERE T1.SSCTA<>'' AND T1.SSSCTA <> '' AND  ")
				.append("SUBSTR(T1.CUENTA,1,3) IN ('821','825','827') ").append("AND SUBSTR(T1.SSSCTA,1,2) = '91' ")
				.append(") T2 GROUP BY T2.SCTA, T2.SSCTA ) T3 GROUP BY SUBSTR(T3.SSCTA,13,3) ")
				.append(") T4 LEFT JOIN FUENTEF F ON F.LIGA=T4.FTE WHERE F.NIVFTE=4 AND SUBSTR(F.CLVFTE,1,1) = '1' ")
				.append(") T5 GROUP BY SUBSTR(T5.CLVFTE,1,1) ").append("UNION ALL ").append("SELECT 2 PRINCIPAL, ")
				.append("       2 GRUPO, ").append("       'B1' LETRA, ").append("       '1' SCTA, ")
				.append("       'Gasto No Etiquetado(sin Incluir Amortización de la Deuda Pública)' CONCEPTO, ")
				.append("       TI.ESTIMADO, ").append("       TI.DEVENGADO, ").append("       TI.RECAUDADO ")
				.append("     FROM( ").append("SELECT  ")
				.append("DECODE(SUBSTR(T5.CLVFTE,1,1),'1','ETIQUETADO', 'NO_ETIQUETADO') CONCEPTO , ")
				.append("SUM(T5.ESTIMADO) ESTIMADO, SUM(T5.DEVENGADO) DEVENGADO, SUM(T5.RECAUDADO) RECAUDADO FROM ")
				.append("(SELECT T4.FTE, F.CLVFTE, T4.ESTIMADO, T4.DEVENGADO, T4.RECAUDADO FROM ")
				.append("(SELECT SUBSTR(T3.SSCTA,13,3) FTE, SUM(T3.ESTIMADO) ESTIMADO ,SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO FROM ")
				.append("(SELECT T2.SCTA, T2.SSCTA, SUM(T2.ESTIMADO) ESTIMADO ,SUM(T2.DEVENGADO) DEVENGADO, SUM(T2.RECAUDADO) RECAUDADO FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.NOMCTA ,T1.SALINI ESTIMADO,  ")
				.append("DECODE(SUBSTR(T1.CUENTA,1,3),'825',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(SUBSTR(T1.CUENTA,1,3),'827',(" + cargos + " - " + abonosRest + "),0.0) RECAUDADO ")
				.append("FROM CUENTA T1 WHERE T1.SSCTA<>'' AND T1.SSSCTA<>'' AND ")
				.append("SUBSTR(T1.CUENTA,1,3) IN ('821','825','827') AND SUBSTR(T1.SSSCTA,1,2)<>'91' ")
				.append(") T2 GROUP BY T2.SCTA, T2.SSCTA ) T3 GROUP BY SUBSTR(T3.SSCTA,13,3) ")
				.append(") T4 LEFT JOIN FUENTEF F ON F.LIGA=T4.FTE WHERE F.NIVFTE=4 ")
				.append(") T5 GROUP BY SUBSTR(T5.CLVFTE,1,1) ) TI ").append("WHERE TI.CONCEPTO = 'NO_ETIQUETADO' ")
				.append("UNION ALL ").append("SELECT 3 PRINCIPAL, ").append("       3 GRUPO, ")
				.append("       'C1' LETRA, ").append("       '2' SCTA, ")
				.append("       'Remanente de Ingresos de Libre Disposición aplicados en el periodo' CONCEPTO, ")
				.append("       0.00 ESTIMADO, ").append("       0.00 DEVENGADO, ").append("       0.00 RECAUDADO ")
				.append("     FROM SYSIBM.SYSDUMMY1 ").append(" ").append("     ) TI ")
				.append("ORDER BY TI.PRINCIPAL, TI.GRUPO, TI.SCTA ASC");
		System.out.println(sSql);
		return sSql.toString();
	}

	public String getQueryBalancePresupuestario4(Integer trimestre, boolean mensual) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargosR = new StringBuilder();
		StringBuilder abonosR = new StringBuilder();
		StringBuilder compro = new StringBuilder();
		StringBuilder ejxpa = new StringBuilder();
		StringBuilder ejpa = new StringBuilder();
		Integer month = mensual ? trimestre : getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			cargos.append("T1.CARGOS_").append(i);
			abonos.append("T1.ABONOS_").append(i);
			cargosR.append("T1.CARGOS_").append(i);
			abonosR.append("T1.ABONOS_").append(i);
			compro.append("P.COMPRO1_").append(i);
			ejxpa.append("P.EJXPA1_").append(i);
			ejpa.append("P.EJPA1_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" - ");
				cargosR.append(" - ");
				abonosR.append(" + ");
				compro.append(" + ");
				ejxpa.append("+");
				ejpa.append("+");
			}
		}

		StringBuilder sSql = new StringBuilder();

		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("0 GRUPO, ");
		sSql.append("'A1' LETRA, ");
		sSql.append("1 SCTA, ");
		sSql.append("'Ingresos de Libre Disposición' CONCEPTO, ");
		sSql.append("SUM(T3.ESTIMADO) ESTIMADO, ");
		sSql.append("SUM(T3.RECAUDADO) DEVENGADO, ");
		sSql.append("SUM(T3.RECAUDADO) RECAUDADO ");
		sSql.append("FROM ");
		sSql.append("( ");
		sSql.append("SELECT ");
		sSql.append("T1.CUENTA, ");
		sSql.append("T1.SCTA, ");
		sSql.append("T1.SSCTA, ");
		sSql.append("T1.SSSCTA, ");
		sSql.append("T1.SSSSCTA, ");
		sSql.append("T1.NOMCTA , ");
		sSql.append("(T1.SALINI) ESTIMADO, ");
		sSql.append("DECODE(T1.CUENTA, ");
		sSql.append("'8110', ");
		sSql.append("("+cargos+" - ");
		sSql.append(abonos+"), ");
		sSql.append("0.0) AMPLIACIONES, ");
		sSql.append("DECODE(T1.CUENTA, ");
		sSql.append("'8140', ");
		sSql.append("("+cargos+" - ");
		sSql.append(abonos+"), ");
		sSql.append("0.0) DEVENGADO, ");
		sSql.append("DECODE(T1.CUENTA, ");
		sSql.append("'8150', ");
		sSql.append("("+abonosR+" - ");
		sSql.append(cargosR+"), ");
		sSql.append("0.0) RECAUDADO ");
		sSql.append("FROM ");
		sSql.append("CUENTA T1 ");
		sSql.append("	LEFT JOIN ");
		sSql.append("	INGRESO T2 ");
		sSql.append("	ON ");
		sSql.append("	T1.CUENTA = T2.CUENTA AND ");
		sSql.append("T1.SCTA = T2.SCTA AND ");
		sSql.append("T1.SSCTA = T2.SSCTA AND ");
		sSql.append("T1.SSSCTA = T2.SSSCTA AND ");
		sSql.append("T1.SSSSCTA = T2.SSSSCTA ");
		sSql.append("WHERE ");
		sSql.append("T1.CUENTA IN ('8110', '8140', '8150') AND ");
		sSql.append("T1.SCTA = '' ) T3 ");
		sSql.append("GROUP BY ");
		sSql.append("SUBSTR(T3.SCTA, 1, 1) ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("0 GRUPO, ");
		sSql.append("'A3.1' LETRA, ");
		sSql.append("'2' SCTA, ");
		sSql.append("'Financiamiento Neto con Fuente de Pago de Ingresos de Libre Disposición (A3.1 = F1 – G1)' CONCEPTO, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO ");
		sSql.append("FROM ");
		sSql.append("SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("1 GRUPO, ");
		sSql.append("'F1' LETRA, ");
		sSql.append("'1' SCTA, ");
		sSql.append("'Financiamiento con Fuente de Pago de Ingresos de Libre Disposición' CONCEPTO, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("1 GRUPO, ");
		sSql.append("'G1' LETRA, ");
		sSql.append("'2' SCTA, ");
		sSql.append("'Amortización de la Deuda Pública con Gasto No Etiquetado' CONCEPTO, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("2 GRUPO, ");
		sSql.append("'B1' LETRA, ");
		sSql.append("1 SCTA, ");
		sSql.append("'Gasto No Etiquetado (Sin Incluir Amortización de la Deuda Pública)' CONCEPTO, ");
		sSql.append("SUM(ESTIMADO) ESTIMADO, ");
		sSql.append("( ");
		sSql.append("SELECT ");
		sSql.append("SUM( ");
		sSql.append(compro+" + ");
		sSql.append(ejxpa+" + ");
		sSql.append(ejpa+" ) ");
		sSql.append("FROM ");
		sSql.append("PASO P ");
		sSql.append("INNER JOIN ");
		sSql.append("MUNINEP M ");
		sSql.append("ON ");
		sSql.append("M.IDSECTOR = P.IDSECTOR AND ");
		sSql.append("M.CAMPO7 = SUBSTR(P.PROGRAMA, ");
		sSql.append("1, ");
		sSql.append("4) ");
		sSql.append("WHERE ");
		sSql.append("P.IDSECTOR = 2 AND ");
		sSql.append("SUBSTR(P.PARTIDA, 4, 1) <> '0') DEVENGADO, ");
		sSql.append("SUM(RECAUDADO) RECAUDADO ");
		sSql.append("FROM ");
		sSql.append("( ");
		sSql.append("SELECT ");
		sSql.append("T1.CUENTA, ");
		sSql.append("T1.SCTA, ");
		sSql.append("T1.SSCTA, ");
		sSql.append("T1.SSSCTA, ");
		sSql.append("T1.NOMCTA , ");
		sSql.append("T1.SALINI ESTIMADO, ");
		sSql.append("0.0 DEVENGADO, ");
		sSql.append("DECODE(SUBSTR(T1.CUENTA, ");
		sSql.append("1, ");
		sSql.append("3), ");
		sSql.append("'827', ");
		sSql.append("("+cargos+" - ");
		sSql.append(abonos+"), ");
		sSql.append("0.0) RECAUDADO ");
		sSql.append("FROM ");
		sSql.append("CUENTA T1 ");
		sSql.append("WHERE ");
		sSql.append("T1.SSCTA<>'' AND ");
		sSql.append("T1.SSSCTA<>'' AND ");
		sSql.append("SUBSTR(T1.CUENTA, ");
		sSql.append("1, ");
		sSql.append("3) IN ('821', ");
		sSql.append("'825', ");
		sSql.append("'827') AND ");
		sSql.append("SUBSTR(T1.SSSCTA, ");
		sSql.append("1, ");
		sSql.append("2)<>'91') ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("3 GRUPO, ");
		sSql.append("'C1' LETRA, ");
		sSql.append("'1' SCTA, ");
		sSql.append("'Remanentes de Ingresos de Libre Disposición Aplicados en el Período' CONCEPTO, ");
		sSql.append("0.00 ESTIMADO, ");
		sSql.append("0.00 DEVEGENADO, ");
		sSql.append("0.00 RECAUDADO ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("ORDER BY 1,2,3,4");

		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String getQueryBalancePresupuestario5(Integer trimestre) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargosRest = new StringBuilder();
		StringBuilder abonosRest = new StringBuilder();
		Integer month = getMonth(trimestre);

		for (int i = 1; i <= month; i++) {
			abonos.append("T1.ABONOS_").append(i);
			cargos.append("T1.CARGOS_").append(i);
			cargosRest.append("T1.CARGOS_").append(i);
			abonosRest.append("T1.ABONOS_").append(i);
			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");
				cargosRest.append(" - ");
				abonosRest.append(" - ");
			}
		}
		StringBuilder sSql = new StringBuilder();

		sSql.append("SELECT TI.PRINCIPAL, ").append("       TI.GRUPO, ").append("       TI.LETRA, ")
				.append("       TI.SCTA, ").append("       TI.CONCEPTO, ").append("       TI.ESTIMADO, ")
				.append("       TI.DEVENGADO, ").append("       TI.RECAUDADO ").append("     FROM( ")
				.append("SELECT 1 PRINCIPAL, ").append("       0 GRUPO, ").append("       'A2' LETRA, ")
				.append("       SUBSTR(T3.SCTA,1,1) SCTA, ")
				.append("       'Transferencias Federales Etiquetadas' CONCEPTO, ")
				.append("        SUM(T3.ESTIMADO) ESTIMADO,   ")
				.append("SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO ").append("FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA, T1.NOMCTA , (T2.AUTOI_1 + T2.AUTOI_2 + T2.AUTOI_3) ESTIMADO,  ")
				.append("DECODE(T1.CUENTA,'8110',(" + cargos + " - " + abonosRest + "),0.0) AMPLIACIONES, ")
				.append("DECODE(T1.CUENTA,'8140',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(T1.CUENTA,'8150',(" + abonos + " - " + cargosRest + "),0.0) RECAUDADO ")
				.append("FROM CUENTA T1  ").append("LEFT JOIN INGRESO T2 ").append("ON T1.CUENTA = T2.CUENTA  ")
				.append("AND T1.SCTA = T2.SCTA ").append("AND T1.SSCTA = T2.SSCTA ")
				.append("AND T1.SSSCTA = T2.SSSCTA ").append("AND T1.SSSSCTA = T2.SSSSCTA ").append("WHERE  ")
				.append("T1.CUENTA IN ('8110','8140','8150') ").append("AND  ")
				.append("( ( SUBSTR(T1.SCTA,7,4) = '4212' AND T1.SSCTA = '' )   ").append("   OR ")
				.append("   ( SUBSTR(T1.SCTA,7,4) = '4213'  AND T1.SSCTA = '000000000000001' AND T1.SSSCTA = '0001' AND T1.SSSSCTA = '')  ")
				.append("   OR  ").append("(SUBSTR(T1.SCTA,7,4) IN ('4213','4223')  ").append("AND T1.SSCTA <> '' ")
				.append("AND T1.SSSCTA <> '' ").append("AND T1.SSSSCTA <> '' ")
				.append("AND T1.SCTA||T1.SSCTA <> '0000004213000000000000001') ").append(") ").append(") T3 ")
				.append("GROUP BY SUBSTR(T3.SCTA,1,1) ").append("UNION ALL ").append("SELECT 1 PRINCIPAL, ")
				.append("       1 GRUPO, ").append("       'F2' LETRA, ").append("       '1' SCTA, ")
				.append("       'Financiamiento con Fuente de Pago de Transferencias Federales Etiquetadas' CONCEPTO, ")
				.append("       0.00 ESTIMADO, ").append("       0.00 DEVENGADO, ").append("       0.00 RECAUDADO ")
				.append("     FROM SYSIBM.SYSDUMMY1 ").append("UNION ALL ").append("SELECT 1 PRINCIPAL, ")
				.append("       1 GRUPO, ").append("       'G2' LETRA, ").append("       '2' SCTA, ")
				.append("       'Amortización de la Deuda Pública con Gasto Etiquetado' CONCEPTO, ")
				.append("       0.00 ESTIMADO, ").append("       0.00 DEVENGADO, ").append("       0.00 RECAUDADO ")
				.append("     FROM SYSIBM.SYSDUMMY1 ").append("UNION ALL ").append("SELECT TI.PRINCIPAL, ")
				.append("       TI.GRUPO, ").append("        TI.LETRA, ").append("       TI.SCTA, ").append("       ")
				.append("       'Gasto Etiquetado(Sin incluir Amortización de la Deuda Pública)' CONCEPTO, ")
				.append("       TI.ESTIMADO, ").append("       TI.DEVENGADO, ").append("       TI.RECAUDADO ")
				.append("      FROM ( ").append("SELECT 1 PRINCIPAL, ").append("       2 GRUPO, ")
				.append("       'B2' LETRA, ").append("       '1' SCTA, ")
				.append("DECODE(SUBSTR(T5.CLVFTE,1,1),'1','ETIQUETADO', 'NO_ETIQUETADO') ET , ")
				.append("SUM(T5.ESTIMADO) ESTIMADO, SUM(T5.DEVENGADO) DEVENGADO, SUM(T5.RECAUDADO) RECAUDADO FROM ")
				.append("(SELECT T4.FTE, F.CLVFTE, T4.ESTIMADO, T4.DEVENGADO, T4.RECAUDADO FROM ")
				.append("(SELECT SUBSTR(T3.SSCTA,13,3) FTE, SUM(T3.ESTIMADO) ESTIMADO ,SUM(T3.DEVENGADO) DEVENGADO, SUM(T3.RECAUDADO) RECAUDADO FROM ")
				.append("(SELECT T2.SCTA, T2.SSCTA, SUM(T2.ESTIMADO) ESTIMADO ,SUM(T2.DEVENGADO) DEVENGADO, SUM(T2.RECAUDADO) RECAUDADO FROM ")
				.append("(SELECT T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.NOMCTA ,T1.SALINI ESTIMADO,  ")
				.append("DECODE(SUBSTR(T1.CUENTA,1,3),'825',(" + cargos + " - " + abonosRest + "),0.0) DEVENGADO, ")
				.append("DECODE(SUBSTR(T1.CUENTA,1,3),'827',(" + cargos + " - " + abonosRest + "),0.0) RECAUDADO ")
				.append("FROM CUENTA T1 WHERE T1.SSCTA<>'' AND T1.SSSCTA<>'' AND ")
				.append("SUBSTR(T1.CUENTA,1,3) IN ('821','825','827') AND SUBSTR(T1.SSSCTA,1,2)<>'91' ")
				.append(") T2 GROUP BY T2.SCTA, T2.SSCTA ) T3 GROUP BY SUBSTR(T3.SSCTA,13,3) ")
				.append(") T4 LEFT JOIN FUENTEF F ON F.LIGA=T4.FTE WHERE F.NIVFTE=4 ")
				.append(") T5 GROUP BY SUBSTR(T5.CLVFTE,1,1) ").append(")TI ").append("WHERE TI.ET = 'ETIQUETADO' ")
				.append("UNION ALL ").append("SELECT 1 PRINCIPAL, ").append("       3 GRUPO, ")
				.append("       'C2' LETRA, ").append("       '4' SCTA, ")
				.append("       'Remanentes de Transferencias Federales Etiquetadas aplicados en el Periodo' CONCEPTO, ")
				.append("       0.00 ESTIAMDO, ").append("       0.00 DEVENGADO, ").append("       0.00 RECAUDADO ")
				.append("     FROM SYSIBM.SYSDUMMY1 ").append("      ) TI  ")
				.append(" ORDER BY TI.PRINCIPAL, TI.GRUPO, TI.SCTA ASC");
		System.out.println(sSql);
		return sSql.toString();
	}
	
	public String getQueryBalancePresupuestario5() {
		StringBuilder sSql = new StringBuilder();

		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("0 GRUPO, ");
		sSql.append("'A2' LETRA, ");
		sSql.append("'1' SCTA, ");
		sSql.append("'Transferencias Federales Etiquetadas' CONCEPTO, ");
		sSql.append("0.00 ESTIMADO, ");
		sSql.append("0.00 DEVENGADO, ");
		sSql.append("0.00 RECAUDADO ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ALL ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("0 GRUPO, ");
		sSql.append("'A3.2' LETRA, ");
		sSql.append("'2' SCTA, ");
		sSql.append("'Financiamiento Neto con Fuente de Pago de Transferencias Federales Etiquetadas (A3.2 = F2-G2)' CONCEPTO, ");
		sSql.append("0.00 ESTIMADO, ");
		sSql.append("0.00 DEVENGADO, ");
		sSql.append("0.00 RECAUDADO ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ALL ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("1 GRUPO, ");
		sSql.append("'F2' LETRA, ");
		sSql.append("'1' SCTA, ");
		sSql.append("'Financiamiento con Fuente de Pago de Transferencias Federales Etiquetadas' CONCEPTO, ");
		sSql.append("0.00 ESTIMADO, ");
		sSql.append("0.00 DEVENGADO, ");
		sSql.append("0.00 RECAUDADO ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ALL ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("1 GRUPO, ");
		sSql.append("'G2' LETRA, ");
		sSql.append("'2' SCTA, ");
		sSql.append("'Amortización de la Deuda Pública con Gasto Etiquetado' CONCEPTO, ");
		sSql.append("0.00 ESTIMADO, ");
		sSql.append("0.00 DEVENGADO, ");
		sSql.append("0.00 RECAUDADO ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ALL ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("2 GRUPO, ");
		sSql.append("'B2' LETRA, ");
		sSql.append("'1' SCTA, ");
		sSql.append("'Gasto Etiquetado(Sin incluir Amortización de la Deuda Pública)' CONCEPTO, ");
		sSql.append("0.00 ESTIMADO, ");
		sSql.append("0.00 DEVENGADO, ");
		sSql.append("0.00 RECAUDADO ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ALL ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("3 GRUPO, ");
		sSql.append("'C2' LETRA, ");
		sSql.append("'1' SCTA, ");
		sSql.append("'Remanentes de Transferencias Federales Etiquetadas aplicados en el Periodo' CONCEPTO, ");
		sSql.append("0.00 ESTIAMDO, ");
		sSql.append("0.00 DEVENGADO, ");
		sSql.append("0.00 RECAUDADO ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ALL ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("4 GRUPO, ");
		sSql.append("'VII' LETRA, ");
		sSql.append("'1' SCTA, ");
		sSql.append("'Balance Presupuestario de Recursos Etiquetados (VII = A2 + A3.2 – B2 + C2)' CONCEPTO, ");
		sSql.append("0.00 ESTIAMDO, ");
		sSql.append("0.00 DEVENGADO, ");
		sSql.append("0.00 RECAUDADO ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ALL ");
		sSql.append("SELECT ");
		sSql.append("1 PRINCIPAL, ");
		sSql.append("4 GRUPO, ");
		sSql.append("'VIII' LETRA, ");
		sSql.append("'2' SCTA, ");
		sSql.append("'Balance Presupuestario de Recursos Etiquetados sin Financiamiento Neto (VIII = VII – A3.2)' CONCEPTO, ");
		sSql.append("0.00 ESTIAMDO, ");
		sSql.append("0.00 DEVENGADO, ");
		sSql.append("0.00 RECAUDADO ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("ORDER BY 1,2,3,4");
		
		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String getQueryEndeudamientoNeto(Integer trimestre) {
		StringBuilder toeje = new StringBuilder();
		Integer month = getMonth(trimestre);
		for (int i = 1; i <= month; i++) {
			toeje.append(" TOEJE1_" + i);
			if (i < month)
				toeje.append(" + ");
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT T1.CONCEPTO,  ").append("T1.CONTRATACION, ").append("T1.AMORTIZACION, ")
				.append("T1.CONTRATACION - T1.AMORTIZACION ENDEUDAMIENTO ").append("FROM ( ")
				.append("SELECT 'Amortizacion de los Certificados Bursatiles Fiduciarios \"A\"' CONCEPTO, 0.00 CONTRATACION, "
						+ toeje + " AMORTIZACION FROM PASO WHERE PARTIDA='9111' ")
				.append("UNION ALL	 ").append("SELECT 'Actualizacion de la Deuda' CONCEPTO, " + toeje
						+ " CONTRATACION, 0.00 AMORTIZACION  FROM PASO WHERE PARTIDA='9112' ")
				.append(") T1");
		System.out.println(sSql);
		return sSql.toString();
	}

	@Override
	public String getQueryInteresDeuda(Integer trimestre) {
		StringBuilder ejxpa = new StringBuilder();
		StringBuilder toeje = new StringBuilder();
		Integer month = getMonth(trimestre);
		for (int i = 1; i <= month; i++) {
			ejxpa.append(" EJXPA1_" + i);
			toeje.append(" TOEJE1_" + i);
			if (i < month) {
				ejxpa.append(" + ");
				toeje.append(" + ");
			}
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT 'Certificados Bursatiles Fiduciarios' CONCEPTO, (" + ejxpa
				+ ") DEVENGADO , (EJPA1_1 + EJPA1_2 +EJPA1_3 +EJPA1_4 +EJPA1_5 +EJPA1_6 +EJPA1_7 +EJPA1_8 +EJPA1_9) PAGADO  FROM PASO WHERE PARTIDA='9211'");
		return sSql.toString();
	}

	@Override
	public String getQueryEstadoFlujoEfectivoLDF(char tipo, Integer trimestre) {
		String sSql = getFlujosEfectivo(tipo, trimestre);
		System.out.println(sSql.toString());
		return sSql.toString();
	}

	@Override
	public TotalFlujoEfectivo getQueryTotalFlujoEfectivoLDF(char tipo, Integer trimestre) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargosAnt = new StringBuilder();
		StringBuilder abonosAnt = new StringBuilder();
		Integer month = tipo == 't' ? getMonth(trimestre) : trimestre;

		Map<String, String> map = getMapCounts(month);
		cargos.append(map.get("cargos"));
		abonos.append(map.get("abonos"));

		map = getMapCounts(month - 1);

		cargosAnt.append(map.get("cargos"));
		abonosAnt.append(map.get("abonos"));

		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT T1.SALINI + T1.CARGOS - T1.ABONOS SALDO_ACT, ")
				.append("        T1.SALINI + CARGOS_ANT - ABONOS_ANT SALDO_ANT ").append("      FROM ( ")
				.append(" SELECT ").append(" 	CU.SALINI, ").append(" 	" + cargosAnt + " CARGOS_ANT, ")
				.append(" 	" + abonosAnt + " ABONOS_ANT, ").append(" 	" + cargos + " CARGOS, ")
				.append(" 	" + abonos + " ABONOS ").append(" FROM CUENTA CU ").append(" WHERE CU.CUENTA = '1110' ")
				.append(" ) T1");
		TotalFlujoEfectivo total = this.jdbcTemplate.queryForObject(sSql.toString(),
				new RowMapper<TotalFlujoEfectivo>() {

					@Override
					public TotalFlujoEfectivo mapRow(ResultSet rs, int rowNum) throws SQLException {
						TotalFlujoEfectivo totalFlujo = new TotalFlujoEfectivo();
						totalFlujo.setSubTotalAct1(rs.getBigDecimal("SALDO_ACT"));
						totalFlujo.setSubTotalAnt1(rs.getBigDecimal("SALDO_ANT"));
						return totalFlujo;
					}

				});
		return total;
	}

	@Override
	public String getQueryEstadoAnaliticoIngresoInfoemRI(Integer periodo, Integer sector, boolean mensual) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();

		Integer month = mensual ? periodo : getMonth(periodo);

		for (int i = 1; i <= month; i++) {
			abonos.append("C.ABONOS_").append(i);
			cargos.append("C.CARGOS_").append(i);

			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");

			}
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT ");
		sSql.append("1 ORDEN, ");
		sSql.append("'Impuestos' NOMBRE, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 AMPLIACIONES, ");
		sSql.append("0 MODIFICADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO, ");
		sSql.append("0 DIFERENCIA ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("2 ORDEN, ");
		sSql.append("'Cuotas y Aportaciones de Seguridad Social' NOMBRE, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 AMPLIACIONES, ");
		sSql.append("0 MODIFICADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO, ");
		sSql.append("0 DIFERENCIA ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("3 ORDEN, ");
		sSql.append("'Contribuciones de Mejoras' NOMBRE, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 AMPLIACIONES, ");
		sSql.append("0 MODIFICADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO, ");
		sSql.append("0 DIFERENCIA ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("4 ORDEN, ");
		sSql.append("'Derechos' NOMBRE, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 AMPLIACIONES, ");
		sSql.append("0 MODIFICADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO, ");
		sSql.append("0 DIFERENCIA ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("6 ORDEN, ");
		sSql.append("'Aprovechamientos' NOMBRE, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 AMPLIACIONES, ");
		sSql.append("0 MODIFICADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO, ");
		sSql.append("0 DIFERENCIA ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("8 ORDEN, ");
		sSql.append("'Participaciones, Aportaciones, Convenios, Incentivos Derivados de la Colaboración Fiscal y Fondos Distintos de Aportaciones' NOMBRE, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 AMPLIACIONES, ");
		sSql.append("0 MODIFICADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO, ");
		sSql.append("0 DIFERENCIA ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT  ");
		sSql.append("T2.ORDEN, ");
		sSql.append("T2.NOMBRE, ");
		sSql.append("T2.ESTIMADO ESTIMADO, ");
		sSql.append("T2.TOT8110 AMPLIACIONES, ");
		sSql.append("(T2.ESTIMADO+T2.TOT8110) MODIFICADO, ");
		sSql.append("T2.DEVENGADO DEVENGADO, ");
		sSql.append("T2.RECAUDADO RECAUDADO, ");
		sSql.append("(T2.RECAUDADO-T2.ESTIMADO)DIFERENCIA ");
		sSql.append("	FROM ");
		sSql.append("		( ");
		sSql.append("SELECT ");
		sSql.append("T1.ORDEN, ");
		sSql.append("T1.NOMBRE, ");
		sSql.append("T1.ESTIMADO, ");
		sSql.append("CASE WHEN T1.SCTA = '000000000004399' AND T1.SSCTA = '' THEN 0.00 ELSE ");
		sSql.append("CASE WHEN (T1.ABONOS8110-T1.CARGOS8110) < 0 THEN (T1.ABONOS8110-T1.CARGOS8110)*-1 ELSE (T1.ABONOS8110-T1.CARGOS8110) END END TOT8110, ");
		sSql.append("(T1.ABONOS8140-T1.CARGOS8140) DEVENGADO, ");
		sSql.append("(T1.ABONOS8150-T1.CARGOS8150) RECAUDADO ");
		sSql.append("FROM ");
		sSql.append("( ");
		sSql.append("SELECT ");
		sSql.append("CASE  ");
		sSql.append("	WHEN C.SCTA = '000000000004311' THEN 5 ");
		sSql.append("	WHEN C.SCTA = '000000000004399' AND C.SSCTA = '' THEN 7 ");
		sSql.append("	WHEN C.SCTA = '000000000004399' AND C.SSSSCTA = '019' THEN 10 ");
		sSql.append("	WHEN C.SCTA = '000000000004223' THEN 9	 ");
		sSql.append("END ORDEN, ");
		sSql.append("C.SCTA SCTA, ");
		sSql.append("C.SSCTA SSCTA, ");
		sSql.append("C.SSSCTA SSSCTA, ");
		sSql.append("C.SSSSCTA SSSSCTA, ");
		sSql.append("CASE  ");
		sSql.append("	WHEN C.SCTA = '000000000004311' THEN 'Productos' ");
		sSql.append("	WHEN C.SCTA = '000000000004399' AND C.SSCTA = '' THEN 'Ingresos por Ventas de Bienes, Prestación de Servicios y Otros Ingresos' ");
		sSql.append("	WHEN C.SCTA = '000000000004399' AND C.SSSSCTA = '019' THEN 'Ingresos Derivados de Financiamientos' ");
		sSql.append("	WHEN C.SCTA = '000000000004223' THEN 'Transferencias, Asignaciones, Subsidios y Subvenciones, y Pensiones y Jubilaciones' ");
		sSql.append("END NOMBRE, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8110',C.SALINI,0.00)) ESTIMADO, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8110',("+cargos+"),0.00)) CARGOS8110, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8110',("+abonos+"),0.00)) ABONOS8110, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8140',("+cargos+"),0.00)) CARGOS8140, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8140',("+abonos+"),0.00)) ABONOS8140, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8150',("+cargos+"),0.00)) CARGOS8150, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8150',("+abonos+"),0.00)) ABONOS8150 ");
		sSql.append("FROM ");
		sSql.append("CUENTA C ");
		sSql.append("WHERE ");
		sSql.append("C.IDSECTOR= "+sector+" AND ");
		sSql.append("C.CUENTA IN ('8110','8140','8150') AND  ");
		sSql.append("(C.SCTA IN ( '000000000004311', '000000000004223', '000000000004399') AND C.SSCTA = '') OR ");
		sSql.append("(C.SCTA = '000000000004399' AND C.SSSSCTA = '019') ");
		sSql.append("GROUP BY ");
		sSql.append("C.SCTA, ");
		sSql.append("C.SSCTA, ");
		sSql.append("C.SSSCTA, ");
		sSql.append("C.SSSSCTA)T1)T2");
//		sSql.append("ORDER BY 1");
		System.out.println(sSql.toString());
		return sSql.toString();
	}
	
	@Override
	public String getQueryEstadoAnaliticoIngresoInfoemPE() {

		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT ");
		sSql.append("1 ORDEN, ");
		sSql.append("'Impuestos' NOMBRE, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 AMPLIACIONES, ");
		sSql.append("0 MODIFICADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO, ");
		sSql.append("0 DIFERENCIA ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("2 ORDEN, ");
		sSql.append("'Cuotas y Aportaciones de Seguridad Social' NOMBRE, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 AMPLIACIONES, ");
		sSql.append("0 MODIFICADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO, ");
		sSql.append("0 DIFERENCIA ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("3 ORDEN, ");
		sSql.append("'Contribuciones de Mejoras' NOMBRE, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 AMPLIACIONES, ");
		sSql.append("0 MODIFICADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO, ");
		sSql.append("0 DIFERENCIA ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("4 ORDEN, ");
		sSql.append("'Derechos' NOMBRE, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 AMPLIACIONES, ");
		sSql.append("0 MODIFICADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO, ");
		sSql.append("0 DIFERENCIA ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("5 ORDEN, ");
		sSql.append("'Productos1' NOMBRE, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 AMPLIACIONES, ");
		sSql.append("0 MODIFICADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO, ");
		sSql.append("0 DIFERENCIA ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("6 ORDEN, ");
		sSql.append("'Aprovechamientos2' NOMBRE, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 AMPLIACIONES, ");
		sSql.append("0 MODIFICADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO, ");
		sSql.append("0 DIFERENCIA ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("7 ORDEN, ");
		sSql.append("'Participaciones, Aportaciones,  Convenios, Incentivos Derivados de la Colaboración Fiscal y Fondos Distintos de Aportaciones' NOMBRE, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 AMPLIACIONES, ");
		sSql.append("0 MODIFICADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO, ");
		sSql.append("0 DIFERENCIA ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT ");
		sSql.append("8 ORDEN, ");
		sSql.append("'Transferencias, Asignaciones, Subsidios y Subvenciones, y Pensiones y Jubilaciones' NOMBRE, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 AMPLIACIONES, ");
		sSql.append("0 MODIFICADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO, ");
		sSql.append("0 DIFERENCIA ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("ORDER BY 1");	
		System.out.println(sSql.toString());
		return sSql.toString();
	}

	@Override
	public String getQueryEstadoAnaliticoIngresoInfoemEP(Integer periodo, Integer sector, boolean mensual) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();

		Integer month = mensual ? periodo : getMonth(periodo);

		for (int i = 1; i <= month; i++) {
			abonos.append("C.ABONOS_").append(i);
			cargos.append("C.CARGOS_").append(i);

			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");

			}
		}
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT ");
		sSql.append("1 ORDEN, ");
		sSql.append("'Cuotas y Aportaciones de Seguridad Social' NOMBRE, ");
		sSql.append("0 ESTIMADO, ");
		sSql.append("0 AMPLIACIONES, ");
		sSql.append("0 MODIFICADO, ");
		sSql.append("0 DEVENGADO, ");
		sSql.append("0 RECAUDADO, ");
		sSql.append("0 DIFERENCIA ");
		sSql.append("FROM SYSIBM.SYSDUMMY1 ");
		sSql.append("UNION ");
		sSql.append("SELECT  ");
		sSql.append("T2.ORDEN, ");
		sSql.append("T2.NOMBRE, ");
		sSql.append("T2.ESTIMADO ESTIMADO, ");
		sSql.append("T2.TOT8110 AMPLIACIONES, ");
		sSql.append("(T2.ESTIMADO+T2.TOT8110) MODIFICADO, ");
		sSql.append("T2.DEVENGADO DEVENGADO, ");
		sSql.append("T2.RECAUDADO RECAUDADO, ");
		sSql.append("(T2.RECAUDADO-T2.ESTIMADO)DIFERENCIA ");
		sSql.append("	FROM ");
		sSql.append("		( ");
		sSql.append("SELECT ");
		sSql.append("T1.ORDEN, ");
		sSql.append("T1.NOMBRE, ");
		sSql.append("T1.ESTIMADO, ");
		sSql.append("CASE WHEN T1.SCTA = '000000000004399' AND T1.SSCTA = '' THEN 0.00 ELSE ");
		sSql.append("CASE WHEN (T1.ABONOS8110-T1.CARGOS8110) < 0 THEN (T1.ABONOS8110-T1.CARGOS8110)*-1 ELSE (T1.ABONOS8110-T1.CARGOS8110) END END TOT8110, ");
		sSql.append("(T1.ABONOS8140-T1.CARGOS8140) DEVENGADO, ");
		sSql.append("(T1.ABONOS8150-T1.CARGOS8150) RECAUDADO ");
		sSql.append("FROM ");
		sSql.append("( ");
		sSql.append("SELECT ");
		sSql.append("CASE  ");
		sSql.append("	WHEN C.SCTA = '000000000004311' THEN 2 ");
		sSql.append("	WHEN C.SCTA = '000000000004399' AND C.SSCTA = '' THEN 3 ");
		sSql.append("	WHEN C.SCTA = '000000000004223' THEN 4	 ");
		sSql.append("END ORDEN, ");
		sSql.append("C.SCTA SCTA, ");
		sSql.append("C.SSCTA SSCTA, ");
		sSql.append("C.SSSCTA SSSCTA, ");
		sSql.append("C.SSSSCTA SSSSCTA, ");
		sSql.append("CASE  ");
		sSql.append("	WHEN C.SCTA = '000000000004311' THEN 'Productos1' ");
		sSql.append("	WHEN C.SCTA = '000000000004399' AND C.SSCTA = '' THEN 'Ingresos por Ventas de Bienes, Prestación de Servicios y Otros Ingresos3' ");
		sSql.append("	WHEN C.SCTA = '000000000004223' THEN 'Transferencias, Asignaciones, Subsidios y Subvenciones, y Pensiones y Jubilaciones' ");
		sSql.append("END NOMBRE, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8110',C.SALINI,0.00)) ESTIMADO, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8110',("+cargos+"),0.00)) CARGOS8110, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8110',("+abonos+"),0.00)) ABONOS8110, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8140',("+cargos+"),0.00)) CARGOS8140, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8140',("+abonos+"),0.00)) ABONOS8140, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8150',("+cargos+"),0.00)) CARGOS8150, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8150',("+abonos+"),0.00)) ABONOS8150 ");
		sSql.append("FROM ");
		sSql.append("CUENTA C ");
		sSql.append("WHERE ");
		sSql.append("C.IDSECTOR= "+sector+" AND ");
		sSql.append("C.CUENTA IN ('8110','8140','8150') AND  ");
		sSql.append("C.SCTA IN ( '000000000004311', '000000000004223', '000000000004399') AND C.SSCTA = ''  ");
		sSql.append("GROUP BY ");
		sSql.append("C.SCTA, ");
		sSql.append("C.SSCTA, ");
		sSql.append("C.SSSCTA, ");
		sSql.append("C.SSSSCTA)T1)T2 ");
		sSql.append("ORDER BY 1");
		System.out.println(sSql.toString());
		return sSql.toString();
	}
	
	@Override
	public String getQueryEstadoAnaliticoIngresoInfoemDF(Integer periodo, Integer sector, boolean mensual) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();

		Integer month = mensual ? periodo : getMonth(periodo);

		for (int i = 1; i <= month; i++) {
			abonos.append("C.ABONOS_").append(i);
			cargos.append("C.CARGOS_").append(i);

			if (i < month) {
				cargos.append(" + ");
				abonos.append(" + ");

			}
		}
		
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT ");
		sSql.append("T2.ORDEN, ");
		sSql.append("T2.NOMBRE, ");
		sSql.append("T2.ESTIMADO ESTIMADO, ");
		sSql.append("T2.TOT8110 AMPLIACIONES, ");
		sSql.append("(T2.ESTIMADO+T2.TOT8110) MODIFICADO, ");
		sSql.append("T2.DEVENGADO DEVENGADO, ");
		sSql.append("T2.RECAUDADO RECAUDADO, ");
		sSql.append("(T2.RECAUDADO-T2.ESTIMADO)DIFERENCIA ");
		sSql.append("	FROM ");
		sSql.append("		( ");
		sSql.append("SELECT ");
		sSql.append("T1.ORDEN, ");
		sSql.append("T1.NOMBRE, ");
		sSql.append("T1.ESTIMADO, ");
		sSql.append("CASE WHEN (T1.ABONOS8110-T1.CARGOS8110) < 0 THEN (T1.ABONOS8110-T1.CARGOS8110)*-1 ELSE (T1.ABONOS8110-T1.CARGOS8110) END TOT8110, ");
		sSql.append("(T1.ABONOS8140-T1.CARGOS8140) DEVENGADO, ");
		sSql.append("(T1.ABONOS8150-T1.CARGOS8150) RECAUDADO ");
		sSql.append("FROM ");
		sSql.append("( ");
		sSql.append("SELECT ");
		sSql.append("1 ORDEN, ");
		sSql.append("C.SCTA SCTA, ");
		sSql.append("C.SSCTA SSCTA, ");
		sSql.append("C.SSSCTA SSSCTA, ");
		sSql.append("C.SSSSCTA SSSSCTA, ");
		sSql.append("'Ingresos Derivados de Financiamientos' NOMBRE, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8110',C.SALINI,0.00)) ESTIMADO, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8110',("+cargos+"),0.00)) CARGOS8110, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8110',("+abonos+"),0.00)) ABONOS8110, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8140',("+cargos+"),0.00)) CARGOS8140, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8140',("+abonos+"),0.00)) ABONOS8140, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8150',("+cargos+"),0.00)) CARGOS8150, ");
		sSql.append("SUM(DECODE(C.CUENTA,'8150',("+abonos+"),0.00)) ABONOS8150 ");
		sSql.append("FROM ");
		sSql.append("CUENTA C ");
		sSql.append("WHERE ");
		sSql.append("C.IDSECTOR= "+sector+" AND ");
		sSql.append("C.CUENTA IN ('8110','8140','8150') AND  ");
		sSql.append("C.SCTA = '000000000004399' AND C.SSSSCTA = '019' ");
		sSql.append("GROUP BY ");
		sSql.append("C.SCTA, ");
		sSql.append("C.SSCTA, ");
		sSql.append("C.SSSCTA, ");
		sSql.append("C.SSSSCTA)T1)T2");
		System.out.println(sSql.toString());
		return sSql.toString();
	}

	@Override
	public String getQueryEdoCambioInfoem(Integer trimestre, Integer sector, boolean mensual) {
		StringBuilder cargosAct = new StringBuilder();
		StringBuilder abonosAct = new StringBuilder();
		StringBuilder cargosAnt = new StringBuilder();
		StringBuilder abonosAnt = new StringBuilder();
		Integer month = mensual ? trimestre : getMonth(trimestre);
		Integer monthB = mensual ? trimestre-1 : getMonthBefore(trimestre);
		
		if(month == 1) {
//			cargosAnt.append("0.00");
//			abonosAnt.append("0.00");
			for (int i = 1; i <= 12; i++) {
				cargosAnt.append("CA.CARGOS_" + i);
				abonosAnt.append("CA.ABONOS_" + i);
				if (i < 12) {
					cargosAnt.append(" + ");
					abonosAnt.append(" + ");
				}
			}
		} else {
			for (int i = 1; i <= monthB; i++) {
				cargosAnt.append("C.CARGOS_" + i);
				abonosAnt.append("C.ABONOS_" + i);
				if (i < monthB) {
					cargosAnt.append(" + ");
					abonosAnt.append(" + ");
				}
			}
		}
	
		for (int i = 1; i <= month; i++) {
			abonosAct.append("C.ABONOS_").append(i);
			cargosAct.append("C.CARGOS_").append(i);
			if (i < month) {
				cargosAct.append(" + ");
				abonosAct.append(" + ");
			}
		}
		
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT ")
		.append("CASE WHEN CUENTA = '2110' THEN 2 ELSE 1 END PRINCIPAL, ")
		.append("CASE ")
		.append("WHEN CUENTA = '1111' THEN 1 ")
		.append("WHEN CUENTA = '1123' THEN 1 ")
		.append("WHEN CUENTA = '1241' THEN 2 ")
		.append("WHEN CUENTA = '1263' THEN 2 ")
		.append("WHEN CUENTA = '2110' THEN 3 ")
		.append("END GRUPO, ")
		.append("CASE ")
		.append("WHEN CUENTA = '1111' THEN 1 ")
		.append("WHEN CUENTA = '1123' THEN 2 ")
		.append("WHEN CUENTA = '1241' THEN 4 ")
		.append("WHEN CUENTA = '1263' THEN 6 ")
		.append("WHEN CUENTA = '2110' THEN 1 ")
		.append("END ORDEN, ")
		.append("NOMBRE, ")
		.append("CASE ")
		.append("WHEN CUENTA = '1111' THEN TOTAL*-1 ")
		.append("WHEN CUENTA = '1123' THEN 0.00 ")
		.append("WHEN CUENTA = '1241' THEN TOTAL*-1 ")
		.append("WHEN CUENTA = '1263' THEN 0.00 ")
		.append("WHEN CUENTA = '2110' THEN TOTAL*-1 ")
		.append("END ORIGEN, ")
		.append("CASE ")
		.append("WHEN CUENTA = '1111' THEN 0.00 ")
		.append("WHEN CUENTA = '1123' THEN TOTAL ")
		.append("WHEN CUENTA = '1241' THEN 0.00 ")
		.append("WHEN CUENTA = '1263' THEN TOTAL ")
		.append("WHEN CUENTA = '2110' THEN 0.00 ")
		.append("END APLICACION ")
		.append("FROM( ")
		.append("SELECT CUENTA,NOMBRE,  ")
		.append("(SUM(SALINI)+SUM(CARGOS_ACT)-SUM(ABONOS_ACT))- ");
		if(month == 1) {
			sSql.append("(SUM(SALINI_ANT)+SUM(CARGOS_ANT)-SUM(ABONOS_ANT)) TOTAL ");
		} else {
			sSql.append("(SUM(SALINI)+SUM(CARGOS_ANT)-SUM(ABONOS_ANT)) TOTAL ");
		}
		sSql.append("FROM( ")
		.append("SELECT ")
		.append("CASE  ")
		.append("WHEN GRUPO = 11 AND CUENTA IN ('1111','1112') THEN '1111' ")
		.append("WHEN GRUPO = 11 AND CUENTA = '1123' THEN '1123' ")
		.append("WHEN GRUPO = 11 AND CUENTA = '1191' THEN '1191' ")
		.append("WHEN GRUPO = 12 AND NATCTA = 'D' THEN '1241' ")
		.append("WHEN GRUPO = 12 AND NATCTA = 'A' THEN '1263' ")
		.append("WHEN GRUPO = 21 AND NATCTA = '' THEN '2110' ")
		.append("END CUENTA, ")
		.append("CASE  ")
		.append("WHEN GRUPO = 11 AND CUENTA IN ('1111','1112') THEN 'Efectivo y Equivalentes' ")
		.append("WHEN GRUPO = 11 AND CUENTA = '1123' THEN 'Derechos a Recibir Efectivo o Equivalentes' ")
		.append("WHEN GRUPO = 11 AND CUENTA = '1191' THEN 'Otros Activos Circulantes' ")
		.append("WHEN GRUPO = 12 AND NATCTA = 'D' THEN 'Bienes Muebles' ")
		.append("WHEN GRUPO = 12 AND NATCTA = 'A' THEN 'Depreciación, Deterioro y Amortización Acumulada de Bienes' ")
		.append("WHEN GRUPO = 21 AND NATCTA = '' THEN 'Cuentas por Pagar a Corto Plazo' ")
		.append("END NOMBRE, ")
		.append("SALINI, SALINI_ANT, CARGOS_ANT,ABONOS_ANT,CARGOS_ACT,ABONOS_ACT ")
		.append("FROM ( ")
		.append("SELECT 		 ")
		.append("SUBSTR(C.CUENTA,1,2)GRUPO, ")
		.append("MA.NATCTA,  ")
		.append("C.NOMCTA,  ")
		.append("C.CUENTA,   ")
		.append("C.SALINI,  ")
		.append("CA.SALINI SALINI_ANT,  ")
		.append("SUM("+cargosAnt+") CARGOS_ANT, ")
		.append("SUM("+abonosAnt+") ABONOS_ANT, ")
		.append("SUM("+abonosAct+") ABONOS_ACT, ")
		.append("SUM("+cargosAct+") CARGOS_ACT ")
		.append("FROM   MAYCTA MA ")
		.append("INNER JOIN CUENTA C ON MA.CUENTA = C.CUENTA  ")
		.append("INNER JOIN CUENTA_ANTERIOR CA ON CA.CUENTA = C.CUENTA  ")
		.append("AND CA.SCTA = C.SCTA AND CA.IDSECTOR = C.IDSECTOR ")
		.append("WHERE  C.CUENTA IN ('1111','1112','1123', ")
		.append("'1241','1242','1244','1246','1263', ")
		.append("'2110') ")
		.append("AND C.SCTA = ''  ")
		.append("AND C.IDSECTOR = ").append(sector)
		.append(" GROUP BY  ")
		.append("MA.NATCTA,  ")
		.append("C.NOMCTA,  ")
		.append("C.CUENTA,   ")
		.append("C.SALINI,  ")
		.append("CA.SALINI,  ")
		.append("SUBSTR(C.CUENTA,1,2) ")
		.append("ORDER BY C.CUENTA)) ")
		.append("GROUP BY CUENTA,NOMBRE ) ")
		.append("UNION ")
		.append("SELECT ")
		.append("3 PRINCIPAL, 6 GRUPO, 1 ORDEN, ")
		.append("'Resultados del Ejercicio (Ahorro/Desahorro)' NOMBRE, 0.00 ORIGEN, ")
		.append("((SUM(T3.VACT4)-SUM(T3.VACT5))-(SUM(T3.VAN4)-SUM(T3.VAN5)))*-1 APLICACION ")
		.append("FROM ")
		.append("( ")
		.append("SELECT ")
		.append("CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMANT) ELSE 0.00 END VAN4, ")
		.append("CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMANT) ELSE 0.00 END VAN5, ")
		.append("CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMACT) ELSE 0.00 END VACT4, ")
		.append("CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMACT) ELSE 0.00 END VACT5 ")
		.append("FROM ")
		.append("( ")
		.append("SELECT ")
		.append("T1.GRUPO, ")
		.append("T1.SALINI, ");
		if(month == 1) {
			sSql.append("CASE WHEN T1.GRUPO ='4' THEN (T1.SALINI_ANT+ABONOS_ANT-CARGOS_ANT) ELSE  ")
			.append("(T1.SALINI_ANT-ABONOS_ANT+CARGOS_ANT) END VMANT, ");
		} else {
			sSql.append("CASE WHEN T1.GRUPO ='4' THEN (T1.SALINI+ABONOS_ANT-CARGOS_ANT) ELSE  ")
			.append("(T1.SALINI-ABONOS_ANT+CARGOS_ANT) END VMANT, ");
		}
		sSql.append("CASE WHEN T1.GRUPO ='4' THEN (T1.SALINI+ABONOS_ACT-CARGOS_ACT) ELSE  ")
		.append("(T1.SALINI-ABONOS_ACT+CARGOS_ACT) END VMACT ")
		.append("FROM ")
		.append("( ")
		.append("SELECT ")
		.append("SUBSTR(C.CUENTA,1,1) GRUPO, ")
		.append("C.CUENTA CUENTA , ")
		.append("C.SALINI SALINI, ")
		.append("CA.SALINI SALINI_ANT, ")
		.append("SUM("+cargosAnt+") CARGOS_ANT, ")
		.append("SUM("+abonosAnt+") ABONOS_ANT, ")
		.append("SUM("+cargosAct+") CARGOS_ACT, ")
		.append("SUM("+abonosAct+") ABONOS_ACT ")
		.append("FROM ")
		.append("CUENTA C ")
		.append("INNER JOIN CUENTA_ANTERIOR CA ON CA.CUENTA = C.CUENTA  ")
		.append("AND CA.SCTA = C.SCTA AND CA.IDSECTOR = C.IDSECTOR ")
		.append("WHERE ")
		.append("C.IDSECTOR= ").append(sector)
		.append(" AND ( ( (SUBSTR(C.CUENTA,1,1) =4 AND ")
		.append("SUBSTR(C.CUENTA,4,1)<>'0') OR ")
		.append("(SUBSTR(C.CUENTA,1,1) = 5 AND ")
		.append("SUBSTR(C.CUENTA,2,1) > 0) ) AND ")
		.append("C.SCTA ='') ")
		.append("GROUP BY ")
		.append("C.CUENTA, ")
		.append("C.SALINI, ")
		.append("CA.SALINI ")
		.append("ORDER BY ")
		.append("C.CUENTA)T1)T2 ")
		.append("GROUP BY ")
		.append("T2.GRUPO )T3 ")
		.append("UNION ALL ")
		.append("SELECT * FROM ( VALUES ")
		.append("(1, 1, 3, 'Derechos a Recibir Bienes o Servicios', 0.00, 0.00), ")
		.append("(1, 1, 4, 'Inventarios', 0.00, 0.00), ")
		.append("(1, 1, 5, 'Almacenes', 0.00, 0.00), ")
		.append("(1, 1, 6, 'Estimación por Pérdida o Deterioro de Activos Circulantes', 0.00, 0.00), ")
		.append("(1, 1, 7, 'Otros Activos Circulantes', 0.00, 0.00), ")
		.append("(1, 2, 1, 'Inversiones Financieras a Largo Plazo', 0.00, 0.00), ")
		.append("(1, 2, 2, 'Derechos a Recibir Efectivo o Equivalentes a Largo Plazo', 0.00, 0.00), ")
		.append("(1, 2, 3, 'Bienes Inmuebles, Infraestructura y Construcciones en Proceso', 0.00, 0.00), ")
		.append("(1, 2, 5, 'Activos Intangibles', 0.00, 0.00), ")
		.append("(1, 2, 7, 'Activos Diferidos', 0.00, 0.00), ")
		.append("(1, 2, 8, 'Estimación por Pérdida o Deterioro de Activos no Circulantes', 0.00, 0.00), ")
		.append("(1, 2, 9, 'Otros Activos no Circulantes', 0.00, 0.00), ")
		.append("(2, 3, 2, 'Documentos por Pagar a Corto Plazo', 0.00, 0.00), ")
		.append("(2, 3, 3, 'Porción a Corto Plazo de la Deuda Pública a Largo Plazo', 0.00, 0.00), ")
		.append("(2, 3, 4, 'Títulos y Valores a Corto Plazo', 0.00, 0.00), ")
		.append("(2, 3, 5, 'Pasivos Diferidos a Corto Plazo', 0.00, 0.00), ")
		.append("(2, 3, 6, 'Fondos y Bienes de Terceros en Garantía y/o Administración a Corto Plazo', 0.00, 0.00), ")
		.append("(2, 3, 7, 'Provisiones a Corto Plazo', 0.00, 0.00), ")
		.append("(2, 3, 8, 'Otros Pasivos a Corto Plazo', 0.00, 0.00), ")
		.append("(2, 4, 1, 'Cuentas por Pagar a Largo Plazo', 0.00, 0.00), ")
		.append("(2, 4, 2, 'Documentos por Pagar a Largo Plazo', 0.00, 0.00), ")
		.append("(2, 4, 3, 'Deuda Pública a Largo Plazo', 0.00, 0.00), ")
		.append("(2, 4, 4, 'Pasivos Diferidos a Largo Plazo', 0.00, 0.00), ")
		.append("(2, 4, 5, 'Fondos y Bienes de Terceros en Garantía y/o en Administración a Largo Plazo', 0.00, 0.00), ")
		.append("(2, 4, 6, 'Provisiones a Largo Plazo', 0.00, 0.00), ")
		.append("(3, 5, 1, 'Aportaciones', 0.00, 0.00), ")
		.append("(3, 5, 2, 'Donaciones de Capital', 0.00, 0.00), ")
		.append("(3, 5, 3, 'Actualización de la Hacienda Pública/Patrimonio', 0.00, 0.00), ")
		.append("(3, 6, 2, 'Resultados de Ejercicios Anteriores', 0.00, 0.00), ")
		.append("(3, 6, 3, 'Revalúos', 0.00, 0.00), ")
		.append("(3, 6, 4, 'Reservas', 0.00, 0.00), ")
		.append("(3, 6, 5, 'Rectificaciones de Resultados de Ejercicios Anteriores', 0.00, 0.00), ")
		.append("(3, 7, 1, 'Resultado por Posición Monetaria', 0.00, 0.00), ")
		.append("(3, 7, 2, 'Resultado por Tenencia de Activos no Monetarios', 0.00, 0.00) ")
		.append(") AS TEMP (PRINCIPAL, GRUPO, ORDEN, NOMBRE, ORIGEN, APLICACION) ")
		.append("ORDER BY 1,2,3");
		
		System.out.println(sSql.toString());
		return sSql.toString();
	}

}
