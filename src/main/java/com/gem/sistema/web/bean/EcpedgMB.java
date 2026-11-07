package com.gem.sistema.web.bean;

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
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

@ManagedBean(name = "ecpedgMB")
@ViewScoped
public class EcpedgMB extends BaseDirectReport {

	private List<TcPeriodo> listMeses;
	private Integer mesSelected;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@PostConstruct
	public void init() {
		listMeses = tcPeriodoRepositoy.findByTipoPeriodo(1);
		jasperReporteName = "ECPEDG";
		endFilename = jasperReporteName + ".pdf";

		if (!listMeses.isEmpty()) {
			mesSelected = listMeses.get(0).getPeriodo();
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
	
		TcPeriodo mes = tcPeriodoRepositoy.findByTipoPeriodoAndPeriodo(1, mesSelected);
		Integer sector = this.getUserDetails().getIdSector();
		TrPuestoFirma firma = null;
		Conctb conctb=conctbRepository.findByIdsector(this.getUserDetails().getIdSector());

		parameters.put("pImage", conctb.getImagePathRigth());
		parameters.put("pMunicipio", conctb.getNomDep());
		parameters.put("pMesFinal", mes.getDescripcion());
		parameters.put("pLastDay", getLastDayByAnoEmp(mesSelected, conctb.getAnoemp()));
		parameters.put("pAnio", conctb.getAnoemp());
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F01.getValue());
		parameters.put("pL1", firma.getPuesto().getPuesto());
		parameters.put("pN1", firma.getNombre());
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F02.getValue());
		parameters.put("pL2", firma.getPuesto().getPuesto());
		parameters.put("pN2", firma.getNombre());
		firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F03.getValue());
		parameters.put("pL3", firma.getPuesto().getPuesto());
		parameters.put("pN3", firma.getNombre());
		parameters.put("pSql", this.generateSQL(this.getUserDetails().getIdSector(), mesSelected));

		return parameters;
	}

	private String generateSQL(Integer sector, Integer mes) {

		StringBuilder sql = new StringBuilder();

		String auto = "(";
		String redu = "(";
		String ampl = "(";
		String toeje = "SUM(";

		for (int y = 1; y <= mes; y++) {
			auto = auto + " PA.AUTO1_" + y + " +";
			redu = redu + " PA.REDU1_" + y + " +";
			ampl = ampl + " PA.AMPLI1_" + y + " +";
			toeje = toeje + "PA.TOEJE1_" + y + " +";
		}

		auto = auto.substring(0, auto.length() - 2) + ")";
		redu = redu.substring(0, redu.length() - 2) + ")";
		ampl = ampl.substring(0, ampl.length() - 2) + ")";
		toeje = toeje.substring(0, toeje.length() - 2) + ") EJERACUM ";

		sql.append("SELECT DEPGEN, NOMBRE, PARTIDA, CONCEPTO, APROBADO, MODIFICADO, EJERCIDO, MODIACUM, EJERACUM, ")
				.append("(MODIACUM-EJERACUM) VARABS, ")
				.append("DECODE(MODIACUM, 0.0, 0, (((MODIACUM-EJERACUM)/ MODIACUM) * 100)) VAR ")
				.append("FROM ( SELECT  GM.CLAVE DEPGEN, GM.NOMBRE, PA.PARTIDA, NA.NOMGAS CONCEPTO, ")
				.append("SUM(PA.AUTO1_1+PA.AUTO1_2+PA.AUTO1_3+PA.AUTO1_4+PA.AUTO1_5+PA.AUTO1_6+")
				.append("PA.AUTO1_7+PA.AUTO1_8+PA.AUTO1_9+PA.AUTO1_10+PA.AUTO1_11+PA.AUTO1_12)APROBADO, ")
				.append("SUM(PA.AUTO1_").append(mes).append(" + PA.AMPLI1_").append(mes).append(" - PA.REDU1_")
				.append(mes).append(") MODIFICADO, ").append("SUM(PA.TOEJE1_").append(mes).append(") EJERCIDO, ")
				.append("SUM(").append(auto).append("+").append(ampl).append("-").append(redu).append(") MODIACUM, ")
				.append(toeje)
				.append("FROM PASO PA INNER JOIN NATGAS NA ON PA.PARTIDA=NA.CLVGAS AND PA.IDSECTOR=NA.IDSECTOR ")
				.append("INNER JOIN CATDAA CA ON SUBSTR(PA.CLAVE,4,3)=CA.CLAVE ")
				.append("INNER JOIN CATDGM GM ON SUBSTR(PA.CLAVE,1,3)=GM.CLAVE ").append("WHERE PA.IDSECTOR= ")
				.append(sector)
				.append(" GROUP BY GM.CLAVE, GM.NOMBRE, PA.PARTIDA, NA.NOMGAS )T1 ORDER BY DEPGEN, T1.PARTIDA ASC ");

		return sql.toString();
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TcPeriodo> getListMeses() {
		return listMeses;
	}

	public void setListMeses(List<TcPeriodo> listMeses) {
		this.listMeses = listMeses;
	}

	public Integer getMesSelected() {
		return mesSelected;
	}

	public void setMesSelected(Integer mesSelected) {
		this.mesSelected = mesSelected;
	}

	public TcPeriodoRepositoy getTcPeriodoRepositoy() {
		return tcPeriodoRepositoy;
	}

	public void setTcPeriodoRepositoy(TcPeriodoRepositoy tcPeriodoRepositoy) {
		this.tcPeriodoRepositoy = tcPeriodoRepositoy;
	}

	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
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

}
