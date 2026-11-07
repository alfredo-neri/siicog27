package com.gem.sistema.business.bs;

import java.util.List;

public interface GeneraTxtBS {

	String generatArchivoTxt(long idReporte, Integer idSector, Integer trimestre);
	
	String generaArchivoEaepecaldf(Integer idSector, Integer trimestre);
	
	String generaArchivoEaepecfldf(Integer idSector, Integer trimestre);
	
	String generaArchivo(List<String> listdatos, String fileSystem);

}
