package com.gem.sistema.web.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.datagrid.DataGrid;
import org.primefaces.event.data.PageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.dto.MetasFisicasDTO;
import com.gem.sistema.business.dto.MetasFisicasDetalleDTO;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.service.metas.MetasFisicasService;
import com.gem.sistema.util.UtilFront;

// TODO: Auto-generated Javadoc
/**
 * The Class MetasCapturaManualMB.
 */
@Component(value = "metasCapturaManualMB")
@ViewScoped
public class MetasCapturaManualMB extends AbstractMB {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MetasCapturaManualMB.class);

	/** The master list. */
	private List<MetasFisicasDTO> masterList = new ArrayList<MetasFisicasDTO>();

	/** The clv dependencias list. */
	private List<String> clvDependenciasList = new ArrayList<String>();

	/** The clv programa list. */
	private List<String> clvProgramaList = new ArrayList<String>();

	/** The clv fuente list. */
	private List<String> clvFuenteList = new ArrayList<String>();

	/** The captura desabilitado reset. */
	private boolean capturaDesabilitadoReset;

	/** The captura desabilitado adicionar. */
	private boolean capturaDesabilitadoAdicionar;

	/** The captura desabilitado borrar. */
	private boolean capturaDesabilitadoBorrar;

	/** The captura desabilitado cancelar. */
	private boolean capturaDesabilitadoCancelar;

	/** The captura visible salvar. */
	private boolean capturaVisibleSalvar;

	/** The captura visible modificar. */
	private boolean capturaVisibleModificar;

	/** The meta desabilitado reset. */
	private boolean metaDesabilitadoReset;

	/** The meta desabilitado adicionar. */
	private boolean metaDesabilitadoAdicionar;

	/** The meta desabilitado borrar. */
	private boolean metaDesabilitadoBorrar;

	/** The meta desabilitado cancelar. */
	private boolean metaDesabilitadoCancelar;

	/** The meta visible salvar. */
	private boolean metaVisibleSalvar;

	/** The meta visible modificar. */
	private boolean metaVisibleModificar;

	/** The captura deshabilitado. */
	private boolean capturaDeshabilitado;

	/** The meta deshabilitado. */
	private boolean metaDeshabilitado;

	/** The captura adicionar. */
	private boolean capturaAdicionar;

	/** The meta adicionar. */
	private boolean metaAdicionar;

	/** The clv dep bean. */
	private String clvDepBean;

	/** The clv prog bean. */
	private String clvProgBean;

	private String claveSearch;

	private String programaSearch;

	/** The metas fisicas service. */
	@Autowired
	private MetasFisicasService metasFisicasService;

	/** The deshabilitado editables. */
	private boolean deshabilitadoEditables;

	/** The temp editar. */
	private MetasFisicasDTO tempEditar;

	/** The temp editar detalle. */
	private MetasFisicasDetalleDTO tempEditarDetalle;

	/** The current page meta. */
	private int currentPageMeta = 0;

	/** The current page captura. */
	private int currentPageCaptura = 0;

	/** Repositorio de Catdep. */
	@ManagedProperty(value = "#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct MetasCapturaManualMB ");
		claveSearch = StringUtils.EMPTY;
		programaSearch = StringUtils.EMPTY;
	}

	/**
	 * metodo que carga la pantalla inicial desde la llamada del menu.
	 */
	public void inicializar() {
		LOGGER.info("*** Inicializando Metas Fisicas Captura Manual");
		this.limpiarPantalla();
		this.inicializarCatalogos();
		this.estadoInicialBotones();
		this.cargarDatos();
		LOGGER.info("XXXXXX");
	}

	public void convertUpper() {
		if (StringUtils.isNotEmpty(claveSearch))
			claveSearch = claveSearch.toUpperCase();

		if (StringUtils.isNotEmpty(programaSearch))
			programaSearch = programaSearch.toUpperCase();
	}

	public void search() {

		masterList = metasFisicasService.getEncabezados(this.getUserDetails().getIdSector(), claveSearch,
				programaSearch);

		this.currentPageCaptura = 0;
		this.currentPageMeta = 0;

	}

	public void cleanSearch() {

		this.claveSearch = StringUtils.EMPTY;
		this.programaSearch = StringUtils.EMPTY;

		System.out.println("");
//		masterList = metasFisicasService.getEncabezados(this.getUserDetails().getIdSector(), claveSearch,
//				programaSearch);
		this.inicializarPantalla();

	}

	/**
	 * Inicializar pantalla.
	 */
	public void inicializarPantalla() {
		LOGGER.info("*** Inicializando Metas Fisicas Captura Manual");
//		currentPageMeta = 0;
//		currentPageCaptura = 0;
		this.limpiarPantalla();
		this.inicializarCatalogos();
		this.estadoInicialBotones();
		this.cargarDatos();
		LOGGER.info("XXXXXX");
	}

	/**
	 * Limpiar pantalla.
	 */
	private void limpiarPantalla() {
		masterList.clear();
	}

	/**
	 * Cargar datos.
	 */
	private void cargarDatos() {
		masterList = metasFisicasService.getEncabezados(this.getUserDetails().getIdSector(), claveSearch,
				programaSearch);
		if (masterList.isEmpty()) {
			MetasFisicasDTO MetasFisicasDTO = new MetasFisicasDTO();
			masterList.add(MetasFisicasDTO);
			this.setCapturaVisibleModificar(false);
			this.setCapturaVisibleSalvar(false);
			this.setCapturaDesabilitadoReset(true);
			this.setCapturaDesabilitadoAdicionar(false);
			this.setCapturaDesabilitadoBorrar(true);
			this.setCapturaDesabilitadoCancelar(true);
			this.setMetaVisibleModificar(false);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoAdicionar(true);
			this.setMetaDesabilitadoBorrar(true);
			this.setMetaDesabilitadoCancelar(true);
			this.setCapturaDeshabilitado(true);
			this.setMetaDeshabilitado(true);
			this.setDeshabilitadoEditables(false);
		}

		for (MetasFisicasDTO m : masterList) {
			if (m.getClvnep() != null) {
				clvProgramaList.add(m.getClvnep());
			}
			if (m.getClvfuen() != null) {
				clvFuenteList.add(m.getClvfuen());
			}
			if (m.getDetalle() == null || m.getDetalle().size() == 0) {// no tiene detalle
				List<MetasFisicasDetalleDTO> detFake = new ArrayList<MetasFisicasDetalleDTO>();
				MetasFisicasDetalleDTO d = new MetasFisicasDetalleDTO();
				d.setClvmet(null);
				detFake.add(d);
				m.setDetalle(detFake);
			}
		}
		if (masterList.get(currentPageCaptura).getDetalle() == null
				|| masterList.get(currentPageCaptura).getDetalle().size() == 0
				|| masterList.get(currentPageCaptura).getDetalle().get(0).getClvmet() == null) {// no tiene detalle

			this.setMetaVisibleModificar(false);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoAdicionar(false);
			this.setMetaDesabilitadoBorrar(true);
			this.setMetaDesabilitadoCancelar(true);
			this.setMetaDeshabilitado(true);
		} else {
			this.setMetaVisibleModificar(true);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoAdicionar(false);
			this.setMetaDesabilitadoBorrar(false);
			this.setMetaDesabilitadoCancelar(true);
			this.setMetaDeshabilitado(true);
		}

	}

	/**
	 * Inicializar catalogos.
	 */
	private void inicializarCatalogos() {
		clvDependenciasList.clear();
		clvProgramaList.clear();
		clvFuenteList.clear();
		Integer sector = this.getUserDetails().getIdSector();
		for (Xcatpro xcat : metasFisicasService.listaDependencias(sector)) {
			if (!clvDependenciasList.contains(xcat.getClvdep()))
				clvDependenciasList.add(xcat.getClvdep());
		}
	}

	/**
	 * Filtra programa.
	 *
	 * @param row the row
	 */
	// busca la lista de programas de esa dependencia y su descripcion
	public void filtraPrograma(Integer row) {
		LOGGER.info("*** Filtrando programas");
		clvProgramaList.clear();
		clvFuenteList.clear();
		List<Xcatpro> listaPro = metasFisicasService.filtraPrograma(masterList.get(row).getClvDep());
		for (Xcatpro dep : listaPro) {
			if (dep.getSectorid().equals(this.getUserDetails().getIdSector())) {
				if (!clvProgramaList.contains(dep.getClvfun()))
					clvProgramaList.add(dep.getClvfun());
			}
		}
		masterList.get(row).setNompro(metasFisicasService.llenaDescNomproNewReq(masterList.get(row).getClvDep()));
	}

	/**
	 * Filtra fuente.
	 *
	 * @param row the row
	 */
	public void filtraFuente(Integer row) {
		LOGGER.info(
				"*** Filtrando fuentes " + masterList.get(row).getClvDep() + " - " + masterList.get(row).getClvnep());
		clvFuenteList.clear();
		List<Xcatpro> listaFuen = metasFisicasService.filtraFuente(masterList.get(row).getClvDep(),
				masterList.get(row).getClvnep());

		for (Xcatpro dep : listaFuen) {
			clvFuenteList.add(dep.getClvfin());
		}
		LOGGER.info("*** Recuperando descripcion programa");
		masterList.get(row).setDesProyecto(metasFisicasService.llenaDescPrograma(masterList.get(row).getClvnep()));
	}

	/**
	 * Filtra fuente desc.
	 *
	 * @param row the row
	 */
	public void filtraFuenteDesc(Integer row) {
		masterList.get(row).setDesfuen(metasFisicasService.llenaDescFuente(masterList.get(row).getClvfuen(),
				this.getUserDetails().getIdSector()));
	}

	/**
	 * Valida clvnep.
	 *
	 * @param row the row
	 */
	public void validaClvnep(Integer row) {
		if (!clvProgramaList.contains(masterList.get(row).getClvnep())) {
			masterList.get(row).setClvnep(null);
		} else {
			this.filtraPrograma(row);
		}
	}

	/**
	 * Valida clvfuen.
	 *
	 * @param row the row
	 */
	public void validaClvfuen(Integer row) {
		if (!clvProgramaList.contains(masterList.get(row).getClvfuen())) {
			masterList.get(row).setClvfuen(null);
		} else {
			this.filtraFuente(row);
		}
	}

	/**
	 * Estado inicial botones.
	 */
	private void estadoInicialBotones() {
		this.metaAdicionar = Boolean.FALSE;
		this.capturaAdicionar = Boolean.FALSE;
		masterList = metasFisicasService.getEncabezados(this.getUserDetails().getIdSector(), claveSearch,
				programaSearch);
		if (!masterList.isEmpty()) {// tiene registros en la base de datos
			this.setCapturaVisibleModificar(true);
			this.setCapturaVisibleSalvar(false);
			this.setCapturaDesabilitadoReset(true);
			this.setCapturaDesabilitadoAdicionar(false);
			this.setCapturaDesabilitadoBorrar(false);
			this.setCapturaDesabilitadoCancelar(true);
			this.setMetaVisibleModificar(true);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoAdicionar(false);
			this.setMetaDesabilitadoBorrar(false);
			this.setMetaDesabilitadoCancelar(true);
			this.setCapturaDeshabilitado(true);
			this.setMetaDeshabilitado(true);
			this.setDeshabilitadoEditables(true);
		} else {
			this.setCapturaVisibleModificar(true);
			this.setCapturaVisibleSalvar(false);
			this.setCapturaDesabilitadoReset(true);
			this.setCapturaDesabilitadoAdicionar(false);
			this.setCapturaDesabilitadoBorrar(false);
			this.setCapturaDesabilitadoCancelar(true);
			this.setMetaVisibleModificar(true);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoAdicionar(false);
			this.setMetaDesabilitadoBorrar(false);
			this.setMetaDesabilitadoCancelar(true);
			this.setCapturaDeshabilitado(true);
			this.setMetaDeshabilitado(true);
			this.setDeshabilitadoEditables(false);
		}
	}

	/**
	 * Estado inicial botones meta.
	 */
	private void estadoInicialBotonesMeta() {
		if (masterList.get(currentPageCaptura).getDetalle() == null
				|| masterList.get(currentPageCaptura).getDetalle().size() == 0
				|| masterList.get(currentPageCaptura).getDetalle().get(currentPageMeta).getClvmet() == null) {// no
																												// tiene
																												// detalle

			this.setMetaVisibleModificar(false);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoAdicionar(false);
			this.setMetaDesabilitadoBorrar(true);
			this.setMetaDesabilitadoCancelar(true);
			this.setMetaDeshabilitado(true);
		} else {
			this.setMetaVisibleModificar(true);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoAdicionar(false);
			this.setMetaDesabilitadoBorrar(false);
			this.setMetaDesabilitadoCancelar(true);
			this.setMetaDeshabilitado(true);
		}
	}

	/**
	 * Adicionar captura.
	 *
	 * @param row the row
	 */
	public void adicionarCaptura(Integer row) {
		this.setCapturaVisibleSalvar(true);
		this.setCapturaVisibleModificar(false);
		this.setCapturaDesabilitadoAdicionar(true);
		this.setCapturaDesabilitadoBorrar(true);
		this.setCapturaDesabilitadoCancelar(false);
		this.setCapturaDesabilitadoReset(false);

		capturaAdicionar = true;
		if (metasFisicasService.getEncabezados(this.getUserDetails().getIdSector(), claveSearch, programaSearch)
				.isEmpty()) {
			masterList.clear();
			MetasFisicasDTO MetasFisicasDTO = new MetasFisicasDTO();
			masterList.add(MetasFisicasDTO);
			List<MetasFisicasDetalleDTO> detFake = new ArrayList<MetasFisicasDetalleDTO>();
			MetasFisicasDetalleDTO d = new MetasFisicasDetalleDTO();
			d.setClvmet(null);
			detFake.add(d);
			masterList.get(row).setDetalle(detFake);
		} else {
			MetasFisicasDTO MetasFisicasDTO = new MetasFisicasDTO();
			masterList.add(MetasFisicasDTO);
		}
		this.setCapturaDeshabilitado(false);
		this.setDeshabilitadoEditables(false);
	}

	/**
	 * Modificar captura.
	 *
	 * @param row the row
	 */
	public void modificarCaptura(Integer row) {
		this.tempEditar = new MetasFisicasDTO();
		this.tempEditar.setObjetivos(masterList.get(row).getObjetivos());
		if (masterList.get(row).getDetalle().size() > 0 && masterList.get(row).getDetalle().get(0).getId() != null) {// si
																														// tiene
																														// detalles
																														// no
																														// puede
																														// modificar
			this.setDeshabilitadoEditables(false);
			this.setCapturaDeshabilitado(true);
		} else {
			this.setDeshabilitadoEditables(false);
			this.setCapturaDeshabilitado(false);
		}
		this.setCapturaVisibleSalvar(true);
		this.setCapturaVisibleModificar(false);
		this.setCapturaDesabilitadoAdicionar(true);
		this.setCapturaDesabilitadoBorrar(true);
		this.setCapturaDesabilitadoReset(false);
		this.setCapturaDesabilitadoCancelar(false);
		filtraPrograma(row);
		filtraFuente(row);
		filtraFuenteDesc(row);
	}

	/**
	 * Salvar captura.
	 *
	 * @param row the row
	 */
	public void salvarCaptura(Integer row) {
		Calendar c = Calendar.getInstance();

		if (masterList.get(row).getClvDep() == null || masterList.get(row).getClvnep() == null
				|| masterList.get(row).getClvfuen() == null) {
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
					"Los campos Dependencia Ejecutora, FN/Fun/SubF/Prog/SubProg/Proy y Fuente de Financiamiento son requeridos",
					"Los campos Dependencia Ejecutora, FN/Fun/SubF/Prog/SubProg/Proy y Fuente de Financiamiento son requeridos");
			return;
		}

		try {
			MetasFisicasDTO indcap = new MetasFisicasDTO();
			indcap.setClaveDep(1);
			indcap.setAnioCap(c.get(Calendar.YEAR));
			indcap.setNumVer(1);
			indcap.setClvDep(masterList.get(row).getClvDep());
			indcap.setClvnep(masterList.get(row).getClvnep());
			indcap.setClvfuen(masterList.get(row).getClvfuen());
			indcap.setObjetivos(masterList.get(row).getObjetivos());
			indcap.setUserId(this.getUserDetails().getUsername());
			indcap.setIdsector(this.getUserDetails().getIdSector());
			if (masterList.get(row).getId() != null) {
				indcap.setId(masterList.get(row).getId());
			} else {
				MetasFisicasDTO det = metasFisicasService.findByClvdepClvNepClvFuen(masterList.get(row).getClvDep(),
						masterList.get(row).getClvnep(), masterList.get(row).getClvfuen());
				if (det != null) {
					UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
							"Ya existe en encabezado seleccionado", "Ya existe en encabezado seleccionado");
					return;
				}
			}

			metasFisicasService.saveCabecero(indcap);

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Encabezado guardado correctamente"));

			this.inicializar();
		} catch (Exception e) {
			e.printStackTrace();
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
					"Ocurrió un error al salvar el Encabezado ", "Ocurrió un error al salvar el Encabezado ");
		}
	}

	/**
	 * Reset captura.
	 *
	 * @param row the row
	 */
	public void resetCaptura(Integer row) {
		List<MetasFisicasDTO> metaListOri = new ArrayList<MetasFisicasDTO>();
		metaListOri = metasFisicasService.getEncabezados(this.getUserDetails().getIdSector(), claveSearch,
				programaSearch);
//		MetasFisicasDTO temp = masterList.get(row);
//		temp = metasFisicasService.findByClvdepClvNepClvFuen(temp.getClvDep(), temp.getClvnep(), temp.getClvfuen());//para saber si esta en la base
		if (capturaAdicionar) {
			this.masterList.set(row, new MetasFisicasDTO());
			List<MetasFisicasDetalleDTO> detFake = new ArrayList<MetasFisicasDetalleDTO>();
			MetasFisicasDetalleDTO d = new MetasFisicasDetalleDTO();
			d.setClvmet(null);
			detFake.add(d);
			masterList.get(row).setDetalle(detFake);
		} else {
			this.masterList.set(row, metaListOri.get(row));

			for (MetasFisicasDTO m : masterList) {
				if (m.getClvnep() != null) {
					clvProgramaList.add(m.getClvnep());
				}
				if (m.getClvfuen() != null) {
					clvFuenteList.add(m.getClvfuen());
				}
			}
			masterList.get(row).setNompro(metasFisicasService.llenaDescNomproNewReq(metaListOri.get(row).getClvDep()));
			if (metaListOri.get(row).getDetalle() == null || metaListOri.get(row).getDetalle().size() == 0) {// no tiene
																												// detalle
				List<MetasFisicasDetalleDTO> detFake = new ArrayList<MetasFisicasDetalleDTO>();
				MetasFisicasDetalleDTO d = new MetasFisicasDetalleDTO();
				d.setClvmet(null);
				detFake.add(d);
				masterList.get(row).setDetalle(detFake);

				this.setMetaVisibleModificar(false);
				this.setMetaVisibleSalvar(false);
				this.setMetaDesabilitadoReset(true);
				this.setMetaDesabilitadoAdicionar(false);
				this.setMetaDesabilitadoBorrar(true);
				this.setMetaDesabilitadoCancelar(true);
				this.setMetaDeshabilitado(true);
			}
		}
	}

	/**
	 * Ubica el paginador de la tabla de encabezados en la pagina indicada.
	 *
	 * @param pagina ndice de la pagina a mostrar
	 */
	private void irAPaginaEncabezado(int pagina) {
		if (pagina >= masterList.size()) {
			pagina = masterList.size() - 1;
		}
		irAPagina("formMetCaptura:metTable", pagina);
	}

	/**
	 * Ubica el paginador de la tabla de detalles en la pagina indicada.
	 *
	 * @param pagina ndice de la pagina a mostrar
	 */
	private void irAPaginaDetalle(int pagina) {
//		if (pagina >= detallesList.size()) {
//			pagina = detallesList.size() - 1;
//		}
//		irAPagina("formMetCaptura:detailTable", pagina);
	}

	/**
	 * Ubica el paginador de la tabla especificada en la pagina indicada.
	 *
	 * @param tabla  Nombre de la tabla a redireccionar
	 * @param pagina ndice de la pagina a mostrar
	 */
	private void irAPagina(String tabla, int pagina) {
		final DataGrid d = (DataGrid) FacesContext.getCurrentInstance().getViewRoot().findComponent(tabla);
		d.setFirst(pagina);
//		if (tabla.equals("formIndCaptura:indTable")) {
//			masterRowSelected = pagina;
//		} else {
//			detalleRowSelected = pagina;
//		}
	}

	/**
	 * Borrar captura.
	 *
	 * @param row the row
	 */
	public void borrarCaptura(Integer row) {
		try {
			if (masterList.get(row).getDetalle().size() > 0
					&& masterList.get(row).getDetalle().get(0).getId() != null) {
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
						"Existen Codigos, no se puede eliminar", "Existen Codigos, no se puede eliminar");
			} else {
				metasFisicasService.deleteCabecero(masterList.get(row).getId().longValue());

				this.setCapturaDeshabilitado(true);
				this.setDeshabilitadoEditables(true);
				if (currentPageCaptura > 0) {
					currentPageCaptura--;
				}

				this.inicializar();

				this.irAPaginaEncabezado(row);

				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Cabecero borrada correctamente"));
			}
		} catch (Exception e) {
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
					"Ocurrió un error al borrar el Encabezado ", "Ocurrió un error al borrar el Encabezado ");
		}
	}

	/**
	 * Cancelar captura.
	 *
	 * @param row the row
	 */
	public void cancelarCaptura(Integer row) {
		if (capturaAdicionar) {// es registro nuevo
			capturaAdicionar = false;
			if (currentPageCaptura == 0) {// si es unico registro lo borra y lo pone nuevo
				masterList.clear();
				MetasFisicasDTO MetasFisicasDTO = new MetasFisicasDTO();
				masterList.add(MetasFisicasDTO);
				List<MetasFisicasDetalleDTO> detFake = new ArrayList<MetasFisicasDetalleDTO>();
				MetasFisicasDetalleDTO d = new MetasFisicasDetalleDTO();
				d.setClvmet(null);
				detFake.add(d);
				masterList.get(row).setDetalle(detFake);

				this.setCapturaVisibleSalvar(false);
				this.setCapturaVisibleModificar(false);
				this.setCapturaDesabilitadoAdicionar(false);
				this.setCapturaDesabilitadoBorrar(true);
				this.setCapturaDesabilitadoCancelar(true);
				this.setCapturaDesabilitadoReset(true);
				this.setCapturaDeshabilitado(true);
				this.setDeshabilitadoEditables(true);
			} else {
				masterList.remove(currentPageCaptura);
				this.setCapturaVisibleSalvar(false);
				this.setCapturaVisibleModificar(true);
				this.setCapturaDesabilitadoAdicionar(false);
				this.setCapturaDesabilitadoBorrar(false);
				this.setCapturaDesabilitadoCancelar(true);
				this.setCapturaDesabilitadoReset(true);
				this.setCapturaDeshabilitado(true);
				this.setDeshabilitadoEditables(true);
			}
			if (currentPageCaptura > 0) {
				currentPageCaptura--;
			}
		} else {// es registro modificado
			this.resetCaptura(currentPageCaptura);
			this.setCapturaVisibleSalvar(false);
			this.setCapturaVisibleModificar(true);
			this.setCapturaDesabilitadoAdicionar(false);
			this.setCapturaDesabilitadoBorrar(false);
			this.setCapturaDesabilitadoCancelar(true);
			this.setCapturaDesabilitadoReset(true);
			this.setCapturaDeshabilitado(true);
			this.setDeshabilitadoEditables(true);
		}
		this.setCapturaDeshabilitado(true);// always
		this.setDeshabilitadoEditables(true);
//		this.estadoInicialBotones();
	}

	/**
	 * METAS *.
	 *
	 * @param rowCaptura the row captura
	 */
	public void adicionarMeta(Integer rowCaptura) {
		this.setMetaVisibleSalvar(Boolean.TRUE);
		this.setMetaVisibleModificar(Boolean.FALSE);
		this.setMetaDesabilitadoAdicionar(Boolean.TRUE);
		this.setMetaDesabilitadoBorrar(Boolean.TRUE);
		this.setMetaDesabilitadoCancelar(Boolean.FALSE);
		this.setMetaDesabilitadoReset(Boolean.FALSE);
		metaAdicionar = Boolean.TRUE;

		if (masterList.get(rowCaptura).getDetalle().size() == 1) {
			if (masterList.get(rowCaptura).getDetalle().get(0).getId() == null
					|| masterList.get(rowCaptura).getDetalle().get(0).getId().equals(0)) {
				masterList.get(rowCaptura).getDetalle().clear();
			}
		}
		masterList.get(rowCaptura).getDetalle().add(new MetasFisicasDetalleDTO());

		this.setMetaDeshabilitado(Boolean.FALSE);
	}

	/**
	 * Modificar meta.
	 */
	public void modificarMeta() {
		this.setMetaVisibleSalvar(true);
		this.setMetaVisibleModificar(false);
		this.setMetaDesabilitadoAdicionar(true);
		this.setMetaDesabilitadoBorrar(true);
		this.setMetaDesabilitadoReset(false);
		this.setMetaDesabilitadoCancelar(false);
		this.setMetaDeshabilitado(false);
		metaAdicionar = Boolean.FALSE;
	}

	/**
	 * Salvar meta.
	 *
	 * @param rowCaptura the row captura
	 * @param rowDetail  the row detail
	 */
	public void salvarMeta(Integer rowCaptura, Integer rowDetail) {// mandar row del cabecero y de la meta
		LOGGER.info("master " + rowCaptura + " - detail " + rowDetail);

		try {
			MetasFisicasDetalleDTO det = metasFisicasService.findByClvdepClvNepClvFuenClvMet(
					masterList.get(rowCaptura).getClvDep(), masterList.get(rowCaptura).getClvnep(),
					masterList.get(rowCaptura).getClvfuen(),
					masterList.get(rowCaptura).getDetalle().get(rowDetail).getClvmet());

			MetasFisicasDetalleDTO dto = masterList.get(rowCaptura).getDetalle().get(rowDetail);
			dto.setClvdep(masterList.get(rowCaptura).getClvDep());
			dto.setClvnep(masterList.get(rowCaptura).getClvnep());
			dto.setClvfuen(masterList.get(rowCaptura).getClvfuen());

			if (metaAdicionar) {
				if (det != null) {
					UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Ya existe en código seleccionado",
							"Ya existe en código seleccionado");
					return;
				}
			}

			if (dto.getClvmet() == null) {
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "El campo codigo es requerido",
						"El campo codigo es requerido");
				return;
			}

			dto.setUserid(this.getUserDetails().getUsername());
			dto.setIdsector(this.getUserDetails().getIdSector());
			dto = metasFisicasService.saveMetas(dto);
			masterList.get(rowCaptura).getDetalle().get(rowDetail).setId(dto.getId());
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Meta guardada correctamente"));
			this.estadoInicialBotones();

		} catch (Exception e) {
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al borrar el Código ",
					"Ocurrió un error al borrar el Código ");
		}
	}

	/**
	 * Reset meta.
	 *
	 * @param rowCaptura the row captura
	 * @param rowDetail  the row detail
	 */
	public void resetMeta(Integer rowCaptura, Integer rowDetail) {
//		this.setMetaDeshabilitado(true);//always
//		this.estadoInicialBotones();

		if (masterList.get(rowCaptura).getDetalle().size() == 1
				&& masterList.get(rowCaptura).getDetalle().get(rowDetail).getId() == null) {
			masterList.get(rowCaptura).getDetalle().clear();
			masterList.get(rowCaptura).getDetalle().add(new MetasFisicasDetalleDTO());
		} else {
			if (metaAdicionar) {
//				masterList.get(rowCaptura).getDetalle().remove(masterList.get(rowCaptura).getDetalle().size()-1);
				masterList.get(rowCaptura).getDetalle().set(masterList.get(rowCaptura).getDetalle().size() - 1,
						new MetasFisicasDetalleDTO());
			} else {
				MetasFisicasDetalleDTO det = metasFisicasService.findByClvdepClvNepClvFuenClvMet(
						masterList.get(rowCaptura).getClvDep(), masterList.get(rowCaptura).getClvnep(),
						masterList.get(rowCaptura).getClvfuen(),
						masterList.get(rowCaptura).getDetalle().get(rowDetail).getClvmet());

				masterList.get(rowCaptura).getDetalle().set(rowDetail, det);
			}
		}

	}

	/**
	 * Borrar meta.
	 *
	 * @param rowCaptura the row captura
	 * @param rowDetail  the row detail
	 */
	public void borrarMeta(Integer rowCaptura, Integer rowDetail) {
		try {
			metasFisicasService
					.deleteMeta((masterList.get(rowCaptura).getDetalle().get(rowDetail).getId().longValue()));

			this.setMetaDeshabilitado(true);
			this.masterList.clear();
			if (currentPageMeta > 0) {
				currentPageMeta--;
			}
			this.cargarDatos();

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Meta borrada correctamente"));
		} catch (Exception e) {
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al borrar el Código ",
					"Ocurrió un error al borrar el Código ");
		}
	}

	/**
	 * Cancelar meta.
	 *
	 * @param rowCaptura the row captura
	 * @param rowDetail  the row detail
	 */
	public void cancelarMeta(Integer rowCaptura, Integer rowDetail) {
		List<MetasFisicasDTO> metaListAux = new ArrayList<MetasFisicasDTO>();
		metaListAux = metasFisicasService.getEncabezados(this.getUserDetails().getIdSector(), claveSearch,
				programaSearch);
		if (metaAdicionar) {
			if (metaListAux.get(currentPageCaptura).getDetalle().isEmpty()) {// si NO tiene registros en la base borralo
				masterList.get(currentPageCaptura).getDetalle()
						.remove(masterList.get(currentPageCaptura).getDetalle().size() - 1);
				if (currentPageMeta == 0) {
					masterList.get(rowCaptura).getDetalle().add(new MetasFisicasDetalleDTO());
				}
				if (currentPageMeta > 0) {
					currentPageMeta--;
				}
			} else {// si es adicionar y tiene registro, borrar el adicionado y agregar el rowDetail
//				MetasFisicasDetalleDTO det = metasFisicasService.findByClvdepClvNepClvFuenClvMet(
//						masterList.get(rowCaptura).getClvDep(), masterList.get(rowCaptura).getClvnep(),
//						masterList.get(rowCaptura).getClvfuen(),
//						masterList.get(rowCaptura).getDetalle().get(rowDetail).getClvmet());
				masterList.get(currentPageCaptura).getDetalle()
						.remove(masterList.get(currentPageCaptura).getDetalle().size() - 1);
				masterList.get(currentPageCaptura).getDetalle().set(rowDetail - 1,
						masterList.get(currentPageCaptura).getDetalle().get(rowDetail - 1));
				currentPageMeta--;// se disminuye 1 por que si tiene registro en base y es agregar
			}
		} else {
			MetasFisicasDetalleDTO det = metasFisicasService.findByClvdepClvNepClvFuenClvMet(
					masterList.get(rowCaptura).getClvDep(), masterList.get(rowCaptura).getClvnep(),
					masterList.get(rowCaptura).getClvfuen(),
					masterList.get(rowCaptura).getDetalle().get(rowDetail).getClvmet());
			if (det == null) {
				masterList.get(rowCaptura).getDetalle().set(rowDetail, new MetasFisicasDetalleDTO());
			} else {
				masterList.get(rowCaptura).getDetalle().set(rowDetail, det);
			}

		}
		this.setMetaDeshabilitado(true);// always
		metaAdicionar = Boolean.FALSE;
		this.estadoInicialBotonesMeta();
	}

	/**
	 * Cambiar pagina captura.
	 *
	 * @param event the event
	 */
	public void cambiarPaginaCaptura(PageEvent event) {
		currentPageCaptura = event.getPage();
		if (masterList.get(currentPageCaptura).getDetalle() == null
				|| masterList.get(currentPageCaptura).getDetalle().size() == 0
				|| masterList.get(currentPageCaptura).getDetalle().get(0).getClvmet() == null) {// no tiene detalle
			List<MetasFisicasDetalleDTO> detFake = new ArrayList<MetasFisicasDetalleDTO>();
			MetasFisicasDetalleDTO d = new MetasFisicasDetalleDTO();
			d.setClvmet(null);
			detFake.add(d);
			masterList.get(currentPageCaptura).setDetalle(detFake);
			this.setMetaVisibleModificar(false);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoAdicionar(false);
			this.setMetaDesabilitadoBorrar(true);
			this.setMetaDesabilitadoCancelar(true);
			this.setMetaDeshabilitado(true);
		} else {
			this.setMetaVisibleModificar(true);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoAdicionar(false);
			this.setMetaDesabilitadoBorrar(false);
			this.setMetaDesabilitadoCancelar(true);
			this.setMetaDeshabilitado(true);
		}
	}

	/**
	 * Cambiar pagina meta.
	 *
	 * @param event the event
	 */
	public void cambiarPaginaMeta(PageEvent event) {
		currentPageMeta = event.getPage();
		masterList = metasFisicasService.getEncabezados(this.getUserDetails().getIdSector(), claveSearch,
				programaSearch);
		if (masterList.get(currentPageCaptura).getDetalle() == null
				|| masterList.get(currentPageCaptura).getDetalle().size() == 0) {// no tiene detalle
			this.setMetaVisibleModificar(false);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoAdicionar(false);
			this.setMetaDesabilitadoBorrar(true);
			this.setMetaDesabilitadoCancelar(true);
			this.setMetaDeshabilitado(true);
		} else {
			if (metaAdicionar) {
				masterList.get(currentPageCaptura).getDetalle().add(new MetasFisicasDetalleDTO());
				this.setMetaVisibleSalvar(Boolean.TRUE);
				this.setMetaVisibleModificar(Boolean.FALSE);
				this.setMetaDesabilitadoAdicionar(Boolean.TRUE);
				this.setMetaDesabilitadoBorrar(Boolean.TRUE);
				this.setMetaDesabilitadoCancelar(Boolean.FALSE);
				this.setMetaDesabilitadoReset(Boolean.FALSE);
			} else {
				this.setMetaVisibleModificar(true);
				this.setMetaVisibleSalvar(false);
				this.setMetaDesabilitadoReset(true);
				this.setMetaDesabilitadoAdicionar(false);
				this.setMetaDesabilitadoBorrar(false);
				this.setMetaDesabilitadoCancelar(true);
				this.setMetaDeshabilitado(true);
			}

		}

	}

	/**
	 * Gets the master list.
	 *
	 * @return the master list
	 */
	public List<MetasFisicasDTO> getMasterList() {
		return masterList;
	}

	/**
	 * Sets the master list.
	 *
	 * @param masterList the new master list
	 */
	public void setMasterList(List<MetasFisicasDTO> masterList) {
		this.masterList = masterList;
	}

	/**
	 * Gets the clv dependencias list.
	 *
	 * @return the clv dependencias list
	 */
	public List<String> getClvDependenciasList() {
		return clvDependenciasList;
	}

	/**
	 * Sets the clv dependencias list.
	 *
	 * @param clvDependenciasList the new clv dependencias list
	 */
	public void setClvDependenciasList(List<String> clvDependenciasList) {
		this.clvDependenciasList = clvDependenciasList;
	}

	/**
	 * Gets the clv programa list.
	 *
	 * @return the clv programa list
	 */
	public List<String> getClvProgramaList() {
		return clvProgramaList;
	}

	/**
	 * Sets the clv programa list.
	 *
	 * @param clvProgramaList the new clv programa list
	 */
	public void setClvProgramaList(List<String> clvProgramaList) {
		this.clvProgramaList = clvProgramaList;
	}

	/**
	 * Checks if is captura desabilitado reset.
	 *
	 * @return true, if is captura desabilitado reset
	 */
	public boolean isCapturaDesabilitadoReset() {
		return capturaDesabilitadoReset;
	}

	/**
	 * Sets the captura desabilitado reset.
	 *
	 * @param capturaDesabilitadoReset the new captura desabilitado reset
	 */
	public void setCapturaDesabilitadoReset(boolean capturaDesabilitadoReset) {
		this.capturaDesabilitadoReset = capturaDesabilitadoReset;
	}

	/**
	 * Checks if is captura desabilitado adicionar.
	 *
	 * @return true, if is captura desabilitado adicionar
	 */
	public boolean isCapturaDesabilitadoAdicionar() {
		return capturaDesabilitadoAdicionar;
	}

	/**
	 * Sets the captura desabilitado adicionar.
	 *
	 * @param capturaDesabilitadoAdicionar the new captura desabilitado adicionar
	 */
	public void setCapturaDesabilitadoAdicionar(boolean capturaDesabilitadoAdicionar) {
		this.capturaDesabilitadoAdicionar = capturaDesabilitadoAdicionar;
	}

	/**
	 * Checks if is captura desabilitado borrar.
	 *
	 * @return true, if is captura desabilitado borrar
	 */
	public boolean isCapturaDesabilitadoBorrar() {
		return capturaDesabilitadoBorrar;
	}

	/**
	 * Sets the captura desabilitado borrar.
	 *
	 * @param capturaDesabilitadoBorrar the new captura desabilitado borrar
	 */
	public void setCapturaDesabilitadoBorrar(boolean capturaDesabilitadoBorrar) {
		this.capturaDesabilitadoBorrar = capturaDesabilitadoBorrar;
	}

	/**
	 * Checks if is captura desabilitado cancelar.
	 *
	 * @return true, if is captura desabilitado cancelar
	 */
	public boolean isCapturaDesabilitadoCancelar() {
		return capturaDesabilitadoCancelar;
	}

	/**
	 * Sets the captura desabilitado cancelar.
	 *
	 * @param capturaDesabilitadoCancelar the new captura desabilitado cancelar
	 */
	public void setCapturaDesabilitadoCancelar(boolean capturaDesabilitadoCancelar) {
		this.capturaDesabilitadoCancelar = capturaDesabilitadoCancelar;
	}

	/**
	 * Checks if is captura visible salvar.
	 *
	 * @return true, if is captura visible salvar
	 */
	public boolean isCapturaVisibleSalvar() {
		return capturaVisibleSalvar;
	}

	/**
	 * Sets the captura visible salvar.
	 *
	 * @param capturaVisibleSalvar the new captura visible salvar
	 */
	public void setCapturaVisibleSalvar(boolean capturaVisibleSalvar) {
		this.capturaVisibleSalvar = capturaVisibleSalvar;
	}

	/**
	 * Checks if is captura visible modificar.
	 *
	 * @return true, if is captura visible modificar
	 */
	public boolean isCapturaVisibleModificar() {
		return capturaVisibleModificar;
	}

	/**
	 * Sets the captura visible modificar.
	 *
	 * @param capturaVisibleModificar the new captura visible modificar
	 */
	public void setCapturaVisibleModificar(boolean capturaVisibleModificar) {
		this.capturaVisibleModificar = capturaVisibleModificar;
	}

	/**
	 * Checks if is meta desabilitado reset.
	 *
	 * @return true, if is meta desabilitado reset
	 */
	public boolean isMetaDesabilitadoReset() {
		return metaDesabilitadoReset;
	}

	/**
	 * Sets the meta desabilitado reset.
	 *
	 * @param metaDesabilitadoReset the new meta desabilitado reset
	 */
	public void setMetaDesabilitadoReset(boolean metaDesabilitadoReset) {
		this.metaDesabilitadoReset = metaDesabilitadoReset;
	}

	/**
	 * Checks if is meta desabilitado adicionar.
	 *
	 * @return true, if is meta desabilitado adicionar
	 */
	public boolean isMetaDesabilitadoAdicionar() {
		return metaDesabilitadoAdicionar;
	}

	/**
	 * Sets the meta desabilitado adicionar.
	 *
	 * @param metaDesabilitadoAdicionar the new meta desabilitado adicionar
	 */
	public void setMetaDesabilitadoAdicionar(boolean metaDesabilitadoAdicionar) {
		this.metaDesabilitadoAdicionar = metaDesabilitadoAdicionar;
	}

	/**
	 * Checks if is meta desabilitado borrar.
	 *
	 * @return true, if is meta desabilitado borrar
	 */
	public boolean isMetaDesabilitadoBorrar() {
		return metaDesabilitadoBorrar;
	}

	/**
	 * Sets the meta desabilitado borrar.
	 *
	 * @param metaDesabilitadoBorrar the new meta desabilitado borrar
	 */
	public void setMetaDesabilitadoBorrar(boolean metaDesabilitadoBorrar) {
		this.metaDesabilitadoBorrar = metaDesabilitadoBorrar;
	}

	/**
	 * Checks if is meta desabilitado cancelar.
	 *
	 * @return true, if is meta desabilitado cancelar
	 */
	public boolean isMetaDesabilitadoCancelar() {
		return metaDesabilitadoCancelar;
	}

	/**
	 * Sets the meta desabilitado cancelar.
	 *
	 * @param metaDesabilitadoCancelar the new meta desabilitado cancelar
	 */
	public void setMetaDesabilitadoCancelar(boolean metaDesabilitadoCancelar) {
		this.metaDesabilitadoCancelar = metaDesabilitadoCancelar;
	}

	/**
	 * Checks if is meta visible salvar.
	 *
	 * @return true, if is meta visible salvar
	 */
	public boolean isMetaVisibleSalvar() {
		return metaVisibleSalvar;
	}

	/**
	 * Sets the meta visible salvar.
	 *
	 * @param metaVisibleSalvar the new meta visible salvar
	 */
	public void setMetaVisibleSalvar(boolean metaVisibleSalvar) {
		this.metaVisibleSalvar = metaVisibleSalvar;
	}

	/**
	 * Checks if is meta visible modificar.
	 *
	 * @return true, if is meta visible modificar
	 */
	public boolean isMetaVisibleModificar() {
		return metaVisibleModificar;
	}

	/**
	 * Sets the meta visible modificar.
	 *
	 * @param metaVisibleModificar the new meta visible modificar
	 */
	public void setMetaVisibleModificar(boolean metaVisibleModificar) {
		this.metaVisibleModificar = metaVisibleModificar;
	}

	/**
	 * Checks if is captura deshabilitado.
	 *
	 * @return true, if is captura deshabilitado
	 */
	public boolean isCapturaDeshabilitado() {
		return capturaDeshabilitado;
	}

	/**
	 * Sets the captura deshabilitado.
	 *
	 * @param capturaDeshabilitado the new captura deshabilitado
	 */
	public void setCapturaDeshabilitado(boolean capturaDeshabilitado) {
		this.capturaDeshabilitado = capturaDeshabilitado;
	}

	/**
	 * Checks if is meta deshabilitado.
	 *
	 * @return true, if is meta deshabilitado
	 */
	public boolean isMetaDeshabilitado() {
		return metaDeshabilitado;
	}

	/**
	 * Sets the meta deshabilitado.
	 *
	 * @param metaDeshabilitado the new meta deshabilitado
	 */
	public void setMetaDeshabilitado(boolean metaDeshabilitado) {
		this.metaDeshabilitado = metaDeshabilitado;
	}

	/**
	 * Checks if is captura adicionar.
	 *
	 * @return true, if is captura adicionar
	 */
	public boolean isCapturaAdicionar() {
		return capturaAdicionar;
	}

	/**
	 * Sets the captura adicionar.
	 *
	 * @param capturaAdicionar the new captura adicionar
	 */
	public void setCapturaAdicionar(boolean capturaAdicionar) {
		this.capturaAdicionar = capturaAdicionar;
	}

	/**
	 * Checks if is meta adicionar.
	 *
	 * @return true, if is meta adicionar
	 */
	public boolean isMetaAdicionar() {
		return metaAdicionar;
	}

	/**
	 * Sets the meta adicionar.
	 *
	 * @param metaAdicionar the new meta adicionar
	 */
	public void setMetaAdicionar(boolean metaAdicionar) {
		this.metaAdicionar = metaAdicionar;
	}

	/**
	 * Gets the clv dep bean.
	 *
	 * @return the clv dep bean
	 */
	public String getClvDepBean() {
		return clvDepBean;
	}

	/**
	 * Sets the clv dep bean.
	 *
	 * @param clvDepBean the new clv dep bean
	 */
	public void setClvDepBean(String clvDepBean) {
		this.clvDepBean = clvDepBean;
	}

	/**
	 * Gets the clv prog bean.
	 *
	 * @return the clv prog bean
	 */
	public String getClvProgBean() {
		return clvProgBean;
	}

	/**
	 * Sets the clv prog bean.
	 *
	 * @param clvProgBean the new clv prog bean
	 */
	public void setClvProgBean(String clvProgBean) {
		this.clvProgBean = clvProgBean;
	}

	/**
	 * Checks if is deshabilitado editables.
	 *
	 * @return true, if is deshabilitado editables
	 */
	public boolean isDeshabilitadoEditables() {
		return deshabilitadoEditables;
	}

	/**
	 * Sets the deshabilitado editables.
	 *
	 * @param deshabilitadoEditables the new deshabilitado editables
	 */
	public void setDeshabilitadoEditables(boolean deshabilitadoEditables) {
		this.deshabilitadoEditables = deshabilitadoEditables;
	}

	/**
	 * Gets the metas fisicas service.
	 *
	 * @return the metas fisicas service
	 */
	public MetasFisicasService getMetasFisicasService() {
		return metasFisicasService;
	}

	/**
	 * Sets the metas fisicas service.
	 *
	 * @param metasFisicasService the new metas fisicas service
	 */
	public void setMetasFisicasService(MetasFisicasService metasFisicasService) {
		this.metasFisicasService = metasFisicasService;
	}

	/**
	 * Gets the clv fuente list.
	 *
	 * @return the clv fuente list
	 */
	public List<String> getClvFuenteList() {
		return clvFuenteList;
	}

	/**
	 * Sets the clv fuente list.
	 *
	 * @param clvFuenteList the new clv fuente list
	 */
	public void setClvFuenteList(List<String> clvFuenteList) {
		this.clvFuenteList = clvFuenteList;
	}

	/**
	 * Gets the temp editar.
	 *
	 * @return the temp editar
	 */
	public MetasFisicasDTO getTempEditar() {
		return tempEditar;
	}

	/**
	 * Sets the temp editar.
	 *
	 * @param tempEditar the new temp editar
	 */
	public void setTempEditar(MetasFisicasDTO tempEditar) {
		this.tempEditar = tempEditar;
	}

	/**
	 * Gets the temp editar detalle.
	 *
	 * @return the temp editar detalle
	 */
	public MetasFisicasDetalleDTO getTempEditarDetalle() {
		return tempEditarDetalle;
	}

	/**
	 * Sets the temp editar detalle.
	 *
	 * @param tempEditarDetalle the new temp editar detalle
	 */
	public void setTempEditarDetalle(MetasFisicasDetalleDTO tempEditarDetalle) {
		this.tempEditarDetalle = tempEditarDetalle;
	}

	/**
	 * Gets the catdep repository.
	 *
	 * @return the catdep repository
	 */
	public CatdepRepository getCatdepRepository() {
		return catdepRepository;
	}

	/**
	 * Sets the catdep repository.
	 *
	 * @param catdepRepository the new catdep repository
	 */
	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

	/**
	 * Gets the logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return LOGGER;
	}

	/**
	 * Gets the current page meta.
	 *
	 * @return the current page meta
	 */
	public int getCurrentPageMeta() {
		return currentPageMeta;
	}

	/**
	 * Sets the current page meta.
	 *
	 * @param currentPageMeta the new current page meta
	 */
	public void setCurrentPageMeta(int currentPageMeta) {
		this.currentPageMeta = currentPageMeta;
	}

	/**
	 * Gets the current page captura.
	 *
	 * @return the current page captura
	 */
	public int getCurrentPageCaptura() {
		return currentPageCaptura;
	}

	/**
	 * Sets the current page captura.
	 *
	 * @param currentPageCaptura the new current page captura
	 */
	public void setCurrentPageCaptura(int currentPageCaptura) {
		this.currentPageCaptura = currentPageCaptura;
	}

	public String getClaveSearch() {
		return claveSearch;
	}

	public void setClaveSearch(String claveSearch) {
		this.claveSearch = claveSearch;
	}

	public String getProgramaSearch() {
		return programaSearch;
	}

	public void setProgramaSearch(String programaSearch) {
		this.programaSearch = programaSearch;
	}

}
