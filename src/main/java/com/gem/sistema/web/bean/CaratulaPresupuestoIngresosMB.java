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
 * The Class CaratulaPresupuestoIngresosMB.
 * 
 * @author Alfredo
 * @Timeline <strong>Date:</strong> 13/08/2021 <strong>Changes:</strong> Se
 *           implementa Servicio de firmas dinamicas
 * @Sector Municipal          
 */
@ManagedBean
@ViewScoped
public class CaratulaPresupuestoIngresosMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_CARATULA_PRESUPUESTO_INGRESOS";
	/** The tipo rep. */
	private String tipoRep;

	/** The conctb. */
	private Conctb conctb;

	/** The conctb repository. */
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
		LOGGER.info(":: En postconsruct CaratulaPresupuestoIngresosMB ");
		jasperReporteName = "CaratulaPresupuestoIngresos";
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
		conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(conctb, reporte);

		if ("pro".equals(getTipoRep())) {
			paramsReport.put("Proyecto", "X");
			paramsReport.put("Definitivo", "");
		} else {
			paramsReport.put("Proyecto", "");
			paramsReport.put("Definitivo", "X");
		}
		paramsReport.put("USUARIO", getUserDetails().getUsername());

		paramsReport.put("Image", conctb.getImagePathLeft());
		paramsReport.put("Image2", conctb.getImagePathRigth());
		paramsReport.put("Ente", conctb.getNomDep());
		paramsReport.put("No", conctb.getClave());
		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("idSector", idSector);
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

	/**
	 * Gets the tipo rep.
	 *
	 * @return the tipo rep
	 */
	public String getTipoRep() {
		return tipoRep;
	}

	/**
	 * Sets the tipo rep.
	 *
	 * @param tipoRep the new tipo rep
	 */
	public void setTipoRep(String tipoRep) {
		this.tipoRep = tipoRep;
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
