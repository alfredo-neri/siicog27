package com.gem.sistema.web.semaphore;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ObjectUnlock.
 */
@ManagedBean
@SessionScoped
public class ObjectUnlock implements Serializable,HttpSessionBindingListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectUnlock.class);

	/** The semaphores. */
	@ManagedProperty(value="#{semaphores}")
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
     * @see javax.servlet.http.HttpSessionBindingListener#valueBound(javax.servlet.http.HttpSessionBindingEvent)
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
     * @see javax.servlet.http.HttpSessionBindingListener#valueUnbound(javax.servlet.http.HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent event) {
    	LOGGER.info("valueUnBound: {} sessionid: {} semaphores {}"  ,event.getName(), event.getSession().getId(), semaphores );              
    	
    	if(!"objectUnlock".equals(event.getName())){
    		semaphores.cleanSession(event.getSession().getId() );
    	}
    }
}