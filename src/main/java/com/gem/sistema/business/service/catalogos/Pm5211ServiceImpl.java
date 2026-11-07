package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm5211BS;
import com.gem.sistema.business.domain.Pm5211;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm5211ServiceImpl.
 */
@Service(value = "pm5211Service")
public class Pm5211ServiceImpl implements Pm5211Service {
	
	/** The pm 5211 BS. */
	@Autowired
	private Pm5211BS pm5211BS;
	
	
	/**
	 * Gets the pm 5211 BS.
	 *
	 * @return the pm 5211 BS
	 */
	public Pm5211BS getPm5211BS() {
		return pm5211BS;
	}

	/**
	 * Sets the pm 5211 BS.
	 *
	 * @param pm5211bs the new pm 5211 BS
	 */
	public void setPm5211BS(Pm5211BS pm5211bs) {
		pm5211BS = pm5211bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5211Service#save(java.util.List, java.lang.Integer)
	 */
	@Override
	public List<Pm5211> save(List<Pm5211> listPm5211, Integer index) {
		return this.pm5211BS.save(listPm5211, index);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5211Service#delete(java.util.List, java.lang.Integer)
	 */
	@Override
	public List<Pm5211> delete(List<Pm5211> listPm5211, Integer index) {
		// TODO Auto-generated method stub
		return this.pm5211BS.delete(listPm5211, index);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5211Service#add(java.util.List, java.lang.Integer)
	 */
	@Override
	public List<Pm5211> add(List<Pm5211> listPm5211, Integer index) {
		return this.pm5211BS.add(listPm5211, index);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5211Service#clean(java.util.List, java.lang.Integer)
	 */
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5211Service#clean(java.util.List, java.lang.Integer)
	 */
	@Override
	public List<Pm5211> clean(List<Pm5211> listPm5211, Integer index) {
		return this.pm5211BS.clean(listPm5211, index);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5211Service#cancel(java.util.List, java.lang.Integer)
	 */
	@Override
	public List<Pm5211> cancel(List<Pm5211> listPm5211, Integer index) {
		return this.pm5211BS.cancel(listPm5211, index);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5211Service#orderByMendsualAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm5211> orderByMendsualAsc(Integer idSector) {
		return this.pm5211BS.orderByMendsualAsc(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5211Service#aculation(java.lang.Integer)
	 */
	@Override
	public List<Pm5211> aculation(Integer idSector) {
		return this.getPm5211BS().aculation(idSector);
	}

}
