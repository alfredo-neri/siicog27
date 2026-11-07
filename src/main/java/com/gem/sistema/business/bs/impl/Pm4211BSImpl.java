package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm4211BS;
import com.gem.sistema.business.domain.Pm4211;
import com.gem.sistema.business.repository.catalogs.Pm4211Repository;


// TODO: Auto-generated Javadoc
/**
 * The Class Pm4211BSImpl.
 *
 * @author Alfredo
 */
@Repository(value = "pm4211BS")
public class Pm4211BSImpl implements Pm4211BS{
	
	/** The Constant EMPTY_VALUE. */
	private static final String EMPTY_VALUE = "";
	
	/** The pm 4211 repository. */
	@Autowired
	private Pm4211Repository pm4211Repository;

	
	/**
	 * Gets the pm 4211 repository.
	 *
	 * @return the pm 4211 repository
	 */
	public Pm4211Repository getPm4211Repository() {
		return pm4211Repository;
	}

	/**
	 * Sets the pm 4211 repository.
	 *
	 * @param pm4711Repository the new pm 4211 repository
	 */
	public void setPm4211Repository(Pm4211Repository pm4711Repository) {
		this.pm4211Repository = pm4211Repository;
	}
	
	/** The entity. */
	Pm4211 entity;
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4211BS#save(com.gem.sistema.business.domain.Pm4211)
	 */
	@Override
	public boolean save(Pm4211 pm4211) {
		
		entity = pm4211;
		
		boolean bandera = true; //findByAnual(Pm4211.getIdsector(), pm4711.getAnual());
		if(bandera) {
			bandera = this.validateTxt(pm4211);
			if(bandera){
				pm4211Repository.save(pm4211);
				//generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");
			}
			
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El año ya existe");
		}
		return bandera;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4211BS#validateTxt(com.gem.sistema.business.domain.Pm4211)
	 */
	@Override
	public boolean validateTxt(Pm4211 pm4211) {
		boolean  bandera = Boolean.TRUE;
		
		if (null == pm4211.getObs1()){
			pm4211.setObs1(EMPTY_VALUE);
		}
		if (null == pm4211.getObs2()){
			pm4211.setObs2(EMPTY_VALUE);
		}
		if (null == pm4211.getObs3()){
			pm4211.setObs3(EMPTY_VALUE);
		}
		if (null == pm4211.getObs4()){
			pm4211.setObs4(EMPTY_VALUE);
		}
		if (null == pm4211.getObs5()){
			pm4211.setObs5(EMPTY_VALUE);
		}
		if (null == pm4211.getObs6()){
			pm4211.setObs6(EMPTY_VALUE);
		}
		if (null == pm4211.getObs7()){
			pm4211.setObs7(EMPTY_VALUE);
		}
		if (null == pm4211.getObs8()){
			pm4211.setObs8(EMPTY_VALUE);
		}
		if (null == pm4211.getObs9()){
			pm4211.setObs9(EMPTY_VALUE);
		}
		pm4211.setObs10(EMPTY_VALUE);
		
        return bandera;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4211BS#countByMes(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean countByMes(Integer idSector, Integer mes) {
		
		return pm4211Repository.countRows(idSector, mes)>0 ? false:true;
	}
}
