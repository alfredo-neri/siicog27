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
 * The Class EstadoAnaliticodeIngresosCPMB.
 * 
 * @author Alfredo
 * @Timeline <strong>Date:</strong> 21/09/2021 <strong>Changes:</strong> Se
 *           implementa servivio de firmas dinamico<br>
 *           <strong>Date:</strong> 17/03/2022 <strong>Changes:</strong> se
 *           agrega combo de Nivel para generar el reporte global o a
 *           detalle<br>
 * @Sector Municipal
 */
@ManagedBean(name = "EstadoAnaliticodeIngresosCPMB")
@ViewScoped
public class EstadoAnaliticodeIngresosCPMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_CP_ESTADO_ANALITICO_INGRESOS";

	private Integer noDecimales;
	private Integer pesos;
	private Integer nivel;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		noDecimales = 2;
		pesos = 1;
		nivel = 5;

		jasperReporteName = "EstadoAnaliticodeIngresos";

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
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(conctb, reporte);

		parameters.put("decimalFormat", "%,." + noDecimales + "f");
		parameters.put("imagenPathLeft", conctb.getImagePathRigth());
		parameters.put("imagenPathRigth", StringUtils.EMPTY);
		parameters.put("nombreEntidad", conctb.getNomDep());
		parameters.put("claveEntidad", conctb.getClave());
		parameters.put("year", conctb.getAnoemp());
		parameters.put("pesos", pesos);
		parameters.put("pSsql", this.generaQuery(sector));
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
	 * Genera activo.
	 *
	 * @param idsector the idsector
	 * @param mes      the mes
	 * @return the string
	 */
	public String generaQuery(Integer idsector) {

		StringBuilder sSql1 = new StringBuilder();
		StringBuilder sqlMiles = new StringBuilder();

		String nivelWhere = StringUtils.EMPTY;
		switch (nivel) {
		case 1:
			nivelWhere = " AND C.SCTA='' ";
			break;
		case 2:
			nivelWhere = " AND C.SSCTA='' ";
			break;
		case 3:
			nivelWhere = " AND C.SSSCTA='' ";
			break;
		case 4:
			nivelWhere = " AND C.SSSSCTA='' ";
			break;

		default:
			nivelWhere = StringUtils.EMPTY;
			break;
		}

		sSql1.append(
				"SELECT DECODE(SSCTA,'',1,0) SSCTAC, DECODE(T2.GRUPO,'',0,T2.GRUPO) GRUPO , T2.CUENTA, T2.SCTA ,  T2.SSCTA , T2.SSSCTA , T2.SSSSCTA , T2.NOMBRE,  ")
				.append("	T2.ESTIMADO , ").append("	T2.TOT AMPLIACIONES,").append("	T2.MODIFICADO, ")
				.append("	T2.DEVENGADO , ").append("	T2.RECAUDADO , ")
				.append("	DECODE(T2.MODIFICADO, 0.0000, 0.0000, ((T2.RECAUDADO / T2.MODIFICADO) * 100.0) ) AVANCE,")
				.append("	(T2.RECAUDADO - T2.MODIFICADO)  INGRESO ")
				.append("FROM  (  SELECT  T1.GRUPO, '8110' CUENTA, \r\n"
						+ "	T1.SCTA , T1.SSCTA , T1.SSSCTA , T1.SSSSCTA , \r\n"
						+ "	MAX(T1.NOMBRE) NOMBRE, SUM(T1.ESTIMADO) ESTIMADO, \r\n"
						+ "	SUM(DECODE(T1.STACTA, 'D',(T1.ESTIMADO + T1.CARGOS8110 - T1.ABONOS8110 ), (T1.ESTIMADO + T1.ABONOS8110 - T1.CARGOS8110 ))) MODIFICADO,\r\n"
						+ "	SUM(DECODE(T1.STACTA, 'D',( T1.CARGOS8110 - T1.ABONOS8110 ), ( T1.ABONOS8110 - T1.CARGOS8110 )))  TOT, \r\n"
						+ "	SUM(DECODE(T1.STACTA, 'D',( T1.CARGOS8140 - T1.ABONOS8140 ), ( T1.ABONOS8140 - T1.CARGOS8140 )))  DEVENGADO,\r\n"
						+ "	SUM(DECODE(T1.STACTA, 'D',( T1.CARGOS8150 - T1.ABONOS8150 ), ( T1.ABONOS8150 - T1.CARGOS8150 )))  RECAUDADO\r\n"
						+ "FROM	(\r\n" + "		SELECT\r\n" + "			SUBSTR(C.SCTA,9,1)||'0' GRUPO, \r\n"
						+ "			SUBSTR(C.SCTA,7,4) SCTA,\r\n" + "			SUBSTR(C.SSCTA,13,3) SSCTA,\r\n"
						+ "			SUBSTR(C.SSSCTA,2,3) SSSCTA , C.SSSSCTA, STACTA,\r\n"
						+ "			DECODE(C.CUENTA,'8110',C.NOMCTA,'') NOMBRE,\r\n"
						+ "			SUM(DECODE(C.CUENTA,'8110', C.SALINI,0.00)) ESTIMADO,\r\n"
						+ "			SUM(DECODE(C.CUENTA,'8110',(C.CARGOS_1+C.CARGOS_2+C.CARGOS_3+C.CARGOS_4+C.CARGOS_5+C.CARGOS_6+C.CARGOS_7+C.CARGOS_8+C.CARGOS_9+C.CARGOS_10+C.CARGOS_11+C.CARGOS_12),0.00)) CARGOS8110,\r\n"
						+ "			SUM(DECODE(C.CUENTA,'8110',(C.ABONOS_1+C.ABONOS_2+C.ABONOS_3+C.ABONOS_4+C.ABONOS_5+C.ABONOS_6+C.ABONOS_7+C.ABONOS_8+C.ABONOS_9+C.ABONOS_10+C.ABONOS_11+C.ABONOS_12),0.00)) ABONOS8110,\r\n"
						+ "			SUM(DECODE(C.CUENTA,'8140',(C.CARGOS_1+C.CARGOS_2+C.CARGOS_3+C.CARGOS_4+C.CARGOS_5+C.CARGOS_6+C.CARGOS_7+C.CARGOS_8+C.CARGOS_9+C.CARGOS_10+C.CARGOS_11+C.CARGOS_12),0.00)) CARGOS8140,\r\n"
						+ "			SUM(DECODE(C.CUENTA,'8140',(C.ABONOS_1+C.ABONOS_2+C.ABONOS_3+C.ABONOS_4+C.ABONOS_5+C.ABONOS_6+C.ABONOS_7+C.ABONOS_8+C.ABONOS_9+C.ABONOS_10+C.ABONOS_11+C.ABONOS_12),0.00)) ABONOS8140,\r\n"
						+ "			SUM(DECODE(C.CUENTA,'8150',(C.CARGOS_1+C.CARGOS_2+C.CARGOS_3+C.CARGOS_4+C.CARGOS_5+C.CARGOS_6+C.CARGOS_7+C.CARGOS_8+C.CARGOS_9+C.CARGOS_10+C.CARGOS_11+C.CARGOS_12),0.00)) CARGOS8150,\r\n"
						+ "			SUM(DECODE(C.CUENTA,'8150',(C.ABONOS_1+C.ABONOS_2+C.ABONOS_3+C.ABONOS_4+C.ABONOS_5+C.ABONOS_6+C.ABONOS_7+C.ABONOS_8+C.ABONOS_9+C.ABONOS_10+C.ABONOS_11+C.ABONOS_12),0.00)) ABONOS8150\r\n"
						+ "		FROM\r\n" + "			CUENTA C\r\n" + "		WHERE C.IDSECTOR= " + idsector
						+ "			AND C.CUENTA IN ('8110', '8140', '8150') \r\n" + "			AND C.NOTCTA=0 \r\n"
						+ nivelWhere
						+ "		GROUP BY C.SCTA, C.SSCTA, C.SSSCTA, C.SSSSCTA, DECODE(C.CUENTA,'8110',C.NOMCTA,''), STACTA\r\n"
						+ "		ORDER BY C.SCTA, C.SSCTA, C.SSSCTA, C.SSSSCTA ASC")
				.append(" )T1  GROUP BY T1.GRUPO, '8110', T1.SCTA , T1.SSCTA , T1.SSSCTA , T1.SSSSCTA )T2 ");

		if (pesos != 1) {
			sqlMiles.append("SELECT T3.SSCTAC, T3.GRUPO,T3.CUENTA,T3.SCTA,T3.SSCTA,T3.SSSCTA,T3.SSSSCTA, T3.NOMBRE, ")
					.append("	(T3.AMPLIACIONES /1000) AMPLIACIONES,(T3.ESTIMADO/1000) ESTIMADO,(T3.MODIFICADO/1000) MODIFICADO , ")
					.append("	(T3.DEVENGADO /1000) DEVENGADO,(T3.RECAUDADO/1000) RECAUDADO ,T3.AVANCE, (T3.INGRESO / 1000) INGRESO ")
					.append("	FROM ( ");
			sSql1.insert(0, sqlMiles);
			sSql1.append(")T3");
		}

		System.out.println(sSql1.toString());
		return sSql1.toString();

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

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

}
