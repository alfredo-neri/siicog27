package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class RF009116MB.
 */
@Component("rF009116MB")
@Scope("view")
public class RF009116MB extends BaseDirectReport {

	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = "SP_REPORTE_RF009116";
	private static final String CLAVE_REPORT = "RPT_EDO_COMP_PRE_INGR";

	/** The mes. */
	private String mes;

	/** The file txt. */
	private StreamedContent fileTxt;

	/** The firmas repository. */
	@Value("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The tc mes repository. */
	@Value("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The execute pl service. */
	@Value("#{executePlService}")
	protected ExecutePlService executePlService;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
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
	 * Sets the file txt.
	 *
	 * @param fileTxt the new file txt
	 */
	public void setFileTxt(StreamedContent fileTxt) {
		this.fileTxt = fileTxt;
	}

	/**
	 * Gets the tc mes repository.
	 *
	 * @return the tc mes repository
	 */
	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	/**
	 * Sets the tc mes repository.
	 *
	 * @param tcMesRepository the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

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

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RF009116MB ");
		jasperReporteName = "rf009_1_16";
		endFilename = jasperReporteName + ".pdf";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		TrPuestoFirma firma = new TrPuestoFirma();
		Integer idSector = this.getUserDetails().getIdSector();
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Conctb conctb = conctbRepository.findByIdsector(idSector);

		paramsReport.put("mesName", tcMesRepository.findByMes(mes).getDescripcion());
		paramsReport.put("lastDayOfMonth", getLastDayByAnoEmp(Integer.valueOf(mes), conctb.getAnoemp()));
		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("mes", Integer.valueOf(mes));
		paramsReport.put("usuario", getUserDetails().getUsername());
		paramsReport.put("entidadName", conctb.getNomDep());
		paramsReport.put("rfc", conctb.getRfc());
		paramsReport.put("pathImage", conctb.getImagePathLeft());
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		paramsReport.put("pImg1", conctb.getImagePathRigth());

		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		endFilename = getNameFileReport(mes, conctb, reporte);
		paramsReport.put("pSQL", generateQuery(Integer.valueOf(mes), getUserDetails().getIdSector()));

		return paramsReport;
	}

	/** The out. */
	Map<String, Object> out;

	/** The stream. */
	InputStream stream = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getFileTxt()
	 */
	public StreamedContent getFileTxt() {
		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("i_mes", mes);
		parameter.addValue("i_sector", this.getUserDetails().getIdSector());
		parameter.addValue("i_municipio", this.getUserDetails().getMunicipio());

		out = executePlService.callProcedure(PROCEDURE_NAME, parameter);

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(
						new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			fileTxt = StreamedContents.of(stream, "application/txt", out.get("O_NAME_FILE").toString(), null);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
		}
		return fileTxt;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Generate query.
	 *
	 * @param mes    the mes
	 * @param sector the sector
	 * @return the string
	 */
	public String generateQuery(Integer mes, Integer sector) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder ACUMULADO = new StringBuilder();
		StringBuilder XAMPLI = new StringBuilder();

		for (int i = 1; i <= mes; i++) {
			ACUMULADO.append("+ (ABONOS_" + i + "_8150 - CARGOS_" + i + "_8150) ");
			XAMPLI.append("+ (CARGOS_" + i + " - ABONOS_" + i + ") ");
		}

		sSql.append(" SELECT CUENTA ,SCTA ,SSCTA,SSSCTA ,SSSSCTA ,NOMCTA " + " ,(ABONOS_" + mes + "_8150 - CARGOS_"
				+ mes + "_8150) DEL_MES " + " ,(" + ACUMULADO.substring(1) + ") ACUMULADO " + " ,CASE WHEN ("
				+ XAMPLI.substring(1) + " ) < 0 THEN " + "  SALINI + ABS(" + XAMPLI.substring(1) + "  ) ELSE "
				+ "   SALINI - ABS(" + XAMPLI.substring(1) + "   ) END XAMPLI " + " FROM( " + " SELECT  " + " B.CUENTA "
				+ " , A.SCTA " + " , A.SSCTA " + " , A.SSSCTA " + " , A.SSSSCTA  " + " , A.NOMCTA " + " , A.SALINI "
				+ " , A.CARGOS_1 " + " , A.CARGOS_2 " + " , A.CARGOS_3 " + " , A.CARGOS_4 " + " , A.CARGOS_5 "
				+ " , A.CARGOS_6 " + " , A.CARGOS_7 " + " , A.CARGOS_8 " + " , A.CARGOS_9 " + " , A.CARGOS_10 "
				+ " , A.CARGOS_11 " + " , A.CARGOS_12 " + " , A.ABONOS_1 " + " , A.ABONOS_2 " + " , A.ABONOS_3 "
				+ " , A.ABONOS_4 " + " , A.ABONOS_5 " + " , A.ABONOS_6 " + " , A.ABONOS_7 " + " , A.ABONOS_8 "
				+ " , A.ABONOS_9 " + " , A.ABONOS_10 " + " , A.ABONOS_11 " + " , A.ABONOS_12 "
				+ " , B.CARGOS_1 CARGOS_1_8150  " + " , B.CARGOS_2 CARGOS_2_8150  " + " , B.CARGOS_3 CARGOS_3_8150  "
				+ " , B.CARGOS_4 CARGOS_4_8150  " + " , B.CARGOS_5 CARGOS_5_8150  " + " , B.CARGOS_6 CARGOS_6_8150  "
				+ " , B.CARGOS_7 CARGOS_7_8150  " + " , B.CARGOS_8 CARGOS_8_8150  " + " , B.CARGOS_9 CARGOS_9_8150  "
				+ " , B.CARGOS_10 CARGOS_10_8150  " + " , B.CARGOS_11 CARGOS_11_8150  "
				+ " , B.CARGOS_12 CARGOS_12_8150  " + " , B.ABONOS_1 ABONOS_1_8150  " + " , B.ABONOS_2 ABONOS_2_8150  "
				+ " , B.ABONOS_3 ABONOS_3_8150  " + " , B.ABONOS_4 ABONOS_4_8150  " + " , B.ABONOS_5 ABONOS_5_8150  "
				+ " , B.ABONOS_6 ABONOS_6_8150  " + " , B.ABONOS_7 ABONOS_7_8150  " + " , B.ABONOS_8 ABONOS_8_8150  "
				+ " , B.ABONOS_9 ABONOS_9_8150  " + " , B.ABONOS_10 ABONOS_10_8150 " + " , B.ABONOS_11 ABONOS_11_8150 "
				+ " , B.ABONOS_12 ABONOS_12_8150 " + " FROM CUENTA A INNER JOIN CUENTA B  " + " 	ON A.SCTA=B.SCTA  "
				+ "    AND B.CUENTA='8150'  " + "    AND A.SSCTA=B.SSCTA  " + "    AND A.SSSCTA=B.SSSCTA  "
				+ "    AND A.SSSSCTA=B.SSSSCTA  " + "    AND A.IDSECTOR=B.IDSECTOR " + " WHERE B.IDSECTOR= " + sector
				+ " AND  A.CUENTA='8110'  " + " ORDER BY CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA ASC ) ");

		return sSql.toString();
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

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
