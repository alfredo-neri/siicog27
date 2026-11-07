package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;


import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm0411BS;
import com.gem.sistema.business.domain.Pm0411;

import com.gem.sistema.business.repository.catalogs.Pm0411Repository;
// TODO: Auto-generated Javadoc

/**
 * The Class Pm0411BSImpl.
 */
@Repository(value = "pm0411BS")
public class Pm0411BSImpl implements Pm0411BS {

	/** The pm 0411 repository. */
	@Autowired
	private Pm0411Repository pm0411Repository;

	/**
	 * Gets the pm 0411 repository.
	 *
	 * @return the pm 0411 repository
	 */
	public Pm0411Repository getPm0411Repository() {
		return pm0411Repository;
	}

	/**
	 * Sets the pm 0411 repository.
	 *
	 * @param pm0411Repository the new pm 0411 repository
	 */
	public void setPm0411Repository(Pm0411Repository pm0411Repository) {
		this.pm0411Repository = pm0411Repository;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0411BS#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm0411> save(Integer index, List<Pm0411> listPm0411) {
		Pm0411 pm0411 = listPm0411.get(index);
		listPm0411.get(index).setgBuardar(Boolean.FALSE);
		boolean bandera = findByTrimestre(pm0411.getTrimestre(), pm0411.getIdsector());
		if(bandera == Boolean.TRUE) {
			
			pm0411Repository.save(pm0411);
			
			this.calculationAccumulated(pm0411.getIdsector());
			listPm0411 = orderByTrimestreAsc(pm0411.getIdsector());
			listPm0411.get(index).setgBuardar(Boolean.TRUE);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El trimestre ya existe");
		}
		return listPm0411;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0411BS#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm0411> delete(Integer index, List<Pm0411> listPm0411) {
		pm0411Repository.delete(listPm0411.get(index).getId());
		this.calculationAccumulated(listPm0411.get(index).getIdsector());
		listPm0411 = orderByTrimestreAsc(listPm0411.get(index).getIdsector());
		
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		return listPm0411;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0411BS#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm0411> clean(Integer index, List<Pm0411> listPm0411) {
		listPm0411.add(index, new Pm0411());
		return listPm0411;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0411BS#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm0411> cancel(Integer index, List<Pm0411> listPm0411) {
		return orderByTrimestreAsc(listPm0411.get(index).getIdsector());
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0411BS#orderByTrimestreAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm0411> orderByTrimestreAsc(Integer idSector) {
		List<Pm0411> listPm0411 = pm0411Repository.findAllByIdsector(idSector, this.oderBy());
		if(CollectionUtils.isEmpty(listPm0411)) {
			Pm0411 Pm0411 = new Pm0411();
			Pm0411.setTrimestre(0);
			Pm0411.setAcudc(0);
			listPm0411.add(Pm0411);
		}
		return listPm0411;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0411BS#oderBy()
	 */
	@Override
	public Sort oderBy() {
		return new Sort(Sort.Direction.ASC, "trimestre");
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0411BS#findByTrimestre(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean findByTrimestre(Integer trimestre, Integer idSector) {
		return  pm0411Repository.countByTrimestre(idSector, trimestre) > 0 ? false : true ;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0411BS#validateTxt(java.lang.Integer, java.util.List)
	 */
	@Override
	public boolean validateTxt(Integer index, List<Pm0411> listPm0411) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0411BS#add()
	 */
	@Override
	public Pm0411 add() {
		Pm0411 Pm0411 = new Pm0411();
		Pm0411.setObsdc("");
		Pm0411.setObsdi("");
		return Pm0411;
	}
	
	/** The acudc. */
	Integer acudc;
	
	/** The acudi. */
	Integer acudi;
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm0411BS#calculationAccumulated(java.lang.Integer)
	 */
	@Override
	public List<Pm0411> calculationAccumulated(Integer idSector) {
		List<Pm0411> listAccu = pm0411Repository.findAllByIdsectorOrderByTrimestreAsc(idSector);
		acudc = 0;
		acudi = 0;
		for(int i = 0; i  < listAccu.size(); i++) {
			acudc = acudc + listAccu.get(i).getDc();
			acudi = acudi + listAccu.get(i).getDi();
			listAccu.get(i).setAcudc(acudc);
			listAccu.get(i).setAcudi(acudi);
			Pm0411 entity = listAccu.get(i);
			pm0411Repository.save(entity);
		}
		return listAccu;
	}

}
