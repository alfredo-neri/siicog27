package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.business.predicates.TcUsuarioPredicates.findByIdSector;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AjaxBehaviorEvent;
import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import com.gem.sistema.business.domain.AbstractEntity;
import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.FuentefGobierno;
import com.gem.sistema.business.domain.ModificaionSolicitud;
import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.domain.Natgas;
import com.gem.sistema.business.domain.TcAdquisicion;
import com.gem.sistema.business.domain.TcAdquisicionGiroSubgiro;
import com.gem.sistema.business.domain.TcAdquisicionPrograma;
import com.gem.sistema.business.domain.TcArticulosSA;
import com.gem.sistema.business.domain.TcBienServicio;
import com.gem.sistema.business.domain.TcEstadoContrato;
import com.gem.sistema.business.domain.TcFirmaDigital;
import com.gem.sistema.business.domain.TcFirmaSolicitudDetalle;
import com.gem.sistema.business.domain.TcTiposPago;
import com.gem.sistema.business.domain.TcOrigenRecurso;
import com.gem.sistema.business.domain.TcPersonalAdministrativo;
import com.gem.sistema.business.domain.TcSubgiro;
import com.gem.sistema.business.domain.TcTipoGasto;
import com.gem.sistema.business.domain.TcTramite;
import com.gem.sistema.business.domain.TcUnidadMedida;
import com.gem.sistema.business.domain.TcUsuario;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.FuenteFGobiernoRepository;
import com.gem.sistema.business.repository.catalogs.ModificacionSolicitudRepository;
import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.business.repository.catalogs.NatgasRepository;
import com.gem.sistema.business.repository.catalogs.TcAdquisicionGiroSubgiroRepository;
import com.gem.sistema.business.repository.catalogs.TcAdquisicionProgramasRepository;
import com.gem.sistema.business.repository.catalogs.TcAdquisicionRepository;
import com.gem.sistema.business.repository.catalogs.TcAreaAdministrativaRepository;
import com.gem.sistema.business.repository.catalogs.TcArticulosSARepository;
import com.gem.sistema.business.repository.catalogs.TcBienServicioRepository;
import com.gem.sistema.business.repository.catalogs.TcEstadosContratoRepository;
import com.gem.sistema.business.repository.catalogs.TcFirmaDigitalRepository;
import com.gem.sistema.business.repository.catalogs.TcFirmaSolicitudDetalleRepository;
import com.gem.sistema.business.repository.catalogs.TcTiposPagoRepository;
import com.gem.sistema.business.repository.catalogs.TcOrigenRecursoRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcPersonalAdministrativoRepository;
import com.gem.sistema.business.repository.catalogs.TcSubgiroRepository;
import com.gem.sistema.business.repository.catalogs.TcTiposGastoRepository;
import com.gem.sistema.business.repository.catalogs.TcTramitesRepository;
import com.gem.sistema.business.repository.catalogs.TcUnidadMedidaRepository;
import com.gem.sistema.business.repository.catalogs.TcUsuarioRepository;
import com.gem.sistema.business.repository.load.PasoRepository;
import com.gem.sistema.business.service.catalogos.AdquisicionesService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.load.fileupload.POIReadParseService;
import com.gem.sistema.util.Regex;
import com.gem.sistema.web.util.GetFileInlineServlet;
import com.gem.sistema.web.util.ImagesFirmasDigitales;

@Component("solicitudesAdquisicionesMB")
@Scope("view")
public class SolicitudesAdquisicionesMB extends BaseDirectReport {
	private static final String DOWNLOAD_TXT = " jQuery('#formArticulos\\\\:downTxt').click();";
	private Integer COLUMNAS_A_LEER_ECXEL[] = { 1, 2, 3, 4, 5, 6 };

	private Boolean contieneEncabezados = true;
	private String columnasAleerPorUsuario = "";
	private Integer[] columnas;
	private Boolean remplazar = false;
	private UploadedFile file;
	private StreamedContent fileErrors = null;

	private TcAdquisicion solicitudCaptured;
	private Conctb conctb = null;
	private List<Integer> listMes;
	private Integer mes;
	private BigDecimal montoComprometidoBySolicitud = BigDecimal.ZERO;

	private Boolean continuarCapturaSolicitud = false;
	/** Array principal de solicitudes */
	private List<TcAdquisicion> solicitudesList = new ArrayList<TcAdquisicion>();

	// Arrays para combos de form
	private List<TcPersonalAdministrativo> personalList = new ArrayList<>();
	private List<TcTramite> tramitesList = new ArrayList<TcTramite>();
	private List<TcTipoGasto> tiposGastoList = new ArrayList<TcTipoGasto>();
	private List<TcOrigenRecurso> recursosList = new ArrayList<TcOrigenRecurso>();
	private List<TcTiposPago> formasPagoList = new ArrayList<TcTiposPago>();
	private List<TcBienServicio> bienesList = new ArrayList<TcBienServicio>();
	private List<TcUsuario> usuariosList = new ArrayList<TcUsuario>();
	private List<TcFirmaDigital> firmasList = new ArrayList<TcFirmaDigital>();
	private List<TcSubgiro> subgirosList = new ArrayList<>();
	private List<Natgas> girosList = new ArrayList<>();
	private List<Cuenta> cuentasList = new ArrayList<>();
	private List<Catdep> dependenciasList = new ArrayList<>();
	private List<FuentefGobierno> fuentesGobiernoList = new ArrayList<>();
	private List<Muninep> muninepList = new ArrayList<>();
	// Arrays par el manejo de giros y subgiros comerciales
	/** Subgiros selecionados en la vista */
	private List<TcSubgiro> subgirosSelected = new ArrayList<>();
	/** codigos programaticos por aquisicion */
	private List<TcAdquisicionPrograma> codigosBySA = new ArrayList<>();
	/** Codigo en captura de lado del front */
	private TcAdquisicionPrograma codigosCaptured = new TcAdquisicionPrograma();
	/**
	 * ID temporal para eliminar un codigo programatico sin afectar al la captura en
	 * curso
	 */
	private Long codigoIdToDelete;
	/**
	 * Array de articulos se actualizada cada que se editan los articulos de una
	 * solicitud
	 */
	private List<TcArticulosSA> articulosBySA = new ArrayList<TcArticulosSA>();
	/** array de firmas */
	private List<TcFirmaSolicitudDetalle> firmasBySA = new ArrayList<TcFirmaSolicitudDetalle>();
	/** entidad para majejo de form de artiuclos */
	private TcArticulosSA articuloAdd = null;
	/** entidad para majejo de vista de detalle de la solicitud */
	private TcArticulosSA articuloView = null;

	private Boolean cargaArticulosManual = false;
	/**
	 * Entidad par eliminar un articulo se separa para evitar sobre escritura con la
	 * captura en curso
	 */
	private TcArticulosSA articuloDelete = null;
	/** Posicion de firma selecionada en front */
	private Integer firmaPosicion = 0;
	/**
	 * Bandera para identificar que panel se esta renderizado
	 * <li>1.-Panel de captura de solicitd</li>
	 * <li>2.-Panel de articulos</li>
	 * <li>3.-Panel de firmas de revision</li>
	 * <li>4.-Panel de firmas de recepcion</li>
	 * <li>5.-Panel lista de solicitudes</li>
	 * <li>6.-Panel detalles de la solicitud</li>
	 * <li>7.-Panel de modificaion de solicitud</li>
	 */
	private Integer panelRendered = 2;
	/** steo activo par la cpautra de solicitud */
	private Integer step = 0;
	private String busquedaSolicitudes = "";

	private Boolean obsRendered = false;
	/** Unidades de medida vigentes del catalogo TC_UNIDADES_MEDIDA */
	private List<TcUnidadMedida> unidadesMedida = new ArrayList<TcUnidadMedida>();
	/** Estados de solicitudes y/o contratos */
	private List<TcEstadoContrato> estadoContrato = new ArrayList<TcEstadoContrato>();
	/** Variable para guardado temporal de modificaion */
	private ModificaionSolicitud modificaionSolicitud = new ModificaionSolicitud();
	/** id de archivo guardao en la sesiom */
	private String id;
	/** nombre de archivo guardao en la sesiom */
	private String name;
	/** tipo de archivo seleccionado */
	private String type = "";
	/** archivos permitidos en la carga de modificaion */
	List<String> permitedTypes = Arrays.asList(".pdf", ".png", ".jpg", ".jpeg");

	/** Lista de modificaciones por solicitud */
	private List<ModificaionSolicitud> modificaionBySA = new ArrayList<ModificaionSolicitud>();

	@Value("#{modificacionSolicitudRepository}")
	private ModificacionSolicitudRepository modificacionSolicitudRepository;

	@Value("#{tcEstadosContratoRepository}")
	private TcEstadosContratoRepository estadosContratoRepository;

	@Value("#{pasoRepository}")
	private PasoRepository pasoRepository;

