package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.TcTabla;
import com.gem.sistema.business.repository.catalogs.TcTablaRepository;

@Service(value="catalogoTablasService")
public class CatalogoTablasServiceImpl implements CatalogoTablasService {

	@Autowired
	private TcTablaRepository tcTablaRepository;

	@Override
	public List<TcTabla> findAllTablas() {
		return (List<TcTabla>) tcTablaRepository.findAll();
	}

	@Override
	public List<TcTabla> saveTabla(TcTabla tabla) {
		tcTablaRepository.save(tabla);
		return (List<TcTabla>) tcTablaRepository.findAll();
	}




	public TcTablaRepository getTcTablaRepository() {
		return tcTablaRepository;
	}

	public void setTcTablaRepository(TcTablaRepository tcTablaRepository) {
		this.tcTablaRepository = tcTablaRepository;
	}

}
