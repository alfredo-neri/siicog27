package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.annotation.PostConstruct;
import com.gem.sistema.business.domain.ProveedorNuevo;
import com.gem.sistema.business.repository.catalogs.ProveedoresNuevoRepository;

@Component("proveedoresNuevoMB")
@Scope("view")
public class ProveedoresNuevoMB extends AbstractMB {

	private ProveedorNuevo proveedor;
	/** Array principal de proveedores */
	private List<ProveedorNuevo> proveedores = new ArrayList<>();
	private String busqueda = "";
	private List<String> tipos = ProveedorNuevo.TIPOS;

	@Value("#{proveedoresNuevoRepository}")
	private ProveedoresNuevoRepository proveedoresRepository;

	@PostConstruct
	public void init() {
		proveedor = getDefaulthProveedor();
		proveedores = proveedoresRepository.findAll();
		if(proveedores.isEmpty()) {
			proveedores = new ArrayList<>();
		}
	}

	/** Instancia por defulth de un proveedor */
	private ProveedorNuevo getDefaulthProveedor() {
		ProveedorNuevo init = new ProveedorNuevo();
		init.setDomicilioFiscal("");
		init.setNombreProveedor("");
		init.setRfc("");
		init.setTelefono("");
		init.setTipo(tipos.get(0));
		init.setCorreo("");
		init.setCurp("");
		init.setDomicilioEstado("");
		init.setInstrumentoAcreditacion("");
		init.setInstrumentoPersonalidad("");
		init.setNombrePropietario("");
		init.setNombreRepresentanteLegal("");
		return init;
	}

	/** Guarda un proveedor */
	public void guardarProveedor() {
		List<String> errors = proveedor.isValid();
		Boolean repetido = false;
		// Elimina puntos, comas, espacion y valida si ya esiste el nombre del
		// proveedor

		if (errors.isEmpty()) {
			for (ProveedorNuevo p : proveedores) {
				if (p.getId() != proveedor.getId()) {
					String index = p.getNombreProveedor().toLowerCase();
					String nombre = proveedor.getNombreProveedor().toLowerCase();
					nombre = nombre.replaceAll("[., 	]", "");
					index = index.replaceAll("[., 	]", "");
					if (nombre.equals(index)) {
						repetido = true;
						break;
					}
				}
			}
			if (!repetido) {
				// Validacion de curp repetida
				ProveedorNuevo proveedorTemp = proveedoresRepository.findOneByCurp(proveedor.getCurp());
				if (proveedorTemp == null || proveedorTemp.getId() == proveedor.getId()) {
					// Validacion de RFC repetido
					proveedorTemp = proveedoresRepository.findOneByRfc(proveedor.getRfc());
					if (proveedorTemp == null || proveedorTemp.getId() == proveedor.getId()) {
						try {
							boolean modificado = proveedor.getId() != null && proveedor.getId() > 0;
							ProveedorNuevo nuevo = proveedoresRepository.save(proveedor);
							if (modificado) {
								proveedores = proveedores.stream().map(p -> p.getId() == nuevo.getId() ? nuevo : p)
										.collect(Collectors.toList());
							} else {
								proveedores.add(nuevo);
							}
							displayInfoMessage("Se ha " + (!modificado ? "agregado" : "modificado ")
									+ " correctamente el proveedor");
							proveedor = getDefaulthProveedor();
						} catch (Exception e) {
							e.printStackTrace();
							displayInfoMessage("Ha ocurrrido un error al guardar el proveedor");
						}
					} else {
						displayInfoMessage("EL RFC ya ha sido registrado");
					}
				} else {
					displayInfoMessage("La CURP ya ha sido registrado");
				}
			} else {
				displayInfoMessage("El nombre del proveedor ya existe");
			}
		} else {
			errors.forEach(e -> displayInfoMessage(e));
		}
		// Evita que la lsita se actualize por referencia con el proveedor selecionado
		buscar();
	}

	/** Elimina un proveedor */
	public void deleteProveedor() {
		if (proveedor != null) {
			try {
				proveedoresRepository.deleteById(proveedor.getId());
				proveedores = proveedores.stream().filter(p -> p.getId() != proveedor.getId())
						.collect(Collectors.toList());
				displayInfoMessage("El proveedor se ha eliminado correctamente");
			} catch (Exception e) {
				e.printStackTrace();
				displayInfoMessage("Ha ocurrido un error eliminando el proveedor");
			}
		}
	}

	public void resetProveedor() {
		if (proveedor.getId() != null) {
			displayInfoMessage("Se cancelo la edicion");
		}
		proveedor = getDefaulthProveedor();

	}

	public void buscar() {
		if (busqueda == null || busqueda.equals("")) {
			proveedores = proveedoresRepository.findAll();
		} else {
			proveedores = proveedoresRepository.findAll().stream()
					.filter(p -> p.busquedaSimple().toLowerCase().contains(busqueda.toLowerCase()))
					.collect(Collectors.toList());
		}
	}

	public ProveedorNuevo getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorNuevo proveedor) {
		this.proveedor = proveedor;
	}

	public List<ProveedorNuevo> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<ProveedorNuevo> proveedores) {
		this.proveedores = proveedores;
	}

	public ProveedoresNuevoRepository getProveedoresRepository() {
		return proveedoresRepository;
	}

	public void setProveedoresRepository(ProveedoresNuevoRepository proveedoresRepository) {
		this.proveedoresRepository = proveedoresRepository;
	}

	public List<String> getTipos() {
		return tipos;
	}

	public void setTipos(List<String> tipos) {
		this.tipos = tipos;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

}
