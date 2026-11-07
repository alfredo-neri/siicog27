package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcEntidadAdministrativa;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcEntidadAdministrativaRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.util.ConstantsClaveEnnum;

/**
 * The Class RF01014MB.
 * 
 * @author Alfredo
 * 
 *         Date: 07/04/2021 Changes:Se ajusta Reporte en la seccion de Ingresos,
 *         se elimina la columna de acumulado, Se ajusta diseño del reporte
 */
@Component("rF01014MB2")
@Scope("view")
public class RF01014MB2 extends BaseDirectReport {

	private static final String CLAVE_REPORT = "RPT_EDOACTIVIDADES";
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	private static final String NAME_PROCEDURE = "SP_GENERA_TXT_EA";

	/** The mes. */
	private Integer mes;

	/** The tc mes repository. */
	@Value("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The conctb repository. */
	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{tcEntidadAdministrativaRepository}")
	private TcEntidadAdministrativaRepository entidadAdministrativaRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/** The execute pl service. */
	@Value("#{executePlService}")
	private ExecutePlService executePlService;

	private StreamedContent psFileTxt;

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * 
	 */
	@Value("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RF010.1.42 ");
		jasperReporteName = "RF010.1.42";
		endFilename = jasperReporteName + ".pdf";
	}

	/** The params report. */
	Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {

		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);

