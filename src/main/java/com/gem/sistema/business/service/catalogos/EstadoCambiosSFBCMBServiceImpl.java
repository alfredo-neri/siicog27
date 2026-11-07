package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.EstadoCambiosSFBCMBDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadoCambiosSFBCMBServiceImpl.
 */
@Service(value = "estadoCambiosSFBCMBService")
public class EstadoCambiosSFBCMBServiceImpl implements EstadoCambiosSFBCMBService {

	/** The estado cambios SFBCMBDAO. */
	@Autowired
	private EstadoCambiosSFBCMBDAO estadoCambiosSFBCMBDAO;

	/**
	 * Gets the estado cambios SFBCMBDAO.
	 *
	 * @return the estado cambios SFBCMBDAO
	 */
	public EstadoCambiosSFBCMBDAO getEstadoCambiosSFBCMBDAO() {
		return estadoCambiosSFBCMBDAO;
	}

	/**
	 * Sets the estado cambios SFBCMBDAO.
	 *
	 * @param estadoCambiosSFBCMBDAO the new estado cambios SFBCMBDAO
	 */
	public void setEstadoCambiosSFBCMBDAO(EstadoCambiosSFBCMBDAO estadoCambiosSFBCMBDAO) {
		this.estadoCambiosSFBCMBDAO = estadoCambiosSFBCMBDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.catalogos.EstadoCambiosSFBCMBService#
	 * generaQueryActivo(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String generaQueryActivo(Integer idsector, Integer mesInicial, Integer mesFinal) {

		return this.estadoCambiosSFBCMBDAO.generaQueryActivo(idsector, mesInicial, mesFinal, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.catalogos.EstadoCambiosSFBCMBService#
	 * generaQueryPasivo(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String generaQueryPasivo(Integer idsector, Integer mesInicial, Integer mesFinal) {

		return this.estadoCambiosSFBCMBDAO.generaQueryPasivo(idsector, mesInicial, mesFinal, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.catalogos.EstadoCambiosSFBCMBService#
	 * generaQuery3211(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String generaQuery3211(Integer idsector, Integer mesInicial, Integer mesFinal) {

		return this.estadoCambiosSFBCMBDAO.generaQuery3211(idsector, mesInicial, mesFinal, false);
	}

	@Override
	public String generaQueryActivo(Integer idsector, Integer mesInicial, Integer mesFinal,
			Boolean mesAnteriorAcumulado) {

		return this.estadoCambiosSFBCMBDAO.generaQueryActivo(idsector, mesInicial, mesFinal, mesAnteriorAcumulado);
	}

	@Override
	public String generaQueryPasivo(Integer idsector, Integer mesInicial, Integer mesFinal,
			Boolean mesAnteriorAcumulado) {

		return this.estadoCambiosSFBCMBDAO.generaQueryPasivo(idsector, mesInicial, mesFinal, mesAnteriorAcumulado);
	}

	@Override
	public String generaQueryHacienda(Integer idsector, Integer mesInicial, Integer mesFinal,
			Boolean mesAnteriorAcumulado) {
		// TODO Auto-generated method stub
		return this.estadoCambiosSFBCMBDAO.generaQueryHacienda(idsector, mesInicial, mesFinal, mesAnteriorAcumulado);
	}

	@Override
	public String generaQuery3211(Integer idsector, Integer mesInicial, Integer mesFinal,
			Boolean mesAnteriorAcumulado) {

		return this.estadoCambiosSFBCMBDAO.generaQuery3211(idsector, mesInicial, mesFinal, mesAnteriorAcumulado);
	}
}
