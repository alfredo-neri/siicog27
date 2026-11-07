package com.gem.sistema.business.domain;

import static com.gem.sistema.business.common.PreCondition.isTrue;
import static com.gem.sistema.business.common.PreCondition.notEmpty;
import static com.gem.sistema.business.common.PreCondition.notNull;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gem.sistema.annotation.ColumnFile;
import com.gem.sistema.annotation.IgnoredQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class Catben.
 */
@Entity
@Table(name = "catben")
public class Catben  extends AbstractEntity{
	
	/** The Constant MAX_LENGTH_nomben. */
	@IgnoredQuery
	static final int MAX_LENGTH_nomben = 80;
	
	/** The clvben. */
	@Column(name = "clvben")
    private BigDecimal clvben;
	
    /** The nomben. */
    @Column(name = "nomben", length = MAX_LENGTH_nomben)
    private String nomben;
    
    /** The corpla. */
    @Column(name = "corpla")
    private BigDecimal corpla;
    
    /** The larpla. */
    @Column(name = "larpla")
    private BigDecimal larpla;
    
    /** The clvben string. */
    @IgnoredQuery
    @Transient
    private String clvbenString;

	/**
	 * Instantiates a new catben.
	 *
	 * @param clvben the clvben
	 * @param nomben the nomben
	 * @param corpla the corpla
	 * @param larpla the larpla
	 */
	public Catben(BigDecimal clvben, String nomben, BigDecimal corpla, BigDecimal larpla) {
		this.clvben = clvben;
		this.nomben = nomben;
		this.corpla = corpla;
		this.larpla = larpla;
	}

	/**
	 * Instantiates a new catben.
	 */
	public Catben(){
		
	}

	/**
	 * Gets the clvben.
	 *
	 * @return the clvben
	 */
	@ColumnFile(indexColumn=1)
	public BigDecimal getClvben() {
		return clvben;
	}

	/**
	 * Sets the clvben.
	 *
	 * @param clvben the new clvben
	 */
	public void setClvben(BigDecimal clvben) {
		this.clvben = clvben;
	}

	/**
	 * Gets the nomben.
	 *
	 * @return the nomben
	 */
	@ColumnFile(indexColumn=2)
	public String getNomben() {
		return nomben;
	}

	/**
	 * Sets the nomben.
	 *
	 * @param nomben the new nomben
	 */
	public void setNomben(String nomben) {
		this.nomben = nomben;
	}

	/**
	 * Gets the corpla.
	 *
	 * @return the corpla
	 */
	public BigDecimal getCorpla() {
		return corpla;
	}

	/**
	 * Sets the corpla.
	 *
	 * @param corpla the new corpla
	 */
	public void setCorpla(BigDecimal corpla) {
		this.corpla = corpla;
	}

	/**
	 * Gets the larpla.
	 *
	 * @return the larpla
	 */
	public BigDecimal getLarpla() {
		return larpla;
	}

	/**
	 * Sets the larpla.
	 *
	 * @param larpla the new larpla
	 */
	public void setLarpla(BigDecimal larpla) {
		this.larpla = larpla;
	}
	
    /**
     * Gets the clvben string.
     *
     * @return the clvben string
     */
    public String getClvbenString() {
    	if (clvben != null) {
    		return clvben.toString();
    	} else {
    		return "";
    	}
	}

	/**
	 * Sets the clvben string.
	 *
	 * @param clvbenString the new clvben string
	 */
	public void setClvbenString(String clvbenString) {
		this.clvbenString = clvbenString;
		this.clvben = new BigDecimal(clvbenString);
	}

	/**
	 * Require valid nomben.
	 *
	 * @param nomben the nomben
	 */
	public void requireValidNomben(String nomben) {
        notNull(nomben, "Nombre no puede ser nulo.");
        notEmpty(nomben, "Nombre no puede ser vacio.");
        isTrue(nomben.length() <= MAX_LENGTH_nomben,
                "La maxima longitud del nombre es de <%d> caracteres.",
                MAX_LENGTH_nomben
        );
    }
    
    /* (non-Javadoc)
     * @see com.gem.sistema.business.domain.AbstractEntity#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)               
                .append("clvben", this.clvben)
                .append("nomben", this.nomben)               
                .append("corpla", this.corpla)
                .append("larpla", this.larpla)
                .toString();
    }   
    
}
