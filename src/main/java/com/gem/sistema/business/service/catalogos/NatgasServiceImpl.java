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

import com.gem.sistema.business.domain.Natgas;
import com.gem.sistema.business.repository.catalogs.NatgasRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class NatgasServiceImpl.
 */
@Service("natgasService")
@Transactional
final class NatgasServiceImpl implements NatgasService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(NatgasServiceImpl.class);

	/** The repository. */
	@Autowired
	private NatgasRepository repository;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.NatgasService#findAll()
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Natgas> findAll() {
		LOGGER.info("Finding all cuenta entries.");
		List<Natgas> cuentaEntries = repository.findAll();
		LOGGER.info("Found {} cuenta entries", cuentaEntries.size());
		return cuentaEntries;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.NatgasService#findAll(org.springframework.data.domain.Pageable)
	 */
	@Transactional(readOnly = true)
	@Override
	public Page<Natgas> findAll(Pageable pageRequest) {
		LOGGER.info("Finding all cuenta entries.");
		return repository.findAll(pageRequest);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.NatgasService#findAllByFilters(java.util.Map, org.springframework.data.domain.Pageable, java.lang.Integer)
	 */
	@Transactional(readOnly = true)
	@Override
	public Page<Natgas> findAllByFilters(Map<String, Object> filters, Pageable pageRequest, Integer count) {
		Page<Natgas> searchResultPage = null;

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

}
