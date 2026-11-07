package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm4411BS;
import com.gem.sistema.business.domain.Pm4411;


// TODO: Auto-generated Javadoc
/**
 * The Class Pm4411ServiceImpl.
 *
 * @author Alfredo
 */
@Service(value="pm4411Service")
public class Pm4411ServiceImpl implements Pm4411Service {

	/** The pm 4411 BS. */
	@Autowired
	private Pm4411BS pm4411BS;
	
	
	/**
	 * Gets the pm 4411 BS.
	 *
	 * @return the pm 4411 BS
	 */
	public Pm4411BS getPm4411BS() {
		return pm4411BS;
	}
	
	/**
	 * Sets the pm 4411 BS.
	 *
	 * @param pm4411bs the new pm 4411 BS
	 */
	public void setPm4411BS(Pm4411BS pm4411bs) {
		pm4411BS = pm4411bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4411Service#save(com.gem.sistema.business.domain.Pm4411)
	 */
	public boolean save(Pm4411 pm4411) {
		return this.pm4411BS.save(pm4411);
		
	}
}
