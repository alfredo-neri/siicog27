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
 * <b> Clase que valida que el contenido de un campo sea alfabetico. </b>
 * </p>
 */
public class AlphaFieldValidator extends AbstractCellFieldValidator {

    /**
     * <p>
     * <b> Metodo que realizaq la validacion de campos(tokens). </b>
     * </p>
     *
     * @param fields the fields
     * @throws ValidateTokenException             Se lanza en caso de alguna eventualidad o falla de la
     *             validacion.
     */
    public void validate(final String[] fields) throws ValidateTokenException {
        final String contenido = getContentFromCell(fields, this.getIndex());
        if (!StringUtils.isBlank(contenido) && !StringUtils.isAlphanumericSpace(contenido.trim())) {
            throw new ValidateTokenException(String.format(this.getMessageError(), this.getFieldName()));
        }
    }

}
