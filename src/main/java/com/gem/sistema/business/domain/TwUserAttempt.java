package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TW_USER_ATTEMPTS database table.
 * 
 */
@Entity
@Table(name="TW_USER_ATTEMPTS")
@NamedQuery(name="TwUserAttempt.findAll", query="SELECT t FROM TwUserAttempt t")
public class TwUserAttempt extends AbstractEntity implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The attempts. */
	private Integer attempts;

	/** The lastmodified. */
	@Temporal(TemporalType.DATE)
	private Date lastmodified;

	/** The usuario. */
	private String usuario;

	/**
	 * Instantiates a new tw user attempt.
	 */
	public TwUserAttempt() {
	}



	/**
	 * Gets the attempts.
	 *
	 * @return the attempts
	 */
	public Integer getAttempts() {
		return attempts;
	}



	/**
	 * Sets the attempts.
	 *
	 * @param attempts the new attempts
	 */
	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}



	/**
	 * Gets the lastmodified.
	 *
	 * @return the lastmodified
	 */
	public Date getLastmodified() {
		return this.lastmodified;
	}

	/**
	 * Sets the lastmodified.
	 *
	 * @param lastmodified the new lastmodified
	 */
	public void setLastmodified(Date lastmodified) {
		this.lastmodified = lastmodified;
	}

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return this.usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}