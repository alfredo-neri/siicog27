package com.gem.sistema.web.util;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.service.catalogos.ConsultaPolizaSirveImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class ImagesPolizas.
 */
@ManagedBean(name = "imagesPoliza")
@RequestScoped
public class ImagesPolizas {

	/** The consulta poliza sirve impl. */
	@ManagedProperty("#{consultaPolizaSirveImpl}")
	private ConsultaPolizaSirveImpl consultaPolizaSirveImpl;

	/**
	 * Gets the image.
	 *
	 * @return the image
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public StreamedContent getImage() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

//		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			// So, we're rendering the view. Return a stub StreamedContent so
			// that it will generate right URL.
//			return new DefaultStreamedContent();
//		} else {
			// So, browser is requesting the image. Return a real
			// StreamedContent with the image bytes.

			String id = context.getExternalContext().getRequestParameterMap().get("id");
//			PolizaMantenimientoMB polizaMantoMB = (PolizaMantenimientoMB) context.getExternalContext().getSessionMap()
//					.get("polizaMantenimientoMB");
			if (id != null && StringUtils.isNumeric(id) && Long.valueOf(id) > 0) {
				return this.consultaPolizaSirveImpl.findPolicy(Long.valueOf(id));
			} else {
				return new DefaultStreamedContent();
			}
//		}
	}

	/**
	 * Gets the consulta poliza sirve impl.
	 *
	 * @return the consultaPolizaSirveImpl
	 */
	public ConsultaPolizaSirveImpl getConsultaPolizaSirveImpl() {
		return consultaPolizaSirveImpl;
	}

	/**
	 * Sets the consulta poliza sirve impl.
	 *
	 * @param consultaPolizaSirveImpl            the consultaPolizaSirveImpl to set
	 */
	public void setConsultaPolizaSirveImpl(ConsultaPolizaSirveImpl consultaPolizaSirveImpl) {
		this.consultaPolizaSirveImpl = consultaPolizaSirveImpl;
	}

}
