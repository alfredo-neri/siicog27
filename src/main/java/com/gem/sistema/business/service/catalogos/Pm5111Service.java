package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm5111;
import com.gem.sistema.business.repository.catalogs.Pm5111Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm5111Service.
 */
@Service(value = "pm5111Service")
public class Pm5111Service {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Pm5111Service.class);
	
	/** The repository. */
	@Autowired
	private Pm5111Repository repository;
	
	/**
	 * Gets the repository.
	 *
	 * @return the repository
	 */
	public Pm5111Repository getRepository() {
		return repository;
	}

	/**
	 * Sets the repository.
	 *
	 * @param repository the new repository
	 */
	public void setRepository(Pm5111Repository repository) {
		this.repository = repository;
	}

	/**
	 * Save.
	 *
	 * @param entity the entity
	 * @return the pm 5111
	 */
	public Pm5111 save(Pm5111 entity) {
		LOGGER.info("Saving a id entry by using information: {}", entity);
		entity = repository.save(entity);
		LOGGER.info("Saved a id entry entry: {}", entity);
		return entity;
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the pm 5111
	 */
	public Pm5111 findById(Long id) {
		LOGGER.info("Finding id entry by using id: {}", id);
		Pm5111 result = repository.findOne(id);
		LOGGER.info("Found id entry: {}", result);
		return result;
	}


	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(Long id) {
		repository.delete(id);
	}
	
	/**
	 * Find all by sector.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@Transactional(readOnly = true)
	public List<Pm5111> findAllBySector(Integer idSector) {
		List<Pm5111> lista = repository.findAllByIdsectorOrderByTrimestreAsc(idSector);
		return lista;
	}
}
