package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
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
import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.PolideRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class ConsultaCambioConceptoMB.
 */
@Component("consultaCambioConceptoMB")
@Scope("view")
public class ConsultaCambioConceptoMB extends BaseDirectReport {

	/** The firmas repository. */
	@Value("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The polide repository. */
	@Value("#{polideRepository}")
	private PolideRepository polideRepository;

	/** The polien repository. */
	@Value("#{polienRepository}")
	private PolienRepository polienRepository;

	/** The polide. */
	private Polide polide;

	/** The anopol. */
	private Date anopol;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		Firmas firmas = firmasRepository.findAllByIdsector(getUserDetails().getIdSector());

		polide = new Polide();
		anopol = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, firmas.getCampo3());
		anopol = calendar.getTime();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		return null;
	}

	/**
	 * Obtener polide.
	 */
	public void obtenerPolide() {
		if (validarCamposVacios() && tienePermiso()) {
			Polide polideFound = polideRepository.findByIdsectorAndTippolAndConpolAndRenpolAndMespolAndAnopol(
					getUserDetails().getIdSector(), polide.getTippol(), polide.getConpol(), polide.getRenpol(),
					polide.getMespol(), polide.getAnopol());
			if (Objects.isNull(polideFound)) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
						"No existe esta poliza o renglon, verifique.");
				FacesContext.getCurrentInstance().addMessage(null, message);
				PrimeFaces.current().executeScript("PF('blockUIWidget').unblock();");
			} else {
				polide = polideFound;
			}
			PrimeFaces.current().executeScript("PF('blockUIWidget').unblock();");
		}
	}

	/**
	 * Guardar cambios.
	 */
	public void guardarCambios() {
		if (validarCamposVacios() && tienePermiso() && Objects.nonNull(polide.getConcep())
				&& StringUtils.isNotBlank(polide.getConcep())) {
			PrimeFaces.current().executeScript("PF('blockUIWidget').unblock();");
			polideRepository.save(polide);
			polide = new Polide();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY,
					"Concepto modificado con éxito.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			if (!Objects.nonNull(polide.getConcep()) && !StringUtils.isNotBlank(polide.getConcep())
					&& validarCamposVacios()) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
						"Favor de llenar el campo Concepto.");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
						"No se puede actualizar el campo en POLIDE.");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			PrimeFaces.current().executeScript("PF('blockUIWidget').unblock();");
		}
	}

	/**
	 * Tiene permiso.
	 *
	 * @return true, if successful
	 */
	private boolean tienePermiso() {
		Polien polien = polienRepository.findFirstByMespolAndAnopolAndConpolAndTippolAndIdsectorOrderById(
				polide.getMespol(), polide.getAnopol(), polide.getConpol(), polide.getTippol(),
				getUserDetails().getIdSector());
		boolean tienePermiso = Objects.nonNull(polien) && polien.getCappol().equals(getUserDetails().getUsername());
		if (!tienePermiso) {
			String msg = Objects.nonNull(polien) ? "Usuario no autorizado para modificar póliza."
					: "No existe la póliza.";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY, msg);
			FacesContext.getCurrentInstance().addMessage(null, message);
			PrimeFaces.current().executeScript("PF('blockUIWidget').unblock();");
		}
		return tienePermiso;
	}

	/**
	 * Validar campos vacios.
	 *
	 * @return true, if successful
	 */
	private boolean validarCamposVacios() {
		boolean isValid = StringUtils.isNotBlank(polide.getTippol()) && Objects.nonNull(polide.getConpol())
				&& polide.getConpol() > 0 && Objects.nonNull(polide.getRenpol())
				&& polide.getRenpol().compareTo(BigDecimal.ZERO) > 0 && Objects.nonNull(polide.getMespol())
				&& polide.getMespol() > 0 && Objects.nonNull(polide.getAnopol()) && polide.getAnopol() > 0;
		if (!isValid) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"Por favor debe llenar todos los campos.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			PrimeFaces.current().executeScript("PF('blockUIWidget').unblock();");
		}
		return isValid;
	}

	/**
	 * Gets the last year.
	 *
	 * @return the last year
	 */
	public String getLastYear() {
		return String.valueOf((LocalDate.now()).getYear());
	}

	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	// getters and setters
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
	 * Gets the polide repository.
	 *
	 * @return the polide repository
	 */
	public PolideRepository getPolideRepository() {
		return polideRepository;
	}

	/**
	 * Sets the polide repository.
	 *
	 * @param polideRepository the new polide repository
	 */
	public void setPolideRepository(PolideRepository polideRepository) {
		this.polideRepository = polideRepository;
	}

	/**
	 * Gets the polien repository.
	 *
	 * @return the polien repository
	 */
	public PolienRepository getPolienRepository() {
		return polienRepository;
	}

	/**
	 * Sets the polien repository.
	 *
	 * @param polienRepository the new polien repository
	 */
	public void setPolienRepository(PolienRepository polienRepository) {
		this.polienRepository = polienRepository;
	}

	/**
	 * Gets the polide.
	 *
	 * @return the polide
	 */
	public Polide getPolide() {
		return polide;
	}

	/**
	 * Sets the polide.
	 *
	 * @param polide the new polide
	 */
	public void setPolide(Polide polide) {
		this.polide = polide;
	}

	/**
	 * Gets the anopol.
	 *
	 * @return the anopol
	 */
	public Date getAnopol() {
		return anopol;
	}

	/**
	 * Sets the anopol.
	 *
	 * @param anopol the new anopol
	 */
	public void setAnopol(Date anopol) {
		this.anopol = anopol;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(anopol);
		polide.setAnopol(calendar.get(Calendar.YEAR));
	}

}
