package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.dto.ReportsParamDTO;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ReporteRF009114Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteRF009114MB.
 */
@ManagedBean(name = "reporteRF009114MB")
@ViewScoped
public class ReporteRF009114MB extends BaseDirectReport {
	
	private static final String CLAVE_REPORT = "RPT_EDO_INGRESOS_EGRESOS";

	private Integer niveles;
	private List<Integer> listNivel;

	/** The mes repository. */
	@ManagedProperty(value = "#{tcMesRepository}")
	private TcMesRepository mesRepository;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;
	
	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public TcMesRepository getMesRepository() {
		return mesRepository;
	}

	public void setMesRepository(TcMesRepository mesRepository) {
		this.mesRepository = mesRepository;
	}

	/** The mes. */
	private String mes;

	/** The content TXT. */
	private StreamedContent contentTXT;

	/** The content CSV. */
	private StreamedContent contentCSV;

	/** The reporte service. */
	@ManagedProperty(value = "#{reporteRF009114Service}")
	private ReporteRF009114Service reporteService;

	String sSql;

	/**
	 * Gets the reporte service.
	 *
	 * @return the reporte service
	 */
	public ReporteRF009114Service getReporteService() {
		return reporteService;
	}

	/**
	 * Gets the content CSV.
	 *
	 * @return the content CSV
	 */
	public StreamedContent getContentCSV() {
		stream = null;
		ReportsParamDTO parameters = new ReportsParamDTO();
		parameters.setMes(Integer.valueOf(mes));
		parameters.setIdSector(this.getUserDetails().getIdSector());
		parameters.setMunicipio(this.getUserDetails().getMunicipio());
		ReportsParamDTO reportsDTO = this.reporteService.executeProcedure(parameters, 2);
		sSql = reportsDTO.getQuery();

		endFilename = reportsDTO.getoFullFile().toString();
		try {
			stream = new FileInputStream(new File(reportsDTO.getoFullFile().toString()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentCSV = new DefaultStreamedContent(stream, "application/txt", endFilename.substring(13));
		endFilename = "";

		return contentCSV;
	}

	/**
	 * Sets the content CSV.
	 *
	 * @param contentCSV the new content CSV
	 */
	public void setContentCSV(StreamedContent contentCSV) {
		this.contentCSV = contentCSV;
	}

	/**
	 * Sets the reporte service.
	 *
	 * @param reporteService the new reporte service
	 */
	public void setReporteService(ReporteRF009114Service reporteService) {
		this.reporteService = reporteService;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the content TXT.
	 *
	 * @return the content TXT
	 */
	public StreamedContent getContentTXT() {
		sSql = this.reporteService.executeQuery(Integer.valueOf(mes), this.getUserDetails().getIdSector(), niveles);
		return this.getFileTxt();

	}

	/**
	 * Sets the content TXT.
	 *
	 * @param contentTXT the new content TXT
	 */
	public void setContentTXT(StreamedContent contentTXT) {
		this.contentTXT = contentTXT;
	}

	/** The stream. */
	InputStream stream = null;

	/**
	 * Gets the file txt procedure.
	 *
	 * @return the file txt procedure
	 */
	public void getFileTxtProcedure() {
		stream = null;
		ReportsParamDTO parameters = new ReportsParamDTO();
		parameters.setMes(Integer.valueOf(mes));
		parameters.setIdSector(this.getUserDetails().getIdSector());
		parameters.setMunicipio(this.getUserDetails().getMunicipio());
		ReportsParamDTO reportsDTO = this.reporteService.executeProcedure(parameters, 1);
		sSql = reportsDTO.getQuery();
		endFilename = reportsDTO.getoFullFile().toString();
		try {
			stream = new FileInputStream(new File(reportsDTO.getoFullFile().toString()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentTXT = new DefaultStreamedContent(stream, "application/txt", endFilename.substring(13));
		endFilename = "";

	}

	/**
	 * Gets the file xls using link.
	 *
	 * @return the file xls using link
	 */
	public StreamedContent getFileXlsProcedure() {
		StreamedContent streamedContent = null;
		try {
			ReportsParamDTO parameters = new ReportsParamDTO();
			parameters.setMes(Integer.valueOf(mes));
			parameters.setIdSector(this.getUserDetails().getIdSector());
			parameters.setMunicipio(this.getUserDetails().getMunicipio());
			sSql = reporteService.executeQuery(Integer.valueOf(mes), this.getUserDetails().getIdSector(), niveles);
			streamedContent = this.getFileXls();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
					"Hubo un error al generar el reporte, contacte a su administrador.");
		}
		return streamedContent;
	}

	/**
	 * Gets the file pdf in link for project in display.
	 */
	public void getFilePdfInlineProcedure() {
		stream = null;
		try {
			jasperReporteName = "reporte_RF009114_pdf";
			endFilename = jasperReporteName + ".pdf";

			sSql = reporteService.executeQuery(Integer.valueOf(mes), this.getUserDetails().getIdSector(), niveles);
			this.createFilePdfInline();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
					"Hubo un error al generar el reporte, contacte a su administrador.");
		}
	}

	/**
	 * Generate file csv.
	 *
	 * @return the streamed content
	 */
	public void getFileCsvProcedure() {
		stream = null;
		ReportsParamDTO parameters = new ReportsParamDTO();
		parameters.setMes(Integer.valueOf(mes));
		parameters.setIdSector(this.getUserDetails().getIdSector());
		parameters.setMunicipio(this.getUserDetails().getMunicipio());
		ReportsParamDTO reportsDTO = this.reporteService.executeProcedure(parameters, 2);
		if (reportsDTO.getoCodError() > 0) {
			endFilename = reportsDTO.getoFullFile().toString();
			try {
				stream = new FileInputStream(new File(reportsDTO.getoFullFile().toString()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			contentCSV = new DefaultStreamedContent(stream, "application/txt", endFilename.substring(13));
			endFilename = "";
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", reportsDTO.getoMsgError());
		}
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "reporte_RF009114_pdf";
		endFilename = jasperReporteName + ".pdf";
		listNivel = new ArrayList<Integer>();
		listNivel.add(2);
		listNivel.add(3);
		listNivel.add(4);
		listNivel.add(5);
		niveles = listNivel.get(0);

	}

	/** The parameters. */
	Map<String, Object> parameters = null;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {

		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcMes mesDes = this.mesRepository.findByMes(StringUtils.leftPad(mes, 2, "0"));
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println(sSql);
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

		parameters = new HashMap<String, Object>();
		endFilename = getNameFileReport(mes, conctb, reporte);

		parameters.put("Mes", this.lastDay(conctb.getAnoemp(), Integer.valueOf(mes)));
		parameters.put("sSql", sSql);
		parameters.put("Anio", conctb.getAnoemp());
		parameters.put("descMes", mesDes.getDescripcion());
		parameters.put("idSector", this.getUserDetails().getIdSector());
		parameters.put("imageLeft", conctb.getImagePathLeft());
		parameters.put("imageRigth", conctb.getImagePathRigth());
		parameters.put("entidadName", conctb.getNomDep());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return parameters;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.gem.sistema.business.dao.ReporteRF009114DAO#lastDay(java.lang.
	 * Integer, java.lang.Integer)
	 */
	public Integer lastDay(Integer anio, Integer mes) {
		Calendar calendario = Calendar.getInstance();
		calendario.set(anio, mes - 1, 1);
		return calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
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

	public Integer getNiveles() {
		return niveles;
	}

	public void setNiveles(Integer niveles) {
		this.niveles = niveles;
	}

	public List<Integer> getListNivel() {
		return listNivel;
	}

	public void setListNivel(List<Integer> listNivel) {
		this.listNivel = listNivel;
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

}
