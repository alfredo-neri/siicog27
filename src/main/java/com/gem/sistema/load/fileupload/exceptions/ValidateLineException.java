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
 * Clase de Excepcion para Errores en una Linea
 * 
 * ValidateLineException .
 * 
 */
public class ValidateLineException extends Exception {

    /**
     * <p>
     * .
     * </p>
     */
    private static final String MISTAKE = "Error al validar Linea:";

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
    public ValidateLineException() {
        super();
        this.mistake = ValidateLineException.MISTAKE;
    }

    /**
     * <p>
     * .
     * </p>
     * 
     * @param err
     *            .
     */
    public ValidateLineException(final String err) {
        super(err);
        this.mistake = err;
    }


    /**
     * Instantiates a new validate line exception.
     *
     * @param err            .
     * @param cause            .
     */
    public ValidateLineException(final String err, final Throwable cause) {
        super(err, cause);
        this.mistake = err;
    }

    /**
     * Instantiates a new validate line exception.
     *
     * @param noLinea            .
     * @param err            .
     * @param cause            .
     */
    public ValidateLineException(final Integer noLinea, final String err,
        final Throwable cause) {
        super(err, cause);
        this.mistake = ValidateLineException.MISTAKE + noLinea + err;

    }

    /**
     * Instantiates a new validate line exception.
     *
     * @param cause            .
     */
    public ValidateLineException(final Throwable cause) {
        super(cause);
        this.mistake = ValidateLineException.MISTAKE;
    }

    /**
     * Gets the error.
     *
     * @return El error
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
