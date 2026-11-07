package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm5811BS;
import com.gem.sistema.business.domain.Pm5811;
import com.gem.sistema.business.repository.catalogs.Pm5811Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm5811BSImpl.
 *
 * @author Mateo
 */

@Repository(value = "pm5811BS")
public class Pm5811BSImpl implements Pm5811BS {
	
	/** The Constant SI. */
	private static final String SI = "SI";
	
	/** The pm 5811 repository. */
	@Autowired
	private Pm5811Repository pm5811Repository;

	/**
	 * Gets the pm 5811 repository.
	 *
	 * @return the pm 5811 repository
	 */
	public Pm5811Repository getPm5811Repository() {
		return pm5811Repository;
	}

	/**
	 * Sets the pm 5811 repository.
	 *
	 * @param pm5811Repository the new pm 5811 repository
	 */
	public void setPm5811Repository(Pm5811Repository pm5811Repository) {
		this.pm5811Repository = pm5811Repository;
	}

	/** The count. */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm5811BS#save(java.lang.Integer,
	 * java.util.List)
	 */
	Integer count;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm5811BS#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm5811> save(Integer index, List<Pm5811> listPm5811) {
		Pm5811 pm5811 = listPm5811.get(index);
		boolean bandera;
		bandera = validateTxt(pm5811);
		listPm5811.get(index).setbGuardar(Boolean.FALSE);
		if (bandera) {
			bandera = findBySemestral(pm5811.getSemes(), pm5811.getIdsector());
			if (bandera) {
				pm5811.setPubpuesto(null == pm5811.getPubpuesto() ? "" : pm5811.getPubpuesto());
				pm5811.setPublugar(null == pm5811.getPublugar() ?  "" : pm5811.getPublugar());
				pm5811.setEstsup(null == pm5811.getEstsup() ? "" : pm5811.getEstsup());
				//pm5811.setEspcert(null == pm5811.getEspcert() ? "" : pm5811.getEspcert());
				pm5811Repository.save(pm5811);
				count = pm5811Repository.count(pm5811.getIdsector());
				listPm5811 = orderBySemestralAsc(pm5811.getIdsector());
				listPm5811.get(index).setbGuardar(Boolean.TRUE);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"Se guardo la información correctamente");
			} else {

				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El semestre ya existe");
			}
		}
		return listPm5811;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm5811BS#delete(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm5811> delete(Integer index, List<Pm5811> listPm5811) {
		pm5811Repository.delete(listPm5811.get(index).getId());
		listPm5811 = orderBySemestralAsc(listPm5811.get(index).getIdsector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		return listPm5811;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm5811BS#clean(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public Pm5811 clean() {
		return new Pm5811();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm5811BS#cancel(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm5811> cancel(Integer index, List<Pm5811> listPm5811) {
		return orderBySemestralAsc(listPm5811.get(index).getIdsector());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm5811BS#orderByTrimestreAsc(java.lang.
	 * Integer)
	 */
	@Override
	public List<Pm5811> orderBySemestralAsc(Integer idSector) {
		List<Pm5811> listPm5811 = pm5811Repository.findAllByIdsector(idSector, this.oderBy());
		if (CollectionUtils.isEmpty(listPm5811)) {
			Pm5811 pm5811 = new Pm5811();
			pm5811.setSemes(0);
			listPm5811.add(pm5811);
		}
		return listPm5811;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm5811BS#oderBy()
	 */
	@Override
	public Sort oderBy() {
		return new Sort(Sort.Direction.ASC, "semes");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm5811BS#findByTrimestre(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public boolean findBySemestral(Integer semestre, Integer idSector) {
		return pm5811Repository.countBySemestre(idSector, semestre) > 0 ? false : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm5811BS#validateTxt(java.lang.Integer,
	 * java.util.List)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm5811BS#validateTxt(com.gem.sistema.business
	 * .domain.Pm5811)
	 */
	@Override
	public boolean validateTxt(Pm5811 pm5811) {
		boolean bandera = Boolean.TRUE;
		if (null == pm5811.getNombre()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El campo Nombre es obligatorio");
			bandera = Boolean.FALSE;
//		} else if (null == pm5811.get()) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El campo Edad es obligatorio");
//			bandera = Boolean.FALSE;
//		} else if (null == pm5811.getFecing()) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"El campo  Fecha de Ingreso al Cargo de Director de Desarrollo Economico es obligatorio");
//			bandera = Boolean.FALSE;
		} else if (null == pm5811.getGrado()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo Maximo grado academico es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm5811.getDocu()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo Documento que lo acredita es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm5811.getExperi()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo  Experiencia laboral relacionada con el puesto que desempeña actualmente es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm5811.getUltpuesto()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo  Experiencia laboral relacionada con el puesto que desempeña actualmente es obligatorio");
			bandera = Boolean.FALSE;
//		} else if (null == pm5811.getUltfecini() || null == pm5811.getUltfecfin() ){
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"Los campos Fecha Inicial y Fecha Final del Empleo Previo son obligatorios");
//			bandera = Boolean.FALSE;
//		}else if (pm5811.getUltfecini().compareTo(pm5811.getUltfecfin()) > 0) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"La Fecha de inicio del empleo previo debe de ser menor a la Fecha de termino del empleo previo");
//			bandera = Boolean.FALSE;
		} else if (pm5811.getSecpub().equals(SI)) {
			
			if (null == pm5811.getPubpuesto()) {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El campo Puesto es obligatorio");
				bandera = Boolean.FALSE;
			} else if (null == pm5811.getPublugar()) {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El campo Lugar es obligatorio");
				bandera = Boolean.FALSE;
//			} else if(null == pm5811.getPubfecini()) {
//				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//						"Fecha de Inicio del cargo del Sector Público anterior es obligatoria");
//				bandera = Boolean.FALSE;
//			} else if (null == pm5811.getPubfecfin()) {
//				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//						"Fecha de Termino del cargo del sector Público anterior ");
//				bandera = Boolean.FALSE;
//			}else if(pm5811.getPubfecfin().compareTo(pm5811.getPubfecini())>0){
//				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//						"La Fecha de inicio del cargo del Sector Público anterior debe de ser menor a la Fecha de inicio del cargo del sector Públic anterior");
//				bandera = Boolean.FALSE;
			}
		} else if (pm5811.getEstsup().equals(SI)) {
			if (null == pm5811.getEspest()) {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
						"El campo institución, y documento que lo avala es obligatorio");
				bandera = Boolean.FALSE;
			}

		}
		return bandera;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm5811BS#add()
	 */
	@Override
	public Pm5811 add() {
		Pm5811 pm5811 = new Pm5811();
		pm5811.setDocu("");
		return pm5811;
	}

}
