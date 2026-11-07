package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.GeneraTxtBS;
import com.gem.sistema.business.dao.QueryDinamicoDAO;

@Service(value = "generaTxtService")
public class GeneraTxtServiceImpl implements GeneraTxtService {

	@Autowired
	private GeneraTxtBS generaTxtBs;
	
	@Autowired 
	private QueryDinamicoDAO queryDinamicoDAO;

	@Override
	public String generatArchivoTxt(long idReporte, Integer idSector, Integer trimestre) {

		return this.generaTxtBs.generatArchivoTxt(idReporte, idSector, trimestre);
	}

	public GeneraTxtBS getGeneraTxtBs() {
		return generaTxtBs;
	}

	public void setGeneraTxtBs(GeneraTxtBS generaTxtBs) {
		this.generaTxtBs = generaTxtBs;
	}

	@Override
	public String generaArchivoEaepecaldf(Integer idSector, Integer trimestre ) {
		return this.generaTxtBs.generaArchivoEaepecaldf(idSector, trimestre);
	}

	@Override
	public String generaArchivoEaepecfldf(Integer idSector, Integer trimestre) {
		// TODO Auto-generated method stub
		return this.generaTxtBs.generaArchivoEaepecfldf(idSector, trimestre);
	}

	@Override
	public String getData(Integer mes, String fileName, Integer programa) {
		
		return this.queryDinamicoDAO.getData(mes, fileName, programa);
	}
	
	@Override
	public String generaArtivoTxtFichaDisenio(Integer sector, String fileName) {
		return this.queryDinamicoDAO.generaTxtFichaDisenio(sector, fileName);
	}

	public QueryDinamicoDAO getQueryDinamicoDAO() {
		return queryDinamicoDAO;
	}

	public void setQueryDinamicoDAO(QueryDinamicoDAO queryDinamicoDAO) {
		this.queryDinamicoDAO = queryDinamicoDAO;
	}


}
