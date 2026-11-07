package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.Pbrm08DetailDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class Pbrm08DetailServiceImp.
 */
@Service(value ="pbrm08DetailService")
public class Pbrm08DetailServiceImp implements Pbrm08DetailService {
	
	/** The pbrm 08 detail DAO. */
	@Autowired
	private Pbrm08DetailDAO pbrm08DetailDAO;
	
	
	
	

	/**
	 * Gets the pbrm 08 detail DAO.
	 *
	 * @return the pbrm 08 detail DAO
	 */
	public Pbrm08DetailDAO getPbrm08DetailDAO() {
		return pbrm08DetailDAO;
	}

	/**
	 * Sets the pbrm 08 detail DAO.
	 *
	 * @param pbrm08DetailDAO the new pbrm 08 detail DAO
	 */
	public void setPbrm08DetailDAO(Pbrm08DetailDAO pbrm08DetailDAO) {
		this.pbrm08DetailDAO = pbrm08DetailDAO;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pbrm08DetailService#executeQueryHead(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String executeQueryHead(Integer idsector, Integer trim) {

		return this.pbrm08DetailDAO.executeQueryHead(idsector, trim);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pbrm08DetailService#executeQueryDetail(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String executeQueryDetail( Integer idsector, Integer trim) {

		return this.pbrm08DetailDAO.executeQueryDetail(idsector, trim);
	}

	
}
