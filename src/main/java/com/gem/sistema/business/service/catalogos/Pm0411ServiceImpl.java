package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm0411BS;
import com.gem.sistema.business.domain.Pm0411;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm0411ServiceImpl.
 *
 * @author Mateo
 */
@Service(value = "pm0411Service")
public class Pm0411ServiceImpl implements Pm0411Service {
	
	/** The pm 0411 BS. */
	@Autowired
	private Pm0411BS pm0411BS;
	
	/**
	 * Gets the pm 0411 BS.
	 *
	 * @return the pm 0411 BS
	 */
	public Pm0411BS getPm0411BS() {
		return pm0411BS;
	}

	/**
	 * Sets the pm 0411 BS.
	 *
	 * @param pm0411bs the new pm 0411 BS
	 */
	public void setPm0411BS(Pm0411BS pm0411bs) {
		pm0411BS = pm0411bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0411Service#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm0411> save(Integer index, List<Pm0411> listPm0411) {
		return this.pm0411BS.save(index, listPm0411);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0411Service#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm0411> delete(Integer index, List<Pm0411> listPm0411) {
		return this.pm0411BS.delete(index, listPm0411);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0411Service#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm0411> clean(Integer index, List<Pm0411> listPm0411) {
		return this.pm0411BS.clean(index, listPm0411);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0411Service#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm0411> cancel(Integer index, List<Pm0411> listPm0411) {
		return this.pm0411BS.cancel(index, listPm0411);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0411Service#orderByTrimestreAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm0411> orderByTrimestreAsc(Integer idSector) {
		return this.pm0411BS.orderByTrimestreAsc(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0411Service#add()
	 */
	@Override
	public Pm0411 add() {
		return this.pm0411BS.add();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0411Service#calculationAccumulated(java.lang.Integer)
	 */
	@Override
	public List<Pm0411> calculationAccumulated(Integer idSector) {
		return this.pm0411BS.calculationAccumulated(idSector);
	}

}
