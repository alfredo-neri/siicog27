package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm1011BS;
import com.gem.sistema.business.domain.Pm1011;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm1011ServiceImpl.
 */
@Service(value="pm1011Service")
public class Pm1011ServiceImpl implements Pm1011Service {

	/** The pm 1011 BS. */
	@Autowired
	private Pm1011BS pm1011BS;
	
	/**
	 * Gets the pm 1011 BS.
	 *
	 * @return the pm 1011 BS
	 */
	public Pm1011BS getPm1011BS() {
		return pm1011BS;
	}

	/**
	 * Sets the pm 1011 BS.
	 *
	 * @param pm1011bs the new pm 1011 BS
	 */
	public void setPm1011BS(Pm1011BS pm1011bs) {
		pm1011BS = pm1011bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm1011Service#save(com.gem.sistema.business.domain.Pm1011)
	 */
	@Override
	public boolean save(Pm1011 pm1011) {
		return this.pm1011BS.save(pm1011);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm1011Service#delete(com.gem.sistema.business.domain.Pm1011)
	 */
	@Override
	public void delete(Pm1011 pm1011) {
		this.pm1011BS.delete(pm1011);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm1011Service#clean(com.gem.sistema.business.domain.Pm1011)
	 */
	@Override
	public Pm1011 clean(Pm1011 pm1011) {
		
		return this.pm1011BS.clean(pm1011);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm1011Service#cancel(com.gem.sistema.business.domain.Pm1011)
	 */
	@Override
	public void cancel(Pm1011 pm1011) {
		this.pm1011BS.cancel(pm1011);
		
	}

	@Override
	public void update(Pm1011 pm1011) {
		this.pm1011BS.update(pm1011);
		
	}

}
