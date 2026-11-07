package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.BalancepreBS;
import com.gem.sistema.business.domain.Balancepre;
import com.gem.sistema.business.repository.catalogs.BalancepreRepository;

@Repository
public class BalancepreBSImpl implements BalancepreBS {

	private static final Boolean FALSE = Boolean.FALSE;

	private static final Boolean TRUE = Boolean.TRUE;

	@Autowired
	private BalancepreRepository balancepreRepository;

	@Override
	public Balancepre save(Balancepre balancepre) {
		balancepre.setbGuardar(FALSE);

		if (null == balancepre.getTrimestre()) {
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error!", "El trimestre es requerido");
		} else if (null == balancepre.getConsecutivo()) {
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error!", "El Consecutivo es requerido");
		} else if (balancepre.getTrimestre() < 1 || balancepre.getTrimestre() > 4) {

			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error!",
					"Trimestre invalido, solo permite valores del 1 al 4");
		} else if (null == balancepre.getConcepto()) {
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error!", "El Concepto es requerido");
		} else if (this.existTrimAndConsecutivo(balancepre.getTrimestre(), balancepre.getIdSector(),
				balancepre.getConsecutivo()) == FALSE) {
			if (null == balancepre.getEa()) {
				balancepre.setEa(BigDecimal.ZERO);
			}
			if (null == balancepre.getDevengado()) {
				balancepre.setDevengado(BigDecimal.ZERO);
			}
			if (null == balancepre.getRp()) {
				balancepre.setRp(BigDecimal.ZERO);
			}

			balancepreRepository.save(balancepre);
			balancepre.setbGuardar(TRUE);

			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se guardo la información correctamente");
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error!", "El trimestre y el consecutivo ya existe");
		}

		return balancepre;
	}

	@Override
	public void delete(Balancepre balancepre) {
		balancepreRepository.delete(balancepre);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino correctamente el trimeste: "
				+ balancepre.getTrimestre() + " y el consecutivo: " + balancepre.getConsecutivo());
	}

	@Override
	public List<Balancepre> findAllByTrimestre(Integer idSector, Long idRef, Long idAnio, Integer trimestre) {
		return balancepreRepository.findAllByIdSectorAndIdAnioAndTrimestreOrderByConsecutivoAsc(idSector, idAnio,
				trimestre);
	}

	@Override
	public Balancepre modify(Balancepre balancepre, Integer oldTrimestre, Long oldConsecutivo) {
		balancepre.setbGuardar(Boolean.FALSE);
		try {
			if (oldTrimestre == balancepre.getTrimestre() && oldConsecutivo == balancepre.getConsecutivo()) {
				balancepreRepository.update(balancepre.getConsecutivo(), balancepre.getTrimestre(),
						balancepre.getConcepto(), balancepre.getEa(), balancepre.getDevengado(), balancepre.getRp(),
						balancepre.getCapturo(), balancepre.getFeccap(), balancepre.getId());

				balancepre.setbGuardar(Boolean.TRUE);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"Se modifico la información correctamente");
			} else {
				if (this.existTrimAndConsecutivo(balancepre.getTrimestre(), balancepre.getIdSector(),
						balancepre.getConsecutivo()) == FALSE) {
					generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error!",
							"El Trimestre y el Consecutivo ya existe");
				} else {
					balancepreRepository.update(balancepre.getConsecutivo(), balancepre.getTrimestre(),
							balancepre.getConcepto(), balancepre.getEa(), balancepre.getDevengado(), balancepre.getRp(),
							balancepre.getCapturo(), balancepre.getFeccap(), balancepre.getId());
					balancepre.setbGuardar(Boolean.TRUE);
					generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
							"Se modifico la información correctamente");
				}
			}

		} catch (Exception e) {
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Info!",
					"El trimestre que intenta modificar ya existe");
			System.out.println(e.getMessage());
		}

		return balancepre;
	}

	@Override
	public Boolean existTrimAndConsecutivo(Integer trimestre, Integer idSector, Long consecutivo) {
		return balancepreRepository.countByTrimestre(idSector, trimestre, consecutivo) == 0 ? false : true;
	}

}
