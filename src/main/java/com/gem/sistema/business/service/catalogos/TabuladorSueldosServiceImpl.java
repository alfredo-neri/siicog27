package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.TabuladorSueldosDAO;
import com.gem.sistema.business.domain.Tsueldo;

// TODO: Auto-generated Javadoc
/**
 * The Class TabuladorSueldosServiceImpl.
 */
@Service("tabuladorSueldosServiceImpl")
public class TabuladorSueldosServiceImpl implements TabuladorSueldosService {
	
	/** The tabulador sueldos DAO. */
	@Autowired
	@Qualifier("tabuladorSueldosDAO")
	private TabuladorSueldosDAO tabuladorSueldosDAO;

	/**
	 * Gets the tabulador sueldos DAO.
	 *
	 * @return the tabulador sueldos DAO
	 */
	public TabuladorSueldosDAO getTabuladorSueldosDAO() {
		return tabuladorSueldosDAO;
	}

	/**
	 * Sets the tabulador sueldos DAO.
	 *
	 * @param tabuladorSueldosDAO the new tabulador sueldos DAO
	 */
	public void setTabuladorSueldosDAO(TabuladorSueldosDAO tabuladorSueldosDAO) {
		this.tabuladorSueldosDAO = tabuladorSueldosDAO;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.TabuladorSueldosService#execuet(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public Tsueldo execuet(String fileName, String filePath, String idUser, Integer idSector) {
		return this.tabuladorSueldosDAO.execute(fileName, filePath, idUser, idSector);
	}

}
