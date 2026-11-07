package com.gem.sistema.web.bean;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcEntidadAdministrativa;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcEntidadAdministrativaRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.util.ConstantsClaveEnnum;

/**
 * The Class RF01014MB.
 * @author Alfredo
 * 
 * Date: 07/04/2021
 * Changes:Se ajusta Reporte en la seccion de Ingresos, se elimina la columna de acumulado, 
 *		   Se ajusta diseño del reporte
 */
@ManagedBean(name = "rF01014MB")
@ViewScoped
public class RF01014MB extends BaseDirectReport {

	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";

	/** The mes. */
	private Integer mes;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcEntidadAdministrativaRepository}")
	private TcEntidadAdministrativaRepository entidadAdministrativaRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

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
	@ManagedProperty("#{validatePolicyService}")
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
		LOGGER.info(":: En postconsruct RF010.1.4 ");
		jasperReporteName = "RF010.1.4";
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
		TcEntidadAdministrativa entidadAdministrativa = entidadAdministrativaRepository
				.findByClave(conctb.getClave().substring(0, 1));
		TrPuestoFirma firma = new TrPuestoFirma();
		
		endFilename = "EDO ACT "+ mes + conctb.getAnoemp()+".pdf";

		paramsReport.put("AN", conctb.getAnoemp());
		paramsReport.put("USUARIO", this.getUserDetails().getUsername());
		paramsReport.put("MES", mes);
		paramsReport.put("LOGO", idSector == 1 ? conctb.getImagePathRigth() : conctb.getImagePathLeft());
		paramsReport.put("SECTOR", idSector);
		paramsReport.put("entidadName", conctb.getNomDep().toUpperCase());
		paramsReport.put("municipioNo", conctb.getClave().substring(1));
		paramsReport.put("tipoOrganismo", entidadAdministrativa.getDescripcion().toUpperCase());

		if (idSector == 1) {
			switch (conctb.getClave().substring(0, 1)) {
			case "0":
				paramsReport.put("pNoFirmas", 3);
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
						ConstantsClaveEnnum.CLAVE_F01.getValue());
				paramsReport.put("firmaP1", firma.getPuesto().getPuesto());
				paramsReport.put("firmaN1", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
						ConstantsClaveEnnum.CLAVE_F03.getValue());
				paramsReport.put("firmaP2", firma.getPuesto().getPuesto());
				paramsReport.put("firmaN2", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
						ConstantsClaveEnnum.CLAVE_F02.getValue());
				paramsReport.put("firmaP3", firma.getPuesto().getPuesto());
				paramsReport.put("firmaN3", firma.getNombre());
				break;
			case "2": // ODAS
				paramsReport.put("pNoFirmas", 3);
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
						ConstantsClaveEnnum.CLAVE_F10.getValue());
				paramsReport.put("firmaP1", firma.getPuesto().getPuesto());
				paramsReport.put("firmaN1", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
						ConstantsClaveEnnum.CLAVE_F11.getValue());
				paramsReport.put("firmaP2", firma.getPuesto().getPuesto());
				paramsReport.put("firmaN2", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
						ConstantsClaveEnnum.CLAVE_F12.getValue());
				paramsReport.put("firmaP3", firma.getPuesto().getPuesto());
				paramsReport.put("firmaN3", firma.getNombre());
				break;
			case "3":
				paramsReport.put("pNoFirmas", 3);
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
						ConstantsClaveEnnum.CLAVE_F07.getValue());
				paramsReport.put("firmaP1", firma.getPuesto().getPuesto());
				paramsReport.put("firmaN1", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
						ConstantsClaveEnnum.CLAVE_F08.getValue());
				paramsReport.put("firmaP2", firma.getPuesto().getPuesto());
				paramsReport.put("firmaN2", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
						ConstantsClaveEnnum.CLAVE_F09.getValue());
				paramsReport.put("firmaP3", firma.getPuesto().getPuesto());
				paramsReport.put("firmaN3", firma.getNombre());
				break;
			case "4":
				paramsReport.put("pNoFirmas", 2);
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
						ConstantsClaveEnnum.CLAVE_F20.getValue());
				paramsReport.put("firmaP1", firma.getPuesto().getPuesto());
				paramsReport.put("firmaN1", firma.getNombre());
				firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
						ConstantsClaveEnnum.CLAVE_F21.getValue());
				paramsReport.put("firmaP3", firma.getPuesto().getPuesto());
				paramsReport.put("firmaN3", firma.getNombre());
				break;
			}
		} else {
			paramsReport.put("pNoFirmas", 3);
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F04.getValue());
			paramsReport.put("firmaP1", firma.getPuesto().getPuesto());
			paramsReport.put("firmaN1", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F05.getValue());
			paramsReport.put("firmaP2", firma.getPuesto().getPuesto());
			paramsReport.put("firmaN2", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F27.getValue());
			paramsReport.put("firmaP3", firma.getPuesto().getPuesto());
			paramsReport.put("firmaN3", firma.getNombre());
		}

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

			RequestContext.getCurrentInstance().execute(DOWNLOAD_XLS);
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
				+ " 	C.CUENTA "
				+ " 	, C.NOMCTA AS CONCEPTO "
				+ " 	, substr(C.SCTA,8,3) AS GRUPO  " + " 	, " + mesAnterior + " AS MES_ANT " + " 	, " + mesActual
				+ " AS MES_ACT " + " 	, " + acomunlado + " AS ACUMULADO " + " FROM " + " 	CUENTA C " + " WHERE "
				+ " 	C.CUENTA BETWEEN '4000' AND '4399' AND " + " 	SUBSTR(C.CUENTA,4,1) = '0' AND "
				+ " 	C.SCTA='' AND C.SSCTA='' AND " + " 	C.IDSECTOR = " + sector + " ) "
				+ " GROUP BY CUENTA, GRUPO, CONCEPTO " + " ORDER BY CUENTA,GRUPO ASC   ");

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
				+ " 	, " + mesAnterior + " as MES_ANT " + " 	, " + mesActual + " as MES_ACT " + " 	, " + acomunlado
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
		StringBuilder acomunlado = new StringBuilder().append(" (SUM(C.CARGOS_1) - SUM(C.ABONOS_1)) ");
		Integer mes2;

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
						+ " , CASE WHEN " + mes + ">1 THEN SUM(C.CARGOS_" + mes2 + ") - SUM(C.ABONOS_" + mes2
						+ ")  ELSE 0.00 END  MESANT " + " , SUM(C.CARGOS_" + mes + ") - SUM(C.ABONOS_" + mes
						+ ") MESACT " + " , " + acomunlado + " MESACUM  "
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

}
