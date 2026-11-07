package com.gem.sistema.business.domain;

import static com.gem.sistema.business.common.PreCondition.isTrue;
import static com.gem.sistema.business.common.PreCondition.notEmpty;
import static com.gem.sistema.business.common.PreCondition.notNull;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gem.sistema.annotation.IgnoredQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class Catpro.
 */
@Entity
@Table(name = "catpro")
public class Catpro extends AbstractEntity{
	
	/** The Constant MAX_LENGTH_clvpro. */
	@IgnoredQuery
	static final int MAX_LENGTH_clvpro = 46;
	
	/** The Constant MAX_LENGTH_nompro. */
	@IgnoredQuery
	static final int MAX_LENGTH_nompro = 150;
	
	/** The Constant MAX_LENGTH_capgas. */
	@IgnoredQuery
	static final int MAX_LENGTH_capgas = 16;
	
	/** The Constant MAX_LENGTH_indcap. */
	@IgnoredQuery
	static final int MAX_LENGTH_indcap = 2;
	
	/** The clvpro. */
	@Column(name = "clvpro", nullable = false, length = MAX_LENGTH_clvpro)
    private String clvpro;
	
	/** The nompro. */
	@Column(name = "nompro", nullable = false, length = MAX_LENGTH_nompro)
    private String nompro;
	
	/** The cappro. */
	@Column(name = "cappro", length = MAX_LENGTH_capgas)
    private String cappro;
	
	/** The feccap. */
	@Column(name = "feccap")
    private Date feccap;
	
	/** The ultniv. */
	@Column(name = "ultniv", length = MAX_LENGTH_indcap)
    private String ultniv;

	/**
	 * Instantiates a new catpro.
	 *
	 * @param clvpro the clvpro
	 * @param nompro the nompro
	 * @param cappro the cappro
	 * @param feccap the feccap
	 * @param ultniv the ultniv
	 */
	public Catpro(String clvpro, String nompro, String cappro, Date feccap, String ultniv) {
		this.clvpro = clvpro;
		this.nompro = nompro;
		this.cappro = cappro;
		this.feccap = feccap;
		this.ultniv = ultniv;
	}
	
	/**
	 * Instantiates a new catpro.
	 */
	public Catpro(){
		
	}

	/**
	 * Gets the clvpro.
	 *
	 * @return the clvpro
	 */
	public String getClvpro() {
		return clvpro;
	}

	/**
	 * Sets the clvpro.
	 *
	 * @param clvpro the new clvpro
	 */
	public void setClvpro(String clvpro) {
		this.clvpro = clvpro;
	}

	/**
	 * Gets the nompro.
	 *
	 * @return the nompro
	 */
	public String getNompro() {
		return nompro;
	}

	/**
	 * Sets the nompro.
	 *
	 * @param nompro the new nompro
	 */
	public void setNompro(String nompro) {
		this.nompro = nompro;
	}

	/**
	 * Gets the cappro.
	 *
	 * @return the cappro
	 */
	public String getCappro() {
		return cappro;
	}

	/**
	 * Sets the cappro.
	 *
	 * @param cappro the new cappro
	 */
	public void setCappro(String cappro) {
		this.cappro = cappro;
	}

	/**
	 * Gets the feccap.
	 *
	 * @return the feccap
	 */
	public Date getFeccap() {
		return feccap;
	}

	/**
	 * Sets the feccap.
	 *
	 * @param feccap the new feccap
	 */
	public void setFeccap(Date feccap) {
		this.feccap = feccap;
	}

	/**
	 * Gets the ultniv.
	 *
	 * @return the ultniv
	 */
	public String getUltniv() {
		return ultniv;
	}

	/**
	 * Sets the ultniv.
	 *
	 * @param ultniv the new ultniv
	 */
	public void setUltniv(String ultniv) {
		this.ultniv = ultniv;
	}
	
    /**
     * Require valid clvpro.
     *
     * @param clvpro the clvpro
     */
    public void requireValidClvpro(String clvpro) {
        notNull(clvpro, "Clave no puede ser nulo.");
        notEmpty(clvpro, "Clave no puede ser vacio.");
        isTrue(clvpro.length() <= MAX_LENGTH_clvpro,
                "La maxima longitud de la clave es de <%d> caracteres.",
                MAX_LENGTH_clvpro
        );
    }
    
    /**
     * Require valid nompro.
     *
     * @param nompro the nompro
     */
    public void requireValidNompro(String nompro) {
        notNull(nompro, "Nombre no puede ser nulo.");
        notEmpty(nompro, "Nombre no puede ser vacio.");
        isTrue(nompro.length() <= MAX_LENGTH_nompro,
                "La maxima longitud del nombre es de <%d> caracteres.",
                MAX_LENGTH_nompro
        );
    }
    
    /* (non-Javadoc)
     * @see com.gem.sistema.business.domain.AbstractEntity#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)               
                .append("clvpro", this.clvpro)
                .append("nompro", this.nompro)               
                .append("cappro", this.cappro)
                .append("feccap", this.feccap)
                .append("ultniv", this.ultniv)
                .toString();
    }  

}
