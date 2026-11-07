package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.UUID;
import jakarta.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.dto.OutputDataProcedure;
import com.gem.sistema.business.service.catalogos.MatrizCargaService;
import com.gem.sistema.util.CopyFileUtil;

@Component("matrizCargaMB")
@Scope("view")
public class MatrizCargaMB extends AbstractMB {

	private static final String PATH = "/gem/upfiles/";
	private String fileError = null;

	@Value("#{matrizCargaService}")
	private MatrizCargaService matrizCargaService;

	@PostConstruct
	public void init() {
	}

	public void handleFileUpload(FileUploadEvent event) {

		String newName = UUID.randomUUID() + "" + event.getFile().getFileName();
		if (event.getFile() != null) {
			if (StringUtils.isNotEmpty(newName)) {
				try {

					String path = CopyFileUtil.copyFile(newName, event.getFile().getInputStream(), PATH);
					if (event.getFile().getSize() <= 0) {
						displayInfoMessage("El archivo no puede ser vacio");

					} else {
						File file = new File(path);
						Boolean salida = this.matrizCargaService.validaArchivo(file);
						if (!salida) {
							fileError = "/gem/errores/" + newName;
							displayInfoMessage(
									"El archivo contiene errores descargar el archivo de errores para corregirlo");
						} else {
							fileError = null;
							OutputDataProcedure dataProcedure = matrizCargaService.cargaArchivo(PATH, newName,
									this.getUserDetails().getIdSector());
							if (dataProcedure == null || dataProcedure.getCodError() == null) {
								displayInfoMessage("Ha ocurrido un error al cargar la informacion");
							} else {
								if (dataProcedure.getCodError() != null && dataProcedure.getCodError() == 1) {
									displayInfoMessage("Ha ocurrido un error al cargar la informacion");
								} else {
									displayInfoMessage(dataProcedure.getMsgError());
								}
							}

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				displayInfoMessage("Seleciona un archivo");
			}
		} else {
			displayInfoMessage("Seleciona un archivo");
		}

	}

	public StreamedContent getFile() {
		StreamedContent st = null;
		try {
			File file = new File(this.getFileError());
			st = StreamedContents.of(new FileInputStream(file), "text/csv", file.getName(), null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return st;
	}

	public String getFileError() {
		return fileError;
	}

	public void setFileError(String fileError) {
		this.fileError = fileError;
	}

	public MatrizCargaService getMatrizCargaService() {
		return matrizCargaService;
	}

	public void setMatrizCargaService(MatrizCargaService matrizCargaService) {
		this.matrizCargaService = matrizCargaService;
	}

}
