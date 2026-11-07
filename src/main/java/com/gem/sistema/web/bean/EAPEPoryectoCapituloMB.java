package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.repository.load.PasoRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * @author José Luis
 *
 */
@Component("eAPEPoryectoCapituloMB")
@Scope("view")
public class EAPEPoryectoCapituloMB extends BaseDirectReport {
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	private static final String CLAVE_REPORT = "RPT_AVAN_PRE_EGR_PROYECTO_CAP";
	private static final String TODOS = "TODOS";

	private String mes;
	private List<TcMes> listTcMes;
	private List<String> proyectos = new ArrayList<>();
	private String proyecto = TODOS;

	@Value("#{tcMesRepository}")
	private TcMesRepository tcmesRepository;

	@Value("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;
	
	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;
	
	@Value("#{pasoRepository}")
	private PasoRepository pasoRepository;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@PostConstruct
	public void init() {
		proyectos.add(TODOS);
		proyectos.addAll(pasoRepository.getProyectoByIdSector(this.getUserDetails().getIdSector()));

		jasperReporteName = "EAPEPoryectoCapitulo";
		listTcMes = tcmesRepository.findAll();
		if (!CollectionUtils.isEmpty(listTcMes)) {
			mes = listTcMes.get(0).getMes();
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(mes, conctb, reporte);
		
		parameters.put("sector", sector);
		parameters.put("mes", Integer.valueOf(mes));
		parameters.put("imageLeft", conctb.getImagePathLeft());
		parameters.put("imageRigth", conctb.getImagePathRigth());
		parameters.put("query", this.generateQuery1(sector, Integer.valueOf(mes)));
		parameters.put("year", conctb.getAnoemp());
		parameters.put("lastDay", getLastDayByAnoEmp(Integer.valueOf(mes), conctb.getAnoemp()));
		parameters.put("mesName", tcmesRepository.findByMes(mes).getDescripcion());
		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));
		System.out.println(this.generateQuery1(sector, Integer.valueOf(mes)));
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

	public String generateQuery1(Integer idsector, Integer mes) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder ejempa = new StringBuilder();
		StringBuilder ejxpa = new StringBuilder();
		StringBuilder toeje = new StringBuilder();
		StringBuilder ampli = new StringBuilder();
		StringBuilder redu = new StringBuilder();
		StringBuilder compro = new StringBuilder();
		sSql.append("SELECT T1.TIPO, T1.DESC_TIPO, T1.PARTIDA, T1.DESCRIPCION, T1.A APROBADO, ")
				.append(" T1.F AMPLIACION, T1.G REDUCCION, (T1.A+T1.F-T1.G) MODIFICADO, ")
				.append(" (T1.CPT)COMPROMETIDO, T1.C DEVENGADO, T1.B PAGADO, ")
				.append(" T1.D EJERCIDO, ((T1.A +T1.F-T1.G)-D) POR_EJERCER FROM ( ").append(" SELECT ")
				.append("SUBSTR(P.PROGRAMA,1,12 ) ").append(" TIPO,").append("M.CAMPO6 DESC_TIPO,")
				.append(" P.PARTIDA PARTIDA,  N.NOMGAS DESCRIPCION, ")
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

		sSql.append(" FROM PASO P JOIN NATGAS N ON N.CLVGAS= P.PARTIDA AND N.IDSECTOR=P.IDSECTOR  ")
				.append(" JOIN MUNINEP M ON SUBSTR(P.PROGRAMA,1,12)=M.CAMPO7 AND M.IDSECTOR=P.IDSECTOR ")
				.append("WHERE  P.IDSECTOR = " + idsector)
				.append(proyecto.equals(TODOS) ? "" : "	AND SUBSTR(P.PROGRAMA,1,12 )='" + proyecto + "'")
				.append(" AND SUBSTR(P.PARTIDA,2,1) IN ('0') GROUP BY ").append("SUBSTR(P.PROGRAMA,1,12 ) ,M.CAMPO6 ,")
				.append(" P.PARTIDA,N.NOMGAS  ORDER BY 1 ) T1 WHERE A+B+C+D+F+G+CPT<>0 ");
		return sSql.toString();
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public List<TcMes> getListTcMes() {
		return listTcMes;
	}

	public void setListTcMes(List<TcMes> listTcMes) {
		this.listTcMes = listTcMes;
	}

	public TcMesRepository getTcmesRepository() {
		return tcmesRepository;
	}

	public void setTcmesRepository(TcMesRepository tcmesRepository) {
		this.tcmesRepository = tcmesRepository;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public List<String> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<String> proyectos) {
		this.proyectos = proyectos;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public PasoRepository getPasoRepository() {
		return pasoRepository;
	}

	public void setPasoRepository(PasoRepository pasoRepository) {
		this.pasoRepository = pasoRepository;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
