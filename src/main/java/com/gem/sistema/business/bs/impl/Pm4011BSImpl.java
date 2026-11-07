package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm4011BS;
import com.gem.sistema.business.domain.Pm4011;
import com.gem.sistema.business.domain.Pm4411;
import com.gem.sistema.business.predicates.Pm4011Predicate;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.Pm4011Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm4011BSImpl.
 */
@Repository
public class Pm4011BSImpl implements Pm4011BS {

	/** The Constant TRUE. */
	private static final Boolean TRUE = Boolean.TRUE;

	/** The Constant FALSE. */
	private static final Boolean FALSE = Boolean.FALSE;

	/** The Constant SORT. */
	private static final Sort SORT = new Sort(Direction.ASC, "clvreq");

	/** The pm 4011 repository. */
	@Autowired
	private Pm4011Repository pm4011Repository;

	/** The conctb repository. */
	@Autowired
	private ConctbRepository conctbRepository;

	/**
	 * Gets the pm 4011 repository.
	 *
	 * @return the pm 4011 repository
	 */
	public Pm4011Repository getPm4011Repository() {
		return pm4011Repository;
	}

	/**
	 * Sets the pm 4011 repository.
	 *
	 * @param pm4011Repository
	 *            the new pm 4011 repository
	 */
	public void setPm4011Repository(Pm4011Repository pm4011Repository) {
		this.pm4011Repository = pm4011Repository;
	}

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository
	 *            the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm4011BS#save(com.gem.sistema.business.domain
	 * .Pm4011)
	 */
	@Override
	public List<Pm4011> save(Pm4011 pm4011) {
		List<Pm4011> lis = new ArrayList<Pm4011>();
		pm4011.setbGuardar(FALSE);
		lis.add(pm4011);
		if (this.validateTxt(pm4011)) {
			if (this.findByClave(pm4011.getClvreq(), pm4011.getIdsector())) {
				pm4011 = this.notEmpty(pm4011);

				pm4011Repository.save(pm4011);
				lis = this.orderByClaveAsc(pm4011.getIdsector());
				pm4011.setbGuardar(TRUE);
				lis.add(pm4011.getIndex(), pm4011);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Los datos se guardaron correctamente.");
			} else {
				lis = this.orderByClaveAsc(pm4011.getIdsector());
				pm4011.setbGuardar(FALSE);
				lis.add(pm4011.getIndex(), pm4011);
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "La clave ya existe.");
			}
		}

		return lis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm4011BS#delete(com.gem.sistema.business.
	 * domain.Pm4011)
	 */
	@Override
	public List<Pm4011> delete(Pm4011 pm4011) {
		this.getPm4011Repository().delete(pm4011);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El registro se Elimino correctamente.");
		return this.orderByClaveAsc(pm4011.getIdsector());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm4011BS#clean(com.gem.sistema.business.
	 * domain.Pm4011)
	 */
	@Override
	public List<Pm4011> clean(Pm4011 pm4011) {

		return this.orderByClaveAsc(pm4011.getIdsector());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm4011BS#cancel(com.gem.sistema.business.
	 * domain.Pm4011)
	 */
	@Override
	public List<Pm4011> cancel(Pm4011 pm4011) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm4011BS#orderByClaveAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm4011> orderByClaveAsc(Integer idSector) {
		return this.getPm4011Repository().findAllByIdsector(idSector, SORT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm4011BS#oderBy()
	 */
	@Override
	public Sort oderBy() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm4011BS#findByClave(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public boolean findByClave(Integer clave, Integer idSector) {
		return null == pm4011Repository.findOne(Pm4011Predicate.findByClaveAndIdSector(clave, idSector)) ? Boolean.TRUE
				: Boolean.FALSE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm4011BS#validateTxt(com.gem.sistema.business
	 * .domain.Pm4011)
	 */
	@Override
	public boolean validateTxt(Pm4011 pm4011) {
		if (null == pm4011.getClvreq()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El campo clave es obligatorio");
			return FALSE;
		} else if (0 == pm4011.getClvreq()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "La clave debe de ser mayor a Cero");
			return FALSE;
		} else
			return TRUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm4011BS#add()
	 */
	@Override
	public Pm4011 add() {
		return new Pm4011();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm4011BS#loadData(java.lang.Integer)
	 */
	@Override
	public Pm4011 loadData(Integer idSector) {
		Pm4011 pm4011 = new Pm4011();
		pm4011.setAnual(this.getConctbRepository().findByIdsector(idSector).getAnoemp());
		return pm4011;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm4011BS#update(com.gem.sistema.business.
	 * domain.Pm4011)
	 */
	@Override
	public List<Pm4011> update(Pm4011 pm4011) {
		pm4011 = this.notEmpty(pm4011);
		
		this.getPm4011Repository().update(pm4011.getObs(),pm4011.getClvreq(),pm4011.getCorreo(),pm4011.getCumple(),pm4011.getEvidencia(),pm4011.getRequer(),pm4011.getId());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Los registros se editaron correctamente");
		return this.orderByClaveAsc(pm4011.getIdsector());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm4011BS#notEmpty(com.gem.sistema.business.
	 * domain.Pm4011)
	 */
	@Override
	public Pm4011 notEmpty(Pm4011 pm4011) {
		if (null == pm4011.getRequer()) {
			pm4011.setRequer("");
		}

		if (null == pm4011.getEvidencia()) {
			pm4011.setEvidencia("");
		}

		if (null == pm4011.getObs()) {
			pm4011.setObs("");
		}

		if (null == pm4011.getCorreo()) {
			pm4011.setCorreo("");
		}
		return pm4011;
	}

}
