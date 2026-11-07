package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm3311BS;
import com.gem.sistema.business.domain.Pm3311;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm3311ServiceImpl.
 */
@Service(value="pm3311Service")
public class Pm3311ServiceImpl implements Pm3311Service {

	/** The pm 3311 BS. */
	@Autowired
	private Pm3311BS pm3311BS;
	
	/**
	 * Gets the pm 3311 BS.
	 *
	 * @return the pm 3311 BS
	 */
	public Pm3311BS getPm3311BS() {
		return pm3311BS;
	}

	/**
	 * Sets the pm 3311 BS.
	 *
	 * @param pm3311bs the new pm 3311 BS
	 */
	public void setPm3311BS(Pm3311BS pm3311bs) {
		pm3311BS = pm3311bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3311Service#save(com.gem.sistema.business.domain.Pm3311)
	 */
	@Override
	public boolean save(Pm3311 pm3311) {
		return this.pm3311BS.save(pm3311);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3311Service#delete(com.gem.sistema.business.domain.Pm3311)
	 */
	@Override
	public void delete(Pm3311 pm3311) {
		this.pm3311BS.delete(pm3311);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3311Service#clean(com.gem.sistema.business.domain.Pm3311)
	 */
	@Override
	public Pm3311 clean(Pm3311 pm3311) {
		
		return this.pm3311BS.clean(pm3311);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3311Service#cancel(com.gem.sistema.business.domain.Pm3311)
	 */
	@Override
	public void cancel(Pm3311 pm3311) {
		this.pm3311BS.cancel(pm3311);
		
	}

}
