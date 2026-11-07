package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilReport.getNameFileReport;

import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * The Class CPEstadoAnaliticoEgresosPresupuestalIntegradoMB.
 * 
 * @author Alfredo
 * @Timeline <strong>Date:</strong> 30/09/2021 <strong>Changes:</strong> Se
 *           implementa Servicio de firmas dinamicas, Se ajusta disenio del
 *           reporte
 * @Sector Municipal
 */
@Component("cpEstadoAnaliticoEgresosPresupuestalIntegradoMB")
@Scope("view")
public class CPEstadoAnaliticoEgresosPresupuestalIntegradoMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_CP_EDO_ANALITICO_EGRESOS_PRESUPUESTAL_INTEGRADO";

	@Value("#{conctbRepository}")
	private ConctbRepository contbRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@PostConstruct
	private void init() {
		jasperReporteName = "CPEdoAnaEgresosPresIntegrado";
		endFilename = jasperReporteName + ".pdf";

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
		Conctb conctb = contbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(conctb, reporte);

		parameters.put("imagenPathLeft", conctb.getImagePathRigth());
		parameters.put("imagenPathRigth", StringUtils.EMPTY);
		parameters.put("nombreEntidad", conctb.getNomDep());
		parameters.put("claveEntidad", conctb.getClave());
		parameters.put("year", conctb.getAnoemp());
		parameters.put("pSql", this.generaQuery(sector, 12));
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

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
	 * Genera query.
	 *
	 * @param idsector the idsector
	 * @param mes      the mes
	 * @return the string
	 */
	public String generaQuery(int idsector, int mes) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder ayto = new StringBuilder();
		StringBuilder agua = new StringBuilder();
		StringBuilder dif = new StringBuilder();
		StringBuilder deporte = new StringBuilder();
		StringBuilder mavici = new StringBuilder();

		sSql.append("SELECT T1.PARTIDA,").append("T1.NOMGAS,").append(" T1.AYTTO,").append(" T1.AGUA,")
				.append(" T1.DIF,").append(" T1.DEPORTE,").append("T1.MAVICI,")
				.append(" (T1.AYTTO +  T1.AGUA + T1.DIF + T1.DEPORTE + T1.MAVICI) TOTAL")
				.append(" FROM (SELECT I.PARTIDA,").append(" N.NOMGAS,");

		for (int i = 1; i <= mes; i++) {

			ayto.append(" I.AYTTOTOEJE1_" + i + "+");
			agua.append(" I.AGUATOEJE1_" + i + "+");
			dif.append(" I.DIFTOEJE1_" + i + "+");
			deporte.append(" I.DEPORTETOEJE1_" + i + "+");
			mavici.append(" I.MAVICITOEJE1_" + i + "+");

		}

		sSql.append(" SUM(" + ayto.substring(1, ayto.length() - 1)).append(" )AYTTO,")
				.append(" SUM(" + agua.substring(1, agua.length() - 1)).append(" ) AGUA,")
				.append(" SUM(" + dif.substring(1, dif.length() - 1)).append(" ) DIF,")
				.append(" SUM(" + deporte.substring(1, deporte.length() - 1)).append(" ) DEPORTE,")
				.append(" SUM(" + mavici.substring(1, mavici.length() - 1)).append(" )MAVICI")
				.append(" FROM INTEGRADOE I  ")
				.append(" JOIN NATGAS N ON N.CLVGAS = I.PARTIDA AND N.IDSECTOR = I.IDSECTOR")
				.append(" WHERE I.IDSECTOR=" + idsector).append(" GROUP BY I.PARTIDA, N.NOMGAS")
				.append(" ORDER BY I.PARTIDA)T1");

		return sSql.toString();

	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public ConctbRepository getContbRepository() {
		return contbRepository;
	}

	public void setContbRepository(ConctbRepository contbRepository) {
		this.contbRepository = contbRepository;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
