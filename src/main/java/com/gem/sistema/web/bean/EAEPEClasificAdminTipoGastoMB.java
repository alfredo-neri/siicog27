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

@ManagedBean(name = "eAEPEClasificAdminTipoGastoMB")
@ViewScoped
public class EAEPEClasificAdminTipoGastoMB extends BaseDirectReport {
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
		jasperReporteName = "clasAdmin";
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

		parameters.put("pNameMun", conctb.getNomDep());
		parameters.put("pLastDay", getLastDayByAnoEmp(mesSelected.getPeriodo(), conctb.getAnoemp()));
		parameters.put("pMesFinal", mesSelected.getDescripcion());
		parameters.put("pAnio", conctb.getAnoemp());
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
		String redu = "SUM(";
		String ejpa = "SUM(";
		String ejxpa = "SUM(";
		String ampli = "SUM(";
//		String toeje = "SUM(";
		String compro = "SUM(";

		for (int y = 1; y <= mes; y++) {
			redu = redu + " PA.REDU1_" + y + " +";
			ejpa = ejpa + " PA.EJPA1_" + y + " +";
			ejxpa = ejxpa + " PA.TOEJE1_" + y + " +";
			ampli = ampli + " PA.AMPLI1_" + y + " +";
//			toeje = toeje + " PA.TOEJE1_" + y + " +";
			compro = compro + " PA.COMPRO1_" + y + " +";
		}

		auto = auto
				+ " PA.AUTO1_1 + PA.AUTO1_2+ PA.AUTO1_3+ PA.AUTO1_4+ PA.AUTO1_5+ PA.AUTO1_6	+ PA.AUTO1_7+ PA.AUTO1_8+ PA.AUTO1_9+ PA.AUTO1_10+ PA.AUTO1_11+ PA.AUTO1_12 ) APROBADO, ";
		redu = redu.substring(0, redu.length() - 2) + " ) REDUCCION, ";
		ejpa = ejpa.substring(0, ejpa.length() - 2) + " ) PAGADO, ";
		ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " ) DEVENGADO, ";
		ampli = ampli.substring(0, ampli.length() - 2) + " ) AMPLIACION, ";
//		toeje = toeje.substring(0, toeje.length() - 2) + " ) EJERCIDO, ";
		compro = compro.substring(0, compro.length() - 2) + " ) COMPROMETIDO ";

		sql.append("SELECT T1.CLAVE, T1.NOMBRE, T1.APROBADO, (T1.AMPLIACION - T1.REDUCCION)AMP_REDU, ")
				.append("(T1.APROBADO + T1.AMPLIACION)- T1.REDUCCION MODIFICADO, (T1.DEVENGADO)DEVENGADO, ")
				.append("(T1.PAGADO)PAGADO, ")
				.append("((T1.APROBADO + T1.AMPLIACION)- T1.REDUCCION) -  T1.DEVENGADO SUBEJERCICIO ")
				.append("FROM (SELECT CA.CLAVE, CA.NOMBRE, ").append(auto).append(ampli).append(redu).append(ejpa)
				.append(ejxpa)
//				.append(toeje)
				.append(compro).append("FROM PASO PA,  CATDGM CA\n" + "WHERE PA.IDSECTOR = ").append(idSector)
				.append(" AND SUBSTR(PA.PARTIDA,4,1) <> '0' ")
				.append(" AND CA.CLAVE=SUBSTR(PA.CLAVE,1,3) GROUP BY CA.CLAVE, CA.NOMBRE ORDER BY 1,2,3) T1");

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

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

}
