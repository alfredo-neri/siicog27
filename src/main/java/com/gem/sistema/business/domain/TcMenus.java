package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TC_MENUS database table.
 * 
 */
@Entity
@Table(name="TC_MENUS")
@NamedQuery(name="TcMenus.findAll", query="SELECT t FROM TcMenus t")
public class TcMenus extends AbstractEntity implements  Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	/** The icon. */
	private String icon;

	/** The id menu padre. */
	@Column(name="ID_MENU_PADRE")
	private long idMenuPadre;

	/** The nombre. */
	private String nombre;

	/** The styleclass. */
	private String styleclass;

	/** The url. */
	private String url;

	/**
	 * Instantiates a new tc menus.
	 */
	public TcMenus() {
	}


	/**
	 * Gets the icon.
	 *
	 * @return the icon
	 */
	public String getIcon() {
		return this.icon;
	}

	/**
	 * Sets the icon.
	 *
	 * @param icon the new icon
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * Gets the id menu padre.
	 *
	 * @return the id menu padre
	 */
	public long getIdMenuPadre() {
		return this.idMenuPadre;
	}

	/**
	 * Sets the id menu padre.
	 *
	 * @param idMenuPadre the new id menu padre
	 */
	public void setIdMenuPadre(long idMenuPadre) {
		this.idMenuPadre = idMenuPadre;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return this.nombre;
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
	 * Gets the styleclass.
	 *
	 * @return the styleclass
	 */
	public String getStyleclass() {
		return this.styleclass;
	}

	/**
	 * Sets the styleclass.
	 *
	 * @param styleclass the new styleclass
	 */
	public void setStyleclass(String styleclass) {
		this.styleclass = styleclass;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}