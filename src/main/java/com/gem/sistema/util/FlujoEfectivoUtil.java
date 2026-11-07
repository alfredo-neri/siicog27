package com.gem.sistema.util;


import java.util.HashMap;
import java.util.Map;

public class FlujoEfectivoUtil {
    private static final String UNION_ALL = " UNION ALL ";
    private static final String SORTED_ASC = " ORDER BY 1, 2, 3 ASC ";

    private static String generaActividadesOperacionOrigen(char tipo, Integer value) {
        StringBuffer sSql = new StringBuffer();
        Integer mesTrimestre = tipo == 't' ? value * 3 : value;
        Map<String, String> cargosAbonos = getMapCounts(mesTrimestre);
        String cargos = cargosAbonos.get("cargos");
        String abonos = cargosAbonos.get("abonos");
        Integer mesTrimestreAnt = tipo == 't' ? (value - 1) * 3 : value - 1;
        Map<String, String> cargosAbonosAnt = getMapCounts(mesTrimestreAnt);
        String cargosAnt = cargosAbonosAnt.get("cargos");
        String abonosAnt = cargosAbonosAnt.get("abonos");
        sSql.append("SELECT  1 PRINCIPAL, ").append(
                "        1 GRUPO, ").append(
                "        DECODE(T1.CUENTA, '4110', '1', '4120', '2', '4130', '3', '4140', '4','4170', '5', '4310', '6', '4160', '7',  '4210', '8', '4220', '9', '4390', '99') CUENTA, ").append(
                "        FN_GET_NOMBRE_CUENTA(T1.CUENTA, T1.NOMCTA) NOMCTA, ").append(
                "        (T1.ABONOS_ANT - T1.CARGOS_ANT) SALDO_ANT, ").append(
                "        (T1.ABONOS - T1.CARGOS) SALDO_ACT ").append(
                "      FROM( ").append(
                " SELECT CU.CUENTA, ").append(
                "       MA.NOMMAY NOMCTA, ").append(
                " " + cargosAnt + "  CARGOS_ANT, ").append(
                " " + abonosAnt + "  ABONOS_ANT, ").append(
                " " + cargos + " CARGOS, ").append(
                " " + abonos + " ABONOS ").append(
                "   FROM CUENTA CU, ").append(
                "        MAYCTA MA ").append(
                " WHERE MA.CUENTA = CU.CUENTA ").append(
                "  AND SUBSTR(CU.CUENTA,1,1) =  '4' ").append(
                "  AND SUBSTR(CU.CUENTA,4,1) =  '0' ").append(
                "  AND SUBSTR(CU.CUENTA,3,1) <>  '0' ").append(
                "  AND CU.CUENTA NOT IN ('4190', '4150' , '4320', '4330', '4340', '4350') ").append(
                "   ").append(
                " ) T1");

        return sSql.toString();
    }

