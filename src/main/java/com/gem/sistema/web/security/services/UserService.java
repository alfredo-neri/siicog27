/**
 * 
 */
package com.gem.sistema.web.security.services;

import com.gem.sistema.business.domain.TwUserAttempt;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserService.
 *
 * @author developer
 */
public interface UserService {
	
	/**
	 * Actualiza los intentos fallidos.
	 *
	 * @param username the username
	 */
	void updateFailAttempts(String username);

	/**
	 * restablece los intentos fallidos.
	 *
	 * @param username the username
	 */
	void resetFailAttempts(String username);
	
	/**
	 * Obtiene datos del intento fallido.
	 *
	 * @param username the username
	 * @return the user attempts
	 */
	TwUserAttempt getUserAttempts(String username);


}
