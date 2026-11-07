package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;

import java.io.File;
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
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;

// TODO: Auto-generated Javadoc
/**
 * The Class Pbrm01b12MB.
 * 
 * @author Alfredo
 * @Timeline <strong>Date:</strong> 16/08/2021 <strong>Changes:</strong> Se
 *           implementa Servicio de firmas dinamicas<br>
 *           <strong>Date:</strong> 24/02/2022 <strong>Changes:</strong> Se crea
 *           reporte anual con nuevo formato<br>
 * @Sector Municipal
 */
@Component("pbrm01b12MB")
@Scope("view")
public class Pbrm01b12MB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_PROGRAMA_ANUAL_DESCRIPCION_PROGRAMA_PRESUPUESTARIO";
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_PROGRAMA_ANUAL_DESCRIPCION_PROGRAMA";

	private String report;
	private StreamedContent txt;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@Value("#{executePlService}")
	private ExecutePlService executePlService;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct Pbrm01b12MB ");
		this.report = "normal";
		jasperReporteName = "pbrm_01b12";
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
			this.jasperReporteName = "pbrm_01b12";
			break;

		case "anual":
			this.jasperReporteName = "programaAnualDescrpcionPrograma";
			this.endFilename = jasperReporteName + ".pdf";
			break;

		}

	}

	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		
		Conctb conctb = conctbRepository.findByIdsector(idSector);

		if (this.report.equals("normal")) {
			
			TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

			endFilename = getNameFileReport(conctb, reporte);

			paramsReport.put("p_Img1", conctb.getImagePathLeft());
			paramsReport.put("p_Img2", conctb.getImagePathRigth());
			paramsReport.put("p_NomMun", conctb.getNomDep());
			paramsReport.put("p_NumMun", conctb.getClave());
			paramsReport.put("pYear", conctb.getAnoemp());
			paramsReport.put("p_IdSector", idSector);
			paramsReport.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));
		} else {
			endFilename = "PADPP" + conctb.getClave() + conctb.getAnoemp() + ".pdf";
			paramsReport.put("sector", idSector);
		}

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

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
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

	Map<String, Object> out;
	InputStream stream = null;

	public StreamedContent getTxt() {
		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("i_idsector", this.getUserDetails().getIdSector());

		out = executePlService.callProcedure(PROCEDURE_NAME, parameter);

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(
						new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			txt = StreamedContents.of(stream, "application/txt", out.get("O_NAME_FILE").toString(), null);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
		}
		return txt;
	}

	public void setTxt(StreamedContent txt) {
		this.txt = txt;
	}

}
