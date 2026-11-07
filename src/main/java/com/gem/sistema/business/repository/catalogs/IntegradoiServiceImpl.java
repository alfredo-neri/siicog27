package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import com.gem.sistema.business.domain.Integradoi;

// TODO: Auto-generated Javadoc
/**
 * The Class IntegradoiServiceImpl.
 */
public class IntegradoiServiceImpl implements IntegradoiService{

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.repository.catalogs.IntegradoiService#findIntegradoByCuenta(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<Integradoi> findIntegradoByCuenta(String cuenta, String scta, String sscta, String ssscta,
			String sssscta, Integer idSector) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("");
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.repository.catalogs.IntegradoiService#findIntegradoCuenta(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<Integradoi> findIntegradoCuenta(String cuenta, String scta, String sscta, String ssscta, String sssscta,
			Integer idSector) {
		// TODO Auto-generated method stub
		return null;
	}

}
