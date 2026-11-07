package com.gem.sistema.web.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.web.bean.CatalogProgramsMB;

// TODO: Auto-generated Javadoc
/**
 * The Class CatProgramConverter.
 */
@FacesConverter("catProgramConverter")
public class CatProgramConverter implements Converter {

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if(value != null && value.trim().length() > 0) {
            try {
            	CatalogProgramsMB service = (CatalogProgramsMB) fc.getExternalContext().getApplicationMap().get("catalogProgramsMB");
                return service.getList().get(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if(object != null) {
            return String.valueOf(((Xcatpro) object).getId());
        }
        else {
            return null;
        }
	}

}
