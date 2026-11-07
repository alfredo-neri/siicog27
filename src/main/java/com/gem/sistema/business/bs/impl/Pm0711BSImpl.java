package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm0711BS;
import com.gem.sistema.business.domain.Pm0711;
import com.gem.sistema.business.repository.catalogs.Pm0711Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm0711BSImpl.
 *
 * @author Mateo
 */
@Repository(value = "pm0711BS")
public class Pm0711BSImpl implements Pm0711BS {
	
	/** The Constant NULL_VALUE. */
	private static final Integer NULL_VALUE = null;
	
	/** The Constant EMPTY_VALUE. */
	private static final String EMPTY_VALUE = "";

	/** The pm 0711 repository. */
	@Autowired
	private Pm0711Repository pm0711Repository;

	/**
	 * Gets the pm 0711 repository.
	 *
	 * @return the pm 0711 repository
	 */
	public Pm0711Repository getPm0711Repository() {
		return pm0711Repository;
	}

	/**
	 * Sets the pm 0711 repository.
	 *
	 * @param pm0711Repository the new pm 0711 repository
	 */
	public void setPm0711Repository(Pm0711Repository pm0711Repository) {
		this.pm0711Repository = pm0711Repository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm0711BS#save(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public boolean save(Pm0711 pm0711) {
		boolean bandera = findByAnual(pm0711.getIdsector(), pm0711.getAnual());
		if (bandera) {
			bandera = this.validateTxt(pm0711);
			if (bandera) {
				pm0711Repository.save(pm0711);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"Se guardo la información correctamente");
			}

		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El año ya existe");
		}
		return bandera;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm0711BS#delete(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public void delete(Pm0711 pm0711) {
		pm0711Repository.delete(pm0711);

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm0711BS#clean(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public Pm0711 clean(Pm0711 pm0711) {
		pm0711.setAnual(NULL_VALUE);
		pm0711.setObsnespa(EMPTY_VALUE);
		pm0711.setObstesp(EMPTY_VALUE);
		pm0711.setTesp(NULL_VALUE);
		pm0711.setNespa(NULL_VALUE);
		return pm0711;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm0711BS#cancel(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public void cancel(Pm0711 pm0711) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm0711BS#validateTxt(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public boolean validateTxt(Pm0711 pm0711) {
		// boolean bandera = Boolean.TRUE;
		if (null == pm0711.getNespa()) {
			// generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
			// "El campo Número de Policias con Arma de Fuego es obligatorio");
			// bandera = Boolean.FALSE;
			pm0711.setNespa(0);
		} 
		if (null == pm0711.getObsnespa()) {
			// generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
			// "El campo Observaciones es obligatorio");
			// bandera = Boolean.FALSE;
			pm0711.setObsnespa(EMPTY_VALUE);
		}
		if (null == pm0711.getTesp()) {
			// generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
			// "El campo Total de Policias Efectivos en la Plantilla es
			// obligatorio");
			// bandera = Boolean.FALSE;
			pm0711.setTesp(0);
		}
		if (null == pm0711.getObstesp()) {
			// generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
			// "El campo Observaciones es obligatorio");
			// bandera = Boolean.FALSE;
			pm0711.setObstesp(EMPTY_VALUE);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0711BS#findByAnual(java.lang.Integer, java.lang.Integer)
	 */
	public boolean findByAnual(Integer idSector, Integer anual) {

		return pm0711Repository.count(idSector, anual) > 0 ? false : true;
	}
}