    private static String gengeneraActividadesOperacionAplicacion(char tipo, Integer value) {
        StringBuffer sSql = new StringBuffer();
        Integer mesTrimestre = tipo == 't' ? value * 3 : value;
        Map<String, String> cargosAbonos = getMapCounts(mesTrimestre);
        String cargos = cargosAbonos.get("cargos");
        String abonos = cargosAbonos.get("abonos");
        Integer mesTrimestreAnt = tipo == 't' ? (value - 1) * 3 : value - 1;
        Map<String, String> cargosAbonosAnt = getMapCounts(mesTrimestreAnt);
        String cargosAnt = cargosAbonosAnt.get("cargos");
        String abonosAnt = cargosAbonosAnt.get("abonos");
        sSql.append("SELECT T2.PRINCIPAL, ").append(
                "       T2.GRUPO, ").append(
                "       T2.CUENTA, ").append(
                "       T2.NOMCTA, ").append(
                "       SUM(T2.SALDO_ANT) SALDO_ANT, ").append(
                "       SUM(T2.SALDO_ACT) SALDO_ACT ").append(
                "     FROM ( ").append(
                " SELECT  1 PRINCIPAL, ").append(
                "        2 GRUPO, ").append(
                "        T1.CUENTA, ").append(
                "        FN_GET_NOMBRE_CUENTA(T1.CUENTA, T1.NOMCTA) NOMCTA, ").append(
                "        (T1.CARGOS_ANT - T1.ABONOS_ANT) SALDO_ANT, ").append(
                "        (T1.CARGOS - T1.ABONOS) SALDO_ACT ").append(
                "      FROM( ").append(
                "SELECT '5001' CUENTA, ").append(
                "       'Servicios Personales' NOMCTA, ").append(
                " " + cargosAnt + "  CARGOS_ANT, ").append(
                " " + abonosAnt + "  ABONOS_ANT, ").append(
                " " + cargos + " CARGOS, ").append(
                " " + abonos + " ABONOS ").append(
                "   FROM CUENTA CU ").append(
                "WHERE SUBSTR(CU.CUENTA,1,1) =  '5' ").append(
                "  AND SUBSTR(CU.CUENTA,3,2) =  '00' ").append(
                "  AND SUBSTR(CU.SSSCTA,4,1) <> '0' ").append(
                "  AND SUBSTR(CU.SSSCTA,1,1) = '1' ").append(
                " ) T1 ").append(
                " ").append(
                ") T2 ").append(
                "GROUP BY T2.PRINCIPAL, ").append(
                "       T2.GRUPO, ").append(
                "       T2.CUENTA, ").append(
                "       T2.NOMCTA ").append(
                UNION_ALL).append(
                "SELECT T2.PRINCIPAL, ").append(
                "       T2.GRUPO, ").append(
                "       T2.CUENTA, ").append(
                "       T2.NOMCTA, ").append(
                "       SUM(T2.SALDO_ANT) SALDO_ANT, ").append(
                "       SUM(T2.SALDO_ACT) SALDO_ACT ").append(
                "     FROM ( ").append(
                " SELECT  1 PRINCIPAL, ").append(
                "        2 GRUPO, ").append(
                "        T1.CUENTA, ").append(
                "        T1.NOMCTA NOMCTA, ").append(
                "        (T1.CARGOS_ANT - T1.ABONOS_ANT) SALDO_ANT, ").append(
                "        (T1.CARGOS - T1.ABONOS) SALDO_ACT ").append(
                "      FROM( ").append(
                "SELECT '5002' CUENTA, ").append(
                "       'Materiales y Suministros' NOMCTA, ").append(
                " " + cargosAnt + "  CARGOS_ANT, ").append(
                " " + abonosAnt + "  ABONOS_ANT, ").append(
                " " + cargos + " CARGOS, ").append(
                " " + abonos + " ABONOS ").append(
                "   FROM CUENTA CU ").append(
                "WHERE SUBSTR(CU.CUENTA,1,1) =  '5' ").append(
                "  AND SUBSTR(CU.CUENTA,3,2) =  '00' ").append(
                "  AND SUBSTR(CU.SSSCTA,4,1) <> '0' ").append(
                "  AND SUBSTR(CU.SSSCTA,1,1) = '2' ").append(
                " ) T1 ").append(
                " ").append(
                ") T2 ").append(
                "GROUP BY T2.PRINCIPAL, ").append(
                "       T2.GRUPO, ").append(
                "       T2.CUENTA, ").append(
                "       T2.NOMCTA ").append(
                UNION_ALL).append(
                "SELECT T2.PRINCIPAL, ").append(
                "       T2.GRUPO, ").append(
                "       T2.CUENTA, ").append(
                "       T2.NOMCTA, ").append(
                "        SUM(T2.SALDO_ANT) SALDO_ANT, ").append(
                "       SUM(T2.SALDO_ACT) SALDO_ACT ").append(
                "     FROM ( ").append(
                " SELECT  1 PRINCIPAL, ").append(
                "        2 GRUPO, ").append(
                "        T1.CUENTA, ").append(
                "        T1.NOMCTA NOMCTA, ").append(
                "        (T1.CARGOS_ANT - T1.ABONOS_ANT) SALDO_ANT, ").append(
                "        (T1.CARGOS - T1.ABONOS) SALDO_ACT ").append(
                "      FROM( ").append(
                "SELECT CU.CUENTA, ").append(
                "       'Servicios Generales' NOMCTA, ").append(
                " " + cargosAnt + "  CARGOS_ANT, ").append(
                " " + abonosAnt + "  ABONOS_ANT, ").append(
                " " + cargos + " CARGOS, ").append(
                " " + abonos + " ABONOS ").append(
                "   FROM CUENTA CU ").append(
                "WHERE SUBSTR(CU.CUENTA,1,1) =  '5' ").append(
                "  AND SUBSTR(CU.CUENTA,3,2) =  '00' ").append(
                "  AND SUBSTR(CU.SSSCTA,4,1) <> '0' ").append(
                "  AND SUBSTR(CU.SSSCTA,1,1) = '3' ").append(
                " ) T1 ").append(
                " ").append(
                ") T2 ").append(
                "GROUP BY T2.PRINCIPAL, ").append(
                "       T2.GRUPO, ").append(
                "       T2.CUENTA, ").append(
                "       T2.NOMCTA ").append(
                UNION_ALL).append(
                "SELECT 1 PRINCIPAL, ").append(
                "       2 GRUPO, ").append(
                "       '5110' CUENTA, ").append(
                "       'Transferencias Internas y Asignaciones al Sector Publico' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1 ").append(
                UNION_ALL).append(
                "SELECT 1 PRINCIPAL, ").append(
                "       2 GRUPO, ").append(
                "       '5120' CUENTA, ").append(
                "       'Transferencias al Resto del Sector Publico' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1 ").append(
                UNION_ALL).append(
                "SELECT T2.PRINCIPAL, ").append(
                "       T2.GRUPO, ").append(
                "       T2.CUENTA, ").append(
                "       T2.NOMCTA, ").append(
                "        SUM(T2.SALDO_ANT) SALDO_ANT, ").append(
                "       SUM(T2.SALDO_ACT) SALDO_ACT ").append(
                "     FROM ( ").append(
                " SELECT  1 PRINCIPAL, ").append(
                "        2 GRUPO, ").append(
                "        T1.CUENTA, ").append(
                "        T1.NOMCTA NOMCTA, ").append(
                "        (T1.CARGOS_ANT - T1.ABONOS_ANT) SALDO_ANT, ").append(
                "        (T1.CARGOS - T1.ABONOS) SALDO_ACT ").append(
                "      FROM( ").append(
                "SELECT CU.CUENTA, ").append(
                "       'Ayudas Sociales' NOMCTA, ").append(
                " " + cargosAnt + "  CARGOS_ANT, ").append(
                " " + abonosAnt + "  ABONOS_ANT, ").append(
                " " + cargos + " CARGOS, ").append(
                " " + abonos + " ABONOS ").append(
                "   FROM CUENTA CU ").append(
                "WHERE CU.CUENTA =  '5200' ").append(
                "  AND SUBSTR(CU.SSSCTA,4,1) <> '0' ").append(
                "  AND SUBSTR(CU.SSSCTA,1,2) = '44' ").append(
                " ) T1 ").append(
                " ").append(
                ") T2 ").append(
                "GROUP BY T2.PRINCIPAL, ").append(
                "       T2.GRUPO, ").append(
                "       T2.CUENTA, ").append(
                "       T2.NOMCTA ").append(
                UNION_ALL).append(
                "SELECT 1 PRINCIPAL, ").append(
                "       2 GRUPO, ").append(
                "       '5210' CUENTA, ").append(
                "       'Pensiones y Jubilaciones' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1 ").append(
                UNION_ALL).append(
                "SELECT 1 PRINCIPAL, ").append(
                "       2 GRUPO, ").append(
                "       '5220' CUENTA, ").append(
                "       'Transferencias a Fideicomisos, Mandatos y Contratos Análogos' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1 ").append(
                UNION_ALL).append(
                "SELECT 1 PRINCIPAL, ").append(
                "       2 GRUPO, ").append(
                "       '5230' CUENTA, ").append(
                "       'Transferencias a la Seguridad Social' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1 ").append(
                UNION_ALL).append(
                "SELECT 1 PRINCIPAL, ").append(
                "       2 GRUPO, ").append(
                "       '5240' CUENTA, ").append(
                "       'Donativos' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1 ").append(
                UNION_ALL).append(
                "SELECT 1 PRINCIPAL, ").append(
                "       2 GRUPO, ").append(
                "       '5250' CUENTA, ").append(
                "       'Transferencias al Exterior' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1 ").append(
                UNION_ALL).append(
                "SELECT 1 PRINCIPAL, ").append(
                "       2 GRUPO, ").append(
                "       '5260' CUENTA, ").append(
                "       'Participaciones' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1 ").append(
                UNION_ALL).append(
                "SELECT 1 PRINCIPAL, ").append(
                "       2 GRUPO, ").append(
                "       '5270' CUENTA, ").append(
                "       'Aportaciones' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1 ").append(
                UNION_ALL).append(
                "SELECT 1 PRINCIPAL, ").append(
                "       2 GRUPO, ").append(
                "       '5280' CUENTA, ").append(
                "       'Convenios' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1 ").append(
                UNION_ALL).append(
    "SELECT T2.PRINCIPAL, ").append(
            "        T2.GRUPO, ").append(
            "        T2.CUENTA, ").append(
            "        T2.NOMCTA, ").append(
            "         SUM(T2.SALDO_ANT) SALDO_ANT, ").append(
            "        SUM(T2.SALDO_ACT) SALDO_ACT ").append(
            "      FROM ( ").append(
            "  SELECT  1 PRINCIPAL, ").append(
            "         2 GRUPO, ").append(
            "         '5700' CUENTA, ").append(
            "         'Otras Aplicaciones de Operación' NOMCTA, ").append(
            "         (T1.CARGOS_ANT - T1.ABONOS_ANT) SALDO_ANT, ").append(
            "         (T1.CARGOS - T1.ABONOS) SALDO_ACT ").append(
            "       FROM( ").append(
            " SELECT CU.CUENTA, ").append(
            "        'Ayudas Sociales' NOMCTA, ").append(
            		 " " + cargosAnt + "  CARGOS_ANT, ").append(
            	                " " + abonosAnt + "  ABONOS_ANT, ").append(
            	                " " + cargos + " CARGOS, ").append(
            	                " " + abonos + " ABONOS ").append(
            "    FROM CUENTA CU ").append(
            " WHERE CU.CUENTA IN (  '5500', '5700') ").append(
            "  AND CU.SCTA = '' ").append(
            "  ) T1 ").append(
            "  ").append(
            " ) T2 ").append(
            " GROUP BY T2.PRINCIPAL, ").append(
            "        T2.GRUPO, ").append(
            "        T2.CUENTA, ").append(
            "        T2.NOMCTA");
                
        return sSql.toString();
    }

