package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.data.PageEvent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Pm4811;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.Pm4811Repository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteIndAnualIncrementoRecaudacionMB.
 */
@ManagedBean
@ViewScoped
public class ReporteIndAnualIncrementoRecaudacionMB extends BaseDirectReport {


	/** The master list. */
	private List<Pm4811> masterList = new ArrayList<Pm4811>();
	
	/** The boton deshabilitado reset. */
	private boolean botonDeshabilitadoReset;
	
	/** The boton deshabilitado adicionar. */
	private boolean botonDeshabilitadoAdicionar;
	
	/** The boton deshabilitado borrar. */
	private boolean botonDeshabilitadoBorrar;
	
	/** The boton deshabilitado cancelar. */
	private boolean botonDeshabilitadoCancelar;
	
	/** The boton visible salvar. */
	private boolean botonVisibleSalvar;
	
	/** The boton visible modificar. */
	private boolean botonVisibleModificar; 
	
	/** The campos deshabilitados. */
	private boolean camposDeshabilitados;
	
	/** The current page. */
	private int currentPage = 0;
	
	/** The conctb. */
	private Conctb conctb;
	
	/** The pm 4811 repository. */
	@ManagedProperty("#{pm4811Repository}")
	private Pm4811Repository pm4811Repository;
	
	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	/**
	 * Gets the conctb.
	 *
	 * @return the conctb
	 */
	public Conctb getConctb() {
		return conctb;
	}

	/**
	 * Sets the conctb.
	 *
	 * @param conctb the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct ReporteIndAnualIncrementoRecaudacionMB ");
		jasperReporteName = "reporte152";
		endFilename = jasperReporteName + ".pdf";
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String,Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();
		
		parameters.put("year", conctb.getAnoemp());
		parameters.put("idSector", idSector);
		parameters.put("municipioName", conctb.getNomDep());
		parameters.put("municipioClave", conctb.getClave());
		parameters.put("pathImage", this.getUserDetails().getPathImgCab1()); 
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
		parameters.put("firmaP1", firma.getPuesto().getPuesto());
		parameters.put("firmaP2", firma.getPuesto().getPuesto());
		parameters.put("firmaN1", firma.getNombre());
		parameters.put("firmaN2", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F11.getValue());
		parameters.put("firmaP3", firma.getPuesto().getPuesto());
		parameters.put("firmaN3", firma.getNombre());
		return parameters;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		return null;
	}
	
	/**
	 * metodo que se llama cuando se carga la pagina Pm4811.
	 */
	public void onPageLoad() {
		this.findAllPm4811();
	}

	/**
	 * metodo que busca todas las pm4811 en la base de datos y pone los valores iniciales.
	 */
	private void findAllPm4811(){
		masterList.clear();
		masterList = pm4811Repository.findAll();
		this.estadoInicialBotones();
		if(masterList.isEmpty()){
			Pm4811 pm4811 = new Pm4811();
			masterList.add(pm4811);
		}

	}
	
	/**
	 * metodo que actualiza el current page de la lista  dependiendo de la pagina seleccionada.
	 *
	 * @param event the event
	 */
	public void cambiarPagina(PageEvent event){
		currentPage = event.getPage();
	}
	
	/**
	 * establece el estado inicial de los botones.
	 */
	public void estadoInicialBotones(){
		if (!pm4811Repository.findAll().isEmpty()) {
			this.setBotonVisibleModificar(true);
			this.setBotonVisibleSalvar(false);
			this.setBotonDeshabilitadoReset(true);
			this.setBotonDeshabilitadoAdicionar(true);
			this.setBotonDeshabilitadoBorrar(false);
			this.setBotonDeshabilitadoCancelar(true);
			this.setCamposDeshabilitados(true);
		} else {		
			this.setBotonVisibleModificar(false);
			this.setBotonVisibleSalvar(false);
			this.setBotonDeshabilitadoReset(true);
			this.setBotonDeshabilitadoAdicionar(false);
			this.setBotonDeshabilitadoBorrar(true);
			this.setBotonDeshabilitadoCancelar(true);
			this.setCamposDeshabilitados(true);
		}
	}
	
