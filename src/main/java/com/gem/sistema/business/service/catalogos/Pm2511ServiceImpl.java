package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.Pm2511BS;
import com.gem.sistema.business.domain.Pm2511;

// TODO: Auto-generated Javadoc
/**
 * The Class Pm2511ServiceImpl.
 *
 * @author Mateo
 */
@Service(value = "pm2511Service")
public class Pm2511ServiceImpl implements Pm2511Service{
	
	/** The pm 2511 BS. */
	@Autowired
	private Pm2511BS pm2511BS;
	
	
	
	/**
	 * Gets the pm 2511 BS.
	 *
	 * @return the pm 2511 BS
	 */
	public Pm2511BS getPm2511BS() {
		return pm2511BS;
	}

	/**
	 * Sets the pm 2511 BS.
	 *
	 * @param pm2511bs the new pm 2511 BS
	 */
	public void setPm2511BS(Pm2511BS pm2511bs) {
		pm2511BS = pm2511bs;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2511Service#save(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2511> save(Integer index, List<Pm2511> listPm2511) {
		return this.pm2511BS.save(index, listPm2511);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2511Service#delete(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2511> delete(Integer index, List<Pm2511> listPm2511) {
		return this.pm2511BS.delete(index, listPm2511);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2511Service#clean(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2511> clean(Integer index, List<Pm2511> listPm2511) {
		return this.pm2511BS.clean(index, listPm2511);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2511Service#cancel(java.lang.Integer, java.util.List)
	 */
	@Override
	public List<Pm2511> cancel(Integer index, List<Pm2511> listPm2511) {
		return this.pm2511BS.cancel(index, listPm2511);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2511Service#orderByTrimestreAsc(java.lang.Integer)
	 */
	@Override
	public List<Pm2511> orderByTrimestreAsc(Integer idSector) {
		return this.pm2511BS.orderByTrimestreAsc(idSector);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.Pm2511Service#add()
	 */
	@Override
	public Pm2511 add() {
		return this.pm2511BS.add();
	}

}
