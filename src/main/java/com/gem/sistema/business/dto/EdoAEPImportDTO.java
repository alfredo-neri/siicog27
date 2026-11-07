/**
 * 
 */
package com.gem.sistema.business.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class EdoAEPImportDTO.
 *
 * @author Gerardo CUERO
 */
public class EdoAEPImportDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7113315803656928472L;

	/** The mes. */
	private Integer mes;

	/** The organismo. */
	private Integer organismo;

	/** The id sector. */
	private Integer idSector;

	/** The userid. */
	private String userid;

	/** The file name. */
	private String fileName;

	/** The cod error. */
	private Integer codError;

	/** The msg error. */
	private String msgError;

	/** The full file. */
	private String fullFile;

	/**
	 * Instantiates a new edo AEP import DTO.
	 */
	public EdoAEPImportDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new edo AEP import DTO.
	 *
	 * @param mes the mes
	 * @param organismo the organismo
	 * @param idSector the id sector
	 * @param userid the userid
	 * @param fileName the file name
	 */
	public EdoAEPImportDTO(Integer mes, Integer organismo, Integer idSector, String userid, String fileName) {
		super();
		this.mes = mes;
		this.organismo = organismo;
		this.idSector = idSector;
		this.userid = userid;
		this.fileName = fileName;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}

	/**
	 * Gets the organismo.
	 *
	 * @return the organismo
	 */
	public Integer getOrganismo() {
		return organismo;
	}

	/**
	 * Sets the organismo.
	 *
	 * @param organismo the new organismo
	 */
	public void setOrganismo(Integer organismo) {
		this.organismo = organismo;
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
	 * Gets the userid.
	 *
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * Sets the userid.
	 *
	 * @param userid the new userid
	 */
	public void setUserid(String userid) {
		this.userid = userid;
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
	 * Gets the full file.
	 *
	 * @return the full file
	 */
	public String getFullFile() {
		return fullFile;
	}

	/**
	 * Sets the full file.
	 *
	 * @param fullFile the new full file
	 */
	public void setFullFile(String fullFile) {
		this.fullFile = fullFile;
	}

}
