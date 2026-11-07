package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm4911;
import com.gem.sistema.business.repository.catalogs.Pm4911Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm4911ServiceImpl.
 */
@Service(value = "pm4911Service")
public class Pm4911ServiceImpl implements Pm4911Service {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Pm4911ServiceImpl.class);
	
	/** The repository. */
	@Autowired
	private Pm4911Repository repository;
	
	/**
	 * Gets the repository.
	 *
	 * @return the repository
	 */
	public Pm4911Repository getRepository() {
		return repository;
	}

	/**
	 * Sets the repository.
	 *
	 * @param repository the new repository
	 */
	public void setRepository(Pm4911Repository repository) {
		this.repository = repository;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4911Service#findAll()
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Pm4911> findAll() {
		LOGGER.info("Finding all id entries.");
		List<Pm4911> lista = repository.findAll();
		LOGGER.info("Found {} id entries", lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4911Service#findAll(org.springframework.data.domain.Pageable)
	 */
	@Transactional(readOnly = true)
	@Override
	public Page<Pm4911> findAll(Pageable pageRequest) {
		LOGGER.info("Finding all id entries.");
		return repository.findAll(pageRequest);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4911Service#save(com.gem.sistema.business.domain.Pm4911)
	 */
	@Override
	public Pm4911 save(Pm4911 entity) {
		LOGGER.info("Saving a id entry by using information: {}", entity);
		entity = repository.save(entity);
		LOGGER.info("Saved a id entry entry: {}", entity);
		return entity;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4911Service#findById(java.lang.Long)
	 */
	@Override
	public Pm4911 findById(Long id) {
		LOGGER.info("Finding id entry by using id: {}", id);
		Pm4911 result = repository.findOne(id);
		LOGGER.info("Found id entry: {}", result);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4911Service#findAllBySector(java.lang.Integer)
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Pm4911> findAllBySector(Integer idSector) {
		List<Pm4911> lista = repository.findAllByIdsectorOrderByMensualAsc(idSector);
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4911Service#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

}
