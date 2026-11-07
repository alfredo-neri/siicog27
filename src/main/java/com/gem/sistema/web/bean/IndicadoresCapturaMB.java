package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.data.PageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.dto.IndCapturaDTO;
import com.gem.sistema.business.dto.IndMetaDTO;
import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.business.service.indicadores.IndicadoresCapturaService;
import com.gem.sistema.util.UtilFront;

// TODO: Auto-generated Javadoc
/**
 * The Class IndicadoresCapturaMB.
 */
@ManagedBean(name = "indicadoresCapturaMB")
@ViewScoped
public class IndicadoresCapturaMB extends AbstractMB {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(IndicadoresCapturaMB.class);
	
	/** The master list. */
	private List<IndCapturaDTO> masterList = new ArrayList<IndCapturaDTO>();
	
	/** The master list original. */
	private List<IndCapturaDTO> masterListOriginal = new ArrayList<IndCapturaDTO>();
	
	/** The clv dependencias list. */
	private List<String> clvDependenciasList = new ArrayList<String>();
	
	/** The clv programa list. */
	private List<String> clvProgramaList = new ArrayList<String>();
	
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
	
	/** The b btn modificar. */
	private Boolean bBtnModificar;
	
	/** The meta visible modificar. */
	private boolean metaVisibleModificar;
	
	/** The dependencia sel. */
	private IndCapturaDTO dependenciaSel;
	
	/** The prog sel. */
	private IndCapturaDTO progSel;
	
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
	
	/** The current row meta. */
	private int currentRowMeta = 0;
	
	/** The b adicionar. */
	private Boolean bAdicionar;
	
	/** The b modificar. */
	private Boolean bModificar;
	
	/** The b modificar de. */
	private Boolean bModificarDe;
	
	/** The current page. */
	Integer currentPage = 0;

	/** The indicadores captura service. */
	@ManagedProperty("#{indicadoresCapturaService}")
	private IndicadoresCapturaService indicadoresCapturaService;
	
	@ManagedProperty("#{muniNepRepository}")
	private MuniNepRepository muniNepRepository;
	
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
	 * Gets the b modificar.
	 *
	 * @return the b modificar
	 */
	public Boolean getbModificar() {
		return bModificar;
	}

	/**
	 * Sets the b modificar.
	 *
	 * @param bModificar the new b modificar
	 */
	public void setbModificar(Boolean bModificar) {
		this.bModificar = bModificar;
	}

	/**
	 * Gets the b modificar de.
	 *
	 * @return the b modificar de
	 */
	public Boolean getbModificarDe() {
		return bModificarDe;
	}

	/**
	 * Sets the b modificar de.
	 *
	 * @param bModificarDe the new b modificar de
	 */
	public void setbModificarDe(Boolean bModificarDe) {
		this.bModificarDe = bModificarDe;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct IndicadoresCapturaMB ");
		this.setbBtnModificar(true);
		this.setbAdicionar(false);
		this.setbModificar(false);
		this.setbModificarDe(false);
	}

	/**
	 * metodo que carga la pantalla inicial desde la llamada del menu.
	 */
	public void findAllIndicadores() {
		this.limpiarListas();
		this.llenarCabecera();
		this.llenarListaDependencias();
		this.estadoInicialBotones();
		this.estadeTagsDetails(3);
		if (CollectionUtils.isEmpty(this.getMasterList().get(0).getMetaList())) {
			this.estadeTagsDetails(4);
		}
		if (null == this.getMasterList().get(0).getId()) {
			this.estadeTagsDetails(1);
		}
	}

	/**
	 * Limpiar listas.
	 */
	public void limpiarListas() {
		masterList.clear();
		masterListOriginal.clear();
		clvDependenciasList.clear();
		clvProgramaList.clear();
	}

	/**
	 * Llenar cabecera.
	 */
	public void llenarCabecera() {
		masterListOriginal = indicadoresCapturaService.llenarCabecera();
		masterList = indicadoresCapturaService.llenarCabecera();
		for (IndCapturaDTO m : masterList) {
			if (indicadoresCapturaService.findAllByClvdep(m.getClvDep(), m.getClvnep()).isEmpty()) {
				List<IndMetaDTO> listaMet = new ArrayList<IndMetaDTO>();
				// listaMet.add(new IndMetaDTO());
				m.setMetaList(listaMet);
				this.estadeTagsDetails(1);
			} else {
				m.setMetaList(indicadoresCapturaService.findAllByClvdep(m.getClvDep(), m.getClvnep()));
				this.estadeTagsDetails(3);
			}
		}
	}

