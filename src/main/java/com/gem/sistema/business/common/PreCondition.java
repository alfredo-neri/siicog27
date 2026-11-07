package com.gem.sistema.business.common;


// TODO: Auto-generated Javadoc
/**
 * The Class PreCondition.
 */
public final class PreCondition {

    /**
     * Instantiates a new pre condition.
     */
    private PreCondition() {}

//comentario prueba    
    
    /**
 * Checks if is true.
 *
 * @param expression the expression
 * @param errorMessageTemplate the error message template
 * @param errorMessageArguments the error message arguments
 */
public static void isTrue(boolean expression, String errorMessageTemplate, Object... errorMessageArguments) {
        isTrue(expression, String.format(errorMessageTemplate, errorMessageArguments));
    }
    
    /**
     * Checks if is true.
     *
     * @param expression the expression
     * @param errorMessage the error message
     */
    public static void isTrue(boolean expression, String errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
    
    /**
     * Not empty.
     *
     * @param string the string
     * @param errorMessage the error message
     */
    public static void notEmpty(String string, String errorMessage) {
        if (string.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
    
    /**
     * Not null.
     *
     * @param reference the reference
     * @param errorMessage the error message
     */
    public static void notNull(Object reference, String errorMessage) {
        if (reference == null) {
            throw new NullPointerException(errorMessage);
        }
    }
}
