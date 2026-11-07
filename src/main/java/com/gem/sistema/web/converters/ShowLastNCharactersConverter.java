/**
 * 
 */
package com.gem.sistema.web.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class ShowLastNCharactersConverter.
 *
 * @author gauss
 */
@FacesConverter("com.gem.sistema.web.converters.ShowLastNCharactersConverter")
public class ShowLastNCharactersConverter implements Converter {

	/* (non-Javadoc)
	 * @see jakarta.faces.convert.Converter#getAsObject(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see jakarta.faces.convert.Converter#getAsString(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		return value.toString();
	}

}
