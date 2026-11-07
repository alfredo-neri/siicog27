/**
 * 
 */
package com.gem.sistema.web.security.listeners;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving loginErrorPhase events.
 * The class that is interested in processing a loginErrorPhase
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addLoginErrorPhaseListener<code> method. When
 * the loginErrorPhase event occurs, that object's appropriate
 * method is invoked.
 *
 * @author gauss
 */
public class LoginErrorPhaseListener implements PhaseListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
	 */
	@Override
	public void afterPhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
	 */
	@Override
	public void beforePhase(PhaseEvent arg0) {
		 Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(
				 WebAttributes.AUTHENTICATION_EXCEPTION);
	 
	        if (e instanceof BadCredentialsException)
	        {
	            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
	            		WebAttributes.AUTHENTICATION_EXCEPTION, null);
	            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Usuario o password invalido."));
	            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();	            
	        }else if( e instanceof LockedException ){
	        	 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
		            		WebAttributes.AUTHENTICATION_EXCEPTION, null);
		            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Usuario Bloqueado."));
		            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();		            
	        }else if( e instanceof AuthenticationException){
	        	//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
	            //		WebAttributes.AUTHENTICATION_EXCEPTION, null);
	            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Usuario no encontrado."));
	            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();	            
	        }

	}

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RENDER_RESPONSE;
	}

}
