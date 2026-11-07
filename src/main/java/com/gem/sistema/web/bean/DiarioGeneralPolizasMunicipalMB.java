package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
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

// TODO: Auto-generated Javadoc
/**
 * The Class DiarioGeneralPolizasMunicipalMB.
 * 
 * @author Alfredo
 * @Timeline <strong>Date: </strong>20/03/2022 <strong> Changes: </strong> Se
 *           crea reporte para el sector municipal<br>
 * @Sector Municipal
 */
@ManagedBean(name = "diarioGeneralPolizasMunicipalMB")
@ViewScoped
public class DiarioGeneralPolizasMunicipalMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_DR_GRAL_POL";

	private Integer mesInicial;

	private Integer mesFinal;

	private List<TcPeriodo> listMes;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@ManagedProperty("#{tcEntidadAdministrativaRepository}")
	private TcEntidadAdministrativaRepository entidadAdministrativaRepository;

	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct DiarioGeneralPolizasMunicipalMB ");

		listMes = periodoRepositoy.findByTipoPeriodo(1);
		if (!CollectionUtils.isEmpty(listMes)) {
			this.mesInicial = listMes.get(0).getPeriodo();
			this.mesFinal = listMes.get(0).getPeriodo();
		}

		jasperReporteName = "diarioGeneralPolizasMunicipal";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(mesFinal, conctb, reporte);

		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("tipo", StringUtils.EMPTY);
		paramsReport.put("sector", sector);
		paramsReport.put("lastDay", getLastDayByAnoEmp(mesFinal, conctb.getAnoemp()));
		paramsReport.put("mesFinal", mesFinal);
		paramsReport.put("mesInicial", mesInicial);
		paramsReport.put("claveEntidad", StringUtils.EMPTY);
		paramsReport.put("nombreEntidad", conctb.getNomDep());
		paramsReport.put("mesFinalName", periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mesFinal).getDescripcion());
		paramsReport.put("mesInicialName",
				periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mesInicial).getDescripcion());
		paramsReport.put("imagenPathLeft", conctb.getImagePathRigth());
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return paramsReport;
	}

	public void viewPdf() {
		this.createFilePdfInline();
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

	public List<TcPeriodo> getListMes() {
		return listMes;
	}

	public void setListMes(List<TcPeriodo> listMes) {
		this.listMes = listMes;
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

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
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
