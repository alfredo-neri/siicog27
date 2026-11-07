package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm3811BS;
import com.gem.sistema.business.domain.Pm3811;
import com.gem.sistema.business.repository.catalogs.Pm3811Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm3811BSImpl.
 *
 * @author Mateo
 */

@Repository(value = "pm3811BS")
public class Pm3811BSImpl implements Pm3811BS {
	
	/** The Constant SI. */
	private static final String SI = "SI";
	
	/** The pm 3811 repository. */
	@Autowired
	private Pm3811Repository pm3811Repository;

	/**
	 * Gets the pm 3811 repository.
	 *
	 * @return the pm 3811 repository
	 */
	public Pm3811Repository getPm3811Repository() {
		return pm3811Repository;
	}

	/**
	 * Sets the pm 3811 repository.
	 *
	 * @param pm3811Repository the new pm 3811 repository
	 */
	public void setPm3811Repository(Pm3811Repository pm3811Repository) {
		this.pm3811Repository = pm3811Repository;
	}

	/** The count. */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3811BS#save(java.lang.Integer,
	 * java.util.List)
	 */
	Integer count;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm3811BS#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm3811> save(Integer index, List<Pm3811> listPm3811) {
		Pm3811 pm3811 = listPm3811.get(index);
		boolean bandera;
		bandera = validateTxt(pm3811);
		listPm3811.get(index).setbGuardar(Boolean.FALSE);
		if (bandera) {
			bandera = findByTrimestre(pm3811.getSemestral(), pm3811.getIdsector());
			if (bandera) {

				pm3811Repository.save(pm3811);
				count = pm3811Repository.count(pm3811.getIdsector());
				listPm3811 = orderByTrimestreAsc(pm3811.getIdsector());
				listPm3811.get(index).setbGuardar(Boolean.TRUE);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"Se guardo la información correctamente");
			} else {

				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El semestre ya existe");
			}
		}
		return listPm3811;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3811BS#delete(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm3811> delete(Integer index, List<Pm3811> listPm3811) {
		pm3811Repository.delete(listPm3811.get(index).getId());
		listPm3811 = orderByTrimestreAsc(listPm3811.get(index).getIdsector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		return listPm3811;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3811BS#clean(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public Pm3811 clean() {
		return new Pm3811();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3811BS#cancel(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm3811> cancel(Integer index, List<Pm3811> listPm3811) {
		return orderByTrimestreAsc(listPm3811.get(index).getIdsector());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3811BS#orderByTrimestreAsc(java.lang.
	 * Integer)
	 */
	@Override
	public List<Pm3811> orderByTrimestreAsc(Integer idSector) {
		List<Pm3811> listPm3811 = pm3811Repository.findAllByIdsector(idSector, this.oderBy());
		if (CollectionUtils.isEmpty(listPm3811)) {
			Pm3811 pm3811 = new Pm3811();
			pm3811.setSemestral(0);
			listPm3811.add(pm3811);
		}
		return listPm3811;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3811BS#oderBy()
	 */
	@Override
	public Sort oderBy() {
		return new Sort(Sort.Direction.ASC, "semestral");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm3811BS#findByTrimestre(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public boolean findByTrimestre(Integer semestre, Integer idSector) {
		return pm3811Repository.countBySemestre(idSector, semestre) > 0 ? false : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3811BS#validateTxt(java.lang.Integer,
	 * java.util.List)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm3811BS#validateTxt(com.gem.sistema.business
	 * .domain.Pm3811)
	 */
	@Override
	public boolean validateTxt(Pm3811 pm3811) {
		boolean bandera = Boolean.TRUE;

		// Se comentas lineas que validaban que el campo no estuviera vacio
		// if (null == pm3811.getNombre()) {
		// generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El
		// campo Nombre es obligatorio");
		// bandera = Boolean.FALSE;
		// } else if (null == pm3811.getEdad()) {
		// generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El
		// campo Edad es obligatorio");
		// bandera = Boolean.FALSE;
		// } else if (null == pm3811.getFecing()) {
		// generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
		// "El campo Fecha de Ingreso al Cargo de Tesoreria Municipal es
		// obligatorio");
		// bandera = Boolean.FALSE;
		// } else if (null == pm3811.getGrado()) {
		// generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
		// "El campo Maximo grado academico es obligatorio");
		// bandera = Boolean.FALSE;
		// } else if (null == pm3811.getDoc()) {
		// generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
		// "El campo Documento que lo acredita es obligatorio");
		// bandera = Boolean.FALSE;
		// } else if (null == pm3811.getExplab()) {
		// generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
		// "El campo Experiencia laboral relacionada con el puesto que desempeña
		// actualmente es obligatorio");
		// bandera = Boolean.FALSE;
		// } else if (null == pm3811.getUltpuesto()) {
		// generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
		// "El campo Experiencia laboral relacionada con el puesto que desempeña
		// actualmente es obligatorio");
		// bandera = Boolean.FALSE;
		// } else
		if (null != pm3811.getFtupuesto() && null != pm3811.getFiupuesto()) {
			if (pm3811.getFiupuesto().compareTo(pm3811.getFtupuesto()) > 0) {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
						"La Fecha de Inicio  de Empleo Previo debe ser menor a la final");
				bandera = Boolean.FALSE;
			}
		} else if (pm3811.getSecpub().equals(SI)) {
			if (null != pm3811.getFipspublico() && null != pm3811.getFtpspublico()) {
				if (pm3811.getFipspublico().compareTo(pm3811.getFtpspublico()) > 0) {

					generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
							"La Fecha de Inicio de Puesto Público debe ser menor a la final");
					bandera = Boolean.FALSE;
				}
			}
		}

		// } else if (pm3811.getSecpub().equals(SI)) {
		// if (null == pm3811.getPubpuesto()) {
		// generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El
		// campo Puesto es obligatorio");
		// bandera = Boolean.FALSE;
		// } else if (null == pm3811.getPublugar()) {
		// generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El
		// campo Lugar es obligatorio");
		// bandera = Boolean.FALSE;
		// }
		// } else if (pm3811.getEstsup().equals(SI)) {
		// if (null == pm3811.getEspest()) {
		// generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
		// "El campo institución, y documento que lo avala es obligatorio");
		// bandera = Boolean.FALSE;
		// }
		//
		// }
		// else
		// if (pm3811.getCert().equals(SI)) {
		// if (null == pm3811.getEspcert()) {
		// generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
		// "La fecha inicial del Reglamento interior de trabajo de ser menor ala
		// final");
		// bandera = Boolean.FALSE;
		// }
		//
		// }
		return bandera;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm3811BS#add()
	 */
	@Override
	public Pm3811 add() {
		Pm3811 pm3811 = new Pm3811();
		pm3811.setDoc("");
		return pm3811;
	}

}
