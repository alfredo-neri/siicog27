package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;
import static java.util.Comparator.comparingLong;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.ProveedorNuevo;
import com.gem.sistema.business.domain.TcAdquisicion;
import com.gem.sistema.business.domain.TcAdquisicionGiroSubgiro;
import com.gem.sistema.business.domain.TcArticulosContrato;
import com.gem.sistema.business.domain.TcArticulosSA;
import com.gem.sistema.business.domain.TcContrato;
import com.gem.sistema.business.domain.TcContratosFirma;
import com.gem.sistema.business.domain.TcContratosRevison;
import com.gem.sistema.business.domain.TcConvenio;
import com.gem.sistema.business.domain.TcEstadoContrato;
import com.gem.sistema.business.domain.TcFirmaSolicitudDetalle;
import com.gem.sistema.business.domain.TcOrigenRecurso;
import com.gem.sistema.business.domain.TcProcedimientoAdquisitivo;
import com.gem.sistema.business.domain.TcTipoContrato;
import com.gem.sistema.business.domain.TcTipoGasto;
import com.gem.sistema.business.domain.TcUsuario;
import com.gem.sistema.business.domain.TrContratoDetail;
import com.gem.sistema.business.domain.TrContratoSolicitud;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcContratosFirmaRepository;
import com.gem.sistema.business.repository.catalogs.TcFirmaSolicitudDetalleRepository;
import com.gem.sistema.business.service.catalogos.ContratosService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.web.util.ImagesFirmasDigitales;

@Component("contratosMB")
@Scope("view")
public class ContratosMB extends BaseDirectReport {

	private static final String FIRST_CONTRATO_STATE = "REVISANDO";
	private static final String SECOND_CONTRATO_STATE_REVISADO = "REVISADO";
	private static final String SECOND_CONTRATO_STATE_DEVUELTO = "DEVUELTO";

	private Boolean showContratosReview = Boolean.FALSE;
	private Boolean editable;
	private Integer currentStep;
	private Integer currentIndex;
	private List<TcAdquisicion> adquisicionSelected = new ArrayList<TcAdquisicion>();
	private ProveedorNuevo proveedorSelected = new ProveedorNuevo();
	private TcConvenio convenio;
	private Boolean convenioEdit;

	private List<TrContratoDetail> contratosList;
	private List<TrContratoDetail> contratosForReview;
	private List<TcTipoGasto> tipoGastosList;
	private List<ProveedorNuevo> proveedorList;
	private List<TcAdquisicion> adquisicionList;
	private List<TcTipoContrato> tipoContratosList;
	private List<TcOrigenRecurso> origenRecursosList;
	private List<TcProcedimientoAdquisitivo> procedimientosList;
	private List<TcArticulosSA> articulosList;
	private List<TcArticulosSA> articulosSelected;
	private List<TcArticulosContrato> articulosContratosList =  new ArrayList<TcArticulosContrato>();
	private List<TcUsuario> usuariosList;
	private List<TcContratosFirma> contratosFirmas = new ArrayList<TcContratosFirma>();

	private List<TcFirmaSolicitudDetalle> firmasBySA = new ArrayList<TcFirmaSolicitudDetalle>();
	private TcContratosRevison revison = new TcContratosRevison();
	private Integer firmaPosicion = 0;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{contratosService}")
	private ContratosService contratosService;

	@Value("#{tcContratosFirmaRepository}")
	private TcContratosFirmaRepository contratosFirmaRepository;

	@Value("#{tcFirmaSolicitudDetalleRepository}")
	private TcFirmaSolicitudDetalleRepository firmaSolicitudDetalleRepository;

	@PostConstruct
	public void init() {
		this.jasperReporteName = "contratoBienesClausulas";
		this.endFilename = this.jasperReporteName + ".pdf";
		this.editable = Boolean.FALSE;
		this.showContratosReview = Boolean.FALSE;

		currentStep = 0;
		currentIndex = 0;

		usuariosList = contratosService.getUsuariosList(this.getUserDetails().getIdSector());
		contratosForReview = contratosService.getContratosByUsuario(this.getUserDetails().getUsername(),
				FIRST_CONTRATO_STATE);
		contratosList = contratosService.getContratos();
		proveedorList = contratosService.getProveedores();
		tipoGastosList = contratosService.getTipoGastos();
		adquisicionList = contratosService.getAdquisicions();
		tipoContratosList = contratosService.getTipoContratos();
		origenRecursosList = contratosService.getOrigenRecursos();
		procedimientosList = contratosService.getProcedimientos();

		if (CollectionUtils.isEmpty(contratosList)) {

			this.addNewContrato();
			this.editable = Boolean.FALSE;
		} else {
			this.loadDataOfAdquisicion();

		}
	}

