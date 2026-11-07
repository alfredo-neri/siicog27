package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm4311;
import com.gem.sistema.business.repository.catalogs.Pm4311Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm4311Service.
 */
@Service(value = "pm4311Service")
public class Pm4311Service {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Pm4311Service.class);
	
	/** The repository. */
	@Autowired
	private Pm4311Repository repository;
	
	/**
	 * Gets the repository.
	 *
	 * @return the repository
	 */
	public Pm4311Repository getRepository() {
		return repository;
	}

	/**
	 * Sets the repository.
	 *
	 * @param repository the new repository
	 */
	public void setRepository(Pm4311Repository repository) {
		this.repository = repository;
	}

	/**
	 * Save.
	 *
	 * @param entity the entity
	 * @return the pm 4311
	 */
	public Pm4311 save(Pm4311 entity) {
		LOGGER.info("Saving a id entry by using information: {}", entity);
		entity = repository.save(entity);
		LOGGER.info("Saved a id entry entry: {}", entity);
		return entity;
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the pm 4311
	 */
	public Pm4311 findById(Long id) {
		LOGGER.info("Finding id entry by using id: {}", id);
		Pm4311 result = repository.findOne(id);
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
	public List<Pm4311> findAllBySector(Integer idSector) {
		List<Pm4311> lista = repository.findAllByIdsectorOrderByMensualAsc(idSector);
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