	/**
	 * Gets the row meta.
	 *
	 * @param event the event
	 * @return the row meta
	 */
	public void getRowMeta(PageEvent event) {
		setCurrentRowMeta(event.getPage());
	}

	/**
	 * Llenar lista dependencias.
	 */
	public void llenarListaDependencias() {
		for (Xcatpro xcat : indicadoresCapturaService.listaDependencias(this.getUserDetails().getIdSector())) {
			if (!clvDependenciasList.contains(xcat.getClvdep())) {
				clvDependenciasList.add(xcat.getClvdep());
			}
			if (!clvProgramaList.contains(xcat.getClvfun())) {
				clvProgramaList.add(xcat.getClvfun());
			}
		}
	}

	/**
	 * Estado inicial botones.
	 */
	public void estadoInicialBotones() {
		if (!masterList.isEmpty()) {
			this.banderaTagsMaster(3);

		} else {
			IndCapturaDTO IndCapturaDTO = new IndCapturaDTO();
			masterList.add(IndCapturaDTO);
			this.banderaTagsMaster(1);
			this.estadeTagsDetails(1);
		}
	}

	/**
	 * Filtra programa desc.
	 *
	 * @param row the row
	 */
	public void filtraProgramaDesc(Integer row) {
		System.out.println("filtrarProgramaDesc: " + masterList.get(row).getClvnep());
		masterList.get(row)
				.setDesProgramas(indicadoresCapturaService.llenaDescPrograma(masterList.get(row).getClvnep()));
		
		Muninep muninep = this.searchMuninepByCampo7(masterList.get(row).getClvnep());
		masterList.get(row).setObjetivos(muninep.getCampo8());
	}

	
	private Muninep searchMuninepByCampo7(String campo7) {
		
		return muniNepRepository.findFirstByCampo7AndIdsector(campo7, this.getUserDetails().getIdSector());
		
	}
	
	/**
	 * Filtra programa.
	 *
	 * @param row the row
	 */
	// busca la lista de programas de esa dependencia y su descripcion
	public void filtraPrograma(Integer row) {
		clvProgramaList.clear();
		List<Xcatpro> listaDepPro = indicadoresCapturaService.filtraPrograma(masterList.get(row).getClvDep());
		System.out.println("listaDepPro: " + listaDepPro.size());
		for (Xcatpro dep : listaDepPro) {
			clvProgramaList.add(dep.getClvfun());
		}
		masterList.get(row).setDesProgramas("");
		masterList.get(row).setDesDep(indicadoresCapturaService.desripcionDep(masterList.get(row).getClvDep()));
	}