	@Value("#{tcAdquisicionProgramaRepository}")
	private TcAdquisicionProgramasRepository adquisicionProgramaRepository;

	@Value("#{tcFirmaSolicitudDetalleRepository}")
	private TcFirmaSolicitudDetalleRepository firmaSolicitudDetalleRepository;

	@Value("#{tcPersonalAdministrativoRepository}")
	private TcPersonalAdministrativoRepository personalAdministrativoRepository;

	@Value("#{tcFirmaDigitalRepository}")
	private TcFirmaDigitalRepository firmaDigitalRepository;

	@Value("#{tcEstadosContratoRepository}")
	private TcEstadosContratoRepository tcEstadosContratoRepository;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@Value("#{tcAdquisicionRepository}")
	private TcAdquisicionRepository adquisicionRepository;

	@Value("#{tcTramitesRepository}")
	private TcTramitesRepository tramitesRepository;

	@Value("#{tcAreaAdministrativaRepository}")
	private TcAreaAdministrativaRepository administrativaRepository;

	@Value("#{tcTiposGastoRepository}")
	private TcTiposGastoRepository tiposGastoRepository;

	@Value("#{tcOrigenRecursoRepository}")
	private TcOrigenRecursoRepository origenRecursoRepository;

	@Value("#{tcTiposPagoRepository}")
	private TcTiposPagoRepository formasPagoRepository;

	@Value("#{tcBienServicioRepository}")
	private TcBienServicioRepository bienServicioRepository;

	@Value("#{tcUsuarioRepository}")
	private TcUsuarioRepository tcUsuarioRepository;

	@Value("#{tcArticulosSARepository}")
	private TcArticulosSARepository articulosRepository;

	@Value("#{tcUnidadMedidaRepository}")
	private TcUnidadMedidaRepository unidadMedidaRepository;

	@Value("#{natgasRepository}")
	private NatgasRepository natgasRepository;

	@Value("#{tcSubgiroRepository}")
	private TcSubgiroRepository subgiroRepository;

	@Value("#{tcAdquisicionGiroSubgiroRepository}")
	private TcAdquisicionGiroSubgiroRepository giroSubgiroRepository;

	// difem26: "#{adquisicionesService}" no corresponde a ningun bean; JSF inyectaba null. Pendiente de corregir.
	@Value("#{null}")
	private AdquisicionesService adquisicionesService;

	@Value("#{poiReadParse}")
	private POIReadParseService poiReadParseService;

	@Value("#{cuentaRepository}")
	private CuentaRepository cuentaRepository;

	@Value("#{catdepRepository}")
	private CatdepRepository catdepRepository;

	@Value("#{muniNepRepository}")
	private MuniNepRepository muniNepRepository;

	@Value("#{fuenteFGobiernoRepository}")
	private FuenteFGobiernoRepository fuenteFGobiernoRepository;

	@PostConstruct
	public void init() {
		panelRendered = 5;
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		// El catalogo se carga antes del articulo por defecto, que toma la primera unidad
		unidadesMedida = unidadMedidaRepository.findAllVigentes();
		articuloAdd = getDefaulthArticulo();
		listMes = IntStream.range(1, 13).boxed().collect(Collectors.toList());
		mes = listMes.get(0);
		jasperReporteName = "solicitudAdquisicion";
		endFilename = jasperReporteName + ".pdf";
		getInitialData();
		buscar();
		solicitudCaptured = solicitudesList.isEmpty() ? new TcAdquisicion() : solicitudesList.get(0);
		step = 0;
	}

