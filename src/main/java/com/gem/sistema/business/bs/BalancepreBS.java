package com.gem.sistema.business.bs;

import java.util.List;

import com.gem.sistema.business.domain.Balancepre;

public interface BalancepreBS {

	Balancepre save(Balancepre balancepre);
	
	void delete(Balancepre balancepre);
	
	List<Balancepre> findAllByTrimestre(Integer idSector,Long idRef, Long idAnio, Integer trimestre);
	
	Balancepre modify(Balancepre balancepre, Integer oldTrimestre, Long oldConsecutivo);
	
	Boolean existTrimAndConsecutivo(Integer trimestre, Integer idSector, Long consecutivo);
}
