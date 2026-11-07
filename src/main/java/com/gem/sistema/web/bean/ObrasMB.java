package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.gem.sistema.business.domain.Catsector;
import com.gem.sistema.business.domain.Obra;
import com.gem.sistema.business.dto.ObrasCargaAutomaticaDTO;
import com.gem.sistema.business.dto.ObrasDTO;
import com.gem.sistema.business.repository.catalogs.ObrasRepository;
import com.gem.sistema.business.repository.load.PasoRepository;
import com.gem.sistema.business.service.catalogos.ObrasService;
import com.gem.sistema.load.fileupload.ReadCSVService;
import com.gem.sistema.load.fileupload.model.FileUpload;
import com.gem.sistema.load.fileupload.validators.FileContentValidator;
import com.gem.sistema.util.UtilFront;

// TODO: Auto-generated Javadoc
/**
 * The Class ObrasMB.
 *
 * @author Sam
 */
@ManagedBean(name = "obrasMB")
@SessionScoped
public class ObrasMB extends AbstractFileUploadBudgetFlow implements Serializable {

	private static final String DEFAULT_PATH = "/tmp/";

	private static final String DEFAULT_PATH_ERRORES = "/gem/errores/";

	private static final String DEFAULT_PATH_OUTVALID = "/gem/downcsv/";
	
	private static final String DEFAULT_PATH_UPLOAD = "/gem/upfiles/";

	private boolean validFormat = Boolean.FALSE;

