package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.JasperGenericServiceImpl;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@Component("gastoGlobalFuenteMB")
@Scope("view")
public class GastoGlobalFuenteMB extends BaseDirectReport {
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";
	private String mes;
	private String cuenta;
	private String partida;
	private String fuenteF;
	private Integer reporte;
	private List<TcMes> listMes = new ArrayList<TcMes>();
	private Boolean txt = Boolean.FALSE;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@PostConstruct
	public void init() {
		JasperGenericServiceImpl.CHAR_WIDTH = 5.45f;
		JasperGenericServiceImpl.CHAR_HEIGHT = 11.85f;
		jasperReporteName = "gastoGlobalFuente";
		endFilename = jasperReporteName + ".pdf";

		this.reporte = 0;
		listMes = tcMesRepository.findAll();
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		parameters.put("entidadName", conctb.getNomDep());
		parameters.put("txt", txt);
		parameters.put("sector", idSector);
		parameters.put("mes", mes);
		parameters.put("cuenta", cuenta);
		parameters.put("ssscta", partida);
		parameters.put("sscta", fuenteF);
		parameters.put("sql", this.generaQuery());
		return parameters;
	}

	private String generaQuery() {
		String sql = "SELECT D.SSSCTA,\r\n"
				+ "	SUM(D.CANPOL) CARGO,\r\n"
				+ "	SUM(D.CANPOLH) ABONO ,\r\n"
				+ "	SUM(D.CANPOL)-SUM(D.CANPOLH) DIF\r\n"
				+ " FROM POLIDE D\r\n"
				+ " WHERE "
				+ (reporte == 1 ?  "D.MESPOL <= "+Integer.valueOf(mes)+" AND ":" D.MESPOL = "+Integer.valueOf(mes)+" AND ")
				+ "	D.IDSECTOR = "+this.getUserDetails().getIdSector()+" AND \r\n"
				+ "	D.CUENTA = '"+cuenta+"' AND \r\n"
				+ "	D.SSSCTA LIKE  '"+partida+"%' AND\r\n"
				+ "	D.SSCTA <>'' AND \r\n"
				+ "	SUBSTR(D.SSCTA ,11,8) = '"+fuenteF+"'\r\n"
				+ " GROUP BY\r\n"
				+ "	D.SSSCTA";
		System.out.println(":::::" + sql);
		return sql;
	}
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {

		return null;
	}

	private Boolean inputsValids() {
		Boolean salida = Boolean.TRUE;
		if (!validNumber(cuenta, "Cuenta")) {
			salida = Boolean.FALSE;
		}
		if (!validNumber(partida, "Partida")) {
			salida = Boolean.FALSE;
		}
		if (!validNumber(fuenteF, "Fuente financiamiento")) {
			salida = Boolean.FALSE;
		}
		return salida;
	}

	private Boolean validNumber(String value, String name) {
		Boolean salida = Boolean.TRUE;
		if (value != null && value != "") {
			try {
				Integer.parseInt(value);
			} catch (NumberFormatException e) {
				displayInfoMessage("El campo " + name + " debe de ser numerico");
				salida = Boolean.FALSE;
			}
		} else {
			displayInfoMessage("El campo " + name + " no puede ser vacio");
			salida = Boolean.FALSE;
		}
		return salida;
	}

	public void displayInfoMessage(String str) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", str);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void createPDF() {
		if (inputsValids()) {
			this.txt = Boolean.FALSE;
			createFilePdfInline();
		}
	}

	public void downlandXLS() {
		if (inputsValids()) {
			this.txt = Boolean.FALSE;
			PrimeFaces.current().executeScript(DOWNLOAD_XLS);
		}

	}

	public void downlandTXT() {
		if (inputsValids()) {
			this.txt = Boolean.TRUE;
			PrimeFaces.current().executeScript(DOWNLOAD_TXT);
		}
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getPartida() {
		return partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	public String getFuenteF() {
		return fuenteF;
	}

	public void setFuenteF(String fuenteF) {
		this.fuenteF = fuenteF;
	}

	public List<TcMes> getListMes() {
		return listMes;
	}

	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
	}

	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public Boolean getTxt() {
		return txt;
	}

	public void setTxt(Boolean txt) {
		this.txt = txt;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public Integer getReporte() {
		return reporte;
	}

	public void setReporte(Integer reporte) {
		this.reporte = reporte;
	}

}
