package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;


import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm3211BS;
import com.gem.sistema.business.domain.Pm3211;
import com.gem.sistema.business.repository.catalogs.Pm3211Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm3211BSImpl.
 *
 * @author Alfredo
 */
@Repository(value = "pm3211BS")
public class Pm3211BSImpl implements Pm3211BS{
	
	/** The Constant NULL_VALUE. */
	private static final Integer NULL_VALUE = null;
	
	/** The Constant EMPTY_VALUE. */
	private static final String EMPTY_VALUE = "";
	
	/** The pm 3211 repository. */
	@Autowired
	private Pm3211Repository pm3211Repository;

	/**
	 * Gets the pm 3211 repository.
	 *
	 * @return the pm 3211 repository
	 */
	public Pm3211Repository getPm3211Repository() {
		return pm3211Repository;
	}

	/**
	 * Sets the pm 3211 repository.
	 *
	 * @param pm3211Repository the new pm 3211 repository
	 */
	public void setPm3211Repository(Pm3211Repository pm3211Repository) {
		this.pm3211Repository = pm3211Repository;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3211BS#save(java.lang.Integer, java.util.List)
	 */
	
	/** The entity. */
	Pm3211 entity;
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3211BS#save(com.gem.sistema.business.domain.Pm3211)
	 */
	@Override
	public boolean save(Pm3211 pm3211) {
		
		entity = pm3211;
		
		boolean bandera = findByAnual(pm3211.getIdsector(), pm3211.getAnual());
		if(bandera) {
			bandera = this.validateTxt(pm3211);
			if(bandera){
				pm3211Repository.save(pm3211);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");
			}
			
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El año ya existe");
		}
		return bandera;
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3211BS#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public void delete(Pm3211 pm3211) {
		pm3211Repository.delete(pm3211);
		
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3211BS#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public Pm3211 clean(Pm3211 pm3211) {
		pm3211.setAnual(NULL_VALUE);
		pm3211.setObsnobs(EMPTY_VALUE);
		pm3211.setObstotobr(EMPTY_VALUE);
		pm3211.setNobs(NULL_VALUE);
		pm3211.setTotobr(NULL_VALUE);
		return pm3211;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3211BS#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public void cancel(Pm3211 pm3211) {
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3211BS#validateTxt(java.lang.Integer, java.util.List)
	 */
	
	@Override
	public boolean validateTxt(Pm3211 pm3211) {
		boolean  bandera = Boolean.TRUE;
        
		if (null == pm3211.getNobs()){
			pm3211.setNobs(0);
		}
		if (null == pm3211.getTotobr()){
			pm3211.setTotobr(0);
		}
		if (null == pm3211.getObstotobr()){
			pm3211.setObstotobr(EMPTY_VALUE);
		}
        if (null == pm3211.getObsnobs()){
			pm3211.setObsnobs(EMPTY_VALUE);
		}
        
        return bandera;
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3211BS#findByAnual(java.lang.Integer, java.lang.Integer)
	 */
	public boolean findByAnual(Integer idSector, Integer anual){
		
		return pm3211Repository.count(idSector, anual)>0 ? false:true;
	}
}
