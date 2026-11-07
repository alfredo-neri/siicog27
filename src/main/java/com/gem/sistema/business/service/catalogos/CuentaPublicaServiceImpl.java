package com.gem.sistema.business.service.catalogos;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.dto.TotalIngresoEgreso;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;

@Service(value = "cuentaPublicaService")
public class CuentaPublicaServiceImpl implements CuentaPublicaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ConctbRepository conctbRepository;

    @Override
    public Conctb getAllConctb(Integer idSector) {
        return this.conctbRepository.findByIdsector(idSector);
    }

    @Override
    public TotalIngresoEgreso getTotalIngresoEgreso() {
        String sSql = "SELECT SUM(T2.AUTORIZADO) AUTORIZADO,\r\n"
        		+ "       SUM(T2.AMPLIACION)AMPLIACION,\r\n"
        		+ "       SUM(T2.REDUCCION) REDUCCION,\r\n"
        		+ "       SUM(T2.AUTO_FINAL) AUTO_FINAL,\r\n"
        		+ "        SUM(T2.PRES_EJERCIDO) PRES_EJERCIDO,\r\n"
        		+ "       SUM(T2.VARIACION_INICIAL)VARIACION_INICIAL,\r\n"
        		+ "       SUM(T2.VARIACION_MODIFICADO)VARIACION_MODIFICADO\r\n"
        		+ "    FROM (\r\n"
        		+ "SELECT  * FROM (\r\n"
        		+ "VALUES\r\n"
        		+ "        ( 0.00, 0.00, 0.00, 0.00, FN_GET_PRESUPUESTO_EJERCIDO_4311, 0.00, 0.00)\r\n"
        		+ "                )\r\n"
        		+ "AS TEMP ( AUTORIZADO, AMPLIACION, REDUCCION, AUTO_FINAL, PRES_EJERCIDO, VARIACION_INICIAL, VARIACION_MODIFICADO)\r\n"
        		+ "UNION ALL\r\n"
        		+ "SELECT "
        		+ "       SUM(T1.AUTORIZADO) AUTORIZADO,\r\n"
        		+ "       SUM(T1.CARGOS)  AMPLIACION,\r\n"
        		+ "       SUM(T1.ABONOS)  REDUCCION,\r\n"
        		+ "       SUM(T1.AUTORIZADO + T1.CARGOS - T1.ABONOS) AUTO_FINAL,\r\n"
        		+ "       SUM(T1.ABONOS - T1.CARGOS) PRES_EJERCIDO,\r\n"
        		+ "      SUM( (T1.ABONOS - T1.CARGOS) + T1.AUTORIZADO) VARIACION_INICIAL,\r\n"
        		+ "      SUM( (T1.AUTORIZADO + T1.CARGOS - T1.ABONOS) - (T1.ABONOS - T1.CARGOS)) VARIACION_MODIFICADO\r\n"
        		+ "    FROM(\r\n"
        		+ "SELECT SUBSTR(CU.SCTA,12,4) CUENTA,\r\n"
        		+ "       CU.NOMCTA,\r\n"
        		+ "       CU.SALINI AUTORIZADO,\r\n"
        		+ "       CU.CARGOS_1 + CU.CARGOS_2 + CU.CARGOS_3 + CU.CARGOS_4 + CU.CARGOS_5 + CU.CARGOS_6 + CU.CARGOS_7 + CU.CARGOS_8 + CU.CARGOS_9 + CU.CARGOS_10 + CU.CARGOS_11 + CU.CARGOS_12 CARGOS,\r\n"
        		+ "       CU.ABONOS_1 + CU.ABONOS_2 + CU.ABONOS_3 + CU.ABONOS_4 + CU.ABONOS_5 + CU.ABONOS_6 + CU.ABONOS_7 + CU.ABONOS_8 + CU.ABONOS_9 + CU.ABONOS_10 + CU.ABONOS_11 + CU.ABONOS_12 ABONOS\r\n"
        		+ "     FROM CUENTA CU\r\n"
        		+ "WHERE CU.CUENTA IN ('8150', '8140', '8110')\r\n"
        		+ "  --AND CU.SALINI <> 0\r\n"
        		+ "  AND SCTA IN( '000000000004399', '000000000004223')\r\n"
        		+ "  AND CU.SSCTA = ''\r\n"
        		+ "  ) T1 )T2"
        		;
        TotalIngresoEgreso total = this.jdbcTemplate.queryForObject(sSql, new RowMapper<TotalIngresoEgreso>() {

            @Override
            public TotalIngresoEgreso mapRow(ResultSet rs, int rowNum) throws SQLException {
                TotalIngresoEgreso total = new TotalIngresoEgreso();
                total.setAutotizado(rs.getBigDecimal("AUTORIZADO"));
                total.setAmpliacion(rs.getBigDecimal("AMPLIACION"));
                total.setReduccion(rs.getBigDecimal("REDUCCION"));
                total.setAotorizadoFinal(rs.getBigDecimal("AUTO_FINAL"));
                total.setEjercido(rs.getBigDecimal("PRES_EJERCIDO"));
                total.setVariacionInicial(rs.getBigDecimal("VARIACION_INICIAL"));
                total.setModifcado(rs.getBigDecimal("VARIACION_MODIFICADO"));
                return total;
            }

        });
        return total;
    }

    @Override
    public String getQueryGastoCatProgramatica(Integer idSector) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("CASE WHEN PARTIDA = '1000' THEN 1 ELSE 4 END PRINCIPAL, ")
                .append("CASE WHEN PARTIDA = '1000' THEN 2 ELSE 9 END GRUPO, ")
                .append("CASE WHEN PARTIDA = '1000' THEN 5 ELSE 1 END ORDEN, ")
                .append("CONCEPTO, APROBADO, AMPL_REDU, MODIFICADO, DEVENGADO, ")
                .append("PAGADO, SUBEJERCICIO ")
                .append("FROM ( ")
                .append("SELECT ")
                .append("T1.PARTIDA, ")
                .append("T1.CONCEPTO, ")
                .append("SUM(T1.APROBADO) APROBADO, SUM(T1.AMPLIACION - T1.REDUCCION) AMPL_REDU, ")
                .append("SUM(T1.APROBADO + T1.AMPLIACION - T1.REDUCCION) MODIFICADO, ")
                .append("SUM(T1.COMPROMETIDO + T1.DEVENGADO + T1.PAGADO) DEVENGADO, ")
                .append("SUM(T1.PAGADO) PAGADO, ")
                .append("SUM(((T1.APROBADO + T1.AMPLIACION - T1.REDUCCION) - (T1.COMPROMETIDO + T1.DEVENGADO + T1.PAGADO))) SUBEJERCICIO ")
                .append("FROM ( ")
                .append("SELECT  ")
                .append("CASE WHEN P.PARTIDA <> '9000' THEN '1000' ELSE '9000' END PARTIDA, ")
                .append("CASE WHEN P.PARTIDA <> '9000' THEN 'Regulación y Supervisión' ")
                .append("ELSE '' END CONCEPTO, ")
                .append("SUM( P.AUTO1_1 +P.AUTO1_2 +P.AUTO1_3 +P.AUTO1_4 +P.AUTO1_5 +P.AUTO1_6 +P.AUTO1_7 +P.AUTO1_8 +P.AUTO1_9 +P.AUTO1_10 +P.AUTO1_11 +P.AUTO1_12) APROBADO, ")
                .append("SUM( P.AMPLI1_1 + P.AMPLI1_2 + P.AMPLI1_3 + P.AMPLI1_4 + P.AMPLI1_5 + P.AMPLI1_6 + P.AMPLI1_7 + P.AMPLI1_8 + P.AMPLI1_9 + P.AMPLI1_10 + P.AMPLI1_11 + P.AMPLI1_12) AMPLIACION, ")
                .append("SUM( P.REDU1_1 + P.REDU1_2 + P.REDU1_3 + P.REDU1_4 + P.REDU1_5 + P.REDU1_6 + P.REDU1_7 + P.REDU1_8 + P.REDU1_9 + P.REDU1_10 + P.REDU1_11 + P.REDU1_12) REDUCCION, ")
                .append("SUM( P.EJPA1_1 + P.EJPA1_2 + P.EJPA1_3 + P.EJPA1_4 + P.EJPA1_5 + P.EJPA1_6 + P.EJPA1_7 + P.EJPA1_8 + P.EJPA1_9 + P.EJPA1_10 + P.EJPA1_11 + P.EJPA1_12) PAGADO, ")
                .append("SUM( P.EJXPA1_1 + P.EJXPA1_2 + P.EJXPA1_3 + P.EJXPA1_4 + P.EJXPA1_5 + P.EJXPA1_6 + P.EJXPA1_7 + P.EJXPA1_8 + P.EJXPA1_9 + P.EJXPA1_10 + P.EJXPA1_11 + P.EJXPA1_12) DEVENGADO, ")
                .append("SUM( P.TOEJE1_1 + P.TOEJE1_2 + P.TOEJE1_3 + P.TOEJE1_4 + P.TOEJE1_5 + P.TOEJE1_6 + P.TOEJE1_7 + P.TOEJE1_8 + P.TOEJE1_9 + P.TOEJE1_10 + P.TOEJE1_11 + P.TOEJE1_12) EJERCIDO, ")
                .append("SUM( P.COMPRO1_1 + P.COMPRO1_2 + P.COMPRO1_3 + P.COMPRO1_4 + P.COMPRO1_5 + P.COMPRO1_6 + P.COMPRO1_7 + P.COMPRO1_8 + P.COMPRO1_9 + P.COMPRO1_10 + P.COMPRO1_11 + P.COMPRO1_12) COMPROMETIDO ")
                .append("FROM PASO P ")
                .append("INNER JOIN NATGAS N ON P.PARTIDA=N.CLVGAS AND P.IDSECTOR=N.IDSECTOR AND N.IDSECTOR=P.IDSECTOR ")
                .append("WHERE P.IDSECTOR = ").append(idSector)
                .append(" AND SUBSTR(P.PARTIDA,2,3) = '000' ")
                .append("GROUP BY N.NOMGAS,P.PARTIDA ORDER BY P.PARTIDA)T1 ")
                .append("GROUP BY T1.PARTIDA, T1.CONCEPTO ) ")
                .append("UNION ALL ")
                .append("SELECT * FROM ( VALUES ")
                .append("(1, 1, 1, 'Sujetos a Reglas de Operación', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1, 1, 2, 'Otros Subsidios', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1, 2, 1, 'Prestación de Servicios Públicos', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1, 2, 2, 'Provisión de Bienes Públicos', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1, 2, 3, 'Planeación, Seguimiento y Evaluación de Políticas Públicas', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1, 2, 4, 'Promoción y Fomento', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1, 2, 6, 'Funciones de las Fuerzas Armadas (Únicamente Gobierno Federal)', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1, 2, 7, 'Específicos', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1, 2, 8, 'Proyectos de Inversión', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1, 3, 1, 'Apoyo al Proceso Presupuestario y para Mejorar la Eficiencia Institucional', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1, 3, 2, 'Apoyo a la Función Pública y al Mejoramiento de la Gestión', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1, 3, 3, 'Operaciones Ajenas', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1, 4, 1, 'Obligaciones de Cumplimiento de Resolución Jurisdiccional', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1, 4, 2, 'Desastres Naturales', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1, 5, 1, 'Pensiones y Jubilaciones', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1, 5, 2, 'Aportaciones a la Seguridad Social', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1, 5, 3, 'Aportaciones a Fondos de Estabilización', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1, 5, 4, 'Aportaciones a Fondos de Inversión y Reestructura de Pensiones', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1, 6, 1, 'Gasto Federalizado', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(2, 7, 1, '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(3, 8, 1, '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00) ")
                .append(") AS TEMP (PRINCIPAL, GRUPO, ORDEN, CONCEPTO, APROBADO, AMPL_REDU, MODIFICADO, DEVENGADO, PAGADO, SUBEJERCICIO) ")
                .append("ORDER BY 1,2,3");

        System.out.println(sql);
        return sql.toString();
    }

    @Override
    public String getQueryEgresosPorPrograma(Integer idSector) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT 1 ORDEN, 'Desarrollo de la Función Pública y Ética en el Servicio Público' CONCEPTO, ")
                .append("T1.AUTORIZADO, T1.AMPLIACION, T1.REDUCCION, ")
                .append("(T1.AUTORIZADO + T1.AMPLIACION - T1.REDUCCION) TOTAL, ")
                .append("(T1.COMPROMETIDO + T1.DEVENGADO + T1.PAGADO) EJERCIDO, ")
                .append("((T1.AUTORIZADO + T1.AMPLIACION - T1.REDUCCION) - (T1.COMPROMETIDO + T1.DEVENGADO + T1.PAGADO)) IMPORTE ")
                .append("FROM (SELECT  ")
                .append("SUM( P.AUTO1_1 +P.AUTO1_2 +P.AUTO1_3 +P.AUTO1_4 +P.AUTO1_5 +P.AUTO1_6 +P.AUTO1_7 +P.AUTO1_8 +P.AUTO1_9 +P.AUTO1_10 +P.AUTO1_11 +P.AUTO1_12) AUTORIZADO, ")
                .append("SUM( P.AMPLI1_1 + P.AMPLI1_2 + P.AMPLI1_3 + P.AMPLI1_4 + P.AMPLI1_5 + P.AMPLI1_6 + P.AMPLI1_7 + P.AMPLI1_8 + P.AMPLI1_9 + P.AMPLI1_10 + P.AMPLI1_11 + P.AMPLI1_12) AMPLIACION, ")
                .append("SUM( P.REDU1_1 + P.REDU1_2 + P.REDU1_3 + P.REDU1_4 + P.REDU1_5 + P.REDU1_6 + P.REDU1_7 + P.REDU1_8 + P.REDU1_9 + P.REDU1_10 + P.REDU1_11 + P.REDU1_12) REDUCCION, ")
                .append("SUM( P.EJPA1_1 + P.EJPA1_2 + P.EJPA1_3 + P.EJPA1_4 + P.EJPA1_5 + P.EJPA1_6 + P.EJPA1_7 + P.EJPA1_8 + P.EJPA1_9 + P.EJPA1_10 + P.EJPA1_11 + P.EJPA1_12) PAGADO, ")
                .append("SUM( P.EJXPA1_1 + P.EJXPA1_2 + P.EJXPA1_3 + P.EJXPA1_4 + P.EJXPA1_5 + P.EJXPA1_6 + P.EJXPA1_7 + P.EJXPA1_8 + P.EJXPA1_9 + P.EJXPA1_10 + P.EJXPA1_11 + P.EJXPA1_12) DEVENGADO, ")
                .append("SUM( P.TOEJE1_1 + P.TOEJE1_2 + P.TOEJE1_3 + P.TOEJE1_4 + P.TOEJE1_5 + P.TOEJE1_6 + P.TOEJE1_7 + P.TOEJE1_8 + P.TOEJE1_9 + P.TOEJE1_10 + P.TOEJE1_11 + P.TOEJE1_12) EJERCIDO, ")
                .append("SUM( P.COMPRO1_1 + P.COMPRO1_2 + P.COMPRO1_3 + P.COMPRO1_4 + P.COMPRO1_5 + P.COMPRO1_6 + P.COMPRO1_7 + P.COMPRO1_8 + P.COMPRO1_9 + P.COMPRO1_10 + P.COMPRO1_11 + P.COMPRO1_12) COMPROMETIDO ")
                .append("FROM PASO P ")
                .append("INNER JOIN NATGAS N ON P.PARTIDA=N.CLVGAS AND P.IDSECTOR=N.IDSECTOR AND N.IDSECTOR=P.IDSECTOR ")
                .append("WHERE P.IDSECTOR = ").append(idSector)
                .append(" AND SUBSTR(P.PARTIDA,2,3) = '000' )T1 ")
                .append("UNION ALL ")
                .append("SELECT * FROM ( VALUES ")
                .append("(2, 'Desarrollo Comunitario', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(3, 'Prevención Médica para la Comunidad', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(4, 'Atención Médica', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(5, 'Salud para la Población Infantil y Adolescente', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(6, 'Salud para la Mujer', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(7, 'Alimentación para la Población Infantil', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(8, 'Alimentación y Nutrición Familiar', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(9, 'Protección a la Población Infantil y Adolescente', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(10, 'Atención a Personas con Discapacidad', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(11, 'Apoyo a los Adultos Mayores', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(12, 'Desarrollo Integral de la Familia', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(13, 'Igualdad de Trato y Oportunidades para la Mujer y el Hombre', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(14, 'Oportunidades para los Jóvenes', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(15, 'Inclusión Económica para la Igualdad de Género', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(16, 'Previsiones para el Pago de Adeudos de Ejecicios Fiscales Anteriores', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00) ")
                .append(") AS TEMP (ORDEN, CONCEPTO, AUTORIZADO, AMPLIACION, REDUCCION, TOTAL, EJERCIDO, IMPORTE) ")
                .append("ORDER BY ORDEN");

        System.out.println(sql);
        return sql.toString();
    }

    @Override
    public String getQueryPilarEjeTrasversal(Integer idSector) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT 3 ORDEN, 'Gobierno Capaz y Responsable' CONCEPTO, ")
                .append("T1.AUTORIZADO, T1.AMPLIACION, T1.REDUCCION, ")
                .append("(T1.AUTORIZADO + T1.AMPLIACION - T1.REDUCCION) TOTAL, ")
                .append("(T1.COMPROMETIDO + T1.DEVENGADO + T1.PAGADO) EJERCIDO, ")
                .append("((T1.AUTORIZADO + T1.AMPLIACION - T1.REDUCCION) - (T1.COMPROMETIDO + T1.DEVENGADO + T1.PAGADO)) IMPORTE ")
                .append("FROM (SELECT ")
                .append("SUM( P.AUTO1_1 +P.AUTO1_2 +P.AUTO1_3 +P.AUTO1_4 +P.AUTO1_5 +P.AUTO1_6 +P.AUTO1_7 +P.AUTO1_8 +P.AUTO1_9 +P.AUTO1_10 +P.AUTO1_11 +P.AUTO1_12) AUTORIZADO, ")
                .append("SUM( P.AMPLI1_1 + P.AMPLI1_2 + P.AMPLI1_3 + P.AMPLI1_4 + P.AMPLI1_5 + P.AMPLI1_6 + P.AMPLI1_7 + P.AMPLI1_8 + P.AMPLI1_9 + P.AMPLI1_10 + P.AMPLI1_11 + P.AMPLI1_12) AMPLIACION, ")
                .append("SUM( P.REDU1_1 + P.REDU1_2 + P.REDU1_3 + P.REDU1_4 + P.REDU1_5 + P.REDU1_6 + P.REDU1_7 + P.REDU1_8 + P.REDU1_9 + P.REDU1_10 + P.REDU1_11 + P.REDU1_12) REDUCCION, ")
                .append("SUM( P.EJPA1_1 + P.EJPA1_2 + P.EJPA1_3 + P.EJPA1_4 + P.EJPA1_5 + P.EJPA1_6 + P.EJPA1_7 + P.EJPA1_8 + P.EJPA1_9 + P.EJPA1_10 + P.EJPA1_11 + P.EJPA1_12) PAGADO, ")
                .append("SUM( P.EJXPA1_1 + P.EJXPA1_2 + P.EJXPA1_3 + P.EJXPA1_4 + P.EJXPA1_5 + P.EJXPA1_6 + P.EJXPA1_7 + P.EJXPA1_8 + P.EJXPA1_9 + P.EJXPA1_10 + P.EJXPA1_11 + P.EJXPA1_12) DEVENGADO, ")
                .append("SUM( P.TOEJE1_1 + P.TOEJE1_2 + P.TOEJE1_3 + P.TOEJE1_4 + P.TOEJE1_5 + P.TOEJE1_6 + P.TOEJE1_7 + P.TOEJE1_8 + P.TOEJE1_9 + P.TOEJE1_10 + P.TOEJE1_11 + P.TOEJE1_12) EJERCIDO, ")
                .append("SUM( P.COMPRO1_1 + P.COMPRO1_2 + P.COMPRO1_3 + P.COMPRO1_4 + P.COMPRO1_5 + P.COMPRO1_6 + P.COMPRO1_7 + P.COMPRO1_8 + P.COMPRO1_9 + P.COMPRO1_10 + P.COMPRO1_11 + P.COMPRO1_12) COMPROMETIDO ")
                .append("FROM PASO P ")
                .append("INNER JOIN NATGAS N ON P.PARTIDA=N.CLVGAS AND P.IDSECTOR=N.IDSECTOR AND N.IDSECTOR=P.IDSECTOR ")
                .append("WHERE P.IDSECTOR = ").append(idSector)
                .append(" AND SUBSTR(P.PARTIDA,2,3) = '000' )T1 ")
                .append("UNION ALL ")
                .append("SELECT * FROM ( VALUES ")
                .append("(1, 'Pilar Social', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(2, 'Igualdad de Género', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00) ")
                .append(") AS TEMP (ORDEN, CONCEPTO, AUTORIZADO, AMPLIACION, REDUCCION, TOTAL, EJERCIDO, IMPORTE) ")
                .append("ORDER BY ORDEN");

        System.out.println(sql);
        return sql.toString();
    }

    @Override
    public String getQueryAvanceOperativo(Integer idSector) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("CASE WHEN PARTIDA = '1000' THEN 4 ELSE 35 END ORDEN, ")
                .append("CASE WHEN PARTIDA = '1000' THEN '01' ELSE '' END FIN, ")
                .append("CASE WHEN PARTIDA = '1000' THEN '08' ELSE '' END FUN, ")
                .append("CASE WHEN PARTIDA = '1000' THEN '04' ELSE '' END SF, ")
                .append("CASE WHEN PARTIDA = '1000' THEN '01' ELSE '' END PP, ")
                .append("CASE WHEN PARTIDA = '1000' THEN '01' ELSE '' END SP, ")
                .append("CASE WHEN PARTIDA = '1000' THEN '01' ELSE '' END PR, ")
                .append("CASE WHEN PARTIDA = '1000' THEN 'VINCULACIÓN CIUDADANA CON LA ADMINISTRACIÓN PÚBLICA'  ")
                .append("ELSE 'Registro de Pagos de Adeudos de Ejercicios Fiscales Anteriores' END CONCEPTO, ")
                .append("CASE WHEN PARTIDA = '1000' THEN '' ELSE 'Documento' END MEDIDA, ")
                .append("CASE WHEN PARTIDA = '1000' THEN '' ELSE '0.00' END PROGRAMADO, ")
                .append("CASE WHEN PARTIDA = '1000' THEN '' ELSE '0.00' END ALCANZADO, ")
                .append("CASE WHEN PARTIDA = '1000' THEN '' ELSE '100.00' END AVANCE, ")
                .append("AUTORIZADO, EJERCIDO ")
                .append("FROM ( ")
                .append("SELECT T1.PARTIDA, ")
                .append("SUM(T1.APROBADO + T1.AMPLIACION - T1.REDUCCION) AUTORIZADO, ")
                .append("SUM(T1.COMPROMETIDO + T1.DEVENGADO + T1.PAGADO) EJERCIDO ")
                .append("FROM ( ")
                .append("SELECT  ")
                .append("CASE WHEN P.PARTIDA <> '9000' THEN '1000' ELSE '9000' END PARTIDA, ")
                .append("SUM( P.AUTO1_1 +P.AUTO1_2 +P.AUTO1_3 +P.AUTO1_4 +P.AUTO1_5 +P.AUTO1_6 +P.AUTO1_7 +P.AUTO1_8 +P.AUTO1_9 +P.AUTO1_10 +P.AUTO1_11 +P.AUTO1_12) APROBADO, ")
                .append("SUM( P.AMPLI1_1 + P.AMPLI1_2 + P.AMPLI1_3 + P.AMPLI1_4 + P.AMPLI1_5 + P.AMPLI1_6 + P.AMPLI1_7 + P.AMPLI1_8 + P.AMPLI1_9 + P.AMPLI1_10 + P.AMPLI1_11 + P.AMPLI1_12) AMPLIACION, ")
                .append("SUM( P.REDU1_1 + P.REDU1_2 + P.REDU1_3 + P.REDU1_4 + P.REDU1_5 + P.REDU1_6 + P.REDU1_7 + P.REDU1_8 + P.REDU1_9 + P.REDU1_10 + P.REDU1_11 + P.REDU1_12) REDUCCION, ")
                .append("SUM( P.EJPA1_1 + P.EJPA1_2 + P.EJPA1_3 + P.EJPA1_4 + P.EJPA1_5 + P.EJPA1_6 + P.EJPA1_7 + P.EJPA1_8 + P.EJPA1_9 + P.EJPA1_10 + P.EJPA1_11 + P.EJPA1_12) PAGADO, ")
                .append("SUM( P.EJXPA1_1 + P.EJXPA1_2 + P.EJXPA1_3 + P.EJXPA1_4 + P.EJXPA1_5 + P.EJXPA1_6 + P.EJXPA1_7 + P.EJXPA1_8 + P.EJXPA1_9 + P.EJXPA1_10 + P.EJXPA1_11 + P.EJXPA1_12) DEVENGADO, ")
                .append("SUM( P.TOEJE1_1 + P.TOEJE1_2 + P.TOEJE1_3 + P.TOEJE1_4 + P.TOEJE1_5 + P.TOEJE1_6 + P.TOEJE1_7 + P.TOEJE1_8 + P.TOEJE1_9 + P.TOEJE1_10 + P.TOEJE1_11 + P.TOEJE1_12) EJERCIDO, ")
                .append("SUM( P.COMPRO1_1 + P.COMPRO1_2 + P.COMPRO1_3 + P.COMPRO1_4 + P.COMPRO1_5 + P.COMPRO1_6 + P.COMPRO1_7 + P.COMPRO1_8 + P.COMPRO1_9 + P.COMPRO1_10 + P.COMPRO1_11 + P.COMPRO1_12) COMPROMETIDO ")
                .append("FROM PASO P ")
                .append("INNER JOIN NATGAS N ON P.PARTIDA=N.CLVGAS AND P.IDSECTOR=N.IDSECTOR AND N.IDSECTOR=P.IDSECTOR ")
                .append("WHERE P.IDSECTOR = ").append(idSector)
                .append(" AND SUBSTR(P.PARTIDA,2,3) = '000' ")
                .append("GROUP BY N.NOMGAS,P.PARTIDA ORDER BY P.PARTIDA)T1 ")
                .append("GROUP BY T1.PARTIDA) ")
                .append("UNION ALL ")
                .append("SELECT * FROM ( VALUES ")
                .append("(1, '', '', '', '', '', '', 'GOBIERNO', '', '', '', '', 0, 0), ")
                .append("(2, '', '', '', '', '', '', 'GOBIERNO CAPAZ Y RESPONSABLE', '', '', '', '', 0, 0), ")
                .append("(3, '', '', '', '', '', '', 'TRANSPARENCIA', '', '', '', '', 0, 0), ")
                .append("(5, '', '', '', '', '', '', 'Participar en Testificaciones con el Propósito de Asegurarse que los Actos Administrativos se Realicen Conforme a la Normatividad Vigente', 'Testificación', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(6, '', '', '', '', '', '', 'Participación del Órgano Interno de Control en Reuniones que por Mandato Legal o Disposición Administrativa así lo Requiera', 'Reunión', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(7, '', '', '', '', '', '', 'Realizar Inspecciones a Rubros Específicos en las Dependencias, Organismos Auxiliares del Ejecutivo Estatal, con el Propósito de Constatar el Cumplimiento del Marco Normativo que lo Regula', 'Inspección', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(8, '', '', '', '', '', '', 'Realizar Evaluaciones para Determinar el Grado de Eficacia y Eficiencia en los Procesos, así como en el Desempeño Institucional de las Dependencias y Organismos Auxiliares del Ejecutivo Estatal', 'Evaluación', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(9, '', '', '', '', '', '', 'Realizar Auditorías, con el Propósito de Verificar el Cumplimiento del Marco Normativo que Regula el Funcionamiento de las Dependencias y Organismos Auxiliares del Ejecutivo Estatal', 'Auditoría', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(10, '', '', '', '', '', '', 'DESARROLLO ECONÓMICO', '', '', '', '', 0, 0), ")
                .append("(11, '', '', '', '', '', '', 'PILAR ECONÓMICO', '', '', '', '', 0, 0), ")
                .append("(12, '', '', '', '', '', '', 'PROMOCIÓN ARTESANAL', '', '', '', '', 0, 0), ")
                .append("(13, '03', '09', '03', '01', '01', '01', 'INVESTIGACIÓN SOBRE ARTESANÍAS', '', '', '', '', 0, 0), ")
                .append("(14, '', '', '', '', '', '', 'Difusión del Trabajo Artesanal y las Artesanías del Estado de México', 'Publicación', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(15, '', '', '', '', '', '', 'Credencialización de los Artesanos Productores del Estado de México', 'Cartilla', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(16, '', '', '', '', '', '', 'Fomentar la Creatividad Artesanal Mediante la Coordinación, la Realización y Participación de Certámenes a Nivel Estatal, Nacional e Internacional', 'Certamen', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(17, '', '', '', '', '', '', 'Coordinación en el Desarrollo de Estudios Artesanales para Preservar, Desarrollar e Impulsar la Actividad Artesanal', 'Estudio', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(18, '03', '09', '03', '01', '01', '02', 'ORGANIZACIÓN, CAPACITACIÓN Y ASESORÍA FINANCIERA', '', '', '', '', 0, 0), ")
                .append("(19, '', '', '', '', '', '', 'Gestión de Capacitación Integral y/o Asistencia Técnica', 'Asesoría', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(20, '', '', '', '', '', '', 'Promover y Gestionar el Registro de Marcas', 'Proyecto', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(21, '', '', '', '', '', '', 'Impartir Cursos de Capacitación', 'Curso', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(22, '', '', '', '', '', '', 'Promover y Gestionar el Establecimiento de Organizaciones Artesanales', 'Asesoría', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(23, '', '', '', '', '', '', 'Gestionar el Financiamiento de la Actividad Artesanal', 'Gestión', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(24, '', '', '', '', '', '', 'Asesoría para la Obtención de Apoyos Financieros', 'Asesoría', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(25, '03', '09', '03', '01', '02', '01', 'COMERCIALIZACIÓN DE ARTESANÍAS', '', '', '', '', 0, 0), ")
                .append("(26, '', '', '', '', '', '', 'Venta de Artesanías en Tienda CASART Bajo el Esquema de Consignación', 'Miles de pesos', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(27, '', '', '', '', '', '', 'Orientación y Fomento a las Exportaciones', 'Asesoría', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(28, '', '', '', '', '', '', 'Gestionar la Apertura de Nuevos Puntos de Venta en Territorio Estatal y Nacional', 'Gestión', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(29, '03', '09', '03', '01', '02', '02', 'PROMOCIÓN Y FOMENTO ARTESANAL', '', '', '', '', 0, 0), ")
                .append("(30, '', '', '', '', '', '', 'Promover la Participación de los Artesanos Mexiquenses en Ferias y Exposiciones de Carácter Estatal, Nacional e Internacional', 'Evento', '0.00', '0.00', '0.00', 0, 0), ")
                .append("(31, '', '', '', '', '', '', 'OTROS NO CLASIFICADOS EN FUNCIONES ANTERIORES', '', '', '', '', 0, 0), ")
                .append("(32, '', '', '', '', '', '', 'GOBIERNO CAPAZ Y RESPONSABLE', '', '', '', '', 0, 0), ")
                .append("(33, '', '', '', '', '', '', 'PREVISIÓN PARA EL PAGO DE ADEUDOS DE EJERCICIOS FISCALES ANTERIORES', '', '', '', '', 0, 0), ")
                .append("(34, '04', '04', '01', '01', '01', '01', 'PASIVOS DERIVADOS DE EROGACIONES DEVENGADAS Y PENDIENTES DE EJERCICIOS ANTERIORES', '', '', '', '', 0, 0) ")
                .append(") AS TEMP (ORDEN, FIN, FUN, SF, PP, SP, PR, CONCEPTO, MEDIDA, PROGRAMADO, ALCANZADO, AVANCE, AUTORIZADO, EJERCIDO) ")
                .append("ORDER BY 1");

        System.out.println(sql);
        return sql.toString();
    }

    @Override
    public String getQueryEgresosPorCapitulo(Integer idSector) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT 1 GRUPO, PARTIDA, NOMGAS CONCEPTO, ")
                .append("AUTORIZADO, AMPLIACION, REDUCCION, ")
                .append("AUTORIZADO+AMPLIACION-REDUCCION TOTAL, ")
                .append("EJERCIDO, ")
                .append("AUTORIZADO+AMPLIACION-REDUCCION-EJERCIDO IMPORTE ")
                .append("FROM( ")
                .append("SELECT  ")
                .append("N.NOMGAS,P.PARTIDA, ")
                .append("SUM( P.AUTO1_1 +P.AUTO1_2 +P.AUTO1_3 +P.AUTO1_4 +P.AUTO1_5 +P.AUTO1_6 +P.AUTO1_7 +P.AUTO1_8 +P.AUTO1_9 +P.AUTO1_10 +P.AUTO1_11 +P.AUTO1_12) AUTORIZADO, ")
                .append("SUM( P.AMPLI1_1 + P.AMPLI1_2 + P.AMPLI1_3 + P.AMPLI1_4 + P.AMPLI1_5 + P.AMPLI1_6 + P.AMPLI1_7 + P.AMPLI1_8 + P.AMPLI1_9 + P.AMPLI1_10 + P.AMPLI1_11 + P.AMPLI1_12) AMPLIACION, ")
                .append("SUM( P.REDU1_1 + P.REDU1_2 + P.REDU1_3 + P.REDU1_4 + P.REDU1_5 + P.REDU1_6 + P.REDU1_7 + P.REDU1_8 + P.REDU1_9 + P.REDU1_10 + P.REDU1_11 + P.REDU1_12) REDUCCION, ")
                .append("SUM( P.TOEJE1_1 + P.TOEJE1_2 + P.TOEJE1_3 + P.TOEJE1_4 + P.TOEJE1_5 + P.TOEJE1_6 + P.TOEJE1_7 + P.TOEJE1_8 + P.TOEJE1_9 + P.TOEJE1_10 + P.TOEJE1_11 + P.TOEJE1_12) EJERCIDO, ")
                .append("SUM( P.COMPRO1_1 + P.COMPRO1_2 + P.COMPRO1_3 + P.COMPRO1_4 + P.COMPRO1_5 + P.COMPRO1_6 + P.COMPRO1_7 + P.COMPRO1_8 + P.COMPRO1_9 + P.COMPRO1_10 + P.COMPRO1_11 + P.COMPRO1_12) COMPROMETIDO ")
                .append("FROM PASO P ")
                .append("INNER JOIN NATGAS N ON P.PARTIDA=N.CLVGAS AND P.IDSECTOR=N.IDSECTOR AND N.IDSECTOR=P.IDSECTOR ")
                .append("WHERE P.IDSECTOR = ").append(idSector)
                .append(" AND SUBSTR(P.PARTIDA,2,3) = '000' ")
                .append("GROUP BY N.NOMGAS,P.PARTIDA ORDER BY P.PARTIDA ) ")
                .append("UNION ALL ")
                .append("SELECT * FROM ( VALUES ")
                .append("(1,'6000', 'Inversión Pública', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(2,'9001', 'Ramo 33', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(2,'9002', 'Ramo 23', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(2,'9003', 'Programa de Acciones para el Desarrollo (PAD)', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00) ")
                .append(") AS TEMP (GRUPO, CONCEPTO, PARTIDA, AUTORIZADO, AMPLIACION, REDUCCIONB, TOTAL, EJERCIDO, IMPORTE) ")
                .append("ORDER BY PARTIDA");

        System.out.println(sql);
        return sql.toString();
    }

    @Override
    public String getQueryEgresosObjetoGasto(Integer idSector) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT 1 PRINCIPAL, GRUPO, ")
                .append("CASE  ")
                .append("WHEN PARTIDA = '2471' THEN '2492'  ")
                .append("WHEN PARTIDA = '2492' THEN '2493'  ")
                .append("WHEN PARTIDA = '2941' THEN '2922' ")
                .append("WHEN PARTIDA = '3231' THEN '3252' ")
                .append("ELSE PARTIDA END PARTIDA,  ")
                .append("CASE  ")
                .append("WHEN PARTIDA = '1551' THEN 'Becas para Hijos de Trabajadores Sindicalizados'  ")
                .append("WHEN PARTIDA = '2561' THEN 'Fibras sintéticas y hules' ")
                .append("WHEN PARTIDA = '2741' THEN 'Blancos y Otros Productos Textiles' ")
                .append("ELSE NOMGAS END CONCEPTO, ")
                .append("AUTORIZADO, AMPLIACION, REDUCCION, ")
                .append("AUTORIZADO+AMPLIACION-REDUCCION TOTAL, ")
                .append("EJERCIDO, ")
                .append("AUTORIZADO+AMPLIACION-REDUCCION-EJERCIDO IMPORTE ")
                .append("FROM( ")
                .append("SELECT ")
                .append("SUBSTR(P.PARTIDA,1,1) GRUPO, ")
                .append("P.PARTIDA,N.NOMGAS, ")
                .append("SUM( P.AUTO1_1 +P.AUTO1_2 +P.AUTO1_3 +P.AUTO1_4 +P.AUTO1_5 +P.AUTO1_6 +P.AUTO1_7 +P.AUTO1_8 +P.AUTO1_9 +P.AUTO1_10 +P.AUTO1_11 +P.AUTO1_12) AUTORIZADO, ")
                .append("SUM( P.AMPLI1_1 + P.AMPLI1_2 + P.AMPLI1_3 + P.AMPLI1_4 + P.AMPLI1_5 + P.AMPLI1_6 + P.AMPLI1_7 + P.AMPLI1_8 + P.AMPLI1_9 + P.AMPLI1_10 + P.AMPLI1_11 + P.AMPLI1_12) AMPLIACION, ")
                .append("SUM( P.REDU1_1 + P.REDU1_2 + P.REDU1_3 + P.REDU1_4 + P.REDU1_5 + P.REDU1_6 + P.REDU1_7 + P.REDU1_8 + P.REDU1_9 + P.REDU1_10 + P.REDU1_11 + P.REDU1_12) REDUCCION, ")
                .append("SUM( P.TOEJE1_1 + P.TOEJE1_2 + P.TOEJE1_3 + P.TOEJE1_4 + P.TOEJE1_5 + P.TOEJE1_6 + P.TOEJE1_7 + P.TOEJE1_8 + P.TOEJE1_9 + P.TOEJE1_10 + P.TOEJE1_11 + P.TOEJE1_12) EJERCIDO, ")
                .append("SUM( P.COMPRO1_1 + P.COMPRO1_2 + P.COMPRO1_3 + P.COMPRO1_4 + P.COMPRO1_5 + P.COMPRO1_6 + P.COMPRO1_7 + P.COMPRO1_8 + P.COMPRO1_9 + P.COMPRO1_10 + P.COMPRO1_11 + P.COMPRO1_12) COMPROMETIDO ")
                .append("FROM PASO P ")
                .append("INNER JOIN NATGAS N ON P.PARTIDA=N.CLVGAS AND P.IDSECTOR=N.IDSECTOR AND N.IDSECTOR=P.IDSECTOR ")
                .append("WHERE P.IDSECTOR = ").append(idSector)
                .append(" AND SUBSTR(P.PARTIDA,4,1) <> '0' ")
                .append("GROUP BY N.NOMGAS,P.PARTIDA ORDER BY P.PARTIDA ) ")
                .append("UNION ALL ")
                .append("SELECT * FROM ( VALUES ")
                .append("(1,1,'1232', 'Becas para Médicos Residentes', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,1,'1313', 'Prima Adicional por Permanencia en el Servicio', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,1,'1325', 'Prima Dominical', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,1,'1326', 'Remuneraciones por Horas Extraordinarias', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,1,'1347', 'Gratificación por Productividad', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,1,'1348', 'Estudios Superiores', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,1,'1442', 'Cuotas para Fondo de Retiro', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,1,'1523', 'Prima por Jubilación', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,1,'1552', 'Días Cívicos y Económicos', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,1,'1553', 'Día del Maestro y del Servidor Público', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,1,'1554', 'Otros Gastos Derivados de Convenio', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,1,'1596', 'Reconocimiento a Servidores Públicos', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,1,'1597', 'Estímulos por Puntualidad y Asistencia', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,1,'1598', 'Recompensas', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,2,'2122', 'Material de Foto, Cine y Grabación', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,2,'2123', 'Material Estadístico y Geográfico', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,2,'2172', 'Material para Identificación y Registro', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,2,'2412', 'Materias Primas y Materiales de Producción', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,2,'2413', 'Productos de Cuero, Piel, Plástico y Hule Adquiridos como Materia Prima', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,2,'2483', 'Productos de Cuero, Piel, Plástico y Hule Adquiridos como Materia Prima', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,2,'2494', 'Sustancias Químicas', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,2,'2542', 'Materiales, Accesorios y Suministros de Laboratorio', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,2,'2722', 'Artículos Deportivos', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,2,'2742', 'Prendas de Protección', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,2,'2932', 'Refacciones y Accesorios Menores de Equipo e Instrumental Médico y de Laboratorio', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,2,'2981', 'Refacciones y Accesorios Menores de Maquinaria y Otros Equipos', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,2,'2982', 'Medidores de Agua', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3121', 'Gas', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3253', 'Arrendamiento de Maquinaria y Equipo', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3312', 'Servicios Informáticos', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3342', 'Servicios de Investigación Cientifica y Desarrollo', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3412', 'Seguros de Responsabilidad Patrimonial y Fianzas', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3532', 'Reparación y Mantenimiento para Equipo y Redes de Tele y Radio Transmisión', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3533', 'Reparación, Instalación y Mantenimiento de Equipo Médico y de Laboratorio', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3613', 'Servicios de Creatividad, Preproducción y Producción de Públicidad, Excepto Internet', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3642', 'Servicios de Cine y Grabación', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3643', 'Otros Servicios de Información', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3771', 'Pasajes Marítimos, Lacustres y Fluviales', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3772', 'Autotransporte', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3773', 'Viáticos en el País', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3774', 'Viáticos en el Extranjero', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3775', 'Gastos de Instalación y Traslado de Menaje', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3776', 'Servicios Integrales de Traslado y Viáticos', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3822', 'Espectáculos Cívicos y Culturales', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3841', 'Exposiciones y Ferias', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3842', 'Gastos de Representación', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3843', 'Servicios Funerarios y de Cementerios', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3996', 'Estudios y Análisis Clínicos', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,3,'3997', 'Subcontratación de Servicios con Terceros', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,4,'4431', 'Gastos Relacionados con Actividades Culturales, Deportivas y de Ayuda Extraordinaria', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,4,'4433', 'Gastos por Servicios de Traslado de Personas', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,4,'4434', 'Donativos a Instituciones sin Fines de Lucro', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,4,'4435', 'Donativos a Entidades Federativas', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,5,'5192', 'Otros Equipos Eléctricos y Electrónicos de Oficina', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,5,'5292', 'Equipo Médico y de Laboratorio', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,5,'5412', 'Vehículos y Equipo Auxiliar de Transporte', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,5,'5413', 'Maquinaria y Equipo Industrial', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,6,'6111', 'Convenios y Aportaciones', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,6,'6112', 'Convenios y Aportaciones', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,6,'6113', 'Ejecución de Obras por Administración', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(1,9,'9912', 'Por Ejercicios Anteriores', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(2,10,'9991', 'Ramo 33', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(2,10,'9992', 'Ramo 23', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(2,10,'9993', 'Programa de Acciones para el Desarrollo (PAD)', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00) ")
                .append(") AS TEMP (PRINCIPAL,GRUPO, CONCEPTO, PARTIDA, AUTORIZADO, AMPLIACION, REDUCCIONB, TOTAL, EJERCIDO, IMPORTE) ")
                .append("ORDER BY PARTIDA");

        System.out.println(sql);
        return sql.toString();
    }

	@Override
	public String getQueryIngresos(Integer idSector) {
		StringBuilder sql = new StringBuilder();
		/**
		 * Query para 2023
		 */
//		sql.append("SELECT 1 PRINCIPAL, ")
//		.append("CASE  ")
//		.append("WHEN ORDEN = 1 THEN 1 ")
//		.append("WHEN ORDEN = 3 THEN 2 ")
//		.append("ELSE 3 END GRUPO, ")
//		.append("ORDEN,  ")
//		.append("CASE  ")
//		.append("WHEN ORDEN = 1 THEN 'Ingresos por Venta de Bienes y Servicios' ")
//		.append("WHEN ORDEN = 3 THEN 'Subsidio' ")
//		.append("WHEN ORDEN = 4 THEN 'Productos' ")
//		.append("WHEN ORDEN = 5 THEN 'Pasivos que se Generen como Resultado de Erogaciones que se Devengan en el Ejercicio Fiscal pero que queden Pendientes de Liquidar al Cierre del Mismo' ")
//		.append("ELSE 'Ingresos Diversos' END CONCEPTO, ")
//		.append("AUTORIZADO, AMPLIACION, REDU REDUCCION, TOTAL, RECAUDADO EJERCIDO, IMPORTE ")
//		.append("FROM ( ")
//		.append("SELECT ORDEN, ")
//		.append("AUTORIZADO, ")
//		.append("CASE WHEN ORDEN = 6 THEN ")
//		.append("(SELECT DI.CARGOS_1+DI.CARGOS_2+DI.CARGOS_3+DI.CARGOS_4+DI.CARGOS_5+DI.CARGOS_6+DI.CARGOS_7+DI.CARGOS_8+DI.CARGOS_9+DI.CARGOS_10+DI.CARGOS_11+DI.CARGOS_12 ")
//		.append("FROM CUENTA DI ")
//		.append("WHERE CUENTA = '8110' ")
//		.append("AND SUBSTR(SCTA,7,4) = '4399' AND SSCTA = '') ")
//		.append("ELSE AMPLI END AMPLIACION, ")
//		.append("REDU, ")
//		.append("CASE WHEN ORDEN = 6 THEN ")
//		.append("AUTORIZADO+(SELECT DI.CARGOS_1+DI.CARGOS_2+DI.CARGOS_3+DI.CARGOS_4+DI.CARGOS_5+DI.CARGOS_6+DI.CARGOS_7+DI.CARGOS_8+DI.CARGOS_9+DI.CARGOS_10+DI.CARGOS_11+DI.CARGOS_12 ")
//		.append("FROM CUENTA DI ")
//		.append("WHERE CUENTA = '8110' ")
//		.append("AND SUBSTR(SCTA,7,4) = '4399' AND SSCTA = '')-REDU ")
//		.append("ELSE TOTAL END TOTAL, RECAUDADO, ")
//		.append("CASE WHEN ORDEN = 6 THEN ")
//		.append("(AUTORIZADO+(SELECT DI.CARGOS_1+DI.CARGOS_2+DI.CARGOS_3+DI.CARGOS_4+DI.CARGOS_5+DI.CARGOS_6+DI.CARGOS_7+DI.CARGOS_8+DI.CARGOS_9+DI.CARGOS_10+DI.CARGOS_11+DI.CARGOS_12 ")
//		.append("FROM CUENTA DI ")
//		.append("WHERE CUENTA = '8110' ")
//		.append("AND SUBSTR(SCTA,7,4) = '4399' AND SSCTA = '')-REDU)-(RECAUDADO) ")
//		.append("ELSE IMPORTE END IMPORTE ")
//		.append("FROM ( ")
//		.append("SELECT ORDEN, ")
//		.append("SUM(AUTORIZADO) AUTORIZADO, ")
//		.append("SUM(AMPLI) AMPLI, ")
//		.append("SUM(REDU) REDU, ")
//		.append("SUM(TOTAL) TOTAL, ")
//		.append("SUM(RECAUDADO) RECAUDADO, ")
//		.append("SUM(IMPORTE) IMPORTE ")
//		.append("FROM( ")
//		.append("SELECT ")
//		.append("CASE  ")
//		.append("WHEN DI_SCTA = 9 AND DI_SSSSCTA = 21 THEN 1 ")
//		.append("WHEN DI_SCTA = 3 THEN 3 ")
//		.append("WHEN DI_SCTA = 1 THEN 4 ")
//		.append("WHEN DI_SCTA = 9 AND DI_SSSSCTA = 18 THEN 5 ")
//		.append("ELSE 6 END ORDEN, ")
//		.append("DI_SALINI AUTORIZADO, DI_CARGO AMPLI, DI_ABONO REDU,  ")
//		.append("DI_SALINI+DI_CARGO-DI_ABONO TOTAL, ")
//		.append("CI_ABONO-CI_CARGO RECAUDADO, ")
//		.append("CI_ABONO, ")
//		.append("(DI_SALINI+DI_CARGO-DI_ABONO)-(CI_ABONO-CI_CARGO) IMPORTE ")
//		.append("FROM( ")
//		.append("SELECT  ")
//		.append("DI.CUENTA DI_CUENTA,  ")
//		.append("SUBSTR(DI.SCTA,10) DI_SCTA,  ")
//		.append("SUBSTR(DI.SSCTA,17) DI_SSCTA, ")
//		.append("SUBSTR(DI.SSSCTA,3) DI_SSSCTA, ")
//		.append("SUBSTR(DI.SSSSCTA,2) DI_SSSSCTA, ")
//		.append("SUBSTR(DI.NOMCTA,1,100) DI_NOMCTA,  ")
//		.append("DI.SALINI DI_SALINI, ")
//		.append("DI.CARGOS_1+DI.CARGOS_2+DI.CARGOS_3+DI.CARGOS_4+DI.CARGOS_5+DI.CARGOS_6+DI.CARGOS_7+DI.CARGOS_8+DI.CARGOS_9+DI.CARGOS_10+DI.CARGOS_11+DI.CARGOS_12 DI_CARGO,  ")
//		.append("DI.ABONOS_1+DI.ABONOS_2+DI.ABONOS_3+DI.ABONOS_4+DI.ABONOS_5+DI.ABONOS_6+DI.ABONOS_7+DI.ABONOS_8+DI.ABONOS_9+DI.ABONOS_10+DI.ABONOS_11+DI.ABONOS_12 DI_ABONO, ")
//		.append("CI.SALINI CI_SALINI, ")
//		.append("CI.CARGOS_1+CI.CARGOS_2+CI.CARGOS_3+CI.CARGOS_4+CI.CARGOS_5+CI.CARGOS_6+CI.CARGOS_7+CI.CARGOS_8+CI.CARGOS_9+CI.CARGOS_10+CI.CARGOS_11+CI.CARGOS_12 CI_CARGO,  ")
//		.append("CI.ABONOS_1+CI.ABONOS_2+CI.ABONOS_3+CI.ABONOS_4+CI.ABONOS_5+CI.ABONOS_6+CI.ABONOS_7+CI.ABONOS_8+CI.ABONOS_9+CI.ABONOS_10+CI.ABONOS_11+CI.ABONOS_12 CI_ABONO, ")
//		.append("CU.SALINI CU_SALINI, ")
//		.append("CU.CARGOS_1+CU.CARGOS_2+CU.CARGOS_3+CU.CARGOS_4+CU.CARGOS_5+CU.CARGOS_6+CU.CARGOS_7+CU.CARGOS_8+CU.CARGOS_9+CU.CARGOS_10+CU.CARGOS_11+CU.CARGOS_12 CU_CARGO,  ")
//		.append("CU.ABONOS_1+CU.ABONOS_2+CU.ABONOS_3+CU.ABONOS_4+CU.ABONOS_5+CU.ABONOS_6+CU.ABONOS_7+CU.ABONOS_8+CU.ABONOS_9+CU.ABONOS_10+CU.ABONOS_11+CU.ABONOS_12 CU_ABONO ")
//		.append("FROM  ")
//		.append("CUENTA AS DI ")
//		.append("INNER JOIN CUENTA AS CI ON CI.CUENTA='8150' AND CI.SCTA=DI.SCTA AND CI.SSCTA=DI.SSCTA AND CI.SSSCTA=DI.SSSCTA AND CI.SSSSCTA=DI.SSSSCTA AND CI.IDSECTOR = DI.IDSECTOR ")
//		.append("INNER JOIN CUENTA AS CU ON CU.CUENTA='8140' AND CU.SCTA=DI.SCTA AND CU.SSCTA=DI.SSCTA AND CU.SSSCTA=DI.SSSCTA AND CU.SSSSCTA=DI.SSSSCTA AND CU.IDSECTOR = DI.IDSECTOR ")
//		.append("WHERE (DI.CUENTA='8110') AND DI.NOTCTA = 0 AND DI.IDSECTOR = ").append(idSector)
//		.append(" AND (DI.SALINI <> 0 OR DI.CARGOS_1+DI.CARGOS_2+DI.CARGOS_3+DI.CARGOS_4+DI.CARGOS_5+DI.CARGOS_6+DI.CARGOS_7+DI.CARGOS_8+DI.CARGOS_9+DI.CARGOS_10+DI.CARGOS_11+DI.CARGOS_12 <> 0 OR DI.ABONOS_1+DI.ABONOS_2+DI.ABONOS_3+DI.ABONOS_4+DI.ABONOS_5+DI.ABONOS_6+DI.ABONOS_7+DI.ABONOS_8+DI.ABONOS_9+DI.ABONOS_10+DI.ABONOS_11+DI.ABONOS_12 <> 0 ")
//		.append("OR   CU.SALINI <> 0 OR CU.CARGOS_1+CU.CARGOS_2+CU.CARGOS_3+CU.CARGOS_4+CU.CARGOS_5+CU.CARGOS_6+CU.CARGOS_7+CU.CARGOS_8+CU.CARGOS_9+CU.CARGOS_10+CU.CARGOS_11+CU.CARGOS_12 <> 0 OR CU.ABONOS_1+CU.ABONOS_2+CU.ABONOS_3+CU.ABONOS_4+CU.ABONOS_5+CU.ABONOS_6+CU.ABONOS_7+CU.ABONOS_8+CU.ABONOS_9+CU.ABONOS_10+CU.ABONOS_11+CU.ABONOS_12 <> 0 ")
//		.append("OR   CI.SALINI <> 0 OR CI.CARGOS_1+CI.CARGOS_2+CI.CARGOS_3+CI.CARGOS_4+CI.CARGOS_5+CI.CARGOS_6+CI.CARGOS_7+CI.CARGOS_8+CI.CARGOS_9+CI.CARGOS_10+CI.CARGOS_11+CI.CARGOS_12 <> 0 OR CI.ABONOS_1+CI.ABONOS_2+CI.ABONOS_3+CI.ABONOS_4+CI.ABONOS_5+CI.ABONOS_6+CI.ABONOS_7+CI.ABONOS_8+CI.ABONOS_9+CI.ABONOS_10+CI.ABONOS_11+CI.ABONOS_12 <> 0 ) ")
//		.append("AND((SUBSTR(DI.SCTA,7,4) = '4223' AND DI.SSSCTA = '0001' AND DI.SSSSCTA = '') ")
//		.append("OR (SUBSTR(DI.SCTA,7,4) = '4311' AND DI.SSCTA = '' ) ")
//		.append("OR (SUBSTR(DI.SCTA,7,4) = '4399' AND DI.SSSSCTA IN ('001','002','005','006','008','012','016','018','021') ) ) ")
//		.append("ORDER BY DI.CUENTA,DI.SCTA, DI.SSCTA, DI.SSSCTA, DI.SSSSCTA ) ")
//		.append("ORDER BY 1) ")
//		.append("GROUP BY ORDEN) ) ")
//		.append("UNION ALL ")
//		.append("SELECT * FROM ( VALUES ")
//		.append("(1, 1, 2, 'Derechos', 0,0,0,0,0,0), ")
//		.append("(2, 4, 7, 'PAD', 0,0,0,0,0,0), ")
//		.append("(2, 4, 8, 'Ramo 33', 0,0,0,0,0,0), ")
//		.append("(2, 4, 9, 'Ramo 23', 0,0,0,0,0,0) ")
//		.append(") AS TEMP (PRINCIPAL, GRUPO, ORDEN, CONCEPTO, AUTORIZADO, AMPLIACION, REDUCCION, TOTAL, EJERCIDO, IMPORTE) ")
//		.append("ORDER BY ORDEN");
		
		/**
		 * Query para 2024
		 */
		sql.append("SELECT 1 PRINCIPAL, ")
		.append("CASE  ")
		.append("WHEN ORDEN = 1 THEN 1 ")
		.append("WHEN ORDEN = 3 THEN 2 ")
		.append("ELSE 3 END GRUPO, ")
		.append("ORDEN,  ")
		.append("CASE  ")
		.append("WHEN ORDEN = 1 THEN 'Ingresos por Venta de Bienes y Servicios' ")
		.append("WHEN ORDEN = 3 THEN 'Subsidio' ")
		.append("WHEN ORDEN = 4 THEN 'Productos' ")
		.append("WHEN ORDEN = 5 THEN 'Pasivos que se Generen como Resultado de Erogaciones que se Devengan en el Ejercicio Fiscal pero que queden Pendientes de Liquidar al Cierre del Mismo' ")
		.append("ELSE 'Ingresos Diversos' END CONCEPTO, ")
		.append("AUTORIZADO, AMPLIACION, REDU REDUCCION, TOTAL, RECAUDADO EJERCIDO, IMPORTE ")
		.append("FROM ( ")
		.append("SELECT ORDEN, AUTORIZADO, ")
		.append("CASE WHEN ORDEN = 6 THEN ")
		.append("(SELECT DI.CARGOS_1+DI.CARGOS_2+DI.CARGOS_3+DI.CARGOS_4+DI.CARGOS_5+DI.CARGOS_6+DI.CARGOS_7+DI.CARGOS_8+DI.CARGOS_9+DI.CARGOS_10+DI.CARGOS_11+DI.CARGOS_12 ")
		.append("FROM CUENTA DI ")
		.append("WHERE CUENTA = '8110' ")
		.append("AND SUBSTR(SCTA,12,4) = '4399' AND SSCTA = '') ")
		.append("ELSE AMPLI END AMPLIACION, ")
		.append("REDU, ")
		.append("CASE WHEN ORDEN = 6 THEN ")
		.append("AUTORIZADO+(SELECT DI.CARGOS_1+DI.CARGOS_2+DI.CARGOS_3+DI.CARGOS_4+DI.CARGOS_5+DI.CARGOS_6+DI.CARGOS_7+DI.CARGOS_8+DI.CARGOS_9+DI.CARGOS_10+DI.CARGOS_11+DI.CARGOS_12 ")
		.append("FROM CUENTA DI ")
		.append("WHERE CUENTA = '8110' ")
		.append("AND SUBSTR(SCTA,12,4) = '4399' AND SSCTA = '')-REDU ")
		.append("ELSE TOTAL END TOTAL, RECAUDADO, ")
		.append("CASE WHEN ORDEN = 6 THEN ")
		.append("(AUTORIZADO+(SELECT DI.CARGOS_1+DI.CARGOS_2+DI.CARGOS_3+DI.CARGOS_4+DI.CARGOS_5+DI.CARGOS_6+DI.CARGOS_7+DI.CARGOS_8+DI.CARGOS_9+DI.CARGOS_10+DI.CARGOS_11+DI.CARGOS_12 ")
		.append("FROM CUENTA DI ")
		.append("WHERE CUENTA = '8110' ")
		.append("AND SUBSTR(SCTA,12,4) = '4399' AND SSCTA = '')-REDU)-(RECAUDADO) ")
		.append("ELSE IMPORTE END IMPORTE ")
		.append("FROM ( ")
		.append("SELECT ORDEN, ")
		.append("SUM(AUTORIZADO) AUTORIZADO, ")
		.append("SUM(AMPLI) AMPLI, ")
		.append("SUM(REDU) REDU, ")
		.append("SUM(TOTAL) TOTAL, ")
		.append("SUM(RECAUDADO) RECAUDADO, ")
		.append("SUM(IMPORTE) IMPORTE ")
		.append("FROM( ")
		.append("SELECT ")
		.append("CASE  ")
		.append("WHEN DI_SCTA = 9 AND DI_SSSSCTA = 21 THEN 1 ")
		.append("WHEN DI_SCTA = 3 THEN 3 ")
		.append("WHEN DI_SCTA = 1 THEN 4 ")
		.append("WHEN DI_SCTA = 9 AND DI_SSSSCTA = 18 THEN 5 ")
		.append("ELSE 6 END ORDEN, ")
		.append("DI_SALINI AUTORIZADO, DI_CARGO AMPLI, DI_ABONO REDU,  ")
		.append("DI_SALINI+DI_CARGO-DI_ABONO TOTAL, ")
		.append("CI_ABONO-CI_CARGO RECAUDADO, ")
		.append("CI_ABONO, ")
		.append("(DI_SALINI+DI_CARGO-DI_ABONO)-(CI_ABONO-CI_CARGO) IMPORTE ")
		.append("FROM( ")
		.append("SELECT  ")
		.append("DI.CUENTA DI_CUENTA,  ")
		.append("SUBSTR(DI.SCTA,15) DI_SCTA,  ")
		.append("SUBSTR(DI.SSCTA,20) DI_SSCTA, ")
		.append("SUBSTR(DI.SSSCTA,3) DI_SSSCTA, ")
		.append("SUBSTR(DI.SSSSCTA,2) DI_SSSSCTA, ")
		.append("SUBSTR(DI.NOMCTA,1,100) DI_NOMCTA,  ")
		.append("DI.SALINI DI_SALINI, ")
		.append("DI.CARGOS_1+DI.CARGOS_2+DI.CARGOS_3+DI.CARGOS_4+DI.CARGOS_5+DI.CARGOS_6+DI.CARGOS_7+DI.CARGOS_8+DI.CARGOS_9+DI.CARGOS_10+DI.CARGOS_11+DI.CARGOS_12 DI_CARGO,  ")
		.append("DI.ABONOS_1+DI.ABONOS_2+DI.ABONOS_3+DI.ABONOS_4+DI.ABONOS_5+DI.ABONOS_6+DI.ABONOS_7+DI.ABONOS_8+DI.ABONOS_9+DI.ABONOS_10+DI.ABONOS_11+DI.ABONOS_12 DI_ABONO, ")
		.append("CI.SALINI CI_SALINI, ")
		.append("CI.CARGOS_1+CI.CARGOS_2+CI.CARGOS_3+CI.CARGOS_4+CI.CARGOS_5+CI.CARGOS_6+CI.CARGOS_7+CI.CARGOS_8+CI.CARGOS_9+CI.CARGOS_10+CI.CARGOS_11+CI.CARGOS_12 CI_CARGO,  ")
		.append("CI.ABONOS_1+CI.ABONOS_2+CI.ABONOS_3+CI.ABONOS_4+CI.ABONOS_5+CI.ABONOS_6+CI.ABONOS_7+CI.ABONOS_8+CI.ABONOS_9+CI.ABONOS_10+CI.ABONOS_11+CI.ABONOS_12 CI_ABONO, ")
		.append("CU.SALINI CU_SALINI, ")
		.append("CU.CARGOS_1+CU.CARGOS_2+CU.CARGOS_3+CU.CARGOS_4+CU.CARGOS_5+CU.CARGOS_6+CU.CARGOS_7+CU.CARGOS_8+CU.CARGOS_9+CU.CARGOS_10+CU.CARGOS_11+CU.CARGOS_12 CU_CARGO,  ")
		.append("CU.ABONOS_1+CU.ABONOS_2+CU.ABONOS_3+CU.ABONOS_4+CU.ABONOS_5+CU.ABONOS_6+CU.ABONOS_7+CU.ABONOS_8+CU.ABONOS_9+CU.ABONOS_10+CU.ABONOS_11+CU.ABONOS_12 CU_ABONO ")
		.append("FROM  ")
		.append("CUENTA AS DI ")
		.append("INNER JOIN CUENTA AS CI ON CI.CUENTA='8150' AND CI.SCTA=DI.SCTA AND CI.SSCTA=DI.SSCTA AND CI.SSSCTA=DI.SSSCTA AND CI.SSSSCTA=DI.SSSSCTA AND CI.IDSECTOR = DI.IDSECTOR ")
		.append("INNER JOIN CUENTA AS CU ON CU.CUENTA='8140' AND CU.SCTA=DI.SCTA AND CU.SSCTA=DI.SSCTA AND CU.SSSCTA=DI.SSSCTA AND CU.SSSSCTA=DI.SSSSCTA AND CU.IDSECTOR = DI.IDSECTOR ")
		.append("WHERE (DI.CUENTA='8110') AND DI.NOTCTA=0 AND DI.IDSECTOR = ").append(idSector)
		.append(" AND (DI.SALINI <> 0 OR DI.CARGOS_1+DI.CARGOS_2+DI.CARGOS_3+DI.CARGOS_4+DI.CARGOS_5+DI.CARGOS_6+DI.CARGOS_7+DI.CARGOS_8+DI.CARGOS_9+DI.CARGOS_10+DI.CARGOS_11+DI.CARGOS_12 <> 0 OR DI.ABONOS_1+DI.ABONOS_2+DI.ABONOS_3+DI.ABONOS_4+DI.ABONOS_5+DI.ABONOS_6+DI.ABONOS_7+DI.ABONOS_8+DI.ABONOS_9+DI.ABONOS_10+DI.ABONOS_11+DI.ABONOS_12 <> 0 ")
		.append("OR   CU.SALINI <> 0 OR CU.CARGOS_1+CU.CARGOS_2+CU.CARGOS_3+CU.CARGOS_4+CU.CARGOS_5+CU.CARGOS_6+CU.CARGOS_7+CU.CARGOS_8+CU.CARGOS_9+CU.CARGOS_10+CU.CARGOS_11+CU.CARGOS_12 <> 0 OR CU.ABONOS_1+CU.ABONOS_2+CU.ABONOS_3+CU.ABONOS_4+CU.ABONOS_5+CU.ABONOS_6+CU.ABONOS_7+CU.ABONOS_8+CU.ABONOS_9+CU.ABONOS_10+CU.ABONOS_11+CU.ABONOS_12 <> 0 ")
		.append("OR   CI.SALINI <> 0 OR CI.CARGOS_1+CI.CARGOS_2+CI.CARGOS_3+CI.CARGOS_4+CI.CARGOS_5+CI.CARGOS_6+CI.CARGOS_7+CI.CARGOS_8+CI.CARGOS_9+CI.CARGOS_10+CI.CARGOS_11+CI.CARGOS_12 <> 0 OR CI.ABONOS_1+CI.ABONOS_2+CI.ABONOS_3+CI.ABONOS_4+CI.ABONOS_5+CI.ABONOS_6+CI.ABONOS_7+CI.ABONOS_8+CI.ABONOS_9+CI.ABONOS_10+CI.ABONOS_11+CI.ABONOS_12 <> 0 ) ")
		.append("AND((SUBSTR(DI.SCTA,12,4) = '4223' AND DI.SSSCTA = '0001' AND DI.SSSSCTA = '') ")
		.append("OR (SUBSTR(DI.SCTA,12,4) = '4311' AND DI.SSCTA = '' ) ")
		.append("OR (SUBSTR(DI.SCTA,12,4) = '4399' AND DI.SSSSCTA IN ('001','002','005','006','008','012','016','018','021') ) ) ")
		.append("ORDER BY DI.CUENTA,DI.SCTA, DI.SSCTA, DI.SSSCTA, DI.SSSSCTA ) ")
		.append("ORDER BY 1) ")
		.append("GROUP BY ORDEN) ) ")
		.append("UNION ALL ")
		.append("SELECT * FROM ( VALUES ")
		.append("(1, 1, 2, 'Derechos', 0,0,0,0,0,0), ")
		.append("(2, 4, 7, 'PAD', 0,0,0,0,0,0), ")
		.append("(2, 4, 8, 'Ramo 33', 0,0,0,0,0,0), ")
		.append("(2, 4, 9, 'Ramo 23', 0,0,0,0,0,0) ")
		.append(") AS TEMP (PRINCIPAL, GRUPO, ORDEN, CONCEPTO, AUTORIZADO, AMPLIACION, REDUCCION, TOTAL, EJERCIDO, IMPORTE) ")
		.append("ORDER BY ORDEN");
		
		System.out.println(sql);
		return sql.toString();
	}	


    @Override
    public String getQueryClasAdminEntidades(Integer idSector) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT 1 ORDEN, T1.CONCEPTO, ")
                .append("SUM(T1.APROBADO) APROBADO, SUM(T1.AMPLIACION - T1.REDUCCION) AMPL_REDU, ")
                .append("SUM(T1.APROBADO + T1.AMPLIACION - T1.REDUCCION) MODIFICADO, ")
                .append("SUM(T1.COMPROMETIDO + T1.DEVENGADO + T1.PAGADO) DEVENGADO, ")
                .append("SUM(T1.PAGADO) PAGADO, ")
                .append("SUM(((T1.APROBADO + T1.AMPLIACION - T1.REDUCCION) - (T1.COMPROMETIDO + T1.DEVENGADO + T1.PAGADO))) SUBEJERCICIO ")
                .append("FROM ( ")
                .append("SELECT '1000' PARTIDA, ")
                .append("'Entidades Paraestatales y Fideicomisos No Empresariales y No Financieros' CONCEPTO, ")
                .append("SUM( P.AUTO1_1 +P.AUTO1_2 +P.AUTO1_3 +P.AUTO1_4 +P.AUTO1_5 +P.AUTO1_6 +P.AUTO1_7 +P.AUTO1_8 +P.AUTO1_9 +P.AUTO1_10 +P.AUTO1_11 +P.AUTO1_12) APROBADO, ")
                .append("SUM( P.AMPLI1_1 + P.AMPLI1_2 + P.AMPLI1_3 + P.AMPLI1_4 + P.AMPLI1_5 + P.AMPLI1_6 + P.AMPLI1_7 + P.AMPLI1_8 + P.AMPLI1_9 + P.AMPLI1_10 + P.AMPLI1_11 + P.AMPLI1_12) AMPLIACION, ")
                .append("SUM( P.REDU1_1 + P.REDU1_2 + P.REDU1_3 + P.REDU1_4 + P.REDU1_5 + P.REDU1_6 + P.REDU1_7 + P.REDU1_8 + P.REDU1_9 + P.REDU1_10 + P.REDU1_11 + P.REDU1_12) REDUCCION, ")
                .append("SUM( P.EJPA1_1 + P.EJPA1_2 + P.EJPA1_3 + P.EJPA1_4 + P.EJPA1_5 + P.EJPA1_6 + P.EJPA1_7 + P.EJPA1_8 + P.EJPA1_9 + P.EJPA1_10 + P.EJPA1_11 + P.EJPA1_12) PAGADO, ")
                .append("SUM( P.EJXPA1_1 + P.EJXPA1_2 + P.EJXPA1_3 + P.EJXPA1_4 + P.EJXPA1_5 + P.EJXPA1_6 + P.EJXPA1_7 + P.EJXPA1_8 + P.EJXPA1_9 + P.EJXPA1_10 + P.EJXPA1_11 + P.EJXPA1_12) DEVENGADO, ")
                .append("SUM( P.TOEJE1_1 + P.TOEJE1_2 + P.TOEJE1_3 + P.TOEJE1_4 + P.TOEJE1_5 + P.TOEJE1_6 + P.TOEJE1_7 + P.TOEJE1_8 + P.TOEJE1_9 + P.TOEJE1_10 + P.TOEJE1_11 + P.TOEJE1_12) EJERCIDO, ")
                .append("SUM( P.COMPRO1_1 + P.COMPRO1_2 + P.COMPRO1_3 + P.COMPRO1_4 + P.COMPRO1_5 + P.COMPRO1_6 + P.COMPRO1_7 + P.COMPRO1_8 + P.COMPRO1_9 + P.COMPRO1_10 + P.COMPRO1_11 + P.COMPRO1_12) COMPROMETIDO ")
                .append("FROM PASO P ")
                .append("INNER JOIN NATGAS N ON P.PARTIDA=N.CLVGAS AND P.IDSECTOR=N.IDSECTOR AND N.IDSECTOR=P.IDSECTOR ")
                .append("WHERE P.IDSECTOR = ").append(idSector)
                .append(" AND SUBSTR(P.PARTIDA,2,3) = '000' ")
                .append("GROUP BY N.NOMGAS,P.PARTIDA ORDER BY P.PARTIDA)T1 ")
                .append("GROUP BY T1.PARTIDA, T1.CONCEPTO ")
                .append("UNION ALL ")
                .append("SELECT * FROM ( VALUES ")
                .append("(2, 'Instituciones Públicas de Seguridad Social', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(3, 'Entidades Paraestatales Empresariales No Financieras con Participación Estatal Mayoritaria', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(4, 'Fideicomisos Empresariales No Financieros con Participación Estatal Mayoritaria', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(5, 'Entidades Paraestatales Empresariales Financieras Monetarias con Participación Estatal Mayoritaria', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(6, 'Entidades Paraestatales Empresariales Financieras No Monetarias con Participación Estatal Mayoritaria', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(7, 'Fideicomisos Financieros Públicos con Participación Estatal Mayoritaria', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00) ")
                .append(") AS TEMP (ORDEN, CONCEPTO, APROBADO, AMPL_REDU, MODIFICADO, DEVENGADO, PAGADO, SUBEJERCICIO) ")
                .append("ORDER BY ORDEN");

        System.out.println(sql);
        return sql.toString();
    }

    @Override
    public String getQueryClasAdminDependencia(Integer idSector) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT 409 CLAVE, T1.CONCEPTO, ")
                .append("SUM(T1.APROBADO) APROBADO, SUM(T1.AMPLIACION - T1.REDUCCION) AMPL_REDU, ")
                .append("SUM(T1.APROBADO + T1.AMPLIACION - T1.REDUCCION) MODIFICADO, ")
                .append("SUM(T1.COMPROMETIDO + T1.DEVENGADO + T1.PAGADO) DEVENGADO, ")
                .append("SUM(T1.PAGADO) PAGADO, ")
                .append("SUM(((T1.APROBADO + T1.AMPLIACION - T1.REDUCCION) - (T1.COMPROMETIDO + T1.DEVENGADO + T1.PAGADO))) SUBEJERCICIO ")
                .append("FROM ( ")
                .append("SELECT '1000' PARTIDA, ")
                .append("'Instituto de Transparencia, Acceso a la Información Pública y Protección de Datos Personales del Estado de México y Municipios' CONCEPTO, ")
                .append("SUM( P.AUTO1_1 +P.AUTO1_2 +P.AUTO1_3 +P.AUTO1_4 +P.AUTO1_5 +P.AUTO1_6 +P.AUTO1_7 +P.AUTO1_8 +P.AUTO1_9 +P.AUTO1_10 +P.AUTO1_11 +P.AUTO1_12) APROBADO, ")
                .append("SUM( P.AMPLI1_1 + P.AMPLI1_2 + P.AMPLI1_3 + P.AMPLI1_4 + P.AMPLI1_5 + P.AMPLI1_6 + P.AMPLI1_7 + P.AMPLI1_8 + P.AMPLI1_9 + P.AMPLI1_10 + P.AMPLI1_11 + P.AMPLI1_12) AMPLIACION, ")
                .append("SUM( P.REDU1_1 + P.REDU1_2 + P.REDU1_3 + P.REDU1_4 + P.REDU1_5 + P.REDU1_6 + P.REDU1_7 + P.REDU1_8 + P.REDU1_9 + P.REDU1_10 + P.REDU1_11 + P.REDU1_12) REDUCCION, ")
                .append("SUM( P.EJPA1_1 + P.EJPA1_2 + P.EJPA1_3 + P.EJPA1_4 + P.EJPA1_5 + P.EJPA1_6 + P.EJPA1_7 + P.EJPA1_8 + P.EJPA1_9 + P.EJPA1_10 + P.EJPA1_11 + P.EJPA1_12) PAGADO, ")
                .append("SUM( P.EJXPA1_1 + P.EJXPA1_2 + P.EJXPA1_3 + P.EJXPA1_4 + P.EJXPA1_5 + P.EJXPA1_6 + P.EJXPA1_7 + P.EJXPA1_8 + P.EJXPA1_9 + P.EJXPA1_10 + P.EJXPA1_11 + P.EJXPA1_12) DEVENGADO, ")
                .append("SUM( P.TOEJE1_1 + P.TOEJE1_2 + P.TOEJE1_3 + P.TOEJE1_4 + P.TOEJE1_5 + P.TOEJE1_6 + P.TOEJE1_7 + P.TOEJE1_8 + P.TOEJE1_9 + P.TOEJE1_10 + P.TOEJE1_11 + P.TOEJE1_12) EJERCIDO, ")
                .append("SUM( P.COMPRO1_1 + P.COMPRO1_2 + P.COMPRO1_3 + P.COMPRO1_4 + P.COMPRO1_5 + P.COMPRO1_6 + P.COMPRO1_7 + P.COMPRO1_8 + P.COMPRO1_9 + P.COMPRO1_10 + P.COMPRO1_11 + P.COMPRO1_12) COMPROMETIDO ")
                .append("FROM PASO P ")
                .append("INNER JOIN NATGAS N ON P.PARTIDA=N.CLVGAS AND P.IDSECTOR=N.IDSECTOR AND N.IDSECTOR=P.IDSECTOR ")
                .append("WHERE P.IDSECTOR = ").append(idSector)
                .append(" AND SUBSTR(P.PARTIDA,2,3) = '000' ")
                .append("GROUP BY N.NOMGAS,P.PARTIDA ORDER BY P.PARTIDA)T1 ")
                .append("GROUP BY T1.PARTIDA, T1.CONCEPTO");

        System.out.println(sql);
        return sql.toString();
    }

    @Override
    public String getQueryClasAdminPoderOrg(Integer idSector) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT 4 ORDEN, T1.CONCEPTO, ")
                .append("SUM(T1.APROBADO) APROBADO, SUM(T1.AMPLIACION - T1.REDUCCION) AMPL_REDU, ")
                .append("SUM(T1.APROBADO + T1.AMPLIACION - T1.REDUCCION) MODIFICADO, ")
                .append("SUM(T1.COMPROMETIDO + T1.DEVENGADO + T1.PAGADO) DEVENGADO, ")
                .append("SUM(T1.PAGADO) PAGADO, ")
                .append("SUM(((T1.APROBADO + T1.AMPLIACION - T1.REDUCCION) - (T1.COMPROMETIDO + T1.DEVENGADO + T1.PAGADO))) SUBEJERCICIO ")
                .append("FROM ( ")
                .append("SELECT '1000' PARTIDA, ")
                .append("'Órganos Autónomos' CONCEPTO, ")
                .append("SUM( P.AUTO1_1 +P.AUTO1_2 +P.AUTO1_3 +P.AUTO1_4 +P.AUTO1_5 +P.AUTO1_6 +P.AUTO1_7 +P.AUTO1_8 +P.AUTO1_9 +P.AUTO1_10 +P.AUTO1_11 +P.AUTO1_12) APROBADO, ")
                .append("SUM( P.AMPLI1_1 + P.AMPLI1_2 + P.AMPLI1_3 + P.AMPLI1_4 + P.AMPLI1_5 + P.AMPLI1_6 + P.AMPLI1_7 + P.AMPLI1_8 + P.AMPLI1_9 + P.AMPLI1_10 + P.AMPLI1_11 + P.AMPLI1_12) AMPLIACION, ")
                .append("SUM( P.REDU1_1 + P.REDU1_2 + P.REDU1_3 + P.REDU1_4 + P.REDU1_5 + P.REDU1_6 + P.REDU1_7 + P.REDU1_8 + P.REDU1_9 + P.REDU1_10 + P.REDU1_11 + P.REDU1_12) REDUCCION, ")
                .append("SUM( P.EJPA1_1 + P.EJPA1_2 + P.EJPA1_3 + P.EJPA1_4 + P.EJPA1_5 + P.EJPA1_6 + P.EJPA1_7 + P.EJPA1_8 + P.EJPA1_9 + P.EJPA1_10 + P.EJPA1_11 + P.EJPA1_12) PAGADO, ")
                .append("SUM( P.EJXPA1_1 + P.EJXPA1_2 + P.EJXPA1_3 + P.EJXPA1_4 + P.EJXPA1_5 + P.EJXPA1_6 + P.EJXPA1_7 + P.EJXPA1_8 + P.EJXPA1_9 + P.EJXPA1_10 + P.EJXPA1_11 + P.EJXPA1_12) DEVENGADO, ")
                .append("SUM( P.TOEJE1_1 + P.TOEJE1_2 + P.TOEJE1_3 + P.TOEJE1_4 + P.TOEJE1_5 + P.TOEJE1_6 + P.TOEJE1_7 + P.TOEJE1_8 + P.TOEJE1_9 + P.TOEJE1_10 + P.TOEJE1_11 + P.TOEJE1_12) EJERCIDO, ")
                .append("SUM( P.COMPRO1_1 + P.COMPRO1_2 + P.COMPRO1_3 + P.COMPRO1_4 + P.COMPRO1_5 + P.COMPRO1_6 + P.COMPRO1_7 + P.COMPRO1_8 + P.COMPRO1_9 + P.COMPRO1_10 + P.COMPRO1_11 + P.COMPRO1_12) COMPROMETIDO ")
                .append("FROM PASO P ")
                .append("INNER JOIN NATGAS N ON P.PARTIDA=N.CLVGAS AND P.IDSECTOR=N.IDSECTOR AND N.IDSECTOR=P.IDSECTOR ")
                .append("WHERE P.IDSECTOR = ").append(idSector)
                .append(" AND SUBSTR(P.PARTIDA,2,3) = '000' ")
                .append("GROUP BY N.NOMGAS,P.PARTIDA ORDER BY P.PARTIDA)T1 ")
                .append("GROUP BY T1.PARTIDA, T1.CONCEPTO ")
                .append("UNION ALL ")
                .append("SELECT * FROM ( VALUES ")
                .append("(1, 'Poder Ejecutivo', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(2, 'Poder Legislativo', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00), ")
                .append("(3, 'Poder Judicial', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00) ")
                .append(") AS TEMP (ORDEN, CONCEPTO, APROBADO, AMPL_REDU, MODIFICADO, DEVENGADO, PAGADO, SUBEJERCICIO) ")
                .append("ORDER BY ORDEN");

        System.out.println(sql);
        return sql.toString();
    }

    @Override
    public TotalIngresoEgreso getTotalEgreso() {
        String sSql = "SELECT \n" +
                "       T1.APROBADO,\n" +
                "       T1.AMPLIACION,\n" +
                "       T1.EJERCIDO,\n" +
                "       T1.REDUCCION," +
                "       (T1.EJERCIDO - T1.APROBADO) VARIACION_INCIAL,\n" +
                "       (T1.APROBADO + T1.AMPLIACION - T1.REDUCCION) MODIFICADO\n" +
                "    FROM(SELECT\n" +
                "              \n" +
                "               SUM(PA.AUTO1_1 + PA.AUTO1_2 + PA.AUTO1_3 + PA.AUTO1_4 + PA.AUTO1_5 + PA.AUTO1_6 + PA.AUTO1_7 +\n" +
                "                   PA.AUTO1_8 + PA.AUTO1_9 + PA.AUTO1_10 + PA.AUTO1_11 + PA.AUTO1_12) APROBADO,\n" +
                "               SUM(PA.AMPLI1_1 + PA.AMPLI1_2 + PA.AMPLI1_3 + PA.AMPLI1_4 + PA.AMPLI1_5 + PA.AMPLI1_6 + PA.AMPLI1_7 +\n" +
                "                   PA.AMPLI1_8 + PA.AMPLI1_9 + PA.AMPLI1_10 + PA.AMPLI1_11 + PA.AMPLI1_12) AMPLIACION,\n" +
                "               SUM(PA.REDU1_1 + PA.REDU1_2 + PA.REDU1_3 + PA.REDU1_4 + PA.REDU1_5 + PA.REDU1_6 + PA.REDU1_7 +\n" +
                "                   PA.REDU1_8 + PA.REDU1_9 + PA.REDU1_10 + PA.REDU1_11 + PA.REDU1_12) REDUCCION,\n" +
                "               SUM(PA.TOEJE1_1 + PA.TOEJE1_2 + PA.TOEJE1_3 + PA.TOEJE1_4 + PA.TOEJE1_5 + PA.TOEJE1_6 + PA.TOEJE1_7 +    PA.TOEJE1_8 + PA.TOEJE1_9 + PA.TOEJE1_10 + PA.TOEJE1_11 + PA.TOEJE1_12) EJERCIDO\n" +
                "    FROM PASO PA\n" +
                "WHERE PA.PARTIDA IN ( '1000' ,'2000', '3000', '4000', '5000', '9000')\n" +
                "\n" +
                "    ) T1";
        TotalIngresoEgreso total = this.jdbcTemplate.queryForObject(sSql, new RowMapper<TotalIngresoEgreso>() {

            @Override
            public TotalIngresoEgreso mapRow(ResultSet rs, int rowNum) throws SQLException {
                TotalIngresoEgreso totalAux = new TotalIngresoEgreso();
                totalAux.setAutotizado(rs.getBigDecimal("APROBADO"));
                totalAux.setAmpliacion(rs.getBigDecimal("AMPLIACION"));
                totalAux.setReduccion(rs.getBigDecimal("REDUCCION"));
                totalAux.setVariacionInicial(rs.getBigDecimal("VARIACION_INCIAL"));
                totalAux.setModifcado(rs.getBigDecimal("MODIFICADO"));
                totalAux.setEjercido(rs.getBigDecimal("EJERCIDO"));
                return totalAux;
            }
        });
        return total;
    }

    public ConctbRepository getConctbRepository() {
        return conctbRepository;
    }

    public void setConctbRepository(ConctbRepository conctbRepository) {
        this.conctbRepository = conctbRepository;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
}
