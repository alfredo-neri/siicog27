
package com.gem.sistema.web.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// TODO: Auto-generated Javadoc
/**
 * The Class LoginController.
 */
//@Named()
@ManagedBean
@RequestScoped
public class LoginController implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	/** The user. */
	private String user;
	
	/** The password. */
	private String password;




	/**
	 * **********************************************.
	 *
	 * @return the user
	 */

	public String getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}



	/**
	 * Login.
	 *
	 * @return the string
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("rawtypes")
	public String login() throws ServletException, IOException { 
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		ServletRequest requestContext =  (ServletRequest) context.getRequest();
		ServletResponse responseContext =  (ServletResponse) context.getResponse();


		RequestDispatcher dispatcher = requestContext.getRequestDispatcher("/j_spring_security_check");

		Iterator entries = requestContext.getParameterMap().entrySet().iterator();
		while (entries.hasNext()) {
			Entry entry = (Entry) entries.next();
			//System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue().toString());
			LOGGER.debug("Key = {} , Value = {} "  ,entry.getKey(), entry.getValue().toString());        
		}



		dispatcher.forward(requestContext, responseContext);
		FacesContext.getCurrentInstance().responseComplete();

		return null;
	}


}