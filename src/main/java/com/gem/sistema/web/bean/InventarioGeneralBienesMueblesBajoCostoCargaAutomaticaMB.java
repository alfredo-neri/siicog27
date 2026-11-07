package com.gem.sistema.web.bean;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.service.callsp.ExecutePlService;

// TODO: Auto-generated Javadoc
/**
 * The Class InventarioGeneralBienesMueblesBajoCostoCargaAutomaticaMB.
 */
@ManagedBean(name = "CBajoCostoMB")
@ViewScoped
public class InventarioGeneralBienesMueblesBajoCostoCargaAutomaticaMB extends AbstractMB {

	private Integer alMes;
	private File archivo;
	private Boolean actBtn = Boolean.FALSE;
	private static String PATH = "/gem/upfiles/";
	private static final String PROCEDURE = "SP_CARGA_ARCHIVO_BIENES_BC";
	private String proceso = "R";

	@ManagedProperty("#{executePlService}")
	private ExecutePlService executePlService;

	@PostConstruct
	public void init() {
		alMes = 1;
	}

	public void cargar() {
		Map<String, Object> result = new HashMap<>();
		setProceso("C");
		try {
			result = this.ejecutarStore();
			this.setActBtn(Integer.valueOf(result.get("O_COD_ERROR").toString()) == 0);
			displayInfoMessage(result.get("O_MSG_ERROR").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public SqlParameterSource getParameters() {
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("i_proc", this.getProceso());
		parametros.addValue("i_id_user", this.getUserDetails().getUsername());
		parametros.addValue("i_id_sector", this.getUserDetails().getIdSector());
		parametros.addValue("i_id_ref", 0);
		parametros.addValue("i_file_name", this.getArchivo().getName());
		parametros.addValue("i_path_file", PATH);
		parametros.addValue("i_mes", this.getAlMes());
		return parametros;
	}

	public Map<String, Object> ejecutarStore() {
		Map<String, Object> out = new HashMap<String, Object>();
		try {
			out = executePlService.callProcedure(PROCEDURE, getParameters());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	public void handleFileUpload(FileUploadEvent event) {
		this.archivo = new File(PATH + event.getFile().getFileName());
		Map<String, Object> result = new HashMap<>();
		setProceso("R");
		if (this.archivo != null) {
			if (this.archivo.getName().equals("bienesbc.csv")) {
				if (StringUtils.isNotEmpty(this.archivo.getName().toString())) {
					try {
						FileUtils.copyInputStreamToFile(event.getFile().getInputstream(), this.archivo);
						if (this.archivo.length() <= 0) {
							this.displayInfoMessage("El archivo no puede ser vacio");
							return;
						}
						result = this.ejecutarStore();
						if (result == null) {
							displayInfoMessage("Ha ocurrido un error al analizar el archivo revisar formato");
							return;
						} else if (result.get("O_MSG_ERROR") == null) {
							displayInfoMessage("Ha ocurrido un error al analizar el archivo revisar formato");
							return;
						}
						this.setActBtn(Integer.valueOf(result.get("O_COD_ERROR").toString()) == 0);
						displayInfoMessage(result.get("O_MSG_ERROR").toString());
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					this.displayInfoMessage("Seleciona un archivo");
				}
			} else {
				this.displayInfoMessage("EL archivo deve de llamarse bienesi.csv");
			}
		} else {
			this.displayInfoMessage("Seleciona un archivo");
		}
	}

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	public Integer getAlMes() {
		return alMes;
	}

	public void setAlMes(Integer alMes) {
		this.alMes = alMes;
	}

	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
	}

	public Boolean getActBtn() {
		return actBtn;
	}

	public void setActBtn(Boolean actBtn) {
		this.actBtn = actBtn;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

}
