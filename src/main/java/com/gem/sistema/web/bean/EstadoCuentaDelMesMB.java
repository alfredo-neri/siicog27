package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilReport.getNameFileReport;

import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@Component("estadoCuentaDelMesMB")
@Scope("view")
public class EstadoCuentaDelMesMB extends BaseDirectReport {

	private static final String OUTPUT_REPORT_NAME = "EstadoCuentaDelMes";
	private static final String CLAVE_REPORT = "RPT_ESTADO_CUENTA";

	private List<TcPeriodo> listMeses;
	private Integer mes;
	private String cuenta;
	private String scta;
	private String sscta;
	private String ssscta;
	private String sssscta;
	private String orderBy;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

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

	@PostConstruct
	public void init() {

		jasperReporteName = "edoCuentaDelMes";
		endFilename = jasperReporteName + ".pdf";
		listMeses = periodoRepositoy.findByTipoPeriodo(1);

		if (!listMeses.isEmpty()) {
			mes = listMeses.get(0).getPeriodo();
		}
		cuenta = StringUtils.EMPTY;
		scta = StringUtils.EMPTY;
		sscta = StringUtils.EMPTY;
		ssscta = StringUtils.EMPTY;
		sssscta = StringUtils.EMPTY;
		orderBy = "FECPOL";
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Integer idSector = this.getUserDetails().getIdSector();
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcPeriodo periodo = periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mes);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(OUTPUT_REPORT_NAME, mes, conctb, reporte);

		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.put("imagenPathLeft", conctb.getImagePathLeft());
		parameters.put("imagenPathRigth", conctb.getImagePathRigth());
		parameters.put("mesName", periodo.getDescripcion());
		parameters.put("idSector", idSector);
		parameters.put("mes", periodo.getPeriodo());
		parameters.put("year", conctb.getAnoemp());
		parameters.put("sql", this.generateSQL(conctb.getAnoemp(), idSector));
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public void leftPad() {
		String value = scta;
		if (StringUtils.isNotEmpty(value)) {
			scta = StringUtils.leftPad(value, 15, StringUtils.EMPTY + ZERO);
		}

		value = sscta;
		if (StringUtils.isNotEmpty(value)) {
			sscta = StringUtils.leftPad(value, 18, StringUtils.EMPTY + ZERO);
		}

		value = ssscta;
		if (StringUtils.isNotEmpty(value)) {
			ssscta = StringUtils.leftPad(value, 4, StringUtils.EMPTY + ZERO);
		}

		value = sssscta;
		if (StringUtils.isNotEmpty(value)) {
			sssscta = StringUtils.leftPad(value, 4, StringUtils.EMPTY + ZERO);
		}
	}

	private String generateSQL(Integer year, Integer sector) {
		StringBuilder sql = new StringBuilder();
		StringBuilder whereCuenta = new StringBuilder();

		whereCuenta.append(" C.CUENTA = '").append(cuenta).append("' ");

		if (StringUtils.isNotEmpty(scta))
			whereCuenta.append(" AND C.SCTA = '").append(scta).append("' ");

		if (StringUtils.isNotEmpty(sscta))
			whereCuenta.append(" AND C.SSCTA = '").append(sscta).append("' ");

		if (StringUtils.isNotEmpty(ssscta))
			whereCuenta.append(" AND C.SSSCTA = '").append(ssscta).append("' ");

		if (StringUtils.isNotEmpty(sssscta))
			whereCuenta.append(" AND C.SSSSCTA = '").append(sssscta).append("' ");

		sql.append(
				"SELECT * FROM (SELECT C.CUENTA, C.SCTA, C.SSCTA, C.SSSCTA, C.SSSSCTA, C.NOMCTA, '*SALDO_INICIAL*' CONCEPTO, C.STACTA, '")
				.append(year).append("-").append(mes)
				.append("-01' FECPOL, '' DN, 1 RENPOL, 1 CONPOL, 'E' TIPPOL, '1' REFPOL, ").append(mes)
				.append(" MESPOL, '' USER, AVG(SALINI) SALINI, NVL(SUM(P.CANPOL),0) CARGOS, NVL(SUM(P.CANPOLH),0) ABONOS ")
				.append(" FROM CUENTA C ")
				.append(" LEFT JOIN POLIDE P ON P.CUENTA = C.CUENTA AND P.SCTA = C.SCTA AND P.SSCTA=C.SSCTA ")
				.append(" AND P.SSSCTA=C.SSSCTA AND P.SSSSCTA=C.SSSSCTA AND P.IDSECTOR= C.IDSECTOR AND P.MESPOL < ")
				.append(mes).append(" WHERE UPPER(C.NIVCTA) = 'S' AND ").append(whereCuenta)
				.append(" AND C.IDSECTOR = ").append(sector)
				.append(" GROUP BY C.CUENTA, C.SCTA, C.SSCTA, C.SSSCTA, C.SSSSCTA, C.NOMCTA, C.STACTA, 7 ")
				.append(" UNION ALL")
				.append(" SELECT C.CUENTA, C.SCTA, C.SSCTA, C.SSSCTA, C.SSSSCTA, C.NOMCTA, P.CONCEP, C.STACTA, E.FECPOL, ")
				.append("P.DN, P.RENPOL, P.CONPOL, P.TIPPOL, P.REFPOL REFPOL, P.MESPOL, P.USERID, 0.00 SALINI, NVL(P.CANPOL,0) CARGOS, ")
				.append(" NVL(P.CANPOLH,0) ABONOS  FROM CUENTA C ")
				.append(" INNER JOIN POLIDE P ON P.CUENTA = C.CUENTA AND P.SCTA = C.SCTA AND P.SSCTA=C.SSCTA AND P.SSSCTA=C.SSSCTA ")
				.append(" AND P.SSSSCTA=C.SSSSCTA AND P.IDSECTOR= C.IDSECTOR AND P.MESPOL = ").append(mes)
				.append(" INNER JOIN POLIEN E ON E.ANOPOL = P.ANOPOL AND E.TIPPOL = P.TIPPOL AND E.MESPOL = P.MESPOL AND E.CONPOL = P.CONPOL AND E.IDSECTOR = P.IDSECTOR ")
				.append(" WHERE UPPER(C.NIVCTA) = 'S' AND ").append(whereCuenta).append(" AND C.IDSECTOR = ")
				.append(sector).append(" ) T1 ORDER BY CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA, ").append(orderBy)
				.append(", DN");
		return sql.toString();
	}

	public List<TcPeriodo> getListMeses() {
		return listMeses;
	}

	public void setListMeses(List<TcPeriodo> listMeses) {
		this.listMeses = listMeses;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getScta() {
		return scta;
	}

	public void setScta(String scta) {
		this.scta = scta;
	}

	public String getSscta() {
		return sscta;
	}

	public void setSscta(String sscta) {
		this.sscta = sscta;
	}

	public String getSsscta() {
		return ssscta;
	}

	public void setSsscta(String ssscta) {
		this.ssscta = ssscta;
	}

	public String getSssscta() {
		return sssscta;
	}

	public void setSssscta(String sssscta) {
		this.sssscta = sssscta;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
