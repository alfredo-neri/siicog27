package com.gem.sistema.web.bean;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

// TODO: Auto-generated Javadoc

/**
 * The Class ExportaInformacionCentralMB.
 */
@ViewScoped
@ManagedBean(name = "exportaInformacionCentralMB")
public class ExportaInformacionCentralMB extends BaseDirectReport {

	private Integer mes;

	private Integer saldo0;

	private List<TcPeriodo> listPeriodo;
	
	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct ExportaInformacionCentralMB ");

		listPeriodo = this.periodoRepositoy.findByTipoPeriodo(1);

		mes = listPeriodo.get(0).getPeriodo();
	}

	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();

		
		return paramsReport;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		return null;
	}

	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());

			//RequestContext.getCurrentInstance().execute(DOWNLOAD_XLS);
		}
	}
	
	private String generaQueryTXT() {

		StringBuilder query = new StringBuilder();

		if (saldo0 == 0) {
			query.append(" WHERE (T1.SALDO_INICIAL <> 0 OR T1.CARGOS <> 0 OR T1.ABONOS <> 0) ");
		}
		
		query.append( " ORDER BY T1.CUENTA)T2");
		
		System.out.println("Query TXT-> " + query.toString());
		return query.toString();
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getSaldo0() {
		return saldo0;
	}

	public void setSaldo0(Integer saldo0) {
		this.saldo0 = saldo0;
	}

	public List<TcPeriodo> getListPeriodo() {
		return listPeriodo;
	}

	public void setListPeriodo(List<TcPeriodo> listPeriodo) {
		this.listPeriodo = listPeriodo;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
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

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}
}
