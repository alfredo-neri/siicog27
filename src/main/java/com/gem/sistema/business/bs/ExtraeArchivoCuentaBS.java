package com.gem.sistema.business.bs;

public interface ExtraeArchivoCuentaBS {
	
	String getNewFile(String fileName);
	
	String generateNewFile(String newCad, String usuario);

}
