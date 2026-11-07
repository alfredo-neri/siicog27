package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.RF00912010DAO;
import com.gem.sistema.business.dto.RF00912010DTO;

// TODO: Auto-generated Javadoc
/**
 * The Class RF00912010ServiceImpl.
 */
@Service(value ="rF00912010Service")
public class RF00912010ServiceImpl implements RF00912010Service {
	
	/** The r F 00912010 DAO. */
	@Autowired
	private RF00912010DAO rF00912010DAO;
	
	/**
	 * Gets the r F 00912010 DAO.
	 *
	 * @return the r F 00912010 DAO
	 */
	public RF00912010DAO getrF00912010DAO() {
		return rF00912010DAO;
	}
	
	/**
	 * Sets the r F 00912010 DAO.
	 *
	 * @param rF00912010DAO the new r F 00912010 DAO
	 */
	public void setrF00912010DAO(RF00912010DAO rF00912010DAO) {
		this.rF00912010DAO = rF00912010DAO;
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.RF00912010Service#executeProcedure(com.gem.sistema.business.dto.RF00912010DTO)
	 */
	@Override
	public RF00912010DTO executeProcedure(RF00912010DTO parametersDTO) {
		return this.rF00912010DAO.executeProcedure(parametersDTO);
	}

}
