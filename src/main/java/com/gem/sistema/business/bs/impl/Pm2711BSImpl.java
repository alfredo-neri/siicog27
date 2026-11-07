package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm2711BS;
import com.gem.sistema.business.domain.Pm2711;
import com.gem.sistema.business.repository.catalogs.Pm2711Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm2711BSImpl.
 *
 * @author Mateo
 */
@Repository(value = "pm2711BS")
public class Pm2711BSImpl implements Pm2711BS{
	
	/** The pm 2711 repository. */
	@Autowired
	private Pm2711Repository pm2711Repository;

	/**
	 * Gets the pm 2711 repository.
	 *
	 * @return the pm 2711 repository
	 */
	public Pm2711Repository getPm2711Repository() {
		return pm2711Repository;
	}

	/**
	 * Sets the pm 2711 repository.
	 *
	 * @param pm2711Repository the new pm 2711 repository
	 */
	public void setPm2711Repository(Pm2711Repository pm2711Repository) {
		this.pm2711Repository = pm2711Repository;
	}

	/** The count. */
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2711BS#save(java.lang.Integer, java.util.List)
	 */
	Integer count;
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2711BS#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2711> save(Integer index, List<Pm2711> listPm2711) {
		Pm2711 pm2711 = listPm2711.get(index);
		boolean bandera;
		bandera = validateTxt(pm2711);
		listPm2711.get(index).setbGuardar(Boolean.FALSE);
		if(bandera) {
			bandera = findByTrimestre(pm2711.getSemestral(), pm2711.getIdsector());
			if(bandera) {
				pm2711Repository.save(pm2711);
				count = pm2711Repository.count(pm2711.getIdsector());
				listPm2711 = this.acumulado(pm2711.getIdsector());
				listPm2711.get(index).setbGuardar(Boolean.TRUE);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");
			} else {
				
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El semestre ya existe");
			}
		}
		return listPm2711;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2711BS#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2711> delete(Integer index, List<Pm2711> listPm2711) {
		pm2711Repository.delete(listPm2711.get(index).getId());
		listPm2711 = this.acumulado(listPm2711.get(index).getIdsector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		return listPm2711;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2711BS#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2711> clean(Integer index, List<Pm2711> listPm2711) {
		listPm2711.get(index).setLap(null);
		listPm2711.get(index).setObslap("");
		listPm2711.get(index).setTloc(null);
		listPm2711.get(index).setObstloc("");
		return listPm2711;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2711BS#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2711> cancel(Integer index, List<Pm2711> listPm2711) {
		return orderByTrimestreAsc(listPm2711.get(index).getIdsector());
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2711BS#orderByTrimestreAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm2711> orderByTrimestreAsc(Integer idSector) {
		List<Pm2711> listPm2711 = pm2711Repository.findAllByIdsector(idSector, this.oderBy());
		if(CollectionUtils.isEmpty(listPm2711)) {
			Pm2711 pm2711 = new Pm2711();
			pm2711.setSemestral(0);
			listPm2711.add(pm2711);
		}
		return listPm2711;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2711BS#oderBy()
	 */
	@Override
	public Sort oderBy() {
		return new Sort(Sort.Direction.ASC, "semestral");
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2711BS#findByTrimestre(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean findByTrimestre(Integer trimestre, Integer idSector) {
		return  pm2711Repository.countBySemestre(idSector, trimestre) > 0 ? false : true ;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2711BS#validateTxt(java.lang.Integer, java.util.List)
	 */
	@Override
	public boolean validateTxt(Pm2711 pm2711) {
		boolean bandera = Boolean.TRUE;
		if(null == pm2711.getLap()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El campo Localidades con alumbrado publico es obligatorio");
			bandera = Boolean.FALSE;
		} else if(null == pm2711.getObslap()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El campo Observaciones de Localidades con alumbrado publico es obligatorio");
			bandera = Boolean.FALSE;
		} else if(null == pm2711.getTloc()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El campo Total de Localidades es obligatorio");
			bandera = Boolean.FALSE;
		} else if(null == pm2711.getObstloc()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El campo Observaciones de Total de Localidades es obligatorio");
			bandera = Boolean.FALSE;
		}
		return bandera;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2711BS#add()
	 */
	@Override
	public Pm2711 add() {
		Pm2711 pm2711 = new Pm2711();
		pm2711.setObslap("");
		pm2711.setObstloc("");
		return pm2711;
	}

	/** The acumualado. */
	Integer acumualado = 0;
	
	/** The list. */
	List<Pm2711> list;
	
	/**
	 * Acumulado.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@Override
	public List<Pm2711> acumulado(Integer idSector) {
		list = new ArrayList<Pm2711>();
		list = pm2711Repository.getDataByIdSector(idSector);
		acumualado = 0;
		for(int i = 0; i < list.size(); i++) {
			acumualado = acumualado + list.get(i).getLap();
			list.get(i).setAculap(acumualado);
			pm2711Repository.save(list.get(i));
		}
		return this.orderByTrimestreAsc(idSector);
	}

}
