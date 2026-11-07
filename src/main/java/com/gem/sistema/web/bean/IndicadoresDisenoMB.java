package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.data.PageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Cpd;
import com.gem.sistema.business.domain.Ftecnicadd;
import com.gem.sistema.business.domain.Ftecnicadm;
import com.gem.sistema.business.domain.Mir;
import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.domain.Variables;
import com.gem.sistema.business.dto.FtecnicaddDTO;
import com.gem.sistema.business.dto.FtecnicadmDTO;
import com.gem.sistema.business.predicates.CatdepPredicate;
import com.gem.sistema.business.predicates.CpdPredicate;
import com.gem.sistema.business.predicates.FtecnicaDdPredicate;
import com.gem.sistema.business.predicates.MirPredicates;
import com.gem.sistema.business.predicates.MuniNepPredicates;
import com.gem.sistema.business.repository.catalogos.VariablesRepository;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.CpdRepository;
import com.gem.sistema.business.repository.catalogs.FtecnicaDdRepository;
import com.gem.sistema.business.repository.catalogs.FtecnicaDmRepository;
import com.gem.sistema.business.repository.catalogs.MirRepository;
import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.business.service.indicadoresdiseno.IndicadoresDisenoService;

// TODO: Auto-generated Javadoc
/**
 * The Class IndicadoresDisenoMB.
 */
@ManagedBean(name = "indicadoresDisenoMB")
@ViewScoped
public class IndicadoresDisenoMB extends AbstractMB {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(IndicadoresDisenoMB.class);

	/** The Constant TRUE. */
	private static final Boolean TRUE = Boolean.TRUE;

	/** The Constant FALSE. */
	private static final Boolean FALSE = Boolean.FALSE;

	/** The indicadores diseno service. */
	@ManagedProperty("#{indicadoresDisenoService}")
	private IndicadoresDisenoService indicadoresDisenoService;

	/** The lista encabezado. */
	private List<FtecnicadmDTO> listaEncabezado = new ArrayList<FtecnicadmDTO>();

	/** The lista detalle. */
	private List<FtecnicaddDTO> listaDetalle = new ArrayList<FtecnicaddDTO>();

	/** The lista temas. */
	private List<Cpd> listaTemas = new ArrayList<Cpd>();

	/** The lista dependencias. */
	private List<String> listaDependencias = new ArrayList<String>();

	/** The lista programas. */
	private List<String> listaProgramas = new ArrayList<String>();

	/** The lista codigos indicador. */
	private List<Mir> listaCodigosIndicador = new ArrayList<Mir>();

	/** The lista frecuencia. */
	private List<String> listaFrecuencia = new ArrayList<String>(
			Arrays.asList("Mensual", "Trimestral", "Semestral", "Anual"));

	/** The lista tipo indicador. */
	private List<String> listaTipoIndicador = new ArrayList<String>(Arrays.asList("Estrategico", "Gestion"));

	/** The lista variables. */
	private List<Variables> listaVariables = new ArrayList<Variables>();

	/** The diseno desabilitado reset. */
	private boolean disenoDesabilitadoReset;

	/** The diseno desabilitado adicionar. */
	private boolean disenoDesabilitadoAdicionar;

	/** The diseno desabilitado borrar. */
	private boolean disenoDesabilitadoBorrar;

	/** The diseno desabilitado cancelar. */
	private boolean disenoDesabilitadoCancelar;

	/** The diseno visible salvar. */
	private boolean disenoVisibleSalvar;

	/** The diseno visible modificar. */
	private boolean disenoVisibleModificar;

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

	/** The diseno deshabilitado. */
	private boolean disenoDeshabilitado;

	/** The meta deshabilitado. */
	private boolean metaDeshabilitado;

	/** The diseno adicionar. */
	private boolean disenoAdicionar;

	/** The diseno modificar. */
	private boolean disenoModificar;

	/** The meta adicionar. */
	private boolean metaAdicionar;

	/** The meta modificar. */
	private boolean metaModificar;

	/** The deshabilitado editables. */
	private boolean deshabilitadoEditables;

	/** The b btn M encabezado. */
	private Boolean bBtnMEncabezado;

	/** The b btn modificar. */
	private Boolean bBtnModificar;

	/** The cvetemas. */
	private String cvetemas;

	/** The clvdep. */
	private String clvdep;

	/** The clvfun. */
	private String clvfun;

	/** The cveind. */
	private String cveind;

	/** The desc temas. */
	private String descTemas;

	/** The desc dependencia. */
	private String descDependencia;

	/** The desc programa. */
	private String descPrograma;

	/** The desc codigo indicador. */
	private String descCodigoIndicador;

	/** The current page. */
	private int currentPage = 0;

	/** The current page detalle. */
	private int currentPageDetalle = 0;

	/** The filtered variables. */
	private List<Variables> filteredVariables;

	/** The variables. */
	private List<Variables> variables;

	/** The variable selected. */
	private Variables variableSelected;

	/** The filtered indicadores. */
	private List<Mir> filteredIndicadores;

	/** The indicadores. */
	private List<Mir> indicadores;

	/** The master row selected. */
	private int masterRowSelected;

	/** The indicador selected. */
	private Mir indicadorSelected;

	/** The tiene datos encabezado. */
	private boolean tieneDatosEncabezado;

	/** The variables repository. */
	@ManagedProperty("#{variablesRepository}")
	private VariablesRepository variablesRepository;

	/** The mir repository. */
	@ManagedProperty("#{mirRepository}")
	private MirRepository mirRepository;

	/** The ftecnica dm repository. */
	@ManagedProperty("#{ftecnicaDmRepository}")
	private FtecnicaDmRepository ftecnicaDmRepository;

	/** The ftecnica dd repository. */
	@ManagedProperty("#{ftecnicaDdRepository}")
	private FtecnicaDdRepository ftecnicaDdRepository;

	/** The muni nep repository. */
	@ManagedProperty("#{muniNepRepository}")
	private MuniNepRepository muniNepRepository;

