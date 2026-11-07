package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcFichaTrimestre;

@Repository
public interface TcFichaTrimestreRepository extends JpaRepository<TcFichaTrimestre, Long> {

}
