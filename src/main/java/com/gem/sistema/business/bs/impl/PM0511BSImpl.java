package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.PM0511BS;
import com.gem.sistema.business.domain.Pm0511;
import com.gem.sistema.business.predicates.Pm5011Predicate;
import com.gem.sistema.business.repository.catalogs.Pm0511Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class PM0511BSImpl.
 *
 * @author Mateo
 */
@Repository
public class PM0511BSImpl implements PM0511BS {

	/** The Constant FALSE. */
	private static final Boolean FALSE = Boolean.FALSE;

	/** The Constant TRUE. */
	private static final Boolean TRUE = Boolean.TRUE;

	/** The pm 0511 repository. */
	@Autowired
	private Pm0511Repository pm0511Repository;

	/**
	 * Gets the pm 0511 repository.
	 *
	 * @return the pm 0511 repository
	 */
	public Pm0511Repository getPm0511Repository() {
		return pm0511Repository;
	}

	/**
	 * Sets the pm 0511 repository.
	 *
	 * @param pm0511Repository
	 *            the new pm 0511 repository
	 */
	public void setPm0511Repository(Pm0511Repository pm0511Repository) {
		this.pm0511Repository = pm0511Repository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.PM0511BS#add()
	 */
	@Override
	public Pm0511 add() {
		Pm0511 pm0511 = new Pm0511();
		pm0511.setObservaciones("");
		return pm0511;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.PM0511BS#save(com.gem.sistema.business.domain
	 * .Pm0511)
	 */
	@Override
	public List<Pm0511> save(Pm0511 pm0511) {
		pm0511.setbGuardar(FALSE);
		List<Pm0511> lis = new ArrayList<Pm0511>();
		lis = this.orderByAsc(pm0511.getIdsector());
		lis.add(pm0511);

		if (null == pm0511.getTrimestre()) {
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error!", "El trimestre es requerido");
		} else if (null == pm0511.getConse()) {
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error!", "El Consecutivo es requerido");
		} else if (pm0511.getTrimestre() < 1 || pm0511.getTrimestre() > 4) {
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error!",
					"Trimestre invalido, solo permite valores del 1 al 4");
		} else if (this.existTrimonth(pm0511.getTrimestre(), pm0511.getIdsector(), pm0511.getConse()) == FALSE) {
			if (null == pm0511.getEmergencia()) {
				pm0511.setEmergencia("");
			}
			if (null == pm0511.getObservaciones()) {
				pm0511.setObservaciones("");
			}
			if (null == pm0511.getTiempo()) {
				pm0511.setTiempo(0);
			}
			if (null == pm0511.getConta()) {
				pm0511.setConta(0);
			}
			pm0511Repository.save(pm0511);
			pm0511.setbGuardar(TRUE);
			lis = this.orderByAsc(pm0511.getIdsector());
			lis.get(pm0511.getIndex()).setbGuardar(pm0511.isbGuardar());

			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error!", "El trimestre y el consecutivo ya existe");
		}

		return lis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.PM0511BS#modify(com.gem.sistema.business.
	 * domain.Pm0511)
	 */
	@Override
	public Pm0511 modify(Pm0511 pm0511, Integer oldValue) {
		
		pm0511.setbGuardar(Boolean.FALSE);
		try {
			if (oldValue == pm0511.getTrimestre()) {
				pm0511Repository.update(pm0511.getConse(), pm0511.getTrimestre(), pm0511.getEmergencia(),
						pm0511.getTiempo(), pm0511.getObservaciones(), pm0511.getId());
				
				pm0511.setbGuardar(Boolean.TRUE);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se modifico la información correctamente");
			} else {
				if (this.existTrimonth(pm0511.getTrimestre(), pm0511.getIdsector(), pm0511.getConse()) == FALSE) {
					generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error!",
							"El trimestre y el consecutivo ya existe");
				} else {
					pm0511Repository.update(pm0511.getConse(), pm0511.getTrimestre(), pm0511.getEmergencia(),
							pm0511.getTiempo(), pm0511.getObservaciones(), pm0511.getId());
					pm0511.setbGuardar(Boolean.TRUE);
					generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se modifico la información correctamente");
				}
			}
			
		} catch (Exception e) {
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Info!",
					"El trimestre que intenta modificar ya existe");
		}

		return pm0511;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.PM0511BS#delete(com.gem.sistema.business.
	 * domain.Pm0511)
	 */
	@Override
	public List<Pm0511> delete(Pm0511 pm0511) {
		pm0511Repository.delete(pm0511);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
				"Se Elimino correctamente el trimestre: " + pm0511.getTrimestre());
		return this.orderByAsc(pm0511.getIdsector());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.PM0511BS#orderByAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm0511> orderByAsc(Integer idSector) {
		return pm0511Repository.findByIdsector(idSector);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.PM0511BS#getSort()
	 */
	@Override
	public Sort getSort() {
		return new Sort(Direction.ASC, "trimestre", "conse");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.PM0511BS#validateTxt(com.gem.sistema.business
	 * .domain.Pm0511)
	 */
	@Override
	public Boolean validateTxt(Pm0511 pm0511) {
		return CollectionUtils.isEmpty((Collection<?>) this.pm0511Repository.findAll(Pm5011Predicate
				.validateTrimonthAndCosec(pm0511.getTrimestre(), pm0511.getConse(), pm0511.getIdsector())));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.PM0511BS#existTrimonth(java.lang.Integer)
	 */
	@Override
	public Boolean existTrimonth(Integer trimestre, Integer idSector, String conse) {
		return pm0511Repository.countByTrimestre(idSector, trimestre, conse) > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.PM0511BS#getTriMonth()
	 */
	@Override
	public List<String> getTriMonth() {
		List<String> listTri = new ArrayList<String>();
		for (int i = 0; i <= 4; i++) {
			listTri.add(" 0" + i);
		}
		return listTri;
	}

}
