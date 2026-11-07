package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm4511BS;
import com.gem.sistema.business.domain.Pm4511;
import com.gem.sistema.business.repository.catalogs.Pm4511Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm4511BSImpl.
 *
 * @author Alfredo
 */
@Repository(value = "pm4511BS")
public class Pm4511BSImpl implements Pm4511BS{
	
	/** The Constant NULL_VALUE. */
	private static final Integer NULL_VALUE = null;
	
	/** The Constant EMPTY_VALUE. */
	private static final String EMPTY_VALUE = "";
	
	/** The pm 4511 repository. */
	@Autowired
	private Pm4511Repository pm4511Repository;

	/**
	 * Gets the pm 4511 repository.
	 *
	 * @return the pm 4511 repository
	 */
	public Pm4511Repository getPm4511Repository() {
		return pm4511Repository;
	}

	/**
	 * Sets the pm 4511 repository.
	 *
	 * @param pm4511Repository the new pm 4511 repository
	 */
	public void setPm4511Repository(Pm4511Repository pm4511Repository) {
		this.pm4511Repository = pm4511Repository;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4511BS#save(java.lang.Integer, java.util.List)
	 */
	
	/** The entity. */
	Pm4511 entity;
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4511BS#save(com.gem.sistema.business.domain.Pm4511)
	 */
	@Override
	public boolean save(Pm4511 pm4511) {
		
		entity = pm4511;
		
		boolean bandera = findByAnual(pm4511.getIdsector(), pm4511.getAnual());
		if(bandera) {
			bandera = this.validateTxt(pm4511);
			if(bandera){
				pm4511Repository.save(pm4511);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");
			}
			
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El año ya existe");
		}
		return bandera;
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4511BS#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public void delete(Pm4511 pm4511) {
		pm4511Repository.delete(pm4511);
		
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4511BS#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public Pm4511 clean(Pm4511 pm4511) {
		pm4511.setAnual(NULL_VALUE);
		pm4511.setObstotpre(EMPTY_VALUE);
		pm4511.setObsnhab(EMPTY_VALUE);
		pm4511.setTotpre(BigDecimal.ZERO);
		pm4511.setNhab(NULL_VALUE);
		return pm4511;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4511BS#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public void cancel(Pm4511 pm4511) {
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4511BS#validateTxt(java.lang.Integer, java.util.List)
	 */
	
	@Override
	public boolean validateTxt(Pm4511 pm4511) {
		boolean  bandera = Boolean.TRUE;
		
		if (null == pm4511.getTotpre()){
			pm4511.setTotpre(BigDecimal.ZERO);
		}
		if (null == pm4511.getNhab()){
			pm4511.setNhab(0);
		}
		if (null == pm4511.getObstotpre()){
			pm4511.setObstotpre(EMPTY_VALUE);
		}
		if (null == pm4511.getObsnhab()){
			pm4511.setObsnhab(EMPTY_VALUE);
		}
        return bandera;
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4511BS#findByAnual(java.lang.Integer, java.lang.Integer)
	 */
	public boolean findByAnual(Integer idSector, Integer anual){
		
		return pm4511Repository.count(idSector, anual)>0 ? false:true;
	}
}
