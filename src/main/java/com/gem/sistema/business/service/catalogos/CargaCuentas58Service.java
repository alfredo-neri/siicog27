package com.gem.sistema.business.service.catalogos;

import java.util.Map;

public interface CargaCuentas58Service {

	Map<String, Object> cargaCueta(String fullPathName, String userName);
	
	Map<String, Object> cargaCuetaTmp(String fullPathName);

}
