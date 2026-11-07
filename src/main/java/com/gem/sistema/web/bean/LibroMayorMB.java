package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.Map;

import static com.gem.sistema.util.UtilReport.getNameFileReport;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TcReportesFirma;

import jakarta.annotation.PostConstruct;

import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcEntidadAdministrativa;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcEntidadAdministrativaRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class AnexoEstadoSituacionFinancieraMB.
 */
@Component("libroMayorMB")
@Scope("view")
public class LibroMayorMB extends BaseDirectReport {

	private static final String CLAVE_REPORT= "RPT_CONAC_LIBRO_MAYOR";
	
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
//	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";

	@Value("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@Value("#{conctbRepository}")
	private ConctbRepository contbRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	@Value("#{tcEntidadAdministrativaRepository}")
	private TcEntidadAdministrativaRepository entidadAdministrativaRepository;
	
	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/** The mes. */
	private String mes;

	/** The cuenta inicial. */
	private Integer cuentaInicial;

	/** The cuenta final. */
	private Integer cuentaFinal;
	private List<TcMes> listMes;

	public List<TcMes> getListMes() {
		return listMes;
	}

	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
	}

	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct LibroMayorMB ");

		listMes = tcMesRepository.findAll();
		if (!CollectionUtils.isEmpty(listMes)) {
			this.mes = listMes.get(0).getMes();
		}
		jasperReporteName = "CNC_LibroMayor";
		
		cuentaInicial = 1000;
		cuentaFinal = 9130;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {

		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = contbRepository.findByIdsector(sector);
		
		parameters.put("P_SECTOR", sector);
		parameters.put("P_MES", Integer.valueOf(mes));
		parameters.put("P_IMG", sector == 1 ? conctb.getImagePathRigth() : conctb.getImagePathLeft());
		parameters.put("P_CAMPO1", conctb.getNomDep());
		parameters.put("P_CAMPO3", conctb.getAnoemp().toString());
		parameters.put("P_CUENTA_INICIAL", cuentaInicial.toString());
		parameters.put("P_CUENTA_FIN", cuentaFinal.toString());
		
		if (sector == 1) {
			
			TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);
			TcEntidadAdministrativa entidadAdministrativa = entidadAdministrativaRepository
					.findByClave(conctb.getClave().substring(0, 1));
			
			parameters.put("tipo", entidadAdministrativa.getDescripcion());
			parameters.put("noEntity", conctb.getClave().substring(1));
			parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));
			
			endFilename = getNameFileReport(mes, conctb, reporte);
			
		} else {
			
			TrPuestoFirma firma = new TrPuestoFirma();
			
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
					ConstantsClaveEnnum.CLAVE_F04.getValue());
			parameters.put("firmaL1", firma.getPuesto().getPuesto());
			parameters.put("firmaN1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
					ConstantsClaveEnnum.CLAVE_F05.getValue());
			parameters.put("firmaL2", firma.getPuesto().getPuesto());
			parameters.put("firmaN2", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L,
					ConstantsClaveEnnum.CLAVE_F27.getValue());
			parameters.put("firmaL3", firma.getPuesto().getPuesto());
			parameters.put("firmaN3", firma.getNombre());
			
			parameters.put("noFirmas", 3);
			parameters.put("tipo", StringUtils.EMPTY);
			parameters.put("noEntity", StringUtils.EMPTY);
		}

		return parameters;
	}

	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(Integer.parseInt(mes), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Integer.parseInt(mes), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Integer.parseInt(mes), null, this.getUserDetails().getIdSector());

			PrimeFaces.current().executeScript(DOWNLOAD_XLS);
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(Integer.parseInt(mes), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Integer.parseInt(mes), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Integer.parseInt(mes), null, this.getUserDetails().getIdSector());
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
	 * Gets the cuenta inicial.
	 *
	 * @return the cuenta inicial
	 */
	public Integer getCuentaInicial() {
		return cuentaInicial;
	}

	/**
	 * Sets the cuenta inicial.
	 *
	 * @param cuentaInicial the new cuenta inicial
	 */
	public void setCuentaInicial(Integer cuentaInicial) {
		this.cuentaInicial = cuentaInicial;
	}

	/**
	 * Gets the cuenta final.
	 *
	 * @return the cuenta final
	 */
	public Integer getCuentaFinal() {
		return cuentaFinal;
	}

	/**
	 * Sets the cuenta final.
	 *
	 * @param cuentaFinal the new cuenta final
	 */
	public void setCuentaFinal(Integer cuentaFinal) {
		this.cuentaFinal = cuentaFinal;
	}

	public ConctbRepository getContbRepository() {
		return contbRepository;
	}

	public void setContbRepository(ConctbRepository contbRepository) {
		this.contbRepository = contbRepository;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public TcEntidadAdministrativaRepository getEntidadAdministrativaRepository() {
		return entidadAdministrativaRepository;
	}

	public void setEntidadAdministrativaRepository(TcEntidadAdministrativaRepository entidadAdministrativaRepository) {
		this.entidadAdministrativaRepository = entidadAdministrativaRepository;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}