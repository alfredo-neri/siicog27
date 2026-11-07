package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm2911BS;
import com.gem.sistema.business.domain.Pm2911;
import com.gem.sistema.business.domain.Pm5011;
import com.gem.sistema.business.repository.catalogs.Pm2911Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm2911BSImpl.
 *
 * @author Mateo
 */
@Repository(value = "pm2911BS")
public class Pm2911BSImpl implements Pm2911BS {

	/** The pm 2911 repository. */
	@Autowired
	private Pm2911Repository pm2911Repository;

	/**
	 * Gets the pm 2911 repository.
	 *
	 * @return the pm 2911 repository
	 */
	public Pm2911Repository getPm2911Repository() {
		return pm2911Repository;
	}

	/**
	 * Sets the pm 2911 repository.
	 *
	 * @param pm2911Repository the new pm 2911 repository
	 */
	public void setPm2911Repository(Pm2911Repository pm2911Repository) {
		this.pm2911Repository = pm2911Repository;
	}

	/** The suma 1. */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm2911BS#save(java.lang.Integer,
	 * java.util.List)
	 */
	Double suma1 = 0.00;
	
	/** The suma 2. */
	Double suma2 = 0.00;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2911BS#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2911> save(Integer index, List<Pm2911> listPm2911) {
		Pm2911 pm2911 = listPm2911.get(index);

		boolean bandera;
		bandera = findByTrimestre(pm2911.getSemes(), pm2911.getIdsector());
		listPm2911.get(index).setbGuardar(Boolean.FALSE);
		if (bandera) {
			bandera = this.validateTxt(pm2911);
			if (bandera) {
				pm2911.setAcumgas(BigDecimal.ZERO);
				pm2911.setAcumtot(BigDecimal.ZERO);
				pm2911Repository.save(pm2911);

				listPm2911 = orderByTrimestreAsc(pm2911.getIdsector());
				listPm2911.get(index).setbGuardar(Boolean.TRUE);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"Se guardo la información correctamente");
			}
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "El semestre ya existe");
		}
		return listPm2911;
	}

	/** The res 1. */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm2911BS#delete(java.lang.Integer,
	 * java.util.List)
	 */
	Double res1;
	
	/** The res 2. */
	Double res2;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2911BS#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2911> delete(Integer index, List<Pm2911> listPm2911) {
		pm2911Repository.delete(listPm2911.get(index).getId());
		List<Pm2911> lis = pm2911Repository.findAllByIdsectorOrderBySemesAsc(listPm2911.get(index).getIdsector());
		BigDecimal sumaAcumuladoTot = new BigDecimal(0);
		BigDecimal sumaAcumuladoGas = new BigDecimal(0);
		for (Pm2911 sum : lis) {
			sumaAcumuladoTot = sumaAcumuladoTot.add(sum.getTotton());
			sum.setAcumtot(sumaAcumuladoTot);
			sumaAcumuladoGas = sumaAcumuladoGas.add(sum.getGastot());
			sum.setAcumgas(sumaAcumuladoGas);
			pm2911Repository.save(sum);
		}
		listPm2911 = orderByTrimestreAsc(listPm2911.get(index).getIdsector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino la información correctamente");
		return listPm2911;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm2911BS#clean(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm2911> clean(Integer index, List<Pm2911> listPm2911) {
		listPm2911.set(index, this.noEmpty(new Pm2911()));
		return listPm2911;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm2911BS#cancel(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public List<Pm2911> cancel(Integer index, List<Pm2911> listPm2911) {
		return orderByTrimestreAsc(listPm2911.get(index).getIdsector());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm2911BS#orderByTrimestreAsc(java.lang.
	 * Integer)
	 */
	@Override
	public List<Pm2911> orderByTrimestreAsc(Integer idSector) {
		List<Pm2911> listPm2911 = pm2911Repository.findAllByIdsector(idSector, this.oderBy());
		if (CollectionUtils.isEmpty(listPm2911)) {
			Pm2911 pm2911 = new Pm2911();
			pm2911.setAcumgas(BigDecimal.ZERO);
			pm2911.setAcumtot(BigDecimal.ZERO);
			pm2911.setObsgas("");
			pm2911.setObstot("");
			pm2911.setSemes(0);
			listPm2911.add(pm2911);
		}
		return listPm2911;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm2911BS#oderBy()
	 */
	@Override
	public Sort oderBy() {
		return new Sort(Sort.Direction.ASC, "semes");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.bs.Pm2911BS#findByTrimestre(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public boolean findByTrimestre(Integer semestre, Integer idSector) {
		return pm2911Repository.countBySemestre(idSector, semestre) > 0 ? false : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.bs.Pm2911BS#validateTxt(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	public boolean validateTxt(Pm2911 pm2911) {
		boolean bandera = Boolean.TRUE;
		if (null == pm2911.getGastot()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo Gasto Total de dispocición final de residuos solidos es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm2911.getTotton()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo Total de toneladas dispuestas de residuos solidos es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm2911.getObsgas()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo Observaciones Gasto Total de dispocición final de residuos solidos es obligatorio");
			bandera = Boolean.FALSE;
		} else if (null == pm2911.getObstot()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"El campo Observacioneste Total de toneladas dispuestas de residuos solidos es obligatorio");
			bandera = Boolean.FALSE;
		}
		return bandera;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.bs.Pm2911BS#add()
	 */
	@Override
	public Pm2911 add() {
		Pm2911 pm2911 = new Pm2911();
		pm2911.setObsgas("");
		pm2911.setObstot("");
		return pm2911;
	}

	
	/**
	 * No empty.
	 *
	 * @param entity the entity
	 * @return the pm 2911
	 */
	public Pm2911 noEmpty(Pm2911 entity) {
		entity.setTotton(entity.getTotton() == null ? BigDecimal.ZERO : entity.getTotton());
		entity.setGastot(entity.getGastot() == null ? BigDecimal.ZERO : entity.getGastot());
		entity.setObstot(entity.getObstot() == null ? "" : entity.getObstot());
		entity.setObsgas(entity.getObsgas() == null ? "" : entity.getObsgas());

		return entity;
	}
}
