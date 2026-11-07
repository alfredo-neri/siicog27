package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.QueryGeneratorService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte55MB.
 */
@Component("reporte55MB")
@Scope("view")
public class Reporte55MB extends BaseDirectReport {

	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	private static final String CLAVE_REPORT = "RPT_AVAN_PRE_EGR_SECR";

	/** The mes. */
	private String mes;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The secretaria. */
	private String secretaria;

	/** The list clvdep. */
	private List<String> listClvdep;

	/** The tcmes. */
	private TcMes tcmes;

	/** The xcatpro. */
	private Xcatpro xcatpro;

	private Catdep catdep;

	/** The catdep repository. */
	@Value("#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/** The tc mes repository. */
	@Value("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The conctb repository. */
	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@Value("#{queryGeneratorService}")
	private QueryGeneratorService queryGeneratorService;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	/**
	 * Gets the list mes.
	 *
	 * @return the list mes
	 */
	public List<TcMes> getListMes() {
		return listMes;
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
	 * Sets the list mes.
	 *
	 * @param listMes the new list mes
	 */
	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the secretaria.
	 *
	 * @return the secretaria
	 */
	public String getSecretaria() {
		return secretaria;
	}

	/**
	 * Sets the secretaria.
	 *
	 * @param secretaria the new secretaria
	 */
	public void setSecretaria(String secretaria) {
		this.secretaria = secretaria;
	}

	/**
	 * Gets the tcmes.
	 *
	 * @return the tcmes
	 */
	public TcMes getTcmes() {
		return tcmes;
	}

	/**
	 * Sets the tcmes.
	 *
	 * @param tcmes the new tcmes
	 */
	public void setTcmes(TcMes tcmes) {
		this.tcmes = tcmes;
	}

	/**
	 * Gets the xcatpro.
	 *
	 * @return the xcatpro
	 */
	public Xcatpro getXcatpro() {
		return xcatpro;
	}

	/**
	 * Sets the xcatpro.
	 *
	 * @param xcatpro the new xcatpro
	 */
	public void setXcatpro(Xcatpro xcatpro) {
		this.xcatpro = xcatpro;
	}

	/**
	 * Gets the tc mes repository.
	 *
	 * @return the tc mes repository
	 */
	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	/**
	 * Sets the tc mes repository.
	 *
	 * @param tcMesRepository the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	/**
	 * Gets the list clvdep.
	 *
	 * @return the list clvdep
	 */
	public List<String> getListClvdep() {
		return listClvdep;
	}

	/**
	 * Sets the list clvdep.
	 *
	 * @param listClvdep the new list clvdep
	 */
	public void setListClvdep(List<String> listClvdep) {
		this.listClvdep = listClvdep;
	}

	/**
	 * Gets the catdep repository.
	 *
	 * @return the catdep repository
	 */
	public CatdepRepository getCatdepRepository() {
		return catdepRepository;
	}

	/**
	 * Sets the catdep repository.
	 *
	 * @param catdepRepository the new catdep repository
	 */
	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		jasperReporteName = "reporte55";
		endFilename = jasperReporteName + ".pdf";

		listMes = tcMesRepository.findAll();
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}

		/*
		 * listClvdep =
		 * catdepRepository.getClvdep3ByIdsector(this.getUserDetails().getIdSector());
		 * if (!CollectionUtils.isEmpty(listClvdep)) { secretaria = listClvdep.get(0); }
		 */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parametrs = new HashMap<String, Object>();

		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		Catdep catdep = catdepRepository.getCatdepByFirst3(sector, secretaria);
		TcPeriodo periodo = periodoRepositoy.findByTipoPeriodoAndPeriodo(1, Integer.valueOf(mes));
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(mes, conctb, reporte);

		parametrs.put("sql", queryGeneratorService.queryAvancePresupuestalEgresosSecretaria(sector,
				Integer.valueOf(mes), secretaria));
		parametrs.put("anio", conctb.getAnoemp());
		parametrs.put("mesName", periodo.getDescripcion());
		parametrs.put("imageLeft", conctb.getImagePathLeft());
		parametrs.put("imageRigth", conctb.getImagePathRigth());
		parametrs.put("nombreEntidad", conctb.getNomDep());
		parametrs.put("lastDayOfMonth", getLastDayByAnoEmp(Integer.valueOf(mes), conctb.getAnoemp()));
		parametrs.put("nameSecretaria", catdep.getNomdep());
		parametrs.put("claveSecretaria", catdep.getClvdep());
		parametrs.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parametrs;

	}

	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {

			Catdep catdep = catdepRepository.getCatdepByFirst3(this.getUserDetails().getIdSector(), secretaria);
			if (null != catdep) {
				PrimeFaces.current().executeScript(DOWNLOAD_XLS);
			} else {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Info!", "La Secretaría es incorrecta");
			}
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {

			Catdep catdep = catdepRepository.getCatdepByFirst3(this.getUserDetails().getIdSector(), secretaria);
			if (null != catdep) {
				this.createFilePdfInline();
			} else {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Info!", "La Secretaría es incorrecta");
			}
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

	public Catdep getCatdep() {
		return catdep;
	}

	public void setCatdep(Catdep catdep) {
		this.catdep = catdep;
	}

	public QueryGeneratorService getQueryGeneratorService() {
		return queryGeneratorService;
	}

	public void setQueryGeneratorService(QueryGeneratorService queryGeneratorService) {
		this.queryGeneratorService = queryGeneratorService;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
