package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.ClasificacionObjGastoDAO;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;

@Service("clasificacionObjGastoService")
public class ClasificacionObjGastoServiceImpl implements ClasificacionObjGastoService {

	@Autowired
	private ClasificacionObjGastoDAO clasificacionObjGastoDAO;

	@Autowired
	private ConctbRepository conctbRepository;

	@Override
	public String generaTxtClasificacionObjGasto(Integer idSector, Integer trimestre) {

		Conctb conctb = conctbRepository.findByIdsector(idSector);

		String fileTXT = "EAEPECOGLDF" + conctb.getClave() + conctb.getAnoemp() + "0" + trimestre + ".txt";

		return this.clasificacionObjGastoDAO.exportTXT(idSector, trimestre, fileTXT);
	}

	public ClasificacionObjGastoDAO getClasificacionObjGastoDAO() {
		return clasificacionObjGastoDAO;
	}

	public void setClasificacionObjGastoDAO(ClasificacionObjGastoDAO clasificacionObjGastoDAO) {
		this.clasificacionObjGastoDAO = clasificacionObjGastoDAO;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

}
