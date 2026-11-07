package com.gem.sistema.business.service.catalogos;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.TbProceso;
import com.gem.sistema.business.repository.catalogs.TbProcesoRepository;

@Service("tbProcesoService")
public class TbProcesoServiceImpl implements TbProcesoService {
	private TbProceso actual;

	@Autowired
	private TbProcesoRepository procesoRepository;

	@Override
	public void start(String nombre, String username, String ruta) {
		this.actual = new TbProceso(nombre, username, ruta);
		try {
			this.actual = procesoRepository.save(actual);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void end() {
		this.actual.setFechaFin(new Date());
		if (actual.getDetalle() == null) {
			actual.setDetalle("Sin detalle");
		}
		try {
			procesoRepository.save(actual);
			this.actual = null;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void end(String detalle) {
		this.actual.setFechaFin(new Date());
		this.actual.setDetalle(detalle);
		try {
			procesoRepository.save(actual);
			this.actual = null;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

	}
}
