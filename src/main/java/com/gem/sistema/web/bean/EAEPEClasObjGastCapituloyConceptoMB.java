
package com.gem.sistema.web.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

@ManagedBean(name = "EAEPEClasObjGastCapituloyConceptoMB")
@ViewScoped

public class EAEPEClasObjGastCapituloyConceptoMB extends BaseDirectReport {
	
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_EAEPE";

	private List<TcPeriodo> listMeses;
	private Integer mes;
	private Integer noDecimales;
	/** The orden. */
	private String orden = "S";

	private Integer pesos;
	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;
	
	

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;
	
	@ManagedProperty("#{executePlService}")
	private ExecutePlService executePlService;
	private StreamedContent txt;
	

	@PostConstruct
	public void init() {
		jasperReporteName = "EAEPEClasObjGastCapituloyConcepto";
		endFilename = jasperReporteName + ".pdf";

		listMeses = periodoRepositoy.findByTipoPeriodo(1);

		if (!listMeses.isEmpty()) {
			mes = listMeses.get(0).getPeriodo();
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		TcPeriodo mesSelected = periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mes);
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = null;

		parameters.put("pMunicipio", conctb.getNomDep());
		parameters.put("pLastDay", getLastDayByAnoEmp(mesSelected.getPeriodo(), conctb.getAnoemp()));
		parameters.put("pLastMonth", mesSelected.getDescripcion());
		parameters.put("pYear", conctb.getAnoemp());
		parameters.put("pMunicipioClave", conctb.getClave());
		parameters.put("pImagenPath", conctb.getImagePathLeft());
		parameters.put("pesos", pesos);
		parameters.put("decimalFormat", "%,." + noDecimales + "f");
		parameters.put("ctasOrden", orden);
		parameters.put("claveMunicipio", conctb.getClave());

		switch (conctb.getClave().substring(0, 1)) {
		case "0":
			parameters.put("pNoFirmas", 2);
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F01.getValue());
			parameters.put("pL1", firma.getPuesto().getPuesto());
			parameters.put("pN1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F03.getValue());
			parameters.put("pL3", firma.getPuesto().getPuesto());
			parameters.put("pN3", firma.getNombre());
			break;

		case "2":
			parameters.put("pNoFirmas", 3);
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F10.getValue());
			parameters.put("pL1", firma.getPuesto().getPuesto());
			parameters.put("pN1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F11.getValue());
			parameters.put("pL2", firma.getPuesto().getPuesto());
			parameters.put("pN2", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F24.getValue());
			parameters.put("pL3", firma.getPuesto().getPuesto());
			parameters.put("pN3", firma.getNombre());
			break;
		case "3":
			parameters.put("pNoFirmas", 3);
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F07.getValue());
			parameters.put("pL1", firma.getPuesto().getPuesto());
			parameters.put("pN1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F08.getValue());
			parameters.put("pL2", firma.getPuesto().getPuesto());
			parameters.put("pN2", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F09.getValue());
			parameters.put("pL3", firma.getPuesto().getPuesto());
			parameters.put("pN3", firma.getNombre());
			break;

		case "4":
			parameters.put("pNoFirmas", 2);
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F20.getValue());
			parameters.put("pL1", firma.getPuesto().getPuesto());
			parameters.put("pN1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F21.getValue());
			parameters.put("pL3", firma.getPuesto().getPuesto());
			parameters.put("pN3", firma.getNombre());
			break;
		}

		parameters.put("pSql", this.generateSQL(idSector, mes));

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateSQL(Integer idSector, Integer mes) {
		StringBuilder sql = new StringBuilder();

		String auto = "SUM(";
		String ejpa = "SUM(";
		String redu = "SUM(";
		String ejxpa = "SUM(";
		String ampli = "SUM(";
		String comp = "SUM(";
		String ejerc = "SUM(";

		for (int y = 1; y <= mes; y++) {
//			auto = auto + " PA.AUTO1_" + y + " +";
			ejpa = ejpa + " PA.EJPA1_" + y + " +";
			redu = redu + " PA.REDU1_" + y + " +";
			ejxpa = ejxpa + " PA.TOEJE1_" + y + " +";
			ampli = ampli + " PA.AMPLI1_" + y + " +";
			comp = comp + " PA.COMPRO1_" + y + " +";
			ejerc = ejerc + " PA.TOEJE1_" + y + " +";

		}

		for (int y = 1; y <= 12; y++) {
			auto = auto + " PA.AUTO1_" + y + " +";
		}

		auto = auto.substring(0, auto.length() - 2) + " ) AUTO, ";
		ejpa = ejpa.substring(0, ejpa.length() - 2) + " ) EJPA, ";
		redu = redu.substring(0, redu.length() - 2) + " ) REDU, ";
		ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " ) EJXPA, ";
		ampli = ampli.substring(0, ampli.length() - 2) + " ) AMPLI, ";
		comp = comp.substring(0, comp.length() - 2) + ")COMP,";
		ejerc=ejerc.substring(0, ejerc.length()-2)+")EJERC";

		sql.append("SELECT T1.PARTIDA, T1.NOMGAS, T1.AUTO APROBADO, (T1.AMPLI - T1.REDU) AMPLIREDU, ").append(
				"(T1.AUTO + T1.AMPLI - T1.REDU) MODIFICADO,T1.COMP COMPROMETIDO ,T1.EJXPA DEVENGADO,T1.EJXPA EJERCIDO ,T1.EJPA PAGADO, ")
				.append("(T1.AUTO + T1.AMPLI - T1.REDU - T1.EJXPA) SUBEJECICIO ")
				.append(" FROM ( SELECT PA.PARTIDA, NA.NOMGAS, ").append(auto).append(ampli).append(redu).append(ejxpa)
				.append(ejpa).append(comp).append(ejerc).append(" FROM PASO PA, NATGAS NA ")
				.append("WHERE PA.PARTIDA = NA.CLVGAS AND PA.IDSECTOR = NA.IDSECTOR AND PA.IDSECTOR = ")
				.append(idSector).append(" GROUP BY PA.PARTIDA, NA.NOMGAS ) T1 ORDER BY T1.PARTIDA");
		System.out.println(sql);
		return sql.toString();
	}
	
	public StreamedContent getTxt() {
		Map<String, Object> out;
		InputStream stream = null;
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String query =this.generateSQL(this.getUserDetails().getIdSector(), mes);

		parameter.addValue("i_Mes", mes);
		parameter.addValue("i_Decimales", noDecimales);
		parameter.addValue("i_Query", query);

		out = executePlService.callProcedure(PROCEDURE_NAME, parameter);

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(
						new File(out.get("O_FULL_PATH").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			txt = new DefaultStreamedContent(stream, "application/txt", out.get("O_FILE_NAME").toString());
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSJ_ERROR").toString());
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSJ_ERROR").toString());
		}

		return txt;
	}

	public void setTxt(StreamedContent txt) {
		this.txt = txt;
	}

	public List<TcPeriodo> getListMeses() {
		return listMeses;
	}

	public void setListMeses(List<TcPeriodo> listMeses) {
		this.listMeses = listMeses;
	}

	public Integer getMes() {
		return mes;
	}

	public Integer getNoDecimales() {
		return noDecimales;
	}

	public void setNoDecimales(Integer noDecimales) {
		this.noDecimales = noDecimales;
	}

	public Integer getPesos() {
		return pesos;
	}

	public void setPesos(Integer pesos) {
		this.pesos = pesos;
	}

	/**
	 * Gets the orden.
	 *
	 * @return the orden
	 */
	public String getOrden() {
		return orden;
	}

	/**
	 * Sets the orden.
	 *
	 * @param orden the new orden
	 */
	public void setOrden(String orden) {
		this.orden = orden;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
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

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
	}

}
