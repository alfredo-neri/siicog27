package com.gem.sistema.business.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TC_MENUS database table.
 * 
 */
@Entity
@Table(name = "TC_MENU_ITEMS")
@NamedQuery(name = "TcMenuItem.findAll", query = "SELECT t FROM TcMenuItem t")
public class TcMenuItem extends AbstractEntity implements Serializable, Comparable<TcMenuItem> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The icon. */
	@Column(name = "ICON")
	private String icon;

	/** The label. */
	@Column(name = "LABEL")
	private String label;

	/** The styleclass. */
	@Column(name = "STYLE_CLASS")
	private String styleclass;

	/** The url. */
	@Column(name = "URL")
	private String url;

	/** The menu. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MENU", nullable = false)
	private TcMenu menu;

	// @ManyToMany(fetch = FetchType.LAZY, mappedBy = "menuItems")
	// private List<TcUsuario> usuarios;

	/** The id sector. */
	@Column(name = "IDSECTOR")
	private Integer idSector;

	/**
	 * Instantiates a new tc menu item.
	 */
	public TcMenuItem() {
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
	 * @param label            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Gets the menu.
	 *
	 * @return the menu
	 */
	public TcMenu getMenu() {
		return menu;
	}

	/**
	 * Sets the menu.
	 *
	 * @param menu            the menu to set
	 */
	public void setMenu(TcMenu menu) {
		this.menu = menu;
	}

	/**
	 * Gets the id sector.
	 *
	 * @return the idSector
	 */
	public Integer getIdSector() {
		return idSector;
	}

	/**
	 * Sets the id sector.
	 *
	 * @param idSector            the idSector to set
	 */
	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
		return this.label;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(TcMenuItem o) {
		return this.getId().intValue() - o.getId().intValue();
	}
	
	

}