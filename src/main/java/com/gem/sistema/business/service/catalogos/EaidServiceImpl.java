package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.EaidDAO;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Eaid;

/**
 * @author mateo
 *
 */
@Service(value = "eaidService")
public class EaidServiceImpl implements EaidService {

	/**
	 * 
	 */
	@Autowired
	private EaidDAO eaidDAO;
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.EaidService#save(com.gem.sistema.business.domain.Eaid)
	 */
	@Override
	public boolean save(Eaid eaid) {
		
		return this.eaidDAO.save(eaid);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.EaidService#delete(com.gem.sistema.business.domain.Eaid)
	 */
	@Override
	public List<Eaid> delete(Eaid eaid) {
		
		return this.eaidDAO.delete(eaid);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.EaidService#update(com.gem.sistema.business.domain.Eaid)
	 */
	@Override
	public List<Eaid> update(Eaid eaid) {
		
		return this.eaidDAO.update(eaid);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.EaidService#findByTrimestre(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Eaid> findByTrimestre(Integer trimestre, Integer idSector) {
		
		return this.eaidDAO.findByTrimestre(trimestre, idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.EaidService#findByTrimestreAndCondition(java.lang.Integer, java.lang.String, java.lang.Integer)
	 */
	@Override
	public Eaid findByTrimestreAndCondition(Integer trimestre, String condicion, Integer idSector) {
		
		return this.eaidDAO.findByTrimestreAndCondition(trimestre, condicion, idSector);
	}

	public EaidDAO getEaidDAO() {
		return eaidDAO;
	}

	/**
	 * @param eaidDAO
	 */
	public void setEaidDAO(EaidDAO eaidDAO) {
		this.eaidDAO = eaidDAO;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.EaidService#findByIdSector(java.lang.Integer)
	 */
	@Override
	public List<Eaid> findByIdSector(Integer idSector) {
		
		return this.eaidDAO.findByIdSector(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.EaidService#findByIdSectorAndTrimestreAndCoonsecutivo(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Eaid findByIdSectorAndTrimestreAndCoonsecutivo(Integer idSector, Integer trimestre,
			Integer consecutivo) {
		
		return this.eaidDAO.findByIdSectorAndTrimestreAndCoonsecutivo(idSector, trimestre, consecutivo);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.EaidService#getAnioContable(java.lang.Integer, long)
	 */
	@Override
	public Conctb getAnioContable(Integer idSector, long idRef) {
		return this.eaidDAO.getAnioContable(idSector, idRef);
	}

	
	

}
