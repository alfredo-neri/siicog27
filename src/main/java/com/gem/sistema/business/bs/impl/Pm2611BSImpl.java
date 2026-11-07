package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm2611BS;
import com.gem.sistema.business.domain.Pm2611;
import com.gem.sistema.business.repository.catalogs.Pm2611Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm2611BSImpl.
 *
 * @author Mateo
 */
@Repository(value = "pm2611BS")
public class Pm2611BSImpl implements Pm2611BS {

	/** The pm 2611 repository. */
	@Autowired
	private Pm2611Repository pm2611Repository;

	/**
	 * Gets the pm 2611 repository.
	 *
	 * @return the pm 2611 repository
	 */
	public Pm2611Repository getPm2611Repository() {
		return pm2611Repository;
	}

	/**
	 * Sets the pm 2611 repository.
	 *
	 * @param pm2611Repository the new pm 2611 repository
	 */
	public void setPm2611Repository(Pm2611Repository pm2611Repository) {
		this.pm2611Repository = pm2611Repository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm2611BS#save(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm2611> save(Integer index, List<Pm2611> listPm2611) {
		Pm2611 pm2611 = listPm2611.get(index);
		boolean bandera;
		listPm2611.get(index).setbGuardar(Boolean.FALSE);
		bandera = findBySemestre(pm2611.getSemes(), pm2611.getIdsector());
		if (bandera) {
			bandera = validateTxt(pm2611);
			if (bandera) {
				pm2611Repository.save(pm2611);
				listPm2611 = orderByTrimestreAsc(pm2611.getIdsector());
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"Se guardo la información correctamente");
				listPm2611.get(index).setbGuardar(Boolean.TRUE);

				pm2611.setObstot(null == pm2611.getObstot() ? "" : pm2611.getObstot());
				pm2611.setObstra(null == pm2611.getObstra() ? "" : pm2611.getObstra());
			}
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El Semestre ya existe");
		}
		return listPm2611;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm2611BS#delete(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm2611> delete(Integer index, List<Pm2611> listPm2611) {
		pm2611Repository.delete(listPm2611.get(index).getId());
		listPm2611 = orderByTrimestreAsc(listPm2611.get(index).getIdsector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		return listPm2611;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm2611BS#clean(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm2611> clean(Integer index, List<Pm2611> listPm2611) {

		listPm2611.get(index).setSemes(0);
		listPm2611.get(index).setVoltot(BigDecimal.ZERO);
		listPm2611.get(index).setVoltra(BigDecimal.ZERO);
		listPm2611.get(index).setObstot("");
		listPm2611.get(index).setObstra("");
		return listPm2611;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm2611BS#cancel(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm2611> cancel(Integer index, List<Pm2611> listPm2611) {
		return orderByTrimestreAsc(listPm2611.get(index).getIdsector());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm2611BS#orderByTrimestreAsc(java.lang.
	 * Integer)
	 */
	@Override
	public List<Pm2611> orderByTrimestreAsc(Integer idSector) {
		List<Pm2611> listPm2611 = pm2611Repository.findAllByIdsector(idSector, this.oderBy());
		if (CollectionUtils.isEmpty(listPm2611)) {
			Pm2611 pm2611 = new Pm2611();
			pm2611.setSemes(0);
			listPm2611.add(pm2611);
		}
		return listPm2611;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm2611BS#oderBy()
	 */
	@Override
	public Sort oderBy() {
		return new Sort(Sort.Direction.ASC, "semes");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm2611BS#findBySemestre(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public boolean findBySemestre(Integer semestre, Integer idSector) {
		return pm2611Repository.countByTrimestre(idSector, semestre) > 0 ? false : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm2611BS#validateTxt(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public boolean validateTxt(Pm2611 pm2611) {
		boolean bandera = Boolean.TRUE;
		if (null == pm2611.getVoltot()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo Volumen total generado es obligatorio");
			bandera = Boolean.FALSE;

		} else if (null == pm2611.getVoltra()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El campo Volumen tratado es obligatorio");
			bandera = Boolean.FALSE;
		}
		// Se comenta validaciones de campos de observaciones nulas
		// } else if(null == pm2611.getObstot()) {
		// generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El
		// campo Observaciones del Volumen Total es obligatorio");
		// bandera = Boolean.FALSE;
		//
		// } else if(null == pm2611.getObstra()) {
		// generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El
		// campo Observaciones del Volumen tratado es obligatorio");
		// bandera = Boolean.FALSE;
		//
		// }
		return bandera;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm2611BS#add()
	 */
	@Override
	public Pm2611 add() {
		Pm2611 pm2611 = new Pm2611();
		pm2611.setObstot("");
		pm2611.setObstra("");
		return pm2611;
	}

}
