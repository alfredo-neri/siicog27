package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
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
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;

// TODO: Auto-generated Javadoc
/**
 * The Class RF009115MB.
 */
@ManagedBean
@ViewScoped
public class RF009115MB extends BaseDirectReport {

	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_ESTADO_INGRESOSD";
	private static final String CLAVE_REPORT = "RPT_ESTADO_INGRESOSD";

	/** The mes. */
	private Integer mes;

	/** The firmas. */
	private Integer firmas;

	/** The txt. */
	private StreamedContent txt;

	/** The execute pl service. */
	@ManagedProperty("#{executePlService}")
	protected ExecutePlService executePlService;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/** The out. */
	Map<String, Object> out;

	/** The stream. */
	InputStream stream = null;

	/**
	 * Gets the txt.
	 *
	 * @return the txt
	 */

	public StreamedContent getTxt() {
		addToBitacora(true);
		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("i_usuario", this.getUserDetails().getUsername());
		parameter.addValue("i_query", this.generateQuery(this.getUserDetails().getIdSector(), mes));
		parameter.addValue("i_mes", mes);
		parameter.addValue("i_firmas", firmas);

		out = executePlService.callProcedure(PROCEDURE_NAME, parameter);

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(
						new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			txt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
		}
		addToBitacora(false);
		return txt;
	}

	/**
	 * Sets the txt.
	 *
	 * @param txt the new txt
	 */
	public void setTxt(StreamedContent txt) {
		this.txt = txt;
	}


	/**
	 * Gets the execute pl service.
	 *
	 * @return the execute pl service
	 */
	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	/**
	 * Sets the execute pl service.
	 *
	 * @param executePlService the new execute pl service
	 */
	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RF009115MB ");
		jasperReporteName = "RF009_1_15";
		endFilename = jasperReporteName + ".pdf";
		
