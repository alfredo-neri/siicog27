package com.gem.sistema.util;

import java.util.regex.Pattern;

/** Regex´s para validar formato de campos como correo, curp, rfc, etc. */
public class Regex {
	/** Regex para validar correos */
	public static final String EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	/** Regex para validar curp */
	public static final String CURP = "[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])"
			+ "[HM]{1}(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)"
			+ "[B-DF-HJ-NP-TV-Z]{3}" + "[0-9A-Z]{1}[0-9]{1}$";;
	/** Regex para validar RFC */
	public static final String RFC = "^([A-ZÑ\\x26]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])([A-Z]|[0-9]){2}([A]|[0-9]){1})?$";
	/**
	 * Reges para horas en formato de 12 horas con ejemplo 12:00AM
	 */
	public static final String HORA = "^((0[0-9]{1}|1[0-2]{1}):([0-5][0-9]) ?([AaPp][Mm]))$";
	public static final String ESPACIO_PUNTO_COMA = "[., 	]";
	public static final String EMPTY = "";
	/** Valida que la cadena solo exista numeros y comas */
	public static final String NUMEROS_COMAS = "^[0-9,]+$";

	/**
	 * Valida si un valor coincide con la regex proporcionada
	 * 
	 */
	public static Boolean isValid(String regex, String value) {
		try {
			if (value == null || regex == null) {
				return false;
			}
			return Pattern.compile(regex).matcher(value).find();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Toma 2 strings los convierte a minusculas quita espacios, comas y puntos,
	 * compara si sin iguales
	 */
	public static Boolean equalStringName(String value1, String value2) {
		return value1.replaceAll(ESPACIO_PUNTO_COMA, EMPTY).toLowerCase()
				.equals(value2.replaceAll(ESPACIO_PUNTO_COMA, EMPTY).toLowerCase());
	}
}
