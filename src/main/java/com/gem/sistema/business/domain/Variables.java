package com.gem.sistema.business.domain;

import static com.gem.sistema.business.common.PreCondition.isTrue;
import static com.gem.sistema.business.common.PreCondition.notNull;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gem.sistema.annotation.ColumnFile;
import com.gem.sistema.annotation.IgnoredQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class Variables.
 */
@Entity
@Table(name = "variables")
public class Variables extends AbstractEntity {
	
	/** The Constant MAX_LENGTH_cvevar. */
	@IgnoredQuery
	static final int MAX_LENGTH_cvevar = 60;
	
	/** The Constant MAX_LENGTH_nomvar. */
	@IgnoredQuery
	static final int MAX_LENGTH_nomvar = 400;
	
	/** The Constant MAX_LENGTH_usuario. */
	@IgnoredQuery
	static final int MAX_LENGTH_usuario = 50;
	
	private Integer numvar;

    /** The cvevar. */
    @Column(name = "cvevar", nullable = false, length = MAX_LENGTH_cvevar)
    private String cvevar;
    
    /** The nomvar. */
    @Column(name = "nomvar", length = MAX_LENGTH_nomvar)
    private String nomvar;
    
    /** The usuario. */
    @Column(name = "usuario", length = MAX_LENGTH_usuario)
    private String usuario;
    
	/** The feccap. */
	@Column(name = "feccap")
	private Date feccap;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_MIR", nullable = false)
	private Mir mir;
	
    /**
     * Require valid cvevar.
     *
     * @param cvevar the cvevar
     */
    public void requireValidCvevar(String cvevar) {
        notNull(cvevar, "cvevar no puede ser nulo.");
        isTrue(cvevar.length() <= MAX_LENGTH_cvevar,
                "La máxima longitud de cvevar es de <%d> caracteres.",
                MAX_LENGTH_cvevar
        );
    }
    
    /**
     * Require valid nomvar.
     *
     * @param nomvar the nomvar
     */
    public void requireValidNomvar(String nomvar) {
        notNull(nomvar, "nomvar no puede ser nulo.");
        isTrue(nomvar.length() <= MAX_LENGTH_nomvar,
                "La máxima longitud de nomvar es de <%d> caracteres.",
                MAX_LENGTH_nomvar
        );
    }

	/**
	 * Instantiates a new variables.
	 *
	 * @param cvevar the cvevar
	 * @param nomvar the nomvar
	 * @param usuario the usuario
	 * @param feccap the feccap
	 */
	public Variables(Integer numvar, String cvevar, String nomvar, String usuario, Date feccap) {
		this.numvar = numvar;
		this.cvevar = cvevar;
		this.nomvar = nomvar;
		this.usuario = usuario;
		this.feccap = feccap;
	}
	
	/**
	 * Instantiates a new variables.
	 */
	public Variables(){
		this.numvar = BigInteger.ZERO.intValue();
		this.cvevar = StringUtils.EMPTY;
		this.nomvar = StringUtils.EMPTY;
	}

	public Integer getNumvar() {
		return numvar;
	}

	public void setNumvar(Integer numvar) {
		this.numvar = numvar;
	}

	/**
	 * Gets the cvevar.
	 *
	 * @return the cvevar
	 */
	@ColumnFile(indexColumn=1)
	public String getCvevar() {
		return cvevar;
	}

	/**
	 * Sets the cvevar.
	 *
	 * @param cvevar the new cvevar
	 */
	public void setCvevar(String cvevar) {
		this.cvevar = cvevar;
	}

	/**
	 * Gets the nomvar.
	 *
	 * @return the nomvar
	 */
	@ColumnFile(indexColumn=2)
	public String getNomvar() {
		return nomvar;
	}

	/**
	 * Sets the nomvar.
	 *
	 * @param nomvar the new nomvar
	 */
	public void setNomvar(String nomvar) {
		this.nomvar = nomvar;
	}

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	
	public Mir getMir() {
		return mir;
	}

	public void setMir(Mir mir) {
		this.mir = mir;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
        return new ToStringBuilder(this)               
                .append("cvevar", this.cvevar)
                .append("nomvar", this.nomvar)               
                .append("usuario", this.usuario)
                .append("feccap", this.feccap)
                .toString();
	}
    
    
	

}
