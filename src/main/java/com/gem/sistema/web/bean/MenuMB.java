/**
 *
 */
package com.gem.sistema.web.bean;

import java.io.Serializable;
import java.text.Normalizer;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.Submenu;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.web.security.model.GemUser;
import com.gem.sistema.web.security.services.MenuService;

import jakarta.annotation.PostConstruct;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuMB.
 *
 * Ademas del menu, da al marco de SIICOG (layout) los datos de la sesion: grupos del menu lateral,
 * ejercicio fiscal, iniciales del usuario, saludo y accesos rapidos de la pantalla de inicio.
 *
 * @author gauss
 */
@Component("menuMB")
@Scope("session")
public class MenuMB extends AbstractMB {

	/**
	 * Grupos del menu lateral (prototipo SIICOG). Cada modulo de primer nivel de TC_MENU_NIVELES se
	 * asigna a un grupo y a un icono por su nombre; los que no aparecen aqui van a "Otros modulos".
	 * Clave: nombre del modulo en minusculas y sin acentos.
	 */
	private static final Map<String, String[]> MODULOS = new LinkedHashMap<>();
	static {
		modulo("catalogos", "Contabilidad", "pi pi-book");
		modulo("cuentas", "Contabilidad", "pi pi-list");
		modulo("polizas", "Contabilidad", "pi pi-file-edit");
		modulo("afectacion", "Contabilidad", "pi pi-sync");
		modulo("consultas", "Contabilidad", "pi pi-search");
		modulo("presupuesto", "Presupuesto y finanzas", "pi pi-chart-pie");
		modulo("control presupuestal", "Presupuesto y finanzas", "pi pi-chart-bar");
		modulo("ingresos propios", "Presupuesto y finanzas", "pi pi-arrow-up");
		modulo("tesoreria", "Presupuesto y finanzas", "pi pi-building-columns");
		modulo("contratos", "Presupuesto y finanzas", "pi pi-file");
		modulo("reportes", "Reportes", "pi pi-chart-line");
		modulo("reportes especiales", "Reportes", "pi pi-star");
		modulo("usuarios", "Administración", "pi pi-users");
		modulo("utilerias", "Administración", "pi pi-wrench");
	}

	/** Orden de los grupos en el menu lateral. */
	private static final List<String> ORDEN_GRUPOS = List.of("Contabilidad", "Presupuesto y finanzas", "Reportes",
			"Administración", "Otros módulos");

	/**
	 * Accesos rapidos de la pantalla de inicio: titulo, descripcion, icono y parte de la URL con que se
	 * localiza la opcion en el menu del usuario. Solo se muestran los que el usuario tiene en su menu.
	 */
	private static final String[][] ACCESOS = {
			{ "Pólizas", "Captura y mantenimiento", "pi pi-file-edit", "/polizas/mantenimientoPolizas" },
			{ "Catálogo de cuentas", "Alta, edición y búsqueda", "pi pi-list", "/catalogs/catalog_maintenance_accounts" },
			{ "Balanza de comprobación", "Reporte mensual", "pi pi-chart-bar", "/reports/RF00914.xhtml" },
			{ "Control presupuestario", "Acciones y contratos por programa", "pi pi-chart-pie",
					"/controlPresupuestario/control.xhtml" },
			{ "Ingresos propios", "Consulta de pólizas de ingreso", "pi pi-arrow-up", "/ingresos/ingresoConsulta.xhtml" } };

	/** The menu model. */
	private MenuModel menuModel;

	/** Grupos del menu lateral, calculados una vez por sesion. */
	private List<GrupoMenu> grupos;

	/** Accesos rapidos de la pantalla de inicio. */
	private List<AccesoRapido> accesosRapidos;

	/** Ejercicio fiscal del sector (CONCTB.ANOEMP). */
	private Integer ejercicio;