	/**
	 * Salvar captura.
	 *
	 * @param row the row
	 */
	public void salvarCaptura(Integer row) {
		LOGGER.info("salvarCaptura... " + capturaAdicionar);

		if (masterList.get(row).getClvDep() == null || masterList.get(row).getClvnep() == null) {
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Favor de capturar campos requeridos...",
					"Favor de capturar campos requeridos...");
		} else {
			Calendar c = Calendar.getInstance();
			IndCapturaDTO indcap = new IndCapturaDTO();
			indcap.setClaveDep(1);
			indcap.setAnioCap(c.get(Calendar.YEAR));
			indcap.setNumVer(1);
			indcap.setClvDep(masterList.get(row).getClvDep());
			indcap.setClvfuen("041");// no utilizado en el
										// requerimiento
			
			indcap.setCampo0(masterList.get(row).getCampo0() == null ? StringUtils.EMPTY : masterList.get(row).getCampo0());
			indcap.setCampo1(masterList.get(row).getCampo1() == null ? StringUtils.EMPTY : masterList.get(row).getCampo1());
			indcap.setCampo2(masterList.get(row).getCampo2() == null ? StringUtils.EMPTY : masterList.get(row).getCampo2());
			indcap.setCampo3(masterList.get(row).getCampo3() == null ? StringUtils.EMPTY : masterList.get(row).getCampo3());
			indcap.setCampo4(masterList.get(row).getCampo4() == null ? StringUtils.EMPTY : masterList.get(row).getCampo4());
			indcap.setCampo5(masterList.get(row).getCampo5() == null ? StringUtils.EMPTY : masterList.get(row).getCampo5());
			
			indcap.setDimensionc(masterList.get(row).getDimensionc());
			indcap.setObjetivos(masterList.get(row).getObjetivos());
			indcap.setEstrategias(masterList.get(row).getEstrategias());
			indcap.setDesProblemasOportunidades(masterList.get(row).getDesProblemasOportunidades());
			indcap.setClvnep(masterList.get(row).getClvnep());
			indcap.setUserId(this.getUserDetails().getUsername());
			indcap.setIdsector(this.getUserDetails().getIdSector());
			if (masterList.get(row).getId() != null) {
				indcap.setId(masterList.get(row).getId());
			}
			if (this.getbModificar() == true) {
				indicadoresCapturaService.saveCabecero(indcap);

				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Indicador de Captura Modificado correctamente"));

				this.banderaTagsMaster(3);
				this.estadeTagsDetails(3);
				if (CollectionUtils.isEmpty(masterList.get(row).getMetaList()))
					this.estadeTagsDetails(4);
				this.metaDesabilitadoAdicionar = false;
				this.setbModificar(false);
				this.setCurrentRowMeta(0);
				//establece las listas al init
				this.limpiarListas();
				this.llenarCabecera();
				this.llenarListaDependencias();
				
			} else {
				if (indicadoresCapturaService.puedeRegistrarCabecero(masterList.get(row).getId(),
						masterList.get(row).getClvDep(), masterList.get(row).getClvnep())) {
					UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
							"Ya existe en encabezado seleccionado", "Ya existe en encabezado seleccionado");
					LOGGER.info("ya existe");
				} else {
					try {

						indicadoresCapturaService.saveCabecero(indcap);

						FacesContext context = FacesContext.getCurrentInstance();
						context.addMessage(null, new FacesMessage("Indicador de Captura guardado correctamente"));

						
						this.banderaTagsMaster(3);
						this.estadeTagsDetails(1);
						this.metaDesabilitadoAdicionar = false;
						capturaAdicionar = false;
						this.setCurrentRowMeta(0);
						//establece las listas al init						
						this.limpiarListas();
						this.llenarCabecera();
						this.llenarListaDependencias();
					} catch (Exception e) {
						UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
								"Ocurrió un error al salvar el Indicador de Diseño",
								"Ocurrió un error al salvar el Indicador de Diseño");
					}
				}
			}
		}
	}

	/**
	 * Modificar captura.
	 *
	 * @param row the row
	 */
	public void modificarCaptura(Integer row) {
		capturaAdicionar = false;
		this.setbModificar(true);
		// if
		// (indicadoresCapturaService.tieneMetas(masterList.get(row).getClvDep(),
		// masterList.get(row).getClvnep())) {
		this.banderaTagsMaster(2);
		// }
		this.estadeTagsDetails(3);
		if (CollectionUtils.isEmpty(masterList.get(row).getMetaList())) {
			this.estadeTagsDetails(4);
		}
		this.setDeshabilitadoEditables(false);
		this.setCapturaDeshabilitado(true);

	}

	/**
	 * Reset captura.
	 *
	 * @param row the row
	 */
	public void resetCaptura(Integer row) {
		this.banderaTagsMaster(2);
		if (null == masterList.get(row).getId()) {
			IndCapturaDTO IndCapturaDTO = new IndCapturaDTO();
			masterList.set(row, IndCapturaDTO);

		} else {

			masterList.set(row, masterListOriginal.get(row));
			List<IndMetaDTO> listMeta = indicadoresCapturaService.findAllByClvdep(masterList.get(row).getClvDep(),
					masterList.get(row).getClvnep());
			masterList.get(row).setMetaList(listMeta);
		}

		// load masterList with get row

	}

	/**
	 * Cancel captura.
	 *
	 * @param row the row
	 */
	public void cancelCaptura(Integer row) {
		this.banderaTagsMaster(3);
		this.setbBtnModificar(true);
		// el unico registro es el vacio
		if (masterList.size() == 1 && null == masterList.get(row).getId()) { 
//			masterList.clear();
//			IndCapturaDTO indCapturaDTO = new IndCapturaDTO();
//			masterList.add(indCapturaDTO);
//			this.banderaTagsMaster(1);
//			this.setMetaDesabilitadoBorrar(true);
//			this.setMetaDesabilitadoCancelar(true);
			masterList.clear();
			IndCapturaDTO indCapturaDTO = new IndCapturaDTO();
			masterList.add(indCapturaDTO);
			bAdicionar = false;
			this.setCapturaVisibleSalvar(false);
			this.setCapturaVisibleModificar(false);
			this.setCapturaDesabilitadoAdicionar(false);
			this.setCapturaDesabilitadoBorrar(true);
			this.setCapturaDesabilitadoCancelar(true);
			this.setCapturaDesabilitadoReset(true);
			this.setCapturaDeshabilitado(true);
			this.setDeshabilitadoEditables(true);
			this.banderaTagsMaster(1);
			this.setMetaDesabilitadoBorrar(true);
			this.setMetaDesabilitadoCancelar(true);

		} else {
			//tiene un solo registro en la base es el modificar+cancelar
			if (masterList.size() == 1 && null != masterList.get(row).getId()) { 
				masterList.set(row, masterListOriginal.get(row));
				List<IndMetaDTO> listMeta = indicadoresCapturaService.findAllByClvdep(masterList.get(row).getClvDep(),
						masterList.get(row).getClvnep());
				masterList.get(row).setMetaList(listMeta);
				this.setMetaVisibleSalvar(false);
				this.setMetaVisibleModificar(true);
				this.setMetaDesabilitadoAdicionar(false);
				this.setMetaDesabilitadoBorrar(false);
				this.setMetaDesabilitadoCancelar(true);
				this.setMetaDesabilitadoReset(true);
				this.setMetaDeshabilitado(true);
				this.setDeshabilitadoEditables(true);
			}else{
				//cuando tiene más de 1 registro en la base
				if (!masterList.isEmpty() && masterList.size() != 1) {
					if (capturaAdicionar) {
						masterList.remove(masterList.size() - 1);
						capturaAdicionar = false;
//						filtraTema(currentPage - 1);
//						filtraDependencia(currentPage - 1);
//						filtraPrograma(currentPage - 1);
//						filtraCodeIndicador(currentPage - 1);
						row = row - 1;
					} else {
						masterList.set(row, masterListOriginal.get(row));
						List<IndMetaDTO> listMeta = indicadoresCapturaService.findAllByClvdep(masterList.get(row).getClvDep(),
								masterList.get(row).getClvnep());
						masterList.get(row).setMetaList(listMeta);
					}
					this.setMetaVisibleSalvar(false);
					this.setMetaVisibleModificar(true);
					this.setMetaDesabilitadoAdicionar(false);
					this.setMetaDesabilitadoBorrar(false);
					this.setMetaDesabilitadoCancelar(true);
					this.setMetaDesabilitadoReset(true);
					this.setMetaDeshabilitado(true);
					this.setDeshabilitadoEditables(true);
				}else{
					resetCaptura(row);
				}
			}
			

		}

		// load masterList with get row

	}

	/**
	 * Adicionar captura.
	 *
	 * @param row the row
	 */
	public void adicionarCaptura(Integer row) {
		this.banderaTagsMaster(2);
		this.setbBtnModificar(false);

		capturaAdicionar = true;
		// if (indicadoresCapturaService.llenarCabecera().isEmpty()) {
		// this.modificarCaptura(row);
		// } else {

		if (null != masterList.get(row).getId()) {
			IndCapturaDTO IndCapturaDTO = new IndCapturaDTO();
			masterList.add(IndCapturaDTO);
		}
		//
		// }
		this.estadeTagsDetails(1);
		this.setCapturaDeshabilitado(false);
		this.setDeshabilitadoEditables(false);
	}

	/**
	 * Borrar captura.
	 *
	 * @param row the row
	 */
	public void borrarCaptura(Integer row) {
		try {
			if (indicadoresCapturaService.tieneMetas(masterList.get(row).getClvDep(),
					masterList.get(row).getClvnep())) {
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
						"Existen Codigos, no se puede eliminar", "Existen Codigos, no se puede eliminar");
			} else {
				indicadoresCapturaService.deleteCabecero(masterList.get(row).getId().longValue());

				this.setCapturaDeshabilitado(true);
				this.setDeshabilitadoEditables(true);
				masterList.clear();
				llenarCabecera();
				estadoInicialBotones();
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Indicador Cabecero borrada correctamente", "");

				if (currentPage > 0)
					currentPage = row - 1;
				else
					currentPage = 0;
				List<IndMetaDTO> listMeta = indicadoresCapturaService.findAllByClvdep(
						masterList.get(currentPage).getClvDep(), masterList.get(currentPage).getClvnep());
				this.estadeTagsDetails(3);
				if (CollectionUtils.isEmpty(listMeta)) {
					this.estadeTagsDetails(4);
				}

			}
		} catch (Exception e) {
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
					"Ocurrió un error al borrar el Indicador Cabeceror",
					"Ocurrió un error al borrar el Indicador Cabecero");
		}
	}

	/**
	 * Cancelar captura.
	 *
	 * @param row the row
	 */
