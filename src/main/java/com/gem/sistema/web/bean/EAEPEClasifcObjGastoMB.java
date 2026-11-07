package com.gem.sistema.web.bean;

import java.util.HashMap;
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

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

@ManagedBean(name = "eAEPEClasifcObjGastoMB")
@ViewScoped
public class EAEPEClasifcObjGastoMB extends BaseDirectReport {

	private List<TcPeriodo> listMeses;
	private Integer mes;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@PostConstruct
	public void init() {
		jasperReporteName = "EAEPEClasificObjGasto";
		endFilename = jasperReporteName + ".pdf";

		listMeses = periodoRepositoy.findByTipoPeriodo(1);

		if (!listMeses.isEmpty()) {
			mes = listMeses.get(0).getPeriodo();
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		TcPeriodo mesSelected = periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mes);
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = null;

		parameters.put("pMunicipio", conctb.getNomDep());
		parameters.put("pLastDay", getLastDayByAnoEmp(mesSelected.getPeriodo(), conctb.getAnoemp()));
		parameters.put("pLastMonth", mesSelected.getDescripcion());
		parameters.put("pYear", conctb.getAnoemp());
		parameters.put("pMunicipioClave", conctb.getClave());
		parameters.put("pImagenPath", conctb.getImagePathLeft());

		switch (conctb.getClave().substring(0, 1)) {
		case "0":
			parameters.put("pNoFirmas", 2);
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F01.getValue());
			parameters.put("pL1", firma.getPuesto().getPuesto());
			parameters.put("pN1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F03.getValue());
			parameters.put("pL3", firma.getPuesto().getPuesto());
			parameters.put("pN3", firma.getNombre());
			break;

		case "2":
			parameters.put("pNoFirmas", 3);
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F10.getValue());
			parameters.put("pL1", firma.getPuesto().getPuesto());
			parameters.put("pN1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F11.getValue());
			parameters.put("pL2", firma.getPuesto().getPuesto());
			parameters.put("pN2", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F24.getValue());
			parameters.put("pL3", firma.getPuesto().getPuesto());
			parameters.put("pN3", firma.getNombre());
			break;
		case "3":
			parameters.put("pNoFirmas", 3);
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F07.getValue());
			parameters.put("pL1", firma.getPuesto().getPuesto());
			parameters.put("pN1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F08.getValue());
			parameters.put("pL2", firma.getPuesto().getPuesto());
			parameters.put("pN2", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F09.getValue());
			parameters.put("pL3", firma.getPuesto().getPuesto());
			parameters.put("pN3", firma.getNombre());
			break;

		case "4":
			parameters.put("pNoFirmas", 2);
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F20.getValue());
			parameters.put("pL1", firma.getPuesto().getPuesto());
			parameters.put("pN1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F21.getValue());
			parameters.put("pL3", firma.getPuesto().getPuesto());
			parameters.put("pN3", firma.getNombre());
			break;
		}

		parameters.put("pSql", this.generateSQL(idSector, mes));

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateSQL(Integer idSector, Integer mes) {
		StringBuilder sql = new StringBuilder();

		String auto = "SUM(";
		String ejpa = "SUM(";
		String redu = "SUM(";
		String ejxpa = "SUM(";
		String ampli = "SUM(";

		for (int y = 1; y <= mes; y++) {
//			auto = auto + " PA.AUTO1_" + y + " +";
			ejpa = ejpa + " PA.EJPA1_" + y + " +";
			redu = redu + " PA.REDU1_" + y + " +";
			ejxpa = ejxpa + " PA.TOEJE1_" + y + " +";
			ampli = ampli + " PA.AMPLI1_" + y + " +";
		}
		
		for (int y = 1; y <= 12; y++) {
			auto = auto + " PA.AUTO1_" + y + " +";
		}

		auto = auto.substring(0, auto.length() - 2) + " ) AUTO, ";
		ejpa = ejpa.substring(0, ejpa.length() - 2) + " ) EJPA ";
		redu = redu.substring(0, redu.length() - 2) + " ) REDU, ";
		ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " ) EJXPA, ";
		ampli = ampli.substring(0, ampli.length() - 2) + " ) AMPLI, ";

		sql.append("SELECT T1.PARTIDA, T1.NOMGAS, T1.AUTO APROBADO, (T1.AMPLI - T1.REDU) AMPLIREDU, ")
				.append("(T1.AUTO + T1.AMPLI - T1.REDU) MODIFICADO, T1.EJXPA DEVENGADO, T1.EJPA PAGADO, ")
				.append("(T1.AUTO + T1.AMPLI - T1.REDU - T1.EJXPA) SUBEJECICIO ")
				.append("FROM ( SELECT PA.PARTIDA, NA.NOMGAS, ").append(auto).append(ampli).append(redu).append(ejxpa)
				.append(ejpa).append("FROM PASO PA, NATGAS NA ")
				.append("WHERE PA.PARTIDA = NA.CLVGAS AND PA.IDSECTOR = NA.IDSECTOR AND PA.IDSECTOR = ")
				.append(idSector).append(" GROUP BY PA.PARTIDA, NA.NOMGAS ) T1 ORDER BY T1.PARTIDA");

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

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
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
