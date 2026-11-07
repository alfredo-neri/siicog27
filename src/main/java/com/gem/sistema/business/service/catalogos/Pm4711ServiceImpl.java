package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm4711BS;
import com.gem.sistema.business.domain.Pm4711;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm4711ServiceImpl.
 */
@Service(value="pm4711Service")
public class Pm4711ServiceImpl implements Pm4711Service {

	/** The pm 4711 BS. */
	@Autowired
	private Pm4711BS pm4711BS;
	
	/**
	 * Gets the pm 4711 BS.
	 *
	 * @return the pm 4711 BS
	 */
	public Pm4711BS getPm4711BS() {
		return pm4711BS;
	}

	/**
	 * Sets the pm 4711 BS.
	 *
	 * @param pm4711bs the new pm 4711 BS
	 */
	public void setPm4711BS(Pm4711BS pm4711bs) {
		pm4711BS = pm4711bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4711Service#save(com.gem.sistema.business.domain.Pm4711)
	 */
	@Override
	public boolean save(Pm4711 pm4711) {
		return this.pm4711BS.save(pm4711);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4711Service#delete(com.gem.sistema.business.domain.Pm4711)
	 */
	@Override
	public void delete(Pm4711 pm4711) {
		this.pm4711BS.delete(pm4711);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4711Service#clean(com.gem.sistema.business.domain.Pm4711)
	 */
	@Override
	public Pm4711 clean(Pm4711 pm4711) {
		
		return this.pm4711BS.clean(pm4711);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm4711Service#cancel(com.gem.sistema.business.domain.Pm4711)
	 */
	@Override
	public void cancel(Pm4711 pm4711) {
		this.pm4711BS.cancel(pm4711);
		
	}

}
