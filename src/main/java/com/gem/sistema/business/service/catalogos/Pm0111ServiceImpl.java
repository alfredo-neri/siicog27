package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm0111BS;
import com.gem.sistema.business.domain.Pm0111;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm0111ServiceImpl.
 */
@Service(value="pm0111Service")
public class Pm0111ServiceImpl implements Pm0111Service {

	/** The pm 0111 BS. */
	@Autowired
	private Pm0111BS pm0111BS;
	
	/**
	 * Gets the pm 0111 BS.
	 *
	 * @return the pm 0111 BS
	 */
	public Pm0111BS getPm0111BS() {
		return pm0111BS;
	}

	/**
	 * Sets the pm 0111 BS.
	 *
	 * @param pm0111bs the new pm 0111 BS
	 */
	public void setPm0111BS(Pm0111BS pm0111bs) {
		pm0111BS = pm0111bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0111Service#save(com.gem.sistema.business.domain.Pm0111)
	 */
	@Override
	public boolean save(Pm0111 pm0111) {
		return this.pm0111BS.save(pm0111);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0111Service#delete(com.gem.sistema.business.domain.Pm0111)
	 */
	@Override
	public void delete(Pm0111 pm0111) {
		this.pm0111BS.delete(pm0111);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0111Service#clean(com.gem.sistema.business.domain.Pm0111)
	 */
	@Override
	public Pm0111 clean(Pm0111 pm0111) {
		
		return this.pm0111BS.clean(pm0111);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm0111Service#cancel(com.gem.sistema.business.domain.Pm0111)
	 */
	@Override
	public void cancel(Pm0111 pm0111) {
		this.pm0111BS.cancel(pm0111);
		
	}

}
