package com.gem.sistema.web.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.gem.sistema.business.domain.TcAreaAdministrativa;
import com.gem.sistema.business.domain.TcPersonalAdministrativo;
import com.gem.sistema.business.repository.catalogs.TcAdquisicionRepository;
import com.gem.sistema.business.repository.catalogs.TcAreaAdministrativaRepository;
import com.gem.sistema.business.repository.catalogs.TcPersonalAdministrativoRepository;
import com.gem.sistema.util.Regex;

@ManagedBean(name = "personalAreasAdministrativasMB")
@ViewScoped
public class PersonalAreasAdministrativasMB extends AbstractMB {

	private TcAreaAdministrativa areaSelected = new TcAreaAdministrativa();
	private TcPersonalAdministrativo administrativoSelected;

	/** Array principal de areas */
	private List<TcAreaAdministrativa> areas = new ArrayList<>();
	/** Array principal de personal */
	private List<TcPersonalAdministrativo> personal = new ArrayList<>();
	/** variable de bsuqea de areas */
	private String busquedaAreas = "";
	/** variable de bsuqea de personal */
	private String busquedaPersonal = "";

	@ManagedProperty("#{tcAreaAdministrativaRepository}")
	private TcAreaAdministrativaRepository administrativaRepository;

	@ManagedProperty("#{tcPersonalAdministrativoRepository}")
	private TcPersonalAdministrativoRepository personalAdministrativoRepository;

	@ManagedProperty("#{tcAdquisicionRepository}")
	private TcAdquisicionRepository adquisicionRepository;

	@PostConstruct
	public void init() {
		areaSelected = new TcAreaAdministrativa();
		administrativoSelected = new TcPersonalAdministrativo();
		buscarArea();
		buscarAdministrativo();
	}

	/** Guarda un area valida que el nombre no exista */
	public void guardarArea() {
		List<String> errors = areaSelected.isValid();
		Boolean nombreRepetido = false;
		if (errors.isEmpty()) {
			for (TcAreaAdministrativa area : areas) {
				if (area.getId() != areaSelected.getId()) {
					if (Regex.equalStringName(area.getDescripcion(), areaSelected.getDescripcion())) {
						nombreRepetido = true;
						break;
					}
				}
			}
			if (!nombreRepetido) {
				TcAreaAdministrativa areaByclave = administrativaRepository.findFirstByClave(areaSelected.getClave());
				if (areaByclave == null || (areaByclave.getId().equals(areaSelected.getId()))) {
					try {
						boolean modificado = areaSelected.getId() != null && areaSelected.getId() > 0;
						areaSelected = administrativaRepository.save(areaSelected);
						if (modificado) {
							areas = areas.stream().map(p -> p.getId().equals(areaSelected.getId()) ? areaSelected : p)
									.collect(Collectors.toList());
						} else {
							areas.add(areaSelected);
						}
						displayInfoMessage("Se ha " + (!modificado ? "agregado" : "modificado ")
								+ " correctamente el area administrativa");
						areaSelected = new TcAreaAdministrativa();
					} catch (Exception e) {
						e.printStackTrace();
						displayInfoMessage("Ha ocurrrido un error al guardar el area");
					}
				} else {
					displayInfoMessage("La clave del area ya ha sido registrada");
				}
			} else {
				displayInfoMessage("El nombre del area ya ha sido registrada");
			}
		} else {
			errors.forEach(this::displayInfoMessage);
		}
		// Evita que la lista se actualize por referencia con el area seleccioonada
		buscarArea();
	}

	/** Elimina un area valida que no sea usadaen algun personal */
	public void deleteArea() {
		if (areaSelected != null) {
			try {
				if (personalAdministrativoRepository.countByAdministrativa(areaSelected) == 0) {
					administrativaRepository.delete(areaSelected.getId());
					areas = areas.stream().filter(p -> p.getId() != areaSelected.getId()).collect(Collectors.toList());
					displayInfoMessage("El area:" + areaSelected.getDescripcion() + " se ha eliminado correctamente");
				} else {
					displayInfoMessage("El area:" + areaSelected.getDescripcion()
							+ " ha sido asignada a uno o mas personas no es posible eliminarla");
				}
			} catch (Exception e) {
				e.printStackTrace();
				displayInfoMessage("Ha ocurrido un error eliminando el area");
			}
		}
	}

	/** Cancela la edidcion de un area y instanacia una nueva area vacia */
	public void resetareaSelected() {
		if (areaSelected.getId() != null) {
			displayInfoMessage("Se cancelo la edicion");
		}
		areaSelected = new TcAreaAdministrativa();
	}

	/**
	 * Optiene todas las areas y con el metdo de busqedasimple de la entiadad filtra
	 * las concidencias
	 */
	public void buscarArea() {
		if (busquedaAreas == null || busquedaAreas.equals("")) {
			areas = administrativaRepository.findAll();
		} else {
			areas = administrativaRepository.findAll().stream()
					.filter(p -> p.busquedaSimple().toLowerCase().contains(busquedaAreas.toLowerCase()))
					.collect(Collectors.toList());
		}
	}

