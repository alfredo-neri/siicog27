package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

/**
 * @author Alfredo
 * @Date 13/08/2021
 * @Changes Se implementa servicios de firmas
 * @Sector Municipal
 */
@ManagedBean(name = "bienesMueblesBCMensualMB")
@ViewScoped
public class BienesMueblesBCMensualMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_INVENTARIO_MENSUAL_BIENES_MUEBLES_BAJO_COSTO";

	private static final String STORE_NAME = "SP_REPORTE_BMBC_TXT";

	private Conctb conctb;

	private Integer mes;

	private List<TcPeriodo> listMeses;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{executePlService}")
	private ExecutePlService executePlService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@PostConstruct
	public void init() {

		jasperReporteName = "bienesMueblesBajoCosto";
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		listMeses = tcPeriodoRepositoy.findByTipoPeriodo(1);

		if (!listMeses.isEmpty()) {
			mes = listMeses.get(0).getPeriodo();
		}

	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();

		TcPeriodo mesSelected = tcPeriodoRepositoy.findByTipoPeriodoAndPeriodo(1, mes);
		TrPuestoFirma firma = new TrPuestoFirma();
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(mes, conctb, reporte);

		parameters.put("nameReport", endFilename.substring(0, endFilename.length() - 4));
		parameters.put("pIdSector", idSector);
		parameters.put("pMes", mes);
		parameters.put("pImg1", conctb.getImagePathRigth());
		parameters.put("pNomMun", conctb.getNomDep());
		parameters.put("pClveMun", conctb.getClave());
		parameters.put("pLastDay", getLastDayByAnoEmp(mesSelected.getPeriodo(), conctb.getAnoemp()));
		parameters.put("pMesName", mesSelected.getDescripcion());
		parameters.put("pAnio", conctb.getAnoemp());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F09.getValue());
		parameters.put("reviso", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F10.getValue());
		parameters.put("elaboro", firma.getNombre());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public StreamedContent getTxt() {
		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("i_mes", mes);
		parameter.addValue("i_idsector", this.getUserDetails().getIdSector());
		parameter.addValue("i_file_name", endFilename.substring(0, endFilename.length() - 4));

		Map<String, Object> out = executePlService.callProcedure(STORE_NAME, parameter);
		InputStream stream = null;
		StreamedContent txt = null;

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(
						new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			txt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
		}
		return txt;
	}

	public Conctb getConctb() {
		return conctb;
	}

	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public List<TcPeriodo> getListMeses() {
		return listMeses;
	}

	public void setListMeses(List<TcPeriodo> listMeses) {
		this.listMeses = listMeses;
	}

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

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}
}
