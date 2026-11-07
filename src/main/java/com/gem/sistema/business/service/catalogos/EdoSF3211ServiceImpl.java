package com.gem.sistema.business.service.catalogos;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.EdoSF3211DAO;
import com.gem.sistema.business.dto.EdoSF3211DTO;
// TODO: Auto-generated Javadoc

/**
 * The Class EdoSF3211ServiceImpl.
 */
@Service(value = "edoSF3211Service")
public class EdoSF3211ServiceImpl implements EdoSF3211Service {

	/** The edo SF 3211 DAO. */
	@Autowired
	private EdoSF3211DAO edoSF3211DAO;

	/**
	 * Gets the edo SF 3211 DAO.
	 *
	 * @return the edo SF 3211 DAO
	 */
	public EdoSF3211DAO getEdoSF3211DAO() {
		return edoSF3211DAO;
	}

	/**
	 * Sets the edo SF 3211 DAO.
	 *
	 * @param edoSF3211DAO the new edo SF 3211 DAO
	 */
	public void setEdoSF3211DAO(EdoSF3211DAO edoSF3211DAO) {
		this.edoSF3211DAO = edoSF3211DAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.catalogos.EdoSF3211Service#executeQuery(java
	 * .lang.Integer, java.lang.Integer)
	 */
	@Override
	public EdoSF3211DTO executeQuery(Integer idsector, Integer mesInicial, Integer mesFinal) {

		return this.getEdoSF3211DAO().executeQuery(idsector, mesInicial, mesFinal, true);
	}

	@Override
	public EdoSF3211DTO executeQuery(Integer idsector, Integer mesInicial, Integer mesFinal,
			Boolean mesAnteriorAcumulado) {

		return this.getEdoSF3211DAO().executeQuery(idsector, mesInicial, mesFinal, mesAnteriorAcumulado);
	}

	@Override
	public EdoSF3211DTO getTotalActualAndAnteriorByAnio(Integer idsector, Integer mesFinal) {
		return this.getEdoSF3211DAO().getTotalActualAndAnteriorByAnio(idsector, mesFinal);
	}

	@Override
	public BigDecimal getTotalEjerciciosAnteriores(Integer idSector) {
		return this.getEdoSF3211DAO().getTotalEjerciciosAnteriores(idSector);
	}

	@Override
	public EdoSF3211DTO getTotalForEfectivoByAnio(Integer mesFinalActual, Integer mesFinalAnterior) {
		return this.getEdoSF3211DAO().getTotalForEfectivoByAnio(mesFinalActual, mesFinalAnterior);
	}

	@Override
	public EdoSF3211DTO getAnteriorByAnio(Integer mesFinalActual, Integer mesFinalAnterior) {
		return this.getEdoSF3211DAO().getAnteriorByAnio(mesFinalActual, mesFinalAnterior);
	}
}
