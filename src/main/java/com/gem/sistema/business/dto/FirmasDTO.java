package com.gem.sistema.business.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class FirmasDTO.
 *
 * @author Mateo
 */
public class FirmasDTO {

	/** The label. */
	private String label;
	
	/** The name. */
	private String name;

	/**
	 * Instantiates a new firmas DTO.
	 */
	public FirmasDTO() {
	}

	/**
	 * Instantiates a new firmas DTO.
	 *
	 * @param label the label
	 * @param name the name
	 */
	public FirmasDTO(String label, String name) {
		this.label = label;
		this.name = name;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
