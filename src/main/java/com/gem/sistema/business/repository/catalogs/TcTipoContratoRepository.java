package com.gem.sistema.business.repository.catalogs;


import com.gem.sistema.business.domain.TcTipoContrato;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "tcTipoContratoRepository")
public interface TcTipoContratoRepository extends CrudRepository<TcTipoContrato, Long> {

}
