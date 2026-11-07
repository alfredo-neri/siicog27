package com.gem.sistema.business.service.catalogos;

import java.util.List;
import com.gem.sistema.business.domain.Copome;
import com.gem.sistema.business.domain.Polien;

// TODO: Auto-generated Javadoc
/**
 * The Interface CopomeService.
 */
public interface CopomeService {
	
	/**
	 * Gets the next nume.
	 *
	 * @param polien the polien
	 * @param idSector the id sector
	 * @param user the user
	 * @return the next nume
	 */
	Copome getNextNume(Polien polien, Integer idSector, String user);

  /**
   * Gets the all.
   *
   * @return the all
   */
  List<Copome> getAll();

  /**
   * Find by tpcpme.
   *
   * @param tpcpme the tpcpme
   * @param idSector the id sector
   * @return the copome
   */
  Copome findByTpcpme(String tpcpme, Integer idSector);

  /**
   * Save.
   *
   * @param copome the copome
   * @return the copome
   */
  Copome save(Copome copome);

	Integer getCurrentPolicyNumber(String tpcpme, Integer mecpme, Integer idSector);
}
