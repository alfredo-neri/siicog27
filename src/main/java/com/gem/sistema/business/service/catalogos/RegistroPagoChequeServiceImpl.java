package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.TcContrato;
import com.gem.sistema.business.domain.TcEstatusCheque;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcTipoCheque;
import com.gem.sistema.business.domain.TrChequeContrato;
import com.gem.sistema.business.domain.TwRegistroPagoCheque;
import com.gem.sistema.business.repository.catalogs.ChequeContratoRepository;
import com.gem.sistema.business.repository.catalogs.RegistroPagoChequesRepository;
import com.gem.sistema.business.repository.catalogs.TcContratosRepository;
import com.gem.sistema.business.repository.catalogs.TcEstatusChequeRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcTipoChequeRepository;

@Service(value = "registroPagoChequeService")
public class RegistroPagoChequeServiceImpl implements RegistroPagoChequeService {

	@Autowired
	private RegistroPagoChequesRepository repository;

	@Autowired
	private TcPeriodoRepositoy periodoRepositoy;

	@Autowired
	private TcEstatusChequeRepository estatusChequeRepository;

	@Autowired
	private TcTipoChequeRepository tipoChequeRepository;

	@Autowired
	private TcContratosRepository contratosRepository;

	@Autowired
	private ChequeContratoRepository chequeContratoRepository;
	TrChequeContrato aux = null;

	@Override
	public TwRegistroPagoCheque save(TwRegistroPagoCheque entity, TcContrato contrato) {
		entity = repository.save(entity);
		if (contrato != null) {
			aux = new TrChequeContrato();
			aux.setIdChque(entity.getId());
			aux.setIdContrato(contrato.getId());
			chequeContratoRepository.save(aux);
		}
		return entity;
	}

	@Override
	public List<TwRegistroPagoCheque> findAll() {

		return (List<TwRegistroPagoCheque>) this.repository.findAll();
	}

	@Override
	public TwRegistroPagoCheque findByDestinatario(String name) {

		return repository.findByDestinario(name);
	}

	@Override
	public TwRegistroPagoCheque findByFolio(Integer folio) {

		return repository.findByFolio(folio);
	}

	@Override
	public TwRegistroPagoCheque findByMonth(Integer month) {

		return this.repository.findByMonth(month);
	}

	@Override
	public TwRegistroPagoCheque update(Integer folio, TwRegistroPagoCheque pagoCheque) {
		TwRegistroPagoCheque auxCheque = this.repository.findByFolio(folio);
		pagoCheque.setId(auxCheque.getId());
		this.repository.save(pagoCheque);
		return pagoCheque;
	}

	@Override
	public List<TcPeriodo> findByMonthPeriodo() {
		// TODO Auto-generated method stub
		return this.periodoRepositoy.findByTipoPeriodo(1);
	}

	public RegistroPagoChequesRepository getRepository() {
		return repository;
	}

	public void setRepository(RegistroPagoChequesRepository repository) {
		this.repository = repository;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	@Override
	public List<TcTipoCheque> findAllTipoCheque() {
		return (List<TcTipoCheque>) this.tipoChequeRepository.findAll();
	}

	@Override
	public List<TcEstatusCheque> findAllEstatusCheque() {
		return (List<TcEstatusCheque>) this.estatusChequeRepository.findAll();
	}

	@Override
	public TwRegistroPagoCheque findByIdCheque(Integer idCheque) {
		return this.repository.findById(idCheque.longValue()).orElse(null);
	}

	@Override
	public TcContrato findContratoByNumContrato(String numContrato) {
		return this.contratosRepository.findByNumeroContrato(numContrato);
	}

	public TcEstatusChequeRepository getEstatusChequeRepository() {
		return estatusChequeRepository;
	}

	public void setEstatusChequeRepository(TcEstatusChequeRepository estatusChequeRepository) {
		this.estatusChequeRepository = estatusChequeRepository;
	}

	public TcTipoChequeRepository getTipoChequeRepository() {
		return tipoChequeRepository;
	}

	public void setTipoChequeRepository(TcTipoChequeRepository tipoChequeRepository) {
		this.tipoChequeRepository = tipoChequeRepository;
	}

	public TcContratosRepository getContratosRepository() {
		return contratosRepository;
	}

	public void setContratosRepository(TcContratosRepository contratosRepository) {
		this.contratosRepository = contratosRepository;
	}

	public ChequeContratoRepository getChequeContratoRepository() {
		return chequeContratoRepository;
	}

	public void setChequeContratoRepository(ChequeContratoRepository chequeContratoRepository) {
		this.chequeContratoRepository = chequeContratoRepository;
	}
}
