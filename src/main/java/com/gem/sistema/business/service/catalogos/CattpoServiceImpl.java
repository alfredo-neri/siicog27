package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Cattpo;
import com.gem.sistema.business.repository.catalogs.CattpoRepository;


// TODO: Auto-generated Javadoc
/**
 * The Class CattpoServiceImpl.
 */
@Service("cattpoService")
public class CattpoServiceImpl implements CattpoService{
	
	/** The cattpo repository. */
	@Autowired
	private CattpoRepository cattpoRepository;
	
	/**
	 * Gets the cattpo repository.
	 *
	 * @return the cattpo repository
	 */
	public CattpoRepository getCattpoRepository() {
		return cattpoRepository;
	}
	
	/**
	 * Sets the cattpo repository.
	 *
	 * @param cattpoRepository the new cattpo repository
	 */
	public void setCattpoRepository(CattpoRepository cattpoRepository) {
		this.cattpoRepository = cattpoRepository;
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.CattpoService#getAll()
	 */
	@Override
	public List<Cattpo> getAll() {
		List<Cattpo> listCattpo = cattpoRepository.findAll();
		return listCattpo;
	}

}
