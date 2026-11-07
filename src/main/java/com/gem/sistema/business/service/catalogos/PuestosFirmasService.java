package com.gem.sistema.business.service.catalogos;

import java.util.List;
import java.util.Map;

import com.gem.sistema.business.domain.TcClave;
import com.gem.sistema.business.domain.TcPuesto;
import com.gem.sistema.business.domain.TrPuestoFirma;

/**
 * @author Alfredo Neri
 *
 */
public interface PuestosFirmasService {

	List<TcClave> listClaves(Integer idSector);
	
	List<TcPuesto> listPuestos(Integer idSector, Long idAnio);
	
	List<TrPuestoFirma> listPuestosFirmas(Integer idSector, Long idAnio);
	
	void createRelationshipPuesto(TcPuesto puesto,Long idAnio);
	
	void deleteRelationshipPuesto(List<TrPuestoFirma> firmas);
	
	void createPuesto(TcPuesto puesto, Long idAnio);
	
	void updateNameFirma(TrPuestoFirma firma);
	
	void updateNamePuesto(TcPuesto puesto);
	
	TrPuestoFirma getFirmaBySectorAnioClave(Integer idSector, Long idAnio, String clave);
	
	Map<String, Object> getFirmasOfReport(Integer idSector, String claveReport);
	
}
