package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.ValidatePoliBS;

@Service("validatePolicyService")
public class ValidatePolicyServiceimpl implements ValidatePolicyService {

	@Autowired
	private ValidatePoliBS validatePoliBS;

	public ValidatePoliBS getValidatePoliBS() {
		return validatePoliBS;
	}

	public void setValidatePoliBS(ValidatePoliBS validatePoliBS) {
		this.validatePoliBS = validatePoliBS;
	}

	@Override
	public Boolean isOpenMonth(Integer mes, Integer anio, Integer idSector) {
		return this.validatePoliBS.isOpenMonth(mes, anio, idSector);
	}

	@Override
	public Boolean isAfectMonth(Integer mes, Integer anio, Integer idSector) {
		return this.validatePoliBS.isAfectMonth(mes, anio, idSector);

	}

	@Override
	public Boolean existPolices(Integer mes, Integer anio, Integer idSector) {
		return this.validatePoliBS.existPolices(mes, anio, idSector);

	}

}
