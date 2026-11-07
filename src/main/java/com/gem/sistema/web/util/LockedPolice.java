package com.gem.sistema.web.util;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.gem.sistema.web.security.model.GemUser;

// TODO: Auto-generated Javadoc
/**
 * The Class LockedPolice.
 */
public class LockedPolice implements Serializable, HttpSessionBindingListener {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3842891724925384429L;
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(LockedPolice.class);

	/** The Constant POLICIES_LOCK_PREFIX. */
	private static final String POLICIES_LOCK_PREFIX = "POLICE";

	/** The police key. */
	private String policeKey;

	/**
	 * Instantiates a new locked police.
	 *
	 * @param policeKey the police key
	 */
	public LockedPolice(String policeKey) {
		this.policeKey = policeKey;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionBindingListener#valueBound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void valueBound(HttpSessionBindingEvent event) {
		log.info("valueBound:" + event.getName() + " session:" + event.getSession().getId() );
	}

	/**
	 * Register session.
	 */
	public void registerSession() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "sessionBindingListener", this  );
		log.info( "registered sessionBindingListener"  );
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionBindingListener#valueUnbound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void valueUnbound(HttpSessionBindingEvent event) {
		log.info("valueUnBound:" + event.getName() + " session:" + event.getSession().getId() );
		ServletContext servletContext = event.getSession().getServletContext();
		if (servletContext.getAttribute(policeKey) != null) {
			String username = getUserDetails().getUsername();
			String attr = (String) servletContext.getAttribute(policeKey);
			if (attr.equals(username)) {
				servletContext.removeAttribute(policeKey);
			}
		}
	}

	/**
	 * Gets the user details.
	 *
	 * @return the user details
	 */
	public GemUser getUserDetails() {
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
			return (GemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} else {
			return null;
		}
	}

	/**
	 * Gets the police key.
	 *
	 * @return the police key
	 */
	public String getPoliceKey() {
		return policeKey;
	}

	/**
	 * Sets the police key.
	 *
	 * @param policeKey the new police key
	 */
	public void setPoliceKey(String policeKey) {
		this.policeKey = policeKey;
	}
}
