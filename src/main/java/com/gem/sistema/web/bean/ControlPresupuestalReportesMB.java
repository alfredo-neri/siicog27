
package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

/**
 * @author Alfredo
 *
 */
@Component("controlPresupuestalReportesMB")
@Scope("view")
public class ControlPresupuestalReportesMB extends ReportePeriodos {

	private static final Logger LOGGER = LoggerFactory.getLogger(ControlPresupuestalReportesMB.class);

	private String report = StringUtils.EMPTY;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct ControlPresupuestalReportesMB ");

		jasperReporteName = "seguimientoFAM";
		endFilename = jasperReporteName + ".pdf";

	}

	public Map<String, Object> getParametersReports() {

		switch (report) {
			case "RPT_PAD":
				jasperReporteName = "seguimientoObraPAD";
				break;
			case "RPT_PAD_TRIM":
				jasperReporteName = "PADtrimestral";
				break;
			case "RPT_FAM":
				jasperReporteName = "seguimientoFAM";
				break;
		}

		endFilename = jasperReporteName + ".pdf";

		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();

		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);

		paramsReport.put("imagePathLeft", conctb.getImagePathLeft());
		paramsReport.put("imagePathRigth", conctb.getImagePathRigth());
		paramsReport.put("nameDependencia", conctb.getNomDep());
		paramsReport.put("year", conctb.getAnoemp());

		return paramsReport;
	}

	
	public void downloadXls() {
	}
	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

}