	public void buscar() {
		try {
			if (busquedaSolicitudes == null || busquedaSolicitudes.equals("")) {
				solicitudesList = adquisicionRepository.getAllAdquisicionesWithRelations();
			} else {
				solicitudesList = adquisicionRepository.getAllAdquisicionesWithRelations().stream()
						.filter(p -> p.busquedaSimple().toLowerCase().contains(busquedaSolicitudes.toLowerCase()))
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Obtiene todos los datos iniciales para el modulo */
	private void getInitialData() {
		Integer sector = this.getUserDetails().getIdSector();
		tramitesList = tramitesRepository.findAll();
		personalList = personalAdministrativoRepository.getAllWithArea();
		bienesList = bienServicioRepository.findAll();
		tiposGastoList = tiposGastoRepository.findAll();
		formasPagoList = formasPagoRepository.findAll();
		recursosList = origenRecursoRepository.findAll();
		tcUsuarioRepository.findAll(findByIdSector(Long.valueOf(sector))).forEach(usuariosList::add);
		estadoContrato = tcEstadosContratoRepository.findAll();
		firmasList = firmaDigitalRepository.findAllFirmas();
		girosList = natgasRepository.findAllByIdsectorOrderByClvgas(sector);
		cuentasList = cuentaRepository.findFirstLevelAccountsBySector(Long.valueOf(sector));
		muninepList = muniNepRepository.getListSubPrograma(sector);
		fuentesGobiernoList = fuenteFGobiernoRepository.findAllFourLevel();
		dependenciasList = catdepRepository.findAllByIdsectorOrderByClvdep(sector);

	}

	/** Instancia por defaulth de una solicitud */
	private TcAdquisicion getDefaultAdquisicion() {
		TcAdquisicion defaultAdquisicion = new TcAdquisicion();
		defaultAdquisicion.setEstado(getEstadoContrato(TcEstadoContrato.REVISANDO));
		defaultAdquisicion.setFechaElaboracion(new Date());
		defaultAdquisicion.setAnticipo(BigDecimal.ZERO);
		defaultAdquisicion.setNumeroControl(adquisicionRepository.getNextConsecutivo());
		defaultAdquisicion.setCargoReceptor("");
		defaultAdquisicion.setDictamenUnico("");
		defaultAdquisicion.setLugarEntrega("");
		defaultAdquisicion.setMontoComprometido(BigDecimal.ZERO);
		defaultAdquisicion.setNoOficioAutorizacion(0);
		defaultAdquisicion.setNombreReceptor("");
		defaultAdquisicion.setObs("");
		defaultAdquisicion.setOficioCertificacion("");
		defaultAdquisicion.setOficioJustificacion("");
		defaultAdquisicion.setProgramaGobierno("");
		defaultAdquisicion.setProgramado("");
		defaultAdquisicion.setUsuario(this.getUserDetails().getUsername());
		return defaultAdquisicion;
	}

	/**
	 * Asingna una nueva instancia al la variable de captura de solicitud y consulta
	 * los subgiros relacionados con la misma
	 */
	public void add() {
		solicitudCaptured = getDefaultAdquisicion();
		this.panelRendered = 1;
	}

	public void updateSolicitud() {
		getSubgirosByAdquisicionSelected();
		getSubgiros();
		getCodigosByAdquisicion();
		articulosRepository.findAllByAdquisicion(solicitudCaptured).forEach(a -> {
			montoComprometidoBySolicitud = montoComprometidoBySolicitud.add(a.getPrecio());
		});
		this.panelRendered = 1;
	}

	/** Guarda los cambios en una solicitud */
	public void guardar() {
		if (validSolicitud(solicitudCaptured)) {
			continuarCapturaSolicitud = true;
			try {
				TcAdquisicion numeroControl = adquisicionRepository
						.findFirstByNumeroControl(solicitudCaptured.getNumeroControl());
				if (numeroControl == null || numeroControl.getId().equals(solicitudCaptured.getId())) {
					boolean modificando = solicitudCaptured != null && solicitudCaptured.getId() != null
							&& solicitudCaptured.getId() > 0;
					String msgByForm = "";
					switch (step) {
					case 0:
						msgByForm = "generales";
						break;
					case 1:
						msgByForm = "de identificaion y tramitación";
						break;
					case 2:
						msgByForm = "datos de la afectación presupuestal";
						break;
					case 3:
						msgByForm = "datos de recepción";
						break;
					}
					String msg = "Los datos " + msgByForm + " se han " + (modificando ? "modificado" : "agreagado")
							+ " con éxito!";
					solicitudCaptured
							.setObs(solicitudCaptured.getObs() == null || solicitudCaptured.getObs().equals("") ? ""
									: solicitudCaptured.getObs());
					solicitudCaptured = adquisicionRepository.save(solicitudCaptured);
					if (modificando) {
						solicitudesList = solicitudesList.stream()
								.map(s -> s.getId().equals(solicitudCaptured.getId()) ? solicitudCaptured : s)
								.collect(Collectors.toList());
					} else {
						solicitudesList.add(solicitudCaptured);
					}
					displayInfoMessage(msg);

				} else {
					displayInfoMessage("El numero de control ya ha sido asignado");
				}
			} catch (Exception e) {
				e.printStackTrace();
				displayInfoMessage("Ha ocurrido un error al guardar la solicitud");
			}

		} else {
			continuarCapturaSolicitud = false;
		}

	}

	/** Develve el estado incial de una solicitud en el momento de caputura */
	public void reset() {
		if (solicitudCaptured != null && solicitudCaptured.getId() != null && solicitudCaptured.getId() > 0) {
			solicitudCaptured = adquisicionRepository.findById(solicitudCaptured.getId()).orElse(null);
			buscar();
		} else {
			solicitudCaptured = getDefaultAdquisicion();
		}
	}

	/** Elimina una solicitud con sus articulos, firmas, subgiros y programas */
	public void delete() {
		try {
			adquisicionesService.deleteAdquisicionAndRelations(solicitudCaptured);
			solicitudesList.remove(solicitudCaptured);
			backSolicitudes();
		} catch (Exception e) {
			displayInfoMessage("Ha ocurrido un error al eliminar la solicitud");
			e.printStackTrace();
		}

	}

	/**
	 * Revisa si el sario actual es el revisor de la solisitud de la pagina actual
	 */
	public Boolean getCurrentUserIsRevisor() {
		return this.getUserDetails().getUsername().equals(solicitudCaptured.getRevisor());
	}

	/** Revisa si el suario actual es el creador de la solicitud actual */
	public Boolean getCurrentUserIsOwner() {
		return this.getUserDetails().getUsername().equals(solicitudCaptured.getUsuario());
	}

	public void guardarObsRevisor() {
		try {
			if (solicitudCaptured.getObsRevisor().length() > 10000) {
				displayInfoMessage("Las notas son demasiados grandes para guardar");
			} else {
				solicitudCaptured = adquisicionRepository.save(solicitudCaptured);
				solicitudesList = solicitudesList.stream()
						.map(s -> s.getId().equals(solicitudCaptured.getId()) ? solicitudCaptured : s)
						.collect(Collectors.toList());
				displayInfoMessage("Se han agregado correctamente las notas");
			}
		} catch (Exception e) {
			e.printStackTrace();
			displayInfoMessage("Ha ocurrido un error al guardar las notas");
		}

	}

	private TcEstadoContrato getEstadoContrato(String estado) {
		return estadoContrato.stream().filter(e -> e.getEstado().equals(estado)).findFirst().orElse(null);
	}

	public void aprobarSolicitud() {
		try {
			solicitudCaptured.setEstado(getEstadoContrato(TcEstadoContrato.REVISADO));
			solicitudCaptured = adquisicionRepository.save(solicitudCaptured);
			solicitudesList = solicitudesList.stream()
					.map(s -> s.getId().equals(solicitudCaptured.getId()) ? solicitudCaptured : s)
					.collect(Collectors.toList());
			displayInfoMessage("Se ha aprobado la solicitud");
		} catch (Exception e) {
			e.printStackTrace();
			displayInfoMessage("Ha ocurrido un error al aprobar la solicitud");
		}

	}

	public String getUsername() {
		return this.getUserDetails().getUsername();
	}

	private void showError(String id, String error) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(error));
	}

	/** Valida que la solicitud sea correcta */
	private Boolean validSolicitud(TcAdquisicion solicitud) {
		if (solicitud == null)
			return false;
		else
			switch (step) {
			case 0:
				return datosGeneralesSolcitudValidos();
			case 1:
				return datosGeneralesSolcitudValidos() && datosIdentificacionTramitacionSolcitudValidos();
			case 2:
				return datosGeneralesSolcitudValidos() && datosIdentificacionTramitacionSolcitudValidos()
						&& datosAfectacionPresupuestalSolcitudValidos();
			case 3:
				return datosGeneralesSolcitudValidos() && datosIdentificacionTramitacionSolcitudValidos()
						&& datosAfectacionPresupuestalSolcitudValidos() && datosRecepcionSolcitudValidos();
			default:
				return false;
			}

	}

	private Boolean datosGeneralesSolcitudValidos() {
		Map<String, String> erros = new HashMap<String, String>();
		if (!isValid(solicitudCaptured.getFechaElaboracion()))
			erros.put("fechaElaboracion", "La fecha elaboracion es requerida");
		if (!isValid(solicitudCaptured.getBienServicio()))
			erros.put("bienServicio", "El bien servicio es requerido");
		if (!isValid(solicitudCaptured.getAdministrativo()))
			erros.put("administrativo", "El personal administrativo no ha sido seleccionado");
		erros.forEach((k, v) -> this.showError(k, v));
		return erros.isEmpty();
	}

	private Boolean datosIdentificacionTramitacionSolcitudValidos() {
		Map<String, String> erros = new HashMap<String, String>();
		if (!isValid(solicitudCaptured.getProgramaGobierno()))
			erros.put("programaGobierno", "El programa gobierno es requerido");
		if (!isValid(solicitudCaptured.getProgramado()))
			erros.put("programado", "EL programado es requerido");
		if (!isValid(solicitudCaptured.getGiro()))
			erros.put("giro", "El giro es requerido");
		if (subgirosSelected.isEmpty())
			erros.put("subgiro", "Debe de seleccionar almenos un sub-giro comercial");
		if (!isValid(solicitudCaptured.getTramite()))
			erros.put("tramite", "El tramite es requerido");
		if (!isValid(solicitudCaptured.getOficioJustificacion()))
			erros.put("oficioJustificacion", "El oficio justificacion es requerido");
		if (!isValid(solicitudCaptured.getDictamenUnico()))
			erros.put("dictamenUnico", "El dictamen unico es requerido");
		erros.forEach((k, v) -> this.showError(k, v));
		return erros.isEmpty();
	}

	private Boolean datosAfectacionPresupuestalSolcitudValidos() {
		Map<String, String> erros = new HashMap<String, String>();
		if (!isValid(solicitudCaptured.getTipoGasto()))
			erros.put("tipoGasto", "El tipo gasto es requerido");
		if (!isValid(solicitudCaptured.getOrigenRecurso()))
			erros.put("origenRecurso", "El origen recurso es requerido");
		if (solicitudCaptured.getAnticipo().compareTo(solicitudCaptured.getMontoComprometido()) > 0)
			erros.put("oficioCertificacion", "El anticipo no puede ser mayor al monto comprometido");
		if (!isValid(solicitudCaptured.getOficioCertificacion()))
			erros.put("oficioCertificacion", "El oficio certificacion es requerido");
		if (!isValid(solicitudCaptured.getFormaPago()))
			erros.put("formaPago", "La forma pago es requerida");
		if (!isValid(solicitudCaptured.getNoOficioAutorizacion()))
			erros.put("noOficioAutorizacion", "El no oficio autorizacion es requerido");
		erros.forEach((k, v) -> this.showError(k, v));
		return erros.isEmpty();
	}

	private Boolean datosRecepcionSolcitudValidos() {
		Map<String, String> erros = new HashMap<String, String>();
		if (!isValid(solicitudCaptured.getFechaSuministro()))
			erros.put("fechaSuministro", "La fecha suministro es requerida");
		if (!isValid(solicitudCaptured.getNombreReceptor()))
			erros.put("nombreReceptor", "El nombre receptor es requerido");
		if (!isValid(solicitudCaptured.getCargoReceptor()))
			erros.put("cargoReceptor", "El cargo receptor es requerido");
		if (!isValidHora(solicitudCaptured.getHorarioDe()))
			erros.put("horarioDe", "La hora de inicio no cumple con el formato HH:MM(am/pm)");
		if (!isValidHora(solicitudCaptured.getHorarioA()))
			erros.put("horarioA", "La hora de fin no cumple con el formato HH:MM(am/pm)");
		if (!isValid(solicitudCaptured.getLugarEntrega()))
			erros.put("lugarEntrega", "El lugar entrega es requerido");
		erros.forEach((k, v) -> this.showError(k, v));
		return erros.isEmpty();
	}

	private Boolean isValid(Integer valor) {
		return valor == null ? false : (valor <= 0 ? false : true);
	}

	private Boolean isValid(BigDecimal valor) {
		return valor == null ? false : (valor.compareTo(BigDecimal.ZERO) <= 0 ? false : true);
	}

	private Boolean isValid(String valor) {
		return valor == null ? false : (valor.equals("") ? false : true);
	}

	private Boolean isValid(Date valor) {
		return valor == null ? false : true;
	}

	private <T extends AbstractEntity> Boolean isValid(T valor) {
		return valor == null ? false : true;
	}

	private Boolean isValidHora(String value) {
		return Regex.isValid(Regex.HORA, value);
	}

	public Date getMaxDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 1);
		return c.getTime();
	}

	public Map<String, Object> getParametersReports() {

		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		// Crea una obejto de imagen para poder mostrar la imagen en el reporte
		firmaSolicitudDetalleRepository.getFirmasByAdquisicionId(solicitudCaptured.getId()).forEach(f -> {
			try {
				paramsReport.put("firma" + f.getPosicion(),
						ImageIO.read(new ByteArrayInputStream(f.getFirma().getArchivo())));
				paramsReport.put("nombreFirma" + f.getPosicion(), f.getFirma().getUsuario().getUsuario());
				if (f.getPosicion() > 4) {
					paramsReport.put("fechaFirma" + f.getPosicion(), f.getFirmadoEl());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		Long numeroProgramas = adquisicionProgramaRepository.countByAdquisicion(solicitudCaptured);
		paramsReport.put("printProgramas", numeroProgramas > 1);
		if (numeroProgramas > 1) {
			paramsReport.put("programa", "SE ANEXA RELACIÓN");
		} else {
			List<TcAdquisicionPrograma> programas = adquisicionProgramaRepository
					.findAllByAdquisicion(solicitudCaptured);
			if (!programas.isEmpty()) {
				TcAdquisicionPrograma programa = programas.get(0);
				paramsReport.put("programa",
						"126" + programa.getCuenta().getCuenta() + " " + programa.getMuninep().getCampo7() + " "
								+ programa.getCatdep().getClvdep() + " " + programa.getNatgas().getClvgas());
			}
		}
		paramsReport.put("footerGem", "/img/footer_gem.png");
		paramsReport.put("imagePath1", conctb.getImagePathLeft());
		paramsReport.put("imagePath2", conctb.getImagePathRigth());
		paramsReport.put("idAdquisicion", solicitudCaptured.getId());
		return paramsReport;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		return null;
	}

	public void displayInfoMessage(String str) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", str);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * Listener de la tabla de usarios para asignar un revisor a la solicitud actual
	 */
	public void onDlClickTbUsers(final SelectEvent event) {
		TcUsuario user = (TcUsuario) event.getObject();
		try {
			String msg = "Se ha asignado de revisor ha: " + user.getNombre();
			solicitudCaptured.setRevisor(user.getUsuario());
			solicitudCaptured = adquisicionRepository.save(solicitudCaptured);
			solicitudesList = solicitudesList.stream()
					.map(s -> s.getId() == solicitudCaptured.getId() ? solicitudCaptured : s)
					.collect(Collectors.toList());
			displayInfoMessage(msg);
		} catch (Exception e) {
			e.printStackTrace();
			displayInfoMessage("Ha ocurrido un error");
		}

	}

	/** Instancia por defulth de un articlo */
	private TcArticulosSA getDefaulthArticulo() {
		TcArticulosSA defaulth = new TcArticulosSA();
		defaulth.setDescripcion("");
		if (!this.unidadesMedida.isEmpty()) {
			defaulth.setUnidad(this.unidadesMedida.get(0));
		}
		defaulth.setCantidad(0);
		defaulth.setPrecio(BigDecimal.ZERO);
		return defaulth;
	}

	public BigDecimal getTotalArticulos() {
		BigDecimal total = BigDecimal.ZERO;
		for (TcArticulosSA a : articulosBySA) {
			total = total.add(a.getSubTotal());
		}
		return total;
	}

	/**
	 * Obtiene los articulos de la solicitud actual y se los asigna a la lista
	 * principal de articulos para su edicion <br>
	 * Cambia el panel visible al de articulos
	 */
	public void showArticulos() {
		this.panelRendered = 2;
		articulosBySA = articulosRepository.findAllByAdquisicion(solicitudCaptured);
	}

	/**
	 * Obtiene los articulos de la solicitud actual y se los asigna a la lista y
	 * cambia el panel de preview de solicitud
	 * 
	 */
	public void verDetalles() {
		articulosBySA = articulosRepository.findAllByAdquisicion(solicitudCaptured);
		modificaionBySA = modificacionSolicitudRepository.findAllByAdquisicion(solicitudCaptured);
		getSubgirosByAdquisicionSelected();
		getSubgiros();
		getCodigosByAdquisicion();
		articulosBySA.forEach(a -> {
			montoComprometidoBySolicitud = montoComprometidoBySolicitud.add(a.getPrecio());
		});
		this.panelRendered = 6;
	}

	/**
	 * Crea las firmas en el directorio temporal para los detalles que tengan una
	 * firma asignada
	 */
	private void createPathImgs() {
		firmasBySA.stream().filter(f -> f.getFirma() != null).map(f -> f.getFirma())
				.forEach(f -> ImagesFirmasDigitales.createPathTempFromImg(f.getNombreArchivo(), f.getArchivo()));
	}

	/**
	 * Optiene las firmas de revision y se las asigna a el array de firmas <br>
	 * Cambia el panel visible al de firmas de revision
	 */
	public void showFirmasRevision() {
		this.panelRendered = 3;
		firmasBySA = firmaSolicitudDetalleRepository.getFirmasRevisionByAdquisicionId(solicitudCaptured.getId());
		// Si no se han agregado firmas crea las 4 firmas de revision para poder
		// renderizar la vista
		if (firmasBySA.isEmpty()) {
			for (int i = 1; i < 5; i++) {
				firmasBySA.add(new TcFirmaSolicitudDetalle(i, null, solicitudCaptured));
			}
			firmasBySA = (List<TcFirmaSolicitudDetalle>) firmaSolicitudDetalleRepository.saveAll(firmasBySA);
		}
		createPathImgs();
	}

	/**
	 * Optiene las firmas de recepcion y se las asigna a el array de firmas <br>
	 * Cambia el panel visible al de firmas de recepcion
	 */
	public void showFirmasRecepcion() {
		this.panelRendered = 4;
		firmasBySA = firmaSolicitudDetalleRepository.getFirmasRecepcionByAdquisicionId(solicitudCaptured.getId());
		// Si no se han agregado firmas crea las 4 firmas de revision para poder
		// renderizar la vista
		if (firmasBySA.isEmpty()) {
			for (int i = 5; i < 8; i++) {
				firmasBySA.add(new TcFirmaSolicitudDetalle(i, null, solicitudCaptured));
			}
			firmasBySA = (List<TcFirmaSolicitudDetalle>) firmaSolicitudDetalleRepository.saveAll(firmasBySA);
		}
		createPathImgs();
	}

	/**
	 * Regresa el panel visible a solicitudes eliminar las imagenes de formas en el
	 * directorio temporal y reinicia el numero de step actual
	 */
	public void backSolicitudes() {
		if (panelRendered > 2)
			ImagesFirmasDigitales.cleanTmp();

		if (!subgirosList.isEmpty()) {
			subgirosList = new ArrayList<TcSubgiro>();
		}
		this.step = 0;
		if (modificaionSolicitud.getId() != null) {
			panelRendered = 6;
			modificaionSolicitud = new ModificaionSolicitud();
		} else {
			panelRendered = 5;
		}

	}

	/** cabia el step activo */
	public void changeStep(Integer number) {
		guardar();
		this.step = number;
	}

	public void nextStep() {
		guardar();
		if (continuarCapturaSolicitud) {
			this.step = this.step == 3 ? 3 : step + 1;
		}
	}

	public void prevStep() {
		guardar();
		if (continuarCapturaSolicitud) {
			this.step = this.step == 0 ? 0 : step - 1;
		}
	}

	/** Valida un articulo */
	private Boolean isValidArticulos(TcArticulosSA articulo) {
		List<String> erros = new ArrayList<String>();
		if (articulo == null)
			return false;
		if (!isValid(articulo.getDescripcion()))
			erros.add("La descripcion del articulo es requerida");
		if (!isValid(articulo.getCantidad()))
			erros.add("La cantidad es requerida y no puede ser 0");
		if (!isValid(articulo.getPrecio()))
			erros.add("El precio es requerido y no puede ser 0");
		if (articulo.getUnidad() == null)
			erros.add("La unidad de medida es requerida");
		if (!isValid(articulo.getClave()))
			erros.add("La clave del articulo es requerida");
		erros.forEach(this::displayInfoMessage);
		return erros.isEmpty();
	}

	/** Guarda un articulo */
	public void guardarArticulos() {
		if (isValidArticulos(this.articuloAdd)) {
			try {
				boolean modificado = articuloAdd.getId() != null && articuloAdd.getId() > 0;
				articuloAdd.setAdquisicion(solicitudCaptured);
				articuloAdd.setRenglon(articulosRepository.getNextRenglon(solicitudCaptured.getId()).orElse(1));
				articulosRepository.save(articuloAdd);
				articulosBySA = articulosRepository.findAllByAdquisicion(solicitudCaptured);
				showError(null, "Se ha " + (!modificado ? "agregado" : "modificado ") + " correctamente el articulo");
				articuloAdd = getDefaulthArticulo();
			} catch (Exception e) {
				e.printStackTrace();
				showError(null, "Ha ocurrrido un error al guardar el articulo");
			}
		}
	}

	/** Elimina un artiuclo */
	public void deleteArticulo() {
		if (articuloDelete != null) {
			try {
				articulosBySA.remove(articuloDelete);
				articuloDelete.setAdquisicion(null);
				articulosRepository.delete(articuloDelete);
				showError(null, "El articulo se ha eliminado correctamnte");
			} catch (Exception e) {
				e.printStackTrace();
				showError(null, "Ha ocurrido un error eliminando el articulo");
			}
		}
	}

	public void resetArticulo() {
		if (articuloAdd.getId() != null) {
			displayInfoMessage("Se cancelo la edicion");
		}
		articuloAdd = getDefaulthArticulo();

	}

	private String typeFileToRead = "excel";
	private String nombreAnexo = "ANEXO TECNICO";

	/** Lee el ecxel cargado con los detalles de los articulos */
	public void cargaEcxelArticulos() {
		Integer filaInicial = 0;
		InputStream inp;
		List<TcArticulosSA> articulosACargar = new ArrayList<TcArticulosSA>();
		try {
			if (file == null) {
				displayInfoMessage("Seleccione archivo");
			} else {
				if (file.getSize() == 0) {
					displayInfoMessage("EL archivo no puede ser vacio ");
				} else {
					if (!file.getFileName().contains(".xls")) {
						displayInfoMessage("Solo se permite archivos de ecxel");
					} else {
						inp = file.getInputStream();
						Workbook wb = WorkbookFactory.create(inp);
						if (validarEcxel(wb)) {
							Sheet sheet = wb.getSheetAt(0);
							Row row = sheet.getRow(filaInicial);
							while (row != null) {
								TcArticulosSA articulo = new TcArticulosSA();
								articulo.setRenglon((int) row.getCell(columnas[0] - 1).getNumericCellValue());
								articulo.setDescripcion(row.getCell(columnas[1] - 1).getStringCellValue());
								articulo.setDescripcionTecnica(row.getCell(columnas[2] - 1).getStringCellValue());
								String textoUnidad = row.getCell(columnas[3] - 1).getStringCellValue();
								TcUnidadMedida unidadDelRenglon = buscarUnidadPorTexto(textoUnidad);
								if (unidadDelRenglon != null) {
									articulo.setUnidad(unidadDelRenglon);
								} else {
									// No deberia pasar: validarEcxel() ya lo reviso antes de llegar aqui
									articulo.setUnidadMedida(textoUnidad);
								}
								articulo.setCantidad((int) row.getCell(columnas[4] - 1).getNumericCellValue());
								articulo.setPrecio(new BigDecimal(row.getCell(columnas[5] - 1).getNumericCellValue()));
								articulosACargar.add(articulo);
								filaInicial++;
								row = sheet.getRow(filaInicial);
							}
							try {
								List<String> erros = new ArrayList<String>();
								articulosACargar.forEach(a -> {
									a.setAdquisicion(solicitudCaptured);
								});
								if (remplazar) {
									articulosRepository
											.deleteAll(articulosRepository.findAllByAdquisicion(solicitudCaptured));
								} else {
									articulosACargar.forEach(a -> {
										TcArticulosSA articuloBase = articulosRepository
												.findOneByRenglon(a.getRenglon());
										if (articuloBase != null) {
											erros.add("El renglon: " + a.getRenglon() + " ya existe ");
										}
									});
								}
								if (!erros.isEmpty()) {
									erros.forEach(this::displayInfoMessage);
								} else {
									articulosRepository.saveAll(articulosACargar);
									displayInfoMessage(
											"Se han cargado: " + articulosACargar.size() + " articulos a la solicitud: "
													+ solicitudCaptured.getNumeroControl() + " correctamente");
								}
								articulosBySA = articulosRepository.findAllByAdquisicion(solicitudCaptured);
							} catch (Exception e) {
								e.printStackTrace();
								displayInfoMessage("Ha ocurrido un errror al realizar la carga ");
							}
						} else {
							displayInfoMessage("El archivo contiene errores de favor de validar");
						}
						inp.close();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Boolean validarEcxel(Workbook wb) {
		String coma = ",";
		if (columnasAleerPorUsuario == null || columnasAleerPorUsuario.equals("")) {
			columnas = COLUMNAS_A_LEER_ECXEL;
		} else {
			int posicion, contador = 0;
			posicion = columnasAleerPorUsuario.indexOf(coma);
			while (posicion != -1) {
				contador++;
				posicion = columnasAleerPorUsuario.indexOf(coma, posicion + 1);
			}
			if (contador == 5) {
				if (Regex.isValid(Regex.NUMEROS_COMAS, columnasAleerPorUsuario)) {
					String cols[] = columnasAleerPorUsuario.split(coma);
					if (cols.length == 6) {
						columnas = new Integer[cols.length];
						for (int i = 0; i < cols.length; i++) {
							columnas[i] = Integer.parseInt(cols[i]);
						}
					} else {
						displayInfoMessage("Debe de indicar 6 columnas a leer");
						return false;
					}
				} else {
					displayInfoMessage("Solo de debe de introducir numero y comas");
					return false;
				}
			} else {
				displayInfoMessage("Debe de saparar las columnas a leer por comas y solo son requeridas 6 columnas");
				return false;
			}
		}
		if (columnas != null) {
			// Apartir de que fila iniciara a validar los datos evitando los encabezados si
			// llegasen a existir
			Integer filaInicial = contieneEncabezados ? 1 : 0;
			// Obtiene la primer oja del excel
			Sheet sheet = wb.getSheetAt(0);
			// Optiene la primera fila
			Row row = sheet.getRow(filaInicial);
			List<String> mensajesSalida = new ArrayList<String>();
			while (row != null) {
				String msnError = "";
				// Valida el secuencial de la lista
				if (!validTypeCell(row.getCell(columnas[0] - 1), CellType.NUMERIC)) {
					msnError += "col: " + columnas[0] + " Secuencial debe de ser numerico, ";
				}
				// Valida el nombre del articulo
				if (!validTypeCell(row.getCell(columnas[1] - 1), CellType.STRING)) {
					msnError += "col: " + columnas[1] + " El nombre debe de alfanuerico, ";
				}
				// Valida la descripcion
				if (!validTypeCell(row.getCell(columnas[2] - 1), CellType.STRING)) {
					msnError += "col: " + columnas[2] + " La descripción tecnica debe de ser alfanumerica, ";
				}
				// Valida la unidad de medida
				if (!validTypeCell(row.getCell(columnas[3] - 1), CellType.STRING)) {
					msnError += "col: " + columnas[3] + " La unidad de medida debe de ser alfanumerica";
				} else {
					if (buscarUnidadPorTexto(row.getCell(columnas[3] - 1).getStringCellValue()) == null) {
						msnError += "col: " + columnas[3]
								+ " La unidad de medida no se encontro por favor valique que se correcta o capturela si no existe";
					}
				}
				// Valida el precio
				if (!validTypeCell(row.getCell(columnas[4] - 1), CellType.NUMERIC)) {
					msnError += "col: " + columnas[4] + " El precio debe de ser numerico";
				}
				// Valida la cantidad
				if (!validTypeCell(row.getCell(columnas[5] - 1), CellType.NUMERIC)) {
					msnError += "col: " + columnas[5] + " La cantidad debe de ser numerica";
				}
				if (!msnError.equals("")) {
					msnError = "Errores en fila " + filaInicial + " " + msnError;
					mensajesSalida.add(msnError);
				}
				filaInicial++;
				row = sheet.getRow(filaInicial);
			}
			if (mensajesSalida.isEmpty()) {
				return true;
			} else {
				createAndDownLandErrors(mensajesSalida);

				mensajesSalida.forEach(System.out::println);
				return false;
			}
		} else {
			displayInfoMessage("Por favor valide que ha indicado correctamente las columnas a leer");
			return false;
		}
	}

	public void createAndDownLandErrors(List<String> errors) {
		try {
			File file = new File("/gem/errores/erroresArchivosEcxel.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			errors.forEach(e -> {
				try {
					bw.write(e + "\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			bw.close();
			fileErrors = StreamedContents.of(new FileInputStream(file), "text/csv", file.getName(), null);
			PrimeFaces.current().executeScript(DOWNLOAD_TXT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Boolean validTypeCell(Cell cell, CellType type) {
		if (cell == null) {
			return false;
		} else {
			if (cell.getCellType().equals(type)) {
				if (cell.getCellType() == CellType.BLANK || CellType.ERROR == cell.getCellType()) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		}
	}

	public void showCargaAutomatica() {
		System.out.println(cargaArticulosManual);
		this.cargaArticulosManual = !this.cargaArticulosManual;
	}

	/** Guarda la firma actual y crea el path de la imagen */
	public void guardarFirma(final SelectEvent event) {
		TcFirmaDigital selected = (TcFirmaDigital) event.getObject();
		try {
			TcFirmaSolicitudDetalle firmaActual = firmasBySA.stream().filter(f -> f.getPosicion() == firmaPosicion)
					.findFirst().orElse(null);
			firmaActual.setFirma(selected);
			TcFirmaSolicitudDetalle nuevaFirma = firmaSolicitudDetalleRepository.save(firmaActual);
			firmasBySA = firmasBySA.stream().map(f -> f.getPosicion() == firmaPosicion ? nuevaFirma : f)
					.collect(Collectors.toList());
			ImagesFirmasDigitales.createPathTempFromImg(selected.getNombreArchivo(), selected.getArchivo());
			displayInfoMessage("Se ha agregado a: " + selected.getUsuario().getUsuario() + " en la firma numero: "
					+ firmaPosicion);
		} catch (Exception e) {
			e.printStackTrace();
			displayInfoMessage("Ha ocurrrido un error agregando la firma a la solicitud");
		}

	}

	/** Elimina la firma asosiada de la solicitud actual */
	public void deleteFirma() {
		if (firmaPosicion != null) {
			try {
				firmasBySA = firmasBySA.stream().map(f -> {
					if (f.getPosicion() == firmaPosicion) {
						f.setFirma(null);
						f.setFirmadoEl(null);
						f.setEstadoSinFirmar();
						firmaSolicitudDetalleRepository.save(f);
					}
					return f;
				}).collect(Collectors.toList());
				displayInfoMessage("La firma se desasocio de la solicitud");
			} catch (Exception e) {
				e.printStackTrace();
				displayInfoMessage("Ha ocurrido un error desasociando la firma de la socilitud");
			}
		}
	}

	/**
	 * Cambia el estado a firmado para la firma que coincida a la posicion actual
	 */
	public void firmarSolicitud() {
		firmasBySA = firmasBySA.stream().map(f -> {
			if (f.getPosicion() == firmaPosicion) {
				f.setEstadoFirmado();
				f.setFirmadoEl(new Date());
				f = firmaSolicitudDetalleRepository.save(f);
				displayInfoMessage("la solicitud se ha firmado correctamente");
			}
			return f;
		}).collect(Collectors.toList());
	}

	public Boolean getShowObs() {
		return getCurrentUserIsRevisor() ? true : false;
	}

	// metodos de control de giros y subgiros
	public void onSelectGiro() {
		try {
			if (solicitudCaptured.getGiro() != null) {
				subgirosList = subgiroRepository.findAllByGiro(solicitudCaptured.getGiro());
				if (subgirosList.isEmpty()) {
					displayInfoMessage("No se ha asignado ningun subg-giro comercial a el giro comercial "
							+ solicitudCaptured.getGiro().getClvgas());
				}
			} else {
				subgirosList = new ArrayList<TcSubgiro>();
			}
		} catch (Exception e) {
			displayInfoMessage("No se ha podido buscar subgiros comerciales ");
			e.printStackTrace();
		}
	}

	/**
	 * Se ejecuta al seleccionar o desseleccionar cualquier item Toma la lista de
	 * todos los items seleccionados
	 */
	public void onSelectSubGiro(AjaxBehaviorEvent event) {
		try {
			@SuppressWarnings("unchecked")
			List<TcSubgiro> subgiro = (List<TcSubgiro>) event.getComponent().getAttributes().get("value");
			subgirosSelected = subgiro;
			setSubgirosSelectedToSolcitudCapture();
		} catch (Exception e) {
			displayInfoMessage("No se ha podido agregar sub-giro comercial");
			e.printStackTrace();
		}
	}

	/** Se ejecuta al selecionar la opcions de todos todos los items */
	public void onUnSelectSubGiro(ToggleSelectEvent event) {
		try {
			if (event.isSelected()) {
				subgirosSelected = subgirosList;
			} else {
				subgirosSelected = new ArrayList<TcSubgiro>();
			}
			setSubgirosSelectedToSolcitudCapture();
		} catch (Exception e) {
			displayInfoMessage("No se ha podido eliminar sub-giro ");
			e.printStackTrace();
		}
	}

	/**
	 * Consulta todos los subgiros, los elimina , crea una lista con la instancia de
	 * la clase de relacion, guarda todos los subgiros
	 */
	private void setSubgirosSelectedToSolcitudCapture() {
		try {
			List<TcAdquisicionGiroSubgiro> subgiros = giroSubgiroRepository.findAllByAdquisicion(solicitudCaptured);
			if (!subgiros.isEmpty()) {
				giroSubgiroRepository.deleteAll(subgiros);
			}
			for (TcSubgiro subgiro : subgirosSelected) {
				giroSubgiroRepository.save(new TcAdquisicionGiroSubgiro(subgiro, solicitudCaptured));
			}
		} catch (Exception e) {
			e.printStackTrace();
			displayInfoMessage("Ha ocurrido un error al guardar los sub-giros seleccionados");
		}

	}

	private void getSubgirosByAdquisicionSelected() {
		subgirosSelected = giroSubgiroRepository.findAllByAdquisicion(solicitudCaptured).stream()
				.map(ga -> ga.getSubgiro()).collect(Collectors.toList());
	}

	private void getSubgiros() {
		if (solicitudCaptured != null && solicitudCaptured.getGiro() != null) {
			subgirosList = subgiroRepository.findAllByGiro(solicitudCaptured.getGiro());
		}
	}

	// Metodos para el manejo de codigos programaticos

	/** Obtienes los programas partirde de la adquisicion seleccionada */
	private void getCodigosByAdquisicion() {
		codigosBySA = adquisicionProgramaRepository.findAllByAdquisicion(solicitudCaptured);
	}

	public void guardarCodigo() {
		if (codigosCaptured != null) {
			if (isValid(codigosCaptured.getMonto())) {
				List<TcAdquisicionPrograma> programasExistentes = adquisicionProgramaRepository
						.findAllByAdquisicionAndCuentaAndCatdepAndMuninepAndNatgasAndFuentef(solicitudCaptured,
								codigosCaptured.getCuenta(), codigosCaptured.getCatdep(), codigosCaptured.getMuninep(),
								codigosCaptured.getNatgas(), codigosCaptured.getFuentefGobierno());
				if (programasExistentes == null || programasExistentes.isEmpty()) {
					codigosCaptured.setAdquisicion(solicitudCaptured);

					codigosCaptured = adquisicionProgramaRepository.save(codigosCaptured);
					codigosBySA.add(codigosCaptured);
					displayInfoMessage("Codigo agregado correctamnte");

				} else
					displayInfoMessage("El codigo ya fue previamente agregado");
			} else
				displayInfoMessage("El monto no puede ser 0");
		}
	}

	public void deleteCodigoProgramatico() {
		if (codigoIdToDelete != null && codigoIdToDelete > 0) {
			adquisicionProgramaRepository.deleteById(codigoIdToDelete);
			codigosBySA = codigosBySA.stream().filter(c -> !c.getId().equals(codigoIdToDelete))
					.collect(Collectors.toList());
			displayInfoMessage("El codigo se ha eliminado correctamente");
		}
	}

	public BigDecimal getTotaProgramas() {
		BigDecimal total = BigDecimal.ZERO;
		for (TcAdquisicionPrograma a : codigosBySA) {
			total = total.add(a.getMonto());
		}
		return total;
	}

	// metodos para le manejo de la modifacion de la solicitud (solo sucuede cuando
	// la solicitud ya tiene un contrato asignado )

	public void showModificaionSolicitud() {
		panelRendered = 7;
	}

	/**
	 * Carga el archivo y lo mantiene de forma temporal dentro de la clase de
	 * moficacion ha espera de la confirmacion
	 */
	public void subirSolicitudModidificacion(FileUploadEvent event) {
		if (event.getFile() == null)
			displayInfoMessage("No se ha podido cargar ala rchivo contacte a su administrador");
		else {
			if (event.getFile().getContent().length == 0)
				displayInfoMessage("El archivo no puede ser vacio");
			else {
				String name = event.getFile().getFileName();
				String type = name.substring(name.lastIndexOf('.'), name.length());
				if (!permitedTypes.contains(type))
					displayInfoMessage("EL tipo de archivo no es permitido");
				else {
					modificaionSolicitud = new ModificaionSolicitud(solicitudCaptured, event.getFile().getContent(),
							name, type, new Date(), this.getUsername());
					displayInfoMessage("Cargado correctamente");
				}
			}
		}
	}

	/**
	 * Guarda el archivo ,resetea todas las firmas y las vuelve a solicitar terminod
	 * cambia la captura del articulo
	 */
	public void aceptarModificaion() {
		try {
			modificaionSolicitud = modificacionSolicitudRepository.save(modificaionSolicitud);
			firmasBySA = firmaSolicitudDetalleRepository.getFirmasByAdquisicionId(solicitudCaptured.getId());
			firmasBySA = firmasBySA.stream().map(a -> {

				return a;
			}).collect(Collectors.toList());
			modificaionBySA.add(modificaionSolicitud);
			panelRendered = 1;
		} catch (Exception e) {
			displayInfoMessage("Ha ocurrido un error contacte a su administrador");
		}

	}

	public void getviewPdf(Long idModificacion) {
		id = UUID.randomUUID().toString() + "_";
		ModificaionSolicitud tmpModificaion = modificaionBySA.stream().filter(m -> m.getId().equals(idModificacion))
				.findFirst().get();
		name = tmpModificaion.getNombreArchivo();
		type = tmpModificaion.getTipoArchivo();
		OutputStream out;
		try {
			out = new FileOutputStream(GetFileInlineServlet.getFullPath(id, name));
			out.write(tmpModificaion.getArchivo());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Integer getPanelRendered() {
		return panelRendered;
	}

	public void setPanelRendered(Integer panelRendered) {
		this.panelRendered = panelRendered;
	}

	public TcAdquisicion getSolicitudCaptured() {
		return solicitudCaptured;
	}

	public void setSolicitudCaptured(TcAdquisicion solicitudCaptured) {
		this.solicitudCaptured = solicitudCaptured;
	}

	public Conctb getConctb() {
		return conctb;
	}

	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	public List<Integer> getListMes() {
		return listMes;
	}

	public void setListMes(List<Integer> listMes) {
		this.listMes = listMes;
	}

	public List<TcAdquisicion> getSolicitudesList() {
		return solicitudesList;
	}

	public void setSolicitudesList(List<TcAdquisicion> solicitudesList) {
		this.solicitudesList = solicitudesList;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public TcPeriodoRepositoy getTcPeriodoRepositoy() {
		return tcPeriodoRepositoy;
	}

	public void setTcPeriodoRepositoy(TcPeriodoRepositoy tcPeriodoRepositoy) {
		this.tcPeriodoRepositoy = tcPeriodoRepositoy;
	}

	public TcAdquisicionRepository getAdquisicionRepository() {
		return adquisicionRepository;
	}

	public void setAdquisicionRepository(TcAdquisicionRepository adquisicionRepository) {
		this.adquisicionRepository = adquisicionRepository;
	}

	public List<TcPersonalAdministrativo> getPersonalList() {
		return personalList;
	}

	public void setPersonalList(List<TcPersonalAdministrativo> personalList) {
		this.personalList = personalList;
	}

	public List<TcTramite> getTramitesList() {
		return tramitesList;
	}

	public void setTramitesList(List<TcTramite> tramitesList) {
		this.tramitesList = tramitesList;
	}

	public List<TcTipoGasto> getTiposGastoList() {
		return tiposGastoList;
	}

	public void setTiposGastoList(List<TcTipoGasto> tiposGastoList) {
		this.tiposGastoList = tiposGastoList;
	}

	public List<TcOrigenRecurso> getRecursosList() {
		return recursosList;
	}

	public void setRecursosList(List<TcOrigenRecurso> recursosList) {
		this.recursosList = recursosList;
	}

	public List<TcTiposPago> getFormasPagoList() {
		return formasPagoList;
	}

	public void setFormasPagoList(List<TcTiposPago> formasPagoList) {
		this.formasPagoList = formasPagoList;
	}

	public List<TcBienServicio> getBienesList() {
		return bienesList;
	}

	public void setBienesList(List<TcBienServicio> bienesList) {
		this.bienesList = bienesList;
	}

	public TcTramitesRepository getTramitesRepository() {
		return tramitesRepository;
	}

	public void setTramitesRepository(TcTramitesRepository tramitesRepository) {
		this.tramitesRepository = tramitesRepository;
	}

	public TcAreaAdministrativaRepository getAdministrativaRepository() {
		return administrativaRepository;
	}

	public void setAdministrativaRepository(TcAreaAdministrativaRepository administrativaRepository) {
		this.administrativaRepository = administrativaRepository;
	}

	public TcTiposGastoRepository getTiposGastoRepository() {
		return tiposGastoRepository;
	}

	public void setTiposGastoRepository(TcTiposGastoRepository tiposGastoRepository) {
		this.tiposGastoRepository = tiposGastoRepository;
	}

	public TcOrigenRecursoRepository getOrigenRecursoRepository() {
		return origenRecursoRepository;
	}

	public void setOrigenRecursoRepository(TcOrigenRecursoRepository origenRecursoRepository) {
		this.origenRecursoRepository = origenRecursoRepository;
	}

	public TcTiposPagoRepository getFormasPagoRepository() {
		return formasPagoRepository;
	}

	public void setFormasPagoRepository(TcTiposPagoRepository formasPagoRepository) {
		this.formasPagoRepository = formasPagoRepository;
	}

	public TcBienServicioRepository getBienServicioRepository() {
		return bienServicioRepository;
	}

	public void setBienServicioRepository(TcBienServicioRepository bienServicioRepository) {
		this.bienServicioRepository = bienServicioRepository;
	}

	public List<TcUsuario> getUsuariosList() {
		return usuariosList;
	}

	public void setUsuariosList(List<TcUsuario> usuariosList) {
		this.usuariosList = usuariosList;
	}

	public TcUsuarioRepository getTcUsuarioRepository() {
		return tcUsuarioRepository;
	}

	public void setTcUsuarioRepository(TcUsuarioRepository tcUsuarioRepository) {
		this.tcUsuarioRepository = tcUsuarioRepository;
	}

	public List<TcArticulosSA> getArticulosBySA() {
		return articulosBySA;
	}

	public void setArticulosBySA(List<TcArticulosSA> articulosBySA) {
		this.articulosBySA = articulosBySA;
	}

	public TcArticulosSARepository getArticulosRepository() {
		return articulosRepository;
	}

	public void setArticulosRepository(TcArticulosSARepository articulosRepository) {
		this.articulosRepository = articulosRepository;
	}

	/**
	 * Resuelve el texto libre que trae el Excel contra el catalogo de unidades. El
	 * empate es sin distinguir mayusculas ni espacios de sobra, y acepta la
	 * descripcion, la abreviatura o la clave. Se hace en memoria porque el catalogo
	 * ya viene cargado y el archivo puede traer cientos de renglones.
	 *
	 * @return la unidad del catalogo, o null si el texto no existe
	 */
	public TcUnidadMedida buscarUnidadPorTexto(String texto) {
		if (texto == null || texto.trim().equals("")) {
			return null;
		}
		String buscado = texto.trim().toUpperCase();
		for (TcUnidadMedida unidad : this.unidadesMedida) {
			if (buscado.equals(trimUpper(unidad.getDescripcion())) || buscado.equals(trimUpper(unidad.getClave()))
					|| buscado.equals(trimUpper(unidad.getAbreviatura()))) {
				return unidad;
			}
		}
		return null;
	}

	private String trimUpper(String valor) {
		return valor == null ? "" : valor.trim().toUpperCase();
	}

	public List<TcUnidadMedida> getUnidadesMedida() {
		return unidadesMedida;
	}

	public void setUnidadesMedida(List<TcUnidadMedida> unidadesMedida) {
		this.unidadesMedida = unidadesMedida;
	}

	public TcUnidadMedidaRepository getUnidadMedidaRepository() {
		return unidadMedidaRepository;
	}

	public void setUnidadMedidaRepository(TcUnidadMedidaRepository unidadMedidaRepository) {
		this.unidadMedidaRepository = unidadMedidaRepository;
	}

	public TcArticulosSA getArticuloAdd() {
		return articuloAdd;
	}

	/**
	 * Al editar un articulo, si viene de antes del catalogo (solo con el texto de
	 * la unidad y sin llave) se intenta resolver contra el catalogo para que el
	 * combo aparezca seleccionado y no se pierda el dato al guardar.
	 */
	public void setArticuloAdd(TcArticulosSA articuloAdd) {
		if (articuloAdd != null && articuloAdd.getUnidad() == null) {
			articuloAdd.setUnidad(buscarUnidadPorTexto(articuloAdd.getUnidadMedida()));
		}
		this.articuloAdd = articuloAdd;
	}

	public TcArticulosSA getArticuloDelete() {
		return articuloDelete;
	}

	public void setArticuloDelete(TcArticulosSA articuloDelete) {
		this.articuloDelete = articuloDelete;
	}

	public List<TcEstadoContrato> getEstadoContrato() {
		return estadoContrato;
	}

	public void setEstadoContrato(List<TcEstadoContrato> estadoContrato) {
		this.estadoContrato = estadoContrato;
	}

	public TcEstadosContratoRepository getTcEstadosContratoRepository() {
		return tcEstadosContratoRepository;
	}

	public void setTcEstadosContratoRepository(TcEstadosContratoRepository tcEstadosContratoRepository) {
		this.tcEstadosContratoRepository = tcEstadosContratoRepository;
	}

	public List<TcFirmaSolicitudDetalle> getFirmasBySA() {
		return firmasBySA;
	}

	public void setFirmasBySA(List<TcFirmaSolicitudDetalle> firmasBySA) {
		this.firmasBySA = firmasBySA;
	}

	public TcFirmaSolicitudDetalleRepository getFirmaSolicitudDetalleRepository() {
		return firmaSolicitudDetalleRepository;
	}

	public void setFirmaSolicitudDetalleRepository(TcFirmaSolicitudDetalleRepository firmaSolicitudDetalleRepository) {
		this.firmaSolicitudDetalleRepository = firmaSolicitudDetalleRepository;
	}

	public Integer getFirmaPosicion() {
		return firmaPosicion;
	}

	public void setFirmaPosicion(Integer firmaPosicion) {
		System.out.println("posicion: " + firmaPosicion);
		this.firmaPosicion = firmaPosicion;
	}

	public List<TcFirmaDigital> getFirmasList() {
		return firmasList;
	}

	public void setFirmasList(List<TcFirmaDigital> firmasList) {
		this.firmasList = firmasList;
	}

	public TcFirmaDigitalRepository getFirmaDigitalRepository() {
		return firmaDigitalRepository;
	}

	public void setFirmaDigitalRepository(TcFirmaDigitalRepository firmaDigitalRepository) {
		this.firmaDigitalRepository = firmaDigitalRepository;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public String getBusquedaSolicitudes() {
		return busquedaSolicitudes;
	}

	public void setBusquedaSolicitudes(String busquedaSolicitudes) {
		this.busquedaSolicitudes = busquedaSolicitudes;
	}

	public TcPersonalAdministrativoRepository getPersonalAdministrativoRepository() {
		return personalAdministrativoRepository;
	}

	public void setPersonalAdministrativoRepository(
			TcPersonalAdministrativoRepository personalAdministrativoRepository) {
		this.personalAdministrativoRepository = personalAdministrativoRepository;
	}

	public List<TcSubgiro> getSubgirosList() {
		return subgirosList;
	}

	public void setSubgirosList(List<TcSubgiro> subgirosList) {
		this.subgirosList = subgirosList;
	}

	public List<Natgas> getGirosList() {
		return girosList;
	}

	public void setGirosList(List<Natgas> girosList) {
		this.girosList = girosList;
	}

	public List<TcSubgiro> getSubgirosSelected() {
		return subgirosSelected;
	}

	public void setSubgirosSelected(List<TcSubgiro> subgirosSelected) {
		this.subgirosSelected = subgirosSelected;
	}

	public NatgasRepository getNatgasRepository() {
		return natgasRepository;
	}

	public void setNatgasRepository(NatgasRepository natgasRepository) {
		this.natgasRepository = natgasRepository;
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

	public PasoRepository getPasoRepository() {
		return pasoRepository;
	}

	public void setPasoRepository(PasoRepository pasoRepository) {
		this.pasoRepository = pasoRepository;
	}

	public TcAdquisicionProgramasRepository getAdquisicionProgramaRepository() {
		return adquisicionProgramaRepository;
	}

	public void setAdquisicionProgramaRepository(TcAdquisicionProgramasRepository adquisicionProgramaRepository) {
		this.adquisicionProgramaRepository = adquisicionProgramaRepository;
	}

	public AdquisicionesService getAdquisicionesService() {
		return adquisicionesService;
	}

	public void setAdquisicionesService(AdquisicionesService adquisicionesService) {
		this.adquisicionesService = adquisicionesService;
	}

	public Boolean getObsRendered() {
		return obsRendered;
	}

	public void changePenalObs() {
		obsRendered = !obsRendered;
	}

	public void setObsRendered(Boolean obsRendered) {
		this.obsRendered = obsRendered;
	}

	public TcEstadosContratoRepository getEstadosContratoRepository() {
		return estadosContratoRepository;
	}

	public void setEstadosContratoRepository(TcEstadosContratoRepository estadosContratoRepository) {
		this.estadosContratoRepository = estadosContratoRepository;
	}

	public TcArticulosSA getArticuloView() {
		return articuloView;
	}

	public void setArticuloView(TcArticulosSA articuloView) {
		this.articuloView = articuloView;
	}

	public Boolean getCargaArticulosManual() {
		return cargaArticulosManual;
	}

	public void setCargaArticulosManual(Boolean cargaArticulosManual) {
		this.cargaArticulosManual = cargaArticulosManual;
	}

	public Boolean getContieneEncabezados() {
		return contieneEncabezados;
	}

	public void setContieneEncabezados(Boolean contieneEncabezados) {
		this.contieneEncabezados = contieneEncabezados;
	}

	public String getColumnasAleerPorUsuario() {
		return columnasAleerPorUsuario;
	}

	public void setColumnasAleerPorUsuario(String columnasAleerPorUsuario) {
		this.columnasAleerPorUsuario = columnasAleerPorUsuario;
	}

	public Boolean getRemplazar() {
		return remplazar;
	}

	public void setRemplazar(Boolean remplazar) {
		this.remplazar = remplazar;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public StreamedContent getFileErrors() {
		return fileErrors;
	}

	public void setFileErrors(StreamedContent fileErrors) {
		this.fileErrors = fileErrors;
	}

	public POIReadParseService getPoiReadParseService() {
		return poiReadParseService;
	}

	public void setPoiReadParseService(POIReadParseService poiReadParseService) {
		this.poiReadParseService = poiReadParseService;
	}

	public String getTypeFileToRead() {
		return typeFileToRead;
	}

	public void setTypeFileToRead(String typeFileToRead) {
		this.typeFileToRead = typeFileToRead;
	}

	public String getNombreAnexo() {
		return nombreAnexo;
	}

	public void setNombreAnexo(String nombreAnexo) {
		this.nombreAnexo = nombreAnexo;
	}

	public BigDecimal getMontoComprometidoBySolicitud() {
		return montoComprometidoBySolicitud;
	}

	public void setMontoComprometidoBySolicitud(BigDecimal montoComprometidoBySolicitud) {
		this.montoComprometidoBySolicitud = montoComprometidoBySolicitud;
	}

	public List<Cuenta> getCuentasList() {
		return cuentasList;
	}

	public void setCuentasList(List<Cuenta> cuentasList) {
		this.cuentasList = cuentasList;
	}

	public List<Catdep> getDependenciasList() {
		return dependenciasList;
	}

	public void setDependenciasList(List<Catdep> dependenciasList) {
		this.dependenciasList = dependenciasList;
	}

	public List<FuentefGobierno> getFuentesGobiernoList() {
		return fuentesGobiernoList;
	}

	public void setFuentesGobiernoList(List<FuentefGobierno> fuentesGobiernoList) {
		this.fuentesGobiernoList = fuentesGobiernoList;
	}

	public List<Muninep> getMuninepList() {
		return muninepList;
	}

	public void setMuninepList(List<Muninep> muninepList) {
		this.muninepList = muninepList;
	}

	public TcAdquisicionPrograma getCodigosCaptured() {
		return codigosCaptured;
	}

	public void setCodigosCaptured(TcAdquisicionPrograma codigosCaptured) {
		this.codigosCaptured = codigosCaptured;
	}

	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}

	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	public CatdepRepository getCatdepRepository() {
		return catdepRepository;
	}

	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

	public MuniNepRepository getMuniNepRepository() {
		return muniNepRepository;
	}

	public void setMuniNepRepository(MuniNepRepository muniNepRepository) {
		this.muniNepRepository = muniNepRepository;
	}

	public FuenteFGobiernoRepository getFuenteFGobiernoRepository() {
		return fuenteFGobiernoRepository;
	}

	public void setFuenteFGobiernoRepository(FuenteFGobiernoRepository fuenteFGobiernoRepository) {
		this.fuenteFGobiernoRepository = fuenteFGobiernoRepository;
	}

	public List<TcAdquisicionPrograma> getCodigosBySA() {
		return codigosBySA;
	}

	public void setCodigosBySA(List<TcAdquisicionPrograma> codigosBySA) {
		this.codigosBySA = codigosBySA;
	}

	public Long getCodigoIdToDelete() {
		return codigoIdToDelete;
	}

	public void setCodigoIdToDelete(Long codigoIdToDelete) {
		this.codigoIdToDelete = codigoIdToDelete;
	}

	public ModificaionSolicitud getModificaionSolicitud() {
		return modificaionSolicitud;
	}

	public void setModificaionSolicitud(ModificaionSolicitud modificaionSolicitud) {
		this.modificaionSolicitud = modificaionSolicitud;
	}

	public ModificacionSolicitudRepository getModificacionSolicitudRepository() {
		return modificacionSolicitudRepository;
	}

	public void setModificacionSolicitudRepository(ModificacionSolicitudRepository modificacionSolicitudRepository) {
		this.modificacionSolicitudRepository = modificacionSolicitudRepository;
	}

	public List<ModificaionSolicitud> getModificaionBySA() {
		return modificaionBySA;
	}

	public void setModificaionBySA(List<ModificaionSolicitud> modificaionBySA) {
		this.modificaionBySA = modificaionBySA;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
