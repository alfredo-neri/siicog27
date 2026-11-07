package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilReport.getNameFileReport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.UtilLastDay;

// TODO: Auto-generated Javadoc
/**
 * The Class CPDeudaOtrosPasivosMB.
 * 
 * @author Alfredo
 * @Timeline <strong>Date:</strong> 10/09/2021 <strong>Changes:</strong> Se
 *           crean componentes del reporte, y se implementa servicio de Firmas
 *           dinamicas<br>
 * @Sector Municipal
 *
 */
@Component("cpDeudaOtrosPasivosMB")
@Scope("view")
public class CPDeudaOtrosPasivosMB extends BaseDirectReport {

	private static final String CLAVE_REPORT = "RPT_CP_EDO_ANA_DEU_OTR_PAS";

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;
	
	private ArrayList<TcPeriodo> listPeriodo;

	private TcPeriodo tcPeriodo;
	
	private ArrayList<TcPeriodo> listMeses;

	private TcPeriodo tcMes;
	
	private boolean mensual;
	
	private String lbl;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "CPDeudaOtrosPasivosInfoem";
		listPeriodo = (ArrayList<TcPeriodo>) this.tcPeriodoRepositoy.findByTipoPeriodo(3);
		tcPeriodo = listPeriodo.get(0);
		listMeses = (ArrayList<TcPeriodo>) this.tcPeriodoRepositoy.findByTipoPeriodo(1);
		tcMes = listMeses.get(0);
		mensual = false;
		lbl = "Trimestre";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();

		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);
		Integer periodo = mensual ? tcMes.getPeriodo() : tcPeriodo.getPeriodo();
		endFilename = getNameFileReport(periodo,conctb, reporte);
		Integer lastDay = UtilLastDay.lastDayOfMonth(conctb.getAnoemp(), periodo, mensual);
		String month = UtilLastDay.getMonthOfYear(periodo, mensual);
		Integer lastDayB = 0;
		String monthB = "";
		if(!mensual) {
			Integer periodoB = periodo == 2 ? 3 : periodo == 3 ? 6 : periodo == 4 ? 9 : 1;
			lastDayB = periodo == 1 ? 1 : UtilLastDay.lastDayOfMonth(conctb.getAnoemp(), periodoB, true);
			monthB = UtilLastDay.getMonthOfYear(periodoB, true);
		}
		
		parameters.put("lastDay", lastDay);
		parameters.put("month", month);
		parameters.put("lastDayB", lastDayB);
		parameters.put("monthB", monthB);
		parameters.put("imagenPathLeft", conctb.getImagePathRigth());
		parameters.put("imagenPathRigth", StringUtils.EMPTY);
		parameters.put("year", conctb.getAnoemp());
		parameters.put("decimalFormat", "%,.2f");
		parameters.put("pSql", this.generateQuery(sector));
		parameters.put("mensual", new Boolean(mensual));
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parameters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateQuery(Integer idsector) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder cargosAnt = new StringBuilder();
		StringBuilder abonosAnt = new StringBuilder();
		StringBuilder cargosAct = new StringBuilder();
		StringBuilder abonosAct = new StringBuilder();
		Integer mes = mensual ? tcMes.getPeriodo() : getMonth(tcPeriodo.getPeriodo());
		if(mes == 1) {
			cargosAnt.append("0");
			abonosAnt.append("0");
		} else {
			if(mensual) {
				for (int i = 1; i < mes; i++) {
					cargosAnt.append("CARGOS_" + i);
					abonosAnt.append("ABONOS_" + i);
					if (i < mes-1) {
						cargosAnt.append(" + ");
						abonosAnt.append(" + ");
					}
				}
			} else {
				int mesT = (mes == 3 ? mes-2 : mes -3);
				for (int i = 1; i <= mesT; i++) {
					cargosAnt.append("CARGOS_" + i);
					abonosAnt.append("ABONOS_" + i);
					if (i < mesT) {
						cargosAnt.append(" + ");
						abonosAnt.append(" + ");
					}
				}
			}
		}
		
		for (int i = 1; i <= mes; i++) {
			cargosAct.append("CARGOS_" + i);
			abonosAct.append("ABONOS_" + i);
			if (i < mes) {
				cargosAct.append(" + ");
				abonosAct.append(" + ");
			}
		}

		sSql.append("SELECT ")
		.append("'Otros Pasivos' NOMCTA, ")
		.append("SALINI-CARGOS_ANT+ABONOS_ANT SALDO_INICIAL, ")
		.append("SALINI-CARGOS_ACT+ABONOS_ACT SALDO_FINAL ")
		.append("FROM ( ")
		.append("SELECT   ")
		.append("SALINI,  ")
		.append(cargosAnt+" CARGOS_ANT, ")
		.append(abonosAnt+" ABONOS_ANT, ")
		.append(cargosAct+" CARGOS_ACT, ")
		.append(abonosAct+" ABONOS_ACT ")
		.append("FROM CUENTA ")
		.append("WHERE CUENTA = '2000' ")
		.append("AND IDSECTOR = "+idsector+" )");

		System.out.println(sSql.toString());
		return sSql.toString();
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

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
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
