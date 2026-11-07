package com.gem.sistema.web.bean;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.service.callsp.ExecutePlService;

// TODO: Auto-generated Javadoc
/**
 * The Class InventarioGeneralBienesMueblesCargaAutomaticaMB.
 */
@ManagedBean(name = "inventarioGeneralBienesMueblesCargaAutomaticaMB")
@ViewScoped
public class InventarioGeneralBienesMueblesCargaAutomaticaMB extends AbstractMB {

	private static final String PROCEDURE_NAME = "SP_CARGA_ARCHIVO_BIENES_MUEBLES";
	private static final String PATH_UPFILES = "/gem/upfiles/";

	private File file;

	@ManagedProperty("#{executePlService}")
	private ExecutePlService executePlService;

	private Integer alMes;

	private UploadedFile archivo;

	@PostConstruct
	public void init() {
		alMes = 1;

	}

	public void handleFileUpload(FileUploadEvent event) {
		Map<String, Object> out = new HashMap<>();

		archivo = event.getFile();
		file = new File(PATH_UPFILES + UUID.randomUUID() + archivo.getFileName());

		if (file != null) {
			if (archivo.getFileName().equals("bienesm.csv")) {

				try {

					FileUtils.copyInputStreamToFile(archivo.getInputstream(), file);

					if (file.length() > 0) {

						out = executePlService.callProcedure(PROCEDURE_NAME, getParameters());

						if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
							FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, StringUtils.EMPTY,
									out.get("O_MSG_ERROR").toString());
							FacesContext.getCurrentInstance().addMessage(null, message);
						} else {
							FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY,
									"La Información se ha cargado correctamente");
							FacesContext.getCurrentInstance().addMessage(null, message);
						}

					} else {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, StringUtils.EMPTY,
								"El Archivo No puede ser vacio");
						FacesContext.getCurrentInstance().addMessage(null, message);
					}

				} catch (Exception e) {
					// TODO: handle exception
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
							"Ha ocurrido un error procesando la información, Favor de revisar el formato y la codificación del archivo");
					FacesContext.getCurrentInstance().addMessage(null, message);
					e.printStackTrace();
				}

			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, StringUtils.EMPTY,
						"El Archivo debe llamarse bienesm.csv");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"Favor de Seleccionar un Archivo");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public SqlParameterSource getParameters() {
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("i_id_sector", this.getUserDetails().getIdSector());
		parametros.addValue("i_file_name", file.getName());
		parametros.addValue("i_path_file", PATH_UPFILES);

		return parametros;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
	}

	public Integer getAlMes() {
		return alMes;
	}

	public void setAlMes(Integer alMes) {
		this.alMes = alMes;
	}

	public UploadedFile getArchivo() {
		return archivo;
	}

	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}

	/**
	 * Salvar.
	 */
//	public void salvar() {
//		RequestContext.getCurrentInstance().execute("PF('blockUIWidget').block();");
//		try {
//			List<Bienesm> toRemove = new ArrayList<Bienesm>();
//			toRemove = repository.findByIdsectorAndMes(this.getUserDetails().getIdSector(), alMes);
//			repository.delete(toRemove);
//			listaFromCSV.stream().forEach(p -> {
//
//				Cuenta cuenta = cuentaRepository.findFirstByCuentaAndScuentaAndIdsector(p.getCuenta(), p.getScta(),
//						Long.valueOf(this.getUserDetails().getIdSector()));
//
//				if (cuenta != null) {
//					p.setNomcta(cuenta.getNomcta());
//				} else {
//					p.setNomcta("No existe la cuenta en el catálogo");
//				}
//
//				// Actualiza mes por si fue cambiado
//				// p.setMes(alMes);
//
//				Bienesm bienesm = repository.findByIdsectorAndMesAndConsec(getUserDetails().getIdSector(), p.getMes(),
//						p.getConsec());
//				// Verifica si registro ya existe
//				if (Objects.nonNull(bienesm)) {
//					log += " Eliminando registro con id: " + bienesm.getId();
//					repository.delete(bienesm);
//				}
//			});
//			System.out.println(listaFromCSV);
//			repository.save(listaFromCSV);
//			uploadFile = Boolean.FALSE;
//			archivo = null;
//			msg = "Registros guardados correctamente.";
//		} catch (Exception e) {
//			msg = "Hubo errores al intentar guardar los registros, revisar combinación del mes con el consecutivo.";
//			e.printStackTrace();
//			archivo = null;
//
//		}
//		System.out.println(log);
//		
//		RequestContext.getCurrentInstance().execute("unblock();");
//		RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
//		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY, msg);
//		FacesContext.getCurrentInstance().addMessage(null, message);
//	}

}
