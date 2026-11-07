package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcControlPresupuestario;

@Repository(value = "tcControlPresupuestarioRepository")
public interface TcControlPresupuestarioRepository extends PagingAndSortingRepository<TcControlPresupuestario, Long> {

	TcControlPresupuestario findByClave(String clave);
}
