package com.gem.sistema.web.bean.cuenta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ServiceLDF;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.UtilLastDay;
import com.gem.sistema.web.bean.BaseDirectReport;

@ManagedBean(name = "endeudamientoNetoCuentaPublicaMB")
@ViewScoped
public class EndeudamientoNetoCuentaPublicaMB extends BaseDirectReport{
	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@ManagedProperty("#{informeAnaliticoDeudaPublicaService}")
	private ServiceLDF service;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	private ArrayList<TcPeriodo> listPeriodo;

	private TcPeriodo tcPeriodo;
	
	private ArrayList<TcPeriodo> listMeses;

	private TcPeriodo tcMes;
	
	private boolean mensual;
	
	private String lbl;

	@PostConstruct
	public void init() {
		jasperReporteName = "endeudamiento_neto_infoem";
		endFilename = jasperReporteName + ".pdf";
		listPeriodo = (ArrayList<TcPeriodo>) this.tcPeriodoRepositoy.findByTipoPeriodo(3);
		tcPeriodo = listPeriodo.get(0);
		listMeses = (ArrayList<TcPeriodo>) this.tcPeriodoRepositoy.findByTipoPeriodo(1);
		tcMes = listMeses.get(11);
		mensual = false;
		lbl = "Trimestre";

	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> map = new HashMap<String, Object>();
		String sSql = this.service.getQueryEndeudamientoNeto(tcPeriodo.getPeriodo());
		Integer trimestre = tcMes.getPeriodo(); 
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = this.service.getAllConctb(idSector);
		Integer lastDay = UtilLastDay.lastDayOfMonth(conctb.getAnoemp(), trimestre, true);
		String month = UtilLastDay.getMonthOfYear(trimestre, true);
		
		map.put("ente", conctb.getNomDep());
		map.put("lastDay", lastDay);
		map.put("month", month);
		map.put("year", conctb.getAnoemp());
		map.put("img1", conctb.getImagePathRigth());
		map.put("img2", conctb.getImagePathLeft());
		map.put("sSql", sSql);
		map.putAll(puestosFirmasService.getFirmasOfReport(idSector, "RPT_INFOEM"));

		return map;
	}
	
	public void cambiarMensual() {
		lbl = mensual ? "Mes" : "Trimestre";
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
	
	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public ArrayList<TcPeriodo> getListPeriodo() {
		return listPeriodo;
	}

	public void setListPeriodo(ArrayList<TcPeriodo> listPeriodo) {
		this.listPeriodo = listPeriodo;
	}

	public TcPeriodo getTcPeriodo() {
		return tcPeriodo;
	}

	public void setTcPeriodo(TcPeriodo tcPeriodo) {
		this.tcPeriodo = tcPeriodo;
	}

	public ServiceLDF getService() {
		return service;
	}

	public void setService(ServiceLDF service) {
		this.service = service;
	}

	public ArrayList<TcPeriodo> getListMeses() {
		return listMeses;
	}

	public void setListMeses(ArrayList<TcPeriodo> listMeses) {
		this.listMeses = listMeses;
	}

	public TcPeriodo getTcMes() {
		return tcMes;
	}

	public void setTcMes(TcPeriodo tcMes) {
		this.tcMes = tcMes;
	}

	public boolean isMensual() {
		return mensual;
	}

	public void setMensual(boolean mensual) {
		this.mensual = mensual;
	}

	public String getLbl() {
		return lbl;
	}

	public void setLbl(String lbl) {
		this.lbl = lbl;
	}

}
