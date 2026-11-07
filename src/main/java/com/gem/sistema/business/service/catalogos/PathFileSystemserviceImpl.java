package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.repository.catalogs.ParametrosRepository;


// TODO: Auto-generated Javadoc
/**
 * The Class PathFileSystemserviceImpl.
 */
@Service(value = "pathFileSystemservice")
public class PathFileSystemserviceImpl implements PathFileSystemservice{
	
	/** The parametros repository. */
	@Autowired
	private ParametrosRepository parametrosRepository; 
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.PathFileSystemservice#getParhFile(java.lang.String)
	 */
	@Override
	public String getParhFile(String cvePath) {
		String path = parametrosRepository.getValorByCv(cvePath);
		return path;
	}

}
