package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.primefaces.component.tabview.Tab;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.GeneraTxtFilesService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.Constants;

/**
 * The Class Pbrm01eMB.
 * 
 * @author Alfredo
 * @Timeline <strong>Date:</strong> 03/12/2021 <strong>Changes:</strong> Se
 *           ajusta reporte y se implementa firmas dinamicas
 * @Sector Municipal
 */
@Component("pbrm01eMB")
@Scope("view")
public class Pbrm01eMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_MATRIZ_INDICADORES_01E";

	private StreamedContent file;
	private String fileName;
	private String report;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{generaTxtFilesService}")
	private GeneraTxtFilesService txtFilesService;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@PostConstruct
	public void init() {
		report = "normal";
		jasperReporteName = "pbrm-01e";
		endFilename = jasperReporteName + ".pdf";

	}

	public void onTabChange(TabChangeEvent event) {

		this.uuid = null;
		this.endFilename = null;
		Tab tab = event.getTab();
		tab.clearInitialState();
		tab.setTitleStyle("background: #8eb987;");

		this.report = event.getTab().getId();

		switch (this.report) {
		case "normal":
			this.jasperReporteName = "pbrm-01e";
			break;

		case "anual":
			this.jasperReporteName = "matrizIndicadoresAnual";
			this.endFilename = jasperReporteName + ".pdf";
			break;

		}

	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();

		if (this.report.equals("normal")) {
			Conctb conctb = conctbRepository.findByIdsector(sector);
			TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

			endFilename = getNameFileReport(conctb, reporte);

			parameters.put("year", conctb.getAnoemp());
			parameters.put("sector", sector);
			parameters.put("claveEntidad", conctb.getClave());
			parameters.put("nombreEntidad", conctb.getNomDep());
			parameters.put("imagenPathLeft", conctb.getImagePathLeft());
			parameters.put("imagenPathRigth", conctb.getImagePathRigth());
			parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));
		}else {
			endFilename = this.jasperReporteName + ".pdf";
		}

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public StreamedContent getFile() {
		fileName = "Pbrm-01e.txt";
		InputStream stream = null;

		try {
			stream = new FileInputStream(this.txtFilesService.generaReporteMatrizIndicadores1e(fileName,
					this.getUserDetails().getIdSector()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		file = StreamedContents.of(stream, Constants.APPLICCTION_TXT, fileName, null);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Success", "Se Generó el Archivo: " + fileName);

		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public GeneraTxtFilesService getTxtFilesService() {
		return txtFilesService;
	}

	public void setTxtFilesService(GeneraTxtFilesService txtFilesService) {
		this.txtFilesService = txtFilesService;
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

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

}
