package com.gem.sistema.business.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuNivelesDTO.
 */
public class MenuNivelesDTO {

	/** The clave menu. */
	private Integer claveMenu;
	
	/** The id menu padre. */
	private Integer idMenuPadre;
	
	/** The label. */
	private String label;
	
	/** The url. */
	private String url;
	
	/** The posicion. */
	private Integer posicion;
	
	/** The styleclass. */
	private String styleclass;
	
	/** The icon. */
	private String icon;
	
	/** The id sector. */
	private Integer idSector;
	
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
	 * Instantiates a new menu niveles DTO.
	 *
	 * @param claveMenu the clave menu
	 * @param idMenuPadre the id menu padre
	 * @param label the label
	 * @param url the url
	 * @param posicion the posicion
	 * @param styleclass the styleclass
	 * @param icon the icon
	 * @param idSector the id sector
	 */
	public MenuNivelesDTO(Integer claveMenu, Integer idMenuPadre, String label, String url, Integer posicion,
			String styleclass, String icon, Integer idSector) {
		super();
		this.claveMenu = claveMenu;
		this.idMenuPadre = idMenuPadre;
		this.label = label;
		this.url = url;
		this.posicion = posicion;
		this.styleclass = styleclass;
		this.icon = icon;
		this.idSector = idSector;
	}
	
}
