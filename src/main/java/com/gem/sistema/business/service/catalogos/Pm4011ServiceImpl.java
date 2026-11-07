package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm4011BS;
import com.gem.sistema.business.domain.Pm4011;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm4011ServiceImpl.
 *
 * @author Mateo
 */
@Service(value = "pm4011Service")
public class Pm4011ServiceImpl implements Pm4011Service {
	
	/** The pm 4011 BS. */
	@Autowired
	private Pm4011BS pm4011BS;

	/**
	 * Gets the pm 4011 BS.
	 *
	 * @return the pm 4011 BS
	 */
	public Pm4011BS getPm4011BS() {
		return pm4011BS;
	}

	/**
	 * Sets the pm 4011 BS.
	 *
	 * @param pm4011bs the new pm 4011 BS
	 */
	public void setPm4011BS(Pm4011BS pm4011bs) {
		pm4011BS = pm4011bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4011Service#save(com.gem.sistema.business.domain.Pm4011)
	 */
	@Override
	public List<Pm4011> save(Pm4011 pm4011) {

		return this.getPm4011BS().save(pm4011);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4011Service#delete(com.gem.sistema.business.domain.Pm4011)
	 */
	@Override
	public List<Pm4011> delete(Pm4011 pm4011) {

		return this.getPm4011BS().delete(pm4011);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4011Service#clean(com.gem.sistema.business.domain.Pm4011)
	 */
	@Override
	public List<Pm4011> clean(Pm4011 pm4011) {

		return null;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4011Service#cancel(com.gem.sistema.business.domain.Pm4011)
	 */
	@Override
	public List<Pm4011> cancel(Pm4011 pm4011) {

		return null;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4011Service#orderByClaveAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm4011> orderByClaveAsc(Integer idSector) {

		return this.getPm4011BS().orderByClaveAsc(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4011Service#oderBy()
	 */
	@Override
	public Sort oderBy() {

		return null;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4011Service#findByClave(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean findByClave(Integer clave, Integer idSector) {

		return this.getPm4011BS().findByClave(clave, idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4011Service#validateTxt(com.gem.sistema.business.domain.Pm4011)
	 */
	@Override
	public boolean validateTxt(Pm4011 pm4011) {
		return this.getPm4011BS().validateTxt(pm4011);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4011Service#add()
	 */
	@Override
	public Pm4011 add() {

		return this.getPm4011BS().add();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4011Service#loadData(java.lang.Integer)
	 */
	@Override
	public Pm4011 loadData(Integer idSector) {

		return this.getPm4011BS().loadData(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4011Service#update(com.gem.sistema.business.domain.Pm4011)
	 */
	@Override
	public List<Pm4011> update(Pm4011 pm4011) {
		return this.getPm4011BS().update(pm4011);
	}

}
