package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm0211;
import com.gem.sistema.business.repository.catalogs.Pm0211Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm0211Service.
 */
@Service(value = "pm0211Service")
public class Pm0211Service {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Pm0211Service.class);
	
	/** The repository. */
	@Autowired
	private Pm0211Repository repository;
	
	/**
	 * Gets the repository.
	 *
	 * @return the repository
	 */
	public Pm0211Repository getRepository() {
		return repository;
	}

	/**
	 * Sets the repository.
	 *
	 * @param repository the new repository
	 */
	public void setRepository(Pm0211Repository repository) {
		this.repository = repository;
	}

	/**
	 * Save.
	 *
	 * @param entity the entity
	 * @return the pm 0211
	 */
	public Pm0211 save(Pm0211 entity) {
		LOGGER.info("Saving a id entry by using information: {}", entity);
		entity = repository.save(entity);
		LOGGER.info("Saved a id entry entry: {}", entity);
		return entity;
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the pm 0211
	 */
	public Pm0211 findById(Long id) {
		LOGGER.info("Finding id entry by using id: {}", id);
		Pm0211 result = repository.findOne(id);
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
	public List<Pm0211> findAllBySector(Integer idSector) {
		List<Pm0211> lista = repository.findAllByIdsectorOrderByMensualAsc(idSector);
		return lista;
	}
}
