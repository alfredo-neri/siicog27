package com.gem.sistema.business.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

// TODO: Auto-generated Javadoc
/**
 * The Class TablaBase.
 */
@MappedSuperclass
public class TablaBase {
	
	/** The Constant MAX_LENGTH_capgas. */
	static final int MAX_LENGTH_capgas = 16;
	
	/** The Constant MAX_LENGTH_indcap. */
	static final int MAX_LENGTH_indcap = 2;

	/** The capgas. */
	@Column(name = "capgas", nullable = false, length = MAX_LENGTH_capgas)
	private String capgas;
	
	/** The feccap. */
	@Column(name = "feccap")
	private Date feccap;
	
	/** The indcap. */
	@Column(name = "indcap", nullable = false, length = MAX_LENGTH_indcap)
	private String indcap;
	
	
	
}
