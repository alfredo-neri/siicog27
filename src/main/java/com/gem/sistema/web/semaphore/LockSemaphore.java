package com.gem.sistema.web.semaphore;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class LockSemaphore.
 */
public class LockSemaphore {
	
	/** The id. */
	private PolienUK id;
	
	/** The id session. */
	private String idSession;
	
	/** The user. */
	private String user;
	
	/** The timestamp. */
	private Date timestamp;
	
	
	
	/**
	 * Instantiates a new lock semaphore.
	 *
	 * @param id the id
	 * @param idSession the id session
	 * @param user the user
	 */
	public LockSemaphore(PolienUK id, String idSession, String user) {
		super();
		this.id = id;
		this.idSession = idSession;
		this.user = user;
		this.timestamp = new Date();
	}
	

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public PolienUK getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(PolienUK id) {
		this.id = id;
	}
	
	/**
	 * Gets the id session.
	 *
	 * @return the id session
	 */
	public String getIdSession() {
		return idSession;
	}
	
	/**
	 * Sets the id session.
	 *
	 * @param idSession the new id session
	 */
	public void setIdSession(String idSession) {
		this.idSession = idSession;
	}
	
	/**
	 * Gets the user.
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
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return new ToStringBuilder(this)               
                .append("id", this.id)
                .append("idSession", this.idSession)               
                .append("user", this.user)
                .append("timestamp", this.timestamp)
                .toString();
	}
}
