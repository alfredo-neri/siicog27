package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm4111BS;
import com.gem.sistema.business.domain.Pm4111;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm4111ServiceImpl.
 */
@Service(value="pm4111Service")
public class Pm4111ServiceImpl implements Pm4111Service {

	/** The pm 4111 BS. */
	@Autowired
	private Pm4111BS pm4111BS;
	
	/**
	 * Gets the pm 4111 BS.
	 *
	 * @return the pm 4111 BS
	 */
	public Pm4111BS getPm4111BS() {
		return pm4111BS;
	}

	/**
	 * Sets the pm 4111 BS.
	 *
	 * @param pm4111bs the new pm 4111 BS
	 */
	public void setPm4111BS(Pm4111BS pm4111bs) {
		pm4111BS = pm4111bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4111Service#save(com.gem.sistema.business.domain.Pm4111)
	 */
	@Override
	public boolean save(Pm4111 pm4111) {
		return this.pm4111BS.save(pm4111);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4111Service#delete(com.gem.sistema.business.domain.Pm4111)
	 */
	@Override
	public void delete(Pm4111 pm4111) {
		this.pm4111BS.delete(pm4111);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4111Service#clean(com.gem.sistema.business.domain.Pm4111)
	 */
	@Override
	public Pm4111 clean(Pm4111 pm4111) {
		
		return this.pm4111BS.clean(pm4111);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4111Service#cancel(com.gem.sistema.business.domain.Pm4111)
	 */
	@Override
	public void cancel(Pm4111 pm4111) {
		this.pm4111BS.cancel(pm4111);
		
	}

}
