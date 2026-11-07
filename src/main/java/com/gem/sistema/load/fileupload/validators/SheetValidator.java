/**
 * 
 */
/**
 * @author Ernesto
 *
 */
package com.gem.sistema.load.fileupload.validators;

import org.apache.poi.ss.usermodel.Sheet;

import com.gem.sistema.load.fileupload.exceptions.ValidateFileException;



// TODO: Auto-generated Javadoc
/**
 * <p>
 * <b> SheetValidator. </b>
 * </p>
 */
public interface SheetValidator {

    /**
     * <p>
     * Metodo para validar el contenido del archivo. Si algo truena se para el
     * procesamiento del archivo.
     * </p>
     *
     * @param sheet            Hoja de excel a validar.
     * @param errorFile the error file
     * @return True si el archivo es valido; false, en caso contrario.
     * @throws ValidateFileException             Se lanza en caso de alguna eventualidad en el proceso de
     *             validacion.
     */
    boolean isValid(Sheet sheet, String errorFile) throws ValidateFileException;
}
