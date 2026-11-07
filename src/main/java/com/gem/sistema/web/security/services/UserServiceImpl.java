/**
 * 
 */
package com.gem.sistema.web.security.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcUsuario;
import com.gem.sistema.business.domain.TwUserAttempt;
import com.gem.sistema.business.repository.catalogs.TcUsuarioRepository;
import com.gem.sistema.business.repository.catalogs.TwUserAttemptRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImpl.
 *
 * @author developer
 */
@Repository("userService")
public class UserServiceImpl implements UserService {
	
	/** Numero Maximo de intentos. */
	private static final int MAX_ATTEMPTS = 3;
	
	/** Numero Maximo de intentos. */
	private static final int INIT_CUONTER = 1;
	
	
	/** The tc usuario repository. */
	@Autowired
	private TcUsuarioRepository tcUsuarioRepository;
	
	/** The tw user attempt repository. */
	@Autowired
	private TwUserAttemptRepository  twUserAttemptRepository;
	
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.security.services.UserService#updateFailAttempts(java.lang.String)
	 */
	@Override
	public void updateFailAttempts(String username) {
		// buscamos a ver si ya existe en nuestra tabla
		final TwUserAttempt twUserAttempt = getUserAttempts(username);
		
		//buscamos que el registro exista en nuestra tabla de usuarios
		final TcUsuario usuario = tcUsuarioRepository.findByUsuario( username);
		
		if( null == twUserAttempt){
			
			if( null != usuario){
				//Si si es un registro valido. entonces insertamo un nuevo registro en la tabla de  user attemps
				final TwUserAttempt userAttempt = new TwUserAttempt();
				userAttempt.setUsuario(username);
				userAttempt.setAttempts(INIT_CUONTER);
				userAttempt.setLastmodified(Calendar.getInstance().getTime());
				twUserAttemptRepository.save(userAttempt);
				
			}
		}else{
			if( null != usuario){
				final int currentAttemps=twUserAttempt.getAttempts();
				twUserAttempt.setAttempts(currentAttemps+1);
				twUserAttempt.setLastmodified(Calendar.getInstance().getTime());
				
				twUserAttemptRepository.save(twUserAttempt);
			}
			
			if(twUserAttempt.getAttempts()>= MAX_ATTEMPTS){
				//usuario.setAccountnonlocked(LOCKED);;
				System.out.println("Usuario antes de actualizar" );
				
				tcUsuarioRepository.setAccountNonLockedFor(Boolean.FALSE,usuario.getId());
				
				throw new LockedException("La cuenta esta Bloqueada!");
			}
			
		}

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.security.services.UserService#resetFailAttempts(java.lang.String)
	 */
	@Override
	public void resetFailAttempts(String username) {
		// buscamos a ver si ya existe en nuestra tabla
				final TwUserAttempt twUserAttempt =  getUserAttempts(username);
				if ( null != twUserAttempt ) {
				twUserAttempt.setAttempts(0);
				twUserAttempt.setLastmodified(null);
				twUserAttemptRepository.save(twUserAttempt);
				}
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.security.services.UserService#getUserAttempts(java.lang.String)
	 */
	@Override
	public TwUserAttempt getUserAttempts(String username) {
		return twUserAttemptRepository.findByUsuario(username);
	}

	/**
	 * Gets the tc usuario repository.
	 *
	 * @return the tc usuario repository
	 */
	public TcUsuarioRepository getTcUsuarioRepository() {
		return tcUsuarioRepository;
	}

	/**
	 * Sets the tc usuario repository.
	 *
	 * @param tcUsuarioRepository the new tc usuario repository
	 */
	public void setTcUsuarioRepository(TcUsuarioRepository tcUsuarioRepository) {
		this.tcUsuarioRepository = tcUsuarioRepository;
	}

	/**
	 * Gets the tw user attempt repository.
	 *
	 * @return the tw user attempt repository
	 */
	public TwUserAttemptRepository getTwUserAttemptRepository() {
		return twUserAttemptRepository;
	}

	/**
	 * Sets the tw user attempt repository.
	 *
	 * @param twUserAttemptRepository the new tw user attempt repository
	 */
	public void setTwUserAttemptRepository(TwUserAttemptRepository twUserAttemptRepository) {
		this.twUserAttemptRepository = twUserAttemptRepository;
	}
	
	
	
	
}