	public void changeSolicitudAdquisicion() {

		this.loadDataOfAdquisicion();
	}

	public void selectProveedor() {
		contratosList.get(currentIndex).getContrato().setProveedor(proveedorSelected);
	}

	public void addNewContrato() {
		this.editable = Boolean.TRUE;

		adquisicionSelected.clear();
		articulosContratosList.clear();
		TcContrato contrato = new TcContrato();
		ProveedorNuevo proveedor = new ProveedorNuevo();
		TrContratoDetail contratoDetail = new TrContratoDetail();
		TcEstadoContrato estadoContrato = contratosService.getEstadoContrato(FIRST_CONTRATO_STATE);

		contrato.setEstadosContrato(estadoContrato);
		contrato.setProveedor(proveedor);
		contratoDetail.setContrato(contrato);
		contratosList.add(currentIndex, contratoDetail);
	}

	public void modificarContrato() {
		this.editable = Boolean.TRUE;
	}

	public void saveContrato() {

		if (validateContrato(contratosList.get(currentIndex))) {

			List<TrContratoSolicitud> list = new ArrayList<TrContratoSolicitud>();
			for (TcAdquisicion tcAdquisicion : adquisicionSelected) {

				if (contratosList.get(currentIndex).getContrato().getId() != null) {
					TrContratoSolicitud contratoSolicitud = contratosService
							.getContratoSolicitud(contratosList.get(currentIndex).getContrato(), tcAdquisicion);
					if (contratoSolicitud == null) {
						contratoSolicitud = new TrContratoSolicitud();

						contratoSolicitud.setContrato(contratosList.get(currentIndex).getContrato());
						contratoSolicitud.setSolicitud(tcAdquisicion);

						list.add(contratoSolicitud);
					}
				} else {
					TrContratoSolicitud contratoSolicitud = new TrContratoSolicitud();

					contratoSolicitud.setContrato(contratosList.get(currentIndex).getContrato());
					contratoSolicitud.setSolicitud(tcAdquisicion);

					list.add(contratoSolicitud);
				}

			}

			contratosService.saveContrato(contratosList.get(currentIndex));
			contratosService.saveContratoSolicitudes(list);
			contratosService.saveArticulos(articulosContratosList);

			this.editable = Boolean.FALSE;
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
					"La información se ha guardado correctamente!");
		}

	}

	public void calculaTotalProducto() {

		for (TcArticulosContrato articulo : articulosContratosList) {

			BigDecimal ivaTotal = articulo.getPrecio().multiply(articulo.getIva()).divide(new BigDecimal(100))
					.multiply(new BigDecimal(articulo.getArticulo().getCantidad()));
			BigDecimal total = articulo.getPrecio().multiply(new BigDecimal(articulo.getArticulo().getCantidad()))
					.add(ivaTotal);

			articulo.setTotal(total);
		}
	}

	public void saveReview() {

		revison.setContrato(contratosList.get(currentIndex).getContrato());
		contratosService.saveRevision(revison);
		TcEstadoContrato estadoContrato = null;

		if (revison.getCorrecto()) {
			estadoContrato = contratosService.getEstadoContrato(SECOND_CONTRATO_STATE_REVISADO);
		} else {
			estadoContrato = contratosService.getEstadoContrato(SECOND_CONTRATO_STATE_DEVUELTO);
		}

		contratosList.get(currentIndex).getContrato().setEstadosContrato(estadoContrato);
		contratosService.saveContrato(contratosList.get(currentIndex));
		revison = new TcContratosRevison();

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "La información se ha guardado correctamente!");
	}

	public void cancelarContrato() {
		this.editable = Boolean.FALSE;
		contratosList = contratosService.getContratos();
	}

	public void borrarContrato() {

		contratosService.deleteContrato(this.contratosList.get(currentIndex));
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El registro se ha eliminado!");
		this.cancelarContrato();
	}

	public void changePage(PageEvent event) {
		currentIndex = event.getPage();
		adquisicionSelected.clear();
		this.loadDataOfAdquisicion();
	}

	private void loadDataOfAdquisicion() {
		this.cleanListAdquisiciones();

		if (contratosList.get(currentIndex).getContrato().getId() != null) {
			articulosContratosList = contratosService
					.getArticulosContratoByContrato(contratosList.get(currentIndex).getContrato());

			for (TrContratoSolicitud contratoSolicitud : contratosService
					.getSolicitudesByContrato(contratosList.get(currentIndex).getContrato())) {
				adquisicionSelected.add(contratoSolicitud.getSolicitud());
			}

			this.cleanListAdquisiciones();

			for (int i = 0; i < adquisicionSelected.size(); i++) {
				System.out.println("::::::::::::->" + adquisicionSelected.get(i).getId());

				List<TcAdquisicionGiroSubgiro> subgiros = contratosService
						.getSubgirosByAdquisicion(adquisicionSelected.get(i));
				System.out.println("--------------------------");
				System.out.println(subgiros.toString());
				System.out.println("--------------------------");
				adquisicionSelected.get(i).setGiroSubgirosList(subgiros);
				adquisicionSelected.get(i)
						.setProgramasList(contratosService.getProgramasByAdquisicion(adquisicionSelected.get(i)));
			}
		}

		articulosList = new ArrayList<TcArticulosSA>();
		for (TcAdquisicion tcAdquisicion : adquisicionSelected) {
			List<TcArticulosSA> list = contratosService.getArticulosByAdquisicion(tcAdquisicion);

			articulosList.addAll(list);
		}
	}

	private void cleanListAdquisiciones() {
		List<TcAdquisicion> unique = adquisicionSelected.stream().collect(collectingAndThen(
				toCollection(() -> new TreeSet<>(comparingLong(TcAdquisicion::getId))), ArrayList::new));

		System.out.println(":::::------#ADQUI> " + unique.size());

		adquisicionSelected.clear();
		adquisicionSelected.addAll(unique);
	}

	public void addArticles() {

		if (!CollectionUtils.isEmpty(articulosSelected)) {

			List<TcArticulosSA> unique = articulosSelected.stream().collect(collectingAndThen(
					toCollection(() -> new TreeSet<>(comparingLong(TcArticulosSA::getId))), ArrayList::new));

			articulosSelected.clear();
			articulosSelected.addAll(unique);

			for (TcArticulosSA articulosSA : articulosSelected) {

				if (!existArticulo(articulosSA)) {

					TcArticulosContrato articulo = new TcArticulosContrato();

					articulo.setArticulo(articulosSA);
					articulo.setContrato(contratosList.get(currentIndex).getContrato());

					articulosContratosList.add(articulo);
				}

			}
		}
	}

	private Boolean existArticulo(TcArticulosSA articulo) {

		Boolean flag = Boolean.FALSE;
		for (TcArticulosContrato articulosContrato : articulosContratosList) {

			if (articulosContrato.getArticulo().equals(articulo))
				flag = Boolean.TRUE;
		}

		return flag;
	}

	public void createConvenio() {
		convenio = new TcConvenio();
		convenioEdit = Boolean.TRUE;

		convenio.setContrato(contratosList.get(currentIndex).getContrato());
	}

	public void saveConvenio() {
		contratosService.saveConvenio(this.convenio);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El convenio se ha registrado correctamente!");
	}

	private Boolean validateContrato(TrContratoDetail contrato) {

		Map<String, String> erros = new HashMap<String, String>();
		if (!isValid(contrato.getContrato().getNumeroContrato()))
			erros.put("numeroContrato", "El Número de contrato es requerido");
		if (!isValid(contrato.getContrato().getFecchaCreacion()))
			erros.put("fechaElaboracion", "La Fecha de elaboración es requerida");
		if (adquisicionSelected.isEmpty())
			erros.put("adquisicion", "No se ha seleccionado ninguna Solicitud de Adquisición");
		if (contrato.getContrato().getTipoContrato().getId() == null)
			erros.put("tipoContrato", "El Tipo de Contrato no se ha seleccionado");
		if (contrato.getContrato().getProveedor().getId() == null)
			erros.put("proveedor", "El Proveedor no se ha seleccionado");
		if (!isValid(contrato.getContrato().getImporteAdquisicion()))
			erros.put("importeAdquisicion", "El Importe de Adquisición es requerido");

		erros.forEach((k, v) -> this.showError(k, v));
		return erros.isEmpty();
	}

	public void showFirmasRecepcion() {
////		this.panelRendered = 4;
//		firmasBySA = firmaSolicitudDetalleRepository.getFirmasRecepcionByAdquisicionId(
//				contratosList.get(currentIndex).getContrato().getAdquisicion().getId());
//		// Si no se han agregado firmas crea las 4 firmas de revision para poder
//		// renderizar la vista
//		if (firmasBySA.isEmpty()) {
//			for (int i = 5; i < 8; i++) {
//				firmasBySA.add(new TcFirmaSolicitudDetalle(i, null,
//						contratosList.get(currentIndex).getContrato().getAdquisicion()));
//			}
//			firmasBySA = (List<TcFirmaSolicitudDetalle>) firmaSolicitudDetalleRepository.save(firmasBySA);
//		}
//		createPathImgs();

		contratosFirmas = contratosFirmaRepository
				.getFirmasAllByContratoId(contratosList.get(currentIndex).getContrato().getId());
		if (contratosFirmas.isEmpty()) {
			for (int i = 1; i < 4; i++) {
				contratosFirmas.add(new TcContratosFirma(i, null, contratosList.get(currentIndex).getContrato()));
			}
			contratosFirmas = (List<TcContratosFirma>) contratosFirmaRepository.saveAll(contratosFirmas);
		}
		createPathImgs();
	}

	/**
	 * Listener de la tabla de usarios para asignar un revisor a la solicitud actual
	 */
	public void onDlClickTbUsers(final SelectEvent event) {
		TcUsuario user = (TcUsuario) event.getObject();
		AbstractMB mbMessage = new AbstractMB();
		try {
			String msg = "Se ha asignado de revisor ha: " + user.getNombre();
			contratosList.get(currentIndex).getContrato().setRevisor(user);
			contratosService.saveContrato(contratosList.get(currentIndex));
			mbMessage.displayInfoMessage(msg);
		} catch (Exception e) {
			e.printStackTrace();
			mbMessage.displayInfoMessage("Ha ocurrido un error");
		}

	}

	public void firmarSolicitud() {
//		firmasBySA = firmasBySA.stream().map(f -> {
//			if (f.getPosicion() == firmaPosicion) {
//				f.setEstadoFirmado();
//				f.setFirmadoEl(new Date());
//				f = firmaSolicitudDetalleRepository.save(f);
//				displayInfoMessage("la solicitud se ha firmado correctamente");
//			}
//			return f;
//		}).collect(Collectors.toList());
	}

	public void changeStep(Integer number) {
		this.currentStep = number;
	}

	public void nextStep() {
		this.currentStep = this.currentStep == 4 ? 4 : this.currentStep + 1;
	}

	public void prevStep() {
		this.currentStep = this.currentStep == 0 ? 0 : this.currentStep - 1;
	}

	private void showError(String id, String error) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(error));
	}

	/**
	 * Crea las firmas en el directorio temporal para los detalles que tengan una
	 * firma asignada
	 */
	private void createPathImgs() {
//		firmasBySA.stream().filter(f -> f.getFirma() != null).map(f -> f.getFirma())
//				.forEach(f -> ImagesFirmasDigitales.createPathTempFromImg(f.getNombreArchivo(), f.getArchivo()));

		contratosFirmas.stream().filter(f -> f.getFirma() != null).map(f -> f.getFirma())
				.forEach(f -> ImagesFirmasDigitales.createPathTempFromImg(f.getNombreArchivo(), f.getArchivo()));
	}

	public void generatePDF() {
		this.createFilePdfInline();
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();

		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		System.out.println("id->" + this.contratosList.get(currentIndex).getContrato().getId());

		parameters.put("idContrato", this.contratosList.get(currentIndex).getContrato().getId());
		parameters.put("sector", this.getUserDetails().getIdSector());
		parameters.put("imagePath1", conctb.getImagePathLeft());
		parameters.put("dependenciaName", conctb.getNomDep());

		return parameters;

	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public void showListado() {
		this.showContratosReview = !this.showContratosReview;
	}

//	private Boolean isValid(Integer valor) {
//		return valor == null ? false : (valor <= 0 ? false : true);
//	}

	private Boolean isValid(BigDecimal valor) {
		return valor == null ? false : (valor.compareTo(BigDecimal.ZERO) <= 0 ? false : true);
	}

	private Boolean isValid(String valor) {
		return valor == null ? false : (valor.equals("") ? false : true);
	}

	private Boolean isValid(Date valor) {
		return valor == null ? false : true;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Integer getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(Integer currentStep) {
		this.currentStep = currentStep;
	}

	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}

	public ContratosService getContratosService() {
		return contratosService;
	}

	public void setContratosService(ContratosService contratosService) {
		this.contratosService = contratosService;
	}

	public List<TcTipoGasto> getTipoGastosList() {
		return tipoGastosList;
	}

	public List<TrContratoDetail> getContratosList() {
		return contratosList;
	}

	public void setContratosList(List<TrContratoDetail> contratosList) {
		this.contratosList = contratosList;
	}

	public void setTipoGastosList(List<TcTipoGasto> tipoGastosList) {
		this.tipoGastosList = tipoGastosList;
	}

	public List<TcTipoContrato> getTipoContratosList() {
		return tipoContratosList;
	}

	public void setTipoContratosList(List<TcTipoContrato> tipoContratosList) {
		this.tipoContratosList = tipoContratosList;
	}

	public List<TcOrigenRecurso> getOrigenRecursosList() {
		return origenRecursosList;
	}

	public void setOrigenRecursosList(List<TcOrigenRecurso> origenRecursosList) {
		this.origenRecursosList = origenRecursosList;
	}

	public List<TcProcedimientoAdquisitivo> getProcedimientosList() {
		return procedimientosList;
	}

	public void setProcedimientosList(List<TcProcedimientoAdquisitivo> procedimientosList) {
		this.procedimientosList = procedimientosList;
	}

	public List<TcAdquisicion> getAdquisicionSelected() {
		return adquisicionSelected;
	}

	public void setAdquisicionSelected(List<TcAdquisicion> adquisicionSelected) {
		this.adquisicionSelected = adquisicionSelected;
	}

	public List<TcAdquisicion> getAdquisicionList() {
		return adquisicionList;
	}

	public void setAdquisicionList(List<TcAdquisicion> adquisicionList) {
		this.adquisicionList = adquisicionList;
	}

	public ProveedorNuevo getProveedorSelected() {
		return proveedorSelected;
	}

	public void setProveedorSelected(ProveedorNuevo proveedorSelected) {
		this.proveedorSelected = proveedorSelected;
	}

	public List<ProveedorNuevo> getProveedorList() {
		return proveedorList;
	}

	public void setProveedorList(List<ProveedorNuevo> proveedorList) {
		this.proveedorList = proveedorList;
	}

	public List<TcArticulosSA> getArticulosList() {
		return articulosList;
	}

	public void setArticulosList(List<TcArticulosSA> articulosList) {
		this.articulosList = articulosList;
	}

	public List<TcArticulosSA> getArticulosSelected() {
		return articulosSelected;
	}

	public void setArticulosSelected(List<TcArticulosSA> articulosSelected) {
		this.articulosSelected = articulosSelected;
	}

	public List<TcArticulosContrato> getArticulosContratosList() {
		return articulosContratosList;
	}

	public void setArticulosContratosList(List<TcArticulosContrato> articulosContratosList) {
		this.articulosContratosList = articulosContratosList;
	}

	public List<TcUsuario> getUsuariosList() {
		return usuariosList;
	}

	public void setUsuariosList(List<TcUsuario> usuariosList) {
		this.usuariosList = usuariosList;
	}

	public List<TrContratoDetail> getContratosForReview() {
		return contratosForReview;
	}

	public void setContratosForReview(List<TrContratoDetail> contratosForReview) {
		this.contratosForReview = contratosForReview;
	}

	public TcContratosRevison getRevison() {
		return revison;
	}

	public void setRevison(TcContratosRevison revison) {
		this.revison = revison;
	}

	public List<TcContratosFirma> getContratosFirmas() {
		return contratosFirmas;
	}

	public void setContratosFirmas(List<TcContratosFirma> contratosFirmas) {
		this.contratosFirmas = contratosFirmas;
	}

	public Integer getFirmaPosicion() {
		return firmaPosicion;
	}

	public void setFirmaPosicion(Integer firmaPosicion) {
		this.firmaPosicion = firmaPosicion;
	}

	public Boolean getShowContratosReview() {
		return showContratosReview;
	}

	public void setShowContratosReview(Boolean showContratosReview) {
		this.showContratosReview = showContratosReview;
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

	public TcContratosFirmaRepository getContratosFirmaRepository() {
		return contratosFirmaRepository;
	}

	public void setContratosFirmaRepository(TcContratosFirmaRepository contratosFirmaRepository) {
		this.contratosFirmaRepository = contratosFirmaRepository;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public TcConvenio getConvenio() {
		return convenio;
	}

	public void setConvenio(TcConvenio convenio) {
		this.convenio = convenio;
	}

	public Boolean getConvenioEdit() {
		return convenioEdit;
	}

	public void setConvenioEdit(Boolean convenioEdit) {
		this.convenioEdit = convenioEdit;
	}

}
