/**
 * 
 */
package com.gem.sistema.business.service.callsp;


import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;


// TODO: Auto-generated Javadoc
/**
 * The Class ParamsRF009115DTO.
 */
public class ParamsRF009115DTO extends ParamsSpDTO {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The mes. */
	protected Integer mes;
	
	/** The firmas. */
	protected Integer firmas;
	
	/**
	 * Gets the firmas.
	 *
	 * @return the firmas
	 */
	public Integer getFirmas() {
		return firmas;
	}

	/**
	 * Sets the firmas.
	 *
	 * @param firmas the new firmas
	 */
	public void setFirmas(Integer firmas) {
		this.firmas = firmas;
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

	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.callsp.ParamsSpDTO#getSqlParameterSource()
	 */
	public SqlParameterSource getSqlParameterSource(){
		return new MapSqlParameterSource().
				addValue("i_usuario", user).
				addValue("i_firmas", firmas).
				addValue("i_mes", mes);
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.callsp.ParamsSpDTO#setResult(java.util.Map)
	 */
	public void setResult(Map<String, Object> result){
		this.setCodError(MapUtils.getIntValue(result, "O_COD_ERROR"));
		this.setMsgError(MapUtils.getString(result, "O_MSG_ERROR"));
		this.setFileNameOut(MapUtils.getString(result, "O_NAME_FILE"));
		this.setPathFileOut(MapUtils.getString(result, "O_RUTA_FILE"));
	}
}
