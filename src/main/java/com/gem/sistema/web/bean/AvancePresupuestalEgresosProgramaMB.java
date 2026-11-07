package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcEntidadAdministrativa;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcEntidadAdministrativaRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * @author Alfredo Neri
 * @timeline <strong>Date: </strong>08/12/2021 <strong>Changes: </strong>Se crea
 *           reporte<br>
 * @sector Municipal
 */
@Component("avancePresupuestalEgresosProgramaMB")
@Scope("view")
public class AvancePresupuestalEgresosProgramaMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_AVANCE_PRESUPUESTAL_EGRESOS_GLOBAL_PROGRAMA";
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";

	private Integer mes;
	private List<TcPeriodo> listPeriodos;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@Value("#{tcEntidadAdministrativaRepository}")
	private TcEntidadAdministrativaRepository entidadAdministrativaRepository;

	@PostConstruct
	public void init() {
		jasperReporteName = "AvancePresupuestalEgresosPrograma";
		listPeriodos = periodoRepositoy.findByTipoPeriodoOrderByPeriodo(1);

		if (!CollectionUtils.isEmpty(listPeriodos)) {
			mes = listPeriodos.get(0).getPeriodo();
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcPeriodo periodo = periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mes);
		TcEntidadAdministrativa entidadAdministrativa = entidadAdministrativaRepository
				.findByClave(conctb.getClave().substring(0, 1));
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(mes, conctb, reporte);

		parameters.put("sql", this.generateQuery(sector, mes));
		parameters.put("mes", mes);
		parameters.put("year", conctb.getAnoemp());
		parameters.put("sector", sector);
		parameters.put("lastDay", getLastDayByAnoEmp(mes, conctb.getAnoemp()));
		parameters.put("tipoOrganismo", entidadAdministrativa.getDescripcion());
		parameters.put("nombreEntidad", conctb.getNomDep());
		parameters.put("mesDescripcion", periodo.getDescripcion().toUpperCase());
		parameters.put("imagenPathLeft", conctb.getImagePathLeft());
		parameters.put("imagenPathRigth", conctb.getImagePathRigth());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public void generatePDF() {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
		}
	}

	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());

			PrimeFaces.current().executeScript(DOWNLOAD_XLS);
		}
	}

	public String generateQuery(Integer idsector, Integer mes) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder ejempa = new StringBuilder();
		StringBuilder ejxpa = new StringBuilder();
		StringBuilder toeje = new StringBuilder();
		StringBuilder ampli = new StringBuilder();
		StringBuilder redu = new StringBuilder();
		StringBuilder compro = new StringBuilder();

		sSql.append("SELECT T1.PROGRAMA, T1.NOMPRO, T1.CLVFTE, T1.PARTIDA, ").append("T1.DESCRIPCION,")
				.append("T1.A APROBADO,").append("T1.F AMPLIACION,").append("T1.G REDUCCION,")
				.append("(T1.A+T1.F-T1.G) MODIFICADO,").append("(T1.CPT)COMPROMETIDO,").append("T1.C DEVENGADO,")
				.append("T1.B PAGADO,").append("T1.D EJERCIDO,").append("((T1.A +T1.F-T1.G)-D) POR_EJERCER")
				.append(" FROM (SELECT P.PROGRAMA, X.NOMPRO, F.CLVFTE, P.PARTIDA PARTIDA,")
				.append("N.NOMGAS DESCRIPCION,")
				.append("SUM(P.AUTO1_1+P.AUTO1_2+P.AUTO1_3+P.AUTO1_4+P.AUTO1_5+P.AUTO1_6+P.AUTO1_7+P.AUTO1_8+P.AUTO1_9+P.AUTO1_10+P.AUTO1_11+P.AUTO1_12)A,");

		for (int i = 1; i <= mes; i++) {

			ejempa.append(" P.EJPA1_" + i + "+");
			ejxpa.append(" P.EJXPA1_" + i + "+");
			toeje.append(" P.TOEJE1_" + i + "+");
			ampli.append(" P.AMPLI1_" + i + "+");
			redu.append(" P.REDU1_" + i + "+");
			compro.append(" P.COMPRO1_" + i + "+");

		}

		sSql.append(" SUM( ").append(ejempa.substring(1, ejempa.length() - 1)).append(")B,").append(" SUM( ")
				.append(ejxpa.substring(1, ejxpa.length() - 1)).append(")C,").append(" SUM( ")
				.append(toeje.substring(1, toeje.length() - 1)).append(")D,").append(" SUM( ")
				.append(ampli.substring(1, ampli.length() - 1)).append(")F,").append(" SUM( ")
				.append(redu.substring(1, redu.length() - 1)).append(")G,").append(" SUM( ")
				.append(compro.substring(1, compro.length() - 1)).append(")CPT ");

		sSql.append(" FROM").append(" PASO P JOIN NATGAS N ").append(" ON N.CLVGAS= P.PARTIDA ")
				.append(" AND N.IDSECTOR=P.IDSECTOR ")
				.append(" INNER JOIN XCATPRO X ON X.CLVFUN||X.CLVFIN = P.PROGRAMA AND X.SECTORID = P.IDSECTOR ")
				.append(" INNER JOIN FUENTEF F ON X.CLVFIN = F.LIGA AND F.IDSECTOR = P.IDSECTOR ").append(" WHERE ")
				.append(" P.IDSECTOR = " + idsector)
				.append(" GROUP BY P.PROGRAMA, X.NOMPRO, F.CLVFTE, P.PARTIDA, N.NOMGAS )")
				.append(" T1 ORDER BY T1.PROGRAMA, T1.PARTIDA");

		System.out.println(sSql.toString());
		return sSql.toString();
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public List<TcPeriodo> getListPeriodos() {
		return listPeriodos;
	}

	public void setListPeriodos(List<TcPeriodo> listPeriodos) {
		this.listPeriodos = listPeriodos;
	}

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

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

	public TcEntidadAdministrativaRepository getEntidadAdministrativaRepository() {
		return entidadAdministrativaRepository;
	}

	public void setEntidadAdministrativaRepository(TcEntidadAdministrativaRepository entidadAdministrativaRepository) {
		this.entidadAdministrativaRepository = entidadAdministrativaRepository;
	}

}
