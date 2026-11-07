package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.QConctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;

import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.ExportInformationService;

@ManagedBean(name = "expInformationMB")
@ViewScoped
public class ExpInformationMB extends BaseDirectReport {

	@ManagedProperty("#{exportInformationService}")
	private ExportInformationService exportInformationService;

	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";
	private static final String DOWNLOAD_TXT2 = " jQuery('#form1\\\\:downTxt2').click();";
	private static final String DOWNLOAD_TXT3 = " jQuery('#form1\\\\:downTxt3').click();";
	private static final String CLAVE_REPORT = "RPT_OSFEM_GENERIC";

	private static final Integer MENSUAL = 1;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	private StreamedContent file1;
	private StreamedContent file2;
	private StreamedContent file3;
	/** The stream. */
	InputStream stream1 = null;
	InputStream stream2 = null;
	InputStream stream3 = null;
	private Integer mes;
	private String ctaCero;
	private String tabSelected;
	private List<TcPeriodo> listPeriodo;

	private String pathName;

	@PostConstruct
	public void init() {

		listPeriodo = this.tcPeriodoRepositoy.findByTipoPeriodo(MENSUAL);

		mes = listPeriodo.get(0).getPeriodo();

		jasperReporteName = "RF009_1_4";
		ctaCero = "N";
		tabSelected = "general";

	}
	public void onChange(TabChangeEvent event){

		System.out.println("omchnage " + event.getTab().getId());
		tabSelected = event.getTab().getId();
	}

	public void downloadCuenta() {

		Integer sector = this.getUserDetails().getIdSector();
		File[] file = new File[4]; 
		file[0] = new File(this.exportInformationService.exportCuentas(sector, mes));
		file[1] = new File( this.exportInformationService.exportPaso(sector, mes));
		file[2] = new File( this.exportInformationService.exportPoliza(sector, mes));
		file[3] = new File( this.exportInformationService.exportCuentaMes(sector, mes));
		pathName = this.exportInformationService.zipFile(file, "/gem/upfiles/export.zip");
		try {
			stream1 = new FileInputStream(new File(pathName));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file1 = new DefaultStreamedContent(stream1, "application/zip", pathName.substring(13));
		
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se Generaro el archivo zip: "
				+ pathName.substring(13) );

	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();

		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcPeriodo periodo = tcPeriodoRepositoy.findByTipoPeriodoAndPeriodo(MENSUAL, mes);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		String query = StringUtils.EMPTY;

		switch (tabSelected) {
			case "general":
				jasperReporteName = "";
				break;
			case "catalogoCuenta":
				jasperReporteName = "CatalogoCuentasContables";
				query = this.exportInformationService.generaQueryCuentaMes(sector, mes, ctaCero);
				endFilename = getNameFileReport("CCC", StringUtils.leftPad(mes.toString(), 2, "0"), conctb, reporte);
				break;
			case "catalogoPoliza":
				jasperReporteName = "CatalogoPolizasContables";
				query = this.exportInformationService.generaQueryPolizas(sector,mes,conctb.getAnoemp());
				endFilename = getNameFileReport("CPC", StringUtils.leftPad(mes.toString(), 2, "0"), conctb, reporte);
				break;
		}

		parameters.put("descriptionMonth", periodo.getDescripcion());
		parameters.put("imagenPathRigth", conctb.getImagePathRigth());
		parameters.put("imagenPathLeft", conctb.getImagePathLeft());
		parameters.put("entidadName", conctb.getNomDep());
		parameters.put("lastDay", getLastDay(mes));
		parameters.put("year", conctb.getAnoemp());
		parameters.put("sSql", query);
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		return null;
	}

	public void downloadCuentaTxt() {
		try {
			this.downloadCuenta();
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void downloadTxt2() {
		try {
			this.downloadCuenta();
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT2);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void downloadTxt3() {
		try {
			this.downloadCuenta();

			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ExportInformationService getExportInformationService() {
		return exportInformationService;
	}

	public void setExportInformationService(ExportInformationService exportInformationService) {
		this.exportInformationService = exportInformationService;
	}

	public TcPeriodoRepositoy getTcPeriodoRepositoy() {
		return tcPeriodoRepositoy;
	}

	public void setTcPeriodoRepositoy(TcPeriodoRepositoy tcPeriodoRepositoy) {
		this.tcPeriodoRepositoy = tcPeriodoRepositoy;
	}

	public StreamedContent getFile1() {
		return file1;
	}

	public void setFile1(StreamedContent file1) {
		this.file1 = file1;
	}

	public StreamedContent getFile2() {
		return file2;
	}

	public void setFile2(StreamedContent file2) {
		this.file2 = file2;
	}

	public StreamedContent getFile3() {
		return file3;
	}

	public void setFile3(StreamedContent file3) {
		this.file3 = file3;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public List<TcPeriodo> getListPeriodo() {
		return listPeriodo;
	}

	public void setListPeriodo(List<TcPeriodo> listPeriodo) {
		this.listPeriodo = listPeriodo;
	}

	public InputStream getStream1() {
		return stream1;
	}

	public void setStream1(InputStream stream1) {
		this.stream1 = stream1;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public String getCtaCero() {
		return ctaCero;
	}

	public void setCtaCero(String ctaCero) {
		this.ctaCero = ctaCero;
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

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public String getTabSelected() {
		return tabSelected;
	}

	public void setTabSelected(String tabSelected) {
		this.tabSelected = tabSelected;
	}
}
