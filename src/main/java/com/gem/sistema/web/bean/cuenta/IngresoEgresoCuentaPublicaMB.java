package com.gem.sistema.web.bean.cuenta;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.dto.TotalIngresoEgreso;
import com.gem.sistema.business.service.catalogos.CuentaPublicaService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.web.bean.BaseDirectReport;

@ViewScoped
@ManagedBean(name = "ingresoEgresoCuentaPublicaMB")
public class IngresoEgresoCuentaPublicaMB extends BaseDirectReport{
	
	
	@ManagedProperty("#{cuentaPublicaService}")
	private CuentaPublicaService cuentaPublicaService;
	
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct EAPI ");
		jasperReporteName = "ingresos_egresos_cuenta_publica";
		endFilename = jasperReporteName + ".pdf";
	}

	public CuentaPublicaService getCuentaPublicaService() {
		return cuentaPublicaService;
	}

	public void setCuentaPublicaService(CuentaPublicaService cuentaPublicaService) {
		this.cuentaPublicaService = cuentaPublicaService;
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> map = new HashMap<String, Object>();
		TotalIngresoEgreso totales = this.cuentaPublicaService.getTotalIngresoEgreso();
		map.put("totalAutorizado", totales.getAutotizado());
		map.put("totalAmpliacion", totales.getAmpliacion());
		map.put("totalReduccion", totales.getReduccion());
		map.put("totalAutoFinal", totales.getAotorizadoFinal());
		map.put("totalPreEjercido", totales.getEjercido());
		map.put("totalVariacionIncial", totales.getVariacionInicial());
		map.put("totalVariacionModi", totales.getModifcado());
		totales = this.cuentaPublicaService.getTotalEgreso();
		map.put("totalAutorizadoEgr", totales.getAutotizado());
		map.put("totalAmpliacionEgr", totales.getAmpliacion());
		map.put("totalReduccionEgr", totales.getReduccion());
		map.put("totalModificadoEgr", totales.getModifcado());
		map.put("totalEjercidoEgr", totales.getModifcado());
		map.put("totalVariacionInicialEgr", totales.getVariacionInicial());
		map.put("year", 2024);
		System.out.println(map);
		return map;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