	/** The catdep repository. */
	@ManagedProperty("#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/** The cpd repository. */
	@ManagedProperty("#{cpdRepository}")
	private CpdRepository cpdRepository;

	private Integer idSector;

	/**
	 * Gets the b btn modificar.
	 *
	 * @return the b btn modificar
	 */
	public Boolean getbBtnModificar() {
		return bBtnModificar;
	}

	/**
	 * Sets the b btn modificar.
	 *
	 * @param bBtnModificar the new b btn modificar
	 */
	public void setbBtnModificar(Boolean bBtnModificar) {
		this.bBtnModificar = bBtnModificar;
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
	 * Gets the cpd repository.
	 *
	 * @return the cpd repository
	 */
	public CpdRepository getCpdRepository() {
		return cpdRepository;
	}

	/**
	 * Sets the cpd repository.
	 *
	 * @param cpdRepository the new cpd repository
	 */
	public void setCpdRepository(CpdRepository cpdRepository) {
		this.cpdRepository = cpdRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct IndicadoresDisenoMB ");

		this.setbBtnModificar(TRUE);

		idSector = this.getUserDetails().getIdSector();
	}

	/**
	 * On page load.
	 */
	public void onPageLoad() {
		this.findAllIndicadoresDisenoMetas();
	}

	/**
	 * Gets the b btn M encabezado.
	 *
	 * @return the b btn M encabezado
	 */
	public Boolean getbBtnMEncabezado() {
		return bBtnMEncabezado;
	}

	/**
	 * Sets the b btn M encabezado.
	 *
	 * @param bBtnMEncabezado the new b btn M encabezado
	 */
	public void setbBtnMEncabezado(Boolean bBtnMEncabezado) {
		this.bBtnMEncabezado = bBtnMEncabezado;
	}

	/**
	 * metodo que carga la pantalla inicial Ficha Técnica de diseño de indicadores
	 * estratégicos o de gestión.
	 */
	public void findAllIndicadoresDisenoMetas() {
		this.limpiarListas();
		this.llenarCombos();
		this.llenarDiseno();
		this.consultaVariables();
		this.consultIndicadores();
		this.estadoInicialBotonesConstruct();
	}

	/**
	 * metodo que hace un cleaner a todas las listas para ponerlas en su estado
	 * inicial.
	 */
	public void limpiarListas() {
		listaEncabezado.clear();
		listaDetalle.clear();
		listaTemas.clear();
		listaDependencias.clear();
		listaProgramas.clear();
		listaCodigosIndicador.clear();
		this.setDescTemas("");
		this.setDescDependencia("");
		this.setDescPrograma("");
		this.setDescCodigoIndicador("");
		currentPage = 0;
		currentPageDetalle = 0;
	}

	/**
	 * establece el estado inicial de los botones.
	 */
	public void estadoInicialBotones() {
		if (null != listaEncabezado.get(currentPage).getClvdep()
				&& null != listaEncabezado.get(currentPage).getClvfin()) {
			this.setDisenoVisibleModificar(true);
			this.setDisenoVisibleSalvar(false);
			this.setDisenoDesabilitadoReset(true);
			this.setDisenoDesabilitadoAdicionar(false);
			this.setDisenoDesabilitadoBorrar(false);
			this.setDisenoDesabilitadoCancelar(true);
			this.setbBtnMEncabezado(FALSE);
			currentPageDetalle = listaDetalle.size() - 1;
			if (!ftecnicaDdRepository.findAll().isEmpty()) {
				this.setMetaVisibleModificar(true);
				this.setMetaVisibleSalvar(false);
				this.setMetaDesabilitadoReset(true);
				this.setMetaDesabilitadoAdicionar(false);
				this.setMetaDesabilitadoBorrar(true);
				this.setMetaDesabilitadoCancelar(true);
				this.setbBtnMEncabezado(FALSE);
			} else {
				this.setMetaVisibleModificar(false);
				this.setMetaVisibleSalvar(false);
				this.setMetaDesabilitadoReset(true);
				this.setMetaDesabilitadoAdicionar(false);
				this.setMetaDesabilitadoBorrar(true);
				this.setMetaDesabilitadoCancelar(true);
			}
			this.setDisenoDeshabilitado(true);
			this.setMetaDeshabilitado(true);
			this.setDeshabilitadoEditables(true);
		} else {
			this.setbBtnMEncabezado(TRUE);
			this.setDisenoVisibleModificar(false);
			this.setDisenoVisibleSalvar(false);
			this.setDisenoDesabilitadoReset(true);
			this.setDisenoDesabilitadoAdicionar(false);
			this.setDisenoDesabilitadoBorrar(true);
			this.setDisenoDesabilitadoCancelar(true);
			this.setMetaVisibleModificar(false);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoAdicionar(true);
			this.setMetaDesabilitadoBorrar(true);
			this.setMetaDesabilitadoCancelar(true);
			this.setDisenoDeshabilitado(false);
			this.setMetaDeshabilitado(false);
			this.setDeshabilitadoEditables(false);
			this.setDisenoDesabilitadoBorrar(TRUE);
			this.deshabilitadoEditables = FALSE;
		}
	}

	/**
	 * Estado inicial botones detalle.
	 */
	public void estadoInicialBotonesDetalle() {
		if (!ftecnicaDdRepository.findAll().isEmpty()) {
			this.setMetaVisibleModificar(true);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoAdicionar(false);
			this.setMetaDesabilitadoBorrar(false);
			this.setMetaDesabilitadoCancelar(true);
			this.setMetaDeshabilitado(true);
		} else {
			this.setMetaVisibleModificar(false);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoAdicionar(false);
			this.setMetaDesabilitadoBorrar(true);
			this.setMetaDesabilitadoCancelar(true);
			this.setMetaDeshabilitado(true);
		}

	}

	/**
	 * metodo que llena la lista del encabezado de diseño de indicadores y las metas
	 * correspondientes.
	 */
	public void llenarDiseno() {
		listaEncabezado = indicadoresDisenoService.llenaListaEncabezado(idSector);
		if (!listaEncabezado.isEmpty()) {
			tieneDatosEncabezado = true;
			filtraTema(ZERO);
			filtraDependencia(ZERO);
			filtraPrograma(ZERO);
			filtraCodeIndicador(ZERO);
			listaDetalle = indicadoresDisenoService.llenaListaDetalle(listaEncabezado.get(ZERO));
			if (listaDetalle.isEmpty()) {
				FtecnicaddDTO ftecnicaddDTO = new FtecnicaddDTO();
				listaDetalle.add(ftecnicaddDTO);
			}
		} else {
			tieneDatosEncabezado = false;
			FtecnicadmDTO ftecnicadmDTO = new FtecnicadmDTO();
			listaEncabezado.add(ftecnicadmDTO);
		}
		if (null != listaEncabezado.get(currentPage).getClvdep()) {
			FtecnicadmDTO encabezado = listaEncabezado.get(0);
			List<Ftecnicadd> detalle = (List<Ftecnicadd>) ftecnicaDdRepository
					.findAll(FtecnicaDdPredicate.findByClvDepAndClvFunAndClvInd(encabezado.getClvdep(),
							encabezado.getClvfun(), encabezado.getCveind(), this.getUserDetails().getIdSector()));
			this.setMetaVisibleModificar(true);
			this.setbBtnModificar(FALSE);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoAdicionar(FALSE);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoBorrar(FALSE);
			this.setMetaDesabilitadoCancelar(true);
			if (CollectionUtils.isEmpty(detalle)) {
				this.setMetaVisibleModificar(true);
				this.setbBtnModificar(TRUE);
				this.setMetaVisibleSalvar(false);
				this.setMetaDesabilitadoAdicionar(TRUE);
				this.setMetaDesabilitadoReset(true);
				this.setMetaDesabilitadoBorrar(true);
				this.setMetaDesabilitadoCancelar(true);
			}
		}
	}

	/**
	 * Llenar detalle.
	 */
	public void llenarDetalle() {
		listaDetalle = indicadoresDisenoService.llenaListaDetalle(listaEncabezado.get(ZERO));
		if (listaDetalle.isEmpty()) {
			FtecnicaddDTO ftecnicaddDTO = new FtecnicaddDTO();
			listaDetalle.add(ftecnicaddDTO);
		}
	}

	/**
	 * establece el estado inicial de los botones.
	 */
	public void estadoInicialBotonesConstruct() {
		if (tieneDatosEncabezado) {
			this.setDisenoVisibleModificar(true);
			this.setDisenoVisibleSalvar(false);
			this.setDisenoDesabilitadoReset(true);
			this.setDisenoDesabilitadoAdicionar(false);
			this.setDisenoDesabilitadoBorrar(false);
			this.setDisenoDesabilitadoCancelar(true);
			if (listaDetalle.isEmpty()) {
				this.setMetaVisibleModificar(true);
				this.setMetaVisibleSalvar(false);
				this.setMetaDesabilitadoReset(true);
				this.setMetaDesabilitadoAdicionar(TRUE);
				this.setMetaDesabilitadoBorrar(true);
				this.setMetaDesabilitadoCancelar(true);
			} else {
				this.setMetaVisibleModificar(true);
				this.setMetaVisibleSalvar(false);
				this.setMetaDesabilitadoReset(true);
				this.setMetaDesabilitadoAdicionar(false);
				this.setMetaDesabilitadoBorrar(false);
				this.setMetaDesabilitadoCancelar(false);
			}
			this.setDisenoDeshabilitado(true);
			this.setMetaDeshabilitado(true);
			this.setDeshabilitadoEditables(true);
		} else {
			FtecnicaddDTO ftecnicaddDTO = new FtecnicaddDTO();
			listaDetalle.add(ftecnicaddDTO);
			this.setDisenoVisibleModificar(false);
			this.setDisenoVisibleSalvar(false);
			this.setDisenoDesabilitadoReset(true);
			this.setDisenoDesabilitadoAdicionar(false);
			this.setDisenoDesabilitadoBorrar(true);
			this.setDisenoDesabilitadoCancelar(true);
			this.setMetaVisibleModificar(false);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoAdicionar(TRUE);
			this.setMetaDesabilitadoBorrar(true);
			this.setMetaDesabilitadoCancelar(true);
			this.setDisenoDeshabilitado(true);
			this.setMetaDeshabilitado(true);
			this.setDeshabilitadoEditables(true);
		}
	}

	/**
	 * metodo que cambia la lista de metas dependiendo de la pagina seleccionada.
	 *
	 * @param event the event
	 */
	public void cambiarPagina(PageEvent event) {
		currentPage = event.getPage();
		LOGGER.info("++ currentPage: " + currentPage);
		filtraTema(currentPage);
		filtraDependencia(currentPage);
		filtraPrograma(currentPage);
		cambiarCodigoIndicador();
		listaDetalle = indicadoresDisenoService.llenaListaDetalle(listaEncabezado.get(currentPage));
		if (listaDetalle.isEmpty()) {
			FtecnicaddDTO ftecnicaddDTO = new FtecnicaddDTO();
			listaDetalle.add(ftecnicaddDTO);
		}
		if (!disenoAdicionar) {
			this.estadoInicialBotones();
		} else {
			this.setMetaVisibleModificar(false);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoAdicionar(false);
			this.setMetaDesabilitadoBorrar(true);
			this.setMetaDesabilitadoCancelar(true);
		}

		this.setMetaVisibleModificar(true);
		this.setbBtnModificar(FALSE);
		this.setMetaVisibleSalvar(false);
		this.setMetaDesabilitadoAdicionar(FALSE);
		this.setMetaDesabilitadoReset(true);
		this.setMetaDesabilitadoBorrar(FALSE);
		this.setMetaDesabilitadoCancelar(true);
		FtecnicadmDTO encabezado = listaEncabezado.get(currentPage);
		if (null != encabezado.getClvdep() && null != encabezado.getClvfun() && null != encabezado.getCveind()) {
			List<Ftecnicadd> detalle = (List<Ftecnicadd>) ftecnicaDdRepository
					.findAll(FtecnicaDdPredicate.findByClvDepAndClvFunAndClvInd(encabezado.getClvdep(),
							encabezado.getClvfun(), encabezado.getCveind(), this.getUserDetails().getIdSector()));

			if (CollectionUtils.isEmpty(detalle)) {
				this.setMetaVisibleModificar(true);
				this.setbBtnModificar(TRUE);
				this.setMetaVisibleSalvar(false);
				this.setMetaDesabilitadoAdicionar(FALSE);
				this.setMetaDesabilitadoReset(true);
				this.setMetaDesabilitadoBorrar(true);
				this.setMetaDesabilitadoCancelar(true);
			}
		}

		// disenoAdicionar = false;
	}

	/**
	 * metodo que cambia la lista de metas dependiendo de la pagina.
	 *
	 * @param eventDetalle the event detalle
	 */
	public void cambiarPaginaDetalle(PageEvent eventDetalle) {
		currentPageDetalle = eventDetalle.getPage();
		FtecnicadmDTO encabezado = listaEncabezado.get(currentPage);
		if (null != encabezado.getClvdep() && null != encabezado.getClvfun() && null != encabezado.getCveind()) {
			List<Ftecnicadd> detalle = (List<Ftecnicadd>) ftecnicaDdRepository
					.findAll(FtecnicaDdPredicate.findByClvDepAndClvFunAndClvInd(encabezado.getClvdep(),
							encabezado.getClvfun(), encabezado.getCveind(), this.getUserDetails().getIdSector()));

			if (CollectionUtils.isEmpty(detalle)) {
				this.setMetaVisibleModificar(true);
				this.setbBtnModificar(TRUE);
				this.setMetaVisibleSalvar(false);
				this.setMetaDesabilitadoAdicionar(FALSE);
				this.setMetaDesabilitadoReset(true);
				this.setMetaDesabilitadoBorrar(true);
				this.setMetaDesabilitadoCancelar(true);
			}
		}
	}

	/**
	 * metodo para poner la descripcion de un tema.
	 *
	 * @param row the row
	 */
	public void filtraTema(Integer row) {
		for (Cpd tema : listaTemas) {
			if (tema.getCvetemas().equals(listaEncabezado.get(row).getCvetemas())) {
				this.setDescTemas(tema.getDescripcion());
			}
		}
		this.blockButtons();
	}

	/**
	 * metodo para poner la descripcion de una dependencia.
	 *
	 * @param row the row
	 */
	public void filtraDependencia(Integer row) {
		List<Catdep> dependencia = indicadoresDisenoService.buscaDescDependencia(listaEncabezado.get(row).getClvdep(),
				this.getUserDetails().getIdSector());
		if (!dependencia.isEmpty()) {
			this.setDescDependencia(dependencia.get(ZERO).getNomdep());
		} else {
			this.setDescDependencia("");
		}

		listaProgramas = indicadoresDisenoService.llenaComboProgramasFiltradoXDep(listaEncabezado.get(row).getClvdep(),
				this.getUserDetails().getIdSector());
		this.blockButtons();
	}

	/**
	 * metodo para poner la descripcion de un programa.
	 *
	 * @param row the row
	 */
	public void filtraPrograma(Integer row) {
		List<Muninep> listaMuninep = muniNepRepository.findByCampo7AndIdsector(listaEncabezado.get(row).getClvfun(),
				this.getUserDetails().getIdSector());
		if (!listaMuninep.isEmpty()) {
			this.setDescPrograma(listaMuninep.get(ZERO).getCampo6());
		} else {
			this.setDescPrograma("");
		}
		this.blockButtons();
	}

	/**
	 * metodo para poner la descripcion de un codigo de indicador.
	 *
	 * @param row the row
	 */
	public void filtraCodeIndicador(Integer row) {
		for (Mir mir : listaCodigosIndicador) {
			if (mir.getCodigo().equals(listaEncabezado.get(row).getCveind())) {
				this.setDescCodigoIndicador(mir.getNomind());
				break;
			}
		}
		this.blockButtons();
	}

	/**
	 * metodo para poner la descripcion de una variable.
	 *
	 * @param row the row
	 */
	public void filtraVariable(Integer row) {
		if (listaDetalle.get(currentPageDetalle).getCvevar().equals("0")) {
			listaDetalle.get(currentPageDetalle).setVariables("");
		} else {
			for (Variables variable : listaVariables) {
				if (variable.getCvevar().equals(listaDetalle.get(currentPageDetalle).getCvevar())) {
					listaDetalle.get(currentPageDetalle).setVariables(variable.getNomvar());
					break;
				}
			}
		}
	}

	/**
	 * metodo que llena los combos utilizados en el diseño de indicadores.
	 */
	public void llenarCombos() {
		for (Cpd cpd : indicadoresDisenoService.llenaComboTemas()) {
			if (cpd.getCvetemas().length() == 8) {
				listaTemas.add(cpd);
			}
		}

		System.out.println("listafiltrada" + listaTemas.size());
		listaDependencias = indicadoresDisenoService.llenaComboDependencias(this.getUserDetails().getIdSector());
		// for (Xcatpro catdep : listaDepFiltrar) {
		// if(catdep.getIdsector()==this.getUserDetails().getIdSector()){
		// listaDependencias.add(catdep);
		// }
		// }
		for (Variables variable : indicadoresDisenoService.llenaComboVariables()) {
			listaVariables.add(variable);
		}
		listaProgramas = indicadoresDisenoService.llenaComboProgramas(this.getUserDetails().getIdSector());
		listaCodigosIndicador = indicadoresDisenoService.llenaComboCodigosIndicador();
	}

	/**
	 * metodo que guarda un encabezado de diseño de indicadores estratégicos o de
	 * gestión.
	 *
	 * @param row the row
	 */
	public void salvarDiseno(Integer row) {
		if (listaEncabezado.get(row).getCvetemas().equals("0")) {
			generateNotificationFront(FacesMessage.SEVERITY_FATAL, "Error!", "El campo Cve. Temas es obligatorio!");
		} else if (listaEncabezado.get(row).getClvdep().equals("0")) {
			generateNotificationFront(FacesMessage.SEVERITY_FATAL, "Error!", "El campo Dependencia es obligatorio!");
		} else if (listaEncabezado.get(row).getClvfun().equals("0")) {
			generateNotificationFront(FacesMessage.SEVERITY_FATAL, "Error!", "El campo Programa es obligatorio!");
		} else if (listaEncabezado.get(row).getFrecuencia().equals("0")) {
			generateNotificationFront(FacesMessage.SEVERITY_FATAL, "Error!", "El campo Frecuencia es obligatorio!");
		} else if (listaEncabezado.get(row).getCveind() == null) {
			generateNotificationFront(FacesMessage.SEVERITY_FATAL, "Error!",
					"El campo Código Indicador es obligatorio!");
		} else if (listaEncabezado.get(row).getTipo().equals("0")) {
			generateNotificationFront(FacesMessage.SEVERITY_FATAL, "Error!",
					"El campo Tipo de Indicador es obligaorio!");
		} else {
			try {
				listaEncabezado.get(row).setUsuario(this.getUserDetails().getUsername());
				listaEncabezado.get(row).setIdsector(this.getUserDetails().getIdSector());
				listaEncabezado.get(row).setClvfin("");
				listaEncabezado.get(row).setUserid(this.getUserDetails().getUsername());
				listaEncabezado.get(row).setFeccap(new Date());
				listaEncabezado.get(row).setNomind(this.getDescCodigoIndicador());
				if (disenoModificar) {
					indicadoresDisenoService.salvarDisenoModificado(listaEncabezado.get(row));

					generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
							"Indicador de Diseño actualizado correctamente");
					this.estadoInicialBotones();

				} else {
					boolean validaSave = indicadoresDisenoService.salvarDiseno(listaEncabezado.get(row));
					if (!validaSave) {
						String codIndicador = listaEncabezado.get(row).getCveind() == null ? ""
								: listaEncabezado.get(row).getCveind();
						generateNotificationFront(SEVERITY_ERROR, "Error!",
								"Ficha de Diseño ya existe con CveTemas: " + listaEncabezado.get(row).getCvetemas()
										+ ", " + "Dependencia: " + listaEncabezado.get(row).getClvdep() + ", Programa: "
										+ listaEncabezado.get(row).getClvfun() + " " + ", Codigo Indicador:"
										+ codIndicador);

						this.setMetaVisibleModificar(true);
						this.setbBtnModificar(FALSE);
						this.setMetaVisibleSalvar(false);
						this.setMetaDesabilitadoAdicionar(FALSE);
						this.setMetaDesabilitadoReset(FALSE);
						this.setMetaDesabilitadoBorrar(FALSE);
						List<Ftecnicadd> ldeta = (List<Ftecnicadd>) this.ftecnicaDdRepository.findAll(
								FtecnicaDdPredicate.findByClvDepAndClvFunAndClvInd(listaEncabezado.get(row).getClvdep(),
										listaEncabezado.get(row).getClvfun(), listaEncabezado.get(row).getCveind(),
										this.getUserDetails().getIdSector()));
						if (CollectionUtils.isEmpty(ldeta)) {
							this.setMetaVisibleModificar(true);
							this.setbBtnModificar(TRUE);
							this.setMetaVisibleSalvar(false);
							this.setMetaDesabilitadoAdicionar(FALSE);
							this.setMetaDesabilitadoReset(true);
							this.setMetaDesabilitadoBorrar(true);
							this.setMetaDesabilitadoCancelar(true);
						}

					} else {
						generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
								"Indicador de Diseño guardado correctamente");
						this.estadoInicialBotones();
					}
				}

			} catch (Exception e) {

				generateNotificationFront(SEVERITY_ERROR, "Error!", "Ha ocurrido un error, contacta al administrador.");
			}
		}
	}

