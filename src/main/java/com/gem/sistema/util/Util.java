package com.gem.sistema.util;

import static com.gem.sistema.util.Constants.ZERO;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import com.gem.sistema.annotation.IgnoredQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class Util.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@Component
public class Util {	

	/**
	 *  Constante para utilizar el log de la aplicacion.
	 *
	 * @param field the field
	 * @return true, if is field ignored
	 */
	//private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);		
	
	/**
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isFieldIgnored(final Field field) {
		boolean result = Boolean.FALSE;		
		final Annotation[] annotations = field.getAnnotations();		
		if(ArrayUtils.isNotEmpty(annotations)) {			
			for(final Annotation value: annotations) {
				if(value instanceof IgnoredQuery) {
					result = Boolean.TRUE;
					break;
				}
			}			
		}
		return result;
	}	
	
	/**
	 * Fill zeros to right.
	 *
	 * @param value the value
	 * @param length the length
	 * @return the string
	 */
	public static String fillZerosToRight(final String value, final int length) {
		final StringBuilder result = new StringBuilder(value);		
		for(int index = ZERO; index < length - value.length(); index++) {
			result.append(ZERO);
		}		
		return result.toString();
	}
		
	
}
