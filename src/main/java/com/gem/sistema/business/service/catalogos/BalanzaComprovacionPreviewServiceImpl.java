package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.BalanzaComprovacionPreviewDAO;
import com.gem.sistema.business.dto.BalanzaDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class BalanzaComprovacionPreviewServiceImpl.
 */
@Service(value = "balanzaComprovacionPreviewService")
public class BalanzaComprovacionPreviewServiceImpl implements BalanzaComprovacionPreviewService {
	
	/** The balanza comprovacion preview DAO. */
	@Autowired
	private BalanzaComprovacionPreviewDAO balanzaComprovacionPreviewDAO;

	/**
	 * Gets the balanza comprovacion preview DAO.
	 *
	 * @return the balanza comprovacion preview DAO
	 */
	public BalanzaComprovacionPreviewDAO getBalanzaComprovacionPreviewDAO() {
		return balanzaComprovacionPreviewDAO;
	}

	/**
	 * Sets the balanza comprovacion preview DAO.
	 *
	 * @param balanzaComprovacionPreviewDAO the new balanza comprovacion preview DAO
	 */
	public void setBalanzaComprovacionPreviewDAO(BalanzaComprovacionPreviewDAO balanzaComprovacionPreviewDAO) {
		this.balanzaComprovacionPreviewDAO = balanzaComprovacionPreviewDAO;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.BalanzaComprovacionPreviewService#generateData(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String generateData(Integer mes, Integer idSector, Integer anio) {
		return this.balanzaComprovacionPreviewDAO.generateData(mes, idSector, anio);
	}

}
