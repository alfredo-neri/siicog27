package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.TcCuentasAdicionales;

// TODO: Auto-generated Javadoc
/**
 * The Interface CuentaAdicionalService.
 *
 * @author Gerardo CUERO
 */
public interface CuentaAdicionalService {
	
	/**
	 * Find by cuenta.
	 *
	 * @param cuenta the cuenta
	 * @param adicional the adicional
	 * @param idSector the id sector
	 * @return the list
	 */
	List<TcCuentasAdicionales> findByCuenta(String cuenta, String adicional, Integer idSector);
	
	/**
	 * Gets the adds.
	 *
	 * @param polide the polide
	 * @param idSector the id sector
	 * @return the adds
	 */
	List<Polide> getAdds(Polide polide, Integer idSector);

}
