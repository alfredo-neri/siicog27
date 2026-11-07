package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.tree.Tree;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Catmun;
import com.gem.sistema.business.domain.TcEntePubCentral;
import com.gem.sistema.business.domain.TcEntidadAdministrativa;
import com.gem.sistema.business.domain.TcMenu;
import com.gem.sistema.business.domain.TcMenuItem;
import com.gem.sistema.business.domain.TcMenuNiveles;
import com.gem.sistema.business.domain.TcRole;
import com.gem.sistema.business.domain.TcTipoUsuario;
import com.gem.sistema.business.domain.TcUsuario;
import com.gem.sistema.business.domain.TrUsuarios2MenuItems;
import com.gem.sistema.business.predicates.PolienPredicates;
import com.gem.sistema.business.predicates.TcUsuarioPredicates;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.CatmunRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.TcEntePubCentralRepository;
import com.gem.sistema.business.repository.catalogs.TcEntidadAdministrativaRepository;
import com.gem.sistema.business.repository.catalogs.TcLocalidadesUsuarioRepository;
import com.gem.sistema.business.repository.catalogs.TcMenuItemRepository;
import com.gem.sistema.business.repository.catalogs.TcMenuNivelesRepository;
import com.gem.sistema.business.repository.catalogs.TcMenuRepository;
import com.gem.sistema.business.repository.catalogs.TcRoleRepository;
import com.gem.sistema.business.repository.catalogs.TcTipoUsuarioRepository;
import com.gem.sistema.business.repository.catalogs.TcUsuarioRepository;
import com.gem.sistema.business.repository.catalogs.TrUsuarios2MenuItemsRepository;
import com.gem.sistema.business.service.catalogos.UsersService;
import com.gem.sistema.web.datamodel.TcUsuarioDataModel;

// TODO: Auto-generated Javadoc
/**
 * The Class UsersMB.
 *
 * @author Gauss
 */
@ManagedBean(name = "usersMB")
@ViewScoped
public class UsersMB extends AbstractMB implements Serializable {

