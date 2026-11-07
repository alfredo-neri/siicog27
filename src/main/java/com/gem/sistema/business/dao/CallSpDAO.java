package com.gem.sistema.business.dao;

import java.util.Map;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

// TODO: Auto-generated Javadoc
/**
 * The Interface CallSpDAO.
 */
public interface CallSpDAO {
	
	/**
	 * Call.
	 *
	 * @param plName the pl name
	 * @param in the in
	 * @return the map
	 */
	public Map<String, Object> call(String plName,  SqlParameterSource in);
}
