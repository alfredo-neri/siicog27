package com.gem.sistema.ennum.constans;

// TODO: Auto-generated Javadoc
/**
 * The Enum QueryEnum.
 */
public enum QueryEnum {
	
	/** The seletc. */
	SELETC("SELECT * FROM PM0511"), 
 /** The where. */
 WHERE(" WHERE"), 
 /** The and. */
 AND("  AND"), 
 /** The eq. */
 EQ(" = "), 
 /** The may. */
 MAY_(">"), 
 /** The may eq. */
 MAY_EQ(">="), 
 /** The men. */
 MEN_(
			"<"), 
 /** The men eq. */
 MEN_EQ("<="), 
 /** The ini. */
 INI("LIKE "), 
 /** The porciento. */
 PORCIENTO("%"), 
 /** The comillas. */
 COMILLAS(
					"'"), 
 /** The trimestre. */
 TRIMESTRE("TRIMESTRE"), 
 /** The emergencia. */
 EMERGENCIA("EMERGENCIA"), 
 /** The id sector. */
 ID_SECTOR("IDSECTOR"), 
 /** The order by. */
 ORDER_BY("ORDER BY");
	
	/** The s sql. */
	private String sSql;

	/**
	 * Instantiates a new query enum.
	 *
	 * @param sSql the s sql
	 */
	private QueryEnum(String sSql) {
		this.sSql = sSql;
	}

	/**
	 * Gets the s sql.
	 *
	 * @return the s sql
	 */
	public String getsSql() {
		return sSql;
	}

}
