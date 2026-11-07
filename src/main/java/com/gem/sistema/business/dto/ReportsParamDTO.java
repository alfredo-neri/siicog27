package com.gem.sistema.business.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class ReportsParamDTO.
 */
public class ReportsParamDTO {
	
	/** The mes. */
	private Integer mes;
	
	/** The id sector. */
	private Integer idSector;
	
	/** The tipo. */
	private Integer tipo;
	
	/** The query. */
	private String query;
	
	/** The q total. */
	private String qTotal;
	
	/** The municipio. */
	private String municipio;

	/** The o cod error. */
	private Integer oCodError;
	
	/** The o msg error. */
	private String  oMsgError;
	
	/** The o full file. */
	private String oFullFile;
	
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
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public Integer getTipo() {
		return tipo;
	}
	
	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Gets the query.
	 *
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}
	
	/**
	 * Sets the query.
	 *
	 * @param query the new query
	 */
	public void setQuery(String query) {
		this.query = query;
	}
	
	/**
	 * Gets the q total.
	 *
	 * @return the q total
	 */
	public String getqTotal() {
		return qTotal;
	}
	
	/**
	 * Sets the q total.
	 *
	 * @param qTotal the new q total
	 */
	public void setqTotal(String qTotal) {
		this.qTotal = qTotal;
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
	 * Gets the o full file.
	 *
	 * @return the o full file
	 */
	public String getoFullFile() {
		return oFullFile;
	}
	
	/**
	 * Sets the o full file.
	 *
	 * @param oFullFile the new o full file
	 */
	public void setoFullFile(String oFullFile) {
		this.oFullFile = oFullFile;
	}
	
	
	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

}
