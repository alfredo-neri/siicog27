package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.GeneratePreviewService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.business.utils.ConvertCharsetUtils;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class CapitalTrabajoMB.
 */
@ManagedBean
@ViewScoped
public class CapitalTrabajoMB extends BaseDirectReport {

	/** The Constant FOCUS_BY_ROWID. */
	private static final String FOCUS_BY_ROWID = "PrimeFaces.focus('form1:preViewTxt');";

	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";

	/** The Constant SP_NAME. */
	private static final String SP_NAME = "SP_CAPITAL_TRABAJO";
	private static final String CLAVE_REPORT = "RPT_CAPITAL_TRABAJO";

	/** The mes. */
	private Integer mes;

	/** The firmas. */
	private Firmas firmas;

	/** The b pre view. */
	private Boolean bPreView;

	Integer idSector = this.getUserDetails().getIdSector();

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The execute pl service. */
	@ManagedProperty("#{executePlService}")
	private ExecutePlService executePlService;

	/** The generate preview service. */
	@ManagedProperty("#{generatePreviewService}")
	private GeneratePreviewService generatePreviewService;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/** The txt preview. */
	private StringBuilder txtPreview;

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
	 * Gets the txt preview.
	 *
	 * @return the txt preview
	 */
	public StringBuilder getTxtPreview() {
		return txtPreview;
	}

	/**
	 * Sets the txt preview.
	 *
	 * @param txtPreview the new txt preview
	 */
	public void setTxtPreview(StringBuilder txtPreview) {
		this.txtPreview = txtPreview;
	}

	/**
	 * Gets the generate preview service.
	 *
	 * @return the generate preview service
	 */
	public GeneratePreviewService getGeneratePreviewService() {
		return generatePreviewService;
	}

	/**
	 * Sets the generate preview service.
	 *
	 * @param generatePreviewService the new generate preview service
	 */
	public void setGeneratePreviewService(GeneratePreviewService generatePreviewService) {
		this.generatePreviewService = generatePreviewService;
	}

	/**
	 * Gets the b pre view.
	 *
	 * @return the b pre view
	 */
	public Boolean getbPreView() {
		return bPreView;
	}

