package com.gem.sistema.business.bs.impl;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.DatosMunicipioBS;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcUsuarioRepository;

/**
 * @author Mateo
 *
 */
@Repository
public class DatosMunicipioBSImpl implements DatosMunicipioBS {

	@Autowired
	private ConctbRepository conctbRepository;

	@Autowired
	private TcUsuarioRepository tcUsuarioRepository;

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	@Override
	public void update(String clave, Integer idSector, String userName) {
		this.conctbRepository.updateClave(clave, idSector);
		this.tcUsuarioRepository.updateLocalidad(this.getClvMunicipio(clave), Long.valueOf(clave.substring(0, 1)),
				userName);
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se actualizo correctamente la clave");

	}

	public TcUsuarioRepository getTcUsuarioRepository() {
		return tcUsuarioRepository;
	}

	public void setTcUsuarioRepository(TcUsuarioRepository tcUsuarioRepository) {
		this.tcUsuarioRepository = tcUsuarioRepository;
	}

	@Override
	public Long getClvMunicipio(String clave) {
		// TODO Auto-generated method stub
		return Long.valueOf(clave.substring(1, 4));
	}

}
