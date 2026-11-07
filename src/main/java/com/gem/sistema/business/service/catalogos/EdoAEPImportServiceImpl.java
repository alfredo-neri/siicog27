/**
 * 
 */
package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.EdoAEPImportDAO;
import com.gem.sistema.business.dto.EdoAEPImportDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class EdoAEPImportServiceImpl.
 *
 * @author Gerardo CUERO
 */
@Service("edoAEPImportService")
public class EdoAEPImportServiceImpl implements EdoAEPImportService {

	/** The edo AEP import DAO. */
	@Autowired
	@Qualifier("edoAEPImportDAO")
	private EdoAEPImportDAO edoAEPImportDAO;

	/**
	 * Instantiates a new edo AEP import service impl.
	 */
	public EdoAEPImportServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.EdoAEPImportService#doImport(
	 * com.gem.sistema.business.dto.EdoAEPImportDTO)
	 */
	@Override
	public EdoAEPImportDTO doImport(EdoAEPImportDTO edoAEPImportDTO) {
		return this.edoAEPImportDAO.doImport(edoAEPImportDTO);
	}

	/**
	 * Gets the edo AEP import DAO.
	 *
	 * @return the edo AEP import DAO
	 */
	public EdoAEPImportDAO getEdoAEPImportDAO() {
		return edoAEPImportDAO;
	}

	/**
	 * Sets the edo AEP import DAO.
	 *
	 * @param edoAEPImportDAO the new edo AEP import DAO
	 */
	public void setEdoAEPImportDAO(EdoAEPImportDAO edoAEPImportDAO) {
		this.edoAEPImportDAO = edoAEPImportDAO;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.EdoAEPImportService#doImportIngreso(com.gem.sistema.business.dto.EdoAEPImportDTO)
	 */
	@Override
	public EdoAEPImportDTO doImportIngreso(EdoAEPImportDTO edoAEPImportDTO) {
		return this.edoAEPImportDAO.doImportIngreso(edoAEPImportDTO);
	}

}
