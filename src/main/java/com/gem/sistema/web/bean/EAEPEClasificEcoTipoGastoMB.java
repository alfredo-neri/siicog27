package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;

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

@Component("eAEPEClasificEcoTipoGastoMB")
@Scope("view")
public class EAEPEClasificEcoTipoGastoMB extends BaseDirectReport {
	private List<TcPeriodo> listMeses;
	private Integer mes;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@PostConstruct
	public void init() {
		jasperReporteName = "clasificacioneconomicaportipodegasto";
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

		parameters.put("pMunicipio", conctb.getNomDep());
		parameters.put("pDay", getLastDayByAnoEmp(mesSelected.getPeriodo(), conctb.getAnoemp()));
		parameters.put("pMes", mesSelected.getDescripcion());
		parameters.put("pYear", conctb.getAnoemp());
		parameters.put("pImagen", conctb.getImagePathLeft());

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

		parameters.put("pQuery", this.generateSQL(idSector, mes));

		return parameters;
	}

	private String generateSQL(Integer idSector, Integer mes) {
		StringBuilder sql = new StringBuilder();

		String auto = "FN_GET_FORMAT_NUMBER(SUM(";
		String ejpa = "FN_GET_FORMAT_NUMBER(SUM(";
		String redu = "FN_GET_FORMAT_NUMBER(SUM(";
		String ejxpa = "FN_GET_FORMAT_NUMBER(SUM(";
		String ampli = "FN_GET_FORMAT_NUMBER(SUM(";

		for (int y = 1; y <= mes; y++) {
//			auto = auto + " P.AUTO1_" + y + " +";
			ejpa = ejpa + " P.EJPA1_" + y + " +";
			redu = redu + " P.REDU1_" + y + " +";
			ejxpa = ejxpa + " P.TOEJE1_" + y + " +";
			ampli = ampli + " P.AMPLI1_" + y + " +";
		}
		
		for (int y = 1; y <= 12; y++) {
			auto = auto + " P.AUTO1_" + y + " +";
		}

		auto = auto.substring(0, auto.length() - 2) + " )) APROBADO, ";
		ejpa = ejpa.substring(0, ejpa.length() - 2) + " )) PAGADO ";
		redu = redu.substring(0, redu.length() - 2) + " )) REDUCCIONES, ";
		ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " )) DEVENGADO, ";
		ampli = ampli.substring(0, ampli.length() - 2) + " )) AMPLIACIONES, ";

		sql.append("SELECT RES.CUENTA, RES.CONCEPTO, RES.APROBADO, (RES.AMPLIACIONES-RES.REDUCCIONES) AMP_RED, ")
				.append("(RES.APROBADO+RES.AMPLIACIONES-RES.REDUCCIONES) MODIFICADO , RES.DEVENGADO, RES.PAGADO, ")
				.append("(RES.APROBADO+RES.AMPLIACIONES-RES.REDUCCIONES-RES.DEVENGADO) SUB_E, 'Gasto Corriente' ")
				.append("FROM (SELECT P.PARTIDA CUENTA, N.NOMGAS CONCEPTO, ").append(auto).append(redu).append(ampli)
				.append(ejxpa).append(ejpa)
				.append("FROM PASO P, NATGAS N WHERE P.PARTIDA=N.CLVGAS AND P.IDSECTOR=N.IDSECTOR AND P.IDSECTOR = ")
				.append(idSector).append(" AND N.IDSECTOR = ").append(idSector)
				.append(" AND P.PARTIDA <5000 GROUP BY P.PARTIDA, N.NOMGAS ORDER BY 1,2 )RES UNION ALL ")
				.append("SELECT RES.CUENTA, RES.CONCEPTO, RES.APROBADO, (RES.AMPLIACIONES-RES.REDUCCIONES) AMP_RED, ")
				.append("(RES.APROBADO+RES.AMPLIACIONES-RES.REDUCCIONES) MODIFICADO , RES.DEVENGADO, RES.PAGADO, ")
				.append("(RES.APROBADO+RES.AMPLIACIONES-RES.REDUCCIONES-RES.DEVENGADO) SUB_E, 'Gasto Capital' ")
				.append("FROM (SELECT P.PARTIDA CUENTA, N.NOMGAS CONCEPTO, ").append(auto).append(redu).append(ampli)
				.append(ejxpa).append(ejpa)
				.append("FROM PASO P,NATGAS N WHERE P.PARTIDA=N.CLVGAS AND P.IDSECTOR=N.IDSECTOR AND P.IDSECTOR = ")
				.append(idSector).append(" AND N.IDSECTOR = ").append(idSector)
				.append(" AND P.PARTIDA >=5000 AND P.PARTIDA <9000 GROUP BY P.PARTIDA, N.NOMGAS ORDER BY 1,2 )RES UNION ALL ")
				.append("SELECT RES.CUENTA, RES.CONCEPTO, RES.APROBADO, (RES.AMPLIACIONES-RES.REDUCCIONES) AMP_RED, ")
				.append("(RES.APROBADO+RES.AMPLIACIONES-RES.REDUCCIONES) MODIFICADO , RES.DEVENGADO, RES.PAGADO, ")
				.append("(RES.APROBADO+RES.AMPLIACIONES-RES.REDUCCIONES-RES.DEVENGADO) SUB_E,")
				.append("'Amortización de la Deuda y Disminucion de Pasivos' ")
				.append("FROM (SELECT P.PARTIDA CUENTA, N.NOMGAS  CONCEPTO, ").append(auto).append(redu).append(ampli)
				.append(ejxpa).append(ejpa)
				.append("FROM PASO P,NATGAS N WHERE P.PARTIDA=N.CLVGAS AND P.IDSECTOR=N.IDSECTOR AND P.IDSECTOR = ")
				.append(idSector).append(" AND N.IDSECTOR = ").append(idSector)
				.append(" AND P.PARTIDA >=9000 GROUP BY P.PARTIDA, N.NOMGAS ORDER BY 1,2 )RES");

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
