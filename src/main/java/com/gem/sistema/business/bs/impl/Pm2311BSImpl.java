package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm2311BS;
import com.gem.sistema.business.domain.Pm2311;
import com.gem.sistema.business.repository.catalogs.Pm2311Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm2311BSImpl.
 *
 * @author Mateo
 */
@Repository(value = "pm2311BS")
public class Pm2311BSImpl implements Pm2311BS{
	
	/** The pm 2311 repository. */
	@Autowired
	private Pm2311Repository pm2311Repository;

	/**
	 * Gets the pm 2311 repository.
	 *
	 * @return the pm 2311 repository
	 */
	public Pm2311Repository getPm2311Repository() {
		return pm2311Repository;
	}

	/**
	 * Sets the pm 2311 repository.
	 *
	 * @param pm2311Repository the new pm 2311 repository
	 */
	public void setPm2311Repository(Pm2311Repository pm2311Repository) {
		this.pm2311Repository = pm2311Repository;
	}

	/** The count. */
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2311BS#save(java.lang.Integer, java.util.List)
	 */
	Integer count;
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2311BS#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2311> save(Integer index, List<Pm2311> listPm2311) {
		Pm2311 pm2311 = listPm2311.get(index);
		boolean bandera;
		bandera = validateTxt(pm2311);
		listPm2311.get(index).setbGuardar(Boolean.FALSE);
		if(bandera) {
			bandera = findByTrimestre(pm2311.getSemestral(), pm2311.getIdsector());
			if(bandera) {
				pm2311Repository.save(pm2311);
				count = pm2311Repository.count(pm2311.getIdsector());
				listPm2311 = orderByTrimestreAsc(pm2311.getIdsector());
				listPm2311.get(index).setbGuardar(Boolean.TRUE);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");
			} else {
				
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El semestre ya existe");
			}
		}
		return listPm2311;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2311BS#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2311> delete(Integer index, List<Pm2311> listPm2311) {
		pm2311Repository.delete(listPm2311.get(index).getId());
		listPm2311 = orderByTrimestreAsc(listPm2311.get(index).getIdsector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		return listPm2311;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2311BS#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2311> clean(Integer index, List<Pm2311> listPm2311) {
		listPm2311.get(index).setTvm(null);
		listPm2311.get(index).setObsgral("");
		listPm2311.get(index).setNvap(null);
		listPm2311.get(index).setNvcp(null);
		listPm2311.get(index).setNvdre(null);
		listPm2311.get(index).setNvrb(null);
		return listPm2311;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2311BS#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2311> cancel(Integer index, List<Pm2311> listPm2311) {
		return orderByTrimestreAsc(listPm2311.get(index).getIdsector());
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2311BS#orderByTrimestreAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm2311> orderByTrimestreAsc(Integer idSector) {
		List<Pm2311> listPm2311 = pm2311Repository.findAllByIdsector(idSector, this.oderBy());
		if(CollectionUtils.isEmpty(listPm2311)) {
			Pm2311 pm2311 = new Pm2311();
			pm2311.setSemestral(0);
			listPm2311.add(pm2311);
		}
		return listPm2311;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2311BS#oderBy()
	 */
	@Override
	public Sort oderBy() {
		return new Sort(Sort.Direction.ASC, "semestral");
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2311BS#findByTrimestre(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean findByTrimestre(Integer trimestre, Integer idSector) {
		return  pm2311Repository.countBySemestre(idSector, trimestre) > 0 ? false : true ;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2311BS#validateTxt(java.lang.Integer, java.util.List)
	 */
	@Override
	public boolean validateTxt(Pm2311 pm2311) {
		boolean bandera = Boolean.TRUE;
		if(null == pm2311.getTvm()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El campo Total de viviendas en el  Municipio es obligatorio");
			bandera = Boolean.FALSE;
		} else if(null == pm2311.getNvap()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El campo Agua Potable es obligatorio");
			bandera = Boolean.FALSE;
		} else if(null == pm2311.getNvcp()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El campo Calles Pavimentadas es obligatorio");
			bandera = Boolean.FALSE;
		} else if(null == pm2311.getNvdre()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El campo Drenaje es obligatorio");
			bandera = Boolean.FALSE;
		} else if(null == pm2311.getNvrb()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El campo Recoleccion de Basura es obligatorio");
			bandera = Boolean.FALSE;
		} else if(null == pm2311.getObsgral()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El campo Observaciones es obligatorio");
			bandera = Boolean.FALSE;
		}
		return bandera;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2311BS#add()
	 */
	@Override
	public Pm2311 add() {
		Pm2311 pm2311 = new Pm2311();
		pm2311.setObsgral("");
		return pm2311;
	}

}
