package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.*;

public interface RegistroPagoChequeService {

	TwRegistroPagoCheque save(TwRegistroPagoCheque entity, TcContrato contrato);

	List<TwRegistroPagoCheque> findAll();

	TwRegistroPagoCheque findByDestinatario(String name);

	TwRegistroPagoCheque findByFolio(Integer folio);

	TwRegistroPagoCheque findByMonth(Integer month);

	TwRegistroPagoCheque update(Integer folio, TwRegistroPagoCheque pagoCheque);

	List<TcPeriodo> findByMonthPeriodo();

	List<TcTipoCheque> findAllTipoCheque();

	List<TcEstatusCheque> findAllEstatusCheque();

	TwRegistroPagoCheque findByIdCheque(Integer idCheque);

	TcContrato findContratoByNumContrato( String numContrato);

}
