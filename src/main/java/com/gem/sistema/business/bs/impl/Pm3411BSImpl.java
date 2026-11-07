package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm3411BS;
import com.gem.sistema.business.domain.Pm3411;
import com.gem.sistema.business.repository.catalogs.Pm3411Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm3411BSImpl.
 *
 * @author Mateo
 */
@Repository(value = "pm3411BS")
public class Pm3411BSImpl implements Pm3411BS {

	/** The pm 3411 repository. */
	@Autowired
	private Pm3411Repository pm3411Repository;

	/**
	 * Gets the pm 3411 repository.
	 *
	 * @return the pm 3411 repository
	 */
	public Pm3411Repository getPm3411Repository() {
		return pm3411Repository;
	}

	/**
	 * Sets the pm 3411 repository.
	 *
	 * @param pm3411Repository the new pm 3411 repository
	 */
	public void setPm3411Repository(Pm3411Repository pm3411Repository) {
		this.pm3411Repository = pm3411Repository;
	}

	/** The count. */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3411BS#save(java.lang.Integer,
	 * java.util.List)
	 */
	Integer count;
	
	/** The acumulado. */
	Integer acumulado = 0;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3411BS#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3411> save(Integer index, List<Pm3411> listPm3411) {
		Pm3411 pm3411 = listPm3411.get(index);
		boolean bandera;

		listPm3411.get(index).setbGuardar(Boolean.FALSE);

		bandera = findByTrimestre(pm3411.getSemestral(), pm3411.getIdsector());
		if (bandera) {

			pm3411Repository.save(pm3411);

			count = pm3411Repository.count(pm3411.getIdsector());
			listPm3411 = this.getAcumulado(pm3411.getIdsector());
			listPm3411.get(index).setbGuardar(Boolean.TRUE);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");
		} else {

			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El Semestre ya existe");
		}

		return listPm3411;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3411BS#delete(java.lang.Integer,
	 * java.util.List)
	 */

	/** The res 1. */
	Integer res1;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3411BS#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3411> delete(Integer index, List<Pm3411> listPm3411) {

		Pm3411 pm3411;
		Integer idSector = listPm3411.get(index).getIdsector();
		pm3411Repository.delete(listPm3411.get(index).getId());
		listPm3411 = this.getAcumulado(idSector);

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		return listPm3411;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3411BS#clean(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm3411> clean(Integer index, List<Pm3411> listPm3411) {
		listPm3411.get(index).setSpcam(null);
		listPm3411.get(index).setObsspcam("");
		listPm3411.get(index).setTspaem(null);
		listPm3411.get(index).setObstspaem("");
		return listPm3411;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3411BS#cancel(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm3411> cancel(Integer index, List<Pm3411> listPm3411) {
		return orderByTrimestreAsc(listPm3411.get(index).getIdsector());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3411BS#orderByTrimestreAsc(java.lang.
	 * Integer)
	 */
	@Override
	public List<Pm3411> orderByTrimestreAsc(Integer idSector) {
		List<Pm3411> listPm3411 = pm3411Repository.findAllByIdsector(idSector, this.oderBy());
		if (CollectionUtils.isEmpty(listPm3411)) {
			Pm3411 pm3411 = new Pm3411();
			pm3411.setSemestral(0);
			listPm3411.add(pm3411);
		}
		return listPm3411;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3411BS#oderBy()
	 */
	@Override
	public Sort oderBy() {
		return new Sort(Sort.Direction.ASC, "semestral");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm3411BS#findByTrimestre(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public boolean findByTrimestre(Integer semestre, Integer idSector) {
		return pm3411Repository.countBySemestre(idSector, semestre) > 0 ? false : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3411BS#validateTxt(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public boolean validateTxt(Pm3411 pm3411) {
		boolean bandera = Boolean.TRUE;
		if (null == pm3411.getSpcam()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
					"El campo Servidores públicos certificados en temas de administración municipal es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm3411.getObsspcam()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
					"El campo Observaciones de Servidores públicos certificados en temas de administración municipal es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm3411.getTspaem()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
					"El campo Total de Servidores públicos  programados a certificar por la entidad municipal es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm3411.getObstspaem()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
					"El campo Observaciones de Total de Servidores públicos  programados a certificar por la entidad municipal es obligatorio");
			bandera = Boolean.FALSE;
		}
		return bandera;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3411BS#add()
	 */
	@Override
	public Pm3411 add() {
		Pm3411 pm3411 = new Pm3411();
		pm3411.setObsspcam("");
		pm3411.setObstspaem("");
		return pm3411;
	}

	/** The acuspcamp. */
	Integer acuspcamp = null;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3411BS#getAcumulado(java.lang.Integer)
	 */
	@Override
	public List<Pm3411> getAcumulado(Integer idSector) {
		acuspcamp = 0;
		List<Pm3411> list = new ArrayList<Pm3411>();
		list = pm3411Repository.findAllByIdsector(idSector);
		for (int i = 0; i < list.size(); i++) {
			acuspcamp = acuspcamp + list.get(i).getSpcam();
			list.get(i).setAcuspcam(acuspcamp);
			pm3411Repository.save(list.get(i));

		}
		return pm3411Repository.findAllByIdsector(idSector);
	}

}
