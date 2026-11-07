package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;


import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm0111BS;
import com.gem.sistema.business.domain.Pm0111;
import com.gem.sistema.business.repository.catalogs.Pm0111Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm0111BSImpl.
 *
 * @author Alfredo
 */
@Repository(value = "pm0111BS")
public class Pm0111BSImpl implements Pm0111BS{
	
	/** The Constant NULL_VALUE. */
	private static final Integer NULL_VALUE = null;
	
	/** The Constant EMPTY_VALUE. */
	private static final String EMPTY_VALUE = "";
	
	/** The pm 0111 repository. */
	@Autowired
	private Pm0111Repository pm0111Repository;

	/**
	 * Gets the pm 0111 repository.
	 *
	 * @return the pm 0111 repository
	 */
	public Pm0111Repository getPm0111Repository() {
		return pm0111Repository;
	}

	/**
	 * Sets the pm 0111 repository.
	 *
	 * @param pm0111Repository the new pm 0111 repository
	 */
	public void setPm0111Repository(Pm0111Repository pm0111Repository) {
		this.pm0111Repository = pm0111Repository;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0111BS#save(java.lang.Integer, java.util.List)
	 */
	
	/** The entity. */
	Pm0111 entity;
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0111BS#save(com.gem.sistema.business.domain.Pm0111)
	 */
	@Override
	public boolean save(Pm0111 pm0111) {
		
		entity = pm0111;
		
		boolean bandera = findByAnual(pm0111.getIdsector(), pm0111.getAnual());
		if(bandera) {
			bandera = this.validateTxt(pm0111);
			if(bandera){
				pm0111Repository.save(pm0111);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");
			}
			
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El año ya existe");
		}
		return bandera;
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0111BS#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public void delete(Pm0111 pm0111) {
		pm0111Repository.delete(pm0111);
		
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0111BS#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public Pm0111 clean(Pm0111 pm0111) {
		pm0111.setAnual(NULL_VALUE);
		pm0111.setObsnjm(EMPTY_VALUE);
		pm0111.setObstji(EMPTY_VALUE);
		pm0111.setNjml(NULL_VALUE);
		pm0111.setNjmc(NULL_VALUE);
		pm0111.setNjma(NULL_VALUE);
		pm0111.setNjmd(NULL_VALUE);
		pm0111.setTjil(NULL_VALUE);
		pm0111.setTjic(NULL_VALUE);
		pm0111.setTjia(NULL_VALUE);
		pm0111.setTjid(NULL_VALUE);
		pm0111.setTotjucon(NULL_VALUE);
		
		return pm0111;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0111BS#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public void cancel(Pm0111 pm0111) {
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0111BS#validateTxt(java.lang.Integer, java.util.List)
	 */
	
	@Override
	public boolean validateTxt(Pm0111 pm0111) {
		boolean  bandera = Boolean.TRUE;
		if (null == pm0111.getObsnjm()){
			pm0111.setObsnjm(EMPTY_VALUE);
		}
		if (null == pm0111.getObstji()){
			pm0111.setObstji(EMPTY_VALUE);
		}
		
		if (null == pm0111.getNjml()){
			pm0111.setNjml(0);
		}
		if (null == pm0111.getNjmc()){
			pm0111.setNjmc(0);
		}
		if (null == pm0111.getNjma()){
			pm0111.setNjma(0);
		}
		if(null == pm0111.getNjmd()){
			pm0111.setNjmd(0);
		}
		if(null == pm0111.getTotjuga()){
			pm0111.setTotjuga(0);
		}
		if(null == pm0111.getTjil()){
			pm0111.setTjil(0);
		}
		if(null == pm0111.getTjic()){
			pm0111.setTjic(0);
		}
		if(null == pm0111.getTjia()){
			pm0111.setTjia(0);
		}
		if(null == pm0111.getTjid()){
			pm0111.setTjid(0);
		}
		if(null == pm0111.getTotjucon()){
			pm0111.setTotjucon(0);
		}
        return bandera;
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0111BS#findByAnual(java.lang.Integer, java.lang.Integer)
	 */
	public boolean findByAnual(Integer idSector, Integer anual){
		
		return pm0111Repository.count(idSector, anual)>0 ? false:true;
	}
}
