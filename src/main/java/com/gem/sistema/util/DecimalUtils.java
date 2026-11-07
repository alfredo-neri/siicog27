/**
 * 
 */
package com.gem.sistema.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

// TODO: Auto-generated Javadoc
/**
 * The Class DecimalUtils.
 *
 * @author g_cue
 */
public final class DecimalUtils {

	/** The df. */
	private static DecimalFormat df;
	static {
		df = new DecimalFormat("#0.00");
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
		otherSymbols.setDecimalSeparator('.');
		df.setDecimalFormatSymbols(otherSymbols);
	}

	/**
	 * Instantiates a new decimal utils.
	 */
	private DecimalUtils() {
		// Hide
	}

	/**
	 * Format double.
	 *
	 * @param toFormat the to format
	 * @return the string
	 */
	public static final String formatDouble(Double toFormat) {
		return DecimalUtils.df.format(toFormat.doubleValue());
	}
}
