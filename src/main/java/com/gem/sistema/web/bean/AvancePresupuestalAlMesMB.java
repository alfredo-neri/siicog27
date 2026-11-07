package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.UtilFront;

@ManagedBean(name = "avancePresupuestalAlMesMB")
@ViewScoped
public class AvancePresupuestalAlMesMB extends BaseDirectReport {

	private static final String DOWNLOAD_PDF = " jQuery('#form1\\\\:downPdf').click();";
	private Integer numero;
	private Integer mesInicial;
	private Integer mesFinal;

	private List<Integer> listMes;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct polizaDetallada ");
		jasperReporteName = "avancePresupuestalAlMes";
		endFilename = jasperReporteName + ".pdf";
		listMes = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++)
			listMes.add(i);
		mesInicial = listMes.get(0);
		mesFinal = listMes.get(0);
	}

	public Map<String, Object> getParametersReports() {
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		paramsReport.put("pAnio", conctb.getAnoemp());
		paramsReport.put("pDiaInicial", getLastDayByAnoEmp(mesInicial, conctb.getAnoemp()));
		paramsReport.put("pDiaFinal", getLastDayByAnoEmp(mesFinal, conctb.getAnoemp()));
		paramsReport.put("USUARIO", getUserDetails().getUsername());
		paramsReport.put("img1", conctb.getImagePathLeft());
		paramsReport.put("img2", conctb.getImagePathRigth());
		paramsReport.put("mesInicial", mesInicial);
		paramsReport.put("mesFinal", mesFinal);
		paramsReport.put("pMesLetraInicial",
				tcPeriodoRepositoy.findByTipoPeriodoAndPeriodo(1, mesInicial).getDescripcion().toUpperCase());
		paramsReport.put("pMesLetraFinal",
				tcPeriodoRepositoy.findByTipoPeriodoAndPeriodo(1, mesFinal).getDescripcion().toUpperCase());
		paramsReport.put("idSector", idSector);
		paramsReport.put("nameDep", conctb.getNomDep());
		paramsReport.put("query", getQuery());
		return paramsReport;
	}

	public void downloadPdf() {
		if (this.validatePolicyService.isOpenMonth(Long.valueOf(mesInicial).intValue(), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Long.valueOf(mesInicial).intValue(), null,
					this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Long.valueOf(mesInicial).intValue(), null,
					this.getUserDetails().getIdSector());

			RequestContext.getCurrentInstance().execute(DOWNLOAD_PDF);
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(mesInicial, null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mesInicial, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mesInicial, null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
			;
		}
	}

	public String getQuery() {
		StringBuilder query = new StringBuilder("");
		String autoMes = "";
		for (int i = mesInicial; i <= mesFinal; i++) {
			autoMes += "TW.AUTORIZADO_" + i + "+ ";
		}
		autoMes = autoMes.substring(0, autoMes.length() - 2);
		query.append(
				"SELECT 	T2.CLAVE CLAVE,T2.NOMBRE,T2.ANUAL AUTO_ANUAL,				        "
						+ "	T2.MES AUTO_MES,	T2.MONTO,	T2.MES - T2.MONTO NO_RECAUDADO,     "
						+ "	T2.ANUAL - T2.MES RECAUDAR_ANUAL                                    "
						+ "FROM(SELECT 	T1.CLAVE,	T1.NOMBRE,                                  "
						+ "		NVL(MAX(T1.AUTO_ANUAL),0) ANUAL,                                "
						+ "     NVL(MAX(T1.AUTO_MES),0) MES,                                    "
						+ "		SUM(NVL(D.MONTO,0)) MONTO                                       "
						+ "	FROM (SELECT I.ID, I.CLAVE,	I.NOMBRE,                               "
						+ "			TW.AUTORIZADO_1+ TW.AUTORIZADO_2+ TW.AUTORIZADO_3+          "
						+ "		    TW.AUTORIZADO_4+ TW.AUTORIZADO_5+ TW.AUTORIZADO_6+          "
						+ "         TW.AUTORIZADO_7+ TW.AUTORIZADO_8+ TW.AUTORIZADO_9+          "
						+ "         TW.AUTORIZADO_10+ TW.AUTORIZADO_11+ TW.AUTORIZADO_12 AUTO_ANUAL, "
						+ autoMes + " AUTO_MES         						                    "
						+ "		FROM TC_INGRESOS_PROPIOS I LEFT JOIN TW_METAS_INGRESO TW        "
						+ "          ON I.CLAVE = TW.CLAVE_INGRESO                              "
						+ "	) T1 LEFT JOIN TW_INGRESO_PROPIOS_DETALLE D                         "
						+ "               ON	D.ID_INGRESO=T1.ID AND D.MESPOL BETWEEN         "
						+ mesInicial + " AND " + mesFinal + "	GROUP BY T1.CLAVE,T1.NOMBRE     "
						+ ")T2 ORDER BY CLAVE                                                   ");
		System.out.println("query::" + query.toString());
		return query.toString();
	}

	public void outMsn(String msn) {
		UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, msn, "");
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getMesInicial() {
		return mesInicial;
	}

	public void setMesInicial(Integer mesInicial) {
		this.mesInicial = mesInicial;
	}

	public Integer getMesFinal() {
		return mesFinal;
	}

	public void setMesFinal(Integer mesFinal) {
		this.mesFinal = mesFinal;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public List<Integer> getListMes() {
		return listMes;
	}

	public void setListMes(List<Integer> listMes) {
		this.listMes = listMes;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	public TcPeriodoRepositoy getTcPeriodoRepositoy() {
		return tcPeriodoRepositoy;
	}

	public void setTcPeriodoRepositoy(TcPeriodoRepositoy tcPeriodoRepositoy) {
		this.tcPeriodoRepositoy = tcPeriodoRepositoy;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

}
