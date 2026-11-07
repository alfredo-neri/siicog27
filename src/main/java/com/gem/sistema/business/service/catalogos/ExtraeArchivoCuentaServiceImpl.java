package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.ExtraeArchivoCuentaBS;

@Service(value = "extraeArchivoCuentaService")
public class ExtraeArchivoCuentaServiceImpl implements ExtraeArchivoCuentaService {

	@Autowired
	private ExtraeArchivoCuentaBS extraeArchivoCuentaBS;

	@Override
	public String generateNewFile(String newCad, String usuario) {
		return this.extraeArchivoCuentaBS.generateNewFile(newCad, usuario);
	}

	public ExtraeArchivoCuentaBS getExtraeArchivoCuentaBS() {
		return extraeArchivoCuentaBS;
	}

	public void setExtraeArchivoCuentaBS(ExtraeArchivoCuentaBS extraeArchivoCuentaBS) {
		this.extraeArchivoCuentaBS = extraeArchivoCuentaBS;
	}

}