	/**
	 * metodo que modifica un encabezado de diseño de indicadores estratégicos o de
	 * gestión.
	 *
	 * @param row the row
	 */
	public void modificarDiseno(Integer row) {
		listaEncabezado.clear();
		listaEncabezado = indicadoresDisenoService.llenaListaEncabezado(idSector);
		if (listaEncabezado.isEmpty()) {
			FtecnicadmDTO ftecnicadmDTO = new FtecnicadmDTO();
			listaEncabezado.add(ftecnicadmDTO);
		}
		disenoAdicionar = false;
		disenoModificar = true;
		if (indicadoresDisenoService.tieneDetalle(listaEncabezado.get(row))) {
			this.setDeshabilitadoEditables(false);
			this.setDisenoDeshabilitado(true);
			// TODO deshabilitar todos los detalles
		} else {
			this.setDeshabilitadoEditables(false);
			this.setDisenoDeshabilitado(false);
		}
		this.setDisenoVisibleSalvar(true);
		this.setDisenoVisibleModificar(false);
		this.setDisenoDesabilitadoAdicionar(true);
		this.setDisenoDesabilitadoBorrar(true);
		this.setDisenoDesabilitadoReset(false);
		this.setDisenoDesabilitadoCancelar(false);
		// al modificar un master no se permite la adicion o modificación

		this.setbBtnModificar(FALSE);
		this.setMetaVisibleSalvar(false);
		this.setMetaDesabilitadoAdicionar(TRUE);
		this.setMetaDesabilitadoReset(true);
		this.setMetaDesabilitadoBorrar(FALSE);
		this.setMetaDesabilitadoCancelar(true);

		// if (null == listaDetalle.get(currentPageDetalle).getClvdep() && null
		// == listaDetalle.get(currentPageDetalle).getCveind() && 0 ==
		// listaDetalle.get(currentPageDetalle).getCodigo()) {
		// this.blockButtons();
		// }
		this.setDisenoModificar(TRUE);
	}

	/**
	 * metodo que resetea un encabezado de diseño de indicadores estratégicos o de
	 * gestión.
	 *
	 * @param row the row
	 */
	public void resetDiseno(Integer row) {
		if (listaEncabezado.size() == 1 && listaEncabezado.get(currentPage).getCvetemas() == null) {
			listaEncabezado.clear();
			FtecnicadmDTO ftecnicadmDTO = new FtecnicadmDTO();
			listaEncabezado.add(ftecnicadmDTO);
			disenoAdicionar = false;
			this.setDisenoVisibleSalvar(false);
			this.setDisenoVisibleModificar(false);
			this.setDisenoDesabilitadoAdicionar(false);
			this.setDisenoDesabilitadoBorrar(true);
			this.setDisenoDesabilitadoCancelar(true);
			this.setDisenoDesabilitadoReset(true);
			this.setDisenoDeshabilitado(true);
			this.setDeshabilitadoEditables(true);
			this.setDescTemas("");
			this.setDescDependencia("");
			this.setDescPrograma("");
			this.setDescCodigoIndicador("");
		} else {
			this.setDisenoDeshabilitado(false);// always
			this.setDeshabilitadoEditables(false);
			this.setDescTemas("");
			this.setDescDependencia("");
			this.setDescPrograma("");
			this.setDescCodigoIndicador("");

			if (disenoAdicionar) {
				listaEncabezado.clear();
				listaEncabezado = indicadoresDisenoService.llenaListaEncabezado(idSector);
				FtecnicadmDTO ftecnicadmDTO = new FtecnicadmDTO();
				listaEncabezado.add(ftecnicadmDTO);
				this.setDisenoVisibleSalvar(true);
				this.setDisenoVisibleModificar(false);
				this.setDisenoDesabilitadoAdicionar(true);
				this.setDisenoDesabilitadoBorrar(true);
				this.setDisenoDesabilitadoCancelar(false);
				this.setDisenoDesabilitadoReset(false);
				listaDetalle = new ArrayList<FtecnicaddDTO>();
			} else {
				// load masterList with get row
				Ftecnicadm fMaster = this.ftecnicaDmRepository.findOne(listaEncabezado.get(currentPage).getId());
				FtecnicadmDTO ftDto = new FtecnicadmDTO();
				if (null != fMaster) {

					ftDto.setId(fMaster.getId());
					ftDto.setClvdep(fMaster.getClvdep());
					ftDto.setClvfin(fMaster.getClvfin());
					ftDto.setClvfun(fMaster.getClvfun());
					ftDto.setCveind(fMaster.getCveind());
					ftDto.setCvetemas(fMaster.getCvetemas());
					ftDto.setDescanual(fMaster.getDescanual());
					ftDto.setDescfac(fMaster.getDescfac());
					ftDto.setDimension(fMaster.getDimension());
					ftDto.setElaboro(fMaster.getElaboro());
					ftDto.setFactor(fMaster.getFactor());
					ftDto.setFeccap(fMaster.getFeccap());
					ftDto.setFormula(fMaster.getFormula());
					ftDto.setFrecuencia(fMaster.getFrecuencia());
					ftDto.setIdRef(0l);
					ftDto.setIdsector(this.getUserDetails().getIdSector());
					ftDto.setInterpretacion(fMaster.getInterpretacion());
					ftDto.setMedios(fMaster.getMedios());
					ftDto.setMetanuale(fMaster.getMetanuale());
					ftDto.setMetasact(fMaster.getMetasact());
					ftDto.setNomind(fMaster.getNomind());
					ftDto.setNope(fMaster.getNope());
					ftDto.setObjetivo(fMaster.getObjetivo());
					ftDto.setPe(fMaster.getPe());
					ftDto.setTema(fMaster.getTema());
					ftDto.setTipo(fMaster.getTipo());
					ftDto.setTrim1e(fMaster.getTrim1e());
					ftDto.setTrim2e(fMaster.getTrim2e());
					ftDto.setTrim3e(fMaster.getTrim3e());
					ftDto.setTrim4e(fMaster.getTrim4e());
					ftDto.setUserid(fMaster.getUserid());
					ftDto.setUsuario(fMaster.getUsuario());
					ftDto.setValido(fMaster.getValido());
					descTemas = this.cpdRepository.findOne(CpdPredicate.findByCveTema(ftDto.getCvetemas()))
							.getDescripcion();
					descDependencia = this.catdepRepository.findOne(
							CatdepPredicate.findByclvDep(ftDto.getClvdep(), this.getUserDetails().getIdSector()))
							.getNomdep();
					descPrograma = this.muniNepRepository.findOne(
							MuniNepPredicates.findByclvfun(ftDto.getClvfun(), this.getUserDetails().getIdSector()))
							.getCampo6();
					descCodigoIndicador = mirRepository.findOne(MirPredicates.findBycveInd(ftDto.getCveind()))
							.getNomind();

				}
				listaEncabezado.set(currentPage, ftDto);
			}
		}
	}

