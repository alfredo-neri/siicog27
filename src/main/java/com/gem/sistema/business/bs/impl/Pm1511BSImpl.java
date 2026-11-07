package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm1511BS;
import com.gem.sistema.business.domain.Pm1511;
import com.gem.sistema.business.repository.catalogs.Pm1511Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm1511BSImpl.
 *
 * @author Mateo
 */
@Repository(value = "pm1511BS")
public class Pm1511BSImpl implements Pm1511BS {

	/** The pm 1511 repository. */
	@Autowired
	private Pm1511Repository pm1511Repository;

	/**
	 * Gets the pm 1511 repository.
	 *
	 * @return the pm 1511 repository
	 */
	public Pm1511Repository getPm1511Repository() {
		return pm1511Repository;
	}

	/**
	 * Sets the pm 1511 repository.
	 *
	 * @param pm1511Repository the new pm 1511 repository
	 */
	public void setPm1511Repository(Pm1511Repository pm1511Repository) {
		this.pm1511Repository = pm1511Repository;
	}

	/** The count. */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm1511BS#save(java.lang.Integer,
	 * java.util.List)
	 */
	Integer count;
	
	/** The sum acumc. */
	Integer sumAcumc;
	
	/** The sum acumcs. */
	Integer sumAcumcs;
	
	/** The sum acucpd. */
	Integer sumAcucpd;
	
	/** The pm 1511. */
	Pm1511 pm1511;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm1511BS#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm1511> save(Integer index, List<Pm1511> listPm1511) {
		pm1511 = listPm1511.get(index);
		sumAcumc = 0;
		sumAcumcs = 0;
		sumAcucpd = 0;
		boolean bandera = findByTrimestre(pm1511.getSemestral(), pm1511.getIdsector());
		listPm1511.get(index).setbGuardar(Boolean.FALSE);
		if (bandera) {

			pm1511.setObsnccm(null == pm1511.getObsnccm() ? "" : pm1511.getObsnccm());
			pm1511.setObsnccms(null == pm1511.getObsnccms() ? "" : pm1511.getObsnccms());
			pm1511.setObstcpd(null == pm1511.getObstcpd() ? "" : pm1511.getObstcpd());
			pm1511Repository.save(pm1511);

			// pm1511Repository.save(pm1511);
			listPm1511 = orderByTrimestreAsc(pm1511.getIdsector());
			listPm1511.get(index).setbGuardar(Boolean.TRUE);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");

		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El semestre ya existe");
		}
		return listPm1511;
	}

	/** The pm operation 1. */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm1511BS#delete(java.lang.Integer,
	 * java.util.List)
	 */
	Pm1511 pmOperation1;
	
	/** The pm operation 2. */
	Pm1511 pmOperation2;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm1511BS#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm1511> delete(Integer index, List<Pm1511> listPm1511) {
		sumAcumcs = 0;
		sumAcumc = 0;
		sumAcucpd = 0;
		Integer nccms = 0;
		Integer nccm = 0;
		Integer tcpd = 0;
		pmOperation1 = new Pm1511();
		Integer idSector  = listPm1511.get(index).getIdsector();
		pm1511Repository.delete(listPm1511.get(index).getId());

		listPm1511 = pm1511Repository.findAllByIdsector(idSector, this.oderBy());
		if (CollectionUtils.isNotEmpty(listPm1511)) {
			for (int i = 0; i < listPm1511.size(); i++) {
				sumAcumcs = sumAcumcs + listPm1511.get(i).getAcunccms();
				sumAcumc = sumAcumc + listPm1511.get(i).getAcunccm();
				sumAcucpd = sumAcucpd + listPm1511.get(i).getAcutcpd();
				pmOperation1 = listPm1511.get(i);
				pmOperation1.setAcunccms(sumAcumcs);
				pmOperation1.setAcunccm(sumAcumc);
				pmOperation1.setAcutcpd(sumAcucpd);
				pm1511Repository.save(pmOperation1);
			}
		}
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		return orderByTrimestreAsc(idSector);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm1511BS#clean(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm1511> clean(Integer index, List<Pm1511> listPm1511) {
		listPm1511.get(index).setNccm(null);
		listPm1511.get(index).setObsnccm("");
		listPm1511.get(index).setNccms(null);
		listPm1511.get(index).setObsnccms("");
		listPm1511.get(index).setTcpd(null);
		listPm1511.get(index).setObstcpd("");
		return listPm1511;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm1511BS#cancel(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm1511> cancel(Integer index, List<Pm1511> listPm1511) {
		return orderByTrimestreAsc(listPm1511.get(index).getIdsector());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm1511BS#orderByTrimestreAsc(java.lang.
	 * Integer)
	 */
	@Override
	public List<Pm1511> orderByTrimestreAsc(Integer idSector) {
		List<Pm1511> listPm1511 = pm1511Repository.findAllByIdsector(idSector, this.oderBy());
		if (CollectionUtils.isEmpty(listPm1511)) {
			Pm1511 pm1511 = new Pm1511();
			pm1511.setSemestral(0);
			listPm1511.add(pm1511);
		}
		return listPm1511;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm1511BS#oderBy()
	 */
	@Override
	public Sort oderBy() {
		return new Sort(Sort.Direction.ASC, "semestral");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm1511BS#findByTrimestre(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public boolean findByTrimestre(Integer trimestre, Integer idSector) {
		return pm1511Repository.countBySemestre(idSector, trimestre) > 0 ? false : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm1511BS#validateTxt(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public boolean validateTxt(Pm1511 pm1511) {
		boolean bandera = Boolean.TRUE;
		if (null == pm1511.getNccms()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
					"El campo Número de casos confirmados por maltrato a los que se les dio seguimiento es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm1511.getNccm()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
					"El campo Numero de casos confirmados por maltrato es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm1511.getTcpd()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
					"El campo Numero de casos confirmados por maltrato es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm1511.getObsnccms()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
					"El campo Observacines de Número de casos confirmados por maltrato a los que se les dio seguimiento es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm1511.getObsnccm()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
					"El campo Observaciones de Numero de casos confirmados por maltrato es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm1511.getObstcpd()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
					"El campo Observaciones de Numero de casos confirmados por maltrato es obligatorio");
			bandera = Boolean.FALSE;
		}
		return bandera;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm1511BS#add()
	 */
	@Override
	public Pm1511 add() {
		Pm1511 pm1511 = new Pm1511();
		pm1511.setObsnccm("");
		pm1511.setObsnccms("");
		return pm1511;
	}

}
