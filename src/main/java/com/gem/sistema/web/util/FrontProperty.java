package com.gem.sistema.web.util;

import org.apache.deltaspike.core.api.config.ConfigResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/*
 * Clase para obtener las descripciones del archivo apache-deltaspike.properties,
 * para la capa Web.
 */

/**
 * The Class FrontProperty.
 */
public final class FrontProperty {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FrontProperty.class);
	
	/**
	 * Gets the property value.
	 *
	 * @param key the key
	 * @return the property value
	 */
	public static String getPropertyValue(String key){
		String val = ConfigResolver.getPropertyValue(key);
		LOGGER.debug("Key={},Value=[{}]", key, val);
		return val;
	}
	
}
