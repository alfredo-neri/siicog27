package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;


import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm3311BS;
import com.gem.sistema.business.domain.Pm3311;
import com.gem.sistema.business.repository.catalogs.Pm3311Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm3311BSImpl.
 *
 * @author Alfredo
 */
@Repository(value = "pm3311BS")
public class Pm3311BSImpl implements Pm3311BS{
	
	/** The Constant NULL_VALUE. */
	private static final Integer NULL_VALUE = null;
	
	/** The Constant EMPTY_VALUE. */
	private static final String EMPTY_VALUE = "";
	
	/** The pm 3311 repository. */
	@Autowired
	private Pm3311Repository pm3311Repository;

	/**
	 * Gets the pm 3311 repository.
	 *
	 * @return the pm 3311 repository
	 */
	public Pm3311Repository getPm3311Repository() {
		return pm3311Repository;
	}

	/**
	 * Sets the pm 3311 repository.
	 *
	 * @param pm3311Repository the new pm 3311 repository
	 */
	public void setPm3311Repository(Pm3311Repository pm3311Repository) {
		this.pm3311Repository = pm3311Repository;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3311BS#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public boolean save(Pm3311 pm3311) {
		boolean bandera = findByAnual(pm3311.getIdsector(), pm3311.getAnual());
		if(bandera) {
			bandera = this.validateTxt(pm3311);
			if(bandera){
				pm3311Repository.save(pm3311);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");
			}
			
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El año ya existe");
		}
		return bandera;
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3311BS#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public void delete(Pm3311 pm3311) {
		pm3311Repository.delete(pm3311);
		
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3311BS#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public Pm3311 clean(Pm3311 pm3311) {
		pm3311.setAnual(NULL_VALUE);
		pm3311.setObspttm(EMPTY_VALUE);
		pm3311.setObsspaem(EMPTY_VALUE);
		pm3311.setSpaem(NULL_VALUE);
		pm3311.setPttm(NULL_VALUE);
		return pm3311;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3311BS#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public void cancel(Pm3311 pm3311) {
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3311BS#validateTxt(java.lang.Integer, java.util.List)
	 */
	
	@Override
	public boolean validateTxt(Pm3311 pm3311) {
		boolean  bandera = Boolean.TRUE;
        if(null == pm3311.getSpaem()) {
        	pm3311.setSpaem(0);
        }
        if(null == pm3311.getObsspaem()) {
           pm3311.setObsspaem(EMPTY_VALUE);
        }
        if(null == pm3311.getPttm()) {
            pm3311.setPttm(0);
        }
        if(null == pm3311.getObspttm()) {
           pm3311.setObspttm(EMPTY_VALUE);
        }
        return bandera;
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3311BS#findByAnual(java.lang.Integer, java.lang.Integer)
	 */
	public boolean findByAnual(Integer idSector, Integer anual){
		
		return pm3311Repository.count(idSector, anual)>0 ? false:true;
	}
}