    private static String generaActivadesInversionOrigen(char tipo, Integer value) {
        StringBuilder sSql = new StringBuilder();
        Integer mesTrimestre = tipo == 't' ? value * 3 : value;
        Map<String, String> cargosAbonos = getMapCounts(mesTrimestre);
        String cargos = cargosAbonos.get("cargos");
        String abonos = cargosAbonos.get("abonos");
        Integer mesTrimestreAnt = tipo == 't' ? (value - 1) * 3 : value - 1;
        Map<String, String> cargosAbonosAnt = getMapCounts(mesTrimestreAnt);
        String cargosAnt = cargosAbonosAnt.get("cargos");
        String abonosAnt = cargosAbonosAnt.get("abonos");

        Integer mesTrimestre1 = tipo == 't' ? (value * 3) - 1 : value - 1;
        Map<String, String> cargosAbonos1 = getMapCounts(mesTrimestre1);
        String cargos1 = cargosAbonos1.get("cargos");
        String abonos1 = cargosAbonos1.get("abonos");
        Integer mesTrimestreAnt1 = tipo == 't' ? ((value - 1) * 3) - 1 : (value - 1) - 1;
        Map<String, String> cargosAbonosAnt1 = getMapCounts(mesTrimestreAnt1);
        String cargosAnt1 = cargosAbonosAnt1.get("cargos");
        String abonosAnt1 = cargosAbonosAnt1.get("abonos");
        sSql.append("SELECT 2 PRINCIPAL, ").append(
                "       3 GRUPO, ").append(
                "       '1199' CUENTA, ").append(
                "       'Bienes Inmuebles, Infraestructura y Construcciones en Proceso' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                " ").append(
                "     FROM SYSIBM.SYSDUMMY1 ").append(
                "UNION ALL ").append(
                "SELECT T1.PRINCIPAL, ").append(
                "       T1.GRUPO, ").append(
                "       T1.CUENTA, ").append(
                "       T1.NOMCTA, ").append(
                "       SUM((T1.SALINI + CARGOS_ANT - ABONOS_ANT) - (T1.SALINI + CARGOS_ANT1 -ABONOS_ANT1 )) SALDO_ANT, ").append(
                "       ABS(SUM((T1.SALINI + CARGOS - ABONOS) - (T1.SALINI + CARGOS1 - ABONOS1))) SALDO_ACT ").append(
                "      FROM( ").append(
                "SELECT 2 PRINCIPAL, ").append(
                "       3 GRUPO, ").append(
                "       SUBSTR(CU.CUENTA,1,2) || '00' CUENTA, ").append(
                "       'Bienes Muebles' NOMCTA, ").append(
                "       CU.SALINI, ").append(
                "  " + cargosAnt + "  CARGOS_ANT, ").append(
                "  " + abonosAnt + "  ABONOS_ANT, ").append(
                "   " + cargosAnt1 + "   CARGOS_ANT1, ").append(
                "  " + abonosAnt1 + "   ABONOS_ANT1, ").append(
                "  " + cargos1 + "  CARGOS1, ").append(
                "  " + abonos1 + "  ABONOS1, ").append(
                "  " + cargos + " CARGOS, ").append(
                "  " + abonos + " ABONOS ").append(
                "     FROM  CUENTA CU, ").append(
                "       MAYCTA MA ").append(
                "WHERE CU.CUENTA = MA.CUENTA ").append(
                "  AND SUBSTR(CU.CUENTA, 1,2) = '12' ").append(
                "  AND SUBSTR(CU.CUENTA, 4,1) <> '0' ").append(
                "  AND CU.SCTA = '' ").append(
                "  AND MA.NATCTA = 'D' ").append(
                ") T1 ").append(
                "GROUP BY T1.PRINCIPAL, T1.GRUPO, T1.CUENTA, T1.NOMCTA");
        sSql.append(UNION_ALL);
        sSql.append("SELECT 2 PRINCIPAL, ").append(
                "       3 GRUPO, ").append(
                "       '1210' CUENTA, ").append(
                "       'Otros Orígenes de Inversión' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1");
        return sSql.toString();
    }

