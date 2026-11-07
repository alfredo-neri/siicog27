package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TwMetasIngreso;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TwMetasIngresoRepository;
import com.gem.sistema.business.service.catalogos.CargaIngresoMetasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.CopyFileUtil;
import com.gem.sistema.util.UtilFront;

@ManagedBean(name = "cargaIngresosMetasMB")
@ViewScoped
public class CargaIngresosMetasMB extends BaseDirectReport {

	private static final String PATH = "/gem/upfiles/";

	private static final String TIPO = "I";
	private Integer numero;
	private String mes;

	private String fileError = null;
	private List<TcMes> listMes;

	private List<TwMetasIngreso> listMestas = new ArrayList<TwMetasIngreso>();

	@ManagedProperty("#{twMetasIngresoRepository}")
	private TwMetasIngresoRepository twMetasIngresoRepository;

	@ManagedProperty("#{cargaIngresoMetasService}")
	private CargaIngresoMetasService cargaIngresoMetasService;

	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public TcPeriodoRepositoy getTcPeriodoRepositoy() {
		return tcPeriodoRepositoy;
	}

	public void setTcPeriodoRepositoy(TcPeriodoRepositoy tcPeriodoRepositoy) {
		this.tcPeriodoRepositoy = tcPeriodoRepositoy;
	}

	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct polizaDetallada ");
		jasperReporteName = "polizaDetallada";
		endFilename = jasperReporteName + ".xls";
		
		listMes = new ArrayList<TcMes>();
		this.setListMes(this.tcMesRepository.findAll());
		this.setMes(this.getListMes().get(0).getMes());
		this.listMestas = this.cargaIngresoMetasService.consultaMetas();
		if(CollectionUtils.isEmpty(listMestas))
			this.listMestas = new ArrayList<TwMetasIngreso>();
	}

	public void handleFileUpload(FileUploadEvent event) {

		String newName = UUID.randomUUID() + "" + event.getFile().getFileName();
		try {
			String filePath = CopyFileUtil.copyFile(newName, event.getFile().getInputstream(), PATH);
			this.fileError = this.cargaIngresoMetasService.validaIngresoPropios(filePath);
			if (StringUtils.isNotEmpty(fileError)) {
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "¡Error!",
						"¡Parece que algo salio mal, favor de revisar!");
			} else {
				listMestas = this.cargaIngresoMetasService.cargaMetas(filePath);
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "¡Informacion!",
						"¡Se cargaron correctamente los datos!");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Map<String, Object> getParametersReports() {
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		paramsReport.put("pAnio", conctb.getAnoemp());
		paramsReport.put("pDia", getLastDayByAnoEmp(Integer.parseInt(mes), conctb.getAnoemp()));
		paramsReport.put("USUARIO", getUserDetails().getUsername());
		paramsReport.put("img1", conctb.getImagePathLeft());
		paramsReport.put("img2", conctb.getImagePathRigth());
		paramsReport.put("mes", Integer.valueOf(mes));
		paramsReport.put("pMesLetra",
				tcPeriodoRepositoy.findByTipoPeriodoAndPeriodo(1, Integer.parseInt(mes)).getDescripcion().toUpperCase());
		paramsReport.put("idSector", idSector);
		paramsReport.put("nameDep", conctb.getNomDep());

		return paramsReport;
	}

	public StreamedContent getFile() {
		StreamedContent st = null;
		try {
			File file = new File(this.getFileError());
			st = new DefaultStreamedContent(new FileInputStream(file), "text/csv", file.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return st;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getFileError() {
		return fileError;
	}

	public void setFileError(String fileError) {
		this.fileError = fileError;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public List<TcMes> getListMes() {
		return listMes;
	}

	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
	}

	public List<TwMetasIngreso> getListMestas() {
		return listMestas;
	}

	public void setListMestas(List<TwMetasIngreso> listMestas) {
		this.listMestas = listMestas;
	}

	public TwMetasIngresoRepository getTwMetasIngresoRepository() {
		return twMetasIngresoRepository;
	}

	public void setTwMetasIngresoRepository(TwMetasIngresoRepository twMetasIngresoRepository) {
		this.twMetasIngresoRepository = twMetasIngresoRepository;
	}

	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	public CargaIngresoMetasService getCargaIngresoMetasService() {
		return cargaIngresoMetasService;
	}

	public void setCargaIngresoMetasService(CargaIngresoMetasService cargaIngresoMetasService) {
		this.cargaIngresoMetasService = cargaIngresoMetasService;
	}

	public String getMes() {
		return mes;
	}


	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

}
