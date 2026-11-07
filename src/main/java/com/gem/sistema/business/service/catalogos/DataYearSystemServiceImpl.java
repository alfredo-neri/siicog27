package com.gem.sistema.business.service.catalogos;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.repository.catalogs.ConctbRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class DataYearSystemServiceImpl.
 */
@Service("dataYearSystemService")
public class DataYearSystemServiceImpl implements DataYearSystemService{
	
	/** The conctb repository. */
	@Autowired
	private ConctbRepository conctbRepository;
	
	
	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}


	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}


	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.DataYearSystemService#getYear(java.lang.Integer)
	 */
	@Override
	public Integer getYear(Integer idSector) {
		return conctbRepository.findByIdsector(idSector).getAnoemp();
	}

}