    private static String generateActividadesFinanciamiento(char tipo, Integer value) {
        StringBuilder sSql = new StringBuilder();
        Integer mesTrimestre = tipo == 't' ? value * 3 : value;
        Map<String, String> cargosAbonos = getMapCounts(mesTrimestre);
        String cargos = cargosAbonos.get("cargos");
        String abonos = cargosAbonos.get("abonos");
        Integer mesTrimestreAnt = tipo == 't' ? (value - 1) * 3 : value - 1;
        Map<String, String> cargosAbonosAnt = getMapCounts(mesTrimestreAnt);
        String cargosAnt = cargosAbonosAnt.get("cargos");
        String abonosAnt = cargosAbonosAnt.get("abonos");

        Integer mesTrimestre1 = tipo == 't' ? (value * 3) - 1 : value - 1;
        Map<String, String> cargosAbonos1 = getMapCounts(mesTrimestre1);
        String cargos1 = cargosAbonos1.get("cargos");
        String abonos1 = cargosAbonos1.get("abonos");
        Integer mesTrimestreAnt1 = tipo == 't' ? ((value - 1) * 3) - 1 : (value - 1) - 1;
        Map<String, String> cargosAbonosAnt1 = getMapCounts(mesTrimestreAnt1);
        String cargosAnt1 = cargosAbonosAnt1.get("cargos");
        String abonosAnt1 = cargosAbonosAnt1.get("abonos");
        sSql.append("SELECT 2 PRINCIPAL, ").append(
                "       4 GRUPO, ").append(
                "       '1100' CUENTA, ").append(
                "       'Bienes Inmuebles, Infraestructura y Construcciones en Proceso' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1");
        sSql.append(UNION_ALL);
        sSql.append("SELECT 2 PRINCIPAL, ").append(
                "       4 GRUPO, ").append(
                "       '1110' CUENTA, ").append(
                "       'Bienes Muebles' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1");
        sSql.append(UNION_ALL);
        sSql.append("SELECT T1.PRINCIPAL, ").append(
                "       T1.GRUPO, ").append(
                "       T1.CUENTA, ").append(
                "       T1.NOMCTA, ").append(
                "       ABS(SUM((T1.SALINI + CARGOS_ANT - ABONOS_ANT) - (T1.SALINI + CARGOS_ANT1 -ABONOS_ANT1 ))) SALDO_ANT, ").append(
                "       ABS(SUM((T1.SALINI + CARGOS - ABONOS) - (T1.SALINI + CARGOS1 - ABONOS1))) SALDO_ACT ").append(
                "      FROM( ").append(
                " SELECT 2 PRINCIPAL, ").append(
                "       4 GRUPO, ").append(
                "      '1120' CUENTA, ").append(
                "       'Otras Aplicaciones de Inversion' NOMCTA, ").append(
                "       CU.SALINI, ").append(
                "  " + cargosAnt + "  CARGOS_ANT, ").append(
                "  " + abonosAnt + "  ABONOS_ANT, ").append(
                "   " + cargosAnt1 + "   CARGOS_ANT1, ").append(
                "  " + abonosAnt1 + "   ABONOS_ANT1, ").append(
                "  " + cargos1 + "  CARGOS1, ").append(
                "  " + abonos1 + "  ABONOS1, ").append(
                "  " + cargos + " CARGOS, ").append(
                "  " + abonos + " ABONOS ").append(
                "     FROM  CUENTA CU, ").append(
                "       MAYCTA MA ").append(
                " WHERE CU.CUENTA = MA.CUENTA ").append(
                "  AND CU.CUENTA IN( '1123' , '1263') ").append(
                "  AND CU.SCTA = '' ").append(
                ") T1 ").append(
                "GROUP BY  T1.PRINCIPAL, ").append(
                "       T1.GRUPO, ").append(
                "       T1.CUENTA, ").append(
                "       T1.NOMCTA");
        return sSql.toString();
    }