		firmas = 4;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		
		Map<String, Object> paramsReport = new HashMap<String, Object>();
		
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcPeriodo periodo = periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mes);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);
		
		endFilename = getNameFileReport(mes, conctb, reporte);

		paramsReport.put("mes", mes);
		paramsReport.put("sql", this.generateQuery(idSector, mes));
		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("lastDay", getLastDayByAnoEmp(mes, conctb.getAnoemp()));
		paramsReport.put("mesName", periodo.getDescripcion().toUpperCase());
		paramsReport.put("idSector", idSector);
		paramsReport.put("imageLeft", conctb.getImagePathLeft());
		paramsReport.put("imageRigth", conctb.getImagePathRigth());
		paramsReport.put("entidadName", conctb.getNomDep());
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return paramsReport;
	}

	/**
	 * Generate query.
	 *
	 * @param idSector the id sector
	 * @param mes      the mes
	 * @return the string
	 */
	public String generateQuery(Integer idSector, Integer mes) {
		StringBuilder cargo10 = new StringBuilder();
		StringBuilder abono10 = new StringBuilder();
		StringBuilder cargo50 = new StringBuilder();
		StringBuilder abono50 = new StringBuilder();
		StringBuilder ingreso = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Integer i = 1;

		while (i <= mes) {
			cargo10.append(" NVL(DI.CARGOS_" + i + ",0) + ");
			abono10.append(" NVL(DI.ABONOS_" + i + ",0) + ");
			cargo50.append(" NVL(CI.CARGOS_" + i + ",0) + ");
			abono50.append(" NVL(CI.ABONOS_" + i + ",0) + ");
			ingreso.append(" NVL(II.AUTOI_" + i + ",0) + ");
			i++;
		}
		query.append(
				"SELECT DISTINCT CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA, NOMCTA, ESTIMADA, MODIFICADO_MES, RECAUDADO_MES, ")
				.append("MODIFICADO_ACUMULADO, RECAUDADO_ACUMULADO, (RECAUDADO_ACUMULADO - MODIFICADO_ACUMULADO) VARABS, ")
				.append("CASE WHEN MODIFICADO_ACUMULADO = 0 THEN 0 ")
				.append("ELSE ABS(((RECAUDADO_ACUMULADO - MODIFICADO_ACUMULADO) / MODIFICADO_ACUMULADO) * 100) END ABSOLUTO ")
				.append("FROM (SELECT DI.CUENTA, DI.SCTA, DI.SSCTA, DI.SSSCTA, DI.SSSSCTA, DI.NOMCTA, ")
				.append("NVL(DI.SALINI,0) ESTIMADA, ")
				.append("(NVL(II.AUTOI_" + mes + ",0) + DI.CARGOS_" + mes + " - DI.ABONOS_" + mes + ") MODIFICADO_MES, ")
				.append("(NVL(CI.ABONOS_" + mes + ",0) - NVL(CI.CARGOS_" + mes + ",0)) RECAUDADO_MES, ")
				.append("(" + ingreso.substring(0, ingreso.length() - 2) + ") + (" + cargo10.substring(0, cargo10.length() - 2) + ") - (" + abono10.substring(0, abono10.length() - 2) + ") MODIFICADO_ACUMULADO, ")
				.append("((" + abono50.substring(0, abono50.length() - 2) + ") - ("
						+ cargo50.substring(0, cargo50.length() - 2) + ")) RECAUDADO_ACUMULADO")
				.append(" FROM CUENTA AS DI ")
				.append("INNER JOIN CUENTA AS CI ON CI.CUENTA= '8150' AND CI.SCTA=DI.SCTA AND CI.SSCTA=DI.SSCTA ")
				.append("AND CI.SSSCTA=DI.SSSCTA AND CI.SSSSCTA=DI.SSSSCTA AND CI.IDSECTOR = DI.IDSECTOR ")
				.append("LEFT JOIN INGRESO AS II ON DI.CUENTA = II.CUENTA AND DI.SCTA = II.SCTA AND DI.SSCTA=II.SSCTA ")
				.append("AND DI.SSSCTA = II.SSSCTA AND DI.SSSSCTA=II.SSSSCTA AND DI.IDSECTOR=II.IDSECTOR ")
				.append("WHERE (DI.CUENTA='8110') AND DI.SCTA <> '' AND DI.SSCTA <> '' AND DI.SSSCTA <> '' ")
				.append("AND DI.SSSSCTA <> '' AND DI.IDSECTOR=" + idSector)
				.append(" ORDER BY DI.CUENTA,DI.SCTA, DI.SSCTA, DI.SSSCTA, DI.SSSSCTA ) T1");

		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println(query.toString());
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		return query.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}

	/**
	 * Gets the firmas.
	 *
	 * @return the firmas
	 */
	public Integer getFirmas() {
		return firmas;
	}

	/**
	 * Sets the firmas.
	 *
	 * @param firmas the new firmas
	 */
	public void setFirmas(Integer firmas) {
		this.firmas = firmas;
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

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

}
/*
 * SELECT DISTINCT CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA, NOMCTA, ESTIMADA,
 * MODIFICADO_MES, RECAUDADO_MES, MODIFICADO_ACUMULADO, RECAUDADO_ACUMULADO,
 * (RECAUDADO_ACUMULADO - MODIFICADO_ACUMULADO) VARABS, CASE WHEN
 * MODIFICADO_ACUMULADO = 0 THEN 0 ELSE ABS(((RECAUDADO_ACUMULADO -
 * MODIFICADO_ACUMULADO) / MODIFICADO_ACUMULADO) * 100) END ABSOLUTO FROM
 * (SELECT DI.CUENTA, DI.SCTA, DI.SSCTA, DI.SSSCTA, DI.SSSSCTA, DI.NOMCTA,
 * (NVL(DI.SALINI,0) + (( NVL(DI.ABONOS_1,0) ) - ( NVL(DI.CARGOS_1,0) )))
 * ESTIMADA, NVL(II.AUTOI_1,0) MODIFICADO_MES, (NVL(CI.ABONOS_1,0) -
 * NVL(CI.CARGOS_1,0)) RECAUDADO_MES, ( NVL(II.AUTOI_1,0) )
 * MODIFICADO_ACUMULADO, (( NVL(CI.ABONOS_1,0) ) - ( NVL(CI.CARGOS_1,0) ))
 * RECAUDADO_ACUMULADO FROM CUENTA AS DI INNER JOIN CUENTA AS CI ON CI.CUENTA=
 * '8150' AND CI.SCTA=DI.SCTA AND CI.SSCTA=DI.SSCTA AND CI.SSSCTA=DI.SSSCTA AND
 * CI.SSSSCTA=DI.SSSSCTA AND CI.IDSECTOR = DI.IDSECTOR LEFT JOIN INGRESO AS II
 * ON DI.CUENTA = II.CUENTA AND DI.SCTA = II.SCTA AND DI.SSCTA=II.SSCTA AND
 * DI.SSSCTA = II.SSSCTA AND DI.SSSSCTA=II.SSSSCTA AND DI.IDSECTOR=II.IDSECTOR
 * WHERE (DI.CUENTA='8110') AND DI.SCTA <> '' AND DI.SSCTA <> '' AND DI.SSSCTA
 * <> '' AND DI.SSSSCTA <> '' AND DI.IDSECTOR=2 ORDER BY DI.CUENTA,DI.SCTA,
 * DI.SSCTA, DI.SSSCTA, DI.SSSSCTA ) T1
 * 
 */
