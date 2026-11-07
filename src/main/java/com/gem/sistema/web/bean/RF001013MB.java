package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDay;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class RF001013MB.
 */
@ManagedBean
@ViewScoped
public class RF001013MB extends BaseDirectReport {

	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";

	/** The mes. */
	private Integer mes;

	/** The conctb. */
	private Conctb conctb;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

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

	/**
	 * 
	 */
	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RF001013MB ");
		// reportId = 2;
		// tcReporte = reportesRepository.findOne(reportId);
		jasperReporteName = "m_edopingegr";
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
		TrPuestoFirma firma = new TrPuestoFirma();

		paramsReport.put("USUARIO", getUserDetails().getUsername());
		paramsReport.put("MES", mes);
		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("municipioName", conctb.getNomDep());
		paramsReport.put("mesName",
				tcMesRepository.findByMes(StringUtils.leftPad(mes.toString(), 2, "0")).getDescripcion());
		paramsReport.put("lastDayOfMonth", getLastDay(mes));
		paramsReport.put("imagePath", idSector==1? conctb.getImagePathRigth(): conctb.getImagePathLeft());
		if (idSector == 1) {
			switch (conctb.getClave().substring(0, 1)) {
		      case "0":
		    	paramsReport.put("pNoFirmas",3);
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
		            ConstantsClaveEnnum.CLAVE_F01.getValue());
		        paramsReport.put("firmaP1", firma.getPuesto().getPuesto());
		        paramsReport.put("firmaN1", firma.getNombre());
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
		            ConstantsClaveEnnum.CLAVE_F03.getValue());
		        paramsReport.put("firmaP2", firma.getPuesto().getPuesto());
		        paramsReport.put("firmaN2", firma.getNombre());
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
		            ConstantsClaveEnnum.CLAVE_F02.getValue());
		        paramsReport.put("firmaP3", firma.getPuesto().getPuesto());
		        paramsReport.put("firmaN3", firma.getNombre());
		        break;
		      case "2": // ODAS
		    	  paramsReport.put("pNoFirmas",3);
		    	firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
		            ConstantsClaveEnnum.CLAVE_F10.getValue());
		        paramsReport.put("firmaP1", firma.getPuesto().getPuesto());
		        paramsReport.put("firmaN1", firma.getNombre());
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
		            ConstantsClaveEnnum.CLAVE_F11.getValue());
		        paramsReport.put("firmaP2", firma.getPuesto().getPuesto());
		        paramsReport.put("firmaN2", firma.getNombre());
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
		            ConstantsClaveEnnum.CLAVE_F12.getValue());
		        paramsReport.put("firmaP3", firma.getPuesto().getPuesto());
		        paramsReport.put("firmaN3", firma.getNombre());
		        break;
		      case "3":
		    	  paramsReport.put("pNoFirmas",3);
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
		            ConstantsClaveEnnum.CLAVE_F07.getValue());
		        paramsReport.put("firmaP1", firma.getPuesto().getPuesto());
		        paramsReport.put("firmaN1", firma.getNombre());
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
		            ConstantsClaveEnnum.CLAVE_F08.getValue());
		        paramsReport.put("firmaP2", firma.getPuesto().getPuesto());
		        paramsReport.put("firmaN2", firma.getNombre());
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
		            ConstantsClaveEnnum.CLAVE_F09.getValue());
		        paramsReport.put("firmaP3", firma.getPuesto().getPuesto());
		        paramsReport.put("firmaN3", firma.getNombre());
		        break;
		      case "4":
		    	  paramsReport.put("pNoFirmas",2);
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
		            ConstantsClaveEnnum.CLAVE_F20.getValue());
		        paramsReport.put("firmaP1", firma.getPuesto().getPuesto());
		        paramsReport.put("firmaN1", firma.getNombre());
		        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
		            ConstantsClaveEnnum.CLAVE_F21.getValue());
		        paramsReport.put("firmaP3", firma.getPuesto().getPuesto());
		        paramsReport.put("firmaN3", firma.getNombre());
		        break;
		      }

		} else {
			paramsReport.put("pNoFirmas",3);
			 firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
			            ConstantsClaveEnnum.CLAVE_F04.getValue());
			        paramsReport.put("firmaP1", firma.getPuesto().getPuesto());
			        paramsReport.put("firmaN1", firma.getNombre());
			        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
			            ConstantsClaveEnnum.CLAVE_F05.getValue());
			        paramsReport.put("firmaP2", firma.getPuesto().getPuesto());
			        paramsReport.put("firmaN2", firma.getNombre());
			        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
			            ConstantsClaveEnnum.CLAVE_F27.getValue());
			        paramsReport.put("firmaP3", firma.getPuesto().getPuesto());
			        paramsReport.put("firmaN3", firma.getNombre());
		}
		
		return paramsReport;
	}

	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());

			RequestContext.getCurrentInstance().execute(DOWNLOAD_XLS);
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
			;
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

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
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

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

}
