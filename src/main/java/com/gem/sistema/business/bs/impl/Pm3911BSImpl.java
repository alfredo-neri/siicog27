package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm3911BS;
import com.gem.sistema.business.domain.Pm3911;
import com.gem.sistema.business.repository.catalogs.Pm3911Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm3911BSImpl.
 *
 * @author Mateo
 */
@Repository(value = "pm3911BS")
public class Pm3911BSImpl implements Pm3911BS {

	/** The pm 3911 repository. */
	@Autowired
	private Pm3911Repository pm3911Repository;

	/**
	 * Gets the pm 3911 repository.
	 *
	 * @return the pm 3911 repository
	 */
	public Pm3911Repository getPm3911Repository() {
		return pm3911Repository;
	}

	/**
	 * Sets the pm 3911 repository.
	 *
	 * @param pm3911Repository the new pm 3911 repository
	 */
	public void setPm3911Repository(Pm3911Repository pm3911Repository) {
		this.pm3911Repository = pm3911Repository;
	}

	/** The count. */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3911BS#save(java.lang.Integer,
	 * java.util.List)
	 */
	Integer count;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3911BS#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3911> save(Integer index, List<Pm3911> listPm3911) {
		Pm3911 pm3911 = listPm3911.get(index);
		boolean bandera;
		//bandera = validateTxt(pm3911);
	    bandera = Boolean.TRUE;
		listPm3911.get(index).setbGuardar(Boolean.FALSE);
		if (bandera) {
			bandera = findByTrimestre(pm3911.getSemes(), pm3911.getIdsector());
			if (bandera) {
				pm3911Repository.save(pm3911);
				count = pm3911Repository.count(pm3911.getIdsector());
				listPm3911 = orderByTrimestreAsc(pm3911.getIdsector());
				listPm3911.get(index).setbGuardar(Boolean.TRUE);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"Se guardo la información correctamente");
			} else {

				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El semestre ya existe");
			}
		}
		return listPm3911;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3911BS#delete(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm3911> delete(Integer index, List<Pm3911> listPm3911) {
		pm3911Repository.delete(listPm3911.get(index).getId());
		listPm3911 = orderByTrimestreAsc(listPm3911.get(index).getIdsector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		return listPm3911;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3911BS#clean(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm3911> clean(Integer index, List<Pm3911> listPm3911) {
		listPm3911.get(index).setManorgno("");
		listPm3911.get(index).setManorgpro("");
		listPm3911.get(index).setManorgsi("");
		listPm3911.get(index).setPerinimo(null);
		listPm3911.get(index).setPerfinmo(null);
		listPm3911.get(index).setOrgno("");
		listPm3911.get(index).setOrgpro("");
		listPm3911.get(index).setOrgsi("");
		listPm3911.get(index).setPerinio(null);
		listPm3911.get(index).setPerfino(null);
		listPm3911.get(index).setRegno("");
		listPm3911.get(index).setRegpro("");
		listPm3911.get(index).setRegsi("");
		listPm3911.get(index).setPreinireg(null);
		listPm3911.get(index).setPerfinreg(null);
		listPm3911.get(index).setManprono("");
		listPm3911.get(index).setManprosi("");
		listPm3911.get(index).setManpropro("");
		listPm3911.get(index).setPeriniman(null);
		listPm3911.get(index).setPerfinman(null);
		listPm3911.get(index).setObservaciones("");
		listPm3911.get(index).setObsmanpro("");
		return listPm3911;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3911BS#cancel(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm3911> cancel(Integer index, List<Pm3911> listPm3911) {
		return orderByTrimestreAsc(listPm3911.get(index).getIdsector());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3911BS#orderByTrimestreAsc(java.lang.
	 * Integer)
	 */
	@Override
	public List<Pm3911> orderByTrimestreAsc(Integer idSector) {
		List<Pm3911> listPm3911 = pm3911Repository.findAllByIdsector(idSector, this.oderBy());
		if (CollectionUtils.isEmpty(listPm3911)) {
			Pm3911 pm3911 = new Pm3911();
			pm3911.setSemes(0);
			listPm3911.add(pm3911);
		}
		return listPm3911;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3911BS#oderBy()
	 */
	@Override
	public Sort oderBy() {
		return new Sort(Sort.Direction.ASC, "semes");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm3911BS#findByTrimestre(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public boolean findByTrimestre(Integer semestre, Integer idSector) {
		return pm3911Repository.countBySemestre(idSector, semestre) > 0 ? false : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3911BS#validateTxt(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public boolean validateTxt(Pm3911 pm3911) {
		boolean bandera = Boolean.TRUE;
		if (null == pm3911.getManorgpro()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo Porcentaje del Manual de Organizacion es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm3911.getOrgpro()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo Porcentaje del Organigrama actualizado y autorizado  es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm3911.getRegpro()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo Porcentaje del Reglamento interior de trabajo es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm3911.getManpropro()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo Procentaje del Manuales de procedimientos es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null != pm3911.getPerinimo() && null != pm3911.getPerfinmo()) {
			if (pm3911.getPerinimo().compareTo(pm3911.getPerfinmo()) > 0) {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
						"La fecha inicial del Manual de Organizacion debe de ser menor a la final");
				bandera = Boolean.FALSE;
			}

		} else if (null != pm3911.getPerinio() && null != pm3911.getPerfino()) {
			if (pm3911.getPerinio().compareTo(pm3911.getPerfino()) > 0) {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
						"La fecha inicial del Organigrama actualizado y autorizado de ser menor a la final");
				bandera = Boolean.FALSE;
			}

		} else if (null != pm3911.getPreinireg() && null != pm3911.getPerfinreg()) {
			if (pm3911.getPreinireg().compareTo(pm3911.getPerfinreg()) > 0) {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
						"La fecha inicial del Reglamento interior de trabajo de ser menor a la final");
				bandera = Boolean.FALSE;
			}

		} else if (null != pm3911.getPreinireg() && null != pm3911.getPerfinreg()) {
			if (pm3911.getPreinireg().compareTo(pm3911.getPerfinreg()) > 0) {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
						"La fecha inicial del Reglamento interior de trabajo de ser menor a la final");
				bandera = Boolean.FALSE;
			}

		} else if (null != pm3911.getPeriniman() && null != pm3911.getPerfinman()) {
			if (pm3911.getPeriniman().compareTo(pm3911.getPerfinman()) > 0) {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
						"La fecha inicial del Manuales de procedimientos de ser menor a la final");
				bandera = Boolean.FALSE;
			}

		}
		return bandera;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3911BS#add()
	 */
	@Override
	public Pm3911 add() {
		Pm3911 pm3911 = new Pm3911();
		pm3911.setObservaciones("");
		return pm3911;
	}

}
