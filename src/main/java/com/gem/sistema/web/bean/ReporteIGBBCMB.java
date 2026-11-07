package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteIGBBCMB.
 * 
 * @author Alfredo
 * @Timeline <strong>Date:</strong> 22/04/2021 <strong>Changes:</strong> Cambio
 *           de Nombre del Archivo, Ajuste diseno reporte<br>
 *           <strong>Date:</strong> 13/08/2021 <strong>Changes:</strong> Se
 *           implementa Servicio de firmas dinamicas
 *
 */
@ManagedBean(name = "reporteIGBBCMB")
@ViewScoped
public class ReporteIGBBCMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_INVENTARIO_GENERAL_BIENES_MUEBLES_BAJO_COSTO";
	/** The conctb. */
	private Conctb conctb;

	/** The semestre. */
	private String semestre;

	/** The listsemestre. */
	private List<String> listsemestre;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{reporte025MB}")
	private Reporte025MB reporte025mb;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

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
	 * Gets the semestre.
	 *
	 * @return the semestre
	 */
	public String getSemestre() {
		return semestre;
	}

	/**
	 * Sets the semestre.
	 *
	 * @param semestre the new semestre
	 */
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	/**
	 * Gets the listsemestre.
	 *
	 * @return the listsemestre
	 */
	public List<String> getListsemestre() {
		return listsemestre;
	}

	/**
	 * Sets the listsemestre.
	 *
	 * @param listsemestre the new listsemestre
	 */
	public void setListsemestre(List<String> listsemestre) {
		this.listsemestre = listsemestre;
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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		jasperReporteName = "ReporteIGBBC";
		endFilename = jasperReporteName + ".pdf";

		listsemestre = new ArrayList<String>();
		for (int i = 1; i <= 2; i++) {
			listsemestre.add("0" + i);
		}
		semestre = listsemestre.get(0);

	}

	/**
	 * Gets the mes.
	 *
	 * @param semes the semes
	 * @return the mes
	 */
	public String getMes(Integer semes) {
		if (semes == 1) {
			return "06";
		} else {
			return "12";
		}

	}

	/**
	 * Gets the int.
	 *
	 * @param semes the semes
	 * @return the int
	 */
	public int getInt(Integer semes) {
		if (semes == 1) {
			return 6;
		} else {
			return 12;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		TrPuestoFirma firma = new TrPuestoFirma();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcMes descripMes = tcMesRepository.findByMes(this.getMes(Integer.valueOf(semestre)));
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(semestre, conctb, reporte);

		parameters.put("pIdSector", idSector);
		parameters.put("pSemestre", this.getInt(Integer.valueOf(semestre)));
		parameters.put("pImg", conctb.getImagePathRigth());
		parameters.put("pNomMun", conctb.getNomDep());
		parameters.put("pClveMun", conctb.getClave());
		parameters.put("pLastDay", getLastDayByAnoEmp(this.getInt(Integer.valueOf(semestre)), conctb.getAnoemp()));
		parameters.put("pMes", descripMes.getDescripcion());
		parameters.put("pAnio", conctb.getAnoemp());

		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F09.getValue());
		parameters.put("pElaboro", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F10.getValue());
		parameters.put("pReviso", firma.getNombre());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return parameters;
	}

	public void downLoadFile() {
		this.reporte025mb.setSemestre(Integer.valueOf(this.getSemestre()));
		try {
			this.reporte025mb.downLoadFile();
		} catch (ReportValidationException e) {
			e.printStackTrace();
		}
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

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public Reporte025MB getReporte025mb() {
		return reporte025mb;
	}

	public void setReporte025mb(Reporte025MB reporte025mb) {
		this.reporte025mb = reporte025mb;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
