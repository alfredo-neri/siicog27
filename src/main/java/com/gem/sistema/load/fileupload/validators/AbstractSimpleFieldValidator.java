/**
 * 
 */
/**
 * @author Ernesto
 *
 */
package com.gem.sistema.load.fileupload.validators;


// TODO: Auto-generated Javadoc
/**
 * <p>
 * <b> Clase abstracta que define el validador simple. </b>
 * </p>
 * 
 * @param <T>
 *            Tipo de dato a validar.
 */
public abstract class AbstractSimpleFieldValidator<T> extends AbstractValidator implements FieldValidator<T> {

    /**
     * <p>
     * <b> Indice del token a validar. </b>
     * </p>
     */
    private Integer index;

    /**
     * <p>
     * <b> Nombre del campo correspondiente al indice. </b>
     * </p>
     */
    private String fieldName;

    /**
     * <p>
     * <b> Bandera para omitir la falla de la validacion. </b>
     * </p>
     */
    private Boolean skipOnFail2 = Boolean.TRUE;

    /**
     * Gets the index.
     *
     * @return el index
     */
    public final Integer getIndex() {
        return this.index;
    }

    /**
     * Sets the index.
     *
     * @param index            el index a establecer
     */
    public final void setIndex(final Integer index) {
        this.index = index;
    }

    /**
     * Gets the field name.
     *
     * @return el fieldName
     */
    public final String getFieldName() {
        return this.fieldName;
    }

    /**
     * Sets the field name.
     *
     * @param fieldName            el fieldName a establecer
     */
    public final void setFieldName(final String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Skip on fail.
     *
     * @return .
     * @see com.hsbc.contrac.filetransfer.fileupload.validators.FieldValidator#skipOnFail()
     */
    public final Boolean skipOnFail() {
        return this.getSkipOnFail();
    }

    /**
     * Gets the skip on fail.
     *
     * @return el skipOnFail2
     */
    public final Boolean getSkipOnFail() {
        return this.skipOnFail2;
    }

    /**
     * Sets the skip on fail.
     *
     * @param skipOnFail            el skipOnFail2 a establecer
     */
    public final void setSkipOnFail(final Boolean skipOnFail) {
        this.skipOnFail2 = skipOnFail;
    }


}
