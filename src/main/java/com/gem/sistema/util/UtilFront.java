package com.gem.sistema.util;

import static com.gem.sistema.util.Constants.PREFIX_SET;
import static com.gem.sistema.util.Util.isFieldIgnored;
import static com.gem.sistema.util.UtilJPA.obtainMehodName;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class UtilFront.
 *
 * @author Juan Carlos Pedraza Alcala
 */
public class UtilFront {

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilFront.class);

	/**
	 * Genera un mensaje para el front.
	 *
	 * @param severiry the severiry
	 * @param summary the summary
	 * @param message the message
	 */
	public static void generateNotificationFront(final Severity severiry, final String summary, final String message) {
		final FacesMessage msg = new FacesMessage(severiry, summary, message);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Clear fields.
	 *
	 * @param entity the entity
	 * @return the map
	 */
	public static Map<String, Object> clearFields(final Object entity) {
		final Map<String, Object> result = new HashMap<String, Object>();
		final Object[] args = new Object[] { null };

		for (final Field field : entity.getClass().getDeclaredFields()) {
			try {
				if (BooleanUtils.negate(isFieldIgnored(field))) {
					entity.getClass().getMethod(obtainMehodName(PREFIX_SET, field.getName()), field.getType())
							.invoke(entity, args);
				}
			} catch (final Exception ignored) {
				LOGGER.error(String.format(":: Error al limpiar los campos [%1$s]", field.getName()), ignored);
			}
		}

		return result;
	}

}
