package com.gem.sistema.business.domain;

import static com.gem.sistema.business.common.PreCondition.isTrue;
import static com.gem.sistema.business.common.PreCondition.notEmpty;
import static com.gem.sistema.business.common.PreCondition.notNull;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gem.sistema.annotation.ColumnFile;
import com.gem.sistema.annotation.IgnoredQuery;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the NATGAS database table.
 * 
 */
@Entity
@Table(name = "NATGAS")
public class Natgas  extends AbstractEntity{
	
	/** The Constant MAX_LENGTH_clvgas. */
	@IgnoredQuery
	static final int MAX_LENGTH_clvgas = 8;
	
	/** The Constant MAX_LENGTH_nomgas. */
	@IgnoredQuery
	static final int MAX_LENGTH_nomgas = 150;
	
	/** The Constant MAX_LENGTH_capgas. */
	@IgnoredQuery
	static final int MAX_LENGTH_capgas = 16;
	
	/** The Constant MAX_LENGTH_indcap. */
	@IgnoredQuery
	static final int MAX_LENGTH_indcap = 2;
	
	/** The clvgas. */
	@Column(name = "clvgas", nullable = false, length = MAX_LENGTH_clvgas)
    private String clvgas;
	
	/** The nomgas. */
	@Column(name = "nomgas", nullable = false, length = MAX_LENGTH_nomgas)
    private String nomgas;
	
	/** The capgas. */
	@Column(name = "capgas", nullable = true, length = MAX_LENGTH_capgas)
    private String capgas;
	
	/** The feccap. */
	@Column(name = "feccap")
    private Date feccap;

	/** The id ref. */
	@Column(name = "ID_REF")
	private Long idRef;

	/** The idsector. */
	@Column(name = "IDSECTOR")
	private Integer idsector;
	
	/** The indcap. */
	@Column(name = "indcap", nullable = true, length = MAX_LENGTH_indcap)
    private String indcap;

	/** The userid. */
	@Column(name = "USERID")
	private String userid;

	/**
	 * Instantiates a new natgas.
	 *
	 * @param clvgas the clvgas
	 * @param nomgas the nomgas
	 * @param capgas the capgas
	 * @param feccap the feccap
	 * @param indcap the indcap
	 */
	public Natgas(String clvgas, String nomgas, String capgas, Date feccap, String indcap) {
		this.clvgas = clvgas;
		this.nomgas = nomgas;
		this.capgas = capgas;
		this.feccap = feccap;
		this.indcap = indcap;
	}
	
	/**
	 * Instantiates a new natgas.
	 */
	public Natgas(){
		
	}

	/**
	 * Gets the clvgas.
	 *
	 * @return the clvgas
	 */
	@ColumnFile(indexColumn=1)
	public String getClvgas() {
		return clvgas;
	}

	/**
	 * Sets the clvgas.
	 *
	 * @param clvgas the new clvgas
	 */
	public void setClvgas(String clvgas) {
		this.clvgas = clvgas;
	}

	/**
	 * Gets the nomgas.
	 *
	 * @return the nomgas
	 */
	@ColumnFile(indexColumn=2)
	public String getNomgas() {
		return nomgas;
	}

	/**
	 * Sets the nomgas.
	 *
	 * @param nomgas the new nomgas
	 */
	public void setNomgas(String nomgas) {
		this.nomgas = nomgas;
	}

	/**
	 * Gets the capgas.
	 *
	 * @return the capgas
	 */
	public String getCapgas() {
		return capgas;
	}

	/**
	 * Sets the capgas.
	 *
	 * @param capgas the new capgas
	 */
	public void setCapgas(String capgas) {
		this.capgas = capgas;
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
	 * Gets the id ref.
	 *
	 * @return the id ref
	 */
	public Long getIdRef() {
		return this.idRef;
	}

	/**
	 * Sets the id ref.
	 *
	 * @param idRef the new id ref
	 */
	public void setIdRef(Long idRef) {
		this.idRef = idRef;
	}

	/**
	 * Gets the idsector.
	 *
	 * @return the idsector
	 */
	public Integer getIdsector() {
		return this.idsector;
	}

	/**
	 * Sets the idsector.
	 *
	 * @param idsector the new idsector
	 */
	public void setIdsector(Integer idsector) {
		this.idsector = idsector;
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
	 * Gets the indcap.
	 *
	 * @return the indcap
	 */
	public String getIndcap() {
		return indcap;
	}

	/**
	 * Sets the indcap.
	 *
	 * @param indcap the new indcap
	 */
	public void setIndcap(String indcap) {
		this.indcap = indcap;
	}
	
    /**
     * Require valid clvgas.
     *
     * @param clvgas the clvgas
     */
    public void requireValidClvgas(String clvgas) {
        notNull(clvgas, "Clave no puede ser nulo.");
        notEmpty(clvgas, "Clave no puede ser vacio.");
        isTrue(clvgas.length() <= MAX_LENGTH_clvgas,
                "La maxima longitud de la clave es de <%d> caracteres.",
                MAX_LENGTH_clvgas
        );
    }
    
    /**
     * Require valid nomgas.
     *
     * @param nomgas the nomgas
     */
    public void requireValidNomgas(String nomgas) {
        notNull(nomgas, "Nombre no puede ser nulo.");
        notEmpty(nomgas, "Nombre no puede ser vacio.");
        isTrue(nomgas.length() <= MAX_LENGTH_nomgas,
                "La maxima longitud del nombre es de <%d> caracteres.",
                MAX_LENGTH_nomgas
        );
    }
	
    /* (non-Javadoc)
     * @see com.gem.sistema.business.domain.AbstractEntity#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }  

	/**
	 * Gets the userid.
	 *
	 * @return the userid
	 */
	public String getUserid() {
		return this.userid;
	}

	/**
	 * Sets the userid.
	 *
	 * @param userid the new userid
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

}
