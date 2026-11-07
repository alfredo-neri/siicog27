package com.gem.sistema.ennum.constans;

// TODO: Auto-generated Javadoc
/**
 * The Enum LogicalConstansEnum.
 *
 * @author Mateo
 */
public enum LogicalConstansEnum {

	/** The mayor que. */
	MAYOR_QUE(1, ">="), /** The mayor igual. */
 MAYOR_IGUAL(2, ">="), /** The igual. */
 IGUAL(3, "="), /** The menor que. */
 MENOR_QUE(4, "<"), /** The menor igual. */
 MENOR_IGUAL(5, " <="), /** The inicie con. */
 INICIE_CON(6,
			"LIKE");

	/** The id logic. */
	private int idLogic;
	
	/** The desc logic. */
	private String descLogic;

	/**
	 * Instantiates a new logical constans enum.
	 *
	 * @param idLogic the id logic
	 * @param descLogic the desc logic
	 */
	private LogicalConstansEnum(int idLogic, String descLogic) {
		this.idLogic = idLogic;
		this.descLogic = descLogic;
	}

	/**
	 * Gext id logic.
	 *
	 * @return the int
	 */
	public int gextIdLogic() {
		return idLogic;
	}

	/**
	 * Gets the desc logic.
	 *
	 * @return the desc logic
	 */
	public String getDescLogic() {
		return descLogic;
	}

}