	/**
	 * Modifica un registro editado de Pm4811.
	 */
	public void modificar(){
		masterList.clear();
		masterList = pm4811Repository.findAll();
		if(masterList.isEmpty()){
			Pm4811 pm4811 = new Pm4811();
				pm4811.setAnual(conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getAnoemp());
			masterList.add(pm4811);
		}
		this.setBotonVisibleSalvar(true);
		this.setBotonVisibleModificar(false);
		this.setBotonDeshabilitadoAdicionar(true);
		this.setBotonDeshabilitadoBorrar(true);
		this.setBotonDeshabilitadoReset(false);
		this.setBotonDeshabilitadoCancelar(false);
		this.setCamposDeshabilitados(false);
	}
	
	/**
	 * metodo que guarda una entidad en Pm4811.
	 */
	public void salvar(){
		try {
			masterList.get(currentPage).setFeccap(new Date());
			masterList.get(currentPage).setUserId(this.getUserDetails().getUsername());
			masterList.get(currentPage).setIdSector(this.getUserDetails().getIdSector());
			masterList.get(currentPage).setCapturo(this.getUserDetails().getUsername());
			masterList.get(currentPage).setIdRef(0);
			
			if (null == masterList.get(currentPage).getRta()){
				masterList.get(currentPage).setRta(BigDecimal.ZERO);
			}
			if (null == masterList.get(currentPage).getRtaa()){
				masterList.get(currentPage).setRtaa(BigDecimal.ZERO);
			}
			if (null == masterList.get(currentPage).getObsrta()){
				masterList.get(currentPage).setObsrta("");
			}
			if (null == masterList.get(currentPage).getObsrtaa()){
				masterList.get(currentPage).setObsrtaa("");
			}
			
			pm4811Repository.save(masterList.get(currentPage));
			
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Registros Guardados",""));

			this.estadoInicialBotones();
		} catch (Exception e) {
			generateNotificationFront(SEVERITY_ERROR, "El año ya existe",
					"");			
		}	
	}
	
	/**
	 * Resetea el registro actual y si es nuevo registro lo deja en blanco.
	 */
	public void reset(){
		masterList.clear();
		masterList = pm4811Repository.findAll();
		if(masterList.size()==0){
			masterList.clear();
			Pm4811 pm4811 = new Pm4811();
				pm4811.setAnual(conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getAnoemp());
			masterList.add(pm4811);	
			this.setBotonVisibleSalvar(true);
			this.setBotonVisibleModificar(false);
			this.setBotonDeshabilitadoAdicionar(true);
			this.setBotonDeshabilitadoBorrar(true);
			this.setBotonDeshabilitadoCancelar(false);
			this.setBotonDeshabilitadoReset(false);
			this.setCamposDeshabilitados(false);
		}else{
			masterList.clear();
			masterList = pm4811Repository.findAll();
			this.setBotonVisibleSalvar(true);
			this.setBotonVisibleModificar(false);
			this.setBotonDeshabilitadoAdicionar(true);
			this.setBotonDeshabilitadoBorrar(true);
			this.setBotonDeshabilitadoCancelar(false);
			this.setBotonDeshabilitadoReset(false);
			this.setCamposDeshabilitados(false);
		}	
	}

	/**
	 * generacion del reporte.
	 */
	public void createFilePdfInlineValidate() {
			createFilePdfInline();
	}
	
