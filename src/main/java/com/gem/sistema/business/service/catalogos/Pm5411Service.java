package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm5411;
import com.gem.sistema.business.repository.catalogs.Pm5411Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm5411Service.
 */
@Service(value = "pm5411Service")
public class Pm5411Service {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Pm5411Service.class);
	
	/** The repository. */
	@Autowired
	private Pm5411Repository repository;
	
	/**
	 * Gets the repository.
	 *
	 * @return the repository
	 */
	public Pm5411Repository getRepository() {
		return repository;
	}

	/**
	 * Sets the repository.
	 *
	 * @param repository the new repository
	 */
	public void setRepository(Pm5411Repository repository) {
		this.repository = repository;
	}

	/**
	 * Save.
	 *
	 * @param entity the entity
	 * @return the pm 5411
	 */
	public Pm5411 save(Pm5411 entity) {
		LOGGER.info("Saving a id entry by using information: {}", entity);
		entity = repository.save(entity);
		LOGGER.info("Saved a id entry entry: {}", entity);
		return entity;
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the pm 5411
	 */
	public Pm5411 findById(Long id) {
		LOGGER.info("Finding id entry by using id: {}", id);
		Pm5411 result = repository.findOne(id);
		LOGGER.info("Found id entry: {}", result);
		return result;
	}

	/**
	 * Find all by sector.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@Transactional(readOnly = true)
	public List<Pm5411> findAllBySector(Integer idSector) {
		List<Pm5411> lista = repository.findAllByIdsectorOrderByMensualAsc(idSector);
		return lista;
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(Long id) {
		repository.delete(id);
	}
	
}
