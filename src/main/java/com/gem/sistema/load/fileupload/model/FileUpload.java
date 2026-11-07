/**
 * 
 */
/**
 * @author Ernesto
 *
 */
package com.gem.sistema.load.fileupload.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;



// TODO: Auto-generated Javadoc
/**
 * .
 * 
 */
public class FileUpload implements Serializable {

    /** The Constant UPLOADED. */
    public static final Integer UPLOADED = 0;
    
    /** The Constant VALIDATED_OK. */
    public static final Integer VALIDATED_OK = 1;
    
    /** The Constant VALIDATED_NOT_OK. */
    public static final Integer VALIDATED_NOT_OK = 2;
    
    /** The Constant VALIDATED_W_ERR. */
    public static final Integer VALIDATED_W_ERR = 3;
    
    /** The Constant PROCESSED_OK. */
    public static final Integer PROCESSED_OK = 4;
    
    /** The Constant PROCESSED_NOT_OK. */
    public static final Integer PROCESSED_NOT_OK = 5;
    
    /** The Constant PROCESSED_W_ERR. */
    public static final Integer PROCESSED_W_ERR = 6;

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 9202402044543706830L;

    /**
     * <p>
     * .
     * </p>
     * 
     */
    private Long id;

    /**
     * <p>
     * <b> . </b>
     * </p>
     */
    private String idStr;

    /**
     * <p>
     * .
     * </p>
     * 
     */
    private String path;
    /**
     * <p>
     * .
     * </p>
     * 
     */
    private String nameOriginal;
    /**
     * <p>
     * .
     * </p>
     * 
     */
    private String nameReal;
    /**
     * <p>
     * .
     * </p>
     * 
     */
    private String type;
    /**
     * <p>
     * .
     * </p>
     * 
     */
    private Date modifiedDate;
    /**
     * <p>
     * .
     * </p>
     * 
     */
    private String modifyBy;
    /**
     * <p>
     * .
     * </p>
     * 
     */
    private int tipo;

    /**
     * <p>
     * .
     * </p>
     * 
     */
    private int correctos;

    /**
     * <p>
     * .
     * </p>
     * 
     */
    private int incorrectos;

    /**
     * <p>
     * .
     * </p>
     * 
     */
    private int total;

    /**
     * <p>
     * .
     * </p>
     * 
     */
    private int estatus;

    /**
     * <p>
     * <b> . </b>
     * </p>
     */
    private File file;
    
    
    /** The error path. */
    private String errorPath;
    
    /** The out put path. */
    private String outPutPath;

    /**
     * <p>
     * <b> . </b>
     * </p>
     * 
     */
    public FileUpload() {
        super();
    }

    /**
     * <p>
     * .
     * </p>
     * 
     * @param id
     *            -
     * @param path
     *            -
     * @param nameOriginal
     *            -
     * @param nameReal
     *            -
     * @param type
     *            -
     * @param modifiedDate
     *            -
     * @param modifyBy
     *            -
     */
    public FileUpload(final Long id, final String path, final String nameOriginal, final String nameReal, final String type,
        final Date modifiedDate, final String modifyBy) {
        super();
        this.id = id;
        this.path = path;
        this.nameOriginal = nameOriginal;
        this.nameReal = nameReal;
        this.type = type;
        this.modifiedDate = modifiedDate;
        this.modifyBy = modifyBy;
    }

    /**
     * Gets the id.
     *
     * @return el id
     */
    public final Long getId() {
        return this.id;
    }

    /**
     * Sets the id.
     *
     * @param id            el id a establecer
     */
    public final void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the path.
     *
     * @return el path
     */
    public final String getPath() {
        return this.path;
    }

    /**
     * Sets the path.
     *
     * @param path            el path a establecer
     */
    public final void setPath(final String path) {
        this.path = path;
    }

    /**
     * Gets the name original.
     *
     * @return el nameOriginal
     */
    public final String getNameOriginal() {
        return this.nameOriginal;
    }

    /**
     * Sets the name original.
     *
     * @param nameOriginal            el nameOriginal a establecer
     */
    public final void setNameOriginal(final String nameOriginal) {
        this.nameOriginal = nameOriginal;
    }

