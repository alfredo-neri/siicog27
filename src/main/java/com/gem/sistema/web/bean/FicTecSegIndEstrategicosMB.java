package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.data.PageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Cpd;
import com.gem.sistema.business.domain.Ftecnicasd;
import com.gem.sistema.business.domain.Ftecnicasm;
import com.gem.sistema.business.domain.Mir;
import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.domain.Variables;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.repository.catalogos.VariablesRepository;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.CpdRepository;
import com.gem.sistema.business.repository.catalogs.FtecnicasdRepository;
import com.gem.sistema.business.repository.catalogs.FtecnicasmRepository;
import com.gem.sistema.business.repository.catalogs.MirRepository;
import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;

// TODO: Auto-generated Javadoc
/**
 * Managed Bean para el control del módulo Ficha tecnica para el seguimiento de
 * indicadores de gestión o estrategicos.
 *
 * @author BIITEC
 */
@Component(value = "ficTecSegIndEstrategicosMB")
@ManagedBean
@ViewScoped
public class FicTecSegIndEstrategicosMB extends AbstractMB implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5290988733113521401L;

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(FicTecSegIndEstrategicosMB.class);

	/** The ftecnicasm repository. */
	@Autowired
	private FtecnicasmRepository ftecnicasmRepository;

	/** The ftecnicasd repository. */
	@Autowired
	private FtecnicasdRepository ftecnicasdRepository;

	/** The cpd repository. */
	@Autowired
	private CpdRepository cpdRepository;

	/** The xcatpro repository. */
	@Autowired
	private XcatproRepository xcatproRepository;

	/** The mir repository. */
	@Autowired
	private MirRepository mirRepository;

	/** The catdep repository. */
	@Autowired
	private CatdepRepository catdepRepository;

	/** The variables repository. */
	@Autowired
	private VariablesRepository variablesRepository;

	/** The muni nep repository. */
	@Autowired
	private MuniNepRepository muniNepRepository;

	/** The encabezados list. */
	private List<Ftecnicasm> encabezadosList;

	/** The detalles list. */
	private List<Ftecnicasd> detallesList;

	/** The tema orig. */
	private String temaOrig;

	/** The dependencia orig. */
	private String dependenciaOrig;

	/** The programa orig. */
	private String programaOrig;

	/** The cod ind orig. */
	private String codIndOrig;

	/** The tiene detalles. */
	private boolean tieneDetalles;

	/** The puede agregar detalles. */
	private boolean puedeAgregarDetalles;

	/** The captura deshabilitado. */
	private boolean capturaDeshabilitado;

	/** The capt det deshabilitado. */
	private boolean captDetDeshabilitado;

	/** The salvar encabezado visible. */
	private boolean salvarEncabezadoVisible;

	/** The salvar detalle visible. */
	private boolean salvarDetalleVisible;

	/** The habilitar modificar. */
	private boolean habilitarModificar;

	/** The des tema. */
	private String desTema;

	/** The des dep. */
	private String desDep;

	/** The des prog. */
	private String desProg;

	/** The des cod ind. */
	private String desCodInd;

	/** The des id tema. */
	private String desIdTema;

	/** The des id dep. */
	private String desIdDep;

	/** The des id prog. */
	private String desIdProg;

	/** The des id cod ind. */
	private String desIdCodInd;

	/** The clv temas list. */
	private List<String> clvTemasList;

	/** The clv dependencias list. */
	private List<String> clvDependenciasList;

	/** The clv programas list. */
	private List<String> clvProgramasList;

	/** The clv codigos ind list. */
	private List<String> clvCodigosIndList;

	/** The frecuenci list. */
	private List<String> frecuenciList;

	/** The filtered variables. */
	private List<Variables> filteredVariables;

	/** The variables. */
	private List<Variables> variables;

	/** The detalle row selected. */
	private int detalleRowSelected;

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

	private boolean encabezadoAdicionar;

	private boolean encabezadoModificar;

	private boolean detalleAdicionar;

	private boolean detalleModificar;

	/**
	 * Inicializa los componentes de la pagina.
	 */
	public void init() {
		cargarCatalogos();
		setBaderas();
		cargarIndicadores(0);
	}

	/**
	 * realiza una consulta en la base de datos para obtener los indicadores a
	 * mostrar.
	 *
	 * @param row the row
	 */
	public void cargarIndicadores(Integer row) {
		encabezadosList = ftecnicasmRepository
				.findByIdsectorOrderByClvdepAscClvfunAsc(getUserDetails().getIdSector());
		if (encabezadosList == null || encabezadosList.isEmpty()) {
			logger.debug("No se encontraron registros de encabezado.");
			encabezadosList = new ArrayList<Ftecnicasm>();
			encabezadosList.add(new Ftecnicasm());
			habilitarModificar = true;
		} else {
			habilitarModificar = false;
		}
		Integer index = encabezadosList.size() - 1;
		inicializarEncabezado(index);
		if (CollectionUtils.isNotEmpty(encabezadosList)) {
			this.cargarDetalles(encabezadosList.get(index).getCvetemas(), encabezadosList.get(index).getClvdep(),
					encabezadosList.get(index).getClvfun(), encabezadosList.get(index).getCveind());
		}
	}

	/**
	 * Inicializa los componentes de la sección encabezado para un objeto ficha
	 * tecnica dado.
	 *
	 * @param index El Indice del objeto ficha tecnica en la lista general
	 */
	private void inicializarEncabezado(int index) {
		Ftecnicasm ft = encabezadosList.get(index);
		temaOrig = ft.getCvetemas();
		dependenciaOrig = ft.getClvdep();
		programaOrig = ft.getClvfun();
		codIndOrig = ft.getCveind();

		cargarDetalles(ft.getCvetemas(), ft.getClvdep(), ft.getClvfun(), ft.getCveind());

		puedeAgregarDetalles = ft.getId() != null && ft.getId().intValue() != 0;
		changeTema(index);
		changeDependencia(index);
		changePrograma(index);
		changeCodInd(index);
	}

	/**
	 * Inicailiza los componentes de la sección detalles para una configuración de
	 * ficha tecnica dada.
	 *
	 * @param tema        Clave del tema para la configuración de la ficha
	 * @param dependencia Dependencia para la configuración de la ficha
	 * @param programa    Clave del Programa para la configuración de la ficha
	 * @param ind         Clave del indicador para la configuración de la ficha
	 */
	private void cargarDetalles(String tema, String dependencia, String programa, String ind) {
		logger.debug("Cvetemas: " + tema + ",Clvdep: " + dependencia + ",Clvfun: " + programa + ",Cveind: " + ind);
		detallesList = ftecnicasdRepository.findByCvetemasAndClvdepAndClvfunAndCveind(tema, dependencia, programa, ind);
		tieneDetalles = detallesList != null && !detallesList.isEmpty();
		if (!tieneDetalles) {
			detallesList = new ArrayList<Ftecnicasd>();
			Ftecnicasd ftd = new Ftecnicasd();
			ftd.setCodigo(0);
			detallesList.add(ftd);
		}
	}

	/**
	 * Obtiene la información sobre los catalogos utilizados en la pagina.
	 */
	private void cargarCatalogos() {
		clvTemasList = new ArrayList<String>();
		List<Cpd> temas = cpdRepository.findAll();
		for (Cpd tema : temas) {
			if (!StringUtils.isEmpty(tema.getTema()) && tema.getCvetemas().length() == 8) {
				clvTemasList.add(tema.getCvetemas());
			}
		}

		clvDependenciasList = new ArrayList<String>();
		// List<Xcatpro> dependencias =
		// xcatproRepository.findCustomDistinct(getUserDetails().getIdSector());
		List<Xcatpro> dependencias = xcatproRepository.findBySectoridOrderByClvdep(getUserDetails().getIdSector());
		for (Xcatpro dependencia : dependencias) {
			if (!clvDependenciasList.contains(dependencia.getClvdep())) {
				clvDependenciasList.add(dependencia.getClvdep());
			}
		}
		clvProgramasList = new ArrayList<String>();
		clvCodigosIndList = new ArrayList<String>();
		List<Mir> codInds = mirRepository.findAll();
		for (Mir codInd : codInds) {
			clvCodigosIndList.add(codInd.getCodigo());
		}

		frecuenciList = new ArrayList<String>();
		frecuenciList.add("Mensual");
		frecuenciList.add("Trimestral");
		frecuenciList.add("Semestral");
		frecuenciList.add("Anual");
	}

	/**
	 * Handler para el evento de cambio de paginas de la tabla en el encabezado.
	 *
	 * @param event the event
	 */
	public void cambiarPagina(PageEvent event) {
		int paginaActual = event.getPage();
		logger.debug("paginaActual: " + paginaActual);
		inicializarEncabezado(paginaActual);
		setBanderasEditEncabezado(false);
	}

	/**
	 * Inicializa las banderas que definen comportamiento visual de los componentes
	 * de la pagina.
	 */
	private void setBaderas() {
		capturaDeshabilitado = true;
		captDetDeshabilitado = true;
		salvarEncabezadoVisible = false;
		salvarDetalleVisible = false;
	}

	/**
	 * Modifica las banderas para habilitar/deshabilitar el modo de edición del
	 * encabezado.
	 *
	 * @param edit Cuando es <code>true</code>, habilita la edición, en caso
	 *             contrario la deshabilita
	 */
	private void setBanderasEditEncabezado(boolean edit) {
		capturaDeshabilitado = !edit;
		salvarEncabezadoVisible = edit;
	}

	/**
	 * Modifica las banderas para habilitar/deshabilitar el modo de edición del
	 * detalle.
	 *
	 * @param edit Cuando es <code>true</code>, habilita la edición, en caso
	 *             contrario la deshabilita
	 */
	private void setBanderasEditDetalle(boolean edit) {
		captDetDeshabilitado = !edit;
		salvarDetalleVisible = edit;
	}

	/**
	 * Persiste en la base de datos los cambios realizados en el encabezado de la
	 * ficha tecnica seleccionada.
	 *
	 * @param row Inidice de la Ficha tecnica seleccionada
	 */
	public void salvarEncabezado(Integer row) {
		Ftecnicasm ft = encabezadosList.get(row);
		if (validarEncabezado(ft)) {
			try {
				ft.setClvfin(""); // TODO de dónde sale este dato?
				ft.setIdsector(getUserDetails().getIdSector());
				ft.setUserid(getUserDetails().getUsername());
				ft.setFeccap(new Date());
				ft.setIdRef(0L);
				
				if(ft.getMetanuale1() == null)
					ft.setMetanuale1(BigDecimal.ZERO);
				
				if(ft.getProge1() == null)
					ft.setProge1(BigDecimal.ZERO);
				
				if(ft.getPorcproge1() == null)
					ft.setPorcproge1(BigDecimal.ZERO);
				
				if(ft.getAlcane1() == null)
					ft.setAlcane1(BigDecimal.ZERO);
				
				if(ft.getSemaforo11() == null)
					ft.setSemaforo11(StringUtils.EMPTY);
				
				if(ft.getSemaforo21() == null)
					ft.setSemaforo21(StringUtils.EMPTY);
				
				if(ft.getMetanuale2() == null)
					ft.setMetanuale2(BigDecimal.ZERO);
				
				if(ft.getProge2() == null)
					ft.setProge2(BigDecimal.ZERO);
				
				if(ft.getPorcproge2() == null)
					ft.setPorcproge2(BigDecimal.ZERO);
				
				if(ft.getAlcane2() == null)
					ft.setAlcane2(BigDecimal.ZERO);
				
				if(ft.getSemaforo12() == null)
					ft.setSemaforo12(StringUtils.EMPTY);
				
				if(ft.getSemaforo22() == null)
					ft.setSemaforo22(StringUtils.EMPTY);
				
				if(ft.getMetanuale3() == null)
					ft.setMetanuale3(BigDecimal.ZERO);
				
				if(ft.getProge3() == null)
					ft.setProge3(BigDecimal.ZERO);
				
				if(ft.getPorcproge3() == null)
					ft.setPorcproge3(BigDecimal.ZERO);
				
				if(ft.getAlcane3() == null)
					ft.setAlcane3(BigDecimal.ZERO);
				
				if(ft.getSemaforo13() == null)
					ft.setSemaforo13(StringUtils.EMPTY);
				
				if(ft.getSemaforo23() == null)
					ft.setSemaforo23(StringUtils.EMPTY);
				
				if(ft.getMetanuale4() == null)
					ft.setMetanuale4(BigDecimal.ZERO);
				
				if(ft.getProge4() == null)
					ft.setProge4(BigDecimal.ZERO);
				
				if(ft.getPorcproge4() == null)
					ft.setPorcproge4(BigDecimal.ZERO);
				
				if(ft.getAlcane4() == null)
					ft.setAlcane4(BigDecimal.ZERO);
				
				if(ft.getSemaforo14() == null)
					ft.setSemaforo14(StringUtils.EMPTY);
				
				if(ft.getSemaforo24() == null)
					ft.setSemaforo24(StringUtils.EMPTY);
				
				if(ft.getPorcalcane1() == null)
					ft.setPorcalcane1(BigDecimal.ZERO);
				
				if(ft.getPorcalcane2() == null)
					ft.setPorcalcane2(BigDecimal.ZERO);
				
				if(ft.getPorcalcane3() == null)
					ft.setPorcalcane3(BigDecimal.ZERO);
				
				if(ft.getPorcalcane4() == null)
					ft.setPorcalcane4(BigDecimal.ZERO);
				
				ft = ftecnicasmRepository.save(ft);

				cargarIndicadores(row);
				irAPaginaEncabezado(row);
				inicializarEncabezado(row);

				puedeAgregarDetalles = true;
				setBanderasEditEncabezado(false);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "La informacion se guardó correctamente",
						"Salvar Encabezado");
			} catch (Exception e) {
				e.printStackTrace();
				generateNotificationFront(SEVERITY_ERROR, "Ocurrió un error al guardar la información: ", "Data Error");
			}
		}
	}

	/**
	 * Verifica que se cumplan las reglas de negocio definidas para el encabezado de
	 * la ficha tecnica.
	 *
	 * @param ft Ficha tecnica a validar
	 * @return <code>true</code> si todas las reglas se cumplen; <code>false</code>
	 *         en caso contrario
	 */
	private boolean validarEncabezado(Ftecnicasm ft) {
		if (StringUtils.isEmpty(ft.getCvetemas())) {
			String msg = "El Campo Cve Temas es obligatorio.";
			generateNotificationFront(SEVERITY_ERROR, msg, "Data Error");
			return false;
		}

		if (StringUtils.isEmpty(ft.getClvdep())) {
			String msg = "El Campo Dependencia es obligatorio.";
			generateNotificationFront(SEVERITY_ERROR, msg, "Data Error");
			return false;
		}

		if (StringUtils.isEmpty(ft.getClvfun())) {
			String msg = "El Campo Programa es obligatorio.";
			generateNotificationFront(SEVERITY_ERROR, msg, "Data Error");
			return false;
		}

		if (StringUtils.isEmpty(ft.getCveind())) {
			String msg = "El Campo Código de Indicador es obligatorio.";
			generateNotificationFront(SEVERITY_ERROR, msg, "Data Error");
			return false;
		}

		if (StringUtils.isEmpty(ft.getFrecuencia())) {
			String msg = "El Campo Frecuencia es obligatorio.";
			generateNotificationFront(SEVERITY_ERROR, msg, "Data Error");
			return false;
		}

		if (tieneDetalles) {
			if (!ft.getCvetemas().equals(temaOrig)) {
				String msg = "No se puede cambiar el Campo Cve Temas. Tiene detalles.";
				generateNotificationFront(SEVERITY_ERROR, msg, "Data Error");
				return false;
			}
			if (!ft.getClvdep().equals(dependenciaOrig)) {
				String msg = "No se puede cambiar el Campo Dependencia. Tiene detalles.";
				generateNotificationFront(SEVERITY_ERROR, msg, "Data Error");
				return false;
			}
			if (!ft.getClvfun().equals(programaOrig)) {
				String msg = "No se puede cambiar el Campo Programa. Tiene detalles.";
				generateNotificationFront(SEVERITY_ERROR, msg, "Data Error");
				return false;
			}
			if (!ft.getCveind().equals(codIndOrig)) {
				String msg = "No se puede cambiar el Campo Indicador. Tiene detalles.";
				generateNotificationFront(SEVERITY_ERROR, msg, "Data Error");
				return false;
			}
		}

		List<Ftecnicasm> existentes = ftecnicasmRepository.findByCvetemasAndClvdepAndClvfunAndCveind(ft.getCvetemas(),
				ft.getClvdep(), ft.getClvfun(), ft.getCveind());
		boolean duplicado = false;
		if (ft.getId() == null && existentes != null && !existentes.isEmpty()) {
			duplicado = true;
		} else {
			for (Ftecnicasm exist : existentes) {
				if (exist.getId().longValue() != ft.getId().longValue()) {
					duplicado = true;
				}
			}
		}
		if (duplicado) {
			String msg = "Ficha de Seguimiento ya existe con Cve Temas: \"" + ft.getCvetemas() + "\" "
					+ "Dependencia: \"" + ft.getClvdep() + "\" " + "Programa: \"" + ft.getClvfun() + "\" "
					+ "Codigo de Indicador: \"" + ft.getCveind() + "\".";
			generateNotificationFront(SEVERITY_ERROR, msg, "Data Error");
			return false;
		}
		return true;
	}

	/**
	 * Habilita la edición del encabezado para la ficha tecnica seleccionada.
	 *
	 * @param row Inidice de la Ficha tecnica seleccionada
	 */
	public void modificarEncabezado(Integer row) {
		encabezadoModificar = true;
		encabezadoAdicionar = false;
		setBanderasEditEncabezado(true);
		masterRowSelected = row;
	}

	/**
	 * Limpia el valor de los componentes editables del encabezado.
	 *
	 * @param row Inidice de la Ficha tecnica seleccionada
	 */
	public void resetEncabezado(Integer row) {
		Ftecnicasm ft = encabezadosList.get(row);
		if (ft.getId() == null || ft.getId() == 0) {
			Ftecnicasm nuevo = new Ftecnicasm();
			encabezadosList.set(row, nuevo);
		} else {
			cargarIndicadores(row);
			irAPaginaEncabezado(row);
		}
	}

	/**
	 * Agrega una Ficha Tecnica nueva en pantalla para ser configurarda y
	 * posteriormente registrada en la base.
	 */
	public void adicionarEncabezado() {
		encabezadoAdicionar = true;
		encabezadoModificar = false;
		int indice = encabezadosList.size() - 1;
		if (encabezadosList.get(indice).getId() != null && encabezadosList.get(indice).getId() != 0) {
			encabezadosList.add(new Ftecnicasm());
			indice++;
		}
		irAPaginaEncabezado(indice);
		inicializarEncabezado(indice);
		masterRowSelected = indice;
		setBanderasEditEncabezado(true);
	}

	/**
	 * Ubica el paginador de la tabla de encabezados en la pagina indicada.
	 *
	 * @param pagina Indice de la pagina a mostrar
	 */
	private void irAPaginaEncabezado(int pagina) {
		if (pagina >= encabezadosList.size()) {
			pagina = encabezadosList.size() - 1;
		}
		irAPagina("formIndCaptura:indTable", pagina);
	}

	/**
	 * Ubica el paginador de la tabla de detalles en la pagina indicada.
	 *
	 * @param pagina Indice de la pagina a mostrar
	 */
	private void irAPaginaDetalle(int pagina) {
		logger.debug("pagina: " + pagina);
		if (pagina >= detallesList.size()) {
			pagina = detallesList.size() - 1;
		}
		irAPagina("formIndCaptura:indDetalleTable", pagina);
	}

	/**
	 * Ubica el paginador de la tabla especificada en la pagina indicada.
	 *
	 * @param tabla  Nombre de la tabla a redireccionar
	 * @param pagina Indice de la pagina a mostrar
	 */
	private void irAPagina(String tabla, int pagina) {
		final DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(tabla);
		d.setFirst(pagina);
		if (tabla.equals("formIndCaptura:indTable")) {
			masterRowSelected = pagina;
		} else {
			detalleRowSelected = pagina;
		}
	}

	/**
	 * Elimina fIsicamente de la base de datos el registro asociado a la ficha
	 * tecnica seleccionada.
	 *
	 * @param row Inidice de la Ficha tecnica seleccionada
	 */
	public void borrarEncabezado(Integer row) {
		if (tieneDetalles) {
			String msg = "Existen temas de desarrollo, no se puede Eliminar...";
			generateNotificationFront(SEVERITY_ERROR, msg, "Data Error");
			return;
		}
		Ftecnicasm ft = encabezadosList.get(row);
		try {
			if (ft.getId() != null) {
				ftecnicasmRepository.delete(ft);
			}
			cargarIndicadores(row);
			setBanderasEditEncabezado(false);
			irAPaginaEncabezado(row);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "El registro fue eliminado correctamente",
					"Borrar Encabezado");
		} catch (Exception e) {
			e.printStackTrace();
			generateNotificationFront(SEVERITY_ERROR, "Ocurrió un error al eliminar el registro: " + e.getMessage(),
					"Data Error");
		}
	}

	/**
	 * Cancela la edicipon y restaura los valores persistidos para la ficha tecnica
	 * selccionada. En caso de ser una ficha nueva que todavIa no existe en base de
	 * datos, la elimina de la lista.
	 * 
	 * @param row Inidice de la Ficha tecnica seleccionada
	 */
	public void cancelarEncabezado(Integer row) {
		if (encabezadoAdicionar) {// es adicion
			row--;
		}
		cargarIndicadores(row);
		irAPaginaEncabezado(row);
		setBanderasEditEncabezado(false);
	}

	/**
	 * Persiste en la base de datos los cambios realizados en el detalle de la ficha
	 * tecnica seleccionada.
	 *
	 * @param row Inidice del detalle de la Ficha tecnica seleccionada
	 */
	public void salvarDetalle(Integer row) {
		Ftecnicasd ftd = detallesList.get(row);
		ftd.setCvetemas(temaOrig);
		ftd.setClvdep(dependenciaOrig);
		ftd.setClvfun(programaOrig);
		ftd.setCveind(codIndOrig);
		if (validarDetalle(ftd)) {
			try {
				ftd.setClvfin(""); // TODO de dónde sale este dato?
				ftd.setIdsector(getUserDetails().getIdSector());
				ftd.setUserid(getUserDetails().getUsername());
				ftd.setFeccap(new Date());
				ftd.setIdRef(0L);

				if (ftd.getCodigo() == 0) {
					int mayor = 0;
					List<Ftecnicasd> regs = ftecnicasdRepository.findByCvetemasAndClvdepAndClvfunAndCveind(
							ftd.getCvetemas(), ftd.getClvdep(), ftd.getClvfun(), ftd.getCveind());
					if (regs != null && !regs.isEmpty()) {
						for (Ftecnicasd reg : regs) {
							if (reg.getCodigo().intValue() > mayor) {
								mayor = reg.getCodigo();
							}
						}
					}
					ftd.setCodigo(mayor + 1);
				}

				ftd = ftecnicasdRepository.save(ftd);
				logger.debug("Id: " + ftd.getId());
				cargarDetalles(temaOrig, dependenciaOrig, programaOrig, codIndOrig);
				setBanderasEditDetalle(false);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "La informacion se guardó correctamente",
						"Salvar Detalle");
			} catch (Exception e) {
				e.printStackTrace();
				generateNotificationFront(SEVERITY_ERROR,
						"Ocurrió un error al guardar la información: " + e.getMessage(), "Data Error");
			}
		}
	}

	/**
	 * Verifica que se cumplan las reglas de negocio definidas para el detalle de la
	 * ficha tecnica.
	 *
	 * @param ftd the ftd
	 * @return <code>true</code> si todas las reglas se cumplen; <code>false</code>
	 *         en caso contrario
	 */
	private boolean validarDetalle(Ftecnicasd ftd) {
		boolean respuesta = true;
		if (ftd.getCodigo() == null) {
			String msg = "Proporcione un código. Update cancelled.";
			generateNotificationFront(SEVERITY_ERROR, msg, "Data Error");
			respuesta = false;
		}

		if (StringUtils.isBlank(ftd.getCvevar())) {
			String msg = "Proporcione una clave de variable. Update cancelled.";
			generateNotificationFront(SEVERITY_ERROR, msg, "Data Error");
			respuesta = false;
		}

		if (!respuesta) {
			return false;
		}

		List<Ftecnicasd> existentes = ftecnicasdRepository.findByCvetemasAndClvdepAndClvfunAndCveindAndCodigo(temaOrig,
				dependenciaOrig, programaOrig, codIndOrig, ftd.getCodigo());

		boolean duplicado = false;
		if (ftd.getId() == null && existentes != null && !existentes.isEmpty()) {
			duplicado = true;
		} else {
			for (Ftecnicasd exist : existentes) {
				if (exist.getId().longValue() != ftd.getId().longValue()) {
					duplicado = true;
				}
			}
		}

		if (duplicado) {
			String msg = "Ftecnicasd ya existe con Cve Temas \"" + ftd.getCvetemas() + "\" " + "clave  Dependencia \""
					+ ftd.getClvdep() + "\" " + "Programa \"" + ftd.getClvfun() + "\" " + "Fuente de Financiamiento \""
					+ ftd.getClvfin() + "\" " + "Codigo de Indicador \"" + ftd.getCveind() + "\" " + "Código \""
					+ ftd.getCodigo() + "\" (132) Update cancelled.";
			generateNotificationFront(SEVERITY_ERROR, msg, "Data Error");
			return false;
		}
		return true;
	}

	/**
	 * Handler para evaluar los cambios en el campo código de variable. Al cambiar
	 * el valor del campo se verifica que el código exista en la tabla variables; en
	 * caso de no existir se manda un mensaje de error
	 * 
	 * @param row Inidice del detalle de la Ficha tecnica seleccionada
	 */
	public void cambiarVariable(Integer row) {
		Ftecnicasd ftd = detallesList.get(row);
		if (ftd.getNumvar() == 0 || ftd.getNumvar() == null) {
			ftd.setNumvar(null);
			ftd.setCvevar(StringUtils.EMPTY);
			ftd.setVariables(StringUtils.EMPTY);
			return;
		}
		Variables variables = variablesRepository.findByNumvar(ftd.getNumvar());
		if (variables == null) {
			String msg = "La clave de variable no existe en el catalogo. Update cancelled.";
			generateNotificationFront(SEVERITY_ERROR, msg, "Data Error");
			ftd.setNumvar(null);
			ftd.setCvevar(StringUtils.EMPTY);
			ftd.setVariables(StringUtils.EMPTY);
			return;
		} else {
			ftd.setNumvar(variables.getNumvar());
			ftd.setCvevar(variables.getCvevar());
			ftd.setVariables(variables.getNomvar());
		}
	}

	/**
	 * Habilita la edición del detalle para la ficha tecnica seleccionada.
	 *
	 * @param row Inidice del detalle de la Ficha tecnica seleccionada
	 */
	public void modificarDetalle(Integer row) {
		detalleRowSelected = row;
		setBanderasEditDetalle(true);
	}

	/**
	 * Limpia el valor de los componentes editables del detalle.
	 *
	 * @param row Inidice del detalle de la Ficha tecnica seleccionada
	 */
	public void resetDetalle(Integer row) {
		Ftecnicasd ftd = detallesList.get(row);
		if (ftd.getId() == null || ftd.getId() == 0) {
			Ftecnicasd nuevo = new Ftecnicasd();
			nuevo.setCvetemas(ftd.getCvetemas());
			nuevo.setClvdep(ftd.getClvdep());
			nuevo.setClvfun(ftd.getClvfun());
			nuevo.setCveind(ftd.getCveind());
			nuevo.setId(ftd.getId());
			if (nuevo.getCodigo() == null) {
				nuevo.setCodigo(0);
			}
			detallesList.set(row, nuevo);
		} else {
			cargarDetalles(temaOrig, dependenciaOrig, programaOrig, codIndOrig);
			irAPaginaDetalle(row);
		}
	}

	/**
	 * Agrega un detalle de Ficha Tecnica en pantalla para ser configurardo y
	 * posteriormente registrado en la base.
	 */
	public void adicionarDetalle() {
		if (detallesList == null) {
			detallesList = new ArrayList<Ftecnicasd>();
		}
		if (detallesList.isEmpty() || detallesList.get(detallesList.size() - 1).getId() != null) {
			Ftecnicasd ftd = new Ftecnicasd();
			ftd.setCodigo(0);
			detallesList.add(ftd);
		}
		tieneDetalles = true;
		int indice = detallesList.size() - 1;
		irAPaginaDetalle(indice);
		detalleRowSelected = indice;
		setBanderasEditDetalle(true);
	}

	/**
	 * Elimina fIsicamente de la base de datos el registro asociado al detalle de la
	 * ficha tecnica seleccionada.
	 *
	 * @param row Inidice del detalle de la Ficha tecnica seleccionada
	 */
	public void borrarDetalle(Integer row) {
		Ftecnicasd ftd = detallesList.get(row);
		try {
			if (ftd.getId() != null) {
				ftecnicasdRepository.delete(ftd);
			}
			cargarDetalles(temaOrig, dependenciaOrig, programaOrig, codIndOrig);
			setBanderasEditDetalle(false);
			irAPaginaDetalle(row);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "El registro fue eliminado correctamente",
					"Borrar Detalle");
		} catch (Exception e) {
			e.printStackTrace();
			generateNotificationFront(SEVERITY_ERROR, "Ocurrió un error al eliminar el registro: " + e.getMessage(),
					"Data Error");
		}
	}

	/**
	 * Cancela la edición y restaura los valores persistidos para el detalle de la
	 * ficha tecnica selccionada. En caso de ser un detalle nuevo que todavIa no
	 * existe en base de datos, lo elimina de la lista.
	 * 
	 * @param row Inidice del detalle de la Ficha tecnica seleccionada
	 */
	public void cancelarDetalle(Integer row) {
		cargarDetalles(temaOrig, dependenciaOrig, programaOrig, codIndOrig);
		irAPaginaDetalle(row);
		setBanderasEditDetalle(false);
	}

	/**
	 * Handler para manejar el cambio de valor en el combo Clave Temas.
	 *
	 * @param row Inidice de la Ficha tecnica seleccionada
	 */
	public void changeTema(Integer row) {
		Ftecnicasm ftecnicasm = encabezadosList.get(row);
		logger.debug("ftecnicasm.getCvetemas(): " + ftecnicasm.getCvetemas());
		if (StringUtils.isEmpty(ftecnicasm.getCvetemas())) {
			desTema = desIdTema = StringUtils.EMPTY;
			return;
		}
		List<Cpd> temas = cpdRepository.findByCvetemas(ftecnicasm.getCvetemas());
		if (temas != null && !temas.isEmpty()) {
			desTema = temas.get(0).getDescripcion();
			desIdTema = temas.get(0).getCvetemas();
		}
	}

	/**
	 * Handler para manejar el cambio de valor en el combo Clave Dependencia.
	 *
	 * @param row Inidice de la Ficha tecnica seleccionada
	 */
	public void changeDependencia(Integer row) {
		Ftecnicasm ftecnicasm = encabezadosList.get(row);
		logger.debug("ftecnicasm.getClvdep(): " + ftecnicasm.getClvdep());
		clvProgramasList.clear();
		desProg = desIdProg = StringUtils.EMPTY;
		if (StringUtils.isEmpty(ftecnicasm.getClvdep())) {
			desDep = desIdDep = StringUtils.EMPTY;
			return;
		}
		List<Xcatpro> listaDepPro = xcatproRepository.findByClvdep(ftecnicasm.getClvdep());
		for (Xcatpro dep : listaDepPro) {
			clvProgramasList.add(dep.getClvfun());
		}
//		if (StringUtils.isEmpty(ftecnicasm.getClvfun()) || !clvProgramasList.contains(ftecnicasm.getClvfun())) {
//			ftecnicasm.setClvfun(null);
//		}
		List<Catdep> dependencias = catdepRepository.findByClvdep(ftecnicasm.getClvdep());
		if (dependencias != null && !dependencias.isEmpty()) {
			desDep = dependencias.get(0).getNomdep();
			desIdDep = dependencias.get(0).getClvdep();
		}
	}

	/**
	 * Handler para manejar el cambio de valor en el combo Clave Programa.
	 *
	 * @param row Inidice de la Ficha tecnica seleccionada
	 */
	public void changePrograma(Integer row) {
		Ftecnicasm ftecnicasm = encabezadosList.get(row);
		logger.debug("ftecnicasm.getClvfun(): " + ftecnicasm.getClvfun());
		if (StringUtils.isEmpty(ftecnicasm.getClvfun())) {
			desProg = desIdProg = StringUtils.EMPTY;
			return;
		}

		// List<Xcatpro> progs = xcatproRepository.findByClvfun(ftecnicasm.getClvfun());
		// if (progs != null && !progs.isEmpty()) {
		// desProg = progs.get(0).getNompro();
		// desIdProg = progs.get(0).getClvfun();
		// }

		List<Muninep> progs = muniNepRepository.findByCampo7AndIdsector(ftecnicasm.getClvfun(),
				getUserDetails().getIdSector());
		if (progs != null && !progs.isEmpty()) {
			desProg = progs.get(0).getCampo6();
			desIdProg = progs.get(0).getCampo7();
		}
	}

	/**
	 * Handler para manejar el cambio de valor en el combo Clave Indicador.
	 *
	 * @param row Inidice de la Ficha tecnica seleccionada
	 */
	public void changeCodInd(Integer row) {
		Ftecnicasm ftecnicasm = encabezadosList.get(row);
		logger.debug("ftecnicasm.getCveind(): " + ftecnicasm.getCveind());
		if (StringUtils.isEmpty(ftecnicasm.getCveind())) {
			desCodInd = desIdCodInd = StringUtils.EMPTY;
			return;
		}
		ftecnicasm.setCveind(ftecnicasm.getCveind().toUpperCase());
		List<Mir> codInds = mirRepository.findByCodigo(ftecnicasm.getCveind());
		if (codInds != null && !codInds.isEmpty()) {
			desIdCodInd = codInds.get(0).getCodigo();
			desCodInd = codInds.get(0).getNomind();
		} else {
			ftecnicasm.setCveind(null);
			desCodInd = desIdCodInd = StringUtils.EMPTY;
			generateNotificationFront(SEVERITY_ERROR, "No se encontró información relacionada al indicador.",
					"Data Error");
		}
	}

	/**
	 * Consult variables.
	 */
	public void consultVariables() {
		logger.debug(":: Consultar Variables ");
		if (null != variables) {
			variables.clear();
		}
		variables = variablesRepository.findAll();
		restartFilteredVariables();
	}

	/**
	 * Restart filtered variables.
	 */
	private void restartFilteredVariables() {
		if (BooleanUtils.negate(filteredVariables == null) && BooleanUtils.negate(variables == null)) {
			filteredVariables.clear();
			filteredVariables.addAll(variables);
		}
	}

	/**
	 * Change variable.
	 */
	public void changeVariable() {
		Ftecnicasd ftd = detallesList.get(detalleRowSelected);
		if (null != variableSelected) {
			ftd.setNumvar(variableSelected.getNumvar());
			ftd.setCvevar(variableSelected.getCvevar());
			ftd.setVariables(variableSelected.getNomvar());
		}
		RequestContext.getCurrentInstance().execute("PF('variableWdg').unselectAllRows();");
		RequestContext.getCurrentInstance().execute("PF('variableWdg').clearFilters();");
	}

	/**
	 * On clvdgm row dbl clck select.
	 *
	 * @param event the event
	 */
	public void onClvdgmRowDblClckSelect(final SelectEvent event) {
		this.changeVariable();
	}

	/**
	 * Muestra el popup de ayuda para búsqueda de indicadores.
	 */
	public void consultIndicadores() {
		logger.debug(":: Consultar Indicadores ");
		if (null != indicadores) {
			indicadores.clear();
		}
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
		Ftecnicasm ft = encabezadosList.get(masterRowSelected);
		if (null != indicadorSelected) {
			ft.setCveind(indicadorSelected.getCodigo());
			this.desCodInd = indicadorSelected.getNomind();
			this.desIdCodInd = indicadorSelected.getCodigo();
		}

		RequestContext.getCurrentInstance().execute("PF('indicadorWdg').unselectAllRows();");
		RequestContext.getCurrentInstance().execute("PF('indicadorWdg').clearFilters();");
//		final RequestContext context = RequestContext.getCurrentInstance();
//		context.update("formIndCaptura:panelIndicadores:indDetalleTable:" + detalleRowSelected + ":varClave");
//		context.update("formIndCaptura:panelIndicadores:indDetalleTable:" + detalleRowSelected + ":varDescripcion");
	}

	/**
	 * On ind row dbl clck select.
	 *
	 * @param event the event
	 */
	public void onIndRowDblClckSelect(final SelectEvent event) {
		this.changeIndicador();
	}

	/**
	 * Checks if is habilitar borrar maestro.
	 *
	 * @return true, if is habilitar borrar maestro
	 */
	public boolean isHabilitarBorrarMaestro() {
		return capturaDeshabilitado && !(encabezadosList == null || encabezadosList.isEmpty()
				|| encabezadosList.get(0).getId() == null || encabezadosList.get(0).getId() == 0);
	}

	/**
	 * Checks if is habilitar borrar detalle.
	 *
	 * @return true, if is habilitar borrar detalle
	 */
	public boolean isHabilitarBorrarDetalle() {
		return captDetDeshabilitado && tieneDetalles;
	}

	/**
	 * Gets the cpd repository.
	 *
	 * @return the cpd repository
	 */
	// Getters and Setters
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
	 * Gets the xcatpro repository.
	 *
	 * @return the xcatpro repository
	 */
	public XcatproRepository getXcatproRepository() {
		return xcatproRepository;
	}

	/**
	 * Sets the xcatpro repository.
	 *
	 * @param xcatproRepository the new xcatpro repository
	 */
	public void setXcatproRepository(XcatproRepository xcatproRepository) {
		this.xcatproRepository = xcatproRepository;
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
	 * Gets the encabezados list.
	 *
	 * @return the encabezados list
	 */
	public List<Ftecnicasm> getEncabezadosList() {
		return encabezadosList;
	}

	/**
	 * Sets the encabezados list.
	 *
	 * @param encabezadosList the new encabezados list
	 */
	public void setEncabezadosList(List<Ftecnicasm> encabezadosList) {
		this.encabezadosList = encabezadosList;
	}

	/**
	 * Gets the detalles list.
	 *
	 * @return the detalles list
	 */
	public List<Ftecnicasd> getDetallesList() {
		return detallesList;
	}

	/**
	 * Sets the detalles list.
	 *
	 * @param detallesList the new detalles list
	 */
	public void setDetallesList(List<Ftecnicasd> detallesList) {
		this.detallesList = detallesList;
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
	 * Checks if is capt det deshabilitado.
	 *
	 * @return true, if is capt det deshabilitado
	 */
	public boolean isCaptDetDeshabilitado() {
		return captDetDeshabilitado;
	}

	/**
	 * Sets the capt det deshabilitado.
	 *
	 * @param captDetDeshabilitado the new capt det deshabilitado
	 */
	public void setCaptDetDeshabilitado(boolean captDetDeshabilitado) {
		this.captDetDeshabilitado = captDetDeshabilitado;
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
	 * Checks if is salvar encabezado visible.
	 *
	 * @return true, if is salvar encabezado visible
	 */
	public boolean isSalvarEncabezadoVisible() {
		return salvarEncabezadoVisible;
	}

	/**
	 * Sets the salvar encabezado visible.
	 *
	 * @param salvarEncabezadoVisible the new salvar encabezado visible
	 */
	public void setSalvarEncabezadoVisible(boolean salvarEncabezadoVisible) {
		this.salvarEncabezadoVisible = salvarEncabezadoVisible;
	}

	/**
	 * Checks if is salvar detalle visible.
	 *
	 * @return true, if is salvar detalle visible
	 */
	public boolean isSalvarDetalleVisible() {
		return salvarDetalleVisible;
	}

	/**
	 * Sets the salvar detalle visible.
	 *
	 * @param salvarDetalleVisible the new salvar detalle visible
	 */
	public void setSalvarDetalleVisible(boolean salvarDetalleVisible) {
		this.salvarDetalleVisible = salvarDetalleVisible;
	}

	/**
	 * Gets the des tema.
	 *
	 * @return the des tema
	 */
	public String getDesTema() {
		return desTema;
	}

	/**
	 * Sets the des tema.
	 *
	 * @param desTema the new des tema
	 */
	public void setDesTema(String desTema) {
		this.desTema = desTema;
	}

	/**
	 * Gets the des dep.
	 *
	 * @return the des dep
	 */
	public String getDesDep() {
		return desDep;
	}

	/**
	 * Sets the des dep.
	 *
	 * @param desDep the new des dep
	 */
	public void setDesDep(String desDep) {
		this.desDep = desDep;
	}

	/**
	 * Gets the des prog.
	 *
	 * @return the des prog
	 */
	public String getDesProg() {
		return desProg;
	}

	/**
	 * Sets the des prog.
	 *
	 * @param desProg the new des prog
	 */
	public void setDesProg(String desProg) {
		this.desProg = desProg;
	}

	/**
	 * Gets the des cod ind.
	 *
	 * @return the des cod ind
	 */
	public String getDesCodInd() {
		return desCodInd;
	}

	/**
	 * Sets the des cod ind.
	 *
	 * @param desCodInd the new des cod ind
	 */
	public void setDesCodInd(String desCodInd) {
		this.desCodInd = desCodInd;
	}

	/**
	 * Gets the ftecnicasm repository.
	 *
	 * @return the ftecnicasm repository
	 */
	public FtecnicasmRepository getFtecnicasmRepository() {
		return ftecnicasmRepository;
	}

	/**
	 * Sets the ftecnicasm repository.
	 *
	 * @param ftecnicasmRepository the new ftecnicasm repository
	 */
	public void setFtecnicasmRepository(FtecnicasmRepository ftecnicasmRepository) {
		this.ftecnicasmRepository = ftecnicasmRepository;
	}

	/**
	 * Gets the ftecnicasd repository.
	 *
	 * @return the ftecnicasd repository
	 */
	public FtecnicasdRepository getFtecnicasdRepository() {
		return ftecnicasdRepository;
	}

	/**
	 * Sets the ftecnicasd repository.
	 *
	 * @param ftecnicasdRepository the new ftecnicasd repository
	 */
	public void setFtecnicasdRepository(FtecnicasdRepository ftecnicasdRepository) {
		this.ftecnicasdRepository = ftecnicasdRepository;
	}

	/**
	 * Gets the des id tema.
	 *
	 * @return the des id tema
	 */
	public String getDesIdTema() {
		return desIdTema;
	}

	/**
	 * Sets the des id tema.
	 *
	 * @param desIdTema the new des id tema
	 */
	public void setDesIdTema(String desIdTema) {
		this.desIdTema = desIdTema;
	}

	/**
	 * Gets the des id dep.
	 *
	 * @return the des id dep
	 */
	public String getDesIdDep() {
		return desIdDep;
	}

	/**
	 * Sets the des id dep.
	 *
	 * @param desIdDep the new des id dep
	 */
	public void setDesIdDep(String desIdDep) {
		this.desIdDep = desIdDep;
	}

	/**
	 * Gets the des id prog.
	 *
	 * @return the des id prog
	 */
	public String getDesIdProg() {
		return desIdProg;
	}

	/**
	 * Sets the des id prog.
	 *
	 * @param desIdProg the new des id prog
	 */
	public void setDesIdProg(String desIdProg) {
		this.desIdProg = desIdProg;
	}

	/**
	 * Gets the des id cod ind.
	 *
	 * @return the des id cod ind
	 */
	public String getDesIdCodInd() {
		return desIdCodInd;
	}

	/**
	 * Sets the des id cod ind.
	 *
	 * @param desIdCodInd the new des id cod ind
	 */
	public void setDesIdCodInd(String desIdCodInd) {
		this.desIdCodInd = desIdCodInd;
	}

	/**
	 * Gets the clv temas list.
	 *
	 * @return the clv temas list
	 */
	public List<String> getClvTemasList() {
		return clvTemasList;
	}

	/**
	 * Sets the clv temas list.
	 *
	 * @param clvTemasList the new clv temas list
	 */
	public void setClvTemasList(List<String> clvTemasList) {
		this.clvTemasList = clvTemasList;
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
	 * Gets the clv programas list.
	 *
	 * @return the clv programas list
	 */
	public List<String> getClvProgramasList() {
		return clvProgramasList;
	}

	/**
	 * Sets the clv programas list.
	 *
	 * @param clvProgramasList the new clv programas list
	 */
	public void setClvProgramasList(List<String> clvProgramasList) {
		this.clvProgramasList = clvProgramasList;
	}

	/**
	 * Gets the clv codigos ind list.
	 *
	 * @return the clv codigos ind list
	 */
	public List<String> getClvCodigosIndList() {
		return clvCodigosIndList;
	}

	/**
	 * Sets the clv codigos ind list.
	 *
	 * @param clvCodigosIndList the new clv codigos ind list
	 */
	public void setClvCodigosIndList(List<String> clvCodigosIndList) {
		this.clvCodigosIndList = clvCodigosIndList;
	}

	/**
	 * Gets the frecuenci list.
	 *
	 * @return the frecuenci list
	 */
	public List<String> getFrecuenciList() {
		return frecuenciList;
	}

	/**
	 * Sets the frecuenci list.
	 *
	 * @param frecuenciList the new frecuenci list
	 */
	public void setFrecuenciList(List<String> frecuenciList) {
		this.frecuenciList = frecuenciList;
	}

	/**
	 * Checks if is tiene detalles.
	 *
	 * @return true, if is tiene detalles
	 */
	public boolean isTieneDetalles() {
		return tieneDetalles;
	}

	/**
	 * Sets the tiene detalles.
	 *
	 * @param tieneDetalles the new tiene detalles
	 */
	public void setTieneDetalles(boolean tieneDetalles) {
		this.tieneDetalles = tieneDetalles;
	}

	/**
	 * Checks if is puede agregar detalles.
	 *
	 * @return true, if is puede agregar detalles
	 */
	public boolean isPuedeAgregarDetalles() {
		return puedeAgregarDetalles;
	}

	/**
	 * Sets the puede agregar detalles.
	 *
	 * @param puedeAgregarDetalles the new puede agregar detalles
	 */
	public void setPuedeAgregarDetalles(boolean puedeAgregarDetalles) {
		this.puedeAgregarDetalles = puedeAgregarDetalles;
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
	 * Checks if is habilitar modificar.
	 *
	 * @return true, if is habilitar modificar
	 */
	public boolean isHabilitarModificar() {
		return habilitarModificar;
	}

	/**
	 * Sets the habilitar modificar.
	 *
	 * @param habilitarModificar the new habilitar modificar
	 */
	public void setHabilitarModificar(boolean habilitarModificar) {
		this.habilitarModificar = habilitarModificar;
	}

	public boolean isEncabezadoAdicionar() {
		return encabezadoAdicionar;
	}

	public void setEncabezadoAdicionar(boolean encabezadoAdicionar) {
		this.encabezadoAdicionar = encabezadoAdicionar;
	}

	public boolean isEncabezadoModificar() {
		return encabezadoModificar;
	}

	public void setEncabezadoModificar(boolean encabezadoModificar) {
		this.encabezadoModificar = encabezadoModificar;
	}

	public boolean isDetalleAdicionar() {
		return detalleAdicionar;
	}

	public void setDetalleAdicionar(boolean detalleAdicionar) {
		this.detalleAdicionar = detalleAdicionar;
	}

	public boolean isDetalleModificar() {
		return detalleModificar;
	}

	public void setDetalleModificar(boolean detalleModificar) {
		this.detalleModificar = detalleModificar;
	}

}
