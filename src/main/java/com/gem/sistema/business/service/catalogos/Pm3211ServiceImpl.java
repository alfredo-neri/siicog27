package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm3211BS;
import com.gem.sistema.business.domain.Pm3211;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm3211ServiceImpl.
 */
@Service(value="pm3211Service")
public class Pm3211ServiceImpl implements Pm3211Service {

	/** The pm 3211 BS. */
	@Autowired
	private Pm3211BS pm3211BS;
	
	/**
	 * Gets the pm 3211 BS.
	 *
	 * @return the pm 3211 BS
	 */
	public Pm3211BS getPm3211BS() {
		return pm3211BS;
	}

	/**
	 * Sets the pm 3211 BS.
	 *
	 * @param pm3211bs the new pm 3211 BS
	 */
	public void setPm3211BS(Pm3211BS pm3211bs) {
		pm3211BS = pm3211bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3211Service#save(com.gem.sistema.business.domain.Pm3211)
	 */
	@Override
	public boolean save(Pm3211 pm3211) {
		return this.pm3211BS.save(pm3211);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3211Service#delete(com.gem.sistema.business.domain.Pm3211)
	 */
	@Override
	public void delete(Pm3211 pm3211) {
		this.pm3211BS.delete(pm3211);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3211Service#clean(com.gem.sistema.business.domain.Pm3211)
	 */
	@Override
	public Pm3211 clean(Pm3211 pm3211) {
		
		return this.pm3211BS.clean(pm3211);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3211Service#cancel(com.gem.sistema.business.domain.Pm3211)
	 */
	@Override
	public void cancel(Pm3211 pm3211) {
		this.pm3211BS.cancel(pm3211);
		
	}

}
