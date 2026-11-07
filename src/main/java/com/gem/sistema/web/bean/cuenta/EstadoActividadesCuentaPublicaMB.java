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
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.ServiceLDF;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.UtilLastDay;
import com.gem.sistema.web.bean.BaseDirectReport;

@Component("estadoActividadesCuentaPublicaMB")
@Scope("view")
public class EstadoActividadesCuentaPublicaMB extends BaseDirectReport {
	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@Value("#{informeAnaliticoDeudaPublicaService}")
	private ServiceLDF service;

	private ArrayList<TcPeriodo> listPeriodo;

	private TcPeriodo tcPeriodo;

	private char tipo = 't';

	@PostConstruct
	public void init() {
		jasperReporteName = "estado_actividades_cuenta_publica";
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

		Integer trimestre = tcPeriodo.getPeriodo();

		

		Conctb conctb = this.service.getAllConctb(this.getUserDetails().getIdSector());
		Integer year = conctb.getAnoemp();
		Integer lastDay = tipo == 't' ? UtilLastDay.lastDayOfMonth(year, trimestre)
				: UtilLastDay.lastDayOfMonthByMonth(year, trimestre);
		String month = getMonth(tipo, 'a',
				trimestre);

		String monthAct = getMonth(tipo, 'a', trimestre);
		String monthAnt = getMonth(tipo, 'b', trimestre);
		;
		map.put("lastDay", lastDay);
		map.put("month", month);
		map.put("monthAct", monthAct);
		map.put("monthAnt", monthAnt);
		map.put("year", year);
		map.put("img1", conctb.getImagePathRigth());
		map.put("img2", conctb.getImagePathLeft());
		

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
