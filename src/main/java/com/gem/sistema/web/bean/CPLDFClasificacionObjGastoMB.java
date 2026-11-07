package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilReport.getNameFileReport;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * @author Alfredo
 * @Date 16/03/2022
 * @Changes Se crea reporte
 * @sector Municipal
 */
@ManagedBean(name = "cPLDFClasificacionObjGastoMB")
@ViewScoped
public class CPLDFClasificacionObjGastoMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_CP_EDO_ANA_PRESUP_EGRE_DET_LDF_CLASF_OBJ_GASTO";

	private Integer noDecimales;
	private Integer pesos;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcReportesFirmasRepository}")
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
		jasperReporteName = "CP_LDF_ClasificacionObjGasto";

	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {

		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(conctb, reporte);

		parameters.put("sql", this.generateSQL(idSector));
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

	private String generateSQL(Integer sector) {
		StringBuilder sql = new StringBuilder();
		int mesInicial = 1;
		int mesFinal = 12;

		String auto = "SUM(";
		String ejpa = "SUM(";
		String redu = "SUM(";
		String ejxpa = "SUM(";
		String ampli = "SUM(";

		for (int y = mesInicial; y <= mesFinal; y++) {
			ejpa = ejpa + " PA.EJPA1_" + y + " +";
			redu = redu + " PA.REDU1_" + y + " +";
			ejxpa = ejxpa + " PA.TOEJE1_" + y + " +";
			ampli = ampli + " PA.AMPLI1_" + y + " +";
		}

		for (int y = 1; y <= 12; y++) {
			auto = auto + " PA.AUTO1_" + y + " +";
		}

		auto = auto.substring(0, auto.length() - 2) + " ) APROBADO, ";
		ejpa = ejpa.substring(0, ejpa.length() - 2) + " ) PAGADO ";
		redu = redu.substring(0, redu.length() - 2) + " ) REDUCCIONES, ";
		ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " ) DEVENGADO, ";
		ampli = ampli.substring(0, ampli.length() - 2) + " ) AMPLIACION, ";

		sql.append("SELECT 1 GRUP, T1.CLVGAS, T1.NOMGAS, T1.APROBADO, (T1.AMPLIACION -T1.REDUCCIONES) AMPL_REDU, ")
				.append("(T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) MODIFICADO, T1.DEVENGADO, T1.PAGADO, ")
				.append("(T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) - T1.DEVENGADO SUBEJERCICIO ")
				.append("FROM (SELECT NAT.CLVGAS, NAT.NOMGAS, ").append(auto).append(ampli).append(redu).append(ejxpa)
				.append(ejpa)
				.append("FROM PASO PA INNER JOIN NATGAS NAT ON NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR AND SUBSTR(PA.PARTIDA, 3, 2) = '00' ")
				.append("WHERE  PA.IDSECTOR = ").append(sector)
				// .append(" AND SUBSTR(PA.PARTIDA,4,1)<>'0' AND ")
				.append(" AND SUBSTR(PA.PROGRAMA,13,3)>='101' AND SUBSTR(PA.PROGRAMA,13,3)<='113' OR ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='201' AND SUBSTR(PA.PROGRAMA,13,3)<='202' ")
				.append("GROUP BY NAT.CLVGAS,NAT.NOMGAS ORDER BY NAT.CLVGAS ASC ) T1 UNION ALL ")
				.append("SELECT 2 GRUP, T1.CLVGAS, T1.NOMGAS, T1.APROBADO, (T1.AMPLIACION -T1.REDUCCIONES) AMPL_REDU, ")
				.append("(T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) MODIFICADO, T1.DEVENGADO, T1.PAGADO, ")
				.append("(T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) - T1.DEVENGADO SUBEJERCICIO "
						+ "FROM (SELECT NAT.CLVGAS, NAT.NOMGAS, ")
				.append(auto).append(ampli).append(redu).append(ejxpa).append(ejpa)
				.append("FROM PASO PA INNER JOIN NATGAS NAT ON NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR AND SUBSTR(PA.PARTIDA, 3, 2) = '00' ")
				.append("WHERE  PA.IDSECTOR = ").append(sector)
				// .append(" AND SUBSTR(PA.PARTIDA,4,1)<>'0' AND ")
				.append(" AND SUBSTR(PA.PROGRAMA,13,3)>='203' AND SUBSTR(PA.PROGRAMA,13,3)<='225' OR ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='114' AND SUBSTR(PA.PROGRAMA,13,3)<='116' ")
				.append("GROUP BY NAT.CLVGAS,NAT.NOMGAS ORDER BY NAT.CLVGAS ASC) T1 ORDER BY GRUP, CLVGAS");

		if (pesos != 1) {
			sql.insert(0,
					" SELECT GRUP, CLVGAS, NOMGAS, APROBADO, AMPL_REDU, MODIFICADO, DEVENGADO, PAGADO, SUBEJERCICIO FROM ( ");
			sql.append(" ) T2");
		}

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
