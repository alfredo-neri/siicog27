package com.gem.sistema.business.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.ChangePasswordDAO;
import com.gem.sistema.business.domain.TcModulosOperacione;
import com.gem.sistema.business.domain.TcParametro;
import com.gem.sistema.business.predicates.ParametrosPredicate;
import com.gem.sistema.business.predicates.TcModulosOperacionePredicate;
import com.gem.sistema.business.repository.catalogs.ParametrosRepository;
import com.gem.sistema.business.repository.catalogs.TcModulosOperacioneRepository;
import com.gem.sistema.ennum.constans.ConstansEnum;

@Repository
public class ChangePasswordDAOImpl implements ChangePasswordDAO {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ParametrosRepository parametrosRepository;

	@Autowired
	private TcModulosOperacioneRepository tcModulosOperacioneRepository;
	


	@Override
	public void save(TcParametro tcParametro, TcModulosOperacione tcModulosOperacione) {
		String passEcript = passwordEncoder.encode(tcParametro.getValor());
		tcModulosOperacione = this.tcModulosOperacioneRepository
				.findOne(TcModulosOperacionePredicate.findByprocesoAndSector(tcModulosOperacione));
		tcParametro.setCveParametro(tcModulosOperacione.getClvProceso());
		tcParametro.setValor(passEcript);
		tcParametro.setDescripcion(tcModulosOperacione.getNombreProceso());
		tcParametro.setDataType(ConstansEnum.TYPE_PASS.getValue());
		this.parametrosRepository.save(tcParametro);

	}

	@Override
	public void changePassword(TcParametro tcParametro, TcModulosOperacione tcModulosOperacione) {
		tcModulosOperacione = this.tcModulosOperacioneRepository
				.findOne(TcModulosOperacionePredicate.findByprocesoAndSector(tcModulosOperacione));
		TcParametro param = this.parametrosRepository.findOne(ParametrosPredicate
				.findByDataType(tcModulosOperacione.getClvProceso(), ConstansEnum.TYPE_PASS.getValue()));
		String passEcript = passwordEncoder.encode(tcParametro.getValor());
		param.setValor(passEcript);
		this.parametrosRepository.save(param);

	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public ParametrosRepository getParametrosRepository() {
		return parametrosRepository;
	}

	public void setParametrosRepository(ParametrosRepository parametrosRepository) {
		this.parametrosRepository = parametrosRepository;
	}

	String encripPass;
	TcParametro param = null;

	@Override
	public boolean validatePassword(TcParametro tcParametro, TcModulosOperacione tcModulosOperacione) {

		tcModulosOperacione = this.tcModulosOperacioneRepository
				.findOne(TcModulosOperacionePredicate.findByprocesoAndSector(tcModulosOperacione));
		param = this.parametrosRepository.findOne(ParametrosPredicate
				.findByDataType(tcModulosOperacione.getClvProceso(), ConstansEnum.TYPE_PASS.getValue()));
		if (null != param) {
			boolean bandera = this.passwordEncoder.matches(tcParametro.getValor(), param.getValor());

			if (bandera) {
				return true;
			}
		}
		return false;
	}
	


	@Override
	public List<TcModulosOperacione> findBySector(Integer idSector) {

		return this.tcModulosOperacioneRepository.findAllByIdSector(idSector);
	}

	public TcModulosOperacioneRepository getTcModulosOperacioneRepository() {
		return tcModulosOperacioneRepository;
	}

	public void setTcModulosOperacioneRepository(TcModulosOperacioneRepository tcModulosOperacioneRepository) {
		this.tcModulosOperacioneRepository = tcModulosOperacioneRepository;
	}

	@Override
	public boolean passwordisTrue(String password, String keyPass) {
		TcParametro tcParametro = this.parametrosRepository.findOne(ParametrosPredicate.findByDataType(keyPass, ConstansEnum.TYPE_PASS.getValue()));
		return passwordEncoder.matches(password, tcParametro.getValor());
	}

	@Override
	public String getValueParameter(String key) {
		
		return this.parametrosRepository.getValorByCv(key);
	}

}
