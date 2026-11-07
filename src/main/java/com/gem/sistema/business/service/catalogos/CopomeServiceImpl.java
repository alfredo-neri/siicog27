package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.ActualizaNumeroPolizaDAO;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Copome;
import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.predicates.PolienPredicates;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.CopomeRepository;
// TODO: Auto-generated Javadoc

/**
 * The Class CopomeServiceImpl.
 */
@Service(value = "copomeService")
public class CopomeServiceImpl implements CopomeService{

	/** The copome repository. */
	@Autowired
  private CopomeRepository copomeRepository;

	@Autowired
	private ConctbRepository conctbRepository;

	/** The actualiza numero poliza DAO. */
	@Autowired
	private ActualizaNumeroPolizaDAO actualizaNumeroPolizaDAO;
	
	/** The polien repository. */
	@Autowired
	private PolienRepository polienRepository;
	
	
	/**
	 * Gets the actualiza numero poliza DAO.
	 *
	 * @return the actualiza numero poliza DAO
	 */
	public ActualizaNumeroPolizaDAO getActualizaNumeroPolizaDAO() {
		return actualizaNumeroPolizaDAO;
	}


	/**
	 * Sets the actualiza numero poliza DAO.
	 *
	 * @param actualizaNumeroPolizaDAO the new actualiza numero poliza DAO
	 */
	public void setActualizaNumeroPolizaDAO(ActualizaNumeroPolizaDAO actualizaNumeroPolizaDAO) {
		this.actualizaNumeroPolizaDAO = actualizaNumeroPolizaDAO;
	}


	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.CopomeService#getNextNume(com.gem.sistema.business.domain.Polien, java.lang.Integer, java.lang.String)
	 */
	@Override
	public Copome getNextNume(Polien polien, Integer idSector, String user) {
		
//		Integer maxPol = this.polienRepository.maxPolizas(polien.getMespol(), polien.getTippol(),
//				idSector);
//
//		if (null == maxPol || maxPol == 0) {
//			polien.setConpol(1);
//		} else {
//			
//			for (int i = 1; i < maxPol; i++) {
//				if(!this.polienRepository.exists(PolienPredicates.next(polien.getTippol(), polien.getMespol(),
//						i, idSector))){
//					polien.setConpol(i);
//					break;
//				}
//			}
//			
//		}
		return this.actualizaNumeroPolizaDAO.actualizaNumero2(polien, idSector, user);
	}

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.catalogos.CopomeService#getAll()
   */
  public List<Copome> getAll(){
    return copomeRepository.findAll();
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.catalogos.CopomeService#findByTpcpme(java.lang.String, java.lang.Integer)
   */
  public Copome findByTpcpme(String tpcpme, Integer idSector){
    return copomeRepository.findFirstByTpcpmeAndIdsectorOrderByIdAsc(tpcpme, idSector);
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.service.catalogos.CopomeService#save(com.gem.sistema.business.domain.Copome)
   */
  public Copome save(Copome copome){
    return copomeRepository.save(copome);
  }

	public Integer getCurrentPolicyNumber(String tpcpme, Integer mecpme, Integer idSector){
		Conctb conctb = conctbRepository.findFirstByIdsectorOrderByIdAsc(idSector);
		Copome c = copomeRepository.findFirstByTpcpmeAndMecpmeAndAnopmeAndIdsectorOrderByIdAsc(tpcpme, mecpme, conctb.getAnoemp(), idSector);
		return c == null ? 0 : c.getCocpme();
	}
}
