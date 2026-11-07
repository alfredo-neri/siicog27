package com.gem.sistema.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Integradoe;
import com.gem.sistema.business.domain.Natgas;
import com.gem.sistema.business.repository.catalogs.IntegradoeRepository;
import com.gem.sistema.business.repository.catalogs.NatgasRepository;
import com.gem.sistema.web.datamodel.NatgasDataModel;

// TODO: Auto-generated Javadoc
/**
 * The Class CmIngresoPresupInteMB.
 *
 * @author Gauss
 */
@ManagedBean(name="cmIngresoPresupInteMB")
@ViewScoped
public class CmIngresoPresupInteMB extends AbstractMB implements Serializable {

	/**
	 * Serial default.
	 */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CmIngresoPresupInteMB.class);

	/** The natgas data model. */
	@ManagedProperty( value = "#{natgasDataModel}")
	private NatgasDataModel natgasDataModel;

	/** The selected natgas. */
	private Natgas selectedNatgas;
	
	/** The mirror integradoe. */
	private Integradoe mirrorIntegradoe;
	
	/** The selected integradoe. */
	private Integradoe selectedIntegradoe;

	/** The new integradoe. */
	private Integradoe newIntegradoe;

	/** The natgas repository. */
	@ManagedProperty(value = "#{natgasRepository}")
	private NatgasRepository natgasRepository;
	
	/** The integradoe repository. */
	@ManagedProperty(value = "#{integradoeRepository}")	
	private IntegradoeRepository integradoeRepository;
	
	/** The existen registros. */
	private Boolean existenRegistros;
	
	/** The activar salvar. */
	private Boolean activarSalvar;
	
	/** The activar modificar. */
	private Boolean activarModificar;
	
	/** The activar reset. */
	private Boolean activarReset;
	
	/** The activar adicionar. */
	private Boolean activarAdicionar;
	
	/** The activar borrar. */
	private Boolean activarBorrar;
	
	/** The activar cancelar. */
	private Boolean activarCancelar;
	
	/** The son editables. */
	private Boolean sonEditables;

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconstruct NatGasMB ");
		existenRegistros = Boolean.FALSE;
		
		//System.out.println(this.getUserDetails());		
		natgasDataModel.setIdSector(this.getUserDetails().getIdSector());
		
	}

	/**
	 * Prepara un nuevo Usuario.
	 *
	 * @param actionEvent the action event
	 */
	public void prepareInsert(ActionEvent actionEvent) {
		newIntegradoe = new Integradoe();
	}
	
	
	/**
	 * Prepara un nuevo Usuario.
	 *
	 * @param actionEvent the action event
	 */
	public void prepareUpdate(ActionEvent actionEvent) {
		LOGGER.debug( "usuario::" +selectedIntegradoe.getPartida()   );			
		mirrorIntegradoe = integradoeRepository.findByPartida(selectedNatgas.getClvgas());
		selectedIntegradoe = integradoeRepository.findByPartida(selectedNatgas.getClvgas());
		
	}
	
	
	
	
	/**
	 * Actualizar.
	 *
	 * @param actionEvent the action event
	 */
	public void actualizar(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", String.format("Partida  %1$s  Actualizada correctamente", selectedIntegradoe.getPartida()) );

		try {
			integradoeRepository.save(selectedIntegradoe);
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
		}
		
		activarReset =Boolean.FALSE;

		context.addMessage(null, msg);
	}

	/**
	 * Insert.
	 *
	 * @param actionEvent the action event
	 */
	public void insert(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = null;

		try {

			integradoeRepository.save(selectedIntegradoe);
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", String.format("Partida [ %1$s ] Agregada correctamente", newIntegradoe.getPartida()) );
		} catch (Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
		}

		context.addMessage(null, msg);
	}

	
	/**
	 * Borrar.
	 *
	 * @param actionEvent the action event
	 */
	public void borrar(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = null;
		
		final String partida = selectedIntegradoe.getPartida();

		try {
			integradoeRepository.delete(selectedIntegradoe);
			existenRegistros=Boolean.FALSE;
			sonEditables =Boolean.FALSE;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", String.format("Partida [ %1$s ] Eliminada correctamente", partida) );
		} catch (Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
		}

		context.addMessage(null, msg);
	}
	
	
	/**
	 * On row select.
	 *
	 * @param event the event
	 */
	public void onRowSelect(SelectEvent event) {
		 final Natgas natgas = (Natgas) event.getObject();
		 
		 FacesMessage msg = new FacesMessage("Nat Selected", natgas.getClvgas() );
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        
	      selectedIntegradoe = integradoeRepository.findByPartida(natgas.getClvgas());
	      mirrorIntegradoe =  integradoeRepository.findByPartida(natgas.getClvgas());
	      
	      if(null!=selectedIntegradoe){
	    	  existenRegistros=Boolean.TRUE;
	    	  activarAdicionar=Boolean.FALSE;
	    	  activarModificar=Boolean.TRUE;
	    	  activarBorrar =Boolean.TRUE;
	    	  
	      } else {
	    	  existenRegistros=Boolean.FALSE;
	    	  activarAdicionar=Boolean.TRUE;
	    	  activarModificar=Boolean.FALSE;
	    	  activarBorrar =Boolean.FALSE;
	    	  selectedIntegradoe=new Integradoe();
	    	  selectedIntegradoe.setPartida(natgas.getClvgas());
	      }
	      
	      activarSalvar = Boolean.FALSE;
    	  activarCancelar = Boolean.FALSE;
    	  activarReset =Boolean.FALSE;
	        
	    }
	
	
	/**
	 * Reset.
	 *
	 * @param actionEvent the action event
	 */
	public void reset(ActionEvent actionEvent) { 
		selectedIntegradoe = mirrorIntegradoe;
	}
	
	
	/**
	 * Cancel.
	 *
	 * @param actionEvent the action event
	 */
	public void cancel(ActionEvent actionEvent) { 
		selectedIntegradoe = mirrorIntegradoe;
		sonEditables =Boolean.FALSE;
		if( existenRegistros == Boolean.TRUE) {
			activarModificar =  Boolean.TRUE;
			activarBorrar =  Boolean.TRUE;
			activarAdicionar =Boolean.FALSE;
		}else {
			activarModificar =  Boolean.FALSE;
			activarBorrar =  Boolean.FALSE;
			activarAdicionar =Boolean.TRUE;
		}
		activarCancelar =  Boolean.FALSE;
	}
	
	/**
	 * Adicionar.
	 *
	 * @param actionEvent the action event
	 */
	public void adicionar(ActionEvent actionEvent) { 
		existenRegistros=Boolean.TRUE; //con esto se activa el formulario
		sonEditables =Boolean.TRUE;
		activarSalvar =Boolean.TRUE;
		activarCancelar =Boolean.TRUE;
		//Rellenar en ceros todas las cantidades
		selectedIntegradoe.setAguatoeje11(BigDecimal.ZERO);
		selectedIntegradoe.setAguatoeje12(BigDecimal.ZERO);
		selectedIntegradoe.setAguatoeje13(BigDecimal.ZERO);
		selectedIntegradoe.setAguatoeje14(BigDecimal.ZERO);
		selectedIntegradoe.setAguatoeje15(BigDecimal.ZERO);
		selectedIntegradoe.setAguatoeje16(BigDecimal.ZERO);
		selectedIntegradoe.setAguatoeje17(BigDecimal.ZERO);
		selectedIntegradoe.setAguatoeje18(BigDecimal.ZERO);
		selectedIntegradoe.setAguatoeje19(BigDecimal.ZERO);
		selectedIntegradoe.setAguatoeje110(BigDecimal.ZERO);
		selectedIntegradoe.setAguatoeje111(BigDecimal.ZERO);
		selectedIntegradoe.setAguatoeje112(BigDecimal.ZERO);
		selectedIntegradoe.setAyttotoeje11(BigDecimal.ZERO);
		selectedIntegradoe.setAyttotoeje12(BigDecimal.ZERO);
		selectedIntegradoe.setAyttotoeje13(BigDecimal.ZERO);
		selectedIntegradoe.setAyttotoeje14(BigDecimal.ZERO);
		selectedIntegradoe.setAyttotoeje15(BigDecimal.ZERO);
		selectedIntegradoe.setAyttotoeje16(BigDecimal.ZERO);
		selectedIntegradoe.setAyttotoeje17(BigDecimal.ZERO);
		selectedIntegradoe.setAyttotoeje18(BigDecimal.ZERO);
		selectedIntegradoe.setAyttotoeje19(BigDecimal.ZERO);
		selectedIntegradoe.setAyttotoeje110(BigDecimal.ZERO);
		selectedIntegradoe.setAyttotoeje111(BigDecimal.ZERO);
		selectedIntegradoe.setAyttotoeje112(BigDecimal.ZERO);
		selectedIntegradoe.setDeportetoeje11(BigDecimal.ZERO);
		selectedIntegradoe.setDeportetoeje12(BigDecimal.ZERO);
		selectedIntegradoe.setDeportetoeje13(BigDecimal.ZERO);
		selectedIntegradoe.setDeportetoeje14(BigDecimal.ZERO);
		selectedIntegradoe.setDeportetoeje15(BigDecimal.ZERO);
		selectedIntegradoe.setDeportetoeje16(BigDecimal.ZERO);
		selectedIntegradoe.setDeportetoeje17(BigDecimal.ZERO);
		selectedIntegradoe.setDeportetoeje18(BigDecimal.ZERO);
		selectedIntegradoe.setDeportetoeje19(BigDecimal.ZERO);
		selectedIntegradoe.setDeportetoeje110(BigDecimal.ZERO);
		selectedIntegradoe.setDeportetoeje111(BigDecimal.ZERO);
		selectedIntegradoe.setDeportetoeje112(BigDecimal.ZERO);
		selectedIntegradoe.setDiftoeje11(BigDecimal.ZERO);
		selectedIntegradoe.setDiftoeje12(BigDecimal.ZERO);
		selectedIntegradoe.setDiftoeje13(BigDecimal.ZERO);
		selectedIntegradoe.setDiftoeje14(BigDecimal.ZERO);
		selectedIntegradoe.setDiftoeje15(BigDecimal.ZERO);
		selectedIntegradoe.setDiftoeje16(BigDecimal.ZERO);
		selectedIntegradoe.setDiftoeje17(BigDecimal.ZERO);
		selectedIntegradoe.setDiftoeje18(BigDecimal.ZERO);
		selectedIntegradoe.setDiftoeje19(BigDecimal.ZERO);
		selectedIntegradoe.setDiftoeje110(BigDecimal.ZERO);
		selectedIntegradoe.setDiftoeje111(BigDecimal.ZERO);
		selectedIntegradoe.setDiftoeje112(BigDecimal.ZERO);

	}
	
	
	/**
	 * Modificar.
	 *
	 * @param actionEvent the action event
	 */
	public void modificar(ActionEvent actionEvent) { 
		sonEditables =Boolean.TRUE;
		activarCancelar =Boolean.TRUE;
		activarBorrar =  Boolean.FALSE;
		activarAdicionar =Boolean.FALSE;
		activarSalvar =Boolean.TRUE;
		activarReset =Boolean.TRUE;
	}
	
	
	
	
	
	
	/**
	 * Gets the natgas data model.
	 *
	 * @return the natgas data model
	 */
	public NatgasDataModel getNatgasDataModel() {
		return natgasDataModel;
	}

	/**
	 * Sets the natgas data model.
	 *
	 * @param natgasDataModel the new natgas data model
	 */
	public void setNatgasDataModel(NatgasDataModel natgasDataModel) {
		this.natgasDataModel = natgasDataModel;
	}

	/**
	 * Gets the selected natgas.
	 *
	 * @return the selected natgas
	 */
	public Natgas getSelectedNatgas() {
		return selectedNatgas;
	}

	/**
	 * Sets the selected natgas.
	 *
	 * @param selectedNatgas the new selected natgas
	 */
	public void setSelectedNatgas(Natgas selectedNatgas) {
		this.selectedNatgas = selectedNatgas;
	}

	/**
	 * Gets the mirror integradoe.
	 *
	 * @return the mirror integradoe
	 */
	public Integradoe getMirrorIntegradoe() {
		return mirrorIntegradoe;
	}

	/**
	 * Sets the mirror integradoe.
	 *
	 * @param mirrorIntegradoe the new mirror integradoe
	 */
	public void setMirrorIntegradoe(Integradoe mirrorIntegradoe) {
		this.mirrorIntegradoe = mirrorIntegradoe;
	}

	/**
	 * Gets the selected integradoe.
	 *
	 * @return the selected integradoe
	 */
	public Integradoe getSelectedIntegradoe() {
		return selectedIntegradoe;
	}

	/**
	 * Sets the selected integradoe.
	 *
	 * @param selectedIntegradoe the new selected integradoe
	 */
	public void setSelectedIntegradoe(Integradoe selectedIntegradoe) {
		this.selectedIntegradoe = selectedIntegradoe;
	}

	/**
	 * Gets the new integradoe.
	 *
	 * @return the new integradoe
	 */
	public Integradoe getNewIntegradoe() {
		return newIntegradoe;
	}

	/**
	 * Sets the new integradoe.
	 *
	 * @param newIntegradoe the new new integradoe
	 */
	public void setNewIntegradoe(Integradoe newIntegradoe) {
		this.newIntegradoe = newIntegradoe;
	}

	/**
	 * Gets the natgas repository.
	 *
	 * @return the natgas repository
	 */
	public NatgasRepository getNatgasRepository() {
		return natgasRepository;
	}

	/**
	 * Sets the natgas repository.
	 *
	 * @param natgasRepository the new natgas repository
	 */
	public void setNatgasRepository(NatgasRepository natgasRepository) {
		this.natgasRepository = natgasRepository;
	}

	/**
	 * Gets the integradoe repository.
	 *
	 * @return the integradoe repository
	 */
	public IntegradoeRepository getIntegradoeRepository() {
		return integradoeRepository;
	}

	/**
	 * Sets the integradoe repository.
	 *
	 * @param integradoeRepository the new integradoe repository
	 */
	public void setIntegradoeRepository(IntegradoeRepository integradoeRepository) {
		this.integradoeRepository = integradoeRepository;
	}

	/**
	 * Gets the existen registros.
	 *
	 * @return the existen registros
	 */
	public Boolean getExistenRegistros() {
		return existenRegistros;
	}

	/**
	 * Sets the existen registros.
	 *
	 * @param existenRegistros the new existen registros
	 */
	public void setExistenRegistros(Boolean existenRegistros) {
		this.existenRegistros = existenRegistros;
	}

	/**
	 * Gets the activar salvar.
	 *
	 * @return the activar salvar
	 */
	public Boolean getActivarSalvar() {
		return activarSalvar;
	}

	/**
	 * Sets the activar salvar.
	 *
	 * @param activarSalvar the new activar salvar
	 */
	public void setActivarSalvar(Boolean activarSalvar) {
		this.activarSalvar = activarSalvar;
	}

	/**
	 * Gets the activar modificar.
	 *
	 * @return the activar modificar
	 */
	public Boolean getActivarModificar() {
		return activarModificar;
	}

	/**
	 * Sets the activar modificar.
	 *
	 * @param activarModificar the new activar modificar
	 */
	public void setActivarModificar(Boolean activarModificar) {
		this.activarModificar = activarModificar;
	}

	/**
	 * Gets the activar reset.
	 *
	 * @return the activar reset
	 */
	public Boolean getActivarReset() {
		return activarReset;
	}

	/**
	 * Sets the activar reset.
	 *
	 * @param activarReset the new activar reset
	 */
	public void setActivarReset(Boolean activarReset) {
		this.activarReset = activarReset;
	}

	/**
	 * Gets the activar adicionar.
	 *
	 * @return the activar adicionar
	 */
	public Boolean getActivarAdicionar() {
		return activarAdicionar;
	}

	/**
	 * Sets the activar adicionar.
	 *
	 * @param activarAdicionar the new activar adicionar
	 */
	public void setActivarAdicionar(Boolean activarAdicionar) {
		this.activarAdicionar = activarAdicionar;
	}

	/**
	 * Gets the activar borrar.
	 *
	 * @return the activar borrar
	 */
	public Boolean getActivarBorrar() {
		return activarBorrar;
	}

	/**
	 * Sets the activar borrar.
	 *
	 * @param activarBorrar the new activar borrar
	 */
	public void setActivarBorrar(Boolean activarBorrar) {
		this.activarBorrar = activarBorrar;
	}

	/**
	 * Gets the activar cancelar.
	 *
	 * @return the activar cancelar
	 */
	public Boolean getActivarCancelar() {
		return activarCancelar;
	}

	/**
	 * Sets the activar cancelar.
	 *
	 * @param activarCancelar the new activar cancelar
	 */
	public void setActivarCancelar(Boolean activarCancelar) {
		this.activarCancelar = activarCancelar;
	}

	/**
	 * Gets the son editables.
	 *
	 * @return the son editables
	 */
	public Boolean getSonEditables() {
		return sonEditables;
	}

	/**
	 * Sets the son editables.
	 *
	 * @param sonEditables the new son editables
	 */
	public void setSonEditables(Boolean sonEditables) {
		this.sonEditables = sonEditables;
	}


	
	




}
