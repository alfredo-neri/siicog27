package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte26MB.
 * 
 * @author Alfredo
 * @Timeline <strong>Date: </strong>22/04/2021 <strong> Changes: </strong> Se
 *            cambia nombre del archivo, se ajusta formato reporte<br>
 *            <strong>Date: </strong>13/08/2021 <strong> Changes: </strong> Se
 *            implementa servicio de firmas dinamico<br>
 *
 */
@Component("reporte26MB")
@Scope("view")
public class Reporte26MB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_INVENTARIO_GENERAL_BIENES_INMUEBLES";
	/** The sem. */
	private Integer sem;

	/** The conctb. */
	private Conctb conctb;

	/** The conctb repository. */
	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@Value("#{reporte27MB}")
	private Reporte27MB reporte27mb;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/**
	 * Gets the sem.
	 *
	 * @return the sem
	 */
	public Integer getSem() {
		return sem;
	}

	/**
	 * Sets the sem.
	 *
	 * @param sem the new sem
	 */
	public void setSem(Integer sem) {
		this.sem = sem;
	}

	/**
	 * Gets the conctb.
	 *
	 * @return the conctb
	 */
	public Conctb getConctb() {
		return conctb;
	}

	/**
	 * Sets the conctb.
	 *
	 * @param conctb the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "reporte26";
		endFilename = jasperReporteName + ".pdf";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getparametrsReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parametrs = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		TrPuestoFirma firma = new TrPuestoFirma();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcPeriodo mesSelected = periodoRepositoy.findByTipoPeriodoAndPeriodo(1, Integer.valueOf(sem * 6));
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(sem, conctb, reporte);

		if (sem == Integer.valueOf(1)) {
			parametrs.put("pMesIni", 1);
			parametrs.put("pMesFin", 6);
		} else if (sem == Integer.valueOf(2)) {
			parametrs.put("pMesIni", 1);
			parametrs.put("pMesFin", 12);
		}

		parametrs.put("pSector", sector);
		parametrs.put("pNomMunicipio", conctb.getNomDep());
		parametrs.put("pNumMunicipio", conctb.getClave());
		parametrs.put("pAn", conctb.getAnoemp());
		parametrs.put("pMes", mesSelected.getDescripcion().toUpperCase());
		parametrs.put("pSemestre", sem);
		parametrs.put("pLastDay", getLastDayByAnoEmp(mesSelected.getPeriodo(), conctb.getAnoemp()));

		firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F09.getValue());
		parametrs.put("pElaboro", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F10.getValue());
		parametrs.put("pReviso", firma.getNombre());

		parametrs.put("pImgMuni", conctb.getImagePathRigth());
		parametrs.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parametrs;
	}

	public void downLoadFile() {
		this.reporte27mb.setSemestre(getSem());
		try {
			this.reporte27mb.downLoadFile();
		} catch (ReportValidationException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public Reporte27MB getReporte27mb() {
		return reporte27mb;
	}

	public void setReporte27mb(Reporte27MB reporte27mb) {
		this.reporte27mb = reporte27mb;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
