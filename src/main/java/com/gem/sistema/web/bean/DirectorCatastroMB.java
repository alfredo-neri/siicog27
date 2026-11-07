package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "directorCatastroMB")
@ViewScoped
public class DirectorCatastroMB extends BaseDirectReport {

	private List<TcPeriodo> listSemestres;
	private Integer semestreSelected;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@PostConstruct
	public void init() {
		listSemestres = tcPeriodoRepositoy.findByTipoPeriodo(6);
		jasperReporteName = "directorCatastro";
		endFilename = jasperReporteName + ".pdf";

		if (!listSemestres.isEmpty()) {
			semestreSelected = listSemestres.get(0).getPeriodo();
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Integer mesFinal = semestreSelected * 6;
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		TcPeriodo mes = tcPeriodoRepositoy.findByTipoPeriodoAndPeriodo(1, mesFinal);

		parameters.put("municipio", firmas.getCampo1());
		parameters.put("imagen", this.getUserDetails().getPathImgCab1());
		parameters.put("anio", firmas.getCampo3());
		parameters.put("mes", mes.getDescripcion());
		parameters.put("dia", getLastDayByAnoEmp(mesFinal, firmas.getCampo3()));
		parameters.put("firmaL1", firmas.getL27());
		parameters.put("firmaN1", firmas.getN27());
		parameters.put("semestre",semestreSelected);

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TcPeriodo> getListSemestres() {
		return listSemestres;
	}

	public void setListSemestres(List<TcPeriodo> listSemestres) {
		this.listSemestres = listSemestres;
	}

	public Integer getSemestreSelected() {
		return semestreSelected;
	}

	public void setSemestreSelected(Integer semestreSelected) {
		this.semestreSelected = semestreSelected;
	}

	public TcPeriodoRepositoy getTcPeriodoRepositoy() {
		return tcPeriodoRepositoy;
	}

	public void setTcPeriodoRepositoy(TcPeriodoRepositoy tcPeriodoRepositoy) {
		this.tcPeriodoRepositoy = tcPeriodoRepositoy;
	}

	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

}
