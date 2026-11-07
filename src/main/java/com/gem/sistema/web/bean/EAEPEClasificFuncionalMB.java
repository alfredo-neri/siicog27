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

@ManagedBean(name = "eAEPEClasificFuncionalMB")
@ViewScoped
public class EAEPEClasificFuncionalMB extends BaseDirectReport {
	private List<TcPeriodo> listMeses;
	private Integer mes;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	@PostConstruct
	public void init() {
		jasperReporteName = "clasificacionFuncional(FinalidadyFuncion)";
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
		TrPuestoFirma firma = new TrPuestoFirma();

		parameters.put("pMunicipi", conctb.getNomDep());
		parameters.put("pDay", getLastDayByAnoEmp(mesSelected.getPeriodo(), conctb.getAnoemp()));
		parameters.put("pMes", mesSelected.getDescripcion());
		parameters.put("pYear", conctb.getAnoemp());
		parameters.put("pImage", conctb.getImagePathLeft());

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

	private String generateSQL(Integer idSector, Integer mes) {
		StringBuilder sql = new StringBuilder();

		String auto = "SUM(";
		String ejpa = "SUM(";
		String redu = "SUM(";
		String ejxpa = "SUM(";
		String ampli = "SUM(";

		for (int y = 1; y <= mes; y++) {

			ejpa = ejpa + " PA.EJPA1_" + y + " +";
			redu = redu + " PA.REDU1_" + y + " +";
			ejxpa = ejxpa + " PA.TOEJE1_" + y + " +";
			ampli = ampli + " PA.AMPLI1_" + y + " +";
		}

		auto = auto
				+ " PA.AUTO1_1 + PA.AUTO1_2+ PA.AUTO1_3+ PA.AUTO1_4+ PA.AUTO1_5+ PA.AUTO1_6	+ PA.AUTO1_7+ PA.AUTO1_8+ PA.AUTO1_9+ PA.AUTO1_10+ PA.AUTO1_11+ PA.AUTO1_12 ) APROBADO, ";
		ejpa = ejpa.substring(0, ejpa.length() - 2) + " ) PAGADO ";
		redu = redu.substring(0, redu.length() - 2) + " ) REDUCCION, ";
		ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " ) DEVENGADO, ";
		ampli = ampli.substring(0, ampli.length() - 2) + " ) AMPLIACION, ";

		sql.append("SELECT T1.FF, T1.FF_NAME, T1.GOB, T1.FF_CONCEPTO, T1.APROBADO, ")
				.append("(T1.AMPLIACION - T1.REDUCCION) APMLIREDU, ")
				.append("(T1.APROBADO+ T1.AMPLIACION) - T1.REDUCCION MODIFICADO, ").append("T1.DEVENGADO, T1.PAGADO, ")
				.append("((T1.APROBADO+ T1.AMPLIACION) - T1.REDUCCION)-T1.DEVENGADO SUBEJERCICIO ")
				.append("FROM(SELECT SUBSTR(PA.PROGRAMA,1,2) FF, MUN.CAMPO6 FF_NAME, ")
				.append("SUBSTR(PA.PROGRAMA,3,2) GOB, MU.CAMPO6 FF_CONCEPTO, ").append(auto).append(ampli).append(redu)
				.append(ejxpa).append(ejpa).append("FROM PASO PA, MUNINEP MU, MUNINEP MUN ")
				.append("WHERE PA.IDSECTOR = MU.IDSECTOR AND SUBSTR(PA.PROGRAMA,1,4) = MU.CAMPO7 ")
				.append("AND SUBSTR(PA.PARTIDA,4,1) <> '0' ")
				.append("AND PA.IDSECTOR = MUN.IDSECTOR AND SUBSTR(PA.PROGRAMA,1,2) = MUN.CAMPO7 ")
				.append("AND PA.IDSECTOR = ").append(idSector).append(" GROUP BY SUBSTR(PA.PROGRAMA,1,2), ")
				.append("MUN.CAMPO6, SUBSTR(PA.PROGRAMA,3,2), MU.CAMPO6 ORDER BY 1,3) T1");
		System.out.println(sql);
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

}
