package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilReport.getNameFileReport;

import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * @author Alfredo
 * @Date 17/03/2022
 * @Changes Se crea reporte
 * @sector Municipal
 */
@Component("cPGastoCategoriaProgramaticaMB")
@Scope("view")
public class CPGastoCategoriaProgramaticaMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_CP_GASTO_CATEGORIA_PROGRAMATICA";

	private Integer noDecimales;
	private Integer pesos;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

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

	@PostConstruct
	public void init() {
		jasperReporteName = "CP_GastoporCategoriaProgramatica";

	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {

		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(conctb, reporte);

		parameters.put("query", this.generateSQL(idSector));
		parameters.put("year", conctb.getAnoemp());
		parameters.put("pesos", pesos);
		parameters.put("decimalFormat", "%,." + noDecimales + "f");
		parameters.put("nombreEntidad", conctb.getNomDep());
		parameters.put("imagenPathLeft", conctb.getImagePathRigth());

		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateSQL(Integer idSector) {
		StringBuilder sql = new StringBuilder();

		String auto = "SUM(";
		String ejpa = "SUM(";
		String redu = "SUM(";
		String ejxpa = "SUM(";
		String ampli = "SUM(";
		String ejercido = "SUM(";
		String comprometido = "SUM(";

		for (int y = 1; y <= 12; y++) {
			auto = auto + " P.AUTO1_" + y + " +";
			ejpa = ejpa + " P.EJPA1_" + y + " +";
			redu = redu + " P.REDU1_" + y + " +";
			ejxpa = ejxpa + " P.TOEJE1_" + y + " +";
			ampli = ampli + " P.AMPLI1_" + y + " +";
			ejercido = ejercido + " P.TOEJE1_" + y + " +";
			comprometido = comprometido + " P.COMPRO1_" + y + " +";
		}

		auto = auto.substring(0, auto.length() - 2) + " ) APROBADO, ";
		ejpa = ejpa.substring(0, ejpa.length() - 2) + " ) PAGADO, ";
		redu = redu.substring(0, redu.length() - 2) + " ) REDUCCIONES, ";
		ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " ) DEVENGADO, ";
		ampli = ampli.substring(0, ampli.length() - 2) + " ) AMPLIACIONES, ";
		ejercido = ejercido.substring(0, ejercido.length() - 2) + " ) EJERCIDO ";
		comprometido = comprometido.substring(0, comprometido.length() - 2) + " ) COMPROMETIDO, ";

		sql.append("SELECT RES.CONCEPTO, RES.APROBADO, (RES.AMPLIACIONES-RES.REDUCCIONES) AMP_RED, ")
				.append("(RES.APROBADO+RES.AMPLIACIONES-RES.REDUCCIONES) MODIFICADO , RES.DEVENGADO, ")
				.append("RES.PAGADO, (RES.APROBADO+RES.AMPLIACIONES-RES.REDUCCIONES-RES.DEVENGADO) SUB_E, RES.COMPROMETIDO, RES.EJERCIDO ")
				.append("FROM (SELECT SUBSTR(P.PROGRAMA,1,8)  ||'       '||  M.CAMPO6 CONCEPTO, ").append(auto)
				.append(redu).append(ampli).append(ejxpa).append(ejpa).append(comprometido).append(ejercido)
				.append("FROM PASO P, MUNINEP M WHERE SUBSTR(P.PROGRAMA,1,8)=M.CAMPO7 AND ")
				.append("SUBSTR(P.PARTIDA,4,1)<>'0' AND ").append("P.IDSECTOR=M.IDSECTOR AND P.IDSECTOR = ")
				.append(idSector).append(" GROUP BY SUBSTR(P.PROGRAMA,1,8), M.CAMPO6 ORDER BY 1,2 )RES");

		System.out.println(sql.toString());
		return sql.toString();
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

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
