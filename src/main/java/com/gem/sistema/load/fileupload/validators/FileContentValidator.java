/**
 * 
 */
/**
 * @author Ernesto
 *
 */
package com.gem.sistema.load.fileupload.validators;

import com.gem.sistema.load.fileupload.exceptions.ValidateFileException;
import com.gem.sistema.load.fileupload.model.FileUpload;

// TODO: Auto-generated Javadoc
/**
 * .
 * 
 */
public interface FileContentValidator {

    /**
     * <p>
     * Metodo para validar el contenido del archivo. Si algo truena se para el
     * procesamiento del archivo.
     * </p>
     * 
     * @param fileUpload
     *            Bean del con los atributos del archivo a validar.
     * @return True si el archivo es valido; false, en caso contrario.
     * @throws ValidateFileException
     *             Se lanza en caso de alguna eventualidad en el proceso de
     *             validacion.
     */
    boolean isValid(FileUpload fileUpload) throws ValidateFileException;

    /**
     * <p>
     * <b> REcupera el archivo con la lista de errores. </b>
     * </p>
     *
     * @return the error file
     */
    String getErrorFile();
}
