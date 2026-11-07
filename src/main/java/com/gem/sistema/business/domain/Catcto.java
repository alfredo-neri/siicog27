package com.gem.sistema.business.domain;

import static com.gem.sistema.business.common.PreCondition.isTrue;
import static com.gem.sistema.business.common.PreCondition.notNull;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gem.sistema.annotation.IgnoredQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class Catcto.
 */
@Entity
@Table(name = "catcto")
public class Catcto  extends AbstractEntity{
	
	/** The Constant MAX_LENGTH_clvcto. */
	@IgnoredQuery
	static final int MAX_LENGTH_clvcto = 56;
	
	/** The Constant MAX_LENGTH_nomcto. */
	@IgnoredQuery
    static final int MAX_LENGTH_nomcto = 304;
    
    /** The clvcto. */
    @Column(name = "clvcto", nullable = false, length = MAX_LENGTH_clvcto)
    private String clvcto;
    
    /** The clvobr. */
    @Column(name = "clvobr")
    private Integer clvobr;
    
    /** The nomcto. */
    @Column(name = "nomcto", nullable = true, length = MAX_LENGTH_nomcto)
    private String nomcto;
    
    /** The fecini. */
    @Column(name = "fecini")
    private Date fecini;
    
    /** The fecfin. */
    @Column(name = "fecfin")
    private Date fecfin;
    
    /** The feccap. */
    @Column(name = "feccap")
    private Date feccap;
    
    /** The capcto. */
    @Column(name = "capcto")
    private String capcto;
    
    /** The clvprv. */
    @Column(name = "clvprv")
    private Integer clvprv;
    
    /** The monant. */
    @Column(name = "monant")
    private Float monant;
    
    /** The antpag. */
    @Column(name = "antpag")
    private Float antpag;
    
    /** The montot. */
    @Column(name = "montot")
    private Double montot;
    

	/**
	 * Instantiates a new catcto.
	 *
	 * @param clvcto the clvcto
	 * @param clvobr the clvobr
	 * @param nomcto the nomcto
	 * @param fecini the fecini
	 * @param fecfin the fecfin
	 * @param feccap the feccap
	 * @param capcto the capcto
	 * @param clvprv the clvprv
	 * @param monant the monant
	 * @param antpag the antpag
	 */
	public Catcto(String clvcto, Integer clvobr, String nomcto, Date fecini, Date fecfin, Date feccap, String capcto,
			Integer clvprv, Float monant, Float antpag) {
		this.clvcto = clvcto;
		this.clvobr = clvobr;
		this.nomcto = nomcto;
		this.fecini = fecini;
		this.fecfin = fecfin;
		this.feccap = feccap;
		this.capcto = capcto;
		this.clvprv = clvprv;
		this.monant = monant;
		this.antpag = antpag;
	}
    
	/**
	 * Instantiates a new catcto.
	 */
	public Catcto(){}

	/**
	 * Gets the clvcto.
	 *
	 * @return the clvcto
	 */
	public String getClvcto() {
		return clvcto;
	}

	/**
	 * Sets the clvcto.
	 *
	 * @param clvcto the new clvcto
	 */
	public void setClvcto(String clvcto) {
		this.clvcto = clvcto;
	}

	/**
	 * Gets the clvobr.
	 *
	 * @return the clvobr
	 */
	public Integer getClvobr() {
		return clvobr;
	}

	/**
	 * Sets the clvobr.
	 *
	 * @param clvobr the new clvobr
	 */
	public void setClvobr(Integer clvobr) {
		this.clvobr = clvobr;
	}

	/**
	 * Gets the nomcto.
	 *
	 * @return the nomcto
	 */
	public String getNomcto() {
		return nomcto;
	}

	/**
	 * Sets the nomcto.
	 *
	 * @param nomcto the new nomcto
	 */
	public void setNomcto(String nomcto) {
		this.nomcto = nomcto;
	}

	/**
	 * Gets the fecini.
	 *
	 * @return the fecini
	 */
	public Date getFecini() {
		return fecini;
	}

	/**
	 * Sets the fecini.
	 *
	 * @param fecini the new fecini
	 */
	public void setFecini(Date fecini) {
		this.fecini = fecini;
	}

	/**
	 * Gets the fecfin.
	 *
	 * @return the fecfin
	 */
	public Date getFecfin() {
		return fecfin;
	}

	/**
	 * Sets the fecfin.
	 *
	 * @param fecfin the new fecfin
	 */
	public void setFecfin(Date fecfin) {
		this.fecfin = fecfin;
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
	 * Gets the capcto.
	 *
	 * @return the capcto
	 */
	public String getCapcto() {
		return capcto;
	}

	/**
	 * Sets the capcto.
	 *
	 * @param capcto the new capcto
	 */
	public void setCapcto(String capcto) {
		this.capcto = capcto;
	}

	/**
	 * Gets the clvprv.
	 *
	 * @return the clvprv
	 */
	public Integer getClvprv() {
		return clvprv;
	}

	/**
	 * Sets the clvprv.
	 *
	 * @param clvprv the new clvprv
	 */
	public void setClvprv(Integer clvprv) {
		this.clvprv = clvprv;
	}

	/**
	 * Gets the monant.
	 *
	 * @return the monant
	 */
	public Float getMonant() {
		return monant;
	}

	/**
	 * Sets the monant.
	 *
	 * @param monant the new monant
	 */
	public void setMonant(Float monant) {
		this.monant = monant;
	}

	/**
	 * Gets the antpag.
	 *
	 * @return the antpag
	 */
	public Float getAntpag() {
		return antpag;
	}

	/**
	 * Sets the antpag.
	 *
	 * @param antpag the new antpag
	 */
	public void setAntpag(Float antpag) {
		this.antpag = antpag;
	}		
	
	
    /**
     * Gets the montot.
     *
     * @return the montot
     */
	public Double getMontot() {
		return montot;
	}

	/**
	 * Sets the montot.
	 *
	 * @param montot the montot to set
	 */
	public void setMontot(Double montot) {
		this.montot = montot;
	}

	/**
	 * Require valid clvcto.
	 *
	 * @param clvcto the clvcto
	 */
	public void requireValidClvcto(String clvcto) {
        notNull(clvcto, "Clave contrato no puede ser nulo.");
        isTrue(clvcto.length() <= MAX_LENGTH_clvcto,
                "La máxima longitud de la clave de contrato es de <%d> caracteres.",
                MAX_LENGTH_clvcto
        );
    }
    
    /**
     * Require valid nomcto.
     *
     * @param nomcto the nomcto
     */
    public void requireValidNomcto(String nomcto) {
        notNull(nomcto, "Nombre contrato no puede ser nulo.");
        isTrue(nomcto.length() <= MAX_LENGTH_nomcto,
                "La máxima longitud del nombre de contrato es de <%d> caracteres.",
                MAX_LENGTH_nomcto
        );
    }
    
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
        return new ToStringBuilder(this)             
        		.append("montot", this.montot)
                .append("clvcto", this.clvcto)
                .append("clvobr", this.clvobr)               
                .append("nomcto", this.nomcto)
                .append("fecini", this.fecini)
                .append("fecfin", this.fecfin)
                .append("capcto", this.capcto)
                .append("clvprv", this.clvprv)
                .append("monant", this.monant)
                .append("antpag", this.antpag)
                .toString();
	}
    
    
    
    
}
