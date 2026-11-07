package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.Constants.ZERO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.data.PageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.Actividad;
import com.gem.sistema.business.domain.Componente;
import com.gem.sistema.business.domain.Cpd;
import com.gem.sistema.business.domain.Finalidad;
import com.gem.sistema.business.domain.Mir;
import com.gem.sistema.business.domain.Proposito;
import com.gem.sistema.business.dto.MatrizIndicadoresDTO;
import com.gem.sistema.business.predicates.ActividadPredicate;
import com.gem.sistema.business.predicates.ComponentePredicates;
import com.gem.sistema.business.predicates.FinalidadPredicates;
import com.gem.sistema.business.predicates.PropositoPredicates;
import com.gem.sistema.business.repository.catalogs.ActividadRepository;
import com.gem.sistema.business.repository.catalogs.ComponenteRepository;
import com.gem.sistema.business.repository.catalogs.FinalidadRepository;
import com.gem.sistema.business.repository.catalogs.PropositoRepository;
import com.gem.sistema.business.service.indicadores.MatrizIndicadoresService;
import com.gem.sistema.util.UtilFront;
import com.roonin.utils.UtilDate;

// TODO: Auto-generated Javadoc
/**
 * The Class MatrizIndicadoresMB.
 */
@ManagedBean(name = "matrizIndicadoresMB")
@ViewScoped
public class MatrizIndicadoresMB extends AbstractMB {

	/** The Constant CLOSE_PANEL_JQUERY. */
	private static final String CLOSE_PANEL_JQUERY = "jQuery('span.ui-icon-minusthick').eq(";

	/** The Constant CLOSE_COMPLETE_JQUERY. */
	private static final String CLOSE_COMPLETE_JQUERY = ").each(function(){jQuery(this).click()});";

	/** The Constant OPEN_PANEL_JQUERY. */
	private static final String OPEN_PANEL_JQUERY = "jQuery('span.ui-icon-plusthick').eq(";

