package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm5311BS;
import com.gem.sistema.business.domain.Pm5311;


// TODO: Auto-generated Javadoc
/**
 * The Class Pm5311ServiceImpl.
 *
 * @author Alfredo
 */
@Service(value="pm5311Service")
public class Pm5311ServiceImpl implements Pm5311Service {

	/** The pm 5311 BS. */
	@Autowired
	private Pm5311BS pm5311BS;
	
	
	/**
	 * Gets the pm 5311 BS.
	 *
	 * @return the pm 5311 BS
	 */
	public Pm5311BS getPm5311BS() {
		return pm5311BS;
	}
	
	/**
	 * Sets the pm 5311 BS.
	 *
	 * @param pm5311bs the new pm 5311 BS
	 */
	public void setPm5311BS(Pm5311BS pm5311bs) {
		pm5311BS = pm5311bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm5311Service#save(com.gem.sistema.business.domain.Pm5311)
	 */
	public boolean save(Pm5311 pm5311) {
		return this.pm5311BS.save(pm5311);
		
	}
}
