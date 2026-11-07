package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;


import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm4611BS;
import com.gem.sistema.business.domain.Pm4611;
import com.gem.sistema.business.repository.catalogs.Pm4611Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm4611BSImpl.
 *
 * @author Vlad
 */
@Repository(value = "pm4611BS")
public class Pm4611BSImpl implements Pm4611BS{
	
	/** The Constant NULL_VALUE. */
	private static final Integer NULL_VALUE = null;
	
	/** The Constant EMPTY_VALUE. */
	private static final String EMPTY_VALUE = "";
	
	/** The pm 4611 repository. */
	@Autowired
	private Pm4611Repository pm4611Repository;

	/**
	 * Gets the pm 4611 repository.
	 *
	 * @return the pm 4611 repository
	 */
	public Pm4611Repository getPm4611Repository() {
		return pm4611Repository;
	}

	/**
	 * Sets the pm 4611 repository.
	 *
	 * @param pm4611Repository the new pm 4611 repository
	 */
	public void setPm4611Repository(Pm4611Repository pm4611Repository) {
		this.pm4611Repository = pm4611Repository;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4611BS#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public boolean save(Pm4611 pm4611) {
		boolean bandera = findByAnual(pm4611.getIdsector(), pm4611.getAnual());
		if(bandera) {
			bandera = this.validateTxt(pm4611);
			if(bandera){
				pm4611Repository.save(pm4611);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");
			}
			
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El año ya existe");
		}
		return bandera;
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4611BS#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public void delete(Pm4611 pm4611) {
		pm4611Repository.delete(pm4611);
		
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4611BS#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public Pm4611 clean(Pm4611 pm4611) {
		pm4611.setAnual(NULL_VALUE);
		pm4611.setObsnrcifp(EMPTY_VALUE);
		pm4611.setObsnrciip(EMPTY_VALUE);
		pm4611.setNrcifp(NULL_VALUE);
		pm4611.setNrciip(NULL_VALUE);
		pm4611.setCapturo(EMPTY_VALUE);
		pm4611.setFeccap(null);
		pm4611.setUserid(EMPTY_VALUE);
		pm4611.setIdsector(NULL_VALUE);
		pm4611.setIdRef(null);
		return pm4611;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4611BS#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public void cancel(Pm4611 pm4611) {
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4611BS#validateTxt(java.lang.Integer, java.util.List)
	 */
	
	@Override
	public boolean validateTxt(Pm4611 pm4611) {
		boolean  bandera = Boolean.TRUE;
        if(null == pm4611.getNrcifp()) {
        	pm4611.setNrcifp(0);
        }
        if(null == pm4611.getObsnrcifp()) {
            pm4611.setObsnrcifp(EMPTY_VALUE);
        }
        if(null == pm4611.getNrciip()) {
            pm4611.setNrciip(0);
        }
        if(null == pm4611.getObsnrciip()) {
            pm4611.setObsnrciip(EMPTY_VALUE);
        }
        return bandera;
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4611BS#findByAnual(java.lang.Integer, java.lang.Integer)
	 */
	public boolean findByAnual(Integer idSector, Integer anual){
		
		return pm4611Repository.count(idSector, anual)>0 ? false:true;
	}
}
