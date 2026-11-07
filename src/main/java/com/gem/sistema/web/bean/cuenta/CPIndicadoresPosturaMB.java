package com.gem.sistema.web.bean.cuenta;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.web.bean.BaseDirectReport;

/**
 * @author Alfredo Neri
 */
@ViewScoped
@ManagedBean(name = "cPIndicadoresPosturaMB")
public class CPIndicadoresPosturaMB extends BaseDirectReport {

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct IndicadoresPosturaFiscal ");
		jasperReporteName = "IndicadoresPosturaFiscal";
		endFilename = jasperReporteName + ".pdf";
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		
		map.put("imagePathRigth", conctb.getImagePathRigth());
		map.put("imagePathLeft", conctb.getImagePathLeft());
		map.put("dependencia", conctb.getNomDep());
		map.put("year", conctb.getAnoemp());
		
		return map;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

}
