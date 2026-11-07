/**
 * 
 */
/**
 * @author Ernesto
 *
 */
package com.gem.sistema.load.fileupload.validators;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * <b> . </b>
 * </p>
 * 
 * @param <T>
 *            Tipo de objeto dto que controla la duplicidad de registros.
 * @param <K>
 *            Objeto a convertir y controlar.
 */
public interface RegisterDuplicateProvider<T, K> {

    /**
     * <p>
     * <b> Metodo que registra la duplicidad. </b>
     * </p>
     * 
     * @param lineNumber
     *            .
     * @param list
     *            .
     * @param toConvert
     *            .
     * @return .
     */
    List<T> register(Integer lineNumber, List<T> list, K toConvert);
}
