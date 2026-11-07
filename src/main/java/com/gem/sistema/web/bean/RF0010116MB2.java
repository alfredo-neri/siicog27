package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDay;

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

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcEntidadAdministrativa;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcEntidadAdministrativaRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class RF0010116MB.
 */
@ManagedBean(name = "rF0010116MB2")
@ViewScoped
public class RF0010116MB2 extends BaseDirectReport {

	private static final String CLAVE_REPORT = "RPT_EDO_ANA_DEU_OTR_PAS";
	private static final String NAME_PROCEDURE = "SP_GENERA_TXT_EADOP";

	/** The mes. */
	private String mes;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcEntidadAdministrativaRepository}")
	private TcEntidadAdministrativaRepository entidadAdministrativaRepository;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;
	/** The execute pl service. */
	@ManagedProperty("#{executePlService}")
	private ExecutePlService executePlService;

	private StreamedContent psFileTxt;

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
	 * Gets the list mes.
	 *
	 * @return the list mes
	 */
	public List<TcMes> getListMes() {
		return listMes;
	}

	/**
	 * Sets the list mes.
	 *
	 * @param listMes the new list mes
	 */
	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
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

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		listMes = tcMesRepository.findAll();
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}
		jasperReporteName = "RF0010116_EdoA2";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters;
		TcMes descripMes = tcMesRepository.findByMes(mes);
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);

		parameters = new HashMap<String, Object>();
		parameters.put("pImg",conctb.getImagePathRigth());
		parameters.put("pDescripMun", conctb.getNomDep());
		parameters.put("pLastDay", getLastDay(Integer.valueOf(mes)));
		parameters.put("pDescripMes", descripMes.getDescripcion());
		parameters.put("pId", sector);
		parameters.put("pAnio", conctb.getAnoemp());

		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		parameters.put("noEntity", "");
		parameters.put("tipo","");
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		endFilename = getNameFileReport(mes, conctb, reporte);

