package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.Balancepre;

public interface BalancepreService {

	Balancepre save(Balancepre balancepre);
	
	void delete(Balancepre balancepre);
	
	List<Balancepre> findAllByTrimestre(Integer idSector, Long idRef, Long idAnio, Integer trimestre);

	Balancepre modify(Balancepre balancepre, Integer oldTrimestre, Long oldConsecutivo);
	
}
