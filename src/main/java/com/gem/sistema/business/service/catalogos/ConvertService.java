package com.gem.sistema.business.service.catalogos;

import java.io.InputStream;

public interface ConvertService {
	
	String readExcel(InputStream file, String fileNameIn, String fileNameOut);
	
	
	

}