	//@Autowired
	/** The menu service. */
	//@Qualifier("menuServiceImpl")
    @Value("#{menuServiceImpl}")
	private MenuService menuService;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	private static void modulo(String nombre, String grupo, String icono) {
		MODULOS.put(nombre, new String[] { grupo, icono });
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init(){
		// las paginas publicas (error, sesion expirada) usan el mismo layout sin usuario autenticado
		if (this.getUserDetails() != null) {
			this.menuModel = menuService.getMenuModelByUsuario(this.getUserDetails().getUsername(), this.getUserDetails().getIdSector());
		}
	}

	/**
	 * Gets the menu model.
	 *
	 * @return the menuModel
	 */
	public MenuModel getMenuModel() {
		if (this.menuModel == null && this.getUserDetails() != null) {
			this.menuModel = menuService.getMenuModelByUsuario(this.getUserDetails().getUsername(), this.getUserDetails().getIdSector());
		}
		return this.menuModel;
	}

	/**
	 * Grupos del menu lateral. Cada grupo lleva su propio modelo con los modulos que le tocan, para
	 * dibujarse como un p:panelMenu bajo su titulo.
	 *
	 * @return grupos en el orden del prototipo; vacio si no hay usuario
	 */
	public List<GrupoMenu> getGrupos() {
		if (grupos == null && getMenuModel() != null) {
			Map<String, DefaultMenuModel> porGrupo = new LinkedHashMap<>();
			ORDEN_GRUPOS.forEach(g -> porGrupo.put(g, new DefaultMenuModel()));
			for (MenuElement element : getMenuModel().getElements()) {
				String[] modulo = MODULOS.getOrDefault(normalizar(etiqueta(element)),
						new String[] { "Otros módulos", "pi pi-folder" });
				if (element instanceof DefaultSubMenu submenu && submenu.getIcon() == null) {
					submenu.setIcon(modulo[1]);
				}
				porGrupo.get(modulo[0]).getElements().add(element);
			}
			List<GrupoMenu> lista = new ArrayList<>();
			porGrupo.forEach((nombre, modelo) -> {
				if (!modelo.getElements().isEmpty()) {
					lista.add(new GrupoMenu(nombre, "sg-menu-" + lista.size(), modelo));
				}
			});
			grupos = lista;
		}
		return grupos == null ? List.of() : grupos;
	}

	/**
	 * Accesos rapidos de la pantalla de inicio, solo los que el usuario tiene en su menu.
	 *
	 * @return accesos con la URL tomada del menu
	 */
	public List<AccesoRapido> getAccesosRapidos() {
		if (accesosRapidos == null && getMenuModel() != null) {
			List<String> urls = new ArrayList<>();
			recolectarUrls(getMenuModel().getElements(), urls);
			List<AccesoRapido> lista = new ArrayList<>();
			for (String[] acceso : ACCESOS) {
				urls.stream().filter(u -> u.contains(acceso[3])).findFirst()
						.ifPresent(u -> lista.add(new AccesoRapido(acceso[0], acceso[1], acceso[2], u)));
			}
			accesosRapidos = lista;
		}
		return accesosRapidos == null ? List.of() : accesosRapidos;
	}

	/**
	 * Ejercicio fiscal del sector del usuario.
	 *
	 * @return año de CONCTB, o null si no hay usuario o configuracion
	 */
	public Integer getEjercicio() {
		GemUser user = this.getUserDetails();
		if (ejercicio == null && user != null) {
			Conctb conctb = conctbRepository.findByIdsector(user.getIdSector());
			ejercicio = conctb == null ? null : conctb.getAnoemp();
		}
		return ejercicio;
	}

	/**
	 * Iniciales para el avatar de la barra superior.
	 *
	 * @return dos letras del nombre (o del usuario)
	 */
	public String getIniciales() {
		GemUser user = this.getUserDetails();
		if (user == null) {
			return StringUtils.EMPTY;
		}
		String nombre = StringUtils.defaultIfBlank(user.getFullName(), user.getUsername());
		String[] partes = StringUtils.split(nombre.trim());
		String iniciales = partes.length > 1 ? partes[0].substring(0, 1) + partes[1].substring(0, 1)
				: StringUtils.left(nombre, 2);
		return iniciales.toUpperCase(Locale.ROOT);
	}

	/**
	 * Nombre a mostrar en la barra superior y en el saludo.
	 *
	 * @return nombre completo, o el usuario si no tiene nombre
	 */
	public String getNombreUsuario() {
		GemUser user = this.getUserDetails();
		return user == null ? StringUtils.EMPTY : StringUtils.defaultIfBlank(user.getFullName(), user.getUsername());
	}

	/**
	 * Saludo segun la hora del servidor, como en el prototipo.
	 *
	 * @return "Buenos dias", "Buenas tardes" o "Buenas noches"
	 */
	public String getSaludo() {
		int hora = LocalTime.now().getHour();
		return hora < 12 ? "Buenos días" : hora < 19 ? "Buenas tardes" : "Buenas noches";
	}

	private static String etiqueta(MenuElement element) {
		if (element instanceof Submenu submenu) {
			return submenu.getLabel();
		}
		return element instanceof MenuItem item && item.getValue() != null ? item.getValue().toString() : "";
	}

	private static String normalizar(String texto) {
		String sinAcentos = Normalizer.normalize(StringUtils.defaultString(texto), Normalizer.Form.NFD)
				.replaceAll("\\p{M}", "");
		return sinAcentos.trim().toLowerCase(Locale.ROOT);
	}

	private static void recolectarUrls(List<MenuElement> elements, List<String> urls) {
		for (MenuElement element : elements) {
			if (element instanceof Submenu submenu) {
				recolectarUrls(submenu.getElements(), urls);
			} else if (element instanceof MenuItem item && item.getUrl() != null) {
				urls.add(item.getUrl());
			}
		}
	}

	/**
	 * Sets the menu model.
	 *
	 * @param menuModel the menuModel to set
	 */
	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	/**
	 * Gets the menu service.
	 *
	 * @return the menuService
	 */
	public MenuService getMenuService() {
		return menuService;
	}

	/**
	 * Sets the menu service.
	 *
	 * @param menuService the menuService to set
	 */
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	/**
	 * Grupo del menu lateral.
	 *
	 * @param nombre titulo del grupo
	 * @param id     id del p:panelMenu (fijo, para que recuerde que modulos estan abiertos)
	 * @param modelo modulos del grupo
	 */
	public record GrupoMenu(String nombre, String id, MenuModel modelo) implements Serializable {
	}

	/**
	 * Acceso rapido de la pantalla de inicio.
	 */
	public record AccesoRapido(String titulo, String descripcion, String icono, String url) implements Serializable {
	}
}
