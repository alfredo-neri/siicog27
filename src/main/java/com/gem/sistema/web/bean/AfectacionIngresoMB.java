package com.gem.sistema.web.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.gem.sistema.business.domain.TcAfectacionIngreso;
import com.gem.sistema.business.predicates.PolienPredicates;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.TcAfectacionIngresoRepository;
import com.gem.sistema.business.service.catalogos.TwIngresoPropioDetalleService;

@ManagedBean(name = "afectacionIngresoMB")
@ViewScoped
public class AfectacionIngresoMB extends AbstractMB {
	private List<TcAfectacionIngreso> afectacionList = new ArrayList<TcAfectacionIngreso>();
	private TcAfectacionIngreso selected;

	@ManagedProperty("#{twIngresoPropioDetalleService}")
	private TwIngresoPropioDetalleService twIngresoPropioDetalleService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty(value = "#{polienRepository}")
	private PolienRepository polienRepository;

	@ManagedProperty(value = "#{tcAfectacionIngresoRepository}")
	private TcAfectacionIngresoRepository afectacionRepository;

	@PostConstruct
	public void init() {
		afectacionList = afectacionRepository.findAll();
	}

	private Boolean mesContalbeAfectado(Integer mes) {
		return this.getPolienRepository()
				.count(PolienPredicates.byMesAfectado(mes, this.getUserDetails().getIdSector(), "A")) > 0;
	}

	public void afectarMes() {
		if (selected != null) {
			if (selected.getStatus().equals("A")) {
				selected.setStatus("N");
				selected.setFecha(null);
				this.procesar(this.afectacionRepository.save(selected));
				displayInfoMessage("El mes:" + selected.getMes() + " se ha desafectado correctamente");
			} else {
				if (mesContalbeAfectado(selected.getMes())) {
					if (selected.getMes() == 1
							|| this.afectacionRepository.getByMes(selected.getMes() - 1).getStatus().equals("A")) {

						Double totalCuenta = this.twIngresoPropioDetalleService.getTotalCuentaByMes(selected.getMes(),
								this.getUserDetails().getIdSector());
						Double totalIngreso = this.twIngresoPropioDetalleService
								.getTotalIngresoByMes(selected.getMes());
						if (totalIngreso != null) {
							if (totalCuenta != null) {
								if (totalCuenta - totalIngreso == 0) {
									selected.setStatus("A");
									selected.setFecha(new Date());
									this.procesar(this.afectacionRepository.save(selected));
									displayInfoMessage("El mes:" + selected.getMes() + " se ha afectado correctamente");
								} else
									displayInfoMessage(
											"El total de ingreso no coincide con el total de la cuenta : Total de ingreso: "
													+ totalIngreso + ", Total cuenta: " + totalCuenta);
							} else
								displayInfoMessage("No se encontro cuenta de ingresos");
						} else
							displayInfoMessage("No se encontraron polizas de ingresos detalladas para el mes:"
									+ selected.getMes());

					} else
						displayInfoMessage("El mes anterior aun no a sido afectado");
				} else
					displayInfoMessage("El mes contable aun no ha sido afectado");
			}
		} else
			displayInfoMessage("Aun no se selecciona ");

	}

	private void procesar(TcAfectacionIngreso entity) {
		this.afectacionList = this.afectacionList.stream().map(o -> {
			if (o.getId() == entity.getId()) {
				o = entity;
			}
			return o;
		}).collect(Collectors.toList());
	}

	public List<TcAfectacionIngreso> getAfectacionList() {
		return afectacionList;
	}

	public void setAfectacionList(List<TcAfectacionIngreso> afectacionList) {
		this.afectacionList = afectacionList;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public TcAfectacionIngresoRepository getAfectacionRepository() {
		return afectacionRepository;
	}

	public void setAfectacionRepository(TcAfectacionIngresoRepository afectacionRepository) {
		this.afectacionRepository = afectacionRepository;
	}

	public TwIngresoPropioDetalleService getTwIngresoPropioDetalleService() {
		return twIngresoPropioDetalleService;
	}

	public void setTwIngresoPropioDetalleService(TwIngresoPropioDetalleService twIngresoPropioDetalleService) {
		this.twIngresoPropioDetalleService = twIngresoPropioDetalleService;
	}

	public PolienRepository getPolienRepository() {
		return polienRepository;
	}

	public void setPolienRepository(PolienRepository polienRepository) {
		this.polienRepository = polienRepository;
	}

	public TcAfectacionIngreso getSelected() {
		return selected;
	}

	public void setSelected(TcAfectacionIngreso selected) {
		this.selected = selected;
	}

}
