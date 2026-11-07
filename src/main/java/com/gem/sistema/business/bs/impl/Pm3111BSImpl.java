package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm3111BS;
import com.gem.sistema.business.domain.Pm3111;
import com.gem.sistema.business.repository.catalogs.Pm3111Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm3111BSImpl.
 *
 * @author Vlad
 */
@Repository(value = "pm3111BS")
public class Pm3111BSImpl implements Pm3111BS {
	
	/** The Constant NULL_VALUE. */
	private static final Integer NULL_VALUE = null;
	
	/** The Constant EMPTY_VALUE. */
	private static final String EMPTY_VALUE = "";

	/** The pm 3111 repository. */
	@Autowired
	private Pm3111Repository pm3111Repository;

	/**
	 * Gets the pm 3111 repository.
	 *
	 * @return the pm 3111 repository
	 */
	public Pm3111Repository getPm3111Repository() {
		return pm3111Repository;
	}

	/**
	 * Sets the pm 3111 repository.
	 *
	 * @param pm3111Repository the new pm 3111 repository
	 */
	public void setPm3111Repository(Pm3111Repository pm3111Repository) {
		this.pm3111Repository = pm3111Repository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3111BS#save(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public boolean save(Pm3111 pm3111) {
		boolean bandera = findByAnual(pm3111.getIdsector(), pm3111.getAnual());
		if (bandera) {
			bandera = this.validateTxt(pm3111);
			if (bandera) {
				pm3111Repository.save(pm3111);
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
	 * @see com.gem.sistema.business.bs.Pm3111BS#delete(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public void delete(Pm3111 pm3111) {
		pm3111Repository.delete(pm3111);

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3111BS#clean(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public Pm3111 clean(Pm3111 pm3111) {
		pm3111.setAnual(NULL_VALUE);
		pm3111.setCpci(NULL_VALUE);
		pm3111.setNtloc(NULL_VALUE);
		pm3111.setObscpci(EMPTY_VALUE);
		pm3111.setObsntloc(EMPTY_VALUE);
		// pm3111.setCapturo(EMPTY_VALUE);
		// pm3111.setFeccap(null);
		// pm3111.setUserid(EMPTY_VALUE);
		// pm3111.setIdsector(NULL_VALUE);
		// pm3111.setIdRef(null);
		return pm3111;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3111BS#cancel(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public void cancel(Pm3111 pm3111) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3111BS#validateTxt(java.lang.Integer,
	 * java.util.List)
	 */

	@Override
	public boolean validateTxt(Pm3111 pm3111) {
		boolean bandera = Boolean.TRUE;
		if (null == pm3111.getCpci()) {
			pm3111.setCpci(0);
		}
		if (null == pm3111.getObscpci()) {
			pm3111.setObscpci(EMPTY_VALUE);
		}
		if (null == pm3111.getNtloc()) {
			pm3111.setNtloc(0);
		}
		if (null == pm3111.getObsntloc()) {
			pm3111.setObsntloc(EMPTY_VALUE);
		}
		return bandera;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3111BS#findByAnual(java.lang.Integer, java.lang.Integer)
	 */
	public boolean findByAnual(Integer idSector, Integer anual) {

		return pm3111Repository.count(idSector, anual) > 0 ? false : true;
	}
}
