package com.gem.sistema.business.dao;

import java.util.Map;

public interface CargaCuentas58DAO {
	Map<String, Object> cargaCueta(String fullPathName, String userName);
	
	Map<String, Object> cargaCuetaTmp(String fullPathName);

}
