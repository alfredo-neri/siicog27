package com.gem.sistema.web.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class AuxiliarCuentaMB.
 */
@ManagedBean(name = "auxiliarCuentaMB")
@ViewScoped
public class AuxiliarCuentaMB extends BaseDirectReport {

	private String cta;

	private Integer mes;

	private List<TcPeriodo> listMes;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@PostConstruct
	public void init() {
		jasperReporteName = "auxiliarCuentas";
		endFilename = jasperReporteName + ".pdf";
		
		listMes = periodoRepositoy.findByTipoPeriodo(1);
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getPeriodo();
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcPeriodo periodo = periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mes);
		
		parameters.put("pImagePath1", conctb.getImagePathLeft());
		parameters.put("pImagePath2", conctb.getImagePathRigth());
		parameters.put("pNombre", conctb.getNomDep());
		parameters.put("pYear", conctb.getAnoemp());
		parameters.put("pMesName", periodo.getDescripcion());
		parameters.put("pLastDay", getLastDayByAnoEmp(mes, conctb.getAnoemp()));
		parameters.put("pQuery", this.generaSql(mes, cta));
		
		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private String generaSql(Integer mes, String cuenta) {
		StringBuilder sql = new StringBuilder();
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();

		for (int y = 1; y <= mes; y++) {
			cargos.append(" + CARGOS_" + y);
			abonos.append(" + ABONOS_" + y);
		}

		sql.append("SELECT CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA, NOMCTA, STACTA, SALINI, CARGOS, ABONOS ")
				.append("	FROM ( ").append("		SELECT CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA, NOMCTA, STACTA, ")
				.append("			SALINI, (" + cargos.substring(1) + ") CARGOS, (" + abonos.substring(1)
						+ ") ABONOS ")
				.append("			FROM CUENTA WHERE CUENTA = '" + cuenta + "' ")
				.append("			AND IDSECTOR = " + this.getUserDetails().getIdSector())
				.append("		) T1 WHERE SALINI <> 0 OR CARGOS <> 0 OR ABONOS <> 0 ")
				.append("ORDER BY CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA");

		System.out.println(sql.toString());
		return sql.toString();
	}

	public String getCta() {
		return cta;
	}

	public void setCta(String cta) {
		this.cta = cta;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public List<TcPeriodo> getListMes() {
		return listMes;
	}

	public void setListMes(List<TcPeriodo> listMes) {
		this.listMes = listMes;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

}
