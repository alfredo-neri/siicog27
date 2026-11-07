package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilReport.getNameFileReport;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * The Class ECPEgresos213MB.
 * 
 * @author Alfredo
 * @Timeline <strong>Date:</strong> 28/09/2021 <strong>Changes:</strong> Se
 *           implementa Servicio de firmas dinamicas, Se ajusta disenio del
 *           reporte
 * @Sector Municipal
 */
@ManagedBean(name = "eCPEgresos213MB")
@ViewScoped
public class ECPEgresos213MB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_CP_EDO_COMPARATIVO_PRESUPUESTAL_EGRESOS";

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@PostConstruct
	public void init() {
		jasperReporteName = "ECPEgresos213";
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
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(conctb, reporte);

		parameters.put("imagenPathLeft", conctb.getImagePathLeft());
		parameters.put("imagenPathRigth", StringUtils.EMPTY);
		parameters.put("nombreEntidad", conctb.getNomDep());
		parameters.put("claveEntidad", conctb.getClave());
		parameters.put("pSsql", this.generaQuery(idSector, 12));
		parameters.put("year", conctb.getAnoemp());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

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
	public String generaQuery(Integer idsector, Integer mes) {

		StringBuilder sSql = new StringBuilder();
		StringBuilder auto = new StringBuilder();
		StringBuilder ejpa = new StringBuilder();
		StringBuilder ejxpa = new StringBuilder();
		StringBuilder toeje = new StringBuilder();
		StringBuilder ampli = new StringBuilder();
		StringBuilder redu = new StringBuilder();
		StringBuilder compro = new StringBuilder();

		sSql.append("SELECT * FROM ( ").append("SELECT ").append(" T2.PARTIDA,").append("T2.NOMBRE,")
				.append(" (T2.XA/1000) AUTORIZADO,").append(" ((T2.XF-XG)/1000) AJUSTEPRES,")
				.append(" (T2.XAFG/1000) MODIFICADO,").append(" (T2.XD/1000) EJERCIDO,")
				.append("(T2.XE/1000) ABSOLUTA,").append(" CASE WHEN T2.XAFG <> 0 THEN (((T2.XAFG-T2.XD)/(XAFG))*100)")
				.append(" ELSE 0 ").append(" END PORCENTAJE").append(" FROM (SELECT T1.PARTIDA,").append(" T1.NOMBRE,")
				.append(" T1.A XA,").append(" T1.F XF,").append(" T1.G XG,").append(" (T1.A+T1.F-T1.G)XAFG,")
				.append(" T1.B XB,").append(" T1.C XC,").append(" T1.CPT XCPT,").append(" T1.D XD,")
				.append(" (T1.A+T1.F-T1.G)-T1.D XE ").append(" FROM(SELECT P.PARTIDA PARTIDA,")
				.append("  N.NOMGAS NOMBRE,");

		for (int i = 1; i <= 12; i++) {
			auto.append(" P.AUTO1_" + i + "+");
		}

		for (int i = 1; i <= mes; i++) {
			ejpa.append(" P.EJPA1_" + i + "+");
			ejxpa.append(" P.EJXPA1_" + i + "+");
			toeje.append(" P.TOEJE1_" + i + "+");
			ampli.append(" P.AMPLI1_" + i + "+");
			redu.append(" P.REDU1_" + i + "+");
			compro.append(" P.COMPRO1_" + i + "+");
		}

		sSql.append("SUM(").append(auto.substring(1, auto.length() - 1)).append(") A,").append(" SUM( ")
				.append(ejpa.substring(1, ejpa.length() - 1)).append(" ) B,").append(" SUM( ")
				.append(ejxpa.substring(1, ejxpa.length() - 1)).append(")C,").append(" SUM( ")
				.append(toeje.substring(1, toeje.length() - 1)).append(")D,").append(" SUM( ")
				.append(ampli.substring(1, ampli.length() - 1)).append(" )F,").append(" SUM( ")
				.append(redu.substring(1, redu.length() - 1)).append(" )G,").append(" SUM( ")
				.append(compro.substring(1, compro.length() - 1)).append(" )CPT").append(" FROM PASO P ")
				.append(" JOIN NATGAS N ON N.CLVGAS = P.PARTIDA AND N.IDSECTOR = P.IDSECTOR")
				.append(" WHERE P.IDSECTOR=" + idsector).append(" GROUP BY P.PARTIDA, ").append(" N.NOMGAS")
				.append(" ORDER BY P.PARTIDA)T1)T2").append(" ) T3 ");

		LOGGER.info("QUERY,{}", sSql.toString());
		return sSql.toString();
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

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
