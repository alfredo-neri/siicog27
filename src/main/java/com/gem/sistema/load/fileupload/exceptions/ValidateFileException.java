/**
 * 
 */
/**
 * @author Ernesto
 *
 */
package com.gem.sistema.load.fileupload.exceptions;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * .
 * </p>
 * 
 * ValidateFileException
 * 
 */
public class ValidateFileException extends Exception {

    /**
     * <p>
     * .
     * </p>
     */
    private static final String MISTAKE = "Error al validar el archivo";

    /**
     * <p>
     * .
     * </p>
     */
    private static final long serialVersionUID = 1L;

    /**
     * <p>
     * Error .
     * </p>
     */
    private final String mistake;

    /**
     * Default contructor .
     */
    public ValidateFileException() {
        super();
        this.mistake = ValidateFileException.MISTAKE;
    }

    /**
     * <p>
     * .
     * </p>
     * 
     * @param err
     *            .
     */
    public ValidateFileException(final String err) {
        super(err);
        this.mistake = err;
    }


    /**
     * Instantiates a new validate file exception.
     *
     * @param err            .
     * @param cause            .
     */
    public ValidateFileException(final String err, final Throwable cause) {
        super(err, cause);
        this.mistake = err;
    }

    /**
     * Instantiates a new validate file exception.
     *
     * @param cause            .
     */
    public ValidateFileException(final Throwable cause) {
        super(cause);
        this.mistake = ValidateFileException.MISTAKE;
    }

    /**
     * Gets the error.
     *
     * @return El error .
     */
    public final String getError() {
        return this.mistake;
    }

    /**
     * Gets the mistake.
     *
     * @return the mistake
     */
    public final String getMistake() {
        return this.mistake;
    }


}
