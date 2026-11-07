package com.gem.sistema.business.service.catalogos;

import java.io.File;

public interface ExportInformationService {

	String exportCuentas(Integer idSector, Integer mes);

	String exportPaso(Integer idSector, Integer mes);

	String exportPoliza(Integer idSecotr, Integer mes);

	String exportCuentaMes(Integer idSector, Integer mes);
	
	String zipFile(File[] inputFile, String zipFilePath);

	String generaQueryCuentaMes(Integer sector, Integer mes, String ctaCero);

	String generaQueryPolizas(Integer sector, Integer mes, Integer anio);
}
