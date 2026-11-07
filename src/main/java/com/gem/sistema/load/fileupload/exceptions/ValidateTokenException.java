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
 * Excepciones para token
 * 
 * ValidateTokenException .
 * 
 */
public class ValidateTokenException extends Exception {

    /**
     * <p>
     * .
     * </p>
     */
    private static final String MISTAKE = "Error el token";

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
     * Default contructor.
     */
    public ValidateTokenException() {
        super();
        this.mistake = ValidateTokenException.MISTAKE;
    }

    /**
     * <p>
     * .
     * </p>
     * 
     * @param err
     *            .
     */
    public ValidateTokenException(final String err) {
        super(err);
        this.mistake = err;
    }

    /**
     * Instantiates a new validate token exception.
     *
     * @param cause            .
     */
    public ValidateTokenException(final Throwable cause) {
        super(cause);
        this.mistake = ValidateTokenException.MISTAKE;
    }

    /**
     * Instantiates a new validate token exception.
     *
     * @param err            .
     * @param cause            .
     */
    public ValidateTokenException(final String err, final Throwable cause) {
        super(err, cause);
        this.mistake = err;
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
