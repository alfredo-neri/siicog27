package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.EmpleadoInfoem;
import com.gem.sistema.business.repository.catalogs.EmpleadoInfoemRepository;
import static com.gem.sistema.web.util.ConvertedNumberLettersUtil.numeroALetras;
@Service(value = "empleadoInfoemService")
public class EmpleadoInfoemServiceImpl implements EmpleadoInfoemService{

	@Autowired
	private EmpleadoInfoemRepository empleadoInfoemRepository;
	
	@Override
	public List<EmpleadoInfoem> findAll() {
		
		return (List<EmpleadoInfoem>) this.empleadoInfoemRepository.findAll();
	}

	public EmpleadoInfoemRepository getEmpleadoInfoemRepository() {
		return empleadoInfoemRepository;
	}

	public void setEmpleadoInfoemRepository(EmpleadoInfoemRepository empleadoInfoemRepository) {
		this.empleadoInfoemRepository = empleadoInfoemRepository;
	}

	@Override
	public String covertedNumbersToLetters(Double numero) {
		// TODO Auto-generated method stub
		return numeroALetras(numero);
	}
	
	

}
