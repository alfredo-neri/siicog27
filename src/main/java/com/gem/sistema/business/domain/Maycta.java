package com.gem.sistema.business.domain;

import static com.gem.sistema.business.common.PreCondition.isTrue;
import static com.gem.sistema.business.common.PreCondition.notNull;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gem.sistema.annotation.ColumnFile;
import com.gem.sistema.annotation.IgnoredQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class Maycta.
 */
@Entity
@Table(name = "maycta")
public class Maycta extends AbstractEntity implements Serializable{
	
	/** The Constant MAX_LENGTH_cuenta. */
	@IgnoredQuery
	static final int MAX_LENGTH_cuenta = 8;
	
	/** The Constant MAX_LENGTH_base. */
	@IgnoredQuery
    static final int MAX_LENGTH_base = 2;
	
	/** The Constant MAX_LENGTH_nommay. */
	@IgnoredQuery
    static final int MAX_LENGTH_nommay = 150;
	
	/** The cuenta. */
	@Column(name = "cuenta", nullable = false, length = MAX_LENGTH_cuenta)
    private String cuenta;
	
	/** The clvtip. */
	@Column(name = "clvtip", length = MAX_LENGTH_base)
    private String clvtip;
	
	/** The clvsti. */
	@Column(name = "clvsti", length = MAX_LENGTH_base)
    private String clvsti;
	
	/** The stacta. */
	@Column(name = "stacta", length = MAX_LENGTH_base)
    private String stacta;
	
	/** The notcta. */
	@Column(name = "notcta")
    private Integer notcta;
	
	/** The natcta. */
	@Column(name = "natcta", length = MAX_LENGTH_base)
    private String natcta;
	
	/** The nivcta. */
	@Column(name = "nivcta")
    private Integer nivcta;
	
	/** The nommay. */
	@Column(name = "nommay", length = MAX_LENGTH_cuenta)
    private String nommay;

	/**
	 * Instantiates a new maycta.
	 *
	 * @param cuenta the cuenta
	 * @param clvtip the clvtip
	 * @param clvsti the clvsti
	 * @param stacta the stacta
	 * @param notcta the notcta
	 * @param natcta the natcta
	 * @param nivcta the nivcta
	 * @param nommay the nommay
	 */
	public Maycta(String cuenta, String clvtip, String clvsti, String stacta, Integer notcta, String natcta,
			Integer nivcta, String nommay) {
		this.cuenta = cuenta;
		this.clvtip = clvtip;
		this.clvsti = clvsti;
		this.stacta = stacta;
		this.notcta = notcta;
		this.natcta = natcta;
		this.nivcta = nivcta;
		this.nommay = nommay;
	}
	
	/**
	 * Instantiates a new maycta.
	 */
	public Maycta(){
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	@ColumnFile(indexColumn=1)
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta the new cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the clvtip.
	 *
	 * @return the clvtip
	 */
	public String getClvtip() {
		return clvtip;
	}

	/**
	 * Sets the clvtip.
	 *
	 * @param clvtip the new clvtip
	 */
	public void setClvtip(String clvtip) {
		this.clvtip = clvtip;
	}

	/**
	 * Gets the clvsti.
	 *
	 * @return the clvsti
	 */
	public String getClvsti() {
		return clvsti;
	}

	/**
	 * Sets the clvsti.
	 *
	 * @param clvsti the new clvsti
	 */
	public void setClvsti(String clvsti) {
		this.clvsti = clvsti;
	}

	/**
	 * Gets the stacta.
	 *
	 * @return the stacta
	 */
	public String getStacta() {
		return stacta;
	}

	/**
	 * Sets the stacta.
	 *
	 * @param stacta the new stacta
	 */
	public void setStacta(String stacta) {
		this.stacta = stacta;
	}

	/**
	 * Gets the notcta.
	 *
	 * @return the notcta
	 */
	public Integer getNotcta() {
		return notcta;
	}

	/**
	 * Sets the notcta.
	 *
	 * @param notcta the new notcta
	 */
	public void setNotcta(Integer notcta) {
		this.notcta = notcta;
	}

	/**
	 * Gets the natcta.
	 *
	 * @return the natcta
	 */
	@ColumnFile(indexColumn=4)
	public String getNatcta() {
		return natcta;
	}

	/**
	 * Sets the natcta.
	 *
	 * @param natcta the new natcta
	 */
	public void setNatcta(String natcta) {
		this.natcta = natcta;
	}

	/**
	 * Gets the nivcta.
	 *
	 * @return the nivcta
	 */
	@ColumnFile(indexColumn=3)
	public Integer getNivcta() {
		return nivcta;
	}

	/**
	 * Sets the nivcta.
	 *
	 * @param nivcta the new nivcta
	 */
	public void setNivcta(Integer nivcta) {
		this.nivcta = nivcta;
	}

	/**
	 * Gets the nommay.
	 *
	 * @return the nommay
	 */
	@ColumnFile(indexColumn=2)
	public String getNommay() {
		return nommay;
	}

	/**
	 * Sets the nommay.
	 *
	 * @param nommay the new nommay
	 */
	public void setNommay(String nommay) {
		this.nommay = nommay;
	}
	
    /**
     * Require valid nommay.
     *
     * @param nommay the nommay
     */
    public void requireValidNommay(String nommay) {
        isTrue(nommay.length() <= MAX_LENGTH_nommay,
                "La máxima longitud del nombre es de <%d> caracteres.",
                MAX_LENGTH_nommay
        );
    }
	
    /**
     * Require valid cuenta.
     *
     * @param cuenta the cuenta
     */
    public void requireValidCuenta(String cuenta) {
    	notNull(cuenta, "Cuenta no puede ser nulo.");
    	isTrue(cuenta.length() <= MAX_LENGTH_cuenta,
                "La máxima longitud de la cuenta es de <%d> caracteres.",
                MAX_LENGTH_cuenta
        );
    }
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
        return new ToStringBuilder(this)
        		.append("id", this.getId())
        		.append("index", this.getIndex())
                .append("cuenta", this.cuenta)
                .append("clvtip", this.clvtip)               
                .append("clvsti", this.clvsti)
                .append("stacta", this.stacta)
                .append("notcta", this.notcta)
                .append("natcta", this.natcta)
                .append("nivcta", this.nivcta)
                .append("nommay", this.nommay)
                .toString();
	}
	
	
	

}
