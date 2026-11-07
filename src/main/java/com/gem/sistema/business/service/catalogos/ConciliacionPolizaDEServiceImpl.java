package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.ConciliacionPolizaDEDAO;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.vo.ConciliacionPolizaDEVO;

/**
 * Implementacion del servicio de conciliacion de polizas D contra E.
 *
 * @author Alfredo Neri
 */
@Service(value = "conciliacionPolizaDEService")
public class ConciliacionPolizaDEServiceImpl implements ConciliacionPolizaDEService {

	@Autowired
	private ConciliacionPolizaDEDAO conciliacionPolizaDEDAO;

	@Autowired
	private ConctbRepository conctbRepository;

	@Override
	public List<ConciliacionPolizaDEVO> findByPeriodo(Integer anopol, Integer mespol, Integer idSector) {
		return this.conciliacionPolizaDEDAO.findByPeriodo(anopol, mespol, idSector);
	}

	@Override
	public Integer getAnioEjercicio(Integer idSector) {
		Conctb conctb = this.conctbRepository.findByIdsector(idSector);
		return conctb == null ? null : conctb.getAnoemp();
	}

	public ConciliacionPolizaDEDAO getConciliacionPolizaDEDAO() {
		return conciliacionPolizaDEDAO;
	}

	public void setConciliacionPolizaDEDAO(ConciliacionPolizaDEDAO conciliacionPolizaDEDAO) {
		this.conciliacionPolizaDEDAO = conciliacionPolizaDEDAO;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

}
