package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm0811BS;
import com.gem.sistema.business.domain.Pm0811;
import com.gem.sistema.business.repository.catalogs.Pm0811Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm0811BSImpl.
 *
 * @author Mateo
 */
@Repository(value = "pm0811BS")
public class Pm0811BSImpl implements Pm0811BS {

	/** The pm 0811 repository. */
	@Autowired
	private Pm0811Repository pm0811Repository;

	/**
	 * Gets the pm 0811 repository.
	 *
	 * @return the pm 0811 repository
	 */
	public Pm0811Repository getPm0811Repository() {
		return pm0811Repository;
	}

	/**
	 * Sets the pm 0811 repository.
	 *
	 * @param pm0811Repository the new pm 0811 repository
	 */
	public void setPm0811Repository(Pm0811Repository pm0811Repository) {
		this.pm0811Repository = pm0811Repository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm0811BS#save(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm0811> save(Integer index, List<Pm0811> listPm0811) {
		Pm0811 pm0811 = listPm0811.get(index);
		boolean bandera;
		bandera = findByTrimestre(pm0811.getSemestral(), pm0811.getIdsector());
		listPm0811.get(index).setbGuarda(Boolean.FALSE);
		if (bandera) {
			bandera = this.validateTxt(pm0811);
			if (bandera) {
				pm0811Repository.save(pm0811);
				listPm0811 = orderByTrimestreAsc(pm0811.getIdsector());
				listPm0811.get(index).setbGuarda(Boolean.TRUE);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"Se guardo la información correctamente");
			}
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El semestre ya existe");
		}
		return listPm0811;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm0811BS#delete(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm0811> delete(Integer index, List<Pm0811> listPm0811) {
		pm0811Repository.delete(listPm0811.get(index).getId());
		listPm0811 = orderByTrimestreAsc(listPm0811.get(index).getIdsector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se eliminó la información correctamente");
		return listPm0811;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm0811BS#clean(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm0811> clean(Integer index, List<Pm0811> listPm0811) {
		listPm0811.set(index, new Pm0811());
		return listPm0811;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm0811BS#cancel(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm0811> cancel(Integer index, List<Pm0811> listPm0811) {
		return orderByTrimestreAsc(listPm0811.get(index).getIdsector());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm0811BS#orderByTrimestreAsc(java.lang.
	 * Integer)
	 */
	@Override
	public List<Pm0811> orderByTrimestreAsc(Integer idSector) {
		List<Pm0811> listPm0811 = pm0811Repository.findAllByIdsector(idSector, this.oderBy());
		if (CollectionUtils.isEmpty(listPm0811)) {
			Pm0811 pm0811 = new Pm0811();
			pm0811.setSemestral(0);
			pm0811.setNspc(0);
			pm0811.setTesp(0);
			listPm0811.add(pm0811);
		}
		return listPm0811;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm0811BS#oderBy()
	 */
	@Override
	public Sort oderBy() {
		return new Sort(Sort.Direction.ASC, "semestral");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm0811BS#findByTrimestre(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public boolean findByTrimestre(Integer trimestre, Integer idSector) {
		return pm0811Repository.countBySemestre(idSector, trimestre) > 0 ? false : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm0811BS#validateTxt(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public boolean validateTxt(Pm0811 pm0811) {
		boolean bandera = Boolean.TRUE;
		
		pm0811.setObsnspc(null == pm0811.getObsnspc()?"":pm0811.getObsnspc());
		pm0811.setObstesp(null == pm0811.getObstesp()?"":pm0811.getObstesp());
		//Se comentan las validaciones ya que aun estando vacias debe permitir guardar el registro 
		// Número de elementos de seguridad pública capacitados en manejor de
		// armas de fuego
//		if (null == pm0811.getNspc()){
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"El campo Número de elementos de seguridad pública capacitados en manejar de armas de fuego es obligatorio");
//			bandera = Boolean.FALSE;
//		} else if (null == pm0811.getNspc())  {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"Total de elementos de seguridad publica");
//			bandera = Boolean.FALSE;
//		}
//		} else if(null == pm0811.getObsnspc() || null == pm0811.getObstesp()) {
//			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
//					"Los campos de Obeservaciones son obligatorios");
//			bandera = Boolean.FALSE;
//		}
		
		return bandera;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0811BS#add()
	 */
	@Override
	public Pm0811 add() {
		Pm0811 pm0811 = new Pm0811();
		pm0811.setObsnspc("");
		pm0811.setObstesp("");
		return pm0811;
	}

}
