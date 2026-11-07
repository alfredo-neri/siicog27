package com.gem.sistema.business.dao.impl;

import static com.roonin.utils.UtilDate.getLastDayOfMonth;
import static com.roonin.utils.UtilDate.getYear;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.CallSpDAO;
import com.gem.sistema.business.dao.ExportInformationDAO;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;

@Repository
public class ExportInformationDAOImpl implements ExportInformationDAO {

	private static final String NAME_PROCEDURE = "SP_EXPORTA_CUENTAS";
	private static final String FILE_CUETA = "cuenta.txt";
	private static final String FILE_PASO = "egrgas.txt";
	private static final String FILE_POLIZA = "p";
	private static final String CUENTA_EXPORT = "c";
	private static final String TXT = ".txt";

	@Autowired
	private CallSpDAO callSpDAO;

	@Autowired
	private ConctbRepository conctbRepository;
	SqlParameterSource parameters;
	Map<String, Object> out;

	@Override
	public String exportCuentas(Integer idSector, Integer mes) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT T1.CUENTA||'|'||T1.SCTA||'|'||\r\n")
				.append("             T1.SSCTA ||'|'||T1.SSSCTA||'|'||\r\n")
				.append("             T1.SSSSCTA||'|'||TRIM(TO_CHAR(T1.CARGOS, '999999999990.99'))||'|'||\r\n")
				.append("             TRIM(TO_CHAR(T1.ABONOS, '999999999990.99'))\r\n")
				.append("      FROM (SELECT CU.CUENTA   ,\r\n").append("       			   CU.SCTA     ,\r\n")
				.append("			       CU.SSCTA    ,\r\n").append("			       CU.SSSCTA   ,\r\n")
				.append("			       CU.SSSSCTA  ,\r\n").append("			       SUM(CU.CARGOS_").append(mes)
				.append(") CARGOS,\r\n").append("			       SUM(CU.ABONOS_").append(mes).append(") ABONOS\r\n")
				.append("			     FROM GEMUSER.CUENTA CU\r\n").append("			WHERE CU.IDSECTOR = ")
				.append(idSector).append("\r\n").append("			GROUP BY  CU.CUENTA   ,\r\n")
				.append("			      	  CU.SCTA     ,\r\n").append("				      CU.SSCTA    ,\r\n")
				.append("				      CU.SSSCTA   ,\r\n").append("				      CU.SSSSCTA \r\n")
				.append("		    ORDER BY CU.CUENTA    ,\r\n").append("			      	  CU.SCTA     ,\r\n")
				.append("				      CU.SSCTA    ,\r\n").append("				      CU.SSSCTA   ,\r\n")
				.append("				      CU.SSSSCTA\r\n").append("				      ASC\r\n")
				.append("		    ) T1");
		parameters = new MapSqlParameterSource().addValue("i_header", StringUtils.EMPTY)
				.addValue("i_query", sSql.toString()).addValue("i_file_name", FILE_CUETA);
		out = this.callSpDAO.call(NAME_PROCEDURE, parameters);
		return out.get("O_FULL_FILE_PATH").toString();
	}

	@Override
	public String exportPaso(Integer idSector, Integer mes) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT '\"'||T1.CLAVE||'\" \"'||T1.PROGRAMA||'\" \"'||\r\n").append(
				"       '\"'||T1.PARTIDA||'\" \"'||TRIM(TO_CHAR(T1.APROBADO, '999999999999990.99'))||'\" \"'||\r\n")
				.append("       '\"'||TRIM(TO_CHAR(T1.APLIACION, '999999999999990.99'))||'\" \"'||TRIM(TO_CHAR(T1.REDUCCION, '999999999999990.99'))||'\" \"'||\r\n")
				.append("       '\"'||TRIM(TO_CHAR(T1.EJPA, '999999999999990.99'))||'\" \"'||TRIM(TO_CHAR(T1.JEXPA, '999999999999990.99'))||'\" \"'||\r\n")
				.append("       '\"'||TRIM(TO_CHAR(T1.POR_EJERCER, '999999999999990.99'))||'\" \"'||TRIM(TO_CHAR(POR_APROBAR, '999999999999990.99'))||'\"'\r\n")
				.append("     FROM (SELECT PA.CLAVE   ,\r\n").append("                  PA.PROGRAMA,\r\n")
				.append("                  PA.PARTIDA ,\r\n").append("                  SUM(PA.AUTO1_").append(mes)
				.append(") APROBADO,\r\n").append("                  SUM(PA.AMPLI1_").append(mes)
				.append(") APLIACION,\r\n").append("                  SUM(PA.REDU1_").append(mes)
				.append(") REDUCCION,\r\n").append("                  SUM(PA.EJPA1_").append(mes).append(") EJPA,\r\n")
				.append("                  SUM(PA.EJXPA1_").append(mes).append(") JEXPA,\r\n")
				.append("                  SUM(PA.TOEJE1_").append(mes).append(") POR_EJERCER,\r\n")
				.append("                  SUM(PA.PORPA1_").append(mes).append(") POR_APROBAR\r\n")
				.append("                FROM GEMUSER.PASO PA\r\n").append("			WHERE PA.IDSECTOR = ")
				.append(idSector).append("\r\n").append("			GROUP BY PA.CLAVE   ,\r\n")
				.append("			         PA.PROGRAMA,\r\n").append("			         PA.PARTIDA\r\n")
				.append("			ORDER BY PA.CLAVE   ,\r\n").append("			       	 PA.PROGRAMA,\r\n")
				.append("				     PA.PARTIDA\r\n").append("				     ASC\r\n")
				.append("		   ) T1");
		parameters = new MapSqlParameterSource().addValue("i_header", StringUtils.EMPTY)
				.addValue("i_query", sSql.toString()).addValue("i_file_name", FILE_PASO);
		out = this.callSpDAO.call(NAME_PROCEDURE, parameters);
		return out.get("O_FULL_FILE_PATH").toString();
	}

	@Override
	public String exportPoliza(Integer idSector, Integer mes) {
		Conctb conctb = this.conctbRepository.findByIdsector(idSector);
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT '\"'||T1.TIPPOL||'\"|\"'||T1.CONPOL||'\"|\"'||\r\n")
				.append("            T1.DIA||'\"|\"'||T1.MES||'\"|\"'||T1.ANO||'\"|\"'||\r\n")
				.append("            T1.CUENTA||'\"|\"'||T1.SCTA||'\"|\"'||T1.SSCTA||'\"|\"'||\r\n")
				.append("            T1.SSSCTA||'\"|\"'||T1.SSSSCTA||'\"|\"'||T1.REFPOL||'\"|\"'||\r\n")
				.append("            TRIM(TO_CHAR(T1.CARGOS, '999999999999990.99'))||'\"|\"'||\r\n")
				.append("            TRIM(TO_CHAR(T1.ABONOS, '999999999999990.99'))||'\"'\r\n")
				.append("     FROM (SELECT PD.TIPPOL,\r\n").append("       		      PD.CONPOL,\r\n")
				.append("		          TO_CHAR(PD.FECPOL, 'DD') DIA,\r\n")
				.append("		          TO_CHAR(PD.FECPOL, 'MM')MES ,\r\n")
				.append("		          TO_CHAR(PD.FECPOL, 'YY')ANO ,\r\n").append("		          PD.CUENTA ,\r\n")
				.append("		          PD.SCTA   ,\r\n").append("		          PD.SSCTA  ,\r\n")
				.append("		          PD.SSSCTA ,\r\n").append("		          PD.SSSSCTA,\r\n")
				.append("		          PD.REFPOL ,\r\n").append("		          PD.CONCEP ,\r\n")
				.append("		          SUM(PD.CANPOL) CARGOS ,\r\n")
				.append("		          SUM(PD.CANPOLH) ABONOS\r\n")
				.append("		        FROM GEMUSER.POLIEN PE,\r\n").append("		            GEMUSER.POLIDE PD\r\n")
				.append("           WHERE 1 = 1\r\n").append("             AND PE.MESPOL   = PD.MESPOL\r\n")
				.append("             AND PE.CONPOL   = PD.CONPOL\r\n")
				.append("             AND PE.TIPPOL   = PD.TIPPOL\r\n")
				.append("             AND PE.ANOPOL   = PD.ANOPOL\r\n")
				.append("             AND PE.IDSECTOR = PD.IDSECTOR\r\n").append("             AND PE.IDSECTOR = ")
				.append(idSector).append(" \r\n").append("             AND PE.MESPOL   =  ").append(mes).append(" \r\n")
				.append("             AND PE.STAAFE   = 'A'\r\n").append("             AND PE.ANOPOL   = ")
				.append(conctb.getAnoemp()).append("\r\n").append("          GROUP BY PD.TIPPOL,\r\n")
				.append("      	           PD.CONPOL,\r\n").append("	     		   TO_CHAR(PD.FECPOL, 'DD'),\r\n")
				.append("				   TO_CHAR(PD.FECPOL, 'MM'),\r\n")
				.append("				   TO_CHAR(PD.FECPOL, 'YY'),\r\n").append("				   PD.CUENTA ,\r\n")
				.append("				   PD.SCTA   ,\r\n").append("				   PD.SSCTA  ,\r\n")
				.append("				   PD.SSSCTA ,\r\n").append("				   PD.SSSSCTA,\r\n")
				.append("				   PD.REFPOL ,\r\n").append("				   PD.CONCEP\r\n")
				.append("          ORDER BY 1, 2\r\n").append("          ) T1\r\n").append("");
		String fileName = FILE_POLIZA
				+ StringUtils.leftPad(String.valueOf(getLastDayOfMonth(Calendar.getInstance().getTime())), 2, '0') + ""
				+ getYear() + TXT;
		parameters = new MapSqlParameterSource().addValue("i_header", StringUtils.EMPTY)
				.addValue("i_query", sSql.toString()).addValue("i_file_name", fileName);
		out = this.callSpDAO.call(NAME_PROCEDURE, parameters);
		return out.get("O_FULL_FILE_PATH").toString();
	}

	public CallSpDAO getCallSpDAO() {
		return callSpDAO;
	}

	public void setCallSpDAO(CallSpDAO callSpDAO) {
		this.callSpDAO = callSpDAO;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	@Override
	public String exportCuentaMes(Integer idSector, Integer mes) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT  '\"'||CUENTA||\r\n").append("		SCTA||\r\n").append("		SSCTA||\r\n")
				.append("		SSSCTA||\r\n").append("		SSSSCTA||'\"|\"'||\r\n")
				.append("		NOMCTA||'\"|\"'||\r\n").append("		DECODE(XNICTA,1,CUENTA,'')||\r\n")
				.append("		DECODE(XNICTA,2,CUENTA,'')||\r\n").append("		DECODE(XNICTA,3,CUENTA||SCTA,'')||\r\n")
				.append("		DECODE(XNICTA,4,CUENTA||SCTA||SSCTA,'')||\r\n")
				.append("		DECODE(XNICTA,5,CUENTA||SCTA||SSCTA||SSSCTA,'')||'\"|\"'||\r\n")
				.append("		XNICTA||'\"|\"'||\r\n").append("		STACTA||'\"|\"'||\r\n")
				.append("		DECODE(XNICTA,4,'R','A')||'\"|\"'||\r\n")
				.append("		DECODE(SUBSTR(CUENTA,1,1),5,'E','A')||'\"|\"'||\r\n")
				.append("		FN_GET_FORMAT_NUMBER(CARGOS_1 - ABONOS_1)||'\"'\r\n").append("	FROM CUENTA\r\n")
				.append("WHERE IDSECTOR = ").append(idSector).append("\r\n")
				.append("ORDER BY CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA");

		String fileName = CUENTA_EXPORT
				+ StringUtils.leftPad(String.valueOf(getLastDayOfMonth(Calendar.getInstance().getTime())), 2, '0') + ""
				+ getYear() + TXT;
		parameters = new MapSqlParameterSource().addValue("i_header", StringUtils.EMPTY)
				.addValue("i_query", sSql.toString()).addValue("i_file_name", fileName);
		out = this.callSpDAO.call(NAME_PROCEDURE, parameters);
		return out.get("O_FULL_FILE_PATH").toString();
	}

	@Override
	public String generaQueryExportCuenaMes(Integer idSector, Integer mes, String ctaCero) {

		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT  CUENTA  ||' '||\n" +
				"\t\tSCTA    ||' '||\n" +
				"\t\tSSCTA   ||' '||\n" +
				"\t\tSSSCTA  ||' '||\n" +
				"\t\tSSSSCTA CUENTA,\n" +
				"\t\tNOMCTA  NOMBRE,\n" +
				"\t\tDECODE(XNICTA,1,CUENTA,'')||\n" +
				"\t\tDECODE(XNICTA,2,CUENTA,'')||\n" +
				"\t\tDECODE(XNICTA,3,CUENTA||SCTA,'')||\n" +
				"\t\tDECODE(XNICTA,4,CUENTA||SCTA||SSCTA,'')||\n" +
				"\t\tDECODE(XNICTA,5,CUENTA||SCTA||SSCTA||SSSCTA,'') CTA_MAYOR,\n" +
				"\t\tXNICTA NIVEL,\n" +
				"\t\tSTACTA NATURALEZA,\n" +
				"\t\tDECODE(XNICTA,4,'R','A') CLASE,\n" +
				"\t\tDECODE(SUBSTR(CUENTA,1,1),5,'E','A') TIPO,\n" +
				"\t\t(CARGOS_"+mes+" - ABONOS_"+mes+") SALDO\n" +
				"\tFROM CUENTA\n" +
				"\tWHERE IDSECTOR = "+idSector );

		if (ctaCero.equals("N"))
			sSql.append(" AND (CARGOS_"+mes+" - ABONOS_"+mes+") <> 0");

		System.out.println("::::::" + sSql.toString());
		return sSql.toString();
	}

	@Override
	public String generaQueryExportPolizasMes(Integer idSector, Integer mes, Integer anio) {
		StringBuilder sSql = new StringBuilder();

		sSql.append("SELECT \tT1.TIPPOL,\n" +
				"\t\tT1.CONPOL,\n" +
				"        T1.DIA,\n" +
				"        T1.MES,\n" +
				"        T1.ANO,\n" +
				"        T1.CUENTA||' '||T1.SCTA||' '||T1.SSCTA||' '||T1.SSSCTA||' '||T1.SSSSCTA CUENTA,\n" +
				"        T1.REFPOL,\n" +
				"        T1.CONCEP,\n" +
				"        CASE \n" +
				"        \tWHEN T1.CARGOS <> 0 AND T1.ABONOS = 0 THEN 1\n" +
				"        \tWHEN T1.CARGOS = 0 AND T1.ABONOS <> 0 THEN 2\n" +
				"        \tELSE 0  \n" +
				"    \tEND AS TIPO,\n" +
				"    \tCASE \n" +
				"        \tWHEN T1.CARGOS <> 0 AND T1.ABONOS = 0 THEN T1.CARGOS\n" +
				"        \tWHEN T1.CARGOS = 0 AND T1.ABONOS <> 0 THEN T1.ABONOS\n" +
				"        \tELSE 0  \n" +
				"    \tEND AS IMPORTE\n" +
				"     FROM (SELECT PD.TIPPOL,\n" +
				"       \t\t      PD.CONPOL,\n" +
				"\t\t          TO_CHAR(PD.FECPOL, 'DD') DIA,\n" +
				"\t\t          TO_CHAR(PD.FECPOL, 'MM')MES ,\n" +
				"\t\t          TO_CHAR(PD.FECPOL, 'YY')ANO ,\n" +
				"\t\t          PD.CUENTA ,\n" +
				"\t\t          PD.SCTA   ,\n" +
				"\t\t          PD.SSCTA  ,\n" +
				"\t\t          PD.SSSCTA ,\n" +
				"\t\t          PD.SSSSCTA,\n" +
				"\t\t          PD.REFPOL ,\n" +
				"\t\t          PD.CONCEP ,\n" +
				"\t\t          PD.CANPOL  CARGOS,\n" +
				"\t\t          PD.CANPOLH ABONOS\n" +
				"\t\t        FROM GEMUSER.POLIEN PE,\n" +
				"\t\t             GEMUSER.POLIDE PD\n" +
				"           WHERE 1 = 1\n" +
				"             AND PE.MESPOL   = PD.MESPOL\n" +
				"             AND PE.CONPOL   = PD.CONPOL\n" +
				"             AND PE.TIPPOL   = PD.TIPPOL\n" +
				"             AND PE.ANOPOL   = PD.ANOPOL\n" +
				"             AND PE.IDSECTOR = PD.IDSECTOR\n" +
				"             AND PE.IDSECTOR = " + idSector + "\n" +
				"             AND PE.MESPOL   = " +  mes + "\n" +
				"             AND PE.STAAFE   = 'A'\n" +
				"             AND PE.ANOPOL   = " + anio + "\n" +
				"          ORDER BY 1, 2\n" +
				"          ) T1");

		System.out.println("::::::" + sSql.toString());
		return sSql.toString();
	}

	@Override
	public String zipFile(File[] inputFile, String zipFilePath) {

		byte[] buffer = new byte[1024];
		FileInputStream in = null;
		try {

			FileOutputStream fos = new FileOutputStream(zipFilePath);
			ZipOutputStream zos = new ZipOutputStream(fos);
			for (int i = 0; i < inputFile.length; i++) {
				ZipEntry ze = new ZipEntry(inputFile[i].getName());
				zos.putNextEntry(ze);
				 in = new FileInputStream(inputFile[i]);

				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				
			}
			in.close();
			zos.closeEntry();

			zos.close();
			System.out.println("Hecho");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return zipFilePath;
	}

}
