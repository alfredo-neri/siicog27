package com.gem.sistema.web.security.providers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.TwUserAttempt;
import com.gem.sistema.web.security.services.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class LimitLoginAuthenticationProvider.
 */
@Component("authenticationProvider")
public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {

	/** The user service. */
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.dao.DaoAuthenticationProvider#setUserDetailsService(org.springframework.security.core.userdetails.UserDetailsService)
	 */
	@Autowired
	@Qualifier("authenticationService")
	@Override
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}
	
	/**
	 * Sets the password encoder.
	 *
	 * @param passwordEncoder the new password encoder
	 */
	@Autowired
	@Qualifier("passwordEncoder")
	public void setPasswordEncoder(PasswordEncoder passwordEncoder){
		super.setPasswordEncoder(passwordEncoder);

	}
	
	
	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		try {

			Authentication auth = super.authenticate(authentication);
			userService.resetFailAttempts(authentication.getName());
			return auth;

		} catch (BadCredentialsException e) {

			userService.updateFailAttempts(authentication.getName());
			throw e;

		} catch (LockedException e) {

			String error = "";
			TwUserAttempt userAttempts = userService.getUserAttempts(authentication.getName());
			if (userAttempts != null) {
				Date lastAttempts = userAttempts.getLastmodified();
				error = "User account is locked! <br><br>Username : " + authentication.getName()
						+ "<br>Last Attempts : " + lastAttempts;
			} else {
				error = e.getMessage();
			}

			throw new LockedException(error);
		}

	}

}
