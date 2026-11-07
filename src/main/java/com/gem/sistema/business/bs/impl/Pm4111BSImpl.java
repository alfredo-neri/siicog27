package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;


import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm4111BS;
import com.gem.sistema.business.domain.Pm4111;
import com.gem.sistema.business.repository.catalogs.Pm4111Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm4111BSImpl.
 *
 * @author Alfredo
 */
@Repository(value = "pm4111BS")
public class Pm4111BSImpl implements Pm4111BS{
	
	/** The Constant NULL_VALUE. */
	private static final Integer NULL_VALUE = null;
	
	/** The Constant EMPTY_VALUE. */
	private static final String EMPTY_VALUE = "";
	
	/** The pm 4111 repository. */
	@Autowired
	private Pm4111Repository pm4111Repository;

	/**
	 * Gets the pm 4111 repository.
	 *
	 * @return the pm 4111 repository
	 */
	public Pm4111Repository getPm4111Repository() {
		return pm4111Repository;
	}

	/**
	 * Sets the pm 4111 repository.
	 *
	 * @param pm4111Repository the new pm 4111 repository
	 */
	public void setPm4111Repository(Pm4111Repository pm4111Repository) {
		this.pm4111Repository = pm4111Repository;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4111BS#save(java.lang.Integer, java.util.List)
	 */
	
	/** The entity. */
	Pm4111 entity;
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4111BS#save(com.gem.sistema.business.domain.Pm4111)
	 */
	@Override
	public boolean save(Pm4111 pm4111) {
		
		entity = pm4111;
		
		boolean bandera = findByAnual(pm4111.getIdsector(), pm4111.getAnual());
		if(bandera) {
			bandera = this.validateTxt(pm4111);
			if(bandera){
				pm4111Repository.save(pm4111);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");
			}
			
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El año ya existe");
		}
		return bandera;
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4111BS#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public void delete(Pm4111 pm4111) {
		pm4111Repository.delete(pm4111);
		
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4111BS#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public Pm4111 clean(Pm4111 pm4111) {
		pm4111.setAnual(NULL_VALUE);
		pm4111.setObsnac(EMPTY_VALUE);
		pm4111.setObsnap(EMPTY_VALUE);
		pm4111.setNac(NULL_VALUE);
		pm4111.setNap(NULL_VALUE);
		return pm4111;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4111BS#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public void cancel(Pm4111 pm4111) {
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4111BS#validateTxt(java.lang.Integer, java.util.List)
	 */
	
	@Override
	public boolean validateTxt(Pm4111 pm4111) {
		boolean  bandera = Boolean.TRUE;
		
		if (null == pm4111.getNac()){
			pm4111.setNac(0);
		}
		if (null == pm4111.getNap()){
			pm4111.setNap(0);
		}
		if (null == pm4111.getObsnac()){
			pm4111.setObsnac(EMPTY_VALUE);
		}
		if (null == pm4111.getObsnap()){
			pm4111.setObsnap(EMPTY_VALUE);
		}
		
        return bandera;
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4111BS#findByAnual(java.lang.Integer, java.lang.Integer)
	 */
	public boolean findByAnual(Integer idSector, Integer anual){
		
		return pm4111Repository.count(idSector, anual)>0 ? false:true;
	}
}
