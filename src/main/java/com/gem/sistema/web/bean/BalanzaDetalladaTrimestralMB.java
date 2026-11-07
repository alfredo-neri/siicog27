package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcEntidadAdministrativa;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcEntidadAdministrativaRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * @author Alfredo
 * @Date 09/04/2021
 * @Changes Se crea el reporte junto con los componentes para la balanza
 *          Trimestral
 * @Sector Municipal
 */
@ManagedBean
@ViewScoped
public class BalanzaDetalladaTrimestralMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_BAL_COM_DET_TRI";
	private static final Integer TIPO_PERIODO = 3;

	private List<TcPeriodo> listMeses;
	private Integer trimestre;
	private Integer nivel;
	private String ctaInical;
	private String ctaFinal;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@ManagedProperty("#{tcEntidadAdministrativaRepository}")
	private TcEntidadAdministrativaRepository entidadAdministrativaRepository;

	@PostConstruct
	public void init() {
		jasperReporteName = "BalanzaDetalladaTrimestral";
		endFilename = jasperReporteName + ".pdf";
		listMeses = periodoRepositoy.findByTipoPeriodo(TIPO_PERIODO);

		nivel = 1;
		ctaInical = "1000";
		ctaFinal = "9999";

		if (!listMeses.isEmpty()) {
			trimestre = listMeses.get(0).getPeriodo();
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Integer mesFinal = trimestre * 3;

		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);
		TcPeriodo periodo = periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mesFinal);
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		
		endFilename = getNameFileReport(trimestre, conctb, reporte);

		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("sector", idSector);
		paramsReport.put("trimestre", trimestre);
		paramsReport.put("imagenPath", conctb.getImagePathLeft());
		paramsReport.put("tipoOrganismo", StringUtils.EMPTY);
		paramsReport.put("numeroOrganismo", StringUtils.EMPTY);
		paramsReport.put("nombreOrganismo", conctb.getNomDep());
		paramsReport.put("lastDay", getLastDayByAnoEmp(mesFinal, conctb.getAnoemp()));
		paramsReport.put("mesName", periodo.getDescripcion().toUpperCase());
		paramsReport.put("queryWhere", this.generaWhere());
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return paramsReport;
	}

	private String generaWhere() {

		StringBuilder where = new StringBuilder();

		switch (nivel) {
		case 1:
			where.append(" SCTA = '' AND ");
			break;
		case 2:
			where.append(" SSCTA = '' AND ");
			break;
		case 3:
			where.append(" SSSCTA = '' AND ");
			break;
		case 4:
			where.append(" SSSSCTA = '' AND ");
			break;

		default:
			break;
		}

		where.append(" CUENTA BETWEEN '" + ctaInical + "' AND '" + ctaFinal + "' AND ");

		return where.toString();
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TcPeriodo> getListMeses() {
		return listMeses;
	}

	public void setListMeses(List<TcPeriodo> listMeses) {
		this.listMeses = listMeses;
	}

	public Integer getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getCtaInical() {
		return ctaInical;
	}

	public void setCtaInical(String ctaInical) {
		this.ctaInical = ctaInical;
	}

	public String getCtaFinal() {
		return ctaFinal;
	}

	public void setCtaFinal(String ctaFinal) {
		this.ctaFinal = ctaFinal;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

	public TcEntidadAdministrativaRepository getEntidadAdministrativaRepository() {
		return entidadAdministrativaRepository;
	}

	public void setEntidadAdministrativaRepository(TcEntidadAdministrativaRepository entidadAdministrativaRepository) {
		this.entidadAdministrativaRepository = entidadAdministrativaRepository;
	}

}
