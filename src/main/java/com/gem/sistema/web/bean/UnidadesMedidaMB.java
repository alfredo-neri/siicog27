package com.gem.sistema.web.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.gem.sistema.business.domain.TcUnidadMedida;
import com.gem.sistema.business.repository.catalogs.TcArticulosSARepository;
import com.gem.sistema.business.repository.catalogs.TcUnidadMedidaRepository;

/**
 * Administracion del catalogo de unidades de medida.
 * <p>
 * Alimenta el combo de unidad de medida de los articulos de una solicitud de
 * adquisicion y la validacion del cargador de Excel. Antes de este catalogo la
 * lista estaba fija en el codigo de {@code SolicitudesAdquisicionesMB}.
 * <p>
 * Sigue el mismo patron que {@code GirosSubgirosMB}: el bean habla directo con
 * el repositorio, la busqueda se resuelve en memoria con
 * {@code busquedaSimple()} de la entidad y la baja se bloquea cuando la unidad
 * ya fue usada en algun articulo.
 */
@ManagedBean(name = "unidadesMedidaMB")
@ViewScoped
public class UnidadesMedidaMB extends AbstractMB {

	/** Unidad en edicion o captura */
	private TcUnidadMedida unidadSelected = new TcUnidadMedida();

	/** Array principal de unidades de medida */
	private List<TcUnidadMedida> unidades = new ArrayList<>();

	/** Texto de busqueda */
	private String busqueda = "";

	/** Id de la unidad que el usuario pidio eliminar */
	private Long idTemporal;

	@ManagedProperty("#{tcUnidadMedidaRepository}")
	private TcUnidadMedidaRepository unidadMedidaRepository;

	@ManagedProperty("#{tcArticulosSARepository}")
	private TcArticulosSARepository articulosRepository;

	@PostConstruct
	public void init() {
		buscarUnidades();
	}

	/**
	 * Guarda la unidad. Valida que ni la clave ni la descripcion esten repetidas:
	 * las dos tienen indice unico en la base y la descripcion es la que empata el
	 * cargador de Excel.
	 */
	public void guardarUnidad() {
		List<String> errors = unidadSelected.isValid();
		if (errors.isEmpty()) {
			errors.addAll(validarDuplicados());
		}
		if (errors.isEmpty()) {
			try {
				boolean modificado = unidadSelected.getId() != null && unidadSelected.getId() > 0;
				unidadSelected = unidadMedidaRepository.save(unidadSelected);
				displayInfoMessage(
						"Se ha " + (!modificado ? "agregado" : "modificado") + " correctamente la unidad de medida");
				unidadSelected = new TcUnidadMedida();
			} catch (Exception e) {
				e.printStackTrace();
				displayInfoMessage("Ha ocurrido un error al guardar la unidad de medida");
			}
		} else {
			errors.forEach(this::displayInfoMessage);
		}
		// Evita que la lista se actualice por referencia con la entidad seleccionada
		buscarUnidades();
	}

	/**
	 * Revisa clave y descripcion contra el catalogo. Compara normalizado porque la
	 * entidad guarda las dos en mayusculas.
	 */
	private List<String> validarDuplicados() {
		List<String> errors = new ArrayList<String>();
		String clave = unidadSelected.getClave().trim().toUpperCase();
		String descripcion = unidadSelected.getDescripcion().trim().toUpperCase();
		TcUnidadMedida porClave = unidadMedidaRepository.findFirstByClave(clave);
		if (porClave != null && !esLaMisma(porClave)) {
			errors.add("La clave " + clave + " ya esta registrada");
		}
		TcUnidadMedida porDescripcion = unidadMedidaRepository.findFirstByDescripcion(descripcion);
		if (porDescripcion != null && !esLaMisma(porDescripcion)) {
			errors.add("La descripción " + descripcion + " ya esta registrada con la clave " + porDescripcion.getClave());
		}
		return errors;
	}

	/** true cuando la unidad encontrada es la que se esta editando */
	private boolean esLaMisma(TcUnidadMedida encontrada) {
		return unidadSelected.getId() != null && unidadSelected.getId().equals(encontrada.getId());
	}

