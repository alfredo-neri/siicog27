package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.Pm5611DAO;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm5611ServiceImpl.
 */
@Service(value = "pm5611Service")
public class Pm5611ServiceImpl implements Pm5611Service {
	
	/** The pm 5611 DAO. */
	@Autowired
	private Pm5611DAO pm5611DAO;
	
	/**
	 * Gets the pm 5611 DAO.
	 *
	 * @return the pm 5611 DAO
	 */
	public Pm5611DAO getPm5611DAO() {
		return pm5611DAO;
	}

	/**
	 * Sets the pm 5611 DAO.
	 *
	 * @param pm5611dao the new pm 5611 DAO
	 */
	public void setPm5611DAO(Pm5611DAO pm5611dao) {
		pm5611DAO = pm5611dao;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5611Service#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer mensual) {
		this.pm5611DAO.delete(mensual);
		
	}

}
