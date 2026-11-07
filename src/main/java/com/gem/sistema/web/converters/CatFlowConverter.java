package com.gem.sistema.web.converters;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

import com.gem.sistema.business.domain.Catflu;
import com.gem.sistema.web.bean.CatalogMaintenanceFlowMB;

// TODO: Auto-generated Javadoc
/**
 * The Class CatFlowConverter.
 */
@FacesConverter("catFlowConverter")
public class CatFlowConverter implements Converter {

	/* (non-Javadoc)
	 * @see jakarta.faces.convert.Converter#getAsObject(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				CatalogMaintenanceFlowMB service = (CatalogMaintenanceFlowMB) fc
						.getExternalContext().getApplicationMap().get("catalogMaintenanceFlowMB");
				return service.getList().get(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
			}
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see jakarta.faces.convert.Converter#getAsString(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Catflu) object).getId());
		} else {
			return null;
		}
	}

}