	/** The Constant OPEN_COMPLETE_JQUERY. */
	private static final String OPEN_COMPLETE_JQUERY = ").each(function(){jQuery(this).click()});";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MatrizIndicadoresMB.class);

	/** The master list. */
	private List<MatrizIndicadoresDTO> masterList = new ArrayList<MatrizIndicadoresDTO>();

	/** The old matriz DTO. */
	private MatrizIndicadoresDTO oldMatrizDTO = new MatrizIndicadoresDTO();

	/** The clv dependencias list. */
	private List<String> clvDependenciasList = new ArrayList<String>();

	/** The clv programa list. */
	private List<String> clvProgramaList = new ArrayList<String>();

	/** The clv tema list. */
	private List<String> clvTemaList = new ArrayList<String>();

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

	/** The captura deshabilitado. */
	private boolean capturaDeshabilitado;

	/** The captura deshabilitado objetivo. */
	private boolean capturaDeshabilitadoObjetivo;

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

	/** The componente actual. */
	private int componenteActual;

	/** The modificar encabezado. */
	private boolean modificarEncabezado;

	/** The finalidad visible modificar. */
	private boolean finalidadVisibleModificar;

	/** The finalidad disabled adicionar. */
	private boolean finalidadDisabledAdicionar;

	/** The finalidad disabled borrar. */
	private boolean finalidadDisabledBorrar;

	/** The finalidad disabled reset. */
	private boolean finalidadDisabledReset;

	/** The finalidad disabled cancelar. */
	private boolean finalidadDisabledCancelar;

	/** The finalidad visible salvar. */
	private boolean finalidadVisibleSalvar;

	/** The finalidad disabled editables. */
	private boolean finalidadDisabledEditables;

	/** The proposito visible modificar. */
	private boolean propositoVisibleModificar;

	/** The proposito disabled adicionar. */
	private boolean propositoDisabledAdicionar;

	/** The proposito disabled borrar. */
	private boolean propositoDisabledBorrar;

	/** The proposito disabled reset. */
	private boolean propositoDisabledReset;

	/** The proposito disabled cancelar. */
	private boolean propositoDisabledCancelar;

	/** The proposito visible salvar. */
	private boolean propositoVisibleSalvar;

	/** The proposito disabled editables. */
	private boolean propositoDisabledEditables;

	/** The componente visible modificar. */
	private boolean componenteVisibleModificar;

	/** The componente disabled adicionar. */
	private boolean componenteDisabledAdicionar;

	/** The componente disabled borrar. */
	private boolean componenteDisabledBorrar;

	/** The componente disabled reset. */
	private boolean componenteDisabledReset;

	/** The componente disabled cancelar. */
	private boolean componenteDisabledCancelar;

	/** The componente visible salvar. */
	private boolean componenteVisibleSalvar;

	/** The componente disabled editables. */
	private boolean componenteDisabledEditables;

	/** The actividad visible modificar. */
	private boolean actividadVisibleModificar;

	/** The actividad disabled adicionar. */
	private boolean actividadDisabledAdicionar;

	/** The actividad disabled borrar. */
	private boolean actividadDisabledBorrar;

	/** The actividad disabled reset. */
	private boolean actividadDisabledReset;

	/** The actividad disabled cancelar. */
	private boolean actividadDisabledCancelar;

	/** The actividad visible salvar. */
	private boolean actividadVisibleSalvar;

	/** The actividad disabled editables. */
	private boolean actividadDisabledEditables;

	/** The bandera modificar. */
	private Boolean banderaModificar = new Boolean(false);

	private Boolean bModificar = Boolean.FALSE;

	/** The b adicionar. */
	private Boolean bAdicionar;

	/** The lista codigo indicador finalidad. */
	private List<Mir> listaCodigoIndicadorFinalidad = new ArrayList<Mir>();

	/** The lista codigo indicador proposito. */
	private List<Mir> listaCodigoIndicadorProposito = new ArrayList<Mir>();

	/** The lista codigo indicador componente. */
	private List<Mir> listaCodigoIndicadorComponente = new ArrayList<Mir>();

	/** The lista codigo indicador actividad. */
	private List<Mir> listaCodigoIndicadorActividad = new ArrayList<Mir>();

	/** The encabezado en edicion. */
	private MatrizIndicadoresDTO encabezadoEnEdicion = new MatrizIndicadoresDTO();

	/** The finalidad en edicion. */
	private Finalidad finalidadEnEdicion = new Finalidad();

	/** The proposito en edicion. */
	private Proposito propositoEnEdicion = new Proposito();

	/** The componente en edicion. */
	private Componente componenteEnEdicion = new Componente();

	/** The actividad en edicion. */
	private Actividad actividadEnEdicion = new Actividad();

	/** The current page. */
	private int currentPage = 0;

	/** The current page det. */
	private int currentPageDet = 0;

	/** The matriz indicadores service. */
	@ManagedProperty("#{matrizIndicadoresService}")
	private MatrizIndicadoresService matrizIndicadoresService;

	@ManagedProperty("#{finalidadRepository}")
	private FinalidadRepository finalidadRepository;

	@ManagedProperty("#{propositoRepository}")
	private PropositoRepository propositoRepository;

	@ManagedProperty("#{componenteRepository}")
	private ComponenteRepository componenteRepository;

	@ManagedProperty("#{actividadRepository}")
	private ActividadRepository actividadRepository;
	/** The deshabilitado editables. */
	private boolean deshabilitadoEditables;

	/**
	 * Gets the b adicionar.
	 *
	 * @return the b adicionar
	 */
	public Boolean getbAdicionar() {
		return bAdicionar;
	}

	/**
	 * Sets the b adicionar.
	 *
	 * @param bAdicionar the new b adicionar
	 */
	public void setbAdicionar(Boolean bAdicionar) {
		this.bAdicionar = bAdicionar;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct MatrizIndicadoresMB ");
		this.setbAdicionar(Boolean.FALSE);
	}

	/**
	 * metodo que carga la pantalla inicial desde la llamada del menu.
	 */
	public void findAllIndicadores() {

		limpiarListas();
		llenarCabecera();
		llenarListaDependencias();
		llenarListaTemas();
		if (0 != masterList.get(0).getId()) {
			llenarListaCodigoIndicadorFinalidad(masterList.get(0).getCveprog());
			llenarListaCodigoIndicadorProposito(masterList.get(0).getCveprog());
			llenarListaCodigoIndicadorComponente(masterList.get(0).getCveprog());
			llenarListaCodigoIndicadorActividad(masterList.get(0).getCveprog());
		} else {
			this.panelToggle(currentPage);
		}

		estadoInicialBotones();
		estadoInicialBotonesFinalidad(ZERO);
		estadoInicialBotonesProposito(ZERO);
		estadoInicialBotonesComponente(ZERO);
		estadoInicialBotonesActividad(ZERO);
	}

	/**
	 * Limpiar listas.
	 */
	public void limpiarListas() {
		masterList.clear();
		clvDependenciasList.clear();
		clvProgramaList.clear();
		clvTemaList.clear();
	}

	/**
	 * Llenar cabecera.
	 */
	public void llenarCabecera() {
		masterList = matrizIndicadoresService.listaMatrizIndicadores();
	}

	/**
	 * Llenar lista dependencias.
	 */
	public void llenarListaDependencias() {
		clvDependenciasList.clear();
		for (String dep : matrizIndicadoresService.listaDependenciasPorSector(getUserDetails().getIdSector())) {
			clvDependenciasList.add(dep);
		}
		if (CollectionUtils.isNotEmpty(masterList)) {
			for (MatrizIndicadoresDTO d : masterList) {
				List<Proposito> propList = llenarListaProposito(d.getClvdepg(), d.getCveprog(), d.getCvetemas());
				List<Finalidad> finList = llenarListaFinalidad(d.getClvdepg(), d.getCveprog(), d.getCvetemas());
				List<Componente> compList = llenarListaComponente(d.getClvdepg(), d.getCveprog(), d.getCvetemas());

				d.setClvdepgDesc(matrizIndicadoresService.getDescripcionDep(d.getClvdepg()));
				d.setCveProgDesc(matrizIndicadoresService.getDescripcionProg(d.getCveprog()));
				d.getProgramasMap().put(d.getCveprog(), matrizIndicadoresService.getDescripcionProg(d.getCveprog()));
				d.setCveTemaDesc(matrizIndicadoresService.getDescripcionTema(d.getCvetemas()));
				if (finList.isEmpty()) {
					finList.add(new Finalidad());
					d.setListaFinalidad(finList);
				} else {
					d.setListaFinalidad(finList);
				}

				if (propList.isEmpty()) {
					propList.add(new Proposito());
					d.setListaProposito(propList);
				} else {
					d.setListaProposito(propList);
				}

				if (compList.isEmpty()) {
					compList.add(new Componente());
					d.setListaComponente(compList);
				} else {
					d.setListaComponente(compList);
				}
			}
		} else {
			MatrizIndicadoresDTO matrizDTO = new MatrizIndicadoresDTO();
			// matrizDTO.setClvdep(clvDependenciasList.get(0));
			// matrizDTO.setClvdepgDesc(matrizIndicadoresService.getDescripcionDep(matrizDTO.getClvdep()));
			// String claveP =
			// matrizIndicadoresService.findProgramasMatrizInd(clvDependenciasList.get(0)).get(0);
			//
			// // (claveP, matrizIndicadoresService.getDescripcionProg(claveP));
			//
			// matrizDTO.getProgramasMap().put(claveP,
			// matrizIndicadoresService.getDescripcionProg(claveP));
			// matrizDTO.setCveProgDesc(matrizIndicadoresService.getDescripcionProg(claveP));
			// Cpd tema =
			// matrizIndicadoresService.findAllTemasMatrizInd().get(0);
			// matrizDTO.setCvetemas(tema.getCvetemas());
			// matrizDTO.setCveTemaDesc(matrizIndicadoresService.getDescripcionTema(tema.getCvetemas()));
			// matrizDTO.getListaFinalidad().get(0)
			// .setNombre(matrizIndicadoresService.getDescripcionIndicador(clvDependenciasList.get(0)));
			// matrizDTO.getListaProposito().get(0)
			// .setNombre(matrizIndicadoresService.getDescripcionIndicador(clvDependenciasList.get(0)));
			// matrizDTO.getListaComponente().get(0)
			// .setNombre(matrizIndicadoresService.getDescripcionIndicador(clvDependenciasList.get(0)));
			// matrizDTO.getListaActividad().get(0)
			// .setNombre(matrizIndicadoresService.getDescripcionIndicador(clvDependenciasList.get(0)));
			masterList.add(matrizDTO);
		}
		LOGGER.debug("Lista dependencias + Lista Programas + descripciones Listo MB!!");
	}

	/**
	 * Adicionar indicador.
	 *
	 * @param rowMaster     the row master
	 * @param rowIndicador  the row indicador
	 * @param tipoIndicador the tipo indicador
	 */
	public void adicionarIndicador(Integer rowMaster, Integer rowIndicador, Integer tipoIndicador) {
		LOGGER.debug(
				"adicionarIndicador ROW Master " + rowMaster + " Row ind " + rowIndicador + ", TIPO " + tipoIndicador);
		MatrizIndicadoresDTO mat = masterList.get(rowMaster);

		switch (tipoIndicador) {
		case 1:

			setFinalidadDisabledEditables(false);
			setFinalidadVisibleSalvar(true);
			setFinalidadVisibleModificar(false);
			setFinalidadDisabledAdicionar(true);
			setFinalidadDisabledBorrar(true);
			setFinalidadDisabledReset(false);
			setFinalidadDisabledCancelar(false);
			if (mat.getListaFinalidad().get(rowIndicador).getCveind() != null) {
				mat.getListaFinalidad().add(new Finalidad());
			}
			llenarListaCodigoIndicadorFinalidad(mat.getCveprog());
			break;
		case 2:

			setPropositoDisabledEditables(false);
			setPropositoVisibleSalvar(true);
			setPropositoVisibleModificar(false);
			setPropositoDisabledAdicionar(true);
			setPropositoDisabledBorrar(true);
			setPropositoDisabledReset(false);
			setPropositoDisabledCancelar(false);
			if (mat.getListaProposito().get(rowIndicador).getCveind() != null) {
				mat.getListaProposito().add(new Proposito());
			}
			llenarListaCodigoIndicadorProposito(mat.getCveprog());
			break;
		case 3:

			setComponenteDisabledEditables(false);
			setComponenteVisibleSalvar(true);
			setComponenteVisibleModificar(false);
			setComponenteDisabledAdicionar(true);
			setComponenteDisabledBorrar(true);
			setComponenteDisabledReset(false);
			setComponenteDisabledCancelar(false);
			if (mat.getListaComponente().get(rowIndicador).getCveind() != null) {
				mat.getListaComponente().add(new Componente());
			}
			llenarListaCodigoIndicadorComponente(mat.getCveprog());
			break;
		case 4:

			setActividadDisabledEditables(false);
			setActividadVisibleSalvar(true);
			setActividadVisibleModificar(false);
			setActividadDisabledAdicionar(true);
			setActividadDisabledBorrar(true);
			setActividadDisabledReset(false);
			setActividadDisabledCancelar(false);
			Actividad act = new Actividad();
			if (mat.getListaActividad().get(rowIndicador).getCveind() != null) {
				act.setCvecom(getComponenteActual());
				mat.getListaActividad().add(act);
			}
			llenarListaCodigoIndicadorActividad(mat.getCveprog());
			break;

		default:
			break;
		}
	}

	/**
	 * Llenar lista codigo indicador finalidad.
	 */
	public void llenarListaCodigoIndicadorFinalidad() {
		listaCodigoIndicadorFinalidad = matrizIndicadoresService.llenarListaCodigoIndicador("NF");
	}

	/**
	 * Llenar lista codigo indicador proposito.
	 */
	public void llenarListaCodigoIndicadorProposito() {
		listaCodigoIndicadorProposito = matrizIndicadoresService.llenarListaCodigoIndicador("NP");
	}

	/**
	 * Llenar lista codigo indicador componente.
	 */
	public void llenarListaCodigoIndicadorComponente() {
		listaCodigoIndicadorComponente = matrizIndicadoresService.llenarListaCodigoIndicador("NC");
	}

	/**
	 * Llenar lista codigo indicador actividad.
	 */
	public void llenarListaCodigoIndicadorActividad() {
		listaCodigoIndicadorActividad = matrizIndicadoresService.llenarListaCodigoIndicador("NA");
	}

	/**
	 * Llenar lista codigo indicador finalidad.
	 *
	 * @param cvePrograma the cve programa
	 */
	public void llenarListaCodigoIndicadorFinalidad(String cvePrograma) {
		listaCodigoIndicadorFinalidad = matrizIndicadoresService.llenarListaCodigoIndicador("NF", cvePrograma);
	}

	/**
	 * Llenar lista codigo indicador proposito.
	 *
	 * @param cvePrograma the cve programa
	 */
	public void llenarListaCodigoIndicadorProposito(String cvePrograma) {
		listaCodigoIndicadorProposito = matrizIndicadoresService.llenarListaCodigoIndicador("NP", cvePrograma);
	}

	/**
	 * Llenar lista codigo indicador componente.
	 *
	 * @param cvePrograma the cve programa
	 */
	public void llenarListaCodigoIndicadorComponente(String cvePrograma) {
		listaCodigoIndicadorComponente = matrizIndicadoresService.llenarListaCodigoIndicador("NC", cvePrograma);
	}

	/**
	 * Llenar lista codigo indicador actividad.
	 *
	 * @param cvePrograma the cve programa
	 */
	public void llenarListaCodigoIndicadorActividad(String cvePrograma) {
		listaCodigoIndicadorActividad = matrizIndicadoresService.llenarListaCodigoIndicador("NA", cvePrograma);
	}

	/**
	 * Salvar indicador.
	 *
	 * @param rowMaster     the row master
	 * @param rowIndicador  the row indicador
	 * @param tipoIndicador the tipo indicador
	 */
	public void salvarIndicador(Integer rowMaster, Integer rowIndicador, Integer tipoIndicador) {
		switch (tipoIndicador) {
		case 1:
			Finalidad f = null;
			try {
				MatrizIndicadoresDTO dto = masterList.get(rowMaster);
				if (matrizIndicadoresService.existsMatrizIndicadores(dto.getClvdepg(), dto.getCveprog(),
						dto.getCvetemas())) {
					f = dto.getListaFinalidad().get(rowIndicador);
					if ("null".equals(f.getCveind())) {
						UtilFront.generateNotificationFront(FacesMessage.SEVERITY_WARN, "Ingrese Código Indicador",
								"Ingrese Código Indicador");
					} else {
						f.setClvdepg(dto.getClvdepg());
						f.setCveprog(dto.getCveprog());
						f.setCvefin(dto.getCvefin());
						f.setCvetemas(dto.getCvetemas());
						f.setUserid(getUserDetails().getUsername());
						f.setUsuario(getUserDetails().getFullName());
						f.setFeccap(Calendar.getInstance().getTime());

						f.setSectorid(getUserDetails().getIdSector());
						Finalidad valida = this.finalidadRepository.findOne(FinalidadPredicates.eixtsFinalidad(f));
						if (null == valida || bModificar) {
							matrizIndicadoresService.saveIndicador(f, 1);

							llenarListaDependencias();
							bModificar = Boolean.FALSE;

							UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO,
									"Finalidad guardada correctamente", "Finalidad guardada correctamente");
							estadoInicialBotonesFinalidad(rowMaster);
						} else {
							UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
									"Finalidad ya existe con Cve Dependencia " + f.getClvdepg() + " Clave Programa "
											+ f.getCveprog() + " Clave Temas de Desarrollo " + f.getCvetemas()
											+ " Num. Indicador " + f.getCvefinal(),
									"Finalidad ya existe con Cve Dependencia " + f.getClvdepg() + " Clave Programa "
											+ f.getCveprog() + " Clave Temas de Desarrollo " + f.getCvetemas()
											+ " Num. Indicador " + f.getCvefinal());
						}

					}
				} else {
					UtilFront.generateNotificationFront(FacesMessage.SEVERITY_WARN, "Debe capturar Encabezado",
							"Debe capturar Encabezado");
				}
			} catch (Exception e) {
				e.printStackTrace();
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
						"Finalidad ya existe con Cve Dependencia " + f.getClvdepg() + " Clave Programa "
								+ f.getCveprog() + " Clave Temas de Desarrollo " + f.getCvetemas() + " Num. Indicador "
								+ f.getCvefinal(),
						"Finalidad ya existe con Cve Dependencia " + f.getClvdepg() + " Clave Programa "
								+ f.getCveprog() + " Clave Temas de Desarrollo " + f.getCvetemas() + " Num. Indicador "
								+ f.getCvefinal());
			}
			break;

		case 2:
			Proposito p = null;
			try {
				MatrizIndicadoresDTO dto = masterList.get(rowMaster);
				if (matrizIndicadoresService.existsMatrizIndicadores(dto.getClvdepg(), dto.getCveprog(),
						dto.getCvetemas())) {

					p = dto.getListaProposito().get(rowIndicador);
					if ("null".equals(p.getCveind())) {
						UtilFront.generateNotificationFront(FacesMessage.SEVERITY_WARN, "Ingrese Código Indicador",
								"Ingrese Código Indicador");
					} else {
						p.setClvdepg(dto.getClvdepg());
						p.setCveprog(dto.getCveprog());
						p.setCvefin(dto.getCvefin());
						p.setCvetemas(dto.getCvetemas());
						p.setUserid(getUserDetails().getUsername());
						p.setUsuario(getUserDetails().getFullName());
						p.setFeccap(UtilDate.getDateSystem());
						p.setSectorid(getUserDetails().getIdSector());
						p.setIdRef(0L);
						Proposito valida = this.propositoRepository.findOne(PropositoPredicates.existeProposito(p));
						if (null == valida || bModificar) {
							matrizIndicadoresService.saveIndicador(p, 2);
							llenarListaDependencias();
							this.bModificar = Boolean.FALSE;
							UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO,
									"Propósito guardado correctamente", "Propósito guardado correctamente");
							estadoInicialBotonesProposito(rowMaster);
						} else {
							UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
									"Propósito ya existe con Cve Dependencia " + p.getClvdepg() + " Clave Programa "
											+ p.getCveprog() + " Clave Temas de Desarrollo " + p.getCvetemas()
											+ " Num. Indicador " + p.getCvepro(),
									"Propósito ya existe con Cve Dependencia " + p.getClvdepg() + " Clave Programa "
											+ p.getCveprog() + " Clave Temas de Desarrollo " + p.getCvetemas()
											+ " Num. Indicador " + p.getCvepro());
						}
					}
				} else {
					UtilFront.generateNotificationFront(FacesMessage.SEVERITY_WARN, "Debe capturar Encabezado",
							"Debe capturar Encabezado");
				}
			} catch (Exception e) {
				e.printStackTrace();
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
						"Propósito ya existe con Cve Dependencia " + p.getClvdepg() + " Clave Programa "
								+ p.getCveprog() + " Clave Temas de Desarrollo " + p.getCvetemas() + " Num. Indicador "
								+ p.getCvepro(),
						"Propósito ya existe con Cve Dependencia " + p.getClvdepg() + " Clave Programa "
								+ p.getCveprog() + " Clave Temas de Desarrollo " + p.getCvetemas() + " Num. Indicador "
								+ p.getCvepro());
			}
			break;

		case 3:
			Componente c = null;
			try {
				MatrizIndicadoresDTO dto = masterList.get(rowMaster);
				if (matrizIndicadoresService.existsMatrizIndicadores(dto.getClvdepg(), dto.getCveprog(),
						dto.getCvetemas())) {
					c = dto.getListaComponente().get(rowIndicador);
					if ("null".equals(c.getCveind())) {
						UtilFront.generateNotificationFront(FacesMessage.SEVERITY_WARN, "Ingrese Código Indicador",
								"Ingrese Código Indicador");
					} else {
						c.setClvdepg(dto.getClvdepg());
						c.setCveprog(dto.getCveprog());
						c.setCvefin(dto.getCvefin());
						c.setCvetemas(dto.getCvetemas());
						c.setUserid(getUserDetails().getUsername());
						c.setUsuario(getUserDetails().getFullName());
						c.setFeccap(UtilDate.getDateSystem());
						c.setIdRef(0L);
						c.setSectorid(getUserDetails().getIdSector());
						Componente valida = this.componenteRepository.findOne(ComponentePredicates.existecomponente(c));
						if (null == valida || bModificar) {
							matrizIndicadoresService.saveIndicador(c, 3);
							llenarListaDependencias();
							bModificar = Boolean.FALSE;
							UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO,
									"Componente guardado correctamente", "Componente guardado correctamente");
							estadoInicialBotonesComponente(rowMaster);
						} else {
							UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
									"Componente ya existe con Cve Dependencia " + c.getClvdepg() + " Clave Programa "
											+ c.getCveprog() + " Clave Temas de Desarrollo " + c.getCvetemas()
											+ " Num. Indicador " + c.getCvecom(),
									"Componente ya existe con Cve Dependencia " + c.getClvdepg() + " Clave Programa "
											+ c.getCveprog() + " Clave Temas de Desarrollo " + c.getCvetemas()
											+ " Num. Indicador " + c.getCvecom());
						}
					}
				} else {
					UtilFront.generateNotificationFront(FacesMessage.SEVERITY_WARN, "Debe capturar Encabezado",
							"Debe capturar Encabezado");
				}
			} catch (Exception e) {
				e.printStackTrace();
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
						"Componente ya existe con Cve Dependencia " + c.getClvdepg() + " Clave Programa "
								+ c.getCveprog() + " Clave Temas de Desarrollo " + c.getCvetemas() + " Num. Indicador "
								+ c.getCvecom(),
						"Componente ya existe con Cve Dependencia " + c.getClvdepg() + " Clave Programa "
								+ c.getCveprog() + " Clave Temas de Desarrollo " + c.getCvetemas() + " Num. Indicador "
								+ c.getCvecom());
			}
			break;

		case 4:
			Actividad a = null;
			try {
				MatrizIndicadoresDTO dto = masterList.get(rowMaster);
				if (matrizIndicadoresService.existsMatrizIndicadores(dto.getClvdepg(), dto.getCveprog(),
						dto.getCvetemas())
						&& matrizIndicadoresService.existsComponente(dto.getClvdepg(), dto.getCveprog(),
								dto.getCvetemas())) {
					a = dto.getListaActividad().get(rowIndicador);
					if ("null".equals(a.getCveind())) {
						UtilFront.generateNotificationFront(FacesMessage.SEVERITY_WARN, "Ingrese Código Indicador",
								"Ingrese Código Indicador");
					} else {
						a.setClvdepg(dto.getClvdepg());
						a.setCveprog(dto.getCveprog());
						a.setCvefin(dto.getCvefin());
						a.setCvetemas(dto.getCvetemas());
						a.setUserid(getUserDetails().getUsername());
						a.setUsuario(getUserDetails().getFullName());
						a.setFeccap(UtilDate.getDateSystem());
						a.setCvecom(getComponenteActual());
						a.setIdRef(0L);
						a.setSectorid(getUserDetails().getIdSector());
						Actividad validar = this.actividadRepository.findOne(ActividadPredicate.existeActividad(a));
						if (null == validar || bModificar) {
							matrizIndicadoresService.saveIndicador(a, 4);

							// TODO
							// llenarListaActividad2(rowMaster, rowComp);
							bModificar = Boolean.FALSE;
							UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO,
									"Actividad guardada correctamente", "Actividad guardada correctamente");
							estadoInicialBotonesActividad(rowMaster);
						} else {
							UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
									"Actividad ya existe con Cve Dependencia " + a.getClvdepg() + " Clave Programa "
											+ a.getCveprog() + " Clave Temas de Desarrollo " + a.getCvetemas()
											+ " Num. Indicador " + a.getCveact(),
									"Actividad ya existe con Cve Dependencia " + a.getClvdepg() + " Clave Programa "
											+ a.getCveprog() + " Clave Temas de Desarrollo " + a.getCvetemas()
											+ " Num. Indicador " + a.getCveact());
						}
					}
				} else {
					UtilFront.generateNotificationFront(FacesMessage.SEVERITY_WARN, "Debe capturar Componente",
							"Debe capturar Componente");
				}
			} catch (Exception e) {
				e.printStackTrace();
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
						"Actividad ya existe con Cve Dependencia " + a.getClvdepg() + " Clave Programa "
								+ a.getCveprog() + " Clave Temas de Desarrollo " + a.getCvetemas() + " Num. Indicador "
								+ a.getCveact(),
						"Actividad ya existe con Cve Dependencia " + a.getClvdepg() + " Clave Programa "
								+ a.getCveprog() + " Clave Temas de Desarrollo " + a.getCvetemas() + " Num. Indicador "
								+ a.getCveact());
			}
			break;

		default:
			break;
		}

	}

	/**
	 * Update desc dep.
	 *
	 * @param row the row
	 */
	public void updateDescDep(Integer row) {
		MatrizIndicadoresDTO mat = masterList.get(row);
		mat.setCveprog("");
		mat.setCveProgDesc("");

		mat.setClvdepgDesc(matrizIndicadoresService.getDescripcionDep(masterList.get(row).getClvdepg()));
		LOGGER.debug("updateDescDep BuscandoMB " + masterList.get(row).getClvdepg() + " Resultado: "
				+ matrizIndicadoresService.getDescripcionDep(masterList.get(row).getClvdepg()));

		llenarListaProgramas(row);
	}

	/**
	 * Update desc prog.
	 *
	 * @param row the row
	 */
	public void updateDescProg(Integer row) {
		masterList.get(row)
				.setCveProgDesc(matrizIndicadoresService.getDescripcionProg(masterList.get(row).getCveprog()));
	}

	/**
	 * Update desc tema.
	 *
	 * @param row the row
	 */
	public void updateDescTema(Integer row) {
		masterList.get(row)
				.setCveTemaDesc(matrizIndicadoresService.getDescripcionTema(masterList.get(row).getCvetemas()));
	}

	/**
	 * Update desc nombre ind.
	 *
	 * @param rowMaster    the row master
	 * @param rowIndicador the row indicador
	 * @param tipo         the tipo
	 */
	public void updateDescNombreInd(Integer rowMaster, Integer rowIndicador, Integer tipo) {
		MatrizIndicadoresDTO mat = masterList.get(rowMaster);
		switch (tipo) {
		case 1:
			Finalidad f = mat.getListaFinalidad().get(rowIndicador);
			f.setNombre(matrizIndicadoresService.getDescripcionIndicador(f.getCveind()));
			for (Mir mir : listaCodigoIndicadorFinalidad) {
				if (f.getCveind().equals(mir.getCodigo())) {
					f.setCvefinal(mir.getConsec().longValue());
					break;
				}
			}
			break;
		case 2:
			Proposito p = mat.getListaProposito().get(rowIndicador);
			p.setNombre(matrizIndicadoresService.getDescripcionIndicador(p.getCveind()));
			for (Mir mir : listaCodigoIndicadorProposito) {
				if (p.getCveind().equals(mir.getCodigo())) {
					p.setCvepro(mir.getConsec().longValue());
					break;
				}
			}
			break;
		case 3:
			Componente c = mat.getListaComponente().get(rowIndicador);
			c.setNombre(matrizIndicadoresService.getDescripcionIndicador(c.getCveind()));
			for (Mir mir : listaCodigoIndicadorComponente) {
				if (c.getCveind().equals(mir.getCodigo())) {
					c.setCvecom(mir.getConsec());
					break;
				}
			}
			break;
		case 4:
			Actividad a = mat.getListaActividad().get(rowIndicador);
			a.setNombre(matrizIndicadoresService.getDescripcionIndicador(a.getCveind()));
			for (Mir mir : listaCodigoIndicadorActividad) {
				if (a.getCveind().equals(mir.getCodigo())) {
					a.setCveact(mir.getConsec());
					break;
				}
			}
			break;

		default:
			break;
		}

	}

	/**
	 * Llenar lista programas.
	 *
	 * @param row the row
	 */
	public void llenarListaProgramas(Integer row) {
		MatrizIndicadoresDTO d = masterList.get(row);
		d.getProgramasMap().clear();
		d.setCveProgDesc(null);
		for (String claveP : matrizIndicadoresService.findProgramasMatrizInd(d.getClvdepg())) {
			d.getProgramasMap().put(claveP, matrizIndicadoresService.getDescripcionProg(claveP));

		}
	}

	/**
	 * Llenar lista temas.
	 */
	public void llenarListaTemas() {
		LOGGER.debug("Llenando lista TEMAS MB");
		clvTemaList.clear();
		for (Cpd tema : matrizIndicadoresService.findAllTemasMatrizInd()) {
			if (tema.getCvetemas().trim().length() >= 8) {
				clvTemaList.add(tema.getCvetemas());
			}
		}
	}

	/**
	 * Estado inicial botones.
	 */
	public void estadoInicialBotones() {
		if (masterList.isEmpty()) {
			masterList.add(new MatrizIndicadoresDTO());
			this.setCapturaVisibleModificar(false);
			this.setCapturaDesabilitadoAdicionar(false);
			this.setCapturaDesabilitadoBorrar(true);
			this.setCapturaDesabilitadoReset(true);
			this.setCapturaDesabilitadoCancelar(true);
			this.setCapturaVisibleSalvar(false);
			this.setDeshabilitadoEditables(true);
			this.setCapturaDeshabilitado(true);
			this.setCapturaDeshabilitadoObjetivo(true);
		} else {
			if (masterList.size() == 1 && masterList.get(ZERO).getClvdepg() == null) {
				this.setCapturaVisibleModificar(false);
				this.setCapturaDesabilitadoAdicionar(false);
				this.setCapturaDesabilitadoBorrar(true);
				this.setCapturaDesabilitadoReset(true);
				this.setCapturaDesabilitadoCancelar(true);
				this.setCapturaVisibleSalvar(false);
				this.setDeshabilitadoEditables(true);
				this.setCapturaDeshabilitado(true);
				this.setCapturaDeshabilitadoObjetivo(true);
			} else {
				this.setCapturaVisibleModificar(true);
				this.setCapturaDesabilitadoAdicionar(false);
				this.setCapturaDesabilitadoBorrar(false);
				this.setCapturaDesabilitadoReset(true);
				this.setCapturaDesabilitadoCancelar(true);
				this.setCapturaVisibleSalvar(false);
				this.setDeshabilitadoEditables(true);
				this.setCapturaDeshabilitado(true);
				this.setCapturaDeshabilitadoObjetivo(true);
			}
		}
	}

	/**
	 * Estado inicial botones finalidad.
	 *
	 * @param row the row
	 */
	public void estadoInicialBotonesFinalidad(Integer row) {
		if (!masterList.isEmpty()) {
			for (MatrizIndicadoresDTO dto : masterList) {
				if (dto.getListaFinalidad().isEmpty()) {
					dto.getListaFinalidad().add(new Finalidad());
				}
			}
			if (!masterList.get(row).getListaFinalidad().isEmpty()
					&& masterList.get(row).getListaFinalidad().get(ZERO).getClvdepg() == null) {// despues
																								// de
																								// agregar
																								// es
																								// una
																								// finalidad
																								// vacia
				setFinalidadVisibleModificar(false);
				setFinalidadDisabledAdicionar(false);
				setFinalidadDisabledBorrar(true);
				setFinalidadDisabledReset(true);
				setFinalidadDisabledCancelar(true);
				setFinalidadVisibleSalvar(false);
				setFinalidadDisabledEditables(true);
			} else {
				setFinalidadVisibleModificar(true);
				setFinalidadDisabledAdicionar(false);
				setFinalidadDisabledBorrar(false);
				setFinalidadDisabledReset(true);
				setFinalidadDisabledCancelar(true);
				setFinalidadVisibleSalvar(false);
				setFinalidadDisabledEditables(true);
			}
		}
	}

	/**
	 * Estado inicial botones proposito.
	 *
	 * @param row the row
	 */
	public void estadoInicialBotonesProposito(Integer row) {
		if (!masterList.isEmpty()) {
			for (MatrizIndicadoresDTO dto : masterList) {
				if (dto.getListaProposito().isEmpty()) {
					dto.getListaProposito().add(new Proposito());
				}
			}
			if (!masterList.get(row).getListaProposito().isEmpty()
					&& masterList.get(row).getListaProposito().get(ZERO).getClvdepg() == null) {// despues
																								// de
																								// agregar
																								// es
																								// un
																								// proposito
																								// vacio
				setPropositoVisibleModificar(false);
				setPropositoDisabledAdicionar(false);
				setPropositoDisabledBorrar(true);
				setPropositoDisabledReset(true);
				setPropositoDisabledCancelar(true);
				setPropositoVisibleSalvar(false);
				setPropositoDisabledEditables(true);
			} else {
				setPropositoVisibleModificar(true);
				setPropositoDisabledAdicionar(false);
				setPropositoDisabledBorrar(false);
				setPropositoDisabledReset(true);
				setPropositoDisabledCancelar(true);
				setPropositoVisibleSalvar(false);
				setPropositoDisabledEditables(true);
			}
		}

	}

	/**
	 * Estado inicial botones componente.
	 *
	 * @param row the row
	 */
	public void estadoInicialBotonesComponente(Integer row) {
		if (!masterList.isEmpty()) {
			for (MatrizIndicadoresDTO dto : masterList) {
				if (dto.getListaComponente().isEmpty()) {
					dto.getListaComponente().add(new Componente());
				}
			}
			if (!masterList.get(row).getListaComponente().isEmpty()
					&& masterList.get(row).getListaComponente().get(ZERO).getClvdepg() == null) {// despues
																									// de
																									// agregar
																									// es
																									// un
																									// componente
																									// vacio
				setComponenteVisibleModificar(false);
				setComponenteDisabledAdicionar(false);
				setComponenteDisabledBorrar(true);
				setComponenteDisabledReset(true);
				setComponenteDisabledCancelar(true);
				setComponenteVisibleSalvar(false);
				setComponenteDisabledEditables(true);
			} else {
				setComponenteVisibleModificar(true);
				setComponenteDisabledAdicionar(false);
				setComponenteDisabledBorrar(false);
				setComponenteDisabledReset(true);
				setComponenteDisabledCancelar(true);
				setComponenteVisibleSalvar(false);
				setComponenteDisabledEditables(true);
			}
		}
	}

	/**
	 * Estado inicial botones actividad.
	 *
	 * @param row the row
	 */
	public void estadoInicialBotonesActividad(Integer row) {
		if (!masterList.isEmpty()) {
			for (MatrizIndicadoresDTO dto : masterList) {
				if (dto.getListaActividad().isEmpty()) {
					dto.getListaActividad().add(new Actividad());
				}
			}
			if (!masterList.get(row).getListaActividad().isEmpty()
					&& masterList.get(row).getListaActividad().get(ZERO).getClvdepg() == null) {// despues
																								// de
																								// agregar
																								// es
																								// una
																								// actividad
																								// vacia
				setActividadVisibleModificar(false);
				setActividadDisabledAdicionar(false);
				setActividadDisabledBorrar(true);
				setActividadDisabledReset(true);
				setActividadDisabledCancelar(true);
				setActividadVisibleSalvar(false);
				setActividadDisabledEditables(true);
			} else {
				setActividadVisibleModificar(true);
				setActividadDisabledAdicionar(false);
				setActividadDisabledBorrar(false);
				setActividadDisabledReset(true);
				setActividadDisabledCancelar(true);
				setActividadVisibleSalvar(false);
				setActividadDisabledEditables(true);
			}
			if (masterList.get(row).getListaComponente().isEmpty()
					|| masterList.get(row).getListaComponente().get(ZERO).getClvdepg() == null) {
				setActividadDisabledAdicionar(true);
			} else {
				setActividadDisabledAdicionar(false);
			}
		}
	}

	/**
	 * Validate combos head.
	 *
	 * @param matrizDTO the matriz DTO
	 * @return the boolean
	 */
	public Boolean validateCombosHead(MatrizIndicadoresDTO matrizDTO) {

		if (matrizDTO.getClvdepg().equals("null")) {

			generateNotificationFront(FacesMessage.SEVERITY_INFO, "La Clave de la Dependencia es Obligatoría", "");
			return Boolean.FALSE;
		} else if (matrizDTO.getCveprog().equals("null")) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "La Clave del Programa es Obligatoría", "");
			return Boolean.FALSE;
		} else if (matrizDTO.getCvetemas().equals("null")) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "la Clave del Tema es Obligatoría", "");
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * Salvar encabezado.
	 *
	 * @param row the row
	 */
	public void salvarEncabezado(Integer row) {
		try {
			MatrizIndicadoresDTO mat = masterList.get(row);
			if (validateCombosHead(mat) == Boolean.TRUE) {
				if (isModificarEncabezado()) {
					mat.setId(encabezadoEnEdicion.getId());
					mat.setCvefin(" ");
					mat.setIdsector(getUserDetails().getIdSector());
					mat.setUserid(getUserDetails().getUsername());
					mat.setUsuario(getUserDetails().getFullName());
					mat.setIdRef(0);
					mat.setFeccap(new Date());

					LOGGER.debug("!isModificarEncabezado " + !isModificarEncabezado());
					matrizIndicadoresService.saveCabecero(mat);

					UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Encabezado Guardado correctamente",
							"Encabezado Guardado correctamente");

					estadoInicialBotones();
					estadoInicialBotonesFinalidad(row);
					estadoInicialBotonesProposito(row);
					estadoInicialBotonesComponente(row);
					estadoInicialBotonesActividad(row);
					setModificarEncabezado(false);
					currentPageDet = 0;

					this.llenarListaCodigoIndicadorFinalidad(mat.getCveprog());
					this.llenarListaCodigoIndicadorProposito(mat.getCveprog());
					this.llenarListaCodigoIndicadorActividad(mat.getCveprog());
					this.llenarListaCodigoIndicadorProposito(mat.getCveprog());
					this.panelToggleoPen(row);
				} else {
					if (matrizIndicadoresService.existsMatrizIndicadores(mat.getClvdepg(), mat.getCveprog(),
							mat.getCvetemas())) {
						UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
								"\"Matind ya existe con Cve Dependencia \"" + mat.getClvdepg() + "\" Clave Programa \""
										+ mat.getCveprog() + "\" Fuente de Financiamiento \"" + mat.getCvefin()
										+ "\" Clave Temas de Desarrollo \"" + mat.getCvetemas()
										+ "\" (132) Update cancelled\"",
								"\"Matind ya existe con Cve Dependencia \"" + mat.getClvdepg() + "\" Clave Programa \""
										+ mat.getCveprog() + "\" Fuente de Financiamiento \"" + mat.getCvefin()
										+ "\" Clave Temas de Desarrollo \"" + mat.getCvetemas()
										+ "\" (132) Update cancelled\"");
					} else {
						mat.setCvefin(" ");
						mat.setIdsector(getUserDetails().getIdSector());
						mat.setUserid(getUserDetails().getUsername());
						mat.setUsuario(getUserDetails().getFullName());
						mat.setIdRef(0);
						mat.setFeccap(new Date());

						LOGGER.debug("!isModificarEncabezado " + !isModificarEncabezado());
						matrizIndicadoresService.saveCabecero(mat);

						UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO,
								"Encabezado guardado correctamente", "Encabezado guardado correctamente");

						findAllIndicadores();
//						estadoInicialBotones();
//						estadoInicialBotonesFinalidad(row);
//						estadoInicialBotonesProposito(row);
//						estadoInicialBotonesComponente(row);
//						estadoInicialBotonesActividad(row);
//						setModificarEncabezado(false);
//						this.llenarListaCodigoIndicadorFinalidad(mat.getCveprog());
//						this.llenarListaCodigoIndicadorProposito(mat.getCveprog());
//						this.llenarListaCodigoIndicadorComponente(mat.getCveprog());
//						this.llenarListaCodigoIndicadorActividad(mat.getCveprog());
//						
//
//						
//						currentPageDet = 0;
//						this.panelToggleoPen(1);
						
					}
				}
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al salvar el Encabezado",
					"Ocurrió un error al salvar el  Encabezado");
		}

	}

	/**
	 * Modificar captura.
	 *
	 * @param row the row
	 */
	public void modificarCaptura(Integer row) {

		List<MatrizIndicadoresDTO> masterListTemp = new ArrayList<MatrizIndicadoresDTO>();
		masterListTemp = matrizIndicadoresService.listaMatrizIndicadores();
		MatrizIndicadoresDTO mat = masterListTemp.get(row);
		oldMatrizDTO = masterListTemp.get(row);
		encabezadoEnEdicion = new MatrizIndicadoresDTO();
		BeanUtils.copyProperties(mat, encabezadoEnEdicion);
		encabezadoEnEdicion.setId(mat.getId());
		boolean finalidad = matrizIndicadoresService.existsFinalidad(mat.getClvdepg(), mat.getCveprog(),
				mat.getCvetemas());
		boolean proposito = matrizIndicadoresService.existsProposito(mat.getClvdepg(), mat.getCveprog(),
				mat.getCvetemas());
		boolean componente = matrizIndicadoresService.existsComponente(mat.getClvdepg(), mat.getCveprog(),
				mat.getCvetemas());

		LOGGER.debug(finalidad + " " + proposito + " " + componente);
		// if (finalidad) {
		// UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO,
		// "Existen registros de Finalidad. No se puede modificar",
		// "Existen registros de Finalidad. No se puede modificar");
		// } else
		//
		// if (proposito) {
		// UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO,
		// "Existen registros de Propósito. No se puede modificar",
		// "Existen registros de Propósito. No se puede modificar");
		// } else
		//
		// if (componente) {
		// UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO,
		// "Existen registros de Componente. No se puede modificar",
		// "Existen registros de Componente. No se puede modificar");
		// }
		//
		// if (!finalidad && !proposito && !componente) {
		setCapturaDeshabilitado(true);
		setCapturaDeshabilitadoObjetivo(false);
		setCapturaVisibleSalvar(true);
		setCapturaVisibleModificar(false);
		setCapturaDesabilitadoAdicionar(true);
		setCapturaDesabilitadoBorrar(true);
		setCapturaDesabilitadoReset(false);
		setCapturaDesabilitadoCancelar(false);
		setBanderaModificar(Boolean.TRUE);

		// }
		capturaAdicionar = false;
		modificarEncabezado = true;
	}

	/**
	 * Modificar indicador.
	 *
	 * @param rowMaster the row master
	 * @param rowInd    the row ind
	 * @param tipoInd   the tipo ind
	 */
	public void modificarIndicador(Integer rowMaster, Integer rowInd, Integer tipoInd) {

		switch (tipoInd) {
		case 1:
			setFinalidadVisibleSalvar(true);
			setFinalidadVisibleModificar(false);
			setFinalidadDisabledAdicionar(true);
			setFinalidadDisabledBorrar(true);
			setFinalidadDisabledReset(false);
			setFinalidadDisabledCancelar(false);
			setFinalidadDisabledEditables(false);
			bModificar = Boolean.TRUE;
			break;
		case 2:
			setPropositoVisibleSalvar(true);
			setPropositoVisibleModificar(false);
			setPropositoDisabledAdicionar(true);
			setPropositoDisabledBorrar(true);
			setPropositoDisabledReset(false);
			setPropositoDisabledCancelar(false);
			setPropositoDisabledEditables(false);
			bModificar = Boolean.TRUE;
			break;
		case 3:
			setComponenteVisibleSalvar(true);
			setComponenteVisibleModificar(false);
			setComponenteDisabledAdicionar(true);
			setComponenteDisabledBorrar(true);
			setComponenteDisabledReset(false);
			setComponenteDisabledCancelar(false);
			setComponenteDisabledEditables(false);
			bModificar = Boolean.TRUE;
			break;
		case 4:
			setActividadVisibleSalvar(true);
			setActividadVisibleModificar(false);
			setActividadDisabledAdicionar(true);
			setActividadDisabledBorrar(true);
			setActividadDisabledReset(false);
			setActividadDisabledCancelar(false);
			setActividadDisabledEditables(false);
			bModificar = Boolean.TRUE;
			break;

		default:
			break;
		}
	}

	/**
	 * Agrega un indicador si la lista esta vacia al cambiar de tab.
	 *
	 * @param rowMaster the row master
	 */
	public void agregarIndicador(Integer rowMaster) {
		MatrizIndicadoresDTO mat = masterList.get(rowMaster);
		if (mat.getListaFinalidad().isEmpty()) {
			mat.getListaFinalidad().add(new Finalidad());
		}
		if (mat.getListaProposito().isEmpty()) {
			mat.getListaProposito().add(new Proposito());
		}
		if (mat.getListaComponente().isEmpty()) {
			mat.getListaComponente().add(new Componente());
		}
		if (mat.getListaActividad().isEmpty()) {
			mat.getListaActividad().add(new Actividad());
		}

	}

	/** The mat. */
	MatrizIndicadoresDTO mat = null;

	/**
	 * Reset captura.
	 *
	 * @param row the row
	 */
	public void resetCaptura(Integer row) {
		this.setCapturaDeshabilitado(false);
		this.setCapturaDeshabilitadoObjetivo(false);
		mat = new MatrizIndicadoresDTO();
		// primer registro sin nada en la base
		if (matrizIndicadoresService.listaMatrizIndicadores().isEmpty()) {
			masterList.clear();
			masterList.add(new MatrizIndicadoresDTO());
			// estadoInicialBotones();
		} else {

			if (this.getBanderaModificar()) {

				// mat.setProgramasMap(mat.getClvdepg(),
				// mat.getCveProgDesc());
				mat = this.resetDTO(currentPage);
				this.setCapturaDeshabilitado(false);
				this.setCapturaDeshabilitadoObjetivo(false);

			}
			masterList.set(row, mat);

		}
		this.setCapturaDeshabilitado(false);
		this.setCapturaDeshabilitadoObjetivo(false);
	}

	/**
	 * Reset indicador.
	 *
	 * @param rowMaster the row master
	 * @param rowInd    the row ind
	 * @param tipoInd   the tipo ind
	 */
	public void resetIndicador(Integer rowMaster, Integer rowInd, Integer tipoInd) {
		LOGGER.debug("row Master " + rowMaster + " row Indicador " + rowInd + " tipo " + tipoInd);
		MatrizIndicadoresDTO mat = masterList.get(rowMaster);
		switch (tipoInd) {
		case 1:
			Finalidad fin = matrizIndicadoresService.findFinalidadById(mat.getListaFinalidad().get(rowInd).getId());
			if (fin == null) {
				fin = new Finalidad();
			}
			mat.getListaFinalidad().set(rowInd, fin);
			setFinalidadDisabledEditables(false);
			break;
		case 2:
			Proposito prop = matrizIndicadoresService.findPropositoById(mat.getListaProposito().get(rowInd).getId());
			if (prop == null) {
				prop = new Proposito();
			}
			mat.getListaProposito().set(rowInd, prop);
			setPropositoDisabledEditables(false);
			break;
		case 3:
			Componente comp = matrizIndicadoresService.findComponenteById(mat.getListaComponente().get(rowInd).getId());
			if (comp == null) {
				comp = new Componente();
			}
			mat.getListaComponente().set(rowInd, comp);
			setComponenteDisabledEditables(false);
			break;
		case 4:
			Actividad act = matrizIndicadoresService.findActividadById(mat.getListaActividad().get(rowInd).getId());
			if (act == null) {
				act = new Actividad();
			}
			mat.getListaActividad().set(rowInd, act);
			setActividadDisabledEditables(false);
			break;

		default:
			break;
		}

	}

	/**
	 * Cambiar pagina.
	 *
	 * @param event the event
	 */
	public void cambiarPagina(PageEvent event) {
		currentPage = event.getPage();
		estadoInicialBotonesFinalidad(currentPage);
		estadoInicialBotonesProposito(currentPage);
		estadoInicialBotonesComponente(currentPage);
		estadoInicialBotonesActividad(currentPage);
		this.currentPageDet = 0;
		if (this.getbAdicionar() == Boolean.FALSE) {
			this.resetDTOOnChange(currentPage);
		}
	}

	/**
	 * Cambiar pagina fina.
	 *
	 * @param event the event
	 */
	public void cambiarPaginaFina(PageEvent event) {
		currentPageDet = event.getPage();

	}

	/**
	 * Cambiar pagina compo.
	 *
	 * @param event the event
	 */
	public void cambiarPaginaCompo(PageEvent event) {
		currentPageDet = event.getPage();

	}

	/**
	 * Cambiar pagina pro.
	 *
	 * @param event the event
	 */
	public void cambiarPaginaPro(PageEvent event) {
		currentPageDet = event.getPage();

	}

	/**
	 * Cambiar pagina act.
	 *
	 * @param event the event
	 */
	public void cambiarPaginaAct(PageEvent event) {
		currentPageDet = event.getPage();

	}

	/**
	 * Adicionar captura.
	 *
	 * @param row the row
	 */
	public void adicionarCaptura(Integer row) {
		this.setbAdicionar(Boolean.TRUE);
		this.setCapturaVisibleSalvar(true);
		this.setCapturaVisibleModificar(false);
		this.setCapturaDesabilitadoAdicionar(true);
		this.setCapturaDesabilitadoBorrar(true);
		this.setCapturaDesabilitadoCancelar(false);
		this.setCapturaDesabilitadoReset(false);
		this.finalidadDisabledAdicionar = Boolean.TRUE;
		this.componenteDisabledAdicionar = Boolean.TRUE;
		this.propositoDisabledAdicionar = Boolean.TRUE;
		if (masterList.isEmpty()) {
			masterList.add(new MatrizIndicadoresDTO());
			this.setCapturaDeshabilitado(false);
			this.setCapturaDeshabilitadoObjetivo(false);

		} else {
			if (masterList.size() == 1 && masterList.get(ZERO).getClvdepg() == null) {
				this.setCapturaDeshabilitado(false);
				this.setCapturaDeshabilitadoObjetivo(false);
			} else {
				if (masterList.size() == 1 && masterList.get(ZERO).getClvdepg() != null) {
					this.modificarCaptura(row);
				} else {
				}
				masterList.add(new MatrizIndicadoresDTO());
				this.setCapturaDeshabilitado(false);
				this.setCapturaDeshabilitadoObjetivo(false);

			}
		}
		this.panelToggle(currentPageDet);
		capturaAdicionar = true;
		modificarEncabezado = false;
	}

	/**
	 * Borrar encabezado.
	 *
	 * @param row the row
	 */
	public void borrarEncabezado(Integer row) {
		LOGGER.debug("Eliminando encabezado==> " + row);
		llenarCabecera();
		try {
			MatrizIndicadoresDTO mat = masterList.get(row);
			if (matrizIndicadoresService.existsFinalidad(mat.getClvdepg(), mat.getCveprog(), mat.getCvetemas())) {
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
						"Existen registros de Finalidad. No se puede eliminar",
						"Existen registros de Finalidad. No se puede eliminar");
			} else if (matrizIndicadoresService.existsProposito(mat.getClvdepg(), mat.getCveprog(),
					mat.getCvetemas())) {
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
						"Existen registros de Propósito. No se puede eliminar",
						"Existen registros de Propósito. No se puede eliminar");

			} else if (matrizIndicadoresService.existsComponente(mat.getClvdepg(), mat.getCveprog(),
					mat.getCvetemas())) {
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
						"Existen registros de Componente. No se puede eliminar",
						"Existen registros de Componente. No se puede eliminar");

			} else {
				matrizIndicadoresService.deleteCabecero(mat.getId());

				this.setCapturaDeshabilitado(true);
				this.setCapturaDeshabilitadoObjetivo(true);
				this.setDeshabilitadoEditables(true);
				RequestContext.getCurrentInstance()
						.execute("PF('indWdg').paginator.setPage(" + (currentPage - 1) + ");");

				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO,
						"Matriz de Indicador borrado correctamente.", "Matriz de Indicador borrado correctamente.");
			}
		} catch (Exception e) {
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
					"Ocurrió un error al borrar Matriz de Indicador.",
					"Ocurrió un error al borrar Matriz de Indicador.");
		}

	}

	/**
	 * Cancelar captura.
	 *
	 * @param row the row
	 */
	public void cancelarCaptura(Integer row) {
		Integer indexOrigin = row;
		Boolean bCancel = Boolean.FALSE;
		if (matrizIndicadoresService.listaMatrizIndicadores().isEmpty()) {// primer
																			// registro
																			// sin
																			// nada
																			// en
																			// la
																			// base
			masterList.clear();
			masterList.add(new MatrizIndicadoresDTO());
			estadoInicialBotones();
		} else {
			// if (masterList.size() == 1) {
			// this.resetCaptura(indexOrigin);
			// bCancel = Boolean.TRUE;
			// } else if (!this.banderaModificar) {
			// indexOrigin = masterList.size() - 1;
			// masterList.remove(indexOrigin);
			// capturaAdicionar = false;
			// bCancel = Boolean.TRUE;
			// }
			this.setCapturaVisibleSalvar(false);
			this.setCapturaVisibleModificar(true);
			this.setCapturaDesabilitadoAdicionar(false);

			masterList.set(currentPage, this.resetDTO(currentPage));
			if (masterList.size() > 0)
				this.setCapturaDesabilitadoBorrar(false);
			else
				this.setCapturaDesabilitadoBorrar(true);

			this.setCapturaDesabilitadoCancelar(true);
			this.setCapturaDesabilitadoReset(true);
			this.setCapturaDeshabilitado(true);
			this.setCapturaDeshabilitadoObjetivo(true);
			this.setDeshabilitadoEditables(true);
			this.setBanderaModificar(Boolean.FALSE);
			currentPage = 0;

		}
		findAllIndicadores();
		panelToggle(currentPage);
	}

	/**
	 * Cancelar captura indicador.
	 *
	 * @param rowMaster the row master
	 * @param rowInd    the row ind
	 * @param tipoInd   the tipo ind
	 */
	public void cancelarCapturaIndicador(Integer rowMaster, Integer rowInd, Integer tipoInd) {
		MatrizIndicadoresDTO mat = masterList.get(rowMaster);
		bModificar = Boolean.FALSE;
		Long id = 0L;
		switch (tipoInd) {

		case 1:
			id = null == mat.getListaFinalidad().get(rowInd).getId() ? 0 : mat.getListaFinalidad().get(rowInd).getId();
			Finalidad fin = matrizIndicadoresService.findFinalidadById(id);
			if (fin == null) {
				mat.getListaFinalidad().remove(rowInd.intValue());
			} else {
				mat.getListaFinalidad().set(rowInd, fin);
			}
			estadoInicialBotonesFinalidad(rowMaster);
			break;
		case 2:
			id = null == mat.getListaProposito().get(rowInd).getId() ? 0 : mat.getListaProposito().get(rowInd).getId();
			Proposito prop = matrizIndicadoresService.findPropositoById(id);
			if (prop == null) {
				mat.getListaProposito().remove(rowInd.intValue());
			} else {
				mat.getListaProposito().set(rowInd, prop);
			}
			estadoInicialBotonesProposito(rowMaster);
			break;
		case 3:
			id = null == mat.getListaComponente().get(rowInd).getId() ? 0
					: mat.getListaComponente().get(rowInd).getId();
			Componente comp = matrizIndicadoresService.findComponenteById(id);
			if (comp == null) {
				mat.getListaComponente().remove(rowInd.intValue());
			} else {
				mat.getListaComponente().set(rowInd, comp);
			}
			estadoInicialBotonesComponente(rowMaster);
			break;
		case 4:
			id = null == mat.getListaActividad().get(rowInd).getId() ? 0 : mat.getListaActividad().get(rowInd).getId();
			Actividad act = matrizIndicadoresService.findActividadById(id);
			if (act == null) {
				mat.getListaActividad().remove(rowInd.intValue());
			} else {
				mat.getListaActividad().set(rowInd, act);
			}
			estadoInicialBotonesActividad(rowMaster);
			break;

		default:
			break;
		}
	}

	/**
	 * Llenar lista finalidad.
	 *
	 * @param clvdepg  the clvdepg
	 * @param cveprog  the cveprog
	 * @param cvetemas the cvetemas
	 * @return the list
	 */
	public List<Finalidad> llenarListaFinalidad(String clvdepg, String cveprog, String cvetemas) {
		return matrizIndicadoresService.llenarListaFinalidad(clvdepg, cveprog, cvetemas);
	}

	/**
	 * Llenar lista proposito.
	 *
	 * @param clvdepg  the clvdepg
	 * @param cveprog  the cveprog
	 * @param cvetemas the cvetemas
	 * @return the list
	 */
	public List<Proposito> llenarListaProposito(String clvdepg, String cveprog, String cvetemas) {
		return matrizIndicadoresService.llenarListaProposito(clvdepg, cveprog, cvetemas);
	}

	/**
	 * Llenar lista componente.
	 *
	 * @param clvdepg  the clvdepg
	 * @param cveprog  the cveprog
	 * @param cvetemas the cvetemas
	 * @return the list
	 */
	public List<Componente> llenarListaComponente(String clvdepg, String cveprog, String cvetemas) {
		return matrizIndicadoresService.llenarListaComponente(clvdepg, cveprog, cvetemas);
	}

	/**
	 * Llenar lista actividad 2.
	 *
	 * @param rowMaster the row master
	 * @param rowComp   the row comp
	 */
	public void llenarListaActividad2(Integer rowMaster, Integer rowComp) {
		MatrizIndicadoresDTO mat = masterList.get(rowMaster);
		Componente comp = mat.getListaComponente().get(rowComp);
		setComponenteActual(comp.getCvecom());
		List<Actividad> acts = matrizIndicadoresService.llenarListaActividad(comp);
		if (acts.isEmpty()) {
			acts.add(new Actividad());
			mat.setListaActividad(acts);
		} else {
			mat.setListaActividad(acts);
		}
		estadoInicialBotonesActividad(rowMaster);
	}

	/**
	 * Borrar indicador.
	 *
	 * @param rowMaster the row master
	 * @param rowInd    the row ind
	 * @param tipoInd   the tipo ind
	 */
	public void borrarIndicador(Integer rowMaster, Integer rowInd, Integer tipoInd) {
		LOGGER.debug("row Master " + rowMaster + " row Indicador " + rowInd + " tipo " + tipoInd);
		MatrizIndicadoresDTO mat = masterList.get(rowMaster);
		switch (tipoInd) {
		case 1:
			matrizIndicadoresService.deleteIndicador(mat.getListaFinalidad().get(rowInd).getId(), tipoInd);
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Finaldidad borrada correctamente.",
					"Finaldidad borrada correctamente.");
			estadoInicialBotonesFinalidad(currentPage);
			if (masterList.get(rowMaster).getListaFinalidad().isEmpty()) {
				estadoInicialBotonesFinalidad(currentPage);

			}
			break;
		case 2:
			matrizIndicadoresService.deleteIndicador(mat.getListaProposito().get(rowInd).getId(), tipoInd);
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Propósito borrado correctamente.",
					"Propósito borrado correctamente.");
			estadoInicialBotonesProposito(currentPage);
			if (masterList.get(rowMaster).getListaProposito().isEmpty()) {
				estadoInicialBotonesProposito(currentPage);
				this.panelToggle(rowInd);
			}
			break;
		case 3:
			Componente comp = mat.getListaComponente().get(rowInd);
			if (matrizIndicadoresService.llenarListaActividad(comp).isEmpty()) {
				matrizIndicadoresService.deleteIndicador(comp.getId(), tipoInd);
				estadoInicialBotonesComponente(currentPage);
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Componente borrado correctamente.",
						"Componente borrado correctamente.");
				if (masterList.get(rowMaster).getListaComponente().isEmpty()) {
					estadoInicialBotonesComponente(currentPage);

				}
			} else {
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
						"Existen registros de Actividad. No se puede eliminar",
						"Existen registros de Actividad. No se puede eliminar");

			}
			break;
		case 4:
			matrizIndicadoresService.deleteIndicador(mat.getListaActividad().get(rowInd).getId(), tipoInd);
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Actividad borrada correctamente.",
					"Actividad borrada correctamente.");
			if (masterList.get(rowMaster).getListaActividad().isEmpty()) {
				estadoInicialBotonesActividad(currentPageDet);
			}
			break;
		default:
			break;
		}

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
	 * Gets the matriz indicadores service.
	 *
	 * @return the matriz indicadores service
	 */
	public MatrizIndicadoresService getMatrizIndicadoresService() {
		return matrizIndicadoresService;
	}

	/**
	 * Sets the matriz indicadores service.
	 *
	 * @param matrizIndicadoresService the new matriz indicadores service
	 */
	public void setMatrizIndicadoresService(MatrizIndicadoresService matrizIndicadoresService) {
		this.matrizIndicadoresService = matrizIndicadoresService;
	}

	/**
	 * Gets the master list.
	 *
	 * @return the master list
	 */
	public List<MatrizIndicadoresDTO> getMasterList() {
		return masterList;
	}

	/**
	 * Sets the master list.
	 *
	 * @param masterList the new master list
	 */
	public void setMasterList(List<MatrizIndicadoresDTO> masterList) {
		this.masterList = masterList;
	}

	/**
	 * Gets the clv tema list.
	 *
	 * @return the clv tema list
	 */
	public List<String> getClvTemaList() {
		return clvTemaList;
	}

	/**
	 * Sets the clv tema list.
	 *
	 * @param clvTemaList the new clv tema list
	 */
	public void setClvTemaList(List<String> clvTemaList) {
		this.clvTemaList = clvTemaList;
	}

	/**
	 * Checks if is modificar encabezado.
	 *
	 * @return true, if is modificar encabezado
	 */
	public boolean isModificarEncabezado() {
		return modificarEncabezado;
	}

	/**
	 * Sets the modificar encabezado.
	 *
	 * @param modificarEncabezado the new modificar encabezado
	 */
	public void setModificarEncabezado(boolean modificarEncabezado) {
		this.modificarEncabezado = modificarEncabezado;
	}

	/**
	 * Gets the lista codigo indicador finalidad.
	 *
	 * @return the lista codigo indicador finalidad
	 */
	public List<Mir> getListaCodigoIndicadorFinalidad() {
		return listaCodigoIndicadorFinalidad;
	}

	/**
	 * Sets the lista codigo indicador finalidad.
	 *
	 * @param listaCodigoIndicadorFinalidad the new lista codigo indicador finalidad
	 */
	public void setListaCodigoIndicadorFinalidad(List<Mir> listaCodigoIndicadorFinalidad) {
		this.listaCodigoIndicadorFinalidad = listaCodigoIndicadorFinalidad;
	}

	/**
	 * Gets the lista codigo indicador proposito.
	 *
	 * @return the lista codigo indicador proposito
	 */
	public List<Mir> getListaCodigoIndicadorProposito() {
		return listaCodigoIndicadorProposito;
	}

	/**
	 * Sets the lista codigo indicador proposito.
	 *
	 * @param listaCodigoIndicadorProposito the new lista codigo indicador proposito
	 */
	public void setListaCodigoIndicadorProposito(List<Mir> listaCodigoIndicadorProposito) {
		this.listaCodigoIndicadorProposito = listaCodigoIndicadorProposito;
	}

	/**
	 * Gets the lista codigo indicador componente.
	 *
	 * @return the lista codigo indicador componente
	 */
	public List<Mir> getListaCodigoIndicadorComponente() {
		return listaCodigoIndicadorComponente;
	}

	/**
	 * Sets the lista codigo indicador componente.
	 *
	 * @param listaCodigoIndicadorComponente the new lista codigo indicador
	 *                                       componente
	 */
	public void setListaCodigoIndicadorComponente(List<Mir> listaCodigoIndicadorComponente) {
		this.listaCodigoIndicadorComponente = listaCodigoIndicadorComponente;
	}

	/**
	 * Gets the lista codigo indicador actividad.
	 *
	 * @return the lista codigo indicador actividad
	 */
	public List<Mir> getListaCodigoIndicadorActividad() {
		return listaCodigoIndicadorActividad;
	}

	/**
	 * Sets the lista codigo indicador actividad.
	 *
	 * @param listaCodigoIndicadorActividad the new lista codigo indicador actividad
	 */
	public void setListaCodigoIndicadorActividad(List<Mir> listaCodigoIndicadorActividad) {
		this.listaCodigoIndicadorActividad = listaCodigoIndicadorActividad;
	}

	/**
	 * Gets the encabezado en edicion.
	 *
	 * @return the encabezado en edicion
	 */
	public MatrizIndicadoresDTO getEncabezadoEnEdicion() {
		return encabezadoEnEdicion;
	}

	/**
	 * Sets the encabezado en edicion.
	 *
	 * @param encabezadoEnEdicion the new encabezado en edicion
	 */
	public void setEncabezadoEnEdicion(MatrizIndicadoresDTO encabezadoEnEdicion) {
		this.encabezadoEnEdicion = encabezadoEnEdicion;
	}

	/**
	 * Checks if is finalidad visible modificar.
	 *
	 * @return true, if is finalidad visible modificar
	 */
	public boolean isFinalidadVisibleModificar() {
		return finalidadVisibleModificar;
	}

	/**
	 * Sets the finalidad visible modificar.
	 *
	 * @param finalidadVisibleModificar the new finalidad visible modificar
	 */
	public void setFinalidadVisibleModificar(boolean finalidadVisibleModificar) {
		this.finalidadVisibleModificar = finalidadVisibleModificar;
	}

	/**
	 * Checks if is finalidad visible salvar.
	 *
	 * @return true, if is finalidad visible salvar
	 */
	public boolean isFinalidadVisibleSalvar() {
		return finalidadVisibleSalvar;
	}

	/**
	 * Sets the finalidad visible salvar.
	 *
	 * @param finalidadVisibleSalvar the new finalidad visible salvar
	 */
	public void setFinalidadVisibleSalvar(boolean finalidadVisibleSalvar) {
		this.finalidadVisibleSalvar = finalidadVisibleSalvar;
	}

	/**
	 * Checks if is finalidad disabled editables.
	 *
	 * @return true, if is finalidad disabled editables
	 */
	public boolean isFinalidadDisabledEditables() {
		return finalidadDisabledEditables;
	}

	/**
	 * Sets the finalidad disabled editables.
	 *
	 * @param finalidadDisabledEditables the new finalidad disabled editables
	 */
	public void setFinalidadDisabledEditables(boolean finalidadDisabledEditables) {
		this.finalidadDisabledEditables = finalidadDisabledEditables;
	}

	/**
	 * Checks if is finalidad disabled adicionar.
	 *
	 * @return true, if is finalidad disabled adicionar
	 */
	public boolean isFinalidadDisabledAdicionar() {
		return finalidadDisabledAdicionar;
	}

	/**
	 * Sets the finalidad disabled adicionar.
	 *
	 * @param finalidadDisabledAdicionar the new finalidad disabled adicionar
	 */
	public void setFinalidadDisabledAdicionar(boolean finalidadDisabledAdicionar) {
		this.finalidadDisabledAdicionar = finalidadDisabledAdicionar;
	}

	/**
	 * Checks if is finalidad disabled borrar.
	 *
	 * @return true, if is finalidad disabled borrar
	 */
	public boolean isFinalidadDisabledBorrar() {
		return finalidadDisabledBorrar;
	}

	/**
	 * Sets the finalidad disabled borrar.
	 *
	 * @param finalidadDisabledBorrar the new finalidad disabled borrar
	 */
	public void setFinalidadDisabledBorrar(boolean finalidadDisabledBorrar) {
		this.finalidadDisabledBorrar = finalidadDisabledBorrar;
	}

	/**
	 * Checks if is finalidad disabled reset.
	 *
	 * @return true, if is finalidad disabled reset
	 */
	public boolean isFinalidadDisabledReset() {
		return finalidadDisabledReset;
	}

	/**
	 * Sets the finalidad disabled reset.
	 *
	 * @param finalidadDisabledReset the new finalidad disabled reset
	 */
	public void setFinalidadDisabledReset(boolean finalidadDisabledReset) {
		this.finalidadDisabledReset = finalidadDisabledReset;
	}

	/**
	 * Checks if is finalidad disabled cancelar.
	 *
	 * @return true, if is finalidad disabled cancelar
	 */
	public boolean isFinalidadDisabledCancelar() {
		return finalidadDisabledCancelar;
	}

	/**
	 * Sets the finalidad disabled cancelar.
	 *
	 * @param finalidadDisabledCancelar the new finalidad disabled cancelar
	 */
	public void setFinalidadDisabledCancelar(boolean finalidadDisabledCancelar) {
		this.finalidadDisabledCancelar = finalidadDisabledCancelar;
	}

	/**
	 * Checks if is proposito visible modificar.
	 *
	 * @return true, if is proposito visible modificar
	 */
	public boolean isPropositoVisibleModificar() {
		return propositoVisibleModificar;
	}

	/**
	 * Sets the proposito visible modificar.
	 *
	 * @param propositoVisibleModificar the new proposito visible modificar
	 */
	public void setPropositoVisibleModificar(boolean propositoVisibleModificar) {
		this.propositoVisibleModificar = propositoVisibleModificar;
	}

	/**
	 * Checks if is proposito disabled adicionar.
	 *
	 * @return true, if is proposito disabled adicionar
	 */
	public boolean isPropositoDisabledAdicionar() {
		return propositoDisabledAdicionar;
	}

	/**
	 * Sets the proposito disabled adicionar.
	 *
	 * @param propositoDisabledAdicionar the new proposito disabled adicionar
	 */
	public void setPropositoDisabledAdicionar(boolean propositoDisabledAdicionar) {
		this.propositoDisabledAdicionar = propositoDisabledAdicionar;
	}

	/**
	 * Checks if is proposito disabled borrar.
	 *
	 * @return true, if is proposito disabled borrar
	 */
	public boolean isPropositoDisabledBorrar() {
		return propositoDisabledBorrar;
	}

	/**
	 * Sets the proposito disabled borrar.
	 *
	 * @param propositoDisabledBorrar the new proposito disabled borrar
	 */
	public void setPropositoDisabledBorrar(boolean propositoDisabledBorrar) {
		this.propositoDisabledBorrar = propositoDisabledBorrar;
	}

	/**
	 * Checks if is proposito disabled reset.
	 *
	 * @return true, if is proposito disabled reset
	 */
	public boolean isPropositoDisabledReset() {
		return propositoDisabledReset;
	}

	/**
	 * Sets the proposito disabled reset.
	 *
	 * @param propositoDisabledReset the new proposito disabled reset
	 */
	public void setPropositoDisabledReset(boolean propositoDisabledReset) {
		this.propositoDisabledReset = propositoDisabledReset;
	}

	/**
	 * Checks if is proposito disabled cancelar.
	 *
	 * @return true, if is proposito disabled cancelar
	 */
	public boolean isPropositoDisabledCancelar() {
		return propositoDisabledCancelar;
	}

	/**
	 * Sets the proposito disabled cancelar.
	 *
	 * @param propositoDisabledCancelar the new proposito disabled cancelar
	 */
	public void setPropositoDisabledCancelar(boolean propositoDisabledCancelar) {
		this.propositoDisabledCancelar = propositoDisabledCancelar;
	}

	/**
	 * Checks if is proposito visible salvar.
	 *
	 * @return true, if is proposito visible salvar
	 */
	public boolean isPropositoVisibleSalvar() {
		return propositoVisibleSalvar;
	}

	/**
	 * Sets the proposito visible salvar.
	 *
	 * @param propositoVisibleSalvar the new proposito visible salvar
	 */
	public void setPropositoVisibleSalvar(boolean propositoVisibleSalvar) {
		this.propositoVisibleSalvar = propositoVisibleSalvar;
	}

	/**
	 * Checks if is proposito disabled editables.
	 *
	 * @return true, if is proposito disabled editables
	 */
	public boolean isPropositoDisabledEditables() {
		return propositoDisabledEditables;
	}

	/**
	 * Sets the proposito disabled editables.
	 *
	 * @param propositoDisabledEditables the new proposito disabled editables
	 */
	public void setPropositoDisabledEditables(boolean propositoDisabledEditables) {
		this.propositoDisabledEditables = propositoDisabledEditables;
	}

	/**
	 * Checks if is componente visible modificar.
	 *
	 * @return true, if is componente visible modificar
	 */
	public boolean isComponenteVisibleModificar() {
		return componenteVisibleModificar;
	}

	/**
	 * Sets the componente visible modificar.
	 *
	 * @param componenteVisibleModificar the new componente visible modificar
	 */
	public void setComponenteVisibleModificar(boolean componenteVisibleModificar) {
		this.componenteVisibleModificar = componenteVisibleModificar;
	}

	/**
	 * Checks if is componente disabled adicionar.
	 *
	 * @return true, if is componente disabled adicionar
	 */
	public boolean isComponenteDisabledAdicionar() {
		return componenteDisabledAdicionar;
	}

	/**
	 * Sets the componente disabled adicionar.
	 *
	 * @param componenteDisabledAdicionar the new componente disabled adicionar
	 */
	public void setComponenteDisabledAdicionar(boolean componenteDisabledAdicionar) {
		this.componenteDisabledAdicionar = componenteDisabledAdicionar;
	}

	/**
	 * Checks if is componente disabled borrar.
	 *
	 * @return true, if is componente disabled borrar
	 */
	public boolean isComponenteDisabledBorrar() {
		return componenteDisabledBorrar;
	}

	/**
	 * Sets the componente disabled borrar.
	 *
	 * @param componenteDisabledBorrar the new componente disabled borrar
	 */
	public void setComponenteDisabledBorrar(boolean componenteDisabledBorrar) {
		this.componenteDisabledBorrar = componenteDisabledBorrar;
	}

	/**
	 * Checks if is componente disabled reset.
	 *
	 * @return true, if is componente disabled reset
	 */
	public boolean isComponenteDisabledReset() {
		return componenteDisabledReset;
	}

	/**
	 * Sets the componente disabled reset.
	 *
	 * @param componenteDisabledReset the new componente disabled reset
	 */
	public void setComponenteDisabledReset(boolean componenteDisabledReset) {
		this.componenteDisabledReset = componenteDisabledReset;
	}

	/**
	 * Checks if is componente disabled cancelar.
	 *
	 * @return true, if is componente disabled cancelar
	 */
	public boolean isComponenteDisabledCancelar() {
		return componenteDisabledCancelar;
	}

	/**
	 * Sets the componente disabled cancelar.
	 *
	 * @param componenteDisabledCancelar the new componente disabled cancelar
	 */
	public void setComponenteDisabledCancelar(boolean componenteDisabledCancelar) {
		this.componenteDisabledCancelar = componenteDisabledCancelar;
	}

	/**
	 * Checks if is componente visible salvar.
	 *
	 * @return true, if is componente visible salvar
	 */
	public boolean isComponenteVisibleSalvar() {
		return componenteVisibleSalvar;
	}

	/**
	 * Sets the componente visible salvar.
	 *
	 * @param componenteVisibleSalvar the new componente visible salvar
	 */
	public void setComponenteVisibleSalvar(boolean componenteVisibleSalvar) {
		this.componenteVisibleSalvar = componenteVisibleSalvar;
	}

	/**
	 * Checks if is componente disabled editables.
	 *
	 * @return true, if is componente disabled editables
	 */
	public boolean isComponenteDisabledEditables() {
		return componenteDisabledEditables;
	}

	/**
	 * Sets the componente disabled editables.
	 *
	 * @param componenteDisabledEditables the new componente disabled editables
	 */
	public void setComponenteDisabledEditables(boolean componenteDisabledEditables) {
		this.componenteDisabledEditables = componenteDisabledEditables;
	}

	/**
	 * Checks if is actividad visible modificar.
	 *
	 * @return true, if is actividad visible modificar
	 */
	public boolean isActividadVisibleModificar() {
		return actividadVisibleModificar;
	}

	/**
	 * Sets the actividad visible modificar.
	 *
	 * @param actividadVisibleModificar the new actividad visible modificar
	 */
	public void setActividadVisibleModificar(boolean actividadVisibleModificar) {
		this.actividadVisibleModificar = actividadVisibleModificar;
	}

	/**
	 * Checks if is actividad disabled adicionar.
	 *
	 * @return true, if is actividad disabled adicionar
	 */
	public boolean isActividadDisabledAdicionar() {
		return actividadDisabledAdicionar;
	}

	/**
	 * Sets the actividad disabled adicionar.
	 *
	 * @param actividadDisabledAdicionar the new actividad disabled adicionar
	 */
	public void setActividadDisabledAdicionar(boolean actividadDisabledAdicionar) {
		this.actividadDisabledAdicionar = actividadDisabledAdicionar;
	}

	/**
	 * Checks if is actividad disabled borrar.
	 *
	 * @return true, if is actividad disabled borrar
	 */
	public boolean isActividadDisabledBorrar() {
		return actividadDisabledBorrar;
	}

	/**
	 * Sets the actividad disabled borrar.
	 *
	 * @param actividadDisabledBorrar the new actividad disabled borrar
	 */
	public void setActividadDisabledBorrar(boolean actividadDisabledBorrar) {
		this.actividadDisabledBorrar = actividadDisabledBorrar;
	}

	/**
	 * Checks if is actividad disabled reset.
	 *
	 * @return true, if is actividad disabled reset
	 */
	public boolean isActividadDisabledReset() {
		return actividadDisabledReset;
	}

	/**
	 * Sets the actividad disabled reset.
	 *
	 * @param actividadDisabledReset the new actividad disabled reset
	 */
	public void setActividadDisabledReset(boolean actividadDisabledReset) {
		this.actividadDisabledReset = actividadDisabledReset;
	}

	/**
	 * Checks if is actividad disabled cancelar.
	 *
	 * @return true, if is actividad disabled cancelar
	 */
	public boolean isActividadDisabledCancelar() {
		return actividadDisabledCancelar;
	}

	/**
	 * Sets the actividad disabled cancelar.
	 *
	 * @param actividadDisabledCancelar the new actividad disabled cancelar
	 */
	public void setActividadDisabledCancelar(boolean actividadDisabledCancelar) {
		this.actividadDisabledCancelar = actividadDisabledCancelar;
	}

	/**
	 * Checks if is actividad visible salvar.
	 *
	 * @return true, if is actividad visible salvar
	 */
	public boolean isActividadVisibleSalvar() {
		return actividadVisibleSalvar;
	}

	/**
	 * Sets the actividad visible salvar.
	 *
	 * @param actividadVisibleSalvar the new actividad visible salvar
	 */
	public void setActividadVisibleSalvar(boolean actividadVisibleSalvar) {
		this.actividadVisibleSalvar = actividadVisibleSalvar;
	}

	/**
	 * Checks if is actividad disabled editables.
	 *
	 * @return true, if is actividad disabled editables
	 */
	public boolean isActividadDisabledEditables() {
		return actividadDisabledEditables;
	}

	/**
	 * Sets the actividad disabled editables.
	 *
	 * @param actividadDisabledEditables the new actividad disabled editables
	 */
	public void setActividadDisabledEditables(boolean actividadDisabledEditables) {
		this.actividadDisabledEditables = actividadDisabledEditables;
	}

	/**
	 * Gets the finalidad en edicion.
	 *
	 * @return the finalidad en edicion
	 */
	public Finalidad getFinalidadEnEdicion() {
		return finalidadEnEdicion;
	}

	/**
	 * Sets the finalidad en edicion.
	 *
	 * @param finalidadEnEdicion the new finalidad en edicion
	 */
	public void setFinalidadEnEdicion(Finalidad finalidadEnEdicion) {
		this.finalidadEnEdicion = finalidadEnEdicion;
	}

	/**
	 * Gets the proposito en edicion.
	 *
	 * @return the proposito en edicion
	 */
	public Proposito getPropositoEnEdicion() {
		return propositoEnEdicion;
	}

	/**
	 * Sets the proposito en edicion.
	 *
	 * @param propositoEnEdicion the new proposito en edicion
	 */
	public void setPropositoEnEdicion(Proposito propositoEnEdicion) {
		this.propositoEnEdicion = propositoEnEdicion;
	}

	/**
	 * Gets the componente en edicion.
	 *
	 * @return the componente en edicion
	 */
	public Componente getComponenteEnEdicion() {
		return componenteEnEdicion;
	}

	/**
	 * Sets the componente en edicion.
	 *
	 * @param componenteEnEdicion the new componente en edicion
	 */
	public void setComponenteEnEdicion(Componente componenteEnEdicion) {
		this.componenteEnEdicion = componenteEnEdicion;
	}

	/**
	 * Gets the actividad en edicion.
	 *
	 * @return the actividad en edicion
	 */
	public Actividad getActividadEnEdicion() {
		return actividadEnEdicion;
	}

	/**
	 * Sets the actividad en edicion.
	 *
	 * @param actividadEnEdicion the new actividad en edicion
	 */
	public void setActividadEnEdicion(Actividad actividadEnEdicion) {
		this.actividadEnEdicion = actividadEnEdicion;
	}

	/**
	 * Checks if is captura deshabilitado objetivo.
	 *
	 * @return true, if is captura deshabilitado objetivo
	 */
	public boolean isCapturaDeshabilitadoObjetivo() {
		return capturaDeshabilitadoObjetivo;
	}

	/**
	 * Sets the captura deshabilitado objetivo.
	 *
	 * @param capturaDeshabilitadoObjetivo the new captura deshabilitado objetivo
	 */
	public void setCapturaDeshabilitadoObjetivo(boolean capturaDeshabilitadoObjetivo) {
		this.capturaDeshabilitadoObjetivo = capturaDeshabilitadoObjetivo;
	}

	/**
	 * Gets the componente actual.
	 *
	 * @return the componente actual
	 */
	public int getComponenteActual() {
		return componenteActual;
	}

	/**
	 * Sets the componente actual.
	 *
	 * @param componenteActual the new componente actual
	 */
	public void setComponenteActual(int componenteActual) {
		this.componenteActual = componenteActual;
	}

	/**
	 * Gets the current page.
	 *
	 * @return the current page
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * Sets the current page.
	 *
	 * @param currentPage the new current page
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * Gets the bandera modificar.
	 *
	 * @return the bandera modificar
	 */
	public Boolean getBanderaModificar() {
		return banderaModificar;
	}

	/**
	 * Sets the bandera modificar.
	 *
	 * @param banderaModificar the new bandera modificar
	 */
	public void setBanderaModificar(Boolean banderaModificar) {
		this.banderaModificar = banderaModificar;
	}

	/** The mat res. */
	MatrizIndicadoresDTO matRes = null;

	/**
	 * Reset DTO.
	 *
	 * @param Index the index
	 * @return the matriz indicadores DTO
	 */
	public MatrizIndicadoresDTO resetDTO(Integer Index) {
		matRes = new MatrizIndicadoresDTO();
		if (masterList.get(currentPage).getId() != 0) {
			matRes = this.matrizIndicadoresService.resetCaptura(masterList.get(currentPage));
			matRes.setClvdepgDesc(matrizIndicadoresService.getDescripcionDep(matRes.getClvdepg()));
			matRes.setCveProgDesc(matrizIndicadoresService.getDescripcionProg(matRes.getCveprog()));
			matRes.setCveTemaDesc(matrizIndicadoresService.getDescripcionTema(matRes.getCvetemas()));
			List<Finalidad> fin = matrizIndicadoresService.finByProgAndTemasAndFinal(matRes.getClvdepg(),
					matRes.getCveprog(), matRes.getCvetemas());

			if (CollectionUtils.isEmpty(matRes.getListaFinalidad())) {

				matRes.getListaFinalidad().add(new Finalidad());
			}
			if (CollectionUtils.isNotEmpty(fin)) {
				matRes.getListaFinalidad().get(currentPageDet).setCveind(fin.get(currentPageDet).getCveind());
				matRes.getListaFinalidad().get(currentPageDet).setObjetivo(fin.get(currentPageDet).getObjetivo());
				matRes.getListaFinalidad().get(currentPageDet).setNombre(fin.get(currentPageDet).getNombre());
				matRes.getListaFinalidad().get(currentPageDet).setFormula(fin.get(currentPageDet).getFormula());
				matRes.getListaFinalidad().get(currentPageDet).setFrecuencia(fin.get(currentPageDet).getFrecuencia());
				matRes.getListaFinalidad().get(currentPageDet).setMedio(fin.get(currentPageDet).getMedio());
				matRes.getListaFinalidad().get(currentPageDet).setSupuesto(fin.get(currentPageDet).getSupuesto());

			}
			List<Proposito> prop = matrizIndicadoresService.findProposito(matRes.getClvdepg(), matRes.getCveprog(),
					matRes.getCvetemas());
			if (CollectionUtils.isEmpty(matRes.getListaProposito())) {

				matRes.getListaProposito().add(new Proposito());
			}
			if (CollectionUtils.isNotEmpty(prop)) {
				matRes.getListaProposito().get(currentPageDet).setCveind(prop.get(currentPageDet).getCveind());
				matRes.getListaProposito().get(currentPageDet).setObjetivo(prop.get(currentPageDet).getObjetivo());
				matRes.getListaProposito().get(currentPageDet).setNombre(prop.get(currentPageDet).getNombre());
				matRes.getListaProposito().get(currentPageDet).setFormula(prop.get(currentPageDet).getFormula());
				matRes.getListaProposito().get(currentPageDet).setFrecuencia(prop.get(currentPageDet).getFrecuencia());
				matRes.getListaProposito().get(currentPageDet).setMedio(prop.get(currentPageDet).getMedio());
				matRes.getListaProposito().get(currentPageDet).setSupuesto(prop.get(currentPageDet).getSupuesto());
			}
			List<Componente> comp = matrizIndicadoresService.findComponente(matRes.getClvdepg(), matRes.getCveprog(),
					matRes.getCvetemas());
			if (CollectionUtils.isEmpty(matRes.getListaComponente())) {

				matRes.getListaComponente().add(new Componente());
			}
			if (CollectionUtils.isNotEmpty(comp)) {
				matRes.getListaComponente().get(currentPageDet).setCveind(comp.get(currentPageDet).getCveind());
				matRes.getListaComponente().get(currentPageDet).setObjetivo(comp.get(currentPageDet).getObjetivo());
				matRes.getListaComponente().get(currentPageDet).setNombre(comp.get(currentPageDet).getNombre());
				matRes.getListaComponente().get(currentPageDet).setFormula(comp.get(currentPageDet).getFormula());
				matRes.getListaComponente().get(currentPageDet).setFrecuencia(comp.get(currentPageDet).getFrecuencia());
				matRes.getListaComponente().get(currentPageDet).setMedio(comp.get(currentPageDet).getMedio());
				matRes.getListaComponente().get(currentPageDet).setSupuesto(comp.get(currentPageDet).getSupuesto());
			}
			List<Actividad> act = matrizIndicadoresService.findActividad(matRes.getClvdepg(), matRes.getCveprog(),
					matRes.getCvetemas());
			if (CollectionUtils.isEmpty(matRes.getListaActividad())) {

				matRes.getListaActividad().add(new Actividad());
			}
			if (CollectionUtils.isNotEmpty(act)) {
				matRes.getListaActividad().get(currentPageDet).setCveind(act.get(currentPageDet).getCveind());
				matRes.getListaActividad().get(currentPageDet).setObjetivo(act.get(currentPageDet).getObjetivo());
				matRes.getListaActividad().get(currentPageDet).setNombre(act.get(currentPageDet).getNombre());
				matRes.getListaActividad().get(currentPageDet).setFormula(act.get(currentPageDet).getFormula());
				matRes.getListaActividad().get(currentPageDet).setFrecuencia(act.get(currentPageDet).getFrecuencia());
				matRes.getListaActividad().get(currentPageDet).setMedio(act.get(currentPageDet).getMedio());
				matRes.getListaActividad().get(currentPageDet).setSupuesto(act.get(currentPageDet).getSupuesto());
			}
			matRes.getProgramasMap().put(matRes.getCveprog(), matRes.getCveProgDesc());
		}
		return matRes;
	}

	/**
	 * Reset DTO on change.
	 *
	 * @param Index the index
	 * @return the matriz indicadores DTO
	 */
	public MatrizIndicadoresDTO resetDTOOnChange(Integer Index) {
		matRes = new MatrizIndicadoresDTO();
		matRes = this.matrizIndicadoresService.resetCaptura(masterList.get(Index));
		matRes.setClvdepgDesc(matrizIndicadoresService.getDescripcionDep(matRes.getClvdepg()));
		matRes.setCveProgDesc(matrizIndicadoresService.getDescripcionProg(matRes.getCveprog()));
		matRes.setCveTemaDesc(matrizIndicadoresService.getDescripcionTema(matRes.getCvetemas()));
		List<Finalidad> fin = matrizIndicadoresService.finByProgAndTemasAndFinal(matRes.getClvdepg(),
				matRes.getCveprog(), matRes.getCvetemas());

		if (CollectionUtils.isNotEmpty(matRes.getListaFinalidad())) {
			matRes.getListaFinalidad().get(currentPageDet).setCveind(fin.get(currentPageDet).getCveind());

		}

		List<Proposito> prop = matrizIndicadoresService.findProposito(matRes.getClvdepg(), matRes.getCveprog(),
				matRes.getCvetemas());
		if (CollectionUtils.isNotEmpty(matRes.getListaProposito())) {
			matRes.getListaProposito().get(currentPageDet).setCveind(prop.get(currentPageDet).getCveind());

		}

		List<Componente> comp = matrizIndicadoresService.findComponente(matRes.getClvdepg(), matRes.getCveprog(),
				matRes.getCvetemas());
		if (CollectionUtils.isNotEmpty(matRes.getListaComponente())) {
			matRes.getListaComponente().get(currentPageDet).setCveind(comp.get(currentPageDet).getCveind());

		}

		List<Actividad> act = matrizIndicadoresService.findActividad(matRes.getClvdepg(), matRes.getCveprog(),
				matRes.getCvetemas());
		if (CollectionUtils.isNotEmpty(matRes.getListaActividad())) {

			matRes.getListaActividad().get(currentPageDet).setCveind(act.get(currentPageDet).getCveind());
		}

		matRes.getProgramasMap().put(matRes.getCveprog(), matRes.getCveProgDesc());
		return matRes;
	}

	/**
	 * Panel toggle.
	 *
	 * @param index the index
	 */
	public void panelToggle(Integer index) {
		StringBuilder executeToggle = new StringBuilder();
		executeToggle.append(CLOSE_PANEL_JQUERY);
		executeToggle.append(index);
		executeToggle.append(CLOSE_COMPLETE_JQUERY);
		RequestContext.getCurrentInstance().execute(executeToggle.toString());
	}

	/**
	 * Panel toggleo pen.
	 *
	 * @param index the index
	 */
	public void panelToggleoPen(Integer index) {
		StringBuilder executeToggle = new StringBuilder();
		executeToggle.append(OPEN_PANEL_JQUERY);
		executeToggle.append(index);
		executeToggle.append(OPEN_COMPLETE_JQUERY);
		RequestContext.getCurrentInstance().execute(executeToggle.toString());
	}

	public FinalidadRepository getFinalidadRepository() {
		return finalidadRepository;
	}

	public void setFinalidadRepository(FinalidadRepository finalidadRepository) {
		this.finalidadRepository = finalidadRepository;
	}

	public PropositoRepository getPropositoRepository() {
		return propositoRepository;
	}

	public void setPropositoRepository(PropositoRepository propositoRepository) {
		this.propositoRepository = propositoRepository;
	}

	public ComponenteRepository getComponenteRepository() {
		return componenteRepository;
	}

	public void setComponenteRepository(ComponenteRepository componenteRepository) {
		this.componenteRepository = componenteRepository;
	}

	public ActividadRepository getActividadRepository() {
		return actividadRepository;
	}

	public void setActividadRepository(ActividadRepository actividadRepository) {
		this.actividadRepository = actividadRepository;
	}

}
