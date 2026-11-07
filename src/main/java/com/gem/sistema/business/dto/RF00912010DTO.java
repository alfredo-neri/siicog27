package com.gem.sistema.business.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class RF00912010DTO.
 */
public class RF00912010DTO {

	/** The id sector. */
	private Integer idSector;

	/** The i mes. */
	private Integer iMes;

	private Integer iCapitulo;

	private int tipoReporte = 1;

	/** The o cod error. */
	private Integer oCodError;

	/** The o msg error. */
	private String oMsgError;

	/** The o name file. */
	private String oNameFile;

	/** The o path file. */
	private String oPathFile;

	private String query;

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
	 * Gets the i mes.
	 *
	 * @return the i mes
	 */
	public Integer getiMes() {
		return iMes;
	}

	/**
	 * Sets the i mes.
	 *
	 * @param iMes the new i mes
	 */
	public void setiMes(Integer iMes) {
		this.iMes = iMes;
	}

	public Integer getiCapitulo() {
		return iCapitulo;
	}

	public void setiCapitulo(Integer iCapitulo) {
		this.iCapitulo = iCapitulo;
	}

	/**
	 * Gets the o cod error.
	 *
	 * @return the o cod error
	 */
	public Integer getoCodError() {
		return oCodError;
	}

	/**
	 * Sets the o cod error.
	 *
	 * @param oCodError the new o cod error
	 */
	public void setoCodError(Integer oCodError) {
		this.oCodError = oCodError;
	}

	/**
	 * Gets the o msg error.
	 *
	 * @return the o msg error
	 */
	public String getoMsgError() {
		return oMsgError;
	}

	/**
	 * Sets the o msg error.
	 *
	 * @param oMsgError the new o msg error
	 */
	public void setoMsgError(String oMsgError) {
		this.oMsgError = oMsgError;
	}

	/**
	 * Gets the o name file.
	 *
	 * @return the o name file
	 */
	public String getoNameFile() {
		return oNameFile;
	}

	/**
	 * Sets the o name file.
	 *
	 * @param oNameFile the new o name file
	 */
	public void setoNameFile(String oNameFile) {
		this.oNameFile = oNameFile;
	}

	/**
	 * Gets the o path file.
	 *
	 * @return the o path file
	 */
	public String getoPathFile() {
		return oPathFile;
	}

	/**
	 * Sets the o path file.
	 *
	 * @param oPathFile the new o path file
	 */
	public void setoPathFile(String oPathFile) {
		this.oPathFile = oPathFile;
	}

	public int getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(int tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}