//	public void cancelarCaptura(Integer row) {
//		if (capturaAdicionar && !masterList.isEmpty()) {
//			masterList.remove(masterList.size() - 1);
//			capturaAdicionar = false;
//
//			this.setCapturaVisibleSalvar(false);
//			this.setCapturaVisibleModificar(true);
//			this.setCapturaDesabilitadoAdicionar(false);
//			this.setCapturaDesabilitadoBorrar(true);
//			this.setCapturaDesabilitadoCancelar(true);
//			this.setCapturaDesabilitadoReset(true);
//			this.setCapturaDeshabilitado(true);
//			this.setDeshabilitadoEditables(true);
//		} else {
//			this.resetCaptura(row);
//		}
//	}

	/**
	 * Salvar meta.
	 *
	 * @param rowMaster the row master
	 */
	public void salvarMeta(Integer rowMaster) {
		LOGGER.info("Salvar meta - RowMaster: " + rowMaster + " RowMeta: " + getCurrentRowMeta());
		IndCapturaDTO mDto = masterList.get(currentPage);
		LOGGER.info("salvarMeta: " + mDto);

		try {
			if (indicadoresCapturaService.tienePrograma(masterList.get(currentPage).getClvDep(),
					masterList.get(currentPage).getClvnep())) {

				IndMetaDTO dto = masterList.get(currentPage).getMetaList().get(getCurrentRowMeta());
				if (indicadoresCapturaService.puedeRegistrarMeta(dto.getId(), dto.getClvdep(), dto.getClvnep(),
						dto.getClvmet())) {
					masterList.get(currentPage).getMetaList().get(getCurrentRowMeta())
							.setClvdep(masterList.get(currentPage).getClvDep());
					masterList.get(currentPage).getMetaList().get(getCurrentRowMeta())
							.setClvnep(masterList.get(currentPage).getClvnep());
					masterList.get(currentPage).getMetaList().get(getCurrentRowMeta())
							.setIdsector(this.getUserDetails().getIdSector());
					if (masterList.get(currentPage).getMetaList().get(getCurrentRowMeta()).getId() != null) {
						masterList.get(currentPage).getMetaList().get(getCurrentRowMeta())
								.setId(masterList.get(currentPage).getMetaList().get(getCurrentRowMeta()).getId());
					}
					if (this.getbModificarDe() == true) {
						indicadoresCapturaService
								.updateMetas(masterList.get(currentPage).getMetaList().get(getCurrentRowMeta()));
						generateNotificationFront(FacesMessage.SEVERITY_INFO, "Datos Editado correctamente", "");
						this.estadeTagsDetails(3);
						this.setbModificarDe(false);
					} else {
						Boolean badera = indicadoresCapturaService
								.saveMetas(masterList.get(currentPage).getMetaList().get(getCurrentRowMeta()));
						if (badera == Boolean.TRUE) {
							FacesContext context = FacesContext.getCurrentInstance();
							context.addMessage(null, new FacesMessage("Meta guardada correctamente"));
							this.estadeTagsDetails(3);
							mDto.setMetaList(
									indicadoresCapturaService.findAllByClvdep(mDto.getClvDep(), mDto.getClvnep()));

						} else {
							this.estadeTagsDetails(2);

						}
					}

					masterList.set(rowMaster, mDto);

				} else {
					UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR,
							"El codigo ya se encuentra registrado", "El codigo ya se encuentra registrado");
				}

			} else {
				UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Debe capturar Cabecero",
						"Debe capturar Cabecero");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al salvar la Meta",
					"Ocurrió un error al salvar la Meta");
		}
	}

	/**
	 * Modificar meta.
	 */
	public void modificarMeta() {
		metaAdicionar = false;
		this.estadeTagsDetails(2);
		this.setbModificarDe(true);
	}

	/**
	 * Reset meta.
	 *
	 * @param row the row
	 * @param idexMaster the idex master
	 */
	public void resetMeta(Integer row, Integer idexMaster) {
		this.setMetaDeshabilitado(true);// always
		this.estadeTagsDetails(2);

		if (masterList.isEmpty()) {
			IndCapturaDTO IndCapturaDTO = new IndCapturaDTO();
			List<IndMetaDTO> listMeta = new ArrayList<IndMetaDTO>();
			IndCapturaDTO.setMetaList(listMeta);
			masterList.add(IndCapturaDTO);

		} else {
			List<IndMetaDTO> listMeta = indicadoresCapturaService
					.findAllByClvdep(masterList.get(idexMaster).getClvDep(), masterList.get(idexMaster).getClvnep());
			if (CollectionUtils.isNotEmpty(listMeta))
				masterList.get(idexMaster).setMetaList(listMeta);
			else
				masterList.get(idexMaster).getMetaList().set(currentRowMeta, new IndMetaDTO());

		}
		this.estadeTagsDetails(2);
		metaDeshabilitado = false;

	}

	/**
	 * Adicionar meta.
	 *
	 * @param rowCaptura the row captura
	 */
	public void adicionarMeta(Integer rowCaptura) {
		System.out.println("adicionarMeta: " + rowCaptura);
		this.estadeTagsDetails(2);

		metaAdicionar = true;
		if (masterList.isEmpty()) {
			this.modificarMeta();
		} else {
			IndMetaDTO indMetaDTO = new IndMetaDTO();
			if (masterList.get(rowCaptura).getMetaList() == null) {
				masterList.get(rowCaptura).setMetaList(new ArrayList<IndMetaDTO>());
			}
			masterList.get(rowCaptura).getMetaList().add(indMetaDTO);
			this.setMetaDeshabilitado(false);
		}
	}

	/**
	 * Borrar meta.
	 *
	 * @param rowMaster the row master
	 */
	public void borrarMeta(Integer rowMaster) {
		if (masterList.get(rowMaster).getMetaList() == null || masterList.get(rowMaster).getMetaList().isEmpty()) {
			return;
		}

		System.out.println("Borrar meta: " + getCurrentRowMeta() + ":ID: "
				+ masterList.get(rowMaster).getMetaList().get(getCurrentRowMeta()).getId());
		try {
			indicadoresCapturaService
					.deleteMeta(masterList.get(rowMaster).getMetaList().get(getCurrentRowMeta()).getId());
			this.estadeTagsDetails(3);
			List<IndMetaDTO> listMeta = indicadoresCapturaService.findAllByClvdep(masterList.get(rowMaster).getClvDep(),
					masterList.get(rowMaster).getClvnep());
			if (CollectionUtils.isEmpty(listMeta)) {
				this.estadeTagsDetails(4);
			}

			if (currentRowMeta != 0) {
				setCurrentRowMeta(currentRowMeta - 1);

			}

			this.setMetaDeshabilitado(true);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Meta borrada correctamente"));

			masterList.get(rowMaster).setMetaList(indicadoresCapturaService
					.findAllByClvdep(masterList.get(rowMaster).getClvDep(), masterList.get(rowMaster).getClvnep()));

		} catch (Exception e) {
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al borrar la Meta",
					"Ocurrió un error al borrar la Meta");
		}
	}

	/**
	 * Cancelar meta.
	 *
	 * @param row the row
	 */
	public void cancelarMeta(Integer row) {
		IndCapturaDTO mDto = masterList.get(row);
		mDto.setMetaList(indicadoresCapturaService.findAllByClvdep(mDto.getClvDep(), mDto.getClvnep()));
		this.estadeTagsDetails(2);

		if (metaAdicionar && !masterList.isEmpty()) {
			// masterList.remove(masterList.size() - 1);
			metaAdicionar = false;
			this.estadeTagsDetails(4);
			currentRowMeta = 0;

			if (CollectionUtils.isNotEmpty(mDto.getMetaList())) {
				this.estadeTagsDetails(3);
				currentRowMeta = masterList.get(row).getMetaList().size() - 1;
			}
		} else {
			List<IndMetaDTO> listMeta = indicadoresCapturaService.findAllByClvdep(mDto.getClvDep(), mDto.getClvnep());
			masterList.get(row).setMetaList(listMeta);
			this.estadeTagsDetails(3);
			currentRowMeta = masterList.get(row).getMetaList().size() - 1;
			if (CollectionUtils.isEmpty(listMeta)) {
				this.estadeTagsDetails(4);
				currentRowMeta = 0;
			}

		}
		this.setMetaAdicionar(false);
		metaDesabilitadoAdicionar = false;
	}

	/**
	 * Gets the master list.
	 *
	 * @return the master list
	 */
	public List<IndCapturaDTO> getMasterList() {
		return masterList;
	}

	/**
	 * Sets the master list.
	 *
	 * @param masterList the new master list
	 */
	public void setMasterList(List<IndCapturaDTO> masterList) {
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
	 * Gets the indicadores captura service.
	 *
	 * @return the indicadores captura service
	 */
	public IndicadoresCapturaService getIndicadoresCapturaService() {
		return indicadoresCapturaService;
	}

	/**
	 * Sets the indicadores captura service.
	 *
	 * @param indicadoresCapturaService the new indicadores captura service
	 */
	public void setIndicadoresCapturaService(IndicadoresCapturaService indicadoresCapturaService) {
		this.indicadoresCapturaService = indicadoresCapturaService;
	}

	/**
	 * Gets the dependencia sel.
	 *
	 * @return the dependencia sel
	 */
	public IndCapturaDTO getDependenciaSel() {
		return dependenciaSel;
	}

	/**
	 * Sets the dependencia sel.
	 *
	 * @param dependenciaSel the new dependencia sel
	 */
	public void setDependenciaSel(IndCapturaDTO dependenciaSel) {
		this.dependenciaSel = dependenciaSel;
	}

	/**
	 * Gets the prog sel.
	 *
	 * @return the prog sel
	 */
	public IndCapturaDTO getProgSel() {
		return progSel;
	}

	/**
	 * Sets the prog sel.
	 *
	 * @param progSel the new prog sel
	 */
	public void setProgSel(IndCapturaDTO progSel) {
		this.progSel = progSel;
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
	 * Gets the current row meta.
	 *
	 * @return the current row meta
	 */
	public int getCurrentRowMeta() {
		return currentRowMeta;
	}

	/**
	 * Sets the current row meta.
	 *
	 * @param currentRowMeta the new current row meta
	 */
	public void setCurrentRowMeta(int currentRowMeta) {
		this.currentRowMeta = currentRowMeta;
	}

	/**
	 * Gets the master list original.
	 *
	 * @return the master list original
	 */
	public List<IndCapturaDTO> getMasterListOriginal() {
		return masterListOriginal;
	}

	/**
	 * Sets the master list original.
	 *
	 * @param masterListOriginal the new master list original
	 */
	public void setMasterListOriginal(List<IndCapturaDTO> masterListOriginal) {
		this.masterListOriginal = masterListOriginal;
	}

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
	 * Bandera tags master.
	 *
	 * @param opt the opt
	 */
	public void banderaTagsMaster(int opt) {
		switch (opt) {
		case 1:
			this.setCapturaDeshabilitado(true);// always
			this.setDeshabilitadoEditables(true);
			this.setCapturaVisibleSalvar(false);
			this.setCapturaVisibleModificar(false);
			this.setCapturaDesabilitadoAdicionar(false);
			this.setCapturaDesabilitadoBorrar(true);
			this.setCapturaDesabilitadoCancelar(true);
			this.setCapturaDesabilitadoReset(true);
			break;
		case 2:
			this.setCapturaDeshabilitado(false);// always
			this.setDeshabilitadoEditables(false);
			this.setCapturaVisibleSalvar(true);
			this.setCapturaVisibleModificar(false);
			this.setCapturaDesabilitadoAdicionar(true);
			this.setCapturaDesabilitadoBorrar(true);
			this.setCapturaDesabilitadoCancelar(false);
			this.setCapturaDesabilitadoReset(false);

			break;
		case 3:
			this.setbBtnModificar(false);
			this.setCapturaDeshabilitado(true);// always
			this.setDeshabilitadoEditables(true);
			this.setCapturaVisibleSalvar(false);
			this.setCapturaVisibleModificar(true);
			this.setCapturaDesabilitadoAdicionar(false);
			this.setCapturaDesabilitadoBorrar(false);
			this.setCapturaDesabilitadoCancelar(true);
			this.setCapturaDesabilitadoReset(true);
			break;
		default:
			break;
		}

	}

	/**
	 * Estade tags details.
	 *
	 * @param opt the opt
	 */
	public void estadeTagsDetails(int opt) {
		switch (opt) {

		case 1:
			this.setMetaVisibleModificar(false);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoAdicionar(true);
			this.setMetaDesabilitadoBorrar(true);
			this.setMetaDesabilitadoCancelar(true);
			this.setCapturaDeshabilitado(true);
			this.setMetaDeshabilitado(true);
			this.setDeshabilitadoEditables(true);
			break;
		case 2:
			this.setMetaVisibleModificar(false);
			this.setMetaVisibleSalvar(true);
			this.setMetaDesabilitadoReset(false);
			this.setMetaDesabilitadoAdicionar(true);
			this.setMetaDesabilitadoBorrar(true);
			this.setMetaDesabilitadoCancelar(false);
			this.setCapturaDeshabilitado(true);
			this.setMetaDeshabilitado(false);
			this.setDeshabilitadoEditables(true);
			break;
		case 3:
			this.setMetaVisibleModificar(true);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoAdicionar(false);
			this.setMetaDesabilitadoBorrar(false);
			this.setMetaDesabilitadoCancelar(true);
			this.setCapturaDeshabilitado(true);
			this.setMetaDeshabilitado(true);
			this.setDeshabilitadoEditables(true);
			break;
		case 4:
			this.setMetaVisibleModificar(false);
			this.setMetaVisibleSalvar(false);
			this.setMetaDesabilitadoReset(true);
			this.setMetaDesabilitadoAdicionar(false);
			this.setMetaDesabilitadoBorrar(true);
			this.setMetaDesabilitadoCancelar(true);
			this.setCapturaDeshabilitado(true);
			this.setMetaDeshabilitado(true);
			this.setDeshabilitadoEditables(true);
			break;
		default:
			break;
		}
	}

	/**
	 * Change page.
	 *
	 * @param event the event
	 */
	public void changePage(PageEvent event) {
		currentPage = (Integer) event.getPage();
		if (capturaAdicionar == true) {
			this.banderaTagsMaster(2);
			this.estadeTagsDetails(1);
			this.setCapturaDeshabilitado(false);
			this.setDeshabilitadoEditables(false);
		} else {
			if (null != masterList.get(currentPage).getId()) {
				this.banderaTagsMaster(3);
				if (CollectionUtils.isNotEmpty(masterList.get(currentPage).getMetaList())) {
					this.estadeTagsDetails(3);
				} else {
					this.estadeTagsDetails(4);
				}
			} else {
				this.banderaTagsMaster(1);
				this.estadeTagsDetails(4);
			}
		}
		currentRowMeta = 0;

	}

	public MuniNepRepository getMuniNepRepository() {
		return muniNepRepository;
	}

	public void setMuniNepRepository(MuniNepRepository muniNepRepository) {
		this.muniNepRepository = muniNepRepository;
	}

}