    public static String generaFlujoEfectivoActividadesFinanciamiento(char tipo, Integer value) {
        StringBuilder sSql = new StringBuilder();
        Integer mesTrimestre = tipo == 't' ? value * 3 : value;
        Map<String, String> cargosAbonos = getMapCounts(mesTrimestre);
        String cargos = cargosAbonos.get("cargos");
        String abonos = cargosAbonos.get("abonos");
        Integer mesTrimestreAnt = tipo == 't' ? (value - 1) * 3 : value - 1;
        Map<String, String> cargosAbonosAnt = getMapCounts(mesTrimestreAnt);
        String cargosAnt = cargosAbonosAnt.get("cargos");
        String abonosAnt = cargosAbonosAnt.get("abonos");

        Integer mesTrimestre1 = tipo == 't' ? (value * 3) - 1 : value - 1;
        Map<String, String> cargosAbonos1 = getMapCounts(mesTrimestre1);
        String cargos1 = cargosAbonos1.get("cargos");
        String abonos1 = cargosAbonos1.get("abonos");
        Integer mesTrimestreAnt1 = tipo == 't' ? ((value - 1) * 3) - 1 : (value - 1) - 1;
        Map<String, String> cargosAbonosAnt1 = getMapCounts(mesTrimestreAnt1);
        String cargosAnt1 = cargosAbonosAnt1.get("cargos");
        String abonosAnt1 = cargosAbonosAnt1.get("abonos");
        sSql.append("SELECT 3 PRINCIPAL, ").append(
                "       5 GRUPO, ").append(
                "       '2000' CUENTA, ").append(
                "       'Endeudamiento Neto' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1");
        sSql.append(UNION_ALL);
        sSql.append("SELECT 3 PRINCIPAL, ").append(
                "       5 GRUPO, ").append(
                "       '2100' CUENTA, ").append(
                "       '    Interno' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1");
        sSql.append(UNION_ALL);
        sSql.append("SELECT 3 PRINCIPAL, ").append(
                "       5 GRUPO, ").append(
                "       '2110' CUENTA, ").append(
                "       '    Externo' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1");
        sSql.append(UNION_ALL);
        sSql.append("SELECT T1.PRINCIPAL, ").append(
                "       T1.GRUPO, ").append(
                "       T1.CUENTA, ").append(
                "       T1.NOMCTA, ").append(
                "       ABS(SUM((T1.SALINI + CARGOS_ANT - ABONOS_ANT) - (T1.SALINI + CARGOS_ANT1 -ABONOS_ANT1 ))) SALDO_ANT, ").append(
                "       ABS(SUM((T1.SALINI + CARGOS - ABONOS) - (T1.SALINI + CARGOS1 - ABONOS1))) SALDO_ACT ").append(
                "      FROM( ").append(
                " SELECT 3 PRINCIPAL, ").append(
                "       5 GRUPO, ").append(
                "       CU.CUENTA, ").append(
                "       'Otros Orígenes de Financiamiento' NOMCTA, ").append(
                "       CU.SALINI, ").append(
                "  " + cargosAnt + "  CARGOS_ANT, ").append(
                "  " + abonosAnt + "  ABONOS_ANT, ").append(
                "   " + cargosAnt1 + "   CARGOS_ANT1, ").append(
                "  " + abonosAnt1 + "   ABONOS_ANT1, ").append(
                "  " + cargos1 + "  CARGOS1, ").append(
                "  " + abonos1 + "  ABONOS1, ").append(
                "  " + cargos + " CARGOS, ").append(
                "  " + abonos + " ABONOS ").append(
                "     FROM  CUENTA CU, ").append(
                "       MAYCTA MA ").append(
                " WHERE CU.CUENTA = MA.CUENTA ").append(
                "  AND CU.CUENTA = '2117' ").append(
                "  AND CU.SCTA = '' ").append(
                " ) T1 ").append(
                " GROUP BY  T1.PRINCIPAL, ").append(
                "       T1.GRUPO, ").append(
                "       T1.CUENTA, ").append(
                "       T1.NOMCTA");
        return sSql.toString();
    }

