package com.gem.sistema.business.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.EaidDAO;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Eaid;
import com.gem.sistema.business.predicates.EaidPredicate;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.EaidRepository;

/**
 * @author mateo
 *
 */
@Repository
public class EaidDAOImpl implements EaidDAO {

	@Autowired
	private EaidRepository eaidRepository;

	@Autowired
	private ConctbRepository conctbRepository;

	/**
	 * @param eaid
	 * @return
	 */
	@Override
	public boolean save(Eaid eaid) {
		Eaid valida = this.eaidRepository.findOne(EaidPredicate.validaEaid(eaid));
		if (null == valida) {
			this.eaidRepository.save(eaid);
			return true;
		}

		return false;

	}

	/**
	 * @param eiad
	 * @return
	 */
	@Override
	public List<Eaid> delete(Eaid eaid) {
		this.eaidRepository.delete(eaid);
		return (List<Eaid>) this.eaidRepository.findAll();
	}

	/**
	 * @param eaid
	 * @return
	 */
	@Override
	public List<Eaid> update(Eaid eaid) {
		this.eaidRepository.save(eaid);
		return (List<Eaid>) this.eaidRepository.findAll();
	}

	/**
	 * @param trimestre
	 * @return
	 */
	@Override
	public List<Eaid> findByTrimestre(Integer trimestre, Integer idSector) {

		return this.eaidRepository.findByTrimestreAndIdSectorOrderByTrimestreAsc(trimestre, idSector);
	}

	/**
	 * @param trimestre
	 * @param condicion
	 * @return
	 */
	@Override
	public Eaid findByTrimestreAndCondition(Integer trimestre, String condicion, Integer idSector) {

		return null;
	}

	public EaidRepository getEaidRepository() {
		return eaidRepository;
	}

	public void setEaidRepository(EaidRepository eaidRepository) {
		this.eaidRepository = eaidRepository;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	@Override
	public List<Eaid> findByIdSector(Integer IdSector) {

		return this.getEaidRepository().findByIdSectorOrderByTrimestreAscConsecutivoAsc(IdSector);
	}

	@Override
	public Eaid findByIdSectorAndTrimestreAndCoonsecutivo(Integer idSector, Integer trimestre, Integer consecutivo) {

		return this.eaidRepository.findByIdSectorAndTrimestreAndConsecutivo(idSector, trimestre, consecutivo);
	}

	@Override
	public Conctb getAnioContable(Integer idSector, long idRef) {

		return this.conctbRepository.findByIdsectorAndIdRef(idSector, idRef);
	}

}
