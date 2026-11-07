package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm4211BS;
import com.gem.sistema.business.domain.Pm4211;


// TODO: Auto-generated Javadoc
/**
 * The Class Pm4211ServiceImpl.
 *
 * @author Alfredo
 */
@Service(value="pm4211Service")
public class Pm4211ServiceImpl implements Pm4211Service {

	/** The pm 4211 BS. */
	@Autowired
	private Pm4211BS pm4211BS;
	
	
	/**
	 * Gets the pm 4211 BS.
	 *
	 * @return the pm 4211 BS
	 */
	public Pm4211BS getPm4211BS() {
		return pm4211BS;
	}
	
	/**
	 * Sets the pm 4211 BS.
	 *
	 * @param pm4211bs the new pm 4211 BS
	 */
	public void setPm4211BS(Pm4211BS pm4211bs) {
		pm4211BS = pm4211bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4211Service#save(com.gem.sistema.business.domain.Pm4211)
	 */
	public boolean save(Pm4211 pm4211) {
		return this.pm4211BS.save(pm4211);
		
	}
}