	/**
	 * Elimina la unidad. Si ya fue usada en algun articulo no se borra, se ofrece
	 * darla de baja para que deje de aparecer en los combos sin perder el
	 * historico.
	 */
	public void deleteUnidad() {
		if (idTemporal == null) {
			return;
		}
		TcUnidadMedida borrar = unidades.stream().filter(u -> u.getId().equals(idTemporal)).findFirst().orElse(null);
		if (borrar == null) {
			displayInfoMessage("No se encontro la unidad de medida a eliminar");
			return;
		}
		try {
			long usos = articulosRepository.countByUnidad(borrar);
			if (usos == 0) {
				unidadMedidaRepository.delete(borrar.getId());
				displayInfoMessage("La unidad de medida: " + borrar.getClave() + " - " + borrar.getDescripcion()
						+ " se ha eliminado correctamente");
			} else {
				displayInfoMessage("La unidad de medida: " + borrar.getClave() + " - " + borrar.getDescripcion()
						+ " esta asignada a " + usos
						+ " articulo(s), no es posible eliminarla. Puede darla de baja para que deje de aparecer en la captura.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			displayInfoMessage("Ha ocurrido un error eliminando la unidad de medida");
		}
		idTemporal = null;
		buscarUnidades();
	}

	/**
	 * Alterna entre vigente y dada de baja. Una unidad dada de baja se sigue
	 * viendo en los articulos que ya la traen, pero ya no se ofrece al capturar.
	 */
	public void cambiarVigencia(TcUnidadMedida unidad) {
		if (unidad == null) {
			return;
		}
		try {
			unidad.setActivo(unidad.isVigente() ? TcUnidadMedida.INACTIVO : TcUnidadMedida.ACTIVO);
			unidadMedidaRepository.save(unidad);
			displayInfoMessage("La unidad " + unidad.getClave() + " quedo "
					+ (unidad.isVigente() ? "activa" : "inactiva"));
		} catch (Exception e) {
			e.printStackTrace();
			displayInfoMessage("Ha ocurrido un error al cambiar la vigencia de la unidad de medida");
		}
		buscarUnidades();
	}

	/** Cancela la edicion e instancia una unidad nueva */
	public void resetUnidadSelected() {
		if (unidadSelected.getId() != null) {
			displayInfoMessage("Se cancelo la edicion");
		}
		unidadSelected = new TcUnidadMedida();
	}

	/**
	 * Trae todo el catalogo y filtra en memoria con busquedaSimple() de la entidad,
	 * igual que giros y subgiros. El catalogo es chico, no amerita paginado en base.
	 */
	public void buscarUnidades() {
		if (busqueda == null || busqueda.trim().equals("")) {
			unidades = unidadMedidaRepository.findAllOrdenadas();
		} else {
			unidades = unidadMedidaRepository.findAllOrdenadas().stream()
					.filter(u -> u.busquedaSimple().toLowerCase().contains(busqueda.trim().toLowerCase()))
					.collect(Collectors.toList());
		}
	}

	/** Cuantas unidades estan vigentes, para el pie de la pantalla */
	public long getTotalVigentes() {
		return unidades.stream().filter(TcUnidadMedida::isVigente).count();
	}

	/** Cuantas unidades trae la lista en pantalla, para el pie */
	public int getTotalUnidades() {
		return unidades.size();
	}

	public TcUnidadMedida getUnidadSelected() {
		return unidadSelected;
	}

	public void setUnidadSelected(TcUnidadMedida unidadSelected) {
		this.unidadSelected = unidadSelected;
	}

	public List<TcUnidadMedida> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<TcUnidadMedida> unidades) {
		this.unidades = unidades;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public Long getIdTemporal() {
		return idTemporal;
	}

	public void setIdTemporal(Long idTemporal) {
		this.idTemporal = idTemporal;
	}

	public TcUnidadMedidaRepository getUnidadMedidaRepository() {
		return unidadMedidaRepository;
	}

	public void setUnidadMedidaRepository(TcUnidadMedidaRepository unidadMedidaRepository) {
		this.unidadMedidaRepository = unidadMedidaRepository;
	}

	public TcArticulosSARepository getArticulosRepository() {
		return articulosRepository;
	}

	public void setArticulosRepository(TcArticulosSARepository articulosRepository) {
		this.articulosRepository = articulosRepository;
	}
}
