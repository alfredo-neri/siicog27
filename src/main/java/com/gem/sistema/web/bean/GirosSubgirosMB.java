package com.gem.sistema.web.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.gem.sistema.business.domain.Natgas;
import com.gem.sistema.business.domain.TcSubgiro;
import com.gem.sistema.business.repository.catalogs.NatgasRepository;
import com.gem.sistema.business.repository.catalogs.TcAdquisicionGiroSubgiroRepository;
import com.gem.sistema.business.repository.catalogs.TcSubgiroRepository;

@ManagedBean(name = "girosSubgirosMB")
@ViewScoped
public class GirosSubgirosMB extends AbstractMB {

	private TcSubgiro subgiroSelected = new TcSubgiro();

	/** Array principal de subgiros */
	private List<TcSubgiro> subgiros = new ArrayList<>();
	/** Array principal de giros */
	private List<Natgas> giros = new ArrayList<>();

	/** variable de busquedaSibgiros */
	private String busqueda = "";
	private Long idTemporal;
	@ManagedProperty("#{natgasRepository}")
	private NatgasRepository natgasRepository;

	@ManagedProperty("#{tcSubgiroRepository}")
	private TcSubgiroRepository subgiroRepository;

	@ManagedProperty("#{tcAdquisicionGiroSubgiroRepository}")
	private TcAdquisicionGiroSubgiroRepository giroSubgiroRepository;

	@PostConstruct
	public void init() {
		buscarGiros();
		buscarSubgiros();
	}

	/** Guarda un subgiro valida la clave y el giro no sea repetidos */
	public void guardarSubgiro() {
		List<String> errors = subgiroSelected.isValid();
		if (errors.isEmpty()) {
			TcSubgiro subgiroByClaveAndGiro = subgiroRepository.findFirstByClaveAndGiro(subgiroSelected.getClave(),
					subgiroSelected.getGiro());
			if (subgiroByClaveAndGiro == null || (subgiroByClaveAndGiro.getId().equals(subgiroSelected.getId()))) {
				try {
					boolean modificado = subgiroSelected.getId() != null && subgiroSelected.getId() > 0;
					subgiroSelected = subgiroRepository.save(subgiroSelected);
					if (modificado) {
						subgiros = subgiros.stream()
								.map(p -> p.getId().equals(subgiroSelected.getId()) ? subgiroSelected : p)
								.collect(Collectors.toList());
					} else {
						subgiros.add(subgiroSelected);
					}
					displayInfoMessage("Se ha " + (!modificado ? "agregado" : "modificado ")
							+ " correctamente el sub-giro comercial");
					subgiroSelected = new TcSubgiro();
				} catch (Exception e) {
					e.printStackTrace();
					displayInfoMessage("Ha ocurrrido un error al guardar el sub-giro comercial");
				}
			} else {
				displayInfoMessage("La clave del sub-giro ya ha sido registrada para el giro comercial");
			}
		} else {
			errors.forEach(this::displayInfoMessage);
		}
		// Evita que la lista se actualize por referencia con la entidad seleccioonada
		buscarSubgiros();
	}

	/** Elimina una entidad, valida que no sea usada en adquisiciones */
	public void deleteSubgiroa() {
		if (idTemporal != null) {
			TcSubgiro deleteSubgiro = subgiros.stream().filter(s -> s.getId().equals(idTemporal)).findFirst()
					.orElse(new TcSubgiro());
			try {
				if (giroSubgiroRepository.countBySubgiro(deleteSubgiro) == 0) {
					subgiroRepository.delete(deleteSubgiro.getId());
					subgiros = subgiros.stream().filter(p -> p.getId() != deleteSubgiro.getId())
							.collect(Collectors.toList());
					displayInfoMessage("El sub-giro comercial: " + deleteSubgiro.getClave() + " - "
							+ deleteSubgiro.getDescripcion() + " se ha eliminado correctamente");
				} else {
					displayInfoMessage("El sub-giro comercial: " + deleteSubgiro.getClave() + " - "
							+ deleteSubgiro.getDescripcion()
							+ " ha sido asignada a uno o mas giros no es posible eliminarlo");
				}
			} catch (Exception e) {
				e.printStackTrace();
				displayInfoMessage("Ha ocurrido un error eliminando el sub-giro comercial");
			}
		}
	}

	/** Cancela la edidcion de un subgiro y instanacia uno nuevo */
	public void resetSubgiroSelected() {
		if (subgiroSelected.getId() != null) {
			displayInfoMessage("Se cancelo la edicion");
		}
		subgiroSelected = new TcSubgiro();
	}

	/**
	 * Optiene todas las subgiros y con el metdo de busqedasimple de la entiadad
	 * filtra las concidencias
	 */
	public void buscarSubgiros() {
		if (busqueda == null || busqueda.equals("")) {
			subgiros = subgiroRepository.findAllWithGiroOrdered();
		} else {
			subgiros = subgiroRepository.findAllWithGiroOrdered().stream()
					.filter(p -> p.busquedaSimple().toLowerCase().contains(busqueda.toLowerCase()))
					.collect(Collectors.toList());
		}
	}

	public void buscarGiros() {
		giros = natgasRepository.findAllByIdsectorOrderByClvgas(this.getUserDetails().getIdSector());
	}

	public TcSubgiro getSubgiroSelected() {
		return subgiroSelected;
	}

	public void setSubgiroSelected(TcSubgiro subgiroSelected) {
		this.subgiroSelected = subgiroSelected;
	}

	public List<TcSubgiro> getSubgiros() {
		return subgiros;
	}

	public void setSubgiros(List<TcSubgiro> subgiros) {
		this.subgiros = subgiros;
	}

	public List<Natgas> getGiros() {
		return giros;
	}

	public void setGiros(List<Natgas> giros) {
		this.giros = giros;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public TcSubgiroRepository getSubgiroRepository() {
		return subgiroRepository;
	}

	public void setSubgiroRepository(TcSubgiroRepository subgiroRepository) {
		this.subgiroRepository = subgiroRepository;
	}

	public TcAdquisicionGiroSubgiroRepository getGiroSubgiroRepository() {
		return giroSubgiroRepository;
	}

	public void setGiroSubgiroRepository(TcAdquisicionGiroSubgiroRepository giroSubgiroRepository) {
		this.giroSubgiroRepository = giroSubgiroRepository;
	}

	public NatgasRepository getNatgasRepository() {
		return natgasRepository;
	}

	public void setNatgasRepository(NatgasRepository natgasRepository) {
		this.natgasRepository = natgasRepository;
	}

	public Long getIdTemporal() {
		return idTemporal;
	}

	public void setIdTemporal(Long idTemporal) {
		this.idTemporal = idTemporal;
	}

}
