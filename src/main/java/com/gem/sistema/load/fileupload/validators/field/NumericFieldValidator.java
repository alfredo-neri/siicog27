/**
 * 
 */
package com.gem.sistema.load.fileupload.validators.field;


import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.load.fileupload.exceptions.ValidateTokenException;
import com.gem.sistema.load.fileupload.validators.AbstractCellFieldValidator;




// TODO: Auto-generated Javadoc
/**
 * <p>
 * <b> Clase que valida un numero. </b>
 * </p>
 */
public class NumericFieldValidator extends AbstractCellFieldValidator {


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
        if (StringUtils.isBlank(contenido) || !StringUtils.isNumeric(contenido.trim())) {
            throw new ValidateTokenException(String.format(this.getMessageError(), this.getFieldName()));
        }
    }


}
