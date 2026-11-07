/**
 * 
 */
package com.gem.sistema.load.fileupload.validators.field;

import java.text.DecimalFormat;
import java.text.ParseException;

import com.gem.sistema.load.fileupload.exceptions.ValidateTokenException;
import com.gem.sistema.load.fileupload.validators.AbstractCellFieldValidator;





// TODO: Auto-generated Javadoc
/**
 * <p>
 * <b> Clase que valida el campo de tipo decimal. </b>
 * </p>
 */
public class DecimalPatternFieldValidator extends AbstractCellFieldValidator {

    /**
     * <p>
     * <b> Patron de decimales. </b>
     * </p>
     */
    private String decimalPattern;

    /**
     * <p>
     * <b> DecimalPattern. </b>
     * </p>
     */
    private DecimalFormat decimalFormat;

    /**
     * Validate.
     *
     * @param fields the fields
     * @throws ValidateTokenException .
     * @see com.hsbc.contrac.filetransfer.fileupload.validators.FieldValidator#validate
     *      (java.lang.Object)
     */
    public void validate(final String[] fields) throws ValidateTokenException {
        final String contenido = getContentFromCell(fields, this.getIndex());
        try {
            this.getDecimalFormat().parse(contenido);
        } catch (ParseException e) {
            throw new ValidateTokenException(String.format(this.getMessageError(), this.getFieldName()), e);
        }
    }

    /**
     * Gets the decimal pattern.
     *
     * @return el decimalPattern
     */
    public final String getDecimalPattern() {
        return this.decimalPattern;
    }

    /**
     * Sets the decimal pattern.
     *
     * @param decimalPattern            el decimalPattern a establecer
     */
    public final void setDecimalPattern(final String decimalPattern) {
        this.decimalPattern = decimalPattern;
        this.decimalFormat = new DecimalFormat(this.decimalPattern);
    }

    /**
     * Gets the decimal format.
     *
     * @return el decimalFormat
     */
    public final DecimalFormat getDecimalFormat() {
        return this.decimalFormat;
    }

    /**
     * Sets the decimal format.
     *
     * @param decimalFormat            el decimalFormat a establecer
     */
    public final void setDecimalFormat(final DecimalFormat decimalFormat) {
        this.decimalFormat = decimalFormat;
    }


}
