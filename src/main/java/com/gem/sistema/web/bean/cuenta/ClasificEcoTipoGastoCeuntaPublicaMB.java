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
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ServiceLDF;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.UtilLastDay;
import com.gem.sistema.web.bean.BaseDirectReport;

@Component("clasificEcoTipoGastoCeuntaPublicaMB")
@Scope("view")
public class ClasificEcoTipoGastoCeuntaPublicaMB extends BaseDirectReport{
	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{informeAnaliticoDeudaPublicaService}")
	private ServiceLDF service;
	
	private ArrayList<TcPeriodo> listPeriodo;
	private TcPeriodo tcPeriodo;
	private ArrayList<TcPeriodo> listMeses;
	private TcPeriodo tcMes;
	private boolean mensual;
	private String lbl;

	@PostConstruct
	public void init() {
		jasperReporteName = "clasEconInfoem";
		endFilename = jasperReporteName + ".pdf";

		listPeriodo = (ArrayList<TcPeriodo>) this.periodoRepositoy.findByTipoPeriodo(3);
		tcPeriodo = listPeriodo.get(0);
		listMeses = (ArrayList<TcPeriodo>) this.periodoRepositoy.findByTipoPeriodo(1);
		tcMes = listMeses.get(11);
		mensual = false;
		lbl = "Trimestre";
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Integer periodo = tcMes.getPeriodo(); 
		Conctb conctb = this.service.getAllConctb(this.getUserDetails().getIdSector());
		Integer lastDay = UtilLastDay.lastDayOfMonth(conctb.getAnoemp(), periodo, true);
		String month = UtilLastDay.getMonthOfYear(periodo, true);
		
		parameters.put("ente", conctb.getNomDep());
		parameters.put("pDay", lastDay);
		parameters.put("pMes", month);
		parameters.put("pYear", conctb.getAnoemp());
		parameters.put("pImage2", conctb.getImagePathRigth());
		parameters.put("pQuery", this.generateSQL(idSector));
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, "RPT_INFOEM"));

		return parameters;
	}

	private String generateSQL(Integer idSector) {
		StringBuilder sql = new StringBuilder();
		
		StringBuilder ampli = new StringBuilder();
		StringBuilder redu = new StringBuilder();
		StringBuilder compro = new StringBuilder();
		StringBuilder ejxpa = new StringBuilder();
		StringBuilder ejpa = new StringBuilder();
		Integer month = mensual ? tcMes.getPeriodo() : getMonth(tcPeriodo.getPeriodo());

		for (int i = 1; i <= month; i++) {
			ampli.append("P.AMPLI1_").append(i);
			redu.append("P.REDU1_").append(i);
			compro.append("P.COMPRO1_").append(i);
			ejxpa.append("P.EJXPA1_").append(i);
			ejpa.append("P.EJPA1_").append(i);
			if (i < month) {
				ampli.append(" + ");
				redu.append(" + ");
				compro.append(" + ");
				ejxpa.append("+");
				ejpa.append("+");
			}
		}

		sql.append("SELECT ");
		sql.append("1 CUENTA, ");
		sql.append("'Gasto Corriente' CONCEPTO, ");
		sql.append("SUM(AUTORIZADO) APROBADO, ");
		sql.append("SUM(AMPLIACION)-SUM(REDUCCION) AMP_RED, ");
		sql.append("SUM(MODIFICADO) MODIFICADO, ");
		sql.append("SUM(EJERCIDO) DEVENGADO, ");
		sql.append("SUM(PAGADO) PAGADO, ");
		sql.append("SUM(MODIFICADO)-SUM(EJERCIDO) SUB_E ");
		sql.append("FROM( ");
		sql.append("SELECT ");
		sql.append("CLAVE, ");
		sql.append("NOMBRE, ");
		sql.append("AUTORIZADO, ");
		sql.append("AMPLIACION, ");
		sql.append("REDUCCION, ");
		sql.append("(AUTORIZADO + AMPLIACION - REDUCCION) MODIFICADO, ");
		sql.append("COMPROMETIDO, ");
		sql.append("DEVENGADO, ");
		sql.append("PAGADO, ");
		sql.append("(COMPROMETIDO + DEVENGADO + PAGADO) EJERCIDO ");
		sql.append("FROM ");
		sql.append("( ");
		sql.append("SELECT ");
		sql.append("SUBSTR(P.PROGRAMA, ");
		sql.append("1, ");
		sql.append("4) CLAVE, ");
		sql.append("M.CAMPO6 NOMBRE, ");
		sql.append("SUM(P.AUTO1_1 + P.AUTO1_2 + P.AUTO1_3 + P.AUTO1_4 + P.AUTO1_5 + P.AUTO1_6 +  ");
		sql.append("P.AUTO1_7 + P.AUTO1_8 + P.AUTO1_9 + P.AUTO1_10 + P.AUTO1_11 + P.AUTO1_12 ) AUTORIZADO, ");
		sql.append("SUM( "+ampli+" ) AMPLIACION, ");
		sql.append("SUM( "+redu+" ) REDUCCION, ");
		sql.append("SUM( "+compro+" ) COMPROMETIDO, ");
		sql.append("SUM( "+ejxpa+" ) DEVENGADO, ");
		sql.append("SUM( "+ejpa+" ) PAGADO ");
		sql.append("FROM ");
		sql.append("PASO P ");
		sql.append("INNER JOIN ");
		sql.append("MUNINEP M ");
		sql.append("ON ");
		sql.append("M.IDSECTOR = P.IDSECTOR AND ");
		sql.append("M.CAMPO7 = SUBSTR(P.PROGRAMA, 1, 4) ");
		sql.append("WHERE ");
		sql.append("P.IDSECTOR = "+idSector+" AND ");
		sql.append("SUBSTR(P.PARTIDA, 4, 1) <> '0' ");
		sql.append("GROUP BY ");
		sql.append("SUBSTR(P.PROGRAMA, 1, 4) , M.CAMPO6 ) T1 ) ");
		sql.append("UNION ");
		sql.append("SELECT ");
		sql.append("2 CUENTA, ");
		sql.append("'Gasto de Capital' CONCEPTO, ");
		sql.append("0.00 APROBADO, ");
		sql.append("0.00 AMP_RED, ");
		sql.append("0.00 MODIFICADO, ");
		sql.append("0.00 DEVENGADO, ");
		sql.append("0.00 PAGADO, ");
		sql.append("0.00 SUB_E ");
		sql.append("FROM SYSIBM.SYSDUMMY1 ");
		sql.append("UNION ");
		sql.append("SELECT ");
		sql.append("3 CUENTA, ");
		sql.append("'Amortización de la Deuda y Disminución de Pasivos' CONCEPTO, ");
		sql.append("0.00 APROBADO, ");
		sql.append("0.00 AMP_RED, ");
		sql.append("0.00 MODIFICADO, ");
		sql.append("0.00 DEVENGADO, ");
		sql.append("0.00 PAGADO, ");
		sql.append("0.00 SUB_E ");
		sql.append("FROM SYSIBM.SYSDUMMY1 ");
		sql.append("UNION ");
		sql.append("SELECT ");
		sql.append("4 CUENTA, ");
		sql.append("'Pensiones y Jubilaciones' CONCEPTO, ");
		sql.append("0.00 APROBADO, ");
		sql.append("0.00 AMP_RED, ");
		sql.append("0.00 MODIFICADO, ");
		sql.append("0.00 DEVENGADO, ");
		sql.append("0.00 PAGADO, ");
		sql.append("0.00 SUB_E ");
		sql.append("FROM SYSIBM.SYSDUMMY1 ");
		sql.append("UNION ");
		sql.append("SELECT ");
		sql.append("5 CUENTA, ");
		sql.append("'Participaciones' CONCEPTO, ");
		sql.append("0.00 APROBADO, ");
		sql.append("0.00 AMP_RED, ");
		sql.append("0.00 MODIFICADO, ");
		sql.append("0.00 DEVENGADO, ");
		sql.append("0.00 PAGADO, ");
		sql.append("0.00 SUB_E ");
		sql.append("FROM SYSIBM.SYSDUMMY1 ");
		sql.append("ORDER BY 1 ");
		
		System.out.println(sql.toString());
		return sql.toString();
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
		return month;
	}
	
	public void cambiarMensual() {
		lbl = mensual ? "Mes" : "Trimestre";
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
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

	public ServiceLDF getService() {
		return service;
	}

	public void setService(ServiceLDF service) {
		this.service = service;
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
