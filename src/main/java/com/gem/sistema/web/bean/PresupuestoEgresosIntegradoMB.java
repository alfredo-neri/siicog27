package com.gem.sistema.web.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.gem.sistema.business.domain.IntegradoEgreso;
import com.gem.sistema.business.domain.TcMes;

import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.PresupuestoIntegradoService;
import com.gem.sistema.business.service.reportador.ReportadorTXTServiceImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class PresupuestoEgresosIntegradoMB.
 */
@ManagedBean(name = "pesupuestoEgresosIntegradoMB")
@SessionScoped
public class PresupuestoEgresosIntegradoMB extends AbstractMB {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReconducPresupEgrIngrTxtMB.class);

	/** The presupuesto integrado service. */
	@ManagedProperty(value = "#{presupuestoIntegradoService}")
	private PresupuestoIntegradoService presupuestoIntegradoService;

	/** The tc mes repository. */
	@ManagedProperty(value = "#{tcMesRepository}"  )
	private TcMesRepository tcMesRepository;

	/** The cve ref. */
	private Integer cveRef=0;
	
	/** The mes. */
	private Integer mes;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The list ref. */
	private Map<Integer, String> listRef;
	
	/** The stream content. */
	private StreamedContent streamContent;

	/**
	 * Gets the stream content.
	 *
	 * @return the stream content
	 */
	public StreamedContent getStreamContent() {
		return streamContent;
	}

	/**
	 * Sets the stream content.
	 *
	 * @param streamContent the new stream content
	 */
	public void setStreamContent(StreamedContent streamContent) {
		this.streamContent = streamContent;
	}

	/**
	 * Gets the list ref.
	 *
	 * @return the list ref
	 */
	public Map<Integer, String> getListRef() {
		return listRef;
	}

	/**
	 * Sets the list ref.
	 *
	 * @param listRef the list ref
	 */
	public void setListRef(Map<Integer, String> listRef) {
		this.listRef = listRef;
	}

	/**
	 * Gets the presupuesto integrado service.
	 *
	 * @return the presupuesto integrado service
	 */
	public PresupuestoIntegradoService getPresupuestoIntegradoService() {
		return presupuestoIntegradoService;
	}

	/**
	 * Sets the presupuesto integrado service.
	 *
	 * @param presupuestoIntegradoService the new presupuesto integrado service
	 */
	public void setPresupuestoIntegradoService(PresupuestoIntegradoService presupuestoIntegradoService) {
		this.presupuestoIntegradoService = presupuestoIntegradoService;
	}

	/**
	 * Gets the tc mes repository.
	 *
	 * @return the tc mes repository
	 */
	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	/**
	 * Sets the tc mes repository.
	 *
	 * @param tcMesRepository the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	
	/**
	 * Gets the cve ref.
	 *
	 * @return the cve ref
	 */
	public Integer getCveRef() {
		return cveRef;
	}

	/**
	 * Sets the cve ref.
	 *
	 * @param cveRef the new cve ref
	 */
	public void setCveRef(Integer cveRef) {
		this.cveRef = cveRef;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}

	/**
	 * Gets the list mes.
	 *
	 * @return the list mes
	 */
	public List<TcMes> getListMes() {
		return listMes;
	}

	/**
	 * Sets the list mes.
	 *
	 * @param listMes the new list mes
	 */
	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
	}
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		listMes = tcMesRepository.findAll();
		listRef = new HashMap<Integer, String>();
		listRef.put(0, "Ayuntamiento");
		listRef.put(1, "Organismo de Agua");
		listRef.put(2, "Dif Municipal");
		listRef.put(3, "Oragnismo del Deporte");
		listRef.put(4, "Mavic");
		
	}

	/** The stream. */
	InputStream stream = null;
    
    /** The out. */
    Map<String, Object> out;
    
	/**
	 * Genetare file.
	 */
	public void genetareFile(){
		if(isNotNullCveRef(cveRef) == Boolean.TRUE) {
			IntegradoEgreso integrad = presupuestoIntegradoService.exeuteIngresoIntegrado(this.getUserDetails().getUsername(), mes, cveRef, this.getUserDetails().getIdMunicipio());
//			IntegradoEgreso integrad = new IntegradoEgreso();
//			integrad.setCodError(1);
//			integrad.setMsgError("OK");
			if(integrad.getCodError() > 0) {
				String pathToSenUser = integrad.getPathFile() + "/" + integrad.getFileName();
				try {
	                stream = new FileInputStream(
	                        new File(pathToSenUser));
	            } catch (FileNotFoundException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
				streamContent = new DefaultStreamedContent(stream, "application/txt", integrad.getFileName());
	            
				//sendFileToUser(pathToSenUser);
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", integrad.getMsgError());
			    FacesContext.getCurrentInstance().addMessage(null, message);
			    //sendFileToUser(pathToSenUser);
			    
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", integrad.getMsgError());
			    FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione una opcion");
		    FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	
	/**
	 * Checks if is not null cve ref.
	 *
	 * @param cveRef the cve ref
	 * @return true, if is not null cve ref
	 */
	public boolean isNotNullCveRef(Integer cveRef){
		if(cveRef != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Send file to user.
	 *
	 * @param path the path
	 */
	public void sendFileToUser(String path) {
		FileInputStream fileInputStream = null;
		OutputStream output = null;
		try {
			
			File fileToSend = new File(path);
			byte[] ingresoRespFile = new byte[(int) fileToSend.length()];
			fileInputStream = new FileInputStream(fileToSend);
			fileInputStream.read(ingresoRespFile);

			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

			String fileName = fileToSend.getName();
			fileToSend.delete();
			response.reset();
			response.setContentType("text/plain");
			response.setCharacterEncoding(ReportadorTXTServiceImpl.CHARSET);
			response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");

			output = response.getOutputStream();
			output.write(ingresoRespFile);
			facesContext.responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != fileInputStream) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}

			if (null != output) {
				try {
					output.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
	}
}
