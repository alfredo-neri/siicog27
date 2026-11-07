package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.dto.BalanzaDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface BalanzaComprovacionPreviewService.
 */
public interface BalanzaComprovacionPreviewService {

	/**
	 * Generate data.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @param anio the anio
	 * @return the string
	 */
	String generateData(Integer mes, Integer idSector, Integer anio);

}
