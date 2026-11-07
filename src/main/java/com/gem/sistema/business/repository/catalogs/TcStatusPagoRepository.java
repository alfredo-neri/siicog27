package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcStatusPago;

@Repository
public interface TcStatusPagoRepository extends JpaRepository<TcStatusPago, Long> {

}
