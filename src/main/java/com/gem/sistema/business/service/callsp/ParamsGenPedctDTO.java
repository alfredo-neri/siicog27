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
 * The Class ParamsGenPedctDTO.
 */
public class ParamsGenPedctDTO extends ParamsSpDTO {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.callsp.ParamsSpDTO#getSqlParameterSource()
	 */
	public SqlParameterSource getSqlParameterSource(){
		return new MapSqlParameterSource().addValue("i_usuario", user);
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
