package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.DatosMunicipioBS;

@Service(value = "datosMunicipioService")
public class DatosMunicipioServiceImpl implements DatosMunicipioService {

	@Autowired
	private DatosMunicipioBS datosMunicipioBS;

	public DatosMunicipioBS getDatosMunicipioBS() {
		return datosMunicipioBS;
	}

	public void setDatosMunicipioBS(DatosMunicipioBS datosMunicipioBS) {
		this.datosMunicipioBS = datosMunicipioBS;
	}

	@Override
	public void update(String clave, Integer idSector, String userName) {
		this.datosMunicipioBS.update(clave, idSector, userName);

	}

}
