package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

@ManagedBean
@ViewScoped
public class BalanzaAnualizadaMB extends BaseDirectReport {
	private static final Integer TIPO_PERIODO = 1;

	private List<TcPeriodo> listMeses;
	private Integer saldos;
	private Integer mes;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;
	
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@PostConstruct
	public void init() {
		jasperReporteName = "BalanzaAnualizada";
		endFilename = jasperReporteName + ".pdf";
		listMeses = periodoRepositoy.findByTipoPeriodo(TIPO_PERIODO);

		if (!listMeses.isEmpty()) {
			mes = listMeses.get(0).getPeriodo();
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		TcPeriodo periodo = periodoRepositoy.findByTipoPeriodoAndPeriodo(TIPO_PERIODO, mes);
		TrPuestoFirma firma = new TrPuestoFirma();
		Conctb conctb = conctbRepository.findByIdsector(idSector);

		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("imagePath", this.getUserDetails().getPathImgCab1());
		paramsReport.put("entidadName", this.getUserDetails().getMunicipio());
		paramsReport.put("lastDay", getLastDayByAnoEmp(mes, conctb.getAnoemp()));
		paramsReport.put("mesName", periodo.getDescripcion());
		if (idSector == 1) {
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
			paramsReport.put("firmaP1", firma.getPuesto().getPuesto());
			paramsReport.put("firmaN1", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F05.getValue());
			paramsReport.put("firmaP2", firma.getPuesto().getPuesto());
			paramsReport.put("firmaN2", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F27.getValue());
			paramsReport.put("firmaP3", firma.getPuesto().getPuesto());
			paramsReport.put("firmaN3", firma.getNombre());
		}else {
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
			paramsReport.put("firmaP1", firma.getPuesto().getPuesto());
			paramsReport.put("firmaN1", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F05.getValue());
			paramsReport.put("firmaP2", firma.getPuesto().getPuesto());
			paramsReport.put("firmaN2", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F27.getValue());
			paramsReport.put("firmaP3", firma.getPuesto().getPuesto());
			paramsReport.put("firmaN3", firma.getNombre());
		}
		
		
		paramsReport.put("query", generateQuery(idSector));

		return paramsReport;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateQuery(Integer sector) {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder sql = new StringBuilder();

		for (int i = 1; i <= mes; i++) {
			cargos.append(" + CARGOS_" + i);
			abonos.append(" + ABONOS_" + i);
		}

		sql.append("SELECT * FROM(").append("SELECT CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA, NOMCTA, SALINI, STACTA, ")
				.append(" (").append(cargos.substring(2)).append(") CARGOS, ").append(" (").append(abonos.substring(2))
				.append(") ABONOS").append(" FROM CUENTA").append(" WHERE IDSECTOR = ").append(sector)
				.append(" AND SCTA='' AND SSCTA='' AND SSSCTA='' AND NOTCTA=0 AND ")
				.append(" (SUBSTR(CUENTA,4,1)<>'0' OR SUBSTR(CUENTA,1,1)='5' OR SUBSTR(CUENTA,1,2)='81'  OR CUENTA LIKE '7%0') ")
				.append(" )T1 ");

		if (saldos < 1) {
			sql.append(" WHERE T1.SALINI <> 0 OR T1.ABONOS <> 0 OR T1.CARGOS <> 0");
		}

		return sql.toString();
	}

	public List<TcPeriodo> getListMeses() {
		return listMeses;
	}

	public void setListMeses(List<TcPeriodo> listMeses) {
		this.listMeses = listMeses;
	}

	public Integer getSaldos() {
		return saldos;
	}

	public void setSaldos(Integer saldos) {
		this.saldos = saldos;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
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

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}
	
	

}
