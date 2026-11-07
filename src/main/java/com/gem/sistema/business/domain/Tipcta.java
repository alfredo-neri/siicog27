package com.gem.sistema.business.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gem.sistema.annotation.ColumnFile;
import com.gem.sistema.annotation.IgnoredQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class Tipcta.
 */
@Entity
@Table(name = "tipcta")
public class Tipcta extends AbstractEntity {

	/** The Constant MAX_LENGTH_CLVTIP. */
	@IgnoredQuery
    static final int MAX_LENGTH_CLVTIP = 2;
	
	/** The Constant MAX_LENGTH_CLVSTI. */
	@IgnoredQuery
    static final int MAX_LENGTH_CLVSTI = 2;
	
	/** The Constant MAX_LENGTH_NOMTIP. */
	@IgnoredQuery
    static final int MAX_LENGTH_NOMTIP = 80;
	
	/** The clvtip. */
	@Column(name = "clvtip", length = MAX_LENGTH_CLVTIP)
	private String clvtip;
	
	/** The clvsti. */
	@Column(name = "clvsti", length = MAX_LENGTH_CLVSTI)
	private String clvsti;
	
	/** The nomtip. */
	@Column(name = "nomtip", length = MAX_LENGTH_NOMTIP)
	private String nomtip;
	
	
	/**
	 * Instantiates a new tipcta.
	 */
	public Tipcta(){
    	
    }
    
	/**
	 * Instantiates a new tipcta.
	 *
	 * @param clvtip the clvtip
	 * @param clvsti the clvsti
	 * @param nomtip the nomtip
	 */
	public Tipcta(final String clvtip, final String clvsti, final String nomtip) {
		this.clvtip = clvtip;
		this.clvsti = clvsti;
		this.nomtip = nomtip;
	}

	/**
	 * Gets the clvtip.
	 *
	 * @return the clvtip
	 */
	@ColumnFile(indexColumn=1)
	public String getClvtip() {
		return clvtip;
	}

	/**
	 * Sets the clvtip.
	 *
	 * @param clvtip the clvtip to set
	 */
	public void setClvtip(String clvtip) {
		this.clvtip = clvtip;
	}

	/**
	 * Gets the clvsti.
	 *
	 * @return the clvsti
	 */
	@ColumnFile(indexColumn=2)
	public String getClvsti() {
		return clvsti;
	}

	/**
	 * Sets the clvsti.
	 *
	 * @param clvsti the clvsti to set
	 */
	public void setClvsti(String clvsti) {
		this.clvsti = clvsti;
	}

	/**
	 * Gets the nomtip.
	 *
	 * @return the nomtip
	 */
	@ColumnFile(indexColumn=3)
	public String getNomtip() {
		return nomtip;
	}

	/**
	 * Sets the nomtip.
	 *
	 * @param nomtip the nomtip to set
	 */
	public void setNomtip(String nomtip) {
		this.nomtip = nomtip;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tipcta [clvtip=" + clvtip + ", clvsti=" + clvsti + ", nomtip=" + nomtip + "]";
	}		
		
	
	
}