	/**
	 * metodo que adiciona un encabezado de diseño de indicadores estratégicos o de
	 * gestión.
	 *
	 * @param row the row
	 */
	public void adicionarDiseno(Integer row) {
		this.setDisenoVisibleSalvar(true);
		this.setDisenoVisibleModificar(false);
		this.setDisenoDesabilitadoAdicionar(true);
		this.setDisenoDesabilitadoBorrar(true);
		this.setDisenoDesabilitadoCancelar(false);
		this.setDisenoDesabilitadoReset(false);
		this.setDescTemas("");
		this.setDescDependencia("");
		this.setDescPrograma("");
		this.setDescCodigoIndicador("");
		disenoAdicionar = true;
		disenoModificar = false;
		if (ftecnicaDmRepository.findAll().isEmpty()) {
			this.modificarDiseno(currentPage);
		} else {
			FtecnicadmDTO ftecnicadmDTO = new FtecnicadmDTO();
			listaEncabezado.add(ftecnicadmDTO);
			this.setDisenoDeshabilitado(false);
			this.setDeshabilitadoEditables(false);

		}
		disenoDeshabilitado = FALSE;
		metaDeshabilitado = TRUE;
		deshabilitadoEditables = FALSE;

		// this.blockButtons();

	}

	/**
	 * metodo que borra un encabezado de diseño de indicadores estratégicos o de
	 * gestión.
	 *
	 * @param row the row
	 */
	public void borrarDiseno(Integer row) {
		listaEncabezado.clear();
		listaEncabezado = indicadoresDisenoService.llenaListaEncabezado(idSector);
		try {
			if (indicadoresDisenoService.tieneDetalle(listaEncabezado.get(row))) {
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"Existe detalle, no se puede eliminar este diseño de indicadores");
			} else {
				boolean resultadoDel = indicadoresDisenoService.deleteDiseno(listaEncabezado.get(row).getId());
				if (resultadoDel) {
					listaEncabezado.clear();
					listaEncabezado = indicadoresDisenoService.llenaListaEncabezado(idSector);
					if (listaEncabezado.isEmpty()) {
						FtecnicadmDTO ftecnicadmDTO = new FtecnicadmDTO();
						listaEncabezado.add(ftecnicadmDTO);
						this.setDisenoDesabilitadoBorrar(true);
						this.setDisenoVisibleModificar(false);
						this.setDisenoVisibleSalvar(false);
						// nueva meta
						if (listaDetalle.isEmpty()) {
							FtecnicaddDTO ftecnicaddDTO = new FtecnicaddDTO();
							listaDetalle.add(ftecnicaddDTO);
						}
						this.setMetaVisibleModificar(false);
						this.setMetaVisibleSalvar(false);
						this.setMetaDesabilitadoReset(true);
						this.setMetaDesabilitadoAdicionar(false);
						this.setMetaDesabilitadoBorrar(true);
						this.setMetaDesabilitadoCancelar(true);
					}
					this.setDisenoDeshabilitado(true);
					this.setDeshabilitadoEditables(true);
					this.findAllIndicadoresDisenoMetas();
					// listaEncabezado.clear();
					// limpiarListas();
					// llenarCombos();
					// llenarDiseno();
					generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
							"El registro se elimino correctamente");
				} else {
					listaEncabezado.clear();
					listaEncabezado = indicadoresDisenoService.llenaListaEncabezado(idSector);
					if (listaEncabezado.isEmpty()) {
						FtecnicadmDTO ftecnicadmDTO = new FtecnicadmDTO();
						listaEncabezado.add(ftecnicadmDTO);
						this.setDisenoDesabilitadoBorrar(true);
						this.setDisenoVisibleModificar(false);
						this.setDisenoVisibleSalvar(false);
					}
				}
			}
		} catch (Exception e) {
			generateNotificationFront(SEVERITY_ERROR, "Error!", "Ocurrió un error al borrar el Indicador de diseño");
		}
	}

	/**
	 * metodo que cancela un encabezado de diseño de indicadores estratégicos o de
	 * gestión.
	 *
	 * @param row the row
	 */
	public void cancelarDiseno(Integer row) {
		if (listaEncabezado.size() == 1 && listaEncabezado.get(currentPage).getCvetemas() == null) {
			listaEncabezado.clear();
			FtecnicadmDTO ftecnicadmDTO = new FtecnicadmDTO();
			listaEncabezado.add(ftecnicadmDTO);
			disenoAdicionar = false;
			this.setDisenoVisibleSalvar(false);
			this.setDisenoVisibleModificar(false);
			this.setDisenoDesabilitadoAdicionar(false);
			this.setDisenoDesabilitadoBorrar(true);
			this.setDisenoDesabilitadoCancelar(true);
			this.setDisenoDesabilitadoReset(true);
			this.setDisenoDeshabilitado(true);
			this.setDeshabilitadoEditables(true);
			this.setDescTemas("");
			this.setDescDependencia("");
			this.setDescPrograma("");
			this.setDescCodigoIndicador("");
		} else {
			if (listaEncabezado.size() == 1 && listaEncabezado.get(currentPage).getCvetemas() != null) {// solo
																										// tiene
																										// un
																										// registro
																										// ya
																										// en
																										// la
																										// base
				this.setDisenoVisibleSalvar(false);
				this.setDisenoVisibleModificar(true);
				this.setDisenoDesabilitadoAdicionar(false);
				this.setDisenoDesabilitadoBorrar(false);
				this.setDisenoDesabilitadoCancelar(true);
				this.setDisenoDesabilitadoReset(true);
				this.setDisenoDeshabilitado(true);
				this.setDeshabilitadoEditables(true);
				filtraTema(currentPage);
				filtraDependencia(currentPage);
				filtraPrograma(currentPage);
				filtraCodeIndicador(currentPage);
				listaEncabezado = indicadoresDisenoService.llenaListaEncabezado(idSector);
				if (listaEncabezado.isEmpty()) {
					FtecnicadmDTO ftecnicadmDTO = new FtecnicadmDTO();
					listaEncabezado.add(ftecnicadmDTO);
					this.setDisenoVisibleSalvar(false);
					this.setDisenoVisibleModificar(true);

					this.setDisenoDesabilitadoAdicionar(false);
					this.setDisenoDesabilitadoBorrar(TRUE);
					this.setDisenoDesabilitadoCancelar(true);
					this.setDisenoDesabilitadoReset(true);
					this.setDisenoDeshabilitado(true);
					this.setDeshabilitadoEditables(true);
					this.setbBtnModificar(TRUE);
				}
				this.estadoInicialBotones();

			} else {
				if (!listaEncabezado.isEmpty() && listaEncabezado.size() != 1) {// cuando
																				// hay
																				// mas
																				// de
																				// 1
																				// registro
					if (disenoAdicionar) {
						listaEncabezado.remove(listaEncabezado.size() - 1);
						disenoAdicionar = false;
						filtraTema(currentPage - 1);
						filtraDependencia(currentPage - 1);
						filtraPrograma(currentPage - 1);
						filtraCodeIndicador(currentPage - 1);
						currentPage = currentPage - 1;
					} else {
						listaEncabezado = indicadoresDisenoService.llenaListaEncabezado(idSector);
						filtraTema(currentPage);
						filtraDependencia(currentPage);
						filtraPrograma(currentPage);
						filtraCodeIndicador(currentPage);
					}
					this.setDisenoVisibleSalvar(false);
					this.setDisenoVisibleModificar(true);
					this.setDisenoDesabilitadoAdicionar(false);
					this.setDisenoDesabilitadoBorrar(false);
					this.setDisenoDesabilitadoCancelar(true);
					this.setDisenoDesabilitadoReset(true);
					this.setDisenoDeshabilitado(true);
					this.setDeshabilitadoEditables(true);
				} else {
					this.resetDiseno(row);
				}
			}
		}
		disenoDeshabilitado = TRUE;

		metaDeshabilitado = TRUE;
		deshabilitadoEditables = TRUE;
	}

	/**
	 * metodo que guarda una meta de diseño de indicadores estratégicos o de
	 * gestión.
	 *
	 */

	List<Ftecnicadd> lDetalle = null;

	/**
	 * Salvar detalle.
	 *
	 * @param row the row
	 */
	public void salvarDetalle(Integer row) {
		FtecnicadmDTO master = listaEncabezado.get(currentPage);
		if (null == listaDetalle.get(currentPageDetalle).getCvevar()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
					"Seleccione la cve Variable Update cancelled");
		} else {
			try {

				lDetalle = ftecnicaDdRepository.findByFTecnia(master.getClvdep(), master.getClvfun(),
						master.getCveind(), listaDetalle.get(currentPageDetalle).getCodigo(),
						this.getUserDetails().getIdSector());

				if (CollectionUtils.isEmpty(lDetalle) || metaModificar == TRUE) {
					listaDetalle.get(currentPageDetalle).setUsuario(this.getUserDetails().getUsername());
					listaDetalle.get(currentPageDetalle).setIdsector(this.getUserDetails().getIdSector());
					listaDetalle.get(currentPageDetalle).setClvfin(listaEncabezado.get(currentPage).getClvfin());
					listaDetalle.get(currentPageDetalle).setClvdep(listaEncabezado.get(currentPage).getClvdep());
					listaDetalle.get(currentPageDetalle).setClvfun(listaEncabezado.get(currentPage).getClvfun());
					listaDetalle.get(currentPageDetalle)
							.setCvetemas(listaEncabezado.get(currentPage).getCvetemas() != null
									? listaEncabezado.get(currentPage).getCvetemas()
									: "");
					listaDetalle.get(currentPageDetalle)
							.setCveind(listaEncabezado.get(currentPage).getCveind() != null
									? listaEncabezado.get(currentPage).getCveind()
									: "1");
					listaDetalle.get(currentPageDetalle).setFeccap(new Date());
					listaDetalle.get(currentPageDetalle).setUserid(this.getUserDetails().getUsername());
					if (metaModificar) {
						indicadoresDisenoService.salvarDetalleModificado(listaDetalle.get(currentPageDetalle));
					} else {
						indicadoresDisenoService.salvarDetalle(listaDetalle.get(currentPageDetalle));
					}
					generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
							"Indicador de Diseño - detalle guardado correctamente ");
					this.estadoInicialBotones();
					this.blockButtonsEmpty();
					this.setDisenoVisibleSalvar(FALSE);
					this.setDisenoVisibleModificar(TRUE);
					this.setDisenoDesabilitadoAdicionar(FALSE);
					this.setDisenoDesabilitadoBorrar(FALSE);
					this.setDisenoDesabilitadoCancelar(TRUE);
					this.setDisenoDesabilitadoReset(TRUE);
					deshabilitadoEditables = TRUE;
				} else {
					generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
							"El codigo ya se encuentra en la base para el indicador ");
				}

			} catch (Exception e) {
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
						"Ocurrió un error al salvar el Indicador de Diseño captura- detalle");
			}
		}
	}

	/**
	 * metodo que modifica una meta de diseño de indicadores estratégicos o de
	 * gestión.
	 *
	 * @param row the row
	 */
	public void modificarDetalle(Integer row) {
		listaDetalle.clear();
		listaDetalle = indicadoresDisenoService.llenaListaDetalle(listaEncabezado.get(currentPage));
		if (listaDetalle.isEmpty()) {
			FtecnicaddDTO ftecnicaddDTO = new FtecnicaddDTO();
			ftecnicaddDTO.setCodigo(1);
			listaDetalle.add(ftecnicaddDTO);
		}
		metaAdicionar = false;
		metaModificar = true;
		this.setMetaDeshabilitado(false);

		this.setMetaVisibleSalvar(true);
		this.setMetaVisibleModificar(false);
		this.setMetaDesabilitadoAdicionar(true);
		this.setMetaDesabilitadoBorrar(true);
		this.setMetaDesabilitadoReset(false);
		this.setMetaDesabilitadoCancelar(false);
		this.setMetaModificar(TRUE);
		deshabilitadoEditables = TRUE;
	}

	/**
	 * metodo que resetea una meta de diseño de indicadores estratégicos o de
	 * gestión.
	 *
	 * @param row the row
	 */
	public void resetDetalle(Integer row) {
		if (listaDetalle.size() == 1 && listaDetalle.get(currentPageDetalle).getCvevar() == null) {
			listaDetalle.clear();
			FtecnicaddDTO ftecnicaddDTO = new FtecnicaddDTO();
			listaDetalle.add(ftecnicaddDTO);
			metaAdicionar = false;
			this.setMetaVisibleSalvar(false);
			this.setMetaVisibleModificar(false);
			this.setMetaDesabilitadoAdicionar(false);
			this.setMetaDesabilitadoBorrar(true);
			this.setMetaDesabilitadoCancelar(true);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDeshabilitado(true);
			listaDetalle.get(currentPageDetalle).setVariables("");
		} else {
			this.setMetaDeshabilitado(false);
			if (metaAdicionar) {
				listaDetalle.clear();
				listaDetalle = indicadoresDisenoService.llenaListaDetalle(listaEncabezado.get(currentPage));
				FtecnicaddDTO ftecnicaddDTO = new FtecnicaddDTO();
				listaDetalle.add(ftecnicaddDTO);
				this.setMetaVisibleSalvar(true);
				this.setMetaVisibleModificar(false);
				this.setMetaDesabilitadoAdicionar(true);
				this.setMetaDesabilitadoBorrar(true);
				this.setMetaDesabilitadoCancelar(false);
				this.setMetaDesabilitadoReset(false);
			} else {
				listaDetalle = indicadoresDisenoService.llenaListaDetalle(listaEncabezado.get(currentPage));
			}
		}
		deshabilitadoEditables = TRUE;
	}

	/**
	 * metodo que adiciona una meta de diseño de indicadores estratégicos o de
	 * gestión.
	 *
	 * @param row the row
	 */
	public void adicionarDetalle(Integer row) {
		this.setMetaVisibleSalvar(true);
		this.setMetaVisibleModificar(false);
		this.setMetaDesabilitadoAdicionar(true);
		this.setMetaDesabilitadoBorrar(true);
		this.setMetaDesabilitadoCancelar(false);
		this.setMetaDesabilitadoReset(false);
		metaAdicionar = true;
		metaModificar = false;
		if (indicadoresDisenoService.llenaListaDetalle(listaEncabezado.get(currentPage)).isEmpty()) {
			this.modificarDetalle(currentPageDetalle);
		} else {
			FtecnicaddDTO ftecnicaddDTO = new FtecnicaddDTO();
			ftecnicaddDTO.setCodigo(listaDetalle.size() + 1);
			listaDetalle.add(ftecnicaddDTO);

			this.setMetaDeshabilitado(false);
			this.setDeshabilitadoEditables(false);
		}
		this.setDisenoVisibleSalvar(FALSE);
		this.setDisenoVisibleModificar(TRUE);
		this.setDisenoDesabilitadoAdicionar(FALSE);
		this.setDisenoDesabilitadoBorrar(FALSE);
		this.setDisenoDesabilitadoCancelar(TRUE);
		this.setDisenoDesabilitadoReset(TRUE);
		deshabilitadoEditables = TRUE;
	}

	/**
	 * metodo que borra una meta de diseño de indicadores estratégicos o de gestión.
	 *
	 * @param row the row
	 */
	public void borrarDetalle(Integer row) {
		listaDetalle.clear();
		listaDetalle = indicadoresDisenoService.llenaListaDetalle(listaEncabezado.get(currentPage));
		try {
			indicadoresDisenoService.deleteDetalle(listaDetalle.get(currentPageDetalle).getId());

			this.setMetaDeshabilitado(true);
			this.setDeshabilitadoEditables(true);
			listaDetalle.clear();
			listaDetalle = indicadoresDisenoService.llenaListaDetalle(listaEncabezado.get(currentPage));
			if (listaDetalle.isEmpty()) {
				FtecnicaddDTO ftecnicaddDTO = new FtecnicaddDTO();
				ftecnicaddDTO.setCodigo(listaDetalle.size());
				listaDetalle.add(ftecnicaddDTO);
				currentPageDetalle = 0;
				this.setMetaVisibleModificar(false);
				this.setMetaVisibleSalvar(false);
				this.setMetaDesabilitadoBorrar(true);
			} else {
				this.setMetaVisibleModificar(false);
				this.setMetaVisibleSalvar(false);
				currentPageDetalle = listaDetalle.size() - 1;
			}
			estadoInicialBotonesDetalle();
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
					"Indicador de diseño - detalle  borrado correctamente");

		} catch (Exception e) {
			generateNotificationFront(SEVERITY_ERROR, "Erro!",
					"Ocurrió un error al borrar el Indicador de diseño - detalle");
		}
		this.setMetaVisibleModificar(true);
		this.setbBtnModificar(FALSE);
		this.setMetaVisibleSalvar(false);
		this.setMetaDesabilitadoAdicionar(FALSE);
		this.setMetaDesabilitadoReset(true);
		this.setMetaDesabilitadoBorrar(FALSE);
		this.setMetaDesabilitadoCancelar(true);
		FtecnicadmDTO encabezado = listaEncabezado.get(currentPage);
		if (null != encabezado.getClvdep() && null != encabezado.getClvfun() && null != encabezado.getCveind()) {
			List<Ftecnicadd> detalle = (List<Ftecnicadd>) ftecnicaDdRepository
					.findAll(FtecnicaDdPredicate.findByClvDepAndClvFunAndClvInd(encabezado.getClvdep(),
							encabezado.getClvfun(), encabezado.getCveind(), this.getUserDetails().getIdSector()));

			if (CollectionUtils.isEmpty(detalle)) {
				this.setMetaVisibleModificar(true);
				this.setbBtnModificar(TRUE);
				this.setMetaVisibleSalvar(false);
				this.setMetaDesabilitadoAdicionar(FALSE);
				this.setMetaDesabilitadoReset(true);
				this.setMetaDesabilitadoBorrar(true);
				this.setMetaDesabilitadoCancelar(true);
			}
		}
		deshabilitadoEditables = TRUE;
	}

	/**
	 * metodo que cancela una meta de diseño de indicadores estratégicos o de
	 * gestión.
	 *
	 * @param row the row
	 */
	public void cancelarDetalle(Integer row) {
		if (listaDetalle.size() == 1 && listaDetalle.get(currentPageDetalle).getCvevar() == null) {
			listaDetalle.clear();
			FtecnicaddDTO ftecnicaddDTO = new FtecnicaddDTO();
			listaDetalle.add(ftecnicaddDTO);
			metaAdicionar = false;
			this.setMetaVisibleSalvar(false);
			this.setMetaVisibleModificar(false);
			this.setMetaDesabilitadoAdicionar(false);
			this.setMetaDesabilitadoBorrar(true);
			this.setMetaDesabilitadoCancelar(true);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDeshabilitado(true);
			listaDetalle.get(currentPageDetalle).setVariables("");
		} else {
			if (listaDetalle.size() == 1 && listaDetalle.get(currentPageDetalle).getCvevar() != null) {// solo
																										// tiene
																										// un
																										// registro
																										// ya
																										// en
																										// la
																										// base
				this.setMetaVisibleSalvar(false);
				this.setMetaVisibleModificar(true);
				this.setMetaDesabilitadoAdicionar(false);
				this.setMetaDesabilitadoBorrar(false);
				this.setMetaDesabilitadoCancelar(true);
				this.setMetaDesabilitadoReset(true);
				this.setMetaDeshabilitado(true);
				listaDetalle = indicadoresDisenoService.llenaListaDetalle(listaEncabezado.get(currentPage));
				if (listaDetalle.isEmpty()) {
					FtecnicaddDTO ftecnicaddDTO = new FtecnicaddDTO();
					listaDetalle.add(ftecnicaddDTO);
				}
			} else {
				if (!listaDetalle.isEmpty() && listaDetalle.size() != 1) {// cuando
																			// hay
																			// mas
																			// de
																			// 1
																			// registro
					if (metaAdicionar) {
						listaDetalle.remove(listaDetalle.size() - 1);
						metaAdicionar = false;
						currentPageDetalle = currentPageDetalle - 1;
					} else {
						listaDetalle = indicadoresDisenoService.llenaListaDetalle(listaEncabezado.get(currentPage));
					}
					this.setMetaVisibleSalvar(false);
					this.setMetaVisibleModificar(true);
					this.setMetaDesabilitadoAdicionar(false);
					this.setMetaDesabilitadoBorrar(false);
					this.setMetaDesabilitadoCancelar(true);
					this.setMetaDesabilitadoReset(true);
					this.setMetaDeshabilitado(true);
				} else {
					this.resetDetalle(currentPageDetalle);
				}
			}
		}

		this.setMetaVisibleModificar(true);
		this.setbBtnModificar(FALSE);
		this.setMetaVisibleSalvar(false);
		this.setMetaDesabilitadoAdicionar(FALSE);
		this.setMetaDesabilitadoReset(true);
		this.setMetaDesabilitadoBorrar(FALSE);
		this.setMetaDesabilitadoCancelar(true);
		FtecnicadmDTO encabezado = listaEncabezado.get(currentPage);
		if (null != encabezado.getClvdep() && null != encabezado.getClvfun() && null != encabezado.getCveind()) {
			List<Ftecnicadd> detalle = (List<Ftecnicadd>) ftecnicaDdRepository
					.findAll(FtecnicaDdPredicate.findByClvDepAndClvFunAndClvInd(encabezado.getClvdep(),
							encabezado.getClvfun(), encabezado.getCveind(), this.getUserDetails().getIdSector()));

			if (CollectionUtils.isEmpty(detalle)) {
				this.setMetaVisibleModificar(true);
				this.setbBtnModificar(TRUE);
				this.setMetaVisibleSalvar(false);
				this.setMetaDesabilitadoAdicionar(FALSE);
				this.setMetaDesabilitadoReset(true);
				this.setMetaDesabilitadoBorrar(true);
				this.setMetaDesabilitadoCancelar(true);
			}
		}
	}

	/**
	 * Consulta variables.
	 */
	public void consultaVariables() {
		LOGGER.info(":: Consultar Variables ");
		variables = variablesRepository.findAll();
		restartFilteredDependenciesGenearl();
	}

	/**
	 * Restart filtered dependencies genearl.
	 */
	private void restartFilteredDependenciesGenearl() {
		if (BooleanUtils.negate(filteredVariables == null) && BooleanUtils.negate(variables == null)) {
			filteredVariables.clear();
			filteredVariables.addAll(variables);
		}

	}

	/**
	 * Change variable.
	 */
	public void changeVariable() {
		if (currentPageDetalle == -1) {
			currentPageDetalle = 0;
		}

		listaDetalle.get(currentPageDetalle).setNumvar(variableSelected.getNumvar());
		listaDetalle.get(currentPageDetalle).setCvevar(variableSelected.getCvevar());
		listaDetalle.get(currentPageDetalle).setVariables(variableSelected.getNomvar());
		RequestContext.getCurrentInstance().execute("PF('variablesWdg').unselectAllRows();");
		RequestContext.getCurrentInstance().execute("PF('variablesWdg').clearFilters();");
	}

	/**
	 * On clvdgm row dbl clck select.
	 *
	 * @param event the event
	 */
	public void onClvdgmRowDblClckSelect(final SelectEvent event) {
		this.changeVariable();
		RequestContext.getCurrentInstance().execute("PF('variablesWdg').unselectAllRows();");
	}

	/**
	 * metodo que actualiza la suma total del encabezado.
	 */
	public void validarKeyUpCalendarizado() {
		listaEncabezado.get(currentPage)
				.setMetanuale((listaEncabezado.get(currentPage).getTrim1e() == null ? BigDecimal.ZERO
						: listaEncabezado.get(currentPage).getTrim1e())
								.add(listaEncabezado.get(currentPage).getTrim2e() == null ? BigDecimal.ZERO
										: listaEncabezado.get(currentPage).getTrim2e())
								.add(listaEncabezado.get(currentPage).getTrim3e() == null ? BigDecimal.ZERO
										: listaEncabezado.get(currentPage).getTrim3e())
								.add(listaEncabezado.get(currentPage).getTrim4e() == null ? BigDecimal.ZERO
										: listaEncabezado.get(currentPage).getTrim4e()));
	}

	/**
	 * Validar key up detalle.
	 */
	public void validarKeyUpDetalle() {
		listaDetalle.get(currentPageDetalle)
				.setMetanual((listaDetalle.get(currentPageDetalle).getTrim1() == null ? BigDecimal.ZERO
						: listaDetalle.get(currentPageDetalle).getTrim1())
								.add(listaDetalle.get(currentPageDetalle).getTrim2() == null ? BigDecimal.ZERO
										: listaDetalle.get(currentPageDetalle).getTrim2())
								.add(listaDetalle.get(currentPageDetalle).getTrim3() == null ? BigDecimal.ZERO
										: listaDetalle.get(currentPageDetalle).getTrim3())
								.add(listaDetalle.get(currentPageDetalle).getTrim4() == null ? BigDecimal.ZERO
										: listaDetalle.get(currentPageDetalle).getTrim4()));
	}

	/**
	 * Muestra el popup de ayuda para búsqueda de indicadores.
	 */
	public void consultIndicadores() {
		LOGGER.info(":: Consultar Indicadores ");
		indicadores = mirRepository.findAll();
		restartFilteredIndicadores();
	}

	/**
	 * Restart filtered indicadores.
	 */
	private void restartFilteredIndicadores() {
		if (BooleanUtils.negate(filteredIndicadores == null) && BooleanUtils.negate(indicadores == null)) {
			filteredIndicadores.clear();
			filteredIndicadores.addAll(indicadores);
		}
	}

	/**
	 * Change indicador.
	 */
	public void changeIndicador() {
		listaEncabezado.get(currentPage).setCveind(indicadorSelected.getCodigo());
		this.setDescCodigoIndicador(indicadorSelected.getNomind());
		RequestContext.getCurrentInstance().execute("PF('indicadorWdg').unselectAllRows();");
		RequestContext.getCurrentInstance().execute("PF('indicadorWdg').clearFilters();");
	}

	/**
	 * On ind row dbl clck select.
	 *
	 * @param event the event
	 */
	public void onIndRowDblClckSelect(final SelectEvent event) {
		this.changeIndicador();
		RequestContext.getCurrentInstance().execute("PF('indicadorWdg').unselectAllRows();");
	}

	/**
	 * Handler para evaluar los cambios en el campo código de variable. Al cambiar
	 * el valor del campo se verifica que el código exista en la tabla variables; en
	 * caso de no existir se manda un mensaje de error
	 */
	public void cambiarVariable() {
		if (listaDetalle.get(currentPageDetalle).getNumvar() == null
				|| listaDetalle.get(currentPageDetalle).getNumvar() == ZERO) {
			listaDetalle.get(currentPageDetalle).setNumvar(null);
			listaDetalle.get(currentPageDetalle).setCvevar(StringUtils.EMPTY);
			listaDetalle.get(currentPageDetalle).setVariables(StringUtils.EMPTY);
			return;
		}

		Variables variables = variablesRepository.findByNumvar(listaDetalle.get(currentPageDetalle).getNumvar());
		if (variables == null) {
			String msg = "El número de variable no existe en el catálogo. Update cancelled.";
			generateNotificationFront(SEVERITY_ERROR, msg, "Data Error");
			listaDetalle.get(currentPageDetalle).setNumvar(null);
			listaDetalle.get(currentPageDetalle).setCvevar(StringUtils.EMPTY);
			listaDetalle.get(currentPageDetalle).setVariables(StringUtils.EMPTY);
			return;
		} else {
			listaDetalle.get(currentPageDetalle).setNumvar(variables.getNumvar());
			listaDetalle.get(currentPageDetalle).setCvevar(variables.getCvevar());
			listaDetalle.get(currentPageDetalle).setVariables(variables.getNomvar());
		}
	}

	/**
	 * Cambiar codigo indicador.
	 */
	public void cambiarCodigoIndicador() {
		if (StringUtils.isEmpty(listaEncabezado.get(currentPage).getCveind())) {
			listaEncabezado.get(currentPage).setCveind(StringUtils.EMPTY);
			this.setDescCodigoIndicador(StringUtils.EMPTY);
			return;
		}
		List<Mir> mir = mirRepository.findByCodigo(listaEncabezado.get(currentPage).getCveind().toUpperCase());
		if (mir == null || mir.isEmpty()) {
			String msg = "La clave del Código Indicador no existe en el catálogo. Update cancelled.";
			generateNotificationFront(SEVERITY_ERROR, "Error!", msg);
			listaEncabezado.get(currentPage).setCveind(StringUtils.EMPTY);
			this.setDescCodigoIndicador(StringUtils.EMPTY);
			return;
		} else {
			listaEncabezado.get(currentPage).setCveind(listaEncabezado.get(currentPage).getCveind().toUpperCase());
			this.setDescCodigoIndicador(mir.get(0).getNomind());
		}
	}

	// GETTERS AND SETTER//
	/**
	 * Gets the indicadores diseno service.
	 *
	 * @return the indicadoresDisenoService
	 */
	public IndicadoresDisenoService getIndicadoresDisenoService() {
		return indicadoresDisenoService;
	}

	/**
	 * Sets the indicadores diseno service.
	 *
	 * @param indicadoresDisenoService the indicadoresDisenoService to set
	 */
	public void setIndicadoresDisenoService(IndicadoresDisenoService indicadoresDisenoService) {
		this.indicadoresDisenoService = indicadoresDisenoService;
	}

	/**
	 * Checks if is diseno desabilitado reset.
	 *
	 * @return the disenoDesabilitadoReset
	 */
	public boolean isDisenoDesabilitadoReset() {
		return disenoDesabilitadoReset;
	}

	/**
	 * Sets the diseno desabilitado reset.
	 *
	 * @param disenoDesabilitadoReset the disenoDesabilitadoReset to set
	 */
	public void setDisenoDesabilitadoReset(boolean disenoDesabilitadoReset) {
		this.disenoDesabilitadoReset = disenoDesabilitadoReset;
	}

	/**
	 * Checks if is diseno desabilitado adicionar.
	 *
	 * @return the disenoDesabilitadoAdicionar
	 */
	public boolean isDisenoDesabilitadoAdicionar() {
		return disenoDesabilitadoAdicionar;
	}

	/**
	 * Sets the diseno desabilitado adicionar.
	 *
	 * @param disenoDesabilitadoAdicionar the disenoDesabilitadoAdicionar to set
	 */
	public void setDisenoDesabilitadoAdicionar(boolean disenoDesabilitadoAdicionar) {
		this.disenoDesabilitadoAdicionar = disenoDesabilitadoAdicionar;
	}

	/**
	 * Checks if is diseno desabilitado borrar.
	 *
	 * @return the disenoDesabilitadoBorrar
	 */
	public boolean isDisenoDesabilitadoBorrar() {
		return disenoDesabilitadoBorrar;
	}

	/**
	 * Sets the diseno desabilitado borrar.
	 *
	 * @param disenoDesabilitadoBorrar the disenoDesabilitadoBorrar to set
	 */
	public void setDisenoDesabilitadoBorrar(boolean disenoDesabilitadoBorrar) {
		this.disenoDesabilitadoBorrar = disenoDesabilitadoBorrar;
	}

	/**
	 * Checks if is diseno desabilitado cancelar.
	 *
	 * @return the disenoDesabilitadoCancelar
	 */
	public boolean isDisenoDesabilitadoCancelar() {
		return disenoDesabilitadoCancelar;
	}

	/**
	 * Sets the diseno desabilitado cancelar.
	 *
	 * @param disenoDesabilitadoCancelar the disenoDesabilitadoCancelar to set
	 */
	public void setDisenoDesabilitadoCancelar(boolean disenoDesabilitadoCancelar) {
		this.disenoDesabilitadoCancelar = disenoDesabilitadoCancelar;
	}

	/**
	 * Checks if is diseno visible salvar.
	 *
	 * @return the disenoVisibleSalvar
	 */
	public boolean isDisenoVisibleSalvar() {
		return disenoVisibleSalvar;
	}

	/**
	 * Sets the diseno visible salvar.
	 *
	 * @param disenoVisibleSalvar the disenoVisibleSalvar to set
	 */
	public void setDisenoVisibleSalvar(boolean disenoVisibleSalvar) {
		this.disenoVisibleSalvar = disenoVisibleSalvar;
	}

	/**
	 * Checks if is diseno visible modificar.
	 *
	 * @return the disenoVisibleModificar
	 */
	public boolean isDisenoVisibleModificar() {
		return disenoVisibleModificar;
	}

	/**
	 * Sets the diseno visible modificar.
	 *
	 * @param disenoVisibleModificar the disenoVisibleModificar to set
	 */
	public void setDisenoVisibleModificar(boolean disenoVisibleModificar) {
		this.disenoVisibleModificar = disenoVisibleModificar;
	}

	/**
	 * Checks if is meta desabilitado reset.
	 *
	 * @return the metaDesabilitadoReset
	 */
	public boolean isMetaDesabilitadoReset() {
		return metaDesabilitadoReset;
	}

	/**
	 * Sets the meta desabilitado reset.
	 *
	 * @param metaDesabilitadoReset the metaDesabilitadoReset to set
	 */
	public void setMetaDesabilitadoReset(boolean metaDesabilitadoReset) {
		this.metaDesabilitadoReset = metaDesabilitadoReset;
	}

	/**
	 * Checks if is meta desabilitado adicionar.
	 *
	 * @return the metaDesabilitadoAdicionar
	 */
	public boolean isMetaDesabilitadoAdicionar() {
		return metaDesabilitadoAdicionar;
	}

	/**
	 * Sets the meta desabilitado adicionar.
	 *
	 * @param metaDesabilitadoAdicionar the metaDesabilitadoAdicionar to set
	 */
	public void setMetaDesabilitadoAdicionar(boolean metaDesabilitadoAdicionar) {
		this.metaDesabilitadoAdicionar = metaDesabilitadoAdicionar;
	}

	/**
	 * Checks if is meta desabilitado borrar.
	 *
	 * @return the metaDesabilitadoBorrar
	 */
	public boolean isMetaDesabilitadoBorrar() {
		return metaDesabilitadoBorrar;
	}

	/**
	 * Sets the meta desabilitado borrar.
	 *
	 * @param metaDesabilitadoBorrar the metaDesabilitadoBorrar to set
	 */
	public void setMetaDesabilitadoBorrar(boolean metaDesabilitadoBorrar) {
		this.metaDesabilitadoBorrar = metaDesabilitadoBorrar;
	}

	/**
	 * Checks if is meta desabilitado cancelar.
	 *
	 * @return the metaDesabilitadoCancelar
	 */
	public boolean isMetaDesabilitadoCancelar() {
		return metaDesabilitadoCancelar;
	}

	/**
	 * Sets the meta desabilitado cancelar.
	 *
	 * @param metaDesabilitadoCancelar the metaDesabilitadoCancelar to set
	 */
	public void setMetaDesabilitadoCancelar(boolean metaDesabilitadoCancelar) {
		this.metaDesabilitadoCancelar = metaDesabilitadoCancelar;
	}

	/**
	 * Checks if is meta visible salvar.
	 *
	 * @return the metaVisibleSalvar
	 */
	public boolean isMetaVisibleSalvar() {
		return metaVisibleSalvar;
	}

	/**
	 * Sets the meta visible salvar.
	 *
	 * @param metaVisibleSalvar the metaVisibleSalvar to set
	 */
	public void setMetaVisibleSalvar(boolean metaVisibleSalvar) {
		this.metaVisibleSalvar = metaVisibleSalvar;
	}

	/**
	 * Checks if is meta visible modificar.
	 *
	 * @return the metaVisibleModificar
	 */
	public boolean isMetaVisibleModificar() {
		return metaVisibleModificar;
	}

	/**
	 * Sets the meta visible modificar.
	 *
	 * @param metaVisibleModificar the metaVisibleModificar to set
	 */
	public void setMetaVisibleModificar(boolean metaVisibleModificar) {
		this.metaVisibleModificar = metaVisibleModificar;
	}

	/**
	 * Checks if is diseno deshabilitado.
	 *
	 * @return the disenoDeshabilitado
	 */
	public boolean isDisenoDeshabilitado() {
		return disenoDeshabilitado;
	}

	/**
	 * Sets the diseno deshabilitado.
	 *
	 * @param disenoDeshabilitado the disenoDeshabilitado to set
	 */
	public void setDisenoDeshabilitado(boolean disenoDeshabilitado) {
		this.disenoDeshabilitado = disenoDeshabilitado;
	}

	/**
	 * Checks if is meta deshabilitado.
	 *
	 * @return the metaDeshabilitado
	 */
	public boolean isMetaDeshabilitado() {
		return metaDeshabilitado;
	}

	/**
	 * Sets the meta deshabilitado.
	 *
	 * @param metaDeshabilitado the metaDeshabilitado to set
	 */
	public void setMetaDeshabilitado(boolean metaDeshabilitado) {
		this.metaDeshabilitado = metaDeshabilitado;
	}

	/**
	 * Checks if is diseno adicionar.
	 *
	 * @return the disenoAdicionar
	 */
	public boolean isDisenoAdicionar() {
		return disenoAdicionar;
	}

	/**
	 * Sets the diseno adicionar.
	 *
	 * @param disenoAdicionar the disenoAdicionar to set
	 */
	public void setDisenoAdicionar(boolean disenoAdicionar) {
		this.disenoAdicionar = disenoAdicionar;
	}

	/**
	 * Checks if is meta adicionar.
	 *
	 * @return the metaAdicionar
	 */
	public boolean isMetaAdicionar() {
		return metaAdicionar;
	}

	/**
	 * Sets the meta adicionar.
	 *
	 * @param metaAdicionar the metaAdicionar to set
	 */
	public void setMetaAdicionar(boolean metaAdicionar) {
		this.metaAdicionar = metaAdicionar;
	}

	/**
	 * Checks if is deshabilitado editables.
	 *
	 * @return the deshabilitadoEditables
	 */
	public boolean isDeshabilitadoEditables() {
		return deshabilitadoEditables;
	}

	/**
	 * Sets the deshabilitado editables.
	 *
	 * @param deshabilitadoEditables the deshabilitadoEditables to set
	 */
	public void setDeshabilitadoEditables(boolean deshabilitadoEditables) {
		this.deshabilitadoEditables = deshabilitadoEditables;
	}

	/**
	 * Gets the lista temas.
	 *
	 * @return the listaTemas
	 */
	public List<Cpd> getListaTemas() {
		return listaTemas;
	}

	/**
	 * Sets the lista temas.
	 *
	 * @param listaTemas the listaTemas to set
	 */
	public void setListaTemas(List<Cpd> listaTemas) {
		this.listaTemas = listaTemas;
	}

	/**
	 * Gets the lista dependencias.
	 *
	 * @return the lista dependencias
	 */
	public List<String> getListaDependencias() {
		return listaDependencias;
	}

	/**
	 * Sets the lista dependencias.
	 *
	 * @param listaDependencias the new lista dependencias
	 */
	public void setListaDependencias(List<String> listaDependencias) {
		this.listaDependencias = listaDependencias;
	}

	/**
	 * Gets the lista programas.
	 *
	 * @return the listaProgramas
	 */
	public List<String> getListaProgramas() {
		return listaProgramas;
	}

	/**
	 * Sets the lista programas.
	 *
	 * @param listaProgramas the listaProgramas to set
	 */
	public void setListaProgramas(List<String> listaProgramas) {
		this.listaProgramas = listaProgramas;
	}

	/**
	 * Gets the lista codigos indicador.
	 *
	 * @return the listaCodigosIndicador
	 */
	public List<Mir> getListaCodigosIndicador() {
		return listaCodigosIndicador;
	}

	/**
	 * Sets the lista codigos indicador.
	 *
	 * @param listaCodigosIndicador the listaCodigosIndicador to set
	 */
	public void setListaCodigosIndicador(List<Mir> listaCodigosIndicador) {
		this.listaCodigosIndicador = listaCodigosIndicador;
	}

	/**
	 * Gets the lista frecuencia.
	 *
	 * @return the listaFrecuencia
	 */
	public List<String> getListaFrecuencia() {
		return listaFrecuencia;
	}

	/**
	 * Sets the lista frecuencia.
	 *
	 * @param listaFrecuencia the listaFrecuencia to set
	 */
	public void setListaFrecuencia(List<String> listaFrecuencia) {
		this.listaFrecuencia = listaFrecuencia;
	}

	/**
	 * Gets the lista tipo indicador.
	 *
	 * @return the listaTipoIndicador
	 */
	public List<String> getListaTipoIndicador() {
		return listaTipoIndicador;
	}

	/**
	 * Sets the lista tipo indicador.
	 *
	 * @param listaTipoIndicador the listaTipoIndicador to set
	 */
	public void setListaTipoIndicador(List<String> listaTipoIndicador) {
		this.listaTipoIndicador = listaTipoIndicador;
	}

	/**
	 * Gets the lista encabezado.
	 *
	 * @return the listaEncabezado
	 */
	public List<FtecnicadmDTO> getListaEncabezado() {
		return listaEncabezado;
	}

	/**
	 * Sets the lista encabezado.
	 *
	 * @param listaEncabezado the listaEncabezado to set
	 */
	public void setListaEncabezado(List<FtecnicadmDTO> listaEncabezado) {
		this.listaEncabezado = listaEncabezado;
	}

	/**
	 * Gets the lista detalle.
	 *
	 * @return the listaDetalle
	 */
	public List<FtecnicaddDTO> getListaDetalle() {
		return listaDetalle;
	}

	/**
	 * Sets the lista detalle.
	 *
	 * @param listaDetalle the listaDetalle to set
	 */
	public void setListaDetalle(List<FtecnicaddDTO> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}

	/**
	 * Gets the desc temas.
	 *
	 * @return the descTemas
	 */
	public String getDescTemas() {
		return descTemas;
	}

	/**
	 * Sets the desc temas.
	 *
	 * @param descTemas the descTemas to set
	 */
	public void setDescTemas(String descTemas) {
		this.descTemas = descTemas;
	}

	/**
	 * Gets the desc dependencia.
	 *
	 * @return the descDependencia
	 */
	public String getDescDependencia() {
		return descDependencia;
	}

	/**
	 * Sets the desc dependencia.
	 *
	 * @param descDependencia the descDependencia to set
	 */
	public void setDescDependencia(String descDependencia) {
		this.descDependencia = descDependencia;
	}

	/**
	 * Gets the desc programa.
	 *
	 * @return the descPrograma
	 */
	public String getDescPrograma() {
		return descPrograma;
	}

	/**
	 * Sets the desc programa.
	 *
	 * @param descPrograma the descPrograma to set
	 */
	public void setDescPrograma(String descPrograma) {
		this.descPrograma = descPrograma;
	}

	/**
	 * Gets the desc codigo indicador.
	 *
	 * @return the descCodigoIndicador
	 */
	public String getDescCodigoIndicador() {
		return descCodigoIndicador;
	}

	/**
	 * Sets the desc codigo indicador.
	 *
	 * @param descCodigoIndicador the descCodigoIndicador to set
	 */
	public void setDescCodigoIndicador(String descCodigoIndicador) {
		this.descCodigoIndicador = descCodigoIndicador;
	}

	/**
	 * Gets the cvetemas.
	 *
	 * @return the cvetemas
	 */
	public String getCvetemas() {
		return cvetemas;
	}

	/**
	 * Sets the cvetemas.
	 *
	 * @param cvetemas the cvetemas to set
	 */
	public void setCvetemas(String cvetemas) {
		this.cvetemas = cvetemas;
	}

	/**
	 * Gets the clvdep.
	 *
	 * @return the clvdep
	 */
	public String getClvdep() {
		return clvdep;
	}

	/**
	 * Sets the clvdep.
	 *
	 * @param clvdep the clvdep to set
	 */
	public void setClvdep(String clvdep) {
		this.clvdep = clvdep;
	}

	/**
	 * Gets the clvfun.
	 *
	 * @return the clvfun
	 */
	public String getClvfun() {
		return clvfun;
	}

	/**
	 * Sets the clvfun.
	 *
	 * @param clvfun the clvfun to set
	 */
	public void setClvfun(String clvfun) {
		this.clvfun = clvfun;
	}

	/**
	 * Gets the cveind.
	 *
	 * @return the cveind
	 */
	public String getCveind() {
		return cveind;
	}

	/**
	 * Sets the cveind.
	 *
	 * @param cveind the cveind to set
	 */
	public void setCveind(String cveind) {
		this.cveind = cveind;
	}

	/**
	 * Gets the current page.
	 *
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * Sets the current page.
	 *
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * Gets the current page detalle.
	 *
	 * @return the currentPageDetalle
	 */
	public int getCurrentPageDetalle() {
		return currentPageDetalle;
	}

	/**
	 * Sets the current page detalle.
	 *
	 * @param currentPageDetalle the currentPageDetalle to set
	 */
	public void setCurrentPageDetalle(int currentPageDetalle) {
		this.currentPageDetalle = currentPageDetalle;
	}

	/**
	 * Gets the lista variables.
	 *
	 * @return the listaVariables
	 */
	public List<Variables> getListaVariables() {
		return listaVariables;
	}

	/**
	 * Sets the lista variables.
	 *
	 * @param listaVariables the listaVariables to set
	 */
	public void setListaVariables(List<Variables> listaVariables) {
		this.listaVariables = listaVariables;
	}

	/**
	 * Gets the variables.
	 *
	 * @return the variables
	 */
	public List<Variables> getVariables() {
		return variables;
	}

	/**
	 * Sets the variables.
	 *
	 * @param variables the new variables
	 */
	public void setVariables(List<Variables> variables) {
		this.variables = variables;
	}

	/**
	 * Gets the variables repository.
	 *
	 * @return the variables repository
	 */
	public VariablesRepository getVariablesRepository() {
		return variablesRepository;
	}

	/**
	 * Sets the variables repository.
	 *
	 * @param variablesRepository the new variables repository
	 */
	public void setVariablesRepository(VariablesRepository variablesRepository) {
		this.variablesRepository = variablesRepository;
	}

	/**
	 * Gets the filtered variables.
	 *
	 * @return the filtered variables
	 */
	public List<Variables> getFilteredVariables() {
		return filteredVariables;
	}

	/**
	 * Sets the filtered variables.
	 *
	 * @param filteredVariables the new filtered variables
	 */
	public void setFilteredVariables(List<Variables> filteredVariables) {
		this.filteredVariables = filteredVariables;
	}

	/**
	 * Gets the variable selected.
	 *
	 * @return the variable selected
	 */
	public Variables getVariableSelected() {
		return variableSelected;
	}

	/**
	 * Sets the variable selected.
	 *
	 * @param variableSelected the new variable selected
	 */
	public void setVariableSelected(Variables variableSelected) {
		this.variableSelected = variableSelected;
	}

	/**
	 * Gets the filtered indicadores.
	 *
	 * @return the filtered indicadores
	 */
	public List<Mir> getFilteredIndicadores() {
		return filteredIndicadores;
	}

	/**
	 * Sets the filtered indicadores.
	 *
	 * @param filteredIndicadores the new filtered indicadores
	 */
	public void setFilteredIndicadores(List<Mir> filteredIndicadores) {
		this.filteredIndicadores = filteredIndicadores;
	}

	/**
	 * Gets the indicadores.
	 *
	 * @return the indicadores
	 */
	public List<Mir> getIndicadores() {
		return indicadores;
	}

	/**
	 * Sets the indicadores.
	 *
	 * @param indicadores the new indicadores
	 */
	public void setIndicadores(List<Mir> indicadores) {
		this.indicadores = indicadores;
	}

	/**
	 * Gets the master row selected.
	 *
	 * @return the master row selected
	 */
	public int getMasterRowSelected() {
		return masterRowSelected;
	}

	/**
	 * Sets the master row selected.
	 *
	 * @param masterRowSelected the new master row selected
	 */
	public void setMasterRowSelected(int masterRowSelected) {
		this.masterRowSelected = masterRowSelected;
	}

	/**
	 * Gets the indicador selected.
	 *
	 * @return the indicador selected
	 */
	public Mir getIndicadorSelected() {
		return indicadorSelected;
	}

	/**
	 * Sets the indicador selected.
	 *
	 * @param indicadorSelected the new indicador selected
	 */
	public void setIndicadorSelected(Mir indicadorSelected) {
		this.indicadorSelected = indicadorSelected;
	}

	/**
	 * Gets the mir repository.
	 *
	 * @return the mir repository
	 */
	public MirRepository getMirRepository() {
		return mirRepository;
	}

	/**
	 * Sets the mir repository.
	 *
	 * @param mirRepository the new mir repository
	 */
	public void setMirRepository(MirRepository mirRepository) {
		this.mirRepository = mirRepository;
	}

	/**
	 * Checks if is tiene datos encabezado.
	 *
	 * @return true, if is tiene datos encabezado
	 */
	public boolean isTieneDatosEncabezado() {
		return tieneDatosEncabezado;
	}

	/**
	 * Sets the tiene datos encabezado.
	 *
	 * @param tieneDatosEncabezado the new tiene datos encabezado
	 */
	public void setTieneDatosEncabezado(boolean tieneDatosEncabezado) {
		this.tieneDatosEncabezado = tieneDatosEncabezado;
	}

	/**
	 * Checks if is diseno modificar.
	 *
	 * @return true, if is diseno modificar
	 */
	public boolean isDisenoModificar() {
		return disenoModificar;
	}

	/**
	 * Sets the diseno modificar.
	 *
	 * @param disenoModificar the new diseno modificar
	 */
	public void setDisenoModificar(boolean disenoModificar) {
		this.disenoModificar = disenoModificar;
	}

	/**
	 * Checks if is meta modificar.
	 *
	 * @return true, if is meta modificar
	 */
	public boolean isMetaModificar() {
		return metaModificar;
	}

	/**
	 * Sets the meta modificar.
	 *
	 * @param metaModificar the new meta modificar
	 */
	public void setMetaModificar(boolean metaModificar) {
		this.metaModificar = metaModificar;
	}

	/**
	 * Gets the ftecnica dd repository.
	 *
	 * @return the ftecnica dd repository
	 */
	public FtecnicaDdRepository getFtecnicaDdRepository() {
		return ftecnicaDdRepository;
	}

	/**
	 * Sets the ftecnica dd repository.
	 *
	 * @param ftecnicaDdRepository the new ftecnica dd repository
	 */
	public void setFtecnicaDdRepository(FtecnicaDdRepository ftecnicaDdRepository) {
		this.ftecnicaDdRepository = ftecnicaDdRepository;
	}

	/**
	 * Gets the ftecnica dm repository.
	 *
	 * @return the ftecnica dm repository
	 */
	public FtecnicaDmRepository getFtecnicaDmRepository() {
		return ftecnicaDmRepository;
	}

	/**
	 * Sets the ftecnica dm repository.
	 *
	 * @param ftecnicaDmRepository the new ftecnica dm repository
	 */
	public void setFtecnicaDmRepository(FtecnicaDmRepository ftecnicaDmRepository) {
		this.ftecnicaDmRepository = ftecnicaDmRepository;
	}

	/**
	 * Gets the muni nep repository.
	 *
	 * @return the muni nep repository
	 */
	public MuniNepRepository getMuniNepRepository() {
		return muniNepRepository;
	}

	/**
	 * Sets the muni nep repository.
	 *
	 * @param muniNepRepository the new muni nep repository
	 */
	public void setMuniNepRepository(MuniNepRepository muniNepRepository) {
		this.muniNepRepository = muniNepRepository;
	}

	/**
	 * Block buttons.
	 */
	public void blockButtons() {

		this.setMetaVisibleModificar(true);
		this.setbBtnModificar(TRUE);
		this.setMetaVisibleSalvar(false);
		this.setMetaDesabilitadoAdicionar(true);
		this.setMetaDesabilitadoReset(true);
		this.setMetaDesabilitadoBorrar(true);
		this.setMetaDesabilitadoCancelar(true);

	}

	/**
	 * Block buttons empty.
	 */
	public void blockButtonsEmpty() {

		this.setMetaVisibleModificar(true);
		this.setbBtnModificar(FALSE);
		this.setMetaVisibleSalvar(false);
		this.setMetaDesabilitadoAdicionar(FALSE);
		this.setMetaDesabilitadoReset(true);
		this.setMetaDesabilitadoBorrar(FALSE);
		this.setMetaDesabilitadoCancelar(true);
		FtecnicadmDTO encabezado = listaEncabezado.get(currentPage);
		if (null != encabezado.getClvdep() && null != encabezado.getClvfun() && null != encabezado.getCveind()) {
			List<Ftecnicadd> detalle = (List<Ftecnicadd>) ftecnicaDdRepository
					.findAll(FtecnicaDdPredicate.findByClvDepAndClvFunAndClvInd(encabezado.getClvdep(),
							encabezado.getClvfun(), encabezado.getCveind(), this.getUserDetails().getIdSector()));

			if (CollectionUtils.isEmpty(detalle)) {
				this.setMetaVisibleModificar(true);
				this.setbBtnModificar(TRUE);
				this.setMetaVisibleSalvar(false);
				this.setMetaDesabilitadoAdicionar(FALSE);
				this.setMetaDesabilitadoReset(true);
				this.setMetaDesabilitadoBorrar(true);
				this.setMetaDesabilitadoCancelar(true);

			}
		} else {

			this.setMetaVisibleModificar(true);
			this.setbBtnModificar(TRUE);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoAdicionar(TRUE);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoBorrar(true);
			this.setMetaDesabilitadoCancelar(true);

		}
	}
}