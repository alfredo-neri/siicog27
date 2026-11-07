package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm4711BS;
import com.gem.sistema.business.domain.Pm4711;
import com.gem.sistema.business.repository.catalogs.Pm4711Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm4711BSImpl.
 *
 * @author Alfredo
 */
@Repository(value = "pm4711BS")
public class Pm4711BSImpl implements Pm4711BS{
	
	/** The Constant NULL_VALUE. */
	private static final Integer NULL_VALUE = null;
	
	/** The Constant EMPTY_VALUE. */
	private static final String EMPTY_VALUE = "";
	
	/** The pm 4711 repository. */
	@Autowired
	private Pm4711Repository pm4711Repository;

	/**
	 * Gets the pm 4711 repository.
	 *
	 * @return the pm 4711 repository
	 */
	public Pm4711Repository getPm4711Repository() {
		return pm4711Repository;
	}

	/**
	 * Sets the pm 4711 repository.
	 *
	 * @param pm4711Repository the new pm 4711 repository
	 */
	public void setPm4711Repository(Pm4711Repository pm4711Repository) {
		this.pm4711Repository = pm4711Repository;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4711BS#save(java.lang.Integer, java.util.List)
	 */
	
	/** The entity. */
	Pm4711 entity;
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4711BS#save(com.gem.sistema.business.domain.Pm4711)
	 */
	@Override
	public boolean save(Pm4711 pm4711) {
		
		entity = pm4711;
		
		boolean bandera = findByAnual(pm4711.getIdsector(), pm4711.getAnual());
		if(bandera) {
			bandera = this.validateTxt(pm4711);
			if(bandera){
				pm4711Repository.save(pm4711);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");
			}
			
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El año ya existe");
		}
		return bandera;
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4711BS#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public void delete(Pm4711 pm4711) {
		pm4711Repository.delete(pm4711);
		
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4711BS#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public Pm4711 clean(Pm4711 pm4711) {
		pm4711.setAnual(NULL_VALUE);
		pm4711.setObsntinsini(EMPTY_VALUE);
		pm4711.setObsntinsfin(EMPTY_VALUE);
		pm4711.setNtinsini(NULL_VALUE);
		pm4711.setNtinsfin(NULL_VALUE);
		return pm4711;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4711BS#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public void cancel(Pm4711 pm4711) {
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4711BS#validateTxt(java.lang.Integer, java.util.List)
	 */
	
	@Override
	public boolean validateTxt(Pm4711 pm4711) {
		boolean  bandera = Boolean.TRUE;
		
		if (null == pm4711.getNtinsfin()){
			pm4711.setNtinsfin(0);
		}
		if (null == pm4711.getNtinsini()){
			pm4711.setNtinsini(0);
		}
		if (null == pm4711.getObsntinsfin()){
			pm4711.setObsntinsfin(EMPTY_VALUE);
		}
		if (null == pm4711.getObsntinsini()){
			pm4711.setObsntinsini(EMPTY_VALUE);
		}
        return bandera;
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm4711BS#findByAnual(java.lang.Integer, java.lang.Integer)
	 */
	public boolean findByAnual(Integer idSector, Integer anual){
		
		return pm4711Repository.count(idSector, anual)>0 ? false:true;
	}
}
