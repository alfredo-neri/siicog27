package com.gem.sistema.business.domain;

import static com.gem.sistema.business.common.PreCondition.isTrue;
import static com.gem.sistema.business.common.PreCondition.notEmpty;
import static com.gem.sistema.business.common.PreCondition.notNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gem.sistema.annotation.IgnoredQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class TipoCuenta.
 */
@Entity
@Table(name = "cTipoCuenta")
public class TipoCuenta extends AbstractEntity {
	
	/** The Constant MAX_LENGTH_NOMBRE. */
	@IgnoredQuery
    static final int MAX_LENGTH_NOMBRE = 90;
    
    /** The id tipo cuenta. */
    @Column(name = "idTipoCuenta")
    private Integer idTipoCuenta;
    
    /** The s tipo. */
    @Column(name = "sTipo")
    private Integer sTipo;
    
    /** The nombre. */
    @Column(name = "nombre", nullable = false, length = MAX_LENGTH_NOMBRE)
    private String nombre;

    /**
     * Instantiates a new tipo cuenta.
     */
    public TipoCuenta(){
    	
    }
    
	/**
	 * Instantiates a new tipo cuenta.
	 *
	 * @param idTipoCuenta the id tipo cuenta
	 * @param sTipo the s tipo
	 * @param nombre the nombre
	 */
	public TipoCuenta(Integer idTipoCuenta, Integer sTipo, String nombre) {
		this.idTipoCuenta = idTipoCuenta;
		this.sTipo = sTipo;
		this.nombre = nombre;
	}

	/**
	 * Gets the id tipo cuenta.
	 *
	 * @return the id tipo cuenta
	 */
	public Integer getIdTipoCuenta() {
		return idTipoCuenta;
	}

	/**
	 * Sets the id tipo cuenta.
	 *
	 * @param idTipoCuenta the new id tipo cuenta
	 */
	public void setIdTipoCuenta(Integer idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}
		
	/**
	 * Gets the s tipo.
	 *
	 * @return the sTipo
	 */
	public Integer getsTipo() {
		return sTipo;
	}

	/**
	 * Sets the s tipo.
	 *
	 * @param sTipo the sTipo to set
	 */
	public void setsTipo(Integer sTipo) {
		this.sTipo = sTipo;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    
    /**
     * Require valid nombre.
     *
     * @param nombre the nombre
     */
    public void requireValidNombre(String nombre) {
        notNull(nombre, "Nombre no puede ser nulo.");
        notEmpty(nombre, "Titulo no puede ser vacio.");
        isTrue(nombre.length() <= MAX_LENGTH_NOMBRE,
                "La maxima longitud del nombre es de <%d> caracteres.",
                MAX_LENGTH_NOMBRE
        );

    }
    
    /* (non-Javadoc)
     * @see com.gem.sistema.business.domain.AbstractEntity#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)               
                .append("idTipoCuenta", this.idTipoCuenta)
                .append("sTipo", this.sTipo)               
                .append("nombre", this.nombre)
                .toString();
    }    

}
