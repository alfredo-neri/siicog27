package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.collections4.CollectionUtils;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class RF009213MB.
 */
@ManagedBean(name = "rF009213MB")
@ViewScoped
public class RF009213MB extends BaseDirectReport {

	/** The mes. */
	private String mes;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The tcmes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcmesRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;
	
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

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
	 * @param mes
	 *            the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
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
	 * Sets the list mes.
	 *
	 * @param listMes
	 *            the new list mes
	 */
	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
	}

	/**
	 * Gets the tcmes repository.
	 *
	 * @return the tcmes repository
	 */
	public TcMesRepository getTcmesRepository() {
		return tcmesRepository;
	}

	/**
	 * Sets the tcmes repository.
	 *
	 * @param tcmesRepository
	 *            the new tcmes repository
	 */
	public void setTcmesRepository(TcMesRepository tcmesRepository) {
		this.tcmesRepository = tcmesRepository;
	}

	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	/**
	 * Sets the firmas repository.
	 *
	 * @param firmasRepository
	 *            the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
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

		jasperReporteName = "RF009_2_1_3";
		endFilename = jasperReporteName + ".pdf";

		// llenar la lista
		listMes = tcmesRepository.findAll();

		// se inicializa las listas
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());

		parameters.put("mesName", tcmesRepository.findByMes(mes).getDescripcion());
		parameters.put("municipio", this.getUserDetails().getMunicipio());
		parameters.put("imagePath", this.getUserDetails().getPathImgCab1());
		parameters.put("anio", conctb.getAnoemp());
		parameters.put("lastDay", getLastDay(Integer.valueOf(mes)));
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F27.getValue());
		parameters.put("firmaP1", firma.getPuesto().getPuesto());
		parameters.put("firmaN1", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F05.getValue());
		parameters.put("firmaP2", firma.getPuesto().getPuesto());
		parameters.put("firmaN2", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F03.getValue());
		parameters.put("firmaP3", firma.getPuesto().getPuesto());
		parameters.put("firmaN3", firma.getNombre());
		parameters.put("query", this.generateQuery(Integer.valueOf(mes), this.getUserDetails().getIdSector()));

		return parameters;
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

	/**
	 * Generate query.
	 *
	 * @param mes
	 *            the mes
	 * @param idSector
	 *            the id sector
	 * @return the string
	 */
	public String generateQuery(Integer mes, Integer idSector) {

		StringBuilder sQuery = new StringBuilder();
		StringBuilder sAmpli = new StringBuilder();
		StringBuilder sRedu = new StringBuilder();
		StringBuilder sEjpa = new StringBuilder();
		StringBuilder sEjxpa = new StringBuilder();
		StringBuilder sToeje = new StringBuilder();
		StringBuilder sCompro = new StringBuilder();

		String sAuto = " SUM(PA.AUTO1_1) + SUM(PA.AUTO1_2) + SUM(PA.AUTO1_3) + SUM(PA.AUTO1_4) + SUM(PA.AUTO1_5) + SUM(PA.AUTO1_6) + SUM(PA.AUTO1_7) + SUM(PA.AUTO1_8) + SUM(PA.AUTO1_9) + SUM(PA.AUTO1_10) + SUM(PA.AUTO1_11) + SUM(PA.AUTO1_12) AUTO1,";
		String foot = " NA.NOMGAS, DA.NOMBRE FROM PASO AS PA, NATGAS AS NA, CATDAA AS DA WHERE PA.IDSECTOR = NA.IDSECTOR AND PA.PARTIDA = NA.CLVGAS AND SUBSTR(PA.CLAVE,4,3) = DA.CLAVE AND PA.IDSECTOR="
				+ idSector
				+ " GROUP BY SUBSTR(PA.CLAVE,4,3), PARTIDA, NOMGAS, NOMBRE ORDER BY SUBSTR(PA.CLAVE,4,3),PA.PARTIDA";

		sQuery.append("SELECT * FROM ( SELECT SUBSTR(PA.CLAVE,4,3) DEP, PA.PARTIDA,");

		for (int i = 1; i <= mes; i++) {
			sAmpli.append(" SUM(PA.AMPLI1_" + i + ") +");
			sRedu.append(" SUM(PA.REDU1_" + i + ") +");
			sEjpa.append(" SUM(PA.EJPA1_" + i + ") +");
			sEjxpa.append(" SUM(PA.EJXPA1_" + i + ") +");
			sToeje.append(" SUM(PA.TOEJE1_" + i + ") +");
			sCompro.append(" SUM(PA.COMPRO1_" + i + ") +");
		}

		sQuery.append(sAuto).append(sAmpli.substring(0, sAmpli.length() - 1)).append(" AMPLI1, ")
				.append(sRedu.substring(0, sRedu.length() - 1)).append(" REDU1, ")
				.append(sEjpa.substring(0, sEjpa.length() - 1)).append(" EJPA1, ")
				.append(sEjxpa.substring(0, sEjxpa.length() - 1)).append(" EJXPA1, ")
				.append(sToeje.substring(0, sToeje.length() - 1)).append(" TOEJE, ")
				.append(sCompro.substring(0, sCompro.length() - 1)).append(" COMPRO1, ").append(foot).append(")T1");
		// .append(")T1 WHERE AUTO1 <> 0 OR AMPLI1 <> 0 OR REDU1 <> 0 OR EJPA1
		// <> 0 OR EJXPA1 <> 0 OR TOEJE <> 0 OR COMPRO1 <> 0");

		return sQuery.toString();
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(Long.valueOf(mes).intValue(), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Long.valueOf(mes).intValue(), null,
					this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Long.valueOf(mes).intValue(), null,
					this.getUserDetails().getIdSector());
			this.createFilePdfInline();
			;
		}
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
