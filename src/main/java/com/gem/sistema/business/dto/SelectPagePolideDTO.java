package com.gem.sistema.business.dto;

import java.util.List;

import com.gem.sistema.business.domain.Polide;

// TODO: Auto-generated Javadoc
/**
 * The Class SelectPagePolideDTO.
 */
public class SelectPagePolideDTO {

	/** The page. */
	private Integer page;
	
	/** The renglon. */
	private Integer renglon;
	
	/** The posicion. */
	private Integer posicion;
	
	/** The list. */
	private List<Polide> list;
	
	/** The bandera. */
	private boolean bandera;

	/**
	 * Gets the page.
	 *
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * Sets the page.
	 *
	 * @param page the new page
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * Gets the renglon.
	 *
	 * @return the renglon
	 */
	public Integer getRenglon() {
		return renglon;
	}

	/**
	 * Sets the renglon.
	 *
	 * @param renglon the new renglon
	 */
	public void setRenglon(Integer renglon) {
		this.renglon = renglon;
	}

	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public List<Polide> getList() {
		return list;
	}

	/**
	 * Sets the list.
	 *
	 * @param list the new list
	 */
	public void setList(List<Polide> list) {
		this.list = list;
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
	 * Checks if is bandera.
	 *
	 * @return true, if is bandera
	 */
	public boolean isBandera() {
		return bandera;
	}

	/**
	 * Sets the bandera.
	 *
	 * @param bandera the new bandera
	 */
	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}

	/**
	 * Instantiates a new select page polide DTO.
	 */
	public SelectPagePolideDTO(){}

	/**
	 * Instantiates a new select page polide DTO.
	 *
	 * @param page the page
	 * @param renglon the renglon
	 * @param posicion the posicion
	 * @param list the list
	 * @param bandera the bandera
	 */
	public SelectPagePolideDTO(Integer page, Integer renglon, Integer posicion, List<Polide> list, boolean bandera) {
		this.page = page;
		this.renglon = renglon;
		this.posicion = posicion;
		this.list = list;
		this.bandera = bandera;
	}
	

}
