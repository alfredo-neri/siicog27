/**
 * 
 */
/**
 * @author Ernesto
 *
 */
package com.gem.sistema.load.fileupload.validators;

import com.gem.sistema.load.fileupload.exceptions.ValidateTokenException;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * <b> Interfaz que define el proceso de validacion de campos. </b>
 * </p>
 * 
 * @param <T>
 *            Tipo de dato a validar.
 */
public interface FieldValidator<T> {

    /**
     * <p>
     * <b> Metodo que realizaq la validacion de campos(tokens). </b>
     * </p>
     * 
     * @param field
     *            campo a validar.
     * @throws ValidateTokenException
     *             Se lanza en caso de alguna eventualidad o falla de la
     *             validacion.
     */
    void validate(T field) throws ValidateTokenException;

    /**
     * <p>
     * <b> Bandera que indica si el validador se omite. </b>
     * </p>
     * 
     * @return True si el validador se omite; false, en caso contrario.
     */
    Boolean skipOnFail();

}
