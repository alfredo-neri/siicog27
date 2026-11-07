package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilReport.getNameFileReport;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;

// TODO: Auto-generated Javadoc
/**
 * The Class RegresosMB.
 * 
 * @author Alfredo
 * @Timeline <strong>Date:</strong> 16/08/2021 <strong>Changes:</strong> Se
 *           implementa Servicio de firmas dinamicas
 * @Sector Municipal
 */
@ManagedBean
@ViewScoped
public class RegresosMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_CARATULA_PRESUPUESTO_EGRESOS";

	/** The definitivo. */
	private String definitivo;

	/** The contb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository contbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/**
	 * Gets the definitivo.
	 *
	 * @return the definitivo
	 */
	public String getDefinitivo() {
		return definitivo;
	}

	/**
	 * Sets the definitivo.
	 *
	 * @param definitivo the new definitivo
	 */
	public void setDefinitivo(String definitivo) {
		this.definitivo = definitivo;
	}

	/**
	 * Gets the contb repository.
	 *
	 * @return the contb repository
	 */
	public ConctbRepository getContbRepository() {
		return contbRepository;
	}

	/**
	 * Sets the contb repository.
	 *
	 * @param contbRepository the new contb repository
	 */
	public void setContbRepository(ConctbRepository contbRepository) {
		this.contbRepository = contbRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RegresosMB ");
		jasperReporteName = "R_egresos";
		endFilename = jasperReporteName + ".pdf";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = contbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(conctb, reporte);

		if ("Proyecto".equals(definitivo)) {
			paramsReport.put("PROYECTO", "X");
		} else {
			paramsReport.put("DEFINITIVO", "X");
		}
		paramsReport.put("IMG_MUN", conctb.getImagePathLeft());
		paramsReport.put("IMG_ENT", conctb.getImagePathRigth());
		paramsReport.put("IDSECTOR", idSector);
		paramsReport.put("pMun", conctb.getNomDep());
		paramsReport.put("pYear", conctb.getAnoemp());
		paramsReport.put("NUMMUN", conctb.getClave());
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return paramsReport;
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

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
