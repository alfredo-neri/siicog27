package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
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
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;;

// TODO: Auto-generated Javadoc
/**
 * The Class RF0010113MB.
 */
@Component("rF0010113MB2")
@Scope("view")
public class RF0010113MB2 extends BaseDirectReport {

	private static final String CLAVE_REPORT = "RPT_EDO_ANALITICO_ACT";
	private static final String NAME_PROCEDURE = "SP_GENERA_TXT_EAA";

	/** The mes. */
	private String mes;

	/** The saldo. */
	private String saldo;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The tc mes repository. */
	@Value("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{tcEntidadAdministrativaRepository}")
	private TcEntidadAdministrativaRepository entidadAdministrativaRepository;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	private StreamedContent psFileTxt;

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

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	/** Servicio para validacion de mes aprturado, polizas, afectacion */

	@Value("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	/** The execute pl service. */
	@Value("#{executePlService}")
	private ExecutePlService executePlService;

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
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public String getSaldo() {
		return saldo;
	}

	/**
	 * Sets the saldo.
	 *
	 * @param saldo the new saldo
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
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

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
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

		TrPuestoFirma firma = new TrPuestoFirma();

		parameters.put("mesName", tcMesRepository.findByMes(mes).getDescripcion());
		parameters.put("lastDay", getLastDayByAnoEmp(Integer.valueOf(mes), conctb.getAnoemp()));
		parameters.put("year", conctb.getAnoemp());
		parameters.put("imagePath", conctb.getImagePathRigth());
		parameters.put("query", this.generateQuery(Integer.valueOf(mes), sector, saldo.equals("S") ? 1 : 0));
		parameters.put("municipio", conctb.getNomDep());
		parameters.put("pId", getUserDetails().getIdSector());
		parameters.put("noEntity","");

		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		parameters.put("tipo", "");
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		endFilename = getNameFileReport(mes, conctb, reporte);


		return parameters;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "RF0010_1_132";

		listMes = tcMesRepository.findAll();
		if (!CollectionUtils.isEmpty(listMes))
			mes = listMes.get(0).getMes();

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
	 * @param mes        the mes
	 * @param idSector   the id sector
	 * @param saldosCero the saldos cero
	 * @return the string
	 */
	public String generateQuery(Integer mes, Integer idSector, Integer saldosCero) {

		StringBuilder sQuery = new StringBuilder();
		StringBuilder sCargosAnt = new StringBuilder();
		StringBuilder sAbonosAnt = new StringBuilder();

		sQuery.append(
				"SELECT CUENTA, NOMCTA, SALDOINICIAL, CARGOS_MES, ABONOS_MES,(SALDOINICIAL + CARGOS_MES - ABONOS_MES) SALDOFINAL,(SALDOINICIAL - (SALDOINICIAL + CARGOS_MES - ABONOS_MES)) FLUJO FROM ( SELECT CUENTA, NOMCTA, CASE WHEN NATCTA IN('A') THEN CASE WHEN CUENTA IN ('1115','1162','1261','1262','1263') THEN ABS(SALINI + CARGOS_ANT - ABONOS_ANT) ELSE ABS(SALINI - CARGOS_ANT + ABONOS_ANT) END ELSE ABS(SALINI + CARGOS_ANT - ABONOS_ANT) END SALDOINICIAL, CARGOS_MES, ABONOS_MES FROM ( SELECT CU.CUENTA, MA.NATCTA, CU.NOMCTA, CU.SALINI,");

		if (mes > 1) {
			for (int i = 1; i < mes; i++) {
				sCargosAnt.append(" CU.CARGOS_" + i + " +");
				sAbonosAnt.append(" CU.ABONOS_" + i + " +");
			}
		} else {
			sCargosAnt.append("  0  ");
			sAbonosAnt.append("  0  ");
		}

		sQuery.append(sCargosAnt.substring(0, sCargosAnt.length() - 1) + "CARGOS_ANT,")
				.append(sAbonosAnt.substring(0, sAbonosAnt.length() - 1) + "ABONOS_ANT,")
				.append(" CU.CARGOS_" + mes + " CARGOS_MES, CU.ABONOS_" + mes + " ABONOS_MES ")
				.append(" FROM CUENTA AS CU, MAYCTA AS MA WHERE CU.IDSECTOR=" + idSector
						+ " AND CU.CUENTA = MA.CUENTA AND CU.SCTA = '' AND CU.SSCTA = '' AND CU.SSSCTA = '' AND CU.SSSSCTA = '' AND CU.CUENTA <= 1300 AND SUBSTR(CU.CUENTA,2,3) <> '000'  AND CU.NOTCTA <> 3 ) T1 )T2");
		if (saldosCero == 0) {
			sQuery.append(
					" WHERE SALDOINICIAL > 0 OR CARGOS_MES > 0 OR ABONOS_MES > 0 OR (SALDOINICIAL + CARGOS_MES - ABONOS_MES) > 0 OR (SALDOINICIAL - (SALDOINICIAL + CARGOS_MES - ABONOS_MES)) >0");
		}
		return sQuery.toString();
	}

	/** Metodo de validacion al descargar el PDF */
	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
			;
		}
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

	public SqlParameterSource parameters() {
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("i_Query", this.generateQuery(Integer.valueOf(mes), this.getUserDetails().getIdSector(),
				saldo.equals("S") ? 1 : 0));
		parametros.addValue("i_Mes", mes);

		return parametros;

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
			streamedContent = StreamedContents.of(stream, "application/txt", out.get("O_FILE_NAME").toString(), null);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSJ_ERROR").toString());
		}

		return streamedContent;
	}

	public void setPsFileTxt(StreamedContent psFileTxt) {
		this.psFileTxt = psFileTxt;
	}

	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
	}

}
