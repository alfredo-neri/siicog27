package com.gem.sistema.web.converters;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.business.domain.AbstractEntity;


// TODO: Auto-generated Javadoc
/**
 * The Class EntityConverter.
 */
@FacesConverter("entityConverter")
public class EntityConverter implements Converter {

/** The Constant key. */
private static final String key = "entityConverter";

/** The Constant empty. */
private static final String empty = "";

/**
 * Gets the view map.
 *
 * @param context the context
 * @return the view map
 */
private Map<String, Object> getViewMap(FacesContext context) {
    Map<String, Object> viewMap = context.getViewRoot().getViewMap();
    @SuppressWarnings({ "unchecked", "rawtypes" })
    Map<String, Object> idMap = (Map) viewMap.get(key);
    if (idMap == null) {
        idMap = new HashMap<String, Object>();
        viewMap.put(key, idMap);
    }
    return idMap;
}

/* (non-Javadoc)
 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
 */
@Override
public Object getAsObject(FacesContext context, UIComponent c, String value) {
    if (value.isEmpty()) {
        return null;
    }
    return getViewMap(context).get(value);
}

/* (non-Javadoc)
 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
 */
@Override
public String getAsString(FacesContext context, UIComponent c, Object value) {
	String isBlank = "N";
	try {
		isBlank = (String) value;
	} catch (Exception e) {
		
	}
    if (Objects.isNull(value) || StringUtils.isBlank(isBlank)) {
        return empty;
    }
    String id = String.valueOf(((AbstractEntity) value).getId());
    getViewMap(context).put(id, value);
    return id;
}
}