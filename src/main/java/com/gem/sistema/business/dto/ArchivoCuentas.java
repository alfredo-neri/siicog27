/**
 * 
 */
package com.gem.sistema.business.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class ArchivoCuentas.
 *
 * @author Gerardo CUERO
 */
public class ArchivoCuentas implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7902769915722607532L;

	/** The cod error. */
	private Integer codError;

	/** The msg error. */
	private String msgError;

	/** The full file path. */
	private String fullFilePath;

	/**
	 * Instantiates a new archivo cuentas.
	 */
	public ArchivoCuentas() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new archivo cuentas.
	 *
	 * @param codError the cod error
	 * @param msgError the msg error
	 * @param fullFilePath the full file path
	 */
	public ArchivoCuentas(Integer codError, String msgError, String fullFilePath) {
		super();
		this.codError = codError;
		this.msgError = msgError;
		this.fullFilePath = fullFilePath;
	}

	/**
	 * Gets the cod error.
	 *
	 * @return the codError
	 */
	public Integer getCodError() {
		return codError;
	}

	/**
	 * Sets the cod error.
	 *
	 * @param codError            the codError to set
	 */
	public void setCodError(Integer codError) {
		this.codError = codError;
	}

	/**
	 * Gets the full file path.
	 *
	 * @return the fullFilePath
	 */
	public String getFullFilePath() {
		return fullFilePath;
	}

	/**
	 * Sets the full file path.
	 *
	 * @param fullFilePath            the fullFilePath to set
	 */
	public void setFullFilePath(String fullFilePath) {
		this.fullFilePath = fullFilePath;
	}

	/**
	 * Gets the msg error.
	 *
	 * @return the msgError
	 */
	public String getMsgError() {
		return msgError;
	}

	/**
	 * Sets the msg error.
	 *
	 * @param msgError            the msgError to set
	 */
	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

}
