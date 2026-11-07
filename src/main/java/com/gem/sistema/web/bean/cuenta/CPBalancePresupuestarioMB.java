package com.gem.sistema.web.bean.cuenta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ServiceLDF;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.UtilLastDay;
import com.gem.sistema.web.bean.BaseDirectReport;

@Component("cpBalancePresupuestarioMB")
@Scope("view")
public class CPBalancePresupuestarioMB extends BaseDirectReport {

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@Value("#{informeAnaliticoDeudaPublicaService}")
	private ServiceLDF service;
	
	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@PostConstruct
	public void init() {
		jasperReporteName = "balance_presupuestario_cuentaPublica";
		endFilename = jasperReporteName + ".pdf";
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer periodo = 4;
		String sSql1 = this.service.getQueryBalancePresupuestario1(periodo, false);
		String sSql2 = this.service.getQueryBalancePresupuestario2();
		String sSql3 = this.service.getQueryBalancePresupuestario3();
		String sSql4 = this.service.getQueryBalancePresupuestario4(periodo, false);
		String sSql5 = this.service.getQueryBalancePresupuestario5();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = this.service.getAllConctb(this.getUserDetails().getIdSector());
		Integer lastDay = UtilLastDay.lastDayOfMonth(conctb.getAnoemp(), periodo, false);
		String month = UtilLastDay.getMonthOfYear(periodo, false);
		map.put("ente", conctb.getNomDep());
		map.put("lastDay", lastDay);
		map.put("month", month);
		map.put("year", conctb.getAnoemp());
		map.put("img1", conctb.getImagePathRigth());
		map.put("img2", conctb.getImagePathLeft());
		map.put("sSql1", sSql1);
		map.put("sSql2", sSql2);
		map.put("sSql3", sSql3);
		map.put("sSql4", sSql4);
		map.put("sSql5", sSql5);
		map.putAll(puestosFirmasService.getFirmasOfReport(idSector, "RPT_INFOEM"));

		return map;
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
