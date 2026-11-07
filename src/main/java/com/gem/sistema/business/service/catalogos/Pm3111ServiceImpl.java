package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm3111BS;
import com.gem.sistema.business.domain.Pm3111;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm3111ServiceImpl.
 */
@Service(value="pm3111Service")
public class Pm3111ServiceImpl implements Pm3111Service {

	/** The pm 3111 BS. */
	@Autowired
	private Pm3111BS pm3111BS;
	
	/**
	 * Gets the pm 3111 BS.
	 *
	 * @return the pm 3111 BS
	 */
	public Pm3111BS getPm3111BS() {
		return pm3111BS;
	}

	/**
	 * Sets the pm 3111 BS.
	 *
	 * @param pm3111bs the new pm 3111 BS
	 */
	public void setPm3111BS(Pm3111BS pm3111bs) {
		pm3111BS = pm3111bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3111Service#save(com.gem.sistema.business.domain.Pm3111)
	 */
	@Override
	public boolean save(Pm3111 pm3111) {
		return this.pm3111BS.save(pm3111);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3111Service#delete(com.gem.sistema.business.domain.Pm3111)
	 */
	@Override
	public void delete(Pm3111 pm3111) {
		this.pm3111BS.delete(pm3111);
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3111Service#clean(com.gem.sistema.business.domain.Pm3111)
	 */
	@Override
	public Pm3111 clean(Pm3111 pm3111) {
		
		return this.pm3111BS.clean(pm3111);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm3111Service#cancel(com.gem.sistema.business.domain.Pm3111)
	 */
	@Override
	public void cancel(Pm3111 pm3111) {
		this.pm3111BS.cancel(pm3111);
		
	}

}
