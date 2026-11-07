package com.gem.sistema.business.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TC_MENU_NIVELES database table.
 * 
 */
@Entity
@Table(name = "TC_MENU_NIVELES")
@NamedQuery(name = "TcMenuNiveles.findAll", query = "SELECT t FROM TcMenuNiveles t")
public class TcMenuNiveles extends AbstractEntity implements Serializable, Comparable<TcMenuNiveles> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The clave menu. */
	@Column(name = "CLAVE_MENU")
	private Integer claveMenu;
	
	/** The id menu padre. */
	@Column(name = "ID_MENU_PADRE")
	private Integer idMenuPadre;
	
	/** The label. */
	@Column(name = "LABEL")
	private String label;
	
	/** The url. */
	@Column(name = "URL")
	private String url;
	
	/** The posicion. */
	@Column(name = "POSICION")
	private Integer posicion;
	
	/** The styleclass. */
	@Column(name = "STYLE_CLASS")
	private String styleclass;
	
	/** The icon. */
	@Column(name = "ICON")
	private String icon;

	/** The id sector. */
	@Column(name = "IDSECTOR")
	private Integer idSector;


	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(TcMenuNiveles o) {
		// TODO Auto-generated method stub
		return 0;
	}


	/**
	 * Gets the id menu padre.
	 *
	 * @return the id menu padre
	 */
	public Integer getIdMenuPadre() {
		return idMenuPadre;
	}


	/**
	 * Sets the id menu padre.
	 *
	 * @param idMenuPadre the new id menu padre
	 */
	public void setIdMenuPadre(Integer idMenuPadre) {
		this.idMenuPadre = idMenuPadre;
	}


	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}


	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}


	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * Gets the posicion.
	 *
	 * @return the posicion
	 */
	public Integer getPosicion() {
		return posicion;
	}


	/**
	 * Sets the posicion.
	 *
	 * @param posicion the new posicion
	 */
	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}


	/**
	 * Gets the styleclass.
	 *
	 * @return the styleclass
	 */
	public String getStyleclass() {
		return styleclass;
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
	 * Gets the icon.
	 *
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
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
	 * Gets the id sector.
	 *
	 * @return the id sector
	 */
	public Integer getIdSector() {
		return idSector;
	}


	/**
	 * Sets the id sector.
	 *
	 * @param idSector the new id sector
	 */
	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}


	/**
	 * Gets the clave menu.
	 *
	 * @return the clave menu
	 */
	public Integer getClaveMenu() {
		return claveMenu;
	}


	/**
	 * Sets the clave menu.
	 *
	 * @param claveMenu the new clave menu
	 */
	public void setClaveMenu(Integer claveMenu) {
		this.claveMenu = claveMenu;
	}	

}