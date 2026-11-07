package com.gem.sistema.business.service.catalogos;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class ParseCuentaServiceImpl.
 */
@Service(value = "parseCuentaService")
public class ParseCuentaServiceImpl implements ParseCuentaService {

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ParseCuentaService#parseCuenta(java.lang.String)
	 */
	@Override
	public String parseCuenta(String cuenta) {
		return StringUtils.leftPad(cuenta, 10, "0");
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ParseCuentaService#parseScuenta(java.lang.String)
	 */
	@Override
	public String parseScuenta(String cuenta) {
		return StringUtils.leftPad(cuenta, 15, "0");
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ParseCuentaService#parseSsCuenta(java.lang.String)
	 */
	@Override
	public String parseSsCuenta(String cuenta) {
		return cuenta.substring(11, 15);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ParseCuentaService#parseSsscuenta(java.lang.String)
	 */
	@Override
	public String parseSsscuenta(String cuenta) {
		if(!cuenta.equals(null)){
			return cuenta.substring(1, 4);
		} else {
			return "";
		}
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.ParseCuentaService#parseSssscuenta(java.lang.String)
	 */
	@Override
	public String parseSssscuenta(String cuenta) {
		// TODO Auto-generated method stub
		return null;
	}

}
