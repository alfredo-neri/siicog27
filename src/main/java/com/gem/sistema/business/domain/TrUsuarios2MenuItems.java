package com.gem.sistema.business.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TC_USUARIOS2MENUITEMS database table.
 * 
 */
@Entity
@Table(name = "TR_USUARIOS2MENUITEMS")
@NamedQuery(name = "TrUsuarios2MenuItems.findAll", query = "SELECT t FROM TrUsuarios2MenuItems t")
public class TrUsuarios2MenuItems implements Serializable, Comparable<TrUsuarios2MenuItems> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name="\"ID\"")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/** The id usuario. */
	@Column(name = "ID_USUARIO")
	private Integer idUsuario;
	
	/** The id menu item. */
	@Column(name = "ID_MENU_ITEM")
	private Integer idMenuItem;

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(TrUsuarios2MenuItems o) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Gets the id usuario.
	 *
	 * @return the id usuario
	 */
	public Integer getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Sets the id usuario.
	 *
	 * @param idUsuario the new id usuario
	 */
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Gets the id menu item.
	 *
	 * @return the id menu item
	 */
	public Integer getIdMenuItem() {
		return idMenuItem;
	}

	/**
	 * Sets the id menu item.
	 *
	 * @param idMenuItem the new id menu item
	 */
	public void setIdMenuItem(Integer idMenuItem) {
		this.idMenuItem = idMenuItem;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}


}