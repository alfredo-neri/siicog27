package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.FirmasBS;
import com.gem.sistema.business.domain.Firmas;

// TODO: Auto-generated Javadoc
/**
 * The Class FirmasServiceImpl.
 */
@Service(value = "firmasService")
public class FirmasServiceImpl implements FirmasService {
	
	/** The firmas BS. */
	@Autowired
	private FirmasBS firmasBS;
	
	

	/**
	 * Gets the firmas BS.
	 *
	 * @return the firmas BS
	 */
	public FirmasBS getFirmasBS() {
		return firmasBS;
	}

	/**
	 * Sets the firmas BS.
	 *
	 * @param firmasBS the new firmas BS
	 */
	public void setFirmasBS(FirmasBS firmasBS) {
		this.firmasBS = firmasBS;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.FirmasService#save(com.gem.sistema.business.domain.Firmas)
	 */
	@Override
	public void save(Firmas entity) {
		
		this.firmasBS.save(entity);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.FirmasService#modify(com.gem.sistema.business.domain.Firmas)
	 */
	@Override
	public void modify(Firmas entity) {
		this.firmasBS.modify(entity);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.FirmasService#delete(com.gem.sistema.business.domain.Firmas)
	 */
	@Override
	public void delete(Firmas entity) {
		this.firmasBS.delete(entity);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.FirmasService#existFiirma(com.gem.sistema.business.domain.Firmas)
	 */
	@Override
	public Boolean existFirma(Firmas entity) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.FirmasService#findByIdSector(java.lang.Integer)
	 */
	@Override
	public Firmas findByIdSector(Integer idSector) {
		return this.firmasBS.findAllbyidSector(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.FirmasService#addElements(java.lang.Integer)
	 */
	@Override
	public Firmas addElements(Integer idSector) {
		return this.firmasBS.addElement(idSector);
	}

}