		paramsReport.put("AN", conctb.getAnoemp());
		paramsReport.put("USUARIO", this.getUserDetails().getUsername());
		paramsReport.put("MES", mes);
		paramsReport.put("LOGO", conctb.getImagePathRigth());
		paramsReport.put("SECTOR", idSector);

		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		paramsReport.put("entidadName", conctb.getNomDep().toUpperCase());
		paramsReport.put("municipioNo","");
		paramsReport.put("tipoOrganismo", "");
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));
		endFilename = getNameFileReport(mes, conctb, reporte);

		paramsReport.put("lastDayOfMonth", getLastDayByAnoEmp(mes, conctb.getAnoemp()));
		paramsReport.put("nameMonth",
				tcMesRepository.findByMes(StringUtils.leftPad(mes.toString(), 2, '0')).getDescripcion());
		paramsReport.put("QUERY_INGRESOS", this.generateQueryIngresos(mes, idSector));
		paramsReport.put("QUERY_EGRESOS", this.generateQueryEgresos(mes, idSector));
		paramsReport.put("QUERY_EGRESOS5002", this.GenerateQueryEgresos5002(mes, idSector));

		return paramsReport;
	}

	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());

			PrimeFaces.current().executeScript(DOWNLOAD_XLS);
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
			;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
		/*
		 * Map<String, Object> paramsQuery = new java.util.HashMap<String, Object>();
		 * paramsQuery.put("ID_REF", new Integer(0)); //FALTA return
		 * reporteadorDirectoImpl.getFileReport(tcReporte,paramsQuery,
		 * reporteName,type);
		 */
	}

	/**
	 * Generate query ingresos.
	 *
	 * @param mes    the mes
	 * @param sector the sector
	 * @return the string
	 */
	public String generateQueryIngresos(Integer mes, Integer sector) {

		StringBuilder sSql = new StringBuilder();
		StringBuilder acomunlado = new StringBuilder().append(" (C.ABONOS_1 - C.CARGOS_1) ");
		StringBuilder mesActual = new StringBuilder().append(" C.ABONOS_" + mes + " - C.CARGOS_" + mes + " ");
		StringBuilder mesAnterior = new StringBuilder().append(" (C.ABONOS_1 - C.CARGOS_1) ");

		for (int i = 2; i <= mes; i++) {

			acomunlado.append("+ (C.ABONOS_" + i + " - C.CARGOS_" + i + ") ");
			if (i < mes) {
				mesAnterior.append("+ (C.ABONOS_" + i + " - C.CARGOS_" + i + ") ");
			}
		}

		if (mes == 1)
			mesAnterior.replace(0, mesActual.length() + 1, " 0.000 ");

		sSql.append(" SELECT " + " CUENTA " + " , CONCEPTO " + " , GRUPO " + " , SUM(MES_ANT) MES_ANT "
				+ " , SUM(MES_ACT) MES_ACT " + " , SUM(ACUMULADO) ACUMULADO " + " FROM ( " + " SELECT "
				+ " 	C.CUENTA " + " 	, C.NOMCTA AS CONCEPTO " + " 	, substr(C.SCTA,8,3) AS GRUPO  " + " 	, "
				+ mesAnterior + " AS MES_ANT " + " 	, " + acomunlado + " AS MES_ACT " + " 	, " + acomunlado
				+ " AS ACUMULADO " + " FROM " + " 	CUENTA C " + " WHERE " + " 	C.CUENTA BETWEEN '4000' AND '4399' AND "
				+ " 	SUBSTR(C.CUENTA,4,1) = '0' AND " + " 	C.SCTA='' AND C.SSCTA='' AND " + " 	C.IDSECTOR = "
				+ sector + " ) " + " GROUP BY CUENTA, GRUPO, CONCEPTO " + " ORDER BY CUENTA,GRUPO ASC   ");

		System.out.println(sSql.toString());
		return sSql.toString();
	}

	/**
	 * Generate query egresos.
	 *
	 * @param mes    the mes
	 * @param sector the sector
	 * @return the string
	 */
	public String generateQueryEgresos(Integer mes, Integer sector) {

		StringBuilder sSql = new StringBuilder();
		StringBuilder acomunlado = new StringBuilder().append(" (C.CARGOS_1 - C.ABONOS_1) ");
		StringBuilder mesActual = new StringBuilder().append(" C.CARGOS_" + mes + " - C.ABONOS_" + mes + " ");
		StringBuilder mesAnterior = new StringBuilder().append(" (C.CARGOS_1 - C.ABONOS_1) ");

		for (int i = 2; i <= mes; i++) {
			acomunlado.append("+ (C.CARGOS_" + i + " - C.ABONOS_" + i + ") ");
			if (i < mes) {
				mesAnterior.append("+ (C.CARGOS_" + i + " - C.ABONOS_" + i + ") ");
			}
		}

		if (mes == 1)
			mesAnterior.replace(0, mesActual.length() + 1, " 0.000 ");

		sSql.append(" SELECT  " + "    CUENTA  " + " ,  CONCEPTO " + " ,  SALINI " + " ,  GRUPO "
				+ " ,  SUM(MES_ANT) MES_ANT " + " ,  SUM(MES_ACT) MES_ACT " + " ,  SUM(ACUMULADO) ACUMULADO "
				+ " FROM ( " + " SELECT " + " 	C.CUENTA "
				+ " 	, DECODE(SUBSTR(C.SSSCTA,1,1)||'000','1000', 'SERVICIOS PERSONALES ','2000','MATERIALES Y SUMINISTROS','3000','SERVICIOS GENERALES','4000','TRANSFERENCIAS','5000','MUEBLES E INMUEBLES','6000','INVERSION PUBLICA','7000','INVERSIONES FINANCIERAS','8000','PART. Y APORTACIONES','9000','DEUDA PUBLICA',C.NOMCTA)  AS CONCEPTO "
				+ " 	, NVL(C.SALINI,0) SALINI " + " 	, C.SSSCTA " + " 	, SUBSTR(C.SSSCTA,1,1)||'000' as GRUPO "
				+ " 	, " + mesAnterior + " as MES_ANT " + " 	, " + acomunlado + " as MES_ACT " + " 	, " + acomunlado
				+ " AS ACUMULADO " + " FROM " + " 	CUENTA C " + " WHERE " + " 	C.CUENTA BETWEEN '5100' AND '5700' "
				+ " 	AND C.CUENTA<>'5500'  " + " 	AND C.IDSECTOR = " + sector + " ) " + " WHERE GRUPO <>' 000' "
				+ " GROUP BY  CUENTA,CONCEPTO, SALINI, GRUPO " + " 	ORDER BY GRUPO ASC ");

		System.out.println(sSql.toString());
		return sSql.toString();
	}

	/**
	 * Generate query egresos 5002.
	 *
	 * @param mes    the mes
	 * @param sector the sector
	 * @return the string
	 */
	public String GenerateQueryEgresos5002(Integer mes, Integer sector) {

		StringBuilder sSql = new StringBuilder();
		StringBuilder anterior = new StringBuilder();
		StringBuilder acomunlado = new StringBuilder().append(" (SUM(C.CARGOS_1) - SUM(C.ABONOS_1)) ");
		Integer mes2;

		if (mes == 1) {
			anterior.append(" SUM(C.SALINI)   ");
		} else {
			for (int y = 1; y < mes; y++) {

				anterior.append("SUM(C.CARGOS_" + y + " - C.ABONOS_" + y + ") + ");

			}
		}

		if (mes <= 1) {
			mes2 = 1;
		} else {
			mes2 = mes - 1;
		}

		for (int i = 2; i <= mes; i++) {

			acomunlado.append("+ (SUM(C.CARGOS_" + i + ") - SUM(C.ABONOS_" + i + ")) ");
		}

		sSql.append(
				" SELECT C.CUENTA , NVL(C.SCTA,'') SCTA , CASE WHEN NVL(SCTA,'')<>'' THEN C.NOMCTA ELSE 'EGRESOS EXTRAORDINARIOS'  END NOMBRE "
						+ " , (" + anterior.substring(0, anterior.length() - 2) + ") MESANT " + " , SUM(C.CARGOS_" + mes
						+ ") - SUM(C.ABONOS_" + mes + ") MESACT " + " , " + acomunlado + " MESACUM  "
						+ " FROM CUENTA C WHERE C.CUENTA='5500' AND C.SSCTA = '' AND C.IDSECTOR = " + sector + " "
						+ " GROUP BY C.CUENTA , C.SCTA, C.NOMCTA ORDER BY 1,2 ");

		System.out.println(sSql.toString());
		return sSql.toString();
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}

	/**
	 * Gets the tc mes repository.
	 *
	 * @return the tc mes repository
	 */
	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	/**
	 * Sets the tc mes repository.
	 *
	 * @param tcMesRepository the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	public TcEntidadAdministrativaRepository getEntidadAdministrativaRepository() {
		return entidadAdministrativaRepository;
	}

	public void setEntidadAdministrativaRepository(TcEntidadAdministrativaRepository entidadAdministrativaRepository) {
		this.entidadAdministrativaRepository = entidadAdministrativaRepository;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

	/** The stream. */
	InputStream stream = null;

	/** The out. */
	Map<String, Object> out;
	StreamedContent streamedContent = null;

	public StreamedContent getPsFileTxt() {

		out = new HashMap<String, Object>();
		out = executePlService.callProcedure(NAME_PROCEDURE, parameters());

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(new File(out.get("O_FULL_PATH").toString()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			streamedContent = StreamedContents.of(stream, "application/txt", out.get("O_FILE_NAME").toString(), null);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSJ_ERROR").toString());
		}

		return streamedContent;
	}

	public void setPsFileTxt(StreamedContent psFileTxt) {
		this.psFileTxt = psFileTxt;
	}

	public SqlParameterSource parameters() {
		Integer idSector = this.getUserDetails().getIdSector();
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("i_Query_Ingresos", this.generateQueryIngresos(mes, idSector));

		parametros.addValue("i_Query_Egresos", this.generateQueryEgresos(mes, idSector));
		parametros.addValue("i_Query_Egr5002", this.GenerateQueryEgresos5002(mes, idSector));
		parametros.addValue("i_Mes", mes);

		return parametros;

	}

	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
	}

}