	/** Guarda un personal, valida que el nombre no se repita */
	public void guardarAdministrativo() {
		System.out.println("veces");
		List<String> errors = administrativoSelected.isValid();
		Boolean nombreRepetido = false;
		// Elimina puntos, comas, espacion y valida si ya esiste el nombre del
		// area
		try {
			if (errors.isEmpty()) {
				for (TcPersonalAdministrativo persona : personal) {
					if (persona.getId() != administrativoSelected.getId()) {
						if (Regex.equalStringName(persona.getNombre(), administrativoSelected.getNombre())) {
							nombreRepetido = true;
							break;
						}
					}
				}
				if (!nombreRepetido) {
					TcPersonalAdministrativo personaByEmail = personalAdministrativoRepository
							.findFirstByEmail(administrativoSelected.getEmail());
					if (personaByEmail == null || personaByEmail.getId().equals(administrativoSelected.getId())) {
						TcPersonalAdministrativo personaByTelefono = personalAdministrativoRepository
								.findFirstByTelefono(administrativoSelected.getTelefono());
						if (personaByTelefono == null
								|| personaByTelefono.getId().equals(administrativoSelected.getId())) {
							boolean modificado = administrativoSelected.getId() != null
									&& administrativoSelected.getId() > 0;
							administrativoSelected = personalAdministrativoRepository.save(administrativoSelected);
							if (modificado) {
								personal = personal.stream()
										.map(p -> p.getId().equals(administrativoSelected.getId())
												? administrativoSelected
												: p)
										.collect(Collectors.toList());
							} else {
								personal.add(administrativoSelected);
							}
							displayInfoMessage("Se ha " + (!modificado ? "agregado" : "modificado ")
									+ " correctamente el personal");
							administrativoSelected = new TcPersonalAdministrativo();
						} else {
							displayInfoMessage("El telefono ya ha sido registrado");
						}
					} else {
						displayInfoMessage("El email ya ha sido registrado");
					}
				} else {
					displayInfoMessage("El nombre del personal ya ha sido registrado");
				}
			} else {
				errors.forEach(this::displayInfoMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
			displayInfoMessage("Ha ocurrrido un error al guardar el personal");
		}
		// Evita que la lista se actualize por referencia con el area seleccioonada
		buscarAdministrativo();
	}

	/** Elimina un Administrativo */
	public void deleteAdministrativo() {
		if (administrativoSelected != null) {
			try {
				if (adquisicionRepository.countByAdministrativo(administrativoSelected) == 0) {

					personalAdministrativoRepository.delete(administrativoSelected.getId());
					personal = personal.stream().filter(p -> p.getId() != administrativoSelected.getId())
							.collect(Collectors.toList());
					displayInfoMessage("El personal se ha eliminado correctamente");
				} else {
					displayInfoMessage("El personal ha sido asignado a una o mas solicitudes, no es posible eliminar");
				}
			} catch (Exception e) {
				e.printStackTrace();
				displayInfoMessage("Ha ocurrido un error eliminando el personal");
			}
		}
	}

	/** Cancela la edicion y reasinga el administrativo */
	public void resetAdministrativoSelected() {
		if (administrativoSelected.getId() != null) {
			displayInfoMessage("Se cancelo la edicion");
		}
		administrativoSelected = new TcPersonalAdministrativo();
	}

	/**
	 * Optiene todas las areas y con el metdo de busqedasimple de la entiadad filtra
	 * las concidencias
	 */
	public void buscarAdministrativo() {
		if (busquedaPersonal == null || busquedaPersonal.equals("")) {
			personal = personalAdministrativoRepository.getAllWithArea();
		} else {
			personal = personalAdministrativoRepository.getAllWithArea().stream()
					.filter(p -> p.busquedaSimple().toLowerCase().contains(busquedaPersonal.toLowerCase()))
					.collect(Collectors.toList());
		}
	}

	public TcAreaAdministrativa getAreaSelected() {
		return areaSelected;
	}

	public void setAreaSelected(TcAreaAdministrativa areaSelected) {
		this.areaSelected = areaSelected;
	}

	public TcPersonalAdministrativo getAdministrativoSelected() {
		return administrativoSelected;
	}

	public void setAdministrativoSelected(TcPersonalAdministrativo administrativoSelected) {
		this.administrativoSelected = administrativoSelected;
	}

	public List<TcAreaAdministrativa> getAreas() {
		return areas;
	}

	public void setAreas(List<TcAreaAdministrativa> areas) {
		this.areas = areas;
	}

	public List<TcPersonalAdministrativo> getPersonal() {
		return personal;
	}

	public void setPersonal(List<TcPersonalAdministrativo> personal) {
		this.personal = personal;
	}

	public String getBusquedaAreas() {
		return busquedaAreas;
	}

	public void setBusquedaAreas(String busquedaAreas) {
		this.busquedaAreas = busquedaAreas;
	}

	public String getBusquedaPersonal() {
		return busquedaPersonal;
	}

	public void setBusquedaPersonal(String busquedaPersonal) {
		this.busquedaPersonal = busquedaPersonal;
	}

	public TcAreaAdministrativaRepository getAdministrativaRepository() {
		return administrativaRepository;
	}

	public void setAdministrativaRepository(TcAreaAdministrativaRepository administrativaRepository) {
		this.administrativaRepository = administrativaRepository;
	}

	public TcPersonalAdministrativoRepository getPersonalAdministrativoRepository() {
		return personalAdministrativoRepository;
	}

	public void setPersonalAdministrativoRepository(
			TcPersonalAdministrativoRepository personalAdministrativoRepository) {
		this.personalAdministrativoRepository = personalAdministrativoRepository;
	}

	public TcAdquisicionRepository getAdquisicionRepository() {
		return adquisicionRepository;
	}

	public void setAdquisicionRepository(TcAdquisicionRepository adquisicionRepository) {
		this.adquisicionRepository = adquisicionRepository;
	}

}
