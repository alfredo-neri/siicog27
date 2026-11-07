package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.GenerateTotaleDAO;
import com.gem.sistema.business.dto.TotalMesesDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class GenerateTotaleServiceImpl.
 */
@Service(value = "generateTotaleService")
public class GenerateTotaleServiceImpl implements  GenerateTotaleService{
	
	/** The generate totale DAO. */
	@Autowired
	private GenerateTotaleDAO generateTotaleDAO;

	/**
	 * Gets the generate totale DAO.
	 *
	 * @return the generate totale DAO
	 */
	public GenerateTotaleDAO getGenerateTotaleDAO() {
		return generateTotaleDAO;
	}

	/**
	 * Sets the generate totale DAO.
	 *
	 * @param generateTotaleDAO the new generate totale DAO
	 */
	public void setGenerateTotaleDAO(GenerateTotaleDAO generateTotaleDAO) {
		this.generateTotaleDAO = generateTotaleDAO;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.GenerateTotaleService#generateTotales(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public TotalMesesDTO generateTotales(Integer idSector, String dependecia, String programa) {
		return this.generateTotaleDAO.generateTotales(idSector, dependecia, programa);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.GenerateTotaleService#generateTotales(java.lang.Integer)
	 */
	@Override
	public TotalMesesDTO generateTotales(Integer idSector, String programa) {
		return this.generateTotaleDAO.generateTotales(idSector, programa);
	}

}
