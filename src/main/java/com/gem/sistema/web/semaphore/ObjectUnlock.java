package com.gem.sistema.web.semaphore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.Serializable;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ObjectUnlock.
 */
@Component("objectUnlock")
@Scope("session")
public class ObjectUnlock implements Serializable,HttpSessionBindingListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectUnlock.class);

	/** The semaphores. */
	@Value("#{semaphores}")
	private Semaphores semaphores;
	

   	/**
	    * Gets the semaphores.
	    *
	    * @return the semaphores
	    */
	   public Semaphores getSemaphores() {
		return semaphores;
	}

	/**
	 * Sets the semaphores.
	 *
	 * @param semaphores the new semaphores
	 */
	public void setSemaphores(Semaphores semaphores) {
		this.semaphores = semaphores;
	}

	
    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpSessionBindingListener#valueBound(jakarta.servlet.http.HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent event) {
    	LOGGER.info("valueBound: {} sessionid: {}", event.getName(), event.getSession().getId() );

    }

    /**
     * Register session.
     */
    public void registerSession() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "sessionBindingListener", this  );
        LOGGER.info( "registered sessionBindingListener"  );
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpSessionBindingListener#valueUnbound(jakarta.servlet.http.HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent event) {
    	LOGGER.info("valueUnBound: {} sessionid: {} semaphores {}"  ,event.getName(), event.getSession().getId(), semaphores );              
    	
    	if(!"objectUnlock".equals(event.getName())){
    		semaphores.cleanSession(event.getSession().getId() );
    	}
    }
}