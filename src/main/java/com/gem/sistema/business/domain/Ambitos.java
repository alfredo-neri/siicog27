package com.gem.sistema.business.domain;

import static com.gem.sistema.business.common.PreCondition.isTrue;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gem.sistema.annotation.IgnoredQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class Ambitos.
 */
@Entity
@Table(name = "ambitos")
public class Ambitos extends AbstractEntity {
	
	/** The Constant MAX_LENGTH_descrip. */
	@IgnoredQuery
	static final int MAX_LENGTH_descrip = 400;
	
	/** The Constant MAX_LENGTH_capturo. */
	@IgnoredQuery
	static final int MAX_LENGTH_capturo = 50;
	
	/** The Constant MAX_LENGTH_campo1. */
	@IgnoredQuery
	static final int MAX_LENGTH_campo1 = 100;
	
	/** The cve ambito. */
	@Column(name = "cveAmbito")
	private Integer cveAmbito;
	
	/** The descrip. */
	@Column(name = "descrip", length = MAX_LENGTH_descrip)
	private String descrip;
	
	/** The capturo. */
	@Column(name = "capturo", length = MAX_LENGTH_capturo)
	private String capturo;
	
	/** The feccap. */
	@Column(name = "feccap")
	private Date feccap;
	
	/** The campo 1. */
	@Column(name = "campo1", length = MAX_LENGTH_campo1)
	private String campo1;
	
	/** The campo 2. */
	@Column(name = "campo2")
	private Float campo2;
	
    /**
     * Require valid descrip.
     *
     * @param descrip the descrip
     */
    public void requireValidDescrip(String descrip) {
        isTrue(descrip.length() <= MAX_LENGTH_descrip,
                "La máxima longitud de descrip es de <%d> caracteres.",
                MAX_LENGTH_descrip
        );
    }
    
    /**
     * Require valid capturo.
     *
     * @param capturo the capturo
     */
    //HOLA
    public void requireValidCapturo(String capturo) {
        isTrue(capturo.length() <= MAX_LENGTH_capturo,
                "La máxima longitud de capturo es de <%d> caracteres.",
                MAX_LENGTH_capturo
        );
    }
    
    /**
     * Require valid campo 1.
     *
     * @param campo1 the campo 1
     */
    public void requireValidCampo1(String campo1) {
        isTrue(campo1.length() <= MAX_LENGTH_campo1,
                "La máxima longitud de campo1 es de <%d> caracteres.",
                MAX_LENGTH_campo1
        );
    }

	/**
	 * Instantiates a new ambitos.
	 *
	 * @param cveAmbito the cve ambito
	 * @param descrip the descrip
	 * @param capturo the capturo
	 * @param feccap the feccap
	 * @param campo1 the campo 1
	 * @param campo2 the campo 2
	 */
	public Ambitos(Integer cveAmbito, String descrip, String capturo, Date feccap, String campo1, Float campo2) {
		this.cveAmbito = cveAmbito;
		this.descrip = descrip;
		this.capturo = capturo;
		this.feccap = feccap;
		this.campo1 = campo1;
		this.campo2 = campo2;
	}
	
	/**
	 * Instantiates a new ambitos.
	 */
	public Ambitos(){}

	/**
	 * Gets the cve ambito.
	 *
	 * @return the cve ambito
	 */
	public Integer getCveAmbito() {
		return cveAmbito;
	}

	/**
	 * Sets the cve ambito.
	 *
	 * @param cveAmbito the new cve ambito
	 */
	public void setCveAmbito(Integer cveAmbito) {
		this.cveAmbito = cveAmbito;
	}

	/**
	 * Gets the descrip.
	 *
	 * @return the descrip
	 */
	public String getDescrip() {
		return descrip;
	}

	/**
	 * Sets the descrip.
	 *
	 * @param descrip the new descrip
	 */
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	/**
	 * Gets the capturo.
	 *
	 * @return the capturo
	 */
	public String getCapturo() {
		return capturo;
	}

	/**
	 * Sets the capturo.
	 *
	 * @param capturo the new capturo
	 */
	public void setCapturo(String capturo) {
		this.capturo = capturo;
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
	 * Gets the campo 1.
	 *
	 * @return the campo 1
	 */
	public String getCampo1() {
		return campo1;
	}

	/**
	 * Sets the campo 1.
	 *
	 * @param campo1 the new campo 1
	 */
	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}

	/**
	 * Gets the campo 2.
	 *
	 * @return the campo 2
	 */
	public Float getCampo2() {
		return campo2;
	}

	/**
	 * Sets the campo 2.
	 *
	 * @param campo2 the new campo 2
	 */
	public void setCampo2(Float campo2) {
		this.campo2 = campo2;
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
        return new ToStringBuilder(this)               
                .append("cveAmbito", this.cveAmbito)
                .append("descrip", this.descrip)               
                .append("capturo", this.capturo)
                .append("feccap", this.feccap)
                .append("campo1", this.campo1)
                .append("campo2", this.campo2)
                .toString();
	}
    
    

}
