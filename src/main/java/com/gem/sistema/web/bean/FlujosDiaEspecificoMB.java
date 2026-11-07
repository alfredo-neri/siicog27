package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class FlujosDiaEspecificoMB.
 */
@Component("flujosDiaEspecificoMB")
@Scope("view")
public class FlujosDiaEspecificoMB extends BaseDirectReport {

	/** The firmas repository. */
	@Value("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	/** The fecha. */
	private Date fecha;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct FlujosDiaEspecificoMB ");
		jasperReporteName = "FlujosDiaEspecifico";
		endFilename = jasperReporteName + ".pdf";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {

		Integer idSector = this.getUserDetails().getIdSector();
		Firmas firmas = firmasRepository.findAllByIdsector(getUserDetails().getIdSector());
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		TrPuestoFirma firma = new TrPuestoFirma();
		parameters.put("P_SECTOR", getUserDetails().getIdSector());
		parameters.put("P_CAMPO1", firmas.getCampo1());
		parameters.put("P_FECHA", new java.sql.Date(fecha.getTime()));
		parameters.put("imagePath", this.getUserDetails().getPathImgCab1());
		parameters.put("entidadRfc", firmas.getCampo2());

		if (idSector == 2) {
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F04.getValue());
			parameters.put("firmaP1", firma.getPuesto().getPuesto());
			parameters.put("firmaN1", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F05.getValue());
			parameters.put("firmaP2", firma.getPuesto().getPuesto());
			parameters.put("firmaN2", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F27.getValue());
			parameters.put("firmaP3", firma.getPuesto().getPuesto());
			parameters.put("firmaN3", firma.getNombre());
		} else {
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F27.getValue());
			parameters.put("firmaP1", firma.getPuesto().getPuesto());
			parameters.put("firmaN1", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F28.getValue());
			parameters.put("firmaP2", firma.getPuesto().getPuesto());
			parameters.put("firmaN2", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F05.getValue());
			parameters.put("firmaP3", firma.getPuesto().getPuesto());
			parameters.put("firmaN3", firma.getNombre());
			firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
					ConstantsClaveEnnum.CLAVE_F03.getValue());
			parameters.put("firmaP4", firma.getPuesto().getPuesto());
			parameters.put("firmaN4", firma.getNombre());
		}

		return parameters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
	}

	/**
	 * Creates the file pdf inline validate.
	 */
	public void createFilePdfInlineValidate() {
		if (Objects.nonNull(fecha)) {
			createFilePdfInline();
			PrimeFaces.current().executeScript("PF('blockUIWidget').unblock();");
			PrimeFaces.current()
					.executeScript("$(PrimeFaces.escapeClientId('form1:panelReporte')).css('height', '45em');");
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"Seleccione una fecha por favor.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			PrimeFaces.current().executeScript("PF('blockUIWidget').unblock();");
		}
	}

	/**
	 * Creates the file txt inline validate.
	 */
	public void createFileTxtInlineValidate() {
		if (Objects.nonNull(fecha)) {
			PrimeFaces.current().executeScript("$(PrimeFaces.escapeClientId('form1:donwloadTxt')).click();");
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"Seleccione una fecha por favor.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	/**
	 * Creates the file xls inline validate.
	 */
	public void createFileXlsInlineValidate() {
		if (Objects.nonNull(fecha)) {
			PrimeFaces.current().executeScript("$(PrimeFaces.escapeClientId('form1:donwloadXls')).click();");
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"Seleccione una fecha por favor.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
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
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha the new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
