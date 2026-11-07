package com.gem.sistema.business.service.callsp;

import java.util.Map;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExecutePlService.
 *
 * @author buser
 */
public interface ExecutePlService {
	
	 /**
 	 * Call procedure.
 	 *
 	 * @param procedureName the procedure name
 	 * @param in the in
 	 * @return the map
 	 */
	Map<String, Object> callProcedure(String procedureName,  SqlParameterSource in);
	
	/**
	 * Gets the parameters.
	 *
	 * @param parametersSql the parameters sql
	 * @return the parameters
	 */
	SqlParameterSource getParameters(SqlParameterSource parametersSql);
	
	
}