	/**
	 * borra un registro de pm4811.
	 */
	public void borrar(){
		masterList.clear();
		masterList = pm4811Repository.findAll();
		try{
			boolean answer=false;
			try{
				pm4811Repository.delete(Long.valueOf(masterList.get(currentPage).getId().toString()).longValue());
				answer = true;
			}catch(Exception e){
				answer = false;
			}
			if(answer){
				masterList.clear();
				masterList = pm4811Repository.findAll();
				if(masterList.isEmpty()){
					Pm4811 pm4811 = new Pm4811();
						pm4811.setAnual(conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getAnoemp());
					masterList.add(pm4811);	
					this.setBotonDeshabilitadoBorrar(true);
					this.setBotonVisibleModificar(false);
					this.setBotonVisibleSalvar(false);
					this.setBotonDeshabilitadoAdicionar(false);
				}
				this.setCamposDeshabilitados(true);
				FacesContext context = FacesContext.getCurrentInstance();	       
			    context.addMessage(null, new FacesMessage("Registros Eliminados", ""));
			}else{
				masterList.clear();
				masterList = pm4811Repository.findAll();
				if(masterList.isEmpty()){
					Pm4811 pm4811 = new Pm4811();
						pm4811.setAnual(conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getAnoemp());
					masterList.add(pm4811);	
					this.setBotonDeshabilitadoBorrar(true);
					this.setBotonVisibleModificar(false);
					this.setBotonVisibleSalvar(false);
				}
				}					
		}catch(Exception e){
			generateNotificationFront(SEVERITY_ERROR, "", 
					"Ocurrió un error al borrar el Reporte Anual de Incremento en la Recaudación");
		}	
	}
	
	/**
	 * cancela la accion de guardar o modificar un registro.
	 */
	public void cancelar(){
		masterList.clear();
		masterList = pm4811Repository.findAll();
		if(masterList.size()==0){
			Pm4811 pm4811 = new Pm4811();
				pm4811.setAnual(conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getAnoemp());
			masterList.add(pm4811);	
			
			this.setBotonVisibleSalvar(false);
			this.setBotonVisibleModificar(false);
			this.setBotonDeshabilitadoAdicionar(false);
			this.setBotonDeshabilitadoBorrar(true);
			this.setBotonDeshabilitadoCancelar(true);
			this.setBotonDeshabilitadoReset(true);
			this.setCamposDeshabilitados(true);
		}else{
			this.setBotonVisibleSalvar(false);
			this.setBotonVisibleModificar(true);
			this.setBotonDeshabilitadoAdicionar(true);
			this.setBotonDeshabilitadoBorrar(false);
			this.setBotonDeshabilitadoCancelar(true);
			this.setBotonDeshabilitadoReset(true);
			this.setCamposDeshabilitados(true);
		}
	}
	
	/**
	 * adiciona un Pm4811 a la lista.
	 */
	public void adicionar(){
		this.setBotonVisibleSalvar(true);
		this.setBotonVisibleModificar(false);
		this.setBotonDeshabilitadoAdicionar(true);
		this.setBotonDeshabilitadoBorrar(true);
		this.setBotonDeshabilitadoCancelar(false);
		this.setBotonDeshabilitadoReset(false);
		if(pm4811Repository.findAll().isEmpty()){
			this.modificar();
		}else{
			Pm4811 pm4811 = new Pm4811();
				pm4811.setAnual(conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getAnoemp());
			masterList.add(pm4811);
			this.setCamposDeshabilitados(false);
			
		}
	}


	/**
	 * Gets the master list.
	 *
	 * @return the master list
	 */
	public List<Pm4811> getMasterList() {
		return masterList;
	}

	/**
	 * Sets the master list.
	 *
	 * @param masterList the new master list
	 */
	public void setMasterList(List<Pm4811> masterList) {
		this.masterList = masterList;
	}

	/**
	 * Checks if is boton deshabilitado reset.
	 *
	 * @return true, if is boton deshabilitado reset
	 */
	public boolean isBotonDeshabilitadoReset() {
		return botonDeshabilitadoReset;
	}

	/**
	 * Sets the boton deshabilitado reset.
	 *
	 * @param botonDeshabilitadoReset the new boton deshabilitado reset
	 */
	public void setBotonDeshabilitadoReset(boolean botonDeshabilitadoReset) {
		this.botonDeshabilitadoReset = botonDeshabilitadoReset;
	}