	/** The valid data. */
	private boolean validData = Boolean.FALSE;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ObrasMB.class);

	/** The Constant messageOk. */
	private final static String messageOk = "OK";

	/** The obras list. */
	private List<ObrasDTO> obrasList = new ArrayList<ObrasDTO>();

	/** The obras list original. */
	private List<ObrasDTO> obrasListOriginal = new ArrayList<ObrasDTO>();

	/** The obras DTO. */
	private ObrasDTO obrasDTO = new ObrasDTO();

	/** The obra buffer. */
	private Obra obraBuffer;

	/** The deshabilitado. */
	private boolean deshabilitado;

	/** The ver boton salvar. */
	private boolean verBotonSalvar;

	/** The ver boton modificar. */
	private boolean verBotonModificar;

	/** The habilitar borrar. */
	private boolean habilitarBorrar;

	/** The habilitar reset. */
	private boolean habilitarReset;

	/** The habilitar adicionar. */
	private boolean habilitarAdicionar;

	/** The habilitar cancelar. */
	private boolean habilitarCancelar;

	/** The file. */
	private UploadedFile file;

	/** The file 2. */
	private StreamedContent file2;

	/** The obra select. */
	private List<Obra> obraSelect;

	/** The adicionar bandera. */
	private boolean adicionarBandera = Boolean.TRUE;

	/** The modificar bandera. */
	private boolean modificarBandera = Boolean.FALSE;

	/** The lista. */
	List<Obra> lista = new ArrayList<Obra>();

	/** The obras repository. */
	@ManagedProperty("#{obrasRepository}")
	private ObrasRepository obrasRepository;

	/** The obras service. */
	@ManagedProperty("#{obrasService}")
	ObrasService obrasService;

	/** The csv service. */
	@ManagedProperty("#{csvService}")
	private ReadCSVService csvService;

	/** The paso repository. */
	@ManagedProperty("#{pasoRepository}")
	private PasoRepository pasoRepository;

	/** The adicionar. */
	private boolean adicionar = false;

	/** The programatica. */
	private String programatica;

	/** The num obra. */
	private String numObra;

	/** The nombre obra. */
	private String nombreObra;

	/** The dt obras. */
	private DataTable dtObras;

	/** The pass borrar. */
	private String passBorrar;

	/** The bandera. */
	private Integer bandera;

	/** The programa. */
	private String programa;

	/** The autoi 13. */
	private BigDecimal autoi13;

	@ManagedProperty("#{obrasCsvValidator}")
	private FileContentValidator obrasCsvValidator;

	/**
	 * Gets the obra select.
	 *
	 * @return the obra select
	 */
	public List<Obra> getObraSelect() {
		return obraSelect;
	}

	/**
	 * Sets the obra select.
	 *
	 * @param obraSelect the new obra select
	 */
	public void setObraSelect(List<Obra> obraSelect) {
		this.obraSelect = obraSelect;
	}

	/**
	 * Gets the obra buffer.
	 *
	 * @return the obra buffer
	 */
	public Obra getObraBuffer() {
		return obraBuffer;
	}

	/**
	 * Sets the obra buffer.
	 *
	 * @param obraBuffer the new obra buffer
	 */
	public void setObraBuffer(Obra obraBuffer) {
		this.obraBuffer = obraBuffer;
	}

	/**
	 * Gets the paso repository.
	 *
	 * @return the paso repository
	 */
	public PasoRepository getPasoRepository() {
		return pasoRepository;
	}

	/**
	 * Sets the paso repository.
	 *
	 * @param pasoRepository the new paso repository
	 */
	public void setPasoRepository(PasoRepository pasoRepository) {
		this.pasoRepository = pasoRepository;
	}

	/**
	 * Valida prog.
	 *
	 * @param row the row
	 */
	public void validaProg(Integer row) {
		Obra obra = new Obra();
		obra.setId(obrasList.get(row).getId());
		obra.setFn(obrasList.get(row).getFn());
		obra.setFun(obrasList.get(row).getFun());
		obra.setSubfun(obrasList.get(row).getSubFun());
		obra.setProg(obrasList.get(row).getProg());
		obra.setSubprog(obrasList.get(row).getSubProg());
		obra.setProy(obrasList.get(row).getProy());
		obra.setFfin(obrasList.get(row).getFfin());

		if (csvService.validaPrograma(obra) == 1) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Programa de Obra Anual no encontrado");
		}
	}

	/**
	 * Metodo que guarda una obra insert/update en su caso.
	 *
	 * @param row the row
	 */
	public void salvar(Integer row) {
		this.setVerBotonSalvar(false);
		this.setVerBotonModificar(true);
		this.setHabilitarAdicionar(false);
		this.setHabilitarBorrar(false);
		this.setHabilitarReset(true);
		this.setHabilitarCancelar(true);
		Obra obra = new Obra();
		if (lista.isEmpty() || adicionar) {
			obra.setCapturo(SecurityContextHolder.getContext().getAuthentication().getName());
			obra.setFeccap(new Date());
		}
		obra.setId(obrasList.get(row).getId());
		obra.setFn(obrasList.get(row).getFn());
		obra.setFun(obrasList.get(row).getFun());
		obra.setSubfun(obrasList.get(row).getSubFun());
		obra.setProg(obrasList.get(row).getProg());
		obra.setSubprog(obrasList.get(row).getSubProg());
		obra.setProy(obrasList.get(row).getProy());
		obra.setFfin(obrasList.get(row).getFfin());

		// VALIDAR CONTRA BASE DE DATOS
		if (obrasRepository.findAllByOrderByNcontrolDesc().isEmpty()) {
			obra.setNcontrol(1);
		} else {
			obra.setNcontrol(obrasList.get(row).getNcontrol());
		}

		obra.setNomobra(obrasList.get(row).getNomobra());
		obra.setTipoejec(obrasList.get(row).getTipoejec());
		obra.setUbicacion(obrasList.get(row).getUbicacion());
		obra.setJustificacion(obrasList.get(row).getJustificacion());
		obra.setPoblacion(obrasList.get(row).getPoblacion());
		obra.setTipoasig(obrasList.get(row).getTipoasig());

		obra.setAuto1(obrasList.get(row).getAuto1() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto1());
		obra.setAuto2(obrasList.get(row).getAuto2() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto2());
		obra.setAuto3(obrasList.get(row).getAuto3() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto3());
		obra.setAuto4(obrasList.get(row).getAuto4() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto4());
		obra.setAuto5(obrasList.get(row).getAuto5() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto5());
		obra.setAuto6(obrasList.get(row).getAuto6() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto6());
		obra.setAuto7(obrasList.get(row).getAuto7() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto7());
		obra.setAuto8(obrasList.get(row).getAuto8() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto8());
		obra.setAuto9(obrasList.get(row).getAuto9() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto9());
		obra.setAuto10(obrasList.get(row).getAuto10() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto10());
		obra.setAuto11(obrasList.get(row).getAuto11() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto11());
		obra.setAuto12(obrasList.get(row).getAuto12() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto12());
		obra.setAuto13(

				obra.getAuto1().add(obra.getAuto2()).add(obra.getAuto3()).add(obra.getAuto4()).add(obra.getAuto5())
						.add(obra.getAuto6()).add(obra.getAuto7()).add(obra.getAuto8()).add(obra.getAuto9())
						.add(obra.getAuto10()).add(obra.getAuto11()).add(obra.getAuto12()));

		obra.setToeje1(obrasList.get(row).getToeje1() == null ? BigDecimal.ZERO : obrasList.get(row).getToeje1());
		obra.setToeje2(obrasList.get(row).getToeje2() == null ? BigDecimal.ZERO : obrasList.get(row).getToeje2());
		obra.setToeje3(obrasList.get(row).getToeje3() == null ? BigDecimal.ZERO : obrasList.get(row).getToeje3());
		obra.setToeje4(obrasList.get(row).getToeje4() == null ? BigDecimal.ZERO : obrasList.get(row).getToeje4());
		obra.setToeje5(obrasList.get(row).getToeje5() == null ? BigDecimal.ZERO : obrasList.get(row).getToeje5());
		obra.setToeje6(obrasList.get(row).getToeje6() == null ? BigDecimal.ZERO : obrasList.get(row).getToeje6());
		obra.setToeje7(obrasList.get(row).getToeje7() == null ? BigDecimal.ZERO : obrasList.get(row).getToeje7());
		obra.setToeje8(obrasList.get(row).getToeje8() == null ? BigDecimal.ZERO : obrasList.get(row).getToeje8());
		obra.setToeje9(obrasList.get(row).getToeje9() == null ? BigDecimal.ZERO : obrasList.get(row).getToeje9());
		obra.setToeje10(obrasList.get(row).getToeje10() == null ? BigDecimal.ZERO : obrasList.get(row).getToeje10());
		obra.setToeje11(obrasList.get(row).getToeje11() == null ? BigDecimal.ZERO : obrasList.get(row).getToeje11());
		obra.setToeje12(obrasList.get(row).getToeje12() == null ? BigDecimal.ZERO : obrasList.get(row).getToeje12());

		obra.setToeje13(obra.getToeje1().add(obra.getToeje2()).add(obra.getToeje3()).add(obra.getToeje4())
				.add(obra.getToeje5()).add(obra.getToeje6()).add(obra.getToeje7()).add(obra.getToeje8())
				.add(obra.getToeje9()).add(obra.getToeje10()).add(obra.getToeje11()).add(obra.getToeje12()));

		Catsector cat = new Catsector();
		cat.setIdsector(this.getUserDetails().getIdSector());
		cat.setUserid(this.getUserDetails().getUsername());
		obra.setCatsector(cat);
		obra.setUserid(this.getUserDetails().getUsername());
		try {
			if (this.validaCampos(obra)) {
				programa = obra.getFn() + obra.getFun() + obra.getSubfun() + obra.getProg() + obra.getSubprog()
						+ obra.getProy() + obra.getFfin();
				bandera = pasoRepository.validatePrograma(programa);
				if (bandera > 0) {

					if (BigDecimal.ZERO.compareTo(obra.getAuto13()) < 0) {
						autoi13 = pasoRepository.existBudget();
						if (BigDecimal.ZERO.compareTo(autoi13) != 0) {
							String validacion = csvService.validaPresupuestoManual(obra);
							if (messageOk.equals(validacion)) {
								obrasRepository.save(obra);

								findAllObras();
								adicionar = false;

								FacesContext context = FacesContext.getCurrentInstance();
								context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
										"Programa de Obra Anual guardada correctamente"));
							} else {
								FacesContext context = FacesContext.getCurrentInstance();
								context.addMessage(null, new FacesMessage(validacion));
								this.setVerBotonSalvar(true);
								this.setVerBotonModificar(false);
								this.setHabilitarReset(false);
								this.setHabilitarAdicionar(true);
								this.setHabilitarBorrar(true);
								this.setHabilitarCancelar(false);
							}
						} else {
							generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
									"No hay suficiente presupuesto");
							buttonsBoolean();
						}
					} else {
						generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
								"La suma del presupuesto calendarizado de egresos debe de ser mayor a cero");
						buttonsBoolean();

					}
				} else {
					generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
							"La partida no tiene presupuesto (6xxx)");
					buttonsBoolean();
				}
			} else {
				buttonsBoolean();
			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Ocurrió un error al salvar el Programa de Obra Anual "));
		}
	}

	/**
	 * Buttons boolean.
	 */
	public void buttonsBoolean() {
		this.setVerBotonSalvar(true);
		this.setVerBotonModificar(false);
		this.setHabilitarReset(false);
		this.setHabilitarAdicionar(true);
		this.setHabilitarBorrar(false);
		this.setHabilitarCancelar(false);
	}

	/**
	 * Valida campos.
	 *
	 * @param obra the obra
	 * @return true, if successful
	 */
	/*
	 * obra.setFun(obrasList.get(row).getFun());
	 * obra.setSubfun(obrasList.get(row).getSubFun());
	 * obra.setProg(obrasList.get(row).getProg());
	 * obra.setSubprog(obrasList.get(row).getSubProg());
	 * obra.setProy(obrasList.get(row).getProy());
	 * obra.setFfin(obrasList.get(row).getFfin());
	 */
	public boolean validaCampos(final Obra obra) {
		boolean bandera = Boolean.TRUE;
		if (StringUtils.isEmpty(obra.getFn())) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo Fn es requerido");
			bandera = Boolean.FALSE;
		} else if (StringUtils.isEmpty(obra.getFun())) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo Fun es requerido");
			bandera = Boolean.FALSE;
		} else if (StringUtils.isEmpty(obra.getSubfun())) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo SubFun es requerido");
			bandera = Boolean.FALSE;
		} else if (StringUtils.isEmpty(obra.getProg())) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo Prog es requerido");
			bandera = Boolean.FALSE;
		} else if (StringUtils.isEmpty(obra.getSubprog())) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo SubProg es requerido");
			bandera = Boolean.FALSE;
		} else if (StringUtils.isEmpty(obra.getProy())) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo Proy es requerido");
			bandera = Boolean.FALSE;
		} else if (StringUtils.isEmpty(obra.getFfin())) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo FFin es requerido");
			bandera = Boolean.FALSE;
		} else if (2 != obra.getFn().length()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo Fn debe de ser de dos Números");
			bandera = Boolean.FALSE;
		} else if (2 != obra.getFun().length()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo Fun debe de ser de dos Números");
			bandera = Boolean.FALSE;
		} else if (2 != obra.getSubfun().length()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo SubFun debe de ser de dos Números");
			bandera = Boolean.FALSE;
		} else if (2 != obra.getProg().length()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo Prog debe de ser de dos Números");
			bandera = Boolean.FALSE;
		} else if (2 != obra.getSubprog().length()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"Campo SubProg debe de ser de dos Números");
			bandera = Boolean.FALSE;
		} else if (2 != obra.getProy().length()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo Proy debe de ser de dos Números");
			bandera = Boolean.FALSE;
		} else if (3 != obra.getFfin().length()) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo FFin debe de ser de tres Números");
			bandera = Boolean.FALSE;
		}
		if (null == obra.getNomobra() || StringUtils.isBlank(obra.getNomobra())) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo Nombre de la Obra es requerido");
			bandera = Boolean.FALSE;
		}
		if (null == obra.getTipoejec() || StringUtils.isBlank(obra.getTipoejec())) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo Tipo de Ejecución es requerido");
			bandera = Boolean.FALSE;
		}
		if (null == obra.getUbicacion() || StringUtils.isBlank(obra.getUbicacion())) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo Ubicación es requerido");
			bandera = Boolean.FALSE;
		}
		if (null == obra.getJustificacion() || StringUtils.isBlank(obra.getJustificacion())) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo Justificación es requerido");
			bandera = Boolean.FALSE;
		}
		if (obra.getPoblacion() <= 0) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo Población es requerido");
			bandera = Boolean.FALSE;
		}

		if (null == obra.getTipoasig() || StringUtils.isBlank(obra.getTipoasig())) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Campo Tipo de Asignación es requerido");
			bandera = Boolean.FALSE;
		}

		return bandera;
	}

	/**
	 * Metodo que modifica los valores de una obra.
	 */
	public void modificar() {
		modificarBandera = Boolean.TRUE;
		this.setVerBotonSalvar(true);
		this.setVerBotonModificar(false);

		this.setHabilitarAdicionar(true);
		this.setHabilitarBorrar(true);
		this.setHabilitarReset(false);
		this.setHabilitarCancelar(false);
		this.setDeshabilitado(false);

	}

	/**
	 * Metodo que realiza un reset a la información de una obra seleccionada.
	 *
	 * @param row the row
	 */
	public void reset(Integer row) {
		this.setDeshabilitado(true);// always
		if (lista.isEmpty()) {
			this.setVerBotonSalvar(false);
			this.setVerBotonModificar(false);
			this.setHabilitarAdicionar(false);
			this.setHabilitarBorrar(true);
			this.setHabilitarCancelar(true);
			this.setHabilitarReset(true);
		} else {
			this.setVerBotonSalvar(false);
			this.setVerBotonModificar(true);
			this.setHabilitarAdicionar(false);
			this.setHabilitarBorrar(false);
			this.setHabilitarCancelar(false);
			this.setHabilitarReset(false);
		}

		if (lista.isEmpty()) {
			obrasList.clear();
			ObrasDTO obrasDTOEmpty = new ObrasDTO();
			obrasDTOEmpty.setNcontrol(1);
			obrasDTOEmpty.setUserCapturo(SecurityContextHolder.getContext().getAuthentication().getName());
			obrasList.add(obrasDTOEmpty);
		} else {
			if (modificarBandera) {
				this.setVerBotonSalvar(true);
				this.setVerBotonModificar(false);
				this.setHabilitarAdicionar(false);
				this.setHabilitarBorrar(true);
				this.setHabilitarCancelar(false);
				this.setHabilitarReset(false);
				deshabilitado = Boolean.FALSE;
				obrasList.get(row).setFn(lista.get(row).getFn());
				obrasList.get(row).setFun(lista.get(row).getFun());
				obrasList.get(row).setSubFun(lista.get(row).getSubfun());
				obrasList.get(row).setProg(lista.get(row).getProg());
				obrasList.get(row).setSubProg(lista.get(row).getSubprog());
				obrasList.get(row).setProy(lista.get(row).getProy());
				obrasList.get(row).setFfin(lista.get(row).getFfin());

				obrasList.get(row).setNomobra(lista.get(row).getNomobra());
				obrasList.get(row).setTipoejec(lista.get(row).getTipoejec());
				obrasList.get(row).setUbicacion(lista.get(row).getUbicacion());
				obrasList.get(row).setJustificacion(lista.get(row).getJustificacion());
				obrasList.get(row).setPoblacion(lista.get(row).getPoblacion());
				obrasList.get(row).setTipoasig(lista.get(row).getTipoasig());

				obrasList.get(row).setAuto1(lista.get(row).getAuto1());
				obrasList.get(row).setAuto2(lista.get(row).getAuto2());
				obrasList.get(row).setAuto3(lista.get(row).getAuto3());
				obrasList.get(row).setAuto4(lista.get(row).getAuto4());
				obrasList.get(row).setAuto5(lista.get(row).getAuto5());
				obrasList.get(row).setAuto6(lista.get(row).getAuto6());
				obrasList.get(row).setAuto7(lista.get(row).getAuto7());
				obrasList.get(row).setAuto8(lista.get(row).getAuto8());
				obrasList.get(row).setAuto9(lista.get(row).getAuto9());
				obrasList.get(row).setAuto10(lista.get(row).getAuto10());
				obrasList.get(row).setAuto11(lista.get(row).getAuto11());
				obrasList.get(row).setAuto12(lista.get(row).getAuto12());
				obrasList.get(row).setAuto13(lista.get(row).getAuto13());

				obrasList.get(row).setToeje1(lista.get(row).getToeje1());
				obrasList.get(row).setToeje2(lista.get(row).getToeje2());
				obrasList.get(row).setToeje3(lista.get(row).getToeje3());
				obrasList.get(row).setToeje4(lista.get(row).getToeje4());
				obrasList.get(row).setToeje5(lista.get(row).getToeje5());
				obrasList.get(row).setToeje6(lista.get(row).getToeje6());
				obrasList.get(row).setToeje7(lista.get(row).getToeje7());
				obrasList.get(row).setToeje8(lista.get(row).getToeje8());
				obrasList.get(row).setToeje9(lista.get(row).getToeje9());
				obrasList.get(row).setToeje10(lista.get(row).getToeje10());
				obrasList.get(row).setToeje11(lista.get(row).getToeje11());
				obrasList.get(row).setToeje12(lista.get(row).getToeje12());
				obrasList.get(row).setToeje13(lista.get(row).getToeje13());
				modificarBandera = Boolean.FALSE;
			} else {
				deshabilitado = Boolean.FALSE;
				this.setVerBotonSalvar(true);
				this.setVerBotonModificar(false);
				this.setHabilitarAdicionar(true);
				this.setHabilitarBorrar(true);
				this.setHabilitarCancelar(false);
				this.setHabilitarReset(false);
				obrasList.get(row).setFn("");
				obrasList.get(row).setFun("");
				obrasList.get(row).setSubFun("");
				obrasList.get(row).setProg("");
				obrasList.get(row).setSubProg("");
				obrasList.get(row).setProy("");
				obrasList.get(row).setFfin("");

				obrasList.get(row).setNomobra("");
				obrasList.get(row).setTipoejec("Seleccione uno...");
				obrasList.get(row).setUbicacion("");
				obrasList.get(row).setJustificacion("");
				obrasList.get(row).setPoblacion(0);
				obrasList.get(row).setTipoasig("Seleccione uno...");

				obrasList.get(row).setAuto1(BigDecimal.ZERO);
				obrasList.get(row).setAuto2(BigDecimal.ZERO);
				obrasList.get(row).setAuto3(BigDecimal.ZERO);
				obrasList.get(row).setAuto4(BigDecimal.ZERO);
				obrasList.get(row).setAuto5(BigDecimal.ZERO);
				obrasList.get(row).setAuto6(BigDecimal.ZERO);
				obrasList.get(row).setAuto7(BigDecimal.ZERO);
				obrasList.get(row).setAuto8(BigDecimal.ZERO);
				obrasList.get(row).setAuto9(BigDecimal.ZERO);
				obrasList.get(row).setAuto10(BigDecimal.ZERO);
				obrasList.get(row).setAuto11(BigDecimal.ZERO);
				obrasList.get(row).setAuto12(BigDecimal.ZERO);
				obrasList.get(row).setAuto13(BigDecimal.ZERO);

				obrasList.get(row).setToeje1(BigDecimal.ZERO);
				obrasList.get(row).setToeje2(BigDecimal.ZERO);
				obrasList.get(row).setToeje3(BigDecimal.ZERO);
				obrasList.get(row).setToeje4(BigDecimal.ZERO);
				obrasList.get(row).setToeje5(BigDecimal.ZERO);
				obrasList.get(row).setToeje6(BigDecimal.ZERO);
				obrasList.get(row).setToeje7(BigDecimal.ZERO);
				obrasList.get(row).setToeje8(BigDecimal.ZERO);
				obrasList.get(row).setToeje9(BigDecimal.ZERO);
				obrasList.get(row).setToeje10(BigDecimal.ZERO);
				obrasList.get(row).setToeje11(BigDecimal.ZERO);
				obrasList.get(row).setToeje12(BigDecimal.ZERO);
				obrasList.get(row).setToeje13(BigDecimal.ZERO);
				adicionarBandera = Boolean.FALSE;
			}
		}

	}

	/**
	 * Metodo que agrega una nueva obra.
	 */
	public void adicionar() {
		adicionarBandera = Boolean.TRUE;
		this.setVerBotonSalvar(true);
		this.setVerBotonModificar(false);
		this.setHabilitarReset(false);
		this.setHabilitarAdicionar(true);
		this.setHabilitarBorrar(true);
		this.setHabilitarCancelar(false);

		adicionar = true;
		if (lista.isEmpty()) {
			this.modificar();
		} else {
			ObrasDTO obrasDTOEmpty = new ObrasDTO();
			obrasDTOEmpty.setNcontrol(obrasRepository.findAllByOrderByNcontrolDesc().get(0).getNcontrol() + 1);
			obrasDTOEmpty.setUserCapturo(SecurityContextHolder.getContext().getAuthentication().getName());
			obrasList.add(obrasDTOEmpty);
			this.setDeshabilitado(false);
		}

	}

	/**
	 * Borrar.
	 *
	 * @param row the row
	 */
	// TODO validar su funcionamiento
	public void borrar(Integer row) {

		try {
			obrasRepository.delete(obrasList.get(row).getId());
			this.setDeshabilitado(true);
			obrasList.clear();
			findAllObras();

			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
					"Programa de Obra Anual borrada correctamente");
		} catch (Exception e) {
			UtilFront.generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"Ocurrió un error al borrar el Programa de Obra Anual");
		}

	}

	/**
	 * Cancelar.
	 *
	 * @param row the row
	 */
	// TODO validar su funcionamiento
	public void cancelar(Integer row) {
		LOGGER.info("ENTRA A CANCELAR");
		if (adicionar && !lista.isEmpty()) {
			obrasList.remove(obrasList.size() - 1);
			adicionar = false;
			this.setVerBotonSalvar(false);
			this.setVerBotonModificar(true);

			this.setHabilitarAdicionar(false);
			this.setHabilitarBorrar(true);
			this.setHabilitarCancelar(true);
			this.setHabilitarReset(true);
			this.deshabilitado = Boolean.TRUE;
		} else {
			this.reset(row);
			this.setVerBotonSalvar(false);
			this.setVerBotonModificar(true);

			this.setHabilitarAdicionar(false);
			this.setHabilitarBorrar(true);
			this.setHabilitarCancelar(true);
			this.setHabilitarReset(true);
			this.deshabilitado = Boolean.TRUE;
		}
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct ObrasMB ");
	}

	/**
	 * Find all obras.
	 */
	public void findAllObras() {
		programatica = null;
		numObra = null;
		nombreObra = null;
		lista.clear();
		lista = obrasRepository.findAllByOrderById();
		LOGGER.info("Element size list: " + lista.size());
		obrasList = new ArrayList<ObrasDTO>();
		ObrasDTO obrasDTO;
		for (Obra obra : lista) {
			obrasDTO = new ObrasDTO();
			obrasDTO.setId(obra.getId());
			obrasDTO.setFn(obra.getFn());
			obrasDTO.setFun(obra.getFun());
			obrasDTO.setSubFun(obra.getSubfun());
			obrasDTO.setProg(obra.getProg());
			obrasDTO.setSubProg(obra.getSubprog());
			obrasDTO.setProy(obra.getProy());
			obrasDTO.setFfin(obra.getFfin().toString());
			obrasDTO.setNcontrol(obra.getNcontrol());
			obrasDTO.setNomobra(obra.getNomobra());
			obrasDTO.setTipoejec(obra.getTipoejec());
			obrasDTO.setUbicacion(obra.getUbicacion());
			obrasDTO.setJustificacion(obra.getJustificacion());
			obrasDTO.setPoblacion(obra.getPoblacion());
			obrasDTO.setTipoasig(obra.getTipoasig());

			obrasDTO.setAuto1(obra.getAuto1() != null ? obra.getAuto1() : new BigDecimal(0));
			obrasDTO.setAuto2(obra.getAuto2() != null ? obra.getAuto2() : new BigDecimal(0));
			obrasDTO.setAuto3(obra.getAuto3() != null ? obra.getAuto3() : new BigDecimal(0));
			obrasDTO.setAuto4(obra.getAuto4() != null ? obra.getAuto4() : new BigDecimal(0));
			obrasDTO.setAuto5(obra.getAuto5() != null ? obra.getAuto5() : new BigDecimal(0));
			obrasDTO.setAuto6(obra.getAuto6() != null ? obra.getAuto6() : new BigDecimal(0));
			obrasDTO.setAuto7(obra.getAuto7() != null ? obra.getAuto7() : new BigDecimal(0));
			obrasDTO.setAuto8(obra.getAuto8() != null ? obra.getAuto8() : new BigDecimal(0));
			obrasDTO.setAuto9(obra.getAuto9() != null ? obra.getAuto9() : new BigDecimal(0));
			obrasDTO.setAuto10(obra.getAuto10() != null ? obra.getAuto10() : new BigDecimal(0));
			obrasDTO.setAuto11(obra.getAuto11() != null ? obra.getAuto11() : new BigDecimal(0));
			obrasDTO.setAuto12(obra.getAuto12() != null ? obra.getAuto12() : new BigDecimal(0));

			obrasDTO.setAuto13(obrasDTO.getAuto1().add(obrasDTO.getAuto2()).add(obrasDTO.getAuto3())
					.add(obrasDTO.getAuto4()).add(obrasDTO.getAuto5()).add(obrasDTO.getAuto6()).add(obrasDTO.getAuto7())
					.add(obrasDTO.getAuto8()).add(obrasDTO.getAuto9()).add(obrasDTO.getAuto10())
					.add(obrasDTO.getAuto11()).add(obrasDTO.getAuto12()));

			obrasDTO.setToeje1(obra.getToeje1() != null ? obra.getToeje1() : new BigDecimal(0));
			obrasDTO.setToeje2(obra.getToeje2() != null ? obra.getToeje2() : new BigDecimal(0));
			obrasDTO.setToeje3(obra.getToeje3() != null ? obra.getToeje3() : new BigDecimal(0));
			obrasDTO.setToeje4(obra.getToeje4() != null ? obra.getToeje4() : new BigDecimal(0));
			obrasDTO.setToeje5(obra.getToeje5() != null ? obra.getToeje5() : new BigDecimal(0));
			obrasDTO.setToeje6(obra.getToeje6() != null ? obra.getToeje6() : new BigDecimal(0));
			obrasDTO.setToeje7(obra.getToeje7() != null ? obra.getToeje7() : new BigDecimal(0));
			obrasDTO.setToeje8(obra.getToeje8() != null ? obra.getToeje8() : new BigDecimal(0));
			obrasDTO.setToeje9(obra.getToeje9() != null ? obra.getToeje9() : new BigDecimal(0));
			obrasDTO.setToeje10(obra.getToeje10() != null ? obra.getToeje10() : new BigDecimal(0));
			obrasDTO.setToeje11(obra.getToeje11() != null ? obra.getToeje11() : new BigDecimal(0));
			obrasDTO.setToeje12(obra.getToeje12() != null ? obra.getToeje12() : new BigDecimal(0));

			obrasDTO.setToeje13(obrasDTO.getToeje1().add(obrasDTO.getToeje2()).add(obrasDTO.getToeje3())
					.add(obrasDTO.getToeje4()).add(obrasDTO.getToeje5()).add(obrasDTO.getToeje6())
					.add(obrasDTO.getToeje7()).add(obrasDTO.getToeje8()).add(obrasDTO.getToeje9())
					.add(obrasDTO.getToeje10()).add(obrasDTO.getToeje11()).add(obrasDTO.getToeje12()));

			obrasList.add(obrasDTO);
		}

		if (!lista.isEmpty()) {
			this.setVerBotonSalvar(false);
			this.setVerBotonModificar(true);
			this.setHabilitarReset(true);
			this.setHabilitarCancelar(true);
			this.setHabilitarBorrar(false);
			this.setHabilitarAdicionar(false);
			this.setDeshabilitado(true);
		} else {
			this.setVerBotonSalvar(false);
			this.setVerBotonModificar(false);
			this.setHabilitarReset(true);
			this.setHabilitarAdicionar(false);
			this.setHabilitarBorrar(true);
			this.setHabilitarCancelar(true);
			this.setDeshabilitado(true);
			// agrega una obra vacia al inicio deshabilitada
			ObrasDTO obrasDTOEmpty = new ObrasDTO();
			obrasDTOEmpty.setNcontrol(1);
			obrasList.add(obrasDTOEmpty);
		}
		obrasListOriginal.addAll(obrasList);
	}

	/**
	 * Procesar archivo.
	 */
	public String procesarArchivo() {
		final StringBuilder errorMsg = new StringBuilder();
		ObrasCargaAutomaticaDTO obrasCargaAutomaticaDTO = new ObrasCargaAutomaticaDTO();
		if (file == null) {
			FacesMessage message = new FacesMessage("Debe seleccionar un archivo");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "";
		}
		if (!file.getFileName().endsWith(".csv")) {
			FacesMessage message = new FacesMessage("La Extensión del archivo no es válida");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} else {

			// llamada al metodo para procesar el archivo csv
			try {
				
				String newUUIDName = UUID.randomUUID() + file.getFileName();
				if (applyContenFileValidators(this.inputFile(newUUIDName, file.getInputstream(), DEFAULT_PATH_UPLOAD))) {
					obrasCargaAutomaticaDTO = csvService.processCsvFile(file.getInputstream(), errorMsg);
					if (!obrasCargaAutomaticaDTO.getErrorMsg().isEmpty()) {
						if (obrasCargaAutomaticaDTO.getErrorMsg().startsWith("1")) {
							FacesMessage message = new FacesMessage(
									"Es posible que el registro del archivo de carga automatica de Obras ya haya sido procesado anteriormente");
							FacesContext.getCurrentInstance().addMessage(null, message);
						} else {
							FacesMessage message = new FacesMessage(obrasCargaAutomaticaDTO.getErrorMsg());
							FacesContext.getCurrentInstance().addMessage(null, message);
						}

					} else {
						FacesMessage message = new FacesMessage("Información cargada exitosamente");
						FacesContext.getCurrentInstance().addMessage(null, message);
					}

					sendFileToUser(obrasCargaAutomaticaDTO.getFileName());
				}else {
					sendFileToUser(DEFAULT_PATH_ERRORES + newUUIDName);
					FacesMessage message = new FacesMessage("El archivo tiene errore de formato");
					FacesContext.getCurrentInstance().addMessage(null, message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public File inputFile(String filename, InputStream input, String path) throws IOException {
		File file = new File(filename);
		OutputStream output = new FileOutputStream(file);
		
			
		try {
			IOUtils.copy(input, output);
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
			
		}
		return file;
	}
		
	/**
	 * Send file to user.
	 *
	 * @param fileToSend the file to send
	 */
	private void sendFileToUser(String fileToSend) {
		try {
			System.out.println("******** " + fileToSend);
			File obrasResp = new File(fileToSend);
			System.out.println("******* " + obrasResp.length());
			InputStream fileInputStream = new FileInputStream(obrasResp);

			setFile2(new DefaultStreamedContent(fileInputStream, "text/plain", "obras_resp.txt"));
			RequestContext.getCurrentInstance().execute("descargar();");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * metodo que actualiza la suma total del egreso calendarizado.
	 *
	 * @param row the row
	 */
	public void validarKeyUpCalendarizado(Integer row) {
		obrasList.get(row)
				.setAuto13(((obrasList.get(row).getAuto1() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto1())
						.add((obrasList.get(row).getAuto2() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto2()))
						.add((obrasList.get(row).getAuto3() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto3()))
						.add((obrasList.get(row).getAuto4() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto4()))
						.add((obrasList.get(row).getAuto5() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto5()))
						.add((obrasList.get(row).getAuto6() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto6()))
						.add((obrasList.get(row).getAuto7() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto7()))
						.add((obrasList.get(row).getAuto8() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto8()))
						.add((obrasList.get(row).getAuto9() == null ? BigDecimal.ZERO : obrasList.get(row).getAuto9()))
						.add((obrasList.get(row).getAuto10() == null ? BigDecimal.ZERO
								: obrasList.get(row).getAuto10()))
						.add((obrasList.get(row).getAuto11() == null ? BigDecimal.ZERO
								: obrasList.get(row).getAuto11()))
						.add((obrasList.get(row).getAuto12() == null ? BigDecimal.ZERO
								: obrasList.get(row).getAuto12()))));
	}

	/**
	 * metodo que actualiza la suma total del egreso ejercido.
	 *
	 * @param row the row
	 */

	public void validarKeyUpEjercido(Integer row) {
		obrasList.get(row)
				.setToeje13(((obrasList.get(row).getToeje1() == null ? BigDecimal.ZERO : obrasList.get(row).getToeje1())
						.add((obrasList.get(row).getToeje2() == null ? BigDecimal.ZERO
								: obrasList.get(row).getToeje2()))
						.add((obrasList.get(row).getToeje3() == null ? BigDecimal.ZERO
								: obrasList.get(row).getToeje3()))
						.add((obrasList.get(row).getToeje4() == null ? BigDecimal.ZERO
								: obrasList.get(row).getToeje4()))
						.add((obrasList.get(row).getToeje5() == null ? BigDecimal.ZERO
								: obrasList.get(row).getToeje5()))
						.add((obrasList.get(row).getToeje6() == null ? BigDecimal.ZERO
								: obrasList.get(row).getToeje6()))
						.add((obrasList.get(row).getToeje7() == null ? BigDecimal.ZERO
								: obrasList.get(row).getToeje7()))
						.add((obrasList.get(row).getToeje8() == null ? BigDecimal.ZERO
								: obrasList.get(row).getToeje8()))
						.add((obrasList.get(row).getToeje9() == null ? BigDecimal.ZERO
								: obrasList.get(row).getToeje9()))
						.add((obrasList.get(row).getToeje10() == null ? BigDecimal.ZERO
								: obrasList.get(row).getToeje10()))
						.add((obrasList.get(row).getToeje11() == null ? BigDecimal.ZERO
								: obrasList.get(row).getToeje11()))
						.add((obrasList.get(row).getToeje12() == null ? BigDecimal.ZERO
								: obrasList.get(row).getToeje12()))));
	}

	/**
	 * Busca por estructura programática/No. control/Nombre de Obra y actualiza la
	 * lista de obras con el resultado
	 */
	public void buscarObra() {

		obrasList.clear();
		obrasList = obrasService.buscarObras(programatica, numObra, nombreObra);
		this.habilitarBorrar = Boolean.FALSE;
	}

	/**
	 * Validar password borrar.
	 *
	 * @param pass the pass
	 * @return true, if successful
	 */
	public boolean validarPasswordBorrar(String pass) {
		return obrasService.validarPasswordBorrar(pass);
	}

	/**
	 * Gets the obras list.
	 *
	 * @return the obras list
	 */
	public List<ObrasDTO> getObrasList() {
		return obrasList;
	}

	/**
	 * Sets the obras list.
	 *
	 * @param obrasList the new obras list
	 */
	public void setObrasList(List<ObrasDTO> obrasList) {
		this.obrasList = obrasList;
	}

	/**
	 * Gets the obras DTO.
	 *
	 * @return the obras DTO
	 */
	public ObrasDTO getObrasDTO() {
		return obrasDTO;
	}

	/**
	 * Sets the obras DTO.
	 *
	 * @param obrasDTO the new obras DTO
	 */
	public void setObrasDTO(ObrasDTO obrasDTO) {
		this.obrasDTO = obrasDTO;
	}

	/**
	 * Checks if is deshabilitado.
	 *
	 * @return true, if is deshabilitado
	 */
	public boolean isDeshabilitado() {
		return deshabilitado;
	}

	/**
	 * Sets the deshabilitado.
	 *
	 * @param deshabilitado the new deshabilitado
	 */
	public void setDeshabilitado(boolean deshabilitado) {
		this.deshabilitado = deshabilitado;
	}

	/**
	 * Checks if is ver boton salvar.
	 *
	 * @return true, if is ver boton salvar
	 */
	public boolean isVerBotonSalvar() {
		return verBotonSalvar;
	}

	/**
	 * Sets the ver boton salvar.
	 *
	 * @param verBotonSalvar the new ver boton salvar
	 */
	public void setVerBotonSalvar(boolean verBotonSalvar) {
		this.verBotonSalvar = verBotonSalvar;
	}

	/**
	 * Checks if is ver boton modificar.
	 *
	 * @return true, if is ver boton modificar
	 */
	public boolean isVerBotonModificar() {
		return verBotonModificar;
	}

	/**
	 * Sets the ver boton modificar.
	 *
	 * @param verBotonModificar the new ver boton modificar
	 */
	public void setVerBotonModificar(boolean verBotonModificar) {
		this.verBotonModificar = verBotonModificar;
	}

	/**
	 * Gets the obras repository.
	 *
	 * @return the obras repository
	 */
	public ObrasRepository getObrasRepository() {
		return obrasRepository;
	}

	/**
	 * Sets the obras repository.
	 *
	 * @param obrasRepository the new obras repository
	 */
	public void setObrasRepository(ObrasRepository obrasRepository) {
		this.obrasRepository = obrasRepository;
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public UploadedFile getFile() {
		return file;
	}

	/**
	 * Sets the file.
	 *
	 * @param file the new file
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	}

	/**
	 * Gets the lista.
	 *
	 * @return the lista
	 */
	public List<Obra> getLista() {
		return lista;
	}

	/**
	 * Sets the lista.
	 *
	 * @param lista the new lista
	 */
	public void setLista(List<Obra> lista) {
		this.lista = lista;
	}

	/**
	 * Checks if is adicionar.
	 *
	 * @return true, if is adicionar
	 */
	public boolean isAdicionar() {
		return adicionar;
	}

	/**
	 * Sets the adicionar.
	 *
	 * @param adicionar the new adicionar
	 */
	public void setAdicionar(boolean adicionar) {
		this.adicionar = adicionar;
	}

	/**
	 * Checks if is habilitar borrar.
	 *
	 * @return true, if is habilitar borrar
	 */
	public boolean isHabilitarBorrar() {
		return habilitarBorrar;
	}

	/**
	 * Sets the habilitar borrar.
	 *
	 * @param habilitarBorrar the new habilitar borrar
	 */
	public void setHabilitarBorrar(boolean habilitarBorrar) {
		this.habilitarBorrar = habilitarBorrar;
	}

	/**
	 * Checks if is habilitar reset.
	 *
	 * @return true, if is habilitar reset
	 */
	public boolean isHabilitarReset() {
		return habilitarReset;
	}

	/**
	 * Sets the habilitar reset.
	 *
	 * @param habilitarReset the new habilitar reset
	 */
	public void setHabilitarReset(boolean habilitarReset) {
		this.habilitarReset = habilitarReset;
	}

	/**
	 * Checks if is habilitar adicionar.
	 *
	 * @return true, if is habilitar adicionar
	 */
	public boolean isHabilitarAdicionar() {
		return habilitarAdicionar;
	}

	/**
	 * Sets the habilitar adicionar.
	 *
	 * @param habilitarAdicionar the new habilitar adicionar
	 */
	public void setHabilitarAdicionar(boolean habilitarAdicionar) {
		this.habilitarAdicionar = habilitarAdicionar;
	}

	/**
	 * Gets the csv service.
	 *
	 * @return the csv service
	 */
	public ReadCSVService getCsvService() {
		return csvService;
	}

	/**
	 * Sets the csv service.
	 *
	 * @param csvService the new csv service
	 */
	public void setCsvService(ReadCSVService csvService) {
		this.csvService = csvService;
	}

	/**
	 * Gets the dt obras.
	 *
	 * @return the dt obras
	 */
	public DataTable getDtObras() {
		return dtObras;
	}

	/**
	 * Sets the dt obras.
	 *
	 * @param dtObras the new dt obras
	 */
	public void setDtObras(DataTable dtObras) {
		this.dtObras = dtObras;
	}

	/**
	 * Checks if is habilitar cancelar.
	 *
	 * @return true, if is habilitar cancelar
	 */
	public boolean isHabilitarCancelar() {
		return habilitarCancelar;
	}

	/**
	 * Sets the habilitar cancelar.
	 *
	 * @param habilitarCancelar the new habilitar cancelar
	 */
	public void setHabilitarCancelar(boolean habilitarCancelar) {
		this.habilitarCancelar = habilitarCancelar;
	}

	/**
	 * Gets the obras list original.
	 *
	 * @return the obras list original
	 */
	public List<ObrasDTO> getObrasListOriginal() {
		return obrasListOriginal;
	}

	/**
	 * Sets the obras list original.
	 *
	 * @param obrasListOriginal the new obras list original
	 */
	public void setObrasListOriginal(List<ObrasDTO> obrasListOriginal) {
		this.obrasListOriginal = obrasListOriginal;
	}

	/**
	 * Gets the programatica.
	 *
	 * @return the programatica
	 */
	public String getProgramatica() {
		return programatica;
	}

	/**
	 * Sets the programatica.
	 *
	 * @param programatica the new programatica
	 */
	public void setProgramatica(String programatica) {
		this.programatica = programatica;
	}

	/**
	 * Gets the num obra.
	 *
	 * @return the num obra
	 */
	public String getNumObra() {
		return numObra;
	}

	/**
	 * Sets the num obra.
	 *
	 * @param numObra the new num obra
	 */
	public void setNumObra(String numObra) {
		this.numObra = numObra;
	}

	/**
	 * Gets the obras service.
	 *
	 * @return the obras service
	 */
	public ObrasService getObrasService() {
		return obrasService;
	}

	/**
	 * Sets the obras service.
	 *
	 * @param obrasService the new obras service
	 */
	public void setObrasService(ObrasService obrasService) {
		this.obrasService = obrasService;
	}

	/**
	 * Gets the nombre obra.
	 *
	 * @return the nombre obra
	 */
	public String getNombreObra() {
		return nombreObra;
	}

	/**
	 * Sets the nombre obra.
	 *
	 * @param nombreObra the new nombre obra
	 */
	public void setNombreObra(String nombreObra) {
		this.nombreObra = nombreObra;
	}

	/**
	 * Gets the pass borrar.
	 *
	 * @return the pass borrar
	 */
	public String getPassBorrar() {
		return passBorrar;
	}

	/**
	 * Sets the pass borrar.
	 *
	 * @param passBorrar the new pass borrar
	 */
	public void setPassBorrar(String passBorrar) {
		this.passBorrar = passBorrar;
	}

	/**
	 * Gets the file 2.
	 *
	 * @return the file 2
	 */
	public StreamedContent getFile2() {
		return file2;
	}

	/**
	 * Sets the file 2.
	 *
	 * @param file2 the new file 2
	 */
	public void setFile2(StreamedContent file2) {
		this.file2 = file2;
	}

	/**
	 * Clean find.
	 */
	public void cleanFind() {
		this.programatica = "";
		this.numObra = "";
		this.nombreObra = "";

	}

	public FileContentValidator getObrasCsvValidator() {
		return obrasCsvValidator;
	}

	public void setObrasCsvValidator(FileContentValidator obrasCsvValidator) {
		this.obrasCsvValidator = obrasCsvValidator;
	}

	@Override
	public String getDefaulPath() {
		// TODO Auto-generated method stub
		return DEFAULT_PATH;
	}

	@Override
	public String getDefaulPathErrores() {
		// TODO Auto-generated method stub
		return DEFAULT_PATH_ERRORES;
	}

	@Override
	public String getFileUploadRequestPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFileUploadResponseFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInitPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefaultClave() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean applyContenFileValidators(File file) {
		final FileUpload fileUpload = new FileUpload();

		fileUpload.setFile(file);
		fileUpload.setPath(DEFAULT_PATH_UPLOAD);
		fileUpload.setNameOriginal(file.getName());

		fileUpload.setNameReal(file.getName());
		fileUpload.setErrorPath(DEFAULT_PATH_UPLOAD);

		fileUpload.setOutPutPath(DEFAULT_PATH_OUTVALID);

		try {

			this.validFormat = this.obrasCsvValidator.isValid(fileUpload);
			return validFormat;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void loadDataIntoTable() {
		// TODO Auto-generated method stub

	}

}
