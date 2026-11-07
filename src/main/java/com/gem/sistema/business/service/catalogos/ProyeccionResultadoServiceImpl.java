package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.TwProyeccionResultado;
import com.gem.sistema.business.repository.catalogs.TwProyeccionResultadoRepository;

@Service(value = "proyeccionResultadoService")
public class ProyeccionResultadoServiceImpl implements ProyeccionResultadoService {

	@Autowired
	private TwProyeccionResultadoRepository proyeccionResultadoRepository;

	@Override
	public TwProyeccionResultado save(TwProyeccionResultado proyeccionResultado) {
		return proyeccionResultadoRepository.save(proyeccionResultado);
	}

	@Override
	public List<TwProyeccionResultado> findAllByTipo(String tipo) {
		return proyeccionResultadoRepository.findAllByTipo(tipo);
	}

	@Override
	public void delete(TwProyeccionResultado proyeccionResultado) {
		proyeccionResultadoRepository.delete(proyeccionResultado);
	}

	public TwProyeccionResultadoRepository getProyeccionResultadoRepository() {
		return proyeccionResultadoRepository;
	}

	public void setProyeccionResultadoRepository(TwProyeccionResultadoRepository proyeccionResultadoRepository) {
		this.proyeccionResultadoRepository = proyeccionResultadoRepository;
	}

}
