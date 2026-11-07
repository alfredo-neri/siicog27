package com.gem.sistema.business.domain;

import static com.gem.sistema.business.common.PreCondition.isTrue;
import static com.gem.sistema.business.common.PreCondition.notNull;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gem.sistema.annotation.ColumnFile;
import com.gem.sistema.annotation.IgnoredQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class Catdgm.
 */
@Entity
@Table(name = "catdgm")
public class Catdgm extends AbstractEntity {
	
	/** The Constant MAX_LENGTH_clave. */
	@IgnoredQuery
	static final int MAX_LENGTH_clave = 6;
	
	/** The Constant MAX_LENGTH_nombre. */
	@IgnoredQuery
	static final int MAX_LENGTH_nombre = 200;
	 
	/** The clave. */
	@Column(name = "clave", nullable = false, length = MAX_LENGTH_clave)
	private String clave;
	
	/** The nombre. */
	@Column(name = "nombre", nullable = false, length = MAX_LENGTH_nombre)
	private String nombre;
	 
	/** The fecha. */
	@Column(name = "fecha")
	private Date fecha;
	
	/** The editable. */
	@IgnoredQuery
	@Transient
	private String editable;

	/**
	 * Instantiates a new catdgm.
	 *
	 * @param clave the clave
	 * @param nombre the nombre
	 * @param fecha the fecha
	 */
	public Catdgm(String clave, String nombre, Date fecha) {
		this.clave = clave;
		this.nombre = nombre;
		this.fecha = fecha;
	}
	
	/**
	 * Instantiates a new catdgm.
	 */
	public Catdgm(){
	}
	
	/**
	 * Gets the clave.
	 *
	 * @return the clave
	 */
	@ColumnFile(indexColumn=1)
	public String getClave() {
		return clave;
	}

	/**
	 * Sets the clave.
	 *
	 * @param clave the new clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	@ColumnFile(indexColumn=2)
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
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha the new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
    /**
     * Gets the editable.
     *
     * @return the editable
     */
    public String getEditable() {
		return this.clave == null || this.clave.equals("") ? "visible" : "hidden";
	}

	/**
	 * Sets the editable.
	 *
	 * @param editable the new editable
	 */
	public void setEditable(String editable) {
		this.editable = editable;
	}

	/**
	 * Require valid clave.
	 *
	 * @param clave the clave
	 */
	public void requireValidClave(String clave) {
        notNull(clave, "Clave no puede ser nulo.");
        isTrue(clave.length() <= MAX_LENGTH_clave,
                "La máxima longitud de la clave es de <%d> caracteres.",
                MAX_LENGTH_clave
        );
    }
    
    /**
     * Require valid nombre.
     *
     * @param nombre the nombre
     */
    public void requireValidNombre(String nombre) {
        notNull(nombre, "Nombre no puede ser nulo.");
        isTrue(nombre.length() <= MAX_LENGTH_nombre,
                "La máxima longitud del nombre es de <%d> caracteres.",
                MAX_LENGTH_nombre
        );
    }
    
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
        return new ToStringBuilder(this)               
                .append("clave", this.clave)
                .append("nombre", this.nombre)               
                .append("fecha", this.fecha)
                .toString();
	}
}
