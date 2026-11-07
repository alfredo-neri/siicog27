package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm2411BS;
import com.gem.sistema.business.domain.Pm2411;
import com.gem.sistema.business.repository.catalogs.Pm2411Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm2411BSImpl.
 *
 * @author Alfredo
 */
@Repository(value = "pm2411BS")
public class Pm2411BSImpl implements Pm2411BS{
	
	/** The Constant NULL_VALUE. */
	private static final Integer NULL_VALUE = null;
	
	/** The Constant EMPTY_VALUE. */
	private static final String EMPTY_VALUE = "";
	
	/** The pm 2411 repository. */
	@Autowired
	private Pm2411Repository pm2411Repository;

	/**
	 * Gets the pm 2411 repository.
	 *
	 * @return the pm 2411 repository
	 */
	public Pm2411Repository getPm2411Repository() {
		return pm2411Repository;
	}

	/**
	 * Sets the pm 2411 repository.
	 *
	 * @param pm2411Repository the new pm 2411 repository
	 */
	public void setPm2411Repository(Pm2411Repository pm2411Repository) {
		this.pm2411Repository = pm2411Repository;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2411BS#save(java.lang.Integer, java.util.List)
	 */
	
	/** The entity. */
	Pm2411 entity;
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2411BS#save(com.gem.sistema.business.domain.Pm2411)
	 */
	@Override
	public boolean save(Pm2411 pm2411) {
		
		entity = pm2411;
		
		boolean bandera = findByAnual(pm2411.getIdsector(), pm2411.getAnual());
		if(bandera) {
			bandera = this.validateTxt(pm2411);
			if(bandera){
				pm2411Repository.save(pm2411);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");
			}
			
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El año ya existe");
		}
		return bandera;
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2411BS#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public void delete(Pm2411 pm2411) {
		pm2411Repository.delete(pm2411);
		
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2411BS#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public Pm2411 clean(Pm2411 pm2411) {
		pm2411.setAnual(NULL_VALUE);
		pm2411.setObsnpu(EMPTY_VALUE);
		pm2411.setObstpu(EMPTY_VALUE);
		pm2411.setNpu(NULL_VALUE);
		pm2411.setTpu(NULL_VALUE);
		return pm2411;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2411BS#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public void cancel(Pm2411 pm2411) {
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2411BS#validateTxt(java.lang.Integer, java.util.List)
	 */
	
	@Override
	public boolean validateTxt(Pm2411 pm2411) {
		boolean  bandera = Boolean.TRUE;
        
		if (null == pm2411.getNpu()){
			pm2411.setNpu(0);
		}
		if (null == pm2411.getTpu()){
			pm2411.setTpu(0);
		}
		if (null == pm2411.getObsnpu()){
			pm2411.setObsnpu(EMPTY_VALUE);
		}
		if (null == pm2411.getObstpu()){
			pm2411.setObstpu(EMPTY_VALUE);
		}
		
		return bandera;
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2411BS#findByAnual(java.lang.Integer, java.lang.Integer)
	 */
	public boolean findByAnual(Integer idSector, Integer anual){
		
		return pm2411Repository.count(idSector, anual)>0 ? false:true;
	}
}
