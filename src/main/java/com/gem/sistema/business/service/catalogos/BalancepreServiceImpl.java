package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.BalancepreBS;
import com.gem.sistema.business.domain.Balancepre;

@Service(value="balancepreService")
public class BalancepreServiceImpl implements BalancepreService {
	
	@Autowired
	private BalancepreBS balancepreBS;
	
	
	@Override
	public Balancepre save(Balancepre balancepre) {
		return this.balancepreBS.save(balancepre);
	}

	@Override
	public Balancepre modify(Balancepre balancepre, Integer oldTrimestre, Long oldConsecutivo) {
		return this.balancepreBS.modify(balancepre, oldTrimestre, oldConsecutivo);
	}

	@Override
	public void delete(Balancepre balancepre) {
		this.balancepreBS.delete(balancepre);
	}

	@Override
	public List<Balancepre> findAllByTrimestre(Integer idSector, Long idRef, Long idAnio, Integer trimestre) {
		return balancepreBS.findAllByTrimestre(idSector, idRef, idAnio, trimestre);
	}
}
