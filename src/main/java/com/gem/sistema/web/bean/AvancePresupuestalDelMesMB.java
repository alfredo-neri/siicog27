package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@Component("avancePresupuestalDelMesMB")
@Scope("view")
public class AvancePresupuestalDelMesMB extends BaseDirectReport {

	private static final String DOWNLOAD_PDF = " jQuery('#form1\\\\:downPdf').click();";
	private Integer mes;
	private List<Integer> listMes;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@Value("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

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

	@PostConstruct
	public void init() {
		jasperReporteName = "avancePresupuestalDelMes";
		endFilename = jasperReporteName + ".pdf";
		listMes = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++)
			listMes.add(i);
		mes = listMes.get(0);
	}

	public Map<String, Object> getParametersReports() {
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		paramsReport.put("pAnio", conctb.getAnoemp());
		paramsReport.put("pDia", getLastDayByAnoEmp(mes, conctb.getAnoemp()));
		paramsReport.put("USUARIO", getUserDetails().getUsername());
		paramsReport.put("img1", conctb.getImagePathLeft());
		paramsReport.put("img2", conctb.getImagePathRigth());
		paramsReport.put("mes", mes);
		paramsReport.put("pMesLetra",
				tcPeriodoRepositoy.findByTipoPeriodoAndPeriodo(1, mes).getDescripcion().toUpperCase());
		paramsReport.put("idSector", idSector);
		paramsReport.put("nameDep", conctb.getNomDep());
		paramsReport.put("query", getQuery());

		return paramsReport;
	}

	public void downloadPdf() {
		if (this.validatePolicyService.isOpenMonth(Long.valueOf(mes).intValue(), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Long.valueOf(mes).intValue(), null,
					this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Long.valueOf(mes).intValue(), null,
					this.getUserDetails().getIdSector());

			PrimeFaces.current().executeScript(DOWNLOAD_PDF);
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

	private String getQuery() {
		StringBuilder query = new StringBuilder("");
		query.append(
				"SELECT 	T2.CLAVE CLAVE,T2.NOMBRE,T2.ANUAL AUTO_ANUAL,					   "
						+ "	T2.MES AUTO_MES,	T2.MONTO,	T2.MES - T2.MONTO NO_RECAUDADO     "
						+ "FROM(SELECT 	T1.CLAVE,	T1.NOMBRE,                                 "
						+ "				NVL(MAX(T1.AUTO_ANUAL), 0) ANUAL,                      "
						+ "             NVL(MAX(T1.AUTO_MES), 0)   MES,                        "
						+ "             SUM(NVL(D.MONTO, 0))       MONTO                       "
						+ "	FROM (SELECT I.ID, I.CLAVE,	I.NOMBRE,                              "
						+ "			TW.AUTORIZADO_1+ TW.AUTORIZADO_2+ TW.AUTORIZADO_3+ 	       "
						+ "		    TW.AUTORIZADO_4+ TW.AUTORIZADO_5+ TW.AUTORIZADO_6+         "
						+ "		    TW.AUTORIZADO_7+ TW.AUTORIZADO_8+ TW.AUTORIZADO_9+         "
						+ "         TW.AUTORIZADO_10+ TW.AUTORIZADO_11+ TW.AUTORIZADO_12 AUTO_ANUAL, "
						+ "         TW.AUTORIZADO_"+mes+" AUTO_MES           "
						+ "       FROM TC_INGRESOS_PROPIOS I                 "
						+ "            LEFT JOIN TW_METAS_INGRESO TW ON I.CLAVE = TW.CLAVE_INGRESO   "
						+ "	) T1 LEFT JOIN TW_INGRESO_PROPIOS_DETALLE D                              "
						+ "                ON	D.ID_INGRESO=T1.ID AND D.MESPOL ="+ mes 
						+ "	GROUP BY T1.CLAVE, T1.NOMBRE    "
						+ ")T2 ORDER BY CLAVE               ");
		
		System.out.println("Query::" + query.toString());
		return query.toString();
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
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

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

}