	/**
	 * Sets the b pre view.
	 *
	 * @param bPreView the new b pre view
	 */
	public void setbPreView(Boolean bPreView) {
		this.bPreView = bPreView;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct CapitalTrabajoMB ");
		jasperReporteName = "CapitalTrabajo";
		endFilename = jasperReporteName + ".pdf";
		setbPreView(Boolean.FALSE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();

		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);
		
		endFilename = getNameFileReport(mes, conctb, reporte);
		
		paramsReport.put("P_MES", mes);
		paramsReport.put("P_ANO", conctb.getAnoemp());
		paramsReport.put("where", this.generateWhereActivo(mes));
		paramsReport.put("P_SECTOR", idSector);
		paramsReport.put("imageLeft", conctb.getImagePathLeft());
		paramsReport.put("imageRigth", conctb.getImagePathRigth());
		paramsReport.put("entidadName", conctb.getNomDep());
		paramsReport.put("queryPasivo", this.generateQueryPasivo(mes, idSector));
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return paramsReport;
	}

	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());

			RequestContext.getCurrentInstance().execute(DOWNLOAD_XLS);
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
			;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
		/*
		 * Map<String, Object> paramsQuery = new java.util.HashMap<String, Object>();
		 * paramsQuery.put("ID_REF", new Integer(0)); //FALTA return
		 * reporteadorDirectoImpl.getFileReport(tcReporte,paramsQuery,
		 * reporteName,type);
		 */
	}

	/**
	 * Generate query pasivo.
	 *
	 * @param mes      the mes
	 * @param idSector the id sector
	 * @return the string
	 */
	public String generateQueryPasivo(Integer mes, Integer idSector) {

		StringBuilder query = new StringBuilder();
		StringBuilder car_al = new StringBuilder();
		StringBuilder abo_al = new StringBuilder();
		StringBuilder car_an = new StringBuilder();
		StringBuilder abo_an = new StringBuilder();
		Integer i = 1;

		while (i <= mes) {
			car_al.append("+ CARGOS_" + i + " ");
			abo_al.append("+ ABONOS_" + i + " ");
			i = i + 1;
		}

		i = 1;
		if (mes == 1) {
			car_an.append(" 0 ");
			abo_an.append(" 0 ");
		} else {
			while (i < mes) {
				car_an.append("+ CARGOS_" + i + " ");
				abo_an.append("+ ABONOS_" + i + " ");
				i = i + 1;
			}
		}

		query.append("SELECT CUENTA, NATCTA, NOMCTA,")
				.append(" (0-(SALINI - CARGOS_MES_ANT + ABONOS_MES_ANT)) MES_ANTERIOR,")
				.append(" (0 - (SALINI - CARGOS_AL_MES + ABONOS_AL_MES)) MES_ACTUAL  FROM(")
				.append(" SELECT CUENTA, STACTA NATCTA, NOMCTA, SUM(SALINI) SALINI, ")
				.append("SUM(" + car_an.substring(0, car_an.length() - 1) + ") CARGOS_MES_ANT, ")
				.append("SUM(" + abo_an.substring(0, abo_an.length() - 1) + ") ABONOS_MES_ANT, ")
				.append("SUM(" + car_al.substring(0, car_al.length() - 1) + ") CARGOS_AL_MES, ")
				.append("SUM(" + abo_al.substring(0, abo_al.length() - 1) + ") ABONOS_AL_MES ")
				.append("FROM CUENTA WHERE IDSECTOR =" + idSector)
				.append(" AND CUENTA BETWEEN '2110' AND '2199' AND SCTA = '' AND NOTCTA = 0 ")
				.append("GROUP BY CUENTA, STACTA, NOMCTA ORDER BY CUENTA")
				.append(")T1 WHERE SALINI <> 0 OR CARGOS_AL_MES <>0 OR ABONOS_AL_MES<>0");

		return query.toString();
	}

	/**
	 * Generate where activo.
	 *
	 * @param mes the mes
	 * @return the string
	 */
	public String generateWhereActivo(Integer mes) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder where = new StringBuilder();
		Integer i = 1;

		while (i <= mes) {
			cargos.append(" OR CUENTA.CARGOS_" + i + " <> 0 ");
			abonos.append(" OR CUENTA.ABONOS_" + i + " <> 0 ");
			i++;
		}

		where.append("AND (CUENTA.SALINI <> 0" + cargos + abonos + ")");
		return where.toString();
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
	public Firmas getFirmas() {
		return firmas;
	}

	/**
	 * Sets the firmas.
	 *
	 * @param firmas the new firmas
	 */
	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
	}

	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	/**
	 * Sets the firmas repository.
	 *
	 * @param firmasRepository the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	/** The stream. */
	InputStream stream = null;

	/** The out. */
	Map<String, Object> out;

	/**
	 * Gets the inputs.
	 *
	 * @return the inputs
	 * @throws ReportValidationException the report validation exception
	 */
	public SqlParameterSource getInputs() throws ReportValidationException {
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("i_query", this.generateQueryPasivo(mes, this.getUserDetails().getIdSector()));
		parametros.addValue("i_id_sector", idSector);
		parametros.addValue("i_mes", Integer.valueOf(mes));
		parametros.addValue("i_where_act", this.generateWhereActivo(mes));
		return parametros;
	}

	/** The Constant charSetISO. */
	private final static String charSetISO = "ISO-8859-1";

	/** The Constant charSetUFT. */
	private final static String charSetUFT = "UTF-8";

	/**
	 * Pre view.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void preView() throws IOException {
		setbPreView(Boolean.TRUE);
		/**
		 * IN i_query VARCHAR(9000) IN i_id_sector INTEGER IN i_mes INTEGER IN
		 * i_where_act VARCHAR(400) OUT o_cod_error INTEGER OUT o_msg_error VARCHAR(300)
		 * OUT o_path_file VARCHAR(4000)
		 */
		try {

			out = this.executePlService.callProcedure(SP_NAME, this.getInputs());
		} catch (ReportValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) == 0) {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, out.get("O_MSG_ERROR").toString());
		} else {
			String enfFile = "/gem/reportes" + "/testCapitulo.txt";
			File source = new File(out.get("O_PATH_FILE").toString());
			File target = new File(enfFile);
			ConvertCharsetUtils.transform(source, charSetUFT, target, charSetISO);

			txtPreview = this.generatePreviewService.generatePreview(enfFile);
			RequestContext.getCurrentInstance().execute(FOCUS_BY_ROWID);
		}
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
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

}
