package com.gem.sistema.web.bean.cuenta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.EdoSF3211Service;
import com.gem.sistema.business.service.catalogos.ServiceLDF;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.UtilLastDay;
import com.gem.sistema.web.bean.BaseDirectReport;


@Component("estadoSituacionFinancieraCuentaPublicaMB")
@Scope("view")
public class EstadoSituacionFinancieraCuentaPublicaMB  extends BaseDirectReport{
	
	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@Value("#{informeAnaliticoDeudaPublicaService}")
	private ServiceLDF service;

	private ArrayList<TcPeriodo> listPeriodo;

	private char tipo = 't';

	private TcPeriodo tcPeriodo;
	
	@Value("#{edoSF3211Service}")
	private EdoSF3211Service edoSF3211Service;

	@PostConstruct
	public void init() {
		jasperReporteName = "estado_situacion_financiera_cuentaPublica";
		endFilename = jasperReporteName + ".pdf";
		listPeriodo = (ArrayList<TcPeriodo>) this.tcPeriodoRepositoy.findByTipoPeriodo(3);
		tcPeriodo = listPeriodo.get(3);

	}

	public void changeTipo() {
		listPeriodo = (ArrayList<TcPeriodo>) this.tcPeriodoRepositoy.findByTipoPeriodo(tipo == 'm' ? 1 : 3);
		tcPeriodo = listPeriodo.get(3);

	}

	Integer index = 0;
	String monthAnt = "";
	String month = "";

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer trimestre = tcPeriodo.getPeriodo();
		
		String sSqlActivo = this.service.getQueryEstadoSituacionFinancieraConac(trimestre, tipo, 'a');
		String sSqlPasivo = this.service.getQueryEstadoSituacionFinancieraConac(trimestre, tipo, 'p');
		String sSqlHacienda = this.service.getQueryEstadoSituacionFinancieraConac(trimestre, tipo, 'h');

		Conctb conctb = this.service.getAllConctb(this.getUserDetails().getIdSector());

		index = tipo == 't' ? tcPeriodo.getPeriodo() - 3 : tcPeriodo.getPeriodo() - 1;
		monthAnt = "Enero";
		if (index > 0)
			monthAnt = listPeriodo.get(index - 1).getDescripcion();

		Integer lastDay = 0;// UtilLastDay.lastDayOfMonth(conctb.getAnoemp(), trimestre);
		
		
		month = UtilLastDay.getMonthOfYear(trimestre);// 't' ;//? UtilLastDay.getMonthOfYear(trimestre) : listPeriodo.get(index).getDescripcion();
		BigDecimal cuenta3211Act = null;// edoSF3211Service.executeQuery(2, tcPeriodo.getPeriodo()).getTotalAct();
		BigDecimal cuenta3211Ant = null; //edoSF3211Service.executeQuery(2, getTrimetreMes(tipo, tcPeriodo.getPeriodo())).getTotalAnt();
		map.put("lastDay", lastDay);
		map.put("month", month);
		map.put("ente", conctb.getNomDep());
		map.put("year", conctb.getAnoemp());
		map.put("img1", conctb.getImagePathRigth());
		map.put("img2", conctb.getImagePathLeft());
		map.put("sSqlActivos", sSqlActivo);
		map.put("sSqlPasivos", sSqlPasivo);
		map.put("sSqlHacienda", sSqlHacienda);
		map.put("cuenta3211Act", cuenta3211Act);
		map.put("cuenta3211Ant", cuenta3211Ant);
		map.put("monthAnt", monthAnt);

		return map;
	}
	
	private int getTrimetreMes(char tipoCuenta, Integer value) {
		if(tipoCuenta == 't') {
			value = value - 3;
			if(value < 0)
				value = 0;
		} else {
			value = value - 1;
			if(value < 0)
				value = 0;
		}
		return value;
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

	public EdoSF3211Service getEdoSF3211Service() {
		return edoSF3211Service;
	}

	public void setEdoSF3211Service(EdoSF3211Service edoSF3211Service) {
		this.edoSF3211Service = edoSF3211Service;
	}

}
