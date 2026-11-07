package com.gem.sistema.web.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Fuentef;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.dto.TotalMesesDTO;
import com.gem.sistema.business.dto.TotalesDTO;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.FuentefRepository;
import com.gem.sistema.business.service.catalogos.GenerateTotaleService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

import static com.roonin.utils.UtilDate.getYear;
import static com.roonin.utils.UtilDate.getDateOfSystem;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteRF0092164MB.
 */
@ManagedBean(name = "reporteRF0092164MB")
@ViewScoped
public class ReporteRF0092164MB extends BaseDirectReport {

	/** The fuente F. */
	private String fuenteF;

	/** The list fuente. */
	private List<Fuentef> listFuente;

	/** The firmas. */
	private Firmas firmas;

	/** The total meses DTO. */
	TotalMesesDTO totalMesesDTO;

	/** The fuente F repository. */
	@ManagedProperty("#{fuentefRepository}")
	private FuentefRepository fuenteFRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The generate totale service. */
	@ManagedProperty("#{generateTotaleService}")
	private GenerateTotaleService generateTotaleService;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Gets the generate totale service.
	 *
	 * @return the generate totale service
	 */
	public GenerateTotaleService getGenerateTotaleService() {
		return generateTotaleService;
	}

	/**
	 * Sets the generate totale service.
	 *
	 * @param generateTotaleService the new generate totale service
	 */
	public void setGenerateTotaleService(GenerateTotaleService generateTotaleService) {
		this.generateTotaleService = generateTotaleService;
	}

	/**
	 * Gets the fuente F.
	 *
	 * @return the fuente F
	 */
	public String getFuenteF() {
		return fuenteF;
	}

	/**
	 * Sets the fuente F.
	 *
	 * @param fuenteF the new fuente F
	 */
	public void setFuenteF(String fuenteF) {
		this.fuenteF = fuenteF;
	}

	/**
	 * Gets the list fuente.
	 *
	 * @return the list fuente
	 */
	public List<Fuentef> getListFuente() {
		return listFuente;
	}

	/**
	 * Sets the list fuente.
	 *
	 * @param listFuente the new list fuente
	 */
	public void setListFuente(List<Fuentef> listFuente) {
		this.listFuente = listFuente;
	}

	/**
	 * Gets the firmas.
	 *
	 * @return the firmas
	 */
	public Firmas getFirmas() {
		return firmas;
	}

	/**
	 * Sets the firmas.
	 *
	 * @param firmas the new firmas
	 */
	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
	}

	/**
	 * Gets the fuente F repository.
	 *
	 * @return the fuente F repository
	 */
	public FuentefRepository getFuenteFRepository() {
		return fuenteFRepository;
	}

	/**
	 * Sets the fuente F repository.
	 *
	 * @param fuenteFRepository the new fuente F repository
	 */
	public void setFuenteFRepository(FuentefRepository fuenteFRepository) {
		this.fuenteFRepository = fuenteFRepository;
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
	 * @param firmasRepository the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "reporte_RF0092164";
		endFilename = jasperReporteName + ".pdf";
		listFuente = fuenteFRepository.getByIdSector(this.getUserDetails().getIdSector());
	}

	/** The parameters. */
	Map<String, Object> parameters;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		parameters = new HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();

		totalMesesDTO = this.generateTotaleService.generateTotales(this.getUserDetails().getIdSector(),
				fuenteF.substring(0, 3));
		parameters.put("programa", fuenteF);

		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F01.getValue());
		parameters.put("firmaP1", firma.getPuesto().getPuesto());
		parameters.put("firmaN1", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F02.getValue());
		parameters.put("firmaP2", firma.getPuesto().getPuesto());
		parameters.put("firmaN2", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F03.getValue());
		parameters.put("firmaP3", firma.getPuesto().getPuesto());
		parameters.put("firmaN3", firma.getNombre());

		parameters.put("pathImg", this.getUserDetails().getPathImgCab2());
		parameters.put("idSector", this.getUserDetails().getIdSector());
		parameters.put("municipio", this.getUserDetails().getMunicipio());
		parameters.put("fecha", getDateOfSystem());
		parameters.put("enero", totalMesesDTO.getEnero());
		parameters.put("febrero", totalMesesDTO.getFebrero());
		parameters.put("marzo", totalMesesDTO.getMarzo());
		parameters.put("abril", totalMesesDTO.getAbril());
		parameters.put("mayo", totalMesesDTO.getMayo());
		parameters.put("junio", totalMesesDTO.getJunio());
		parameters.put("julio", totalMesesDTO.getJulio());
		parameters.put("agosto", totalMesesDTO.getAgosto());
		parameters.put("septiembre", totalMesesDTO.getSeptiembre());
		parameters.put("octubre", totalMesesDTO.getOctubre());
		parameters.put("noviembre", totalMesesDTO.getNoviembre());
		parameters.put("diciembre", totalMesesDTO.getDiciembre());
		parameters.put("total", totalMesesDTO.getTotal());
		parameters.put("anio", conctb.getAnoemp());
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

}
