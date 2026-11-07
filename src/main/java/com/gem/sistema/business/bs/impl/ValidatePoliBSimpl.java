package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.ValidatePoliBS;
import com.gem.sistema.business.predicates.PolienPredicates;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;

@Repository
public class ValidatePoliBSimpl implements ValidatePoliBS {

	@Autowired
	private PolienRepository polienRepository;

	@Autowired
	private ConctbRepository conctbRepository;

	public PolienRepository getPolienRepository() {
		return polienRepository;
	}

	public void setPolienRepository(PolienRepository polienRepository) {
		this.polienRepository = polienRepository;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	@Override
	public Boolean isOpenMonth(Integer mes, Integer anio, Integer idSector) {
		Integer monthOpen = conctbRepository.findByIdsector(idSector).getMesemp();

		if (mes > monthOpen) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Mes " + mes + " incorrecto");
			return Boolean.FALSE;
		}
		return Boolean.TRUE;

	}

	@Override
	public Boolean isAfectMonth(Integer mes, Integer anio, Integer idSector) {
		if (0 == polienRepository.count(PolienPredicates.isAfect(mes, "A", idSector))) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El mes " + mes + " no esta afectado");
			return Boolean.FALSE;
		}
		return Boolean.TRUE;

	}

	@Override
	public Boolean existPolices(Integer mes, Integer anio, Integer idSector) {
		if (0 == polienRepository.count(PolienPredicates.findByidSectorAndMonth(idSector, mes))) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El mes " + mes + " no tiene polizas");
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

}
