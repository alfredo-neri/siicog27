package com.gem.sistema.business.repository.catalogs;

import com.gem.sistema.business.domain.TcConvenio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "tcConvenioRepository")
public interface TcConvenioRepository extends CrudRepository<TcConvenio, Long>{

}
