/**
 * 
 */
package com.gem.sistema.load.fileupload.validators.field;

import com.gem.sistema.load.fileupload.exceptions.ValidateTokenException;
import com.gem.sistema.load.fileupload.validators.AbstractCellFieldValidator;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * <b> Clase que valida por medio de un patron. </b>
 * </p>
 */
public class PatternPermitBlankFieldValidator extends AbstractCellFieldValidator {

	/**
	 * <p>
	 * <b> Patron de validacion regexp. </b>
	 * </p>
	 */
	private String pattern;

	/**
	 * Validate.
	 *
	 * @param fields the fields
	 * @throws ValidateTokenException             .
	 * @see com.hsbc.contrac.filetransfer.fileupload.validators.FieldValidator#validate
	 *      (java.lang.Object)
	 */
	public void validate(final String[] fields) throws ValidateTokenException {
		final String contenido = getContentFromCell(fields, this.getIndex());
		if (!contenido.trim().matches(this.getPattern())) {
			throw new ValidateTokenException(String.format(this.getMessageError(), this.getFieldName()));
		}
	}

	/**
	 * Gets the pattern.
	 *
	 * @return el pattern
	 */
	public final String getPattern() {
		return this.pattern;
	}

	/**
	 * Sets the pattern.
	 *
	 * @param pattern            el pattern a establecer
	 */
	public final void setPattern(final String pattern) {
		this.pattern = pattern;
	}

}
