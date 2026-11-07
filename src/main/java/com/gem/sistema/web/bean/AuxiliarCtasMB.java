package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

@ManagedBean(name = "auxiliarCtasMB")
@ViewScoped
public class AuxiliarCtasMB extends BaseDirectReport {

	private List<TcPeriodo> listMeses;
	private Integer mes;
	private Integer mesF;
	private String cuenta;
	private String scta;
	private String sscta;
	private String ssscta;
	private String sssscta;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

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

		jasperReporteName = "auxiliarCtas";
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
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Integer idSector = this.getUserDetails().getIdSector();
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		TrPuestoFirma firma = new TrPuestoFirma();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcPeriodo periodo = periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mesF);

		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.put("imagenPath", conctb.getImagePathLeft());
		parameters.put("mesName", periodo.getDescripcion());
		parameters.put("idSector", idSector);
		parameters.put("mes", periodo.getPeriodo());
		parameters.put("year", conctb.getAnoemp());

		parameters.put("sql", this.generateSQL(conctb.getAnoemp(), idSector));

		if (idSector == 2) {
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F27.getValue());
			parameters.put("firmaL1", firma.getPuesto().getPuesto());
			parameters.put("firmaN1", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F04.getValue());
			parameters.put("firmaL2", firma.getPuesto().getPuesto());
			parameters.put("firmaN2", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F05.getValue());
			parameters.put("firmaL3", firma.getPuesto().getPuesto());
			parameters.put("firmaN3", firma.getNombre());
		} else {
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F03.getValue());
			parameters.put("firmaL1", firma.getPuesto().getPuesto());
			parameters.put("firmaN1", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F04.getValue());
			parameters.put("firmaL2", firma.getPuesto().getPuesto());
			parameters.put("firmaN2", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F05.getValue());
			parameters.put("firmaL3", firma.getPuesto().getPuesto());
			parameters.put("firmaN3", firma.getNombre());
		}

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
			scta = StringUtils.leftPad(value, 10, StringUtils.EMPTY + ZERO);
		}

		value = sscta;
		if (StringUtils.isNotEmpty(value)) {
			sscta = StringUtils.leftPad(value, 15, StringUtils.EMPTY + ZERO);
		}

		value = ssscta;
		if (StringUtils.isNotEmpty(value)) {
			ssscta = StringUtils.leftPad(value, 4, StringUtils.EMPTY + ZERO);
		}

		value = sssscta;
		if (StringUtils.isNotEmpty(value)) {
			sssscta = StringUtils.leftPad(value, 3, StringUtils.EMPTY + ZERO);
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

		sql.append("SELECT C.CUENTA, C.SCTA, C.SSCTA, C.SSSCTA, C.SSSSCTA, C.NOMCTA, P.CONCEP, C.STACTA, E.FECPOL,E.ANOPOL ,")
				.append("P.DN, P.RENPOL, P.CONPOL, P.TIPPOL, TO_CHAR(P.REFPOL,'9999999999') REFPOL, P.MESPOL, P.USERID, 0.00 SALINI, NVL(P.CANPOL,0) CARGOS, ")
				.append(" NVL(P.CANPOLH,0) ABONOS  FROM CUENTA C ")
				.append(" INNER JOIN POLIDE P ON P.CUENTA = C.CUENTA AND P.SCTA = C.SCTA AND P.SSCTA=C.SSCTA AND P.SSSCTA=C.SSSCTA ")
				.append(" AND P.SSSSCTA=C.SSSSCTA AND P.IDSECTOR= C.IDSECTOR AND P.MESPOL BETWEEN ").append(mes)
				.append(" AND ").append(mesF)
				.append(" INNER JOIN POLIEN E ON E.ANOPOL = P.ANOPOL AND E.TIPPOL = P.TIPPOL AND E.MESPOL = P.MESPOL AND E.CONPOL = P.CONPOL AND E.IDSECTOR = P.IDSECTOR ")
				.append(" WHERE UPPER(C.NIVCTA) = 'S' AND ").append(whereCuenta).append(" AND C.IDSECTOR = ")
				.append(sector).append(" ORDER BY CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA, DN");
		System.out.println(sql.toString());
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

	public Integer getMesF() {
		return mesF;
	}

	public void setMesF(Integer mesF) {
		this.mesF = mesF;
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

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}
}
