package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm1411;
import com.gem.sistema.business.repository.catalogs.Pm1411Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm1411Service.
 */
@Service(value = "pm1411Service")
public class Pm1411Service {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Pm1411Service.class);
	
	/** The repository. */
	@Autowired
	private Pm1411Repository repository;
	
	/**
	 * Gets the repository.
	 *
	 * @return the repository
	 */
	public Pm1411Repository getRepository() {
		return repository;
	}

	/**
	 * Sets the repository.
	 *
	 * @param repository the new repository
	 */
	public void setRepository(Pm1411Repository repository) {
		this.repository = repository;
	}

	/**
	 * Save.
	 *
	 * @param entity the entity
	 * @return the pm 1411
	 */
	public Pm1411 save(Pm1411 entity) {
		LOGGER.info("Saving a id entry by using information: {}", entity);
		entity = repository.save(entity);
		LOGGER.info("Saved a id entry entry: {}", entity);
		return entity;
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the pm 1411
	 */
	public Pm1411 findById(Long id) {
		LOGGER.info("Finding id entry by using id: {}", id);
		Pm1411 result = repository.findOne(id);
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
	public List<Pm1411> findAllBySector(Integer idSector) {
		List<Pm1411> lista = repository.findAllByIdsectorOrderByMensualAsc(idSector);
		return lista;
	}
}
