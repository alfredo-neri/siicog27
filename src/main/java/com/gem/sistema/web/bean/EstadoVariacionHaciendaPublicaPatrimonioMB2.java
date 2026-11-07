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

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcEntidadAdministrativa;
import com.gem.sistema.business.domain.TcMes;
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

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDay;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadoVariacionHaciendaPublicaPatrimonioMB.
 */
@Component("estadoVariacionHaciendaPublicaPatrimonioMB2")
@Scope("view")
public class EstadoVariacionHaciendaPublicaPatrimonioMB2 extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_EDO_VAR_HAC_PUB";
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	private static final String NAME_PROCEDURE = "SP_GENERA_TXT_EVHP";
	/** The mes. */
	private Integer mes;
	
	/** The conctb. */
	private Conctb conctb;
	
	private StreamedContent psFileTxt;

	/** The conctb repository. */
	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The tc mes repository. */
	@Value("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@Value("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;
	

	@Value("#{tcEntidadAdministrativaRepository}")
	private TcEntidadAdministrativaRepository entidadAdministrativaRepository;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/** The execute pl service. */
	@Value("#{executePlService}")
	private ExecutePlService executePlService;

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

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct EstadoVariacionHaciendaPublicaPatrimonioMB ");
		// reportId = 2;
		jasperReporteName = "HaciendaPublicaPatrimonio2";
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		TcMes descriMes = tcMesRepository.findByMes(StringUtils.leftPad(mes.toString(), 2, "0"));
		endFilename = "EDO VAR HAC PUB " + StringUtils.leftPad(mes.toString(), 2, "0") + conctb.getAnoemp() + ".pdf";
		parameters.put("pMunicipio", conctb.getNomDep());
		parameters.put("pImg", idSector==1? conctb.getImagePathRigth(): conctb.getImagePathLeft());
		parameters.put("pAn", conctb.getAnoemp());
		parameters.put("pLastDay", getLastDay(mes));
		parameters.put("pDesMes", descriMes.getDescripcion());
		parameters.put("pNoMun", conctb.getClave());
		parameters.put("pIdSector", idSector);
		
		if (idSector == 1) {
			
			TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);
			TcEntidadAdministrativa entidadAdministrativa = entidadAdministrativaRepository
					.findByClave(conctb.getClave().substring(0, 1));

			parameters.put("pTipo", entidadAdministrativa.getDescripcion());
			parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

			endFilename = getNameFileReport(mes, conctb, reporte);
			switch (conctb.getClave().substring(0, 1)) {
			case "0":
				parameters.put("pNoFirmas",3);
				parameters.put("pTipo", "Ayuntamiento");
				firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
						ConstantsClaveEnnum.CLAVE_F01.getValue());
				parameters.put("pL3", firma.getPuesto().getPuesto());
				parameters.put("pN3", firma.getNombre());
				firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
						ConstantsClaveEnnum.CLAVE_F03.getValue());
				parameters.put("pL5", firma.getPuesto().getPuesto());
				parameters.put("pN5", firma.getNombre());
				firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
						ConstantsClaveEnnum.CLAVE_F02.getValue());
				parameters.put("pL4", firma.getPuesto().getPuesto());
				parameters.put("pN4", firma.getNombre());
				break;
			case "2":
				parameters.put("pNoFirmas",3);
				parameters.put("pTipo", "Organismo de agua");
				firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
						ConstantsClaveEnnum.CLAVE_F10.getValue());
				parameters.put("pL3", firma.getPuesto().getPuesto());
				parameters.put("pN3", firma.getNombre());
				firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
						ConstantsClaveEnnum.CLAVE_F11.getValue());
				parameters.put("pL5", firma.getPuesto().getPuesto());
				parameters.put("pN5", firma.getNombre());
				firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
						ConstantsClaveEnnum.CLAVE_F12.getValue());
				parameters.put("pL4", firma.getPuesto().getPuesto());
				parameters.put("pN4", firma.getNombre());
				break;
			case "3":
				parameters.put("pNoFirmas",3);
				parameters.put("pTipo", "Dif municipal");
				firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
						ConstantsClaveEnnum.CLAVE_F07.getValue());
				parameters.put("pL3", firma.getPuesto().getPuesto());
				parameters.put("pN3", firma.getNombre());
				firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
						ConstantsClaveEnnum.CLAVE_F08.getValue());
				parameters.put("pL5", firma.getPuesto().getPuesto());
				parameters.put("pN5", firma.getNombre());
				firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
						ConstantsClaveEnnum.CLAVE_F09.getValue());
				parameters.put("pL4", firma.getPuesto().getPuesto());
				parameters.put("pN4", firma.getNombre());
				break;
			case "4":
				parameters.put("pNoFirmas",2);
				parameters.put("pTipo", "Organismo del deporte");
				firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
						ConstantsClaveEnnum.CLAVE_F20.getValue());
				parameters.put("pL3", firma.getPuesto().getPuesto());
				parameters.put("pN3", firma.getNombre());
				firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
						ConstantsClaveEnnum.CLAVE_F21.getValue());
				parameters.put("pL4", firma.getPuesto().getPuesto());
				parameters.put("pN4", firma.getNombre());
				break;
			}

		} else {
			parameters.put("pNoFirmas",3);
			parameters.put("pTipo", "");
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F04.getValue());
			parameters.put("pL3", firma.getPuesto().getPuesto());
			parameters.put("pN3", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F05.getValue());
			parameters.put("pL5", firma.getPuesto().getPuesto());
			parameters.put("pN5", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F27.getValue());
			parameters.put("pL4", firma.getPuesto().getPuesto());
			parameters.put("pN4", firma.getNombre());
		}
				
		parameters.put("sSqlPatrimonio", this.generaQueryPatrimonio(this.getUserDetails().getIdSector(), mes));
		parameters.put("sSql3211", this.generaQuery3211(this.getUserDetails().getIdSector(), mes));

		return parameters;
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
	 * Gets the conctb.
	 *
	 * @return the conctb
	 */
	public Conctb getConctb() {
		return conctb;
	}

	/**
	 * Sets the conctb.
	 *
	 * @param conctb the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
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

	/**
	 * Genera query patrimonio.
	 *
	 * @param idsector the idsector
	 * @param mes      the mes
	 * @return the string
	 */
	public String generaQueryPatrimonio(Integer idsector, Integer mes) {
		StringBuilder cargosPant = new StringBuilder();
		StringBuilder abonosPant = new StringBuilder();
		StringBuilder cargosPact = new StringBuilder();
		StringBuilder abonosPact = new StringBuilder();
		StringBuilder sSqlP = new StringBuilder();

		if (mes == 1) {
			cargosPant.append(" 0.00");
			abonosPant.append(" 0.00");
		} else {

			for (int i = 1; i < mes; i++) {
				cargosPant.append(" C.CARGOS_" + i + "+");
				abonosPant.append(" C.ABONOS_" + i + "+");
			}
		}

		for (int i = 1; i <= mes; i++) {
			cargosPact.append(" C.CARGOS_" + i + "+");
			abonosPact.append(" C.ABONOS_" + i + "+");
		}

		sSqlP.append("SELECT T2.NATCTA,").append(" T2.GRUPO,").append(" T2.CUENTA,").append(" T2.NOMCTA,")
				.append(" T2.SALINI,")
				.append(" CASE WHEN T2.CUENTA = '1115' OR T2.CUENTA='1162' THEN T2.SALINI+T2.CARGOS_ANT-T2.ABONOS_ANT")
				.append(" ELSE T2.MESANT").append(" END MESANT,")
				.append(" CASE WHEN T2.CUENTA = '1115' OR  T2.CUENTA='1162' THEN T2.SALINI+T2.CARGOS_ACT-T2.ABONOS_ACT")
				.append(" ELSE T2.MESACT").append(" END MESACT").append(" FROM (SELECT T1.NATCTA,").append(" T1.GRUPO,")
				.append(" T1.CUENTA,").append(" T1.NOMCTA,").append(" T1.SALINI,").append(" T1.CARGOS_ANT,")
				.append(" T1.ABONOS_ANT,").append(" T1.CARGOS_ACT,").append(" T1.ABONOS_ACT,")
				.append(" CASE WHEN T1.NATCTA='D' THEN T1.SALINI+CARGOS_ANT-ABONOS_ANT")
				.append(" ELSE T1.SALINI-CARGOS_ANT+ABONOS_ANT").append(" END MESANT,")
				.append(" CASE WHEN T1.NATCTA='D' THEN T1.SALINI+CARGOS_ACT-ABONOS_ACT")
				.append(" ELSE T1.SALINI-CARGOS_ACT+ABONOS_ACT").append(" END MESACT")
				.append(" FROM (SELECT M.NATCTA NATCTA,").append(" SUBSTR(C.CUENTA,1,1) GRUPO,")
				.append(" C.CUENTA CUENTA,").append(" C.NOMCTA NOMCTA,").append(" C.SALINI SALINI,").append(" SUM(")
				.append(cargosPant.substring(1, cargosPant.length() - 1)).append(" ) CARGOS_ANT, ").append(" SUM(")
				.append(abonosPant.substring(1, abonosPant.length() - 1)).append("  ) ABONOS_ANT, ").append(" SUM(")
				.append(cargosPact.substring(1, cargosPact.length() - 1)).append("  ) CARGOS_ACT,").append(" SUM(")
				.append(abonosPact.substring(1, abonosPact.length() - 1)).append(" ) ABONOS_ACT")
				.append(" FROM CUENTA C,").append(" MAYCTA M").append(" WHERE C.CUENTA = M.CUENTA")
				.append(" AND C.CUENTA BETWEEN '3000' AND '3399'").append(" AND C.SCTA=''").append(" AND C.NOTCTA=0")
				.append(" AND C.IDSECTOR=" + idsector).append(" GROUP BY M.NATCTA,").append(" SUBSTR(C.CUENTA,1,1),")
				.append(" C.CUENTA,").append(" C.NOMCTA,").append(" C.SALINI").append(" ORDER BY C.CUENTA)T1)T2");

		return sSqlP.toString();
	}

	/**
	 * Genera query 3211.
	 *
	 * @param idsector the idsector
	 * @param mes      the mes
	 * @return the string
	 */
	public String generaQuery3211(Integer idsector, Integer mes) {
		StringBuilder cargonant = new StringBuilder();
		StringBuilder abonosant = new StringBuilder();
		StringBuilder cargosact = new StringBuilder();
		StringBuilder abonosact = new StringBuilder();
		StringBuilder sSql = new StringBuilder();

		if (mes == 1) {
			cargonant.append(" 0.00");
			abonosant.append(" 0.00");
		} else {

			for (int i = 1; i < mes; i++) {
				cargonant.append(" C.CARGOS_" + i + "+");
				abonosant.append(" C.ABONOS_" + i + "+");
			}
		}

		for (int i = 1; i <= mes; i++) {
			cargosact.append(" C.CARGOS_" + i + "+");
			abonosact.append(" C.ABONOS_" + i + "+");
		}

		sSql.append("SELECT (SUM(T3.VAN4)-SUM(T3.VAN5)) TOTAL_ANT,").append(" (SUM(T3.VACT4)-SUM(T3.VACT5)) TOTAL_ACT")
				.append(" FROM (SELECT ").append(" CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMANT) ELSE 0.00 END VAN4,")
				.append(" CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMANT) ELSE 0.00 END VAN5,")
				.append(" CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMACT) ELSE 0.00 END VACT4,")
				.append(" CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMACT)ELSE 0.00 END VACT5")
				.append(" FROM(SELECT  T1.GRUPO, ").append(" T1.SALINI,")
				.append(" CASE WHEN T1.GRUPO  ='4' THEN (T1.SALINI+ABONOS_ANT-CARGOS_ANT)")
				.append(" ELSE (T1.SALINI-ABONOS_ANT+CARGOS_ANT)").append(" END VMANT,")
				.append(" CASE WHEN T1.GRUPO  ='4' THEN (T1.SALINI+ABONOS_ACT-CARGOS_ACT)")
				.append(" ELSE (T1.SALINI-ABONOS_ACT+CARGOS_ACT)").append(" END VMACT")
				.append(" FROM (SELECT SUBSTR(C.CUENTA,1,1) GRUPO, ").append(" C.CUENTA CUENTA ,")
				.append(" C.SALINI SALINI,").append(" SUM(").append(cargonant.substring(1, cargonant.length() - 1))
				.append(") CARGOS_ANT,").append(" SUM(").append(abonosant.substring(1, abonosant.length() - 1))
				.append(") ABONOS_ANT,").append(" SUM(").append(cargosact.substring(1, cargosact.length() - 1))
				.append(") CARGOS_ACT,").append(" SUM(").append(abonosact.substring(1, abonosact.length() - 1))
				.append(") ABONOS_ACT").append(" FROM CUENTA C").append(" WHERE C.IDSECTOR=" + idsector)
				.append(" AND (((C.CUENTA >='4100' AND C.CUENTA<='4399' AND SUBSTR(C.CUENTA,4,1)<>'0')")
				.append(" OR (C.CUENTA IN ('5100','5200','5300','5400','5600','5700')))").append(" AND C.SCTA ='')")
				.append(" OR (C.CUENTA ='5500' AND C.SCTA<>'' AND C.SSCTA ='' AND C.NOTCTA=0)")
				.append(" GROUP BY C.CUENTA,").append(" C.SALINI").append(" ORDER BY C.CUENTA)T1)T2")
				.append(" GROUP BY T2.GRUPO )T3");

		return sSql.toString();

	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
	}

	public TcEntidadAdministrativaRepository getEntidadAdministrativaRepository() {
		return entidadAdministrativaRepository;
	}

	public void setEntidadAdministrativaRepository(TcEntidadAdministrativaRepository entidadAdministrativaRepository) {
		this.entidadAdministrativaRepository = entidadAdministrativaRepository;
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

	public SqlParameterSource parameters() {
		Integer idSector = this.getUserDetails().getIdSector();
		MapSqlParameterSource parametros = new MapSqlParameterSource();

		parametros.addValue("i_Query_Patrimonio", this.generaQueryPatrimonio(idSector, mes));

		parametros.addValue("i_Query_3211", this.generaQuery3211(idSector, mes));

		parametros.addValue("i_Mes", mes);

		return parametros;

	}

	public void setPsFileTxt(StreamedContent psFileTxt) {
		this.psFileTxt = psFileTxt;
	}
}
