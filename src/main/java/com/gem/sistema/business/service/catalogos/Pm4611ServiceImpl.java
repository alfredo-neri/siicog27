package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm4611BS;
import com.gem.sistema.business.domain.Pm4611;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm4611ServiceImpl.
 */
@Service(value="pm4611Service")
public class Pm4611ServiceImpl implements Pm4611Service {

	/** The pm 4611 BS. */
	@Autowired
	private Pm4611BS pm4611BS;
	
	/**
	 * Gets the pm 4611 BS.
	 *
	 * @return the pm 4611 BS
	 */
	public Pm4611BS getPm4611BS() {
		return pm4611BS;
	}

	/**
	 * Sets the pm 4611 BS.
	 *
	 * @param pm4611bs the new pm 4611 BS
	 */
	public void setPm4611BS(Pm4611BS pm4611bs) {
		pm4611BS = pm4611bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4611Service#save(com.gem.sistema.business.domain.Pm4611)
	 */
	@Override
	public boolean save(Pm4611 pm4611) {
		return this.pm4611BS.save(pm4611);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4611Service#delete(com.gem.sistema.business.domain.Pm4611)
	 */
	@Override
	public void delete(Pm4611 pm4611) {
		this.pm4611BS.delete(pm4611);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4611Service#clean(com.gem.sistema.business.domain.Pm4611)
	 */
	@Override
	public Pm4611 clean(Pm4611 pm4611) {
		
		return this.pm4611BS.clean(pm4611);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4611Service#cancel(com.gem.sistema.business.domain.Pm4611)
	 */
	@Override
	public void cancel(Pm4611 pm4611) {
		this.pm4611BS.cancel(pm4611);
		
	}

}
