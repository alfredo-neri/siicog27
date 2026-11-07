/**
 * 
 */
package com.gem.sistema.business.service.catalogos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.repository.catalogs.PolideRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class PolideServiceImpl.
 *
 * @author Gerardo CUERO
 */
@Service("polideService")
@Transactional
public class PolideServiceImpl implements PolideService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PolideServiceImpl.class);

	/** The repository. */
	@Autowired
	private PolideRepository repository;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.PolideService#save(com.gem.sistema.business.domain.Polide)
	 */
	@Override
	public Polide save(Polide newPolide) {
		LOGGER.info("Saving a Polide entry by using information: {}", newPolide);

		Polide created = repository.save(newPolide);
		LOGGER.info("Saved a Polide entry entry: {}", created);

		return created;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.PolideService#deletePolide(com.gem.sistema.business.domain.Polide)
	 */
	@Override
	public void deletePolide(Polide Polide) {
		LOGGER.info("Deleting a Polide entry : {}", Polide);
		repository.delete(Polide);
		LOGGER.info("Deleted Polide entry: {}", Polide);

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.PolideService#findAll()
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Polide> findAll() {
		LOGGER.info("Finding all Polide entries.");
		List<Polide> PolideEntries = repository.findAll();
		LOGGER.info("Found {} Polide entries", PolideEntries.size());
		return PolideEntries;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.PolideService#findAll(org.springframework.data.domain.Pageable)
	 */
	@Transactional(readOnly = true)
	@Override
	public Page<Polide> findAll(Pageable pageRequest) {
		LOGGER.info("Finding all Polide entries.");
		return repository.findAll(pageRequest);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.PolideService#findPolideById(java.lang.Long)
	 */
	@Transactional(readOnly = true)
	@Override
	public Polide findPolideById(Long id) {
		LOGGER.info("Finding Polide entry by using id: {}", id);
		Polide PolideResult = repository.findOne(id);
		LOGGER.info("Found Polide entry: {}", PolideResult);
		return PolideResult;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.PolideService#findByDescription(java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Transactional(readOnly = true)
	@Override
	public Page<Polide> findByDescription(String searchTerm, Pageable pageRequest) {
		Page<Polide> searchResultPage;
		LOGGER.info("Finding Polide entries by search term: {} and page request: {}", searchTerm, pageRequest);
		if (searchTerm == null || searchTerm.isEmpty()) {
			searchResultPage = repository.findAll(pageRequest);
		} else {
			searchResultPage = repository.findAll(pageRequest);
		}

		LOGGER.info("Found {} Polide entries. Returned page {} contains {} Polide entries",
				searchResultPage.getTotalElements(), searchResultPage.getNumber(),
				searchResultPage.getNumberOfElements());
		return searchResultPage;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.PolideService#findAllByFilters(java.util.Map, org.springframework.data.domain.Pageable, java.lang.Integer)
	 */
	@Transactional(readOnly = true)
	@Override
	public Page<Polide> findAllByFilters(Map<String, Object> filters, Pageable pageRequest, Integer count) {
		Page<Polide> searchResultPage = null;

		LOGGER.info("Finding Polide entries by search page request: {}", pageRequest);
		searchResultPage = repository.findAllByFilters(filters, pageRequest, count);
		LOGGER.info("Found {} Polide entries. Returned page {} contains {} Polide entries",
				searchResultPage.getTotalElements(), searchResultPage.getNumber(),
				searchResultPage.getNumberOfElements());

		return searchResultPage;
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.PolideService#findAllByFilters(java.util.Map, org.springframework.data.domain.Pageable, java.lang.Integer, java.lang.Integer)
	 */
	@Transactional(readOnly = true)
	@Override
	public Page<Polide> findAllByFilters(Map<String, Object> filters, Pageable pageRequest, Integer count, Integer renpol) {
		Page<Polide> searchResultPage = null;

		LOGGER.info("Finding Polide entries by search page request: {}", pageRequest);
		searchResultPage = repository.findAllByFilters(filters, pageRequest, count, renpol);
		LOGGER.info("Found {} Polide entries. Returned page {} contains {} Polide entries",
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
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.PolideService#actCargoAbono(com.gem.sistema.business.domain.Polien, java.lang.Integer)
	 */
	@Override
	public void actCargoAbono(Polien polien, Integer idSector){
		repository.actCargoAbono(polien, idSector);
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.PolideService#getLastRow(java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Polide getLastRow(Integer anopol, String tippol, Integer conpol, Integer idsector, Integer mespol){
		return repository.getLastRow(anopol, tippol, conpol, idsector, mespol);
	}

	 /* (non-Javadoc)
 	 * @see com.gem.sistema.business.service.catalogos.PolideService#buscarCantidadReferencia(java.math.BigDecimal, java.math.BigDecimal, java.lang.Integer)
 	 */
 	public List<Polide> buscarCantidadReferencia(BigDecimal cantidad, BigDecimal referencia, Integer idSector){
			return repository.buscarCantidadReferencia(cantidad, referencia, idSector);
		}

	 /* (non-Javadoc)
 	 * @see com.gem.sistema.business.service.catalogos.PolideService#consultaMovimientos(com.gem.sistema.business.domain.Polide)
 	 */
 	@Transactional
	 @Override
	 public List<Polide> consultaMovimientos(Polide polide){
		 return repository.consultaMovimientos(polide);
	 }
}