	/**
	 * Checks if is boton deshabilitado adicionar.
	 *
	 * @return true, if is boton deshabilitado adicionar
	 */
	public boolean isBotonDeshabilitadoAdicionar() {
		return botonDeshabilitadoAdicionar;
	}

	/**
	 * Sets the boton deshabilitado adicionar.
	 *
	 * @param botonDeshabilitadoAdicionar the new boton deshabilitado adicionar
	 */
	public void setBotonDeshabilitadoAdicionar(boolean botonDeshabilitadoAdicionar) {
		this.botonDeshabilitadoAdicionar = botonDeshabilitadoAdicionar;
	}

	/**
	 * Checks if is boton deshabilitado borrar.
	 *
	 * @return true, if is boton deshabilitado borrar
	 */
	public boolean isBotonDeshabilitadoBorrar() {
		return botonDeshabilitadoBorrar;
	}

	/**
	 * Sets the boton deshabilitado borrar.
	 *
	 * @param botonDeshabilitadoBorrar the new boton deshabilitado borrar
	 */
	public void setBotonDeshabilitadoBorrar(boolean botonDeshabilitadoBorrar) {
		this.botonDeshabilitadoBorrar = botonDeshabilitadoBorrar;
	}

	/**
	 * Checks if is boton deshabilitado cancelar.
	 *
	 * @return true, if is boton deshabilitado cancelar
	 */
	public boolean isBotonDeshabilitadoCancelar() {
		return botonDeshabilitadoCancelar;
	}

	/**
	 * Sets the boton deshabilitado cancelar.
	 *
	 * @param botonDeshabilitadoCancelar the new boton deshabilitado cancelar
	 */
	public void setBotonDeshabilitadoCancelar(boolean botonDeshabilitadoCancelar) {
		this.botonDeshabilitadoCancelar = botonDeshabilitadoCancelar;
	}

	/**
	 * Checks if is boton visible salvar.
	 *
	 * @return true, if is boton visible salvar
	 */
	public boolean isBotonVisibleSalvar() {
		return botonVisibleSalvar;
	}

	/**
	 * Sets the boton visible salvar.
	 *
	 * @param botonVisibleSalvar the new boton visible salvar
	 */
	public void setBotonVisibleSalvar(boolean botonVisibleSalvar) {
		this.botonVisibleSalvar = botonVisibleSalvar;
	}

	/**
	 * Checks if is boton visible modificar.
	 *
	 * @return true, if is boton visible modificar
	 */
	public boolean isBotonVisibleModificar() {
		return botonVisibleModificar;
	}

	/**
	 * Sets the boton visible modificar.
	 *
	 * @param botonVisibleModificar the new boton visible modificar
	 */
	public void setBotonVisibleModificar(boolean botonVisibleModificar) {
		this.botonVisibleModificar = botonVisibleModificar;
	}

	/**
	 * Checks if is campos deshabilitados.
	 *
	 * @return true, if is campos deshabilitados
	 */
	public boolean isCamposDeshabilitados() {
		return camposDeshabilitados;
	}

	/**
	 * Sets the campos deshabilitados.
	 *
	 * @param camposDeshabilitados the new campos deshabilitados
	 */
	public void setCamposDeshabilitados(boolean camposDeshabilitados) {
		this.camposDeshabilitados = camposDeshabilitados;
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
	 * Gets the pm 4811 repository.
	 *
	 * @return the pm 4811 repository
	 */
	public Pm4811Repository getPm4811Repository() {
		return pm4811Repository;
	}

	/**
	 * Sets the pm 4811 repository.
	 *
	 * @param pm4811Repository the new pm 4811 repository
	 */
	public void setPm4811Repository(Pm4811Repository pm4811Repository) {
		this.pm4811Repository = pm4811Repository;
	}

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}
	
	
	
}