	/**
	 * Serial default.
	 */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersMB.class);

	/** The tc usuario data model. */
	@ManagedProperty(value = "#{tcUsuarioDataModel}")
	private TcUsuarioDataModel tcUsuarioDataModel;

	/** The selected tc usuario. */
	private TcUsuario selectedTcUsuario;

	/** The mirror tc usuario. */
	private TcUsuario mirrorTcUsuario;

	/** The new tc usuario. */
	private TcUsuario newTcUsuario = new TcUsuario();

	private TcUsuario updateUsuario = new TcUsuario();

	/** The tc usuario repository. */
	@ManagedProperty(value = "#{tcUsuarioRepository}")
	private TcUsuarioRepository tcUsuarioRepository;

	/** The entidad administrativa repository. */
	@ManagedProperty(value = "#{tcEntidadAdministrativaRepository}")
	private TcEntidadAdministrativaRepository entidadAdministrativaRepository;

	/** The localidades usuario repository. */
	@ManagedProperty(value = "#{tcLocalidadesUsuarioRepository}")
	private TcLocalidadesUsuarioRepository localidadesUsuarioRepository;

	/** The tc menu repository. */
	@ManagedProperty(value = "#{tcMenuRepository}")
	private TcMenuRepository tcMenuRepository;

	/** The tc role repository. */
	@ManagedProperty(value = "#{tcRoleRepository}")
	private TcRoleRepository tcRoleRepository;

	/** The tc tipo usuario repository. */
	@ManagedProperty(value = "#{tcTipoUsuarioRepository}")
	private TcTipoUsuarioRepository tcTipoUsuarioRepository;

	/** The tc menu item repository. */
	@ManagedProperty(value = "#{tcMenuItemRepository}")
	private TcMenuItemRepository tcMenuItemRepository;

	/** The tc ente pub central repository. */
	@ManagedProperty(value = "#{tcEntePubCentralRepository}")
	private TcEntePubCentralRepository tcEntePubCentralRepository;

	/** The catdep repository. */
	@ManagedProperty(value = "#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/** The catmun repository. */
	@ManagedProperty(value = "#{catmunRepository}")
	private CatmunRepository catmunRepository;

	/** The password encoder. */
	@ManagedProperty(value = "#{passwordEncoder}")
	private PasswordEncoder passwordEncoder;

	/** The tc menu niveles repository. */
	@ManagedProperty(value = "#{tcMenuNivelesRepository}")
	private TcMenuNivelesRepository tcMenuNivelesRepository;

	/** The tr usuarios 2 menu items repository. */
	@ManagedProperty(value = "#{trUsuarios2MenuItemsRepository}")
	private TrUsuarios2MenuItemsRepository trUsuarios2MenuItemsRepository;

	/** The polien repository. */
	@ManagedProperty("#{polienRepository}")
	private PolienRepository polienRepository;

	@ManagedProperty("#{usersService}")
	private UsersService usersService;

	// private List<TcLocalidadesUsuario> localidades;

	/** The entidades. */
	private List<TcEntidadAdministrativa> entidades;

	/** The tipo usuarios. */
	private List<TcTipoUsuario> tipoUsuarios;

	/** The menus. */
	private List<TcMenu> menus;

	/** The roles. */
	private List<TcRole> roles;

	/** The selected menu items. */
	private Map<Long, String[]> selectedMenuItems = new HashMap<Long, String[]>();

	/** The active update. */
	private Boolean activeUpdate = Boolean.TRUE;

	/** The entidades central. */
	private List<TcEntePubCentral> entidadesCentral;

	/** The unidad admin central. */
	private List<Catdep> unidadAdminCentral;

	/** The Constant SECTOR_CENTRAL. */
	private static final Long SECTOR_CENTRAL = 2l;

	/** The Constant CATDEP_LAST_LEVEL. */
	private static final String CATDEP_LAST_LEVEL = "S";

	/** The id localidad central. */
	private Long idLocalidadCentral;

	/** The localidades muni. */
	private List<Catmun> localidadesMuni;

	/** The menu. */
	private TreeNode menu;

	/** The selected menus. */
	private TreeNode[] selectedMenus;

	/** The arbol. */
	private Tree arbol;

	/** The lista menu niveles. */
	private List<TcMenuNiveles> listaMenuNiveles;

	/**
	 * Gets the id localidad central.
	 *
	 * @return the idLocalidadCentral
	 */
	public Long getIdLocalidadCentral() {
		return idLocalidadCentral;
	}

	/**
	 * Sets the id localidad central.
	 *
	 * @param idLocalidadCentral
	 *            the idLocalidadCentral to set
	 */
	public void setIdLocalidadCentral(Long idLocalidadCentral) {
		this.idLocalidadCentral = idLocalidadCentral;
	}

	/**
	 * Gets the catmun repository.
	 *
	 * @return the catmunRepository
	 */
	public CatmunRepository getCatmunRepository() {
		return catmunRepository;
	}

	/**
	 * Sets the catmun repository.
	 *
	 * @param catmunRepository
	 *            the catmunRepository to set
	 */
	public void setCatmunRepository(CatmunRepository catmunRepository) {
		this.catmunRepository = catmunRepository;
	}

	/**
	 * Gets the localidades muni.
	 *
	 * @return the localidadesMuni
	 */
	public List<Catmun> getLocalidadesMuni() {
		return localidadesMuni;
	}

	/**
	 * Sets the localidades muni.
	 *
	 * @param localidadesMuni
	 *            the localidadesMuni to set
	 */
	public void setLocalidadesMuni(List<Catmun> localidadesMuni) {
		this.localidadesMuni = localidadesMuni;
	}

	/**
	 * Gets the polien repository.
	 *
	 * @return the polien repository
	 */
	public PolienRepository getPolienRepository() {
		return polienRepository;
	}

	/**
	 * Sets the polien repository.
	 *
	 * @param polienRepository
	 *            the new polien repository
	 */
	public void setPolienRepository(PolienRepository polienRepository) {
		this.polienRepository = polienRepository;
	}

	public UsersService getUsersService() {
		return usersService;
	}

	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	public TcUsuario getUpdateUsuario() {
		return updateUsuario;
	}

	public void setUpdateUsuario(TcUsuario updateUsuario) {
		this.updateUsuario = updateUsuario;
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct UsersMB ");
		entidades = entidadAdministrativaRepository.findAll();
		unidadAdminCentral = this.catdepRepository.findAllByIdsectorAndUltnivOrderByClvdep(SECTOR_CENTRAL.intValue(),
				CATDEP_LAST_LEVEL);
		entidadesCentral = this.tcEntePubCentralRepository.findAll();
		// localidades = localidadesUsuarioRepository.findAll();
		localidadesMuni = IteratorUtils.toList(this.catmunRepository.findAll(SORT_BY_CLVMUN).iterator());
		tipoUsuarios = tcTipoUsuarioRepository.findAll();

		listaMenuNiveles = new ArrayList<TcMenuNiveles>();
		listaMenuNiveles = tcMenuNivelesRepository.findByIdSector(this.getUserDetails().getIdSector());
		// this.setMenu(this.getMenuRoot(menus));
		this.setMenu(this.getMenuRootNiveles(listaMenuNiveles));
		roles = tcRoleRepository.findAll();
		this.tcUsuarioDataModel.setIdSector(Long.valueOf(this.getUserDetails().getIdSector()));
	}

	/**
	 * Gets the menu root niveles.
	 *
	 * @param menusNiveles
	 *            the menus niveles
	 * @return the menu root niveles
	 */
	private TreeNode getMenuRootNiveles(List<TcMenuNiveles> menusNiveles) {
		TreeNode root = new CheckboxTreeNode();
		for (TcMenuNiveles tcMenu : menusNiveles) {
			if (tcMenu.getIdMenuPadre() == 0) { // Se agregan los menu padre
				TreeNode menu = new CheckboxTreeNode(tcMenu, root);
				validarHijos(menusNiveles, tcMenu.getClaveMenu(), menu);
			}

		}
		root.setExpanded(Boolean.TRUE);
		return root;
	}

	/**
	 * Validar hijos.
	 *
	 * @param menusDTO
	 *            the menus DTO
	 * @param claveMenu
	 *            the clave menu
	 * @param root
	 *            the root
	 */
	private void validarHijos(List<TcMenuNiveles> menusDTO, int claveMenu, TreeNode root) {

		List<TcMenuNiveles> menuTemp = new ArrayList<TcMenuNiveles>();
		for (TcMenuNiveles men : menusDTO) {
			if (men.getIdMenuPadre().intValue() == claveMenu) {
				menuTemp.add(men);
			}
		}
		/**
		 * Cuando el tamano del arreglo sea mayor a uno debe ser submenu, en
		 * otro caso debera ser un SubMenuItem
		 */
		if (menuTemp.size() >= 1) {
			for (TcMenuNiveles men : menuTemp) {
				TreeNode submenu0 = new CheckboxTreeNode(men, root);
				validarHijos(menusDTO, men.getClaveMenu(), submenu0);
			}
		}

	}

	// private TreeNode getMenuRoot(List<TcMenu> menus) {
	// TreeNode root = new CheckboxTreeNode();
	// for (TcMenu tcMenu : menus) {
	// TreeNode menu = new CheckboxTreeNode(tcMenu, root);
	// List<TcMenuItem> menuItems = tcMenu.getMenuItems();
	// for (TcMenuItem tcMenu2 : menuItems) {
	// if (tcMenu2.getIdSector().intValue() ==
	// this.getUserDetails().getIdSector()) {
	// new CheckboxTreeNode(tcMenu2, menu);
	// }
	// }
	// menu.setExpanded(Boolean.TRUE);
	// }
	// root.setExpanded(Boolean.TRUE);
	// return root;
	// }

	/** The Constant SORT_BY_CLVMUN. */
	private static final Sort SORT_BY_CLVMUN = new Sort(Sort.Direction.ASC, "nommun");

	/**
	 * Prepara un nuevo Usuario.
	 */
	public void prepareInsert() {
		newTcUsuario = new TcUsuario();
		newTcUsuario.setNombre(StringUtils.EMPTY);
		newTcUsuario.setUsuario(StringUtils.EMPTY);
		newTcUsuario.setContrasenia(StringUtils.EMPTY);
		newTcUsuario.setAccountnonlocked(Boolean.TRUE);
		newTcUsuario.setAccountNonExpired(Boolean.TRUE);
		newTcUsuario.setHabilitado(Boolean.TRUE);
		newTcUsuario.setCredentialsNonExpired(Boolean.TRUE);
		newTcUsuario.getTipoUsuario().setId(Long.valueOf(this.getUserDetails().getIdSector()));
		this.selectedMenus = new TreeNode[] {};
		this.menu.setSelected(Boolean.FALSE);
		selectedMenuItems = transformMenuItems2Map(newTcUsuario.getMenuItems());
		chooseSector();
	}

	/**
	 * Prepara un nuevo Usuario.
	 *
	 * @param actionEvent
	 *            the action event
	 */
	public void prepareUpdate(ActionEvent actionEvent) {
		LOGGER.debug("usuario::" + selectedTcUsuario.getNombre());
		mirrorTcUsuario = tcUsuarioRepository.findOne(selectedTcUsuario.getId());
		this.selectedTcUsuario = tcUsuarioRepository.findOne(selectedTcUsuario.getId());
		selectedMenuItems = transformMenuItems2Map(mirrorTcUsuario.getMenuItems());
		this.setMenu(this.getMenuRootNiveles(listaMenuNiveles));
		this.menu.setSelected(Boolean.FALSE);
		List<TreeNode> menus = this.menu.getChildren();
		updateUsuario = selectedTcUsuario;
		// metodo recursivo para llenar elementos seleccionados
		for (TrUsuarios2MenuItems menuItemsRelation : trUsuarios2MenuItemsRepository
				.findAllByIdUsuario(Long.valueOf(selectedTcUsuario.getId()).intValue())) {
			this.isInNodeSelected(menus, menuItemsRelation.getIdMenuItem());
		}

		if (SECTOR_CENTRAL == selectedTcUsuario.getTipoUsuario().getId()) {
			RequestContext.getCurrentInstance().execute("PF('usuarioUpdateDialog2').show()");
		} else {
			RequestContext.getCurrentInstance().execute("PF('usuarioUpdateDialog').show()");
		}
	}

	/**
	 * Checks if is in node selected.
	 *
	 * @param menus
	 *            the menus
	 * @param idMenuItem
	 *            the id menu item
	 */
	private void isInNodeSelected(List<TreeNode> menus, Integer idMenuItem) {
		List<TreeNode> selected = new ArrayList<TreeNode>();
		for (TreeNode hijos1 : menus) {
			if (!hijos1.getChildren().isEmpty()) {// tiene más hijos
				List<TreeNode> menuHijos2 = hijos1.getChildren();
				isInNodeSelected(menuHijos2, idMenuItem);
			} else {
				if (idMenuItem.equals(((TcMenuNiveles) hijos1.getData()).getClaveMenu())) {
					hijos1.setSelected(Boolean.TRUE);
					selected.add(hijos1);
				}
			}
		}
		this.selectedMenus = selected.toArray(new TreeNode[selected.size()]);
	}

	/**
	 * Prepara un nuevo Usuario.
	 *
	 * @param actionEvent
	 *            the action event
	 */
	public void deleteUser(ActionEvent actionEvent) {
		LOGGER.debug("usuario::" + selectedTcUsuario.getNombre());
		mirrorTcUsuario = tcUsuarioRepository.findOne(selectedTcUsuario.getId());
		Long count = this.polienRepository.count(PolienPredicates.deleteByidUser(mirrorTcUsuario.getUsuario()));
		if (count > 0) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
					"El usuario no se puede eliminar porque tiene polizas asociadas");
		} else {

			mirrorTcUsuario.setHabilitado(Boolean.FALSE);
			this.tcUsuarioRepository.save(mirrorTcUsuario);
			this.activeUpdate = !this.mirrorTcUsuario.getHabilitado();
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se elimino correctamente el usuario");
		}

	}

	/**
	 * Actualiza un usuario.
	 *
	 * @param actionEvent
	 *            the action event
	 */
	public void actualizar(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();

		try {

			List<TcMenuNiveles> toSave = new ArrayList<TcMenuNiveles>();
			for (TreeNode treeNode : this.selectedMenus) {
				if (treeNode.getData() instanceof TcMenuNiveles) {
					toSave.add((TcMenuNiveles) treeNode.getData());
					while (treeNode.getParent() != null) {
						toSave.add((TcMenuNiveles) treeNode.getData());
						treeNode = treeNode.getParent();
					}
				}
			}
			List<TcMenuNiveles> nivelesCompletosList = new ArrayList<TcMenuNiveles>();
			Map<Integer, TcMenuNiveles> mapNiveles = new HashMap<Integer, TcMenuNiveles>(toSave.size());
			for (TcMenuNiveles saveElement : toSave) {
				mapNiveles.put(saveElement.getClaveMenu(), saveElement);
			}
			for (Entry<Integer, TcMenuNiveles> p : mapNiveles.entrySet()) {
				nivelesCompletosList.add(p.getValue());

			}
			// selectedTcUsuario.setMenuNiveles(toSave);
			if (StringUtils.isNotBlank(selectedTcUsuario.getCapturedPass())
					&& !StringUtils.equals(this.passwordEncoder.encode(getSelectedTcUsuario().getCapturedPass()),
							mirrorTcUsuario.getContrasenia())) {
				final String pass = selectedTcUsuario.getCapturedPass();
				selectedTcUsuario.setContrasenia(passwordEncoder.encode(pass));
			} else {
				selectedTcUsuario.setContrasenia(mirrorTcUsuario.getContrasenia());
			}
			tcUsuarioRepository.save(selectedTcUsuario);
			// usersService.update(updateUsuario);

			trUsuarios2MenuItemsRepository.limpiarRelacion(Long.valueOf(selectedTcUsuario.getId()).intValue());
			// insertar en la entidad relacion usuarios 2 menu items
			for (TcMenuNiveles entidad : nivelesCompletosList) {
				TrUsuarios2MenuItems entity = new TrUsuarios2MenuItems();
				entity.setIdUsuario(Long.valueOf(selectedTcUsuario.getId()).intValue());
				entity.setIdMenuItem(entidad.getClaveMenu());

				trUsuarios2MenuItemsRepository.save(entity);
			}
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Exito!",
					String.format("Usuario  %1$s  Actualizado correctamente", selectedTcUsuario.getUsuario()));

		} catch (Exception e) {
			e.printStackTrace();
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Error!", e.getMessage());
		}

		this.chooseCloseUpdateSector();
	}

	/**
	 * Inserta un usuaario.
	 *
	 * @param actionEvent
	 *            the action event
	 */
	public void insert(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = null;
		if (this.selectedMenus.length > 0) {
			if (!this.tcUsuarioRepository.exists(TcUsuarioPredicates.findByUsuario(newTcUsuario.getUsuario()))) {
				try {
					final String pass = newTcUsuario.getCapturedPass();
					newTcUsuario.setContrasenia(passwordEncoder.encode(pass));
					newTcUsuario = tcUsuarioRepository.save(generateUserCad(newTcUsuario));
					List<TcMenuNiveles> toSave = new ArrayList<TcMenuNiveles>();
					for (TreeNode treeNode : this.selectedMenus) {
						if (treeNode.getData() instanceof TcMenuNiveles) {
							toSave.add((TcMenuNiveles) treeNode.getData());
							while (treeNode.getParent() != null) {
								toSave.add((TcMenuNiveles) treeNode.getData());
								treeNode = treeNode.getParent();
							}
						}
					}
					List<TcMenuNiveles> nivelesCompletosList = new ArrayList<TcMenuNiveles>();
					Map<Integer, TcMenuNiveles> mapNiveles = new HashMap<Integer, TcMenuNiveles>(toSave.size());
					for (TcMenuNiveles saveElement : toSave) {
						mapNiveles.put(saveElement.getClaveMenu(), saveElement);
					}
					for (Entry<Integer, TcMenuNiveles> p : mapNiveles.entrySet()) {
						nivelesCompletosList.add(p.getValue());

					}

					// newTcUsuario.setMenuNiveles(toSave);
					tcUsuarioRepository.save(newTcUsuario);
					// insertar en la entidad relacion usuarios 2 menu items
					trUsuarios2MenuItemsRepository.limpiarRelacion(Long.valueOf(newTcUsuario.getId()).intValue());
					for (TcMenuNiveles entidad : nivelesCompletosList) {
						TrUsuarios2MenuItems entity = new TrUsuarios2MenuItems();
						entity.setIdUsuario(Long.valueOf(newTcUsuario.getId()).intValue());
						entity.setIdMenuItem(entidad.getClaveMenu());

						trUsuarios2MenuItemsRepository.save(entity);
					}
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito",
							String.format("Usuario [ %1$s ] Agregado correctamente", newTcUsuario.getUsuario()));
					this.chooseCloseSector();
				} catch (Exception e) {
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
				}
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
						"El User ID ya fué capturado previamente, favor de modificarlo");
			}
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Favor de seleccionar al menos una opción de menu válida");
		}

		context.addMessage(this.chooseErrMsgSector(), msg);
	}

	/**
	 * Gets the tc usuario data model.
	 *
	 * @return the tcUsuarioDataModel
	 */
	public TcUsuarioDataModel getTcUsuarioDataModel() {
		return tcUsuarioDataModel;
	}

	/**
	 * Sets the tc usuario data model.
	 *
	 * @param tcUsuarioDataModel
	 *            the tcUsuarioDataModel to set
	 */
	public void setTcUsuarioDataModel(TcUsuarioDataModel tcUsuarioDataModel) {
		this.tcUsuarioDataModel = tcUsuarioDataModel;
	}

	/**
	 * Gets the selected tc usuario.
	 *
	 * @return the selectedTcUsuario
	 */
	public TcUsuario getSelectedTcUsuario() {
		return selectedTcUsuario;
	}

	/**
	 * Sets the selected tc usuario.
	 *
	 * @param selectedTcUsuario
	 *            the selectedTcUsuario to set
	 */
	public void setSelectedTcUsuario(TcUsuario selectedTcUsuario) {
		this.selectedTcUsuario = selectedTcUsuario;
	}

	/**
	 * Gets the tc usuario repository.
	 *
	 * @return the tcUsuarioRepository
	 */
	public TcUsuarioRepository getTcUsuarioRepository() {
		return tcUsuarioRepository;
	}

	/**
	 * Sets the tc usuario repository.
	 *
	 * @param tcUsuarioRepository
	 *            the tcUsuarioRepository to set
	 */
	public void setTcUsuarioRepository(TcUsuarioRepository tcUsuarioRepository) {
		this.tcUsuarioRepository = tcUsuarioRepository;
	}

	/**
	 * Gets the entidades.
	 *
	 * @return the entidades
	 */
	public List<TcEntidadAdministrativa> getEntidades() {
		return entidades;
	}

	/**
	 * Sets the entidades.
	 *
	 * @param entidades
	 *            the entidades to set
	 */
	public void setEntidades(List<TcEntidadAdministrativa> entidades) {
		this.entidades = entidades;
	}

	/**
	 * Gets the tipo usuarios.
	 *
	 * @return the tipoUsuarios
	 */
	public List<TcTipoUsuario> getTipoUsuarios() {
		return tipoUsuarios;
	}

	/**
	 * Sets the tipo usuarios.
	 *
	 * @param tipoUsuarios
	 *            the tipoUsuarios to set
	 */
	public void setTipoUsuarios(List<TcTipoUsuario> tipoUsuarios) {
		this.tipoUsuarios = tipoUsuarios;
	}

	/**
	 * Gets the new tc usuario.
	 *
	 * @return the newTcUsuario
	 */
	public TcUsuario getNewTcUsuario() {
		return newTcUsuario;
	}

	/**
	 * Sets the new tc usuario.
	 *
	 * @param newTcUsuario
	 *            the newTcUsuario to set
	 */
	public void setNewTcUsuario(TcUsuario newTcUsuario) {
		this.newTcUsuario = newTcUsuario;
	}

	/**
	 * Gets the entidad administrativa repository.
	 *
	 * @return the entidadAdministrativaRepository
	 */
	public TcEntidadAdministrativaRepository getEntidadAdministrativaRepository() {
		return entidadAdministrativaRepository;
	}

	/**
	 * Sets the entidad administrativa repository.
	 *
	 * @param entidadAdministrativaRepository
	 *            the entidadAdministrativaRepository to set
	 */
	public void setEntidadAdministrativaRepository(TcEntidadAdministrativaRepository entidadAdministrativaRepository) {
		this.entidadAdministrativaRepository = entidadAdministrativaRepository;
	}

	/**
	 * Gets the localidades usuario repository.
	 *
	 * @return the localidadesUsuarioRepository
	 */
	public TcLocalidadesUsuarioRepository getLocalidadesUsuarioRepository() {
		return localidadesUsuarioRepository;
	}

	/**
	 * Sets the localidades usuario repository.
	 *
	 * @param localidadesUsuarioRepository
	 *            the localidadesUsuarioRepository to set
	 */
	public void setLocalidadesUsuarioRepository(TcLocalidadesUsuarioRepository localidadesUsuarioRepository) {
		this.localidadesUsuarioRepository = localidadesUsuarioRepository;
	}

	/**
	 * Gets the tc tipo usuario repository.
	 *
	 * @return the tcTipoUsuarioRepository
	 */
	public TcTipoUsuarioRepository getTcTipoUsuarioRepository() {
		return tcTipoUsuarioRepository;
	}

	/**
	 * Sets the tc tipo usuario repository.
	 *
	 * @param tcTipoUsuarioRepository
	 *            the tcTipoUsuarioRepository to set
	 */
	public void setTcTipoUsuarioRepository(TcTipoUsuarioRepository tcTipoUsuarioRepository) {
		this.tcTipoUsuarioRepository = tcTipoUsuarioRepository;
	}

	/**
	 * Gets the tc menu repository.
	 *
	 * @return the tcMenuRepository
	 */
	public TcMenuRepository getTcMenuRepository() {
		return tcMenuRepository;
	}

	/**
	 * Sets the tc menu repository.
	 *
	 * @param tcMenuRepository
	 *            the tcMenuRepository to set
	 */
	public void setTcMenuRepository(TcMenuRepository tcMenuRepository) {
		this.tcMenuRepository = tcMenuRepository;
	}

	/**
	 * Gets the menus.
	 *
	 * @return the menus
	 */
	public List<TcMenu> getMenus() {
		return menus;
	}

	/**
	 * Sets the menus.
	 *
	 * @param menus
	 *            the menus to set
	 */
	public void setMenus(List<TcMenu> menus) {
		this.menus = menus;
	}

	/**
	 * Generate user cad.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the tc usuario
	 */
	private TcUsuario generateUserCad(TcUsuario usuario) {
		String usercad = concatenateValues(String.valueOf(tcUsuarioRepository.count()),
				findCveInListTipoUsuarios(usuario.getTipoUsuario().getId()),
				findCveInListEntidades(usuario.getIdEntidadAdmini()),
				findCveInListLocalidades(usuario.getIdLocalidad()));

		LOGGER.debug(String.format("usercad :  %s", usercad));

		usuario.setUsuarioCtl(usercad);
		return usuario;
	}

	/**
	 * Concatenate values.
	 *
	 * @param consecutive
	 *            the consecutive
	 * @param cvetipoUsuario
	 *            the cvetipo usuario
	 * @param cveEntidad
	 *            the cve entidad
	 * @param cveLocalidad
	 *            the cve localidad
	 * @return the string
	 */
	private String concatenateValues(String consecutive, String cvetipoUsuario, String cveEntidad,
			String cveLocalidad) {
		LOGGER.debug(String.format(" concatenateValues [ %s , %s ,%s ,%s]   ", consecutive, cvetipoUsuario, cveEntidad,
				cveLocalidad));

		String aux = StringUtils.EMPTY;
		String aux2 = StringUtils.EMPTY;

		if (StringUtils.length(consecutive) < 4) {
			aux = StringUtils.leftPad(consecutive, 4, "0");
		} else {
			aux = consecutive;
		}

		if (StringUtils.length(cveLocalidad) < 3) {
			aux2 = StringUtils.leftPad(consecutive, 3, "0");
		} else {
			aux2 = cveLocalidad;
		}

		return StringUtils.join(aux, cvetipoUsuario, aux2, cveEntidad);
	}

	/**
	 * Find cve in list entidades.
	 *
	 * @param id
	 *            the id
	 * @return the string
	 */
	private String findCveInListEntidades(Long id) {
		for (TcEntidadAdministrativa entidad : entidades) {
			if (entidad.getId() == id) {
				return entidad.getClave();
			}
		}
		return StringUtils.EMPTY;
	}

	/** The Constant DEC_FORMATER. */
	private static final DecimalFormat DEC_FORMATER = new DecimalFormat("000");

	/**
	 * Find cve in list localidades.
	 *
	 * @param id
	 *            the id
	 * @return the string
	 */
	private String findCveInListLocalidades(Long id) {
		return DEC_FORMATER.format(id);
	}

	/**
	 * Find cve in list tipo usuarios.
	 *
	 * @param id
	 *            the id
	 * @return the string
	 */
	private String findCveInListTipoUsuarios(Long id) {
		for (TcTipoUsuario entidad : tipoUsuarios) {
			if (entidad.getId() == id) {
				return entidad.getClave();
			}
		}
		return StringUtils.EMPTY;
	}

	/**
	 * Transform menu items 2 map.
	 *
	 * @param menusList
	 *            the menus list
	 * @return the map
	 */
	@SuppressWarnings("unchecked")
	private Map<Long, String[]> transformMenuItems2Map(List<TcMenuItem> menusList) {

		final Map<Long, Set<String>> menusDelUser = new HashMap<>();

		for (TcMenuItem menuItem : menusList) {
			TcMenu tcMenu = menuItem.getMenu();

			Set<String> submenus = (Set<String>) MapUtils.getObject(menusDelUser, tcMenu.getId());
			if (submenus == null) {
				submenus = new HashSet<>();
				submenus.add(menuItem.getId().toString());
				menusDelUser.put(menuItem.getMenu().getId(), submenus);
			}

			submenus.add(menuItem.getId().toString());
		}

		return transformMapLis2MapArray(menusDelUser);
	}

	/**
	 * Transform map lis 2 map array.
	 *
	 * @param menusDelUser
	 *            the menus del user
	 * @return the map
	 */
	private Map<Long, String[]> transformMapLis2MapArray(Map<Long, Set<String>> menusDelUser) {
		final Map<Long, String[]> menus = new HashMap<>();
		for (Entry<Long, Set<String>> selMenus : menusDelUser.entrySet()) {
			Set<String> submenus = selMenus.getValue();
			String[] stockArr = submenus.toArray(new String[submenus.size()]);
			menus.put(selMenus.getKey(), stockArr);
		}

		return menus;
	}

	/**
	 * Convert map selects to list.
	 *
	 * @param submenusSel
	 *            the submenus sel
	 * @return the list
	 */
	private List<TcMenuItem> convertMapSelectsToList(Map<Long, String[]> submenusSel) {
		final List<TcMenuItem> menuItems = new ArrayList<TcMenuItem>();

		for (Entry<Long, String[]> selMenus : getSelectedMenuItems().entrySet()) {
			for (String id : selMenus.getValue()) {
				TcMenuItem tcMenuItem = tcMenuItemRepository.findOne(Long.valueOf(id));
				menuItems.add(tcMenuItem);
			}
		}
		return menuItems;
	}

	/**
	 * Gets the selected menu items.
	 *
	 * @return the selectedMenuItems
	 */
	public Map<Long, String[]> getSelectedMenuItems() {
		return selectedMenuItems;
	}

	/**
	 * Sets the selected menu items.
	 *
	 * @param selectedMenuItems
	 *            the selectedMenuItems to set
	 */
	public void setSelectedMenuItems(Map<Long, String[]> selectedMenuItems) {
		this.selectedMenuItems = selectedMenuItems;
	}

	/**
	 * Gets the tc menu item repository.
	 *
	 * @return the tcMenuItemRepository
	 */
	public TcMenuItemRepository getTcMenuItemRepository() {
		return tcMenuItemRepository;
	}

	/**
	 * Sets the tc menu item repository.
	 *
	 * @param tcMenuItemRepository
	 *            the tcMenuItemRepository to set
	 */
	public void setTcMenuItemRepository(TcMenuItemRepository tcMenuItemRepository) {
		this.tcMenuItemRepository = tcMenuItemRepository;
	}

	/**
	 * Gets the tc role repository.
	 *
	 * @return the tc role repository
	 */
	public TcRoleRepository getTcRoleRepository() {
		return tcRoleRepository;
	}

	/**
	 * Sets the tc role repository.
	 *
	 * @param tcRoleRepository
	 *            the new tc role repository
	 */
	public void setTcRoleRepository(TcRoleRepository tcRoleRepository) {
		this.tcRoleRepository = tcRoleRepository;
	}

	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public List<TcRole> getRoles() {
		return roles;
	}

	/**
	 * Sets the roles.
	 *
	 * @param roles
	 *            the new roles
	 */
	public void setRoles(List<TcRole> roles) {
		this.roles = roles;
	}

	/**
	 * Gets the mirror tc usuario.
	 *
	 * @return the mirror tc usuario
	 */
	public TcUsuario getMirrorTcUsuario() {
		return mirrorTcUsuario;
	}

	/**
	 * Sets the mirror tc usuario.
	 *
	 * @param mirrorTcUsuario
	 *            the new mirror tc usuario
	 */
	public void setMirrorTcUsuario(TcUsuario mirrorTcUsuario) {
		this.mirrorTcUsuario = mirrorTcUsuario;
	}

	/**
	 * Gets the password encoder.
	 *
	 * @return the password encoder
	 */
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	/**
	 * Sets the password encoder.
	 *
	 * @param passwordEncoder
	 *            the new password encoder
	 */
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * On row select.
	 *
	 * @param event
	 *            the event
	 */
	public void onRowSelect(SelectEvent event) {
		this.selectedTcUsuario = (TcUsuario) event.getObject();
		this.activeUpdate = !this.selectedTcUsuario.getHabilitado();
	}

	/**
	 * On row unselect.
	 *
	 * @param event
	 *            the event
	 */
	public void onRowUnselect(UnselectEvent event) {
		this.selectedTcUsuario = (TcUsuario) event.getObject();
		this.activeUpdate = Boolean.TRUE;
	}

	/**
	 * Gets the active update.
	 *
	 * @return the active update
	 */
	public Boolean getActiveUpdate() {
		return activeUpdate;
	}

	/**
	 * Sets the active update.
	 *
	 * @param activeUpdate
	 *            the new active update
	 */
	public void setActiveUpdate(Boolean activeUpdate) {
		this.activeUpdate = activeUpdate;
	}

	/**
	 * Gets the tc ente pub central repository.
	 *
	 * @return the tcEntePubCentralRepository
	 */
	public TcEntePubCentralRepository getTcEntePubCentralRepository() {
		return tcEntePubCentralRepository;
	}

	/**
	 * Sets the tc ente pub central repository.
	 *
	 * @param tcEntePubCentralRepository
	 *            the tcEntePubCentralRepository to set
	 */
	public void setTcEntePubCentralRepository(TcEntePubCentralRepository tcEntePubCentralRepository) {
		this.tcEntePubCentralRepository = tcEntePubCentralRepository;
	}

	/**
	 * Gets the entidades central.
	 *
	 * @return the entidadesCentral
	 */
	public List<TcEntePubCentral> getEntidadesCentral() {
		return entidadesCentral;
	}

	/**
	 * Sets the entidades central.
	 *
	 * @param entidadesCentral
	 *            the entidadesCentral to set
	 */
	public void setEntidadesCentral(List<TcEntePubCentral> entidadesCentral) {
		this.entidadesCentral = entidadesCentral;
	}

	/**
	 * Gets the catdep repository.
	 *
	 * @return the catdepRepository
	 */
	public CatdepRepository getCatdepRepository() {
		return catdepRepository;
	}

	/**
	 * Sets the catdep repository.
	 *
	 * @param catdepRepository
	 *            the catdepRepository to set
	 */
	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

	/**
	 * Gets the unidad admin central.
	 *
	 * @return the unidadAdminCentral
	 */
	public List<Catdep> getUnidadAdminCentral() {
		return unidadAdminCentral;
	}

	/**
	 * Sets the unidad admin central.
	 *
	 * @param unidadAdminCentral
	 *            the unidadAdminCentral to set
	 */
	public void setUnidadAdminCentral(List<Catdep> unidadAdminCentral) {
		this.unidadAdminCentral = unidadAdminCentral;
	}

	/**
	 * Choose sector.
	 */
	public void chooseSector() {
		if (SECTOR_CENTRAL == this.newTcUsuario.getTipoUsuario().getId()) {
			RequestContext.getCurrentInstance().execute("PF('usuarioNuevoDialog2').show()");
		} else {
			RequestContext.getCurrentInstance().execute("PF('usuarioNuevoDialog').show()");
		}
	}

	/**
	 * Choose close sector.
	 */
	public void chooseCloseSector() {
		if (SECTOR_CENTRAL == this.newTcUsuario.getTipoUsuario().getId()) {
			RequestContext.getCurrentInstance().execute("PF('usuarioNuevoDialog2').hide()");
		} else {
			RequestContext.getCurrentInstance().execute("PF('usuarioNuevoDialog').hide()");
		}

		RequestContext.getCurrentInstance().update(":form1:messagesEdit");
	}

	/**
	 * Choose close update sector.
	 */
	public void chooseCloseUpdateSector() {
		if (SECTOR_CENTRAL == this.selectedTcUsuario.getTipoUsuario().getId()) {
			RequestContext.getCurrentInstance().execute("PF('usuarioUpdateDialog2').hide()");
		} else {
			RequestContext.getCurrentInstance().execute("PF('usuarioUpdateDialog').hide()");
		}

		RequestContext.getCurrentInstance().update(":form1:messagesEdit");
	}

	/**
	 * Choose err msg sector.
	 *
	 * @return the string
	 */
	private String chooseErrMsgSector() {
		if (SECTOR_CENTRAL == this.newTcUsuario.getTipoUsuario().getId()) {
			return "errorMsg2";
		} else {
			return "errorMsg";
		}
	}

	/**
	 * Gets the menu.
	 *
	 * @return the menu
	 */
	public TreeNode getMenu() {
		return menu;
	}

	/**
	 * Sets the menu.
	 *
	 * @param menu
	 *            the menu to set
	 */
	public void setMenu(TreeNode menu) {
		this.menu = menu;
	}

	/**
	 * Gets the selected menus.
	 *
	 * @return the selectedMenus
	 */
	public TreeNode[] getSelectedMenus() {
		return selectedMenus;
	}

	/**
	 * Sets the selected menus.
	 *
	 * @param selectedMenus
	 *            the selectedMenus to set
	 */
	public void setSelectedMenus(TreeNode[] selectedMenus) {
		this.selectedMenus = selectedMenus;
	}

	/**
	 * Gets the tc menu niveles repository.
	 *
	 * @return the tc menu niveles repository
	 */
	public TcMenuNivelesRepository getTcMenuNivelesRepository() {
		return tcMenuNivelesRepository;
	}

	/**
	 * Sets the tc menu niveles repository.
	 *
	 * @param tcMenuNivelesRepository
	 *            the new tc menu niveles repository
	 */
	public void setTcMenuNivelesRepository(TcMenuNivelesRepository tcMenuNivelesRepository) {
		this.tcMenuNivelesRepository = tcMenuNivelesRepository;
	}

	/**
	 * Gets the tr usuarios 2 menu items repository.
	 *
	 * @return the tr usuarios 2 menu items repository
	 */
	public TrUsuarios2MenuItemsRepository getTrUsuarios2MenuItemsRepository() {
		return trUsuarios2MenuItemsRepository;
	}

	/**
	 * Sets the tr usuarios 2 menu items repository.
	 *
	 * @param trUsuarios2MenuItemsRepository
	 *            the new tr usuarios 2 menu items repository
	 */
	public void setTrUsuarios2MenuItemsRepository(TrUsuarios2MenuItemsRepository trUsuarios2MenuItemsRepository) {
		this.trUsuarios2MenuItemsRepository = trUsuarios2MenuItemsRepository;
	}

	/**
	 * Gets the arbol.
	 *
	 * @return the arbol
	 */
	public Tree getArbol() {
		return arbol;
	}

	/**
	 * Sets the arbol.
	 *
	 * @param arbol
	 *            the new arbol
	 */
	public void setArbol(Tree arbol) {
		this.arbol = arbol;
	}

	/**
	 * Gets the lista menu niveles.
	 *
	 * @return the lista menu niveles
	 */
	public List<TcMenuNiveles> getListaMenuNiveles() {
		return listaMenuNiveles;
	}

	/**
	 * Sets the lista menu niveles.
	 *
	 * @param listaMenuNiveles
	 *            the new lista menu niveles
	 */
	public void setListaMenuNiveles(List<TcMenuNiveles> listaMenuNiveles) {
		this.listaMenuNiveles = listaMenuNiveles;
	}

}
