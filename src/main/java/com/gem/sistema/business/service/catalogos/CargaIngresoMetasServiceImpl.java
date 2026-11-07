package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.CargaIngresoMetasDAO;
import com.gem.sistema.business.domain.TwMetasIngreso;

@Service(value = "cargaIngresoMetasService")
public class CargaIngresoMetasServiceImpl implements CargaIngresoMetasService{
	
	@Autowired
	private CargaIngresoMetasDAO cargaIngresoMetasDAO;

	@Override
	public List<TwMetasIngreso> cargaMetas(String fileName) {

		return this.cargaIngresoMetasDAO.cargaMetas(fileName);
	}

	@Override
	public List<TwMetasIngreso> consultaMetas() {

		return this.cargaIngresoMetasDAO.consultaMetas();
	}

	public CargaIngresoMetasDAO getCargaIngresoMetasDAO() {
		return cargaIngresoMetasDAO;
	}

	public void setCargaIngresoMetasDAO(CargaIngresoMetasDAO cargaIngresoMetasDAO) {
		this.cargaIngresoMetasDAO = cargaIngresoMetasDAO;
	}

	@Override
	public String validaIngresoPropios(String fileName) {

		return this.cargaIngresoMetasDAO.validaIngresoPropios(fileName);
	}

}
