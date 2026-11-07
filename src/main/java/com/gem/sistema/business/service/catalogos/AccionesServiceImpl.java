package com.gem.sistema.business.service.catalogos;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.TcAccion;
import com.gem.sistema.business.domain.TcArea;
import com.gem.sistema.business.domain.TrAreaAccion;
import com.gem.sistema.business.domain.TrPresupuestoArea;
import com.gem.sistema.business.repository.catalogs.TcAccionRepository;
import com.gem.sistema.business.repository.catalogs.TcAreaRepository;
import com.gem.sistema.business.repository.catalogs.TrAreaAccionRepository;
import com.gem.sistema.business.repository.catalogs.TrPresupuestoAreaRepository;

@Service(value = "accionesService")
public class AccionesServiceImpl implements AccionesService {

	@Autowired
	private TcAccionRepository accionRepository;

	@Autowired
	private TcAreaRepository areaRepository;

	@Autowired
	private TrAreaAccionRepository areaAccionRepository;

	@Autowired
	private TrPresupuestoAreaRepository presupuestoAreaRepository;

	@Override
	public List<TcArea> listadoDirecciones() {
		return areaRepository.findAllByExcept0OrderByClave();
	}

	@Override
	public List<TcAccion> listadoAcciones() {
		return (List<TcAccion>) accionRepository.findAll();
	}

	@Override
	public List<TcAccion> searchAcciones(String criterio) {
		return accionRepository.findByClaveContainingOrNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(
				criterio, criterio, criterio);
	}

	@Override
	public TcAccion saveAccion(TcAccion accion) {

		TcAccion savedAction = accionRepository.save(accion);

		if (null == presupuestoAreaRepository.findByArea(savedAction.getDireccion())) {
			TrPresupuestoArea presupuestoArea = new TrPresupuestoArea();
			presupuestoArea.setArea(accion.getDireccion());
			presupuestoArea.setPresupuestoAutorizado(BigDecimal.ZERO);

			presupuestoArea = presupuestoAreaRepository.save(presupuestoArea);

			TrAreaAccion areaAccion = new TrAreaAccion();
			areaAccion.setAccion(savedAction);
			areaAccion.setPresupuestoArea(presupuestoArea);
			areaAccion.setAutorizadoInicial(BigDecimal.ZERO);

			areaAccionRepository.save(areaAccion);
		}
		return savedAction;
	}

	@Override
	public Boolean deleteAccion(TcAccion accion) {

		TrAreaAccion areaAccion = areaAccionRepository.findByAccion(accion);

		if (null == areaAccion) {
			accionRepository.delete(accion);
			return Boolean.TRUE;
		}else if( CollectionUtils.isEmpty(areaAccion.getListAccionesContrato())) {

			List<TrAreaAccion> areaAccions = areaAccionRepository
					.findByPresupuestoArea(areaAccion.getPresupuestoArea());
			if (areaAccions.size() <= 1) {
				areaAccionRepository.delete(areaAccion);
				presupuestoAreaRepository.delete(areaAccion.getPresupuestoArea());
				accionRepository.delete(accion);
			}else {
				areaAccionRepository.delete(areaAccion);
				accionRepository.delete(accion);
			}
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public TcAccionRepository getAccionRepository() {
		return accionRepository;
	}

	public void setAccionRepository(TcAccionRepository accionRepository) {
		this.accionRepository = accionRepository;
	}

	public TcAreaRepository getAreaRepository() {
		return areaRepository;
	}

	public void setAreaRepository(TcAreaRepository areaRepository) {
		this.areaRepository = areaRepository;
	}

	public TrAreaAccionRepository getAreaAccionRepository() {
		return areaAccionRepository;
	}

	public void setAreaAccionRepository(TrAreaAccionRepository areaAccionRepository) {
		this.areaAccionRepository = areaAccionRepository;
	}

	public TrPresupuestoAreaRepository getPresupuestoAreaRepository() {
		return presupuestoAreaRepository;
	}

	public void setPresupuestoAreaRepository(TrPresupuestoAreaRepository presupuestoAreaRepository) {
		this.presupuestoAreaRepository = presupuestoAreaRepository;
	}

}
