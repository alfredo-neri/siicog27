package com.gem.sistema.business.domain;

import javax.persistence.Transient;

// TODO: Auto-generated Javadoc
/**
 * The Class IntegradoEgreso.
 */
public class IntegradoEgreso {
	
	/** The cod error. */
	@Transient
	private Integer codError;
	
	/** The msg error. */
	@Transient
	private String  msgError;
	
	/** The path file. */
	@Transient
	private String pathFile;
	
	/** The file name. */
	@Transient
	private String fileName;
	
	/**
	 * Gets the cod error.
	 *
	 * @return the cod error
	 */
	public Integer getCodError() {
		return codError;
	}
	
	/**
	 * Sets the cod error.
	 *
	 * @param codError the new cod error
	 */
	public void setCodError(Integer codError) {
		this.codError = codError;
	}
	
	/**
	 * Gets the msg error.
	 *
	 * @return the msg error
	 */
	public String getMsgError() {
		return msgError;
	}
	
	/**
	 * Sets the msg error.
	 *
	 * @param msgError the new msg error
	 */
	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}
	
	/**
	 * Gets the path file.
	 *
	 * @return the path file
	 */
	public String getPathFile() {
		return pathFile;
	}
	
	/**
	 * Sets the path file.
	 *
	 * @param pathFile the new path file
	 */
	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}
	
	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
