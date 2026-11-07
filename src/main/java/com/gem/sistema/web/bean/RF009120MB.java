package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;

// TODO: Auto-generated Javadoc
/**
 * The Class RF009120MB.
 */
@ManagedBean
@ViewScoped
public class RF009120MB extends BaseDirectReport {

	private static final String CLAVE_REPORT = "RPT_AVAN_PRE_EGR_GLOBAL_RAN_MES";

	/** The mes inicial. */
	private Integer mesInicial;

	/** The mes final. */
	private Integer mesFinal;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
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

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	/**
	 * Gets the mes inicial.
	 *
	 * @return the mes inicial
	 */
	public Integer getMesInicial() {
		return mesInicial;
	}

	/**
	 * Sets the mes inicial.
	 *
	 * @param mesInicial the new mes inicial
	 */
	public void setMesInicial(Integer mesInicial) {
		this.mesInicial = mesInicial;
	}

	/**
	 * Gets the mes final.
	 *
	 * @return the mes final
	 */
	public Integer getMesFinal() {
		return mesFinal;
	}

	/**
	 * Sets the mes final.
	 *
	 * @param mesFinal the new mes final
	 */
	public void setMesFinal(Integer mesFinal) {
		this.mesFinal = mesFinal;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RF009120MB ");
		jasperReporteName = "GlobalRangoMeses";
		endFilename = jasperReporteName + ".pdf";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(mesInicial, conctb, reporte);

		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("lastDay", getLastDayByAnoEmp(mesFinal, conctb.getAnoemp()));
		paramsReport.put("mesFinal", mesFinal);
		paramsReport.put("idSector", idSector);
		paramsReport.put("imageLeft", conctb.getImagePathLeft());
		paramsReport.put("imageRigth", conctb.getImagePathRigth());
		paramsReport.put("mesInicial", mesInicial);
		paramsReport.put("entidadName", conctb.getNomDep());
		paramsReport.put("mesFinalName", periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mesFinal).getDescripcion());
		paramsReport.put("mesInicialName",
				periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mesInicial).getDescripcion());
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return paramsReport;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#createFilePdfInline()
	 */
	@Override
	public void createFilePdfInline() {
		if (isValid()) {
			super.createFilePdfInline();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getFileXls()
	 */
	@Override
	public StreamedContent getFileXls() {
		if (isValid()) {
			return super.getFileXls();
		} else {
			return null;
		}
	}

	/**
	 * Checks if is valid.
	 *
	 * @return true, if is valid
	 */
	public boolean isValid() {
		if (mesInicial > mesFinal) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"El mes inicial no puede ser mayor al mes final.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return false;
		} else {
			return true;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
		/*
		 * Map<String, Object> paramsQuery = new java.util.HashMap<String, Object>();
		 * paramsQuery.put("ID_REF", new Integer(0)); //FALTA return
		 * reporteadorDirectoImpl.getFileReport(tcReporte,paramsQuery,
		 * reporteName,type);
		 */
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

}
