package com.gem.sistema.web.bean.cuenta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.MesTrimestreAnteriorUtil.getMonth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.dto.TotalFlujoEfectivo;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.ServiceLDF;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.UtilLastDay;
import com.gem.sistema.web.bean.BaseDirectReport;

@Component("estadoFlujoEfectivoCuentaPublicaMB")
@Scope("view")
public class EstadoFlujoEfectivoCuentaPublicaMB extends BaseDirectReport {
	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@Value("#{informeAnaliticoDeudaPublicaService}")
	private ServiceLDF service;

	private ArrayList<TcPeriodo> listPeriodo;

	private TcPeriodo tcPeriodo;

	private char tipo = 't';

	@PostConstruct
	public void init() {
		jasperReporteName = "estado_flujo_efectivo_cuentaPublica";
		endFilename = jasperReporteName + ".pdf";
		listPeriodo = (ArrayList<TcPeriodo>) this.tcPeriodoRepositoy.findByTipoPeriodo(3);
		tcPeriodo = listPeriodo.get(0);

	}

	public void changeByType() {
		tcPeriodo = new TcPeriodo();
		if (tipo == 't') {
			listPeriodo = (ArrayList<TcPeriodo>) this.tcPeriodoRepositoy.findByTipoPeriodo(3);
			tcPeriodo = listPeriodo.get(3);
		} else {
			listPeriodo = (ArrayList<TcPeriodo>) this.tcPeriodoRepositoy.findByTipoPeriodo(1);
			tcPeriodo = listPeriodo.get(11);
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> map = new HashMap<String, Object>();

		Conctb conctb = this.service.getAllConctb(this.getUserDetails().getIdSector());
		Integer year = conctb.getAnoemp();
		Integer trimestre = tcPeriodo.getPeriodo();
		Integer lastDay = tipo == 't' ? UtilLastDay.lastDayOfMonth(year, trimestre)
				: UtilLastDay.lastDayOfMonthByMonth(year, trimestre);
		String month = getMonth(tipo, 'a', trimestre);

		String monthAct = getMonth(tipo, 'a', trimestre);
		String monthAnt = getMonth(tipo, 'b', trimestre);

		TotalFlujoEfectivo totalAct = this.service.getQueryTotalFlujoEfectivoLDF(tipo, trimestre);

		TotalFlujoEfectivo totalAnt = this.service.getQueryTotalFlujoEfectivoLDF(tipo,
				tipo == 't' ? trimestre - 1 : trimestre - 1);
		map.put("lastDay", lastDay);
		map.put("monthAct", monthAct);
		map.put("monthAnt", monthAnt);
		map.put("month", month);
		map.put("year", conctb.getAnoemp());
		map.put("img1", conctb.getImagePathRigth());
		map.put("img2", conctb.getImagePathLeft());
		map.put("totalEfectivoEqAct1", totalAct.getSubTotalAnt1());
		map.put("totalEfectivoEqAct2", totalAct.getSubTotalAct1());
		map.put("totalEfectivoEqAnt1", totalAnt.getSubTotalAnt1());
		map.put("totalEfectivoEqAnt2", totalAnt.getSubTotalAct1());

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

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

}