//		parameters.put("pSsql2", this.generateQuery(this.getUserDetails().getIdSector(), Integer.valueOf(mes)));
		parameters.put("pSql", this.generatePasivo(sector, Integer.valueOf(mes)));
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

	/**
	 * Generate query.
	 *
	 * @param idsector the idsector
	 * @param mes      the mes
	 * @return the string
	 */
	public String generateQuery(Integer idsector, Integer mes) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder debe1 = new StringBuilder();
		StringBuilder haber1 = new StringBuilder();

		sSql.append("SELECT ").append("T2.CUENTA,").append("T2.NAT,").append("T2.NOMBRE,").append("T2.SALDOINI,")
				.append("T2.SIHABER,").append("CASE WHEN T2.NAT <>'D'").append(" THEN T2.SIHABER-T2.DEBE2+T2.HABER2")
				.append(" ELSE 0.00 ").append(" END SALDOFINPER ").append("FROM ").append(" (SELECT ")
				.append("T1.CUENTA,").append(" T1.NAT NAT,").append("T1.NOMBRE NOMBRE,").append("T1.SALDOINI SALDOINI,")
				.append(" CASE WHEN T1.NAT <>'D'").append(" THEN  SALDOINI-T1.DEBE1+T1.HABER1").append(" ELSE 0.00 ")
				.append(" END SIHABER,").append("T1.DEBE2,").append("T1.HABER2").append(" FROM ").append("(SELECT ")
				.append(" M.NATCTA NAT,").append("C.NOMCTA NOMBRE,").append("C.CUENTA CUENTA,").append("C.SCTA  SCTA,")
				.append(" C.SSCTA SSCTA,").append("C.SSSCTA SSSCTA,").append("C.SSSSCTA SSSSCTA,")
				.append(" DECODE (C.CUENTA ,'1115',-C.SALINI,C.SALINI,C.CUENTA ,'1161',-C.SALINI, C.SALINI) SALDOINI,")
				.append(" M.NOTCTA,");

		if (mes == 1) {

			sSql.append("SUM (0.00)DEBE1,").append("SUM (0.00)HABER1,");
		} else {

			for (int i = 1; i < mes; i++) {
				debe1.append(" C.CARGOS_" + i + "+");
				haber1.append(" C.ABONOS_" + i + "+");
			}

			sSql.append("SUM(").append(debe1.substring(1, debe1.length() - 1)).append(")DEBE1,").append("SUM( ")
					.append(haber1.substring(1, haber1.length() - 1)).append(")HABER1,");
		}

		sSql.append("C.CARGOS_" + mes).append(" DEBE2,").append("C.ABONOS_" + mes).append(" HABER2")
				.append(" FROM MAYCTA M").append(" JOIN CUENTA C ON C.CUENTA=M.CUENTA")
				.append(" WHERE C.IDSECTOR=" + idsector).append(" AND C.CUENTA >='1001'")
				.append(" AND C.CUENTA <='6039'").append(" AND C.SCTA=''").append("AND C.NOTCTA=0 ")
				.append(" GROUP BY M.NATCTA,").append("C.NOMCTA,").append("C.CUENTA,").append("C.SCTA,")
				.append("C.SSCTA,").append("C.SSSCTA,").append("C.SSSSCTA,").append("C.SALINI,").append("M.NOTCTA,")
				.append("C.CARGOS_" + mes + ",").append(" C.ABONOS_" + mes).append(" ORDER BY C.CUENTA,")
				.append(" C.SCTA,").append("C.SSCTA,").append(" C.SSSCTA,").append("C.SSSSCTA)T1)T2");

		System.out.println(sSql.toString());
		return sSql.toString();
	}

	/**
	 * Generate pasivo.
	 *
	 * @param idsector the idsector
	 * @param mes      the mes
	 * @return the string
	 */
	public String generatePasivo(Integer idsector, Integer mes) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();

		if (mes > 1) {
			for (int i = 1; i < mes; i++) {
				cargos.append(" + C.CARGOS_" + i);
				abonos.append(" + C.ABONOS_" + i);
			}
		} else {
			cargos.append("  0 ");
			abonos.append("  0 ");
		}

		sSql.append("SELECT CUENTA, NOMCTA, ").append(
				"DECODE(STACTA,'A',(SALINI-CARGOS_ANT+ABONOS_ANT), 'D',(SALINI+CARGOS_ANT-ABONOS_ANT)) SALDO_INICIAL, ")
				.append("DECODE(STACTA,'A',(SALINI-CARGOS+ABONOS), 'D',(SALINI+CARGOS-ABONOS)) SALDO_FINAL")
				.append(" FROM (").append(" SELECT C.CUENTA,  C.STACTA, C.NOMCTA, C.SALINI, ")
				.append(cargos.substring(2)).append(" CARGOS_ANT, ").append(abonos.substring(2)).append(" ABONOS_ANT, ")
				.append(cargos.substring(2)).append(" + C.CARGOS_").append(mes).append(" CARGOS, ")
				.append(abonos.substring(2)).append(" + C.ABONOS_").append(mes).append(" ABONOS ")
				.append(" FROM CUENTA C ").append("	WHERE	C.CUENTA BETWEEN '2000' AND '3000' ")
				.append(" AND C.SCTA = '' ").append(" AND C.NOTCTA = 0 ").append(" AND C.IDSECTOR = ").append(idsector)
				.append(" ) T1");

		System.out.println(sSql.toString());
		return sSql.toString();
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

	public TcEntidadAdministrativaRepository getEntidadAdministrativaRepository() {
		return entidadAdministrativaRepository;
	}

	public void setEntidadAdministrativaRepository(TcEntidadAdministrativaRepository entidadAdministrativaRepository) {
		this.entidadAdministrativaRepository = entidadAdministrativaRepository;
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

	/** The stream. */
	InputStream stream = null;

	/** The out. */
	Map<String, Object> out;
	StreamedContent streamedContent = null;

	public StreamedContent getPsFileTxt() {

		out = new HashMap<String, Object>();
		out = executePlService.callProcedure(NAME_PROCEDURE, parameters());

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(new File(out.get("O_FULL_PATH").toString()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			streamedContent = new DefaultStreamedContent(stream, "application/txt", out.get("O_FILE_NAME").toString());
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSJ_ERROR").toString());
		}

		return streamedContent;
	}

	public SqlParameterSource parameters() {
		Integer idSector = this.getUserDetails().getIdSector();
		MapSqlParameterSource parametros = new MapSqlParameterSource();

		parametros.addValue("i_Query_Pasivo", this.generatePasivo(idSector, Integer.valueOf(mes)));
		parametros.addValue("i_Mes", mes);

		return parametros;

	}

	public void setPsFileTxt(StreamedContent psFileTxt) {
		this.psFileTxt = psFileTxt;
	}

}
