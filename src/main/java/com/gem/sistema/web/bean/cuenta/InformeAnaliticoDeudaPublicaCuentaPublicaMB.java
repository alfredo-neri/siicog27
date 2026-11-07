package com.gem.sistema.web.bean.cuenta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ServiceLDF;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.UtilLastDay;
import com.gem.sistema.web.bean.BaseDirectReport;
@Component("informeAnaliticoDeudaPublicaCuentaPublicaMB")
@Scope("view")
public class InformeAnaliticoDeudaPublicaCuentaPublicaMB extends BaseDirectReport{

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@Value("#{informeAnaliticoDeudaPublicaService}")
	private ServiceLDF service;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	private ArrayList<TcPeriodo> listPeriodo;

	private TcPeriodo tcPeriodo;

	@PostConstruct
	public void init() {
		jasperReporteName = "informe_analitico_deuda_publica_cuenta_publica";
		endFilename = jasperReporteName + ".pdf";
		listPeriodo = (ArrayList<TcPeriodo>) this.tcPeriodoRepositoy.findByTipoPeriodo(3);
		tcPeriodo = listPeriodo.get(3);

	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> map = new HashMap<String, Object>();
		String sSql = this.service.getQueryInfoem(tcPeriodo.getPeriodo());
		Conctb conctb = this.service.getAllConctb(this.getUserDetails().getIdSector());
		Integer trimestre = tcPeriodo.getPeriodo();
		Integer mes = getMonth(trimestre);
		Integer mesB = mes == 3 ? 1 : mes - 3;
		Integer lastDay = UtilLastDay.lastDayOfMonth(conctb.getAnoemp(), trimestre);
		String month = UtilLastDay.getMonthOfYear(trimestre);
		Integer lastDayB = mesB == 1 ? 1 : UtilLastDay.lastDayOfMonth(conctb.getAnoemp(), mesB, true);
		String monthB = UtilLastDay.getMonthOfYear(mesB, true);
		map.put("lastDay", lastDay);
		map.put("month", month);
		map.put("lastDayB", lastDayB);
		map.put("monthB", monthB);
		map.put("year", conctb.getAnoemp());
		map.put("img1", conctb.getImagePathRigth());
		map.put("img2", conctb.getImagePathLeft());
		map.put("sSql", sSql);
		map.putAll(puestosFirmasService.getFirmasOfReport(this.getUserDetails().getIdSector(), "RPT_INFOEM"));
		return map;
	}

	private int getMonth(Integer trimestre) {
		int month = 0;
		if (trimestre == 1)
			month = 3;
		else if (trimestre == 2)
			month = 6;
		else if (trimestre == 3)
			month = 9;
		else if (trimestre == 4)
			month = 12;
		else if (trimestre <= 0)
			month = 1;
		return month;
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

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}
}
