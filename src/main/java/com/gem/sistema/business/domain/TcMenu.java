package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the TC_MENUS database table.
 * 
 */
@Entity
@Table(name = "TC_MENUS")
@NamedQuery(name = "TcMenu.findAll", query = "SELECT t FROM TcMenu t")
public class TcMenu extends AbstractEntity implements Serializable, Comparable<TcMenu> {
	
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

	/** The menu items. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
	private List<TcMenuItem> menuItems;

	/**
	 * Instantiates a new tc menu.
	 */
	public TcMenu() {
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
	 * @param icon            the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
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
	 * @param styleclass            the styleclass to set
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
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets the menu items.
	 *
	 * @return the menuItems
	 */
	public List<TcMenuItem> getMenuItems() {
		return menuItems;
	}

	/**
	 * Sets the menu items.
	 *
	 * @param menuItems            the menuItems to set
	 */
	public void setMenuItems(List<TcMenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	/*
	 * (non-Javadoc)
	 * 
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
	public int compareTo(TcMenu o) {
		return this.getId().intValue() - o.getId().intValue();
	}

}