    public static String generaFlujoEfectivoActividadesFinanciamientoAplicacion(char tipo, Integer value) {
        StringBuilder sSql = new StringBuilder();
        sSql.append("SELECT 3 PRINCIPAL, ").append(
                "       6 GRUPO, ").append(
                "       '3000' CUENTA, ").append(
                "       'Servicios de la Deuda' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1");
        sSql.append(UNION_ALL);
        sSql.append("SELECT 3 PRINCIPAL, ").append(
                "       6 GRUPO, ").append(
                "       '3100' CUENTA, ").append(
                "       '    Interno' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1");
        sSql.append(UNION_ALL);
        sSql.append("SELECT 3 PRINCIPAL, ").append(
                "       6 GRUPO, ").append(
                "       '3110' CUENTA, ").append(
                "       '    Externo' NOMCTA, ").append(
                "       0 SALDO_ANT, ").append(
                "       0 SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1");
        sSql.append(UNION_ALL);
        Integer mesTrimestre = tipo == 't' ? value * 3 : value;
        Integer mesTrimestreAnt = tipo == 't' ? (value - 1) * 3 : value - 1;
        sSql.append("SELECT 3 PRINCIPAL, ").append(
                "       6 GRUPO, ").append(
                "       '3211' CUENTA, ").append(
                "       'Otras Aplicaciones de Financiamiento' NOMCTA, ").append(
                "       FN_GET_3211(" + mesTrimestreAnt + ", 'a') SALDO_ANT, ").append(
                "       FN_GET_3211(" + mesTrimestre + ", 'a') SALDO_ACT ").append(
                "     FROM SYSIBM.SYSDUMMY1");
        return sSql.toString();
    }

