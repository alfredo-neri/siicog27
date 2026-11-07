/**
 * 
 */
package com.gem.sistema.business.service.callsp;


import java.io.File;
import java.io.Serializable;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;


// TODO: Auto-generated Javadoc
/**
 * The Class ParamsSpDTO.
 */
public abstract  class ParamsSpDTO implements Serializable {
	
	/** The user. */
	protected String user;
	
	/** The id sector. */
	protected Integer idSector;

	/** The cod error. */
	protected Integer codError;
	
	/** The msg error. */
	protected String msgError;
	

	/** The path file out. */
	protected String pathFileOut;
	
	/** The file name out. */
	protected String fileNameOut;

	/** The result file. */
	protected File resultFile;

	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
	/**
	 * Gets the result file.
	 *
	 * @return the result file
	 */
	public File getResultFile() {
		return resultFile;
	}

	/**
	 * Sets the result file.
	 *
	 * @param resultFile the new result file
	 */
	public void setResultFile(File resultFile) {
		this.resultFile = resultFile;
	}

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
	 * Gets the path file out.
	 *
	 * @return the path file out
	 */
	public String getPathFileOut() {
		return pathFileOut;
	}

	/**
	 * Sets the path file out.
	 *
	 * @param pathFileOut the new path file out
	 */
	public void setPathFileOut(String pathFileOut) {
		this.pathFileOut = pathFileOut;
	}

	/**
	 * Gets the file name out.
	 *
	 * @return the file name out
	 */
	public String getFileNameOut() {
		return fileNameOut;
	}

	/**
	 * Sets the file name out.
	 *
	 * @param fileNameOut the new file name out
	 */
	public void setFileNameOut(String fileNameOut) {
		this.fileNameOut = fileNameOut;
	}
	
	/**
	 * Gets the id sector.
	 *
	 * @return the id sector
	 */
	public Integer getIdSector() {
		return idSector;
	}

	/**
	 * Sets the id sector.
	 *
	 * @param idSector the new id sector
	 */
	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

	
	/**
	 * Gets the sql parameter source.
	 *
	 * @return the sql parameter source
	 */
	public abstract SqlParameterSource getSqlParameterSource(); 
	
	/**
	 * Sets the result.
	 *
	 * @param result the result
	 */
	public abstract void setResult(Map<String, Object> result); 
}
