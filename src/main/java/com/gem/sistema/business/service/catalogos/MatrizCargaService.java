package com.gem.sistema.business.service.catalogos;

import java.io.File;

import com.gem.sistema.business.dto.OutputDataProcedure;

public interface MatrizCargaService {

	Boolean validaArchivo(File newFile);

	OutputDataProcedure cargaArchivo(String path, String nameFile, Integer sector);

}
