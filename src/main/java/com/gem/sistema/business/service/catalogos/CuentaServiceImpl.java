/**
 * 
 */
package com.gem.sistema.business.service.catalogos;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.impl.CuentaRepositoryImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class CuentaServiceImpl.
 */
@Service("cuentaService")
@Transactional
final class CuentaServiceImpl implements CuentaService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CuentaServiceImpl.class);

	/** The repository. */
	@Autowired
	private CuentaRepository repository;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.CuentaService#save(com.gem.sistema.business.domain.Cuenta)
	 */
	@Override
	public Cuenta save(Cuenta newCuenta) {
		LOGGER.info("Saving a cuenta entry by using information: {}", newCuenta);

		Cuenta created = repository.save(newCuenta);
		LOGGER.info("Saved a cuenta entry entry: {}", created);

		return created;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.CuentaService#deleteCuenta(com.gem.sistema.business.domain.Cuenta)
	 */
	@Override
	public void deleteCuenta(Cuenta cuenta) {
		LOGGER.info("Deleting a cuenta entry : {}", cuenta);
		repository.delete(cuenta);
		LOGGER.info("Deleted cuenta entry: {}", cuenta);

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.CuentaService#findAll()
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Cuenta> findAll() {
		LOGGER.info("Finding all cuenta entries.");
		List<Cuenta> cuentaEntries = repository.findAll();
		LOGGER.info("Found {} cuenta entries", cuentaEntries.size());
		return cuentaEntries;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.CuentaService#findAll(org.springframework.data.domain.Pageable)
	 */
	@Transactional(readOnly = true)
	@Override
	public Page<Cuenta> findAll(Pageable pageRequest) {
		LOGGER.info("Finding all cuenta entries.");
		return repository.findAll(pageRequest);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.CuentaService#findCuentaById(java.lang.Long)
	 */
	@Transactional(readOnly = true)
	@Override
	public Cuenta findCuentaById(Long id) {
		LOGGER.info("Finding cuenta entry by using id: {}", id);
		Cuenta cuentaResult = repository.findOne(id);
		LOGGER.info("Found cuenta entry: {}", cuentaResult);
		return cuentaResult;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.CuentaService#findByDescription(java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Transactional(readOnly = true)
	@Override
	public Page<Cuenta> findByDescription(String searchTerm, Pageable pageRequest) {
		Page<Cuenta> searchResultPage;
		LOGGER.info("Finding cuenta entries by search term: {} and page request: {}", searchTerm, pageRequest);
		if (searchTerm == null || searchTerm.isEmpty()) {
			searchResultPage = repository.findAll(pageRequest);
		} else {
			searchResultPage = repository.findAll(pageRequest);
		}

		LOGGER.info("Found {} cuenta entries. Returned page {} contains {} cuenta entries",
				searchResultPage.getTotalElements(), searchResultPage.getNumber(),
				searchResultPage.getNumberOfElements());
		return searchResultPage;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.CuentaService#findAllByFilters(java.util.Map, org.springframework.data.domain.Pageable, java.lang.Integer)
	 */
	@Transactional(readOnly = true)
	@Override
	public Page<Cuenta> findAllByFilters(Map<String, Object> filters, Pageable pageRequest, Integer count) {
		Page<Cuenta> searchResultPage = null;

		LOGGER.info("Finding cuenta entries by search page request: {}", pageRequest);
		searchResultPage = repository.findAllByFilters(filters, pageRequest, count);
		LOGGER.info("Found {} cuenta entries. Returned page {} contains {} cuenta entries",
				searchResultPage.getTotalElements(), searchResultPage.getNumber(),
				searchResultPage.getNumberOfElements());

		return searchResultPage;
	}

	/**
	 * Count.
	 *
	 * @param filters the filters
	 * @return the integer
	 */
	@Override
	public Integer count(Map<String, Object> filters) {
		return this.repository.count(filters);
	}

	/**
	 * Gets the where from filters.
	 *
	 * @param filters the filters
	 * @return the where from filters
	 */
	@Override
	public String getWhereFromFilters(Map<String, Object> filters) {
		return CuentaRepositoryImpl.getWhereByFilters(filters);
	}

}
