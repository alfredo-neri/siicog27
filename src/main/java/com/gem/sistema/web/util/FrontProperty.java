package com.gem.sistema.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Clase para obtener las descripciones del archivo front.properties, para la capa Web.
 *
 * En difem26 se leia con DeltaSpike (META-INF/apache-deltaspike.properties). Se conserva el mismo
 * orden de resolucion que DeltaSpike: propiedad de sistema, variable de entorno y por ultimo archivo.
 */

/**
 * The Class FrontProperty.
 */
public final class FrontProperty {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FrontProperty.class);

	/** The Constant RESOURCE. */
	private static final String RESOURCE = "front.properties";

	/** The Constant PROPERTIES. */
	private static final Properties PROPERTIES = load();

	private FrontProperty() {
	}

	/**
	 * Gets the property value.
	 *
	 * @param key the key
	 * @return the property value
	 */
	public static String getPropertyValue(String key) {
		String val = System.getProperty(key);
		if (val == null) {
			val = System.getenv(key);
		}
		if (val == null) {
			val = PROPERTIES.getProperty(key);
		}
		LOGGER.debug("Key={},Value=[{}]", key, val);
		return val;
	}

	private static Properties load() {
		Properties properties = new Properties();
		try (InputStream in = FrontProperty.class.getClassLoader().getResourceAsStream(RESOURCE)) {
			if (in == null) {
				LOGGER.warn("No se encontro {} en el classpath", RESOURCE);
				return properties;
			}
			properties.load(new InputStreamReader(in, StandardCharsets.UTF_8));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		return properties;
	}
}
