package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm0711BS;
import com.gem.sistema.business.domain.Pm0711;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm0711ServiceImpl.
 */
@Service(value="pm0711Service")
public class Pm0711ServiceImpl implements Pm0711Service {

	/** The pm 0711 BS. */
	@Autowired
	private Pm0711BS pm0711BS;
	
	/**
	 * Gets the pm 0711 BS.
	 *
	 * @return the pm 0711 BS
	 */
	public Pm0711BS getPm0711BS() {
		return pm0711BS;
	}

	/**
	 * Sets the pm 0711 BS.
	 *
	 * @param pm0711bs the new pm 0711 BS
	 */
	public void setPm0711BS(Pm0711BS pm0711bs) {
		pm0711BS = pm0711bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0711Service#save(com.gem.sistema.business.domain.Pm0711)
	 */
	@Override
	public boolean save(Pm0711 pm0711) {
		return this.pm0711BS.save(pm0711);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0711Service#delete(com.gem.sistema.business.domain.Pm0711)
	 */
	@Override
	public void delete(Pm0711 pm0711) {
		this.pm0711BS.delete(pm0711);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0711Service#clean(com.gem.sistema.business.domain.Pm0711)
	 */
	@Override
	public Pm0711 clean(Pm0711 pm0711) {
		
		return this.pm0711BS.clean(pm0711);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0711Service#cancel(com.gem.sistema.business.domain.Pm0711)
	 */
	@Override
	public void cancel(Pm0711 pm0711) {
		this.pm0711BS.cancel(pm0711);
		
	}

}
