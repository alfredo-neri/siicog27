package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm4511BS;
import com.gem.sistema.business.domain.Pm4511;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm4511ServiceImpl.
 */
@Service(value="pm4511Service")
public class Pm4511ServiceImpl implements Pm4511Service {

	/** The pm 4511 BS. */
	@Autowired
	private Pm4511BS pm4511BS;
	
	/**
	 * Gets the pm 4511 BS.
	 *
	 * @return the pm 4511 BS
	 */
	public Pm4511BS getPm4511BS() {
		return pm4511BS;
	}

	/**
	 * Sets the pm 4511 BS.
	 *
	 * @param pm4511bs the new pm 4511 BS
	 */
	public void setPm4511BS(Pm4511BS pm4511bs) {
		pm4511BS = pm4511bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4511Service#save(com.gem.sistema.business.domain.Pm4511)
	 */
	@Override
	public boolean save(Pm4511 pm4511) {
		return this.pm4511BS.save(pm4511);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4511Service#delete(com.gem.sistema.business.domain.Pm4511)
	 */
	@Override
	public void delete(Pm4511 pm4511) {
		this.pm4511BS.delete(pm4511);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4511Service#clean(com.gem.sistema.business.domain.Pm4511)
	 */
	@Override
	public Pm4511 clean(Pm4511 pm4511) {
		
		return this.pm4511BS.clean(pm4511);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4511Service#cancel(com.gem.sistema.business.domain.Pm4511)
	 */
	@Override
	public void cancel(Pm4511 pm4511) {
		this.pm4511BS.cancel(pm4511);
		
	}

}
