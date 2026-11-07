package com.gem.sistema.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc
/**
 * Anotacion para crear por reflexion un archivo de texto plano .
 *
 * @author Juan Carlos Pedraza Alcala
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ColumnFile {
	
	/**
	 * Indica el numero de la columna .
	 *
	 * @return numero de la columna
	 */
	public int indexColumn() default 0;
	
	
}

