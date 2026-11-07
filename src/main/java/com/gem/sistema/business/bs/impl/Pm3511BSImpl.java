package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm3511BS;
import com.gem.sistema.business.domain.Pm3511;
import com.gem.sistema.business.repository.catalogs.Pm3511Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm3511BSImpl.
 *
 * @author Mateo
 */

@Repository(value = "pm3511BS")
public class Pm3511BSImpl implements Pm3511BS {
	
	/** The Constant SI. */
	private static final String SI = "SI";
	
	/** The pm 3511 repository. */
	@Autowired
	private Pm3511Repository pm3511Repository;

	/**
	 * Gets the pm 3511 repository.
	 *
	 * @return the pm 3511 repository
	 */
	public Pm3511Repository getPm3511Repository() {
		return pm3511Repository;
	}

	/**
	 * Sets the pm 3511 repository.
	 *
	 * @param pm3511Repository the new pm 3511 repository
	 */
	public void setPm3511Repository(Pm3511Repository pm3511Repository) {
		this.pm3511Repository = pm3511Repository;
	}

	/** The count. */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3511BS#save(java.lang.Integer,
	 * java.util.List)
	 */
	Integer count;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3511BS#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3511> save(Integer index, List<Pm3511> listPm3511) {
		Pm3511 pm3511 = listPm3511.get(index);
		boolean bandera;
		bandera = validateTxt(pm3511);
		listPm3511.get(index).setbGuardar(Boolean.FALSE);
		if (bandera) {
			bandera = findByTrimestre(pm3511.getSemes(), pm3511.getIdsector());
			if (bandera) {
				pm3511.setPubpuesto(null == pm3511.getPubpuesto() ? "" : pm3511.getPubpuesto());
				pm3511.setPublugar(null == pm3511.getPublugar() ?  "" : pm3511.getPublugar());
				pm3511.setEstsup(null == pm3511.getEstsup() ? "" : pm3511.getEstsup());
				pm3511.setEspecer(null == pm3511.getEspecer() ? "" : pm3511.getEspecer());
				pm3511Repository.save(pm3511);
				count = pm3511Repository.count(pm3511.getIdsector());
				listPm3511 = orderByTrimestreAsc(pm3511.getIdsector());
				listPm3511.get(index).setbGuardar(Boolean.TRUE);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"Se guardo la información correctamente");
			} else {

				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El semestre ya existe");
			}
		}
		return listPm3511;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3511BS#delete(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm3511> delete(Integer index, List<Pm3511> listPm3511) {
		pm3511Repository.delete(listPm3511.get(index).getId());
		listPm3511 = orderByTrimestreAsc(listPm3511.get(index).getIdsector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		return listPm3511;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3511BS#clean(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public Pm3511 clean() {
		Pm3511 pm3511 = new Pm3511();
		pm3511.setDocu("");
		pm3511.setSecpub("SI");
		pm3511.setEstsup("SI");
		pm3511.setCertif("SI");
		return pm3511;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3511BS#cancel(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm3511> cancel(Integer index, List<Pm3511> listPm3511) {
		return orderByTrimestreAsc(listPm3511.get(index).getIdsector());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3511BS#orderByTrimestreAsc(java.lang.
	 * Integer)
	 */
	@Override
	public List<Pm3511> orderByTrimestreAsc(Integer idSector) {
		List<Pm3511> listPm3511 = pm3511Repository.findAllByIdsector(idSector, this.oderBy());
		if (CollectionUtils.isEmpty(listPm3511)) {
			Pm3511 pm3511 = new Pm3511();
			pm3511.setSemes(0);
			listPm3511.add(pm3511);
		}
		return listPm3511;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3511BS#oderBy()
	 */
	@Override
	public Sort oderBy() {
		return new Sort(Sort.Direction.ASC, "semes");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm3511BS#findByTrimestre(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public boolean findByTrimestre(Integer semestre, Integer idSector) {
		return pm3511Repository.countBySemestre(idSector, semestre) > 0 ? false : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3511BS#validateTxt(java.lang.Integer,
	 * java.util.List)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm3511BS#validateTxt(com.gem.sistema.business
	 * .domain.Pm3511)
	 */
	@Override
	public boolean validateTxt(Pm3511 pm3511) {
		boolean bandera = Boolean.TRUE;
		if (null == pm3511.getNombre()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El campo Nombre es obligatorio");
			bandera = Boolean.FALSE;
//		} else if (null == pm3511.getEdad()) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El campo Edad es obligatorio");
//			bandera = Boolean.FALSE;
//		} else if (null == pm3511.getFecing()) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"El campo Fecha de Ingreso al Cargo de Tesoreria Municipal es obligatorio");
//			bandera = Boolean.FALSE;
		} else if (null == pm3511.getGrado()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo Maximo grado academico es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm3511.getDocu()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo Documento que lo acredita es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm3511.getExperi()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo  Experiencia laboral relacionada con el puesto que desempeña actualmente es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm3511.getUltpuesto()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo  Experiencia laboral relacionada con el puesto que desempeña actualmente es obligatorio");
			bandera = Boolean.FALSE;
//		} else if (null == pm3511.getFiupuesto() || null == pm3511.getFtupuesto() ){
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"La Fecha de Inicio y la Fecha Final son obligatorias");
//			bandera = Boolean.FALSE;
//		}else if (pm3511.getFiupuesto().compareTo(pm3511.getFtupuesto()) > 0) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"La Fecha de Inicio debe de ser menor a la final");
//			bandera = Boolean.FALSE;
		} else if (pm3511.getSecpub().equals(SI)) {
			if (null == pm3511.getPubpuesto()) {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El campo Puesto es obligatorio");
				bandera = Boolean.FALSE;
			} else if (null == pm3511.getUltlugar()) {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El campo Lugar es obligatorio");
				bandera = Boolean.FALSE;
			}
		} else if (pm3511.getEstsup().equals(SI)) {
			if (null == pm3511.getEspest()) {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
						"El campo institución, y documento que lo avala es obligatorio");
				bandera = Boolean.FALSE;
			}

//		} else if (pm3511.getCertif().equals(SI)) {
//			if (null == pm3511.getEspecer()) {
//				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//						"La fecha inicial del Reglamento interior de trabajo de ser menor a la final");
//				bandera = Boolean.FALSE;
//			}
//
		} 
		return bandera;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3511BS#add()
	 */
	@Override
	public Pm3511 add() {
		Pm3511 pm3511 = new Pm3511();
		pm3511.setDocu("");
		pm3511.setSecpub("SI");
		pm3511.setEstsup("SI");
		pm3511.setCertif("SI");
		return pm3511;
	}

}
