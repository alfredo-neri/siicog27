package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm2511BS;
import com.gem.sistema.business.domain.Pm2511;
import com.gem.sistema.business.repository.catalogs.Pm2511Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm2511BSImpl.
 *
 * @author Mateo
 */
@Repository(value = "pm2511BS")
public class Pm2511BSImpl implements Pm2511BS{
	
	/** The pm 2511 repository. */
	@Autowired
	private Pm2511Repository pm2511Repository;

	/**
	 * Gets the pm 2511 repository.
	 *
	 * @return the pm 2511 repository
	 */
	public Pm2511Repository getPm2511Repository() {
		return pm2511Repository;
	}

	/**
	 * Sets the pm 2511 repository.
	 *
	 * @param pm2511Repository the new pm 2511 repository
	 */
	public void setPm2511Repository(Pm2511Repository pm2511Repository) {
		this.pm2511Repository = pm2511Repository;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2511BS#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2511> save(Integer index, List<Pm2511> listPm2511) {
		Pm2511 pm2511 = listPm2511.get(index);
		
		boolean bandera = Boolean.TRUE;
		bandera = validateTxt(pm2511);
		listPm2511.get(index).setbValidar(bandera);
		if(bandera) {
			bandera = findByTrimestre(pm2511.getTrimestre(), pm2511.getIdsector());
			if(bandera) {
				
					pm2511Repository.save(pm2511);
					
					listPm2511 = orderByTrimestreAsc(pm2511.getIdsector());
					listPm2511.get(index).setbValidar(Boolean.TRUE);
					generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");
			} else {
				listPm2511.get(index).setbValidar(Boolean.FALSE);
				listPm2511.get(index) ;
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El trimestre ya existe");
			}
	    }
		return listPm2511;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2511BS#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2511> delete(Integer index, List<Pm2511> listPm2511) {
		pm2511Repository.delete(listPm2511.get(index).getId());
		listPm2511 = orderByTrimestreAsc(listPm2511.get(index).getIdsector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		return listPm2511;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2511BS#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2511> clean(Integer index, List<Pm2511> listPm2511) {
		
		listPm2511.get(index).setTrimestre(0);
		listPm2511.get(index).setAgua(BigDecimal.ZERO);
		listPm2511.get(index).setObsagua("");
		listPm2511.get(index).setPoblacion(0);
		listPm2511.get(index).setObspob("");
		return listPm2511;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2511BS#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2511> cancel(Integer index, List<Pm2511> listPm2511) {
		return orderByTrimestreAsc(listPm2511.get(index).getIdsector());
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2511BS#orderByTrimestreAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm2511> orderByTrimestreAsc(Integer idSector) {
		List<Pm2511> listPm2511 = pm2511Repository.findAllByIdsector(idSector, this.oderBy());
		if(CollectionUtils.isEmpty(listPm2511)) {
			Pm2511 pm2511 = new Pm2511();
			pm2511.setTrimestre(0);
			pm2511.setAcumagua(BigDecimal.ZERO);
			listPm2511.add(pm2511);
		}
		return listPm2511;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2511BS#oderBy()
	 */
	@Override
	public Sort oderBy() {
		return new Sort(Sort.Direction.ASC, "trimestre");
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2511BS#findByTrimestre(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean findByTrimestre(Integer trimestre, Integer idSector) {
		return  pm2511Repository.countByTrimestre(idSector, trimestre) > 0 ? false : true ;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2511BS#validateTxt(java.lang.Integer, java.util.List)
	 */
	@Override
	public boolean validateTxt(Pm2511 pm2511) {
		
		boolean bandera = Boolean.TRUE;
		if(null == pm2511.getAgua()) 
			pm2511.setAgua(BigDecimal.ZERO);
		if(null == pm2511.getObsagua()) 
			pm2511.setObsagua("");
		if(null == pm2511.getPoblacion()) 
			pm2511.setPoblacion(0);
		if(null == pm2511.getObspob()) 
			pm2511.setObspob("");
		
		return bandera;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2511BS#add()
	 */
	@Override
	public Pm2511 add() {
		Pm2511 pm2511 = new Pm2511();
		pm2511.setObsagua("");
		pm2511.setObspob("");
		return pm2511;
	}

}