    public static String getFlujosEfectivo(char tipo, Integer value) {
        StringBuilder sSql = new StringBuilder();
        sSql.append(generaActividadesOperacionOrigen(tipo, value))
                .append(UNION_ALL)
                .append(gengeneraActividadesOperacionAplicacion(tipo, value))
                .append(UNION_ALL)
                .append(generaActivadesInversionOrigen(tipo, value))
                .append(UNION_ALL)
                .append(generateActividadesFinanciamiento(tipo, value))
                .append(UNION_ALL)
                .append(generaFlujoEfectivoActividadesFinanciamiento(tipo, value))
                .append(UNION_ALL)
                .append(generaFlujoEfectivoActividadesFinanciamientoAplicacion(tipo, value))
                .append(SORTED_ASC);
        System.out.println(sSql);
        return sSql.toString();
    }

    public static Map<String, String> getMapCounts(Integer value) {
        StringBuilder cargos = new StringBuilder();
        StringBuilder abonos = new StringBuilder();
        value =  value == 0 ? 1 : value;
        if (value <= 0) {
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
    
    public static Map<String, String> getMapCounts(char tipo, Integer value) {
        StringBuilder cargos = new StringBuilder();
        StringBuilder abonos = new StringBuilder();
        if(tipo == 't' && value <= 0 )
        	value = 0;
        else 
        	value =  value == 0 ? 1 : value;        
        
        if (value <= 0) {
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
}

