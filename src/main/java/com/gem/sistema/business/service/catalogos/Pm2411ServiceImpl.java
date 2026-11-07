package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm2411BS;
import com.gem.sistema.business.domain.Pm2411;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm2411ServiceImpl.
 */
@Service(value="pm2411Service")
public class Pm2411ServiceImpl implements Pm2411Service {

	/** The pm 2411 BS. */
	@Autowired
	private Pm2411BS pm2411BS;
	
	/**
	 * Gets the pm 2411 BS.
	 *
	 * @return the pm 2411 BS
	 */
	public Pm2411BS getPm2411BS() {
		return pm2411BS;
	}

	/**
	 * Sets the pm 2411 BS.
	 *
	 * @param pm2411bs the new pm 2411 BS
	 */
	public void setPm2411BS(Pm2411BS pm2411bs) {
		pm2411BS = pm2411bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2411Service#save(com.gem.sistema.business.domain.Pm2411)
	 */
	@Override
	public boolean save(Pm2411 pm2411) {
		return this.pm2411BS.save(pm2411);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2411Service#delete(com.gem.sistema.business.domain.Pm2411)
	 */
	@Override
	public void delete(Pm2411 pm2411) {
		this.pm2411BS.delete(pm2411);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2411Service#clean(com.gem.sistema.business.domain.Pm2411)
	 */
	@Override
	public Pm2411 clean(Pm2411 pm2411) {
		
		return this.pm2411BS.clean(pm2411);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2411Service#cancel(com.gem.sistema.business.domain.Pm2411)
	 */
	@Override
	public void cancel(Pm2411 pm2411) {
		this.pm2411BS.cancel(pm2411);
		
	}

}