    /**
     * Gets the name real.
     *
     * @return el nameReal
     */
    public final String getNameReal() {
        return this.nameReal;
    }

    /**
     * Sets the name real.
     *
     * @param nameReal            el nameReal a establecer
     */
    public final void setNameReal(final String nameReal) {
        this.nameReal = nameReal;
    }

    /**
     * Gets the type.
     *
     * @return el type
     */
    public final String getType() {
        return this.type;
    }

    /**
     * Sets the type.
     *
     * @param type            el type a establecer
     */
    public final void setType(final String type) {
        this.type = type;
    }

    /**
     * Gets the modified date.
     *
     * @return the modifiedDate
     */
    public final Date getModifiedDate() {
        return this.modifiedDate;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate            - the modifiedDate to set
     */
    public final void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Gets the modify by.
     *
     * @return el modifyBy
     */
    public final String getModifyBy() {
        return this.modifyBy;
    }

    /**
     * Sets the modify by.
     *
     * @param modifyBy            el modifyBy a establecer
     */
    public final void setModifyBy(final String modifyBy) {
        this.modifyBy = modifyBy;
    }

    /**
     * To string.
     *
     * @return .
     * @see java.lang.Object#toString()
     */
    public final String toString() {
        return "Customer [id=" + this.id + ", path=" + this.path + ", nameOR=" + this.nameOriginal + ", nameReal=" + this.nameReal
            + "]";
    }

    /**
     * Gets the tipo.
     *
     * @return the tipo
     */
    public final int getTipo() {
        return this.tipo;
    }

    /**
     * Gets the id str.
     *
     * @return el idStr
     */
    public final String getIdStr() {
        return this.idStr;
    }

    /**
     * Sets the id str.
     *
     * @param idStr            el idStr a establecer
     */
    public final void setIdStr(final String idStr) {
        this.idStr = idStr;
    }

    /**
     * Gets the correctos.
     *
     * @return el correctos
     */
    public final int getCorrectos() {
        return this.correctos;
    }

    /**
     * Sets the correctos.
     *
     * @param correctos            el correctos a establecer
     */
    public final void setCorrectos(final int correctos) {
        this.correctos = correctos;
    }

    /**
     * Gets the incorrectos.
     *
     * @return el incorrectos
     */
    public final int getIncorrectos() {
        return this.incorrectos;
    }

    /**
     * Sets the incorrectos.
     *
     * @param incorrectos            el incorrectos a establecer
     */
    public final void setIncorrectos(final int incorrectos) {
        this.incorrectos = incorrectos;
    }

    /**
     * Gets the total.
     *
     * @return el total
     */
    public final int getTotal() {
        return this.total;
    }

    /**
     * Sets the total.
     *
     * @param total            el total a establecer
     */
    public final void setTotal(final int total) {
        this.total = total;
    }

    /**
     * Gets the estatus.
     *
     * @return el estatus
     */
    public final int getEstatus() {
        return this.estatus;
    }

    /**
     * Sets the estatus.
     *
     * @param estatus            el estatus a establecer
     */
    public final void setEstatus(final int estatus) {
        this.estatus = estatus;
    }

    /**
     * Sets the tipo.
     *
     * @param tipo            el tipo a establecer
     */
    public final void setTipo(final int tipo) {
        this.tipo = tipo;
    }

    /**
     * Gets the file.
     *
     * @return el file
     */
    public final File getFile() {
        return this.file;
    }

    /**
     * Sets the file.
     *
     * @param file            el file a establecer
     */
    public final void setFile(final File file) {
        this.file = file;
    }

	/**
	 * Gets the error path.
	 *
	 * @return the error path
	 */
	public String getErrorPath() {
		return errorPath;
	}

	/**
	 * Sets the error path.
	 *
	 * @param errorPath the new error path
	 */
	public void setErrorPath(String errorPath) {
		this.errorPath = errorPath;
	}

	/**
	 * Gets the out put path.
	 *
	 * @return the outPutPath
	 */
	public String getOutPutPath() {
		return outPutPath;
	}

	/**
	 * Sets the out put path.
	 *
	 * @param outPutPath the outPutPath to set
	 */
	public void setOutPutPath(String outPutPath) {
		this.outPutPath = outPutPath;
	}
    
    
    

}
