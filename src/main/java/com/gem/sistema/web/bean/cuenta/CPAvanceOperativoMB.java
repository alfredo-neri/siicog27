package com.gem.sistema.web.bean.cuenta;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.service.catalogos.CuentaPublicaService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.web.bean.BaseDirectReport;

@ManagedBean(name = "cpAvanceOperativoMB")
@ViewScoped
public class CPAvanceOperativoMB extends BaseDirectReport {

	@ManagedProperty("#{cuentaPublicaService}")
	private CuentaPublicaService cuentaPublicaService;

	@PostConstruct
	public void init() {
		jasperReporteName = "avanceOperativo";
		endFilename = jasperReporteName + ".pdf";
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		String sSql = this.cuentaPublicaService.getQueryAvanceOperativo(idSector);

		Conctb conctb = this.cuentaPublicaService.getAllConctb(idSector);
		Integer year = conctb.getAnoemp();
		map.put("pYear", year);
		map.put("pSql", sSql);

		return map;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public CuentaPublicaService getCuentaPublicaService() {
		return cuentaPublicaService;
	}

	public void setCuentaPublicaService(CuentaPublicaService cuentaPublicaService) {
		this.cuentaPublicaService = cuentaPublicaService;
	}

}
