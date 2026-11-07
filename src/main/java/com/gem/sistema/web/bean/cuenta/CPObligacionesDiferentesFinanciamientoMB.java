package com.gem.sistema.web.bean.cuenta;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ServiceLDF;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.web.bean.BaseDirectReport;

@ManagedBean(name = "cpObligacionesDiferentesFinanciamientoMB")
@ViewScoped
public class CPObligacionesDiferentesFinanciamientoMB extends BaseDirectReport {
	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@ManagedProperty("#{informeAnaliticoDeudaPublicaService}")
	private ServiceLDF service;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@PostConstruct
	public void init() {
		jasperReporteName = "obligaciones_diferentes_financiamiento_cuentaPublica";
		endFilename = jasperReporteName + ".pdf";
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = this.service.getAllConctb(idSector);
		parameters.put("ente", conctb.getNomDep());
		parameters.put("lastDay", 31);
		parameters.put("month", "Diciembre");
		parameters.put("year", conctb.getAnoemp());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, "RPT_INFOEM"));
		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public TcPeriodoRepositoy getTcPeriodoRepositoy() {
		return tcPeriodoRepositoy;
	}

	public void setTcPeriodoRepositoy(TcPeriodoRepositoy tcPeriodoRepositoy) {
		this.tcPeriodoRepositoy = tcPeriodoRepositoy;
	}

	public ServiceLDF getService() {
		return service;
	}

	public void setService(ServiceLDF service) {
		this.service = service;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

}
