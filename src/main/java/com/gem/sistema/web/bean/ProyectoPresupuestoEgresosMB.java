package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.domain.TwAnteproyecto;
import com.gem.sistema.business.repository.catalogs.TwAnteproyectoRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * @author Alfredo
 * @Timeline <strong>Date: </strong>19/08/2021 <strong> Changes: </strong> Se
 *           crean componentes para el proceso<br>
 */
@Scope("view")
@Component("proyectoPresupuestoEgresosMB")
public class ProyectoPresupuestoEgresosMB extends GenericExecuteProcedure {
	private static final String PASSWORD = "xcargaproyecto";
	private static final String UPFILE_PATH = "/gem/upfiles/";
	private static final String PROCEDURE_NAME = "SP_CARGA_ARCHIVO_ANTEPROYECTO";

	private String validaPass;
	private String fileName;
	private boolean objDisabled;
	private List<TwAnteproyecto> anteproyectoList;

	@Value("#{twAnteproyectoRepository}")
	private TwAnteproyectoRepository anteproyectoRepository;

	@PostConstruct
	public void init() {
		objDisabled = true;

		anteproyectoList = anteproyectoRepository
				.findAllByIdsectorOrderByClaveAscProgramaAscPartidaAsc(this.getUserDetails().getIdSector());
	}

	public void validaPassword() {
		if (validaPass.equals(PASSWORD)) {
			objDisabled = false;
		} else {
			generateNotificationFront(SEVERITY_ERROR, "Error", "Password incorrecto");
		}
	}

	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("i_id_sector", this.getUserDetails().getIdSector());
		parameter.addValue("i_file_name", this.fileName);
		parameter.addValue("i_path_file", UPFILE_PATH);

		return parameter;
	}

	public void handleFileUpload(FileUploadEvent event) {
		Map<String, Object> out;

		try {

			this.saveFile(UPFILE_PATH, event.getFile().getFileName(), event.getFile().getInputStream());

			this.fileName = event.getFile().getFileName();

			try {
				out = executePlService.callProcedure(PROCEDURE_NAME, this.getParametersReports());

				if (Integer.valueOf(out.get("O_COD_ERROR").toString()) == 0) {
//					generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
					generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Datos actualizados correctamente.");
				} else {
					generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
				}
			} catch (ReportValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			this.anteproyectoList = anteproyectoRepository
					.findAllByIdsectorOrderByClaveAscProgramaAscPartidaAsc(this.getUserDetails().getIdSector());

			objDisabled = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveFile(String path, String filename, InputStream input) throws IOException {
		OutputStream output = new FileOutputStream(new File(path, filename));

		try {
			IOUtils.copy(input, output);
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
		}
	}

	public List<TwAnteproyecto> getAnteproyectoList() {
		return anteproyectoList;
	}

	public void setAnteproyectoList(List<TwAnteproyecto> anteproyectoList) {
		this.anteproyectoList = anteproyectoList;
	}

	public TwAnteproyectoRepository getAnteproyectoRepository() {
		return anteproyectoRepository;
	}

	public void setAnteproyectoRepository(TwAnteproyectoRepository anteproyectoRepository) {
		this.anteproyectoRepository = anteproyectoRepository;
	}

	public boolean isObjDisabled() {
		return objDisabled;
	}

	public void setObjDisabled(boolean objDisabled) {
		this.objDisabled = objDisabled;
	}

	public String getValidaPass() {
		return validaPass;
	}

	public void setValidaPass(String validaPass) {
		this.validaPass = validaPass;
	}

	@Override
	public void downLoadFile() throws ReportValidationException {
		// TODO Auto-generated method stub

	}

}
