package com.gem.sistema.business.service.catalogos;

import java.math.BigDecimal;
import java.util.List;

import org.primefaces.model.StreamedContent;


import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.Poliend;



// TODO: Auto-generated Javadoc
/**
 * The Interface ConsultaPolizaSirve.
 */
public interface ConsultaPolizaSirve {
	
	/**
	 * Find policy.
	 *
	 * @param tipo the tipo
	 * @param mes the mes
	 * @param numero the numero
	 * @param idSector the id sector
	 * @return the streamed content
	 */
	public StreamedContent findPolicy(String tipo, int mes, int numero, Integer idSector);
	
	/**
	 * Find police by.
	 *
	 * @param tipo the tipo
	 * @param mes the mes
	 * @param numero the numero
	 * @param idSector the id sector
	 * @return the list
	 */
	public List<Polide> findPoliceBy(String tipo, int mes, int numero, Integer idSector);
	
	/**
	 * Wrong policy.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @return the list
	 */
	public List<Poliend> wrongPolicy(int mes, Integer idSector);
	
	/**
	 * Find cuentas.
	 *
	 * @param cuenta the cuenta
	 * @param sCta the s cta
	 * @param ssCta the ss cta
	 * @param sssCta the sss cta
	 * @param ssssCta the ssss cta
	 * @param idSector the id sector
	 * @return the list
	 */
	public List<Cuenta> findCuentas(final String cuenta, final String sCta, String ssCta, final String sssCta, final String ssssCta, Integer idSector );
	
	/**
	 * Find row.
	 *
	 * @param row the row
	 * @param mes the mes
	 * @param numero the numero
	 * @param tipo the tipo
	 * @param idSector the id sector
	 * @return the list
	 */
	public List<Polide> findRow(final BigDecimal row, final int mes, final int numero, final String tipo, Integer idSector);
	
	/**
	 * Poliz is true.
	 *
	 * @param tipo the tipo
	 * @param mes the mes
	 * @param numero the numero
	 * @param idSector the id sector
	 * @return the integer
	 */
	public Integer PolizIsTrue(String tipo, int mes, int numero, Integer idSector);
	
	/**
	 * Update registra.
	 *
	 * @param tipo the tipo
	 * @param mes the mes
	 * @param numero the numero
	 * @param idSector the id sector
	 */
	public void updateRegistra(String tipo, int mes, int numero, Integer idSector);
	
	/**
	 * Suma abono 8000.
	 *
	 * @param tipo the tipo
	 * @param mes the mes
	 * @param numero the numero
	 * @param idSector the id sector
	 * @return the string
	 */
	public String sumaAbono8000(String tipo, int mes, int numero, Integer idSector);
	
	/**
	 * Suma cargo 8000.
	 *
	 * @param tipo the tipo
	 * @param mes the mes
	 * @param numero the numero
	 * @param idSector the id sector
	 * @return the string
	 */
	public String sumaCargo8000(String tipo, int mes, int numero, Integer idSector);
	
	/**
	 * Checks if is police active.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @return the integer
	 */
	public Integer isPoliceActive(int mes,  int idSector);
	
	
	/**
	 * Gets the sum by cargo.
	 *
	 * @param mes the mes
	 * @param numero the numero
	 * @param tipo the tipo
	 * @param idSector the id sector
	 * @return the sum by cargo
	 */
	String getSumByCargo(Integer mes,  Integer numero, String tipo, Integer idSector);
	
	
	/**
	 * Gets the sum by abono.
	 *
	 * @param mes the mes
	 * @param numero the numero
	 * @param tipo the tipo
	 * @param idSector the id sector
	 * @return the sum by abono
	 */
	String getSumByAbono(Integer mes, Integer numero,  String tipo, Integer idSector);
	
	/**
	 * Update poliza.
	 *
	 * @param cControl the c control
	 * @param cargo the cargo
	 * @param aabonot the aabonot
	 * @param mes the mes
	 * @param numero the numero
	 * @param tpo the tpo
	 * @param idSector the id sector
	 * @return the integer
	 */
	Integer updatePoliza(BigDecimal cControl, BigDecimal cargo, BigDecimal aabonot, Integer mes, Integer numero, String tpo, Integer idSector);
	
	/**
	 * Exit poliza.
	 *
	 * @param mes the mes
	 * @param numero the numero
	 * @param tipo the tipo
	 * @param idSector the id sector
	 * @return the integer
	 */
	Integer exitPoliza(Integer mes, Integer numero, String tipo, Integer idSector);
	
	/**
	 * Find all counts.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Cuenta> findAllCounts(Integer idSector);
	
	/**
	 * Find active month.
	 *
	 * @param idSector the id sector
	 * @return the integer
	 */
	Integer findActiveMonth(int idSector);
	
	/**
	 * Find policy.
	 *
	 * @param idRegistraArchivo the id registra archivo
	 * @return the streamed content
	 */
	StreamedContent findPolicy(Long idRegistraArchivo);

}
