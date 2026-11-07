package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilReport.getNameFileReport;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * @author Alfredo Neri
 * @Timeline <strong>Date: </strong>27/08/2021 <strong> Changes: </strong> Se
 *           ajusta reporte a nuevos lineamientos, Se implementa servicio de
 *           firmas dinamico<br>
 * @Sector Municipal
 */
@ManagedBean(name = "cPEstadoFlujosEfectivoMB")
@ViewScoped
public class CPEstadoFlujosEfectivoMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_CP_EDO_FLU_EFE";

	private Integer noDecimales;
	private Integer pesos;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		noDecimales = 2;
		pesos = 1;

		jasperReporteName = "CPEstadoFlujosEfectivo";
		endFilename = jasperReporteName + ".pdf";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(conctb, reporte);

		parameters.put("decimalFormat", "%,." + noDecimales + "f");
		parameters.put("imagenPathLeft", conctb.getImagePathRigth());
		parameters.put("imagenPathRigth", StringUtils.EMPTY);
		parameters.put("nombreEntidad", conctb.getNomDep());
		parameters.put("query", this.generaQuery());
		parameters.put("pesos", pesos);
		parameters.put("year", conctb.getAnoemp());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parameters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private String generaQuery() {
		StringBuilder query = new StringBuilder();

		query.append("SELECT CLVFLU, NOMFLU, (MESFLU_1 + MESFLU_2 + MESFLU_3 + MESFLU_4 + \r\n"
				+ "						MESFLU_5 + MESFLU_6 + MESFLU_7 + MESFLU_8 + \r\n"
				+ "						MESFLU_9 + MESFLU_10 + MESFLU_11 + MESFLU_12) MONTO_CURRENT, \r\n"
				+ "						MESFLU_14 MONTO_LAST\r\n" + "		FROM CATFLU \r\n" + " WHERE IDSECTOR = "
				+ this.getUserDetails().getIdSector() + " ORDER BY CLVFLU");

		if (pesos != 1) {
			query.insert(0,
					"SELECT CLVFLU, NOMFLU, (MONTO_CURRENT / 1000) MONTO_CURRENT, (MONTO_LAST / 1000) MONTO_LAST FROM ( ");
			query.append(" )T1");
		}

		return query.toString();
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public Integer getNoDecimales() {
		return noDecimales;
	}

	public void setNoDecimales(Integer noDecimales) {
		this.noDecimales = noDecimales;
	}

	public Integer getPesos() {
		return pesos;
	}

	public void setPesos(Integer pesos) {
		this.pesos = pesos;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
